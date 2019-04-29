package com.gms.xms.weblib.controller.webship.history;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.ScheduleCollectionModel;
import com.gms.xms.model.webship.ShipmentModel;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from HistoryScheduleController
 * <p>
 * Author TanDT Date Jul 9, 2015
 */
public class HistoryScheduleController extends JsonBaseController {
    /**
     *
     */
    private static final long serialVersionUID = -7901889480717968757L;
    private String shipmentId;
    private HistoryDetailFilterModel detailFilterModel;
    private HistoryDetailInfoModel detailInfoModel;
    private ScheduleCollectionModel scheduleCollectionModel;
    private ShipmentModel shipmentModel;
    private AddressModel addressModel;
    private String pickupTimeNoLate;
    private List<String> weightUnit;
    private String pickupTimeString;
    private String action;
    private String listShipmentId;
    private String collectionNoNew;
    private String reason;
    private String confirmationNumber;
    private String isSuccess;
    private String pickupErrorMessage;

    public String historyProceedToVoid() {
        try {
            if (this.getListShipmentId() != null) {
                String[] listSmId = this.getListShipmentId().split("\\|");
                for (int i = 1; i < listSmId.length; i++) {
                    if (!"".equals(listSmId[i].trim())) {
                        shipmentId = "";
                        this.setShipmentId(listSmId[i].trim());
                        prepareDataSchedule();
                        if (detailInfoModel.getScStatus() != null && detailInfoModel.getScStatus().equals("1")) {
                            ScheduleCollectionModel collectionModelN = new ScheduleCollectionModel();
                            collectionModelN.setScheduleCollectionId(detailInfoModel.getSchId());
                            this.setScheduleCollectionModel(collectionModelN);
                            doCancelCollection();
                        }
                        if (detailInfoModel.getStatus().equals("0")) {
                            ShipmentModel shipmentModelN = new ShipmentModel();
                            shipmentModelN.setShipmentId(shipmentId);
                            this.setShipmentModel(shipmentModelN);
                            doVoidAirbill();
                        }
                    } else {
                        setErrorMessage("Please chooice shipment to update.");
                        setErrorCode(ErrorCode.ACTION_ERROR);
                        return "success";
                    }
                }
            } else {
                setErrorMessage("Please chooice shipment to void.");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        setErrorMessage("Voided successfully");
        setErrorCode(ErrorCode.ACTION_ERROR);
        return "success";
    }

    public String historyProceedUpdateCollection() {
        try {
            if (!StringUtils.isEmpty(collectionNoNew)) {
                if (this.getListShipmentId() != null) {
                    String[] listSmId = this.getListShipmentId().split("\\|");
                    for (int i = 1; i < listSmId.length; i++) {
                        if (!"".equals(listSmId[i].trim())) {
                            shipmentId = "";
                            this.setShipmentId(listSmId[i].trim());
                            prepareDataSchedule();
                            if (detailInfoModel.getScStatus() != null && detailInfoModel.getScStatus().equals("1")) {
                                ScheduleCollectionModel collectionModelN = new ScheduleCollectionModel();
                                collectionModelN.setScheduleCollectionId(detailInfoModel.getSchId());
                                collectionModelN.setConfirmationNo(collectionNoNew);
                                this.setScheduleCollectionModel(collectionModelN);
                                doCancelCollection();
                            }
                            if (detailInfoModel.getStatus().equals("0")) {
                                ShipmentModel shipmentModelN = new ShipmentModel();
                                shipmentModelN.setShipmentId(shipmentId);
                                shipmentModelN.setDhlNote(collectionNoNew);
                                this.setShipmentModel(shipmentModelN);
                                doVoidAirbill();
                            }
                        } else {
                            setErrorMessage("Please chooice shipment to update.");
                            setErrorCode(ErrorCode.ACTION_ERROR);
                            return "success";
                        }
                    }
                } else {
                    setErrorMessage("Please chooice shipment to update.");
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    return "success";
                }
            } else {
                setErrorMessage("Please input a collection number");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        setErrorMessage("Updated collection number successfully");
        setErrorCode(ErrorCode.ACTION_ERROR);
        return "success";
    }

    public String historyVoidAirbill() {
        this.setPageTitle("Void Airbill");
        try {
            this.prepareDataSchedule();
        } catch (Exception e) {
            addActionError(e.getMessage());
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String historyCancelCollection() {
        this.setPageTitle("Cancel collection");
        try {
            this.prepareDataSchedule();
            Integer serviceId = Integer.parseInt(detailInfoModel.getServiceId());
            switch (serviceId) {
                case 1: // DHL service
                case 15:
                    break;
                case 3:
                    break;
                case 52:
                case 59:
                    break;
                default:
                    throw new Exception("This service wasn't supported. Please contact the administrator for more information.");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String doVoidAirbill() {
        try {
            if (StringUtils.isEmpty(reason)) {
                this.addActionError("Please choose reason.");
                return "success";
            }
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            // Call work-flow to void airbill.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(this.getShipmentModel().getShipmentId()));
            Integer shipmentTypeId = shipmentVo.getShipmentTypeId();
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentTypeId);
            String wflName = "";
            String wflNameCancel = "";
            switch (shipmentTypeVo.getServiceId()) {
                case 1:
                    wflName = "Wfl-DhlVoidShipment";
                    break;
                case 72:
                    //wflNameCancel = "Wfl-StartrackCancelCollection";
                	wflName = "Wfl-StartrackCancelCollection";
                	break;
                case 400:
                    wflName = "Wfl-UpsVoidShipment";
                    break;    
            }
            if (StringUtils.isNotBlank(wflName)) {
                context.put(Attributes.REASON, reason);
                context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentModel().getShipmentId()));
                context.put(Attributes.WFP_NAME, wflName);
                context = WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE).replace("\n", "<br/>"));
                    return "success";
                }
                
                //code by shahabuddin
                else {
                    ShipmentVo shipment = new ShipmentVo();
                    shipment.setShipmentId(Long.parseLong(this.getShipmentModel().getShipmentId()));
                    shipmentService.voidShipment(this.getAddInfoMap(), null, shipment);
                }
                
            }
            //below code by rakesh sir 
            /*else {
                ShipmentVo shipment = new ShipmentVo();
                shipment.setShipmentId(Long.parseLong(this.getShipmentModel().getShipmentId()));
                shipmentService.voidShipment(this.getAddInfoMap(), null, shipment);
            }*/

            if (StringUtils.isNotBlank(scheduleCollectionModel.getScheduleCollectionId()) && StringUtils.isNotBlank(wflNameCancel)) {
                context.put(Attributes.REASON, reason);
                context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentModel().getShipmentId()));
                context.put(Attributes.WFP_NAME, wflNameCancel);
                context = WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE).replace("\n", "<br/>"));
                    return "success";
                }
            }

