//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 10:24:43 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for actionDepotType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="actionDepotType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depotCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionDayOfWeek" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="actionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionDepotType", propOrder = {
        "depotCode",
        "actionDayOfWeek",
        "actionDate"
})
public class ActionDepotType {

    @XmlElement(required = true)
    protected String depotCode;
    protected int actionDayOfWeek;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar actionDate;

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

    /**
     * Gets the value of the actionDayOfWeek property.
     */
    public int getActionDayOfWeek() {
        return actionDayOfWeek;
    }

    /**
     * Sets the value of the actionDayOfWeek property.
     */
    public void setActionDayOfWeek(int value) {
        this.actionDayOfWeek = value;
    }

    /**
     * Gets the value of the actionDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getActionDate() {
        return actionDate;
    }

    /**
     * Sets the value of the actionDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setActionDate(XMLGregorianCalendar value) {
        this.actionDate = value;
    }

}
