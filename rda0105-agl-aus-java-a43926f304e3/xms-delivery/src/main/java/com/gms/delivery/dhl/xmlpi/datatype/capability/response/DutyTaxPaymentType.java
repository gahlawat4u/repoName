//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 03:07:35 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DutyTaxPaymentType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DutyTaxPaymentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="1"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="T"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DutyTaxPaymentType")
@XmlEnum
public enum DutyTaxPaymentType {

    S,
    R,
    T;

    public String value() {
        return name();
    }

    public static DutyTaxPaymentType fromValue(String v) {
        return valueOf(v);
    }

}
