package com.vince.orders.vo;

/**
 * @author 兵哥哥
 * @create 2018-09-30 10:46
 * @desc 促销商品订单明细
 **/
public class PromotionOrderItem extends OrderItem {

    //促销价格
    private Double PromotionPrice;

    //促销开始数量
    private Integer PromotionNum;

    public PromotionOrderItem() {
        super();
    }

    public PromotionOrderItem(Double promotionPrice, Integer promotionNum) {
        PromotionPrice = promotionPrice;
        PromotionNum = promotionNum;
    }

    public PromotionOrderItem(Integer itemId, Integer orderId, String name, Integer discountType, Integer unitNum,
                              Double unitPrice, Double sum, Double promotionPrice, Integer promotionNum) {
        super(itemId, orderId, name, discountType, unitNum, unitPrice, sum);
        PromotionPrice = promotionPrice;
        PromotionNum = promotionNum;
    }

    public Double getPromotionPrice() {
        return PromotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        PromotionPrice = promotionPrice;
    }

    public Integer getPromotionNum() {
        return PromotionNum;
    }

    public void setPromotionNum(Integer promotionNum) {
        PromotionNum = promotionNum;
    }
}
