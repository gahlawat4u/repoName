//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.03 at 06:21:26 PM ICT 
//

package com.gms.delivery.tnt.xmlpi.shipping.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Consignment complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * <pre>
 * &lt;complexType name="Consignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CONREF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONNUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SUCCESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIRSTTIMETRADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BOOKINGREF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consignment", propOrder = {"conref", "connumber", "success", "firsttimetrader", "bookingRef"})
public class Consignment {

    @XmlElement(name = "CONREF", required = true)
    protected String conref;
    @XmlElement(name = "CONNUMBER", required = true)
    protected String connumber;
    @XmlElement(name = "SUCCESS", required = true)
    protected String success;
    @XmlElement(name = "FIRSTTIMETRADER", required = true)
    protected String firsttimetrader;
    @XmlElement(name = "BOOKINGREF", required = false)
    protected String bookingRef;

    /**
     * Gets the value of the conref property.
     *
     * @return possible object is {@link String }
     */
    public String getCONREF() {
        return conref;
    }

    /**
     * Sets the value of the conref property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCONREF(String value) {
        this.conref = value;
    }

    /**
     * Gets the value of the connumber property.
     *
     * @return possible object is {@link String }
     */
    public String getCONNUMBER() {
        return connumber;
    }

    /**
     * Sets the value of the connumber property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCONNUMBER(String value) {
        this.connumber = value;
    }

    /**
     * Gets the value of the success property.
     *
     * @return possible object is {@link String }
     */
    public String getSUCCESS() {
        return success;
    }

    /**
     * Sets the value of the success property.
     *
     * @param value allowed object is {@link String }
     */
    public void setSUCCESS(String value) {
        this.success = value;
    }

    /**
     * Gets the value of the firsttimetrader property.
     *
     * @return possible object is {@link String }
     */
    public String getFIRSTTIMETRADER() {
        return firsttimetrader;
    }

    /**
     * Sets the value of the firsttimetrader property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFIRSTTIMETRADER(String value) {
        this.firsttimetrader = value;
    }

    public String getBOOKINGREF() {
        return bookingRef;
    }

    public void setBOOKINGREF(String bookingRef) {
        this.bookingRef = bookingRef;
    }
}