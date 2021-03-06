//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup;

import com.gms.delivery.dhl.xmlpi.datatype.ChargeCardType;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for ChargeCard complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ChargeCard"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ChargeCardNo" type="{http://www.dhl.com/datatypes_global}ChargeCardNo"/&gt;
 *         &lt;element name="ChargeCardType" type="{http://www.dhl.com/datatypes_global}ChargeCardType"/&gt;
 *         &lt;element name="ChargeCardExpiryDate" type="{http://www.dhl.com/datatypes_global}ChargeCardExpDateValidator"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeCard", propOrder = {
        "chargeCardNo",
        "chargeCardType",
        "chargeCardExpiryDate"
})
public class ChargeCard {

    @XmlElement(name = "ChargeCardNo")
    @XmlSchemaType(name = "positiveInteger")
    protected long chargeCardNo;
    @XmlElement(name = "ChargeCardType", required = true)
    @XmlSchemaType(name = "string")
    protected ChargeCardType chargeCardType;
    @XmlElement(name = "ChargeCardExpiryDate", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger chargeCardExpiryDate;

    /**
     * Gets the value of the chargeCardNo property.
     */
    public long getChargeCardNo() {
        return chargeCardNo;
    }

    /**
     * Sets the value of the chargeCardNo property.
     */
    public void setChargeCardNo(long value) {
        this.chargeCardNo = value;
    }

    /**
     * Gets the value of the chargeCardType property.
     *
     * @return possible object is
     * {@link ChargeCardType }
     */
    public ChargeCardType getChargeCardType() {
        return chargeCardType;
    }

    /**
     * Sets the value of the chargeCardType property.
     *
     * @param value allowed object is
     *              {@link ChargeCardType }
     */
    public void setChargeCardType(ChargeCardType value) {
        this.chargeCardType = value;
    }

    /**
     * Gets the value of the chargeCardExpiryDate property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getChargeCardExpiryDate() {
        return chargeCardExpiryDate;
    }

    /**
     * Sets the value of the chargeCardExpiryDate property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setChargeCardExpiryDate(BigInteger value) {
        this.chargeCardExpiryDate = value;
    }

}
