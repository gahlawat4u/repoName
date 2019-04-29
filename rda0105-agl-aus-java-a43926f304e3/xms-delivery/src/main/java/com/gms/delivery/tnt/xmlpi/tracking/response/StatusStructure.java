//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:26:43 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusStructure complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="StatusStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LocalEventDate" type="{}DateType" minOccurs="0"/>
 *         &lt;element name="LocalEventTime" type="{}TimeType" minOccurs="0"/>
 *         &lt;element name="Depot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepotName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusStructure", propOrder = {
        "statusCode",
        "statusDescription",
        "localEventDate",
        "localEventTime",
        "depot",
        "depotName"
})
public class StatusStructure {

    @XmlElement(name = "StatusCode", required = true)
    protected String statusCode;
    @XmlElement(name = "StatusDescription", required = true)
    protected String statusDescription;
    @XmlElement(name = "LocalEventDate")
    protected DateType localEventDate;
    @XmlElement(name = "LocalEventTime")
    protected TimeType localEventTime;
    @XmlElement(name = "Depot")
    protected String depot;
    @XmlElement(name = "DepotName")
    protected String depotName;

    /**
     * Gets the value of the statusCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDescription property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * Sets the value of the statusDescription property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatusDescription(String value) {
        this.statusDescription = value;
    }

    /**
     * Gets the value of the localEventDate property.
     *
     * @return possible object is
     * {@link DateType }
     */
    public DateType getLocalEventDate() {
        return localEventDate;
    }

    /**
     * Sets the value of the localEventDate property.
     *
     * @param value allowed object is
     *              {@link DateType }
     */
    public void setLocalEventDate(DateType value) {
        this.localEventDate = value;
    }

    /**
     * Gets the value of the localEventTime property.
     *
     * @return possible object is
     * {@link TimeType }
     */
    public TimeType getLocalEventTime() {
        return localEventTime;
    }

    /**
     * Sets the value of the localEventTime property.
     *
     * @param value allowed object is
     *              {@link TimeType }
     */
    public void setLocalEventTime(TimeType value) {
        this.localEventTime = value;
    }

    /**
     * Gets the value of the depot property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepot() {
        return depot;
    }

    /**
     * Sets the value of the depot property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepot(String value) {
        this.depot = value;
    }

    /**
     * Gets the value of the depotName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepotName() {
        return depotName;
    }

    /**
     * Sets the value of the depotName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepotName(String value) {
        this.depotName = value;
    }

}
