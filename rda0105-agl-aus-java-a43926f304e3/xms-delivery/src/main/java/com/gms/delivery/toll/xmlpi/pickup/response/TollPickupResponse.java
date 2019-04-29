//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.15 at 04:35:40 PM ICT 
//


package com.gms.delivery.toll.xmlpi.pickup.response;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{}HeaderType"/>
 *         &lt;choice>
 *           &lt;element name="PickupConfirmation" type="{}PickupConfirmationType"/>
 *           &lt;element name="Error" type="{}PickupError"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "header",
        "pickupConfirmation",
        "error"
})
@XmlRootElement(name = "TollPickupResponse")
public class TollPickupResponse {

    @XmlElement(name = "Header", required = true)
    protected HeaderType header;
    @XmlElement(name = "PickupConfirmation")
    protected PickupConfirmationType pickupConfirmation;
    @XmlElement(name = "Error")
    protected PickupError error;

    /**
     * Gets the value of the header property.
     *
     * @return possible object is
     * {@link HeaderType }
     */
    public HeaderType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     *
     * @param value allowed object is
     *              {@link HeaderType }
     */
    public void setHeader(HeaderType value) {
        this.header = value;
    }

    /**
     * Gets the value of the pickupConfirmation property.
     *
     * @return possible object is
     * {@link PickupConfirmationType }
     */
    public PickupConfirmationType getPickupConfirmation() {
        return pickupConfirmation;
    }

    /**
     * Sets the value of the pickupConfirmation property.
     *
     * @param value allowed object is
     *              {@link PickupConfirmationType }
     */
    public void setPickupConfirmation(PickupConfirmationType value) {
        this.pickupConfirmation = value;
    }

    /**
     * Gets the value of the error property.
     *
     * @return possible object is
     * {@link PickupError }
     */
    public PickupError getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     * @param value allowed object is
     *              {@link PickupError }
     */
    public void setError(PickupError value) {
        this.error = value;
    }

}
