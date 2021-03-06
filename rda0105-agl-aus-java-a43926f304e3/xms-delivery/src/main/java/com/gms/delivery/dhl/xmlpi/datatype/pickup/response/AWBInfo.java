//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 07:55:03 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.pickup.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for AWBInfo complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="AWBInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AWBNumber" type="{http://www.dhl.com/datatypes_global}AWBNumber"/>
 *         &lt;element name="Status" type="{http://www.dhl.com/datatypes_global}Status"/>
 *         &lt;element name="ShipmentInfo" type="{http://www.dhl.com/datatypes_global}ShipmentInfo" minOccurs="0"/>
 *         &lt;element name="PieceInfo" type="{http://www.dhl.com/datatypes_global}PieceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AWBInfo", propOrder = {
        "awbNumber",
        "status",
        "shipmentInfo",
        "pieceInfo"
})
public class AWBInfo {

    @XmlElement(name = "AWBNumber", required = true)
    protected String awbNumber;
    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElement(name = "ShipmentInfo")
    protected ShipmentInfo shipmentInfo;
    @XmlElement(name = "PieceInfo")
    protected List<PieceInfo> pieceInfo;

    /**
     * Gets the value of the awbNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAWBNumber() {
        return awbNumber;
    }

    /**
     * Sets the value of the awbNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAWBNumber(String value) {
        this.awbNumber = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link Status }
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the shipmentInfo property.
     *
     * @return possible object is
     * {@link ShipmentInfo }
     */
    public ShipmentInfo getShipmentInfo() {
        return shipmentInfo;
    }

    /**
     * Sets the value of the shipmentInfo property.
     *
     * @param value allowed object is
     *              {@link ShipmentInfo }
     */
    public void setShipmentInfo(ShipmentInfo value) {
        this.shipmentInfo = value;
    }

    /**
     * Gets the value of the pieceInfo property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pieceInfo property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPieceInfo().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PieceInfo }
     */
    public List<PieceInfo> getPieceInfo() {
        if (pieceInfo == null) {
            pieceInfo = new ArrayList<PieceInfo>();
        }
        return this.pieceInfo;
    }

}
