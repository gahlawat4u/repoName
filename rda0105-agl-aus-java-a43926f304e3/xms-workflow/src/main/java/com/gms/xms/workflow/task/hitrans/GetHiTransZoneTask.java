package com.gms.xms.workflow.task.hitrans;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

/**
 * @author tkvcl
 */
public class GetHiTransZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetHiTransZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
            String senderPostalCode = shipmentInfoVo.getSenderAddress().getPostalCode();
            String senderCity = shipmentInfoVo.getSenderAddress().getCity();
            String receiverPostalCode = shipmentInfoVo.getReceiverAddress().getPostalCode();
            String receiverCity = shipmentInfoVo.getReceiverAddress().getCity();

            if (!ShipmentUtils.isCarrierSuburb(senderCity, senderPostalCode)) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Please enter valid city name and postal code for sender's address.");
                return false;
            }
            if (!ShipmentUtils.isCarrierSuburb(receiverCity, receiverPostalCode)) {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Please enter valid city name and postal code for receiver's address.");
                return false;
            }

            Integer shipmentTypeId = 0;
            String customerCode = "";
            String emailFranchise = "";
            String phoneFranchise = "";
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            customerCode = webshipLoginInfo.getCustomerCode().toString();
            if (shipmentInfoVo != null) {
                shipmentTypeId = shipmentInfoVo.getShipmentTypeId();
            }

            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            CustomerAddressVo addressBookVo = customerAddressDao.getByCode(customerCode);
            if (addressBookVo != null) {
                emailFranchise = addressBookVo.getEmail();
                phoneFranchise = addressBookVo.getPhone();
            }

            String[] serviceAvaible = {"1", "2", "3", "15", "40", "50", "51", "52", "54", "55", "56", "57", "58", "59"};
            String message = "Please contact your freight consultant at (".concat(phoneFranchise).concat(" )").concat(" or email ( ").concat(emailFranchise).concat(" ).");
            if (shipmentTypeVo != null) {
                if (shipmentInfoVo.getServiceId() == 1 && (shipmentTypeVo.getShipmentTypeName().trim().equals("DHL ETS") || shipmentTypeVo.getShipmentTypeName().trim().equals("ETS"))) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, message);
                    return false;
                }

                if (!Arrays.asList(serviceAvaible).contains(shipmentInfoVo.getServiceId().toString())) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, message);
                    return false;
                }
            }

        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
            return false;
        }
        return true;
    }

}
