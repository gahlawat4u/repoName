//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 09:41:18 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.tracking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventRemarks complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="EventRemarks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FurtherDetails" type="{http://www.dhl.com/datatypes}FurtherDetails"/>
 *         &lt;element name="NextSteps" type="{http://www.dhl.com/datatypes}NextSteps"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventRemarks", propOrder = {
        "furtherDetails",
        "nextSteps"
})
public class EventRemarks {

    @XmlElement(name = "FurtherDetails", required = true)
    protected String furtherDetails;
    @XmlElement(name = "NextSteps", required = true)
    protected String nextSteps;

    /**
     * Gets the value of the furtherDetails property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFurtherDetails() {
        return furtherDetails;
    }

    /**
     * Sets the value of the furtherDetails property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFurtherDetails(String value) {
        this.furtherDetails = value;
    }

    /**
     * Gets the value of the nextSteps property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNextSteps() {
        return nextSteps;
    }

    /**
     * Sets the value of the nextSteps property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNextSteps(String value) {
        this.nextSteps = value;
    }

}
