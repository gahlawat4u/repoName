package com.gms.xms.workflow.task2.webship.cancelschedule.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.txndb.vo.CustomerCodeFilter;
import com.gms.xms.txndb.vo.CustomerFranchiseInfoVo;
import com.gms.xms.txndb.vo.LocationCodeVo;
import com.gms.xms.txndb.vo.webship.HistoryDetailScheduleInfoVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryScheduleServiceImp;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryScheduleService;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import com.gms.xms.workflow.task2.webship.cancelschedule.ReasonVoidEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thinhdd on 2/21/2017.
 */
public class PrepareCancelStartrackScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareCancelStartrackScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Long shipmentId = context.get(Attributes.SHIPMENT_ID);
        String reason = context.getString(Attributes.REASON);
        String reasonString = ReasonVoidEnum.get(reason).getName();

        IHistoryScheduleService historyScheduleService = new HistoryScheduleServiceImp();
        ShipmentScheduleCollectionVo shipmentScheduleCollectionVo = historyScheduleService.selectShipmentScheduleCollectionByShipmentId(shipmentId);
        ILocationCodeService locationCodeService = new LocationCodeServiceImp();
        LocationCodeVo locationCodeVo = locationCodeService.getLocationCodeById(shipmentScheduleCollectionVo.getLocationCodeId());
        IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentScheduleCollectionVo.getShipmentTypeId());
        IPackageService packageService = new PackageServiceImp();
        PackageVo packageVo = packageService.getPackagebyId(shipmentScheduleCollectionVo.getPackageId());
        IHistoryService historyService = new HistoryServiceImp();
        HistoryDetailScheduleInfoVo historyDetailScheduleInfoVo = historyService.selectHistoryDetailScheduleCollection(shipmentId);

        ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
        String customerCode = shipmentScheduleCollectionVo.getCustomerCode();
        String franchiseCode = customerCode.substring(0, 3) + "00001";
        CustomerCodeFilter customerCodeFilter = new CustomerCodeFilter(customerCode, franchiseCode);
        CustomerFranchiseInfoVo customerFranchiseInfoVo = customerAddressService.selectCustomerAndFranchiseInfo(customerCodeFilter);
        Map<String, Object> dataMapping = new HashMap<>();
        dataMapping.put("reasonCode", reasonString);
        dataMapping.put("shipmentScheduleCollectionVo", shipmentScheduleCollectionVo);
        dataMapping.put("locationCodeVo", locationCodeVo);
        dataMapping.put("shipmentTypeVo", shipmentTypeVo);
        dataMapping.put("packageVo", packageVo);
        dataMapping.put("historyDetailScheduleInfoVo", historyDetailScheduleInfoVo);
        dataMapping.put("customerFranchiseInfoVo", customerFranchiseInfoVo);
        context.put(Attributes.CANCEL_COLLECTION_DATA_MAPPING, dataMapping);
        context.put(Attributes.AIRBILL_NUMBER, historyDetailScheduleInfoVo.getAirbillNumber());
        context.put(Attributes.FRANCHISE_EMAIL, customerFranchiseInfoVo.getFranchiseEmail());
        return true;
    }

}
