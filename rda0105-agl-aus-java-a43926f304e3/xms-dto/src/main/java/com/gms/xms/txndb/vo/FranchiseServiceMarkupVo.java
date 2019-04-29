package com.gms.xms.txndb.vo;

/**
 * Posted from FranchiseServiceMarkupVo
 * <p>
 * Author @author HungNT May 20, 2016
 */
public class FranchiseServiceMarkupVo extends BaseVo {
    private static final long serialVersionUID = 3213580071631551928L;

    private Integer markupId;
    private Long franchiseCode;
    private Integer serviceId;
    private Integer shipmentTypeId;
    private Double markupRate;

    public Integer getMarkupId() {
        return markupId;
    }

    public void setMarkupId(Integer markupId) {
        this.markupId = markupId;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Double getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(Double markupRate) {
        this.markupRate = markupRate;
    }

    @Override
    public String toString() {
        return "FranchiseServiceMarkupVo [markupId=" + markupId + ", franchiseCode=" + franchiseCode + ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + ", markupRate=" + markupRate + "]";
    }
}
