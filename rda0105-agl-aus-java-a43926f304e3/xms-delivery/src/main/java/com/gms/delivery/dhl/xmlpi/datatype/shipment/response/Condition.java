//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 10:39:35 AM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.shipment.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Condition complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Condition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConditionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConditionData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Condition", propOrder = {
        "conditionCode",
        "conditionData"
})
public class Condition {

    @XmlElement(name = "ConditionCode", required = true)
    protected String conditionCode;
    @XmlElement(name = "ConditionData")
    protected String conditionData;

    /**
     * Gets the value of the conditionCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConditionCode() {
        return conditionCode;
    }

    /**
     * Sets the value of the conditionCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConditionCode(String value) {
        this.conditionCode = value;
    }

    /**
     * Gets the value of the conditionData property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConditionData() {
        return conditionData;
    }

    /**
     * Sets the value of the conditionData property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConditionData(String value) {
        this.conditionData = value;
    }

}
