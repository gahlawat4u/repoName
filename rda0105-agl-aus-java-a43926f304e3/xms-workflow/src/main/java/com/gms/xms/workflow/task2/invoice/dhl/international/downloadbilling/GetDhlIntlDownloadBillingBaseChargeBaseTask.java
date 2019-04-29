package com.gms.xms.workflow.task2.invoice.dhl.international.downloadbilling;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.dao.RateSheetRowDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.dhl.DhlRateSheetsVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jul 25, 2016 4:37:56 PM
 * <p>
 * Author huynd
 */
public class GetDhlIntlDownloadBillingBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlIntlDownloadBillingBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            DhlRateSheetsVo dhlRateSheetsVo = context.get(Attributes.DHL_RATE_SHEET);
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            Integer bound = context.getInt(Attributes.BOUND);
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            WebshipRateSheetDetailVo webshipRateSheetDetailVo = new WebshipRateSheetDetailVo();
            String columnName = shipmentBilling.getZone();
            if ("0".equalsIgnoreCase(columnName) || StringUtils.isBlank(columnName) || columnName == null) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCalculatedCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }
            rateSheetDetailFilter.setColumnName(columnName);

            Double rowName = Double.valueOf(shipmentBilling.getWeight());
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

            Boolean isDocMustBeCalByPackage = false;
            Boolean isPerWeight = false;

            if (shipmentBilling.getBillingContents()) {
                contents = 1;
            }

            if (contents == 0) {

                // Get min document weight
                rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierDocumentRate());
                webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                if (webshipRateSheetDetailVo != null) {
                    minDocWeightNonCarrier = webshipRateSheetDetailVo.getMinWeight();
                    minDocValueNonCarrier = webshipRateSheetDetailVo.getMinValue();
                }

                rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierDocumentRate());
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
                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierDocumentRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxDocValueNonCarrier = webshipRateSheetDetailVo.getMaxValue();
                    }

                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierDocumentRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxDocWeightCarrier = webshipRateSheetDetailVo.getMaxWeight();
                        maxDocValueCarrier = webshipRateSheetDetailVo.getMaxValue();
                    }
                    // Max document overweight
                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setMaxRowName(maxDocWeightCarrier);
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        maxOverDocWeight = webshipRateSheetDetailVo.getMaxWeight();
                    }

                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierPakRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        pakMaxOverDocWeight = webshipRateSheetDetailVo.getMaxWeight();
                    }

                    if (rowName >= maxDocWeightCarrier && (rowName < maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        ncRate = maxDocValueNonCarrier;
                        cRate = maxDocValueCarrier;
                    } else if (rowName >= maxDocWeightCarrier && (rowName >= maxOverDocWeight || rowName >= pakMaxOverDocWeight)) {
                        contents = 1;
                        isDocMustBeCalByPackage = true;
                    } else {
                        rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierDocumentRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            ncRate = webshipRateSheetDetailVo.getValue();
                        }

                        rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierDocumentRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            cRate = webshipRateSheetDetailVo.getValue();
                        }
                    }
                }
            }

            if (contents == 1 || isDocMustBeCalByPackage) {
                isDocMustBeCalByPackage = false;
                Boolean checkNonCarrierPerWeightZone = ShipmentUtils.checkColumnIsExist(dhlRateSheetsVo.getNonCarrierPackagePerWeightRate(), columnName);
                Boolean checkCarrierPerWeightZone = ShipmentUtils.checkColumnIsExist(dhlRateSheetsVo.getCarrierPackagePerWeightRate(), columnName);

                if (checkNonCarrierPerWeightZone || checkCarrierPerWeightZone) {
                    Integer totalRows = 0;
                    RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
                    List<RateSheetRowVo> rateSheetRowVos = new LinkedList<>();
                    // Get non carrier rate per weight row
                    rateSheetRowVos = rateSheetRowDao.selectBaseRateRow(dhlRateSheetsVo.getNonCarrierPackagePerWeightRate());
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
                    rateSheetRowVos = rateSheetRowDao.selectBaseRateRow(dhlRateSheetsVo.getCarrierPackagePerWeightRate());
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
                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierPackagePerWeightRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        ncPerWeightRate = webshipRateSheetDetailVo.getValue();
                        ncRate = ncPerWeightRate;
                    }

                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierPackagePerWeightRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        cPerWeightRate = webshipRateSheetDetailVo.getValue();
                        cRate = cPerWeightRate;
                    }
                } else {
                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        minPackageWeightNonCarrier = webshipRateSheetDetailVo.getMinWeight();
                        minPackageValueNonCarrier = webshipRateSheetDetailVo.getMinValue();
                    }

                    rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
                    webshipRateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                    if (webshipRateSheetDetailVo != null) {
                        minPackageWeightCarrier = webshipRateSheetDetailVo.getMinWeight();
                        minPackageValueCarrier = webshipRateSheetDetailVo.getMinValue();
                    }

                    if (rowName <= minPackageWeightNonCarrier || rowName <= minPackageWeightCarrier) {
                        ncRate = minPackageValueNonCarrier;
                        cRate = minPackageValueCarrier;
                    } else {
                        rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            ncRate = webshipRateSheetDetailVo.getValue();
                        }

                        rateSheetDetailFilter.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
                        webshipRateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (webshipRateSheetDetailVo != null) {
                            cRate = webshipRateSheetDetailVo.getValue();
                        }
                    }
                }
            }

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);

            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(customerCode).substring(0, 3));
            Double franchiseMarkup = 0D;
            if (franchiseVo.getMarkupRate() != null) {
                franchiseMarkup = franchiseVo.getMarkupRate();
            }

            // Put franchise markup for handling surcharge calculation
            context.put(Attributes.FRANCHISE_MARKUP, String.valueOf(franchiseMarkup));

            cRate = cRate + (cRate * franchiseMarkup) / 100;
            cRate = MathUtils.round(cRate, 2);

            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge + (carrierBaseCharge * franchiseMarkup) / 100;
            franchiseCost = MathUtils.round(franchiseCost, 2);

            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            customerBaseRateFilter.setCustomerCode(customerCode);
            customerBaseRateFilter.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId());
            customerBaseRateFilter.setContent(contents);
            customerBaseRateFilter.setBound(bound);
            customerBaseRateFilter.setWeight(rowName);
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);

            // Get minimum base charge cost
            Double miniumBaseChargePercent = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(customerCode);
            if (customerVo != null) {
                miniumBaseChargePercent = customerVo.getMinimunBaseCharge();
            }

            // Get customer base rate
            Double baseRate = 0D;
            Integer rateType = 0;
            if (customerBaseRateVo != null) {
                if (customerBaseRateVo.getZoneCheck()) {
                    customerBaseRateFilter.setZone(columnName);
                    customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                    baseRate = customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
                } else {
                    baseRate = customerBaseRateVo.getRate();
                }
                rateType = customerBaseRateVo.getRateType();
            }

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");

            // Get extra charges
            Double extraCharge = 0D;
            if (shipmentTypeVo.getShipmentTypeId() == 122) {
                extraCharge = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("ESI HANDLING CHARGE"));
            }

            if (isPerWeight) {
                ncRate = rowName * ncRate + extraCharge;
                cRate = rowName * cRate + extraCharge;
            } else {
                ncRate = ncRate + extraCharge;
                cRate = cRate + extraCharge;
            }
            cRate = (cRate == 0) ? shipmentBilling.getCarrierCost() : MathUtils.round(cRate, 2);
            ncRate = (ncRate == 0) ? shipmentBilling.getCustomerCost() : MathUtils.round(ncRate, 2);

            // Carrier cost for shipment billing
            Double carrierCost = cRate;
            // Customer cost for shipment billing
            Double customerCost = ncRate;
            // Customer tax percent for shipment billing
            Double customerTaxPercent = shipmentBilling.getCustomerTaxPercent();
            // Customer tax amount for shipment billing
            Double customerTaxAmount = (customerCost * customerTaxPercent) / 100;
            customerTaxAmount = MathUtils.round(customerTaxAmount, 2);

            ShipmentBillingVo billingCost = new ShipmentBillingVo();
            billingCost.setCalculatedCarrierCost(carrierCost);
            billingCost.setFranchiseCost(franchiseCost);
            billingCost.setCustomerCost(customerCost);
            billingCost.setCustomerTaxPercent(customerTaxPercent);
            billingCost.setCustomerTaxAmount(customerTaxAmount);
            context.put(Attributes.BILLING_COST, billingCost);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
