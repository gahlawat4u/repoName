package com.gms.xms.workflow.task2.webship.voids.ups;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlCancelPickupRequestVo;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.core2.Task2;

public class PrepareDataForCancelShipmentTask implements Task2 {
	private static final Log log = LogFactory.getLog(PrepareDataForCancelShipmentTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
	try {
	    context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
	    Long shipmentId = context.get(Attributes.SHIPMENT_ID);
	    String reason = context.getString(Attributes.REASON);
	    DhlCancelPickupRequestVo cancelPickupRequestVo = new DhlCancelPickupRequestVo();
	    // Get Shipment Info.
	    IShipmentService shipmentService = new ShipmentServiceImp();
	    cancelPickupRequestVo.setShipment(shipmentService.selectById(shipmentId));
	    if (cancelPickupRequestVo.getShipment() == null) {
		log.error("The shipment does not existed.");
		throw new Exception("The shipment does not existed.");

	    }
	    // Get Schedule Collection Info.
	    if (checkScheduleCollectionById(shipmentId) != null) {
		cancelPickupRequestVo.setScheduleCollection(checkScheduleCollectionById(shipmentId));
		// Get Address.
		IAddressService addressService = new AddressServiceImp();
		ScheduleCollectionVo scheduleCollection = cancelPickupRequestVo.getScheduleCollection();
		scheduleCollection.setSaddress(addressService.getAddressById(scheduleCollection.getAddressId()));
		ICountryService countryService = new CountryServiceImp();
		CountryVo countryDetail = countryService.getCountryByCountryId(scheduleCollection.getSaddress().getCountry());
		scheduleCollection.getSaddress().setCountryDetail(countryDetail);
		// Get DhlCountry.
		IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
		Long dhlCountryId = scheduleCollection.getSaddress().getCountry();
		DhlCountryVo dhlCountryVo = dhlCountryService.selectDhlCountryByDhlCountryId(dhlCountryId);
		cancelPickupRequestVo.setDhlCountry(dhlCountryVo);
	    }
	    // Set reason.
	    cancelPickupRequestVo.setReason(reason);
	    context.put(Attributes.UPS_CANCEL_SHIPMENT_REQUEST, cancelPickupRequestVo);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	}
	return true;
    }

    private ScheduleCollectionVo checkScheduleCollectionById(Long shipmentId) throws Exception {
	IScheduleCollectionService service = new ScheduleCollectionServiceImp();
	List<ScheduleCollectionVo> scheduleCollectionVos = new ArrayList<ScheduleCollectionVo>();
	scheduleCollectionVos = service.selectListScheduleCollectionBySmId(shipmentId);
	if (scheduleCollectionVos != null) {
	    for (ScheduleCollectionVo schedule : scheduleCollectionVos) {
		if (schedule.getStatus() == 1) {
		    return schedule;
		}
	    }
	}
	return null;
    }

}
