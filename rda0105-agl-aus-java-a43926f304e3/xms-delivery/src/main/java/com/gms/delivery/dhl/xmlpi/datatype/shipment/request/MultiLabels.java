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
 * <p>Java class for MultiLabels complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="MultiLabels">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultiLabel" maxOccurs="99">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="DocFormat" type="{http://www.dhl.com/datatypes_global}DocFormat"/>
 *                   &lt;element name="DocImage" type="{http://www.dhl.com/datatypes_global}DocImageVal"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiLabels", propOrder = {
        "multiLabel"
})
public class MultiLabels {

    @XmlElement(name = "MultiLabel", required = true)
    protected List<MultiLabels.MultiLabel> multiLabel;

    /**
     * Gets the value of the multiLabel property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the multiLabel property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMultiLabel().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiLabels.MultiLabel }
     */
    public List<MultiLabels.MultiLabel> getMultiLabel() {
        if (multiLabel == null) {
            multiLabel = new ArrayList<MultiLabels.MultiLabel>();
        }
        return this.multiLabel;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="DocName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="DocFormat" type="{http://www.dhl.com/datatypes_global}DocFormat"/>
     *         &lt;element name="DocImage" type="{http://www.dhl.com/datatypes_global}DocImageVal"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "docName",
            "docFormat",
            "docImage"
    })
    public static class MultiLabel {

        @XmlElement(name = "DocName", required = true)
        protected String docName;
        @XmlElement(name = "DocFormat", required = true)
        protected String docFormat;
        @XmlElement(name = "DocImage", required = true)
        protected byte[] docImage;

        /**
         * Gets the value of the docName property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDocName() {
            return docName;
        }

        /**
         * Sets the value of the docName property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDocName(String value) {
            this.docName = value;
        }

        /**
         * Gets the value of the docFormat property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDocFormat() {
            return docFormat;
        }

        /**
         * Sets the value of the docFormat property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDocFormat(String value) {
            this.docFormat = value;
        }

        /**
         * Gets the value of the docImage property.
         *
         * @return possible object is
         * byte[]
         */
        public byte[] getDocImage() {
            return docImage;
        }

        /**
         * Sets the value of the docImage property.
         *
         * @param value allowed object is
         *              byte[]
         */
        public void setDocImage(byte[] value) {
            this.docImage = value;
        }

    }

}
