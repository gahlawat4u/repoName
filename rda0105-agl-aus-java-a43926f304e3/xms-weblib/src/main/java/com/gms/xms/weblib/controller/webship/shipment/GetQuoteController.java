package com.gms.xms.weblib.controller.webship.shipment;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentInfoModel;
import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.model.webship.ship.SaveQuoteModel;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;
import com.gms.xms.txndb.vo.webship.ship.SaveQuoteVo;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.client.GetQuoteClient;
import com.gms.xms.workflow.client.integration.request.GetQuoteRequest;
import com.gms.xms.workflow.client.integration.response.GetQuoteResponse;
import com.gms.xms.workflow.service.webship.quotejob.IQuoteJobService;
import com.gms.xms.workflow.service.webship.quotejob.QuoteJobServiceImp;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * Posted from GetQuoteController
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public class GetQuoteController extends ShipmentController {
    private static final Log log = LogFactory.getLog(GetQuoteController.class);
    private static final long serialVersionUID = -2483961826971801508L;
    private QuoteModel quoteModel;
    private DhlCapabilityVo dhlCapabilityVo;
    private String quoteModelJson;
    private String saveQuoteInfoJson;

    public String getQuote() {
        Locale.setDefault(Locale.ENGLISH);
        GetQuoteRequest getQuoteRequest = new GetQuoteRequest();
        GetQuoteClient getQuoteClient = new GetQuoteClient(this.getAddInfoMap());
        WebshipLoginVo webshipLoginInfo = this.getWebshipLoginInfo();
        try {
            if (!this.validateShipmentRequest()) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "field_error";
            }

            if(!StringUtils.equals(getShipmentPage().getSenderAddress().getCountry(),"12")
                    && !StringUtils.equals(getShipmentPage().getReceiverAddress().getCountry(),"12")){
                this.addFieldError("shipmentRequest.country", "Please contact your sales consultant at (1300 469 773) or (info.au@AGL.com).");
                return "field_error";
            }
            getQuoteRequest.setShipmentInfoVo(ModelUtils.createVoFromModel(this.getShipmentPage(), ShipmentInfoVo.class));
            getQuoteRequest.setWebshipLoginVo(webshipLoginInfo);
            // old is GetQuoteResponse getQuoteResponse =
            // getQuoteClient.getQuote(getQuoteRequest);
            GetQuoteResponse getQuoteResponse = getQuoteClient.getQuote2(getQuoteRequest);

            if (getQuoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
                this.quoteModel = (ModelUtils.createModelFromVo(getQuoteResponse.getQuoteVo(), QuoteModel.class));
                this.dhlCapabilityVo = getQuoteResponse.getDhlCapabilityVo();
                // add currency symbol and weight unit
                this.quoteModel.setBaseChargeUnit(HelperUtils.formatCurrency(quoteModel.getBaseCharge()));
                for (AccessorialModel accessorial : quoteModel.getAccessorial()) {
                    accessorial.setValueCurrency(HelperUtils.formatCurrency(accessorial.getValue()));
                    if (accessorial.getDescription().equalsIgnoreCase("Additional Protection") && accessorial.getValue().equals("0.00")) {
                        accessorial.setValueCurrency("Not available");
                    }
                }
                this.quoteModel.setTotalChargeUnit(HelperUtils.formatCurrency(quoteModel.getTotalCharge()));
                this.quoteModel.setWeightFomated(quoteModel.getWeight() + "kg(s)");

                ShipmentInfoModel shipmentInfoModel = new ShipmentInfoModel();
                shipmentInfoModel = ModelUtils.createModelFromVo(getQuoteResponse.getShipmentInfoVo(), ShipmentInfoModel.class);
                this.setShipmentPage(shipmentInfoModel);
                this.setquoteModelJson(GsonUtils.toGson(this.quoteModel));

                // Create save quote model and keep it in json string
                // It will be use to save quote
                SaveQuoteModel saveQuoteModel = new SaveQuoteModel();
                saveQuoteModel.setShipment(shipmentInfoModel);
                saveQuoteModel.setQuote(quoteModel);
                this.setSaveQuoteInfoJson(GsonUtils.toGson(saveQuoteModel));
            } else {
                this.setErrorCode(getQuoteResponse.getErrorCode());
                this.setErrorMessage(getQuoteResponse.getErrorMessage());
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public void save() {
        if (StringUtils.isBlank(saveQuoteInfoJson)) {
            return;
        }
        try {
            SaveQuoteModel saveQuoteModel = GsonUtils.fromGson(saveQuoteInfoJson, SaveQuoteModel.class);
            SaveQuoteVo saveQuoteVo = ModelUtils.createVoFromModel(saveQuoteModel, SaveQuoteVo.class);
            QuoteJobVo quoteJobVo = transferSaveQuoteVo2QuoteLogVo(saveQuoteVo);
            IQuoteJobService quoteJobService = new QuoteJobServiceImp();
            quoteJobService.saveQuoteLog(this.getAddInfoMap(), quoteJobVo);
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
    }

    protected QuoteJobVo transferSaveQuoteVo2QuoteLogVo(SaveQuoteVo saveQuoteVo) throws DaoException {
        IQuoteJobService quoteJobService = new QuoteJobServiceImp();
        String prevQuoteNumber = quoteJobService.getPreviousQuoteNumber(this.getWebshipLoginInfo().getCustomerCode());

        WebshipLoginVo loginVo = this.getWebshipLoginInfo();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        QuoteJobVo quoteJobVo = new QuoteJobVo();
        // Get sender and receiver address from shipment
        AddressVo sender = saveQuoteVo.getShipment().getSenderAddress();
        AddressVo receiver = saveQuoteVo.getShipment().getReceiverAddress();
        quoteJobVo.setSenderAddress(sender);
        quoteJobVo.setReceiverAddress(receiver);
        // Get webship quote log info
        if (prevQuoteNumber == null) {
            quoteJobVo.setQuoteNumber(GenCodeUtils.createQuoteJobNumber(String.valueOf(loginVo.getCustomerCode()), ""));
        } else {
            quoteJobVo.setQuoteNumber(GenCodeUtils.createQuoteJobNumber(String.valueOf(loginVo.getCustomerCode()), prevQuoteNumber.substring(8, 12)));
        }
        quoteJobVo.setCustomerCode(loginVo.getCustomerCode());
        quoteJobVo.setWebshipId(loginVo.getWebshipId());
        quoteJobVo.setQuoteDate(now);
        quoteJobVo.setShipmentTypeId(saveQuoteVo.getShipment().getShipmentTypeId());
        quoteJobVo.setPackageId(saveQuoteVo.getShipment().getPackageId());
        quoteJobVo.setContents(saveQuoteVo.getShipment().getContentType());
        quoteJobVo.setWeightUnit(saveQuoteVo.getShipment().getWeightUnit());
        quoteJobVo.setDimensionUnit(saveQuoteVo.getShipment().getDimensionUnit());
        quoteJobVo.setNoOfPieces(saveQuoteVo.getShipment().getPieces().size());
        quoteJobVo.setWithInsurance(saveQuoteVo.getQuote().getInsuranceValue());
        quoteJobVo.setIpAddress(this.getIpAddress());
        quoteJobVo.setQuoteStatus(false); // Need to be change to appropriate
        // status later
        quoteJobVo.setBaseCharge(saveQuoteVo.getQuote().getBaseCharge());
        quoteJobVo.setResidentialDelivery(saveQuoteVo.getShipment().getResidentialDelivery());
        quoteJobVo.setResidentialPickup(saveQuoteVo.getShipment().getResidentialPickup());
        quoteJobVo.setNonStandardCharge(saveQuoteVo.getQuote().getNonStandardCharge()); // Need
        quoteJobVo.setManualHandlingSurcharge(saveQuoteVo.getQuote().getManualHandlingSurcharge());
        quoteJobVo.setTotalCharge(saveQuoteVo.getQuote().getTotalCharge());
        quoteJobVo.setShipDate(saveQuoteVo.getShipment().getShipmentDate());
        quoteJobVo.setExtraService(""); // Need to be change later
        quoteJobVo.setZone(saveQuoteVo.getShipment().getZone());
        // Get list quote pieces
        List<QuotePieceVo> quotePieceVos = new ArrayList<QuotePieceVo>();
        for (PieceVo pieceVo : saveQuoteVo.getShipment().getPieces()) {
            QuotePieceVo quotePieceVo = new QuotePieceVo();
            quotePieceVo.setWeight(pieceVo.getWeight());
            Double dimL = pieceVo.getDimensionL() != null ? pieceVo.getDimensionL() : 0F;
            Double dimW = pieceVo.getDimensionW() != null ? pieceVo.getDimensionW() : 0F;
            Double dimH = pieceVo.getDimensionH() != null ? pieceVo.getDimensionH() : 0F;
            quotePieceVo.setDimensionL(dimL);
            quotePieceVo.setDimensionW(dimW);
            quotePieceVo.setDimensionH(dimH);
            quotePieceVo.setCustomValue(new Double(pieceVo.getCustomValue()));
            if (pieceVo.getQuantity() != null) {
                for (int i = 0; i < pieceVo.getQuantity(); i++) {
                    quotePieceVos.add(quotePieceVo);
                }
            } else {
                quotePieceVos.add(quotePieceVo);
            }
        }
        quoteJobVo.setQuotePieces(quotePieceVos);
        // Get webship quote log detail
        List<WebshipQuoteLogDetailVo> quoteLogDetailVos = new ArrayList<WebshipQuoteLogDetailVo>();
        for (AccessorialVo accessorialVo : saveQuoteVo.getQuote().getAccessorial()) {
            WebshipQuoteLogDetailVo quoteLogDetailVo = new WebshipQuoteLogDetailVo();
            quoteLogDetailVo.setAccessorialId(accessorialVo.getAccessorialId());
            quoteLogDetailVo.setAmount(accessorialVo.getValue());
            quoteLogDetailVo.setType(accessorialVo.getType());
            quoteLogDetailVos.add(quoteLogDetailVo);
        }
        quoteJobVo.setQuoteLogDetails(quoteLogDetailVos);
        return quoteJobVo;
    }

    public QuoteModel getQuoteModel() {
        return quoteModel;
    }

    public void setQuoteModel(QuoteModel quoteModel) {
        this.quoteModel = quoteModel;
    }

    public String getQuoteModelJson() {
        return quoteModelJson;
    }

    public void setQuoteModelJson(String quoteModelJson) {
        this.quoteModelJson = quoteModelJson;
    }

    public String getquoteModelJson() {
        return quoteModelJson;
    }

    public void setquoteModelJson(String quoteModelJson) {
        this.quoteModelJson = quoteModelJson;
    }

    public String getSaveQuoteInfoJson() {
        return saveQuoteInfoJson;
    }

    public void setSaveQuoteInfoJson(String saveQuoteInfoJson) {
        this.saveQuoteInfoJson = saveQuoteInfoJson;
    }

    public DhlCapabilityVo getDhlCapabilityVo() {
        return dhlCapabilityVo;
    }

    public void setDhlCapabilityVo(DhlCapabilityVo dhlCapabilityVo) {
        this.dhlCapabilityVo = dhlCapabilityVo;
    }

}
