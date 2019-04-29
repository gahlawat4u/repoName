//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 10:24:43 AM ICT 
//


package com.gms.delivery.tnt.xmlpi.label.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.gms.delivery.tnt.xmlpi.label.response package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NameAndAddressResponseTypeAddressLine3_QNAME = new QName("", "addressLine3");
    private final static QName _NameAndAddressResponseTypeAddressLine2_QNAME = new QName("", "addressLine2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gms.delivery.tnt.xmlpi.label.response
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LabelResponse }
     */
    public LabelResponse createLabelResponse() {
        return new LabelResponse();
    }

    /**
     * Create an instance of {@link ConsignmentResponseType }
     */
    public ConsignmentResponseType createConsignmentResponseType() {
        return new ConsignmentResponseType();
    }

    /**
     * Create an instance of {@link BrokenRules }
     */
    public BrokenRules createBrokenRules() {
        return new BrokenRules();
    }

    /**
     * Create an instance of {@link Fault }
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link TransitDepotListType }
     */
    public TransitDepotListType createTransitDepotListType() {
        return new TransitDepotListType();
    }

    /**
     * Create an instance of {@link RenderInstructionsType }
     */
    public RenderInstructionsType createRenderInstructionsType() {
        return new RenderInstructionsType();
    }

    /**
     * Create an instance of {@link DestinationDepotType }
     */
    public DestinationDepotType createDestinationDepotType() {
        return new DestinationDepotType();
    }

    /**
     * Create an instance of {@link NameAndAddressResponseType }
     */
    public NameAndAddressResponseType createNameAndAddressResponseType() {
        return new NameAndAddressResponseType();
    }

    /**
     * Create an instance of {@link SortDepotType }
     */
    public SortDepotType createSortDepotType() {
        return new SortDepotType();
    }

    /**
     * Create an instance of {@link ProductDescriptionType }
     */
    public ProductDescriptionType createProductDescriptionType() {
        return new ProductDescriptionType();
    }

    /**
     * Create an instance of {@link ActionDepotType }
     */
    public ActionDepotType createActionDepotType() {
        return new ActionDepotType();
    }

    /**
     * Create an instance of {@link ConsignmentRoutingLabelType }
     */
    public ConsignmentRoutingLabelType createConsignmentRoutingLabelType() {
        return new ConsignmentRoutingLabelType();
    }

    /**
     * Create an instance of {@link OptionDescriptionType }
     */
    public OptionDescriptionType createOptionDescriptionType() {
        return new OptionDescriptionType();
    }

    /**
     * Create an instance of {@link DepotType }
     */
    public DepotType createDepotType() {
        return new DepotType();
    }

    /**
     * Create an instance of {@link PieceRoutingLabelType }
     */
    public PieceRoutingLabelType createPieceRoutingLabelType() {
        return new PieceRoutingLabelType();
    }

    /**
     * Create an instance of {@link AccountType }
     */
    public AccountType createAccountType() {
        return new AccountType();
    }

    /**
     * Create an instance of {@link ContactType }
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link BarcodeType }
     */
    public BarcodeType createBarcodeType() {
        return new BarcodeType();
    }

    /**
     * Create an instance of {@link TwoDBarcodeType }
     */
    public TwoDBarcodeType createTwoDBarcodeType() {
        return new TwoDBarcodeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "addressLine3", scope = NameAndAddressResponseType.class)
    public JAXBElement<String> createNameAndAddressResponseTypeAddressLine3(String value) {
        return new JAXBElement<String>(_NameAndAddressResponseTypeAddressLine3_QNAME, String.class, NameAndAddressResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "addressLine2", scope = NameAndAddressResponseType.class)
    public JAXBElement<String> createNameAndAddressResponseTypeAddressLine2(String value) {
        return new JAXBElement<String>(_NameAndAddressResponseTypeAddressLine2_QNAME, String.class, NameAndAddressResponseType.class, value);
    }

}
