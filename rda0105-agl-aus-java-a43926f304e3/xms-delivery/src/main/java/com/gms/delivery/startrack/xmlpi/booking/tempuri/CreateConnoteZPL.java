//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.15 at 03:26:24 PM ICT 
//

package com.gms.delivery.startrack.xmlpi.booking.tempuri;

import com.gms.delivery.startrack.xmlpi.booking.AaEConnoteType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="composite" type="{http://schemas.datacontract.org/2004/07/}AaEConnoteType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"composite"})
@XmlRootElement(name = "CreateConnoteZPL")
public class CreateConnoteZPL {

    @XmlElementRef(name = "composite", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<AaEConnoteType> composite;

    /**
     * Gets the value of the composite property.
     *
     * @return possible object is {@link JAXBElement }{@code <}
     * {@link AaEConnoteType }{@code >}
     */
    public JAXBElement<AaEConnoteType> getComposite() {
        return composite;
    }

    /**
     * Sets the value of the composite property.
     *
     * @param value allowed object is {@link JAXBElement }{@code <}
     *              {@link AaEConnoteType }{@code >}
     */
    public void setComposite(JAXBElement<AaEConnoteType> value) {
        this.composite = value;
    }

}
