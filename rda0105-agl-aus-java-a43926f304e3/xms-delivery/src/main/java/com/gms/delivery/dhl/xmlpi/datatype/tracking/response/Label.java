//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.19 at 12:07:19 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Label
 * <p>
 * <p>Java class for Label complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Label">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LabelTemplate" type="{http://www.dhl.com/datatypes}LabelTemplate" minOccurs="0"/>
 *         &lt;element name="Logo" type="{http://www.dhl.com/datatypes}YesNo" minOccurs="0"/>
 *         &lt;element name="CustomerLogo" type="{http://www.dhl.com/datatypes}CustomerLogo" minOccurs="0"/>
 *         &lt;element name="Resolution" type="{http://www.dhl.com/datatypes}Resolution" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Label", propOrder = {
        "labelTemplate",
        "logo",
        "customerLogo",
        "resolution"
})
public class Label {

    @XmlElement(name = "LabelTemplate")
    protected String labelTemplate;
    @XmlElement(name = "Logo")
    protected YesNo logo;
    @XmlElement(name = "CustomerLogo")
    protected CustomerLogo customerLogo;
    @XmlElement(name = "Resolution")
    protected Integer resolution;

    /**
     * Gets the value of the labelTemplate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLabelTemplate() {
        return labelTemplate;
    }

    /**
     * Sets the value of the labelTemplate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLabelTemplate(String value) {
        this.labelTemplate = value;
    }

    /**
     * Gets the value of the logo property.
     *
     * @return possible object is
     * {@link YesNo }
     */
    public YesNo getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     *
     * @param value allowed object is
     *              {@link YesNo }
     */
    public void setLogo(YesNo value) {
        this.logo = value;
    }

    /**
     * Gets the value of the customerLogo property.
     *
     * @return possible object is
     * {@link CustomerLogo }
     */
    public CustomerLogo getCustomerLogo() {
        return customerLogo;
    }

    /**
     * Sets the value of the customerLogo property.
     *
     * @param value allowed object is
     *              {@link CustomerLogo }
     */
    public void setCustomerLogo(CustomerLogo value) {
        this.customerLogo = value;
    }

    /**
     * Gets the value of the resolution property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setResolution(Integer value) {
        this.resolution = value;
    }

}
