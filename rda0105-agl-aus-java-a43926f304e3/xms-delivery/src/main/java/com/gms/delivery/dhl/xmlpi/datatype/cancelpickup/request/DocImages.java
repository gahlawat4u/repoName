//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.18 at 01:56:24 PM ICT 
//


package com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * DocImages
 * <p>
 * <p>Java class for DocImages complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="DocImages">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocImage" type="{http://www.dhl.com/datatypes_global}DocImage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocImages", propOrder = {
        "docImage"
})
public class DocImages {

    @XmlElement(name = "DocImage")
    protected List<DocImage> docImage;

    /**
     * Gets the value of the docImage property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docImage property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocImage().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocImage }
     */
    public List<DocImage> getDocImage() {
        if (docImage == null) {
            docImage = new ArrayList<DocImage>();
        }
        return this.docImage;
    }

}
