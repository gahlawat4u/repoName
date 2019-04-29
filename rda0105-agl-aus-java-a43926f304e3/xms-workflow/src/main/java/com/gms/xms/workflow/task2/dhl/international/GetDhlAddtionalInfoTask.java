package com.gms.xms.workflow.task2.dhl.international;

import com.gms.delivery.dhl.service.DhlCapabilityServiceClient;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetDhlAddtionalInfoTask
 * <p>
 * Author Toantq Date Apr 23, 2015
 */
public class GetDhlAddtionalInfoTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlAddtionalInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            DhlCapabilityServiceClient client = new DhlCapabilityServiceClient();
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            String shipmentTypeName = shipmentTypeVo.getEdiDescription();
            String selectedService = shipmentTypeVo.getShipmentTypeName();
            String xmlRequest = client.parseCapabilityXMLRequest(shipmentInfoVo);

            DhlCapabilityVo capabilityVo = client.getCapability(shipmentTypeName, selectedService, xmlRequest);
            context.put(Attributes.DHL_CAPABILITY_RESULT, capabilityVo);
            log.info("capabilityVo:" + capabilityVo);
        } catch (Exception e) {
            log.error("Fail to perform DHL Capability Request", e);
        }
        return true;
    }

}
