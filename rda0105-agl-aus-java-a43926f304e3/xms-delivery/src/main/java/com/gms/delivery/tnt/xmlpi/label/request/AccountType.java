//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 09:59:03 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="accountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNumber" type="{}stringMaxLength10"/>
 *         &lt;element name="accountCountry" type="{}stringMinLength2MaxLength2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountType", propOrder = {
        "accountNumber",
        "accountCountry"
})
public class AccountType {

    @XmlElement(required = true)
    protected String accountNumber;
    @XmlElement(required = true)
    protected String accountCountry;

    /**
     * Gets the value of the accountNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the accountCountry property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAccountCountry() {
        return accountCountry;
    }

    /**
     * Sets the value of the accountCountry property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAccountCountry(String value) {
        this.accountCountry = value;
    }

}