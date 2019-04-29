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
 * <p>Java class for ShipmentSummaryStructure complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ShipmentSummaryStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TermsOfPayment">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Sender"/>
 *               &lt;enumeration value="Receiver"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DueDate" type="{}DateType"/>
 *         &lt;element name="Service" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentSummaryStructure", propOrder = {
        "termsOfPayment",
        "dueDate",
        "service"
})
public class ShipmentSummaryStructure {

    @XmlElement(name = "TermsOfPayment", required = true)
    protected String termsOfPayment;
    @XmlElement(name = "DueDate", required = true)
    protected DateType dueDate;
    @XmlElement(name = "Service", required = true)
    protected String service;

    /**
     * Gets the value of the termsOfPayment property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTermsOfPayment() {
        return termsOfPayment;
    }

    /**
     * Sets the value of the termsOfPayment property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTermsOfPayment(String value) {
        this.termsOfPayment = value;
    }

    /**
     * Gets the value of the dueDate property.
     *
     * @return possible object is
     * {@link DateType }
     */
    public DateType getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     *
     * @param value allowed object is
     *              {@link DateType }
     */
    public void setDueDate(DateType value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the service property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setService(String value) {
        this.service = value;
    }

}