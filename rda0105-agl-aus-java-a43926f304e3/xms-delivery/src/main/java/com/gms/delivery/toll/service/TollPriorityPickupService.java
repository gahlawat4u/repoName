package com.gms.delivery.toll.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.toll.xmlpi.pickup.request.*;
import com.gms.delivery.toll.xmlpi.pickup.request.PickupType.AccountDetail;
import com.gms.delivery.toll.xmlpi.pickup.request.PickupType.ConfirmationDetail;
import com.gms.delivery.toll.xmlpi.pickup.request.PickupType.Items;
import com.gms.delivery.toll.xmlpi.pickup.request.PickupType.PickupDetail;
import com.gms.delivery.toll.xmlpi.pickup.request.PickupType.PickupDetail.Reference;
import com.gms.delivery.toll.xmlpi.pickup.response.TollPickupResponse;
import com.gms.delivery.toll.xmlpi.pickup.response.TollPickupResult;
import com.gms.delivery.toll.xmlpi.pickup.response.XmlEncodeError;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.delivery.toll.TollPriorityPickupRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * Posted from TollPriorityPickupService
 * <p>
 * Author dattrinh Feb 16, 2016 3:56:03 PM
 */
public class TollPriorityPickupService {
    private static final Log log = LogFactory.getLog(TollPriorityPickupService.class);

    public String execute(String xmlRequest, String xmlResponseCached) {
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/toll_priority_pickup_request_" + uniqueString + ".xml");
        // Create post object to send xml data to Toll server using post method.
        PostMethod post = null;
        String xmlResponse;
        try {
            String url = "https://online.toll.com.au/trackandtrace/b2b";
            url += "?User=" + SystemSettingCache.getInstance().getValueByKey("TOLL User Name");
            url += "&Password=" + SystemSettingCache.getInstance().getValueByKey("TOLL Password");
            post = new PostMethod(url);
            post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
            post.setRequestHeader("soapaction", "TollPickupRequest");
            post.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(xmlRequest.getBytes("UTF8"))));
            // Send request to get access key.
            int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (resultCode == 200) {
                xmlResponse = post.getResponseBodyAsString();
                AppUtils.writeToFileByDate(xmlResponse, "/toll_priority_pickup_response_" + uniqueString + ".xml");
            } else {
                xmlResponse = null;
            }
            return xmlResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public TollPickupResult parseResponse(String xmlResponse) {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        TollPickupResult tollPickupResult = new TollPickupResult();
        try {
            // Parse as Toll Pickup Response.
            TollPickupResponse tollPickupResponse = AppUtils.xmlString2Object(xmlResponse, TollPickupResponse.class);
            tollPickupResult.setPickupResponse(tollPickupResponse);
        } catch (Exception e) {
            tollPickupResult.setPickupResponse(null);
        }
        try {
            // Parse as Xml Encode Error.
            XmlEncodeError xmlEncodeError = AppUtils.xmlString2Object(xmlResponse, XmlEncodeError.class);
            tollPickupResult.setEncodeError(xmlEncodeError);
        } catch (Exception e) {
            tollPickupResult.setEncodeError(null);
        }
        return tollPickupResult;
    }

