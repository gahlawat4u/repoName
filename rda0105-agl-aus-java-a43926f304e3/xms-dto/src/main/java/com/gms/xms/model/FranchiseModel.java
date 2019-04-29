package com.gms.xms.model;

import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.webship.WebshipGroupModel;

/**
 * Posted from FranchiseModel
 * <p>
 * Author TANDT 09-11-2015
 */
public class FranchiseModel extends BaseModel {

    private static final long serialVersionUID = 3428856988303195597L;

    private String id;

    private String franchiseCode;

    private String createDate;

    private String activateDate;

    private String inactive;

    private String groupId;

    private String webshipGroupid;

    private String salesRepId;

    private String registrationid;

    private String gstid;

    private String franchiseTerritory;

    private String dhlAccount;

    private String dhlInternationalAccount;

    private String dhlInboundAccount;

    private String otherAccount;

    private String markupRate;

    private String tntMarkupRate;

    private String tollMarkupRate;

    private String minimunBaseCharge;

    private String invoiceSorting;

    private String invoiceTerms;

    private String invoiceToCustomerid;

    private String pickupFee;

    private String invoiceLateFee;

    private String emailInvoice;

    private String downloadCsvInvoice;

    private String overnight;

    private String nextAfternoon;

    private String secondDay;

    private String ground;

    private String intlOutbound;

    private String intlInbound;

    private String other;

    private String expressPerAirbill;

    private String freightPerAirbill;

    private String invoicingFee;

    private String charge1;

    private String charge1Amount;

    private String charge2;

    private String charge2Amount;

    private String charge3;

    private String charge3Amount;

    private String swMaintenance;

    private String swDevelopment;

    private String marketing;

    private String webship;

    private String adminFunction;

    private String webshipAdminid;

    private String dhlDomesticAccount;

    private String franchiseStartDate;

    private String areaId;

    private String createdUserid;

    private String tntAccount;

    private String aaeAccount;

    private String hubAccount;

    private String profitShare;

    private String fedexAccount;

    private String tollPriorityAccount;

    private String ukMailAccount;

    private String upsAccount;

    private String bookingEmailNotification;

    private String tntInternationalMarkupRate;

    private String invoicingCharge;

    private String tollIpecAccount;

    private String ramAccount;

    private String excludeFromAll;

    private String swWebshipUsage;

    private String swCollectionServiceFee;

    private String managementMargin;

    private String internationalShipmentFee;

    private String domesticShipmentFee;

    private String printingFee;

    private String postageFee;

    private String uspsAccount;

    private String ontracAccount;

    private String tollIpecMarkupRate;

    private String dispatchId;

    private String startrackMarkupRate;

    private String startrackAccount;

    private String dhlDomMarkupRate;

    private String enableSi;

    private String upsMarkupRate;

    private CustomerAddressModel customerAddress;

    private WebshipGroupModel webshipGroup;

    private CustomerBillingAddressModel customerBilling;
    
    private byte[] profileImage;
    
    

    public String getStartrackAccount() {
        return startrackAccount;
    }

    public void setStartrackAccount(String startrackAccount) {
        this.startrackAccount = startrackAccount;
    }

    public String getStartrackMarkupRate() {
        return startrackMarkupRate;
    }

