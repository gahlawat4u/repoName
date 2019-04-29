//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:26:43 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.response;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for AddressStructure complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="AddressStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;sequence maxOccurs="3">
 *           &lt;element name="AddressLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Postcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Country" type="{}CountryStructure"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContactPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="VATNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="addressParty" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Sender"/>
 *             &lt;enumeration value="Receiver"/>
 *             &lt;enumeration value="Collection"/>
 *             &lt;enumeration value="Delivery"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressStructure", propOrder = {
        "name",
        "addressLine",
        "city",
        "province",
        "postcode",
        "country",
        "phoneNumber",
        "contactName",
        "contactPhoneNumber",
        "accountNumber",
        "vatNumber"
})
public class AddressStructure {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "AddressLine", required = true)
    protected List<String> addressLine;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "Province")
    protected String province;
    @XmlElement(name = "Postcode")
    protected String postcode;
    @XmlElement(name = "Country", required = true)
    protected CountryStructure country;
    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "ContactName")
    protected String contactName;
    @XmlElement(name = "ContactPhoneNumber")
    protected String contactPhoneNumber;
    @XmlElement(name = "AccountNumber")
    protected Object accountNumber;
    @XmlElement(name = "VATNumber")
    protected String vatNumber;
    @XmlAttribute(name = "addressParty", required = true)
    protected String addressParty;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the addressLine property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<String>();
        }
        return this.addressLine;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the province property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProvince(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the postcode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link CountryStructure }
     */
    public CountryStructure getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link CountryStructure }
     */
    public void setCountry(CountryStructure value) {
        this.country = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the contactName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactPhoneNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    /**
     * Sets the value of the contactPhoneNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContactPhoneNumber(String value) {
        this.contactPhoneNumber = value;
    }

    /**
     * Gets the value of the accountNumber property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setAccountNumber(Object value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the vatNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVATNumber() {
        return vatNumber;
    }

    /**
     * Sets the value of the vatNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVATNumber(String value) {
        this.vatNumber = value;
    }

    /**
     * Gets the value of the addressParty property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddressParty() {
        return addressParty;
    }

    /**
     * Sets the value of the addressParty property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAddressParty(String value) {
        this.addressParty = value;
    }

}