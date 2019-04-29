//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 09:41:18 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.tracking.request;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelImageFormat.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LabelImageFormat">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;minLength value="3"/>
 *     &lt;maxLength value="4"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="ZPL2"/>
 *     &lt;enumeration value="EPL2"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "LabelImageFormat")
@XmlEnum
public enum LabelImageFormat {

    PDF("PDF"),
    @XmlEnumValue("ZPL2")
    ZPL_2("ZPL2"),
    @XmlEnumValue("EPL2")
    EPL_2("EPL2");
    private final String value;

    LabelImageFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LabelImageFormat fromValue(String v) {
        for (LabelImageFormat c : LabelImageFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
