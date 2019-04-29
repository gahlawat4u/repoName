package com.gms.delivery.tnt.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tnt.xmlpi.tracking.request.MarketTypeType;
import com.gms.delivery.tnt.xmlpi.tracking.request.ObjectFactory;
import com.gms.delivery.tnt.xmlpi.tracking.request.TrackRequest;
import com.gms.delivery.tnt.xmlpi.tracking.request.TrackRequest.LevelOfDetail;
import com.gms.delivery.tnt.xmlpi.tracking.request.TrackRequest.SearchCriteria;
import com.gms.delivery.tnt.xmlpi.tracking.response.TrackResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.StringBufferInputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Posted from TntTrackingService
 * <p>
 * Author dattrinh Jan 28, 2016 5:33:47 PM
 */
@SuppressWarnings("deprecation")
public class TntTrackingService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/tnt_intl_tracking_request_" + uniqueString + ".xml");
        PostMethod post = null;
        try {
            String xmlResponse;
            post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("TNT AU Tracking URL"));
            String authenticateToken = SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Company") + ":" + SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Password");
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            post.setRequestHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(authenticateToken.getBytes()));
            post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream("xml_in=" + URLEncoder.encode(xmlRequest, "UTF-8"))));
            if (StringUtils.isBlank(xmlResponseCached)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    xmlResponse = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(xmlResponse, "/tnt_intl_tracking_response_" + uniqueString + ".xml");
                } else {
                    xmlResponse = null;
                }
            } else {
                xmlResponse = xmlResponseCached;
            }
            return xmlResponse;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public TrackResponse parseXmlResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        return AppUtils.xmlString2Object(xmlResponse, TrackResponse.class);
    }

    public String prepareXmlRequest(String airbillNumber, String countryCode) throws JAXBException {
        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setVersion(3.1f);
        trackRequest.setLocale("en_US");
        // Create SearchCriteria.
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setOriginCountry(countryCode);
        searchCriteria.setMarketType(MarketTypeType.INTERNATIONAL);
        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<String> consignmentNumber = objectFactory.createTrackRequestSearchCriteriaConsignmentNumber(airbillNumber);
        searchCriteria.getContent().add(consignmentNumber);
        trackRequest.setSearchCriteria(searchCriteria);
        // Create LevelOfDetails.
        LevelOfDetail levelOfDetail = new LevelOfDetail();
        TrackRequest.LevelOfDetail.Complete complete = new TrackRequest.LevelOfDetail.Complete();
        complete.setShipment(true);
        complete.setPackage(true);
        complete.setOriginAddress(true);
        complete.setDestinationAddress(true);
        complete.setPodImage(true);
        levelOfDetail.setComplete(complete);
        trackRequest.setLevelOfDetail(levelOfDetail);
        return AppUtils.Object2XmlString(trackRequest, TrackRequest.class);
    }

    public String getErrorMessage(TrackResponse trackResponse) {
        String errorMessage = "";
        return errorMessage;
    }
}
