//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 08:12:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.error.response;

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
 *         &lt;element name="Response" type="{http://www.dhl.com/datatypes}ErrorResponse"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "response"
})
@XmlRootElement(name = "ShipmentValidateErrorResponse", namespace = "http://www.dhl.com")
public class ShipmentValidateErrorResponse {

    @XmlElement(name = "Response", required = true)
    protected ErrorResponse response;

    /**
     * Gets the value of the response property.
     *
     * @return possible object is
     * {@link ErrorResponse }
     */
    public ErrorResponse getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     *
     * @param value allowed object is
     *              {@link ErrorResponse }
     */
    public void setResponse(ErrorResponse value) {
        this.response = value;
    }

}
