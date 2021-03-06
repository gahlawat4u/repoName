//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.13 at 11:30:46 AM ICT 
//


package com.gms.delivery.startrack.xmlpi.aaecreatebooking;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for AaEBookingType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="AaEBookingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BusinessUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ConNoteRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CustReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ItemCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ItemDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ItemHeight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ItemLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ItemWidth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PickupLatest" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PickupTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PrepaidNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReceiverAddr1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ReceiverAddr2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReceiverCity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ReceiverContactName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ReceiverEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReceiverName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ReceiverPhone" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ReceiverPostCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderAddr1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderAddr2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderCity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderContactArea" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderContactName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderPhone" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SenderPostCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ServiceType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ShipInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalWeight" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AaEBookingType", propOrder = {
        "businessUnit",
        "conNoteRequired",
        "custId",
        "custReference",
        "itemCount",
        "itemDescription",
        "itemHeight",
        "itemLength",
        "itemWidth",
        "notes",
        "pickupDate",
        "pickupLatest",
        "pickupTime",
        "prepaidNumber",
        "receiverAddr1",
        "receiverAddr2",
        "receiverCity",
        "receiverContactName",
        "receiverEmailAddress",
        "receiverName",
        "receiverPhone",
        "receiverPostCode",
        "senderAddr1",
        "senderAddr2",
        "senderCity",
        "senderContactArea",
        "senderContactName",
        "senderEmailAddress",
        "senderName",
        "senderPhone",
        "senderPostCode",
        "serviceType",
        "shipInstructions",
        "totalWeight"
})
public class AaEBookingType {

