package com.gms.xms.model.admin.invoicing.manageinvoice.editairbill;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class AccessorialInfoModel extends BaseModel {

    private static final long serialVersionUID = -2905775842083573779L;
    private String serviceId;
    private String descriptionExt;
    private String description;
    private String customerCost;
    private String franchiseCost;
    private String carrierCost;
    private String customerTaxPercent;
    private String customerTaxAmount;
    private String requireCalculate;
    private String isGst;
    private String descriptionOld;
    private String accessorialCount;
    private String accessorialId;
    private String isNew;

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

    public String getIsGst() {
        return isGst;
    }

    public void setIsGst(String isGst) {
        this.isGst = isGst;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(String customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    public String getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(String customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public String getRequireCalculate() {
        return requireCalculate;
    }

    public void setRequireCalculate(String requireCalculate) {
        this.requireCalculate = requireCalculate;
    }

    public String getAccessorialCount() {
        return accessorialCount;
    }

    public void setAccessorialCount(String accessorialCount) {
        this.accessorialCount = accessorialCount;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "AccessorialInfoModel [serviceId=" + serviceId + ", descriptionExt=" + descriptionExt + ", description=" + description + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", customerTaxPercent=" + customerTaxPercent + ", customerTaxAmount=" + customerTaxAmount + ", requireCalculate=" + requireCalculate + ", isGst=" + isGst + ", descriptionOld=" + descriptionOld + ", accessorialCount=" + accessorialCount + ", accessorialId="
                + accessorialId + ", isNew=" + isNew + "]";
    }

}
