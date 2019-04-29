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
 * <p>Java class for contact complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="contact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PersonName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Phone">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="25"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PhoneExtension" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contact", namespace = "http://www.dhl.com/pickupdatatypes_global", propOrder = {
        "personName",
        "phone",
        "phoneExtension"
})
public class Contact {

    @XmlElement(name = "PersonName", required = true)
    protected String personName;
    @XmlElement(name = "Phone", required = true)
    protected String phone;
    @XmlElement(name = "PhoneExtension")
    protected String phoneExtension;

    /**
     * Gets the value of the personName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets the value of the personName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPersonName(String value) {
        this.personName = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the phoneExtension property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhoneExtension() {
        return phoneExtension;
    }

    /**
     * Sets the value of the phoneExtension property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhoneExtension(String value) {
        this.phoneExtension = value;
    }

}
