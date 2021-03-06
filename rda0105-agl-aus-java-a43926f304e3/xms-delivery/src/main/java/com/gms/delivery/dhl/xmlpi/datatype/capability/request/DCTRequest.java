//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.14 at 02:49:39 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.capability.request;

import javax.xml.bind.annotation.*;


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
 *         &lt;choice>
 *           &lt;element name="GetQuote">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="Request" type="{http://www.dhl.com/datatypes}Request"/>
 *                     &lt;element name="From" type="{http://www.dhl.com/DCTRequestdatatypes}DCTFrom"/>
 *                     &lt;element name="BkgDetails" type="{http://www.dhl.com/DCTRequestdatatypes}BkgDetailsType"/>
 *                     &lt;element name="To" type="{http://www.dhl.com/DCTRequestdatatypes}DCTTo"/>
 *                     &lt;element name="Dutiable" type="{http://www.dhl.com/DCTRequestdatatypes}DCTDutiable" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="GetCapability">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="Request" type="{http://www.dhl.com/datatypes}Request"/>
 *                     &lt;element name="From" type="{http://www.dhl.com/DCTRequestdatatypes}DCTFrom"/>
 *                     &lt;element name="BkgDetails" type="{http://www.dhl.com/DCTRequestdatatypes}BkgDetailsType"/>
 *                     &lt;element name="To" type="{http://www.dhl.com/DCTRequestdatatypes}DCTTo"/>
 *                     &lt;element name="Dutiable" type="{http://www.dhl.com/DCTRequestdatatypes}DCTDutiable" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getQuote",
        "getCapability"
})
@XmlRootElement(name = "DCTRequest", namespace = "http://www.dhl.com")
public class DCTRequest {

    @XmlElement(name = "GetQuote")
    protected DCTRequest.GetQuote getQuote;
    @XmlElement(name = "GetCapability")
    protected DCTRequest.GetCapability getCapability;

    /**
     * Gets the value of the getQuote property.
     *
     * @return possible object is
     * {@link DCTRequest.GetQuote }
     */
    public DCTRequest.GetQuote getGetQuote() {
        return getQuote;
    }

    /**
     * Sets the value of the getQuote property.
     *
     * @param value allowed object is
     *              {@link DCTRequest.GetQuote }
     */
    public void setGetQuote(DCTRequest.GetQuote value) {
        this.getQuote = value;
    }

    /**
     * Gets the value of the getCapability property.
     *
     * @return possible object is
     * {@link DCTRequest.GetCapability }
     */
    public DCTRequest.GetCapability getGetCapability() {
        return getCapability;
    }

