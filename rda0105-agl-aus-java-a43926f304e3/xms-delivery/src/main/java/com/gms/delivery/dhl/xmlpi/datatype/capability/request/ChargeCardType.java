//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:49:39 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeCardType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChargeCardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="AM"/>
 *     &lt;enumeration value="DC"/>
 *     &lt;enumeration value="DI"/>
 *     &lt;enumeration value="MC"/>
 *     &lt;enumeration value="VI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "ChargeCardType")
@XmlEnum
public enum ChargeCardType {

    AM,
    DC,
    DI,
    MC,
    VI;

    public String value() {
        return name();
    }

    public static ChargeCardType fromValue(String v) {
        return valueOf(v);
    }

}
