//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilingType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FilingType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;minLength value="3"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *     &lt;enumeration value="FTR"/&gt;
 *     &lt;enumeration value="ITN"/&gt;
 *     &lt;enumeration value="AES4"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "FilingType")
@XmlEnum
public enum FilingType {

    FTR("FTR"),
    ITN("ITN"),
    @XmlEnumValue("AES4")
    AES_4("AES4");
    private final String value;

    FilingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FilingType fromValue(String v) {
        for (FilingType c : FilingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
