//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 07:55:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Generic response header
 * <p>
 * <p>Java class for Response complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceHeader" type="{http://www.dhl.com/datatypes_global}ResponseServiceHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
        "serviceHeader"
})
public class Response {

    @XmlElement(name = "ServiceHeader", required = true)
    protected ResponseServiceHeader serviceHeader;

    /**
     * Gets the value of the serviceHeader property.
     *
     * @return possible object is
     * {@link ResponseServiceHeader }
     */
    public ResponseServiceHeader getServiceHeader() {
        return serviceHeader;
    }

    /**
     * Sets the value of the serviceHeader property.
     *
     * @param value allowed object is
     *              {@link ResponseServiceHeader }
     */
    public void setServiceHeader(ResponseServiceHeader value) {
        this.serviceHeader = value;
    }

}
