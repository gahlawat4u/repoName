//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 09:59:03 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for consignmentIdentityType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="consignmentIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consignmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consignmentIdentityType", propOrder = {
        "consignmentNumber",
        "customerReference"
})
public class ConsignmentIdentityType {

    @XmlElement(required = true)
    protected String consignmentNumber;
    @XmlElementRef(name = "customerReference", type = JAXBElement.class, required = false)
    protected JAXBElement<String> customerReference;

    /**
     * Gets the value of the consignmentNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConsignmentNumber() {
        return consignmentNumber;
    }

    /**
     * Sets the value of the consignmentNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConsignmentNumber(String value) {
        this.consignmentNumber = value;
    }

    /**
     * Gets the value of the customerReference property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getCustomerReference() {
        return customerReference;
    }

    /**
     * Sets the value of the customerReference property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setCustomerReference(JAXBElement<String> value) {
        this.customerReference = value;
    }

}
