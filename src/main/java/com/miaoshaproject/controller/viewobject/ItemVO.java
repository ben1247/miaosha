package com.miaoshaproject.controller.viewobject;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class ItemVO {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer stock;

    private String description;

    // 商品的销量
    private Integer sales;

    // 商品描述图片URL
    private String imgUrl;

    // 记录商品是否在秒杀活动中，以及对应的状态 0：没有秒杀活动 1：秒杀活动待开始 2：秒杀活动进行中 3：秒杀活动已结束
    private Integer promoStatus;

    // 秒杀活动价
    private BigDecimal promoPrice;

    // 秒杀活动ID
    private Long promoId;

    // 秒杀活动开始时间
    private String promoStartDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPromoStatus() {
        return promoStatus;
    }

    public void setPromoStatus(Integer promoStatus) {
        this.promoStatus = promoStatus;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public String getPromoStartDate() {
        return promoStartDate;
    }

    public void setPromoStartDate(String promoStartDate) {
        this.promoStartDate = promoStartDate;
    }
}
