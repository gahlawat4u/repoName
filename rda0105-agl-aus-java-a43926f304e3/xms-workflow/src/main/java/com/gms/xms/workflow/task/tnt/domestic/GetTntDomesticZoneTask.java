package com.gms.xms.workflow.task.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CarrierPostCodeDao;
import com.gms.xms.txndb.vo.CarrierPostCodeFilter;
import com.gms.xms.txndb.vo.CarrierPostCodeVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTntDomesticZoneTask
 * <p>
 * Author HungNT Date Aug 6, 2015
 */
public class GetTntDomesticZoneTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntDomesticZoneTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
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

            CarrierPostCodeFilter carrierPostCodeFilter = new CarrierPostCodeFilter();
            CarrierPostCodeVo carrierPostCodeVo = new CarrierPostCodeVo();
            CarrierPostCodeDao carrierPostCodeDao = new CarrierPostCodeDao();
            carrierPostCodeFilter.setCarrier(3L);
            // Get sender zone code
            carrierPostCodeFilter.setPostCode(senderPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String senderZoneCode = carrierPostCodeVo.getZoneCode();

            // Get receiver zone code
            carrierPostCodeFilter.setPostCode(receiverPostalCode);
            carrierPostCodeVo = carrierPostCodeDao.selectCarrierPostCodeByPostCodeAndCarrier(carrierPostCodeFilter);
            String receiverZoneCode = carrierPostCodeVo.getZoneCode();
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
