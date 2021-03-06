//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.15 at 04:35:40 PM ICT 
//


package com.gms.delivery.toll.xmlpi.pickup.response;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for HeaderType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="HeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Receiver" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateTimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderType", propOrder = {
        "sender",
        "receiver",
        "documentType",
        "documentID",
        "dateTimeStamp"
})
public class HeaderType {

    @XmlElement(name = "Sender", required = true)
    protected String sender;
    @XmlElement(name = "Receiver", required = true)
    protected String receiver;
    @XmlElement(name = "DocumentType", required = true)
    protected String documentType;
    @XmlElement(name = "DocumentID", required = true)
    protected String documentID;
    @XmlElement(name = "DateTimeStamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTimeStamp;

    /**
     * Gets the value of the sender property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the documentType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

    /**
     * Gets the value of the documentID property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDocumentID() {
        return documentID;
    }

    /**
     * Sets the value of the documentID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentID(String value) {
        this.documentID = value;
    }

    /**
     * Gets the value of the dateTimeStamp property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDateTimeStamp() {
        return dateTimeStamp;
    }

    /**
     * Sets the value of the dateTimeStamp property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDateTimeStamp(XMLGregorianCalendar value) {
        this.dateTimeStamp = value;
    }

}
