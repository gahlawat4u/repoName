//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:49:39 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * CustomerLogo
 * <p>
 * <p>Java class for CustomerLogo complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="CustomerLogo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LogoImage" type="{http://www.dhl.com/datatypes}LogoImage"/>
 *         &lt;element name="LogoImageFormat" type="{http://www.dhl.com/datatypes}LogoImageFormat"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerLogo", propOrder = {
        "logoImage",
        "logoImageFormat"
})
public class CustomerLogo {

    @XmlElement(name = "LogoImage", required = true)
    protected byte[] logoImage;
    @XmlElement(name = "LogoImageFormat", required = true)
    protected LogoImageFormat logoImageFormat;

    /**
     * Gets the value of the logoImage property.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getLogoImage() {
        return logoImage;
    }

    /**
     * Sets the value of the logoImage property.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setLogoImage(byte[] value) {
        this.logoImage = value;
    }

    /**
     * Gets the value of the logoImageFormat property.
     *
     * @return possible object is
     * {@link LogoImageFormat }
     */
    public LogoImageFormat getLogoImageFormat() {
        return logoImageFormat;
    }

    /**
     * Sets the value of the logoImageFormat property.
     *
     * @param value allowed object is
     *              {@link LogoImageFormat }
     */
    public void setLogoImageFormat(LogoImageFormat value) {
        this.logoImageFormat = value;
    }

}
