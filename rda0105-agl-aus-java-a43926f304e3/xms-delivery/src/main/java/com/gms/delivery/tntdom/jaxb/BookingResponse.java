//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 04:01:49 PM ICT 
//


package com.gms.delivery.tntdom.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="BookingResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Errors" type="{http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking}ArrayOfErrorInformation" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingResponse", namespace = "http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking", propOrder = {
        "orderNumber",
        "errors"
})
public class BookingResponse {

    @XmlElementRef(name = "OrderNumber", namespace = "http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking", type = JAXBElement.class, required = false)
    protected JAXBElement<String> orderNumber;
    @XmlElementRef(name = "Errors", namespace = "http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfErrorInformation> errors;

    /**
     * Gets the value of the orderNumber property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setOrderNumber(JAXBElement<String> value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the errors property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfErrorInformation }{@code >}
     */
    public JAXBElement<ArrayOfErrorInformation> getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ArrayOfErrorInformation }{@code >}
     */
    public void setErrors(JAXBElement<ArrayOfErrorInformation> value) {
        this.errors = value;
    }

}
