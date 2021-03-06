//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DestinationServiceArea complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="DestinationServiceArea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ServiceAreaCode" type="{http://www.dhl.com/datatypes_global}ServiceAreaCode" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InboundSortCode" type="{http://www.dhl.com/datatypes_global}InboundSortCode" minOccurs="0"/&gt;
 *         &lt;element name="FacilityCode" type="{http://www.dhl.com/datatypes_global}FacilityCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DestinationServiceArea", propOrder = {
        "serviceAreaCode",
        "description",
        "inboundSortCode",
        "facilityCode"
})
public class DestinationServiceArea {

    @XmlElement(name = "ServiceAreaCode")
    protected String serviceAreaCode;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "InboundSortCode")
    protected String inboundSortCode;
    @XmlElement(name = "FacilityCode")
    protected String facilityCode;

    /**
     * Gets the value of the serviceAreaCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getServiceAreaCode() {
        return serviceAreaCode;
    }

    /**
     * Sets the value of the serviceAreaCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setServiceAreaCode(String value) {
        this.serviceAreaCode = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the inboundSortCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInboundSortCode() {
        return inboundSortCode;
    }

    /**
     * Sets the value of the inboundSortCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInboundSortCode(String value) {
        this.inboundSortCode = value;
    }

    /**
     * Gets the value of the facilityCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFacilityCode() {
        return facilityCode;
    }

    /**
     * Sets the value of the facilityCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFacilityCode(String value) {
        this.facilityCode = value;
    }

}
