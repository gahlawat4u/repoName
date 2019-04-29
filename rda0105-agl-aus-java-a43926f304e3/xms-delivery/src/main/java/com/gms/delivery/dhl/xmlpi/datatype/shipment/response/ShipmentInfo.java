//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 10:39:35 AM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.shipment.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ShipmentInfo complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ShipmentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OriginServiceArea" type="{http://www.dhl.com/datatypes_global}ServiceArea"/>
 *         &lt;element name="DestinationServiceArea" type="{http://www.dhl.com/datatypes_global}ServiceArea"/>
 *         &lt;element name="ShipperName" type="{http://www.dhl.com/datatypes_global}PersonName"/>
 *         &lt;element name="ShipperAccountNumber" type="{http://www.dhl.com/datatypes_global}AccountNumber" minOccurs="0"/>
 *         &lt;element name="ConsigneeName" type="{http://www.dhl.com/datatypes_global}PersonName"/>
 *         &lt;element name="ShipmentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Pieces" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WeightUnit" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="L"/>
 *               &lt;enumeration value="K"/>
 *               &lt;enumeration value="G"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EstDlvyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="ShipmentEvent" type="{http://www.dhl.com/datatypes_global}ShipmentEvent" maxOccurs="unbounded"/>
 *           &lt;element name="ShipperReference" type="{http://www.dhl.com/datatypes_global}Reference"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentInfo", propOrder = {
        "originServiceArea",
        "destinationServiceArea",
        "shipperName",
        "shipperAccountNumber",
        "consigneeName",
        "shipmentDate",
        "pieces",
        "weight",
        "weightUnit",
        "estDlvyDate",
        "shipmentEvent",
        "shipperReference"
})
public class ShipmentInfo {

    @XmlElement(name = "OriginServiceArea", required = true)
    protected ServiceArea originServiceArea;
    @XmlElement(name = "DestinationServiceArea", required = true)
    protected ServiceArea destinationServiceArea;
    @XmlElement(name = "ShipperName", required = true)
    protected String shipperName;
    @XmlElement(name = "ShipperAccountNumber")
    protected String shipperAccountNumber;
    @XmlElement(name = "ConsigneeName", required = true)
    protected String consigneeName;
    @XmlElement(name = "ShipmentDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar shipmentDate;
    @XmlElement(name = "Pieces")
    protected Object pieces;
    @XmlElement(name = "Weight")
    protected String weight;
    @XmlElement(name = "WeightUnit")
    protected String weightUnit;
    @XmlElement(name = "EstDlvyDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar estDlvyDate;
    @XmlElement(name = "ShipmentEvent")
    protected List<ShipmentEvent> shipmentEvent;
    @XmlElement(name = "ShipperReference")
    protected Reference shipperReference;

    /**
     * Gets the value of the originServiceArea property.
     *
     * @return possible object is
     * {@link ServiceArea }
     */
    public ServiceArea getOriginServiceArea() {
        return originServiceArea;
    }

    /**
     * Sets the value of the originServiceArea property.
     *
     * @param value allowed object is
     *              {@link ServiceArea }
     */
    public void setOriginServiceArea(ServiceArea value) {
        this.originServiceArea = value;
    }

    /**
     * Gets the value of the destinationServiceArea property.
     *
     * @return possible object is
     * {@link ServiceArea }
     */
    public ServiceArea getDestinationServiceArea() {
        return destinationServiceArea;
    }

    /**
     * Sets the value of the destinationServiceArea property.
     *
     * @param value allowed object is
     *              {@link ServiceArea }
     */
    public void setDestinationServiceArea(ServiceArea value) {
        this.destinationServiceArea = value;
    }

    /**
     * Gets the value of the shipperName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipperName() {
        return shipperName;
    }

    /**
     * Sets the value of the shipperName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipperName(String value) {
        this.shipperName = value;
    }

    /**
     * Gets the value of the shipperAccountNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipperAccountNumber() {
        return shipperAccountNumber;
    }

    /**
     * Sets the value of the shipperAccountNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipperAccountNumber(String value) {
        this.shipperAccountNumber = value;
    }

    /**
     * Gets the value of the consigneeName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * Sets the value of the consigneeName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConsigneeName(String value) {
        this.consigneeName = value;
    }

    /**
     * Gets the value of the shipmentDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getShipmentDate() {
        return shipmentDate;
    }

    /**
     * Sets the value of the shipmentDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setShipmentDate(XMLGregorianCalendar value) {
        this.shipmentDate = value;
    }

    /**
     * Gets the value of the pieces property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getPieces() {
        return pieces;
    }

    /**
     * Sets the value of the pieces property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setPieces(Object value) {
        this.pieces = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWeight(String value) {
        this.weight = value;
    }

    /**
     * Gets the value of the weightUnit property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * Sets the value of the weightUnit property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWeightUnit(String value) {
        this.weightUnit = value;
    }

    /**
     * Gets the value of the estDlvyDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getEstDlvyDate() {
        return estDlvyDate;
    }

    /**
     * Sets the value of the estDlvyDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setEstDlvyDate(XMLGregorianCalendar value) {
        this.estDlvyDate = value;
    }

    /**
     * Gets the value of the shipmentEvent property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentEvent property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentEvent().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShipmentEvent }
     */
    public List<ShipmentEvent> getShipmentEvent() {
        if (shipmentEvent == null) {
            shipmentEvent = new ArrayList<ShipmentEvent>();
        }
        return this.shipmentEvent;
    }

    /**
     * Gets the value of the shipperReference property.
     *
     * @return possible object is
     * {@link Reference }
     */
    public Reference getShipperReference() {
        return shipperReference;
    }

    /**
     * Sets the value of the shipperReference property.
     *
     * @param value allowed object is
     *              {@link Reference }
     */
    public void setShipperReference(Reference value) {
        this.shipperReference = value;
    }

}
