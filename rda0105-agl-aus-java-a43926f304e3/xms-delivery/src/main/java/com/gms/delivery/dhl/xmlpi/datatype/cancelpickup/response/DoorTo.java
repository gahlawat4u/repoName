//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 02:02:43 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoorTo.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DoorTo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="DD"/>
 *     &lt;enumeration value="DA"/>
 *     &lt;enumeration value="AA"/>
 *     &lt;enumeration value="DC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DoorTo")
@XmlEnum
public enum DoorTo {

    DD,
    DA,
    AA,
    DC;

    public String value() {
        return name();
    }

    public static DoorTo fromValue(String v) {
        return valueOf(v);
    }

}
