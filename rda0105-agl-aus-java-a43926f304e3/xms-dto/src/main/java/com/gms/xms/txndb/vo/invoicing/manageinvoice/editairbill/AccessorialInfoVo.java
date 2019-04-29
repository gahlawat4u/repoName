package com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * @author TANDT
 */
public class AccessorialInfoVo extends BaseVo {

    private static final long serialVersionUID = -4588251934182093004L;
    private Integer serviceId;
    private String descriptionExt;
    private String description;
    private Double customerCost;
    private Double franchiseCost;
    private Double carrierCost;
    private Double customerTaxPercent;
    private Double customerTaxAmount;
    private Boolean requireCalculate;
    private Boolean isGst;
    private String descriptionOld;
    private Integer accessorialCount;
    private Long accessorialId;
    private Integer isNew = 0;

    public String getDescriptionOld() {
        return descriptionOld;
    }

    public void setDescriptionOld(String descriptionOld) {
        this.descriptionOld = descriptionOld;
    }

    public String getDescriptionExt() {
        return descriptionExt;
    }

    public void setDescriptionExt(String descriptionExt) {
        this.descriptionExt = descriptionExt;
    }

    public Boolean getIsGst() {
        return isGst;
    }

    public void setIsGst(Boolean isGst) {
        this.isGst = isGst;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public Double getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Double carrierCost) {
        this.carrierCost = carrierCost;
    }

    public Double getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(Double customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    public Double getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(Double customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public Boolean getRequireCalculate() {
        return requireCalculate;
    }

    public void setRequireCalculate(Boolean requireCalculate) {
        this.requireCalculate = requireCalculate;
    }

    public Integer getAccessorialCount() {
        return accessorialCount;
    }

    public void setAccessorialCount(Integer accessorialCount) {
        this.accessorialCount = accessorialCount;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "AccessorialInfoVo [serviceId=" + serviceId + ", descriptionExt=" + descriptionExt + ", description=" + description + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", customerTaxPercent=" + customerTaxPercent + ", customerTaxAmount=" + customerTaxAmount + ", requireCalculate=" + requireCalculate + ", isGst=" + isGst + ", descriptionOld=" + descriptionOld + ", accessorialCount=" + accessorialCount + ", accessorialId="
                + accessorialId + ", isNew=" + isNew + "]";
    }
}
