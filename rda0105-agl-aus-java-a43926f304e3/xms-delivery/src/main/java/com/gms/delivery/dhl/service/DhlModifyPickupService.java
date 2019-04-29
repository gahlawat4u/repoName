package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.*;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.Contact;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.*;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.Place;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.modify.ModifyPURequest;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.modify.ModifyPUResponse;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.modify.ModifyPUResult;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.txndb.vo.schedulecollection.ModifyPickupRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Posted from DhlModifyPickupService
 * <p>
 * Author dattrinh Jan 22, 2016 4:57:36 PM
 */
@SuppressWarnings("deprecation")
public class DhlModifyPickupService {
    private static final Log log = LogFactory.getLog(DhlModifyPickupService.class);

    public String execute(String xmlRequest, String xmlResponse) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/dhl_modify_pickup_request_" + uniqueString + ".xml");
        PostMethod post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("DHL URL"));
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xmlRequest)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String reponseXml;
            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    reponseXml = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(reponseXml, "/dhl_modify_pickup_response_" + uniqueString + ".xml");
                } else {
                    reponseXml = null;
                }
            } else {
                reponseXml = xmlResponse;
            }
            return reponseXml;
        } catch (Exception e) {
            log.error("Fail to perfrom DHL modify pickup service.", e);
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public String parseRequest(ModifyPickupRequestVo modifyPickupRequestVo) throws JAXBException, DaoException, DatatypeConfigurationException, Exception {
        ModifyPURequest modifyPURequest = new ModifyPURequest();
        modifyPURequest.setSchemaVersion(new BigDecimal("1.0").setScale(2, RoundingMode.HALF_UP));
        // Set request.
        Request request = new Request();
        ServiceHeader serviceHeader = new ServiceHeader();
        serviceHeader.setMessageTime(DateUtils.convertDate2XMLGregorianCalendar(new Date()));
        serviceHeader.setMessageReference(AppUtils.createMessageReference().substring(0, 32));
        serviceHeader.setSiteID(SystemSettingCache.getInstance().getValueByKey("Site ID"));
        serviceHeader.setPassword(SystemSettingCache.getInstance().getValueByKey("DHL Password"));
        request.setServiceHeader(serviceHeader);
        modifyPURequest.setRequest(request);
        // Set region code.
        modifyPURequest.setRegionCode(RegionCode.valueOf(modifyPickupRequestVo.getDhlCountry().getDhlRegion()));
        // Set confirmation number.
        modifyPURequest.setConfirmationNumber(modifyPickupRequestVo.getScheduleCollection().getConfirmationNo());
        // Set requestor.
        Requestor requestor = new Requestor();
        requestor.setAccountType("D");
        requestor.setAccountNumber(modifyPickupRequestVo.getShipment().getBillingAccount());
        RequestorContact requestorContact = new RequestorContact();
        requestorContact.setPersonName(modifyPickupRequestVo.getSenderAddress().getContactName());
        requestorContact.setPhone(modifyPickupRequestVo.getSenderAddress().getPhone());
        requestor.setRequestorContact(requestorContact);
        modifyPURequest.setRequestor(requestor);
        // Set place.
        Place place = new Place();
        place.setLocationType(modifyPickupRequestVo.getLocationCode().getLocationCodeName());
        place.setCompanyName(modifyPickupRequestVo.getPickupAddress().getCompanyName());
        place.setAddress1(modifyPickupRequestVo.getPickupAddress().getAddress());
        place.setAddress2(modifyPickupRequestVo.getPickupAddress().getAddress2());
        place.setPackageLocation(modifyPickupRequestVo.getScheduleCollection().getDescription());
        place.setCity(modifyPickupRequestVo.getPickupAddress().getCity());
        place.setStateCode(modifyPickupRequestVo.getPickupAddress().getState());
        place.setDivisionName("");
        place.setCountryCode(modifyPickupRequestVo.getPickupAddress().getCountryDetail().getCountryCode());
        place.setPostalCode(modifyPickupRequestVo.getPickupAddress().getPostalCode());
        modifyPURequest.setPlace(place);
        // Set pickup.
        Pickup pickup = new Pickup();
        pickup.setPickupDate(DateUtils.convertDateToString(modifyPickupRequestVo.getScheduleCollection().getPickupDate(), "yyyy-MM-dd", null));
        pickup.setReadyByTime(modifyPickupRequestVo.getScheduleCollection().getPickupTime().substring(0, 5));
        pickup.setCloseTime(modifyPickupRequestVo.getScheduleCollection().getPickupTimeNoLater().substring(0, 5));
        pickup.setPieces(modifyPickupRequestVo.getScheduleCollection().getNoOfPieces());
        WeightSeg weight = new WeightSeg();
        weight.setWeight(new BigDecimal(modifyPickupRequestVo.getScheduleCollection().getTotalWeight()).setScale(1, RoundingMode.HALF_UP));
        weight.setWeightUnit(WeightUnit.K);
        pickup.setWeight(weight);
        modifyPURequest.setPickup(pickup);
        // Set pickup contact.
        Contact contact = new Contact();
        contact.setPersonName(modifyPickupRequestVo.getPickupAddress().getContactName());
        contact.setPhone(modifyPickupRequestVo.getPickupAddress().getPhone());
        modifyPURequest.setPickupContact(contact);
        // Set Origin Service Code Area.
        modifyPURequest.setOriginSvcArea(modifyPickupRequestVo.getShipment().getServiceAreaCodeOrigin());
        // Xml String.
        String xmsString = AppUtils.Object2XmlString(modifyPURequest, ModifyPURequest.class);
        xmsString = xmsString.replace("<ModifyPURequest schemaVersion=\"1.00\">", "<ns2:ModifyPURequest schemaVersion=\"1.00\" xmlns:ns2=\"http://www.dhl.com\">");
        xmsString = xmsString.replace("</ModifyPURequest>", "</ns2:ModifyPURequest>");
        return xmsString;
    }

    public ModifyPUResult parseResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("<res:ModifyPUResponse xmlns:res='http://www.dhl.com' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation= 'http://www.dhl.com pickup-res.xsd'>", "<ModifyPUResponse>");
        replaceMap.put("</res:ModifyPUResponse>", "</ModifyPUResponse>");
        replaceMap.put("<res:PickupErrorResponse xmlns:res='http://www.dhl.com' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation= 'http://www.dhl.com pickup-err-res.xsd'>", "<PickupErrorResponse>");
        replaceMap.put("</res:PickupErrorResponse>", "</PickupErrorResponse>");
        xmlResponse = AppUtils.replaceStringByMap(replaceMap, xmlResponse);
        ModifyPUResult modifyPUResult = new ModifyPUResult();
        ModifyPUResponse modifyPUResponse = null;
        PickupErrorResponse pickupErrorResponse = null;
        if (AppUtils.checkTagName(xmlResponse, "PickupErrorResponse")) {
            pickupErrorResponse = AppUtils.xmlString2Object(xmlResponse, PickupErrorResponse.class);
        } else {
            modifyPUResponse = AppUtils.xmlString2Object(xmlResponse, ModifyPUResponse.class);
        }
        modifyPUResult.setModifyPUResponse(modifyPUResponse);
        modifyPUResult.setPickupErrorResponse(pickupErrorResponse);
        return modifyPUResult;
    }

    public String getErrorMessage(PickupErrorResponse errorResponse) {
        String errorMsg = "";
        for (Condition condition : errorResponse.getResponse().getStatus().getCondition()) {
            errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
        }
        return errorMsg;
    }
}
