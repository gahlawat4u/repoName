//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 03:07:35 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BkgDetailsType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="BkgDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OriginServiceArea" type="{http://www.dhl.com/DCTResponsedatatypes}OrgnSvcAreaType"/>
 *         &lt;element name="DestinationServiceArea" type="{http://www.dhl.com/DCTResponsedatatypes}DestSvcAreaType"/>
 *         &lt;element name="QtdShp" type="{http://www.dhl.com/DCTResponsedatatypes}QtdShpType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CalcNextDayInd" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="1"/>
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
@XmlType(name = "BkgDetailsType", namespace = "http://www.dhl.com/DCTResponsedatatypes", propOrder = {
        "originServiceArea",
        "destinationServiceArea",
        "qtdShp",
        "calcNextDayInd"
})
public class BkgDetailsType {

    @XmlElement(name = "OriginServiceArea", required = true)
    protected OrgnSvcAreaType originServiceArea;
    @XmlElement(name = "DestinationServiceArea", required = true)
    protected DestSvcAreaType destinationServiceArea;
    @XmlElement(name = "QtdShp")
    protected List<QtdShpType> qtdShp;
    @XmlElement(name = "CalcNextDayInd")
    protected String calcNextDayInd;

    /**
     * Gets the value of the originServiceArea property.
     *
     * @return possible object is
     * {@link OrgnSvcAreaType }
     */
    public OrgnSvcAreaType getOriginServiceArea() {
        return originServiceArea;
    }

    /**
     * Sets the value of the originServiceArea property.
     *
     * @param value allowed object is
     *              {@link OrgnSvcAreaType }
     */
    public void setOriginServiceArea(OrgnSvcAreaType value) {
        this.originServiceArea = value;
    }

    /**
     * Gets the value of the destinationServiceArea property.
     *
     * @return possible object is
     * {@link DestSvcAreaType }
     */
    public DestSvcAreaType getDestinationServiceArea() {
        return destinationServiceArea;
    }

    /**
     * Sets the value of the destinationServiceArea property.
     *
     * @param value allowed object is
     *              {@link DestSvcAreaType }
     */
    public void setDestinationServiceArea(DestSvcAreaType value) {
        this.destinationServiceArea = value;
    }

    /**
     * Gets the value of the qtdShp property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qtdShp property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQtdShp().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QtdShpType }
     */
    public List<QtdShpType> getQtdShp() {
        if (qtdShp == null) {
            qtdShp = new ArrayList<QtdShpType>();
        }
        return this.qtdShp;
    }

    /**
     * Gets the value of the calcNextDayInd property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCalcNextDayInd() {
        return calcNextDayInd;
    }

    /**
     * Sets the value of the calcNextDayInd property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCalcNextDayInd(String value) {
        this.calcNextDayInd = value;
    }

}
