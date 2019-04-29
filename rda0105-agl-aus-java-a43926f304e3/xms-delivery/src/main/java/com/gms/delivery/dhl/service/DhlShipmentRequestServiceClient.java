package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.ShipmentValidateErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.request.*;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.response.ShipmentRequestResult;
import com.gms.delivery.dhl.xmlpi.datatype.shipment.response.ShipmentResponse;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.persistence.service.billingtype.BillingTypeServiceImp;
import com.gms.xms.persistence.service.billingtype.IBillingTypeService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class DhlShipmentRequestServiceClient {
    public String executeGetPickup(String xml, String xmlResponse) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xml, "/dhl_shipment_request_" + uniqueString + ".xml");
        PostMethod post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("DHL URL"));
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xml)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String shipmentResponseXML;
            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    shipmentResponseXML = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(shipmentResponseXML, "/dhl_shipment_response_" + uniqueString + ".xml");
                } else {
                    return null;
                }
            } else {
                shipmentResponseXML = xmlResponse;
            }
            return shipmentResponseXML;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public String parseShipmentXMLRequest(ShipmentRequestVo shipmentRequestVo, DhlCapabilityVo dhlCapabilityVo) throws Exception {
        ShipmentInfoVo shipmentInfoVo = shipmentRequestVo.getShipmentInfo();
        ShipmentRequest shipmentRequest = new ShipmentRequest();
        ContentDetailVo contentDetailVo = shipmentRequestVo.getContentDetail();
        shipmentRequest.setSchemaVersion(new BigDecimal("1.0").setScale(1));
        com.gms.delivery.dhl.xmlpi.datatype.shipment.request.Request request = new com.gms.delivery.dhl.xmlpi.datatype.shipment.request.Request();
        com.gms.delivery.dhl.xmlpi.datatype.shipment.request.ServiceHeader serviceHeader = new com.gms.delivery.dhl.xmlpi.datatype.shipment.request.ServiceHeader();
        Billing billing = new Billing();
        Consignee consignee = new Consignee();
        Commodity commodity = new Commodity();
        Dutiable dutiable = new Dutiable();
        Reference reference = new Reference();
        ShipmentDetails shipmentDetails = new ShipmentDetails();
        com.gms.delivery.dhl.xmlpi.datatype.shipment.request.Pieces pieces = new com.gms.delivery.dhl.xmlpi.datatype.shipment.request.Pieces();
        Shipper shipper = new Shipper();
        AddressVo receiverAddress = shipmentInfoVo.getReceiverAddress();
        AddressVo senderAddress = shipmentInfoVo.getSenderAddress();
        // Service header
        serviceHeader.setMessageTime(DateUtils.convertDate2XMLGregorianCalendar(new Date()));
        String messageid = String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime());
        messageid = messageid.substring(0, 30);
        serviceHeader.setMessageReference(messageid);
        serviceHeader.setSiteID(SystemSettingCache.getInstance().getValueByKey("Site ID"));
        serviceHeader.setPassword(SystemSettingCache.getInstance().getValueByKey("DHL Password"));
        request.setServiceHeader(serviceHeader);
        ICountryService countryService = new CountryServiceImp();
        IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
        CountryVo senderCountry = countryService.getCountryByCountryId(senderAddress.getCountry());
        DhlCountryVo senderDhlCountry = dhlCountryService.getDhlCountryByCountryName(senderCountry.getCountryName());
        if (senderDhlCountry.getDhlRegion().equals("EA")) {
            shipmentRequest.setRegionCode(RegionCode.EU);
        } else {
            shipmentRequest.setRegionCode(RegionCode.fromValue(senderDhlCountry.getDhlRegion()));
        }
        shipmentRequest.setRequest(request);
        shipmentRequest.setLanguageCode("en");
        shipmentRequest.setPiecesEnabled(PiecesEnabled.Y);
        shipmentRequest.setRequestedPickupTime(YesNo.N);

        // Billing
        String billingAccount = StringUtils.isBlank(shipmentInfoVo.getBillingAccount()) ? shipmentInfoVo.getShipperAccount() : shipmentInfoVo.getBillingAccount();
        // "963374484"
        IBillingTypeService billingTypeService = new BillingTypeServiceImp();
        Integer billingType = shipmentInfoVo.getBillingType() != null ? shipmentInfoVo.getBillingType() : 1;
        BillingTypeVo billingTypeVo = billingTypeService.getBillingTypeById(billingType);
        String billingName = billingTypeVo.getBillingName().toUpperCase().substring(0, 1);
        billing.setShipperAccountNumber(billingAccount);
        billing.setShippingPaymentType(ShipmentPaymentType.fromValue(billingName));
        billing.setBillingAccountNumber(billingAccount);
        if (!shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
            Integer dutiesType = shipmentInfoVo.getDutiesType() != null ? shipmentInfoVo.getDutiesType() : 2;
            billingTypeVo = billingTypeService.getBillingTypeById(dutiesType);
            billingName = billingTypeVo.getBillingName().toUpperCase().substring(0, 1);
            billing.setDutyPaymentType(DutyTaxPaymentType.fromValue(billingName));
            billing.setDutyAccountNumber(shipmentInfoVo.getDutiesAccount());
        }
        shipmentRequest.setBilling(billing);

        // Consignee
        CountryVo receiverCountryVo = countryService.getCountryByCountryId(receiverAddress.getCountry());
        consignee.setCompanyName(receiverAddress.getCompanyName());
        consignee.getAddressLine().add(receiverAddress.getAddress());
        consignee.getAddressLine().add(receiverAddress.getAddress2());
        consignee.setCity(receiverAddress.getCity());
        consignee.setPostalCode(receiverAddress.getPostalCode());
        consignee.setCountryCode(receiverCountryVo.getCountryCode());
        consignee.setCountryName(receiverCountryVo.getCountryName());
        // Contact
        Contact consigneeContact = new Contact();
        consigneeContact.setPersonName(receiverAddress.getContactName());
        consigneeContact.setPhoneNumber(receiverAddress.getPhone());
        consignee.setContact(consigneeContact);
        shipmentRequest.setConsignee(consignee);

        // commodity
        commodity.setCommodityCode("1");
        commodity.setCommodityName("String");
        shipmentRequest.getCommodity().add(commodity);
        // Dutiable
        dutiable.setDeclaredValue(shipmentInfoVo.getTotalCustomValue().setScale(2, RoundingMode.HALF_UP));
        dutiable.setDeclaredCurrency(shipmentInfoVo.getCurrencyCode());
        dutiable.setShipperEIN("");
        if (!shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
            if (billingName.equalsIgnoreCase("S")) {
                dutiable.setTermsOfTrade(TermsOfTrade.DTP);
            } else {
                dutiable.setTermsOfTrade(TermsOfTrade.DTU);
            }
        }
        shipmentRequest.setDutiable(dutiable);

        // Reference
        reference.setReferenceID(shipmentRequestVo.getShipmentReference());
        reference.setReferenceType("st");
        shipmentRequest.getReference().add(reference);

        // Shipment Details
        String globalProductCode = dhlCapabilityVo.getGlobalProductCode();
        String localProductCode = dhlCapabilityVo.getLocalProductCode();

        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfoVo.getServiceAddConMap();
        shipmentDetails.setWeight(new BigDecimal(shipmentInfoVo.getTotalWeight()).setScale(3));
        shipmentDetails.setWeightUnit(WeightUnit.K);
        shipmentDetails.setGlobalProductCode(globalProductCode);
        shipmentDetails.setLocalProductCode(localProductCode);
        shipmentDetails.setDoorTo(DoorTo.DD);
        shipmentDetails.setIsDutiable(YesNo.Y);
        if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equalsIgnoreCase("1")) {
            shipmentDetails.setInsuredAmount(shipmentInfoVo.getTotalCustomValue().setScale(2).floatValue());
        } else {
            shipmentDetails.setInsuredAmount(0F);
        }
        shipmentDetails.setDate(DateUtils.convertDateToString(shipmentInfoVo.getShipmentDate(), "yyyy-MM-dd", null));
        Integer totalPieces = 0;
        for (PieceVo pieceVo : shipmentInfoVo.getShipmentRequestPieces()) {
            Piece piece = new Piece();
            for (int i = 0; i < pieceVo.getQuantity(); i++) {
                Double weight = pieceVo.getWeight();
                if (weight == 0) {
                    weight = 0.5D;
                }
                piece.setWeight(new BigDecimal(pieceVo.getWeight()).setScale(2, RoundingMode.HALF_UP));
                BigInteger dimH = pieceVo.getDimensionH() != null && pieceVo.getDimensionH() != 0 ? new BigDecimal(pieceVo.getDimensionH()).setScale(2).toBigInteger() : BigInteger.ONE;
                BigInteger dimW = pieceVo.getDimensionW() != null && pieceVo.getDimensionW() != 0 ? new BigDecimal(pieceVo.getDimensionW()).setScale(2).toBigInteger() : BigInteger.ONE;
                BigInteger dimL = pieceVo.getDimensionL() != null && pieceVo.getDimensionL() != 0 ? new BigDecimal(pieceVo.getDimensionL()).setScale(2).toBigInteger() : BigInteger.ONE;
                piece.setWidth(dimW);
                piece.setHeight(dimH);
                piece.setDepth(dimL);
                pieces.getPiece().add(piece);
                totalPieces++;
            }
        }
        shipmentDetails.setNumberOfPieces(totalPieces);
        shipmentDetails.setContents(contentDetailVo.getDescription());
        shipmentDetails.setPieces(pieces);
        shipmentDetails.setDimensionUnit(DimensionUnit.C);
        shipmentDetails.setCurrencyCode(shipmentInfoVo.getCurrencyCode());
        shipmentRequest.setShipmentDetails(shipmentDetails);
        shipmentRequest.setLabelImageFormat(LabelImageFormat.PDF);
        // Shipper
        shipper.setShipperID(billingAccount);
        shipper.setCompanyName(senderAddress.getCompanyName());
        shipper.getAddressLine().add(senderAddress.getAddress());
        shipper.getAddressLine().add(senderAddress.getAddress2());
        shipper.setCity(senderAddress.getCity());
        shipper.setPostalCode(senderAddress.getPostalCode());
        Contact shipperContact = new Contact();
        shipperContact.setPersonName(senderAddress.getContactName());
        shipperContact.setPhoneNumber(senderAddress.getPhone());
        shipper.setContact(shipperContact);
        CountryVo senderCountryVo = countryService.getCountryByCountryId(senderAddress.getCountry());
        shipper.setCountryCode(senderCountryVo.getCountryCode());
        shipper.setCountryName(senderCountryVo.getCountryName());
        shipmentRequest.setShipper(shipper);

        return AppUtils.Object2XmlString(shipmentRequest, ShipmentRequest.class);
    }

    public ShipmentRequestResult parseResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        ShipmentRequestResult shipmentRequestResult = new ShipmentRequestResult();
        ShipmentResponse shipmentResponse = null;
        ShipmentValidateErrorResponse shipmentValidateErrorResponse = null;
        DhlErrorResponse errorResponse = null;
        if (xmlResponse.contains("ShipmentValidateErrorResponse")) {
            shipmentValidateErrorResponse = AppUtils.xmlString2Object(xmlResponse, ShipmentValidateErrorResponse.class);
        } else if (xmlResponse.contains("ErrorResponse")) {
            errorResponse = AppUtils.xmlString2Object(xmlResponse, DhlErrorResponse.class);
        } else {
            shipmentResponse = AppUtils.xmlString2Object(xmlResponse, ShipmentResponse.class);
        }
        shipmentRequestResult.setShipmentResponse(shipmentResponse);
        shipmentRequestResult.setErrorResponse(errorResponse);
        shipmentRequestResult.setShipmentValidateErrorResponse(shipmentValidateErrorResponse);
        return shipmentRequestResult;
    }
}
