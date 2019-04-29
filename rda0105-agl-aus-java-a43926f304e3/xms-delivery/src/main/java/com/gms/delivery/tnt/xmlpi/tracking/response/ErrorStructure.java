//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:26:43 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.response;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ErrorStructure complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ErrorStructure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorStructure", propOrder = {
        "codeAndMessage"
})
public class ErrorStructure {

    @XmlElements({
            @XmlElement(name = "Code", required = true, type = BigInteger.class),
            @XmlElement(name = "Message", required = true, type = String.class)
    })
    protected List<Serializable> codeAndMessage;

    /**
     * Gets the value of the codeAndMessage property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codeAndMessage property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodeAndMessage().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * {@link String }
     */
    public List<Serializable> getCodeAndMessage() {
        if (codeAndMessage == null) {
            codeAndMessage = new ArrayList<Serializable>();
        }
        return this.codeAndMessage;
    }

}