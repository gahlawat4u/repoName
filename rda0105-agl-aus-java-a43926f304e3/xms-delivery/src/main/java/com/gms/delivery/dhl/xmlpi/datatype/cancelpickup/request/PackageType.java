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
 * <p>Java class for PackageType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PackageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="BD"/>
 *     &lt;enumeration value="BP"/>
 *     &lt;enumeration value="CP"/>
 *     &lt;enumeration value="DC"/>
 *     &lt;enumeration value="DF"/>
 *     &lt;enumeration value="DM"/>
 *     &lt;enumeration value="ED"/>
 *     &lt;enumeration value="EE"/>
 *     &lt;enumeration value="FR"/>
 *     &lt;enumeration value="JB"/>
 *     &lt;enumeration value="JD"/>
 *     &lt;enumeration value="JJ"/>
 *     &lt;enumeration value="JP"/>
 *     &lt;enumeration value="OD"/>
 *     &lt;enumeration value="PA"/>
 *     &lt;enumeration value="YP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "PackageType")
@XmlEnum
public enum PackageType {

    BD,
    BP,
    CP,
    DC,
    DF,
    DM,
    ED,
    EE,
    FR,
    JB,
    JD,
    JJ,
    JP,
    OD,
    PA,
    YP;

    public String value() {
        return name();
    }

    public static PackageType fromValue(String v) {
        return valueOf(v);
    }

}
