package com.gms.xms.workflow.task2.invoice.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Aug 12, 2016 10:48:57 AM
 * <p>
 * Author huynd
 */
public class GetStartrackZoneBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetStartrackZoneBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);
            String startrackZone = "";

            if ("road express".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName()) || "premium air freight".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName())) {
                List<String> serviceAreaCode = new ArrayList<String>();
                serviceAreaCode.add(shipmentBilling.getZone());
                context.put(Attributes.SERVICE_AREA_CODE, serviceAreaCode);
            } else {
                startrackZone = "1";
            }
            context.put(Attributes.STARTRACK_ZONE, startrackZone);
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
