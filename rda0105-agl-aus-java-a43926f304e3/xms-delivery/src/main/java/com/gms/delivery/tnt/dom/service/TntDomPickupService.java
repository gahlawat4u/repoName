package com.gms.delivery.tnt.dom.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tntdom.jaxb.*;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.shipment.TntDomShipmentRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

/**
 * Posted from TntPickupService
 * <p>
 * Author dattrinh Jan 30, 2016 10:28:02 AM
 */
@SuppressWarnings("deprecation")
public class TntDomPickupService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/tnt_dom_pickup_request_" + uniqueString + ".xml");
        PostMethod post = new PostMethod("http://www.tntexpress.com.au/webservices/Booking.svc?wsdl");
        post.setRequestHeader("soapaction", "http://tempuri.org/IBooking/SubmitBooking");
        post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xmlRequest)));
        try {
            String xmlResponse;
            int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (result == 200) {
                xmlResponse = post.getResponseBodyAsString();
                AppUtils.writeToFileByDate(xmlResponse, "/tnt_dom_pickup_response_" + uniqueString + ".xml");
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

    public SubmitBookingResponse parseResponse(String xmlResponse) throws Exception {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(SubmitBookingResponse.class).createUnmarshaller();
        SubmitBookingResponse bookingResponse = (SubmitBookingResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
        return bookingResponse;
    }

    public String getErrorMessage(SubmitBookingResponse bookingResponse) {
        String errorMessage = "";
        if (bookingResponse.getSubmitBookingResult().getValue().getErrors().getValue() != null) {
            for (ErrorInformation error : bookingResponse.getSubmitBookingResult().getValue().getErrors().getValue().getErrorInformation()) {
                errorMessage += error.getErrorCode() + " : " + error.getErrorMessage() + "<br/>";
            }
        }
        return errorMessage;
    }

    public String prepareXmlRequest(TntDomShipmentRequestVo shipmentRequestVo) throws JAXBException {
        SubmitBooking submitBooking = new SubmitBooking();
        ObjectFactory objectFactory = new ObjectFactory();
        // Set user name.
        String userName = SystemSettingCache.getInstance().getValueByKey("TNT Web Service UserName");
        JAXBElement<String> userElement = objectFactory.createSubmitBookingUserName(userName);
        submitBooking.setUserName(userElement);
        // Set password.
        String password = SystemSettingCache.getInstance().getValueByKey("TNT Web Service Password");
        JAXBElement<String> passElement = objectFactory.createSubmitBookingPassword(password);
        submitBooking.setPassword(passElement);
        // Set sender account.
        String senderAccount = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Account Number");
        JAXBElement<String> senderAccountElement = objectFactory.createSubmitBookingSenderAccount(senderAccount);
        submitBooking.setSenderAccount(senderAccountElement);
        // Set sender company name.
        String senderCompanyName = shipmentRequestVo.getShipmentInfo().getSenderAddress().getCompanyName();
        JAXBElement<String> senderCompanyNameElement = objectFactory.createSubmitBookingSenderCompanyName(senderCompanyName);
        submitBooking.setSenderCompanyName(senderCompanyNameElement);
        // Set sender address line 1.
        String senderAddress1 = shipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress();
        JAXBElement<String> senderAddress1Element = objectFactory.createSubmitBookingSenderAddressLine1(senderAddress1);
        submitBooking.setSenderAddressLine1(senderAddress1Element);
        // Set sender address line 2.
        String senderAddress2 = shipmentRequestVo.getShipmentInfo().getSenderAddress().getAddress2();
        JAXBElement<String> senderAddress2Element = objectFactory.createSubmitBookingSenderAddressLine2(senderAddress2);
        submitBooking.setSenderAddressLine2(senderAddress2Element);
        // Set sender suburb.
        String senderSuburb = shipmentRequestVo.getShipmentInfo().getSenderAddress().getCity().toUpperCase();
        JAXBElement<String> senderSuburbElement = objectFactory.createSubmitBookingSenderSuburb(senderSuburb);
        submitBooking.setSenderSuburb(senderSuburbElement);
        // Set sender postal code.
        String senderPostalCode = shipmentRequestVo.getShipmentInfo().getSenderAddress().getPostalCode();
        JAXBElement<String> senderPostalCodeElement = objectFactory.createSubmitBookingSenderPostcode(senderPostalCode);
        submitBooking.setSenderPostcode(senderPostalCodeElement);
        // Set sender state.
        String senderState = shipmentRequestVo.getShipmentInfo().getSenderAddress().getState().toUpperCase();
        submitBooking.setSenderState(AustralianState.valueOf(senderState));
        // Set sender contact name.
        String senderContactName = shipmentRequestVo.getShipmentInfo().getSenderAddress().getContactName();
        JAXBElement<String> senderContactNameElement = objectFactory.createSubmitBookingSenderContactName(senderContactName);
        submitBooking.setSenderContactName(senderContactNameElement);
        // Set sender contact phone area code.
        String senderPhone = shipmentRequestVo.getShipmentInfo().getSenderAddress().getPhone();
        JAXBElement<String> senderContactPhoneAreaCodeElement = objectFactory.createSubmitBookingSenderContactPhoneAreaCode(senderPhone.substring(0, 2));
        submitBooking.setSenderContactPhoneAreaCode(senderContactPhoneAreaCodeElement);
        // Set sender contact phone number.
        JAXBElement<String> senderContactPhoneNumberElement = objectFactory.createSubmitBookingSenderContactPhoneNumber(senderPhone.substring(2, 8));
        submitBooking.setSenderContactPhoneNumber(senderContactPhoneNumberElement);
        // Set collection company name.
        String collCompanyName = shipmentRequestVo.getScheduleCollection().getPickupAddress().getCompanyName();
        JAXBElement<String> collCompanyNameElement = objectFactory.createSubmitBookingCollectionCompanyName(collCompanyName);
        submitBooking.setCollectionCompanyName(collCompanyNameElement);
        // Set collection address line 1.
        String collAddress1 = shipmentRequestVo.getScheduleCollection().getPickupAddress().getAddress();
        JAXBElement<String> collAddress1Element = objectFactory.createSubmitBookingCollectionAddressLine1(collAddress1);
        submitBooking.setCollectionAddressLine1(collAddress1Element);
        // Set collection address line 2.
        String collAddress2 = shipmentRequestVo.getScheduleCollection().getPickupAddress().getAddress2();
        JAXBElement<String> collAddress2Element = objectFactory.createSubmitBookingCollectionAddressLine2(collAddress2);
        submitBooking.setCollectionAddressLine2(collAddress2Element);
        // Set collection suburb.
        String collSuburb = shipmentRequestVo.getScheduleCollection().getPickupAddress().getCity().toUpperCase();
        JAXBElement<String> collSuburbElement = objectFactory.createSubmitBookingCollectionSuburb(collSuburb);
        submitBooking.setCollectionSuburb(collSuburbElement);
        // Set collection postal code.
        String collPostalCode = shipmentRequestVo.getScheduleCollection().getPickupAddress().getPostalCode();
        JAXBElement<String> collPostalCodeElement = objectFactory.createSubmitBookingCollectionPostcode(collPostalCode);
        submitBooking.setCollectionPostcode(collPostalCodeElement);
        // Set collection state.
        String collState = shipmentRequestVo.getScheduleCollection().getPickupAddress().getState();
        submitBooking.setCollectionState(AustralianState.valueOf(collState));
        // Set collection contact name.
        String collContactName = shipmentRequestVo.getScheduleCollection().getPickupAddress().getContactName();
        JAXBElement<String> collContactNameElement = objectFactory.createSubmitBookingCollectionContactName(collContactName);
        submitBooking.setCollectionContactName(collContactNameElement);
        // Set collection phone area code.
        String collPhone = shipmentRequestVo.getScheduleCollection().getPickupAddress().getPhone();
        JAXBElement<String> collPhoneAreaCodeElement = objectFactory.createSubmitBookingCollectionContactPhoneAreaCode(collPhone.substring(0, 2));
        submitBooking.setCollectionContactPhoneAreaCode(collPhoneAreaCodeElement);
        // Set collection phone number.
        JAXBElement<String> collPhoneNumberElement = objectFactory.createSubmitBookingCollectionContactPhoneNumber(collPhone.substring(2, 8));
        submitBooking.setCollectionContactPhoneNumber(collPhoneNumberElement);
        // Set receiver company name.
        String receiverCompanyName = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCompanyName();
        JAXBElement<String> receiverCompanyNameElement = objectFactory.createSubmitBookingReceiverCompanyName(receiverCompanyName);
        submitBooking.setReceiverCompanyName(receiverCompanyNameElement);
        // Set receiver address line 1.
        String receiverAddress1 = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress();
        JAXBElement<String> receiverAddress1Element = objectFactory.createSubmitBookingReceiverAddressLine1(receiverAddress1);
        submitBooking.setReceiverAddressLine1(receiverAddress1Element);
        // Set receiver address line 2.
        String receiverAddress2 = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getAddress2();
        JAXBElement<String> receiverAddress2Element = objectFactory.createSubmitBookingReceiverAddressLine2(receiverAddress2);
        submitBooking.setReceiverAddressLine2(receiverAddress2Element);
        // Set receiver suburb.
        String receiverSuburb = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getCity().toUpperCase();
        JAXBElement<String> receiverSuburbElement = objectFactory.createSubmitBookingReceiverSuburb(receiverSuburb);
        submitBooking.setReceiverSuburb(receiverSuburbElement);
        // Set receiver postal code.
        String receiverPostalCode = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPostalCode();
        JAXBElement<String> receiverPostalCodeElement = objectFactory.createSubmitBookingReceiverPostcode(receiverPostalCode);
        submitBooking.setReceiverPostcode(receiverPostalCodeElement);
        // Set receiver state.
        String receiverState = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getState();
        submitBooking.setReceiverState(AustralianState.valueOf(receiverState));
        // Set receiver contact name.
        String receiverContactName = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getContactName();
        JAXBElement<String> receiverContactNameElement = objectFactory.createSubmitBookingReceiverContactName(receiverContactName);
        submitBooking.setReceiverContactName(receiverContactNameElement);
        // Set receiver phone area code.
        String receiverPhone = shipmentRequestVo.getShipmentInfo().getReceiverAddress().getPhone();
        JAXBElement<String> receiverPhoneAreaCodeElement = objectFactory.createSubmitBookingReceiverContactPhoneAreaCode(receiverPhone.substring(0, 2));
        submitBooking.setReceiverContactPhoneAreaCode(receiverPhoneAreaCodeElement);
        // Set receiver phone number.
        JAXBElement<String> receiverPhoneNumberElement = objectFactory.createSubmitBookingReceiverContactPhoneNumber(receiverPhone.substring(2, receiverPhone.length()));
        submitBooking.setReceiverContactPhoneNumber(receiverPhoneNumberElement);
        // Set item count.
        submitBooking.setItemCount(shipmentRequestVo.getShipmentInfo().getNoOfPieces());
        // Set total weight.
        submitBooking.setTotalWeight(new BigDecimal(shipmentRequestVo.getShipmentInfo().getTotalWeight()).setScale(1, RoundingMode.HALF_UP));
        // Set max width, length, height.
        Double maxWeight = 0d;
        Double itemLength = 0d;
        Double itemWidth = 0d;
        Double itemHeight = 0d;
        List<PieceVo> pieces = shipmentRequestVo.getShipmentInfo().getPieces();
        for (PieceVo pieceVo : pieces) {
            if (maxWeight < pieceVo.getWeight()) {
                maxWeight = pieceVo.getWeight();
                itemLength = pieceVo.getDimensionL();
                itemWidth = pieceVo.getDimensionW();
                itemHeight = pieceVo.getDimensionH();
            }
        }
        if (itemLength == 0) {
            itemLength = 10d;
        }
        if (itemWidth == 0) {
            itemWidth = 10d;
        }
        if (itemHeight == 0) {
            itemHeight = 10d;
        }
        submitBooking.setMaxWidth(itemWidth.intValue());
        submitBooking.setMaxLength(itemLength.intValue());
        submitBooking.setMaxHeight(itemHeight.intValue());
        // Set package type code.
        JAXBElement<String> packingCode = objectFactory.createSubmitBookingPackagingCode(shipmentRequestVo.getPackageInfo().getPackageTypeCode());
        submitBooking.setPackagingCode(packingCode);
        // Set collection date time.
        XMLGregorianCalendar collDateTime = DateUtils.convertDate2XMLGregorianCalendar(shipmentRequestVo.getScheduleCollection().getPickupDate());
        String readyTime = shipmentRequestVo.getScheduleCollection().getPickupTime();
        String[] resultTime = readyTime.split(":");
        Integer hour = Integer.valueOf(resultTime[0]);
        Integer minute = Integer.valueOf(resultTime[1]);
        collDateTime.setHour(hour);
        collDateTime.setMinute(minute);
        submitBooking.setCollectionDateTime(collDateTime);
        // Set collection close time.
        String closeTimeStr = shipmentRequestVo.getScheduleCollection().getPickupTimeNoLater();
        closeTimeStr = closeTimeStr.substring(0, 5).replace(":", "");
        JAXBElement<String> closeTime = objectFactory.createSubmitBookingCollectionCloseTime(closeTimeStr);
        submitBooking.setCollectionCloseTime(closeTime);
        // Set payer.
        submitBooking.setPayer(PayerType.SENDER);
        // Set service code.
        JAXBElement<String> serviceCode = objectFactory.createSubmitBookingServiceCode(shipmentRequestVo.getServiceCode());
        submitBooking.setServiceCode(serviceCode);
        // Set special instructions.
        JAXBElement<String> specialInstructions = objectFactory.createSubmitBookingSpecialInstructions(shipmentRequestVo.getScheduleCollection().getSpecialInstructions());
        submitBooking.setSpecialInstructions(specialInstructions);
        // Set is dangerous goods.
        boolean isDangerousGoods = false;
        String dangerUN = "";
        String dangerGroup = "";
        if ("DG".equalsIgnoreCase(shipmentRequestVo.getShipmentInfo().getCourierMessage().trim())) {
            isDangerousGoods = true;
            String[] dangerResult = shipmentRequestVo.getShipmentInfo().getDhlRoutingCode().split("##@##");
            dangerUN = dangerResult[0];
            dangerGroup = dangerResult[1];
        }
        submitBooking.setIsDangerousGoods(isDangerousGoods);
        // Set dangerous goods UN.
        JAXBElement<String> dangerousGoodsUN = objectFactory.createSubmitBookingDangerousGoodsUN(dangerUN);
        submitBooking.setDangerousGoodsUN(dangerousGoodsUN);
        // Set dangerous goods group.
        JAXBElement<String> dangerousGoodsGroup = objectFactory.createSubmitBookingDangerousGoodsPackingGroup(dangerGroup);
        submitBooking.setDangerousGoodsPackingGroup(dangerousGoodsGroup);
        // Set consignment note.
        JAXBElement<String> consignmentNoteNumber = objectFactory.createSubmitBookingConsignmentNoteNumber(shipmentRequestVo.getShipmentInfo().getAirbillNumber());
        submitBooking.setConsignmentNoteNumber(consignmentNoteNumber);
        // Set customer reference.
        JAXBElement<String> customerRef = objectFactory.createSubmitBookingCustomerReference(shipmentRequestVo.getShipmentInfo().getReference());
        submitBooking.setCustomerReference(customerRef);
        String xml = AppUtils.Object2XmlString(submitBooking, SubmitBooking.class);
        String xmlHeader = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns=\"http://tempuri.org/\"><soapenv:Header/><soapenv:Body>";
        xml = xml.replace("<SubmitBooking", xmlHeader + "<SubmitBooking") + "</soapenv:Body></soapenv:Envelope>";
        return xml;
    }
}
