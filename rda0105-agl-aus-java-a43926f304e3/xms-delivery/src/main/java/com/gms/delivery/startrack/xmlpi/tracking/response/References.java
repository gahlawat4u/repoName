//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 03:08:02 PM ICT 
//


package com.gms.delivery.startrack.xmlpi.tracking.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for references complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="references"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="lock_seq" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="printOnLabel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "references")
public class References {

    @XmlAttribute(name = "lock_seq")
    protected Integer lockSeq;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "printOnLabel")
    protected String printOnLabel;
    @XmlAttribute(name = "priority")
    protected Integer priority;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "value")
    protected String value;

    /**
     * Gets the value of the lockSeq property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getLockSeq() {
        return lockSeq;
    }

    /**
     * Sets the value of the lockSeq property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setLockSeq(Integer value) {
        this.lockSeq = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the printOnLabel property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPrintOnLabel() {
        return printOnLabel;
    }

    /**
     * Sets the value of the printOnLabel property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPrintOnLabel(String value) {
        this.printOnLabel = value;
    }

    /**
     * Gets the value of the priority property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPriority(Integer value) {
        this.priority = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

}
