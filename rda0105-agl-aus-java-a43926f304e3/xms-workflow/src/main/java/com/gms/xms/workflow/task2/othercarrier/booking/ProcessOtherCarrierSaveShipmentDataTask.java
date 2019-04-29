package com.gms.xms.workflow.task2.othercarrier.booking;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.webship.OtherConnoteFilter;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.service.otherconnote.IOtherConnoteService;
import com.gms.xms.persistence.service.otherconnote.OtherConnoteServiceImp;
import com.gms.xms.persistence.service.webship.shipment.BookingShipmentServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IBookingShipmentService;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SavePiecesTask
 * <p>
 * Author TANDT
 */
public class ProcessOtherCarrierSaveShipmentDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessOtherCarrierSaveShipmentDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
        IBookingShipmentService bookingShipmentService = new BookingShipmentServiceImp();
        try {
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            bookingDataVo = bookingShipmentService.bookingShipment(addInfo, bookingDataVo);

            // Insert connote
            IOtherConnoteService connoteService = new OtherConnoteServiceImp();
            OtherConnoteFilter otherConnoteFilter = new OtherConnoteFilter();
            String connStart = SystemSettingCache.getInstance().getValueByKey("Other Carrier AWB Number Start");
            connStart = StringUtils.isBlank(connStart) ? "10000001" : connStart;
            String connEnd = SystemSettingCache.getInstance().getValueByKey("Other Carrier AWB Number End");
            connEnd = StringUtils.isBlank(connEnd) ? "99999999" : connEnd;
            String connPrefix = SystemSettingCache.getInstance().getValueByKey("Other Carrier AWB Number Prefix");
            connPrefix = StringUtils.isBlank(connPrefix) ? "XMS" : connPrefix;
            otherConnoteFilter.setConnEnd(connEnd);
            otherConnoteFilter.setConnStart(connStart);
            otherConnoteFilter.setConnPrefix(connPrefix);
            otherConnoteFilter.setShipmentId(bookingDataVo.getShipmentVo().getShipmentId());
            connoteService.insertConnote(addInfo, otherConnoteFilter);
            String airbillNumber = connPrefix + connoteService.getConnNumber(otherConnoteFilter);

            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(bookingDataVo.getShipmentVo().getShipmentId());
            shipmentVo.setAirbillNumber(airbillNumber);
            shipmentService.update(addInfo, shipmentVo);
            bookingDataVo.getShipmentVo().setAirbillNumber(airbillNumber);
            context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
