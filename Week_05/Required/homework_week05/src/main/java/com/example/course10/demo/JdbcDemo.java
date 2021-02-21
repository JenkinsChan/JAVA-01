package com.example.course10.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcDemo {
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcDemo.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            url = properties.getProperty("mysql.url");
            user = properties.getProperty("mysql.user");
            password = properties.getProperty("mysql.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insert();
        query();
        update();
        delete();

        // 批量，事务，PreparedStatement
        exeBatchSql();

        hikariCP();
    }

    public static void query() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            conn = DriverManager.getConnection(url, user, password);
            //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            st = conn.createStatement();
            rs = st.executeQuery("select * from student");
            //4.处理数据库的返回结果(使用ResultSet类)
            while(rs.next()){
                System.out.println("查询到数据：id=" + rs.getString("id")+",name="
                        +rs.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭资源
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (st != null) {
                    st.close();
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

    private static void insert(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            int count = stmt.executeUpdate("insert into student(id,name) values ('1','xiaoming')");
            System.out.println("插入数据的行数：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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

    private static void delete(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            int count = stmt.executeUpdate("delete from student where id='1'");
            System.out.println("删除的行数：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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

    private static void update(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            int count = stmt.executeUpdate("update student set name='bball' where id='1'");
            System.out.println("修改的行数：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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

    private static void exeBatchSql(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库链接
            conn = DriverManager.getConnection(url, user, password);
            // 关闭自动提交，即开启事务
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("insert into student(id,name) values (?,?)");
            // 预定义参数
            pstmt.setString(1,"101");
            pstmt.setString(2,"Lily");
            pstmt.addBatch();
            pstmt.setString(1,"102");
            pstmt.setString(2,"John");
            pstmt.addBatch();
            // 添加静态SQL
            pstmt.addBatch("update student set name='bball' where id='101'");
            pstmt.addBatch("delete from  student where id in ('101','102')");
            // 批量执行预定义SQL
            int[] exeNums = pstmt.executeBatch();
            // 提交事务
            conn.commit();
            System.out.println("批量执行结果：");
            for (int num : exeNums){
                System.out.println(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    private static void hikariCP(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            HikariConfig config = new HikariConfig(JdbcDemo.class.getClassLoader().getResource("hikari.properties").getPath());
//        config.addDataSourceProperty()
            HikariDataSource ds = new HikariDataSource(config);

            conn = ds.getConnection();
            // 关闭自动提交，即开启事务
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement("insert into student(id,name) values (?,?)");
            // 预定义参数
            pstmt.setString(1,"101");
            pstmt.setString(2,"Lily");
            pstmt.addBatch();
            pstmt.setString(1,"102");
            pstmt.setString(2,"John");
            pstmt.addBatch();
            // 添加静态SQL
            pstmt.addBatch("update student set name='bball' where id='101'");
            pstmt.addBatch("delete from  student where id in ('101','102')");
            // 批量执行预定义SQL
            int[] exeNums = pstmt.executeBatch();
            // 提交事务
            conn.commit();
            System.out.println("批量执行结果：");
            for (int num : exeNums){
                System.out.println(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
}
