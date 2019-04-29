package com.gms.xms.workflow.task2.ups.international.booking;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.email.ups.UpsIntlBookingEmailVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.render.email.ups.IUpsEmailRender;
import com.gms.xms.workflow.render.email.ups.UpsEmailRenderImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProcessUpsBookingSendEmailTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessUpsBookingSendEmailTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentRequestVo shipmentRequestVo = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            ShipmentInfoVo shipmentInfoVo = shipmentRequestVo.getShipmentInfo();
            String doSendEmail = context.getString(Attributes.DO_SEND_EMAIL);
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ILocationCodeService locationCodeService = new LocationCodeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            if (shipmentTypeVo.getShipmentTypeName().equalsIgnoreCase("Sameday Express")) {
                doSendEmail = AppConstants.YES_FLAG;
            }
            if (doSendEmail.equalsIgnoreCase(AppConstants.YES_FLAG)) {
                WebshipLoginVo webshipLoginVo = shipmentRequestVo.getWebshipLogin();
                BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
                UpsIntlBookingEmailVo bookingEmailVo = new UpsIntlBookingEmailVo();
                ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
                CustomerAddressVo customerAddress = customerAddressService.getCustomerAddressByCustomerCode(webshipLoginVo.getCustomerCode());
                String franchiseCode = String.valueOf(webshipLoginVo.getCustomerCode()).substring(0, 3) + "00001";
                CustomerAddressVo franchiseAdress = customerAddressService.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));
                bookingEmailVo.setFranchiseName(franchiseAdress.getCustomerName());
                bookingEmailVo.setCustomerName(customerAddress.getCustomerName());
                bookingEmailVo.setCustomerCode(String.valueOf(webshipLoginVo.getCustomerCode()));

                ScheduleCollectionVo scheduleCollection = bookingDataVo.getScheduleCollection();
                bookingEmailVo.setScheduleInfo(scheduleCollection);
                if(scheduleCollection!=null)
                {
                    LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(scheduleCollection.getLocationCodeId());
                    bookingEmailVo.setLocationCode(locationCodeVo.getLocationCodeName());
                    bookingEmailVo.setLocationDescription(locationCodeVo.getLocationCodeDescription());
                }
                String message = "";
                bookingEmailVo.setMessage(message);
                bookingEmailVo.setShipmentDate(shipmentInfoVo.getShipmentDate());
                bookingEmailVo.setShipmentTypeName(shipmentTypeVo.getShipmentTypeName());
                IPackageService packageService = new PackageServiceImp();
                PackageVo packageVo = packageService.getPackagebyId(shipmentInfoVo.getPackageId());
                bookingEmailVo.setPackageName(packageVo.getPackageName());
                bookingEmailVo.setNoOfPieces(shipmentInfoVo.getShipmentRequestPieces().size());
                bookingEmailVo.setTotalWeight(shipmentRequestVo.getQuote().getWeight());
                List<String> dimensions = new ArrayList<>();
                List<PieceVo> pieceVos = shipmentInfoVo.getShipmentRequestPieces();
                List<PieceModel> pieceModels = ModelUtils.createListModelFromVo(pieceVos, PieceModel.class);
                Integer count = 1;
                for (PieceModel pieceModel : pieceModels) {
                    String dimension = pieceModel.getWeight() + "kg(s)" + pieceModel.getDimensionL() + " x " + pieceModel.getDimensionW() + " x " + pieceModel.getDimensionH() + "cm(s), Pieces " + count;
                    dimensions.add(dimension);
                    count++;
                }
                bookingEmailVo.setDimensions(dimensions);
                bookingEmailVo.setCollectionInstruction(shipmentInfoVo.getSpecialDelivery());

                if (shipmentInfoVo.getWithInsurance() == null || shipmentInfoVo.getWithInsurance().equals(BigDecimal.ZERO)) {
                    bookingEmailVo.setInsurance("No");
                } else {
                    bookingEmailVo.setInsurance("Yes");
                }
                bookingEmailVo.setSenderAddress(shipmentInfoVo.getSenderAddress());
                bookingEmailVo.setReceiverAddress(shipmentInfoVo.getReceiverAddress());
                bookingEmailVo.setAirbillNumber(bookingDataVo.getShipmentVo().getAirbillNumber());
                IServiceService serviceService = new ServiceServiceImp();
                ServiceVo serviceVo = serviceService.selectById(shipmentInfoVo.getServiceId());
                bookingEmailVo.setCarrierName(serviceVo.getServiceName());
                IUpsEmailRender render = new UpsEmailRenderImp();
                String uniqueString = UUID.randomUUID().toString().replace("-", "");
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/ups_intl_email_" + uniqueString + ".txt";
                File dirTmp = new File(AppConstants.APP_SETTINGS.getAppTmpPath());
                if(!dirTmp.exists())
                {
                    dirTmp.mkdirs();
                }
                render.genrateBookingEmailMessage(bookingEmailVo, outputFilePath);
                String content = AppUtils.readUTF8File2String(outputFilePath);
                String subject = serviceVo.getServiceName() + " Booking for " + bookingDataVo.getShipmentVo().getAirbillNumber();
                String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
                String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
                String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
                String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
                String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
                int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
                if(shipmentInfoVo.getSenderAddress().getEmail() != null && shipmentInfoVo.getSenderAddress().getEmail() != ""){
                	AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseAdress.getEmail(), shipmentInfoVo.getSenderAddress().getEmail(), null, subject, content, null);
                }else{
                	AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseAdress.getEmail(), null, null, subject, content, null);	
                }
                
            }

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
