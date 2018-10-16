package com.vince.orders.dao.impl;

import com.vince.orders.utils.db.DBUtils;
import com.vince.orders.vo.Order;
import com.vince.orders.dao.IOrderDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 兵哥哥
 * @create 2018-10-03 9:40
 * @desc
 **/
public class OrderDAOImpl implements IOrderDAO {

    //添加一个订单
    @Override
    public void save(Order order) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into torder(OrderCode,Buyer,Sum,DeliveryMethod,LastmodifyDate,CreatedDate) " +
                "values(?,?,?,?,?,?)";
        order.setCreatedDate(new Date());  //当前时间
        order.setLastmodifyDate(new Date());
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,order.getOrderCode());
            ps.setString(2,order.getBuyer());
            ps.setDouble(3,order.getSum());
            ps.setString(4,order.getDeliveryMethod());
            ps.setDate(5, new java.sql.Date(order.getLastmodifyDate().getTime()));
            ps.setDate(6, new java.sql.Date(order.getCreatedDate().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    //根据订单编号修改订单
    @Override
    public void update(Order order) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update torder set OrderCode=?,Buyer=?,Sum=?,DeliveryMethod=?,LastmodifyDate=?," +
                "CreatedDate=? where orderid=? ";
        order.setLastmodifyDate(new Date());
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,order.getOrderCode());
            ps.setString(2,order.getBuyer());
            ps.setObject(3,order.getSum());
            ps.setString(4,order.getDeliveryMethod());
            ps.setDate(5, new java.sql.Date(order.getCreatedDate().getTime()));
            ps.setDate(6, new java.sql.Date(order.getLastmodifyDate().getTime()));
            ps.setInt(7,order.getOrderId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    //更新订单总金额
    @Override
    public void updateSum(int orderid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Double sum = 0.0;
        //用sum函数把所有订单明细的总金额相加，再用s表示出来
        String sql1 = "select sum(sum) as s from orderitem where orderid=?";
        String sql2 = "update torder set Sum=? where orderid=?";
        try {
            conn = DBUtils.getConnection();
            //禁用事务的自动提交模式
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,orderid);
            rs = ps.executeQuery();
            if (rs.next()) {
                sum = rs.getDouble("s");
            }
            ps = conn.prepareStatement(sql2);
            ps.setDouble(1,sum);
            ps.setInt(2,orderid);
            ps.executeUpdate();
            //事务的提交
            conn.commit();
        } catch (Exception e) {
            //事务的回滚
            conn.rollback();
            e.printStackTrace();
        } finally {
            DBUtils.close(rs,ps,conn);
        }
    }

    //根据id删除
    @Override
    public void deleteById(int orderid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from torder where OrderId=?";
        try {
            conn = DBUtils.getConnection();
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //赋值
            ps.setInt(1,orderid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    //根据id查找
    @Override
    public Order findById(int orderid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        String sql = "select * from torder where orderid=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderid);
            //执行sql语句并用rs接收返回结果
            rs = ps.executeQuery();
            order = new Order();
            while (rs.next()) {
                order.setOrderId(rs.getInt(1));
                order.setOrderCode(rs.getString(2));
                order.setBuyer(rs.getString(3));
                order.setSum(rs.getDouble(4));
                order.setDeliveryMethod(rs.getString(5));
                order.setLastmodifyDate(rs.getDate(6));
                order.setCreatedDate(rs.getDate(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("用户不存在！");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return order;
    }

    //查询，根据订单编号、订单金额和配送方式,
    // 这三个条件可能都有，可能只有一个，所以用到字符串拼接
    @Override
    public List<Order> search(String userName, String orderCode, Double sum, String deliveryMethod) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<Order>();
        Order order = null;
        //where 1=1是为了可以拼条件
        StringBuffer sql = new StringBuffer("select orderId,orderCode,deliveryMethod,sum,lastmodifyDate " +
                "from torder where Buyer=? ") ;

        if (orderCode!=null && !"".equals(orderCode)) {
            sql.append(" and orderCode like '%");
            sql.append(orderCode);
            sql.append("%'");
        }
        if (sum!=null) {
            sql.append(" and sum=");
            sql.append(sum);
        }
        if (deliveryMethod!=null && !"".equals(deliveryMethod)) {
            sql.append(" and deliveryMethod='");
            sql.append(deliveryMethod);
            sql.append("'");
        }

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,userName);
            //执行sql语句并用rs接收返回结果
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setOrderCode(rs.getString(2));
                order.setDeliveryMethod(rs.getString(3));
                order.setSum(rs.getDouble(4));
                order.setLastmodifyDate(rs.getDate(5));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("用户不存在！");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return orderList;
    }

    //通过用户名查询
    @Override
    public List<Order> findByBuyer(String BuyerName) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<Order>();
        Order order = null;
        String sql = "select OrderId,OrderCode,Buyer,Sum,DeliveryMethod,LastmodifyDate,CreatedDate" +
                " from torder where Buyer=? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, BuyerName);
            //执行sql语句并用rs接收返回结果
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt(1));
                order.setOrderCode(rs.getString(2));
                order.setBuyer(rs.getString(3));
                order.setSum(rs.getDouble(4));
                order.setDeliveryMethod(rs.getString(5));
                order.setLastmodifyDate(rs.getDate(6));
                order.setCreatedDate(rs.getDate(7));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("查看订单失败！");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return orderList;
    }
}
