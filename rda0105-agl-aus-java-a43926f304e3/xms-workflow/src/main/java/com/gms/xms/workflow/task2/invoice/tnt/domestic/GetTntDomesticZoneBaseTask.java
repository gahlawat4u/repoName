package com.gms.xms.workflow.task2.invoice.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.CarrierPostCodeDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CarrierPostCodeFilter;
import com.gms.xms.txndb.vo.CarrierPostCodeVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 19, 2016 5:10:49 PM
 * <p>
 * Author huynd
 */
public class GetTntDomesticZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntDomesticZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            AddressDao addressDao = new AddressDao();
            String senderPostalCode = "";
            Integer senderAddressId = shipmentBilling.getSenderAddressId();
            AddressVo senderAddress = addressDao.selectById(senderAddressId);
            if (senderAddress != null) {
                senderPostalCode = (senderAddress.getPostalCode() == null) ? "" : senderAddress.getPostalCode();
            }

            Integer receiverAddressId = shipmentBilling.getReceiverAddressId();
            String receiverPostalCode = "";
            AddressVo receiverAddress = addressDao.selectById(receiverAddressId);
            if (receiverAddress != null) {
                receiverPostalCode = (receiverAddress.getPostalCode() == null) ? "" : receiverAddress.getPostalCode();
            }

            CarrierPostCodeFilter carrierPostCodeFilter = new CarrierPostCodeFilter();
            CarrierPostCodeVo carrierPostCodeVo = new CarrierPostCodeVo();
            CarrierPostCodeDao carrierPostCodeDao = new CarrierPostCodeDao();
            carrierPostCodeFilter.setCarrier(3L);
            // Get sender zone code
            carrierPostCodeFilter.setPostCode(senderPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String senderZoneCode = (shipmentBilling.getServiceAreaCodeOrigin() == null) ? "" : shipmentBilling.getServiceAreaCodeOrigin();
            if (carrierPostCodeVo != null) {
                senderZoneCode = carrierPostCodeVo.getZoneCode();
            }

            // Get receiver zone code
            carrierPostCodeFilter.setPostCode(receiverPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String receiverZoneCode = (shipmentBilling.getServiceAreaCodeDestination() == null) ? "" : shipmentBilling.getServiceAreaCodeDestination();
            if (carrierPostCodeVo != null) {
                receiverZoneCode = carrierPostCodeVo.getZoneCode();
            }
            context.put(Attributes.SENDER_ZONE_CODE, senderZoneCode);
            context.put(Attributes.RECEIVER_ZONE_CODE, receiverZoneCode);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
