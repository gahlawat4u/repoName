package com.gms.delivery.startrack.service;

import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.delivery.startrack.xmlpi.tracking.response.GetConsignmentDetailResponse;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.StarTrackConstants;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Posted from Jun 23, 2016 11:17:10 AM
 * <p>
 * Author dattrinh
 */
public class StarTrackTrackingService {
    public String execute(String xmlRequest, String xmlResponseCached) {
        if (!StringUtils.isBlank(xmlResponseCached)) {
            return xmlResponseCached;
        }
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xmlRequest, "/startrack_tracking_request_" + uniqueString + ".xml");
        String xmlResponse;
        PostMethod post = null;
        try {
            post = new PostMethod(AppConstants.APP_SETTINGS.getStarTrackTrackingAPI_1_0());
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            post.addParameter("xml", xmlRequest);
            post.addParameter("url", SystemSettingCache.getInstance().getValueByKey(StarTrackConstants.STARTRACK_TRACKING_URL));

            // Send request to get access key.
            int resultCode = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
            if (resultCode == 200) {
                xmlResponse = post.getResponseBodyAsString();
                AppUtils.writeToFileByDate(xmlResponse, "/startrack_tracking_response_" + uniqueString + ".xml");
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

    public GetConsignmentDetailResponse parseResponse(String xmlResponse) throws Exception {
        if (StringUtils.isBlank(xmlResponse)) {
            return null;
        }
        xmlResponse = xmlResponse.replaceAll("xmlns:ns(\\d{1,2})", "xmlns");
        xmlResponse = xmlResponse.replaceAll("<ns(\\d{1,2}):", "<");
        xmlResponse = xmlResponse.replaceAll("</ns(\\d{1,2}):", "</");
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put(":SOAP-ENV", "");
        replaceMap.put("xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"", "");
        replaceMap.put("xmlns=\"http://startrackexpress/Common/actions/externals/Consignment/v1\"", "");
        replaceMap.put("xmlns=\"http://startrackexpress/Common/FreightProcessing/v1\"", "");
        replaceMap.put("xmlns=\"http://startrackexpress/Common/Primitives/v1\"", "");
        replaceMap.put("xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"", "");
        replaceMap.put("xmlns=\"http://startrackexpress/Common/Imaging/v1\"", "");
        xmlResponse = AppUtils.replaceStringByMap(replaceMap, xmlResponse);
        replaceMap.clear();
        replaceMap.put("<SOAP-ENV:Envelope", "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"");
        replaceMap.put("xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\"", "");
        xmlResponse = AppUtils.replaceStringByMap(replaceMap, xmlResponse);
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xmlResponse.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(GetConsignmentDetailResponse.class).createUnmarshaller();
        GetConsignmentDetailResponse bookingResponse = (GetConsignmentDetailResponse) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
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
        if (fault.getDetail() != null && fault.getDetail().getFirstChild() != null) {
            Node first = fault.getDetail().getFirstChild();
            NodeList nodeList = first.getChildNodes();
            errorMessage = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                if ("ns:fs_message".equalsIgnoreCase(nodeList.item(i).getNodeName())) {
                    errorMessage += nodeList.item(i).getTextContent() + "\n";
                }
                System.out.println(nodeList.item(i).getNodeName());
            }
        }
        return errorMessage;
    }
}
