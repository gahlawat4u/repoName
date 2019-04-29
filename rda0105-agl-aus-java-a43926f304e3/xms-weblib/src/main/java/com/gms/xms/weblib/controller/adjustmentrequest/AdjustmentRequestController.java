package com.gms.xms.weblib.controller.adjustmentrequest;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.adjustment.SubAdjustmentTypeModel;
import com.gms.xms.model.adjustmentrequest.AdjustmentClaimRequestModel;
import com.gms.xms.model.adjustmentrequest.AirbillAdjustmentRequestModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.AdjustmentType;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.CreditType;
import com.gms.xms.txndb.vo.GstType;
import com.gms.xms.txndb.vo.adjustmentrequest.AdjustmentClaimRequestVo;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustmentrequest.ManageAdjustmentRequestClient;
import com.gms.xms.workflow.client.integration.request.AdjustmentClaimRequest;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from AdjustmentRequestController
 * </p>
 *
 * @author hungnt - Nov 5, 2015
 */
public class AdjustmentRequestController extends JsonBaseController {

    private static final long serialVersionUID = 1150086839303600717L;
    private List<AdjustmentType> adjustmentTypes;
    private List<String> subAdjustmentTypes;
    private List<CreditType> creditTypes;
    private List<GstType> gstTypes;
    private AdjustmentClaimRequestModel adjustmentRequest;
    private AirbillAdjustmentRequestModel adjustmentInfo;
    private AirbillAdjustmentRequestModel adjustmentTotal;
    private String requestList;
    private Integer userLevel;

    private void determineUserLevel() throws Exception {
        ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
        Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
        Double userLevel = client.getUserLevel(userLevelId);
        this.userLevel = userLevel.intValue();
    }

    public String show() {
        try {
            prepareAdjustmentTypes();
            prepareCreditTypes();
            prepareGstTypes();
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return "success";
        }
        if (adjustmentRequest == null) {
            subAdjustmentTypes = new ArrayList<String>();
            return "success";
        }
        try {
            AdjustmentClaimRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentClaimRequestVo.class);
            prepareSubAdjustmentTypes(requestVo.getShipmentId(), requestVo.getAirbillNumber(), 1);
            adjustmentRequest.setAdjustType("1");
            if (subAdjustmentTypes.size() > 0) {
                adjustmentRequest.setAdjustmentType(subAdjustmentTypes.get(0));
                buildAdjustmentInfo();
            }
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "success";
    }

    public String searchByType() {
        prepareAdjustmentTypes();
        prepareCreditTypes();
        prepareGstTypes();
        try {
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return "success";
        }
        if (adjustmentRequest == null) {
            subAdjustmentTypes = new ArrayList<String>();
            return "success";
        }
        try {
            AdjustmentClaimRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentClaimRequestVo.class);
            prepareSubAdjustmentTypes(requestVo.getShipmentId(), requestVo.getAirbillNumber(), requestVo.getAdjustType());
            if (subAdjustmentTypes.size() > 0) {
                adjustmentRequest.setAdjustmentType(subAdjustmentTypes.get(0));
                buildAdjustmentInfo();
            }
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "success";
    }

    public String searchBySubType() {
        prepareAdjustmentTypes();
        prepareCreditTypes();
        prepareGstTypes();
        try {
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return "success";
        }
        if (adjustmentRequest == null) {
            subAdjustmentTypes = new ArrayList<String>();
            return "success";
        }
        try {
            AdjustmentClaimRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentClaimRequestVo.class);
            prepareSubAdjustmentTypes(requestVo.getShipmentId(), requestVo.getAirbillNumber(), requestVo.getAdjustType());
            buildAdjustmentInfo();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "success";
    }

