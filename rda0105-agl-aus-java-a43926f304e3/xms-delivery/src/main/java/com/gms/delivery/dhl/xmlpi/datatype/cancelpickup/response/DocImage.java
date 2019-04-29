//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 02:02:43 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * DocImage
 * <p>
 * <p>Java class for DocImage complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="DocImage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.dhl.com/datatypes_global}Type"/>
 *         &lt;element name="Image" type="{http://www.dhl.com/datatypes_global}Image"/>
 *         &lt;element name="ImageFormat" type="{http://www.dhl.com/datatypes_global}ImageFormat"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocImage", propOrder = {
        "type",
        "image",
        "imageFormat"
})
public class DocImage {

    @XmlElement(name = "Type", required = true)
    protected Type type;
    @XmlElement(name = "Image", required = true)
    protected byte[] image;
    @XmlElement(name = "ImageFormat", required = true)
    protected ImageFormat imageFormat;

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link Type }
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link Type }
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the image property.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setImage(byte[] value) {
        this.image = value;
    }

    /**
     * Gets the value of the imageFormat property.
     *
     * @return possible object is
     * {@link ImageFormat }
     */
    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    /**
     * Sets the value of the imageFormat property.
     *
     * @param value allowed object is
     *              {@link ImageFormat }
     */
    public void setImageFormat(ImageFormat value) {
        this.imageFormat = value;
    }

}
