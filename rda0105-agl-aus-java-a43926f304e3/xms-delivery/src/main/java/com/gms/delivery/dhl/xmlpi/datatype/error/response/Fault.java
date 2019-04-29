//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 08:12:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.error.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * Piece Fault
 * <p>
 * <p>Java class for Fault complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="Fault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PieceFault" type="{http://www.dhl.com/datatypes}PieceFault" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fault", propOrder = {
        "pieceFault"
})
public class Fault {

    @XmlElement(name = "PieceFault", required = true)
    protected List<PieceFault> pieceFault;

    /**
     * Gets the value of the pieceFault property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pieceFault property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPieceFault().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PieceFault }
     */
    public List<PieceFault> getPieceFault() {
        if (pieceFault == null) {
            pieceFault = new ArrayList<PieceFault>();
        }
        return this.pieceFault;
    }

}
