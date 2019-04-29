package com.gms.xms.weblib.controller.adjustment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.AirbillAdjustmentModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.CreditType;
import com.gms.xms.txndb.vo.GstType;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestFilterVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from EditAdjustmentController
 * <p>
 * Author DatTV Date May 22, 2015 5:06:23 PM
 */
public class EditAdjustmentController extends JsonBaseController {

    private static final long serialVersionUID = 9215093948782015888L;
    private List<CreditType> creditTypes;
    private List<GstType> gstTypes;
    private AirbillAdjustmentModel adjustment;
    private AirbillAdjustmentModel adjustmentInfo;
    private AirbillAdjustmentModel adjustmentValid;
    private String adjustmentId;
    private Integer userLevel;
    private boolean isAglWarranty;

    public String show() {
        try {
            // Valid adjustment.
            if (StringUtils.isBlank(adjustmentId) || !NumberUtils.isNumber(adjustmentId)) {
                throw new CustomException("The adjustment id must be a long number.");
            }
            prepareAdjustmentInfo();
            determineUserLevel();
            prepareCreditTypes();
            prepareGstTypes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    public void update() {
        try {
            // Valid adjustment.
            if (adjustment == null) {
                throw new CustomException("No adjustment to save.");
            }
            determineUserLevel();
            AirbillAdjustmentVo adjustmentVo = ModelUtils.createVoFromModel(adjustment, AirbillAdjustmentVo.class);
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected void detectAglWarranty(Long adjustmentId) throws Exception {
        // Get airbill adjustment info.
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        AirbillAdjustmentVo adjustmentVo = client.getAdjustmentById(Long.valueOf(adjustmentId));
        // Detect the airbill has Agl Warranty or not?
        ShipmentBillingDao billingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
        shipmentBillingVo.setShipmentId(adjustmentVo.getShipmentId());
        shipmentBillingVo.setAirbillNumber(adjustmentVo.getAirbillNumber());
        this.setAglWarranty(billingDao.isAglWarrantyAirbill(shipmentBillingVo) > 0);
    }

    private boolean isValidAdjustment(AirbillAdjustmentVo adjustment, Integer userLevel) {
        if (!adjustment.getAdjustmentType().equalsIgnoreCase("Full Refund")) {
            if (adjustment.getStatus() != 4) {
                if (userLevel >= 3) {
                    if (adjustment.getFranchiseAmount() < 0 || adjustment.getCustomerAmount() < 0) {
                        return false;
                    }
                } else {
                    if (adjustment.getCarrierAmount() < 0 || adjustment.getFranchiseAmount() < 0 || adjustment.getCustomerAmount() < 0) {
                        return false;
                    }
                }
            } else {
                if (userLevel >= 3) {
                    if (adjustment.getCustomerAmount() < 0) {
                        return false;
                    }
                } else {
                    if (adjustment.getCarrierAmount() < 0 || adjustment.getCustomerAmount() < 0) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private void prepareAdjustmentInfo() throws Exception {
        // Get airbill adjustment info.
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        AirbillAdjustmentVo adjustmentVo = client.getAdjustmentById(Long.valueOf(adjustmentId));
        this.setAdjustment(ModelUtils.createModelFromVo(adjustmentVo, AirbillAdjustmentModel.class));

        AirbillAdjustmentVo validAdjustmentVo = new AirbillAdjustmentVo();
        // Build the filter to get details of adjustment (include payment info).
        AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
        filter.setShipmentId(adjustmentVo.getShipmentId());
        filter.setAdjustmentType(adjustmentVo.getAdjustmentType());
        filter.setAirbillNumber(adjustmentVo.getAirbillNumber());
        filter.setAdjustType(determineAdjustType(adjustmentVo.getAdjustmentType()));

        // Get total adjustable amount of the airbill
        AirbillAdjustmentVo adjustableTotal = client.getAdjustableAmount(filter);

        // Get total adjustable amount by adjust type
        AirbillAdjustmentVo adjustableByTypeVo = client.getTotalPayableOfAdjustType(filter);
        this.setAdjustmentInfo(ModelUtils.createModelFromVo(adjustableByTypeVo, AirbillAdjustmentModel.class));

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
        if (adjustmentVo.getCreditType() == 2) {
            AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
            // Get total Agl Warranty adjusted amount for this airbill.
            AirbillAdjustmentVo warrantyAdjustmentVo = new AirbillAdjustmentVo();
            warrantyAdjustmentVo.setShipmentId(adjustmentVo.getShipmentId());
            warrantyAdjustmentVo.setAirbillNumber(adjustmentVo.getAirbillNumber());
            Double total = adjustmentDao.getAglWarrantyAdjustedAmount(warrantyAdjustmentVo);
            // Get value of Agl Warranty Credit Limit.
            Double limit = this.getAglWarrantyLimit();
            validCustomerAmount = limit - total + adjustmentVo.getCustomerAmount();
        }
        if (validCustomerAmount < 0) {
            validCustomerAmount = 0D;
        }
        validCustomerAmount = MathUtils.round(validCustomerAmount, 2);
        validAdjustmentVo.setCustomerAmount(validCustomerAmount);
        this.setAdjustmentValid(ModelUtils.createModelFromVo(validAdjustmentVo, AirbillAdjustmentModel.class));
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
            case "Agl Warranty":
                return 7;
            default:
                return 1; // Accessorial Disputes (Surcharge)
        }
    }

    private void prepareCreditTypes() throws Exception {
        List<CreditType> typeList = new ArrayList<CreditType>();
        typeList.add(new CreditType(0, "Upon Carrier approval"));
        typeList.add(new CreditType(1, "Credit Now"));
        detectAglWarranty(Long.valueOf(this.getAdjustmentId()));
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

    private void determineUserLevel() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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

    public AirbillAdjustmentModel getAdjustmentInfo() {
        return adjustmentInfo;
    }

    public void setAdjustmentInfo(AirbillAdjustmentModel adjustmentInfo) {
        this.adjustmentInfo = adjustmentInfo;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public AirbillAdjustmentModel getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(AirbillAdjustmentModel adjustment) {
        this.adjustment = adjustment;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public AirbillAdjustmentModel getAdjustmentValid() {
        return adjustmentValid;
    }

    public void setAdjustmentValid(AirbillAdjustmentModel adjustmentValid) {
        this.adjustmentValid = adjustmentValid;
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
            aglWarrantyLimit = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Agl Warranty Credit Limit"));
        } catch (Exception e) {
            aglWarrantyLimit = 0d;
        }
        return aglWarrantyLimit;
    }
}