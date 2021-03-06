//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:49:39 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for PieceType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PieceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PieceID">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PackageTypeCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *               &lt;enumeration value="FLY"/>
 *               &lt;enumeration value="COY"/>
 *               &lt;enumeration value="NCY"/>
 *               &lt;enumeration value="PAL"/>
 *               &lt;enumeration value="DBL"/>
 *               &lt;enumeration value="BOX"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Height" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="10"/>
 *               &lt;fractionDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Depth" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="10"/>
 *               &lt;fractionDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Width" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="10"/>
 *               &lt;fractionDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Weight">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PieceType", namespace = "http://www.dhl.com/DCTRequestdatatypes", propOrder = {
        "pieceID",
        "packageTypeCode",
        "height",
        "depth",
        "width",
        "weight"
})
public class PieceType {

    @XmlElement(name = "PieceID", required = true)
    protected String pieceID;
    @XmlElement(name = "PackageTypeCode", defaultValue = "BOX")
    protected String packageTypeCode;
    @XmlElement(name = "Height")
    protected BigDecimal height;
    @XmlElement(name = "Depth")
    protected BigDecimal depth;
    @XmlElement(name = "Width")
    protected BigDecimal width;
    @XmlElement(name = "Weight", required = true)
    protected BigDecimal weight;

    /**
     * Gets the value of the pieceID property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPieceID() {
        return pieceID;
    }

    /**
     * Sets the value of the pieceID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPieceID(String value) {
        this.pieceID = value;
    }

    /**
     * Gets the value of the packageTypeCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    /**
     * Sets the value of the packageTypeCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPackageTypeCode(String value) {
        this.packageTypeCode = value;
    }

    /**
     * Gets the value of the height property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setHeight(BigDecimal value) {
        this.height = value;
    }

    /**
     * Gets the value of the depth property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setDepth(BigDecimal value) {
        this.depth = value;
    }

    /**
     * Gets the value of the width property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setWidth(BigDecimal value) {
        this.width = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

}
