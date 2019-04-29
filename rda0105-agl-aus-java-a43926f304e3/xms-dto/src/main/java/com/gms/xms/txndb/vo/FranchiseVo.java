package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class FranchiseVo extends BaseVo {
    private static final long serialVersionUID = 5351572621212060933L;

    private Long id;

    private Long franchiseCode;

    private Date createDate;

    private Date activateDate;

    private Boolean inactive;

    private Integer groupId;

    private Integer webshipGroupid;

    private Long salesRepId;

    private String registrationid;

    private Integer gstid;

    private String franchiseTerritory;

    private String dhlAccount;

    private String dhlInternationalAccount;

    private String dhlInboundAccount;

    private String otherAccount;

    private Double markupRate;

    private Double tntMarkupRate;

    private Double tollMarkupRate;

    private Double minimunBaseCharge;

    private Integer invoiceSorting;

    private Integer invoiceTerms;

    private Long invoiceToCustomerid;

    private Integer pickupFee;

    private Double invoiceLateFee;

    private Boolean emailInvoice;

    private Boolean downloadCsvInvoice;

    private Double overnight;

    private Double nextAfternoon;

    private Double secondDay;

    private Double ground;

    private Double intlOutbound;

    private Double intlInbound;

    private Double other;

    private Double expressPerAirbill;

    private Double freightPerAirbill;

    private Double invoicingFee;

    private String charge1;

    private Double charge1Amount;

    private String charge2;

    private Double charge2Amount;

    private String charge3;

    private Double charge3Amount;

    private Double swMaintenance;

    private Double swDevelopment;

    private Double marketing;

    private Double webship;

    private Boolean adminFunction;

    private Long webshipAdminid;

    private String dhlDomesticAccount;

    private Date franchiseStartDate;

    private Integer areaId;

    private Long createdUserid;

    private String tntAccount;

    private String aaeAccount;

    private String hubAccount;

    private Double profitShare;

    private String fedexAccount;

    private String tollPriorityAccount;

    private String ukMailAccount;

    private String upsAccount;

    private Boolean bookingEmailNotification;

    private Double tntInternationalMarkupRate;

    private Boolean invoicingCharge;

    private String tollIpecAccount;

    private String ramAccount;

    private Boolean excludeFromAll;

    private Double swWebshipUsage;

    private Double swCollectionServiceFee;

    private Double managementMargin;

    private Double internationalShipmentFee;

    private Double domesticShipmentFee;

    private Double printingFee;

    private Double postageFee;

    private String uspsAccount;

    private String ontracAccount;

    private Double startrackMarkupRate;

    private Double tollIpecMarkupRate;

    private String dispatchId;

    private String startrackAccount;

    private CustomerAddressVo customerAddress;

    private WebshipGroupVo webshipGroup;

    private CustomerBillingAddressVo customerBilling;

    private Double dhlDomMarkupRate;

    private Boolean enableSi;

    private Double upsMarkupRate;
    
    private byte[] profileImage;

    public String getStartrackAccount() {
        return startrackAccount;
    }

    public void setStartrackAccount(String startrackAccount) {
        this.startrackAccount = startrackAccount;
    }

    public Double getStartrackMarkupRate() {
        return startrackMarkupRate;
    }

    public void setStartrackMarkupRate(Double startrackMarkupRate) {
        this.startrackMarkupRate = startrackMarkupRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getActivateDate() {
        return activateDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getWebshipGroupid() {
        return webshipGroupid;
    }

    public void setWebshipGroupid(Integer webshipGroupid) {
        this.webshipGroupid = webshipGroupid;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(String registrationid) {
        this.registrationid = registrationid;
    }

    public Integer getGstid() {
        return gstid;
    }

    public void setGstid(Integer gstid) {
        this.gstid = gstid;
    }

    public String getFranchiseTerritory() {
        return franchiseTerritory;
    }

    public void setFranchiseTerritory(String franchiseTerritory) {
        this.franchiseTerritory = franchiseTerritory;
    }

    public String getDhlAccount() {
        return dhlAccount;
    }

    public void setDhlAccount(String dhlAccount) {
        this.dhlAccount = dhlAccount;
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

    public Double getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(Double markupRate) {
        this.markupRate = markupRate;
    }

    public Double getTntMarkupRate() {
        return tntMarkupRate;
    }

    public void setTntMarkupRate(Double tntMarkupRate) {
        this.tntMarkupRate = tntMarkupRate;
    }

    public Double getTollMarkupRate() {
        return tollMarkupRate;
    }

    public void setTollMarkupRate(Double tollMarkupRate) {
        this.tollMarkupRate = tollMarkupRate;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public Integer getInvoiceSorting() {
        return invoiceSorting;
    }

    public void setInvoiceSorting(Integer invoiceSorting) {
        this.invoiceSorting = invoiceSorting;
    }

    public Integer getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(Integer invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public Long getInvoiceToCustomerid() {
        return invoiceToCustomerid;
    }

    public void setInvoiceToCustomerid(Long invoiceToCustomerid) {
        this.invoiceToCustomerid = invoiceToCustomerid;
    }

    public Integer getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(Integer pickupFee) {
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

    public Double getOvernight() {
        return overnight;
    }

    public void setOvernight(Double overnight) {
        this.overnight = overnight;
    }

    public Double getNextAfternoon() {
        return nextAfternoon;
    }

    public void setNextAfternoon(Double nextAfternoon) {
        this.nextAfternoon = nextAfternoon;
    }

    public Double getSecondDay() {
        return secondDay;
    }

    public void setSecondDay(Double secondDay) {
        this.secondDay = secondDay;
    }

    public Double getGround() {
        return ground;
    }

    public void setGround(Double ground) {
        this.ground = ground;
    }

    public Double getIntlOutbound() {
        return intlOutbound;
    }

    public void setIntlOutbound(Double intlOutbound) {
        this.intlOutbound = intlOutbound;
    }

    public Double getIntlInbound() {
        return intlInbound;
    }

    public void setIntlInbound(Double intlInbound) {
        this.intlInbound = intlInbound;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getExpressPerAirbill() {
        return expressPerAirbill;
    }

    public void setExpressPerAirbill(Double expressPerAirbill) {
        this.expressPerAirbill = expressPerAirbill;
    }

    public Double getFreightPerAirbill() {
        return freightPerAirbill;
    }

    public void setFreightPerAirbill(Double freightPerAirbill) {
        this.freightPerAirbill = freightPerAirbill;
    }

    public Double getInvoicingFee() {
        return invoicingFee;
    }

    public void setInvoicingFee(Double invoicingFee) {
        this.invoicingFee = invoicingFee;
    }

    public String getCharge1() {
        return charge1;
    }

    public void setCharge1(String charge1) {
        this.charge1 = charge1;
    }

    public Double getCharge1Amount() {
        return charge1Amount;
    }

    public void setCharge1Amount(Double charge1Amount) {
        this.charge1Amount = charge1Amount;
    }

    public String getCharge2() {
        return charge2;
    }

    public void setCharge2(String charge2) {
        this.charge2 = charge2;
    }

    public Double getCharge2Amount() {
        return charge2Amount;
    }

    public void setCharge2Amount(Double charge2Amount) {
        this.charge2Amount = charge2Amount;
    }

    public String getCharge3() {
        return charge3;
    }

    public void setCharge3(String charge3) {
        this.charge3 = charge3;
    }

    public Double getCharge3Amount() {
        return charge3Amount;
    }

    public void setCharge3Amount(Double charge3Amount) {
        this.charge3Amount = charge3Amount;
    }

    public Double getSwMaintenance() {
        return swMaintenance;
    }

    public void setSwMaintenance(Double swMaintenance) {
        this.swMaintenance = swMaintenance;
    }

    public Double getSwDevelopment() {
        return swDevelopment;
    }

    public void setSwDevelopment(Double swDevelopment) {
        this.swDevelopment = swDevelopment;
    }

    public Double getMarketing() {
        return marketing;
    }

    public void setMarketing(Double marketing) {
        this.marketing = marketing;
    }

    public Double getWebship() {
        return webship;
    }

    public void setWebship(Double webship) {
        this.webship = webship;
    }

    public Boolean getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(Boolean adminFunction) {
        this.adminFunction = adminFunction;
    }

    public Long getWebshipAdminid() {
        return webshipAdminid;
    }

    public void setWebshipAdminid(Long webshipAdminid) {
        this.webshipAdminid = webshipAdminid;
    }

    public String getDhlDomesticAccount() {
        return dhlDomesticAccount;
    }

    public void setDhlDomesticAccount(String dhlDomesticAccount) {
        this.dhlDomesticAccount = dhlDomesticAccount;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getFranchiseStartDate() {
        return franchiseStartDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setFranchiseStartDate(Date franchiseStartDate) {
        this.franchiseStartDate = franchiseStartDate;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Long getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(Long createdUserid) {
        this.createdUserid = createdUserid;
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

    public Double getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Double profitShare) {
        this.profitShare = profitShare;
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

    public Double getTntInternationalMarkupRate() {
        return tntInternationalMarkupRate;
    }

    public void setTntInternationalMarkupRate(Double tntInternationalMarkupRate) {
        this.tntInternationalMarkupRate = tntInternationalMarkupRate;
    }

    public Boolean getInvoicingCharge() {
        return invoicingCharge;
    }

    public void setInvoicingCharge(Boolean invoicingCharge) {
        this.invoicingCharge = invoicingCharge;
    }

    public String getTollIpecAccount() {
        return tollIpecAccount;
    }

    public void setTollIpecAccount(String tollIpecAccount) {
        this.tollIpecAccount = tollIpecAccount;
    }

    public String getRamAccount() {
        return ramAccount;
    }

    public void setRamAccount(String ramAccount) {
        this.ramAccount = ramAccount;
    }

    public Boolean getExcludeFromAll() {
        return excludeFromAll;
    }

    public void setExcludeFromAll(Boolean excludeFromAll) {
        this.excludeFromAll = excludeFromAll;
    }

    public Double getSwWebshipUsage() {
        return swWebshipUsage;
    }

    public void setSwWebshipUsage(Double swWebshipUsage) {
        this.swWebshipUsage = swWebshipUsage;
    }

    public Double getSwCollectionServiceFee() {
        return swCollectionServiceFee;
    }

    public void setSwCollectionServiceFee(Double swCollectionServiceFee) {
        this.swCollectionServiceFee = swCollectionServiceFee;
    }

    public Double getManagementMargin() {
        return managementMargin;
    }

    public void setManagementMargin(Double managementMargin) {
        this.managementMargin = managementMargin;
    }

    public Double getInternationalShipmentFee() {
        return internationalShipmentFee;
    }

    public void setInternationalShipmentFee(Double internationalShipmentFee) {
        this.internationalShipmentFee = internationalShipmentFee;
    }

    public Double getDomesticShipmentFee() {
        return domesticShipmentFee;
    }

    public void setDomesticShipmentFee(Double domesticShipmentFee) {
        this.domesticShipmentFee = domesticShipmentFee;
    }

    public Double getPrintingFee() {
        return printingFee;
    }

    public void setPrintingFee(Double printingFee) {
        this.printingFee = printingFee;
    }

    public Double getPostageFee() {
        return postageFee;
    }

    public void setPostageFee(Double postageFee) {
        this.postageFee = postageFee;
    }

    public String getUspsAccount() {
        return uspsAccount;
    }

    public void setUspsAccount(String uspsAccount) {
        this.uspsAccount = uspsAccount;
    }

    public String getOntracAccount() {
        return ontracAccount;
    }

    public void setOntracAccount(String ontracAccount) {
        this.ontracAccount = ontracAccount;
    }

    public Double getTollIpecMarkupRate() {
        return tollIpecMarkupRate;
    }

    public void setTollIpecMarkupRate(Double tollIpecMarkupRate) {
        this.tollIpecMarkupRate = tollIpecMarkupRate;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public CustomerAddressVo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressVo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public WebshipGroupVo getWebshipGroup() {
        return webshipGroup;
    }

    public void setWebshipGroup(WebshipGroupVo webshipGroup) {
        this.webshipGroup = webshipGroup;
    }

    public CustomerBillingAddressVo getCustomerBilling() {
        return customerBilling;
    }

    public void setCustomerBilling(CustomerBillingAddressVo customerBilling) {
        this.customerBilling = customerBilling;
    }

    public Double getDhlDomMarkupRate() {
        return dhlDomMarkupRate;
    }

    public void setDhlDomMarkupRate(Double dhlDomMarkupRate) {
        this.dhlDomMarkupRate = dhlDomMarkupRate;
    }

    public Boolean getEnableSi() {
        return enableSi;
    }

    public void setEnableSi(Boolean enableSi) {
        this.enableSi = enableSi;
    }

    public Double getUpsMarkupRate() {
        return upsMarkupRate;
    }

    public void setUpsMarkupRate(Double upsMarkupRate) {
        this.upsMarkupRate = upsMarkupRate;
    }

   
    public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	@Override
    public String toString() {
        return "FranchiseVo [id=" + id + ", franchiseCode=" + franchiseCode + ", createDate=" + createDate + ", activateDate=" + activateDate + ", inactive=" + inactive + ", groupId=" + groupId + ", webshipGroupid=" + webshipGroupid + ", salesRepId=" + salesRepId + ", registrationid=" + registrationid + ", gstid=" + gstid + ", franchiseTerritory=" + franchiseTerritory + ", dhlAccount=" + dhlAccount + ", dhlInternationalAccount=" + dhlInternationalAccount + ", dhlInboundAccount="
                + dhlInboundAccount + ", otherAccount=" + otherAccount + ", markupRate=" + markupRate + ", tntMarkupRate=" + tntMarkupRate + ", tollMarkupRate=" + tollMarkupRate + ", minimunBaseCharge=" + minimunBaseCharge + ", invoiceSorting=" + invoiceSorting + ", invoiceTerms=" + invoiceTerms + ", invoiceToCustomerid=" + invoiceToCustomerid + ", pickupFee=" + pickupFee + ", invoiceLateFee=" + invoiceLateFee + ", emailInvoice=" + emailInvoice + ", downloadCsvInvoice=" + downloadCsvInvoice
                + ", overnight=" + overnight + ", nextAfternoon=" + nextAfternoon + ", secondDay=" + secondDay + ", ground=" + ground + ", intlOutbound=" + intlOutbound + ", intlInbound=" + intlInbound + ", other=" + other + ", expressPerAirbill=" + expressPerAirbill + ", freightPerAirbill=" + freightPerAirbill + ", invoicingFee=" + invoicingFee + ", charge1=" + charge1 + ", charge1Amount=" + charge1Amount + ", charge2=" + charge2 + ", charge2Amount=" + charge2Amount + ", charge3=" + charge3
                + ", charge3Amount=" + charge3Amount + ", swMaintenance=" + swMaintenance + ", swDevelopment=" + swDevelopment + ", marketing=" + marketing + ", webship=" + webship + ", adminFunction=" + adminFunction + ", webshipAdminid=" + webshipAdminid + ", dhlDomesticAccount=" + dhlDomesticAccount + ", franchiseStartDate=" + franchiseStartDate + ", areaId=" + areaId + ", createdUserid=" + createdUserid + ", tntAccount=" + tntAccount + ", aaeAccount=" + aaeAccount + ", hubAccount="
                + hubAccount + ", profitShare=" + profitShare + ", fedexAccount=" + fedexAccount + ", tollPriorityAccount=" + tollPriorityAccount + ", ukMailAccount=" + ukMailAccount + ", upsAccount=" + upsAccount + ", bookingEmailNotification=" + bookingEmailNotification + ", tntInternationalMarkupRate=" + tntInternationalMarkupRate + ", invoicingCharge=" + invoicingCharge + ", tollIpecAccount=" + tollIpecAccount + ", ramAccount=" + ramAccount + ", excludeFromAll=" + excludeFromAll
                + ", swWebshipUsage=" + swWebshipUsage + ", swCollectionServiceFee=" + swCollectionServiceFee + ", managementMargin=" + managementMargin + ", internationalShipmentFee=" + internationalShipmentFee + ", domesticShipmentFee=" + domesticShipmentFee + ", printingFee=" + printingFee + ", postageFee=" + postageFee + ", uspsAccount=" + uspsAccount + ", ontracAccount=" + ontracAccount + ", startrackMarkupRate=" + startrackMarkupRate + ", tollIpecMarkupRate=" + tollIpecMarkupRate
                + ", dispatchId=" + dispatchId + ", startrackAccount=" + startrackAccount + ", customerAddress=" + customerAddress + ", webshipGroup=" + webshipGroup + ", customerBilling=" + customerBilling + ", dhlDomMarkupRate=" + dhlDomMarkupRate + ", enableSi=" + enableSi + "]";
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aaeAccount == null) ? 0 : aaeAccount.hashCode());
		result = prime * result + ((activateDate == null) ? 0 : activateDate.hashCode());
		result = prime * result + ((adminFunction == null) ? 0 : adminFunction.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((bookingEmailNotification == null) ? 0 : bookingEmailNotification.hashCode());
		result = prime * result + ((charge1 == null) ? 0 : charge1.hashCode());
		result = prime * result + ((charge1Amount == null) ? 0 : charge1Amount.hashCode());
		result = prime * result + ((charge2 == null) ? 0 : charge2.hashCode());
		result = prime * result + ((charge2Amount == null) ? 0 : charge2Amount.hashCode());
		result = prime * result + ((charge3 == null) ? 0 : charge3.hashCode());
		result = prime * result + ((charge3Amount == null) ? 0 : charge3Amount.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((createdUserid == null) ? 0 : createdUserid.hashCode());
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerBilling == null) ? 0 : customerBilling.hashCode());
		result = prime * result + ((dhlAccount == null) ? 0 : dhlAccount.hashCode());
		result = prime * result + ((dhlDomMarkupRate == null) ? 0 : dhlDomMarkupRate.hashCode());
		result = prime * result + ((dhlDomesticAccount == null) ? 0 : dhlDomesticAccount.hashCode());
		result = prime * result + ((dhlInboundAccount == null) ? 0 : dhlInboundAccount.hashCode());
		result = prime * result + ((dhlInternationalAccount == null) ? 0 : dhlInternationalAccount.hashCode());
		result = prime * result + ((dispatchId == null) ? 0 : dispatchId.hashCode());
		result = prime * result + ((domesticShipmentFee == null) ? 0 : domesticShipmentFee.hashCode());
		result = prime * result + ((downloadCsvInvoice == null) ? 0 : downloadCsvInvoice.hashCode());
		result = prime * result + ((emailInvoice == null) ? 0 : emailInvoice.hashCode());
		result = prime * result + ((enableSi == null) ? 0 : enableSi.hashCode());
		result = prime * result + ((excludeFromAll == null) ? 0 : excludeFromAll.hashCode());
		result = prime * result + ((expressPerAirbill == null) ? 0 : expressPerAirbill.hashCode());
		result = prime * result + ((fedexAccount == null) ? 0 : fedexAccount.hashCode());
		result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
		result = prime * result + ((franchiseStartDate == null) ? 0 : franchiseStartDate.hashCode());
		result = prime * result + ((franchiseTerritory == null) ? 0 : franchiseTerritory.hashCode());
		result = prime * result + ((freightPerAirbill == null) ? 0 : freightPerAirbill.hashCode());
		result = prime * result + ((ground == null) ? 0 : ground.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((gstid == null) ? 0 : gstid.hashCode());
		result = prime * result + ((hubAccount == null) ? 0 : hubAccount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inactive == null) ? 0 : inactive.hashCode());
		result = prime * result + ((internationalShipmentFee == null) ? 0 : internationalShipmentFee.hashCode());
		result = prime * result + ((intlInbound == null) ? 0 : intlInbound.hashCode());
		result = prime * result + ((intlOutbound == null) ? 0 : intlOutbound.hashCode());
		result = prime * result + ((invoiceLateFee == null) ? 0 : invoiceLateFee.hashCode());
		result = prime * result + ((invoiceSorting == null) ? 0 : invoiceSorting.hashCode());
		result = prime * result + ((invoiceTerms == null) ? 0 : invoiceTerms.hashCode());
		result = prime * result + ((invoiceToCustomerid == null) ? 0 : invoiceToCustomerid.hashCode());
		result = prime * result + ((invoicingCharge == null) ? 0 : invoicingCharge.hashCode());
		result = prime * result + ((invoicingFee == null) ? 0 : invoicingFee.hashCode());
		result = prime * result + ((managementMargin == null) ? 0 : managementMargin.hashCode());
		result = prime * result + ((marketing == null) ? 0 : marketing.hashCode());
		result = prime * result + ((markupRate == null) ? 0 : markupRate.hashCode());
		result = prime * result + ((minimunBaseCharge == null) ? 0 : minimunBaseCharge.hashCode());
		result = prime * result + ((nextAfternoon == null) ? 0 : nextAfternoon.hashCode());
		result = prime * result + ((ontracAccount == null) ? 0 : ontracAccount.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		result = prime * result + ((otherAccount == null) ? 0 : otherAccount.hashCode());
		result = prime * result + ((overnight == null) ? 0 : overnight.hashCode());
		result = prime * result + ((pickupFee == null) ? 0 : pickupFee.hashCode());
		result = prime * result + ((postageFee == null) ? 0 : postageFee.hashCode());
		result = prime * result + ((printingFee == null) ? 0 : printingFee.hashCode());
		result = prime * result + ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + ((profitShare == null) ? 0 : profitShare.hashCode());
		result = prime * result + ((ramAccount == null) ? 0 : ramAccount.hashCode());
		result = prime * result + ((registrationid == null) ? 0 : registrationid.hashCode());
		result = prime * result + ((salesRepId == null) ? 0 : salesRepId.hashCode());
		result = prime * result + ((secondDay == null) ? 0 : secondDay.hashCode());
		result = prime * result + ((startrackAccount == null) ? 0 : startrackAccount.hashCode());
		result = prime * result + ((startrackMarkupRate == null) ? 0 : startrackMarkupRate.hashCode());
		result = prime * result + ((swCollectionServiceFee == null) ? 0 : swCollectionServiceFee.hashCode());
		result = prime * result + ((swDevelopment == null) ? 0 : swDevelopment.hashCode());
		result = prime * result + ((swMaintenance == null) ? 0 : swMaintenance.hashCode());
		result = prime * result + ((swWebshipUsage == null) ? 0 : swWebshipUsage.hashCode());
		result = prime * result + ((tntAccount == null) ? 0 : tntAccount.hashCode());
		result = prime * result + ((tntInternationalMarkupRate == null) ? 0 : tntInternationalMarkupRate.hashCode());
		result = prime * result + ((tntMarkupRate == null) ? 0 : tntMarkupRate.hashCode());
		result = prime * result + ((tollIpecAccount == null) ? 0 : tollIpecAccount.hashCode());
		result = prime * result + ((tollIpecMarkupRate == null) ? 0 : tollIpecMarkupRate.hashCode());
		result = prime * result + ((tollMarkupRate == null) ? 0 : tollMarkupRate.hashCode());
		result = prime * result + ((tollPriorityAccount == null) ? 0 : tollPriorityAccount.hashCode());
		result = prime * result + ((ukMailAccount == null) ? 0 : ukMailAccount.hashCode());
		result = prime * result + ((upsAccount == null) ? 0 : upsAccount.hashCode());
		result = prime * result + ((upsMarkupRate == null) ? 0 : upsMarkupRate.hashCode());
		result = prime * result + ((uspsAccount == null) ? 0 : uspsAccount.hashCode());
		result = prime * result + ((webship == null) ? 0 : webship.hashCode());
		result = prime * result + ((webshipAdminid == null) ? 0 : webshipAdminid.hashCode());
		result = prime * result + ((webshipGroup == null) ? 0 : webshipGroup.hashCode());
		result = prime * result + ((webshipGroupid == null) ? 0 : webshipGroupid.hashCode());
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
		FranchiseVo other = (FranchiseVo) obj;
		if (aaeAccount == null) {
			if (other.aaeAccount != null)
				return false;
		} else if (!aaeAccount.equals(other.aaeAccount))
			return false;
		if (activateDate == null) {
			if (other.activateDate != null)
				return false;
		} else if (!activateDate.equals(other.activateDate))
			return false;
		if (adminFunction == null) {
			if (other.adminFunction != null)
				return false;
		} else if (!adminFunction.equals(other.adminFunction))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (bookingEmailNotification == null) {
			if (other.bookingEmailNotification != null)
				return false;
		} else if (!bookingEmailNotification.equals(other.bookingEmailNotification))
			return false;
		if (charge1 == null) {
			if (other.charge1 != null)
				return false;
		} else if (!charge1.equals(other.charge1))
			return false;
		if (charge1Amount == null) {
			if (other.charge1Amount != null)
				return false;
		} else if (!charge1Amount.equals(other.charge1Amount))
			return false;
		if (charge2 == null) {
			if (other.charge2 != null)
				return false;
		} else if (!charge2.equals(other.charge2))
			return false;
		if (charge2Amount == null) {
			if (other.charge2Amount != null)
				return false;
		} else if (!charge2Amount.equals(other.charge2Amount))
			return false;
		if (charge3 == null) {
			if (other.charge3 != null)
				return false;
		} else if (!charge3.equals(other.charge3))
			return false;
		if (charge3Amount == null) {
			if (other.charge3Amount != null)
				return false;
		} else if (!charge3Amount.equals(other.charge3Amount))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createdUserid == null) {
			if (other.createdUserid != null)
				return false;
		} else if (!createdUserid.equals(other.createdUserid))
			return false;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerBilling == null) {
			if (other.customerBilling != null)
				return false;
		} else if (!customerBilling.equals(other.customerBilling))
			return false;
		if (dhlAccount == null) {
			if (other.dhlAccount != null)
				return false;
		} else if (!dhlAccount.equals(other.dhlAccount))
			return false;
		if (dhlDomMarkupRate == null) {
			if (other.dhlDomMarkupRate != null)
				return false;
		} else if (!dhlDomMarkupRate.equals(other.dhlDomMarkupRate))
			return false;
		if (dhlDomesticAccount == null) {
			if (other.dhlDomesticAccount != null)
				return false;
		} else if (!dhlDomesticAccount.equals(other.dhlDomesticAccount))
			return false;
		if (dhlInboundAccount == null) {
			if (other.dhlInboundAccount != null)
				return false;
		} else if (!dhlInboundAccount.equals(other.dhlInboundAccount))
			return false;
		if (dhlInternationalAccount == null) {
			if (other.dhlInternationalAccount != null)
				return false;
		} else if (!dhlInternationalAccount.equals(other.dhlInternationalAccount))
			return false;
		if (dispatchId == null) {
			if (other.dispatchId != null)
				return false;
		} else if (!dispatchId.equals(other.dispatchId))
			return false;
		if (domesticShipmentFee == null) {
			if (other.domesticShipmentFee != null)
				return false;
		} else if (!domesticShipmentFee.equals(other.domesticShipmentFee))
			return false;
		if (downloadCsvInvoice == null) {
			if (other.downloadCsvInvoice != null)
				return false;
		} else if (!downloadCsvInvoice.equals(other.downloadCsvInvoice))
			return false;
		if (emailInvoice == null) {
			if (other.emailInvoice != null)
				return false;
		} else if (!emailInvoice.equals(other.emailInvoice))
			return false;
		if (enableSi == null) {
			if (other.enableSi != null)
				return false;
		} else if (!enableSi.equals(other.enableSi))
			return false;
		if (excludeFromAll == null) {
			if (other.excludeFromAll != null)
				return false;
		} else if (!excludeFromAll.equals(other.excludeFromAll))
			return false;
		if (expressPerAirbill == null) {
			if (other.expressPerAirbill != null)
				return false;
		} else if (!expressPerAirbill.equals(other.expressPerAirbill))
			return false;
		if (fedexAccount == null) {
			if (other.fedexAccount != null)
				return false;
		} else if (!fedexAccount.equals(other.fedexAccount))
			return false;
		if (franchiseCode == null) {
			if (other.franchiseCode != null)
				return false;
		} else if (!franchiseCode.equals(other.franchiseCode))
			return false;
		if (franchiseStartDate == null) {
			if (other.franchiseStartDate != null)
				return false;
		} else if (!franchiseStartDate.equals(other.franchiseStartDate))
			return false;
		if (franchiseTerritory == null) {
			if (other.franchiseTerritory != null)
				return false;
		} else if (!franchiseTerritory.equals(other.franchiseTerritory))
			return false;
		if (freightPerAirbill == null) {
			if (other.freightPerAirbill != null)
				return false;
		} else if (!freightPerAirbill.equals(other.freightPerAirbill))
			return false;
		if (ground == null) {
			if (other.ground != null)
				return false;
		} else if (!ground.equals(other.ground))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (gstid == null) {
			if (other.gstid != null)
				return false;
		} else if (!gstid.equals(other.gstid))
			return false;
		if (hubAccount == null) {
			if (other.hubAccount != null)
				return false;
		} else if (!hubAccount.equals(other.hubAccount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inactive == null) {
			if (other.inactive != null)
				return false;
		} else if (!inactive.equals(other.inactive))
			return false;
		if (internationalShipmentFee == null) {
			if (other.internationalShipmentFee != null)
				return false;
		} else if (!internationalShipmentFee.equals(other.internationalShipmentFee))
			return false;
		if (intlInbound == null) {
			if (other.intlInbound != null)
				return false;
		} else if (!intlInbound.equals(other.intlInbound))
			return false;
		if (intlOutbound == null) {
			if (other.intlOutbound != null)
				return false;
		} else if (!intlOutbound.equals(other.intlOutbound))
			return false;
		if (invoiceLateFee == null) {
			if (other.invoiceLateFee != null)
				return false;
		} else if (!invoiceLateFee.equals(other.invoiceLateFee))
			return false;
		if (invoiceSorting == null) {
			if (other.invoiceSorting != null)
				return false;
		} else if (!invoiceSorting.equals(other.invoiceSorting))
			return false;
		if (invoiceTerms == null) {
			if (other.invoiceTerms != null)
				return false;
		} else if (!invoiceTerms.equals(other.invoiceTerms))
			return false;
		if (invoiceToCustomerid == null) {
			if (other.invoiceToCustomerid != null)
				return false;
		} else if (!invoiceToCustomerid.equals(other.invoiceToCustomerid))
			return false;
		if (invoicingCharge == null) {
			if (other.invoicingCharge != null)
				return false;
		} else if (!invoicingCharge.equals(other.invoicingCharge))
			return false;
		if (invoicingFee == null) {
			if (other.invoicingFee != null)
				return false;
		} else if (!invoicingFee.equals(other.invoicingFee))
			return false;
		if (managementMargin == null) {
			if (other.managementMargin != null)
				return false;
		} else if (!managementMargin.equals(other.managementMargin))
			return false;
		if (marketing == null) {
			if (other.marketing != null)
				return false;
		} else if (!marketing.equals(other.marketing))
			return false;
		if (markupRate == null) {
			if (other.markupRate != null)
				return false;
		} else if (!markupRate.equals(other.markupRate))
			return false;
		if (minimunBaseCharge == null) {
			if (other.minimunBaseCharge != null)
				return false;
		} else if (!minimunBaseCharge.equals(other.minimunBaseCharge))
			return false;
		if (nextAfternoon == null) {
			if (other.nextAfternoon != null)
				return false;
		} else if (!nextAfternoon.equals(other.nextAfternoon))
			return false;
		if (ontracAccount == null) {
			if (other.ontracAccount != null)
				return false;
		} else if (!ontracAccount.equals(other.ontracAccount))
			return false;
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		if (otherAccount == null) {
			if (other.otherAccount != null)
				return false;
		} else if (!otherAccount.equals(other.otherAccount))
			return false;
		if (overnight == null) {
			if (other.overnight != null)
				return false;
		} else if (!overnight.equals(other.overnight))
			return false;
		if (pickupFee == null) {
			if (other.pickupFee != null)
				return false;
		} else if (!pickupFee.equals(other.pickupFee))
			return false;
		if (postageFee == null) {
			if (other.postageFee != null)
				return false;
		} else if (!postageFee.equals(other.postageFee))
			return false;
		if (printingFee == null) {
			if (other.printingFee != null)
				return false;
		} else if (!printingFee.equals(other.printingFee))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (profitShare == null) {
			if (other.profitShare != null)
				return false;
		} else if (!profitShare.equals(other.profitShare))
			return false;
		if (ramAccount == null) {
			if (other.ramAccount != null)
				return false;
		} else if (!ramAccount.equals(other.ramAccount))
			return false;
		if (registrationid == null) {
			if (other.registrationid != null)
				return false;
		} else if (!registrationid.equals(other.registrationid))
			return false;
		if (salesRepId == null) {
			if (other.salesRepId != null)
				return false;
		} else if (!salesRepId.equals(other.salesRepId))
			return false;
		if (secondDay == null) {
			if (other.secondDay != null)
				return false;
		} else if (!secondDay.equals(other.secondDay))
			return false;
		if (startrackAccount == null) {
			if (other.startrackAccount != null)
				return false;
		} else if (!startrackAccount.equals(other.startrackAccount))
			return false;
		if (startrackMarkupRate == null) {
			if (other.startrackMarkupRate != null)
				return false;
		} else if (!startrackMarkupRate.equals(other.startrackMarkupRate))
			return false;
		if (swCollectionServiceFee == null) {
			if (other.swCollectionServiceFee != null)
				return false;
		} else if (!swCollectionServiceFee.equals(other.swCollectionServiceFee))
			return false;
		if (swDevelopment == null) {
			if (other.swDevelopment != null)
				return false;
		} else if (!swDevelopment.equals(other.swDevelopment))
			return false;
		if (swMaintenance == null) {
			if (other.swMaintenance != null)
				return false;
		} else if (!swMaintenance.equals(other.swMaintenance))
			return false;
		if (swWebshipUsage == null) {
			if (other.swWebshipUsage != null)
				return false;
		} else if (!swWebshipUsage.equals(other.swWebshipUsage))
			return false;
		if (tntAccount == null) {
			if (other.tntAccount != null)
				return false;
		} else if (!tntAccount.equals(other.tntAccount))
			return false;
		if (tntInternationalMarkupRate == null) {
			if (other.tntInternationalMarkupRate != null)
				return false;
		} else if (!tntInternationalMarkupRate.equals(other.tntInternationalMarkupRate))
			return false;
		if (tntMarkupRate == null) {
			if (other.tntMarkupRate != null)
				return false;
		} else if (!tntMarkupRate.equals(other.tntMarkupRate))
			return false;
		if (tollIpecAccount == null) {
			if (other.tollIpecAccount != null)
				return false;
		} else if (!tollIpecAccount.equals(other.tollIpecAccount))
			return false;
		if (tollIpecMarkupRate == null) {
			if (other.tollIpecMarkupRate != null)
				return false;
		} else if (!tollIpecMarkupRate.equals(other.tollIpecMarkupRate))
			return false;
		if (tollMarkupRate == null) {
			if (other.tollMarkupRate != null)
				return false;
		} else if (!tollMarkupRate.equals(other.tollMarkupRate))
			return false;
		if (tollPriorityAccount == null) {
			if (other.tollPriorityAccount != null)
				return false;
		} else if (!tollPriorityAccount.equals(other.tollPriorityAccount))
			return false;
		if (ukMailAccount == null) {
			if (other.ukMailAccount != null)
				return false;
		} else if (!ukMailAccount.equals(other.ukMailAccount))
			return false;
		if (upsAccount == null) {
			if (other.upsAccount != null)
				return false;
		} else if (!upsAccount.equals(other.upsAccount))
			return false;
		if (upsMarkupRate == null) {
			if (other.upsMarkupRate != null)
				return false;
		} else if (!upsMarkupRate.equals(other.upsMarkupRate))
			return false;
		if (uspsAccount == null) {
			if (other.uspsAccount != null)
				return false;
		} else if (!uspsAccount.equals(other.uspsAccount))
			return false;
		if (webship == null) {
			if (other.webship != null)
				return false;
		} else if (!webship.equals(other.webship))
			return false;
		if (webshipAdminid == null) {
			if (other.webshipAdminid != null)
				return false;
		} else if (!webshipAdminid.equals(other.webshipAdminid))
			return false;
		if (webshipGroup == null) {
			if (other.webshipGroup != null)
				return false;
		} else if (!webshipGroup.equals(other.webshipGroup))
			return false;
		if (webshipGroupid == null) {
			if (other.webshipGroupid != null)
				return false;
		} else if (!webshipGroupid.equals(other.webshipGroupid))
			return false;
		return true;
	}

}