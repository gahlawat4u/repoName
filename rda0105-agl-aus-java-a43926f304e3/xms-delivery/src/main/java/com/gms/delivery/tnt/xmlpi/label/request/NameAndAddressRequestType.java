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
 * <p>Java class for nameAndAddressRequestType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="nameAndAddressRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{}stringMaxLength40"/>
 *         &lt;element name="addressLine1" type="{}stringMaxLength30"/>
 *         &lt;element name="addressLine2" type="{}stringMaxLength30" minOccurs="0"/>
 *         &lt;element name="addressLine3" type="{}stringMaxLength30" minOccurs="0"/>
 *         &lt;element name="town" type="{}stringMaxLength40" minOccurs="0"/>
 *         &lt;element name="exactMatch" type="{}booleanEnum" minOccurs="0"/>
 *         &lt;element name="province" type="{}stringMaxLength30" minOccurs="0"/>
 *         &lt;element name="postcode" type="{}stringMaxLength9" minOccurs="0"/>
 *         &lt;element name="country" type="{}stringMinLength2MaxLength2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nameAndAddressRequestType", propOrder = {
        "name",
        "addressLine1",
        "addressLine2",
        "addressLine3",
        "town",
        "exactMatch",
        "province",
        "postcode",
        "country"
})
public class NameAndAddressRequestType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String addressLine1;
    @XmlElementRef(name = "addressLine2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> addressLine2;
    @XmlElementRef(name = "addressLine3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> addressLine3;
    protected String town;
    @XmlElement(defaultValue = "Y")
    protected BooleanEnum exactMatch;
    protected String province;
    protected String postcode;
    @XmlElement(required = true)
    protected String country;

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
     * Gets the value of the addressLine1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setAddressLine2(JAXBElement<String> value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the addressLine3 property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getAddressLine3() {
        return addressLine3;
    }

    /**
     * Sets the value of the addressLine3 property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setAddressLine3(JAXBElement<String> value) {
        this.addressLine3 = value;
    }

    /**
     * Gets the value of the town property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the exactMatch property.
     *
     * @return possible object is
     * {@link BooleanEnum }
     */
    public BooleanEnum getExactMatch() {
        return exactMatch;
    }

    /**
     * Sets the value of the exactMatch property.
     *
     * @param value allowed object is
     *              {@link BooleanEnum }
     */
    public void setExactMatch(BooleanEnum value) {
        this.exactMatch = value;
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
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountry(String value) {
        this.country = value;
    }

}
