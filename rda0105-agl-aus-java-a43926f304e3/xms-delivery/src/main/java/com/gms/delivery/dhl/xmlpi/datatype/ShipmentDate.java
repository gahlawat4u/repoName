//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 04:23:02 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ShipmentDate complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ShipmentDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ShipmentDateFrom" type="{http://www.dhl.com/datatypes_global}Date"/&gt;
 *         &lt;element name="ShipmentDateTo" type="{http://www.dhl.com/datatypes_global}Date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShipmentDate", propOrder = {
        "shipmentDateFrom",
        "shipmentDateTo"
})
public class ShipmentDate {

    @XmlElement(name = "ShipmentDateFrom", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar shipmentDateFrom;
    @XmlElement(name = "ShipmentDateTo", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar shipmentDateTo;

    /**
     * Gets the value of the shipmentDateFrom property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getShipmentDateFrom() {
        return shipmentDateFrom;
    }

    /**
     * Sets the value of the shipmentDateFrom property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setShipmentDateFrom(XMLGregorianCalendar value) {
        this.shipmentDateFrom = value;
    }

    /**
     * Gets the value of the shipmentDateTo property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getShipmentDateTo() {
        return shipmentDateTo;
    }

    /**
     * Sets the value of the shipmentDateTo property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setShipmentDateTo(XMLGregorianCalendar value) {
        this.shipmentDateTo = value;
    }

}