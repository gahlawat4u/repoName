//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 07:55:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalProtection complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="AdditionalProtection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.dhl.com/datatypes_global}Code"/>
 *         &lt;element name="Value" type="{http://www.dhl.com/datatypes_global}Value"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalProtection", propOrder = {
        "code",
        "value"
})
public class AdditionalProtection {

    @XmlElement(name = "Code", required = true)
    protected Code code;
    @XmlElement(name = "Value")
    protected float value;

    /**
     * Gets the value of the code property.
     *
     * @return possible object is
     * {@link Code }
     */
    public Code getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value allowed object is
     *              {@link Code }
     */
    public void setCode(Code value) {
        this.code = value;
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

}
