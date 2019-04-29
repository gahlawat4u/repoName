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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ShipmentDate complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ShipmentDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipmentDateFrom" type="{http://www.dhl.com/datatypes}Date"/>
 *         &lt;element name="ShipmentDateTo" type="{http://www.dhl.com/datatypes}Date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentDate", propOrder = {
        "shipmentDateFrom",
        "shipmentDateTo"
})
public class ShipmentDate {

    @XmlElement(name = "ShipmentDateFrom", required = true)
    @XmlJavaTypeAdapter(Adapter1.class)
    protected String shipmentDateFrom;
    @XmlElement(name = "ShipmentDateTo", required = true)
    @XmlJavaTypeAdapter(Adapter1.class)
    protected String shipmentDateTo;

    /**
     * Gets the value of the shipmentDateFrom property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipmentDateFrom() {
        return shipmentDateFrom;
    }

    /**
     * Sets the value of the shipmentDateFrom property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipmentDateFrom(String value) {
        this.shipmentDateFrom = value;
    }

    /**
     * Gets the value of the shipmentDateTo property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShipmentDateTo() {
        return shipmentDateTo;
    }

    /**
     * Sets the value of the shipmentDateTo property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShipmentDateTo(String value) {
        this.shipmentDateTo = value;
    }

}
