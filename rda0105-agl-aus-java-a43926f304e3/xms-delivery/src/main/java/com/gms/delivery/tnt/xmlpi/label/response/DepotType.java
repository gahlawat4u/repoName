//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 10:24:43 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for depotType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="depotType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depotCode" type="{}stringMinLength3MaxLength3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depotType", propOrder = {
        "depotCode"
})
public class DepotType {

    @XmlElement(required = true)
    protected String depotCode;

    /**
     * Gets the value of the depotCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepotCode() {
        return depotCode;
    }

    /**
     * Sets the value of the depotCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepotCode(String value) {
        this.depotCode = value;
    }

}
