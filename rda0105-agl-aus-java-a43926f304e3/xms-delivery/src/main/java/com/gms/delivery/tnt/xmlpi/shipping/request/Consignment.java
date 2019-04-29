//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.27 at 04:33:23 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.shipping.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Consignment complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Consignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CONREF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="DETAILS" type="{}Details"/>
 *           &lt;element name="CONNUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Consignment", propOrder = {
        "conref",
        "details",
        "connumber"
})
public class Consignment {

    @XmlElement(name = "CONREF", required = true)
    protected String conref;
    @XmlElement(name = "DETAILS")
    protected Details details;
    @XmlElement(name = "CONNUMBER")
    protected String connumber;

    /**
     * Gets the value of the conref property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONREF() {
        return conref;
    }

    /**
     * Sets the value of the conref property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONREF(String value) {
        this.conref = value;
    }

    /**
     * Gets the value of the details property.
     *
     * @return possible object is
     * {@link Details }
     */
    public Details getDETAILS() {
        return details;
    }

    /**
     * Sets the value of the details property.
     *
     * @param value allowed object is
     *              {@link Details }
     */
    public void setDETAILS(Details value) {
        this.details = value;
    }

    /**
     * Gets the value of the connumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONNUMBER() {
        return connumber;
    }

    /**
     * Sets the value of the connumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONNUMBER(String value) {
        this.connumber = value;
    }

}
