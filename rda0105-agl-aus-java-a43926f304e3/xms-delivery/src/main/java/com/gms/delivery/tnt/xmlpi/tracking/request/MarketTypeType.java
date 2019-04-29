//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:13:39 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarketTypeType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MarketTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Domestic"/>
 *     &lt;enumeration value="International"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "MarketTypeType")
@XmlEnum
public enum MarketTypeType {

    @XmlEnumValue("Domestic")
    DOMESTIC("Domestic"),
    @XmlEnumValue("International")
    INTERNATIONAL("International");
    private final String value;

    MarketTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MarketTypeType fromValue(String v) {
        for (MarketTypeType c : MarketTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}