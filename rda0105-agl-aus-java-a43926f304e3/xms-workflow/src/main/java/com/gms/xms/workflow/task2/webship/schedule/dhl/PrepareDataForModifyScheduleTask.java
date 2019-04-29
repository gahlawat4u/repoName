package com.gms.xms.workflow.task2.webship.schedule.dhl;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.schedulecollection.ModifyPickupRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareDataForModifyScheduleTask
 * <p>
 * Author dattrinh Jan 25, 2016 3:18:04 PM
 */
public class PrepareDataForModifyScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataForModifyScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get Schedule Collection.
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULE);

            ScheduleCollectionDao collectionDao = new ScheduleCollectionDao();
            ScheduleCollectionVo oldScheduleCollection = collectionDao.selectById(String.valueOf(scheduleCollectionVo.getScheduleCollectionId()));

            scheduleCollectionVo.setConfirmationNo(oldScheduleCollection.getConfirmationNo());
            // Get Pickup Address.
            AddressVo pickupAddressVo = context.get(Attributes.PICKUP_ADDRESS);

            // Get Shipment.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(oldScheduleCollection.getShipmentId());
            // Get Sender Address.
            IAddressService addressService = new AddressServiceImp();
            AddressVo senderAddressVo = addressService.getAddressById(shipmentVo.getSenderAddressId());

            CountryDao countryDao = new CountryDao();
            CountryVo countryVo = countryDao.getCountryById(pickupAddressVo.getCountry());
            pickupAddressVo.setCountryDetail(countryVo);
            // Get Location Code.
            ILocationCodeService locationCodeService = new LocationCodeServiceImp();
            LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(scheduleCollectionVo.getLocationCodeId());
            // Get DHL Country.
            IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
            DhlCountryVo dhlCountryVo = dhlCountryService.selectDhlCountryByDhlCountryId(senderAddressVo.getCountry());
            // Prepare DHL ModifyPickupRequestVo.
            ModifyPickupRequestVo modifyPickupRequestVo = new ModifyPickupRequestVo();
            modifyPickupRequestVo.setScheduleCollection(scheduleCollectionVo);
            modifyPickupRequestVo.setPickupAddress(pickupAddressVo);
            modifyPickupRequestVo.setSenderAddress(senderAddressVo);
            modifyPickupRequestVo.setLocationCode(locationCodeVo);
            modifyPickupRequestVo.setDhlCountry(dhlCountryVo);
            modifyPickupRequestVo.setShipment(shipmentVo);
            context.put(Attributes.DHL_MODIFY_SCHEDULE_REQUEST, modifyPickupRequestVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
        }
        return true;
    }
}
