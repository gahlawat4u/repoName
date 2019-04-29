package com.gms.xms.workflow.task2.startrack.booking;

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
import com.gms.xms.txndb.vo.startrack.StartrackShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 15, 2016 2:34:25 PM
 * <p>
 * Author huynd
 */
public class PrepareStartrackShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareStartrackShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            ShipmentRequestVo shipmentRequestPage2 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE2);
            ShipmentRequestVo shipmentRequestPage1 = context.get(Attributes.SHIPMENT_REQUEST_INFO_PAGE1);
            Integer selCollection = context.getInt(Attributes.SCHEDULE_LIST_SELECT);
            StartrackShipmentRequestVo startrackShipmentRequestVo = new StartrackShipmentRequestVo();
            ShipmentInfoVo shipmentInfo = new ShipmentInfoVo();
            shipmentInfo = shipmentRequestPage1.getShipmentInfo();
            ICountryService countryService = new CountryServiceImp();
            CountryVo senderCountryVo = countryService.getCountryByCountryId(shipmentInfo.getSenderAddress().getCountry());
            shipmentInfo.getSenderAddress().setCountryDetail(senderCountryVo);
            CountryVo receiverCountryVo = countryService.getCountryByCountryId(shipmentInfo.getReceiverAddress().getCountry());
            shipmentInfo.getReceiverAddress().setCountryDetail(receiverCountryVo);
            shipmentInfo.setContentDescription(shipmentRequestPage2.getContentDetail().getDescription());
            shipmentInfo.setSpecialDelivery(shipmentRequestPage2.getShipmentInfo().getSpecialDelivery());
            shipmentInfo.setCustomerCode(shipmentRequestPage1.getWebshipLogin().getCustomerCode());
            startrackShipmentRequestVo.setShipmentInfo(shipmentInfo);
            startrackShipmentRequestVo.setShipmentReference(shipmentRequestPage2.getShipmentReference());
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
            startrackShipmentRequestVo.setScheduleCollection(scheduleCollection);
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(shipmentInfo.getPackageId());
            startrackShipmentRequestVo.setPackageInfo(packageVo);
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentRequestPage1.getShipmentInfo().getShipmentTypeId());
            String serviceCode = shipmentTypeVo.getServiceCode();
            String[] serviceCodeArr = serviceCode.split(",");
            serviceCode = serviceCodeArr[0];
            startrackShipmentRequestVo.setServiceCode(serviceCode);
            context.put(Attributes.STARTRACK_SHIPMENT_REQUEST_VO, startrackShipmentRequestVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
