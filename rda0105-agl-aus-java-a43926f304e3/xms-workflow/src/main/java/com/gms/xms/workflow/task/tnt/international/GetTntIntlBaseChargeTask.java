package com.gms.xms.workflow.task.tnt.international;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.TntIntlRateSheetVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from GetDhlIntlBaseChargeTask
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class GetTntIntlBaseChargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntIntlBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        try {
            TntIntlRateSheetVo tntRateSheetsVo = GsonUtils.fromGson(context.get(Attributes.TNT_RATE_SHEET), TntIntlRateSheetVo.class);
            ShipmentInfoVo shipmentInfoVo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
            WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);

            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            WebshipRateSheetDetailVo rateSheetDetailVo = new WebshipRateSheetDetailVo();
            String columnName = shipmentInfoVo.getZone();
            rateSheetDetailFilter.setColumnName(columnName);

            Double rowName = Double.parseDouble(context.get(Attributes.SHIPMENT_TOTAL_WEIGHT));
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
                    } else if (rowName >= maxDocWeightCarrier && (rowName > maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
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
                String franchiseCode = String.valueOf(webshipLoginInfo.getCustomerCode());
                franchiseInfo = franchiseService.getFranchiseInfoByCode(franchiseCode.substring(0, 3));
                Double franchiseMarkup = franchiseInfo.getTntInternationalMarkupRate();

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
                    cRate = cRate + ((cRate * franchiseMarkup) / 100);
                    cRate = MathUtils.round(cRate, 2);
                } else {
                    rateSheetDetailFilter.setSheetId(tntRateSheetsVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setType("upper");
                    rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    cRate = rateSheetDetailVo.getValue();
                    cRate = cRate + ((cRate * franchiseMarkup) / 100);
                    cRate = MathUtils.round(cRate, 2);
                }

            }
            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
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
            cRate = cRate + (cRate * franchiseMarkup) / 100;
            cRate = MathUtils.round(cRate, 2);

            customerBaseRateFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            customerBaseRateFilter.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
            customerBaseRateFilter.setContent(contents);
            customerBaseRateFilter.setBound(shipmentInfoVo.getBound());
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
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");
            context.put(Attributes.CARRIER_COST, String.valueOf(cRate));
            context.put(Attributes.CUSTOMER_COST, String.valueOf(ncRate));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }

        return true;
    }
}