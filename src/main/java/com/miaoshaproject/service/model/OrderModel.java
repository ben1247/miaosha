package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
* 〈一句话功能简述〉<br>
* 用户下单的交易模型
*
* @author zhangyue
* @see [相关类/方法]（可选）
* @since [产品/模块版本] （可选）
* @date 2020/4/4 2:47 下午
*/
public class OrderModel {

    // 交易号 2020040400012828
    private String id;

    // 购买的用户id
    private Long userId;

    // 购买的商品id
    private Long itemId;

    // 如果非空，则表示是以秒杀商品方式下单
    private Long promoId;

    // 购买商品的单价，如果promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;

    // 购买数量
    private Integer amount;

    // 购买金额，如果promoId非空，则表示秒杀金额
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }
}
