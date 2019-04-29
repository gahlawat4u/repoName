package com.gms.xms.workflow.task2.toll.priority.booking;

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
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.email.tollpriority.TollPriorityShipmentRequestEmailVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.render.email.tollpriority.ITollPriorityEmailRender;
import com.gms.xms.workflow.render.email.tollpriority.TollPriorityEmailRenderImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Posted from ProcessSendEmailTollPriorityTask
 * <p>
 * Author @author HungNT Feb 18, 2016
 */
public class ProcessSendEmailTollPriorityTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessSendEmailTollPriorityTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {

            ShipmentRequestVo shipmentRequestVo = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            ShipmentInfoVo shipmentInfoVo = shipmentRequestVo.getShipmentInfo();
            WebshipLoginVo webshipLoginVo = shipmentRequestVo.getWebshipLogin();
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);

            Map<String, ServiceAddConVo> serviceAddCon = shipmentInfoVo.getServiceAddConMap();
            if ((serviceAddCon.get("timecriticial") != null && serviceAddCon.get("timecriticial").getValue() != null && serviceAddCon.get("timecriticial").getValue().equals("1")) || shipmentInfoVo.getShipmentTypeId() == 174) {
                TollPriorityShipmentRequestEmailVo requestEmailVo = new TollPriorityShipmentRequestEmailVo();
                ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
                CustomerAddressVo customerAddress = customerAddressService.getCustomerAddressByCustomerCode(webshipLoginVo.getCustomerCode());
                String franchiseCode = String.valueOf(webshipLoginVo.getCustomerCode()).substring(0, 3) + "00001";
                CustomerAddressVo franchiseAdress = customerAddressService.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));
                String timeCriticial;
                if (serviceAddCon.get("timecriticial") != null) {
                    timeCriticial = serviceAddCon.get("timecriticial").getListProperties().get(0).getValue();
                    requestEmailVo.setTimeCriticial(timeCriticial);
                }

                requestEmailVo.setFranchiseName(franchiseAdress.getCustomerName());
                requestEmailVo.setCustomerName(customerAddress.getCustomerName());
                requestEmailVo.setCustomerCode(String.valueOf(webshipLoginVo.getCustomerCode()));
                requestEmailVo.setShipmentDate(shipmentInfoVo.getShipmentDate());

                IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
                ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
                requestEmailVo.setShipmentTypeName(shipmentTypeVo.getShipmentTypeName());
                IPackageService packageService = new PackageServiceImp();
                PackageVo packageVo = packageService.getPackagebyId(shipmentInfoVo.getPackageId());
                requestEmailVo.setPackageName(packageVo.getPackageName());
                requestEmailVo.setNoOfPieces(shipmentInfoVo.getShipmentRequestPieces().size());
                requestEmailVo.setTotalWeight(shipmentRequestVo.getQuote().getWeight());
                List<String> dimensions = new ArrayList<>();
                List<PieceVo> pieceVos = shipmentInfoVo.getShipmentRequestPieces();
                List<PieceModel> pieceModels = ModelUtils.createListModelFromVo(pieceVos, PieceModel.class);
                Integer count = 1;
                for (PieceModel pieceModel : pieceModels) {
                    String dimension = pieceModel.getWeight() + "kg(s)" + pieceModel.getDimensionL() + " x " + pieceModel.getDimensionH() + " x " + pieceModel.getDimensionW() + "cm(s), Pieces " + count;
                    dimensions.add(dimension);
                    count++;
                }
                requestEmailVo.setDimensions(dimensions);
                requestEmailVo.setInsurance("No");
                requestEmailVo.setSenderAddress(shipmentInfoVo.getSenderAddress());
                requestEmailVo.setReceiverAddress(shipmentInfoVo.getReceiverAddress());
                requestEmailVo.setAirbillNumber(bookingDataVo.getShipmentVo().getAirbillNumber());
                IServiceService serviceService = new ServiceServiceImp();
                ServiceVo serviceVo = serviceService.selectById(shipmentInfoVo.getServiceId());
                requestEmailVo.setCarrierName(serviceVo.getServiceName());

                ITollPriorityEmailRender render = new TollPriorityEmailRenderImp();
                String uniqueString = UUID.randomUUID().toString().replace("-", "");
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/toll_priority_email_" + uniqueString + ".txt";
                File dirTmp = new File(AppConstants.APP_SETTINGS.getAppTmpPath());
                if(!dirTmp.exists())
                {
                    dirTmp.mkdirs();
                }
                render.generateShipmentRequestEmailMessage(requestEmailVo, outputFilePath);
                String content = AppUtils.readUTF8File2String(outputFilePath);
                String subject = serviceVo.getServiceName() + " Booking for " + bookingDataVo.getShipmentVo().getAirbillNumber();
                String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
                String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
                String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
                String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
                String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
                int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
               // AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseAdress.getEmail(), null, null, subject, content, null);
                if(shipmentInfoVo.getSenderAddress().getEmail() != null && shipmentInfoVo.getSenderAddress().getEmail() != ""){
                	AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseAdress.getEmail(), shipmentInfoVo.getSenderAddress().getEmail(), null, subject, content, null);
                }else{
                	AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, franchiseAdress.getEmail(), null, null, subject, content, null);	
                }
            
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "Send email failed:" + e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