    /**
     * Saves the list of adjustment request
     */
    public void saveAdjustmentRequests() {
        try {
            AdjustmentClaimRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentClaimRequestVo.class);
            String invoiceCode = requestVo.getInvoiceCode();
            List<AdjustmentClaimRequestModel> adjustmentRequestModels = GsonUtils.fromGson(this.requestList, new TypeToken<List<AdjustmentClaimRequestModel>>() {
            }.getType());
            List<AdjustmentClaimRequestVo> adjustmentRequestVos = ModelUtils.createListVoFromModel(adjustmentRequestModels, AdjustmentClaimRequestVo.class);
            AdjustmentClaimRequest request = buildAdjustmentRequest(invoiceCode, adjustmentRequestVos);
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            client.saveAdjustmentRequests(request);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    private AdjustmentClaimRequest buildAdjustmentRequest(String invoiceCode, List<AdjustmentClaimRequestVo> adjustmentRequests) throws Exception {
        AdjustmentClaimRequest request = new AdjustmentClaimRequest();
        CreditNoteVo creditNoteVo = null;

        // Get current date
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        // Check if existing adjustment with credit type is Credit Now or not?
        // 1. And create list of AirbillAdjustmentVo
        List<AirbillAdjustmentRequestVo> adjustmentList = new ArrayList<AirbillAdjustmentRequestVo>();
        boolean hasCreditNow = false;
        for (AdjustmentClaimRequestVo adjustmentRequest : adjustmentRequests) {
            if (!isValidAdjustment(adjustmentRequest)) {
                throw new Exception("Invalid adjustment: you can't create a negative adjustment.");
            }
            AirbillAdjustmentRequestVo airbillAdjustment = new AirbillAdjustmentRequestVo();
            airbillAdjustment.setAdjustmentType(adjustmentRequest.getAdjustmentType());
            airbillAdjustment.setShipmentId(adjustmentRequest.getShipmentId());
            airbillAdjustment.setAirbillNumber(adjustmentRequest.getAirbillNumber());
            airbillAdjustment.setCreateDate(now);
            airbillAdjustment.setCarrierAmount(adjustmentRequest.getCarrierAmount());
            airbillAdjustment.setGstCarrierAmount(adjustmentRequest.getGstCarrierAmount());
            airbillAdjustment.setFranchiseAmount(adjustmentRequest.getFranchiseAmount());
            airbillAdjustment.setGstFranchiseAmount(adjustmentRequest.getGstFranchiseAmount());
            airbillAdjustment.setCustomerAmount(adjustmentRequest.getCustomerAmount());
            airbillAdjustment.setGstCustomerAmount(adjustmentRequest.getGstCustomerAmount());
            airbillAdjustment.setCarrierCredit(0D);
            airbillAdjustment.setGstCarrierCredit(0D);
            airbillAdjustment.setRequestCarrier(adjustmentRequest.getRequestCarrier());
            airbillAdjustment.setNote(adjustmentRequest.getNote());
            airbillAdjustment.setCreditType(adjustmentRequest.getCreditType());
            airbillAdjustment.setApplyGstType(adjustmentRequest.getApplyGstType());
            if (adjustmentRequest.getCreditType() == 1) {
                airbillAdjustment.setStatus((byte) 4); // Approved
                hasCreditNow = true;
            } else {
                airbillAdjustment.setStatus((byte) 1); // Submitted
            }
            adjustmentList.add(airbillAdjustment);
        }
        // 2. Create credit note record if has credit now adjustment
        if (hasCreditNow) {
            creditNoteVo = new CreditNoteVo();
            creditNoteVo.setInvoiceCode(invoiceCode);
            creditNoteVo.setCreateDate(now);
            creditNoteVo.setCreditCode(GenCodeUtils.generateCreditCode(invoiceCode, now));
            creditNoteVo.setStatus(0);
        }

        request.setCreditNote(creditNoteVo);
        request.setAdjustmentList(adjustmentList);

        return request;
    }

    private void buildAdjustmentInfo() throws Exception {
        AdjustmentClaimRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentClaimRequestVo.class);
        AirbillAdjustmentRequestFilter filter = new AirbillAdjustmentRequestFilter();
        filter.setShipmentId(requestVo.getShipmentId());
        filter.setAdjustmentType(requestVo.getAdjustmentType());
        filter.setAdjustType(requestVo.getAdjustType());
        filter.setAirbillNumber(requestVo.getAirbillNumber());
        ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
        // Get total payable of the adjustment type
        AirbillAdjustmentRequestModel adjustmentModel = ModelUtils.createModelFromVo(client.getTotalPayableOfAdjustType(filter), AirbillAdjustmentRequestModel.class);
        this.setAdjustmentInfo(adjustmentModel);
        // Get total adjustable amount of the airbill
        adjustmentModel = ModelUtils.createModelFromVo(client.getAdjustableAmount(filter), AirbillAdjustmentRequestModel.class);
        this.setAdjustmentTotal(adjustmentModel);
    }

