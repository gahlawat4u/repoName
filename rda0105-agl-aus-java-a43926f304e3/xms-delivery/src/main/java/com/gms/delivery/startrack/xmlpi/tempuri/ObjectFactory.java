//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.13 at 11:30:46 AM ICT 
//

package com.gms.delivery.startrack.xmlpi.tempuri;

import com.gms.delivery.startrack.xmlpi.aaecreatebooking.AaEBookingResult;
import com.gms.delivery.startrack.xmlpi.aaecreatebooking.AaEBookingType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the org.tempuri package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateBookingComposite_QNAME = new QName("http://tempuri.org/", "composite");
    private final static QName _CreateBookingResponseCreateBookingResult_QNAME = new QName("http://tempuri.org/", "CreateBookingResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateBooking }
     */
    public CreateBooking createCreateBooking() {
        return new CreateBooking();
    }

    /**
     * Create an instance of {@link CreateBookingResponse }
     */
    public CreateBookingResponse createCreateBookingResponse() {
        return new CreateBookingResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AaEBookingType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "composite", scope = CreateBooking.class)
    public JAXBElement<AaEBookingType> createCreateBookingComposite(AaEBookingType value) {
        return new JAXBElement<AaEBookingType>(_CreateBookingComposite_QNAME, AaEBookingType.class, CreateBooking.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AaEBookingResult }{@code >}}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateBookingResult", scope = CreateBookingResponse.class)
    public JAXBElement<AaEBookingResult> createCreateBookingResponseCreateBookingResult(AaEBookingResult value) {
        return new JAXBElement<AaEBookingResult>(_CreateBookingResponseCreateBookingResult_QNAME, AaEBookingResult.class, CreateBookingResponse.class, value);
    }

}