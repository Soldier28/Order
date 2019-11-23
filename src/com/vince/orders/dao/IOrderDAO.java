package com.vince.orders.dao;

import com.vince.orders.vo.Order;

import java.util.List;

/**
 * @author 兵哥哥
 * @create 2018-10-03 10:21
 * @desc
 **/
public interface IOrderDAO {

    //添加订单
    public void save(Order order) throws Exception;

    //根据订单编号修改订单
    public void update(Order order) throws Exception;

    //根据id修改总金额
    public void updateSum(final int orderid) throws Exception;

    //根据id删除
    public void deleteById(final int orderid) throws Exception;

    //根据id查询
    public Order findById(final int orderid) throws Exception;

    //查询，根据用户名及订单编号或订单金额或配送方式(三个条件最少一个最多三个)
    public List<Order> search(String userName, String ordercode, Double sum, String deliverymethod) throws Exception;

    //根据用户名查询
    public List<Order> findByBuyer(String username) throws Exception;
}
