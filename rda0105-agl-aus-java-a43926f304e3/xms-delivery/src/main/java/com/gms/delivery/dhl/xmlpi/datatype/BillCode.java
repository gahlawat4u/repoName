//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillCode.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;minLength value="2"/&gt;
 *     &lt;maxLength value="3"/&gt;
 *     &lt;enumeration value="DSA"/&gt;
 *     &lt;enumeration value="DBA"/&gt;
 *     &lt;enumeration value="TCA"/&gt;
 *     &lt;enumeration value="IEA"/&gt;
 *     &lt;enumeration value="UAN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "BillCode")
@XmlEnum
public enum BillCode {

    DSA,
    DBA,
    TCA,
    IEA,
    UAN;

    public String value() {
        return name();
    }

    public static BillCode fromValue(String v) {
        return valueOf(v);
    }

}
