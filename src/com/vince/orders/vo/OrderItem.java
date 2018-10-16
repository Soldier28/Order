package com.vince.orders.vo;

/**
 * @author 兵哥哥
 * @create 2018-09-30 10:46
 * @desc 订单明细     --促销商品总金额=（商品数-促销商品数）*单价+促销商品*促销价
 **/
public class OrderItem {

    //订单明细主键
    private Integer ItemId;

    //订单编号
    private Integer OrderId;

    //货物名称
    private String Name;

    //折扣类型 0：无折扣 1：折扣 2：促销
    private Integer DiscountType;

    //货物数量
    private Integer UnitNum;

    //单位商品价格
    private Double UnitPrice;

    //当前明细的总金额
    private Double Sum;

    public OrderItem() {
        super();
    }

    public OrderItem(Integer itemId, Integer orderId, String name, Integer discountType, Integer unitNum, Double unitPrice, Double sum) {
        ItemId = itemId;
        OrderId = orderId;
        Name = name;
        DiscountType = discountType;
        UnitNum = unitNum;
        UnitPrice = unitPrice;
        Sum = sum;
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(Integer discountType) {
        DiscountType = discountType;
    }

    public Integer getUnitNum() {
        return UnitNum;
    }

    public void setUnitNum(Integer unitNum) {
        UnitNum = unitNum;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }

    public Double getSum() {
        return Sum;
    }

    public void setSum(Double sum) {
        Sum = sum;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "ItemId=" + ItemId +
                ", OrderId=" + OrderId +
                ", Name='" + Name + '\'' +
                ", DiscountType=" + DiscountType +
                ", UnitNum=" + UnitNum +
                ", UnitPrice=" + UnitPrice +
                ", Sum=" + Sum +
                '}';
    }
}
