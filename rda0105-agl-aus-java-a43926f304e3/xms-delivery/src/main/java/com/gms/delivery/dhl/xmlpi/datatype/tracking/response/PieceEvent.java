//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.19 at 12:07:19 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.tracking.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Describes the checkpoint information
 * <p>
 * <p>Java class for PieceEvent complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PieceEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="ServiceEvent" type="{http://www.dhl.com/datatypes}ServiceEvent"/>
 *         &lt;element name="Signatory" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ServiceArea" type="{http://www.dhl.com/datatypes}ServiceArea"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PieceEvent", propOrder = {
        "date",
        "time",
        "serviceEvent",
        "signatory",
        "serviceArea"
})
public class PieceEvent {

    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "Time", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(name = "ServiceEvent", required = true)
    protected ServiceEvent serviceEvent;
    @XmlElement(name = "Signatory")
    protected String signatory;
    @XmlElement(name = "ServiceArea", required = true)
    protected ServiceArea serviceArea;

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the serviceEvent property.
     *
     * @return possible object is
     * {@link ServiceEvent }
     */
    public ServiceEvent getServiceEvent() {
        return serviceEvent;
    }

    /**
     * Sets the value of the serviceEvent property.
     *
     * @param value allowed object is
     *              {@link ServiceEvent }
     */
    public void setServiceEvent(ServiceEvent value) {
        this.serviceEvent = value;
    }

    /**
     * Gets the value of the signatory property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSignatory() {
        return signatory;
    }

    /**
     * Sets the value of the signatory property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSignatory(String value) {
        this.signatory = value;
    }

    /**
     * Gets the value of the serviceArea property.
     *
     * @return possible object is
     * {@link ServiceArea }
     */
    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    /**
     * Sets the value of the serviceArea property.
     *
     * @param value allowed object is
     *              {@link ServiceArea }
     */
    public void setServiceArea(ServiceArea value) {
        this.serviceArea = value;
    }

}
