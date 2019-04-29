package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ProductCarrierVo
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductCarrierVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private Long productCarrierId;
    private String productCarrierName;

    @Override
    public String toString() {
        return "ProductCarrierVo [productCarrierId=" + productCarrierId + ", productCarrierName=" + productCarrierName + "]";
    }

    public Long getProductCarrierId() {
        return productCarrierId;
    }

    public void setProductCarrierId(Long productCarrierId) {
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
        ProductCarrierVo other = (ProductCarrierVo) obj;
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