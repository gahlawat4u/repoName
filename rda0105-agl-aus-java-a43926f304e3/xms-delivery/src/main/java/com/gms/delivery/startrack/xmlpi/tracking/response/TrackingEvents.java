//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 03:08:02 PM ICT 
//


package com.gms.delivery.startrack.xmlpi.tracking.response;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for trackingEvents complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="trackingEvents"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="scanner" type="{}scanner"/&gt;
 *         &lt;element name="scanMode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="transitState" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="eventDateTime" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="quantityDelivered" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="quantityOnHand" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="scanningDepot" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="signatoryName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackingEvents", propOrder = {
        "scanner",
        "scanMode",
        "transitState"
})
public class TrackingEvents {

    @XmlElement(required = true)
    protected Scanner scanner;
    @XmlElement(required = true)
    protected String scanMode;
    @XmlElement(required = true)
    protected String transitState;
    @XmlAttribute(name = "eventDateTime")
    protected String eventDateTime;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "quantityDelivered")
    protected Integer quantityDelivered;
    @XmlAttribute(name = "quantityOnHand")
    protected Integer quantityOnHand;
    @XmlAttribute(name = "scanningDepot")
    protected String scanningDepot;
    @XmlAttribute(name = "signatoryName")
    protected String signatoryName;

    /**
     * Gets the value of the scanner property.
     *
     * @return possible object is
     * {@link Scanner }
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Sets the value of the scanner property.
     *
     * @param value allowed object is
     *              {@link Scanner }
     */
    public void setScanner(Scanner value) {
        this.scanner = value;
    }

    /**
     * Gets the value of the scanMode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getScanMode() {
        return scanMode;
    }

    /**
     * Sets the value of the scanMode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setScanMode(String value) {
        this.scanMode = value;
    }

    /**
     * Gets the value of the transitState property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTransitState() {
        return transitState;
    }

    /**
     * Sets the value of the transitState property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTransitState(String value) {
        this.transitState = value;
    }

    /**
     * Gets the value of the eventDateTime property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Sets the value of the eventDateTime property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEventDateTime(String value) {
        this.eventDateTime = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the quantityDelivered property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getQuantityDelivered() {
        return quantityDelivered;
    }

    /**
     * Sets the value of the quantityDelivered property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setQuantityDelivered(Integer value) {
        this.quantityDelivered = value;
    }

    /**
     * Gets the value of the quantityOnHand property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Sets the value of the quantityOnHand property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setQuantityOnHand(Integer value) {
        this.quantityOnHand = value;
    }

    /**
     * Gets the value of the scanningDepot property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getScanningDepot() {
        return scanningDepot;
    }

    /**
     * Sets the value of the scanningDepot property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setScanningDepot(String value) {
        this.scanningDepot = value;
    }

    /**
     * Gets the value of the signatoryName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSignatoryName() {
        return signatoryName;
    }

    /**
     * Sets the value of the signatoryName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSignatoryName(String value) {
        this.signatoryName = value;
    }

}
