package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request.CancelPURequest;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request.Request;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request.ServiceHeader;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response.CancelPUResponse;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response.CancelPUResult;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.PickupErrorResponse;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.delivery.dhl.DhlCancelPickupRequestVo;
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
import java.util.UUID;

/**
 * Posted from DhlCancelPickupService
 * <p>
 * Author dattrinh Jan 19, 2016 10:04:53 AM
 */
@SuppressWarnings("deprecation")
public class DhlCancelPickupService {
    private static final Log log = LogFactory.getLog(DhlCancelPickupService.class);

    public String execute(String xmlRequest, String xmlResponse) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/dhl_cancel_pickup_request_" + uniqueString + ".xml");
        PostMethod post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("DHL URL"));
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xmlRequest)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String capResponseXml;
            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    capResponseXml = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(capResponseXml, "/dhl_cancel_pickup_response_" + uniqueString + ".xml");
                } else {
                    capResponseXml = null;
                }
            } else {
                capResponseXml = xmlResponse;
            }
            return capResponseXml;
        } catch (Exception e) {
            log.error("Fail to perfrom DHL cancel pickup service.", e);
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public String parseRequest(DhlCancelPickupRequestVo cancelPickupRequestVo) throws JAXBException, DaoException, DatatypeConfigurationException, Exception {
        CancelPURequest cancelPURequest = new CancelPURequest();
        cancelPURequest.setSchemaVersion(new BigDecimal("1.0").setScale(2, RoundingMode.HALF_UP));
        Request request = new Request();
        ServiceHeader serviceHeader = new ServiceHeader();
        serviceHeader.setMessageTime(DateUtils.convertDate2XMLGregorianCalendar(new Date()));
        String messageid = String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime());
        messageid = messageid.substring(0, 30);
        serviceHeader.setMessageReference(messageid);
        serviceHeader.setSiteID(SystemSettingCache.getInstance().getValueByKey("Site ID"));
        serviceHeader.setPassword(SystemSettingCache.getInstance().getValueByKey("DHL Password"));
        request.setServiceHeader(serviceHeader);
        cancelPURequest.setRequest(request);
        cancelPURequest.setConfirmationNumber(Integer.valueOf(cancelPickupRequestVo.getScheduleCollection().getConfirmationNo()));
        cancelPURequest.setRequestorName(cancelPickupRequestVo.getScheduleCollection().getSaddress().getContactName());
        cancelPURequest.setCountryCode(String.valueOf(cancelPickupRequestVo.getScheduleCollection().getSaddress().getCountryDetail().getCountryCode()));
        cancelPURequest.setOriginSvcArea(cancelPickupRequestVo.getShipment().getServiceAreaCodeOrigin());
        cancelPURequest.setReason(cancelPickupRequestVo.getReason());
        if (cancelPickupRequestVo.getDhlCountry() == null) {
            throw new Exception("This schedule collection has invalid address.");
        } else {
            cancelPURequest.setRegionCode(com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.request.RegionCode.valueOf(cancelPickupRequestVo.getDhlCountry().getDhlRegion()));
        }
        cancelPURequest.setPickupDate(DateUtils.convertDateToString(cancelPickupRequestVo.getScheduleCollection().getPickupDate(), "yyyy-MM-dd", null));
        cancelPURequest.setCancelTime(cancelPickupRequestVo.getScheduleCollection().getPickupTime().substring(0, 5));
        return AppUtils.Object2XmlString(cancelPURequest, CancelPURequest.class);
    }

    public CancelPUResult parseResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        CancelPUResult cancelPUResult = new CancelPUResult();
        CancelPUResponse cancelPUResponse = null;
        PickupErrorResponse errorResponse = null;
        if (AppUtils.checkXmlTagValue(xmlResponse, "ActionStatus", "Error")) {
            errorResponse = AppUtils.xmlString2Object(xmlResponse, PickupErrorResponse.class);
        } else {
            cancelPUResponse = AppUtils.xmlString2Object(xmlResponse, CancelPUResponse.class);
        }
        cancelPUResult.setErrorResponse(errorResponse);
        cancelPUResult.setResponse(cancelPUResponse);
        return cancelPUResult;
    }

    public String getErrorMessage(PickupErrorResponse errorResponse) {
        String errorMsg = "";
        for (Condition condition : errorResponse.getResponse().getStatus().getCondition()) {
            errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
        }
        return errorMsg;
    }
}
