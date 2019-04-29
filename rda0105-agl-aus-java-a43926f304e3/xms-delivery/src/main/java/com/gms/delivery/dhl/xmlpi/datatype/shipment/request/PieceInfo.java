//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 10:37:06 AM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.shipment.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PieceInfo complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PieceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PieceDetails" type="{http://www.dhl.com/datatypes_global}PieceDetails"/>
 *         &lt;element name="PieceEvent" type="{http://www.dhl.com/datatypes_global}PieceEvent" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PieceInfo", propOrder = {
        "pieceDetails",
        "pieceEvent"
})
public class PieceInfo {

    @XmlElement(name = "PieceDetails", required = true)
    protected PieceDetails pieceDetails;
    @XmlElement(name = "PieceEvent", required = true)
    protected List<PieceEvent> pieceEvent;

    /**
     * Gets the value of the pieceDetails property.
     *
     * @return possible object is
     * {@link PieceDetails }
     */
    public PieceDetails getPieceDetails() {
        return pieceDetails;
    }

    /**
     * Sets the value of the pieceDetails property.
     *
     * @param value allowed object is
     *              {@link PieceDetails }
     */
    public void setPieceDetails(PieceDetails value) {
        this.pieceDetails = value;
    }

    /**
     * Gets the value of the pieceEvent property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pieceEvent property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPieceEvent().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PieceEvent }
     */
    public List<PieceEvent> getPieceEvent() {
        if (pieceEvent == null) {
            pieceEvent = new ArrayList<PieceEvent>();
        }
        return this.pieceEvent;
    }

}
