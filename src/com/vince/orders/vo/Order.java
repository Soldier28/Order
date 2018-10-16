package com.vince.orders.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 兵哥哥
 * @create 2018-09-30 10:46
 * @desc 订单
 **/
public class Order {

    //主键ID
    private Integer OrderId;

    //订单名称
    private String OrderCode;

    //购买者名称
    private String Buyer;

    //订单合计金额：为所有明细订单的金额之和
    private Double Sum;

    //订单配送方式，使用选择项 1：自取 2：邮寄 3：快递
    private String DeliveryMethod;

    //最后修改日期
    private Date  LastmodifyDate;

    //创建日期
    private Date CreatedDate;

    //订单明细集合
    private List<OrderItem> orderItems;

    public Order() {
        super();
    }

    public Order(Integer orderId, String orderCode, Double sum, String deliveryMethod, Date lastmodifyDate) {
        OrderId = orderId;
        OrderCode = orderCode;
        Sum = sum;
        DeliveryMethod = deliveryMethod;
        LastmodifyDate = lastmodifyDate;
    }

    public Order(Integer orderId, String orderCode, String buyer, Double sum, String deliveryMethod, Date lastmodifyDate, Date createdDate) {
        OrderId = orderId;
        OrderCode = orderCode;
        Buyer = buyer;
        Sum = sum;
        DeliveryMethod = deliveryMethod;
        LastmodifyDate = lastmodifyDate;
        CreatedDate = lastmodifyDate;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public Double getSum() {
        return Sum;
    }

    public void setSum(Double sum) {
        Sum = sum;
    }

    public String getDeliveryMethod() {
        return DeliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        DeliveryMethod = deliveryMethod;
    }

    public Date getLastmodifyDate() {
        return LastmodifyDate;
    }

    public void setLastmodifyDate(Date lastmodifyDate) {
        LastmodifyDate = lastmodifyDate;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId=" + OrderId +
                ", OrderCode='" + OrderCode + '\'' +
                ", Buyer='" + Buyer + '\'' +
                ", Sum=" + Sum +
                ", DeliveryMethod='" + DeliveryMethod + '\'' +
                ", LastmodifyDate=" + LastmodifyDate +
                ", CreatedDate=" + CreatedDate +
                ", orderItems=" + orderItems +
                '}';
    }
}
