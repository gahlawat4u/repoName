//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 07:55:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Pickup complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Pickup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PickupDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ReadyByTime" type="{http://www.dhl.com/pickupdatatypes_global}TimeHM"/>
 *         &lt;element name="CloseTime" type="{http://www.dhl.com/pickupdatatypes_global}TimeHM"/>
 *         &lt;element name="AfterHoursClosingTime" type="{http://www.dhl.com/pickupdatatypes_global}TimeHM" minOccurs="0"/>
 *         &lt;element name="AfterHoursLocation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Pieces" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="weight" type="{http://www.dhl.com/datatypes_global}WeightSeg" minOccurs="0"/>
 *         &lt;element name="SpecialInstructions" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="75"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pickup", namespace = "http://www.dhl.com/pickupdatatypes_global", propOrder = {
        "pickupDate",
        "readyByTime",
        "closeTime",
        "afterHoursClosingTime",
        "afterHoursLocation",
        "pieces",
        "weight",
        "specialInstructions"
})
public class Pickup {

    @XmlElement(name = "PickupDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pickupDate;
    @XmlElement(name = "ReadyByTime", required = true)
    protected String readyByTime;
    @XmlElement(name = "CloseTime", required = true)
    protected String closeTime;
    @XmlElement(name = "AfterHoursClosingTime")
    protected String afterHoursClosingTime;
    @XmlElement(name = "AfterHoursLocation")
    protected String afterHoursLocation;
    @XmlElement(name = "Pieces")
    protected Integer pieces;
    protected WeightSeg weight;
    @XmlElement(name = "SpecialInstructions")
    protected String specialInstructions;

    /**
     * Gets the value of the pickupDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setPickupDate(XMLGregorianCalendar value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the readyByTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReadyByTime() {
        return readyByTime;
    }

    /**
     * Sets the value of the readyByTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReadyByTime(String value) {
        this.readyByTime = value;
    }

    /**
     * Gets the value of the closeTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * Sets the value of the closeTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCloseTime(String value) {
        this.closeTime = value;
    }

    /**
     * Gets the value of the afterHoursClosingTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAfterHoursClosingTime() {
        return afterHoursClosingTime;
    }

    /**
     * Sets the value of the afterHoursClosingTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAfterHoursClosingTime(String value) {
        this.afterHoursClosingTime = value;
    }

    /**
     * Gets the value of the afterHoursLocation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAfterHoursLocation() {
        return afterHoursLocation;
    }

    /**
     * Sets the value of the afterHoursLocation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAfterHoursLocation(String value) {
        this.afterHoursLocation = value;
    }

    /**
     * Gets the value of the pieces property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getPieces() {
        return pieces;
    }

    /**
     * Sets the value of the pieces property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPieces(Integer value) {
        this.pieces = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return possible object is
     * {@link WeightSeg }
     */
    public WeightSeg getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value allowed object is
     *              {@link WeightSeg }
     */
    public void setWeight(WeightSeg value) {
        this.weight = value;
    }

    /**
     * Gets the value of the specialInstructions property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    /**
     * Sets the value of the specialInstructions property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSpecialInstructions(String value) {
        this.specialInstructions = value;
    }

}
