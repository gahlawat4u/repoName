package com.gms.delivery.dhl.service;

import com.gms.delivery.dhl.xmlpi.datatype.capability.request.*;
import com.gms.delivery.dhl.xmlpi.datatype.capability.request.BkgDetailsType.Pieces;
import com.gms.delivery.dhl.xmlpi.datatype.capability.request.DCTRequest.GetCapability;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.DCTResponse;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.DCTResponse.GetCapabilityResponse;
import com.gms.delivery.dhl.xmlpi.datatype.capability.response.QtdShpType;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.delivery.httpclient.HttpClientFactory;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.ErrorInfoVo;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class DhlCapabilityServiceClient {
    private static final Log log = LogFactory.getLog(DhlCapabilityServiceClient.class);

    public String parseCapabilityXMLRequest(ShipmentInfoVo shipmentInfoVo) throws JAXBException, DaoException, DatatypeConfigurationException {
        Double totalShipmentValue = 0D;
        Double totalWeight = 0D;
        Double itemWeight = 0D;
        Integer itemQuantity = 0;

        for (PieceVo pieceVo : shipmentInfoVo.getPieces()) {
            totalShipmentValue += pieceVo.getCustomValue();
            itemWeight = pieceVo.getWeight() == null ? 0 : pieceVo.getWeight();
            itemQuantity = pieceVo.getQuantity() == null ? 1 : pieceVo.getQuantity();
            itemQuantity = (itemQuantity == 0) ? 1 : itemQuantity;
            totalWeight += itemWeight * itemQuantity;
        }

        DCTRequest dctRequest = new DCTRequest();
        GetCapability capability = new GetCapability();
        Request request = new Request();

        DCTFrom dctFrom = new DCTFrom();
        BkgDetailsType bkgDetails = new BkgDetailsType();
        DCTTo dctTo = new DCTTo();
        DCTDutiable dctDutiable = new DCTDutiable();
        // begin content
        ServiceHeader serviceHeader = new ServiceHeader();
        serviceHeader.setSiteID(SystemSettingCache.getInstance().getValueByKey("Site ID"));
        serviceHeader.setPassword(SystemSettingCache.getInstance().getValueByKey("DHL Password"));
        // dctFrom
        dctFrom.setCity(shipmentInfoVo.getSenderAddress().getCity());
        dctFrom.setPostalcode(shipmentInfoVo.getSenderAddress().getPostalCode());
        ICountryService countryService = new CountryServiceImp();
        CountryVo senderCountryVo = countryService.getCountryByCountryId(shipmentInfoVo.getSenderAddress().getCountry());
        dctFrom.setCountryCode(senderCountryVo.getCountryCode());
        dctFrom.setCity(shipmentInfoVo.getSenderAddress().getCity());
        dctFrom.setPostalcode(shipmentInfoVo.getSenderAddress().getPostalCode());
        // bkgDetails
        bkgDetails.setPaymentCountryCode(senderCountryVo.getCountryCode());
        bkgDetails.setDate(DateUtils.convertDateToString(shipmentInfoVo.getShipmentDate(), "yyyy-MM-dd", null));
        DatatypeFactory datatypeFactory = null;
        datatypeFactory = DatatypeFactory.newInstance();
        Duration duration = datatypeFactory.newDuration("PT13H21M");
        bkgDetails.setReadyTime(duration);
        bkgDetails.setDimensionUnit("CM");
        bkgDetails.setWeightUnit("KG");
        PieceType pieceType = new PieceType();
        pieceType.setPieceID("1");
        pieceType.setWeight(new BigDecimal(totalWeight).setScale(3, RoundingMode.HALF_UP));
        Pieces pieces = new Pieces();
        pieces.getPiece().add(pieceType);
        bkgDetails.setPieces(pieces);

        if (!"DOX".equalsIgnoreCase(shipmentInfoVo.getContentType())) {
            bkgDetails.setIsDutiable("Y");
        } else {
            bkgDetails.setIsDutiable("N");
        }
        bkgDetails.setNetworkTypeCode("AL");
        // DctTo
        dctTo.setCity(shipmentInfoVo.getReceiverAddress().getCity());
        dctTo.setPostalcode(shipmentInfoVo.getReceiverAddress().getPostalCode());
        CountryVo receiveCountryVo = countryService.getCountryByCountryId(shipmentInfoVo.getReceiverAddress().getCountry());
        dctTo.setCountryCode(receiveCountryVo.getCountryCode());
        // dctDutiable
        if ("Y".equalsIgnoreCase(bkgDetails.getIsDutiable())) {
            dctDutiable.setDeclaredValue((new BigDecimal(totalShipmentValue).setScale(3, RoundingMode.HALF_UP)));
            dctDutiable.setDeclaredCurrency(shipmentInfoVo.getCurrencyCode());
        } else {
            dctDutiable = null;
        }
        // end content
        request.setServiceHeader(serviceHeader);
        capability.setRequest(request);
        capability.setFrom(dctFrom);
        capability.setBkgDetails(bkgDetails);
        capability.setTo(dctTo);
        capability.setDutiable(dctDutiable);
        dctRequest.setGetCapability(capability);
        return AppUtils.Object2XmlString(dctRequest, DCTRequest.class);
    }

    public String executeGetCapability(String xml, String xmlResponse) throws Exception {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        AppUtils.writeToFileByDate(xml, "/dhl_cap_request_" + uniqueString + ".xml");
        String capResponseXml = null;
        PostMethod post = new PostMethod(SystemSettingCache.getInstance().getValueByKey("DHL URL"));
        try {
            post.setRequestEntity(new InputStreamRequestEntity(new StringBufferInputStream(xml)));
            post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");

            if (StringUtils.isBlank(xmlResponse)) {
                int result = HttpClientFactory.getInstance().getHttpClient().executeMethod(post);
                if (result == 200) {
                    capResponseXml = post.getResponseBodyAsString();
                    AppUtils.writeToFileByDate(capResponseXml, "/dhl_cap_response_" + uniqueString + ".xml");
                } else {
                    capResponseXml = null;
                }
            } else {
                capResponseXml = xmlResponse;
            }

            return capResponseXml;
        } catch (Exception e) {
            log.error("Fail to perfrom DHL capability service.", e);
            return capResponseXml;
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
        boolean haveResult = true;
        capabilityVo.setSelectedService(selectedService);
        xmlResponse = executeGetCapability(xmlRequest, xmlResponse);

        if (StringUtils.isBlank(xmlResponse)) {
            haveResult = false;
        } else {
            if (AppUtils.checkXmlTagValue(xmlResponse, "ActionStatus", "Error")) {
                errorResponse = AppUtils.xmlString2Object(xmlResponse, DhlErrorResponse.class);
            } else {
                dctResponse = AppUtils.xmlString2Object(xmlResponse, DCTResponse.class);
            }
        }

        String globalProductCode = "";

        if (!haveResult) {
            capabilityVo.setActionStatus("Error");
            ErrorInfoVo errorInfoVo = new ErrorInfoVo();
            errorInfoVo.setErrCode("DHLCAP001");
            errorInfoVo.setErrMsg("Fail to get DHL Capability Service");
            capabilityVo.getErrorList().add(errorInfoVo);
            return capabilityVo;
        }

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
            if (capabilityResponse.getNote() != null && capabilityResponse.getNote().size() > 0) {
                if (capabilityResponse.getNote().get(0).getCondition().size() > 0) {
                    capabilityVo.setActionStatus("Error");
                    ErrorInfoVo errorInfoVo = new ErrorInfoVo();
                    errorInfoVo.setErrCode(capabilityResponse.getNote().get(0).getCondition().get(0).getConditionCode());
                    errorInfoVo.setErrMsg(capabilityResponse.getNote().get(0).getCondition().get(0).getConditionData());
                    capabilityVo.getErrorList().add(errorInfoVo);
                    return capabilityVo;
                }
            }

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

                    List<String> dom12s = Arrays.asList(new String[]{"EXPRESS DOMESTIC 12:00", "EXPRESS 12:00", "DOM EXPRESS 12:00", "DOME EXPRESS 12:00"});

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
        List<String> doms = Arrays.asList(new String[]{"EXPRESS DOMESTIC", "EXPRESS DOMESTIC 18:00", "EXPRESS DOMESTIC 18", "DOMESTIC EXPRESS", "DOM EXPRESS 18:00", "DOME EXPRESS 18:00", "DOMESTIC EXPRESS 18:00", "DOMESTIC EXPRESS 18:", "DOMESTIC EXPRESS 18"});
        List<String> dom9s = Arrays.asList(new String[]{"EXPRESS DOMESTIC 9:00", "EXPRESS 9:00", "DOM EXPRESS 9:00", "DOME EXPRESS 9:00"});
        List<String> dom12s = Arrays.asList(new String[]{"EXPRESS DOMESTIC 12:00", "EXPRESS 12:00", "DOM EXPRESS 12:00", "DOME EXPRESS 12:00"});

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
