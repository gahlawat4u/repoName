package com.gms.xms.workflow.task.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CarrierPostCodeDao;
import com.gms.xms.persistence.dao.MultiZoneDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlDomesticZoneTask
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class GetDhlDomesticZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetDhlDomesticZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CarrierPostCodeDao carrierPostCodeDao = new CarrierPostCodeDao();
        CarrierPostCodeVo carrierPostCodeVo = new CarrierPostCodeVo();
        CarrierPostCodeFilter carrierPostCodeFilter = new CarrierPostCodeFilter();
        MultiZoneDao multiZoneDao = new MultiZoneDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
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

            carrierPostCodeFilter.setCarrier(2L);
            // Get sender zone code
            carrierPostCodeFilter.setPostCode(senderPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String senderZoneCode = carrierPostCodeVo.getZoneCode();

            // Get receiver zone code
            carrierPostCodeFilter.setPostCode(receiverPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String receiverZoneCode = carrierPostCodeVo.getZoneCode();
            // Get DHL Domestic zone
            MultiZoneFilter multiZoneFilter = new MultiZoneFilter();
            multiZoneFilter.setSenderZoneCode(senderZoneCode);
            multiZoneFilter.setReceiverZoneCode(receiverZoneCode);
            MultiZoneVo multiZoneVo = multiZoneDao.selectDhlDomesticZone(multiZoneFilter);

            String dhlDomesticZone = multiZoneVo.getZone();

            shipmentInfoVo.setZone(dhlDomesticZone);
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfoVo));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
