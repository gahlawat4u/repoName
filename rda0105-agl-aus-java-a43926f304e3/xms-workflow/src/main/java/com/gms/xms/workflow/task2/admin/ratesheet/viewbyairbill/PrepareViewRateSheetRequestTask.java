package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.ratesheet.ViewRateSheetDao;
import com.gms.xms.persistence.utils.ShipmentHelper;
import com.gms.xms.txndb.vo.CustomerBaseRateDetailVo;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetBaseRateVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;
import com.gms.xms.txndb.vo.ratesheet.ViewRateSheetShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 16, 2016 3:45:33 PM
 * <p>
 * Author dattrinh
 */
public class PrepareViewRateSheetRequestTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get shipment id.
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Get airbill number.
            // String airbillNumber = context.getString(Attributes.AIRBILL_NUMBER);
            // Create filter to get shipment & shipment billing info.
            ViewRateSheetShipmentVo filter = new ViewRateSheetShipmentVo();
            filter.setShipmentId(shipmentId);
            // Get shipment info for rate sheet detecting.
            ViewRateSheetDao viewRateSheetDao = new ViewRateSheetDao();
            ViewRateSheetShipmentVo shipmentVo = viewRateSheetDao.getShipmentInfo(filter);
            if (shipmentVo == null) {
                throw new Exception("This shipment is invalid: it doesn't exisits or missing the details.");
            }
            // Detect shipment type id.
            ShipmentHelper helper = new ShipmentHelper();
            ShipmentTypeVo shipmentTypeVo = helper.getShipmentType(shipmentVo.getDescription(), shipmentVo.getDisplayDescription(), shipmentVo.getCarrier().intValue());
            Integer shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
            int defaultRateType = 1;
            if (shipmentTypeVo.getAllowNonCarrier()) {
                defaultRateType = 0;
            }

            // Detect content.
            Integer content = helper.getContentType4ViewRateSheet(shipmentVo.getDisplayDescription());
            if (!shipmentTypeVo.getDocument() && !shipmentTypeVo.getDocumentInbound() && !shipmentTypeVo.getPackage() && !shipmentTypeVo.getPackageInbound() && !shipmentTypeVo.getPak() && !shipmentTypeVo.getPakInbound()) {
                content = -1;
            }
            // Detect bound.
            Integer bound = helper.getBound(shipmentVo.getDisplayDescription());
            // Create new view rate sheet request.
            ViewRateSheetRequestVo rateSheetRequestVo = new ViewRateSheetRequestVo();
            rateSheetRequestVo.setShipmentTypeId(shipmentTypeId);
            rateSheetRequestVo.setContent(content);
            rateSheetRequestVo.setBound(bound);
            rateSheetRequestVo.setColumnName(shipmentVo.getServiceAreaCodeOrigin());
            rateSheetRequestVo.setCustomerCode(shipmentVo.getCustomerCode());
            rateSheetRequestVo.setMinimumBaseCharge(shipmentVo.getMinimunBaseCharge());
            Integer type = null;
            if (shipmentVo.getCustomerCode().endsWith("00001")) {
                type = 1; // This is franchise.
            } else {
                type = 0; // This is customer.
            }
            rateSheetRequestVo.setType(type);
            // Get customer base rate and zone rates.
            detectCustomerBaseRates(rateSheetRequestVo, defaultRateType);
            // Update into the context.
            context.put(Attributes.VIEW_RATE_SHEET_REQUEST_VO, rateSheetRequestVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private void detectCustomerBaseRates(ViewRateSheetRequestVo rateSheetRequestVo, int defaultRateType) throws Exception {
        // Get customer base rates.
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao();
        CustomerBaseRateFilter filter = new CustomerBaseRateFilter();
        filter.setCustomerCode(Long.valueOf(rateSheetRequestVo.getCustomerCode()));
        filter.setShipmentTypeId(rateSheetRequestVo.getShipmentTypeId());
        filter.setContent(rateSheetRequestVo.getContent() == -1 ? 0 : rateSheetRequestVo.getContent());
        filter.setBound(rateSheetRequestVo.getBound());
        List<CustomerBaseRateVo> baseRateVos = baseRateDao.getBaseRateDetailByFilter(filter);
        // Convert to view rate sheet base rates.
        List<ViewRateSheetBaseRateVo> viewBaseRateVos = new ArrayList<ViewRateSheetBaseRateVo>();
        ViewRateSheetBaseRateVo viewBaseRate;
        if (baseRateVos == null || baseRateVos.size() == 0) {
            viewBaseRate = new ViewRateSheetBaseRateVo();
            viewBaseRate.setRate(0d);
            viewBaseRate.setRateType(defaultRateType);
            viewBaseRate.setWeight(0d);
            viewBaseRate.setZoneRates(null);
            viewBaseRateVos.add(viewBaseRate);
        } else {
            for (CustomerBaseRateVo baseRate : baseRateVos) {
                viewBaseRate = new ViewRateSheetBaseRateVo();
                viewBaseRate.setRate(baseRate.getRate());
                viewBaseRate.setRateType(baseRate.getRateType());
                viewBaseRate.setWeight(baseRate.getWeight());
                if (baseRate.getZoneCheck()) {
                    viewBaseRate.setZoneRates(convertBaseRateDetailList2Map(baseRate.getCustomerBaseRateDetails()));
                } else {
                    viewBaseRate.setZoneRates(null);
                }
                viewBaseRateVos.add(viewBaseRate);
            }
        }
        // Set view rate sheet base rates.
        rateSheetRequestVo.setBaseRates(viewBaseRateVos);
    }

    private Map<String, Double> convertBaseRateDetailList2Map(List<CustomerBaseRateDetailVo> baseRateDetailVos) {
        Map<String, Double> result = new HashMap<String, Double>();
        if (baseRateDetailVos != null && baseRateDetailVos.size() != 0) {
            for (CustomerBaseRateDetailVo rateSheetDetail : baseRateDetailVos) {
                result.put(rateSheetDetail.getZone(), rateSheetDetail.getRate());
            }
        }
        return result;
    }
}