            return "success";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doCancelCollection() {
        try {
            if (StringUtils.isEmpty(reason)) {
                throw new CustomException("Please choose reason.");
            }
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(this.getShipmentModel().getShipmentId()));
            Integer shipmentTypeId = shipmentVo.getShipmentTypeId();
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentTypeId);
            String wflName = "";
            switch (shipmentTypeVo.getServiceId()) {
                case 1:
                case 15:
                    wflName = "Wfl-DhlCancelCollection";
                    break;
                case 3:
                    wflName = "Wfl-TntCancelCollection";
                    break;
                case 52:
                case 59:
                    wflName = "Wfl-TollCancelCollection";
                    break;
                case 72:
                    wflName = "Wfl-StartrackCancelCollection";
                    break;

            }
            if (StringUtils.isNotBlank(wflName)) {
                // Call work-flow to void airbill.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.REASON, this.getReason());
                context.put(Attributes.SHIPMENT_ID, Long.parseLong(this.getShipmentModel().getShipmentId()));
                context.put(Attributes.WFP_NAME, wflName);
                context = WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE).replace("\n", "<br/>"));
                    return "success";
                }
            } else {
                throw new CustomException("This service cannot cancel collection. Please contact administrator for more details.");
            }
            return "success";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareWeightUnit() {
        List<String> listWeightUnitN = new ArrayList<String>();
        listWeightUnitN.add("kg");
        listWeightUnitN.add("lb");
        this.setWeightUnit(listWeightUnitN);
    }

    protected boolean isValidFormInputSchedule(ScheduleCollectionModel scheduleCollectionModel) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date pickUpdateDate = sdf.parse(scheduleCollectionModel.getPickupDate());
        Date currentDate = sdf.parse(sdf.format(new Date()));
        if (pickUpdateDate.compareTo(currentDate) < 0) {
            addFieldError("scheduleCollectionModel.pickupDate", "Pickup Date must not be less than today date.");
        }
        if (StringUtils.isBlank(scheduleCollectionModel.getNoOfPieces())) {
            addFieldError("scheduleCollectionModel.noOfPieces", "Number of pieces is not empty.");
        } else {
            if (!NumberUtils.isNumber(scheduleCollectionModel.getNoOfPieces())) {
                addFieldError("scheduleCollectionModel.noOfPieces", "Number of pieces is not number.");
            } else {
                if (scheduleCollectionModel.getNoOfPieces().contains("-")) {
                    addFieldError("scheduleCollectionModel.noOfPieces", "Number of pieces is negative.");
                } else {
                    if (scheduleCollectionModel.getNoOfPieces().contains(".")) {
                        addFieldError("scheduleCollectionModel.noOfPieces", "Number of pieces is decimal.");
                    }
                }
            }
        }
        if (StringUtils.isBlank(scheduleCollectionModel.getTotalWeight())) {
            addFieldError("scheduleCollectionModel.totalWeight", "Total weight is not empty.");
        } else {
            if (!NumberUtils.isNumber(scheduleCollectionModel.getTotalWeight())) {
                addFieldError("scheduleCollectionModel.totalWeight", "Total weight is not number.");
            } else {
                if (scheduleCollectionModel.getTotalWeight().contains("-")) {
                    addFieldError("scheduleCollectionModel.totalWeight", "Total weight is negative.");
                }
            }
        }
        if (StringUtils.isBlank(scheduleCollectionModel.getDescription())) {
            addFieldError("scheduleCollectionModel.description", "Location Description is not empty.");
        }
        if (!scheduleCollectionModel.getPickupTime().equals("0")) {
            if (scheduleCollectionModel.getPickupTimeNoLater().equals("0")) {
                addFieldError("scheduleCollectionModel.pickupTimeNoLater", "Please choose pickup no later.");
            } else {
                String[] pickTimeArr = scheduleCollectionModel.getPickupTime().split(":");
                String[] closeTimeArr = scheduleCollectionModel.getPickupTimeNoLater().split(":");
                Integer pickTime = Integer.parseInt(pickTimeArr[0]) * 3600 + Integer.parseInt(pickTimeArr[1]) * 60 + Integer.parseInt(pickTimeArr[2]);
                Integer closeTime = Integer.parseInt(closeTimeArr[0]) * 3600 + Integer.parseInt(closeTimeArr[1]) * 60 + Integer.parseInt(closeTimeArr[2]);
                String defaultMinimumPickupHour = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_MINIMUM_PICKUP_HOUR); // hour
                Integer defaultPickMin = Integer.valueOf(defaultMinimumPickupHour) * 3600;
                Integer pickDiff = closeTime - pickTime;
                if (pickTime > closeTime) {
                    addFieldError("scheduleCollectionModel.pickTime", "Package Pickup Time is greater than pickup package no later than.");
                }
                if (pickDiff < defaultPickMin) {
                    addFieldError("scheduleCollectionModel.pickTime", "Package Pickup Time should be before the Pickup Package no later than for about Min " + defaultMinimumPickupHour + " hour");
                }
            }
        } else {
            addFieldError("scheduleCollectionModel.pickupTimeNoLater", "Please choose pickup time.");
            if (scheduleCollectionModel.getPickupTimeNoLater().equals("0")) {
                addFieldError("scheduleCollectionModel.pickupTimeNoLater", "Please choose pickup no later.");
            }
        }
        if (hasFieldErrors()) {
            return false;
        }
        return true;
    }

    protected void prepareDataSchedule() throws Exception {
        IHistoryDetailService iService = new HistoryDetailServiceImp();
        preapareFilterHistoryDetail();
        HistoryDetailFilter filter = ModelUtils.createVoFromModel(detailFilterModel, HistoryDetailFilter.class);
        HistoryDetailInfoVo historyDetailInfoVo = iService.selectHistoryDetailInfo(filter);
        if (historyDetailInfoVo != null) {
            if (StringUtils.isBlank(shipmentId)) {
                throw new Exception("The shipment does not existed.");
            }
        }
        this.setDetailInfoModel(ModelUtils.createModelFromVo(iService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class));
        if (!StringUtils.isEmpty(detailInfoModel.getActualWeight())) {
            detailInfoModel.setActualWeight(detailInfoModel.getActualWeight().substring(0, detailInfoModel.getActualWeight().length() - 6));
        }
        if (!StringUtils.isEmpty(detailInfoModel.getPickupDate())) {
            DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date pickUpdateDate = null;
            pickUpdateDate = inFormat.parse(detailInfoModel.getPickupDate());
            detailInfoModel.setPickupDate(outFormat.format(pickUpdateDate));
        } else {
            DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();
            detailInfoModel.setPickupDate(outFormat.format(currentDate));
        }
    }

    protected void preapareFilterHistoryDetail() throws Exception {
        if (StringUtils.isBlank(shipmentId)) {
            throw new Exception("Shipment ".concat(shipmentId).concat(" is empty!"));
        } else {
            HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
            detailFilterModelN.setShipmentId(shipmentId);
            detailFilterModelN.setLbToKg("0.45359237");
            detailFilterModelN.setInToCm("2.54");
            detailFilterModelN.setWeightValue("5000");
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
            HistoryDetailFilter filter = new HistoryDetailFilter();
            filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
            historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
            detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
            this.setDetailFilterModel(detailFilterModelN);
        }
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public HistoryDetailFilterModel getDetailFilterModel() {
        return detailFilterModel;
    }

    public void setDetailFilterModel(HistoryDetailFilterModel detailFilterModel) {
        this.detailFilterModel = detailFilterModel;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

    public ScheduleCollectionModel getScheduleCollectionModel() {
        return scheduleCollectionModel;
    }

    public void setScheduleCollectionModel(ScheduleCollectionModel scheduleCollectionModel) {
        this.scheduleCollectionModel = scheduleCollectionModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public String getPickupTimeNoLate() {
        return pickupTimeNoLate;
    }

    public void setPickupTimeNoLate(String pickupTimeNoLate) {
        this.pickupTimeNoLate = pickupTimeNoLate;
    }

    public String getPickupTimeString() {
        return pickupTimeString;
    }

    public void setPickupTimeString(String pickupTimeString) {
        this.pickupTimeString = pickupTimeString;
    }

    public List<String> getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(List<String> weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ShipmentModel getShipmentModel() {
        return shipmentModel;
    }

    public void setShipmentModel(ShipmentModel shipmentModel) {
        this.shipmentModel = shipmentModel;
    }

    public String getListShipmentId() {
        return listShipmentId;
    }

    public void setListShipmentId(String listShipmentId) {
        this.listShipmentId = listShipmentId;
    }

    public String getCollectionNoNew() {
        return collectionNoNew;
    }

    public void setCollectionNoNew(String collectionNoNew) {
        this.collectionNoNew = collectionNoNew;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getPickupErrorMessage() {
        return pickupErrorMessage;
    }

    public void setPickupErrorMessage(String pickupErrorMessage) {
        this.pickupErrorMessage = pickupErrorMessage;
    }
}