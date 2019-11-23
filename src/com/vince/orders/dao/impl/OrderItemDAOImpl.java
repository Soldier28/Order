package com.vince.orders.dao.impl;

import com.vince.orders.dao.IOrderItemDAO;
import com.vince.orders.utils.db.DBUtils;
import com.vince.orders.vo.DiscountOrderItem;
import com.vince.orders.vo.OrderItem;
import com.vince.orders.vo.PromotionOrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 兵哥哥
 * @create 2018-10-03 9:40
 * @desc
 **/
public class OrderItemDAOImpl implements IOrderItemDAO {

    //根据id查找订单明细
    @Override
    public List<OrderItem> findByOrderId(Integer orderId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem orderItem = null;
        String sql = "select * from orderItem where orderId=? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            //执行sql语句并用rs接收返回结果
            rs = ps.executeQuery();
            while (rs.next()) {
                orderItem = new OrderItem();
                orderItem.setItemId(rs.getInt(1));
                orderItem.setOrderId(orderId);
                orderItem.setName(rs.getString(3));
                orderItem.setDiscountType(rs.getInt(4));
                orderItem.setUnitNum(rs.getInt(5));
                orderItem.setUnitPrice(rs.getDouble(6));
                orderItem.setSum(rs.getDouble(7));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("查看订单明细失败！");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return orderItems;
    }

    //查看/修改订单时用到
    @Override
    public OrderItem findByItemId(Integer itemId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderItem orderItem = null;
        String sql = "select * from orderItem where itemId=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int discountType = rs.getInt(4);
                switch (discountType) {
                    case 2:  //有折扣
                        orderItem = new DiscountOrderItem();
                        ((DiscountOrderItem) orderItem).setDiscount(rs.getDouble(10));
                        break;
                    case 3:  //促销
                        orderItem = new PromotionOrderItem();
                        ((PromotionOrderItem) orderItem).setPromotionNum(rs.getInt(9));
                        ((PromotionOrderItem) orderItem).setPromotionPrice(rs.getDouble(8));
                        break;
                    case 1:  //无折扣
                        orderItem = new OrderItem();
                        break;
                    default: break;
                }
                orderItem.setItemId(rs.getInt(1));
                orderItem.setOrderId(rs.getInt(2));
                orderItem.setName(rs.getString(3));
                orderItem.setDiscountType(rs.getInt(4));
                orderItem.setUnitNum(rs.getInt(5));
                orderItem.setUnitPrice(rs.getDouble(6));
                orderItem.setSum(rs.getDouble(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("根据订单明细ID查找失败！");
        } finally {
            DBUtils.close(rs,ps,conn);
        }
        return orderItem;
    }

    //添加明细
    @Override
    public void save(OrderItem orderItem) throws Exception {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        StringBuffer sql = new StringBuffer();

        //有折扣
        if (orderItem.getDiscountType() == 2) {
            DiscountOrderItem ditem = (DiscountOrderItem) orderItem;
            sql.append("insert into orderItem(orderId,name,discountType,unitNum,unitPrice,sum,discount)values(?,?,?,?,?,?,?)");
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,ditem.getOrderId());
            ps.setString(2,ditem.getName());
            ps.setInt(3,ditem.getDiscountType());
            ps.setInt(4,ditem.getUnitNum());
            ps.setDouble(5,ditem.getUnitPrice());
            ps.setDouble(6,ditem.getSum());
            ps.setDouble(7,ditem.getDiscount());
        }
        //促销
        else if (orderItem.getDiscountType() == 3) {
            PromotionOrderItem pitem = (PromotionOrderItem) orderItem;
            sql.append("insert into orderItem(orderId,name,discountType,unitNum,unitPrice,sum,promotionPrice,promotionNum)values(?,?,?,?,?,?,?,?)");
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,pitem.getOrderId());
            ps.setString(2,pitem.getName());
            ps.setInt(3,pitem.getDiscountType());
            ps.setInt(4,pitem.getUnitNum());
            ps.setDouble(5,pitem.getUnitPrice());
            ps.setDouble(6,pitem.getSum());
            ps.setDouble(7,pitem.getPromotionPrice());
            ps.setInt(8,pitem.getPromotionNum());
        }
        //无折扣
        else {
            sql.append("insert into orderItem(orderId,name,discountType,unitNum,unitPrice,sum)values(?,?,?,?,?,?)");
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,orderItem.getOrderId());
            ps.setString(2,orderItem.getName());
            ps.setInt(3,orderItem.getDiscountType());
            ps.setInt(4,orderItem.getUnitNum());
            ps.setDouble(5,orderItem.getUnitPrice());
            ps.setDouble(6,orderItem.getSum());
        }
        try {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("添加订单明细失败！");
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    //修改订单明细
    @Override
    public void update(OrderItem orderItem) throws Exception {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        StringBuffer sql = new StringBuffer();

        if (orderItem.getDiscountType() == 2) {  //有折扣
            DiscountOrderItem ditem = (DiscountOrderItem) orderItem;
            sql.append("update orderItem set name=?,discountType=?,unitNum=?,unitPrice=?,sum=?,discount=? where itemId=?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,ditem.getName());
            ps.setInt(2,ditem.getDiscountType());
            ps.setInt(3,ditem.getUnitNum());
            ps.setDouble(4,ditem.getUnitPrice());
            ps.setDouble(5,ditem.getSum());
            ps.setDouble(6,ditem.getDiscount());
            ps.setInt(7,ditem.getItemId());
        } else if (orderItem.getDiscountType() == 3) {  //促销
            PromotionOrderItem pitem = (PromotionOrderItem) orderItem;
            sql.append("update orderItem set name=?,discountType=?,unitNum=?,unitPrice=?,sum=?,promotionNum=?,promotionPrice=? where itemId=?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,pitem.getName());
            ps.setInt(2,pitem.getDiscountType());
            ps.setInt(3,pitem.getUnitNum());
            ps.setDouble(4,pitem.getUnitPrice());
            ps.setDouble(5,pitem.getSum());
            ps.setInt(6,pitem.getPromotionNum());
            ps.setDouble(7,pitem.getPromotionPrice());
            ps.setInt(8,pitem.getItemId());
        } else {  //无折扣
            sql.append("update orderItem set name=?,discountType=?,unitNum=?,unitPrice=?,sum=? where itemId=?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,orderItem.getName());
            ps.setInt(2,orderItem.getDiscountType());
            ps.setInt(3,orderItem.getUnitNum());
            ps.setDouble(4,orderItem.getUnitPrice());
            ps.setDouble(5,orderItem.getSum());
            ps.setInt(6,orderItem.getItemId());
        }
        try {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("修改订单明细失败！");
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }

    //删除订单明细
    @Override
    public void delete(Integer itemId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from orderItem where itemId=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,itemId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除订单明细失败！");
        } finally {
            DBUtils.close(null,ps,conn);
        }
    }
}
