//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 01:56:24 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IDType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SSN"/>
 *     &lt;enumeration value="EIN"/>
 *     &lt;enumeration value="DUNS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "IDType")
@XmlEnum
public enum IDType {

    SSN,
    EIN,
    DUNS;

    public String value() {
        return name();
    }

    public static IDType fromValue(String v) {
        return valueOf(v);
    }

}
