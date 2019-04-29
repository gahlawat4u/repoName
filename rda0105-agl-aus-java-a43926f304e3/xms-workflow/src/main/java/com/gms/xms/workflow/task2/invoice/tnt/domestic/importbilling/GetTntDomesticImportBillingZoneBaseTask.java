package com.gms.xms.workflow.task2.invoice.tnt.domestic.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CarrierPostCodeDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CarrierPostCodeFilter;
import com.gms.xms.txndb.vo.CarrierPostCodeVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jul 22, 2016 10:31:56 AM
 * <p>
 * Author huynd
 */
public class GetTntDomesticImportBillingZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntDomesticImportBillingZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            AddressVo senderAddress = saveImportBillingVo.getSenderAddress();
            String senderPostalCode = (senderAddress.getPostalCode() == null) ? "" : senderAddress.getPostalCode();

            AddressVo receiverAddress = saveImportBillingVo.getReceiverAddress();
            String receiverPostalCode = (receiverAddress.getPostalCode() == null) ? "" : receiverAddress.getPostalCode();

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