    public String prepareXmlRequest(TollPriorityPickupRequestVo pickupRequestVo) throws JAXBException {
        TollPickupRequest tollPickupRequest = new TollPickupRequest();
        // Set header.
        HeaderType header = new HeaderType();
        header.setSender("TollConnect");
        header.setReceiver("Toll");
        header.setDocumentType(pickupRequestVo.getDocumentType());
        header.setDocumentID(pickupRequestVo.getDocumentId());
        String timeZone = "+1000";
        if (!StringUtils.isBlank(SystemSettingCache.getInstance().getValueByKey("AU GMT"))) {
            timeZone = "+" + SystemSettingCache.getInstance().getValueByKey("AU GMT") + "00";
        }
        Date currentDate = new Date();
        String dateTimeStamp = DateUtils.convertDateToString(currentDate, "yyyy-MM-dd", null);
        dateTimeStamp += "T" + DateUtils.convertDateToString(currentDate, "hh:mm:ss", null);
        header.setDateTimeStamp(dateTimeStamp + timeZone);
        tollPickupRequest.setHeader(header);
        // Set pickup request.
        PickupType pickupRequest = new PickupType();
        pickupRequest.setCarrierID("PRIO");
        // Set pickup request - confirmation detail
        ConfirmationDetail confirmationDetail = new ConfirmationDetail();
        confirmationDetail.setDeclarationAccepted(true);
        confirmationDetail.setConfirmationNumber("");
        pickupRequest.setConfirmationDetail(confirmationDetail);
        // Set pickup request - account detail
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAccountCode(pickupRequestVo.getAccountCode());
        accountDetail.setSubAccountID("");
        pickupRequest.setAccountDetail(accountDetail);
        // Set pickup request - initiator detail
        InitiatorType initiatorDetail = new InitiatorType();
        initiatorDetail.setCompanyName(pickupRequestVo.getScheduleCollection().getPickupAddress().getCompanyName());
        initiatorDetail.setContactName(pickupRequestVo.getScheduleCollection().getPickupAddress().getContactName());
        String phone = pickupRequestVo.getScheduleCollection().getPickupAddress().getPhone();
        String areaCode = phone.substring(0, 2);
        String phoneNumber = phone.substring(2, phone.length());
        initiatorDetail.setContactAreaCode(areaCode);
        initiatorDetail.setContactPhoneNumber(phoneNumber);
        initiatorDetail.setContactEmail(pickupRequestVo.getScheduleCollection().getPickupAddress().getEmail());
        pickupRequest.setInitiatorDetail(initiatorDetail);
        // Set pickup request - sender detail
        SenderType senderDetail = new SenderType();
        senderDetail.setCompanyName(pickupRequestVo.getShipment().getSenderAddress().getCompanyName());
        senderDetail.setAddressLine1(pickupRequestVo.getShipment().getSenderAddress().getAddress());
        senderDetail.setAddressLine2(pickupRequestVo.getShipment().getSenderAddress().getAddress2());
        senderDetail.setSuburb(pickupRequestVo.getShipment().getSenderAddress().getCity().toUpperCase());
        senderDetail.setState(pickupRequestVo.getShipment().getSenderAddress().getState().toUpperCase());
        String postalCode = pickupRequestVo.getShipment().getSenderAddress().getPostalCode();
        postalCode = postalCode.substring(0, postalCode.length());
        senderDetail.setPostcode(postalCode);
        senderDetail.setCountry("AU");
        senderDetail.setContactName(pickupRequestVo.getShipment().getSenderAddress().getContactName());
        phone = pickupRequestVo.getShipment().getSenderAddress().getPhone();
        areaCode = phone.substring(0, 2);
        phoneNumber = phone.substring(2, phone.length());
        senderDetail.setContactAreaCode(areaCode);
        senderDetail.setContactPhoneNumber(phoneNumber);
        senderDetail.setContactEmail(pickupRequestVo.getShipment().getSenderAddress().getEmail());
        String specialInstructions = pickupRequestVo.getScheduleCollection().getSpecialInstructions();
        specialInstructions = specialInstructions.substring(0, specialInstructions.length() > 100 ? 100 : specialInstructions.length());
        senderDetail.setSpecialInstructions(specialInstructions);
        pickupRequest.setSenderDetail(senderDetail);
        // Set pickup request - pickup detail
        PickupDetail pickupDetail = new PickupDetail();
        // Set pickup request - pickup detail - reference
        Reference reference = new Reference();
        reference.setReference1(pickupRequestVo.getShipment().getReference());
        reference.setReference2(pickupRequestVo.getShipment().getReference2());
        pickupDetail.setReference(reference);
        String pickupDate = DateUtils.convertDateToString(pickupRequestVo.getScheduleCollection().getPickupDate(), "yyyy-MM-dd", null);
        String pickupDateTime = pickupDate + "T" + DateUtils.convertDateToString(pickupRequestVo.getScheduleCollection().getPickupDate(), "hh:mm:ss", null);
        pickupDetail.setPickupDateTime(pickupDateTime + timeZone);
        // Get Open Time
        String openTime = pickupDate + "T" + pickupRequestVo.getScheduleCollection().getPickupTime() + timeZone;
        pickupDetail.setOpenTime(openTime);
        // Get Close Time
        String closeTime = pickupDate + "T" + pickupRequestVo.getScheduleCollection().getPickupTimeNoLater() + timeZone;
        pickupDetail.setCloseTime(closeTime);
        pickupDetail.setRegularPickup(true);
        pickupDetail.setBringConNote(pickupRequestVo.getBringConNote());
        pickupDetail.setSameLocation(true);
        String location = pickupRequestVo.getScheduleCollection().getDescription();
        location = location.substring(0, location.length() > 25 ? 25 : location.length());
        pickupDetail.setPickupFromLocation(location);
        pickupDetail.setReturnJob(false);
        // Set pickup request - items
        Items items = new Items();
        String description = pickupRequestVo.getShipment().getContentDescription();
        description = description.substring(0, description.length() > 80 ? 80 : description.length());
        items.setDescription(description);
        BigInteger numberItems = BigInteger.valueOf(pickupRequestVo.getScheduleCollection().getNoOfPieces().intValue());
        items.setNumberItems(numberItems);
        items.setDangerousGoods(false);
        items.setServiceID(pickupRequestVo.getServiceId());
        items.setServiceName(pickupRequestVo.getServiceName());
        items.setProductID(pickupRequestVo.getProductId());
        items.setFood(false);
        items.setLargestItemType(pickupRequestVo.getLargestItemType());
        items.setWeight(pickupRequestVo.getWeight().floatValue());
        items.setLength(BigInteger.valueOf(pickupRequestVo.getLength().intValue()));
        items.setWidth(BigInteger.valueOf(pickupRequestVo.getWidth().intValue()));
        items.setHeight(BigInteger.valueOf(pickupRequestVo.getHeight().intValue()));
        // Set pickup request - items - receiver detail
        ReceiverType receiverDetail = new ReceiverType();
        receiverDetail.setCompanyName(pickupRequestVo.getShipment().getReceiverAddress().getCompanyName());
        receiverDetail.setAddressLine1(pickupRequestVo.getShipment().getReceiverAddress().getAddress());
        receiverDetail.setAddressLine2(pickupRequestVo.getShipment().getReceiverAddress().getAddress2());
        receiverDetail.setSuburb(pickupRequestVo.getShipment().getReceiverAddress().getCity().toUpperCase());
        receiverDetail.setState(pickupRequestVo.getShipment().getReceiverAddress().getState().toUpperCase());
        postalCode = pickupRequestVo.getShipment().getReceiverAddress().getPostalCode();
        postalCode = postalCode.substring(0, postalCode.length() > 4 ? 4 : postalCode.length());
        receiverDetail.setPostcode(postalCode);
        receiverDetail.setCountry("AU");
        receiverDetail.setContactName(pickupRequestVo.getShipment().getReceiverAddress().getContactName());
        phone = pickupRequestVo.getShipment().getReceiverAddress().getPhone();
        areaCode = phone.substring(0, 2);
        phoneNumber = phone.substring(2, phone.length());
        receiverDetail.setContactAreaCode(areaCode);
        receiverDetail.setContactPhoneNumber(phoneNumber);
        receiverDetail.setContactEmail(pickupRequestVo.getShipment().getReceiverAddress().getEmail());
        items.setReceiverDetail(receiverDetail);
        pickupRequest.getItems().add(items);
        pickupRequest.setPickupDetail(pickupDetail);
        tollPickupRequest.setPickupRequest(pickupRequest);
        return AppUtils.Object2XmlString(tollPickupRequest, TollPickupRequest.class);
    }

    public String getErrorMessage(TollPickupResult pickupResult) {
        String errorMessage = "";
        // Encode Error.
        if (pickupResult.getEncodeError() != null) {
            if (pickupResult.getEncodeError().getError() != null && pickupResult.getEncodeError().getError().size() > 0) {
                for (String error : pickupResult.getEncodeError().getError()) {
                    errorMessage += error + "\n";
                }
            }
        } else {
            // Toll Pickup Error.
            if (pickupResult.getPickupResponse() != null && pickupResult.getPickupResponse().getError() != null) {
                errorMessage = pickupResult.getPickupResponse().getError().getMessage();
            }
        }
        return errorMessage;
    }
}
