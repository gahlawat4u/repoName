//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 08:12:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.error.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Dutiable complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Dutiable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeclaredValue" type="{http://www.dhl.com/datatypes}Money" minOccurs="0"/>
 *         &lt;element name="DeclaredCurrency" type="{http://www.dhl.com/datatypes}CurrencyCode" minOccurs="0"/>
 *         &lt;element name="ScheduleB" type="{http://www.dhl.com/datatypes}ScheduleB" minOccurs="0"/>
 *         &lt;element name="ExportLicense" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="ShipperEIN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ShipperIDType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="S"/>
 *               &lt;enumeration value="E"/>
 *               &lt;enumeration value="D"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConsigneeIDType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
 *               &lt;enumeration value="S"/>
 *               &lt;enumeration value="E"/>
 *               &lt;enumeration value="D"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ImportLicense" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="ConsigneeEIN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TermsOfTrade" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dutiable", propOrder = {
        "declaredValue",
        "declaredCurrency",
        "scheduleB",
        "exportLicense",
        "shipperEIN",
        "shipperIDType",
        "consigneeIDType",
        "importLicense",
        "consigneeEIN",
        "termsOfTrade"
})
public class Dutiable {

    @XmlElement(name = "DeclaredValue")
    protected Float declaredValue;
    @XmlElement(name = "DeclaredCurrency")
    protected String declaredCurrency;
    @XmlElement(name = "ScheduleB")
    protected String scheduleB;
    @XmlElement(name = "ExportLicense")
    protected Object exportLicense;
    @XmlElement(name = "ShipperEIN")
    protected String shipperEIN;
    @XmlElement(name = "ShipperIDType")
    protected String shipperIDType;
    @XmlElement(name = "ConsigneeIDType")
    protected String consigneeIDType;
    @XmlElement(name = "ImportLicense")
    protected Object importLicense;
    @XmlElement(name = "ConsigneeEIN")
    protected String consigneeEIN;
    @XmlElement(name = "TermsOfTrade")
    protected Object termsOfTrade;

    /**
     * Gets the value of the declaredValue property.
     *
     * @return possible object is
     * {@link Float }
     */
    public Float getDeclaredValue() {
        return declaredValue;
    }

    /**
     * Sets the value of the declaredValue property.
     *
     * @param value allowed object is
     *              {@link Float }
     */
    public void setDeclaredValue(Float value) {
        this.declaredValue = value;
    }

    /**
     * Gets the value of the declaredCurrency property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDeclaredCurrency() {
        return declaredCurrency;
    }

    /**
     * Sets the value of the declaredCurrency property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeclaredCurrency(String value) {
        this.declaredCurrency = value;
    }

    /**
     * Gets the value of the scheduleB property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getScheduleB() {
        return scheduleB;
    }

    /**
     * Sets the value of the scheduleB property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setScheduleB(String value) {
        this.scheduleB = value;
    }

    /**
     * Gets the value of the exportLicense property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getExportLicense() {
        return exportLicense;
    }

    /**
     * Sets the value of the exportLicense property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setExportLicense(Object value) {
        this.exportLicense = value;
    }

    /**
     * Gets the value of the shipperEIN property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipperEIN() {
        return shipperEIN;
    }

    /**
     * Sets the value of the shipperEIN property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipperEIN(String value) {
        this.shipperEIN = value;
    }

    /**
     * Gets the value of the shipperIDType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipperIDType() {
        return shipperIDType;
    }

    /**
     * Sets the value of the shipperIDType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipperIDType(String value) {
        this.shipperIDType = value;
    }

    /**
     * Gets the value of the consigneeIDType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConsigneeIDType() {
        return consigneeIDType;
    }

    /**
     * Sets the value of the consigneeIDType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConsigneeIDType(String value) {
        this.consigneeIDType = value;
    }

    /**
     * Gets the value of the importLicense property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getImportLicense() {
        return importLicense;
    }

    /**
     * Sets the value of the importLicense property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setImportLicense(Object value) {
        this.importLicense = value;
    }

    /**
     * Gets the value of the consigneeEIN property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConsigneeEIN() {
        return consigneeEIN;
    }

    /**
     * Sets the value of the consigneeEIN property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConsigneeEIN(String value) {
        this.consigneeEIN = value;
    }

    /**
     * Gets the value of the termsOfTrade property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getTermsOfTrade() {
        return termsOfTrade;
    }

    /**
     * Sets the value of the termsOfTrade property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setTermsOfTrade(Object value) {
        this.termsOfTrade = value;
    }

}