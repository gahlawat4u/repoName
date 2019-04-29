package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response.CancelPUResponse;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.DCTResponse;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.DCTResponse.GetCapabilityResponse;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.QtdShpType;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.dto.ErrorInfoVo;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class DhlServiceClient {

    public String executeGetCapability(String xml, String xmlResponse) throws Exception {

        PostMethod post = new PostMethod("https://xmlpi-ea.dhl.com/XMLShippingServlet");
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xml)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String capResponseXml;
            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    capResponseXml = post.getResponseBodyAsString();
                } else {
                    throw new Exception("Fail to perform Capability Service.| errorCode:" + result);
                }
            } else {
                capResponseXml = xmlResponse;
            }
            return capResponseXml;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public CancelPUResponse executeCancelPickup(String xml, String xmlResponse) {
        PostMethod post = new PostMethod("https://xmlpi-ea.dhl.com/XMLShippingServlet");
        post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xml)));
        post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
        try {
            String capResponseXml;
            if (StringUtils.isBlank(xmlResponse)) {
                // HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                capResponseXml = post.getResponseBodyAsString();
            } else {
                capResponseXml = xmlResponse;
            }
            CancelPUResponse cancelPUResponse = AppUtils.xmlString2Object(capResponseXml, CancelPUResponse.class);
            return cancelPUResponse;
        } catch (Exception e) {
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public DhlCapabilityVo getCapability(String shipmentTypeName, String selectedService, String xmlRequest) throws Exception {
        DhlCapabilityVo capabilityVo = null;
        capabilityVo = new DhlCapabilityVo();
        String xmlResponse = null;
        DCTResponse dctResponse = null;
        DhlErrorResponse errorResponse = null;
        capabilityVo.setSelectedService(selectedService);
        xmlResponse = executeGetCapability(xmlRequest, xmlResponse);
        if (AppUtils.checkXmlTagValue(xmlResponse, "ActionStatus", "Error")) {
            errorResponse = AppUtils.xmlString2Object(xmlResponse, DhlErrorResponse.class);
        } else {
            dctResponse = AppUtils.xmlString2Object(xmlResponse, DCTResponse.class);
        }

        String globalProductCode = "";

        if (errorResponse != null) {
            capabilityVo.setActionStatus(errorResponse.getResponse().getStatus().getActionStatus());
            for (Condition condition : errorResponse.getResponse().getStatus().getCondition()) {
                ErrorInfoVo errorInfoVo = new ErrorInfoVo();
                errorInfoVo.setErrCode(condition.getConditionCode());
                errorInfoVo.setErrMsg(condition.getConditionData());
                capabilityVo.getErrorList().add(errorInfoVo);

            }

        } else {

            if ("EXPRESS WORLDWIDE".equalsIgnoreCase(shipmentTypeName) || "EXPRESS WWIDE".equalsIgnoreCase(shipmentTypeName) || "EXPRESS WWIDE EU".equalsIgnoreCase(shipmentTypeName)) {
                shipmentTypeName = "EXPRESS WORLDWIDE";
            }

            if ("EXPRESS JUMBO".equalsIgnoreCase(shipmentTypeName) || "JUMBO BOX".equalsIgnoreCase(shipmentTypeName)) {
                shipmentTypeName = "JUMBO BOX";
            }

            if ("DOM Express 18:00".equalsIgnoreCase(shipmentTypeName)) {
                shipmentTypeName = "DOMESTIC EXPRESS";
            }

            GetCapabilityResponse capabilityResponse = dctResponse.getGetCapabilityResponse();
            if (capabilityResponse != null && capabilityResponse.getBkgDetails() != null && capabilityResponse.getBkgDetails().get(0).getQtdShp() != null) {
                for (QtdShpType qtdShpType : capabilityResponse.getBkgDetails().get(0).getQtdShp()) {
                    String productShortName = qtdShpType.getProductShortName();
                    if (StringUtils.isNotBlank(qtdShpType.getLocalProductName())) {
                        capabilityVo.getAvailSvrs().add(qtdShpType.getLocalProductName());
                    }

                    if ("EXPRESS WORLDWIDE".equalsIgnoreCase(productShortName) || "EXPRESS WWIDE".equalsIgnoreCase(productShortName) || "EXPRESS WWIDE EU".equalsIgnoreCase(productShortName)) {
                        productShortName = "EXPRESS WORLDWIDE";
                    }

                    if (shipmentTypeName.trim().equalsIgnoreCase(productShortName.trim())) {
                        globalProductCode = qtdShpType.getGlobalProductCode();
                        capabilityVo.setGlobalProductCode(qtdShpType.getGlobalProductCode());
                        capabilityVo.setLocalProductCode(qtdShpType.getLocalProductCode());
                        capabilityVo.setTotalTransitDays(qtdShpType.getTotalTransitDays());
                        capabilityVo.setPickupCutoffTime(qtdShpType.getPickupCutoffTime().toString());
                        capabilityVo.setBookingTime(qtdShpType.getBookingTime().toString());
                        capabilityVo.setPickupDate(qtdShpType.getPickupDate().toGregorianCalendar().getTime());
                        capabilityVo.setDeliveryDate(qtdShpType.getDeliveryDate().toGregorianCalendar().getTime());
                        capabilityVo.setFound(true);
                        capabilityVo.setActionStatus(AppConstants.SUCCESS);
                    }

                    if (checkDHLDomestic(shipmentTypeName, productShortName)) {

                        globalProductCode = qtdShpType.getGlobalProductCode();
                        capabilityVo.setGlobalProductCode(qtdShpType.getGlobalProductCode());
                        capabilityVo.setLocalProductCode(qtdShpType.getLocalProductCode());
                        capabilityVo.setTotalTransitDays(qtdShpType.getTotalTransitDays());
                        String pickupCutoffTime = "";
                        try {
                            if (qtdShpType.getPickupCutoffTime().getHours() < 10) {
                                pickupCutoffTime = "0" + String.valueOf(qtdShpType.getPickupCutoffTime().getHours());
                            } else {
                                pickupCutoffTime = String.valueOf(qtdShpType.getPickupCutoffTime().getHours());
                            }

                            if (qtdShpType.getPickupCutoffTime().getMinutes() < 10) {
                                pickupCutoffTime = pickupCutoffTime + ":" + "0" + String.valueOf(qtdShpType.getPickupCutoffTime().getMinutes());
                            } else {
                                pickupCutoffTime = pickupCutoffTime + ":" + String.valueOf(qtdShpType.getPickupCutoffTime().getMinutes());
                            }
                        } catch (Exception e) {

                        }
                        capabilityVo.setPickupCutoffTime(pickupCutoffTime);

                        String bookingTime = "";
                        try {
                            if (qtdShpType.getBookingTime().getHours() < 10) {
                                bookingTime = "0" + String.valueOf(qtdShpType.getBookingTime().getHours());
                            } else {
                                bookingTime = String.valueOf(qtdShpType.getBookingTime().getHours());
                            }
                            if (qtdShpType.getBookingTime().getMinutes() < 10) {
                                bookingTime = bookingTime + ":" + "0" + String.valueOf(qtdShpType.getBookingTime().getMinutes());
                            } else {
                                bookingTime = bookingTime + ":" + String.valueOf(qtdShpType.getBookingTime().getMinutes());
                            }
                        } catch (Exception e) {

                        }
                        capabilityVo.setBookingTime(bookingTime);
                        capabilityVo.setPickupDate(qtdShpType.getPickupDate().toGregorianCalendar().getTime());
                        capabilityVo.setDeliveryDate(qtdShpType.getDeliveryDate().toGregorianCalendar().getTime());
                        capabilityVo.setFound(true);
                        capabilityVo.setActionStatus(AppConstants.SUCCESS);
                    }

                    List<String> dom12s = Arrays.asList(new String[]{"EXPRESS 12:00", "DOM EXPRESS 12:00", "DOME EXPRESS 12:00"});

                    if (dom12s.contains(shipmentTypeName) && dom12s.contains(productShortName) && "T".equalsIgnoreCase(globalProductCode)) {
                        globalProductCode = "1";
                        capabilityVo.setGlobalProductCode(qtdShpType.getGlobalProductCode());
                    }
                }
            }
        }

        return capabilityVo;
    }

    public boolean checkDHLDomestic(String ediname, String capName) {
        ediname = ediname.trim();
        capName = capName.trim();
        List<String> doms = Arrays.asList(new String[]{"DOMESTIC EXPRESS", "DOM EXPRESS 18:00", "DOME EXPRESS 18:00", "DOMESTIC EXPRESS 18:00", "DOMESTIC EXPRESS 18:", "DOMESTIC EXPRESS 18"});
        List<String> dom9s = Arrays.asList(new String[]{"EXPRESS 9:00", "DOM EXPRESS 9:00", "DOME EXPRESS 9:00"});
        List<String> dom12s = Arrays.asList(new String[]{"EXPRESS 12:00", "DOM EXPRESS 12:00", "DOME EXPRESS 12:00"});

        if (doms.contains(ediname) && doms.contains(capName)) {
            return true;
        }
        if (dom9s.contains(ediname) && dom9s.contains(capName)) {
            return true;
        }
        if (dom12s.contains(ediname) && dom12s.contains(capName)) {
            return true;
        }
        if (ediname.equalsIgnoreCase(capName))
            return true;

        return false;

    }
}
