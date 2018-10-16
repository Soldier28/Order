package com.vince.orders.vo;

/**
 * @author 兵哥哥
 * @create 2018-09-30 10:45
 * @desc 折扣商品订单明细
 **/
public class DiscountOrderItem extends OrderItem {

    //折扣率
    private Double discount;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public DiscountOrderItem() {
        super();
    }

    public DiscountOrderItem(Double discount) {
        this.discount = discount;
    }
}
