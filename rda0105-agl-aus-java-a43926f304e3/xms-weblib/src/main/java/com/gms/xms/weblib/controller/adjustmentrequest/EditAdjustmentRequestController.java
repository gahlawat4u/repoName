package com.gms.xms.weblib.controller.adjustmentrequest;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.adjustmentrequest.AirbillAdjustmentRequestModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.CreditType;
import com.gms.xms.txndb.vo.GstType;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustmentrequest.ManageAdjustmentRequestClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from EditAdjustmentRequestController
 * </p>
 *
 * @author hungnt - Nov 12, 2015
 */
public class EditAdjustmentRequestController extends JsonBaseController {
    private static final long serialVersionUID = 115323470100794818L;
    private List<CreditType> creditTypes;
    private List<GstType> gstTypes;
    private AirbillAdjustmentRequestModel adjustment;
    private AirbillAdjustmentRequestModel adjustmentInfo;
    private AirbillAdjustmentRequestModel adjustmentValid;
    private String adjustmentId;
    private Integer userLevel;

    public String show() {
        if (StringUtils.isBlank(adjustmentId) || !NumberUtils.isNumber(adjustmentId)) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("The adjustment id must be a long number."));
        }

        try {
            prepareAdjustmentInfo();
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        prepareCreditTypes();
        prepareGstTypes();

        return "success";
    }

    public void update() {
        if (adjustment == null) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No adjustment to save."));
        }
        try {
            determineUserLevel();
            AirbillAdjustmentRequestVo adjustmentVo = ModelUtils.createVoFromModel(adjustment, AirbillAdjustmentRequestVo.class);
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            // If level of user is greater than 3
            // then they cannot update carrier and gst carrier amount
            if (userLevel >= 3) {
                adjustmentVo.setCarrierAmount(null);
                adjustmentVo.setGstCarrierAmount(null);
            }
            if (!isValidAdjustment(adjustmentVo, userLevel)) {
                throw new Exception("Invalid adjustment: you can't create a negative adjustment.");
            }
            client.updateAdjustment(adjustmentVo);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    private boolean isValidAdjustment(AirbillAdjustmentRequestVo adjustment, Integer userLevel) {
        if (userLevel >= 3) {
            if (adjustment.getFranchiseAmount() < 0 || adjustment.getCustomerAmount() < 0) {
                return false;
            }
        } else {
            if (adjustment.getCarrierAmount() < 0 || adjustment.getFranchiseAmount() < 0 || adjustment.getCustomerAmount() < 0) {
                return false;
            }
        }
        return true;
    }

    private void prepareAdjustmentInfo() throws Exception {
        // Get airbill adjustment info.
        ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
        AirbillAdjustmentRequestVo adjustmentVo = client.getAdjustmentById(Long.valueOf(adjustmentId));
        this.setAdjustment(ModelUtils.createModelFromVo(adjustmentVo, AirbillAdjustmentRequestModel.class));

        // Build the filter to get details of adjustment (include payment info).
        AirbillAdjustmentRequestFilter filter = new AirbillAdjustmentRequestFilter();
        filter.setShipmentId(adjustmentVo.getShipmentId());
        filter.setAdjustmentType(adjustmentVo.getAdjustmentType());
        filter.setAirbillNumber(adjustmentVo.getAirbillNumber());
        filter.setAdjustType(determineAdjustType(adjustmentVo.getAdjustmentType()));

        // Get total adjustable amount of the airbill
        AirbillAdjustmentRequestVo adjustableTotal = client.getAdjustableAmount(filter);

        // Get total adjustable amount by adjust type
        AirbillAdjustmentRequestVo adjustableByTypeVo = client.getTotalPayableOfAdjustType(filter);
        this.setAdjustmentInfo(ModelUtils.createModelFromVo(adjustableByTypeVo, AirbillAdjustmentRequestModel.class));

        AirbillAdjustmentRequestVo validAdjustmentVo = new AirbillAdjustmentRequestVo();
        // Get valid carrier amount
        Double validCarrierAmount = adjustableTotal.getCarrierAmount() + adjustmentVo.getCarrierAmount();
        if (validCarrierAmount > adjustableByTypeVo.getCarrierAmount()) {
            validCarrierAmount = adjustableByTypeVo.getCarrierAmount();
        }
        if (validCarrierAmount < 0) {
            validCarrierAmount = 0D;
        }
        validCarrierAmount = MathUtils.round(validCarrierAmount, 2);
        validAdjustmentVo.setCarrierAmount(validCarrierAmount);
        // Get valid franchise amount
        Double validFranchiseAmount = adjustableTotal.getFranchiseAmount() + (adjustmentVo.getFranchiseAmount() != null ? adjustmentVo.getFranchiseAmount() : 0);
        if (validFranchiseAmount > adjustableByTypeVo.getFranchiseAmount()) {
            validFranchiseAmount = adjustableByTypeVo.getFranchiseAmount();
        }
        if (validFranchiseAmount < 0) {
            validFranchiseAmount = 0D;
        }
        validFranchiseAmount = MathUtils.round(validFranchiseAmount, 2);
        validAdjustmentVo.setFranchiseAmount(validFranchiseAmount);
        // Get valid customer amount
        Double validCustomerAmount = adjustableTotal.getCustomerAmount() + adjustmentVo.getCustomerAmount();
        if (validCustomerAmount > adjustableByTypeVo.getCustomerAmount()) {
            validCustomerAmount = adjustableByTypeVo.getCustomerAmount();
        }
        if (validCustomerAmount < 0) {
            validCustomerAmount = 0D;
        }
        validCustomerAmount = MathUtils.round(validCustomerAmount, 2);
        validAdjustmentVo.setCustomerAmount(validCustomerAmount);
        this.setAdjustmentValid(ModelUtils.createModelFromVo(validAdjustmentVo, AirbillAdjustmentRequestModel.class));
    }

    private Integer determineAdjustType(String adjustmentType) {
        switch (adjustmentType) {
            case "Rate Dispute":
            case "Service Level":
                return 2; // Base Rate Disputes (Base Charge)
            case "Late Delivery":
            case "Lost Shipment":
            case "Damaged Shipment":
            case "Damange Shipment":
            case "Damanged Shipment":
                return 3; // Service Failure
            case "Incorrect Weight":
            case "Reweight Error":
            case "Zero Weight Default(ZWD)":
                return 4; // Weight Disputes
            case "Full Refund":
                return 5; // Full Refund
            case "Partial Refund":
                return 6; // Partial Refund
            default:
                return 1; // Accessorial Disputes (Surcharge)
        }
    }

    private void prepareCreditTypes() {
        List<CreditType> typeList = new ArrayList<CreditType>();
        typeList.add(new CreditType(0, "Upon Carrier approval"));
        typeList.add(new CreditType(1, "Credit Now"));
        this.setCreditTypes(typeList);
    }

    private void prepareGstTypes() {
        List<GstType> typeList = new ArrayList<GstType>();
        typeList.add(new GstType(0, "Non-GST Airbill (Not Applicable)"));
        typeList.add(new GstType(1, "Apply GST on adjustment"));
        this.setGstTypes(typeList);
    }

    private void determineUserLevel() throws Exception {
        ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
        Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
        Double userLevel = client.getUserLevel(userLevelId);
        this.userLevel = userLevel.intValue();
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

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public AirbillAdjustmentRequestModel getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(AirbillAdjustmentRequestModel adjustment) {
        this.adjustment = adjustment;
    }

    public AirbillAdjustmentRequestModel getAdjustmentInfo() {
        return adjustmentInfo;
    }

    public void setAdjustmentInfo(AirbillAdjustmentRequestModel adjustmentInfo) {
        this.adjustmentInfo = adjustmentInfo;
    }

    public AirbillAdjustmentRequestModel getAdjustmentValid() {
        return adjustmentValid;
    }

    public void setAdjustmentValid(AirbillAdjustmentRequestModel adjustmentValid) {
        this.adjustmentValid = adjustmentValid;
    }
}