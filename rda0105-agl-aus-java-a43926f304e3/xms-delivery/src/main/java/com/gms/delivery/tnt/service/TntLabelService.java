package com.gms.delivery.tnt.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.tnt.xmlpi.label.request.LabelRequest;
import com.gms.delivery.tnt.xmlpi.label.response.LabelResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import java.io.StringBufferInputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Posted from TntLabelService
 * <p>
 * Author dattrinh Jan 29, 2016 11:31:31 AM
 */
@SuppressWarnings("deprecation")
public class TntLabelService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/tnt_label_request_" + uniqueString + ".xml");
        PostMethod post = null;
        try {
            post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("TNT AU Label URL"));
            String authenticateToken = SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Company") + ":" + SystemSettingCache.getInstance().getValueByKey("TNT AU Web Service Password");
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            post.setRequestHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(authenticateToken.getBytes()));
            post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream("xml_in=" + URLEncoder.encode(xmlRequest, "UTF-8"))));
            String xmlResponse;
            if (StringUtils.isBlank(xmlResponseCached)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    xmlResponse = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(xmlResponse, "/tnt_label_response_" + uniqueString + ".xml");
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

    public LabelResponse parseXmlResponse(String xmlResponse) throws JAXBException {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        return AppUtils.xmlString2Object(xmlResponse, LabelResponse.class);
    }

    public String prepareXmlRequest(Object valueObject) throws JAXBException {
        LabelRequest labelRequest = new LabelRequest();
        // Code business here.
        // Create Consignment.
        // LabelConsignmentsType consignment = new LabelConsignmentsType();
        // consignment.setKey("");
        // labelRequest.getConsignment().add(consignment);
        return AppUtils.Object2XmlString(labelRequest, LabelRequest.class);
    }

    public String getErrorMessage(LabelResponse labelResponse) {
        String errorMessage = "";
        return errorMessage;
    }
}
