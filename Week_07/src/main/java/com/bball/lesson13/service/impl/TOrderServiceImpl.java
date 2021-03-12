package com.bball.lesson13.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bball.lesson13.dao.OrderDao;
import com.bball.lesson13.entity.TOrder;
import com.bball.lesson13.service.ITOrderService;
import com.bball.lesson13.util.SnowFlakeUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-11
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<OrderDao, TOrder> implements ITOrderService {
    private static ThreadLocal<SnowFlakeUtil> threadLocal = new ThreadLocal<>();

    public void batchInsertOrder(){
        System.out.println("########开始批量插入数据#########");
        CountDownLatch latch = new CountDownLatch(10);
        long startTime = System.currentTimeMillis();
        // 批量插入，每次1万条
        for(int i = 0;i < 10;i++){
            new Thread(new InsertThread2(latch, i, i)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long costSecond = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("批量插入数据完成，耗时：" + costSecond + "秒");
    }

    class InsertThread2 implements Runnable{
        CountDownLatch latch;
        private long workerId; // 雪花算法，机器ID
        private long datacenterId; // 雪花算法，数据中心ID

        public InsertThread2(CountDownLatch latch, long workerId, long datacenterId) {
            this.latch = latch;
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        @Override
        public void run() {
            // 每个线程一个雪花算法实例
            SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(workerId,datacenterId);
            threadLocal.set(snowFlakeUtil);
            try {
                // 批量插入，每次1万条
                for(int i = 0;i < 10;i++){
                    // 生成订单集合
                    List<TOrder> orderList = generateOrderList();
                    saveBatch(orderList);
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            } finally {
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

}
