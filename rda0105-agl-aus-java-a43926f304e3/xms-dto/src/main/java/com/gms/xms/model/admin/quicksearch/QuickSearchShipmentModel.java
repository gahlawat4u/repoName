package com.gms.xms.model.admin.quicksearch;

import com.gms.xms.model.webship.ShipmentModel;

/**
 * Posted from Apr 27, 2016 3:20:33 PM
 * <p>
 * Author huynd
 */
public class QuickSearchShipmentModel extends ShipmentModel {

    private static final long serialVersionUID = 1L;

    private String customerName;
    private String serviceName;
    private String serviceId;
    private String voidStatus;
    private String weightStr;
    private String collectionTypeName;
    private String confirmationNo;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getVoidStatus() {
        return voidStatus;
    }

    public void setVoidStatus(String voidStatus) {
        this.voidStatus = voidStatus;
    }

    public String getWeightStr() {
        return weightStr;
    }

    public void setWeightStr(String weightStr) {
        this.weightStr = weightStr;
    }

    public String getCollectionTypeName() {
        return collectionTypeName;
    }

    public void setCollectionTypeName(String collectionTypeName) {
        this.collectionTypeName = collectionTypeName;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    @Override
    public String toString() {
        return "QuickSearchShipmentModel [customerName=" + customerName + ", serviceName=" + serviceName + ", serviceId=" + serviceId + ", voidStatus=" + voidStatus + ", weightStr=" + weightStr + ", collectionTypeName=" + collectionTypeName + ", confirmationNo=" + confirmationNo + "]";
    }

}
