//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 04:01:49 PM ICT 
//


package com.gms.delivery.tntdom.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SubmitBookingResult" type="{http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking}BookingResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "submitBookingResult"
})
@XmlRootElement(name = "SubmitBookingResponse")
public class SubmitBookingResponse {

    @XmlElementRef(name = "SubmitBookingResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<BookingResponse> submitBookingResult;

    /**
     * Gets the value of the submitBookingResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link BookingResponse }{@code >}
     */
    public JAXBElement<BookingResponse> getSubmitBookingResult() {
        return submitBookingResult;
    }

    /**
     * Sets the value of the submitBookingResult property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link BookingResponse }{@code >}
     */
    public void setSubmitBookingResult(JAXBElement<BookingResponse> value) {
        this.submitBookingResult = value;
    }

}
