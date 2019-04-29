package com.gms.xms.model;

import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.webship.WebshipGroupModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from CustomerModel
 * <p>
 * Author HungNT Date May 22, 2015
 */
public class CustomerModel extends BaseModel {
    private static final long serialVersionUID = 258381042688283749L;

    private String id;

    private String customerCode;

    private String franchiseCode;

    private String srno;

    private String createDate;

    private String activateDate;

    private String inActive;

    private String groupId;

    private String webshipGroupId;

    private String industryId;

    private String salesRepId;

    private String collectorId;

    private String registrationId;

    private String gstId;

    private String rejectionNote;

    private String minimunBaseCharge;

    private String invoiceSorting;

    private String invoiceTerms;

    private String invoiceToCustomerId;

    private String pickupFee;

    private String invoiceLateFee;

    private String emailInvoice;

    private String downloadCsvInvoice;

    private String adminFunction;

    private String webshipAdminId;

    private String dhlAccount;

    private String dhlDomesticAccount;

    private String dhlInternationalAccount;

    private String dhlInboundAccount;

    private String otherAccount;

    private String previousCarrier;

    private String areaId;

    private String createdUserId;

    private String tntAccount;

    private String aaeAccount;

    private String hubAccount;

    private String fedexAccount;

    private String tollPriorityAccount;

    private String startrackAccount;

    private String ukMailAccount;

    private String upsAccount;

    private String bookingEmailNotification;

    private String tollIpecAccount;

    private String ramAccount;

    private String uspsAccount;

    private String ontracAccount;

    private String dispatchId;

    private String enableSi;
    
    private byte[] profileImage;

    @JsonIgnore
    private String owner;

    private CustomerAddressModel customerAddress;

    private WebshipGroupModel webshipGroup;

    private CustomerBillingAddressModel customerBilling;

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

    public CustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
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

    public String getInActive() {
        return inActive;
    }

