//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 09:41:18 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.tracking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PieceDetails complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PieceDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AWBNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicensePlate" type="{http://www.dhl.com/datatypes}TrackingPieceID"/>
 *         &lt;element name="PieceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualDepth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualWidth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualHeight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualWeight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageType" type="{http://www.dhl.com/datatypes}PackageType" minOccurs="0"/>
 *         &lt;element name="DimWeight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WeightUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PieceContents" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PieceDetails", propOrder = {
        "awbNumber",
        "licensePlate",
        "pieceNumber",
        "actualDepth",
        "actualWidth",
        "actualHeight",
        "actualWeight",
        "depth",
        "width",
        "height",
        "weight",
        "packageType",
        "dimWeight",
        "weightUnit",
        "pieceContents"
})
public class PieceDetails {

    @XmlElement(name = "AWBNumber", required = true)
    protected String awbNumber;
    @XmlElement(name = "LicensePlate", required = true)
    protected String licensePlate;
    @XmlElement(name = "PieceNumber")
    protected String pieceNumber;
    @XmlElement(name = "ActualDepth")
    protected String actualDepth;
    @XmlElement(name = "ActualWidth")
    protected String actualWidth;
    @XmlElement(name = "ActualHeight")
    protected String actualHeight;
    @XmlElement(name = "ActualWeight")
    protected String actualWeight;
    @XmlElement(name = "Depth")
    protected String depth;
    @XmlElement(name = "Width")
    protected String width;
    @XmlElement(name = "Height")
    protected String height;
    @XmlElement(name = "Weight")
    protected String weight;
    @XmlElement(name = "PackageType")
    protected PackageType packageType;
    @XmlElement(name = "DimWeight")
    protected String dimWeight;
    @XmlElement(name = "WeightUnit")
    protected String weightUnit;
    @XmlElement(name = "PieceContents")
    protected String pieceContents;

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
     * Gets the value of the licensePlate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets the value of the licensePlate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLicensePlate(String value) {
        this.licensePlate = value;
    }

    /**
     * Gets the value of the pieceNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPieceNumber() {
        return pieceNumber;
    }

    /**
     * Sets the value of the pieceNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPieceNumber(String value) {
        this.pieceNumber = value;
    }

    /**
     * Gets the value of the actualDepth property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getActualDepth() {
        return actualDepth;
    }

    /**
     * Sets the value of the actualDepth property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActualDepth(String value) {
        this.actualDepth = value;
    }

    /**
     * Gets the value of the actualWidth property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getActualWidth() {
        return actualWidth;
    }

    /**
     * Sets the value of the actualWidth property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActualWidth(String value) {
        this.actualWidth = value;
    }

    /**
     * Gets the value of the actualHeight property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getActualHeight() {
        return actualHeight;
    }

    /**
     * Sets the value of the actualHeight property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActualHeight(String value) {
        this.actualHeight = value;
    }

    /**
     * Gets the value of the actualWeight property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getActualWeight() {
        return actualWeight;
    }

    /**
     * Sets the value of the actualWeight property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActualWeight(String value) {
        this.actualWeight = value;
    }

    /**
     * Gets the value of the depth property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepth(String value) {
        this.depth = value;
    }

    /**
     * Gets the value of the width property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWidth(String value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHeight(String value) {
        this.height = value;
    }

    /**
     * Gets the value of the weight property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWeight(String value) {
        this.weight = value;
    }

    /**
     * Gets the value of the packageType property.
     *
     * @return possible object is
     * {@link PackageType }
     */
    public PackageType getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     *
     * @param value allowed object is
     *              {@link PackageType }
     */
    public void setPackageType(PackageType value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the dimWeight property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDimWeight() {
        return dimWeight;
    }

    /**
     * Sets the value of the dimWeight property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDimWeight(String value) {
        this.dimWeight = value;
    }

    /**
     * Gets the value of the weightUnit property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * Sets the value of the weightUnit property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWeightUnit(String value) {
        this.weightUnit = value;
    }

    /**
     * Gets the value of the pieceContents property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPieceContents() {
        return pieceContents;
    }

    /**
     * Sets the value of the pieceContents property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPieceContents(String value) {
        this.pieceContents = value;
    }

}
