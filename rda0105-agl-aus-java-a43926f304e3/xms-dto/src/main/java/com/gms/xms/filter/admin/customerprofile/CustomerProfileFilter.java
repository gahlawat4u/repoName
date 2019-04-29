package com.gms.xms.filter.admin.customerprofile;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from CustomerProfileFilter
 * <p>
 * Author TANDT 22-10-2015
 */
public class CustomerProfileFilter extends BaseFilter {
    private String franchiseCode;
    private String profileName;
    private String zone;
    private Long baseRateId;
    private Long CustomerCode;
    private Integer bound;
    private Integer content;
    private Integer shipmentTypeId;
    private Long serviceId;
    private String listServices;
    private Long profileId;

    public Long getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(Long customerCode) {
        CustomerCode = customerCode;
    }

    public String getListServices() {
        return listServices;
    }

    public void setListServices(String listServices) {
        this.listServices = listServices;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Long getBaseRateId() {
        return baseRateId;
    }

    public void setBaseRateId(Long baseRateId) {
        this.baseRateId = baseRateId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

}
