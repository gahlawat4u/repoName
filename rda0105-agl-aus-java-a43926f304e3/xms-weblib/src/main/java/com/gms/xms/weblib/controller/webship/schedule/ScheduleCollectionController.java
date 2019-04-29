package com.gms.xms.weblib.controller.webship.schedule;

import com.gms.xms.common.config.dto.PickupTime;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.LocationCodeModel;
import com.gms.xms.model.webship.ScheduleCollectionModel;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.LocationCodeVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from ScheduleCollectionController
 * <p>
 * Author dattrinh Jan 25, 2016 5:09:04 PM
 */
public class ScheduleCollectionController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String serviceId;
    private ScheduleCollectionModel schedule;
    private AddressModel pickupAddress;
    private List<String> weightUnits = new ArrayList<String>();
    private List<PickupTime> readyTimes = new ArrayList<PickupTime>();
    private List<PickupTime> closeTimes = new ArrayList<PickupTime>();
    private List<LocationCodeModel> locationCodes = new ArrayList<LocationCodeModel>();

    public String load2Modify() {
        if (StringUtils.isBlank(this.getShipmentId())) {
            this.addActionError("No shipment id to modify schedule collection.");
        } else {
            try {
                // Load schedule collection to modify.
                this.prepareSchedule2Modify();
                // Load all list.
                this.prepareWeightUnits();
                this.prepareCloseTimes();
                this.prepareReadyTimes();
                this.prepareLocationCodes();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                this.addActionError(e.getMessage());
            }
        }
        return "success";
    }

    public String modify() {
        try {
            if (this.getSchedule() == null) {
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage("No schedule collection to modify.");
                return "success";
            }
            if (!isValidSchedule2Modify()) {
                setErrorCode(ErrorCode.FIELD_ERROR);
                // Load all list.
                this.prepareWeightUnits();
                this.prepareCloseTimes();
                this.prepareReadyTimes();
                this.prepareLocationCodes();
            } else {
                // Convert model to vo.
                ScheduleCollectionVo scheduleCollectionVo = ModelUtils.createVoFromModel(schedule, ScheduleCollectionVo.class);
                AddressVo pickupAddressVo = ModelUtils.createVoFromModel(pickupAddress, AddressVo.class);
                // Call work-flow to modify schedule collection.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.SCHEDULE, scheduleCollectionVo);
                context.put(Attributes.PICKUP_ADDRESS, pickupAddressVo);
                Integer serviceId = 1;
                // Call appropriate work-flow to modify schedule collection.
                switch (serviceId) {
                    case 1: // DHL service
                        context.put(Attributes.WFP_NAME, "Wfl-DhlModifySchedule");
                        break;
                    default:
                        throw new Exception("This service wasn't supported. Please contact the administrator for more information.");
                }

                context = WorkFlowManager2.getInstance().process(context);
                // Write log if there is error message.
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    log.error(context.getString(Attributes.ERROR_MESSAGE));
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    String errMess = !StringUtils.isBlank(context.getString(Attributes.ERROR_MESSAGE)) ? context.getString(Attributes.ERROR_MESSAGE) : AppConstants.SYSTEM_ERROR_MESSAGE;
                    setErrorMessage(errMess);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String create() {
        try {

            if (!isValidSchedule2Create()) {
                setErrorCode(ErrorCode.FIELD_ERROR);
               // return "FIELD_ERROR";
            }
                // Load all list.
                this.prepareWeightUnits();
                this.prepareCloseTimes();
                this.prepareReadyTimes();
                this.prepareLocationCodes();
            /*} else {*/
                // Convert model to vo.
                ScheduleCollectionVo scheduleCollectionVo = ModelUtils.createVoFromModel(schedule, ScheduleCollectionVo.class);
                AddressVo pickupAddressVo = ModelUtils.createVoFromModel(pickupAddress, AddressVo.class);
                // Call work-flow to modify schedule collection.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.SCHEDULE, scheduleCollectionVo);
                context.put(Attributes.PICKUP_ADDRESS, pickupAddressVo);
                context.put(Attributes.SHIPMENT_ID, Long.parseLong(shipmentId));
                scheduleCollectionVo.setPickupAddress(pickupAddressVo);
                context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
                Integer serviceId = Integer.parseInt(this.getServiceId());
                // Call appropriate work-flow to modify schedule collection.
                switch (serviceId) {
                    case 1: // DHL service
                        context.put(Attributes.WFP_NAME, "Wfl-DhlBookSchedule");
                        break;
                    case 15: // DHL Domestic service
                        context.put(Attributes.WFP_NAME, "Wfl-DhlBookSchedule");
                        break;
                    case 54:
                        context.put(Attributes.WFP_NAME, "Wfl-TntIntlScheduleCollection");
                        break;
                    case 3:
                        context.put(Attributes.WFP_NAME, "Wfl-TntDomScheduleCollection");
                        break;
                    case 52:
                        context.put(Attributes.WFP_NAME, "Wfl-TollPriorityScheduleCollection");
                        break;
                    case 59:
                        context.put(Attributes.WFP_NAME, "Wfl-TollIpecScheduleCollection");
                        break;
                    case 72:
                        context.put(Attributes.WFP_NAME, "Wfl-StartrackScheduleCollection");
                        break;
                    case 400:
                        context.put(Attributes.WFP_NAME, "Wfl-UpsScheduleCollection");
                        break;
                    default:
                        throw new Exception("This service wasn't supported. Please contact the administrator for more information.");
                }
                context = WorkFlowManager2.getInstance().process(context);
                // Write log if there is error message.
                if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    log.error(context.getString(Attributes.ERROR_MESSAGE));
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
                } else {
                    scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
                    this.setSchedule(ModelUtils.createModelFromVo(scheduleCollectionVo, ScheduleCollectionModel.class));
                }
            /*}*/  //rakesh sir
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        
        return "success";
    }

    protected boolean isValidSchedule2Modify() {
        // Valid schedule collection model.
        if (StringUtils.isBlank(this.getSchedule().getScheduleCollectionId())) {
            this.addFieldError("schedule.scheduleCollectionId", "No schedule collection id.");
        }
        if (StringUtils.isBlank(this.getSchedule().getPickupDate())) {
            this.addFieldError("schedule.pickupDate", "Pickup date cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getPickupTime())) {
            this.addFieldError("schedule.pickupTime", "Pickup time cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getPickupTimeNoLater())) {
            this.addFieldError("schedule.pickupTimeNoLater", "Pickup time no later cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getNoOfPieces())) {
            this.addFieldError("schedule.noOfPieces", "Number of pieces cannot leave blank.");
        } else {
            try {
                Integer.valueOf(this.getSchedule().getNoOfPieces());
            } catch (Exception e) {
                this.addFieldError("schedule.noOfPieces", "Number of pieces must be a integer number.");
            }
        }
        if (StringUtils.isBlank(this.getSchedule().getTotalWeight())) {
            this.addFieldError("schedule.totalWeight", "Total weight cannot leave blank.");
        } else {
            try {
                Float.valueOf(this.getSchedule().getTotalWeight());
            } catch (Exception e) {
                this.addFieldError("schedule.noOfPieces", "Total weight must be a float number.");
            }
        }
        if (StringUtils.isBlank(this.getSchedule().getScheduleWeightUnit())) {
            this.addFieldError("schedule.sheduleWeightUnit", "Schedule weight unit cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getDescription())) {
            this.addFieldError("schedule.description", "Description cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getLocationCodeId())) {
            this.addFieldError("schedule.locationCodeId", "Location Code cannot leave blank.");
        }
        // Valid pickup address model.
        if (StringUtils.isBlank(this.getPickupAddress().getContactName())) {
            this.addFieldError("pickupAddress.contactName", "Contact name cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getCompanyName())) {
            this.addFieldError("pickupAddress.companyName", "Company name cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getPhone())) {
            this.addFieldError("pickupAddress.phone", "Phone cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getAddress())) {
            this.addFieldError("pickupAddress.address", "Address cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getCity())) {
            this.addFieldError("pickupAddress.city", "City cannot leave blank.");
        }
        return !this.hasFieldErrors();
    }

    protected boolean isValidSchedule2Create() {
        // Valid schedule collection model.
        if (StringUtils.isBlank(this.getSchedule().getPickupDate())) {
            this.addFieldError("schedule.pickupDate", "Pickup date cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getPickupTime())) {
            this.addFieldError("schedule.pickupTime", "Pickup time cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getPickupTimeNoLater())) {
            this.addFieldError("schedule.pickupTimeNoLater", "Pickup time no later cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getNoOfPieces())) {
            this.addFieldError("schedule.noOfPieces", "Number of pieces cannot leave blank.");
        } else {
            try {
                Integer.valueOf(this.getSchedule().getNoOfPieces());
            } catch (Exception e) {
                this.addFieldError("schedule.noOfPieces", "Number of pieces must be a integer number.");
            }
        }
        if (StringUtils.isBlank(this.getSchedule().getTotalWeight())) {
            this.addFieldError("schedule.totalWeight", "Total weight cannot leave blank.");
        } else {
            try {
                Float.valueOf(this.getSchedule().getTotalWeight());
            } catch (Exception e) {
                this.addFieldError("schedule.noOfPieces", "Total weight must be a float number.");
            }
        }
        if (StringUtils.isBlank(this.getSchedule().getScheduleWeightUnit())) {
            this.addFieldError("schedule.sheduleWeightUnit", "Schedule weight unit cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getDescription())) {
            this.addFieldError("schedule.description", "Description cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getSchedule().getLocationCodeId())) {
            this.addFieldError("schedule.locationCodeId", "Location Code cannot leave blank.");
        }
        // Valid pickup address model.
        if (StringUtils.isBlank(this.getPickupAddress().getContactName())) {
            this.addFieldError("pickupAddress.contactName", "Contact name cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getCompanyName())) {
            this.addFieldError("pickupAddress.companyName", "Company name cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getPhone())) {
            this.addFieldError("pickupAddress.phone", "Phone cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getAddress())) {
            this.addFieldError("pickupAddress.address", "Address cannot leave blank.");
        }
        if (StringUtils.isBlank(this.getPickupAddress().getCity())) {
            this.addFieldError("pickupAddress.city", "City cannot leave blank.");
        }
        return !this.hasFieldErrors();
    }

    public String load2Create() {
        this.setPageTitle("Schedule Collection");
        if (StringUtils.isBlank(this.getShipmentId())) {
            this.addActionError("No shipment id to create schedule collection.");
        } else {
            try {
                // Create new schedule collection.
                this.prepareSchedule2Create();
                // Load all list.
                this.prepareWeightUnits();
                this.prepareCloseTimes();
                this.prepareReadyTimes();
                this.prepareLocationCodes();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                this.addActionError(e.getMessage());
            }
        }
        return "success";
    }

    protected void prepareSchedule2Create() throws Exception {
        ScheduleCollectionModel schedule = new ScheduleCollectionModel();
        DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        schedule.setPickupDate(outFormat.format(currentDate));
        IHistoryDetailService iService = new HistoryDetailServiceImp();
        HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(Long.parseLong(shipmentId));
        HistoryDetailInfoVo historyDetailInfoModel = iService.selectHistoryDetailInfo(filter);
        if (historyDetailInfoModel != null) {
            IAddressService aService = new AddressServiceImp();
            AddressVo address = aService.getAddressById(historyDetailInfoModel.getsAddressId());

            this.setPickupAddress(ModelUtils.createModelFromVo(address, AddressModel.class));
            this.setServiceId(String.valueOf(historyDetailInfoModel.getServiceId()));
            schedule.setAddressId(String.valueOf(historyDetailInfoModel.getsAddressId()));
            schedule.setNoOfPieces(String.valueOf(historyDetailInfoModel.getNoOfPieces()));
            schedule.setScheduleWeightUnit(historyDetailInfoModel.getWeightUnit());
            schedule.setSpecialInstructions(historyDetailInfoModel.getSpecialInstructions());
            schedule.setTotalWeight(historyDetailInfoModel.getTotalWeight());
            schedule.setDescription(historyDetailInfoModel.getContentDescription());
            schedule.setLocationCodeId(historyDetailInfoModel.getLocationCodeId());
            schedule.setDescription("Front Desk");
        } else {
            throw new Exception("Not found shipment to create schedule.");
        }
        this.setSchedule(schedule);
    }

    protected void prepareSchedule2Modify() throws Exception {
        // Load schedule collection data.
        IScheduleCollectionService scheduleCollectionService = new ScheduleCollectionServiceImp();
        Long shipmentId = Long.valueOf(this.getShipmentId());
        ScheduleCollectionVo scheduleCollectionVo = scheduleCollectionService.getScheduleCollectionForModify(shipmentId);
        if (scheduleCollectionVo == null) {
            throw new Exception("No schedule collection to modify.");
        } else {
            ScheduleCollectionModel scheduleCollectionModel = ModelUtils.createModelFromVo(scheduleCollectionVo, ScheduleCollectionModel.class);
            if (StringUtils.isEmpty(scheduleCollectionModel.getDescription())) {
                scheduleCollectionModel.setDescription("Front Desk");
            }
            this.setSchedule(scheduleCollectionModel);
            // Load pickup address data.
            IAddressService addressService = new AddressServiceImp();
            AddressVo addressVo = addressService.getAddressById(scheduleCollectionVo.getAddressId());
            AddressModel addressModel = ModelUtils.createModelFromVo(addressVo, AddressModel.class);
            this.setPickupAddress(addressModel);
        }
    }

    protected void prepareWeightUnits() {
        List<String> weightUnitList = new ArrayList<String>();
        weightUnitList.add("kg");
        weightUnitList.add("lb");
        this.setWeightUnits(weightUnitList);
    }

    protected void prepareReadyTimes() {
        List<PickupTime> pickupTimeList = new ArrayList<PickupTime>();
        try {
            pickupTimeList = DateUtils.createPickupTimeList(6, 24);
        } catch (Exception e) {
        }
        this.setReadyTimes(pickupTimeList);
    }

    protected void prepareCloseTimes() {
        List<PickupTime> pickupTimeList = new ArrayList<PickupTime>();
        try {
            pickupTimeList = DateUtils.createPickupTimeList(14, 24);
        } catch (Exception e) {
        }
        this.setCloseTimes(pickupTimeList);
    }

    protected void prepareLocationCodes() {
        List<LocationCodeModel> locationCodeModels = new ArrayList<LocationCodeModel>();
        try {
            ILocationCodeService locationCodeService = new LocationCodeServiceImp();
            List<LocationCodeVo> locationCodeVos = locationCodeService.getLocationCodeList();
            locationCodeModels = ModelUtils.createListModelFromVo(locationCodeVos, LocationCodeModel.class);
        } catch (Exception e) {
        }
        this.setLocationCodes(locationCodeModels);
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public ScheduleCollectionModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleCollectionModel schedule) {
        this.schedule = schedule;
    }

    public AddressModel getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(AddressModel pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public List<String> getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(List<String> weightUnits) {
        this.weightUnits = weightUnits;
    }

    public List<PickupTime> getReadyTimes() {
        return readyTimes;
    }

    public void setReadyTimes(List<PickupTime> readyTimes) {
        this.readyTimes = readyTimes;
    }

    public List<PickupTime> getCloseTimes() {
        return closeTimes;
    }

    public void setCloseTimes(List<PickupTime> closeTimes) {
        this.closeTimes = closeTimes;
    }

    public List<LocationCodeModel> getLocationCodes() {
        return locationCodes;
    }

    public void setLocationCodes(List<LocationCodeModel> locationCodes) {
        this.locationCodes = locationCodes;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
