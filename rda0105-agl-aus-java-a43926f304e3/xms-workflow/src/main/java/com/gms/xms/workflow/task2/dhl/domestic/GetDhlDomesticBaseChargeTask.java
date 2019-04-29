package com.gms.xms.workflow.task2.dhl.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from GetDhlQuoteChargeTask
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class GetDhlDomesticBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlDomesticBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            Double quoteWeight = context.get(Attributes.SHIPMENT_TOTAL_WEIGHT);
            Double rowName = Math.ceil(quoteWeight);
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }

            String columnName = shipmentInfoVo.getZone();
            Long sheetId = context.get(Attributes.DHL_DOMESTIC_RATE_SHEET_ID);
            Long perWeightSheetId = context.get(Attributes.DHL_DOMESTIC_PER_WEIGHT_RATE_SHEET_ID);

            // Get max weight and max value
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            rateSheetDetailFilter.setSheetId(sheetId);
            rateSheetDetailFilter.setColumnName(columnName);
            WebshipRateSheetDetailVo rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);

            Double maxWeight = rateSheetDetailVo.getMaxWeight();
            Double maxValue = rateSheetDetailVo.getMaxValue();

            // Get carrier cost
            if (rowName > maxWeight) {
                rateSheetDetailFilter.setRowName(rowName);
                rateSheetDetailFilter.setColumnName(columnName);
                rateSheetDetailFilter.setSheetId(perWeightSheetId);
                rateSheetDetailVo = rateSheetDetailDao.selectDhlDomesticBaseChargeRate(rateSheetDetailFilter);
                Double perWeightRate = rateSheetDetailVo.getValue();
                Double perWeight = Math.ceil(rowName - maxWeight);
                cRate = maxValue + (perWeight * perWeightRate);
            } else {
                rateSheetDetailFilter.setRowName(rowName);
                rateSheetDetailFilter.setColumnName(columnName);
                rateSheetDetailFilter.setSheetId(sheetId);
                rateSheetDetailVo = rateSheetDetailDao.selectDhlDomesticBaseChargeRate(rateSheetDetailFilter);
                cRate = rateSheetDetailVo.getValue();
            }

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);

            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(webshipLoginInfo.getCustomerCode()).substring(0, 3));
            Double franchiseMarkup = 0D;
            try {
                if (franchiseVo.getDhlDomMarkupRate() != null) {
                    franchiseMarkup = franchiseVo.getDhlDomMarkupRate();
                }
            } catch (Exception e) {
            }

            cRate = cRate + (cRate * franchiseMarkup) / 100;
            cRate = MathUtils.round(cRate, 2);

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
            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            cRate = rate.get("cRate");
            ncRate = rate.get("ncRate");

            context.put(Attributes.CARRIER_COST, cRate);
            context.put(Attributes.CUSTOMER_COST, ncRate);
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }
}
