//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.15 at 04:10:15 PM ICT 
//


package com.gms.delivery.toll.xmlpi.pickup.request;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.gms.delivery.toll.xmlpi.pickup.request package.
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gms.delivery.toll.xmlpi.pickup.request
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PickupType }
     */
    public PickupType createPickupType() {
        return new PickupType();
    }

    /**
     * Create an instance of {@link PickupType.PickupDetail }
     */
    public PickupType.PickupDetail createPickupTypePickupDetail() {
        return new PickupType.PickupDetail();
    }

    /**
     * Create an instance of {@link TollPickupRequest }
     */
    public TollPickupRequest createTollPickupRequest() {
        return new TollPickupRequest();
    }

    /**
     * Create an instance of {@link HeaderType }
     */
    public HeaderType createHeaderType() {
        return new HeaderType();
    }

    /**
     * Create an instance of {@link InitiatorType }
     */
    public InitiatorType createInitiatorType() {
        return new InitiatorType();
    }

    /**
     * Create an instance of {@link SenderType }
     */
    public SenderType createSenderType() {
        return new SenderType();
    }

    /**
     * Create an instance of {@link ReceiverType }
     */
    public ReceiverType createReceiverType() {
        return new ReceiverType();
    }

    /**
     * Create an instance of {@link PickupType.ConfirmationDetail }
     */
    public PickupType.ConfirmationDetail createPickupTypeConfirmationDetail() {
        return new PickupType.ConfirmationDetail();
    }

    /**
     * Create an instance of {@link PickupType.AccountDetail }
     */
    public PickupType.AccountDetail createPickupTypeAccountDetail() {
        return new PickupType.AccountDetail();
    }

    /**
     * Create an instance of {@link PickupType.Items }
     */
    public PickupType.Items createPickupTypeItems() {
        return new PickupType.Items();
    }

    /**
     * Create an instance of {@link PickupType.PickupDetail.Reference }
     */
    public PickupType.PickupDetail.Reference createPickupTypePickupDetailReference() {
        return new PickupType.PickupDetail.Reference();
    }

}
