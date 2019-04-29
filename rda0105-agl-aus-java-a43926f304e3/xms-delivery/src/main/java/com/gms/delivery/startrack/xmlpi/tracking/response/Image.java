//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 03:08:02 PM ICT 
//


package com.gms.delivery.startrack.xmlpi.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for image complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="image"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creationDateTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="signatoryName" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="cartridge" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="frame" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="consignmentId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="isPOD" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "image", propOrder = {
        "creationDateTime",
        "type",
        "signatoryName",
        "cartridge",
        "frame",
        "consignmentId",
        "isPOD"
})
public class Image {

    @XmlElement(required = true)
    protected String creationDateTime;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected Object signatoryName;
    @XmlElement(required = true)
    protected String cartridge;
    protected int frame;
    @XmlElement(required = true)
    protected String consignmentId;
    @XmlElement(required = true)
    protected String isPOD;

    /**
     * Gets the value of the creationDateTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets the value of the creationDateTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreationDateTime(String value) {
        this.creationDateTime = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the signatoryName property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getSignatoryName() {
        return signatoryName;
    }

    /**
     * Sets the value of the signatoryName property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setSignatoryName(Object value) {
        this.signatoryName = value;
    }

    /**
     * Gets the value of the cartridge property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCartridge() {
        return cartridge;
    }

    /**
     * Sets the value of the cartridge property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCartridge(String value) {
        this.cartridge = value;
    }

    /**
     * Gets the value of the frame property.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * Sets the value of the frame property.
     */
    public void setFrame(int value) {
        this.frame = value;
    }

    /**
     * Gets the value of the consignmentId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConsignmentId() {
        return consignmentId;
    }

    /**
     * Sets the value of the consignmentId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConsignmentId(String value) {
        this.consignmentId = value;
    }

    /**
     * Gets the value of the isPOD property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIsPOD() {
        return isPOD;
    }

    /**
     * Sets the value of the isPOD property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIsPOD(String value) {
        this.isPOD = value;
    }

}