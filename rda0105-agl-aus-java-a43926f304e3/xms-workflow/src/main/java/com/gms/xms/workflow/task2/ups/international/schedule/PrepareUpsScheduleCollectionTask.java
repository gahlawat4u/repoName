package com.gms.xms.workflow.task2.ups.international.schedule;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.dhlcountry.DhlCountryServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.persistence.service.piece.IPieceService;
import com.gms.xms.persistence.service.piece.PieceServiceImp;
import com.gms.xms.txndb.vo.schedulecollection.BookPickupRequestVo;
import com.gms.xms.persistence.service.dhlcountry.IDhlCountryService;



import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from PrepareTntDomScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class PrepareUpsScheduleCollectionTask implements Task2 {

	 private static final Log log = LogFactory.getLog(PrepareUpsScheduleCollectionTask.class);

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
		    senderAddressVo.setPostalCode(senderAddressVo.getPostalCode().split(" ")[0]);
		    AddressVo receiverAddressVo = addressService.getAddressById(shipmentVo.getReceiverAddressId());
		    receiverAddressVo.setPostalCode(receiverAddressVo.getPostalCode().split(" ")[0]);
		    // Get Location Code.
		    ILocationCodeService locationCodeService = new LocationCodeServiceImp();
		    LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(scheduleCollectionVo.getLocationCodeId());
		    // Get DHL Country.
		    IDhlCountryService dhlCountryService = new DhlCountryServiceImp();
		    DhlCountryVo dhlCountryVo = dhlCountryService.selectDhlCountryByDhlCountryId(senderAddressVo.getCountry());
		    IPieceService pieceService = new PieceServiceImp();
		    List<PieceVo> pieceVos = pieceService.getPiecesByShipmentId(shipmentId);
		    PieceVo pickupPieceVo = pieceVos.get(0);

		    // Prepare Ups bookPickupRequestVo.
		    BookPickupRequestVo bookPickupRequestVo = new BookPickupRequestVo();
		    bookPickupRequestVo.setShipment(shipmentVo);
		    scheduleCollectionVo.setShipmentId(shipmentId);
		    bookPickupRequestVo.setScheduleCollection(scheduleCollectionVo);
		    bookPickupRequestVo.setPickupAddress(pickupAddressVo);
		    bookPickupRequestVo.setSenderAddress(senderAddressVo);
		    bookPickupRequestVo.setLocationCode(locationCodeVo);
		    bookPickupRequestVo.setDhlCountry(dhlCountryVo);
		    bookPickupRequestVo.setPieceVo(pickupPieceVo);
		    bookPickupRequestVo.setPieceVos(pieceVos);
		    bookPickupRequestVo.setReceiverAddress(receiverAddressVo);
		    context.put(Attributes.UPS_BOOK_SCHEDULE_REQUEST, bookPickupRequestVo);
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
		    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
		}
		return true;
	    }
    

    private ShipmentInfoVo transferShipmentVo2ShipmentInfoVo(ShipmentVo shipmentVo) {
        ShipmentInfoVo shipmentInfoVo = new ShipmentInfoVo();
        shipmentInfoVo.setShipmentId(shipmentVo.getShipmentId());
        shipmentInfoVo.setCustomerCode(shipmentVo.getCustomerCode());
        shipmentInfoVo.setWebshipId(shipmentVo.getWebshipId());
        shipmentInfoVo.setAirbillNumber(shipmentVo.getAirbillNumber());
        shipmentInfoVo.setShipmentDate(shipmentVo.getShipmentDate());
        shipmentInfoVo.setShipmentTypeId(shipmentVo.getShipmentTypeId());
        shipmentInfoVo.setPackageId(shipmentVo.getPackageId());
        shipmentInfoVo.setWeightUnit(shipmentVo.getWeightUnit());
        shipmentInfoVo.setContents(shipmentVo.getContents());
        shipmentInfoVo.setDimensionUnit(shipmentVo.getDimensionUnit());
        shipmentInfoVo.setNoOfPieces(shipmentVo.getNoOfPieces());
        shipmentInfoVo.setCurrencyCode(shipmentVo.getCurrencyCode());
        shipmentInfoVo.setContentDescription(shipmentVo.getContentDescription());
        shipmentInfoVo.setTotalCustomValue(shipmentVo.getTotalCustomValue());
        shipmentInfoVo.setCommercialInvoiceId(shipmentVo.getCommercialInvoiceId());
        shipmentInfoVo.setCollectionTypeId(shipmentVo.getCollectionTypeId());
        shipmentInfoVo.setDutiesType(shipmentVo.getDutiesType());
        shipmentInfoVo.setReference(shipmentVo.getReference());
        shipmentInfoVo.setReference2(shipmentVo.getReference2());
        shipmentInfoVo.setPackingList(shipmentVo.getPackingList());
        shipmentInfoVo.setBoundStatus(shipmentVo.getBoundStatus());
        shipmentInfoVo.setSalesRepId(shipmentVo.getSalesRepId());
        shipmentInfoVo.setCarrierPayment(shipmentVo.getCarrierPayment());
        shipmentInfoVo.setAwbProductContentCode(shipmentVo.getAwbProductContentCode());
        shipmentInfoVo.setZone(shipmentVo.getZone());
        shipmentInfoVo.setCourierMessage(shipmentVo.getCourierMessage());
        shipmentInfoVo.setDhlRoutingCode(shipmentVo.getDhlRoutingCode());
        return shipmentInfoVo;
    }

}
