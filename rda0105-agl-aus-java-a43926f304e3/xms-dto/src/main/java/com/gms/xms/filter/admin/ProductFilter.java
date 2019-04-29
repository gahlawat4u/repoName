package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from ProductFilter
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductFilter extends BaseFilter {
    private Long carrierId;
    private String productName;
    private Long productId;

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
