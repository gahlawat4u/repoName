package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerBillingAddressModel
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CustomerBillingAddressModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;
    private String customerCode;

    private String usertype;

    private String billingSameWithCustomer;

    private String billingCustomerName;

    private String billingContactName;

    private String billingCity;

    private String billingStateCode;

    private String billingCountry;

    private String billingPostalCode;

    private String billingPhone;

    private String billingFax;

    private String billingEmail;

    private String billingMobile;

    private String billingAlternatePhone;

    private String billingContactTitle;

    private String billingAddress1;

    private String billingAddress2;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getBillingSameWithCustomer() {
        return billingSameWithCustomer;
    }

    public void setBillingSameWithCustomer(String billingSameWithCustomer) {
        this.billingSameWithCustomer = billingSameWithCustomer;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingStateCode() {
        return billingStateCode;
    }

    public void setBillingStateCode(String billingStateCode) {
        this.billingStateCode = billingStateCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingFax() {
        return billingFax;
    }

    public void setBillingFax(String billingFax) {
        this.billingFax = billingFax;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingMobile() {
        return billingMobile;
    }

    public void setBillingMobile(String billingMobile) {
        this.billingMobile = billingMobile;
    }

    public String getBillingAlternatePhone() {
        return billingAlternatePhone;
    }

    public void setBillingAlternatePhone(String billingAlternatePhone) {
        this.billingAlternatePhone = billingAlternatePhone;
    }

    public String getBillingContactTitle() {
        return billingContactTitle;
    }

    public void setBillingContactTitle(String billingContactTitle) {
        this.billingContactTitle = billingContactTitle;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    @Override
    public String toString() {
        return "CustomerBillingAddressModel [customerCode=" + customerCode + ", usertype=" + usertype + ", billingSameWithCustomer=" + billingSameWithCustomer + ", billingCustomerName=" + billingCustomerName + ", billingContactName=" + billingContactName + ", billingCity=" + billingCity + ", billingStateCode=" + billingStateCode + ", billingCountry=" + billingCountry + ", billingPostalCode=" + billingPostalCode + ", billingPhone=" + billingPhone + ", billingFax=" + billingFax + ", billingEmail="
                + billingEmail + ", billingMobile=" + billingMobile + ", billingAlternatePhone=" + billingAlternatePhone + ", billingContactTitle=" + billingContactTitle + ", billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((billingAddress1 == null) ? 0 : billingAddress1.hashCode());
        result = prime * result + ((billingAddress2 == null) ? 0 : billingAddress2.hashCode());
        result = prime * result + ((billingAlternatePhone == null) ? 0 : billingAlternatePhone.hashCode());
        result = prime * result + ((billingCity == null) ? 0 : billingCity.hashCode());
        result = prime * result + ((billingContactName == null) ? 0 : billingContactName.hashCode());
        result = prime * result + ((billingContactTitle == null) ? 0 : billingContactTitle.hashCode());
        result = prime * result + ((billingCountry == null) ? 0 : billingCountry.hashCode());
        result = prime * result + ((billingCustomerName == null) ? 0 : billingCustomerName.hashCode());
        result = prime * result + ((billingEmail == null) ? 0 : billingEmail.hashCode());
        result = prime * result + ((billingFax == null) ? 0 : billingFax.hashCode());
        result = prime * result + ((billingMobile == null) ? 0 : billingMobile.hashCode());
        result = prime * result + ((billingPhone == null) ? 0 : billingPhone.hashCode());
        result = prime * result + ((billingPostalCode == null) ? 0 : billingPostalCode.hashCode());
        result = prime * result + ((billingSameWithCustomer == null) ? 0 : billingSameWithCustomer.hashCode());
        result = prime * result + ((billingStateCode == null) ? 0 : billingStateCode.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((usertype == null) ? 0 : usertype.hashCode());
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
        CustomerBillingAddressModel other = (CustomerBillingAddressModel) obj;
        if (billingAddress1 == null) {
            if (other.billingAddress1 != null)
                return false;
        } else if (!billingAddress1.equals(other.billingAddress1))
            return false;
        if (billingAddress2 == null) {
            if (other.billingAddress2 != null)
                return false;
        } else if (!billingAddress2.equals(other.billingAddress2))
            return false;
        if (billingAlternatePhone == null) {
            if (other.billingAlternatePhone != null)
                return false;
        } else if (!billingAlternatePhone.equals(other.billingAlternatePhone))
            return false;
        if (billingCity == null) {
            if (other.billingCity != null)
                return false;
        } else if (!billingCity.equals(other.billingCity))
            return false;
        if (billingContactName == null) {
            if (other.billingContactName != null)
                return false;
        } else if (!billingContactName.equals(other.billingContactName))
            return false;
        if (billingContactTitle == null) {
            if (other.billingContactTitle != null)
                return false;
        } else if (!billingContactTitle.equals(other.billingContactTitle))
            return false;
        if (billingCountry == null) {
            if (other.billingCountry != null)
                return false;
        } else if (!billingCountry.equals(other.billingCountry))
            return false;
        if (billingCustomerName == null) {
            if (other.billingCustomerName != null)
                return false;
        } else if (!billingCustomerName.equals(other.billingCustomerName))
            return false;
        if (billingEmail == null) {
            if (other.billingEmail != null)
                return false;
        } else if (!billingEmail.equals(other.billingEmail))
            return false;
        if (billingFax == null) {
            if (other.billingFax != null)
                return false;
        } else if (!billingFax.equals(other.billingFax))
            return false;
        if (billingMobile == null) {
            if (other.billingMobile != null)
                return false;
        } else if (!billingMobile.equals(other.billingMobile))
            return false;
        if (billingPhone == null) {
            if (other.billingPhone != null)
                return false;
        } else if (!billingPhone.equals(other.billingPhone))
            return false;
        if (billingPostalCode == null) {
            if (other.billingPostalCode != null)
                return false;
        } else if (!billingPostalCode.equals(other.billingPostalCode))
            return false;
        if (billingSameWithCustomer == null) {
            if (other.billingSameWithCustomer != null)
                return false;
        } else if (!billingSameWithCustomer.equals(other.billingSameWithCustomer))
            return false;
        if (billingStateCode == null) {
            if (other.billingStateCode != null)
                return false;
        } else if (!billingStateCode.equals(other.billingStateCode))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (usertype == null) {
            if (other.usertype != null)
                return false;
        } else if (!usertype.equals(other.usertype))
            return false;
        return true;
    }

}
