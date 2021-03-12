package com.bball.lesson13.service;

import com.bball.lesson13.entity.TOrder;
import com.bball.lesson13.util.SnowFlakeUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class OrderService {
    private static ThreadLocal<SnowFlakeUtil> threadLocal = new ThreadLocal<>();
    private static HikariDataSource ds = null;
    static {
        // 初始化连接池
        HikariConfig config = new HikariConfig(OrderService.class.getClassLoader().getResource("hikari.properties").getPath());
        // 开启批量提交参数
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        ds = new HikariDataSource(config);
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        try {
            CountDownLatch latch = new CountDownLatch(10);
            for(int i = 0;i < 10;i++){
                new Thread(new InsertThread(latch, 1+i, 1+i)).start();
            }
            // 等待所有工作线程结束才退出
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后关闭连接池
            if(ds != null){
                ds.close();
            }
        }
        long costSecond = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("耗时：" + costSecond + "秒");
    }

    static class InsertThread implements Runnable{
        CountDownLatch latch;
        private long workerId; // 雪花算法，机器ID
        private long datacenterId; // 雪花算法，数据中心ID

        public InsertThread(CountDownLatch latch, long workerId, long datacenterId) {
            this.latch = latch;
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        @Override
        public void run() {
            // 每个线程一个雪花算法实例
            SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(workerId,datacenterId);
            threadLocal.set(snowFlakeUtil);

            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = getConnection();
                pstmt = conn.prepareStatement("insert into t_order(id, status, user_id," +
                        "receiver_name,receiver_phone,receiver_addr,goods_id,payment_amt," +
                        "discount_amt,payment_time,dispatch_time,create_time,update_time) " +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                // 批量插入，每次1万条
                int batchSize = 10000;
                for(int i = 0;i < 10;i++){
                    // 生成订单集合
                    List<TOrder> orderList = generateOrderList();
                    int count = 0;
                    for(TOrder order:orderList){
                        // 预定义参数
                        pstmt.setLong(1,order.getId());
                        pstmt.setString(2,order.getStatus());
                        pstmt.setLong(3, order.getUserId());
                        pstmt.setString(4, order.getReceiverName());
                        pstmt.setString(5, order.getReceiverPhone());
                        pstmt.setString(6, order.getReceiverAddr());
                        pstmt.setLong(7, order.getGoodsId());
                        pstmt.setBigDecimal(8, order.getPaymentAmt());
                        pstmt.setBigDecimal(9, order.getDiscountAmt());
                        pstmt.setObject(10, order.getPaymentTime());
                        pstmt.setObject(11, order.getDispatchTime());
                        pstmt.setObject(12, order.getCreateTime());
                        pstmt.setObject(13, order.getUpdateTime());

                        pstmt.addBatch();
                        if(++count % batchSize == 0){
                            // 批量执行预定义SQL
                            pstmt.executeBatch();
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                // 关闭连接
                closeConnection(conn, pstmt);
                // 计数器减一
                latch.countDown();
                threadLocal.remove();
            }
        }
    }

    /**
     * 生成订单集合，每次生成1万条
     * @return
     */
    private static List<TOrder> generateOrderList(){
        List<TOrder> list = new LinkedList<>();
        for(int i = 0;i < 10000;i++){
            list.add(TOrder.builder()
                    .id(threadLocal.get().nextId())
                    .status("1")
                    .userId(threadLocal.get().nextId())
                    .receiverName("张三")
                    .receiverPhone("13431990911")
                    .receiverAddr("广东深圳")
                    .goodsId(threadLocal.get().nextId())
                    .paymentAmt(new BigDecimal(ThreadLocalRandom.current().nextDouble(1,10000)))
                    .discountAmt(new BigDecimal(10))
                    .paymentTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                    .dispatchTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                    .createTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                    .updateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))
                    .build());
        }
        return list;
    }

    private static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static void closeConnection(Connection conn, PreparedStatement pstmt){
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
