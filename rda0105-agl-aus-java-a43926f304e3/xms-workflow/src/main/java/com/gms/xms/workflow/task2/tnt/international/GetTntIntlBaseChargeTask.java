package com.gms.xms.workflow.task2.tnt.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.filter.account.franchises.FranchiseServiceMarkupFilter;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.service.CustomerBaseRateService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntIntlRateSheetVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from GetDhlIntlBaseChargeTask
 * <p>
 * Author TANDT
 */
public class GetTntIntlBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntIntlBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            TntIntlRateSheetVo tntRateSheetsVo = context.get(Attributes.TNT_RATE_SHEET);
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);

            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            WebshipRateSheetDetailVo rateSheetDetailVo = new WebshipRateSheetDetailVo();
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            // Get franchise service markup
            String franchiseCode = String.valueOf(webshipLoginInfo.getCustomerCode());
            FranchiseServiceMarkupFilter filter = new FranchiseServiceMarkupFilter();
            filter.setFranchiseCode(franchiseCode.substring(0, 3).concat("00001"));
            filter.setServiceId(shipmentTypeVo.getServiceId());
            filter.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId());
            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseServiceMarkupVo serviceMarkup = franchiseDao.getFranchiseServiceMarkup(filter);
            Double franchiseServiceMarkup = 0D;
            if (serviceMarkup != null) {
                franchiseServiceMarkup = serviceMarkup.getMarkupRate();
            }
            String columnName = shipmentInfoVo.getZone();
            rateSheetDetailFilter.setColumnName(columnName);

            Double rowName = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
            rateSheetDetailFilter.setRowName(rowName);

            Double ncRate = 0D;
            Double cRate = 0D;

            Double minDocWeightNonCarrier = 0D;
            Double minDocWeightCarrier = 0D;
            Double maxDocValueNonCarrier = 0D;

            Double maxDocWeightCarrier = 0D;
            Double maxDocValueCarrier = 0D;
            Double maxOverDocWeight = 0D;

            Double pakMaxOverDocWeight = 0D;
            Integer contents = 0;

            Boolean isDocMustBeCalByPackage = false;

            Double maxPackageWeight = 0D;
            Double maxPackageValue = 0D;

            Double overWeightAmount = 0D;
            Double overWeightValueAmount = 0D;

            if (shipmentInfoVo.getContentType().equalsIgnoreCase("DOX")) {
                contents = 0;
            } else {
                contents = 1;
            }
            shipmentInfoVo.setContents(contents);

            if (contents == 0) {

                rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierDocumentRate());
                rateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                if (rateSheetDetailVo != null) {
                    minDocWeightCarrier = rateSheetDetailVo.getMinWeight();
                }

                if (rowName < minDocWeightCarrier || rowName <= minDocWeightNonCarrier) {
                    ncRate = minDocWeightNonCarrier;
                    cRate = minDocWeightCarrier;
                } else {
                    // Max document
                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getNonCarrierDocumentRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxDocValueNonCarrier = rateSheetDetailVo.getMaxValue();
                    }

                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierDocumentRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxDocWeightCarrier = rateSheetDetailVo.getMaxWeight();
                        maxDocValueCarrier = rateSheetDetailVo.getMaxValue();
                    }
                    // Max document overweight
                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setMaxRowName(maxDocWeightCarrier);
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxOverDocWeight = rateSheetDetailVo.getMaxWeight();
                    }

                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPakRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        pakMaxOverDocWeight = rateSheetDetailVo.getMaxWeight();
                    }

                    if (rowName >= maxDocWeightCarrier && (rowName < maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        ncRate = maxDocValueNonCarrier;
                        cRate = maxDocValueCarrier;
                    } else if (rowName >= maxDocWeightCarrier && (rowName >= maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        contents = 1;
                        isDocMustBeCalByPackage = true;
                    } else {
                        rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getNonCarrierDocumentRate());
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            ncRate = rateSheetDetailVo.getValue();
                        }

                        rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierDocumentRate());
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            cRate = rateSheetDetailVo.getValue();
                        }
                    }
                }
            }
            if (contents == 1 || isDocMustBeCalByPackage) {
                IFranchiseService franchiseService = new FranchiseServiceImp();
                FranchiseVo franchiseInfo = new FranchiseVo();
                franchiseInfo = franchiseService.getFranchiseInfoByCode(franchiseCode.substring(0, 3));
                Double franchiseMarkup = franchiseInfo.getTntInternationalMarkupRate();
                franchiseMarkup += franchiseServiceMarkup;

                rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPackageRate());
                rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                if (rateSheetDetailVo != null) {
                    maxPackageWeight = rateSheetDetailVo.getMaxWeight();
                    maxPackageValue = rateSheetDetailVo.getMaxValue();
                }

                if (rowName > maxPackageWeight) {
                    overWeightAmount = rowName - maxPackageWeight;
                    overWeightAmount = Math.ceil(overWeightAmount);
                    // Get per weight rate
                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPackagePerWeightRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    overWeightValueAmount = rateSheetDetailVo.getValue() * overWeightAmount;
                    cRate = maxPackageValue + overWeightValueAmount;
                    cRate = MathUtils.round(cRate, 2);
                } else {
                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setType("upper");
                    rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    cRate = rateSheetDetailVo.getValue();
                    cRate = MathUtils.round(cRate, 2);
                }

            }
            // Get customer base rate
            // CustomerBaseRateDao customerBaseRateDao = new
            // CustomerBaseRateDao();
            CustomerBaseRateService customerBaseRateService = new CustomerBaseRateService();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            Long userId = webshipLoginInfo.getCustomerCode();
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(userId).substring(0, 3));
            Double franchiseMarkup = 0D;
            try {
                if (franchiseVo.getTntInternationalMarkupRate() != null) {
                    franchiseMarkup = franchiseVo.getTntInternationalMarkupRate();
                }
            } catch (Exception e) {
            }
            franchiseMarkup += franchiseServiceMarkup;
            cRate = cRate + (cRate * franchiseMarkup) / 100;
            cRate = MathUtils.round(cRate, 2);

            customerBaseRateFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            customerBaseRateFilter.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            customerBaseRateFilter.setContent(contents);
            customerBaseRateFilter.setBound(shipmentInfoVo.getBound());
            customerBaseRateFilter.setWeight(rowName);
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateService.selectCustomerBaseRateByCustomerCodeAndShipmentTypeIdWithDefaultValue(customerBaseRateFilter);

            // Get minimum base charge cost
            Double miniumBaseChargePercent = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(userId);
            miniumBaseChargePercent = customerVo.getMinimunBaseCharge();

            // Get customer base rate
            Double baseRate = 0D;
            Integer rateType = 1;
            if (customerBaseRateVo != null) {
                if (customerBaseRateVo.getZoneCheck()) {
                    customerBaseRateFilter.setZone(columnName);
                    customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                    baseRate = customerBaseRateService.selectCustomerBaseRateByZone(customerBaseRateFilter);
                } else {
                    baseRate = customerBaseRateVo.getRate();
                }
                rateType = customerBaseRateVo.getRateType();
            }

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");
            context.put(Attributes.CARRIER_COST, cRate);
            context.put(Attributes.CUSTOMER_COST, ncRate);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }
}