package com.gms.xms.txndb.vo;

import java.util.Date;

/**
 * Posted from Aug 22, 2016 4:55:31 PM
 * <p>
 * Author dattrinh
 */
public class ProspectVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long prospectId;
    private String companyName;
    private String contactName;
    private String address1;
    private String address2;
    private String phone;
    private String email;
    private String postalCode;
    private Integer active;
    private Integer status;
    private Date createDate;
    private Integer qualifiedStatus;
    private Long salesPersonId;
    private Long salesRepTerritory;
    private Date opportunityStartDate;
    private Date firstVisitDate;
    private Integer daysOfClose;
    private String customerCode;
    private Double minimunBaseCharge;
    private Long country;
    private String city;
    private String territoryName;
    private String dhlInternationalAccount;
    private String dhlInboundAccount;
    private String comment;

    public Long getProspectId() {
        return prospectId;
    }

    public void setProspectId(Long prospectId) {
        this.prospectId = prospectId;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getQualifiedStatus() {
        return qualifiedStatus;
    }

    public void setQualifiedStatus(Integer qualifiedStatus) {
        this.qualifiedStatus = qualifiedStatus;
    }

    public Long getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(Long salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public Long getSalesRepTerritory() {
        return salesRepTerritory;
    }

    public void setSalesRepTerritory(Long salesRepTerritory) {
        this.salesRepTerritory = salesRepTerritory;
    }

    public Date getOpportunityStartDate() {
        return opportunityStartDate;
    }

    public void setOpportunityStartDate(Date opportunityStartDate) {
        this.opportunityStartDate = opportunityStartDate;
    }

    public Date getFirstVisitDate() {
        return firstVisitDate;
    }

    public void setFirstVisitDate(Date firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    public Integer getDaysOfClose() {
        return daysOfClose;
    }

    public void setDaysOfClose(Integer daysOfClose) {
        this.daysOfClose = daysOfClose;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public String getDhlInternationalAccount() {
        return dhlInternationalAccount;
    }

    public void setDhlInternationalAccount(String dhlInternationalAccount) {
        this.dhlInternationalAccount = dhlInternationalAccount;
    }

    public String getDhlInboundAccount() {
        return dhlInboundAccount;
    }

    public void setDhlInboundAccount(String dhlInboundAccount) {
        this.dhlInboundAccount = dhlInboundAccount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ProspectVo [prospectId=" + prospectId + ", companyName=" + companyName + ", contactName=" + contactName + ", address1=" + address1 + ", address2=" + address2 + ", phone=" + phone + ", email=" + email + ", postalCode=" + postalCode + ", active=" + active + ", status=" + status + ", createDate=" + createDate + ", qualifiedStatus=" + qualifiedStatus + ", salesPersonId=" + salesPersonId + ", salesRepTerritory=" + salesRepTerritory + ", opportunityStartDate=" + opportunityStartDate
                + ", firstVisitDate=" + firstVisitDate + ", daysOfClose=" + daysOfClose + ", customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", country=" + country + ", city=" + city + ", territoryName=" + territoryName + ", dhlInternationalAccount=" + dhlInternationalAccount + ", dhlInboundAccount=" + dhlInboundAccount + ", comment=" + comment + "]";
    }
}