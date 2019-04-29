package com.gms.xms.txndb.vo.webship;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.txndb.vo.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Posted from ShipmentTypeVo
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class ShipmentTypeVo extends BaseVo {
    private static final long serialVersionUID = 4699718229801414494L;

    private Integer shipmentTypeId;

    private Integer servicePriority;

    private String shipmentTypeName;

    private String ediDescription;

    private String serviceCode;

    private Double basicCharge;

    private Boolean document;

    private Boolean documentInbound;

    private Boolean _package;

    private Boolean packageInbound;

    private Boolean pak;

    private Boolean pakInbound;

    private Boolean allowNonCarrier;

    private Boolean allowCarrier;

    private Long carrierDocumentRate;

    private Long carrierDocumentInboundRate;

    private Long carrierPackageRate;

    private Long carrierPackagePerWeightRate;

    private Long carrierPackageInboundRate;

    private Long carrierPackageInboundPerWeightRate;

    private Long carrierPakRate;

    private Long carrierPakInboundRate;

    private Long carrierPakPerWeightRate;

    private Long carrierPakInboundPerWeightRate;

    private Date modifiedDate;

    private Long nonCarrierDocumentRate;

    private Long nonCarrierDocumentInboundRate;

    private Long nonCarrierPackageRate;

    private Long nonCarrierPackagePerWeightRate;

    private Long nonCarrierPackageInboundRate;

    private Long nonCarrierPackageInboundPerWeightRate;

    private Long nonCarrierPakRate;

    private Long nonCarrierPakInboundRate;

    private Long nonCarrierPakPerWeightRate;

    private Long nonCarrierPakInboundPerWeightRate;

    private Integer serviceId;

    private Integer noOfRate;

    private Long localizationId;

    private Byte perWeightStatus;

    private Byte showStatus;

    private Byte showDomestic;

    private String globalProductCodeDoc;

    private String globalProductCodeNonDoc;

    private String localProductCodeDoc;

    private String localProductCodeNonDoc;

    private Double docOutboundTax;

    private Double nonDocOutboundTax;

    private Double docInboundTax;

    private Double nonDocInboundTax;

    private Boolean allowChangeName;

    private Boolean startWithCarrierName;

    private String perKg;

    private String serviceGroup;

    private ServiceVo service;
    
    //private String carrierId; //code by shahabuddin

    private List<PackageTypeVo> packageTypes;

    private List<AccountServiceVo> accountServices;

    private List<CarrierZoneVo> carrierZones;

    public Boolean get_package() {
        return _package;
    }

    public void set_package(Boolean _package) {
        this._package = _package;
    }

    public List<CarrierZoneVo> getCarrierZones() {
        return carrierZones;
    }

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }

    public void setCarrierZones(List<CarrierZoneVo> carrierZones) {
        this.carrierZones = carrierZones;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getServicePriority() {
        return servicePriority;
    }

    public void setServicePriority(Integer servicePriority) {
        this.servicePriority = servicePriority;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getEdiDescription() {
        return ediDescription;
    }

    public void setEdiDescription(String ediDescription) {
        this.ediDescription = ediDescription;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Double getBasicCharge() {
        return basicCharge;
    }

    public void setBasicCharge(Double basicCharge) {
        this.basicCharge = basicCharge;
    }

    public Boolean getDocument() {
        return document;
    }

    public void setDocument(Boolean document) {
        this.document = document;
    }

    public Boolean getDocumentInbound() {
        return documentInbound;
    }

    public void setDocumentInbound(Boolean documentInbound) {
        this.documentInbound = documentInbound;
    }

    public Boolean getPackageInbound() {
        return packageInbound;
    }

    public void setPackageInbound(Boolean packageInbound) {
        this.packageInbound = packageInbound;
    }

    public Boolean getPak() {
        return pak;
    }

    public void setPak(Boolean pak) {
        this.pak = pak;
    }

    public Boolean getPakInbound() {
        return pakInbound;
    }

    public void setPakInbound(Boolean pakInbound) {
        this.pakInbound = pakInbound;
    }

    public Boolean getAllowNonCarrier() {
        return allowNonCarrier;
    }

    public void setAllowNonCarrier(Boolean allowNonCarrier) {
        this.allowNonCarrier = allowNonCarrier;
    }

    public Boolean getAllowCarrier() {
        return allowCarrier;
    }

    public void setAllowCarrier(Boolean allowCarrier) {
        this.allowCarrier = allowCarrier;
    }

    public Long getCarrierDocumentRate() {
        return carrierDocumentRate;
    }

    public void setCarrierDocumentRate(Long carrierDocumentRate) {
        this.carrierDocumentRate = carrierDocumentRate;
    }

    public Long getCarrierDocumentInboundRate() {
        return carrierDocumentInboundRate;
    }

    public void setCarrierDocumentInboundRate(Long carrierDocumentInboundRate) {
        this.carrierDocumentInboundRate = carrierDocumentInboundRate;
    }

    public Long getCarrierPackageRate() {
        return carrierPackageRate;
    }

    public void setCarrierPackageRate(Long carrierPackageRate) {
        this.carrierPackageRate = carrierPackageRate;
    }

    public Long getCarrierPackagePerWeightRate() {
        return carrierPackagePerWeightRate;
    }

    public void setCarrierPackagePerWeightRate(Long carrierPackagePerWeightRate) {
        this.carrierPackagePerWeightRate = carrierPackagePerWeightRate;
    }

    public Long getCarrierPackageInboundRate() {
        return carrierPackageInboundRate;
    }

    public void setCarrierPackageInboundRate(Long carrierPackageInboundRate) {
        this.carrierPackageInboundRate = carrierPackageInboundRate;
    }

    public Long getCarrierPackageInboundPerWeightRate() {
        return carrierPackageInboundPerWeightRate;
    }

    public void setCarrierPackageInboundPerWeightRate(Long carrierPackageInboundPerWeightRate) {
        this.carrierPackageInboundPerWeightRate = carrierPackageInboundPerWeightRate;
    }

    public Long getCarrierPakRate() {
        return carrierPakRate;
    }

    public void setCarrierPakRate(Long carrierPakRate) {
        this.carrierPakRate = carrierPakRate;
    }

    public Long getCarrierPakInboundRate() {
        return carrierPakInboundRate;
    }

    public void setCarrierPakInboundRate(Long carrierPakInboundRate) {
        this.carrierPakInboundRate = carrierPakInboundRate;
    }

    public Long getCarrierPakPerWeightRate() {
        return carrierPakPerWeightRate;
    }

    public void setCarrierPakPerWeightRate(Long carrierPakPerWeightRate) {
        this.carrierPakPerWeightRate = carrierPakPerWeightRate;
    }

    public Long getCarrierPakInboundPerWeightRate() {
        return carrierPakInboundPerWeightRate;
    }

    public void setCarrierPakInboundPerWeightRate(Long carrierPakInboundPerWeightRate) {
        this.carrierPakInboundPerWeightRate = carrierPakInboundPerWeightRate;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getNonCarrierDocumentRate() {
        return nonCarrierDocumentRate;
    }

    public void setNonCarrierDocumentRate(Long nonCarrierDocumentRate) {
        this.nonCarrierDocumentRate = nonCarrierDocumentRate;
    }

    public Long getNonCarrierDocumentInboundRate() {
        return nonCarrierDocumentInboundRate;
    }

    public void setNonCarrierDocumentInboundRate(Long nonCarrierDocumentInboundRate) {
        this.nonCarrierDocumentInboundRate = nonCarrierDocumentInboundRate;
    }

    public Long getNonCarrierPackageRate() {
        return nonCarrierPackageRate;
    }

    public void setNonCarrierPackageRate(Long nonCarrierPackageRate) {
        this.nonCarrierPackageRate = nonCarrierPackageRate;
    }

    public Long getNonCarrierPackagePerWeightRate() {
        return nonCarrierPackagePerWeightRate;
    }

    public void setNonCarrierPackagePerWeightRate(Long nonCarrierPackagePerWeightRate) {
        this.nonCarrierPackagePerWeightRate = nonCarrierPackagePerWeightRate;
    }

    public Long getNonCarrierPackageInboundRate() {
        return nonCarrierPackageInboundRate;
    }

    public void setNonCarrierPackageInboundRate(Long nonCarrierPackageInboundRate) {
        this.nonCarrierPackageInboundRate = nonCarrierPackageInboundRate;
    }

    public Long getNonCarrierPackageInboundPerWeightRate() {
        return nonCarrierPackageInboundPerWeightRate;
    }

    public void setNonCarrierPackageInboundPerWeightRate(Long nonCarrierPackageInboundPerWeightRate) {
        this.nonCarrierPackageInboundPerWeightRate = nonCarrierPackageInboundPerWeightRate;
    }

    public Long getNonCarrierPakRate() {
        return nonCarrierPakRate;
    }

    public void setNonCarrierPakRate(Long nonCarrierPakRate) {
        this.nonCarrierPakRate = nonCarrierPakRate;
    }

    public Long getNonCarrierPakInboundRate() {
        return nonCarrierPakInboundRate;
    }

    public void setNonCarrierPakInboundRate(Long nonCarrierPakInboundRate) {
        this.nonCarrierPakInboundRate = nonCarrierPakInboundRate;
    }

    public Long getNonCarrierPakPerWeightRate() {
        return nonCarrierPakPerWeightRate;
    }

    public void setNonCarrierPakPerWeightRate(Long nonCarrierPakPerWeightRate) {
        this.nonCarrierPakPerWeightRate = nonCarrierPakPerWeightRate;
    }

    public Long getNonCarrierPakInboundPerWeightRate() {
        return nonCarrierPakInboundPerWeightRate;
    }

    public void setNonCarrierPakInboundPerWeightRate(Long nonCarrierPakInboundPerWeightRate) {
        this.nonCarrierPakInboundPerWeightRate = nonCarrierPakInboundPerWeightRate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getNoOfRate() {
        return noOfRate;
    }

    public void setNoOfRate(Integer noOfRate) {
        this.noOfRate = noOfRate;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    public Byte getPerWeightStatus() {
        return perWeightStatus;
    }

    public void setPerWeightStatus(Byte perWeightStatus) {
        this.perWeightStatus = perWeightStatus;
    }

    public Byte getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Byte showStatus) {
        this.showStatus = showStatus;
    }

    public Byte getShowDomestic() {
        return showDomestic;
    }

    public void setShowDomestic(Byte showDomestic) {
        this.showDomestic = showDomestic;
    }

    public String getGlobalProductCodeDoc() {
        return globalProductCodeDoc;
    }

    public void setGlobalProductCodeDoc(String globalProductCodeDoc) {
        this.globalProductCodeDoc = globalProductCodeDoc;
    }

    public String getGlobalProductCodeNonDoc() {
        return globalProductCodeNonDoc;
    }

    public void setGlobalProductCodeNonDoc(String globalProductCodeNonDoc) {
        this.globalProductCodeNonDoc = globalProductCodeNonDoc;
    }

    public String getLocalProductCodeDoc() {
        return localProductCodeDoc;
    }

    public void setLocalProductCodeDoc(String localProductCodeDoc) {
        this.localProductCodeDoc = localProductCodeDoc;
    }

    public String getLocalProductCodeNonDoc() {
        return localProductCodeNonDoc;
    }

    public void setLocalProductCodeNonDoc(String localProductCodeNonDoc) {
        this.localProductCodeNonDoc = localProductCodeNonDoc;
    }

    public Double getDocOutboundTax() {
        return docOutboundTax;
    }

    public void setDocOutboundTax(Double docOutboundTax) {
        this.docOutboundTax = docOutboundTax;
    }

    public Double getNonDocOutboundTax() {
        return nonDocOutboundTax;
    }

    public void setNonDocOutboundTax(Double nonDocOutboundTax) {
        this.nonDocOutboundTax = nonDocOutboundTax;
    }

    public Double getDocInboundTax() {
        return docInboundTax;
    }

    public void setDocInboundTax(Double docInboundTax) {
        this.docInboundTax = docInboundTax;
    }

    public Double getNonDocInboundTax() {
        return nonDocInboundTax;
    }

    public void setNonDocInboundTax(Double nonDocInboundTax) {
        this.nonDocInboundTax = nonDocInboundTax;
    }

    public Boolean getAllowChangeName() {
        return allowChangeName;
    }

    public void setAllowChangeName(Boolean allowChangeName) {
        this.allowChangeName = allowChangeName;
    }

    public Boolean getStartWithCarrierName() {
        return startWithCarrierName;
    }

    public void setStartWithCarrierName(Boolean startWithCarrierName) {
        this.startWithCarrierName = startWithCarrierName;
    }

    public String getPerKg() {
        return perKg;
    }

    public void setPerKg(String perKg) {
        this.perKg = perKg;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public Boolean getPackage() {
        return _package;
    }

    public void setPackage(Boolean _package) {
        this._package = _package;
    }

    public List<PackageTypeVo> getPackageTypes() {
        return packageTypes;
    }

    public void setPackageTypes(List<PackageTypeVo> packageTypes) {
        this.packageTypes = packageTypes;
    }

    public List<AccountServiceVo> getAccountServices() {
        return accountServices;
    }

    public void setAccountServices(List<AccountServiceVo> accountServices) {
        this.accountServices = accountServices;
    }
    
    
    /*//code by shahabuddin
    
    public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
    
    //end by shahabuddin
*/
    @Override
    public String toString() {
        return "ShipmentTypeVo [shipmentTypeId=" + shipmentTypeId + ", servicePriority=" + servicePriority + ", shipmentTypeName=" + shipmentTypeName + ", ediDescription=" + ediDescription + ", serviceCode=" + serviceCode + ", basicCharge=" + basicCharge + ", document=" + document + ", documentInbound=" + documentInbound + ", _package=" + _package + ", packageInbound=" + packageInbound + ", pak=" + pak + ", pakInbound=" + pakInbound + ", allowNonCarrier=" + allowNonCarrier + ", allowCarrier="
                + allowCarrier + ", carrierDocumentRate=" + carrierDocumentRate + ", carrierDocumentInboundRate=" + carrierDocumentInboundRate + ", carrierPackageRate=" + carrierPackageRate + ", carrierPackagePerWeightRate=" + carrierPackagePerWeightRate + ", carrierPackageInboundRate=" + carrierPackageInboundRate + ", carrierPackageInboundPerWeightRate=" + carrierPackageInboundPerWeightRate + ", carrierPakRate=" + carrierPakRate + ", carrierPakInboundRate=" + carrierPakInboundRate
                + ", carrierPakPerWeightRate=" + carrierPakPerWeightRate + ", carrierPakInboundPerWeightRate=" + carrierPakInboundPerWeightRate + ", modifiedDate=" + modifiedDate + ", nonCarrierDocumentRate=" + nonCarrierDocumentRate + ", nonCarrierDocumentInboundRate=" + nonCarrierDocumentInboundRate + ", nonCarrierPackageRate=" + nonCarrierPackageRate + ", nonCarrierPackagePerWeightRate=" + nonCarrierPackagePerWeightRate + ", nonCarrierPackageInboundRate=" + nonCarrierPackageInboundRate
                + ", nonCarrierPackageInboundPerWeightRate=" + nonCarrierPackageInboundPerWeightRate + ", nonCarrierPakRate=" + nonCarrierPakRate + ", nonCarrierPakInboundRate=" + nonCarrierPakInboundRate + ", nonCarrierPakPerWeightRate=" + nonCarrierPakPerWeightRate + ", nonCarrierPakInboundPerWeightRate=" + nonCarrierPakInboundPerWeightRate + ", serviceId=" + serviceId + ", noOfRate=" + noOfRate + ", localizationId=" + localizationId + ", perWeightStatus=" + perWeightStatus + ", showStatus="
                + showStatus + ", showDomestic=" + showDomestic + ", globalProductCodeDoc=" + globalProductCodeDoc + ", globalProductCodeNonDoc=" + globalProductCodeNonDoc + ", localProductCodeDoc=" + localProductCodeDoc + ", localProductCodeNonDoc=" + localProductCodeNonDoc + ", docOutboundTax=" + docOutboundTax + ", nonDocOutboundTax=" + nonDocOutboundTax + ", docInboundTax=" + docInboundTax + ", nonDocInboundTax=" + nonDocInboundTax + ", allowChangeName=" + allowChangeName
                + ", startWithCarrierName=" + startWithCarrierName + ", perKg=" + perKg + ", serviceGroup=" + serviceGroup + ", service=" + service + ", packageTypes=" + packageTypes + ", accountServices=" + accountServices + ", carrierZones=" + carrierZones + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_package == null) ? 0 : _package.hashCode());
        result = prime * result + ((accountServices == null) ? 0 : accountServices.hashCode());
        result = prime * result + ((allowCarrier == null) ? 0 : allowCarrier.hashCode());
        result = prime * result + ((allowChangeName == null) ? 0 : allowChangeName.hashCode());
        result = prime * result + ((allowNonCarrier == null) ? 0 : allowNonCarrier.hashCode());
        result = prime * result + ((basicCharge == null) ? 0 : basicCharge.hashCode());
        result = prime * result + ((carrierDocumentInboundRate == null) ? 0 : carrierDocumentInboundRate.hashCode());
        result = prime * result + ((carrierDocumentRate == null) ? 0 : carrierDocumentRate.hashCode());
        result = prime * result + ((carrierPackageInboundPerWeightRate == null) ? 0 : carrierPackageInboundPerWeightRate.hashCode());
        result = prime * result + ((carrierPackageInboundRate == null) ? 0 : carrierPackageInboundRate.hashCode());
        result = prime * result + ((carrierPackagePerWeightRate == null) ? 0 : carrierPackagePerWeightRate.hashCode());
        result = prime * result + ((carrierPackageRate == null) ? 0 : carrierPackageRate.hashCode());
        result = prime * result + ((carrierPakInboundPerWeightRate == null) ? 0 : carrierPakInboundPerWeightRate.hashCode());
        result = prime * result + ((carrierPakInboundRate == null) ? 0 : carrierPakInboundRate.hashCode());
        result = prime * result + ((carrierPakPerWeightRate == null) ? 0 : carrierPakPerWeightRate.hashCode());
        result = prime * result + ((carrierPakRate == null) ? 0 : carrierPakRate.hashCode());
        result = prime * result + ((carrierZones == null) ? 0 : carrierZones.hashCode());
        result = prime * result + ((docInboundTax == null) ? 0 : docInboundTax.hashCode());
        result = prime * result + ((docOutboundTax == null) ? 0 : docOutboundTax.hashCode());
        result = prime * result + ((document == null) ? 0 : document.hashCode());
        result = prime * result + ((documentInbound == null) ? 0 : documentInbound.hashCode());
        result = prime * result + ((ediDescription == null) ? 0 : ediDescription.hashCode());
        result = prime * result + ((globalProductCodeDoc == null) ? 0 : globalProductCodeDoc.hashCode());
        result = prime * result + ((globalProductCodeNonDoc == null) ? 0 : globalProductCodeNonDoc.hashCode());
        result = prime * result + ((localProductCodeDoc == null) ? 0 : localProductCodeDoc.hashCode());
        result = prime * result + ((localProductCodeNonDoc == null) ? 0 : localProductCodeNonDoc.hashCode());
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((noOfRate == null) ? 0 : noOfRate.hashCode());
        result = prime * result + ((nonCarrierDocumentInboundRate == null) ? 0 : nonCarrierDocumentInboundRate.hashCode());
        result = prime * result + ((nonCarrierDocumentRate == null) ? 0 : nonCarrierDocumentRate.hashCode());
        result = prime * result + ((nonCarrierPackageInboundPerWeightRate == null) ? 0 : nonCarrierPackageInboundPerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPackageInboundRate == null) ? 0 : nonCarrierPackageInboundRate.hashCode());
        result = prime * result + ((nonCarrierPackagePerWeightRate == null) ? 0 : nonCarrierPackagePerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPackageRate == null) ? 0 : nonCarrierPackageRate.hashCode());
        result = prime * result + ((nonCarrierPakInboundPerWeightRate == null) ? 0 : nonCarrierPakInboundPerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPakInboundRate == null) ? 0 : nonCarrierPakInboundRate.hashCode());
        result = prime * result + ((nonCarrierPakPerWeightRate == null) ? 0 : nonCarrierPakPerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPakRate == null) ? 0 : nonCarrierPakRate.hashCode());
        result = prime * result + ((nonDocInboundTax == null) ? 0 : nonDocInboundTax.hashCode());
        result = prime * result + ((nonDocOutboundTax == null) ? 0 : nonDocOutboundTax.hashCode());
        result = prime * result + ((packageInbound == null) ? 0 : packageInbound.hashCode());
        result = prime * result + ((packageTypes == null) ? 0 : packageTypes.hashCode());
        result = prime * result + ((pak == null) ? 0 : pak.hashCode());
        result = prime * result + ((pakInbound == null) ? 0 : pakInbound.hashCode());
        result = prime * result + ((perKg == null) ? 0 : perKg.hashCode());
        result = prime * result + ((perWeightStatus == null) ? 0 : perWeightStatus.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        result = prime * result + ((serviceCode == null) ? 0 : serviceCode.hashCode());
        result = prime * result + ((serviceGroup == null) ? 0 : serviceGroup.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
        result = prime * result + ((servicePriority == null) ? 0 : servicePriority.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((shipmentTypeName == null) ? 0 : shipmentTypeName.hashCode());
        result = prime * result + ((showDomestic == null) ? 0 : showDomestic.hashCode());
        result = prime * result + ((showStatus == null) ? 0 : showStatus.hashCode());
        result = prime * result + ((startWithCarrierName == null) ? 0 : startWithCarrierName.hashCode());
        //result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode()); //code by shahabuddin
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
        ShipmentTypeVo other = (ShipmentTypeVo) obj;
        if (_package == null) {
            if (other._package != null)
                return false;
        } else if (!_package.equals(other._package))
            return false;
        if (accountServices == null) {
            if (other.accountServices != null)
                return false;
        } else if (!accountServices.equals(other.accountServices))
            return false;
        if (allowCarrier == null) {
            if (other.allowCarrier != null)
                return false;
        } else if (!allowCarrier.equals(other.allowCarrier))
            return false;
        if (allowChangeName == null) {
            if (other.allowChangeName != null)
                return false;
        } else if (!allowChangeName.equals(other.allowChangeName))
            return false;
        if (allowNonCarrier == null) {
            if (other.allowNonCarrier != null)
                return false;
        } else if (!allowNonCarrier.equals(other.allowNonCarrier))
            return false;
        if (basicCharge == null) {
            if (other.basicCharge != null)
                return false;
        } else if (!basicCharge.equals(other.basicCharge))
            return false;
        if (carrierDocumentInboundRate == null) {
            if (other.carrierDocumentInboundRate != null)
                return false;
        } else if (!carrierDocumentInboundRate.equals(other.carrierDocumentInboundRate))
            return false;
        if (carrierDocumentRate == null) {
            if (other.carrierDocumentRate != null)
                return false;
        } else if (!carrierDocumentRate.equals(other.carrierDocumentRate))
            return false;
        if (carrierPackageInboundPerWeightRate == null) {
            if (other.carrierPackageInboundPerWeightRate != null)
                return false;
        } else if (!carrierPackageInboundPerWeightRate.equals(other.carrierPackageInboundPerWeightRate))
            return false;
        if (carrierPackageInboundRate == null) {
            if (other.carrierPackageInboundRate != null)
                return false;
        } else if (!carrierPackageInboundRate.equals(other.carrierPackageInboundRate))
            return false;
        if (carrierPackagePerWeightRate == null) {
            if (other.carrierPackagePerWeightRate != null)
                return false;
        } else if (!carrierPackagePerWeightRate.equals(other.carrierPackagePerWeightRate))
            return false;
        if (carrierPackageRate == null) {
            if (other.carrierPackageRate != null)
                return false;
        } else if (!carrierPackageRate.equals(other.carrierPackageRate))
            return false;
        if (carrierPakInboundPerWeightRate == null) {
            if (other.carrierPakInboundPerWeightRate != null)
                return false;
        } else if (!carrierPakInboundPerWeightRate.equals(other.carrierPakInboundPerWeightRate))
            return false;
        if (carrierPakInboundRate == null) {
            if (other.carrierPakInboundRate != null)
                return false;
        } else if (!carrierPakInboundRate.equals(other.carrierPakInboundRate))
            return false;
        if (carrierPakPerWeightRate == null) {
            if (other.carrierPakPerWeightRate != null)
                return false;
        } else if (!carrierPakPerWeightRate.equals(other.carrierPakPerWeightRate))
            return false;
        if (carrierPakRate == null) {
            if (other.carrierPakRate != null)
                return false;
        } else if (!carrierPakRate.equals(other.carrierPakRate))
            return false;
        if (carrierZones == null) {
            if (other.carrierZones != null)
                return false;
        } else if (!carrierZones.equals(other.carrierZones))
            return false;
        if (docInboundTax == null) {
            if (other.docInboundTax != null)
                return false;
        } else if (!docInboundTax.equals(other.docInboundTax))
            return false;
        if (docOutboundTax == null) {
            if (other.docOutboundTax != null)
                return false;
        } else if (!docOutboundTax.equals(other.docOutboundTax))
            return false;
        if (document == null) {
            if (other.document != null)
                return false;
        } else if (!document.equals(other.document))
            return false;
        if (documentInbound == null) {
            if (other.documentInbound != null)
                return false;
        } else if (!documentInbound.equals(other.documentInbound))
            return false;
        if (ediDescription == null) {
            if (other.ediDescription != null)
                return false;
        } else if (!ediDescription.equals(other.ediDescription))
            return false;
        if (globalProductCodeDoc == null) {
            if (other.globalProductCodeDoc != null)
                return false;
        } else if (!globalProductCodeDoc.equals(other.globalProductCodeDoc))
            return false;
        if (globalProductCodeNonDoc == null) {
            if (other.globalProductCodeNonDoc != null)
                return false;
        } else if (!globalProductCodeNonDoc.equals(other.globalProductCodeNonDoc))
            return false;
        if (localProductCodeDoc == null) {
            if (other.localProductCodeDoc != null)
                return false;
        } else if (!localProductCodeDoc.equals(other.localProductCodeDoc))
            return false;
        if (localProductCodeNonDoc == null) {
            if (other.localProductCodeNonDoc != null)
                return false;
        } else if (!localProductCodeNonDoc.equals(other.localProductCodeNonDoc))
            return false;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        if (modifiedDate == null) {
            if (other.modifiedDate != null)
                return false;
        } else if (!modifiedDate.equals(other.modifiedDate))
            return false;
        if (noOfRate == null) {
            if (other.noOfRate != null)
                return false;
        } else if (!noOfRate.equals(other.noOfRate))
            return false;
        if (nonCarrierDocumentInboundRate == null) {
            if (other.nonCarrierDocumentInboundRate != null)
                return false;
        } else if (!nonCarrierDocumentInboundRate.equals(other.nonCarrierDocumentInboundRate))
            return false;
        if (nonCarrierDocumentRate == null) {
            if (other.nonCarrierDocumentRate != null)
                return false;
        } else if (!nonCarrierDocumentRate.equals(other.nonCarrierDocumentRate))
            return false;
        if (nonCarrierPackageInboundPerWeightRate == null) {
            if (other.nonCarrierPackageInboundPerWeightRate != null)
                return false;
        } else if (!nonCarrierPackageInboundPerWeightRate.equals(other.nonCarrierPackageInboundPerWeightRate))
            return false;
        if (nonCarrierPackageInboundRate == null) {
            if (other.nonCarrierPackageInboundRate != null)
                return false;
        } else if (!nonCarrierPackageInboundRate.equals(other.nonCarrierPackageInboundRate))
            return false;
        if (nonCarrierPackagePerWeightRate == null) {
            if (other.nonCarrierPackagePerWeightRate != null)
                return false;
        } else if (!nonCarrierPackagePerWeightRate.equals(other.nonCarrierPackagePerWeightRate))
            return false;
        if (nonCarrierPackageRate == null) {
            if (other.nonCarrierPackageRate != null)
                return false;
        } else if (!nonCarrierPackageRate.equals(other.nonCarrierPackageRate))
            return false;
        if (nonCarrierPakInboundPerWeightRate == null) {
            if (other.nonCarrierPakInboundPerWeightRate != null)
                return false;
        } else if (!nonCarrierPakInboundPerWeightRate.equals(other.nonCarrierPakInboundPerWeightRate))
            return false;
        if (nonCarrierPakInboundRate == null) {
            if (other.nonCarrierPakInboundRate != null)
                return false;
        } else if (!nonCarrierPakInboundRate.equals(other.nonCarrierPakInboundRate))
            return false;
        if (nonCarrierPakPerWeightRate == null) {
            if (other.nonCarrierPakPerWeightRate != null)
                return false;
        } else if (!nonCarrierPakPerWeightRate.equals(other.nonCarrierPakPerWeightRate))
            return false;
        if (nonCarrierPakRate == null) {
            if (other.nonCarrierPakRate != null)
                return false;
        } else if (!nonCarrierPakRate.equals(other.nonCarrierPakRate))
            return false;
        if (nonDocInboundTax == null) {
            if (other.nonDocInboundTax != null)
                return false;
        } else if (!nonDocInboundTax.equals(other.nonDocInboundTax))
            return false;
        if (nonDocOutboundTax == null) {
            if (other.nonDocOutboundTax != null)
                return false;
        } else if (!nonDocOutboundTax.equals(other.nonDocOutboundTax))
            return false;
        if (packageInbound == null) {
            if (other.packageInbound != null)
                return false;
        } else if (!packageInbound.equals(other.packageInbound))
            return false;
        if (packageTypes == null) {
            if (other.packageTypes != null)
                return false;
        } else if (!packageTypes.equals(other.packageTypes))
            return false;
        if (pak == null) {
            if (other.pak != null)
                return false;
        } else if (!pak.equals(other.pak))
            return false;
        if (pakInbound == null) {
            if (other.pakInbound != null)
                return false;
        } else if (!pakInbound.equals(other.pakInbound))
            return false;
        if (perKg == null) {
            if (other.perKg != null)
                return false;
        } else if (!perKg.equals(other.perKg))
            return false;
        if (perWeightStatus == null) {
            if (other.perWeightStatus != null)
                return false;
        } else if (!perWeightStatus.equals(other.perWeightStatus))
            return false;
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        if (serviceCode == null) {
            if (other.serviceCode != null)
                return false;
        } else if (!serviceCode.equals(other.serviceCode))
            return false;
        if (serviceGroup == null) {
            if (other.serviceGroup != null)
                return false;
        } else if (!serviceGroup.equals(other.serviceGroup))
            return false;
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
            return false;
        if (servicePriority == null) {
            if (other.servicePriority != null)
                return false;
        } else if (!servicePriority.equals(other.servicePriority))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (shipmentTypeName == null) {
            if (other.shipmentTypeName != null)
                return false;
        } else if (!shipmentTypeName.equals(other.shipmentTypeName))
            return false;
        if (showDomestic == null) {
            if (other.showDomestic != null)
                return false;
        } else if (!showDomestic.equals(other.showDomestic))
            return false;
        if (showStatus == null) {
            if (other.showStatus != null)
                return false;
        } else if (!showStatus.equals(other.showStatus))
            return false;
        if (startWithCarrierName == null) {
            if (other.startWithCarrierName != null)
                return false;
        } else if (!startWithCarrierName.equals(other.startWithCarrierName))
            return false;
/*//code by shahabuddin
        
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        
        //end by shahabuddin
*/        
        return true;
    }

}