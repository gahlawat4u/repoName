package com.gms.delivery.startrack.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.startrack.xmlpi.aaecreatebooking.AaEBookingType;
import com.gms.delivery.startrack.xmlpi.aaecreatebooking.ObjectFactory;
import com.gms.delivery.startrack.xmlpi.tempuri.CreateBooking;
import com.gms.delivery.startrack.xmlpi.tempuri.CreateBookingResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.StarTrackConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.startrack.StartrackShipmentRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Posted from Jun 14, 2016 11:15:11 AM
 * <p>
 * Author dattrinh
 */
public class StarTrackScheduleService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/star_track_pickup_request_" + uniqueString + ".xml");
        PostMethod post = null;
        String xmlResponse;
        try {
            String apiUrl = SystemSettingCache.getInstance().getValueByKey("StarTrack pickup url");
            if (StringUtils.isBlank(apiUrl)) {
                apiUrl = "https://extwebservices.startrack.com.au:3112/AaECreateBooking/AaECreateBooking.Service1.svc/extBasic";
            }
            post = new PostMethod(apiUrl);
            post.setRequestHeader("soapaction", "http://tempuri.org/IService1/CreateBooking");
            post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
            post.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(xmlRequest.getBytes("UTF8"))));
            int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (result == 200 || result == 500) {
                xmlResponse = post.getResponseBodyAsString();
                AppUtils.writeToFileByDate(xmlResponse, "/star_track_pickup_response_" + uniqueString + ".xml");
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

    public CreateBookingResponse parseResponse(String xmlResponse) throws Exception {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(CreateBookingResponse.class).createUnmarshaller();
        CreateBookingResponse bookingResponse = (CreateBookingResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
        return bookingResponse;
    }

    public String getErrorMessage(String xmlResponse) throws Exception {
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        if (message == null || message.getSOAPBody() == null || message.getSOAPBody().getFault() == null) {
            return null;
        }
        SOAPFault fault = message.getSOAPBody().getFault();
        String errorMessage = "";
        errorMessage += fault.getFaultString();
        if (!StringUtils.isBlank(StarTrackConstants.STARTRACK_BOOKING_ERRORS.get(Integer.valueOf(fault.getFaultString())))) {
            errorMessage += " - " + StarTrackConstants.STARTRACK_BOOKING_ERRORS.get(Integer.valueOf(fault.getFaultString()));
        } else {
            errorMessage += " - " + "Undefined error";
        }
        return errorMessage;
    }

    public String prepareXmlRequest(StartrackShipmentRequestVo startrackShipmentRequestVo) throws JAXBException {
        CreateBooking createBooking = new CreateBooking();
        ObjectFactory objectFactory = new ObjectFactory();
        com.gms.delivery.startrack.xmlpi.tempuri.ObjectFactory temOjectFactory = new com.gms.delivery.startrack.xmlpi.tempuri.ObjectFactory();
        // Code for prepare xml date here...
        AaEBookingType type = new AaEBookingType();
        type.setCustId(objectFactory.createAaEBookingTypeCustId(SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_ACCOUNT)));
        type.setItemCount(startrackShipmentRequestVo.getShipmentInfo().getNoOfPieces());
        type.setItemDescription(startrackShipmentRequestVo.getShipmentInfo().getContentDescription());
        Double maxL = 0D, maxH = 0D, maxW = 0D, totWeight = 0D;
        if (startrackShipmentRequestVo.getShipmentInfo().getPieces() != null && startrackShipmentRequestVo.getShipmentInfo().getPieces().size() > 0) {
            for (PieceVo piece : startrackShipmentRequestVo.getShipmentInfo().getPieces()) {
                if (piece.getDimensionL() > maxL) {
                    maxL = piece.getDimensionL();
                }
                if (piece.getDimensionH() > maxH) {
                    maxH = piece.getDimensionH();
                }
                if (piece.getDimensionW() > maxW) {
                    maxW = piece.getDimensionW();
                }
                Double picWeight = piece.getWeight() * piece.getQuantity();
                totWeight += picWeight;
            }
        }
        totWeight = MathUtils.round(totWeight, 0);
        if (totWeight > 20) {
            type.setItemHeight(Integer.valueOf(maxH.intValue()));
            type.setItemLength(Integer.valueOf(maxL.intValue()));
            type.setItemWidth(Integer.valueOf(maxW.intValue()));
        }
        Date pickupDate = new Date();
        if (startrackShipmentRequestVo.getScheduleCollection().getPickupDate() != null) {
            pickupDate = startrackShipmentRequestVo.getScheduleCollection().getPickupDate();
        } else {
            pickupDate = startrackShipmentRequestVo.getShipmentInfo().getShipmentDate();
        }
        type.setPickupDate(String.valueOf(DateUtils.convertDateToString(pickupDate, "dd/MM/yyyy", null)));
        type.setPickupLatest(startrackShipmentRequestVo.getScheduleCollection().getPickupTimeNoLater().substring(0, 5));
        type.setPickupTime(startrackShipmentRequestVo.getScheduleCollection().getPickupTime().substring(0, 5));
        type.setReceiverAddr1(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress());
        type.setReceiverAddr2(objectFactory.createAaEBookingTypeReceiverAddr2(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress2()));
        type.setReceiverCity(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getCity());
        type.setReceiverContactName(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getContactName());
        type.setReceiverName(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getCompanyName());
        String receiverPhoneWithoutSpace = startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setReceiverPhone(receiverPhoneWithoutSpace);
        type.setReceiverPostCode(startrackShipmentRequestVo.getShipmentInfo().getReceiverAddress().getPostalCode());
        type.setSenderAddr1(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress());
        type.setSenderAddr2(objectFactory.createAaEBookingTypeSenderAddr2(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress2()));
        type.setSenderCity(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getCity());
        type.setSenderContactArea(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getContactName());
        type.setSenderContactName(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getCompanyName());
        type.setSenderName(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getCompanyName());
        String senderPhoneWithoutSpace = startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone().replaceAll("\\s", "").replaceAll("\\W", "");
        type.setSenderPhone(senderPhoneWithoutSpace);
        type.setSenderPostCode(startrackShipmentRequestVo.getShipmentInfo().getSenderAddress().getPostalCode());
        type.setServiceType(startrackShipmentRequestVo.getServiceCode());
        type.setTotalWeight(Integer.valueOf(totWeight.intValue()));
        // Set composite
        createBooking.setComposite(temOjectFactory.createCreateBookingComposite(type));
        String xml = AppUtils.Object2XmlString(createBooking, CreateBooking.class);
        String xmlHeader = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:aaec=\"http://schemas.datacontract.org/2004/07/AaECreateBooking\"><soapenv:Header><wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken wsu:Id=\"UsernameToken-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><wsse:Username>YourUsername</wsse:Username><wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">YourPassword</wsse:Password><wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">eWKkxq3KfihkOn1QisM+Rw==</wsse:Nonce><wsu:Created>2011-03-21T01:48:08.609Z</wsu:Created></wsse:UsernameToken></wsse:Security></soapenv:Header>";
        String userName = SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_USERNAME);
        String password = SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_PASSWORD);
        xmlHeader = xmlHeader.replace("YourUsername", userName);
        xmlHeader = xmlHeader.replace("YourPassword", password);
        xml = xml.replace("ns2:", "tem:");
        xml = xml.replace("<", "<aaec:");
        xml = xml.replace("aaec:/", "/aaec:");
        xml = xml.replace("aaec:tem", "tem");
        xml = xml.replace("aaec:?", "?");
        xml = xml.replace(" xmlns=\"http://schemas.datacontract.org/2004/07/AaECreateBooking\"", "");
        xml = xml.replace(" xmlns:ns2=\"http://tempuri.org/\"", "");
        xml = xml.replace("<tem:CreateBooking", xmlHeader + "<soapenv:Body><tem:CreateBooking") + "</soapenv:Body></soapenv:Envelope>";
        return xml;
    }
}
