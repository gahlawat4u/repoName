//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.03 at 06:21:26 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.shipping.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Print complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Print">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CONNOTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LABEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MANIFEST" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="INVOICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Print", propOrder = {
        "connote",
        "label",
        "manifest",
        "invoice"
})
public class Print {

    @XmlElement(name = "CONNOTE", required = true)
    protected String connote;
    @XmlElement(name = "LABEL", required = true)
    protected String label;
    @XmlElement(name = "MANIFEST", required = true)
    protected String manifest;
    @XmlElement(name = "INVOICE", required = true)
    protected String invoice;

    /**
     * Gets the value of the connote property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCONNOTE() {
        return connote;
    }

    /**
     * Sets the value of the connote property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCONNOTE(String value) {
        this.connote = value;
    }

    /**
     * Gets the value of the label property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLABEL() {
        return label;
    }

    /**
     * Sets the value of the label property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLABEL(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the manifest property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMANIFEST() {
        return manifest;
    }

    /**
     * Sets the value of the manifest property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMANIFEST(String value) {
        this.manifest = value;
    }

    /**
     * Gets the value of the invoice property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getINVOICE() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setINVOICE(String value) {
        this.invoice = value;
    }

}