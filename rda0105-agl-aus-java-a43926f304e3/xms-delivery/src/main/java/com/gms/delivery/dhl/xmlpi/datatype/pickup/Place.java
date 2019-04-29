//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Pickup place
 * <p>
 * <p>Java class for Place complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Place"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LocationType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="1"/&gt;
 *               &lt;enumeration value="B"/&gt;
 *               &lt;enumeration value="R"/&gt;
 *               &lt;enumeration value="C"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CompanyName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Address1"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Address2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PackageLocation"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="35"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="City" type="{http://www.dhl.com/datatypes_global}City"/&gt;
 *         &lt;element name="StateCode" type="{http://www.dhl.com/datatypes_global}DivisionCode" minOccurs="0"/&gt;
 *         &lt;element name="DivisionName" type="{http://www.dhl.com/datatypes_global}Division" minOccurs="0"/&gt;
 *         &lt;element name="CountryCode" type="{http://www.dhl.com/datatypes_global}CountryCode"/&gt;
 *         &lt;element name="PostalCode" type="{http://www.dhl.com/datatypes_global}PostalCode" minOccurs="0"/&gt;
 *         &lt;element name="RouteCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="([A-Z]{2}\d{2})"/&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Place", propOrder = {
        "locationType",
        "companyName",
        "address1",
        "address2",
        "packageLocation",
        "city",
        "stateCode",
        "divisionName",
        "countryCode",
        "postalCode",
        "routeCode"
})
public class Place {

    @XmlElement(name = "LocationType", required = true)
    protected String locationType;
    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "Address1", required = true)
    protected String address1;
    @XmlElement(name = "Address2")
    protected String address2;
    @XmlElement(name = "PackageLocation", required = true)
    protected String packageLocation;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "StateCode")
    protected String stateCode;
    @XmlElement(name = "DivisionName")
    protected String divisionName;
    @XmlElement(name = "CountryCode", required = true)
    protected String countryCode;
    @XmlElement(name = "PostalCode")
    protected String postalCode;
    @XmlElement(name = "RouteCode")
    protected String routeCode;

    /**
     * Gets the value of the locationType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Sets the value of the locationType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLocationType(String value) {
        this.locationType = value;
    }

    /**
     * Gets the value of the companyName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the address1 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the packageLocation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPackageLocation() {
        return packageLocation;
    }

    /**
     * Sets the value of the packageLocation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPackageLocation(String value) {
        this.packageLocation = value;
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
     * Gets the value of the stateCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStateCode(String value) {
        this.stateCode = value;
    }

    /**
     * Gets the value of the divisionName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets the value of the divisionName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDivisionName(String value) {
        this.divisionName = value;
    }

    /**
     * Gets the value of the countryCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the postalCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the routeCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRouteCode() {
        return routeCode;
    }

    /**
     * Sets the value of the routeCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRouteCode(String value) {
        this.routeCode = value;
    }

}
