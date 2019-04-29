package com.gms.xms.workflow.client.adjustmentrequest;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.adjustment.AirbillAdjustmentTotalModel;
import com.gms.xms.model.adjustmentrequest.AirbillAdjustmentRequestDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.SubAdjustmentTypeDao;
import com.gms.xms.persistence.dao.UserLevelDao;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.adjustment.SubAdjustmentTypeVo;
import com.gms.xms.txndb.vo.adjustmentrequest.*;
import com.gms.xms.workflow.client.WorkflowBaseClient;
import com.gms.xms.workflow.client.integration.request.AdjustmentClaimRequest;
import com.gms.xms.workflow.client.integration.response.ManageAdjustmentRequestResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ManageAdjustmentRequestClient
 * </p>
 *
 * @author hungnt - Nov 4, 2015
 */
public class ManageAdjustmentRequestClient extends WorkflowBaseClient {
    public ManageAdjustmentRequestClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public Long countAWBAdjustmentByFilter(AirbillAdjustmentRequestDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-CountAWBAdjustmentByFilter-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            return Long.valueOf(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_COUNT));
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public ManageAdjustmentRequestResponse getAWBAdjustmentByFilter(AirbillAdjustmentRequestDetailFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_DETAIL_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetAWBAdjustmentByFilter-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            ManageAdjustmentRequestResponse response = new ManageAdjustmentRequestResponse();
            List<AirbillAdjustmentRequestDetailVo> adjustmentRequestList = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_LIST_RESULT), new TypeToken<List<AirbillAdjustmentRequestDetailVo>>() {
            }.getType());
            AirbillAdjustmentRequestTotalVo totalRecord = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_TOTAL), AirbillAdjustmentRequestTotalVo.class);
            response.setAdjustmentList(adjustmentRequestList);
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

    public List<AirbillAdjustmentRequestVo> getRequestTypes() throws DaoException {
        AirbillAdjustmentRequestDao adjustmentRequestDao = new AirbillAdjustmentRequestDao();
        return adjustmentRequestDao.selectAdjustmentTypes();
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

    public AirbillAdjustmentRequestVo getTotalPayableOfAdjustType(AirbillAdjustmentRequestFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetTotalPayableOfAdjustType-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            AirbillAdjustmentRequestVo adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_TOTAL_PAYABLE_BY_ADJUST_TYPE), AirbillAdjustmentRequestVo.class);
            return adjustmentVo;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public AirbillAdjustmentRequestVo getAdjustableAmount(AirbillAdjustmentRequestFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustableAmount-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            AirbillAdjustmentRequestVo totalPayable = GsonUtils.fromGson(context.get(Attributes.AIRBILL_TOTAL_PAYABLE), AirbillAdjustmentRequestVo.class);
            AirbillAdjustmentRequestVo totalAdjust = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTED_AMOUNT_TOTAL), AirbillAdjustmentRequestVo.class);
            AirbillAdjustmentRequestVo adjustmentVo = new AirbillAdjustmentRequestVo();

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

    public void saveAdjustmentRequests(AdjustmentClaimRequest request) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.WFP_NAME, "Wfl-SaveAdjustmentRequests-Request");
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
        AirbillAdjustmentRequestVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_RESULT), AirbillAdjustmentRequestVo.class);
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
            context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT, GsonUtils.toGson(adjustmentVo));
            context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment-Request");
            context = WorkFlowManager.getInstance().process(context);
            if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                throw new Exception(context.get(Attributes.ERROR_MESSAGE));
            }
        }
    }

    public void updateSubStatus(Long adjustmentId, Integer userLevel) throws Exception {
        AirbillAdjustmentRequestVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_RESULT), AirbillAdjustmentRequestVo.class);
            if (adjustmentVo.getStatus() == 1) {
                Integer subStatus = adjustmentVo.getSubStatus() == null ? 0 : adjustmentVo.getSubStatus();
                Integer newSubStatus = null;
                if (userLevel < 3) {
                    switch (subStatus) {
                        case 0:
                            newSubStatus = 1;
                            // If user level is 1, 2
                            if (userLevel < 3) {
                                newSubStatus = subStatus * 10 + 1; // 1: Request
                                // franchise
                                // comment
                            } else {
                                newSubStatus = subStatus * 10 + 2; // 2: Update to
                                // FSC
                            }
                            break;
                        case 1:
                            newSubStatus = 2;
                            // If user level is 1, 2
                            if (userLevel < 3) {
                                newSubStatus = 0;
                            } else {
                                newSubStatus = 2;
                            }
                            break;
                        case 2:
                            newSubStatus = 0;
                            // If user level is 1, 2
                            if (userLevel < 3) {
                                newSubStatus = 1;
                            } else {
                                // No support (no changesub status)
                                throw new Exception("You cannot change this status.");
                            }
                            break;
                    }
                } else {
                    newSubStatus = 2;
                }
                adjustmentVo.setSubStatus(newSubStatus);
                context = new ContextBase(this.getAddInfo());
                context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT, GsonUtils.toGson(adjustmentVo));
                context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment-Request");
                context = WorkFlowManager.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                    throw new Exception(context.get(Attributes.ERROR_MESSAGE));
                }
            } else {
                throw new Exception("Cannot update sub status for this adjustment.");
            }
        }

    }

    public AirbillAdjustmentRequestVo getAdjustmentById(Long adjustmentId) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            return GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_RESULT), AirbillAdjustmentRequestVo.class);
        }
    }

    public void updateAdjustment(AirbillAdjustmentRequestVo adjustment) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT, GsonUtils.toGson(adjustment));
        context.put(Attributes.WFP_NAME, "Wfl-EditAdjustment-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    public void resubmit(Long adjustmentId) throws Exception {
        AirbillAdjustmentVo adjustmentVo = null;
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_ID, String.valueOf(adjustmentId));
        context.put(Attributes.WFP_NAME, "Wfl-GetAdjustmentById-Request");
        context = WorkFlowManager.getInstance().process(context);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        } else {
            adjustmentVo = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_RESULT), AirbillAdjustmentVo.class);
            // If current status is Disputed
            if (adjustmentVo.getStatus() == 3) {
                // Then change it's status to Pending
                adjustmentVo.setStatus((byte) 2);
                context = new ContextBase(this.getAddInfo());
                context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_OBJECT, GsonUtils.toGson(adjustmentVo));
                context.put(Attributes.WFP_NAME, "Wfl-UpdateAdjustment-Request");
                context = WorkFlowManager.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
                    throw new Exception(context.get(Attributes.ERROR_MESSAGE));
                }
            } else {
                throw new Exception("The current status of this adjustment is not Disputed so it cannot change to Pending status.");
            }
        }
    }

    public void renderManageAdjustmentXLSFile(AirbillAdjustmentRequestDetailFilter filter, String outPutFilePath, String tmpName) throws Exception {
        ManageAdjustmentRequestResponse response = this.getAWBAdjustmentByFilter(filter);
        List<AirbillAdjustmentRequestDetailModel> adjustmentRequestList = ModelUtils.createListModelFromVo(response.getAdjustmentList(), AirbillAdjustmentRequestDetailModel.class);
        for (AirbillAdjustmentRequestDetailModel adjustmentRequestDetailModel : adjustmentRequestList) {
            String franchiseCommentsToFsc = adjustmentRequestDetailModel.getFranchiseCommentsToFsc();
            String fscCreditNote = adjustmentRequestDetailModel.getFscCreditNote();
            franchiseCommentsToFsc = AppUtils.removeHtmlTags(franchiseCommentsToFsc);
            fscCreditNote = AppUtils.removeHtmlTags(fscCreditNote);
            adjustmentRequestDetailModel.setFranchiseCommentsToFsc(franchiseCommentsToFsc);
            adjustmentRequestDetailModel.setFscCreditNote(fscCreditNote);
        }
        AirbillAdjustmentTotalModel total = ModelUtils.createModelFromVo(response.getTotalRecord(), AirbillAdjustmentTotalModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("adjustmentList", adjustmentRequestList);
        data.put("total", total);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/manage_adjustment_request/" + tmpName, data);
    }

    public void renderManageAdjustmentHtmlFile(AirbillAdjustmentRequestDetailFilter filter, Integer userLevel, String realPath, String outputFilePath) throws Exception {
        ManageAdjustmentRequestResponse response = this.getAWBAdjustmentByFilter(filter);
        List<AirbillAdjustmentRequestDetailModel> adjustmentList = ModelUtils.createListModelFromVo(response.getAdjustmentList(), AirbillAdjustmentRequestDetailModel.class);
        AirbillAdjustmentTotalModel total = ModelUtils.createModelFromVo(response.getTotalRecord(), AirbillAdjustmentTotalModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("adjustmentList", adjustmentList);
        data.put("total", total);
        data.put("userLevel", userLevel);

        AppUtils.template2File(outputFilePath, false, "templates/html/manage_adjustment_request/manage_adjustment_request.ftl", data);
    }
}