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
 * <p>Java class for printStatus complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="printStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="printedInd" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="reprintedInd" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printStatus")
public class PrintStatus {

    @XmlAttribute(name = "printedInd")
    protected String printedInd;
    @XmlAttribute(name = "reprintedInd")
    protected String reprintedInd;

    /**
     * Gets the value of the printedInd property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPrintedInd() {
        return printedInd;
    }

    /**
     * Sets the value of the printedInd property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPrintedInd(String value) {
        this.printedInd = value;
    }

    /**
     * Gets the value of the reprintedInd property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReprintedInd() {
        return reprintedInd;
    }

    /**
     * Sets the value of the reprintedInd property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReprintedInd(String value) {
        this.reprintedInd = value;
    }

}