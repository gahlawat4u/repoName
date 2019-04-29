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
 * <p>Java class for Print complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Print">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="REQUIRED" type="{}Required"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="CONNOTE" type="{}Connote"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="LABEL" type="{}Label"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="MANIFEST" type="{}Manifest"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="INVOICE" type="{}Invoice"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="EMAILTO" type="{}EmailTo"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="EMAILFROM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Print", propOrder = {
        "required",
        "connote",
        "label",
        "manifest",
        "invoice",
        "emailto",
        "emailfrom"
})
public class Print {

    @XmlElement(name = "REQUIRED")
    protected Required required;
    @XmlElement(name = "CONNOTE")
    protected Connote connote;
    @XmlElement(name = "LABEL")
    protected Label label;
    @XmlElement(name = "MANIFEST")
    protected Manifest manifest;
    @XmlElement(name = "INVOICE")
    protected Invoice invoice;
    @XmlElement(name = "EMAILTO")
    protected EmailTo emailto;
    @XmlElement(name = "EMAILFROM")
    protected String emailfrom;

    /**
     * Gets the value of the required property.
     *
     * @return possible object is
     * {@link Required }
     */
    public Required getREQUIRED() {
        return required;
    }

    /**
     * Sets the value of the required property.
     *
     * @param value allowed object is
     *              {@link Required }
     */
    public void setREQUIRED(Required value) {
        this.required = value;
    }

    /**
     * Gets the value of the connote property.
     *
     * @return possible object is
     * {@link Connote }
     */
    public Connote getCONNOTE() {
        return connote;
    }

    /**
     * Sets the value of the connote property.
     *
     * @param value allowed object is
     *              {@link Connote }
     */
    public void setCONNOTE(Connote value) {
        this.connote = value;
    }

    /**
     * Gets the value of the label property.
     *
     * @return possible object is
     * {@link Label }
     */
    public Label getLABEL() {
        return label;
    }

    /**
     * Sets the value of the label property.
     *
     * @param value allowed object is
     *              {@link Label }
     */
    public void setLABEL(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the manifest property.
     *
     * @return possible object is
     * {@link Manifest }
     */
    public Manifest getMANIFEST() {
        return manifest;
    }

    /**
     * Sets the value of the manifest property.
     *
     * @param value allowed object is
     *              {@link Manifest }
     */
    public void setMANIFEST(Manifest value) {
        this.manifest = value;
    }

    /**
     * Gets the value of the invoice property.
     *
     * @return possible object is
     * {@link Invoice }
     */
    public Invoice getINVOICE() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     *
     * @param value allowed object is
     *              {@link Invoice }
     */
    public void setINVOICE(Invoice value) {
        this.invoice = value;
    }

    /**
     * Gets the value of the emailto property.
     *
     * @return possible object is
     * {@link EmailTo }
     */
    public EmailTo getEMAILTO() {
        return emailto;
    }

    /**
     * Sets the value of the emailto property.
     *
     * @param value allowed object is
     *              {@link EmailTo }
     */
    public void setEMAILTO(EmailTo value) {
        this.emailto = value;
    }

    /**
     * Gets the value of the emailfrom property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEMAILFROM() {
        return emailfrom;
    }

    /**
     * Sets the value of the emailfrom property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEMAILFROM(String value) {
        this.emailfrom = value;
    }

}