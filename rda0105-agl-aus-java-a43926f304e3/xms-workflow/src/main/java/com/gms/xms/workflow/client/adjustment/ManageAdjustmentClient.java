package com.gms.xms.workflow.client.adjustment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.adjustment.AirbillAdjustmentDetailModel;
import com.gms.xms.model.adjustment.AirbillAdjustmentTotalModel;
import com.gms.xms.model.adjustment.CarrierAdjustmentModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.persistence.dao.SubAdjustmentTypeDao;
import com.gms.xms.persistence.dao.UserLevelDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.adjustment.*;
import com.gms.xms.workflow.client.WorkflowBaseClient;
import com.gms.xms.workflow.client.integration.request.AdjustmentRequest;
import com.gms.xms.workflow.client.integration.request.CarrierAdjustmentRequest;
import com.gms.xms.workflow.client.integration.response.CarrierAdjustmentResponse;
import com.gms.xms.workflow.client.integration.response.ManageAdjustmentResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ManageAdjustmentClient
 * <p>
 * Author DatTV Date May 13, 2015 10:51:30 AM
 */
public class ManageAdjustmentClient extends WorkflowBaseClient {
    public ManageAdjustmentClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public Long countAWBAdjustmentByFilter(AirbillAdjustmentDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-CountAWBAdjustmentByFilter");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            return Long.valueOf(context.get(Attributes.AIRBILL_ADJUSTMENT_COUNT));
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public ManageAdjustmentResponse getAWBAdjustmentByFilter(AirbillAdjustmentDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetAWBAdjustmentByFilter");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            ManageAdjustmentResponse response = new ManageAdjustmentResponse();
            List<AirbillAdjustmentDetailVo> adjustmentList = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_LIST_RESULT), new TypeToken<List<AirbillAdjustmentDetailVo>>() {
            }.getType());
            AirbillAdjustmentTotalVo totalRecord = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_TOTAL), AirbillAdjustmentTotalVo.class);
            response.setAdjustmentList(adjustmentList);
            response.setTotalRecord(totalRecord);
            return response;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public List<ServiceVo> getServices() throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetServiceList");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<ServiceVo> serviceList = GsonUtils.fromGson(context.get(Attributes.SERVICE_LIST_RESULT), new TypeToken<List<ServiceVo>>() {
            }.getType());
            return serviceList;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public List<AirbillAdjustmentVo> getRequestTypes() throws DaoException {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        return adjustmentDao.selectAdjustmentTypes();
    }

    public List<FranchiseInfoVo> getFranchiseListManagedByUser(String userId) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.USER_ID, userId);
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchiseListManagedByUser");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<FranchiseInfoVo> franchiseInfoVos = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_LIST_RESULT), new TypeToken<List<FranchiseInfoVo>>() {
            }.getType());
            return franchiseInfoVos;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public List<SubAdjustmentTypeVo> getSubAdjustmentTypesByAWB(Long shipmentId, String airbillNumber) throws DaoException {
        SubAdjustmentTypeDao adjustmentTypeDao = new SubAdjustmentTypeDao();
        ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
        shipmentBillingVo.setShipmentId(shipmentId);
        shipmentBillingVo.setAirbillNumber(airbillNumber);
        return adjustmentTypeDao.selectByAWB(shipmentBillingVo);
    }

    public AirbillAdjustmentVo getTotalPayableOfAdjustType(AdjustmentRequestFilterVo filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.ADJUSTMENT_REQUEST_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetTotalPayableOfAdjustType");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            AirbillAdjustmentVo adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_TOTAL_PAYABLE_BY_ADJUST_TYPE), AirbillAdjustmentVo.class);
            return adjustmentVo;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public AirbillAdjustmentVo getAdjustableAmount(AdjustmentRequestFilterVo filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.ADJUSTMENT_REQUEST_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustableAmount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            AirbillAdjustmentVo totalPayable = GsonUtils.fromGson(context.get(Attributes.AIRBILL_TOTAL_PAYABLE), AirbillAdjustmentVo.class);
            AirbillAdjustmentVo totalAdjust = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTED_AMOUNT_TOTAL), AirbillAdjustmentVo.class);
            AirbillAdjustmentVo adjustmentVo = new AirbillAdjustmentVo();

            // Calculate adjustable amount
            Double carrierAmount = totalPayable.getCarrierAmount() > totalAdjust.getCarrierAmount() ? totalPayable.getCarrierAmount() - totalAdjust.getCarrierAmount() : 0;
            Double gstCarrierAmount = totalPayable.getGstCarrierAmount() > totalAdjust.getGstCarrierAmount() ? totalPayable.getGstCarrierAmount() - totalAdjust.getGstCarrierAmount() : 0;
            Double franchiseAmount = totalPayable.getFranchiseAmount() > totalAdjust.getFranchiseAmount() ? totalPayable.getFranchiseAmount() - totalAdjust.getFranchiseAmount() : 0;
            Double gstFranchiseAmount = totalPayable.getGstFranchiseAmount() > totalAdjust.getGstFranchiseAmount() ? totalPayable.getGstFranchiseAmount() - totalAdjust.getGstFranchiseAmount() : 0;
            Double customerAmount = totalPayable.getCustomerAmount() > totalAdjust.getCustomerAmount() ? totalPayable.getCustomerAmount() - totalAdjust.getCustomerAmount() : 0;
            Double gstCustomerAmount = totalPayable.getGstCustomerAmount() > totalAdjust.getGstCustomerAmount() ? totalPayable.getGstCustomerAmount() - totalAdjust.getGstCustomerAmount() : 0;

            // Set amount to return object
            adjustmentVo.setCarrierAmount(MathUtils.round(carrierAmount, 2));
            adjustmentVo.setGstCarrierAmount(MathUtils.round(gstCarrierAmount, 2));
            adjustmentVo.setFranchiseAmount(MathUtils.round(franchiseAmount, 2));
            adjustmentVo.setGstFranchiseAmount(MathUtils.round(gstFranchiseAmount, 2));
            adjustmentVo.setCustomerAmount(MathUtils.round(customerAmount, 2));
            adjustmentVo.setGstCustomerAmount(MathUtils.round(gstCustomerAmount, 2));
            return adjustmentVo;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public void saveAdjustmentRequests(AdjustmentRequest request) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-SaveAdjustmentRequests");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public Double getUserLevel(Integer userLevelId) throws Exception {
        UserLevelDao userLevelDao = new UserLevelDao();
        UserLevelVo userLevelVo = userLevelDao.selectById(userLevelId);
        if (userLevelVo != null) {
            return userLevelVo.getUserLevelCode();
        } else {
            throw new Exception("Invalid user level id.");
        }
    }

    public void updateNoteForAdjustment(Long adjustmentId, String note, Integer noteType, Integer userLevel) throws Exception {
        AirbillAdjustmentVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_RESULT), AirbillAdjustmentVo.class);
            switch (noteType) {
                case 1:
                    adjustmentVo.setReasonForDeleting(note); // Make reason for
                    // deleting note
                    if (userLevel >= 3) {
                        adjustmentVo.setStatus((byte) 7);
                    } else {
                        adjustmentVo.setStatus((byte) 6);
                    }
                    break;
                case 2:
                    adjustmentVo.setFranchiseCommentsToFsc(note); // Make franchise
                    // comments to fsc
                    break;
                case 3:
                    adjustmentVo.setFscCreditNote(note); // Make fsc credit note
                    break;
            }
            context = new ContextBase(this.getAddInfo());
            context.put(Attributes.AIRBILL_ADJUSTMENT_OBJECT, GsonUtils.toGson(adjustmentVo));
            context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment");
            context = WorkFlowManager.getInstance().process(context);
            if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                throw new Exception(context.get(Attributes.ERROR_MESSAGE));
            }
        }
    }

    public void updateSubStatus(Long adjustmentId, Integer userLevel) throws Exception {
        AirbillAdjustmentVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_RESULT), AirbillAdjustmentVo.class);
            if (adjustmentVo.getStatus() == 1) {
                Integer subStatus = adjustmentVo.getSubStatus() == null ? 0 : adjustmentVo.getSubStatus();
                Integer newSubStatus = null;
                if (userLevel < 3) {
                    switch (subStatus) {
                        case 0:
                            newSubStatus = 1;
            /*
			 * // If user level is 1, 2 if (userLevel < 3) {
			 * newSubStatus = subStatus * 10 + 1; // 1: Request
			 * franchise comment } else { newSubStatus = subStatus *
			 * 10 + 2; // 2: Update to FSC }
			 */
                            break;
                        case 1:
                            newSubStatus = 2;
			/*
			 * // If user level is 1, 2 if (userLevel < 3) {
			 * newSubStatus = 0; } else { newSubStatus = 2; }
			 */
                            break;
                        case 2:
                            newSubStatus = 0;
			/*
			 * // If user level is 1, 2 if (userLevel < 3) {
			 * newSubStatus = 1; } else { // No support (no change
			 * sub status) throw new Exception(
			 * "You cannot change this status."); }
			 */
                            break;
                    }
                } else {
                    newSubStatus = 2;
                }
                adjustmentVo.setSubStatus(newSubStatus);
                context = new ContextBase(this.getAddInfo());
                context.put(Attributes.AIRBILL_ADJUSTMENT_OBJECT, GsonUtils.toGson(adjustmentVo));
                context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment");
                context = WorkFlowManager.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                    throw new Exception(context.get(Attributes.ERROR_MESSAGE));
                }
            } else {
                throw new Exception("Cannot update sub status for this adjustment.");
            }
        }
    }

    public AirbillAdjustmentVo getAdjustmentById(Long adjustmentId) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            return GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_RESULT), AirbillAdjustmentVo.class);
        }
    }

    public void updateAdjustment(AirbillAdjustmentVo adjustment) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_OBJECT, GsonUtils.toGson(adjustment));
        context.put(Attributes.WFP_NAME, "Wfl-EditAdjustment");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    /**
     * Get List Carrier Adjustment
     *
     * @param CarrierAdjustmentFilter
     * @return CarrierAdjustmentResponse
     * @throws Exception
     */
    public CarrierAdjustmentResponse manageCarrierAdjustment(CarrierAdjustmentRequest request) throws Exception {
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CARRIER_ADJUSTMENT_FILTER, GsonUtils.toGson(request.getFilter()));
        context.put(Attributes.WFP_NAME, "Wfl-ManageCarrierAdjustment");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            response.setTotalCarrierAdjustment(Integer.parseInt(context.get(Attributes.CARRIER_ADJUSTMENT_COUNT)));
            response.setErrorCode(context.getErrorCode());
            response.setErrorMessage(context.getErrorMessage());
            List<CarrierAdjustmentVo> listCarrierAdjustment = GsonUtils.fromGson(context.get(Attributes.CARRIER_ADJUSTMENT_LIST), new TypeToken<List<CarrierAdjustmentVo>>() {
            }.getType());
            response.setListCarrierAdjustment(listCarrierAdjustment);
        } else {
            response.setErrorCode(context.getErrorCode());
            response.setErrorMessage(context.getErrorMessage());
        }
        return response;
    }

    /**
     * Accept submitted request Carrier Adjustment
     *
     * @param request
     * @return
     * @throws Exception
     */
    public CarrierAdjustmentResponse acceptSubmittedRequests(CarrierAdjustmentRequest request) throws Exception {
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CARRIER_ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-CarrierAdjustmentAcceptSubmittedRequest");
        context = WorkFlowManager.getInstance().process(context);
        response.setErrorType(context.getString(Attributes.ERROR_TYPE));
        response.setErrorCode(context.getErrorCode());
        response.setErrorMessage(context.getErrorMessage());
        return response;
    }

    /**
     * Accept Reject request Carrier Adjustment
     *
     * @param request
     * @return
     * @throws Exception
     */
    public CarrierAdjustmentResponse rejectSubmittedRequests(CarrierAdjustmentRequest request) throws Exception {
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CARRIER_ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-CarrierAdjustmentRejectSubmittedRequest");
        context = WorkFlowManager.getInstance().process(context);
        response.setErrorCode(context.getErrorCode());
        response.setErrorMessage(context.getErrorMessage());
        return response;
    }

    /**
     * Accept Pending request Carrier Adjustment
     *
     * @param request
     * @return
     * @throws Exception
     */
    public CarrierAdjustmentResponse acceptPendingRequests(CarrierAdjustmentRequest request) throws Exception {
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CARRIER_ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-CarrierAdjustmentAcceptPendingRequest");
        context = WorkFlowManager.getInstance().process(context);
        response.setErrorCode(context.getErrorCode());
        response.setErrorMessage(context.getErrorMessage());
        return response;
    }

    /**
     * Reject request Carrier Adjustment
     *
     * @param request
     * @return
     * @throws Exception
     */
    public CarrierAdjustmentResponse rejectPendingRequests(CarrierAdjustmentRequest request) throws Exception {
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CARRIER_ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-CarrierAdjustmentRejectPendingRequest");
        context = WorkFlowManager.getInstance().process(context);
        response.setErrorCode(context.getErrorCode());
        response.setErrorMessage(context.getErrorMessage());
        return response;
    }

    /**
     * getCarrierAdjustmentCountStaticByStatus
     *
     * @return
     * @throws Exception
     */
    public CarrierAdjustmentCountStaticVo getCarrierAdjustmentCountStaticByStatus() throws Exception {
        CarrierAdjustmentCountStaticVo countStaticVo = new CarrierAdjustmentCountStaticVo();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-CarrierAdjustmentCountStaticByStatus");
        context = WorkFlowManager.getInstance().process(context);
        countStaticVo = GsonUtils.fromGson(context.get(Attributes.CARRIER_ADJUSTMENT_RESPONSE), CarrierAdjustmentCountStaticVo.class);
        return countStaticVo;
    }

    public void resubmit(Long adjustmentId) throws Exception {
        AirbillAdjustmentVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_RESULT), AirbillAdjustmentVo.class);
            // If current status is Disputed
            if (adjustmentVo.getStatus() == 3) {
                // Then change it's status to Pending
                adjustmentVo.setStatus((byte) 2);
                context = new ContextBase(this.getAddInfo());
                context.put(Attributes.AIRBILL_ADJUSTMENT_OBJECT, GsonUtils.toGson(adjustmentVo));
                context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment");
                context = WorkFlowManager.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                    throw new Exception(context.get(Attributes.ERROR_MESSAGE));
                }
            } else {
                throw new Exception("The current status of this adjustment is not Disputed so it cannot change to Pending status.");
            }
        }
    }

    public void renderCarrierAdjustmentXLSFile(CarrierAdjustmentRequest carrierAdjustmentRequest, String outPutFilePath) throws Exception {
        CarrierAdjustmentResponse carrierAdjustmentResponse = this.manageCarrierAdjustment(carrierAdjustmentRequest);
        List<CarrierAdjustmentModel> listCarrierAdjustment = ModelUtils.createListModelFromVo(carrierAdjustmentResponse.getListCarrierAdjustment(), CarrierAdjustmentModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("listCarrierAdjustment", listCarrierAdjustment);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/carrier_adjustment/carrier_adjustment.xls", data);
    }

    public void renderManageAdjustmentXLSFile(AirbillAdjustmentDetailFilter filter, String outPutFilePath, String tmpName) throws Exception {
        ManageAdjustmentResponse response = this.getAWBAdjustmentByFilter(filter);
        List<AirbillAdjustmentDetailModel> adjustmentList = ModelUtils.createListModelFromVo(response.getAdjustmentList(), AirbillAdjustmentDetailModel.class);
        for (AirbillAdjustmentDetailModel airbillAdjustmentDetailModel : adjustmentList) {
            String franchiseCommentsToFsc = airbillAdjustmentDetailModel.getFranchiseCommentsToFsc();
            String fscCreditNote = airbillAdjustmentDetailModel.getFscCreditNote();
            franchiseCommentsToFsc = AppUtils.removeHtmlTags(franchiseCommentsToFsc);
            fscCreditNote = AppUtils.removeHtmlTags(fscCreditNote);
            airbillAdjustmentDetailModel.setFranchiseCommentsToFsc(franchiseCommentsToFsc);
            airbillAdjustmentDetailModel.setFscCreditNote(fscCreditNote);
        }
        AirbillAdjustmentTotalModel total = ModelUtils.createModelFromVo(response.getTotalRecord(), AirbillAdjustmentTotalModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("adjustmentList", adjustmentList);
        data.put("total", total);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/manage_adjustment/" + tmpName, data);
    }

    public void renderManageAdjustmentHtmlFile(AirbillAdjustmentDetailFilter filter, Integer userLevel, String realPath, String outputFilePath) throws Exception {
        ManageAdjustmentResponse response = this.getAWBAdjustmentByFilter(filter);
        List<AirbillAdjustmentDetailModel> adjustmentList = ModelUtils.createListModelFromVo(response.getAdjustmentList(), AirbillAdjustmentDetailModel.class);
        AirbillAdjustmentTotalModel total = ModelUtils.createModelFromVo(response.getTotalRecord(), AirbillAdjustmentTotalModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("realPath", realPath);
        data.put("adjustmentList", adjustmentList);
        data.put("total", total);
        data.put("userLevel", userLevel);

        AppUtils.template2File(outputFilePath, false, "templates/html/manage_adjustment/manage_adjustment.ftl", data);
    }
}