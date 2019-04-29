package com.gms.xms.workflow.client;

import com.gms.delivery.dhl.xmlpi.datatype.Piece;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.client.integration.request.GetQuoteRequest;
import com.gms.xms.workflow.client.integration.response.GetQuoteResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.task2.ups.international.GetUpsIntlSurchargeTask;
import com.gms.xms.workflow.utils.ShipmentUtils;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Posted from GetQuoteClient
 * <p>
 * Author HungNT Date Apr 18, 2015
 */
public class GetQuoteClient extends WorkflowBaseClient {
    public GetQuoteClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public GetQuoteResponse getQuote(GetQuoteRequest getQuoteRequest) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        GetQuoteResponse getQuoteResponse = new GetQuoteResponse();
        ShipmentInfoVo shipmentInfoVo = getQuoteRequest.getShipmentInfoVo();
        Integer bound = ShipmentUtils.getBound(shipmentInfoVo.getSenderAddress().getCountry(), shipmentInfoVo.getReceiverAddress().getCountry());
        shipmentInfoVo.setBound(bound);

        if (shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
            shipmentInfoVo.setContents(0);
        } else {
            shipmentInfoVo.setContents(1);
        }

        context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
        String serviceId = String.valueOf(shipmentInfoVo.getServiceId());
        context.put(Attributes.USER_LOGGIN_INFO, GsonUtils.toGson(getQuoteRequest.getWebshipLoginVo()));
        switch (serviceId) {
            case "15":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticCharge");
                break;
            case "1":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlIntlCharge");
                break;
            case "54":
                context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlCharge");
                break;
            case "3":
                context.put(Attributes.WFP_NAME, "Wfl-GetTntDomesticCharge");
                break;
            case "59":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollIpecCharge");
                break;
            case "16":
                context.put(Attributes.WFP_NAME, "Wfl-GetHiTransCharge");
                break;
            case "17":
                context.put(Attributes.WFP_NAME, "Wfl-GetCouriersPleaseCharge");
                break;
            case "18":
                context.put(Attributes.WFP_NAME, "Wfl-GetDirectCouriersCharge");
                break;
            case "19":
                context.put(Attributes.WFP_NAME, "Wfl-GetCapitalTransportCharge");
                break;
            case "20":
                context.put(Attributes.WFP_NAME, "Wfl-GetFastwayCharge");
                break;
            case "26":
                context.put(Attributes.WFP_NAME, "Wfl-GetFollowmontTransportCharge");
                break;
            case "27":
                context.put(Attributes.WFP_NAME, "Wfl-GetCopeTransportCharge");
                break;
            case "36":
                context.put(Attributes.WFP_NAME, "Wfl-GetNorthlineCharge");
                break;
            case "52":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityCharge");
                break;
            case "400":
                context.put(Attributes.WFP_NAME, "Wfl-GetUpsCharge");
                break;
            default:
                getQuoteResponse.setErrorCode(ErrorCode.ERROR);
                getQuoteResponse.setErrorMessage("This service is under construction. Please try again later.");
                return getQuoteResponse;
        }
        context = WorkFlowManager.getInstance().process(context);

