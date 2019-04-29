package com.gms.xms.model.customer;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerCollectionModel
 * <p>
 * Author DatTV Sep 28, 2015
 */
public class CustomerCollectionModel extends BaseModel {

    private static final long serialVersionUID = -2100509434365689382L;

    private String userId;
    private String userType;
    private String freightCreditLimit;
    private String reminder;
    private String reminderEmail;
    private String reminderPrint;
    private String reminderUseEmailInvoice;
    private String reminderEmailAddress;
    private String cimCustomerProfileId;
    private String cimCustomerPaymentProfileId;
    private String cimCustomerShippingAddressId;
    private String customerCode;

    @Override
    public String toString() {
        return "CustomerCollectionModel [userId=" + userId + ", userType=" + userType + ", freightCreditLimit=" + freightCreditLimit + ", reminder=" + reminder + ", reminderEmail=" + reminderEmail + ", reminderPrint=" + reminderPrint + ", reminderUseEmailInvoice=" + reminderUseEmailInvoice + ", reminderEmailAddress=" + reminderEmailAddress + ", cimCustomerProfileId=" + cimCustomerProfileId + ", cimCustomerPaymentProfileId=" + cimCustomerPaymentProfileId + ", cimCustomerShippingAddressId="
                + cimCustomerShippingAddressId + "]";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFreightCreditLimit() {
        return freightCreditLimit;
    }

    public void setFreightCreditLimit(String freightCreditLimit) {
        this.freightCreditLimit = freightCreditLimit;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getReminderEmail() {
        return reminderEmail;
    }

    public void setReminderEmail(String reminderEmail) {
        this.reminderEmail = reminderEmail;
    }

    public String getReminderPrint() {
        return reminderPrint;
    }

    public void setReminderPrint(String reminderPrint) {
        this.reminderPrint = reminderPrint;
    }

    public String getReminderEmailAddress() {
        return reminderEmailAddress;
    }

    public void setReminderEmailAddress(String reminderEmailAddress) {
        this.reminderEmailAddress = reminderEmailAddress;
    }

    public String getCimCustomerProfileId() {
        return cimCustomerProfileId;
    }

    public void setCimCustomerProfileId(String cimCustomerProfileId) {
        this.cimCustomerProfileId = cimCustomerProfileId;
    }

    public String getCimCustomerPaymentProfileId() {
        return cimCustomerPaymentProfileId;
    }

    public void setCimCustomerPaymentProfileId(String cimCustomerPaymentProfileId) {
        this.cimCustomerPaymentProfileId = cimCustomerPaymentProfileId;
    }

    public String getCimCustomerShippingAddressId() {
        return cimCustomerShippingAddressId;
    }

    public void setCimCustomerShippingAddressId(String cimCustomerShippingAddressId) {
        this.cimCustomerShippingAddressId = cimCustomerShippingAddressId;
    }

    public String getReminderUseEmailInvoice() {
        return reminderUseEmailInvoice;
    }

    public void setReminderUseEmailInvoice(String reminderUseEmailInvoice) {
        this.reminderUseEmailInvoice = reminderUseEmailInvoice;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
