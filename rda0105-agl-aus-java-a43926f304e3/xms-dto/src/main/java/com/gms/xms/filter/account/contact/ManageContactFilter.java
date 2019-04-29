package com.gms.xms.filter.account.contact;

import com.gms.xms.filter.BaseFilter;

public class ManageContactFilter extends BaseFilter {

    private String prospectList;
    private String companyName;
    private String contactName;
    private String address1;
    private String address2;
    private String phone;
    private String email;
    private String postalCode;
    private String saleStage;

    public String getProspectList() {
        return prospectList;
    }

    public void setProspectList(String prospectList) {
        this.prospectList = prospectList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSaleStage() {
        return saleStage;
    }

    public void setSaleStage(String saleStage) {
        this.saleStage = saleStage;
    }
}
