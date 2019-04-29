package com.gms.xms.workflow.task2.webship.schedule;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;
import com.gms.xms.persistence.service.piece.IPieceService;
import com.gms.xms.persistence.service.piece.PieceServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.schedulecollection.BookPickupRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from PrepareDataScheduleTask
 * <p>
 * Author TANDT Date
 */
public class PrepareDataScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            // Get Schedule Collection.
            ScheduleCollectionVo scheduleCollectionVo = bookingDataVo.getScheduleCollection();
            // Get Pickup Address.
            AddressVo pickupAddressVo = scheduleCollectionVo.getSaddress();
            // Get Shipment.
            ShipmentVo shipmentVo = bookingDataVo.getShipmentVo();
            // Get Sender Address.
            AddressVo senderAddressVo = bookingDataVo.getsAddress();
            // Get Location Code.
            ILocationCodeService locationCodeService = new LocationCodeServiceImp();
            LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(scheduleCollectionVo.getLocationCodeId());
            // Get DHL Country.
            IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
            DhlCountryVo dhlCountryVo = dhlCountryService.selectDhlCountryByDhlCountryId(senderAddressVo.getCountry());
            IPieceService pieceService = new PieceServiceImp();
            List<PieceVo> pieceVos = pieceService.getPiecesByShipmentId(shipmentVo.getShipmentId());
            PieceVo pickupPieceVo = pieceVos.get(0);

            // Prepare DHL bookPickupRequestVo.
            BookPickupRequestVo bookPickupRequestVo = new BookPickupRequestVo();
            bookPickupRequestVo.setScheduleCollection(scheduleCollectionVo);
            bookPickupRequestVo.setPickupAddress(pickupAddressVo);
            bookPickupRequestVo.setSenderAddress(senderAddressVo);
            bookPickupRequestVo.setLocationCode(locationCodeVo);
            bookPickupRequestVo.setDhlCountry(dhlCountryVo);
            bookPickupRequestVo.setPieceVo(pickupPieceVo);
            context.put(Attributes.DHL_BOOK_SCHEDULE_REQUEST, bookPickupRequestVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
