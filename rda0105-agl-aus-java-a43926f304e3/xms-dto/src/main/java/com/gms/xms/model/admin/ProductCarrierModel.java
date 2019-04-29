package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ProductCarrierModel
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductCarrierModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private String productCarrierId;
    private String productCarrierName;

    @Override
    public String toString() {
        return "ProductCarrierModel [productCarrierId=" + productCarrierId + ", productCarrierName=" + productCarrierName + "]";
    }

    public String getProductCarrierId() {
        return productCarrierId;
    }

    public void setProductCarrierId(String productCarrierId) {
        this.productCarrierId = productCarrierId;
    }

    public String getProductCarrierName() {
        return productCarrierName;
    }

    public void setProductCarrierName(String productCarrierName) {
        this.productCarrierName = productCarrierName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productCarrierId == null) ? 0 : productCarrierId.hashCode());
        result = prime * result + ((productCarrierName == null) ? 0 : productCarrierName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductCarrierModel other = (ProductCarrierModel) obj;
        if (productCarrierId == null) {
            if (other.productCarrierId != null)
                return false;
        } else if (!productCarrierId.equals(other.productCarrierId))
            return false;
        if (productCarrierName == null) {
            if (other.productCarrierName != null)
                return false;
        } else if (!productCarrierName.equals(other.productCarrierName))
            return false;
        return true;
    }

}