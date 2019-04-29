//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 10:37:06 AM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.shipment.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Filing complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Filing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FilingType" type="{http://www.dhl.com/datatypes_global}FilingType" minOccurs="0"/>
 *         &lt;element name="FTSR" type="{http://www.dhl.com/datatypes_global}FTSR" minOccurs="0"/>
 *         &lt;element name="ITN" type="{http://www.dhl.com/datatypes_global}ITN" minOccurs="0"/>
 *         &lt;element name="AES4EIN" type="{http://www.dhl.com/datatypes_global}AES4EIN" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Filing", propOrder = {
        "filingType",
        "ftsr",
        "itn",
        "aes4EIN"
})
public class Filing {

    @XmlElement(name = "FilingType")
    protected FilingType filingType;
    @XmlElement(name = "FTSR")
    protected String ftsr;
    @XmlElement(name = "ITN")
    protected String itn;
    @XmlElement(name = "AES4EIN")
    protected String aes4EIN;

    /**
     * Gets the value of the filingType property.
     *
     * @return possible object is
     * {@link FilingType }
     */
    public FilingType getFilingType() {
        return filingType;
    }

    /**
     * Sets the value of the filingType property.
     *
     * @param value allowed object is
     *              {@link FilingType }
     */
    public void setFilingType(FilingType value) {
        this.filingType = value;
    }

    /**
     * Gets the value of the ftsr property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFTSR() {
        return ftsr;
    }

    /**
     * Sets the value of the ftsr property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFTSR(String value) {
        this.ftsr = value;
    }

    /**
     * Gets the value of the itn property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getITN() {
        return itn;
    }

    /**
     * Sets the value of the itn property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setITN(String value) {
        this.itn = value;
    }

    /**
     * Gets the value of the aes4EIN property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAES4EIN() {
        return aes4EIN;
    }

    /**
     * Sets the value of the aes4EIN property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAES4EIN(String value) {
        this.aes4EIN = value;
    }

}
