package com.gms.xms.workflow.task2.tnt.international.booking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.shipment.TntShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareTntShipmentRequestTask
 * <p>
 * Author TANDT
 */
public class PrepareTntIntlShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareTntIntlShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
            ShipmentRequestVo shipmentRequestPage1 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            Integer selCollection = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
            TntShipmentRequestVo tntShipmentRequestVo = new TntShipmentRequestVo();
            ShipmentInfoVo shipmentInfo = new ShipmentInfoVo();
            shipmentInfo = shipmentRequestPage1.getShipmentInfo();
            ICountryService countryService = new CountryServiceImp();
            CountryVo senderCountryVo = countryService.getCountryByCountryId(shipmentInfo.getSenderAddress().getCountry());
            shipmentInfo.getSenderAddress().setCountryDetail(senderCountryVo);
            CountryVo receiverCountryVo = countryService.getCountryByCountryId(shipmentInfo.getReceiverAddress().getCountry());
            shipmentInfo.getReceiverAddress().setCountryDetail(receiverCountryVo);
            shipmentInfo.setContentDescription(shipmentRequestPage2.getContentDetail().getDescription());
            shipmentInfo.setSpecialDelivery(shipmentRequestPage2.getShipmentInfo().getSpecialDelivery());
            tntShipmentRequestVo.setShipmentInfo(shipmentInfo);
            tntShipmentRequestVo.setShipmentReference(shipmentRequestPage2.getShipmentReference());
            ScheduleCollectionVo scheduleCollection = new ScheduleCollectionVo();
            if (shipmentRequestPage2.getScheduleCollection() != null) {
                scheduleCollection = shipmentRequestPage2.getScheduleCollection();
            } else {
                scheduleCollection.setPickupTime("0900");
                scheduleCollection.setPickupTimeNoLater("1600");
            }
            if (selCollection != 1) {
                scheduleCollection.setPickupAddress(shipmentInfo.getSenderAddress());
                scheduleCollection.setPickupDate(shipmentInfo.getShipmentDate());
                scheduleCollection.setDescription("Front Desk");
            } else {
                AddressVo pickupAddress = scheduleCollection.getSaddress();
                pickupAddress.setCountryDetail(senderCountryVo);
                scheduleCollection.setPickupAddress(pickupAddress);
            }
            tntShipmentRequestVo.setScheduleCollection(scheduleCollection);
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(shipmentInfo.getPackageId());
            tntShipmentRequestVo.setPackageInfo(packageVo);
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentRequestPage1.getShipmentInfo().getShipmentTypeId());
            if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("express")) {
                if (shipmentInfo.getContentType().equalsIgnoreCase("WPX")) {
                    tntShipmentRequestVo.setServiceCode("15N");
                } else {
                    tntShipmentRequestVo.setServiceCode("15D");
                }
            } else if (shipmentTypeVo.getServiceCode().equalsIgnoreCase("economy_express")) {
                tntShipmentRequestVo.getShipmentInfo().setContentType("WPX");
                tntShipmentRequestVo.setServiceCode("48N");
            }
            context.put(Attributes.TNT_SHIPMENT_REQUEST_VO, tntShipmentRequestVo);

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
