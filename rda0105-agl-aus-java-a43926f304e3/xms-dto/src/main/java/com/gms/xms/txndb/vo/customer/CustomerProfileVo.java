package com.gms.xms.txndb.vo.customer;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerProfileVo
 * <p>
 * Author DatTV Oct 12, 2015
 */
public class CustomerProfileVo extends BaseVo {

    private static final long serialVersionUID = 7389933607333123610L;

    private Long profileId;
    private String profileName;
    private Long franchiseCode;
    private Boolean inActive;
    private Integer groupId;
    private Integer webshipGroupId;
    private Long industryId;
    private Long salesRepId;
    private Integer collectorId;
    private Integer registrationId;
    private Integer gstId;
    private String dhlAccount;
    private String dhlDomesticAccount;
    private String dhlInternationalAccount;
    private String dhlInboundAccount;
    private String otherAccount;
    private Double minimunBaseCharge;
    private Byte invoiceSorting;
    private Byte invoiceTerms;
    private Long invoiceToCustomerId;
    private Byte pickupFee;
    private Double invoiceLateFee;
    private Boolean emailInvoice;
    private Boolean downloadCsvInvoice;
    private Integer previousCarrier;
    private Integer areaId;
    private String tntAccount;
    private String aaeAccount;
    private String hubAccount;
    private String fedexAccount;
    private String tollPriorityAccount;
    private String ukMailAccount;
    private String upsAccount;
    private Boolean bookingEmailNotification;
    private String tollIpecAccount;
    private String startrackAccount;
    private String dispatchId;
    private String rejectionNote;
    private Boolean enableSi;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Boolean getInActive() {
        return inActive;
    }

    public void setInActive(Boolean inActive) {
        this.inActive = inActive;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(Integer webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getGstId() {
        return gstId;
    }

    public void setGstId(Integer gstId) {
        this.gstId = gstId;
    }

    public String getDhlAccount() {
        return dhlAccount;
    }

    public void setDhlAccount(String dhlAccount) {
        this.dhlAccount = dhlAccount;
    }

    public String getDhlDomesticAccount() {
        return dhlDomesticAccount;
    }

    public void setDhlDomesticAccount(String dhlDomesticAccount) {
        this.dhlDomesticAccount = dhlDomesticAccount;
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

    public String getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(String otherAccount) {
        this.otherAccount = otherAccount;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public Byte getInvoiceSorting() {
        return invoiceSorting;
    }

    public void setInvoiceSorting(Byte invoiceSorting) {
        this.invoiceSorting = invoiceSorting;
    }

    public Byte getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(Byte invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public Long getInvoiceToCustomerId() {
        return invoiceToCustomerId;
    }

    public void setInvoiceToCustomerId(Long invoiceToCustomerId) {
        this.invoiceToCustomerId = invoiceToCustomerId;
    }

    public Byte getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(Byte pickupFee) {
        this.pickupFee = pickupFee;
    }

    public Double getInvoiceLateFee() {
        return invoiceLateFee;
    }

    public void setInvoiceLateFee(Double invoiceLateFee) {
        this.invoiceLateFee = invoiceLateFee;
    }

    public Boolean getEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(Boolean emailInvoice) {
        this.emailInvoice = emailInvoice;
    }

    public Boolean getDownloadCsvInvoice() {
        return downloadCsvInvoice;
    }

    public void setDownloadCsvInvoice(Boolean downloadCsvInvoice) {
        this.downloadCsvInvoice = downloadCsvInvoice;
    }

    public Integer getPreviousCarrier() {
        return previousCarrier;
    }

    public void setPreviousCarrier(Integer previousCarrier) {
        this.previousCarrier = previousCarrier;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getTntAccount() {
        return tntAccount;
    }

    public void setTntAccount(String tntAccount) {
        this.tntAccount = tntAccount;
    }

    public String getAaeAccount() {
        return aaeAccount;
    }

    public void setAaeAccount(String aaeAccount) {
        this.aaeAccount = aaeAccount;
    }

    public String getHubAccount() {
        return hubAccount;
    }

    public void setHubAccount(String hubAccount) {
        this.hubAccount = hubAccount;
    }

    public String getFedexAccount() {
        return fedexAccount;
    }

    public void setFedexAccount(String fedexAccount) {
        this.fedexAccount = fedexAccount;
    }

    public String getTollPriorityAccount() {
        return tollPriorityAccount;
    }

    public void setTollPriorityAccount(String tollPriorityAccount) {
        this.tollPriorityAccount = tollPriorityAccount;
    }

    public String getUkMailAccount() {
        return ukMailAccount;
    }

    public void setUkMailAccount(String ukMailAccount) {
        this.ukMailAccount = ukMailAccount;
    }

    public String getUpsAccount() {
        return upsAccount;
    }

    public void setUpsAccount(String upsAccount) {
        this.upsAccount = upsAccount;
    }

    public Boolean getBookingEmailNotification() {
        return bookingEmailNotification;
    }

    public void setBookingEmailNotification(Boolean bookingEmailNotification) {
        this.bookingEmailNotification = bookingEmailNotification;
    }

    public String getTollIpecAccount() {
        return tollIpecAccount;
    }

    public void setTollIpecAccount(String tollIpecAccount) {
        this.tollIpecAccount = tollIpecAccount;
    }

    public String getStartrackAccount() {
        return startrackAccount;
    }

    public void setStartrackAccount(String startrackAccount) {
        this.startrackAccount = startrackAccount;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getRejectionNote() {
        return rejectionNote;
    }

    public void setRejectionNote(String rejectionNote) {
        this.rejectionNote = rejectionNote;
    }

    public Boolean getEnableSi() {
        return enableSi;
    }

    public void setEnableSi(Boolean enableSi) {
        this.enableSi = enableSi;
    }

    @Override
    public String toString() {
        return "CustomerProfileVo [profileId=" + profileId + ", profileName=" + profileName + ", franchiseCode=" + franchiseCode + ", inActive=" + inActive + ", groupId=" + groupId + ", webshipGroupId=" + webshipGroupId + ", industryId=" + industryId + ", salesRepId=" + salesRepId + ", collectorId=" + collectorId + ", registrationId=" + registrationId + ", gstId=" + gstId + ", dhlAccount=" + dhlAccount + ", dhlDomesticAccount=" + dhlDomesticAccount + ", dhlInternationalAccount="
                + dhlInternationalAccount + ", dhlInboundAccount=" + dhlInboundAccount + ", otherAccount=" + otherAccount + ", minimunBaseCharge=" + minimunBaseCharge + ", invoiceSorting=" + invoiceSorting + ", invoiceTerms=" + invoiceTerms + ", invoiceToCustomerId=" + invoiceToCustomerId + ", pickupFee=" + pickupFee + ", invoiceLateFee=" + invoiceLateFee + ", emailInvoice=" + emailInvoice + ", downloadCsvInvoice=" + downloadCsvInvoice + ", previousCarrier=" + previousCarrier + ", areaId="
                + areaId + ", tntAccount=" + tntAccount + ", aaeAccount=" + aaeAccount + ", hubAccount=" + hubAccount + ", fedexAccount=" + fedexAccount + ", tollPriorityAccount=" + tollPriorityAccount + ", ukMailAccount=" + ukMailAccount + ", upsAccount=" + upsAccount + ", bookingEmailNotification=" + bookingEmailNotification + ", tollIpecAccount=" + tollIpecAccount + ", startrackAccount=" + startrackAccount + ", dispatchId=" + dispatchId + ", rejectionNote=" + rejectionNote + ", enableSi="
                + enableSi + "]";
    }

}
