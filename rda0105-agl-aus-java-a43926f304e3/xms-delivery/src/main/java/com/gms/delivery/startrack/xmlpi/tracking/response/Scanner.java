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
 * <p>
 * Java class for scanner complex type.
 * <p>
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="scanner"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="depotCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scanner")
public class Scanner {

    @XmlAttribute(name = "depotCode")
    protected String depotCode;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the depotCode property.
     *
     * @return possible object is {@link String }
     */
    public String getDepotCode() {
        return depotCode;
    }

    /**
     * Sets the value of the depotCode property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDepotCode(String value) {
        this.depotCode = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link Integer }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link Integer }
     */
    public void setId(String value) {
        this.id = value;
    }

}