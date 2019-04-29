//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:13:39 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * <p>Java class for TimeFrameStructure complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TimeFrameStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DateFrom" type="{}DateType"/>
 *         &lt;choice>
 *           &lt;element name="DateTo" type="{}DateType"/>
 *           &lt;element ref="{}NumberOfDays"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeFrameStructure", propOrder = {
        "dateFrom",
        "dateTo",
        "numberOfDays"
})
public class TimeFrameStructure {

    @XmlElement(name = "DateFrom", required = true)
    protected DateType dateFrom;
    @XmlElement(name = "DateTo")
    protected DateType dateTo;
    @XmlElement(name = "NumberOfDays")
    protected BigInteger numberOfDays;

    /**
     * Gets the value of the dateFrom property.
     *
     * @return possible object is
     * {@link DateType }
     */
    public DateType getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     *
     * @param value allowed object is
     *              {@link DateType }
     */
    public void setDateFrom(DateType value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     *
     * @return possible object is
     * {@link DateType }
     */
    public DateType getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     *
     * @param value allowed object is
     *              {@link DateType }
     */
    public void setDateTo(DateType value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the numberOfDays property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * Sets the value of the numberOfDays property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setNumberOfDays(BigInteger value) {
        this.numberOfDays = value;
    }

}