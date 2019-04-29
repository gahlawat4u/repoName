package com.gms.xms.workflow.task2.ups.international.booking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core.Task;

public class GetUpsAddtionalInfoTask implements Task {

	private static final Log log =LogFactory.getLog(GetUpsAddtionalInfoTask.class);
	@Override
	public boolean execute(ContextBase context) throws Exception {
		context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
		
		
		
		try{
			ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
			    IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
			    ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
			    String shipmentTypeName = shipmentTypeVo.getEdiDescription();
			    String selectedService = shipmentTypeVo.getShipmentTypeName();
		
		}catch (Exception e) {
			 log.error("Fail to perform Ups Capability Request", e);
		}
		
		return true;
	}


}