        getQuoteResponse.setErrorCode(context.get(Attributes.ERROR_CODE));
        getQuoteResponse.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        if (getQuoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            QuoteVo quoteVo = new QuoteVo();
            quoteVo = GsonUtils.fromGson(context.get(Attributes.QUOTE_VO), QuoteVo.class);
            shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            getQuoteResponse.setQuoteVo(quoteVo);
            getQuoteResponse.setShipmentInfoVo(shipmentInfoVo);
            if (context.get(Attributes.DHL_CAPABILITY_RESULT) != null) {
                DhlCapabilityVo capabilityVo = GsonUtils.fromGson(context.get(Attributes.DHL_CAPABILITY_RESULT), DhlCapabilityVo.class);
                getQuoteResponse.setDhlCapabilityVo(capabilityVo);
            }
        }
        return getQuoteResponse;
    }

    public GetQuoteResponse getQuote2(GetQuoteRequest getQuoteRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        GetQuoteResponse getQuoteResponse = new GetQuoteResponse();
        ShipmentInfoVo shipmentInfoVo = getQuoteRequest.getShipmentInfoVo();
        Integer bound = ShipmentUtils.getBound(shipmentInfoVo.getSenderAddress().getCountry(), shipmentInfoVo.getReceiverAddress().getCountry());
        shipmentInfoVo.setBound(bound);

        if (shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
            shipmentInfoVo.setContents(0);
        } else {
            shipmentInfoVo.setContents(1);
        }

        context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
        String serviceId = String.valueOf(shipmentInfoVo.getServiceId());
        WebshipLoginVo webshipLoginVo = getQuoteRequest.getWebshipLoginVo();
        context.put(Attributes.USER_LOGGIN_INFO, webshipLoginVo);
        switch (serviceId) {
            case "15":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticCharge");
                break;
            case "1":
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlIntlCharge");
                break;
            case "54":
                context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlCharge");
                break;
            case "3":
                if (shipmentInfoVo.getShipmentTypeId() == 23) {
                    getQuoteResponse.setErrorCode(ErrorCode.ERROR);
                    String franchiseCode = String.valueOf(webshipLoginVo.getCustomerCode()).substring(0, 3) + "00001";
                    ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
                    CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));
                    getQuoteResponse.setErrorMessage("Please contact your freight consultant at (" + customerAddressVo.getPhone() + ") or (" + customerAddressVo.getEmail() + ").");
                    return getQuoteResponse;
                }
                context.put(Attributes.WFP_NAME, "Wfl-GetTntDomesticCharge");
                break;
            case "59":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollIpecCharge");
                break;
            case "52":
                context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityCharge");
                break;
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            case "26":
            case "27":
            case "36":
                getQuoteResponse.setErrorCode(ErrorCode.ERROR);
                getQuoteResponse.setIsOtherCarrier(true);
                String franchiseCode = String.valueOf(webshipLoginVo.getCustomerCode()).substring(0, 3) + "00001";
                ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
                CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));
                getQuoteResponse.setErrorMessage("Please contact your freight consultant at (" + customerAddressVo.getPhone() + ") or (" + customerAddressVo.getEmail() + ").");
                return getQuoteResponse;
            case "72":
                context.put(Attributes.WFP_NAME, "Wfl-GetStartrackCharge");
                break;
            case "400":
               /* getQuoteResponse = checkUpsExceptionCase(shipmentInfoVo);
                if (getQuoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.ERROR)) {
                    return getQuoteResponse;
                }*/
                context.put(Attributes.WFP_NAME, "Wfl-GetUpsCharge");
                break;
            default:
                getQuoteResponse.setErrorCode(ErrorCode.ERROR);
                getQuoteResponse.setErrorMessage("This service is under construction. Please try again later.");
                return getQuoteResponse;
        }
        context = WorkFlowManager2.getInstance().process(context);

        getQuoteResponse.setErrorCode(context.getString(Attributes.ERROR_CODE));
        getQuoteResponse.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
        if (getQuoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            QuoteVo quoteVo = new QuoteVo();
            quoteVo = context.get(Attributes.QUOTE_VO);
            shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            getQuoteResponse.setQuoteVo(quoteVo);
            getQuoteResponse.setShipmentInfoVo(shipmentInfoVo);
        }
        return getQuoteResponse;
    }

    private GetQuoteResponse checkUpsExceptionCase(ShipmentInfoVo shipmentInfoVo) {
        GetQuoteResponse getQuoteResponse = new GetQuoteResponse();
        setupCommonQuoteResponse(getQuoteResponse, null, ErrorCode.SUCCESS);

        if (checkUpsFallInLargeSurcharge(shipmentInfoVo) || checkUpsFallInEnvelop(shipmentInfoVo)) {
            setupCommonQuoteResponse(getQuoteResponse, SystemSettingCache.getInstance().getValueByKey("UPS Fall Large Package Charge"), ErrorCode.ERROR);
            getQuoteResponse.setUpsLargeCharger(true);
        }
        return getQuoteResponse;
    }

    private boolean checkUpsFallInEnvelop(ShipmentInfoVo shipmentInfoVo) {
        if(shipmentInfoVo.getContents() == 0){
            return true;
        }
        return false;
    }

    private GetQuoteResponse setupCommonQuoteResponse(GetQuoteResponse getQuoteResponse, String message, String code) {
        getQuoteResponse.setErrorCode(code);
        getQuoteResponse.setErrorMessage(message);
        return getQuoteResponse;
    }

    private boolean checkUpsFallInLargeSurcharge(ShipmentInfoVo shipmentInfoVo){
        List<PieceVo> pieceVos = shipmentInfoVo.getPieces();
        Double largeMinimumDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Minimum Dimensions Data"));
        Double largeMaxDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Maximum Dimensions Data"));
        Double largeMinDeadWeight = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Dead Weight Apply"));
        Double largeMinLDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Dimensions L Apply"));

        GetUpsIntlSurchargeTask getUpsIntlSurchargeTask = new GetUpsIntlSurchargeTask();
        BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(shipmentInfoVo.getServiceId(), shipmentInfoVo.getShipmentTypeId());

        for (PieceVo pieceVo : pieceVos) {
            Boolean isLargeSurchargeApply = getUpsIntlSurchargeTask.checkLargePackageSurcharge(shipmentInfoVo, pieceVo, largeMinimumDimension,
                    largeMinDeadWeight, largeMinLDimension
                    , shipmentInfoVo.getDimensionUnit(), shipmentInfoVo.getWeightUnit());
            if (isLargeSurchargeApply) {
                return true;
            }
        }
        return false;
    }
}