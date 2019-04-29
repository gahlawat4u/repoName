package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.tracking.request.KnownTrackingRequest;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.request.LevelOfDetails;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.request.Request;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.request.ServiceHeader;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.ErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.TrackingResponse;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.dto.delivery.dhl.DhlTrackingRequestVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.StringBufferInputStream;
import java.util.UUID;

/**
 * Posted from DhlTrackingService
 * <p>
 * Author dattrinh Jan 19, 2016 11:24:42 AM
 */
@SuppressWarnings("deprecation")
public class DhlTrackingService {
    private static final Log log = LogFactory.getLog(DhlTrackingService.class);

    public String execute(String xmlRequest, String xmlResponse) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/dhl_tracking_request_" + uniqueString + ".xml");
        PostMethod post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("DHL URL"));
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xmlRequest)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String capResponseXml;
            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    capResponseXml = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(capResponseXml, "/dhl_tracking_response_" + uniqueString + ".xml");
                } else {
                    capResponseXml = null;
                }
            } else {
                capResponseXml = xmlResponse;
            }
            return capResponseXml;
        } catch (Exception e) {
            log.error("Fail to perfrom DHL tracking service.", e);
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public String parseRequest(DhlTrackingRequestVo trackingRequestVo) throws JAXBException, DaoException, DatatypeConfigurationException {
        KnownTrackingRequest trackingRequest = new KnownTrackingRequest();
        Request request = new Request();
        ServiceHeader serviceHeader = new ServiceHeader();
        serviceHeader.setSiteID(SystemSettingCache.getInstance().getValueByKey("Site ID"));
        serviceHeader.setPassword(SystemSettingCache.getInstance().getValueByKey("DHL Password"));
        request.setServiceHeader(serviceHeader);
        trackingRequest.setRequest(request);
        trackingRequest.setLanguageCode(trackingRequestVo.getLangCode());
        trackingRequest.getAWBNumber().add(trackingRequestVo.getAirbillNumber());
        trackingRequest.setLevelOfDetails(LevelOfDetails.ALL_CHECK_POINTS);
        return AppUtils.Object2XmlString(trackingRequest, KnownTrackingRequest.class);
    }

    public TrackingResponse parseResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        TrackingResponse trackingResponse = AppUtils.xmlString2Object(xmlResponse, TrackingResponse.class);
        return trackingResponse;
    }

    public String getErrorMessage(ErrorResponse errorResponse) {
        String errorMsg = "";
        for (Condition condition : errorResponse.getStatus().getCondition()) {
            errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
        }
        return errorMsg;
    }
}