    private void prepareSubAdjustmentTypes(Long shipmentId, String airbillNumber, Integer adjustType) throws Exception {
        List<String> subTypes = new ArrayList<String>();
        switch (adjustType) {
            case 1:
                // Accessorial Disputes (Surcharge)
                ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
                List<SubAdjustmentTypeModel> subAdjustTypes = ModelUtils.createListModelFromVo(client.getSubAdjustmentTypesByAWB(shipmentId, airbillNumber), SubAdjustmentTypeModel.class);
                for (SubAdjustmentTypeModel type : subAdjustTypes) {
                    subTypes.add(type.getTypeName());
                }
                break;
            case 2:
                // Base Rate Disputes (Base Charge)
                subTypes.add("Rate Dispute");
                subTypes.add("Service Level");
                break;
            case 3:
                // Service Failure
                subTypes.add("Late Delivery");
                subTypes.add("Lost Shipment");
                subTypes.add("Damaged Shipment");
                break;
            case 4:
                // Weight Disputes
                subTypes.add("Incorrect Weight");
                subTypes.add("Reweight Error");
                subTypes.add("Zero Weight Default(ZWD)");
                break;
            case 5:
                // Full Refund
                subTypes.add("Full Refund");
                break;
            case 6:
                // Partial Refund
                subTypes.add("Partial Refund");
                break;
        }
        this.setSubAdjustmentTypes(subTypes);
    }

    private void prepareAdjustmentTypes() {
        List<AdjustmentType> typesList = new ArrayList<AdjustmentType>();
        typesList.add(new AdjustmentType(1, "Accessorial Disputes"));
        typesList.add(new AdjustmentType(2, "Base Rate Disputes"));
        typesList.add(new AdjustmentType(3, "Service Failure"));
        typesList.add(new AdjustmentType(4, "Weight Disputes"));
        typesList.add(new AdjustmentType(5, "Full Refund"));
        typesList.add(new AdjustmentType(6, "Partial Refund"));
        this.setAdjustmentTypes(typesList);
    }

    private void prepareCreditTypes() {
        List<CreditType> typeList = new ArrayList<CreditType>();
        typeList.add(new CreditType(0, "Upon Carrier approval"));
        this.setCreditTypes(typeList);
    }

    private void prepareGstTypes() {
        List<GstType> typeList = new ArrayList<GstType>();
        typeList.add(new GstType(0, "Non-GST Airbill (Not Applicable)"));
        typeList.add(new GstType(1, "Apply GST on adjustment"));
        this.setGstTypes(typeList);
    }

    private boolean isValidAdjustment(AdjustmentClaimRequestVo adjustmentRequest2) {
        if (adjustmentRequest2.getCarrierAmount() < 0 || adjustmentRequest2.getFranchiseAmount() < 0 || adjustmentRequest2.getCustomerAmount() < 0) {
            return false;
        }
        return true;
    }

    public List<AdjustmentType> getAdjustmentTypes() {
        return adjustmentTypes;
    }

    public void setAdjustmentTypes(List<AdjustmentType> adjustmentTypes) {
        this.adjustmentTypes = adjustmentTypes;
    }

    public List<String> getSubAdjustmentTypes() {
        return subAdjustmentTypes;
    }

    public void setSubAdjustmentTypes(List<String> subAdjustmentTypes) {
        this.subAdjustmentTypes = subAdjustmentTypes;
    }

    public List<CreditType> getCreditTypes() {
        return creditTypes;
    }

    public void setCreditTypes(List<CreditType> creditTypes) {
        this.creditTypes = creditTypes;
    }

    public List<GstType> getGstTypes() {
        return gstTypes;
    }

    public void setGstTypes(List<GstType> gstTypes) {
        this.gstTypes = gstTypes;
    }

    public AdjustmentClaimRequestModel getAdjustmentRequest() {
        return adjustmentRequest;
    }

    public void setAdjustmentRequest(AdjustmentClaimRequestModel adjustmentRequest) {
        this.adjustmentRequest = adjustmentRequest;
    }

    public String getRequestList() {
        return requestList;
    }

    public void setRequestList(String requestList) {
        this.requestList = requestList;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public AirbillAdjustmentRequestModel getAdjustmentInfo() {
        return adjustmentInfo;
    }

    public void setAdjustmentInfo(AirbillAdjustmentRequestModel adjustmentInfo) {
        this.adjustmentInfo = adjustmentInfo;
    }

    public AirbillAdjustmentRequestModel getAdjustmentTotal() {
        return adjustmentTotal;
    }

    public void setAdjustmentTotal(AirbillAdjustmentRequestModel adjustmentTotal) {
        this.adjustmentTotal = adjustmentTotal;
    }
}