    /**
     * Sets the value of the getCapability property.
     *
     * @param value allowed object is
     *              {@link DCTRequest.GetCapability }
     */
    public void setGetCapability(DCTRequest.GetCapability value) {
        this.getCapability = value;
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
     *         &lt;element name="Request" type="{http://www.dhl.com/datatypes}Request"/>
     *         &lt;element name="From" type="{http://www.dhl.com/DCTRequestdatatypes}DCTFrom"/>
     *         &lt;element name="BkgDetails" type="{http://www.dhl.com/DCTRequestdatatypes}BkgDetailsType"/>
     *         &lt;element name="To" type="{http://www.dhl.com/DCTRequestdatatypes}DCTTo"/>
     *         &lt;element name="Dutiable" type="{http://www.dhl.com/DCTRequestdatatypes}DCTDutiable" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "request",
            "from",
            "bkgDetails",
            "to",
            "dutiable"
    })
    public static class GetCapability {

        @XmlElement(name = "Request", required = true)
        protected Request request;
        @XmlElement(name = "From", required = true)
        protected DCTFrom from;
        @XmlElement(name = "BkgDetails", required = true)
        protected BkgDetailsType bkgDetails;
        @XmlElement(name = "To", required = true)
        protected DCTTo to;
        @XmlElement(name = "Dutiable")
        protected DCTDutiable dutiable;

        /**
         * Gets the value of the request property.
         *
         * @return possible object is
         * {@link Request }
         */
        public Request getRequest() {
            return request;
        }

        /**
         * Sets the value of the request property.
         *
         * @param value allowed object is
         *              {@link Request }
         */
        public void setRequest(Request value) {
            this.request = value;
        }

        /**
         * Gets the value of the from property.
         *
         * @return possible object is
         * {@link DCTFrom }
         */
        public DCTFrom getFrom() {
            return from;
        }

        /**
         * Sets the value of the from property.
         *
         * @param value allowed object is
         *              {@link DCTFrom }
         */
        public void setFrom(DCTFrom value) {
            this.from = value;
        }

        /**
         * Gets the value of the bkgDetails property.
         *
         * @return possible object is
         * {@link BkgDetailsType }
         */
        public BkgDetailsType getBkgDetails() {
            return bkgDetails;
        }

        /**
         * Sets the value of the bkgDetails property.
         *
         * @param value allowed object is
         *              {@link BkgDetailsType }
         */
        public void setBkgDetails(BkgDetailsType value) {
            this.bkgDetails = value;
        }

        /**
         * Gets the value of the to property.
         *
         * @return possible object is
         * {@link DCTTo }
         */
        public DCTTo getTo() {
            return to;
        }

        /**
         * Sets the value of the to property.
         *
         * @param value allowed object is
         *              {@link DCTTo }
         */
        public void setTo(DCTTo value) {
            this.to = value;
        }

        /**
         * Gets the value of the dutiable property.
         *
         * @return possible object is
         * {@link DCTDutiable }
         */
        public DCTDutiable getDutiable() {
            return dutiable;
        }

        /**
         * Sets the value of the dutiable property.
         *
         * @param value allowed object is
         *              {@link DCTDutiable }
         */
        public void setDutiable(DCTDutiable value) {
            this.dutiable = value;
        }

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
     *         &lt;element name="Request" type="{http://www.dhl.com/datatypes}Request"/>
     *         &lt;element name="From" type="{http://www.dhl.com/DCTRequestdatatypes}DCTFrom"/>
     *         &lt;element name="BkgDetails" type="{http://www.dhl.com/DCTRequestdatatypes}BkgDetailsType"/>
     *         &lt;element name="To" type="{http://www.dhl.com/DCTRequestdatatypes}DCTTo"/>
     *         &lt;element name="Dutiable" type="{http://www.dhl.com/DCTRequestdatatypes}DCTDutiable" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "request",
            "from",
            "bkgDetails",
            "to",
            "dutiable"
    })
    public static class GetQuote {

        @XmlElement(name = "Request", required = true)
        protected Request request;
        @XmlElement(name = "From", required = true)
        protected DCTFrom from;
        @XmlElement(name = "BkgDetails", required = true)
        protected BkgDetailsType bkgDetails;
        @XmlElement(name = "To", required = true)
        protected DCTTo to;
        @XmlElement(name = "Dutiable")
        protected DCTDutiable dutiable;

        /**
         * Gets the value of the request property.
         *
         * @return possible object is
         * {@link Request }
         */
        public Request getRequest() {
            return request;
        }

        /**
         * Sets the value of the request property.
         *
         * @param value allowed object is
         *              {@link Request }
         */
        public void setRequest(Request value) {
            this.request = value;
        }

        /**
         * Gets the value of the from property.
         *
         * @return possible object is
         * {@link DCTFrom }
         */
        public DCTFrom getFrom() {
            return from;
        }

        /**
         * Sets the value of the from property.
         *
         * @param value allowed object is
         *              {@link DCTFrom }
         */
        public void setFrom(DCTFrom value) {
            this.from = value;
        }

        /**
         * Gets the value of the bkgDetails property.
         *
         * @return possible object is
         * {@link BkgDetailsType }
         */
        public BkgDetailsType getBkgDetails() {
            return bkgDetails;
        }

        /**
         * Sets the value of the bkgDetails property.
         *
         * @param value allowed object is
         *              {@link BkgDetailsType }
         */
        public void setBkgDetails(BkgDetailsType value) {
            this.bkgDetails = value;
        }

        /**
         * Gets the value of the to property.
         *
         * @return possible object is
         * {@link DCTTo }
         */
        public DCTTo getTo() {
            return to;
        }

        /**
         * Sets the value of the to property.
         *
         * @param value allowed object is
         *              {@link DCTTo }
         */
        public void setTo(DCTTo value) {
            this.to = value;
        }

        /**
         * Gets the value of the dutiable property.
         *
         * @return possible object is
         * {@link DCTDutiable }
         */
        public DCTDutiable getDutiable() {
            return dutiable;
        }

        /**
         * Sets the value of the dutiable property.
         *
         * @param value allowed object is
         *              {@link DCTDutiable }
         */
        public void setDutiable(DCTDutiable value) {
            this.dutiable = value;
        }

    }

}
