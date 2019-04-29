package com.gms.xms.workflow.task.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.TntDomesticRateSheetVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTntDomesticBaseChargeTask
 * <p>
 * Author HungNT Date Aug 11, 2015
 */
public class GetTntDomesticBaseChargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntDomesticBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
            Double quoteWeight = Double.parseDouble(context.get(Attributes.SHIPMENT_TOTAL_WEIGHT));
            Double rowName = Math.ceil(quoteWeight);
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }

            String columnName = context.get(Attributes.RECEIVER_ZONE_CODE);
            String charRowName = context.get(Attributes.SENDER_ZONE_CODE);
            TntDomesticRateSheetVo domesticRateSheetVo = AppUtils.getObjectFromContext(context, Attributes.TNT_RATE_SHEET, TntDomesticRateSheetVo.class);
            Long sheetId = domesticRateSheetVo.getCarrierDocumentRate();

            // Get max weight and max value
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            rateSheetDetailFilter.setSheetId(sheetId);
            rateSheetDetailFilter.setColumnName(columnName);
            rateSheetDetailFilter.setCharRowName(charRowName);

            Double baseCharge = 0D;
            Double minCharge = 0D;
            Double perKg = 0D;
            // Get TNT Weight Break
            rateSheetDetailFilter.setRowName(rowName);
            WebshipRateSheetDetailVo tntWeightBreakDetail = rateSheetDetailDao.selectTntWeightBreak(rateSheetDetailFilter);
            if (tntWeightBreakDetail != null) {
                if (tntWeightBreakDetail.getRateSheetRow().getCharRowName().equalsIgnoreCase("D")) {
                    // Get TNT Base charge
                    WebshipRateSheetDetailVo tntRateSheetDetail = rateSheetDetailDao.selectBaseChargeByCharRowAndCol(rateSheetDetailFilter);
                    if (tntRateSheetDetail != null) {
                        baseCharge = tntRateSheetDetail.getBaseCharge();
                        minCharge = tntRateSheetDetail.getMinCharge();
                        perKg = tntRateSheetDetail.getPerKg();
                    }
                    Double breakAmount = tntWeightBreakDetail.getValue();
                    Double discBaseCharge = baseCharge * (breakAmount / 100);
                    discBaseCharge = MathUtils.round(discBaseCharge, 2);
                    baseCharge = baseCharge - discBaseCharge;

                    Double discMinCharge = minCharge * (breakAmount / 100);
                    discMinCharge = MathUtils.round(discMinCharge, 2);
                    minCharge = minCharge - discMinCharge;

                    Double discPerKg = perKg * (breakAmount / 100);
                    discPerKg = MathUtils.round(discPerKg, 2);
                    perKg = perKg - discPerKg;
                } else {
                    baseCharge = tntWeightBreakDetail.getValue();
                }
            } else {
                // Get TNT Base charge
                WebshipRateSheetDetailVo tntRateSheetDetail = rateSheetDetailDao.selectBaseChargeByCharRowAndCol(rateSheetDetailFilter);
                if (tntRateSheetDetail != null) {
                    baseCharge = tntRateSheetDetail.getBaseCharge();
                    minCharge = tntRateSheetDetail.getMinCharge();
                    perKg = tntRateSheetDetail.getPerKg();
                }
            }
            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(webshipLoginInfo.getCustomerCode()).substring(0, 3));
            Double franchiseMarkup = 0D;
            try {
                if (franchiseVo.getTntMarkupRate() != null) {
                    franchiseMarkup = franchiseVo.getTntMarkupRate();
                }
            } catch (Exception e) {
            }

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            Long userId = webshipLoginInfo.getCustomerCode();
            customerBaseRateFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            customerBaseRateFilter.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            customerBaseRateFilter.setWeight(rowName);
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);

            // Get minimum base charge cost
            Double miniumBaseChargePercent = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(userId);
            miniumBaseChargePercent = customerVo.getMinimunBaseCharge();

            // Get customer base rate
            Double baseRate = 0D;
            if (customerBaseRateVo.getZoneCheck()) {
                customerBaseRateFilter.setZone(columnName);
                customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                baseRate = customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
            } else {
                baseRate = customerBaseRateVo.getRate();
            }
            Integer rateType = customerBaseRateVo.getRateType();

            Double cBaseCharge = 0D;
            Double ncBaseCharge = 0D;
            Double cPerKg = 0D;
            Double ncPerKg = 0D;
            Double cMinCharge = 0D;
            Double ncMinCharge = 0D;

            cBaseCharge = MathUtils.round((baseCharge + (baseCharge * franchiseMarkup) / 100), 2);
            ncBaseCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cBaseCharge, ncBaseCharge);

            cPerKg = MathUtils.round((perKg + (perKg * franchiseMarkup) / 100), 2);
            ncPerKg = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cPerKg, ncPerKg);

            cMinCharge = MathUtils.round((minCharge + (minCharge * franchiseMarkup) / 100), 2);
            ncMinCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cMinCharge, ncMinCharge);

            cRate = cBaseCharge + (rowName * cPerKg);
            if (cRate < cMinCharge) {
                cRate = cMinCharge;
            }
            cRate = MathUtils.round(cRate, 2);

            ncRate = ncBaseCharge + (rowName * ncPerKg);
            if (ncRate < ncMinCharge) {
                ncRate = ncMinCharge;
            }
            ncRate = MathUtils.round(ncRate, 2);

            context.put(Attributes.CARRIER_COST, String.valueOf(cRate));
            context.put(Attributes.CUSTOMER_COST, String.valueOf(ncRate));
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }
}
