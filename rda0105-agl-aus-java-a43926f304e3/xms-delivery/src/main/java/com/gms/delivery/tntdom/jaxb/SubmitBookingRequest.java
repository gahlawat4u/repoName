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
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="booking" type="{http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking}BookingRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userName",
        "password",
        "booking"
})
@XmlRootElement(name = "SubmitBookingRequest")
public class SubmitBookingRequest {

    @XmlElementRef(name = "userName", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userName;
    @XmlElementRef(name = "password", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> password;
    @XmlElementRef(name = "booking", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<BookingRequest> booking;

    /**
     * Gets the value of the userName property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setUserName(JAXBElement<String> value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setPassword(JAXBElement<String> value) {
        this.password = value;
    }

    /**
     * Gets the value of the booking property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link BookingRequest }{@code >}
     */
    public JAXBElement<BookingRequest> getBooking() {
        return booking;
    }

    /**
     * Sets the value of the booking property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link BookingRequest }{@code >}
     */
    public void setBooking(JAXBElement<BookingRequest> value) {
        this.booking = value;
    }

}
