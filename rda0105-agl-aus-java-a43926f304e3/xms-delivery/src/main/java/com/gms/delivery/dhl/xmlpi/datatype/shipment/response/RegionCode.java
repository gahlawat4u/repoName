//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 10:39:35 AM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.shipment.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegionCode.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RegionCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;minLength value="2"/>
 *     &lt;maxLength value="2"/>
 *     &lt;enumeration value="AP"/>
 *     &lt;enumeration value="EU"/>
 *     &lt;enumeration value="AM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "RegionCode")
@XmlEnum
public enum RegionCode {

    AP,
    EU,
    AM;

    public String value() {
        return name();
    }

    public static RegionCode fromValue(String v) {
        return valueOf(v);
    }

}
