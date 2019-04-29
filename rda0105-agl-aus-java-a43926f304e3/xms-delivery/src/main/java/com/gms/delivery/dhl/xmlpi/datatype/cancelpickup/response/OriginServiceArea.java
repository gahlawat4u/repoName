//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 02:02:43 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OriginServiceArea complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="OriginServiceArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceAreaCode" type="{http://www.dhl.com/datatypes_global}ServiceAreaCode" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FacilityCode" type="{http://www.dhl.com/datatypes_global}FacilityCode" minOccurs="0"/>
 *         &lt;element name="OutboundSortCode" type="{http://www.dhl.com/datatypes_global}OutboundSortCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginServiceArea", propOrder = {
        "serviceAreaCode",
        "description",
        "facilityCode",
        "outboundSortCode"
})
public class OriginServiceArea {

    @XmlElement(name = "ServiceAreaCode")
    protected String serviceAreaCode;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "FacilityCode")
    protected String facilityCode;
    @XmlElement(name = "OutboundSortCode")
    protected String outboundSortCode;

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

    /**
     * Gets the value of the outboundSortCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOutboundSortCode() {
        return outboundSortCode;
    }

    /**
     * Sets the value of the outboundSortCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOutboundSortCode(String value) {
        this.outboundSortCode = value;
    }

}
