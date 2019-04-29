package com.gms.xms.workflow.task2.toll.ipec.booking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.webship.TollIpecConnoteFilter;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.service.tollipecconnote.ITollIpecConnoteService;
import com.gms.xms.persistence.service.tollipecconnote.TollIpecConnoteServiceImp;
import com.gms.xms.persistence.service.webship.shipment.BookingShipmentServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IBookingShipmentService;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Posted from ProcessTollIpecSaveShipmentDataTask
 * <p>
 * Author @author HungNT Feb 16, 2016
 */
public class ProcessTollIpecSaveShipmentDataTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessTollIpecSaveShipmentDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        IBookingShipmentService bookingShipmentService = new BookingShipmentServiceImp();
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            Integer selCollection = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
            bookingDataVo = bookingShipmentService.bookingShipment(addInfo, bookingDataVo);

            // Insert connote
            ITollIpecConnoteService tollIpecConnoteService = new TollIpecConnoteServiceImp();
            TollIpecConnoteFilter tollIpecConnoteFilter = new TollIpecConnoteFilter();
            String connStart = "1";
            String connEnd = "9999999";
            //String connPrefix = "736531";//prevoius
            String connPrefix = "657180";
            tollIpecConnoteFilter.setConnEnd(connEnd);
            tollIpecConnoteFilter.setConnStart(connStart);
             tollIpecConnoteFilter.setConnPrefix(connPrefix); 
            tollIpecConnoteFilter.setShipmentId(bookingDataVo.getShipmentVo().getShipmentId());
            tollIpecConnoteService.insertConnote(addInfo, tollIpecConnoteFilter);
            String airbillNumber = tollIpecConnoteService.getConnNumber(tollIpecConnoteFilter);

            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(bookingDataVo.getShipmentVo().getShipmentId());
            shipmentVo.setAirbillNumber(airbillNumber);
            shipmentService.update(addInfo, shipmentVo);
            bookingDataVo.getShipmentVo().setAirbillNumber(airbillNumber);
            if (selCollection == 1) {
                ScheduleCollectionVo scheduleCollectionVo = bookingDataVo.getScheduleCollection();
                AddressVo pickupAddress = scheduleCollectionVo.getSaddress();
                if (StringUtils.isBlank(bookingDataVo.getShipmentVo().getSpecialDeliveryinst())) {
                    scheduleCollectionVo.setSpecialInstructions("None");
                } else {
                    scheduleCollectionVo.setSpecialInstructions(bookingDataVo.getShipmentVo().getSpecialDeliveryinst());
                }
                scheduleCollectionVo.setNoOfPieces(bookingDataVo.getShipmentVo().getNoOfPieces());
                scheduleCollectionVo.setTotalWeight(new BigDecimal(bookingDataVo.getQuoteVo().getWeight()).setScale(2, RoundingMode.HALF_UP).floatValue());
                scheduleCollectionVo.setPickupAddress(pickupAddress);
                ContextBase2 context2 = new ContextBase2(addInfo);
                context2.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
                context2.put(Attributes.SHIPMENT_ID, bookingDataVo.getShipmentVo().getShipmentId());
                context2.put(Attributes.WFP_NAME, "Wfl-TollIpecScheduleCollection");

                context2 = WorkFlowManager2.getInstance().process(context2);
                if (context2.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.ERROR)) {
                    context.put(Attributes.SCHEDULE_COLLECTION_ERROR_MESSAGE, context2.getString(Attributes.ERROR_MESSAGE));
                } else {
                    scheduleCollectionVo = context2.get(Attributes.SCHEDULECOLLECTION_VO);
                    bookingDataVo.setScheduleCollection(scheduleCollectionVo);
                }
            }
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
