package com.gms.xms.txndb.vo.customer;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from CustomerCollectionVo
 * <p>
 * Author DatTV Sep 28, 2015
 */
public class CustomerCollectionVo extends BaseVo {

    private static final long serialVersionUID = -6163046102153949248L;

    private Long userId;
    private Integer userType;
    private Double freightCreditLimit;
    private Boolean reminder;
    private Boolean reminderEmail;
    private Boolean reminderPrint;
    private Boolean reminderUseEmailInvoice;
    private String reminderEmailAddress;
    private String cimCustomerProfileId;
    private String cimCustomerPaymentProfileId;
    private String cimCustomerShippingAddressId;
    private String customerCode;

    @Override
    public String toString() {
        return "CustomerCollectionVo [userId=" + userId + ", userType=" + userType + ", freightCreditLimit=" + freightCreditLimit + ", reminder=" + reminder + ", reminderEmail=" + reminderEmail + ", reminderPrint=" + reminderPrint + ", reminderUseEmailInvoice=" + reminderUseEmailInvoice + ", reminderEmailAddress=" + reminderEmailAddress + ", cimCustomerProfileId=" + cimCustomerProfileId + ", cimCustomerPaymentProfileId=" + cimCustomerPaymentProfileId + ", cimCustomerShippingAddressId="
                + cimCustomerShippingAddressId + "]";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getFreightCreditLimit() {
        return freightCreditLimit;
    }

    public void setFreightCreditLimit(Double freightCreditLimit) {
        this.freightCreditLimit = freightCreditLimit;
    }

    public Boolean getReminder() {
        return reminder;
    }

    public void setReminder(Boolean reminder) {
        this.reminder = reminder;
    }

    public Boolean getReminderEmail() {
        return reminderEmail;
    }

    public void setReminderEmail(Boolean reminderEmail) {
        this.reminderEmail = reminderEmail;
    }

    public Boolean getReminderPrint() {
        return reminderPrint;
    }

    public void setReminderPrint(Boolean reminderPrint) {
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

    public Boolean getReminderUseEmailInvoice() {
        return reminderUseEmailInvoice;
    }

    public void setReminderUseEmailInvoice(Boolean reminderUseEmailInvoice) {
        this.reminderUseEmailInvoice = reminderUseEmailInvoice;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
