//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.28 at 05:13:39 PM ICT 
//


package com.gms.delivery.tnt.xmlpi.tracking.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.math.BigInteger;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.gms.delivery.tnt.xmlpi.tracking.request package.
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

    private final static QName _Period_QNAME = new QName("", "Period");
    private final static QName _Account_QNAME = new QName("", "Account");
    private final static QName _NumberOfDays_QNAME = new QName("", "NumberOfDays");
    private final static QName _TrackRequestSearchCriteriaConsignmentNumber_QNAME = new QName("", "ConsignmentNumber");
    private final static QName _TrackRequestSearchCriteriaAlternativeConsignmentNumber_QNAME = new QName("", "AlternativeConsignmentNumber");
    private final static QName _TrackRequestSearchCriteriaCustomerReference_QNAME = new QName("", "CustomerReference");
    private final static QName _TrackRequestSearchCriteriaPieceReference_QNAME = new QName("", "PieceReference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gms.delivery.tnt.xmlpi.tracking.request
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TrackRequest }
     */
    public TrackRequest createTrackRequest() {
        return new TrackRequest();
    }

    /**
     * Create an instance of {@link TrackRequest.LevelOfDetail }
     */
    public TrackRequest.LevelOfDetail createTrackRequestLevelOfDetail() {
        return new TrackRequest.LevelOfDetail();
    }

    /**
     * Create an instance of {@link TimeFrameStructure }
     */
    public TimeFrameStructure createTimeFrameStructure() {
        return new TimeFrameStructure();
    }

    /**
     * Create an instance of {@link TrackRequest.SearchCriteria }
     */
    public TrackRequest.SearchCriteria createTrackRequestSearchCriteria() {
        return new TrackRequest.SearchCriteria();
    }

    /**
     * Create an instance of {@link AccountStructure }
     */
    public AccountStructure createAccountStructure() {
        return new AccountStructure();
    }

    /**
     * Create an instance of {@link DateType }
     */
    public DateType createDateType() {
        return new DateType();
    }

    /**
     * Create an instance of {@link TrackRequest.LevelOfDetail.Complete }
     */
    public TrackRequest.LevelOfDetail.Complete createTrackRequestLevelOfDetailComplete() {
        return new TrackRequest.LevelOfDetail.Complete();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeFrameStructure }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "Period")
    public JAXBElement<TimeFrameStructure> createPeriod(TimeFrameStructure value) {
        return new JAXBElement<TimeFrameStructure>(_Period_QNAME, TimeFrameStructure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountStructure }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "Account")
    public JAXBElement<AccountStructure> createAccount(AccountStructure value) {
        return new JAXBElement<AccountStructure>(_Account_QNAME, AccountStructure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "NumberOfDays")
    public JAXBElement<BigInteger> createNumberOfDays(BigInteger value) {
        return new JAXBElement<BigInteger>(_NumberOfDays_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "ConsignmentNumber", scope = TrackRequest.SearchCriteria.class)
    public JAXBElement<String> createTrackRequestSearchCriteriaConsignmentNumber(String value) {
        return new JAXBElement<String>(_TrackRequestSearchCriteriaConsignmentNumber_QNAME, String.class, TrackRequest.SearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "AlternativeConsignmentNumber", scope = TrackRequest.SearchCriteria.class)
    public JAXBElement<String> createTrackRequestSearchCriteriaAlternativeConsignmentNumber(String value) {
        return new JAXBElement<String>(_TrackRequestSearchCriteriaAlternativeConsignmentNumber_QNAME, String.class, TrackRequest.SearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CustomerReference", scope = TrackRequest.SearchCriteria.class)
    public JAXBElement<String> createTrackRequestSearchCriteriaCustomerReference(String value) {
        return new JAXBElement<String>(_TrackRequestSearchCriteriaCustomerReference_QNAME, String.class, TrackRequest.SearchCriteria.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "PieceReference", scope = TrackRequest.SearchCriteria.class)
    public JAXBElement<String> createTrackRequestSearchCriteriaPieceReference(String value) {
        return new JAXBElement<String>(_TrackRequestSearchCriteriaPieceReference_QNAME, String.class, TrackRequest.SearchCriteria.class, value);
    }

}
