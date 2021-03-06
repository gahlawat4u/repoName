//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.27 at 04:33:23 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.shipping.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Activity complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Activity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="CREATE" type="{}Create"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="RATE" type="{}Rate"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="BOOK" type="{}Book"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="SHIP" type="{}Ship"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="PRINT" type="{}Print"/>
 *         &lt;/sequence>
 *         &lt;sequence minOccurs="0">
 *           &lt;element name="SHOW_GROUPCODE">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Activity", propOrder = {
        "create",
        "rate",
        "book",
        "ship",
        "print",
        "showgroupcode"
})
public class Activity {

    @XmlElement(name = "CREATE")
    protected Create create;
    @XmlElement(name = "RATE")
    protected Rate rate;
    @XmlElement(name = "BOOK")
    protected Book book;
    @XmlElement(name = "SHIP")
    protected Ship ship;
    @XmlElement(name = "PRINT")
    protected Print print;
    @XmlElement(name = "SHOW_GROUPCODE")
    protected Activity.SHOWGROUPCODE showgroupcode;

    /**
     * Gets the value of the create property.
     *
     * @return possible object is
     * {@link Create }
     */
    public Create getCREATE() {
        return create;
    }

    /**
     * Sets the value of the create property.
     *
     * @param value allowed object is
     *              {@link Create }
     */
    public void setCREATE(Create value) {
        this.create = value;
    }

    /**
     * Gets the value of the rate property.
     *
     * @return possible object is
     * {@link Rate }
     */
    public Rate getRATE() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     *
     * @param value allowed object is
     *              {@link Rate }
     */
    public void setRATE(Rate value) {
        this.rate = value;
    }

    /**
     * Gets the value of the book property.
     *
     * @return possible object is
     * {@link Book }
     */
    public Book getBOOK() {
        return book;
    }

    /**
     * Sets the value of the book property.
     *
     * @param value allowed object is
     *              {@link Book }
     */
    public void setBOOK(Book value) {
        this.book = value;
    }

    /**
     * Gets the value of the ship property.
     *
     * @return possible object is
     * {@link Ship }
     */
    public Ship getSHIP() {
        return ship;
    }

    /**
     * Sets the value of the ship property.
     *
     * @param value allowed object is
     *              {@link Ship }
     */
    public void setSHIP(Ship value) {
        this.ship = value;
    }

    /**
     * Gets the value of the print property.
     *
     * @return possible object is
     * {@link Print }
     */
    public Print getPRINT() {
        return print;
    }

    /**
     * Sets the value of the print property.
     *
     * @param value allowed object is
     *              {@link Print }
     */
    public void setPRINT(Print value) {
        this.print = value;
    }

    /**
     * Gets the value of the showgroupcode property.
     *
     * @return possible object is
     * {@link Activity.SHOWGROUPCODE }
     */
    public Activity.SHOWGROUPCODE getSHOWGROUPCODE() {
        return showgroupcode;
    }

    /**
     * Sets the value of the showgroupcode property.
     *
     * @param value allowed object is
     *              {@link Activity.SHOWGROUPCODE }
     */
    public void setSHOWGROUPCODE(Activity.SHOWGROUPCODE value) {
        this.showgroupcode = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SHOWGROUPCODE {


    }

}
