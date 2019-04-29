package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.ScheduleCollectionModel;
import com.gms.xms.model.webship.ShipmentModel;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.service.webship.history.*;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from ManageCustomerWebshipHistoryScheduleController
 * <p>
 * Author TANDT 06-11-2015
 */
public class ManageCustomerWebshipHistoryScheduleController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -7559082423356364136L;
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

    public String scheduleCollection() {
        if (action != null) {
            this.setPageTitle("Modify Schedule Collection");
        } else {
            this.setPageTitle("Schedule Collection");
        }

        pepareWeightUnit();
        try {
            boolean flagViewError = true;
            if (this.getScheduleCollectionModel() != null && this.getAddressModel() != null) {
                IHistoryScheduleService iService = new HistoryScheduleServiceImp();
                IHistoryAddressService iServiceAddress = new HistoryAddressServiceImp();
                if (StringUtils.isBlank(scheduleCollectionModel.getScheduleCollectionId())) {
                    if (isValidFormInputSchedule(scheduleCollectionModel)) {
                        scheduleCollectionModel.setStatus("1");
                        iService.insertScheduleCollection(this.getAddInfoMap(), ModelUtils.createVoFromModel(scheduleCollectionModel, ScheduleCollectionVo.class));
                    } else {
                        flagViewError = false;
                    }
                } else {
                    if (isValidFormInputSchedule(scheduleCollectionModel)) {
                        scheduleCollectionModel.setStatus("1");
                        iService.updateScheduleCollectionById(this.getAddInfoMap(), ModelUtils.createVoFromModel(this.getScheduleCollectionModel(), ScheduleCollectionVo.class));
                    } else {
                        flagViewError = false;
                    }
                }

                if (StringUtils.isBlank(addressModel.getAddressId())) {
                    if (isValidFormInputAddress(addressModel)) {
                        iServiceAddress.insertAddress(this.getAddInfoMap(), ModelUtils.createVoFromModel(addressModel, AddressVo.class));
                    } else {
                        flagViewError = false;
                    }
                } else {
                    if (isValidFormInputAddress(addressModel)) {
                        iServiceAddress.updateAddress(this.getAddInfoMap(), ModelUtils.createVoFromModel(addressModel, AddressVo.class));
                    } else {
                        flagViewError = false;
                    }
                }
                setErrorMessage("<font style='color:green;'>Schedule collection ".concat(scheduleCollectionModel.getScheduleCollectionId()).concat(" is successful !</font>"));
            }
            if (!flagViewError) {
                setErrorCode(ErrorCode.FIELD_ERROR);
                return "error";
            }

            prepareDataSchedule();

            if (this.getDetailInfoModel().getPickupTime() != null) {
                this.setPickupTimeString(HelperUtils.getPickupTime(String.valueOf(this.getDetailInfoModel().getPickupTime())));
            } else {
                this.setPickupTimeString(HelperUtils.getPickupTime(String.valueOf("")));
            }
            if (this.getDetailInfoModel().getPickupTimeNoLater() != null) {
                this.setPickupTimeNoLate(HelperUtils.getPickupTime(String.valueOf(this.getDetailInfoModel().getPickupTimeNoLater())));
            } else {
                this.setPickupTimeNoLate(HelperUtils.getPickupTime(String.valueOf("")));
            }

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

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
                            cancelScheduleCollection();
                        }
                        if (detailInfoModel.getStatus().equals("0")) {
                            ShipmentModel shipmentModelN = new ShipmentModel();
                            shipmentModelN.setShipmentId(shipmentId);
                            this.setShipmentModel(shipmentModelN);
                            voidAirbill();
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
                                cancelScheduleCollection();
                            }
                            if (detailInfoModel.getStatus().equals("0")) {
                                ShipmentModel shipmentModelN = new ShipmentModel();
                                shipmentModelN.setShipmentId(shipmentId);
                                shipmentModelN.setDhlNote(collectionNoNew);
                                this.setShipmentModel(shipmentModelN);
                                voidAirbill();
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
            if (scheduleCollectionModel != null) {
                cancelScheduleCollection();
                if (shipmentModel != null) {
                    voidAirbill();
                }
                return "success";
            }
            this.prepareDataSchedule();

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String historyCancelCollection() {
        this.setPageTitle("Cancel collection");
        try {
            if (scheduleCollectionModel != null) {
                cancelScheduleCollection();
                return "success";
            }
            this.prepareDataSchedule();

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void cancelScheduleCollection() throws DaoException, Exception {
        if (!StringUtils.isEmpty(scheduleCollectionModel.getScheduleCollectionId())) {
            Date currentDate = new Date();
            scheduleCollectionModel.setCreateDate(String.valueOf(currentDate));
            scheduleCollectionModel.setStatus("0");
            IHistoryScheduleService iService = new HistoryScheduleServiceImp();
            iService.cancelScheduleCollectionById(this.getAddInfoMap(), ModelUtils.createVoFromModel(scheduleCollectionModel, ScheduleCollectionVo.class));
        }

    }

    protected void voidAirbill() throws DaoException, Exception {
        Date currentDate = new Date();
        shipmentModel.setCreateDate(String.valueOf(currentDate));
        if (collectionNoNew == null) {
            shipmentModel.setStatus("1");
        }
        IShipmentService iService = new ShipmentServiceImp();
        iService.update(this.getAddInfoMap(), ModelUtils.createVoFromModel(shipmentModel, ShipmentVo.class));
    }

    protected boolean isValidFormInputAddress(AddressModel addressModel) {
        if (StringUtils.isBlank(addressModel.getContactName())) {
            addFieldError("addressModel.contactName", "Contact is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getAddress())) {
            addFieldError("addressModel.address", "Address is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getPhone())) {
            addFieldError("addressModel.phone", "Phone is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getCompanyName())) {
            addFieldError("addressModel.companyName", "Company Name is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getCity())) {
            addFieldError("addressModel.companyName", "City is not empty.");
        }

        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void pepareWeightUnit() {
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

        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareDataSchedule() throws Exception {

        IHistoryDetailService iService = new HistoryDetailServiceImp();
        preapareFilterHistoryDetail();
        HistoryDetailFilter filter = ModelUtils.createVoFromModel(detailFilterModel, HistoryDetailFilter.class);
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
            setErrorMessage("Shipment ".concat(shipmentId).concat(" is not valid!"));
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Shipment ".concat(shipmentId).concat(" is empty!"));
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

}