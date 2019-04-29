//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.01 at 04:01:49 PM ICT 
//


package com.gms.delivery.tntdom.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PayerType.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PayerType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Sender"/&gt;
 *     &lt;enumeration value="Receiver"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "PayerType", namespace = "http://schemas.datacontract.org/2004/07/TNT.DataContracts.Booking")
@XmlEnum
public enum PayerType {

    @XmlEnumValue("Sender")
    SENDER("Sender"),
    @XmlEnumValue("Receiver")
    RECEIVER("Receiver");
    private final String value;

    PayerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PayerType fromValue(String v) {
        for (PayerType c : PayerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}