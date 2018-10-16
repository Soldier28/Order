package com.vince.orders.dao;

import com.vince.orders.vo.Order;
import com.vince.orders.vo.OrderItem;

import java.util.List;

/**
 * @author 兵哥哥
 * @create 2018-10-03 10:21
 * @desc
 **/
public interface IOrderItemDAO {

    //根据id查找
    public List<OrderItem> findByOrderId(final Integer orderId) throws Exception;

    //根据id查找
    public OrderItem findByItemId(final Integer itemId) throws Exception;

    //添加明细
    public void save(OrderItem orderItem) throws Exception;

    //修改订单明细
    public void update(OrderItem orderItem) throws Exception;

    //删除订单明细
    public void delete(final Integer itemId) throws Exception;
}
