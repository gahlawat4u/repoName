package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ProductModel
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private String productId;
    private String productName;
    private String description;
    private String carrierId;
    private String localizationId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "ProductModel [productId=" + productId + ", productName=" + productName + ", description=" + description + ", carrierid=" + carrierId + ", localizationid=" + localizationId + "]";
    }
}