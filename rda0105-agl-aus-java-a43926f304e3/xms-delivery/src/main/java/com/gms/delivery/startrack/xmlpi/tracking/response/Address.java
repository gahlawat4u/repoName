//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 03:08:02 PM ICT 
//


package com.gms.delivery.startrack.xmlpi.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for address complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="address"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressLine" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *         &lt;element name="suburbOrLocation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="stateCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
        "addressLine",
        "suburbOrLocation",
        "state",
        "postCode",
        "country",
        "stateCode"
})
public class Address {

    @XmlElement(required = true)
    protected List<String> addressLine;
    @XmlElement(required = true)
    protected String suburbOrLocation;
    @XmlElement(required = true)
    protected String state;
    protected int postCode;
    @XmlElement(required = true)
    protected Object country;
    protected int stateCode;

    /**
     * Gets the value of the addressLine property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<String>();
        }
        return this.addressLine;
    }

    /**
     * Gets the value of the suburbOrLocation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSuburbOrLocation() {
        return suburbOrLocation;
    }

    /**
     * Sets the value of the suburbOrLocation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSuburbOrLocation(String value) {
        this.suburbOrLocation = value;
    }

    /**
     * Gets the value of the state property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the postCode property.
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     */
    public void setPostCode(int value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setCountry(Object value) {
        this.country = value;
    }

    /**
     * Gets the value of the stateCode property.
     */
    public int getStateCode() {
        return stateCode;
    }

    /**
     * Sets the value of the stateCode property.
     */
    public void setStateCode(int value) {
        this.stateCode = value;
    }

}
