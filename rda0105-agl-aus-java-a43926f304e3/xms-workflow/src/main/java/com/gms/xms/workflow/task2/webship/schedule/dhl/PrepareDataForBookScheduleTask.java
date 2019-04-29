package com.gms.xms.workflow.task2.webship.schedule.dhl;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;
import com.gms.xms.persistence.service.piece.IPieceService;
import com.gms.xms.persistence.service.piece.PieceServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.schedulecollection.BookPickupRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from PrepareDataForBookScheduleTask
 * <p>
 * Author TANDT
 */
public class PrepareDataForBookScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataForBookScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Get Schedule Collection.
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULE);
            // Get Pickup Address.
            AddressVo pickupAddressVo = context.get(Attributes.PICKUP_ADDRESS);
            String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            if (pickupAddressVo.getCountry() == null) {
                pickupAddressVo.setCountry(Long.parseLong(defaultOriginCountry));
            }
            if (pickupAddressVo.getCountryDetail() == null) {
                ICountryService countryService = new CountryServiceImp();
                CountryVo countryDetail = countryService.getCountryByCountryId(pickupAddressVo.getCountry());
                pickupAddressVo.setCountryDetail(countryDetail);
            }
            // Get Shipment.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            // Get Sender Address.
            IAddressService addressService = new AddressServiceImp();
            AddressVo senderAddressVo = addressService.getAddressById(shipmentVo.getSenderAddressId());
            // Get Location Code.
            ILocationCodeService locationCodeService = new LocationCodeServiceImp();
            LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(scheduleCollectionVo.getLocationCodeId());
            // Get DHL Country.
            IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
            CountryDao countryDao = new CountryDao();
            CountryVo countryVo = countryDao.getCountryById(senderAddressVo.getCountry());
            DhlCountryVo dhlCountryVo = dhlCountryService.selectDhlCountryByName(countryVo.getCountryName());
            IPieceService pieceService = new PieceServiceImp();
            List<PieceVo> pieceVos = pieceService.getPiecesByShipmentId(shipmentId);
            PieceVo pickupPieceVo = pieceVos.get(0);

            // Prepare DHL bookPickupRequestVo.
            BookPickupRequestVo bookPickupRequestVo = new BookPickupRequestVo();
            bookPickupRequestVo.setShipment(shipmentVo);
            scheduleCollectionVo.setShipmentId(shipmentId);
            bookPickupRequestVo.setScheduleCollection(scheduleCollectionVo);
            bookPickupRequestVo.setPickupAddress(pickupAddressVo);
            bookPickupRequestVo.setSenderAddress(senderAddressVo);
            bookPickupRequestVo.setLocationCode(locationCodeVo);
            bookPickupRequestVo.setDhlCountry(dhlCountryVo);
            bookPickupRequestVo.setPieceVo(pickupPieceVo);
            context.put(Attributes.DHL_BOOK_SCHEDULE_REQUEST, bookPickupRequestVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
        }
        return true;
    }
}
