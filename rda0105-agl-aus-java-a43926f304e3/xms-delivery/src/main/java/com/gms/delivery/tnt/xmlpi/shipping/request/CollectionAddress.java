//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.27 at 04:33:23 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.shipping.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CollectionAddress complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="CollectionAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COMPANYNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STREETADDRESS1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="STREETADDRESS2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="STREETADDRESS3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;element name="CITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="PROVINCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;element name="POSTCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNTRY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="VAT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;element name="CONTACTNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONTACTDIALCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONTACTTELEPHONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="CONTACTEMAIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionAddress", propOrder = {
        "companyname",
        "streetaddress1",
        "streetaddress2",
        "streetaddress3",
        "city",
        "province",
        "postcode",
        "country",
        "vat",
        "contactname",
        "contactdialcode",
        "contacttelephone",
        "contactemail"
})
public class CollectionAddress {

    @XmlElement(name = "COMPANYNAME", required = true)
    protected String companyname;
    @XmlElement(name = "STREETADDRESS1", required = true)
    protected String streetaddress1;
    @XmlElement(name = "STREETADDRESS2")
    protected String streetaddress2;
    @XmlElement(name = "STREETADDRESS3")
    protected String streetaddress3;
    @XmlElement(name = "CITY", required = true)
    protected String city;
    @XmlElement(name = "PROVINCE")
    protected String province;
    @XmlElement(name = "POSTCODE", required = true)
    protected String postcode;
    @XmlElement(name = "COUNTRY", required = true)
    protected String country;
    @XmlElement(name = "VAT")
    protected String vat;
    @XmlElement(name = "CONTACTNAME", required = true)
    protected String contactname;
    @XmlElement(name = "CONTACTDIALCODE", required = true)
    protected String contactdialcode;
    @XmlElement(name = "CONTACTTELEPHONE", required = true)
    protected String contacttelephone;
    @XmlElement(name = "CONTACTEMAIL")
    protected String contactemail;

    /**
     * Gets the value of the companyname property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCOMPANYNAME() {
        return companyname;
    }

    /**
     * Sets the value of the companyname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCOMPANYNAME(String value) {
        this.companyname = value;
    }

    /**
     * Gets the value of the streetaddress1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSTREETADDRESS1() {
        return streetaddress1;
    }

    /**
     * Sets the value of the streetaddress1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSTREETADDRESS1(String value) {
        this.streetaddress1 = value;
    }

    /**
     * Gets the value of the streetaddress2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSTREETADDRESS2() {
        return streetaddress2;
    }

    /**
     * Sets the value of the streetaddress2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSTREETADDRESS2(String value) {
        this.streetaddress2 = value;
    }

    /**
     * Gets the value of the streetaddress3 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSTREETADDRESS3() {
        return streetaddress3;
    }

    /**
     * Sets the value of the streetaddress3 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSTREETADDRESS3(String value) {
        this.streetaddress3 = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCITY() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCITY(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the province property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPROVINCE() {
        return province;
    }

    /**
     * Sets the value of the province property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPROVINCE(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the postcode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPOSTCODE() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPOSTCODE(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCOUNTRY() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCOUNTRY(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the vat property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVAT() {
        return vat;
    }

    /**
     * Sets the value of the vat property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVAT(String value) {
        this.vat = value;
    }

    /**
     * Gets the value of the contactname property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONTACTNAME() {
        return contactname;
    }

    /**
     * Sets the value of the contactname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONTACTNAME(String value) {
        this.contactname = value;
    }

    /**
     * Gets the value of the contactdialcode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONTACTDIALCODE() {
        return contactdialcode;
    }

    /**
     * Sets the value of the contactdialcode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONTACTDIALCODE(String value) {
        this.contactdialcode = value;
    }

    /**
     * Gets the value of the contacttelephone property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONTACTTELEPHONE() {
        return contacttelephone;
    }

    /**
     * Sets the value of the contacttelephone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONTACTTELEPHONE(String value) {
        this.contacttelephone = value;
    }

    /**
     * Gets the value of the contactemail property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONTACTEMAIL() {
        return contactemail;
    }

    /**
     * Sets the value of the contactemail property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONTACTEMAIL(String value) {
        this.contactemail = value;
    }

}