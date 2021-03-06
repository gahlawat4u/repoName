package com.gms.xms.workflow.task2.ups.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.dao.RateSheetRowDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.ups.UpsIntlRateSheetVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from GetUpsIntlBaseChargeTask
 * <p>
 * Author Thinhdd Date Mar 29, 2017
 */
public class GetUpsIntlBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetUpsIntlBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {

            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            UpsIntlRateSheetVo upsIntlRateSheetVo = context.get(Attributes.UPS_RATE_SHEET);
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);

            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            WebshipRateSheetDetailVo webshipRateSheetDetailVo = new WebshipRateSheetDetailVo();
            String columnName = shipmentInfoVo.getZone();
            rateSheetDetailFilter.setColumnName(columnName);
            
           Double rowName = context.get(Attributes.SHIPMENT_TOTAL_WEIGHT);  //code by rakesh sir
            rateSheetDetailFilter.setRowName(rowName);

            Double ncRate = 0D;
            Double cRate = 0D;

            Double ncPerWeightRate = 0D;
            Double cPerWeightRate = 0D;

            Double minDocWeightNonCarrier = 0D;
            Double minDocValueNonCarrier = 0D;
            Double minDocWeightCarrier = 0D;
            Double minDocValueCarrier = 0D;
            Double maxDocValueNonCarrier = 0D;

            Double maxDocWeightCarrier = 0D;
            Double maxDocValueCarrier = 0D;
            Double maxOverDocWeight = 0D;

            Double pakMaxOverDocWeight = 0D;
            Integer contents = 0;

            Double minPackageWeightNonCarrier = 0D;
            Double minPackageValueNonCarrier = 0D;

            Double minPackageWeightCarrier = 0D;
            Double minPackageValueCarrier = 0D;
          
            Boolean isPerWeight = false;

            if (shipmentInfoVo.getContents() != null) {
                contents = shipmentInfoVo.getContents();
            }

            if (contents == 0) {

                // Get min document weight
                rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierDocumentRate());
                webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                if (webshipRateSheetDetailVo != null) {
                    minDocWeightNonCarrier = webshipRateSheetDetailVo.getMinWeight();
                    minDocValueNonCarrier = webshipRateSheetDetailVo.getMinValue();
                }

                rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierDocumentRate());
                webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                if (webshipRateSheetDetailVo != null) {
                    minDocWeightCarrier = webshipRateSheetDetailVo.getMinWeight();
                    minDocValueCarrier = webshipRateSheetDetailVo.getMinValue();
                }

                if (rowName < minDocWeightCarrier || rowName <= minDocWeightNonCarrier) {
                    ncRate = minDocValueNonCarrier;
                    cRate = minDocValueCarrier;
                } else {
                    // Max document
                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierDocumentRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxDocValueNonCarrier = webshipRateSheetDetailVo.getMaxValue();
                    }

                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierDocumentRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxDocWeightCarrier = webshipRateSheetDetailVo.getMaxWeight();
                        maxDocValueCarrier = webshipRateSheetDetailVo.getMaxValue();
                    }
                    // Max document overweight
                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setMaxRowName(maxDocWeightCarrier);
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxOverDocWeight = webshipRateSheetDetailVo.getMaxWeight();
                    }

                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierPakRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        pakMaxOverDocWeight = webshipRateSheetDetailVo.getMaxWeight();
                    }

                    if (rowName >= maxDocWeightCarrier && (rowName < maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        ncRate = maxDocValueNonCarrier;
                        cRate = maxDocValueCarrier;
                    } else if (rowName >= maxDocWeightCarrier && (rowName >= maxOverDocWeight || rowName >= pakMaxOverDocWeight)) {
                        contents = 1;  
                        
                    
                    } else {
                        rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierDocumentRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            ncRate = webshipRateSheetDetailVo.getValue();
                        }

                        rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierDocumentRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            cRate = webshipRateSheetDetailVo.getValue();
                        }
                    }
                }
            }

            if (contents == 1) {
            	rowName = checkLatestRowName(upsIntlRateSheetVo.getCarrierPackageRate(), rateSheetDetailDao, rateSheetDetailFilter, rowName);
                rowName = checkLatestRowName(upsIntlRateSheetVo.getNonCarrierPackageRate(), rateSheetDetailDao, rateSheetDetailFilter, rowName);

                Boolean checkNonCarrierPerWeightZone = ShipmentUtils.checkColumnIsExist(upsIntlRateSheetVo.getNonCarrierPackagePerWeightRate(), columnName);
                Boolean checkCarrierPerWeightZone = ShipmentUtils.checkColumnIsExist(upsIntlRateSheetVo.getCarrierPackagePerWeightRate(), columnName);

                if (checkNonCarrierPerWeightZone || checkCarrierPerWeightZone) {
                    Integer totalRows = 0;
                    RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
                    List<RateSheetRowVo> rateSheetRowVos;
                    // Get non carrier rate per weight row
                    rateSheetRowVos = rateSheetRowDao.selectBaseRateRow(upsIntlRateSheetVo.getNonCarrierPackagePerWeightRate());
                    totalRows = rateSheetRowVos.size();
                    if (totalRows > 0) {
                        for (int i = 0; i < totalRows; i++) {
                            RateSheetRowVo rateSheetFirstRowVo = rateSheetRowVos.get(0);
                            RateSheetRowVo rateSheetRowVo = rateSheetRowVos.get(i);
                            if (i == totalRows - 1) {
                                if (rowName >= rateSheetFirstRowVo.getRowName()) {
                                    isPerWeight = true;
                                    totalRows = 0;
                                    break;
                                }
                            } else {
                                RateSheetRowVo rateSheetRowNextVo = rateSheetRowVos.get(i + 1);
                                if (rowName >= rateSheetRowVo.getRowName() && rowName <= rateSheetRowNextVo.getRowName()) {
                                    isPerWeight = true;
                                    totalRows = 0;
                                    break;
                                }
                            }
                        }
                    }

                    // Get carrier per weight row
                    rateSheetRowVos = rateSheetRowDao.selectBaseRateRow(upsIntlRateSheetVo.getCarrierPackagePerWeightRate());
                    totalRows = rateSheetRowVos.size();
                    if (totalRows > 0) {
                        for (int i = 0; i < totalRows; i++) {
                            RateSheetRowVo rateSheetFirstRowVo = rateSheetRowVos.get(0);
                            RateSheetRowVo rateSheetRowVo = rateSheetRowVos.get(i);
                            if (i == totalRows - 1) {
                                if (rowName >= rateSheetFirstRowVo.getRowName()) {
                                    isPerWeight = true;
                                    break;
                                }
                            } else {
                                RateSheetRowVo rateSheetRowNextVo = rateSheetRowVos.get(i + 1);
                                if (rowName >= rateSheetRowVo.getRowName() && rowName <= rateSheetRowNextVo.getRowName()) {
                                    isPerWeight = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (isPerWeight) {
                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierPackagePerWeightRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        ncPerWeightRate = webshipRateSheetDetailVo.getValue();
                        ncRate = ncPerWeightRate;
                    }

                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierPackagePerWeightRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        cPerWeightRate = webshipRateSheetDetailVo.getValue();
                        cRate = cPerWeightRate;
                    }
                } else {
                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierPackageRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        minPackageWeightNonCarrier = webshipRateSheetDetailVo.getMinWeight();
                        minPackageValueNonCarrier = webshipRateSheetDetailVo.getMinValue();
                    }

                    rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierPackageRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        minPackageWeightCarrier = webshipRateSheetDetailVo.getMinWeight();
                        minPackageValueCarrier = webshipRateSheetDetailVo.getMinValue();
                    }

                    if (rowName <= minPackageWeightNonCarrier || rowName <= minPackageWeightCarrier) {
                        ncRate = minPackageValueNonCarrier;
                        cRate = minPackageValueCarrier;
                    } else {
                        rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getNonCarrierPackageRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            ncRate = webshipRateSheetDetailVo.getValue();
                        }

                        rateSheetDetailFilter.setSheetId(upsIntlRateSheetVo.getCarrierPackageRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            cRate = webshipRateSheetDetailVo.getValue();
                        }
                    }
                }
            }

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);

            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(webshipLoginInfo.getCustomerCode()).substring(0, 3));
            Double franchiseMarkup = 0D;
            try {
                if (franchiseVo.getMarkupRate() != null) {
                    franchiseMarkup = franchiseVo.getUpsMarkupRate();
                }
            } catch (Exception ignored) {
            }
            // Put franchise markup for handling surcharge calculation
            context.put(Attributes.FRANCHISE_MARKUP, String.valueOf(franchiseMarkup));

            cRate = cRate + (cRate * franchiseMarkup) / 100;

            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            Long userId = webshipLoginInfo.getCustomerCode();
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
            if(customerBaseRateVo != null){
            if (customerBaseRateVo.getZoneCheck()) {
                customerBaseRateFilter.setZone(columnName);
                customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                baseRate = customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
            } else {
                baseRate = customerBaseRateVo.getRate();
            }
            }
            // put base rate for handling surcharge calculation
            context.put(Attributes.CUSTOMER_BASE_RATE, customerBaseRateVo);
            context.put(Attributes.MINIMUM_BASECHARGE_RATE, MathUtils.double2String(miniumBaseChargePercent, 2));

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(customerBaseRateVo.getRateType(), baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");
            // Get extra charges
            Double extraCharge = 0D;
            if (shipmentInfoVo.getShipmentTypeId() == 122) {
                extraCharge = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("ESI HANDLING CHARGE"));
            }

            if (isPerWeight) {
                context.put(Attributes.SHIPMENT_TOTAL_WEIGHT, rowName);
                ncRate = rowName * ncRate + extraCharge;
                cRate = rowName * cRate + extraCharge;
            } else {
                ncRate = ncRate + extraCharge;
                cRate = cRate + extraCharge;
            }
            cRate = MathUtils.round(cRate, 2);
            ncRate = MathUtils.round(ncRate, 2);
            context.put(Attributes.CARRIER_COST, cRate);
            context.put(Attributes.CUSTOMER_COST, ncRate);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private Double checkLatestRowName(Long sheetId, RateSheetDetailDao rateSheetDetailDao, RateSheetDetailFilter rateSheetDetailFilter, Double rowName) throws DaoException {
        WebshipRateSheetDetailVo webshipRateSheetDetailVo;
        rateSheetDetailFilter.setSheetId(sheetId);
        webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
        if (webshipRateSheetDetailVo != null) {
            if(rowName > webshipRateSheetDetailVo.getMaxWeight())
            {
                rowName = Math.ceil(rowName);
            }
        }
        rateSheetDetailFilter.setRowName(rowName);
        return rowName;
    }
}
