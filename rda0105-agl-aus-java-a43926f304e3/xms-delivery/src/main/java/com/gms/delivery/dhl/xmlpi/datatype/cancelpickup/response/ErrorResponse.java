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
 * Generic response header
 * <p>
 * <p>Java class for ErrorResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ErrorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceHeader" type="{http://www.dhl.com/datatypes_global}ServiceHeader"/>
 *         &lt;element name="Status" type="{http://www.dhl.com/datatypes_global}Status"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorResponse", propOrder = {
        "serviceHeader",
        "status"
})
public class ErrorResponse {

    @XmlElement(name = "ServiceHeader", required = true)
    protected ServiceHeader serviceHeader;
    @XmlElement(name = "Status", required = true)
    protected Status status;

    /**
     * Gets the value of the serviceHeader property.
     *
     * @return possible object is
     * {@link ServiceHeader }
     */
    public ServiceHeader getServiceHeader() {
        return serviceHeader;
    }

    /**
     * Sets the value of the serviceHeader property.
     *
     * @param value allowed object is
     *              {@link ServiceHeader }
     */
    public void setServiceHeader(ServiceHeader value) {
        this.serviceHeader = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link Status }
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    public void setStatus(Status value) {
        this.status = value;
    }

}
