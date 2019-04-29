package com.gms.xms.dto.delivery;

import com.gms.xms.dto.ErrorInfoVo;
import com.gms.xms.txndb.vo.BaseVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from DhlQtdShpVo
 * <p>
 * Author HungNT Date Apr 6, 2015
 */
public class DhlCapabilityVo extends BaseVo {

    private static final long serialVersionUID = -5320720029851262259L;
    private boolean found = false;
    private String actionStatus = "";
    private List<String> availSvrs;
    private List<ErrorInfoVo> errorList;
    private String selectedService;
    private String globalProductCode;
    private String localProductCode;
    private String productShortName;
    private String localProductName;
    private String networkTypeCode;
    private Date pickupDate;
    private String pickupCutoffTime;
    private String bookingTime;
    private Integer totalTransitDays;
    private Date deliveryDate;
    private String deliveryTime;

    public String getGlobalProductCode() {
        return globalProductCode;
    }

    public void setGlobalProductCode(String globalProductCode) {
        this.globalProductCode = globalProductCode;
    }

    public String getLocalProductCode() {
        return localProductCode;
    }

    public void setLocalProductCode(String localProductCode) {
        this.localProductCode = localProductCode;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public String getLocalProductName() {
        return localProductName;
    }

    public void setLocalProductName(String localProductName) {
        this.localProductName = localProductName;
    }

    public String getNetworkTypeCode() {
        return networkTypeCode;
    }

    public void setNetworkTypeCode(String networkTypeCode) {
        this.networkTypeCode = networkTypeCode;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupCutoffTime() {
        return pickupCutoffTime;
    }

    public void setPickupCutoffTime(String pickupCutoffTime) {
        this.pickupCutoffTime = pickupCutoffTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getTotalTransitDays() {
        return totalTransitDays;
    }

    public void setTotalTransitDays(Integer totalTransitDays) {
        this.totalTransitDays = totalTransitDays;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public List<String> getAvailSvrs() {
        if (availSvrs == null)
            availSvrs = new ArrayList<>();
        return availSvrs;
    }

    public void setAvailSvrs(List<String> availSvrs) {
        this.availSvrs = availSvrs;
    }

    public List<ErrorInfoVo> getErrorList() {
        if (errorList == null) {
            errorList = new ArrayList<>();
        }
        return errorList;
    }

    public void setErrorList(List<ErrorInfoVo> errorList) {
        this.errorList = errorList;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(String selectedService) {
        this.selectedService = selectedService;
    }

    @Override
    public String toString() {
        return "DhlCapabilityVo [found=" + found + ", actionStatus=" + actionStatus + ", availSvrs=" + availSvrs + ", errorList=" + errorList + ", selectedService=" + selectedService + ", globalProductCode=" + globalProductCode + ", localProductCode=" + localProductCode + ", productShortName=" + productShortName + ", localProductName=" + localProductName + ", networkTypeCode=" + networkTypeCode + ", pickupDate=" + pickupDate + ", pickupCutoffTime=" + pickupCutoffTime + ", bookingTime="
                + bookingTime + ", totalTransitDays=" + totalTransitDays + ", deliveryDate=" + deliveryDate + ", deliveryTime=" + deliveryTime + "]";
    }


}