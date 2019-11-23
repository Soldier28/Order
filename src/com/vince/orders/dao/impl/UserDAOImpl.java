package com.vince.orders.dao.impl;

import com.vince.orders.utils.db.DBUtils;
import com.vince.orders.vo.User;
import com.vince.orders.dao.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * @author 兵哥哥
 * @create 2018-10-03 8:38
 * @desc User的实现类
 **/
public class UserDAOImpl implements IUserDAO {

    //用户注册
    @Override
    public void register(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into user(userName,passWord,createdDate,lastmodifyDate) values(?,?,?,?)";
        user.setCreatedDate(new Date());
        user.setLastmodifyDate(new Date());
        try {
            conn = DBUtils.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setDate(3, new java.sql.Date(user.getCreatedDate().getTime()));
            ps.setDate(4, new java.sql.Date(user.getLastmodifyDate().getTime()));
            //执行sql语句
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("用户：【"+user.getUserName()+"】的注册失败！");
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    /**
     * 根据用户名和密码登录系统
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public User login(String username, String password) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from user where userid=? and password=? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            //执行sql语句并用rs接收返回结果
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassWord(rs.getString(3));
                user.setCreatedDate(rs.getDate(4));
                user.setLastmodifyDate(rs.getDate(5) );  //new Date(user.getLastmodifyDate().getTime())
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("用户名或密码错误");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return user;
    }

    //修改用户信息
    @Override
    public void update(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update user set username=?,password=?,LastmodifyDate=? where userid=? ";
        user.setLastmodifyDate(new Date());
        try {
            conn = DBUtils.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setDate(3, new java.sql.Date(user.getLastmodifyDate().getTime()));
            ps.setInt(4, user.getUserId());
            //执行sql语句
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("修改用户：【"+user.getUserName()+"】的个人信息失败！");
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }
}