    public void setStartrackMarkupRate(String startrackMarkupRate) {
        this.startrackMarkupRate = startrackMarkupRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getWebshipGroupid() {
        return webshipGroupid;
    }

    public void setWebshipGroupid(String webshipGroupid) {
        this.webshipGroupid = webshipGroupid;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(String registrationid) {
        this.registrationid = registrationid;
    }

    public String getGstid() {
        return gstid;
    }

    public void setGstid(String gstid) {
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

    public String getMarkupRate() {
        return markupRate;
    }

    public void setMarkupRate(String markupRate) {
        this.markupRate = markupRate;
    }

    public String getTntMarkupRate() {
        return tntMarkupRate;
    }

    public void setTntMarkupRate(String tntMarkupRate) {
        this.tntMarkupRate = tntMarkupRate;
    }

    public String getTollMarkupRate() {
        return tollMarkupRate;
    }

    public void setTollMarkupRate(String tollMarkupRate) {
        this.tollMarkupRate = tollMarkupRate;
    }

    public String getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(String minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public String getInvoiceSorting() {
        return invoiceSorting;
    }

    public void setInvoiceSorting(String invoiceSorting) {
        this.invoiceSorting = invoiceSorting;
    }

    public String getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(String invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public String getInvoiceToCustomerid() {
        return invoiceToCustomerid;
    }

    public void setInvoiceToCustomerid(String invoiceToCustomerid) {
        this.invoiceToCustomerid = invoiceToCustomerid;
    }

    public String getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(String pickupFee) {
        this.pickupFee = pickupFee;
    }

    public String getInvoiceLateFee() {
        return invoiceLateFee;
    }

    public void setInvoiceLateFee(String invoiceLateFee) {
        this.invoiceLateFee = invoiceLateFee;
    }

    public String getEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(String emailInvoice) {
        this.emailInvoice = emailInvoice;
    }

    public String getDownloadCsvInvoice() {
        return downloadCsvInvoice;
    }

    public void setDownloadCsvInvoice(String downloadCsvInvoice) {
        this.downloadCsvInvoice = downloadCsvInvoice;
    }

    public String getOvernight() {
        return overnight;
    }

    public void setOvernight(String overnight) {
        this.overnight = overnight;
    }

    public String getNextAfternoon() {
        return nextAfternoon;
    }

    public void setNextAfternoon(String nextAfternoon) {
        this.nextAfternoon = nextAfternoon;
    }

    public String getSecondDay() {
        return secondDay;
    }

    public void setSecondDay(String secondDay) {
        this.secondDay = secondDay;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getIntlOutbound() {
        return intlOutbound;
    }

    public void setIntlOutbound(String intlOutbound) {
        this.intlOutbound = intlOutbound;
    }

    public String getIntlInbound() {
        return intlInbound;
    }

    public void setIntlInbound(String intlInbound) {
        this.intlInbound = intlInbound;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getExpressPerAirbill() {
        return expressPerAirbill;
    }

    public void setExpressPerAirbill(String expressPerAirbill) {
        this.expressPerAirbill = expressPerAirbill;
    }

    public String getFreightPerAirbill() {
        return freightPerAirbill;
    }

    public void setFreightPerAirbill(String freightPerAirbill) {
        this.freightPerAirbill = freightPerAirbill;
    }

    public String getInvoicingFee() {
        return invoicingFee;
    }

    public void setInvoicingFee(String invoicingFee) {
        this.invoicingFee = invoicingFee;
    }

    public String getCharge1() {
        return charge1;
    }

    public void setCharge1(String charge1) {
        this.charge1 = charge1;
    }

    public String getCharge1Amount() {
        return charge1Amount;
    }

    public void setCharge1Amount(String charge1Amount) {
        this.charge1Amount = charge1Amount;
    }

    public String getCharge2() {
        return charge2;
    }

    public void setCharge2(String charge2) {
        this.charge2 = charge2;
    }

    public String getCharge2Amount() {
        return charge2Amount;
    }

    public void setCharge2Amount(String charge2Amount) {
        this.charge2Amount = charge2Amount;
    }

    public String getCharge3() {
        return charge3;
    }

    public void setCharge3(String charge3) {
        this.charge3 = charge3;
    }

    public String getCharge3Amount() {
        return charge3Amount;
    }

    public void setCharge3Amount(String charge3Amount) {
        this.charge3Amount = charge3Amount;
    }

    public String getSwMaintenance() {
        return swMaintenance;
    }

    public void setSwMaintenance(String swMaintenance) {
        this.swMaintenance = swMaintenance;
    }

    public String getSwDevelopment() {
        return swDevelopment;
    }

    public void setSwDevelopment(String swDevelopment) {
        this.swDevelopment = swDevelopment;
    }

    public String getMarketing() {
        return marketing;
    }

    public void setMarketing(String marketing) {
        this.marketing = marketing;
    }

    public String getWebship() {
        return webship;
    }

    public void setWebship(String webship) {
        this.webship = webship;
    }

    public String getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(String adminFunction) {
        this.adminFunction = adminFunction;
    }

    public String getWebshipAdminid() {
        return webshipAdminid;
    }

    public void setWebshipAdminid(String webshipAdminid) {
        this.webshipAdminid = webshipAdminid;
    }

    public String getDhlDomesticAccount() {
        return dhlDomesticAccount;
    }

    public void setDhlDomesticAccount(String dhlDomesticAccount) {
        this.dhlDomesticAccount = dhlDomesticAccount;
    }

    public String getFranchiseStartDate() {
        return franchiseStartDate;
    }

    public void setFranchiseStartDate(String franchiseStartDate) {
        this.franchiseStartDate = franchiseStartDate;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(String createdUserid) {
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

    public String getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(String profitShare) {
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

    public String getBookingEmailNotification() {
        return bookingEmailNotification;
    }

    public void setBookingEmailNotification(String bookingEmailNotification) {
        this.bookingEmailNotification = bookingEmailNotification;
    }

    public String getTntInternationalMarkupRate() {
        return tntInternationalMarkupRate;
    }

    public void setTntInternationalMarkupRate(String tntInternationalMarkupRate) {
        this.tntInternationalMarkupRate = tntInternationalMarkupRate;
    }

    public String getInvoicingCharge() {
        return invoicingCharge;
    }

    public void setInvoicingCharge(String invoicingCharge) {
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

    public String getExcludeFromAll() {
        return excludeFromAll;
    }

    public void setExcludeFromAll(String excludeFromAll) {
        this.excludeFromAll = excludeFromAll;
    }

    public String getSwWebshipUsage() {
        return swWebshipUsage;
    }

    public void setSwWebshipUsage(String swWebshipUsage) {
        this.swWebshipUsage = swWebshipUsage;
    }

    public String getSwCollectionServiceFee() {
        return swCollectionServiceFee;
    }

    public void setSwCollectionServiceFee(String swCollectionServiceFee) {
        this.swCollectionServiceFee = swCollectionServiceFee;
    }

    public String getManagementMargin() {
        return managementMargin;
    }

    public void setManagementMargin(String managementMargin) {
        this.managementMargin = managementMargin;
    }

    public String getInternationalShipmentFee() {
        return internationalShipmentFee;
    }

    public void setInternationalShipmentFee(String internationalShipmentFee) {
        this.internationalShipmentFee = internationalShipmentFee;
    }

    public String getDomesticShipmentFee() {
        return domesticShipmentFee;
    }

    public void setDomesticShipmentFee(String domesticShipmentFee) {
        this.domesticShipmentFee = domesticShipmentFee;
    }

    public String getPrintingFee() {
        return printingFee;
    }

    public void setPrintingFee(String printingFee) {
        this.printingFee = printingFee;
    }

    public String getPostageFee() {
        return postageFee;
    }

    public void setPostageFee(String postageFee) {
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

    public String getTollIpecMarkupRate() {
        return tollIpecMarkupRate;
    }

    public void setTollIpecMarkupRate(String tollIpecMarkupRate) {
        this.tollIpecMarkupRate = tollIpecMarkupRate;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public CustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }

    public WebshipGroupModel getWebshipGroup() {
        return webshipGroup;
    }

    public void setWebshipGroup(WebshipGroupModel webshipGroup) {
        this.webshipGroup = webshipGroup;
    }

    public CustomerBillingAddressModel getCustomerBilling() {
        return customerBilling;
    }

    public void setCustomerBilling(CustomerBillingAddressModel customerBilling) {
        this.customerBilling = customerBilling;
    }

    public String getDhlDomMarkupRate() {
        return dhlDomMarkupRate;
    }

    public void setDhlDomMarkupRate(String dhlDomMarkupRate) {
        this.dhlDomMarkupRate = dhlDomMarkupRate;
    }

    public String getEnableSi() {
        return enableSi;
    }

    public void setEnableSi(String enableSi) {
        this.enableSi = enableSi;
    }

    public String getUpsMarkupRate() {
        return upsMarkupRate;
    }

    public void setUpsMarkupRate(String upsMarkupRate) {
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
		return "FranchiseModel [id=" + id + ", franchiseCode=" + franchiseCode + ", createDate=" + createDate
				+ ", activateDate=" + activateDate + ", inactive=" + inactive + ", groupId=" + groupId
				+ ", webshipGroupid=" + webshipGroupid + ", salesRepId=" + salesRepId + ", registrationid="
				+ registrationid + ", gstid=" + gstid + ", franchiseTerritory=" + franchiseTerritory + ", dhlAccount="
				+ dhlAccount + ", dhlInternationalAccount=" + dhlInternationalAccount + ", dhlInboundAccount="
				+ dhlInboundAccount + ", otherAccount=" + otherAccount + ", markupRate=" + markupRate
				+ ", tntMarkupRate=" + tntMarkupRate + ", tollMarkupRate=" + tollMarkupRate + ", minimunBaseCharge="
				+ minimunBaseCharge + ", invoiceSorting=" + invoiceSorting + ", invoiceTerms=" + invoiceTerms
				+ ", invoiceToCustomerid=" + invoiceToCustomerid + ", pickupFee=" + pickupFee + ", invoiceLateFee="
				+ invoiceLateFee + ", emailInvoice=" + emailInvoice + ", downloadCsvInvoice=" + downloadCsvInvoice
				+ ", overnight=" + overnight + ", nextAfternoon=" + nextAfternoon + ", secondDay=" + secondDay
				+ ", ground=" + ground + ", intlOutbound=" + intlOutbound + ", intlInbound=" + intlInbound + ", other="
				+ other + ", expressPerAirbill=" + expressPerAirbill + ", freightPerAirbill=" + freightPerAirbill
				+ ", invoicingFee=" + invoicingFee + ", charge1=" + charge1 + ", charge1Amount=" + charge1Amount
				+ ", charge2=" + charge2 + ", charge2Amount=" + charge2Amount + ", charge3=" + charge3
				+ ", charge3Amount=" + charge3Amount + ", swMaintenance=" + swMaintenance + ", swDevelopment="
				+ swDevelopment + ", marketing=" + marketing + ", webship=" + webship + ", adminFunction="
				+ adminFunction + ", webshipAdminid=" + webshipAdminid + ", dhlDomesticAccount=" + dhlDomesticAccount
				+ ", franchiseStartDate=" + franchiseStartDate + ", areaId=" + areaId + ", createdUserid="
				+ createdUserid + ", tntAccount=" + tntAccount + ", aaeAccount=" + aaeAccount + ", hubAccount="
				+ hubAccount + ", profitShare=" + profitShare + ", fedexAccount=" + fedexAccount
				+ ", tollPriorityAccount=" + tollPriorityAccount + ", ukMailAccount=" + ukMailAccount + ", upsAccount="
				+ upsAccount + ", bookingEmailNotification=" + bookingEmailNotification
				+ ", tntInternationalMarkupRate=" + tntInternationalMarkupRate + ", invoicingCharge=" + invoicingCharge
				+ ", tollIpecAccount=" + tollIpecAccount + ", ramAccount=" + ramAccount + ", excludeFromAll="
				+ excludeFromAll + ", swWebshipUsage=" + swWebshipUsage + ", swCollectionServiceFee="
				+ swCollectionServiceFee + ", managementMargin=" + managementMargin + ", internationalShipmentFee="
				+ internationalShipmentFee + ", domesticShipmentFee=" + domesticShipmentFee + ", printingFee="
				+ printingFee + ", postageFee=" + postageFee + ", uspsAccount=" + uspsAccount + ", ontracAccount="
				+ ontracAccount + ", tollIpecMarkupRate=" + tollIpecMarkupRate + ", dispatchId=" + dispatchId
				+ ", startrackMarkupRate=" + startrackMarkupRate + ", startrackAccount=" + startrackAccount
				+ ", dhlDomMarkupRate=" + dhlDomMarkupRate + ", enableSi=" + enableSi + ", upsMarkupRate="
				+ upsMarkupRate + ", customerAddress=" + customerAddress + ", webshipGroup=" + webshipGroup
				+ ", customerBilling=" + customerBilling + ", profileImage=" + profileImage + "]";
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
		FranchiseModel other = (FranchiseModel) obj;
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