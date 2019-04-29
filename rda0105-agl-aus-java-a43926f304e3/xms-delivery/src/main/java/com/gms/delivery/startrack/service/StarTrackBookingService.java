package com.gms.delivery.startrack.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.startrack.xmlpi.booking.AaEConnoteType;
import com.gms.delivery.startrack.xmlpi.booking.ObjectFactory;
import com.gms.delivery.startrack.xmlpi.booking.tempuri.CreateConnoteBinary;
import com.gms.delivery.startrack.xmlpi.booking.tempuri.CreateConnoteBinaryResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.StarTrackConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.startrack.StartrackShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.*;

/**
 * Posted from Jun 15, 2016 4:22:22 PM
 * <p>
 * Author huynd
 */
public class StarTrackBookingService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/startrack_shipmentrequest_do_" + uniqueString + ".xml");
        PostMethod post = null;
        String xmlResponse;
        try {
            String apiUrl = SystemSettingCache.getInstance().getValueByKey("StarTrack connote url");
            if (StringUtils.isBlank(apiUrl)) {
                apiUrl = "https://extwebservices.startrack.com.au:3112/AaECreateEConnote/AaECreateEConnote.Service1.svc/extBasic";
            }
            post = new PostMethod(apiUrl);
            post.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
            post.setRequestHeader("SOAPAction", "http://tempuri.org/IService1/CreateConnoteBinary");
            post.setRequestHeader("Content-length", String.valueOf(xmlRequest.length()));
            post.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(xmlRequest.getBytes("UTF8"))));
            int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (result == 200 || result == 500) {
                xmlResponse = post.getResponseBodyAsString();
                AppUtils.writeToFileByDate(xmlResponse, "/startrack_shipmentresponse_response_" + uniqueString + ".xml");
            } else {
                xmlResponse = null;
            }
            return xmlResponse;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public CreateConnoteBinaryResponse parseResponse(String xmlResponse) throws Exception {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(CreateConnoteBinaryResponse.class).createUnmarshaller();
        CreateConnoteBinaryResponse connoteBinaryResponse = (CreateConnoteBinaryResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
        return connoteBinaryResponse;
    }

    public String getErrorMessage(String xmlResponse) throws Exception {
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        if (message == null || message.getSOAPBody() == null || message.getSOAPBody().getFault() == null) {
            return null;
        }
        SOAPFault fault = message.getSOAPBody().getFault();
        String errorMessage = "";
        errorMessage += fault.getFaultString();
        if (!StringUtils.isBlank(StarTrackConstants.STARTRACK_ECONNOTE_ERRORS.get(Integer.valueOf(fault.getFaultString())))) {
            errorMessage += " - " + StarTrackConstants.STARTRACK_ECONNOTE_ERRORS.get(Integer.valueOf(fault.getFaultString()));
        } else {
            errorMessage += " - " + "Undefined error";
        }
        return errorMessage;
    }

    public String prepareXmlRequest(StartrackShipmentRequestVo startrackShipmentRequestVo) throws JAXBException, DaoException, ParseException {
        CreateConnoteBinary connoteBinary = new CreateConnoteBinary();
        ObjectFactory objectFactory = new ObjectFactory();
        com.gms.delivery.startrack.xmlpi.booking.tempuri.ObjectFactory temOjectFactory = new com.gms.delivery.startrack.xmlpi.booking.tempuri.ObjectFactory();
        // Code for prepare xml date here...
        AaEConnoteType type = new AaEConnoteType();
        Map<String, ServiceAddConVo> serviceAddCon = startrackShipmentRequestVo.getShipmentInfo().getServiceAddConMap();
        if (serviceAddCon.get("atl") != null && serviceAddCon.get("atl").getValue() != null && serviceAddCon.get("atl").getValue().equals("1")) {
            type.setATL("Y");
        } else {
            type.setATL("N");
        }
        type.setAccount("");
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(startrackShipmentRequestVo.getShipmentInfo().getShipmentTypeId());
        String shipmentTypeCode = shipmentTypeVo.getServiceCode();
        JAXBElement<String> bookInDeliverFlag = objectFactory.createAaEConnoteTypeBookInDeliverFlag("N");
        if ("EXP".equalsIgnoreCase(shipmentTypeCode)) {
            bookInDeliverFlag = objectFactory.createAaEConnoteTypeBookInDeliverFlag("Y");
        }
        type.setBookInDeliverFlag(bookInDeliverFlag);
        Date pickupTime = new Date();
        if (startrackShipmentRequestVo.getScheduleCollection().getPickupDate() != null) {
            pickupTime = startrackShipmentRequestVo.getScheduleCollection().getPickupDate();
        } else {
            pickupTime = startrackShipmentRequestVo.getShipmentInfo().getShipmentDate();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(pickupTime);
        cal.add(Calendar.DATE, 30); // add 30 days
        Date pickupTimeAfter = cal.getTime();
        type.setBookInNotAfter(objectFactory.createAaEConnoteTypeBookInNotAfter(DateUtils.convertDateToString(pickupTimeAfter, "dd/MM/yyyy", null)));
        type.setBookInNotBefore(objectFactory.createAaEConnoteTypeBookInNotBefore(DateUtils.convertDateToString(pickupTime, "dd/MM/yyyy", null)));
        // Get dispatch ID & account
        String defaultStartrackAccount = (SystemSettingCache.getInstance().getValueByKey("StarTrack Account") == null) ? "" : SystemSettingCache.getInstance().getValueByKey("StarTrack Account");
        String defaultDispatchId = (SystemSettingCache.getInstance().getValueByKey("Default Startrack Dispatch ID") == null) ? "" : SystemSettingCache.getInstance().getValueByKey("Default Startrack Dispatch ID");
        String customerCode = startrackShipmentRequestVo.getShipmentInfo().getCustomerCode().toString();
        String subCustomerCode = customerCode.substring(3, customerCode.length());
        FranchiseDao franchiseDao = new FranchiseDao();
        String dispatchId = "", startrackAccount = "";
        if ("00001".equalsIgnoreCase(subCustomerCode)) { // Franchise
            FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCode(customerCode.substring(0, 3));
            if ("3p".equalsIgnoreCase(franchiseVo.getDispatchId()) || StringUtils.isBlank(franchiseVo.getDispatchId())) {
                dispatchId = defaultDispatchId;
            } else {
                dispatchId = franchiseVo.getDispatchId();
            }
            if ("3p".equalsIgnoreCase(franchiseVo.getStartrackAccount()) || StringUtils.isBlank(franchiseVo.getStartrackAccount())) {
                startrackAccount = defaultStartrackAccount;
            } else {
                startrackAccount = franchiseVo.getStartrackAccount();
            }
        } else { // Customer
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = customerDao.selectByCode(customerCode);
            if ("3p".equalsIgnoreCase(customerVo.getDispatchId()) || StringUtils.isBlank(customerVo.getDispatchId())) {
                FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCode(customerCode.substring(0, 3));
                if ("3p".equalsIgnoreCase(franchiseVo.getDispatchId()) || StringUtils.isBlank(franchiseVo.getDispatchId())) {
                    dispatchId = defaultDispatchId;
                } else {
                    dispatchId = franchiseVo.getDispatchId();
                }
            } else {
                dispatchId = customerVo.getDispatchId();
            }
            if ("3p".equalsIgnoreCase(customerVo.getStartrackAccount()) || StringUtils.isBlank(customerVo.getStartrackAccount())) {
                FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCode(customerCode.substring(0, 3));
                if ("3p".equalsIgnoreCase(franchiseVo.getStartrackAccount()) || StringUtils.isBlank(franchiseVo.getStartrackAccount())) {
                    startrackAccount = defaultStartrackAccount;
                } else {
                    startrackAccount = franchiseVo.getStartrackAccount();
                }
            } else {
                startrackAccount = customerVo.getStartrackAccount();
            }
        }
        type.setCID(dispatchId);
        type.setStarTrackAcct(objectFactory.createAaEConnoteTypeStarTrackAcct(startrackAccount));
        type.setCreatorName(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getContactName());
        String creatorPhoneWithoutSpaceAndSpecialCharacter = startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setCreatorPhone(creatorPhoneWithoutSpaceAndSpecialCharacter);
        // Get total weight & volume
        String shipmentTypeName = shipmentTypeVo.getShipmentTypeName();
        Integer forceVolWeight = 0;
        if ("road express".equalsIgnoreCase(shipmentTypeName) || "premium air freight".equalsIgnoreCase(shipmentTypeName)) {
            // Road Express & Premium air freight
            forceVolWeight = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Volume Weight Divided By (Startrack)")); // 4000;
        } else { // FPP
            forceVolWeight = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Volume Weight Divided By (Startrack FPP)")); // 5263;
        }
        if (forceVolWeight == 0)
            forceVolWeight = 5000;
        String dimensionunit = startrackShipmentRequestVo.getShipmentInfo().getDimensionUnit();
        Integer noOfPieces = 0;
        Double maxL = 0D, maxH = 0D, maxW = 0D, grossWeight = 0D, cubicVolume = 0D, totWeight = 0D, totCubicWeight = 0D, totCubicVolume = 0D;
        if (startrackShipmentRequestVo.getShipmentInfo().getPieces() != null && startrackShipmentRequestVo.getShipmentInfo().getPieces().size() > 0) {
            for (PieceVo piece : startrackShipmentRequestVo.getShipmentInfo().getPieces()) {
                if (piece.getDimensionL() != null && piece.getDimensionL() > maxL) {
                    maxL = piece.getDimensionL();
                }
                if (piece.getDimensionH() != null && piece.getDimensionH() > maxH) {
                    maxH = piece.getDimensionH();
                }
                if (piece.getDimensionW() != null && piece.getDimensionW() > maxW) {
                    maxW = piece.getDimensionW();
                }
                noOfPieces += piece.getQuantity();
                Double picWeight = piece.getWeight() * piece.getQuantity();
                totWeight += picWeight;
                if (piece.getDimensionL() != null && piece.getDimensionH() != null && piece.getDimensionW() != null) {
                    grossWeight = getGrossWeight(piece.getDimensionW(), piece.getDimensionH(), piece.getDimensionL(), dimensionunit, forceVolWeight) * piece.getQuantity();
                    cubicVolume = piece.getDimensionL() * piece.getDimensionW() * piece.getDimensionH() * piece.getQuantity() / 1000;
                }
                totCubicWeight += grossWeight;
                totCubicVolume += cubicVolume;
            }
        }
        //totWeight = MathUtils.round(totWeight, 0);  //code by rakesh sir
        totWeight= MathUtils.shipmentWeightRound(totWeight, true);  //code by shahabuddin
        
        totCubicWeight = MathUtils.roundUp(totCubicWeight, 0);
        totCubicVolume *= 1000;
        totCubicVolume = MathUtils.round(totCubicVolume, 0);
        type.setArticles(noOfPieces);
        type.setCubicVolume(Integer.valueOf(totCubicVolume.intValue()));
        type.setCubicWeight(Integer.valueOf(totCubicWeight.intValue()).toString());
        type.setDeadWeight(Integer.valueOf(totWeight.intValue()).toString());
        type.setDeliveryLines(objectFactory.createAaEConnoteTypeDeliveryLines(startrackShipmentRequestVo.getShipmentInfo().getSpecialDelivery()));
        type.setDescription(startrackShipmentRequestVo.getShipmentInfo().getContentDescription());
        type.setEmailAddress(objectFactory.createAaEConnoteTypeEmailAddress(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getEmail()));
        type.setEmailLabels("N");
        type.setHeight(Integer.valueOf(maxH.intValue()));
        type.setLength(Integer.valueOf(maxL.intValue()));
        type.setWidth(Integer.valueOf(maxW.intValue()));
        type.setInvoiceList(objectFactory.createAaEConnoteTypeInvoiceList(""));
        type.setProductCode(objectFactory.createAaEConnoteTypeProductCode(shipmentTypeCode));
        type.setReceiverAddress1(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress());
        type.setReceiverAddress2(objectFactory.createAaEConnoteTypeReceiverAddress2(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress2()));
        type.setReceiverCompanyName(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getCompanyName());
        type.setReceiverContactName(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getContactName());
        type.setReceiverEmail(objectFactory.createAaEConnoteTypeReceiverEmail(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getEmail()));
        type.setReceiverPostcode(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getPostalCode());
        type.setReceiverSuburb(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getCity());
        String receiverTelWithoutSpaceAndSpecialCharacter = startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setReceiverTel(objectFactory.createAaEConnoteTypeReceiverTel(receiverTelWithoutSpaceAndSpecialCharacter));
        type.setRef1(objectFactory.createAaEConnoteTypeRef1(startrackShipmentRequestVo.getShipmentReference()));
        type.setRef2(objectFactory.createAaEConnoteTypeRef2(startrackShipmentRequestVo.getShipmentReference()));
        type.setReturnsCCEmail(objectFactory.createAaEConnoteTypeReturnsCCEmail("abc@gmail.com"));
        type.setReturnsLabelPrintFlag(objectFactory.createAaEConnoteTypeReturnsLabelPrintFlag("S"));
        type.setReturnsReceiverEmail(objectFactory.createAaEConnoteTypeReturnsReceiverEmail(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getEmail()));
        type.setReturnsSenderContact(objectFactory.createAaEConnoteTypeReturnsSenderContact(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getContactName()));
        type.setReturnsSenderEmail(objectFactory.createAaEConnoteTypeReturnsSenderEmail(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getEmail()));
        String returnSenderTelWithoutSpaceAndSpecialCharacter = startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setReturnsSenderTel(objectFactory.createAaEConnoteTypeReturnsSenderTel(returnSenderTelWithoutSpaceAndSpecialCharacter));
        type.setReturnsServiceType(objectFactory.createAaEConnoteTypeReturnsServiceType("T"));
        type.setSenderAddress1(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress());
        type.setSenderAddress2(objectFactory.createAaEConnoteTypeSenderAddress2(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress2()));
        type.setSenderCompany(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getCompanyName());
        type.setSenderEmail(objectFactory.createAaEConnoteTypeSenderEmail(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getEmail()));
        type.setSenderPostcode(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPostalCode());
        type.setSenderSuburb(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getCity());
        String senderTelWithoutSpaceAndSpecialCharacter = startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setSenderTel(objectFactory.createAaEConnoteTypeSenderTel(senderTelWithoutSpaceAndSpecialCharacter));
        type.setServiceCode("");
        type.setThermalLabelPDF(objectFactory.createAaEConnoteTypeThermalLabelPDF("Y"));
        type.setUnitType(objectFactory.createAaEConnoteTypeUnitType("CTN"));
        if (startrackShipmentRequestVo.getShipmentInfo().getPackageId().intValue() == 15) {
            type.setUnitType(objectFactory.createAaEConnoteTypeUnitType("SAT"));
        }
        // Set composite
        connoteBinary.setComposite(temOjectFactory.createCreateConnoteBinaryComposite(type));
        String xml = AppUtils.Object2XmlString(connoteBinary, CreateConnoteBinary.class);
        String xmlHeader = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:ns=\"http://schemas.datacontract.org/2004/07/\"><soapenv:Header><wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken wsu:Id=\"UsernameToken-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><wsse:Username>YourUsername</wsse:Username><wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">YourPassword</wsse:Password><wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">eWKkxq3KfihkOn1QisM+Rw==</wsse:Nonce><wsu:Created>2011-03-21T01:48:08.609Z</wsu:Created></wsse:UsernameToken></wsse:Security></soapenv:Header>";
        String userName = SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_USERNAME);
        String password = SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_PASSWORD);
        xmlHeader = xmlHeader.replace("YourUsername", userName);
        xmlHeader = xmlHeader.replace("YourPassword", password);
        xmlHeader += "<soapenv:Body><tem:CreateConnoteBinary";
        return replaceInvalidStrings(xml, xmlHeader);
    }

    private String replaceInvalidStrings(String xmlResponse, String header) {
        String result = xmlResponse;
        // First replacement.
        Map<String, String> replaceMap = new LinkedHashMap<String, String>();
        replaceMap.put("<", "<ns:");
        replaceMap.put("<ns:/", "</ns:");
        replaceMap.put("<ns:ns2:", "<tem:");
        replaceMap.put("</ns:ns2:", "</tem:");
        replaceMap.put("<ns:?", "<?");
        replaceMap.put(" xmlns=\"http://schemas.datacontract.org/2004/07/\"", "");
        replaceMap.put(" xmlns:ns2=\"http://tempuri.org/\"", "");
        replaceMap.put(" xmlns:ns3=\"http://schemas.datacontract.org/2004/07/AaECreateEConnote\"", "");
        replaceMap.put("<tem:CreateConnoteBinary", header);
        result = AppUtils.replaceStringByMap(replaceMap, result);
        result += "</soapenv:Body></soapenv:Envelope>";
        return result;
    }

    private Double getGrossWeight(double w, double h, double l, String dimUnit, Integer forceVolWeight) {
        double volWeight = 5000D;
        try {
            volWeight = Float.parseFloat(SystemSettingCache.getInstance().getValueByKey("Volume Weight Divided By"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (forceVolWeight > 0) {
            volWeight = forceVolWeight;
        }
        w = MathUtils.dimUnitConvert(w, dimUnit);
        l = MathUtils.dimUnitConvert(l, dimUnit);
        h = MathUtils.dimUnitConvert(h, dimUnit);
        double grossWeight = (w * l * h) / volWeight;
        grossWeight = MathUtils.round(grossWeight, 2);
        return grossWeight;
    }
}
