//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 02:02:43 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * <p>Java class for ExportLineItem complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ExportLineItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineNumber" type="{http://www.dhl.com/datatypes_global}LineNumber"/>
 *         &lt;element name="Quantity" type="{http://www.dhl.com/datatypes_global}Quantity"/>
 *         &lt;element name="QuantityUnit" type="{http://www.dhl.com/datatypes_global}QuantityUnit"/>
 *         &lt;element name="Description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="75"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Value">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *               &lt;minInclusive value="0.00"/>
 *               &lt;maxInclusive value="9999999999.99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IsDomestic" type="{http://www.dhl.com/datatypes_global}YesNo" minOccurs="0"/>
 *         &lt;element name="CommodityCode" type="{http://www.dhl.com/datatypes_global}CommodityCode" minOccurs="0"/>
 *         &lt;element name="ScheduleB" type="{http://www.dhl.com/datatypes_global}ScheduleB" minOccurs="0"/>
 *         &lt;element name="ECCN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="11"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Weight" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Weight" type="{http://www.dhl.com/datatypes_global}Weight"/>
 *                   &lt;element name="WeightUnit" type="{http://www.dhl.com/datatypes_global}WeightUnit"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="License" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LicenseNumber" type="{http://www.dhl.com/datatypes_global}LicenseNumber"/>
 *                   &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LicenseSymbol" type="{http://www.dhl.com/datatypes_global}LicenseNumber" minOccurs="0"/>
 *         &lt;element name="ManufactureCountryCode" type="{http://www.dhl.com/datatypes_global}CountryCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExportLineItem", propOrder = {
        "lineNumber",
        "quantity",
        "quantityUnit",
        "description",
        "value",
        "isDomestic",
        "commodityCode",
        "scheduleB",
        "eccn",
        "weight",
        "license",
        "licenseSymbol",
        "manufactureCountryCode"
})
public class ExportLineItem {

    @XmlElement(name = "LineNumber")
    protected int lineNumber;
    @XmlElement(name = "Quantity", required = true)
    protected BigInteger quantity;
    @XmlElement(name = "QuantityUnit", required = true)
    protected String quantityUnit;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Value")
    protected float value;
    @XmlElement(name = "IsDomestic")
    protected YesNo isDomestic;
    @XmlElement(name = "CommodityCode")
    protected String commodityCode;
    @XmlElement(name = "ScheduleB")
    protected String scheduleB;
    @XmlElement(name = "ECCN")
    protected String eccn;
    @XmlElement(name = "Weight")
    protected ExportLineItem.Weight weight;
    @XmlElement(name = "License")
    protected ExportLineItem.License license;
    @XmlElement(name = "LicenseSymbol")
    protected String licenseSymbol;
    @XmlElement(name = "ManufactureCountryCode")
    protected String manufactureCountryCode;

    /**
     * Gets the value of the lineNumber property.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     */
    public void setLineNumber(int value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the quantity property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setQuantity(BigInteger value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the quantityUnit property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQuantityUnit() {
        return quantityUnit;
    }

    /**
     * Sets the value of the quantityUnit property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQuantityUnit(String value) {
        this.quantityUnit = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the value property.
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Gets the value of the isDomestic property.
     *
     * @return possible object is
     * {@link YesNo }
     */
    public YesNo getIsDomestic() {
        return isDomestic;
    }

    /**
     * Sets the value of the isDomestic property.
     *
     * @param value allowed object is
     *              {@link YesNo }
     */
    public void setIsDomestic(YesNo value) {
        this.isDomestic = value;
    }

    /**
     * Gets the value of the commodityCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * Sets the value of the commodityCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCommodityCode(String value) {
        this.commodityCode = value;
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
     * Gets the value of the eccn property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getECCN() {
        return eccn;
    }

    /**
     * Sets the value of the eccn property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setECCN(String value) {
        this.eccn = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return possible object is
     * {@link ExportLineItem.Weight }
     */
    public ExportLineItem.Weight getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value allowed object is
     *              {@link ExportLineItem.Weight }
     */
    public void setWeight(ExportLineItem.Weight value) {
        this.weight = value;
    }

    /**
     * Gets the value of the license property.
     *
     * @return possible object is
     * {@link ExportLineItem.License }
     */
    public ExportLineItem.License getLicense() {
        return license;
    }

    /**
     * Sets the value of the license property.
     *
     * @param value allowed object is
     *              {@link ExportLineItem.License }
     */
    public void setLicense(ExportLineItem.License value) {
        this.license = value;
    }

    /**
     * Gets the value of the licenseSymbol property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLicenseSymbol() {
        return licenseSymbol;
    }

    /**
     * Sets the value of the licenseSymbol property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLicenseSymbol(String value) {
        this.licenseSymbol = value;
    }

    /**
     * Gets the value of the manufactureCountryCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getManufactureCountryCode() {
        return manufactureCountryCode;
    }

    /**
     * Sets the value of the manufactureCountryCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setManufactureCountryCode(String value) {
        this.manufactureCountryCode = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="LicenseNumber" type="{http://www.dhl.com/datatypes_global}LicenseNumber"/>
     *         &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "licenseNumber",
            "expiryDate"
    })
    public static class License {

        @XmlElement(name = "LicenseNumber", required = true)
        protected String licenseNumber;
        @XmlElement(name = "ExpiryDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar expiryDate;

        /**
         * Gets the value of the licenseNumber property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getLicenseNumber() {
            return licenseNumber;
        }

        /**
         * Sets the value of the licenseNumber property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLicenseNumber(String value) {
            this.licenseNumber = value;
        }

        /**
         * Gets the value of the expiryDate property.
         *
         * @return possible object is
         * {@link XMLGregorianCalendar }
         */
        public XMLGregorianCalendar getExpiryDate() {
            return expiryDate;
        }

        /**
         * Sets the value of the expiryDate property.
         *
         * @param value allowed object is
         *              {@link XMLGregorianCalendar }
         */
        public void setExpiryDate(XMLGregorianCalendar value) {
            this.expiryDate = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Weight" type="{http://www.dhl.com/datatypes_global}Weight"/>
     *         &lt;element name="WeightUnit" type="{http://www.dhl.com/datatypes_global}WeightUnit"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "weight",
            "weightUnit"
    })
    public static class Weight {

        @XmlElement(name = "Weight", required = true)
        protected BigDecimal weight;
        @XmlElement(name = "WeightUnit", required = true)
        protected WeightUnit weightUnit;

        /**
         * Gets the value of the weight property.
         *
         * @return possible object is
         * {@link BigDecimal }
         */
        public BigDecimal getWeight() {
            return weight;
        }

        /**
         * Sets the value of the weight property.
         *
         * @param value allowed object is
         *              {@link BigDecimal }
         */
        public void setWeight(BigDecimal value) {
            this.weight = value;
        }

        /**
         * Gets the value of the weightUnit property.
         *
         * @return possible object is
         * {@link WeightUnit }
         */
        public WeightUnit getWeightUnit() {
            return weightUnit;
        }

        /**
         * Sets the value of the weightUnit property.
         *
         * @param value allowed object is
         *              {@link WeightUnit }
         */
        public void setWeightUnit(WeightUnit value) {
            this.weightUnit = value;
        }

    }

}
