package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * File Name: CustomerVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 01-12-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo <br/>
 * Class: CustomerVo
 */
public class CustomerVo extends BaseVo {

    private static final long serialVersionUID = 1713688605111227199L;

    private Long id;

    private Long customerCode;

    private Long franchiseCode;

    private Integer srno;

    private Date createDate;

    private Date activateDate;

    private Boolean inActive;

    private Integer groupId;

    private Integer webshipGroupId;

    private Integer industryId;

    private Long salesRepId;

    private Long collectorId;

    private String registrationId;

    private Integer gstId;

    private String rejectionNote;

    private Double minimunBaseCharge;

    private Byte invoiceSorting;

    private Byte invoiceTerms;

    private Long invoiceToCustomerId;

    private Byte pickupFee;

    private Double invoiceLateFee;

    private Boolean emailInvoice;

    private Boolean downloadCsvInvoice;

    private Boolean adminFunction;

    private Long webshipAdminId;

    private String dhlAccount;

    private String dhlDomesticAccount;

    private String dhlInternationalAccount;

    private String dhlInboundAccount;

    private String otherAccount;

    private Integer previousCarrier;

    private Integer areaId;

    private Long createdUserId;

    private String tntAccount;

    private String aaeAccount;

    private String hubAccount;

    private String fedexAccount;

    private String tollPriorityAccount;

    private String startrackAccount;

    private String ukMailAccount;

    private String upsAccount;

    private Boolean bookingEmailNotification;

    private String tollIpecAccount;

    private String ramAccount;

    private String uspsAccount;

    private String ontracAccount;

    private String dispatchId;

    private Boolean enableSi;

    private CustomerAddressVo customerAddress;

    private WebshipGroupVo webshipGroup;

    private CustomerBillingAddressVo customerBilling;
    
   private byte[] profileImage;
    

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

    public CustomerAddressVo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressVo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getActivateDate() {
        return activateDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
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

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getRejectionNote() {
        return rejectionNote;
    }

    public void setRejectionNote(String rejectionNote) {
        this.rejectionNote = rejectionNote == null ? null : rejectionNote.trim();
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
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

    public Byte getPickupFee() {
        return pickupFee;
    }

    public void setPickupFee(Byte pickupFee) {
        this.pickupFee = pickupFee;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
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

    public Boolean getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(Boolean adminFunction) {
        this.adminFunction = adminFunction;
    }

    public String getDhlAccount() {
        return dhlAccount;
    }

    public void setDhlAccount(String dhlAccount) {
        this.dhlAccount = dhlAccount == null ? null : dhlAccount.trim();
    }

    public String getDhlDomesticAccount() {
        return dhlDomesticAccount;
    }

    public void setDhlDomesticAccount(String dhlDomesticAccount) {
        this.dhlDomesticAccount = dhlDomesticAccount == null ? null : dhlDomesticAccount.trim();
    }

    public String getDhlInternationalAccount() {
        return dhlInternationalAccount;
    }

    public void setDhlInternationalAccount(String dhlInternationalAccount) {
        this.dhlInternationalAccount = dhlInternationalAccount == null ? null : dhlInternationalAccount.trim();
    }

    public String getDhlInboundAccount() {
        return dhlInboundAccount;
    }

    public void setDhlInboundAccount(String dhlInboundAccount) {
        this.dhlInboundAccount = dhlInboundAccount == null ? null : dhlInboundAccount.trim();
    }

    public String getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(String otherAccount) {
        this.otherAccount = otherAccount == null ? null : otherAccount.trim();
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

    public Integer getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(Integer webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Long collectorId) {
        this.collectorId = collectorId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getGstId() {
        return gstId;
    }

    public void setGstId(Integer gstId) {
        this.gstId = gstId;
    }

    public Long getInvoiceToCustomerId() {
        return invoiceToCustomerId;
    }

    public void setInvoiceToCustomerId(Long invoiceToCustomerId) {
        this.invoiceToCustomerId = invoiceToCustomerId;
    }

    public Long getWebshipAdminId() {
        return webshipAdminId;
    }

    public void setWebshipAdminId(Long webshipAdminId) {
        this.webshipAdminId = webshipAdminId;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getTntAccount() {
        return tntAccount;
    }

    public void setTntAccount(String tntAccount) {
        this.tntAccount = tntAccount == null ? null : tntAccount.trim();
    }

    public String getAaeAccount() {
        return aaeAccount;
    }

    public void setAaeAccount(String aaeAccount) {
        this.aaeAccount = aaeAccount == null ? null : aaeAccount.trim();
    }

    public String getHubAccount() {
        return hubAccount;
    }

    public void setHubAccount(String hubAccount) {
        this.hubAccount = hubAccount == null ? null : hubAccount.trim();
    }

    public String getFedexAccount() {
        return fedexAccount;
    }

    public void setFedexAccount(String fedexAccount) {
        this.fedexAccount = fedexAccount == null ? null : fedexAccount.trim();
    }

    public String getTollPriorityAccount() {
        return tollPriorityAccount;
    }

    public void setTollPriorityAccount(String tollPriorityAccount) {
        this.tollPriorityAccount = tollPriorityAccount == null ? null : tollPriorityAccount.trim();
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
        this.ukMailAccount = ukMailAccount == null ? null : ukMailAccount.trim();
    }

    public String getUpsAccount() {
        return upsAccount;
    }

    public void setUpsAccount(String upsAccount) {
        this.upsAccount = upsAccount == null ? null : upsAccount.trim();
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
        this.tollIpecAccount = tollIpecAccount == null ? null : tollIpecAccount.trim();
    }

    public String getRamAccount() {
        return ramAccount;
    }

    public void setRamAccount(String ramAccount) {
        this.ramAccount = ramAccount == null ? null : ramAccount.trim();
    }

    public String getUspsAccount() {
        return uspsAccount;
    }

    public void setUspsAccount(String uspsAccount) {
        this.uspsAccount = uspsAccount == null ? null : uspsAccount.trim();
    }

    public String getOntracAccount() {
        return ontracAccount;
    }

    public void setOntracAccount(String ontracAccount) {
        this.ontracAccount = ontracAccount == null ? null : ontracAccount.trim();
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Boolean getEnableSi() {
        return enableSi;
    }

    public void setEnableSi(Boolean enableSi) {
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
		return "CustomerVo [id=" + id + ", customerCode=" + customerCode + ", franchiseCode=" + franchiseCode
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
				+ ontracAccount + ", dispatchId=" + dispatchId + ", enableSi=" + enableSi + ", customerAddress="
				+ customerAddress + ", webshipGroup=" + webshipGroup + ", customerBilling=" + customerBilling
				+ ", profileImage=" + profileImage + "]";
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
		CustomerVo other = (CustomerVo) obj;
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