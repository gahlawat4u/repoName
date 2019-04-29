package com.gms.xms.workflow.task2.toll.priority;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.webship.TollPriorityRateSheetDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetFilter;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import com.gms.xms.workflow.utils.TollUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTntDomesticBaseChargeTask
 * <p>
 * Author HungNT Date Aug 11, 2015
 */
public class GetTollPriorityBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollPriorityBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
            Double quoteWeight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
            Double rowName = Math.ceil(quoteWeight);
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }
            if (shipmentInfoVo.getShipmentTypeId() == 175) {
                if (rowName > 1) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "Weight shouldn't exceed 1 kg for this service type!");
                    return false;
                }
            }
            if (shipmentInfoVo.getShipmentTypeId() == 176) {
                if (rowName > 3) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "Weight shouldn't exceed 3 kg for this service type!");
                    return false;
                }
            }

            if (shipmentInfoVo.getShipmentTypeId() == 177) {
                if (rowName > 5) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "Weight shouldn't exceed 5 kg for this service type!");
                    return false;
                }
            }

            String senderZone = context.getString(Attributes.SENDER_ZONE_CODE);
            String receiverZone = context.getString(Attributes.RECEIVER_ZONE_CODE);
            if (!StringUtils.isBlank(senderZone) && !StringUtils.isBlank(receiverZone)) {
                ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
                ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(shipmentInfoVo.getShipmentTypeId());
                Double baseCharge = shipmentType.getBasicCharge();
                String[] serviceGroup = shipmentType.getServiceGroup().split("#");

                String serviceCode = !StringUtils.isBlank(serviceGroup[1]) ? serviceGroup[1] : shipmentType.getServiceCode();
                String productCode = !StringUtils.isBlank(serviceGroup[0]) ? serviceGroup[0] : "2";
                if (shipmentType.getPerWeightStatus() == 1) {
                    Integer totalPiece = 1;
                    if (shipmentInfoVo.getShipmentTypeId() == 176 || shipmentInfoVo.getShipmentTypeId() == 175 || shipmentInfoVo.getShipmentTypeId() == 177) {
                        totalPiece = 1;
                    } else {
                        totalPiece = TollUtils.getTotalPiecesQuantity(shipmentInfoVo.getPieces());
                    }

                    cRate = baseCharge * totalPiece;
                } else {
                    TollPriorityRateSheetDao priorityRateSheetDao = new TollPriorityRateSheetDao();
                    TollPriorityRateSheetFilter priorityRateSheetFilter = new TollPriorityRateSheetFilter();
                    priorityRateSheetFilter.setProduct(productCode);
                    priorityRateSheetFilter.setService(serviceCode);
                    priorityRateSheetFilter.setZoneFrom(senderZone);
                    priorityRateSheetFilter.setZoneTo(receiverZone);
                    TollPriorityRateSheetVo priorityRateSheetVo = priorityRateSheetDao.selectTollPriorityRateSheet(priorityRateSheetFilter);
                    if (priorityRateSheetVo != null) {
                        Double conRate = 0D;
                        try {
                            conRate = Double.parseDouble(priorityRateSheetVo.getConRate());
                        } catch (Exception e) {
                        }
                        Double kgRate = 0D;
                        try {
                            kgRate = Double.parseDouble(priorityRateSheetVo.getKgsRate());
                        } catch (Exception e) {
                        }
                        cRate = conRate + (rowName * kgRate);
                    } else {
                        context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                        context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
                        return false;
                    }
                }

                baseCharge = cRate;
                // Franchise markup
                IFranchiseService franchiseService = new FranchiseServiceImp();
                FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(webshipLoginInfo.getCustomerCode()).substring(0, 3));
                Double franchiseMarkup = 0D;
                try {
                    if (franchiseVo.getTollMarkupRate() != null) {
                        franchiseMarkup = franchiseVo.getTollMarkupRate();
                    }
                } catch (Exception e) {
                }
                // Get customer base rate
                CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                Long userId = webshipLoginInfo.getCustomerCode();
                customerBaseRateFilter.setShipmentTypeId(shipmentInfoVo.getShipmentTypeId());
                customerBaseRateFilter.setWeight(rowName);
                customerBaseRateFilter.setCustomerCode(userId);
                CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);

                // Get minimum base charge cost
                Double miniumBaseChargePercent = 0D;
                CustomerDao customerDao = new CustomerDao();
                CustomerVo customerVo = new CustomerVo();
                customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(userId);
                miniumBaseChargePercent = customerVo.getMinimunBaseCharge();
                Double baseRate = 0D;
                cRate = cRate + (cRate * franchiseMarkup) / 100;
                if (customerBaseRateVo != null) {
                    baseRate = customerBaseRateVo.getRate();
                    ncRate = ShipmentUtils.calculateCostByBaseRate(customerBaseRateVo.getRateType(), baseRate, miniumBaseChargePercent, cRate, ncRate);
                } else {
                    ncRate = cRate;
                }

            }

            cRate = MathUtils.round(cRate, 2);
            ncRate = MathUtils.round(ncRate, 2);
            context.put(Attributes.CARRIER_COST, cRate);
            context.put(Attributes.CUSTOMER_COST, ncRate);
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }
}