    public void setInActive(String inActive) {
        this.inActive = inActive;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(String webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public String getRejectionNote() {
        return rejectionNote;
    }

    public void setRejectionNote(String rejectionNote) {
        this.rejectionNote = rejectionNote;
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

    public String getInvoiceToCustomerId() {
        return invoiceToCustomerId;
    }

    public void setInvoiceToCustomerId(String invoiceToCustomerId) {
        this.invoiceToCustomerId = invoiceToCustomerId;
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

    public String getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(String adminFunction) {
        this.adminFunction = adminFunction;
    }

    public String getWebshipAdminId() {
        return webshipAdminId;
    }

    public void setWebshipAdminId(String webshipAdminId) {
        this.webshipAdminId = webshipAdminId;
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

    public String getPreviousCarrier() {
        return previousCarrier;
    }

    public void setPreviousCarrier(String previousCarrier) {
        this.previousCarrier = previousCarrier;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
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

    public String getStartrackAccount() {
        return startrackAccount;
    }

    public void setStartrackAccount(String startrackAccount) {
        this.startrackAccount = startrackAccount;
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

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getEnableSi() {
        return enableSi;
    }

    public void setEnableSi(String enableSi) {
        this.enableSi = enableSi;
    }
    
    

   

    public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	@Override
	public String toString() {
		return "CustomerModel [id=" + id + ", customerCode=" + customerCode + ", franchiseCode=" + franchiseCode
				+ ", srno=" + srno + ", createDate=" + createDate + ", activateDate=" + activateDate + ", inActive="
				+ inActive + ", groupId=" + groupId + ", webshipGroupId=" + webshipGroupId + ", industryId="
				+ industryId + ", salesRepId=" + salesRepId + ", collectorId=" + collectorId + ", registrationId="
				+ registrationId + ", gstId=" + gstId + ", rejectionNote=" + rejectionNote + ", minimunBaseCharge="
				+ minimunBaseCharge + ", invoiceSorting=" + invoiceSorting + ", invoiceTerms=" + invoiceTerms
				+ ", invoiceToCustomerId=" + invoiceToCustomerId + ", pickupFee=" + pickupFee + ", invoiceLateFee="
				+ invoiceLateFee + ", emailInvoice=" + emailInvoice + ", downloadCsvInvoice=" + downloadCsvInvoice
				+ ", adminFunction=" + adminFunction + ", webshipAdminId=" + webshipAdminId + ", dhlAccount="
				+ dhlAccount + ", dhlDomesticAccount=" + dhlDomesticAccount + ", dhlInternationalAccount="
				+ dhlInternationalAccount + ", dhlInboundAccount=" + dhlInboundAccount + ", otherAccount="
				+ otherAccount + ", previousCarrier=" + previousCarrier + ", areaId=" + areaId + ", createdUserId="
				+ createdUserId + ", tntAccount=" + tntAccount + ", aaeAccount=" + aaeAccount + ", hubAccount="
				+ hubAccount + ", fedexAccount=" + fedexAccount + ", tollPriorityAccount=" + tollPriorityAccount
				+ ", startrackAccount=" + startrackAccount + ", ukMailAccount=" + ukMailAccount + ", upsAccount="
				+ upsAccount + ", bookingEmailNotification=" + bookingEmailNotification + ", tollIpecAccount="
				+ tollIpecAccount + ", ramAccount=" + ramAccount + ", uspsAccount=" + uspsAccount + ", ontracAccount="
				+ ontracAccount + ", dispatchId=" + dispatchId + ", enableSi=" + enableSi + ", profileImage="
				+ profileImage + ", owner=" + owner + ", customerAddress=" + customerAddress + ", webshipGroup="
				+ webshipGroup + ", customerBilling=" + customerBilling + "]";
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
		result = prime * result + ((collectorId == null) ? 0 : collectorId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((createdUserId == null) ? 0 : createdUserId.hashCode());
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerBilling == null) ? 0 : customerBilling.hashCode());
		result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result + ((dhlAccount == null) ? 0 : dhlAccount.hashCode());
		result = prime * result + ((dhlDomesticAccount == null) ? 0 : dhlDomesticAccount.hashCode());
		result = prime * result + ((dhlInboundAccount == null) ? 0 : dhlInboundAccount.hashCode());
		result = prime * result + ((dhlInternationalAccount == null) ? 0 : dhlInternationalAccount.hashCode());
		result = prime * result + ((dispatchId == null) ? 0 : dispatchId.hashCode());
		result = prime * result + ((downloadCsvInvoice == null) ? 0 : downloadCsvInvoice.hashCode());
		result = prime * result + ((emailInvoice == null) ? 0 : emailInvoice.hashCode());
		result = prime * result + ((enableSi == null) ? 0 : enableSi.hashCode());
		result = prime * result + ((fedexAccount == null) ? 0 : fedexAccount.hashCode());
		result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((gstId == null) ? 0 : gstId.hashCode());
		result = prime * result + ((hubAccount == null) ? 0 : hubAccount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inActive == null) ? 0 : inActive.hashCode());
		result = prime * result + ((industryId == null) ? 0 : industryId.hashCode());
		result = prime * result + ((invoiceLateFee == null) ? 0 : invoiceLateFee.hashCode());
		result = prime * result + ((invoiceSorting == null) ? 0 : invoiceSorting.hashCode());
		result = prime * result + ((invoiceTerms == null) ? 0 : invoiceTerms.hashCode());
		result = prime * result + ((invoiceToCustomerId == null) ? 0 : invoiceToCustomerId.hashCode());
		result = prime * result + ((minimunBaseCharge == null) ? 0 : minimunBaseCharge.hashCode());
		result = prime * result + ((ontracAccount == null) ? 0 : ontracAccount.hashCode());
		result = prime * result + ((otherAccount == null) ? 0 : otherAccount.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((pickupFee == null) ? 0 : pickupFee.hashCode());
		result = prime * result + ((previousCarrier == null) ? 0 : previousCarrier.hashCode());
		result = prime * result + ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + ((ramAccount == null) ? 0 : ramAccount.hashCode());
		result = prime * result + ((registrationId == null) ? 0 : registrationId.hashCode());
		result = prime * result + ((rejectionNote == null) ? 0 : rejectionNote.hashCode());
		result = prime * result + ((salesRepId == null) ? 0 : salesRepId.hashCode());
		result = prime * result + ((srno == null) ? 0 : srno.hashCode());
		result = prime * result + ((startrackAccount == null) ? 0 : startrackAccount.hashCode());
		result = prime * result + ((tntAccount == null) ? 0 : tntAccount.hashCode());
		result = prime * result + ((tollIpecAccount == null) ? 0 : tollIpecAccount.hashCode());
		result = prime * result + ((tollPriorityAccount == null) ? 0 : tollPriorityAccount.hashCode());
		result = prime * result + ((ukMailAccount == null) ? 0 : ukMailAccount.hashCode());
		result = prime * result + ((upsAccount == null) ? 0 : upsAccount.hashCode());
		result = prime * result + ((uspsAccount == null) ? 0 : uspsAccount.hashCode());
		result = prime * result + ((webshipAdminId == null) ? 0 : webshipAdminId.hashCode());
		result = prime * result + ((webshipGroup == null) ? 0 : webshipGroup.hashCode());
		result = prime * result + ((webshipGroupId == null) ? 0 : webshipGroupId.hashCode());
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
		CustomerModel other = (CustomerModel) obj;
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
		if (collectorId == null) {
			if (other.collectorId != null)
				return false;
		} else if (!collectorId.equals(other.collectorId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createdUserId == null) {
			if (other.createdUserId != null)
				return false;
		} else if (!createdUserId.equals(other.createdUserId))
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
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		if (dhlAccount == null) {
			if (other.dhlAccount != null)
				return false;
		} else if (!dhlAccount.equals(other.dhlAccount))
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
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (gstId == null) {
			if (other.gstId != null)
				return false;
		} else if (!gstId.equals(other.gstId))
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
		if (inActive == null) {
			if (other.inActive != null)
				return false;
		} else if (!inActive.equals(other.inActive))
			return false;
		if (industryId == null) {
			if (other.industryId != null)
				return false;
		} else if (!industryId.equals(other.industryId))
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
		if (invoiceToCustomerId == null) {
			if (other.invoiceToCustomerId != null)
				return false;
		} else if (!invoiceToCustomerId.equals(other.invoiceToCustomerId))
			return false;
		if (minimunBaseCharge == null) {
			if (other.minimunBaseCharge != null)
				return false;
		} else if (!minimunBaseCharge.equals(other.minimunBaseCharge))
			return false;
		if (ontracAccount == null) {
			if (other.ontracAccount != null)
				return false;
		} else if (!ontracAccount.equals(other.ontracAccount))
			return false;
		if (otherAccount == null) {
			if (other.otherAccount != null)
				return false;
		} else if (!otherAccount.equals(other.otherAccount))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (pickupFee == null) {
			if (other.pickupFee != null)
				return false;
		} else if (!pickupFee.equals(other.pickupFee))
			return false;
		if (previousCarrier == null) {
			if (other.previousCarrier != null)
				return false;
		} else if (!previousCarrier.equals(other.previousCarrier))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (ramAccount == null) {
			if (other.ramAccount != null)
				return false;
		} else if (!ramAccount.equals(other.ramAccount))
			return false;
		if (registrationId == null) {
			if (other.registrationId != null)
				return false;
		} else if (!registrationId.equals(other.registrationId))
			return false;
		if (rejectionNote == null) {
			if (other.rejectionNote != null)
				return false;
		} else if (!rejectionNote.equals(other.rejectionNote))
			return false;
		if (salesRepId == null) {
			if (other.salesRepId != null)
				return false;
		} else if (!salesRepId.equals(other.salesRepId))
			return false;
		if (srno == null) {
			if (other.srno != null)
				return false;
		} else if (!srno.equals(other.srno))
			return false;
		if (startrackAccount == null) {
			if (other.startrackAccount != null)
				return false;
		} else if (!startrackAccount.equals(other.startrackAccount))
			return false;
		if (tntAccount == null) {
			if (other.tntAccount != null)
				return false;
		} else if (!tntAccount.equals(other.tntAccount))
			return false;
		if (tollIpecAccount == null) {
			if (other.tollIpecAccount != null)
				return false;
		} else if (!tollIpecAccount.equals(other.tollIpecAccount))
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
		if (uspsAccount == null) {
			if (other.uspsAccount != null)
				return false;
		} else if (!uspsAccount.equals(other.uspsAccount))
			return false;
		if (webshipAdminId == null) {
			if (other.webshipAdminId != null)
				return false;
		} else if (!webshipAdminId.equals(other.webshipAdminId))
			return false;
		if (webshipGroup == null) {
			if (other.webshipGroup != null)
				return false;
		} else if (!webshipGroup.equals(other.webshipGroup))
			return false;
		if (webshipGroupId == null) {
			if (other.webshipGroupId != null)
				return false;
		} else if (!webshipGroupId.equals(other.webshipGroupId))
			return false;
		return true;
	}

}
