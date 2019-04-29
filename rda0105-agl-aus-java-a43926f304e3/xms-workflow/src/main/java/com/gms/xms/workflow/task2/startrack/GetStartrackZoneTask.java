package com.gms.xms.workflow.task2.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.startrack.StartrackZoneDao;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.startrack.StartrackZoneVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Jun 13, 2016 2:51:08 PM
 * <p>
 * Author huynd
 */
public class GetStartrackZoneTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetStartrackZoneTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            String startrackZone = "";

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
            shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            if ("road express".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName()) || "premium air freight".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName())) {
                List<String> serviceAreaCodeOrigin = new ArrayList<String>();
                List<String> serviceAreaCodeDestination = new ArrayList<String>();
                List<String> serviceAreaCode = new ArrayList<String>();

                AddressVo senderAddress = shipmentInfoVo.getSenderAddress();
                String senderPostCode = (senderAddress.getPostalCode() == null) ? "" : senderAddress.getPostalCode();
                String senderCity = (senderAddress.getCity() == null) ? "" : senderAddress.getCity();
                String senderState = (senderAddress.getState() == null) ? "" : senderAddress.getState();

                AddressVo receiverAddress = shipmentInfoVo.getReceiverAddress();
                String receiverPostCode = (receiverAddress.getPostalCode() == null) ? "" : receiverAddress.getPostalCode();
                String receiverCity = (receiverAddress.getCity() == null) ? "" : receiverAddress.getCity();
                String receiverState = (receiverAddress.getState() == null) ? "" : receiverAddress.getState();

                StartrackZoneDao zoneDao = new StartrackZoneDao();
                StartrackZoneVo filter = new StartrackZoneVo();
                StartrackZoneVo startrackZoneVo = new StartrackZoneVo();
                // 1.
                filter.setPostCode(senderPostCode);
                filter.setCityName(senderCity);
                filter.setOriginLocation(senderState);
                startrackZoneVo = zoneDao.selectStartrackZone(filter);
                if (startrackZoneVo != null) {
                    serviceAreaCodeOrigin.add(startrackZoneVo.getDirectZone().concat(".D"));
                    serviceAreaCodeOrigin.add(startrackZoneVo.getOfwdZone().concat(".O"));
                }
                // 2.
                filter.setPostCode(receiverPostCode);
                filter.setCityName(receiverCity);
                startrackZoneVo = zoneDao.selectStartrackZone(filter);
                if (startrackZoneVo != null) {
                    serviceAreaCodeDestination.add(startrackZoneVo.getDirectZone().concat(".D"));
                    serviceAreaCodeDestination.add(startrackZoneVo.getOfwdZone().concat(".O"));
                }
                // 3.
                filter.setPostCode(senderPostCode);
                filter.setCityName(senderCity);
                filter.setOriginLocation(receiverState);
                startrackZoneVo = zoneDao.selectStartrackZone(filter);
                if (startrackZoneVo != null && !serviceAreaCodeOrigin.contains(startrackZoneVo.getDirectZone().concat(".D"))) {
                    serviceAreaCodeOrigin.add(startrackZoneVo.getDirectZone().concat(".D"));
                }
                if (startrackZoneVo != null && !serviceAreaCodeOrigin.contains(startrackZoneVo.getOfwdZone().concat(".O"))) {
                    serviceAreaCodeOrigin.add(startrackZoneVo.getOfwdZone().concat(".O"));
                }
                // 4.
                filter.setPostCode(receiverPostCode);
                filter.setCityName(receiverCity);
                startrackZoneVo = zoneDao.selectStartrackZone(filter);
                if (startrackZoneVo != null && !serviceAreaCodeDestination.contains(startrackZoneVo.getDirectZone().concat(".D"))) {
                    serviceAreaCodeDestination.add(startrackZoneVo.getDirectZone().concat(".D"));
                }
                if (startrackZoneVo != null && !serviceAreaCodeDestination.contains(startrackZoneVo.getOfwdZone().concat(".O"))) {
                    serviceAreaCodeDestination.add(startrackZoneVo.getOfwdZone().concat(".O"));
                }
                for (String origin : serviceAreaCodeOrigin) {
                    for (String destination : serviceAreaCodeDestination) {
                        serviceAreaCode.add(origin + "###" + destination);
                    }
                }
                context.put(Attributes.SERVICE_AREA_CODE, serviceAreaCode);
            } else {
                startrackZone = "1";
            }
            shipmentInfoVo.setZone(startrackZone);
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
            context.put(Attributes.SHIPMENT_TYPE_RESULT, shipmentTypeVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
