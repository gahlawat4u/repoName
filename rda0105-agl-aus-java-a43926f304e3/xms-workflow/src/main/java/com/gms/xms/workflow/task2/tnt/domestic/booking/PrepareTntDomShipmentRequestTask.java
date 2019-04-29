package com.gms.xms.workflow.task2.tnt.domestic.booking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.shipment.TntDomShipmentRequestVo;
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
public class PrepareTntDomShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareTntDomShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
            ShipmentRequestVo shipmentRequestPage1 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            Integer selCollection = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
            TntDomShipmentRequestVo tntDomShipmentRequestVo = new TntDomShipmentRequestVo();
            ShipmentInfoVo shipmentInfo = new ShipmentInfoVo();
            shipmentInfo = shipmentRequestPage1.getShipmentInfo();
            ICountryService countryService = new CountryServiceImp();
            CountryVo senderCountryVo = countryService.getCountryByCountryId(shipmentInfo.getSenderAddress().getCountry());
            shipmentInfo.getSenderAddress().setCountryDetail(senderCountryVo);
            CountryVo receiverCountryVo = countryService.getCountryByCountryId(shipmentInfo.getReceiverAddress().getCountry());
            shipmentInfo.getReceiverAddress().setCountryDetail(receiverCountryVo);
            shipmentInfo.setContentDescription(shipmentRequestPage2.getContentDetail().getDescription());
            shipmentInfo.setSpecialDelivery(shipmentRequestPage2.getShipmentInfo().getSpecialDelivery());
            tntDomShipmentRequestVo.setShipmentInfo(shipmentInfo);
            tntDomShipmentRequestVo.setShipmentReference(shipmentRequestPage2.getShipmentReference());
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
            }
            tntDomShipmentRequestVo.setScheduleCollection(scheduleCollection);
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(shipmentInfo.getPackageId());
            tntDomShipmentRequestVo.setPackageInfo(packageVo);
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentRequestPage1.getShipmentInfo().getShipmentTypeId());
            String serviceCode = shipmentTypeVo.getServiceCode();
            String[] serviceCodeArr = serviceCode.split(",");
            serviceCode = serviceCodeArr[0];
            tntDomShipmentRequestVo.setServiceCode(serviceCode);
            context.put(Attributes.TNT_DOM_SHIPMENT_REQUEST_VO, tntDomShipmentRequestVo);

        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
