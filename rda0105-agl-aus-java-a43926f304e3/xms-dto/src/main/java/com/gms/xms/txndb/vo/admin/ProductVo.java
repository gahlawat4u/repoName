package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ProductVo
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductVo extends BaseVo {

    private static final long serialVersionUID = -3265481811907141192L;

    private Long productId;
    private String productName;
    private String description;
    private Long carrierId;
    private Long localizationId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "ProductVo [productId=" + productId + ", productName=" + productName + ", description=" + description + ", carrierId=" + carrierId + ", localizationId=" + localizationId + "]";
    }
}