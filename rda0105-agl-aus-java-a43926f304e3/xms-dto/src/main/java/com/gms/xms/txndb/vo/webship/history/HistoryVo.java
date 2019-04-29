package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentDetailVo;

import java.util.List;

/**
 * Posted from HistoryListShipmentVo
 * <p>
 * Author TanDT Date Jul 8, 2015
 */
public class HistoryVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String shipmentId;
    private String serviceName;
    private String voidStatus;
    private String airbillNumber;
    private String createDate;
    private String timeStamp;
    private String shipDate;
    private Integer noOfPieces;
    private String shipmentType;
    private String packageName;
    private String weight;
    private Double deadWeight;
    private String dimensions;
    private String total;
    private String withInsurance;
    private String schedule;
    private String schcollTimeStamp;
    private String confirmationNo;
    private String reference;
    private String billingParty;
    private String senderCompany;
    private String senderName;
    private String senderLocation;
    private String reciverCompany;
    private String reciverContact;
    private String destinations;
    private String destCountry;
    private String packingList;
    private Integer commercialInvoiceId;
    private Integer serviceId;
    private Integer schId;
    private List<ShipmentDetailVo> shipmentDetails;

    public List<ShipmentDetailVo> getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(List<ShipmentDetailVo> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCommercialInvoiceId() {
        return commercialInvoiceId;
    }

    public void setCommercialInvoiceId(Integer commercialInvoiceId) {
        this.commercialInvoiceId = commercialInvoiceId;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVoidStatus() {
        return voidStatus;
    }

    public void setVoidStatus(String voidStatus) {
        this.voidStatus = voidStatus;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(String withInsurance) {
        this.withInsurance = withInsurance;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchcollTimeStamp() {
        return schcollTimeStamp;
    }

    public void setSchcollTimeStamp(String schcollTimeStamp) {
        this.schcollTimeStamp = schcollTimeStamp;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBillingParty() {
        return billingParty;
    }

    public void setBillingParty(String billingParty) {
        this.billingParty = billingParty;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderLocation() {
        return senderLocation;
    }

    public void setSenderLocation(String senderLocation) {
        this.senderLocation = senderLocation;
    }

    public String getReciverCompany() {
        return reciverCompany;
    }

    public void setReciverCompany(String reciverCompany) {
        this.reciverCompany = reciverCompany;
    }

    public String getReciverContact() {
        return reciverContact;
    }

    public void setReciverContact(String reciverContact) {
        this.reciverContact = reciverContact;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public Double getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(Double deadWeight) {
        this.deadWeight = deadWeight;
    }

    @Override
    public String toString() {
        return "HistoryVo [shipmentId=" + shipmentId + ", serviceName=" + serviceName + ", voidStatus=" + voidStatus + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", timeStamp=" + timeStamp + ", shipDate=" + shipDate + ", noOfPieces=" + noOfPieces + ", shipmentType=" + shipmentType + ", packageName=" + packageName + ", weight=" + weight + ", dimensions=" + dimensions + ", total=" + total + ", withInsurance=" + withInsurance + ", schedule=" + schedule
                + ", schcollTimeStamp=" + schcollTimeStamp + ", confirmationNo=" + confirmationNo + ", reference=" + reference + ", billingParty=" + billingParty + ", senderCompany=" + senderCompany + ", senderName=" + senderName + ", senderLocation=" + senderLocation + ", reciverCompany=" + reciverCompany + ", reciverContact=" + reciverContact + ", destinations=" + destinations + ", destCountry=" + destCountry + ", packingList=" + packingList + ", commercialInvoiceId=" + commercialInvoiceId
                + ", serviceId=" + serviceId + ", schId=" + schId + ", shipmentDetails=" + shipmentDetails + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((billingParty == null) ? 0 : billingParty.hashCode());
        result = prime * result + ((commercialInvoiceId == null) ? 0 : commercialInvoiceId.hashCode());
        result = prime * result + ((confirmationNo == null) ? 0 : confirmationNo.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((destCountry == null) ? 0 : destCountry.hashCode());
        result = prime * result + ((destinations == null) ? 0 : destinations.hashCode());
        result = prime * result + ((dimensions == null) ? 0 : dimensions.hashCode());
        result = prime * result + ((noOfPieces == null) ? 0 : noOfPieces.hashCode());
        result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
        result = prime * result + ((packingList == null) ? 0 : packingList.hashCode());
        result = prime * result + ((reciverCompany == null) ? 0 : reciverCompany.hashCode());
        result = prime * result + ((reciverContact == null) ? 0 : reciverContact.hashCode());
        result = prime * result + ((reference == null) ? 0 : reference.hashCode());
        result = prime * result + ((schId == null) ? 0 : schId.hashCode());
        result = prime * result + ((schcollTimeStamp == null) ? 0 : schcollTimeStamp.hashCode());
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
        result = prime * result + ((senderCompany == null) ? 0 : senderCompany.hashCode());
        result = prime * result + ((senderLocation == null) ? 0 : senderLocation.hashCode());
        result = prime * result + ((senderName == null) ? 0 : senderName.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result + ((shipDate == null) ? 0 : shipDate.hashCode());
        result = prime * result + ((shipmentDetails == null) ? 0 : shipmentDetails.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((shipmentType == null) ? 0 : shipmentType.hashCode());
        result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        result = prime * result + ((voidStatus == null) ? 0 : voidStatus.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((withInsurance == null) ? 0 : withInsurance.hashCode());
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
        HistoryVo other = (HistoryVo) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (billingParty == null) {
            if (other.billingParty != null)
                return false;
        } else if (!billingParty.equals(other.billingParty))
            return false;
        if (commercialInvoiceId == null) {
            if (other.commercialInvoiceId != null)
                return false;
        } else if (!commercialInvoiceId.equals(other.commercialInvoiceId))
            return false;
        if (confirmationNo == null) {
            if (other.confirmationNo != null)
                return false;
        } else if (!confirmationNo.equals(other.confirmationNo))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (destCountry == null) {
            if (other.destCountry != null)
                return false;
        } else if (!destCountry.equals(other.destCountry))
            return false;
        if (destinations == null) {
            if (other.destinations != null)
                return false;
        } else if (!destinations.equals(other.destinations))
            return false;
        if (dimensions == null) {
            if (other.dimensions != null)
                return false;
        } else if (!dimensions.equals(other.dimensions))
            return false;
        if (noOfPieces == null) {
            if (other.noOfPieces != null)
                return false;
        } else if (!noOfPieces.equals(other.noOfPieces))
            return false;
        if (packageName == null) {
            if (other.packageName != null)
                return false;
        } else if (!packageName.equals(other.packageName))
            return false;
        if (packingList == null) {
            if (other.packingList != null)
                return false;
        } else if (!packingList.equals(other.packingList))
            return false;
        if (reciverCompany == null) {
            if (other.reciverCompany != null)
                return false;
        } else if (!reciverCompany.equals(other.reciverCompany))
            return false;
        if (reciverContact == null) {
            if (other.reciverContact != null)
                return false;
        } else if (!reciverContact.equals(other.reciverContact))
            return false;
        if (reference == null) {
            if (other.reference != null)
                return false;
        } else if (!reference.equals(other.reference))
            return false;
        if (schId == null) {
            if (other.schId != null)
                return false;
        } else if (!schId.equals(other.schId))
            return false;
        if (schcollTimeStamp == null) {
            if (other.schcollTimeStamp != null)
                return false;
        } else if (!schcollTimeStamp.equals(other.schcollTimeStamp))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        if (senderCompany == null) {
            if (other.senderCompany != null)
                return false;
        } else if (!senderCompany.equals(other.senderCompany))
            return false;
        if (senderLocation == null) {
            if (other.senderLocation != null)
                return false;
        } else if (!senderLocation.equals(other.senderLocation))
            return false;
        if (senderName == null) {
            if (other.senderName != null)
                return false;
        } else if (!senderName.equals(other.senderName))
            return false;
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (shipDate == null) {
            if (other.shipDate != null)
                return false;
        } else if (!shipDate.equals(other.shipDate))
            return false;
        if (shipmentDetails == null) {
            if (other.shipmentDetails != null)
                return false;
        } else if (!shipmentDetails.equals(other.shipmentDetails))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (shipmentType == null) {
            if (other.shipmentType != null)
                return false;
        } else if (!shipmentType.equals(other.shipmentType))
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        if (voidStatus == null) {
            if (other.voidStatus != null)
                return false;
        } else if (!voidStatus.equals(other.voidStatus))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (withInsurance == null) {
            if (other.withInsurance != null)
                return false;
        } else if (!withInsurance.equals(other.withInsurance))
            return false;
        return true;
    }

}