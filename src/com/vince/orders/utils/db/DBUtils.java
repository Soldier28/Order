package com.vince.orders.utils.db;

/**
 * @author 兵哥哥
 * @create 2018-09-30 16:35
 * @desc
 **/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {

    //定义一个获取数据库连接的方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //创建数据源(c3p0组件)
            DataSource ds = new ComboPooledDataSource("dbInfo");
            conn = ds.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param rs
     * @param state
     * @param conn
     */
    public static void close(ResultSet rs,Statement state,Connection conn) {
        try {
            if(rs != null) rs.close();
            if(state != null) state.close();
            if(conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