    @XmlElementRef(name = "BusinessUnit", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> businessUnit;
    @XmlElementRef(name = "ConNoteRequired", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> conNoteRequired;
    @XmlElementRef(name = "CustId", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> custId;
    @XmlElementRef(name = "CustReference", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> custReference;
    @XmlElement(name = "ItemCount")
    protected int itemCount;
    @XmlElement(name = "ItemDescription", required = true, nillable = true)
    protected String itemDescription;
    @XmlElement(name = "ItemHeight")
    protected Integer itemHeight;
    @XmlElement(name = "ItemLength")
    protected Integer itemLength;
    @XmlElement(name = "ItemWidth")
    protected Integer itemWidth;
    @XmlElementRef(name = "Notes", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> notes;
    @XmlElement(name = "PickupDate", required = true, nillable = true)
    protected String pickupDate;
    @XmlElement(name = "PickupLatest", required = true, nillable = true)
    protected String pickupLatest;
    @XmlElement(name = "PickupTime", required = true, nillable = true)
    protected String pickupTime;
    @XmlElementRef(name = "PrepaidNumber", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> prepaidNumber;
    @XmlElement(name = "ReceiverAddr1", required = true, nillable = true)
    protected String receiverAddr1;
    @XmlElementRef(name = "ReceiverAddr2", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receiverAddr2;
    @XmlElement(name = "ReceiverCity", required = true, nillable = true)
    protected String receiverCity;
    @XmlElement(name = "ReceiverContactName", required = true, nillable = true)
    protected String receiverContactName;
    @XmlElementRef(name = "ReceiverEmailAddress", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receiverEmailAddress;
    @XmlElement(name = "ReceiverName", required = true, nillable = true)
    protected String receiverName;
    @XmlElement(name = "ReceiverPhone", required = true, nillable = true)
    protected String receiverPhone;
    @XmlElement(name = "ReceiverPostCode", required = true, nillable = true)
    protected String receiverPostCode;
    @XmlElement(name = "SenderAddr1", required = true, nillable = true)
    protected String senderAddr1;
    @XmlElementRef(name = "SenderAddr2", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderAddr2;
    @XmlElement(name = "SenderCity", required = true, nillable = true)
    protected String senderCity;
    @XmlElement(name = "SenderContactArea", required = true, nillable = true)
    protected String senderContactArea;
    @XmlElement(name = "SenderContactName", required = true, nillable = true)
    protected String senderContactName;
    @XmlElementRef(name = "SenderEmailAddress", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderEmailAddress;
    @XmlElement(name = "SenderName", required = true, nillable = true)
    protected String senderName;
    @XmlElement(name = "SenderPhone", required = true, nillable = true)
    protected String senderPhone;
    @XmlElement(name = "SenderPostCode", required = true, nillable = true)
    protected String senderPostCode;
    @XmlElement(name = "ServiceType", required = true, nillable = true)
    protected String serviceType;
    @XmlElementRef(name = "ShipInstructions", namespace = "http://schemas.datacontract.org/2004/07/AaECreateBooking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> shipInstructions;
    @XmlElement(name = "TotalWeight")
    protected int totalWeight;

    /**
     * Gets the value of the businessUnit property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getBusinessUnit() {
        return businessUnit;
    }

    /**
     * Sets the value of the businessUnit property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setBusinessUnit(JAXBElement<String> value) {
        this.businessUnit = value;
    }

    /**
     * Gets the value of the conNoteRequired property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getConNoteRequired() {
        return conNoteRequired;
    }

    /**
     * Sets the value of the conNoteRequired property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setConNoteRequired(JAXBElement<String> value) {
        this.conNoteRequired = value;
    }

    /**
     * Gets the value of the custId property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCustId() {
        return custId;
    }

    /**
     * Sets the value of the custId property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCustId(JAXBElement<String> value) {
        this.custId = value;
    }

    /**
     * Gets the value of the custReference property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCustReference() {
        return custReference;
    }

    /**
     * Sets the value of the custReference property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCustReference(JAXBElement<String> value) {
        this.custReference = value;
    }

    /**
     * Gets the value of the itemCount property.
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Sets the value of the itemCount property.
     */
    public void setItemCount(int value) {
        this.itemCount = value;
    }

    /**
     * Gets the value of the itemDescription property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the value of the itemDescription property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setItemDescription(String value) {
        this.itemDescription = value;
    }

    /**
     * Gets the value of the itemHeight property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getItemHeight() {
        return itemHeight;
    }

    /**
     * Sets the value of the itemHeight property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setItemHeight(Integer value) {
        this.itemHeight = value;
    }

    /**
     * Gets the value of the itemLength property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getItemLength() {
        return itemLength;
    }

    /**
     * Sets the value of the itemLength property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setItemLength(Integer value) {
        this.itemLength = value;
    }

    /**
     * Gets the value of the itemWidth property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getItemWidth() {
        return itemWidth;
    }

    /**
     * Sets the value of the itemWidth property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setItemWidth(Integer value) {
        this.itemWidth = value;
    }

    /**
     * Gets the value of the notes property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setNotes(JAXBElement<String> value) {
        this.notes = value;
    }

    /**
     * Gets the value of the pickupDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPickupDate(String value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the pickupLatest property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPickupLatest() {
        return pickupLatest;
    }

    /**
     * Sets the value of the pickupLatest property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPickupLatest(String value) {
        this.pickupLatest = value;
    }

    /**
     * Gets the value of the pickupTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPickupTime() {
        return pickupTime;
    }

    /**
     * Sets the value of the pickupTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPickupTime(String value) {
        this.pickupTime = value;
    }

    /**
     * Gets the value of the prepaidNumber property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getPrepaidNumber() {
        return prepaidNumber;
    }

    /**
     * Sets the value of the prepaidNumber property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setPrepaidNumber(JAXBElement<String> value) {
        this.prepaidNumber = value;
    }

    /**
     * Gets the value of the receiverAddr1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverAddr1() {
        return receiverAddr1;
    }

    /**
     * Sets the value of the receiverAddr1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverAddr1(String value) {
        this.receiverAddr1 = value;
    }

    /**
     * Gets the value of the receiverAddr2 property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getReceiverAddr2() {
        return receiverAddr2;
    }

    /**
     * Sets the value of the receiverAddr2 property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setReceiverAddr2(JAXBElement<String> value) {
        this.receiverAddr2 = value;
    }

    /**
     * Gets the value of the receiverCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * Sets the value of the receiverCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverCity(String value) {
        this.receiverCity = value;
    }

    /**
     * Gets the value of the receiverContactName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverContactName() {
        return receiverContactName;
    }

    /**
     * Sets the value of the receiverContactName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverContactName(String value) {
        this.receiverContactName = value;
    }

    /**
     * Gets the value of the receiverEmailAddress property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getReceiverEmailAddress() {
        return receiverEmailAddress;
    }

    /**
     * Sets the value of the receiverEmailAddress property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setReceiverEmailAddress(JAXBElement<String> value) {
        this.receiverEmailAddress = value;
    }

    /**
     * Gets the value of the receiverName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Sets the value of the receiverName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverName(String value) {
        this.receiverName = value;
    }

    /**
     * Gets the value of the receiverPhone property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * Sets the value of the receiverPhone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverPhone(String value) {
        this.receiverPhone = value;
    }

    /**
     * Gets the value of the receiverPostCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    /**
     * Sets the value of the receiverPostCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiverPostCode(String value) {
        this.receiverPostCode = value;
    }

    /**
     * Gets the value of the senderAddr1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderAddr1() {
        return senderAddr1;
    }

    /**
     * Sets the value of the senderAddr1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderAddr1(String value) {
        this.senderAddr1 = value;
    }

    /**
     * Gets the value of the senderAddr2 property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getSenderAddr2() {
        return senderAddr2;
    }

    /**
     * Sets the value of the senderAddr2 property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setSenderAddr2(JAXBElement<String> value) {
        this.senderAddr2 = value;
    }

    /**
     * Gets the value of the senderCity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderCity() {
        return senderCity;
    }

    /**
     * Sets the value of the senderCity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderCity(String value) {
        this.senderCity = value;
    }

    /**
     * Gets the value of the senderContactArea property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderContactArea() {
        return senderContactArea;
    }

    /**
     * Sets the value of the senderContactArea property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderContactArea(String value) {
        this.senderContactArea = value;
    }

    /**
     * Gets the value of the senderContactName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderContactName() {
        return senderContactName;
    }

    /**
     * Sets the value of the senderContactName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderContactName(String value) {
        this.senderContactName = value;
    }

    /**
     * Gets the value of the senderEmailAddress property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getSenderEmailAddress() {
        return senderEmailAddress;
    }

    /**
     * Sets the value of the senderEmailAddress property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setSenderEmailAddress(JAXBElement<String> value) {
        this.senderEmailAddress = value;
    }

    /**
     * Gets the value of the senderName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Sets the value of the senderName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderName(String value) {
        this.senderName = value;
    }

    /**
     * Gets the value of the senderPhone property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderPhone() {
        return senderPhone;
    }

    /**
     * Sets the value of the senderPhone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderPhone(String value) {
        this.senderPhone = value;
    }

    /**
     * Gets the value of the senderPostCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSenderPostCode() {
        return senderPostCode;
    }

    /**
     * Sets the value of the senderPostCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSenderPostCode(String value) {
        this.senderPostCode = value;
    }

    /**
     * Gets the value of the serviceType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the shipInstructions property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getShipInstructions() {
        return shipInstructions;
    }

    /**
     * Sets the value of the shipInstructions property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setShipInstructions(JAXBElement<String> value) {
        this.shipInstructions = value;
    }

    /**
     * Gets the value of the totalWeight property.
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the value of the totalWeight property.
     */
    public void setTotalWeight(int value) {
        this.totalWeight = value;
    }

}
