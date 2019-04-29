package com.gms.xms.workflow.task2.invoice.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.CarrierPostCodeDao;
import com.gms.xms.persistence.dao.MultiZoneDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 19, 2016 3:11:09 PM
 * <p>
 * Author huynd
 */
public class GetDhlDomesticZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlDomesticZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            CarrierPostCodeDao carrierPostCodeDao = new CarrierPostCodeDao();
            CarrierPostCodeVo carrierPostCodeVo = new CarrierPostCodeVo();
            CarrierPostCodeFilter carrierPostCodeFilter = new CarrierPostCodeFilter();
            MultiZoneDao multiZoneDao = new MultiZoneDao();
            String dhlDomesticZone = (shipmentBilling.getZone() == null) ? "1" : shipmentBilling.getZone();

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

            carrierPostCodeFilter.setCarrier(2L);
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
            // Get DHL Domestic zone
            MultiZoneFilter multiZoneFilter = new MultiZoneFilter();
            multiZoneFilter.setSenderZoneCode(senderZoneCode);
            multiZoneFilter.setReceiverZoneCode(receiverZoneCode);
            MultiZoneVo multiZoneVo = multiZoneDao.selectDhlDomesticZone(multiZoneFilter);
            if (multiZoneVo != null) {
                dhlDomesticZone = multiZoneVo.getZone();
            }
            if (shipmentBilling.getZone() == null) {
                shipmentBilling.setZone(dhlDomesticZone);
                context.put(Attributes.ZONE, dhlDomesticZone);
            }
            context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
