package com.gms.xms.weblib.controller.webship.shipment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.*;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.service.webship.shipment.BookingShipmentServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IBookingShipmentService;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentProductDetailVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.client.BookingShipmentClient;
import com.gms.xms.workflow.client.integration.response.BookingShipmentResponse;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Posted from BookingController
 * <p>
 * Author TANDT
 */
public class BookingController extends ContinueBookingController {

    /**
     *
     */
    private static final long serialVersionUID = -8322372266020873021L;
    private String shipmentRequestModelGson;
    private ShipmentRequestModel shipmentRequestModel;
    private ShipmentModel shipmentModel;
    private String packingListSelect;
    private String commercialInvoiceSelect;
    private String confirmationNumber;

    public String booking() {
        try {
            ShipmentRequestModel shipmentRequestModelN = GsonUtils.fromGson(shipmentRequestModelGson, ShipmentRequestModel.class);
            shipmentRequestModelN = reBuildShipmentRequestModel(shipmentRequestModelN);
            WebshipLoginVo webshipLoginInfo = this.getWebshipLoginInfo();
            ShipmentRequestVo shipmentRequestVo = ModelUtils.createVoFromModel(shipmentRequestModelN, ShipmentRequestVo.class);
            if (shipmentRequestVo.getShipmentInfo().getIsSaveSenderAddressBook() == null) {
                shipmentRequestVo.getShipmentInfo().setIsSaveSenderAddressBook(0);
            }
            if (shipmentRequestVo.getShipmentInfo().getIsSaveRecipientAddressBook() == null) {
                shipmentRequestVo.getShipmentInfo().setIsSaveRecipientAddressBook(0);
            }
            shipmentRequestVo.setWebshipLogin(webshipLoginInfo);
            String selPackingList = "1";
            String selScheduleCollection = "3";
            String selCommercialInvoice = "1";
            if (!StringUtils.isBlank(this.getPackingListSelect())) {
                selPackingList = this.getPackingListSelect();
            }
            if (!StringUtils.isBlank(this.getCommercialInvoiceSelect())) {
                selCommercialInvoice = this.getCommercialInvoiceSelect();
            }
            selScheduleCollection = validateScheduleCollection(true);
            shipmentRequestVo.setSelCollection(Integer.parseInt(selScheduleCollection));
            shipmentRequestVo.setSelCommercial(Integer.parseInt(selCommercialInvoice));
            shipmentRequestVo.setSelPackingList(Integer.parseInt(selPackingList));
            if (shipmentRequestVo.getSelCommercial() == 3 || shipmentRequestVo.getSelPackingList() == 2) {
                validateShipmentProductDetails(this.getShipmentRequestModel().getShipmentProductDetails(), selPackingList);
                List<ShipmentProductDetailVo> shipmentProductDetailVos = shipmentRequestVo.getShipmentProductDetails();
                for (ShipmentProductDetailVo productDetailVo: shipmentProductDetailVos) {
                    productDetailVo.setAmount(MathUtils.round(productDetailVo.getAmount()*productDetailVo.getQty(), 2));
                }
                shipmentRequestVo.setShipmentProductDetails(shipmentProductDetailVos);
            }
            BookingShipmentClient client = new BookingShipmentClient(this.getAddInfoMap());
            BookingShipmentResponse response = client.bookShipment(shipmentRequestVo);
            if (response.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
                IBookingShipmentService service = new BookingShipmentServiceImp();
                response.getBookingDataVo().setShipmentInfoVo(shipmentRequestVo.getShipmentInfo());
                BookingDataVo bookingDataVo = service.bookingShipment(this.getAddInfoMap(), response.getBookingDataVo());
                if (selScheduleCollection.equalsIgnoreCase("1")) {
                    ScheduleCollectionVo scheduleCollectionVo = shipmentRequestVo.getScheduleCollection();
                    scheduleCollectionVo.setNoOfPieces(shipmentRequestVo.getShipmentInfo().getShipmentRequestPieces().size());
                    scheduleCollectionVo.setTotalWeight(shipmentRequestVo.getQuote().getWeight().floatValue());
                    scheduleCollectionVo.getSaddress().setCountry(shipmentRequestVo.getShipmentInfo().getSenderAddress().getCountry());
                    ContextBase2 context2 = new ContextBase2(this.getAddInfoMap());
                    context2.put(Attributes.SCHEDULE, scheduleCollectionVo);
                    context2.put(Attributes.SHIPMENT_ID, bookingDataVo.getShipmentVo().getShipmentId());
                    context2.put(Attributes.PICKUP_ADDRESS, scheduleCollectionVo.getSaddress());
                    
                    //code by shahabuddin
                    if(bookingDataVo.getShipmentVo().getShipmentTypeId() == 250)
                    {
                    context2.put(Attributes.WFP_NAME, "Wfl-UpsBookSchedule");
                    }
                    else
                    {
                    	context2.put(Attributes.WFP_NAME, "Wfl-DhlBookSchedule");
                    }
                    context2 = WorkFlowManager2.getInstance().process(context2);
                    if (ErrorCode.ERROR.equalsIgnoreCase(context2.getString(Attributes.ERROR_CODE))) {
                        this.addActionError(this.getLocalizationText("Schedule collection failed."));
                        this.addActionError(context2.getString(Attributes.ERROR_MESSAGE));
                    } else {
                        scheduleCollectionVo = context2.get(Attributes.SCHEDULECOLLECTION_VO);
                        this.setConfirmationNumber(scheduleCollectionVo.getConfirmationNo());
                    }
                }
                this.setShipmentModel(ModelUtils.createModelFromVo(bookingDataVo.getShipmentVo(), ShipmentModel.class));
            } else {
                this.setErrorCode(response.getErrorCode());
                this.setErrorMessage(response.getErrorMessage());
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doShip() {
        try {
            String selPackingList = "1";
            String selScheduleCollection = "1";
            String selCommercialInvoice = "1";
            if (!StringUtils.isBlank(this.getPackingListSelect())) {
                selPackingList = this.getPackingListSelect();
            }
            if (!StringUtils.isBlank(this.getCommercialInvoiceSelect())) {
                selCommercialInvoice = this.getCommercialInvoiceSelect();
            }

            if (selCommercialInvoice.equalsIgnoreCase("3") || selPackingList.equalsIgnoreCase("2")) {
                validateShipmentProductDetails(this.getShipmentRequestModel().getShipmentProductDetails(), selPackingList);
            }
            ShipmentRequestModel shipmentRequestModelPage1 = GsonUtils.fromGson(shipmentRequestModelGson, ShipmentRequestModel.class);
            ShipmentRequestModel shipmentRequestModelPage2 = this.getShipmentRequestModel();
            ShipmentRequestVo shipmentRequestVoPage1 = ModelUtils.createVoFromModel(shipmentRequestModelPage1, ShipmentRequestVo.class);
            ShipmentRequestVo shipmentRequestVoPage2 = ModelUtils.createVoFromModel(shipmentRequestModelPage2, ShipmentRequestVo.class);
            if (selCommercialInvoice.equalsIgnoreCase("3") || selPackingList.equalsIgnoreCase("2")) {
                List<ShipmentProductDetailVo> shipmentProductDetailVos = shipmentRequestVoPage2.getShipmentProductDetails();
                for (ShipmentProductDetailVo productDetailVo: shipmentProductDetailVos) {
                    productDetailVo.setAmount(MathUtils.round(productDetailVo.getAmount()*productDetailVo.getQty(), 2));
                }
                shipmentRequestVoPage2.setShipmentProductDetails(shipmentProductDetailVos);
            }
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            String serviceId = shipmentRequestModelPage1.getShipmentInfo().getServiceId();

            boolean isRequiredCollectionOption = true;

            switch (serviceId) {
                case "3":
                    context.put(Attributes.WFP_NAME, "Wfl-TntDomBooking");
                    break;
                case "54":
                    context.put(Attributes.WFP_NAME, "Wfl-TntIntlBooking");
                    break;
                case "59":
                    context.put(Attributes.WFP_NAME, "Wfl-TollIpecBooking");
                    break;
                case "52":
                    context.put(Attributes.WFP_NAME, "Wfl-TollPriorityBooking");
                    break;
                case "16":
                case "17":
                case "18":
                case "19":
                case "20":
                case "26":
                case "27":
                case "36":
                    context.put(Attributes.WFP_NAME, "Wfl-OtherCarrierBooking");
                    isRequiredCollectionOption = false;
                    break;
                case "72":
                    context.put(Attributes.WFP_NAME, "Wfl-StartrackBooking");
                    break;
                case "400":
                    context.put(Attributes.WFP_NAME, "Wfl-UpsBooking");
                    break;
                default:
                    throw new Exception("This service is under construction. Please try again later.");
            }

            if(isRequiredCollectionOption){

                if (!StringUtils.isBlank(shipmentRequestModelPage2.getScheduleCollectionSelect())
                        && !shipmentRequestModelPage2.getScheduleCollectionSelect().equalsIgnoreCase("0")) {
                    selScheduleCollection = shipmentRequestModelPage2.getScheduleCollectionSelect();
                } else {
                    throw new CustomException("Please select collection option.");
                }
            }else
            {
                selScheduleCollection = null;
            }

            ScheduleCollectionVo scheduleCollection = shipmentRequestVoPage2.getScheduleCollection();
            if(!isRequiredCollectionOption)
            {
                context.put(Attributes.REASON_FOR_EXPORT, scheduleCollection.getSpecialInstructions());
            }
            if (scheduleCollection != null) {
                AddressVo pickupAddress = scheduleCollection.getSaddress();
                if (pickupAddress != null) {
                    pickupAddress.setCountry(shipmentRequestVoPage1.getShipmentInfo().getSenderAddress().getCountry());
                    scheduleCollection.setPickupAddress(pickupAddress);
                }
                if(selScheduleCollection!=null)
                {
                    if (selScheduleCollection.equalsIgnoreCase("3")) {
                        shipmentRequestVoPage2.setScheduleCollection(null);
                    } else {
                        shipmentRequestVoPage2.setScheduleCollection(scheduleCollection);
                    }
                }

            }
            shipmentRequestVoPage1.setWebshipLogin(this.getWebshipLoginInfo());
            context.put(Attributes.SHIPMENT_REQUEST_INFO_PAGE1, shipmentRequestVoPage1);
            context.put(Attributes.SHIPMENT_REQUEST_INFO_PAGE2, shipmentRequestVoPage2);

            context.put(Attributes.PACKING_LIST_SELECT, Integer.parseInt(selPackingList));
            if(selScheduleCollection!=null)
            {
                context.put(Attributes.SCHEDULE_LIST_SELECT, Integer.parseInt(selScheduleCollection));
            }
            context.put(Attributes.COMMERCIAL_LIST_SELECT, Integer.parseInt(selCommercialInvoice));
            context = WorkFlowManager2.getInstance().process(context);
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                log.error(context.getString(Attributes.ERROR_MESSAGE));
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
            } else {
                BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
                ShipmentModel shipmentModel = new ShipmentModel();
                shipmentModel.setAirbillNumber(bookingDataVo.getShipmentVo().getAirbillNumber());
                shipmentModel.setShipmentId(String.valueOf(bookingDataVo.getShipmentVo().getShipmentId()));
                this.setShipmentModel(shipmentModel);
                if(selScheduleCollection!=null)
                {
                    if (selScheduleCollection.equals("1")) {
                        String scheduleCollectionErrMsg = context.getString(Attributes.SCHEDULE_COLLECTION_ERROR_MESSAGE);
                        if (StringUtils.isBlank(scheduleCollectionErrMsg)) {
                            ScheduleCollectionVo scheduleCollectionVo = bookingDataVo.getScheduleCollection();
                            this.setConfirmationNumber(scheduleCollectionVo.getConfirmationNo());
                        } else {
                            this.addActionError(this.getLocalizationText("Schedule collection failed."));
                            this.addActionError(scheduleCollectionErrMsg);
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";

    }

    private String validateScheduleCollection(boolean isRequiredOption) throws CustomException {

        String selScheduleCollection;
        if ((isRequiredOption && !StringUtils.isBlank(this.getShipmentRequestModel().getScheduleCollectionSelect())
                && !this.getShipmentRequestModel().getScheduleCollectionSelect().equalsIgnoreCase("0")) || !isRequiredOption) {
            selScheduleCollection = this.getShipmentRequestModel().getScheduleCollectionSelect();
        } else {
            throw new CustomException("Please select collection option.");
        }
        return selScheduleCollection;
    }

    protected void validateShipmentProductDetails(List<ShipmentProductDetailModel> productDetailModels, String selPackingList) throws CustomException {
        Integer i = 1;
        String errMsg = "";
        Double totalUnitValue = 0D;
        for (ShipmentProductDetailModel shipmentProductDetail : productDetailModels) {
            if (selPackingList.equalsIgnoreCase("2")) {
                if (StringUtils.isBlank(shipmentProductDetail.getNoOfCarton())) {
                    errMsg += "No of Cartons is required for Good" + String.valueOf(i) + "<br/>";
                }
            }
            if (StringUtils.isBlank(shipmentProductDetail.getDescription())) {
                errMsg += "Goods Description is required for Good" + String.valueOf(i) + "<br/>";
            }
            if (StringUtils.isBlank(shipmentProductDetail.getQty())) {
                errMsg += "Quantity is required for Good" + String.valueOf(i) + "<br/>";
            } else if (shipmentProductDetail.getQty().length() > 10){
                errMsg += "Quantity max length of Good" + String.valueOf(i) + " must be <= 10" + "<br/>";
            }
            if (StringUtils.isBlank(shipmentProductDetail.getAmount())) {
                errMsg += "Unit Value is required for Good" + String.valueOf(i) + "<br/>";
            } else if (shipmentProductDetail.getAmount().length() > 10){
                errMsg += "Unit Value max length of Good" + String.valueOf(i) + " must be <= 10" + "<br/>";
            }
            if (StringUtils.isBlank(errMsg)) {
                totalUnitValue += MathUtils.round(Double.valueOf(shipmentProductDetail.getAmount()) * Double.valueOf(shipmentProductDetail.getQty()), 2);
            }
            i++;
        }

        try {
            totalUnitValue = MathUtils.round(totalUnitValue, 2);
            Double totalCustomValue = Double.parseDouble(this.getShipmentRequestModel().getQuote().getTotalCustomValue());
            if (totalCustomValue < 0) {
                errMsg += "Total Customs Value must be >= 0" + "<br/>";
            }
            totalCustomValue = MathUtils.round(totalCustomValue, 2);
            Double substract = totalCustomValue - totalUnitValue;
            if (StringUtils.isBlank(errMsg)) {
                if (substract != 0) {
                    errMsg += "Total Customs Value must match with the Item Sub-Total on the list of Goods.";
                }
            }
        } catch (Exception e) {
            errMsg += "Total Customs Value must be number" + "<br/>";
        }
        if (!StringUtils.isBlank(errMsg)) {
            throw new CustomException(errMsg);
        }
    }

    protected boolean isValidFormInputAddress(AddressModel addressModel) {
        if (StringUtils.isBlank(addressModel.getContactName())) {
            addFieldError("shipmentRequestModel.scheduleCollection.saddress.contactName", "Contact is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getAddress())) {
            addFieldError("shipmentRequestModel.scheduleCollection.saddress.address", "Address is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getPhone())) {
            addFieldError("shipmentRequestModel.scheduleCollection.saddress.phone", "Phone is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getCompanyName())) {
            addFieldError("shipmentRequestModel.scheduleCollection.saddress.companyName", "Company Name is not empty.");
        }

        if (StringUtils.isBlank(addressModel.getCity())) {
            addFieldError("shipmentRequestModel.scheduleCollection.saddress.companyName", "City is not empty.");
        }

        if (hasFieldErrors()) {
            return false;
        }
        return true;
    }

    protected boolean isValidFormInputSchedule(ScheduleCollectionModel scheduleCollectionModel) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date pickUpdateDate = sdf.parse(scheduleCollectionModel.getPickupDate());
        Date currentDate = sdf.parse(sdf.format(new Date()));

        if (pickUpdateDate.compareTo(currentDate) < 0) {
            addFieldError("shipmentRequestModel.scheduleCollection.pickupDate", "Pickup Date must not be less than today date.");
        }

        if (StringUtils.isBlank(scheduleCollectionModel.getNoOfPieces())) {
            addFieldError("shipmentRequestModel.scheduleCollection.noOfPieces", "Number of pieces is not empty.");
        } else {
            if (!NumberUtils.isNumber(scheduleCollectionModel.getNoOfPieces())) {
                addFieldError("shipmentRequestModel.scheduleCollection.noOfPieces", "Number of pieces is not number.");
            } else {
                if (scheduleCollectionModel.getNoOfPieces().contains("-")) {
                    addFieldError("shipmentRequestModel.scheduleCollection.noOfPieces", "Number of pieces is negative.");
                } else {
                    if (scheduleCollectionModel.getNoOfPieces().contains(".")) {
                        addFieldError("shipmentRequestModel.scheduleCollection.noOfPieces", "Number of pieces is decimal.");
                    }
                }
            }
        }

        if (StringUtils.isBlank(scheduleCollectionModel.getTotalWeight())) {
            addFieldError("shipmentRequestModel.scheduleCollection.totalWeight", "Total weight is not empty.");
        } else {
            if (!NumberUtils.isNumber(scheduleCollectionModel.getTotalWeight())) {
                addFieldError("shipmentRequestModel.scheduleCollection.totalWeight", "Total weight is not number.");
            } else {
                if (scheduleCollectionModel.getTotalWeight().contains("-")) {
                    addFieldError("scheduleCollectionModel.totalWeight", "Total weight is negative.");
                }
            }
        }

        if (StringUtils.isBlank(scheduleCollectionModel.getDescription())) {
            addFieldError("shipmentRequestModel.scheduleCollection.description", "Location Description is not empty.");
        }

        if (!scheduleCollectionModel.getPickupTime().equals("0")) {
            if (scheduleCollectionModel.getPickupTimeNoLater().equals("0")) {
                addFieldError("shipmentRequestModel.scheduleCollection.pickupTimeNoLater", "Please choose pickup no later.");
            } else {
                String[] pickTimeArr = scheduleCollectionModel.getPickupTime().split(":");
                String[] closeTimeArr = scheduleCollectionModel.getPickupTimeNoLater().split(":");
                Integer pickTime = Integer.parseInt(pickTimeArr[0]) * 3600 + Integer.parseInt(pickTimeArr[1]) * 60 + Integer.parseInt(pickTimeArr[2]);
                Integer closeTime = Integer.parseInt(closeTimeArr[0]) * 3600 + Integer.parseInt(closeTimeArr[1]) * 60 + Integer.parseInt(closeTimeArr[2]);
                String defaultMinimumPickupHour = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_MINIMUM_PICKUP_HOUR); // hour
                Integer defaultPickMin = Integer.valueOf(defaultMinimumPickupHour) * 3600;
                Integer pickDiff = closeTime - pickTime;
                if (pickTime > closeTime) {
                    addFieldError("shipmentRequestModel.scheduleCollection.pickTime", "Package Pickup Time is greater than pickup package no later than.");
                }
                if (pickDiff < defaultPickMin) {
                    addFieldError("shipmentRequestModel.scheduleCollection.pickTime", "Package Pickup Time should be before the Pickup Package no later than for about Min " + defaultMinimumPickupHour + " hour");
                }
            }
        } else {
            addFieldError("shipmentRequestModel.scheduleCollection.pickupTimeNoLater", "Please choose pickup time.");
            if (scheduleCollectionModel.getPickupTimeNoLater().equals("0")) {
                addFieldError("shipmentRequestModel.scheduleCollection.pickupTimeNoLater", "Please choose pickup no later.");
            }
        }

        if (hasFieldErrors()) {
            return false;
        }
        return true;
    }

    protected ShipmentRequestModel reBuildShipmentRequestModel(ShipmentRequestModel shipmentRequestModelN) throws Exception {
        if (shipmentRequestModel.getScheduleCollection() != null) {
            shipmentRequestModelN.setScheduleCollection(shipmentRequestModel.getScheduleCollection());
        }

        if (shipmentRequestModel.getShipmentInfo() != null) {
            if (shipmentRequestModel.getShipmentInfo().getBillingAccount() != null) {
                shipmentRequestModelN.getShipmentInfo().setBillingAccount(shipmentRequestModel.getShipmentInfo().getBillingAccount());
            }

            if (shipmentRequestModel.getShipmentInfo().getShipperAccount() != null) {
                shipmentRequestModelN.getShipmentInfo().setShipperAccount(shipmentRequestModel.getShipmentInfo().getShipperAccount());
            }
            shipmentRequestModelN.getShipmentInfo().setBillingType(shipmentRequestModel.getShipmentInfo().getBillingType());
            shipmentRequestModelN.getShipmentInfo().setDutiesType(shipmentRequestModel.getShipmentInfo().getDutiesType());
            shipmentRequestModelN.getShipmentInfo().setDutiesAccount(shipmentRequestModel.getShipmentInfo().getDutiesAccount());
        }

        shipmentRequestModelN.setContentDetail(shipmentRequestModel.getContentDetail());
        shipmentRequestModelN.setShipmentReference(shipmentRequestModel.getShipmentReference());

        if (!shipmentRequestModelN.getShipmentInfo().getBound().equalsIgnoreCase("15")) {
            if (!StringUtils.isBlank(shipmentRequestModel.getQuote().getTotalCustomValue())) {
                shipmentRequestModelN.getQuote().setTotalCustomValue(shipmentRequestModel.getQuote().getTotalCustomValue());
            }
        } else {
            shipmentRequestModelN.getQuote().setTotalCustomValue("0.00");
        }
        if (this.getShipmentRequestModel().getShipmentInfo() != null) {
            if (!StringUtils.isBlank(shipmentRequestModel.getShipmentInfo().getSpecialDelivery())) {
                shipmentRequestModelN.getShipmentInfo().setSpecialDeliveryinst(this.getShipmentRequestModel().getShipmentInfo().getSpecialDelivery());
            }
            if (!StringUtils.isBlank(this.getShipmentRequestModel().getShipmentInfo().getReasonForExport())) {
                shipmentRequestModelN.getShipmentInfo().setReasonForExport(this.getShipmentRequestModel().getShipmentInfo().getReasonForExport());
            }
            if (!StringUtils.isBlank(this.getShipmentRequestModel().getShipmentInfo().getTermOfTrade())) {
                shipmentRequestModelN.getShipmentInfo().setTermOfTrade(this.getShipmentRequestModel().getShipmentInfo().getTermOfTrade());
            }
            if (!StringUtils.isBlank(this.getShipmentRequestModel().getShipmentInfo().getReceiverTaxId())) {
                shipmentRequestModelN.getShipmentInfo().setReceiverTaxId(this.getShipmentRequestModel().getShipmentInfo().getReceiverTaxId());
            }
        }
        shipmentRequestModelN.setShipmentProductDetails(this.getShipmentRequestModel().getShipmentProductDetails());
        return shipmentRequestModelN;
    }

    @Override
    public String getShipmentRequestModelGson() {
        return shipmentRequestModelGson;
    }

    @Override
    public void setShipmentRequestModelGson(String shipmentRequestModelGson) {
        this.shipmentRequestModelGson = shipmentRequestModelGson;
    }

    @Override
    public ShipmentRequestModel getShipmentRequestModel() {
        return shipmentRequestModel;
    }

    @Override
    public void setShipmentRequestModel(ShipmentRequestModel shipmentRequestModel) {
        this.shipmentRequestModel = shipmentRequestModel;
    }

    @Override
    public ShipmentModel getShipmentModel() {
        return shipmentModel;
    }

    @Override
    public void setShipmentModel(ShipmentModel shipmentModel) {
        this.shipmentModel = shipmentModel;
    }

    @Override
    public String getPackingListSelect() {
        return packingListSelect;
    }

    @Override
    public void setPackingListSelect(String packingListSelect) {
        this.packingListSelect = packingListSelect;
    }

    @Override
    public String getCommercialInvoiceSelect() {
        return commercialInvoiceSelect;
    }

    @Override
    public void setCommercialInvoiceSelect(String commercialInvoiceSelect) {
        this.commercialInvoiceSelect = commercialInvoiceSelect;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

}