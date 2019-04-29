package com.gms.xms.weblib.controller.adjustment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.AirbillAdjustmentModel;
import com.gms.xms.model.adjustment.AdjustmentRequestModel;
import com.gms.xms.model.adjustment.SubAdjustmentTypeModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestFilterVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.AdjustmentRequest;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from AdjustmentController
 * <p>
 * Author DatTV Date May 18, 2015 9:05:51 AM
 */
public class AdjustmentController extends JsonBaseController {

    private static final long serialVersionUID = -760628950575543577L;
    private List<AdjustmentType> adjustmentTypes;
    private List<String> subAdjustmentTypes;
    private List<CreditType> creditTypes;
    private List<GstType> gstTypes;
    private AdjustmentRequestModel adjustmentRequest;
    private AirbillAdjustmentModel adjustmentInfo;
    private AirbillAdjustmentModel adjustmentTotal;
    private String requestList;
    private Integer userLevel;

    private boolean isAglWarranty;

    private void determineUserLevel() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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
            adjustmentRequest.setAdjustType("1");
            AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentRequestVo.class);
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

    public String searchByType() {
        try {
            prepareAdjustmentTypes();
            prepareCreditTypes();
            prepareGstTypes();
            determineUserLevel();
            AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentRequestVo.class);
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
        try {
            prepareAdjustmentTypes();
            prepareCreditTypes();
            prepareGstTypes();
            determineUserLevel();
            AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentRequestVo.class);
            prepareSubAdjustmentTypes(requestVo.getShipmentId(), requestVo.getAirbillNumber(), requestVo.getAdjustType());
            buildAdjustmentInfo();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "success";
    }

    public String doAdjustment() {
        try {
            if (this.getAdjustmentRequest() == null) {
                throw new CustomException("Please select airbill for adjustment.");
            }
            prepareAdjustmentTypes();
            prepareCreditTypes();
            prepareGstTypes();
            determineUserLevel();
            if ("2".equalsIgnoreCase(this.getAdjustmentRequest().getCreditType())) {
                this.getAdjustmentRequest().setAdjustType("7");
            } else if ("7".equalsIgnoreCase(this.getAdjustmentRequest().getAdjustType())) {
                String adjustType = String.valueOf(this.getAdjustmentTypes().get(0).getId());
                this.getAdjustmentRequest().setAdjustType(adjustType);
            }
            AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(this.getAdjustmentRequest(), AdjustmentRequestVo.class);
            prepareSubAdjustmentTypes(requestVo.getShipmentId(), requestVo.getAirbillNumber(), requestVo.getAdjustType());
            if (subAdjustmentTypes.size() > 0) {
                if (StringUtils.isBlank(adjustmentRequest.getAdjustmentType())) {
                    adjustmentRequest.setAdjustmentType(subAdjustmentTypes.get(0));
                }
                buildAdjustmentInfo();
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    /**
     * Saves the list of adjustment request
     */
    public void saveAdjustmentRequests() {
        try {
            AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentRequestVo.class);
            String invoiceCode = requestVo.getInvoiceCode();
            List<AdjustmentRequestModel> adjustmentRequestModels = GsonUtils.fromGson(this.requestList, new TypeToken<List<AdjustmentRequestModel>>() {
            }.getType());
            List<AdjustmentRequestVo> adjustmentRequestVos = ModelUtils.createListVoFromModel(adjustmentRequestModels, AdjustmentRequestVo.class);
            AdjustmentRequest request = buildAdjustmentRequest(invoiceCode, adjustmentRequestVos);
            this.checkAdjustmentAmount(request);
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
            client.saveAdjustmentRequests(request);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    private AdjustmentRequest buildAdjustmentRequest(String invoiceCode, List<AdjustmentRequestVo> adjustmentRequests) throws Exception {
        AdjustmentRequest request = new AdjustmentRequest();
        CreditNoteVo creditNoteVo = null;

        // Get current date
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        // Check if existing adjustment with credit type is Credit Now or not?
        // 1. And create list of AirbillAdjustmentVo
        List<AirbillAdjustmentVo> adjustmentList = new ArrayList<AirbillAdjustmentVo>();
        boolean hasCreditNow = false;
        for (AdjustmentRequestVo adjustmentRequest : adjustmentRequests) {
            if (!isValidAdjustment(adjustmentRequest)) {
                throw new Exception("Invalid adjustment: you can't create a negative adjustment.");
            }
            AirbillAdjustmentVo airbillAdjustment = new AirbillAdjustmentVo();
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

    private boolean isValidAdjustment(AirbillAdjustmentVo adjustment) {
        if (adjustment.getCarrierAmount() < 0 || adjustment.getFranchiseAmount() < 0 || adjustment.getCustomerAmount() < 0) {
            return false;
        }
        return true;
    }

    protected void checkAdjustmentAmount(AdjustmentRequest request) throws Exception {
        // Get total of amount of the request airbill.
        Long shipmentId = null;
        String airbillNumber = null;
        if (request != null && request.getAdjustmentList() != null && request.getAdjustmentList().size() > 0) {
            shipmentId = request.getAdjustmentList().get(0).getShipmentId();
            airbillNumber = request.getAdjustmentList().get(0).getAirbillNumber();
            AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
            filter.setShipmentId(shipmentId);
            filter.setAirbillNumber(airbillNumber);
            AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
            // Get adjusted amount of this airbill.
            AirbillAdjustmentVo adjustedAmount = adjustmentDao.selectTotalAdjustAmountOfAWB(filter);
            if (adjustedAmount == null) {
                adjustedAmount = AirbillAdjustmentVo.createNotNullObject();
            } else {
                adjustedAmount = AirbillAdjustmentVo.removeNullValue(adjustedAmount);
            }
            // Get total adjustable amount of the airbill.
            AirbillAdjustmentVo totalAmount = adjustmentDao.selectTotalPayableOfAWB(filter);
            if (totalAmount == null) {
                totalAmount = AirbillAdjustmentVo.createNotNullObject();
            } else {
                totalAmount = AirbillAdjustmentVo.removeNullValue(totalAmount);
            }
            // Calculate remaining amount for adjustment.
            Double carrierAmount = totalAmount.getCarrierAmount() - adjustedAmount.getCarrierAmount();
            carrierAmount = carrierAmount > 0 ? carrierAmount : 0.0;
            carrierAmount = new BigDecimal(carrierAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
            Double franchiseAmount = totalAmount.getFranchiseAmount() - adjustedAmount.getFranchiseAmount();
            franchiseAmount = franchiseAmount > 0 ? franchiseAmount : 0.0;
            franchiseAmount = new BigDecimal(franchiseAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
            Double customerAmount = totalAmount.getCustomerAmount() - adjustedAmount.getCustomerAmount();
            customerAmount = customerAmount > 0 ? customerAmount : 0.0;
            customerAmount = new BigDecimal(customerAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
            // Get total amount of the request adjustments.
            Double carrier = 0.0;
            Double franchise = 0.0;
            Double customer = 0.0;
            for (AirbillAdjustmentVo airbillVo : request.getAdjustmentList()) {
                if (airbillVo.getCreditType() != 2) {
                    carrier += airbillVo.getCarrierAmount();
                    franchise += airbillVo.getFranchiseAmount();
                    customer += airbillVo.getCustomerAmount();
                } else {
                    // Get total Agl Warranty adjusted amount for this
                    // airbill.
                    AirbillAdjustmentVo adjustmentVo = new AirbillAdjustmentVo();
                    adjustmentVo.setShipmentId(airbillVo.getShipmentId());
                    adjustmentVo.setAirbillNumber(airbillVo.getAirbillNumber());
                    Double total = adjustmentDao.getAglWarrantyAdjustedAmount(adjustmentVo);
                    // Get value of Agl Warranty Credit Limit.
                    Double limit = this.getAglWarrantyLimit();
                    Double remain = limit - total;
                    // Validate the value of input customer amount.
                    if (remain <= 0) {
                        throw new CustomException("This airbill cannot be adjusted anymore.");
                    } else if (airbillVo.getCustomerAmount() > remain) {
                        throw new CustomException("The total of the agl Warranty adjustment value cannot be greater than " + String.valueOf(remain));
                    }
                }
            }
            if (carrier > carrierAmount || franchise > franchiseAmount || customer > customerAmount) {
                throw new CustomException("This airbill cannot be adjusted anymore.");
            }
        }
    }

    private void buildAdjustmentInfo() throws Exception {
        AdjustmentRequestVo requestVo = ModelUtils.createVoFromModel(adjustmentRequest, AdjustmentRequestVo.class);
        AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
        filter.setShipmentId(requestVo.getShipmentId());
        filter.setAdjustmentType(requestVo.getAdjustmentType());
        filter.setAdjustType(requestVo.getAdjustType());
        filter.setAirbillNumber(requestVo.getAirbillNumber());
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        // Get total payable of the adjustment type
        AirbillAdjustmentVo adjustmentVo = client.getTotalPayableOfAdjustType(filter);
        if (requestVo.getAdjustType() == 7) { // if it's agl Warranty
            Double remainAmount = getRemainAglWarranty();
            remainAmount = MathUtils.round(remainAmount, 2);
            adjustmentVo.setCustomerAmount(remainAmount);
            adjustmentVo.setGstCustomerAmount(0.0);
        }
        AirbillAdjustmentModel adjustmentModel = ModelUtils.createModelFromVo(adjustmentVo, AirbillAdjustmentModel.class);
        this.setAdjustmentInfo(adjustmentModel);
        // Get total adjustable amount of the airbill
        adjustmentVo = client.getAdjustableAmount(filter);
        adjustmentModel = ModelUtils.createModelFromVo(adjustmentVo, AirbillAdjustmentModel.class);
        this.setAdjustmentTotal(adjustmentModel);
    }

    private void prepareSubAdjustmentTypes(Long shipmentId, String airbillNumber, Integer adjustType) throws Exception {
        List<String> subTypes = new ArrayList<String>();
        switch (adjustType) {
            case 1:
                // Accessorial Disputes (Surcharge)
                ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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
            case 7:
                // Agl Warranty
                subTypes.add("Agl Warranty");
                break;
        }
        this.setSubAdjustmentTypes(subTypes);
    }

    protected void detectAglWarranty(AdjustmentRequestModel request) throws Exception {
        ShipmentBillingDao billingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
        shipmentBillingVo.setShipmentId(Long.valueOf(request.getShipmentId()));
        shipmentBillingVo.setAirbillNumber(request.getAirbillNumber());
        this.setAglWarranty(billingDao.isAglWarrantyAirbill(shipmentBillingVo) > 0);
    }

    private void prepareAdjustmentTypes() {
        List<AdjustmentType> typesList = new ArrayList<AdjustmentType>();
        if (this.getAdjustmentRequest().getCreditType() != null && "2".equalsIgnoreCase(this.getAdjustmentRequest().getCreditType())) {
            typesList.add(new AdjustmentType(7, "Agl Warranty"));
        } else {
            typesList.add(new AdjustmentType(1, "Accessorial Disputes"));
            typesList.add(new AdjustmentType(2, "Base Rate Disputes"));
            typesList.add(new AdjustmentType(3, "Service Failure"));
            typesList.add(new AdjustmentType(4, "Weight Disputes"));
            typesList.add(new AdjustmentType(5, "Full Refund"));
            typesList.add(new AdjustmentType(6, "Partial Refund"));
        }
        this.setAdjustmentTypes(typesList);
    }

    private void prepareCreditTypes() throws Exception {
        List<CreditType> typeList = new ArrayList<CreditType>();
        typeList.add(new CreditType(0, "Upon Carrier approval"));
        typeList.add(new CreditType(1, "Credit Now"));
        detectAglWarranty(this.getAdjustmentRequest());
        if (this.isAglWarranty()) {
            typeList.add(new CreditType(2, "Agl Warranty Credit"));
        }
        this.setCreditTypes(typeList);
    }

    private void prepareGstTypes() {
        List<GstType> typeList = new ArrayList<GstType>();
        typeList.add(new GstType(0, "Non-GST Airbill (Not Applicable)"));
        typeList.add(new GstType(1, "Apply GST on adjustment"));
        this.setGstTypes(typeList);
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

    public AdjustmentRequestModel getAdjustmentRequest() {
        return adjustmentRequest;
    }

    public void setAdjustmentRequest(AdjustmentRequestModel adjustmentRequest) {
        this.adjustmentRequest = adjustmentRequest;
    }

    public String getRequestList() {
        return requestList;
    }

    public void setRequestList(String requestList) {
        this.requestList = requestList;
    }

    public AirbillAdjustmentModel getAdjustmentInfo() {
        return adjustmentInfo;
    }

    public void setAdjustmentInfo(AirbillAdjustmentModel adjustmentInfo) {
        this.adjustmentInfo = adjustmentInfo;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public AirbillAdjustmentModel getAdjustmentTotal() {
        return adjustmentTotal;
    }

    public void setAdjustmentTotal(AirbillAdjustmentModel adjustmentTotal) {
        this.adjustmentTotal = adjustmentTotal;
    }

   

    public boolean isAglWarranty() {
		return isAglWarranty;
	}

	public void setAglWarranty(boolean isAglWarranty) {
		this.isAglWarranty = isAglWarranty;
	}

	public Double getAglWarrantyLimit() {
        Double aglWarrantyLimit = 0d;
        try {
        	aglWarrantyLimit = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("agl Warranty Credit Limit"));
        } catch (Exception e) {
        	aglWarrantyLimit = 0d;
        }
        return aglWarrantyLimit;
    }

    public Double getRemainAglWarranty() throws Exception {
        Double limit = getAglWarrantyLimit();
        AdjustmentRequestModel request = this.getAdjustmentRequest();
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        AirbillAdjustmentVo adjustmentVo = new AirbillAdjustmentVo();
        adjustmentVo.setShipmentId(Long.valueOf(request.getShipmentId()));
        adjustmentVo.setAirbillNumber(request.getAirbillNumber());
        Double total = adjustmentDao.getAglWarrantyAdjustedAmount(adjustmentVo);
        return limit - total;
    }
}