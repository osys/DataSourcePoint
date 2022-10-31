package com.osys.dynamic.example.dto;

import java.util.StringJoiner;

/**
 * <p><b>{@link UserShopping} Description</b>:
 * </p>
 * @author Created by osys on 2022/09/02 11:30.
 */
public class UserShopping {
    private int userId;

    private String platform;

    private String shopName;

    private String productName;

    private float price;

    public UserShopping() {
    }

    public UserShopping(int userId, String platform, String shopName, String productName, float price) {
        this.userId = userId;
        this.platform = platform;
        this.shopName = shopName;
        this.productName = productName;
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserShopping.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("platform='" + platform + "'")
                .add("shopName='" + shopName + "'")
                .add("productName='" + productName + "'")
                .add("price=" + price)
                .toString();
    }
}
