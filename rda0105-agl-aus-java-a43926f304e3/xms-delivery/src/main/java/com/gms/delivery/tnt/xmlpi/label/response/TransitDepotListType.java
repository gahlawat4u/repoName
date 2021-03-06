//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 10:24:43 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.response;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for transitDepotListType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="transitDepotListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="transitDepot" type="{}depotType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actionDepot" type="{}actionDepotType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sortDepot" type="{}sortDepotType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transitDepotListType", propOrder = {
        "transitDepotOrActionDepotOrSortDepot"
})
public class TransitDepotListType {

    @XmlElements({
            @XmlElement(name = "transitDepot", type = DepotType.class),
            @XmlElement(name = "actionDepot", type = ActionDepotType.class),
            @XmlElement(name = "sortDepot", type = SortDepotType.class)
    })
    protected List<Object> transitDepotOrActionDepotOrSortDepot;

    /**
     * Gets the value of the transitDepotOrActionDepotOrSortDepot property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transitDepotOrActionDepotOrSortDepot property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransitDepotOrActionDepotOrSortDepot().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepotType }
     * {@link ActionDepotType }
     * {@link SortDepotType }
     */
    public List<Object> getTransitDepotOrActionDepotOrSortDepot() {
        if (transitDepotOrActionDepotOrSortDepot == null) {
            transitDepotOrActionDepotOrSortDepot = new ArrayList<Object>();
        }
        return this.transitDepotOrActionDepotOrSortDepot;
    }

}
