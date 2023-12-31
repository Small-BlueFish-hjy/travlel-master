package com.hjq.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: hjq
 * @CreateTime: 2023-07-28  22:42
 * @Description: jdbc工具类
 * @Version: 1.0
 */

/*
    1.声明静态数据源成员变量
    2.创建连接池对象
    3.定义公有的得到数据源的方法
    4.定义得到连接对象的方法
    5.定义关闭资源的方法
 */
public class JDBCUtils {

    //1.声明静态数据源成员变量
    private static DataSource ds;

    //2.创建连接池对象
    static {
        //加载配置文件中的数据
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try{
            properties.load(is);
            //创建连接池，使用配置文件中的参数
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.定义公有的得到数据源的方法
    public static DataSource getDataSources(){
        return ds;
    }

    //4.定义得到连接对象的方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //5.定义关闭资源的方法
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {

        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
