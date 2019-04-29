package com.gms.xms.workflow.task2.admin.ratesheet.viewbycustomer;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailInfoModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.model.admin.ratesheets.RateSheetInfoModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.RateSheetColumnDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.dao.RateSheetRowDao;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetBaseRateVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 18, 2016 4:43:58 PM
 * <p>
 * Author dattrinh
 */
public class GetRateSheetDataTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get view rate sheet request vo.
            ViewRateSheetRequestVo requestVo = context.get(Attributes.VIEW_RATE_SHEET_REQUEST_VO);
            // Get service type info.
            ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
            // Get service markup rate.
            Double serviceMarkupRate = context.get(Attributes.SERVICE_MARKUP_RATE);
            // Get rate sheet data.
            RateSheetInfoModel rateSheetModel = null;
            RateSheetInfoModel perWeightRateSheetModel = null;
            switch (serviceTypeVo.getServiceId()) {
                case 3: // TNT Dom Service
                    rateSheetModel = loadTntDomRateSheet(serviceTypeVo.getRateSheetId(), serviceTypeVo.getNcRateSheetId(), serviceMarkupRate, requestVo);
                    rateSheetModel.setSenderSuburb(this.detectSenderSuburb(serviceTypeVo.getServiceId(), requestVo.getColumnName()));
                    perWeightRateSheetModel = loadTntDomRateSheet(serviceTypeVo.getPerWeightRateSheetId(), serviceTypeVo.getNcPerWeightRateSheetId(), serviceMarkupRate, requestVo);
                    break;
                case 72: // Star Track Service.
                    if (serviceTypeVo.getShipmentTypeId() == 228 || serviceTypeVo.getShipmentTypeId() == 229) {
                        // If the shipment type is Road Express or Premium Air Freight then show rate sheet look like TNT Dom.
                        rateSheetModel = loadStarTrackRateSheet(serviceTypeVo.getRateSheetId(), serviceTypeVo.getNcRateSheetId(), serviceMarkupRate, requestVo);
                        rateSheetModel.setSenderSuburb(this.detectSenderSuburb(serviceTypeVo.getServiceId(), requestVo.getColumnName()));
                        perWeightRateSheetModel = loadStarTrackRateSheet(serviceTypeVo.getPerWeightRateSheetId(), serviceTypeVo.getNcPerWeightRateSheetId(), serviceMarkupRate, requestVo);
                    } else {
                        rateSheetModel = loadRateSheet(serviceTypeVo.getRateSheetId(), serviceTypeVo.getNcRateSheetId(), serviceMarkupRate, requestVo);
                        perWeightRateSheetModel = loadRateSheet(serviceTypeVo.getPerWeightRateSheetId(), serviceTypeVo.getNcPerWeightRateSheetId(), serviceMarkupRate, requestVo);
                    }
                    break;
                case 400: // Ups Service
                	rateSheetModel = loadRateSheetUps(serviceTypeVo.getRateSheetId(), serviceTypeVo.getNcRateSheetId(), serviceMarkupRate, requestVo);
                    perWeightRateSheetModel = loadRateSheetUps(serviceTypeVo.getPerWeightRateSheetId(), serviceTypeVo.getNcPerWeightRateSheetId(), serviceMarkupRate, requestVo);
                    break;    
                    
                default:
                    rateSheetModel = loadRateSheet(serviceTypeVo.getRateSheetId(), serviceTypeVo.getNcRateSheetId(), serviceMarkupRate, requestVo);
                    perWeightRateSheetModel = loadRateSheet(serviceTypeVo.getPerWeightRateSheetId(), serviceTypeVo.getNcPerWeightRateSheetId(), serviceMarkupRate, requestVo);
                    break;
            }
            // Put into the context.
            if (rateSheetModel != null) {
                rateSheetModel.setTitle(serviceTypeVo.getDisplayName());
                context.put(Attributes.RATE_SHEET_RESULT, rateSheetModel);
            }
            if (perWeightRateSheetModel != null) {
                context.put(Attributes.PER_WEIGHT_RATE_SHEET_RESULT, perWeightRateSheetModel);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    protected String detectSenderSuburb(Integer serviceId, String columnName) throws Exception {
        CarrierZoneVo zoneFilter = new CarrierZoneVo();
        zoneFilter.setCarrier(serviceId.longValue());
        zoneFilter.setZoneCode(columnName);
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        CarrierZoneVo carrierZoneVo = carrierZoneDao.getZoneByCarrierAndCode(zoneFilter);
        String senderSuburb = "";
        if (carrierZoneVo != null) {
            senderSuburb = carrierZoneVo.getZoneName() + " - " + columnName + " TO ALL";
        }
        return senderSuburb;
    }

    protected RateSheetInfoModel loadRateSheet(Long sheetId, Long ncSheetId, Double serviceMarkupRate, ViewRateSheetRequestVo rateSheetRequestVo) throws Exception {
        // Not load rate sheet if it's id is 0.
        if (sheetId != 0) {
            String key;
            // Load carrier rate sheet rows.
            RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
            List<RateSheetRowVo> cRowVos = rateSheetRowDao.selectBySheetId(sheetId);
            Map<String, RateSheetRowVo> cRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : cRowVos) {
                key = String.valueOf(rowVo.getRowId());
                cRowMap.put(key, rowVo);
            }
            // Load carrier rate sheet columns.
            RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
            List<RateSheetColumnVo> cColumnVos = rateSheetColumnDao.selectBySheetId(sheetId);
            Map<String, RateSheetColumnVo> cColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : cColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                cColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of carrier rate.
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            List<RateSheetDetailVo> cDetailVos = rateSheetDetailDao.selectBySheetId(sheetId);
            // Load non carrier rate sheet rows.
            List<RateSheetRowVo> ncRowVos = rateSheetRowDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetRowVo> ncRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : ncRowVos) {
                key = String.valueOf(rowVo.getRowId());
                ncRowMap.put(key, rowVo);
            }
            // Load non carrier rate sheet columns.
            List<RateSheetColumnVo> ncColumnVos = rateSheetColumnDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetColumnVo> ncColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : ncColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                ncColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of non carrier.
            List<RateSheetDetailVo> ncDetailVos = rateSheetDetailDao.selectBySheetId(ncSheetId);
            // Create 2 map for carrier rate and none carrier rate.
            Map<String, RateSheetDetailVo> cRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                cRateMap.put(key, detailVo);
            }
            Map<String, RateSheetDetailVo> ncRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : ncDetailVos) {
                RateSheetRowVo row = ncRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = ncColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ncRateMap.put(key, detailVo);
            }
            // Create new rate sheet map to keep rate sheet result.
            Map<String, RateSheetDetailInfoModel> map = new HashMap<String, RateSheetDetailInfoModel>();
            // Recalculate charge of each cell.
            RateSheetDetailVo rateVo = null;
            RateSheetDetailInfoVo rateInfoVo = null;
            for (RateSheetDetailVo detailVo : cDetailVos) {
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ViewRateSheetBaseRateVo baseRateVo = findBaseRate(rateSheetRequestVo, row.getRowName());
                // Get zone rate.
                Double zoneRate = baseRateVo.getZoneRates() == null ? null : baseRateVo.getZoneRates().get(column.getColumnName());
                // Get base rate.
                Double baseRate = zoneRate != null ? zoneRate : baseRateVo.getRate();
                RateSheetDetailVo cRateVo = cRateMap.get(key);
                cRateVo = (cRateVo == null) ? new RateSheetDetailVo() : cRateVo;
                RateSheetDetailVo ncRateVo = ncRateMap.get(key);
                ncRateVo = (ncRateVo == null) ? new RateSheetDetailVo() : ncRateVo;
                rateVo = (baseRateVo.getRateType() == 0) ? ncRateVo : cRateVo;
                // Recalculate charge.
                Double cRate = cRateVo.getValue() == null ? 0.0 : cRateVo.getValue();
                cRate = MathUtils.round3Decimal(cRate);
                Double ncRate = ncRateVo.getValue() == null ? 0.0 : ncRateVo.getValue();
                ncRate = MathUtils.round3Decimal(ncRate);
                // Get franchise cost is origin carrier rate with service markup rate.
                Double franchiseCost = cRate * (1 + serviceMarkupRate / 100);
                franchiseCost = MathUtils.round3Decimal(franchiseCost);
                rateInfoVo.setFranchiseCost(franchiseCost);
                // Get customer cost.
                Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), franchiseCost, ncRate);
                Double charge = resultMap.get("ncRate");
                charge = MathUtils.round3Decimal(charge);
                rateVo.setValue(charge);
                rateInfoVo.setRate(rateVo);
                rateInfoVo.setCustomerCost(charge);
                // Get margin.
                rateInfoVo.setMargin(MathUtils.round3Decimal(rateInfoVo.getCustomerCost() - rateInfoVo.getFranchiseCost()));
                // Get percent.
                Double percent = rateInfoVo.getCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getCustomerCost() - rateInfoVo.getFranchiseCost()) / rateInfoVo.getCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setPercent(percent);
                map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
            }
            // Detect rows and columns used.
            List<RateSheetRowVo> rowVos = cRowVos;
            if (rowVos == null || rowVos.size() == 0) {
                rowVos = ncRowVos;
            }
            List<RateSheetColumnVo> columnVos = cColumnVos;
            if (cColumnVos == null || cColumnVos.size() == 0) {
                columnVos = ncColumnVos;
            }
            // Detect max weight.
            Double maxWeight = 0.0;
            for (RateSheetRowVo rowVo : rowVos) {
                Double weight = rowVo.getRowName();
                if (weight > maxWeight) {
                    maxWeight = weight;
                }
            }
            // Create new rate sheet info model.
            RateSheetInfoModel rateSheetInfo = new RateSheetInfoModel();
            rateSheetInfo.setRows(ModelUtils.createListModelFromVo(rowVos, RateSheetRowModel.class));
            rateSheetInfo.setColumns(ModelUtils.createListModelFromVo(columnVos, RateSheetColumnModel.class));
            rateSheetInfo.setData(map);
            rateSheetInfo.setMaxWeight(String.valueOf(maxWeight));
            return rateSheetInfo;
        }
        return null;
    }

    
    protected RateSheetInfoModel loadRateSheetUps( Long sheetId, Long ncSheetId, Double serviceMarkupRate, ViewRateSheetRequestVo rateSheetRequestVo) throws Exception {
        // Not load rate sheet if it's id is 0.
        if (sheetId != 0) {
            String key;
            // Load carrier rate sheet rows.
            RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
            List<RateSheetRowVo> cRowVos = rateSheetRowDao.selectBySheetId(sheetId);
            Map<String, RateSheetRowVo> cRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : cRowVos) {
                key = String.valueOf(rowVo.getRowId());
                cRowMap.put(key, rowVo);
            }
            // Load carrier rate sheet columns.
            RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
            List<RateSheetColumnVo> cColumnVos = rateSheetColumnDao.selectBySheetIdForUps(sheetId);
            Map<String, RateSheetColumnVo> cColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : cColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                cColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of carrier rate.
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            List<RateSheetDetailVo> cDetailVos = rateSheetDetailDao.selectBySheetId(sheetId);
            // Load non carrier rate sheet rows.
            List<RateSheetRowVo> ncRowVos = rateSheetRowDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetRowVo> ncRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : ncRowVos) {
                key = String.valueOf(rowVo.getRowId());
                ncRowMap.put(key, rowVo);
            }
            // Load non carrier rate sheet columns.
            List<RateSheetColumnVo> ncColumnVos = rateSheetColumnDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetColumnVo> ncColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : ncColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                ncColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of non carrier.
            List<RateSheetDetailVo> ncDetailVos = rateSheetDetailDao.selectBySheetId(ncSheetId);
            // Create 2 map for carrier rate and none carrier rate.
            Map<String, RateSheetDetailVo> cRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                cRateMap.put(key, detailVo);
            }
            Map<String, RateSheetDetailVo> ncRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : ncDetailVos) {
                RateSheetRowVo row = ncRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = ncColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ncRateMap.put(key, detailVo);
            }
            // Create new rate sheet map to keep rate sheet result.
            Map<String, RateSheetDetailInfoModel> map = new HashMap<String, RateSheetDetailInfoModel>();
            // Recalculate charge of each cell.
            RateSheetDetailVo rateVo = null;
            RateSheetDetailInfoVo rateInfoVo = null;
            for (RateSheetDetailVo detailVo : cDetailVos) {
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ViewRateSheetBaseRateVo baseRateVo = findBaseRate(rateSheetRequestVo, row.getRowName());
                // Get zone rate.
                Double zoneRate = baseRateVo.getZoneRates() == null ? null : baseRateVo.getZoneRates().get(column.getColumnName());
                // Get base rate.
                Double baseRate = zoneRate != null ? zoneRate : baseRateVo.getRate();
                RateSheetDetailVo cRateVo = cRateMap.get(key);
                cRateVo = (cRateVo == null) ? new RateSheetDetailVo() : cRateVo;
                RateSheetDetailVo ncRateVo = ncRateMap.get(key);
                ncRateVo = (ncRateVo == null) ? new RateSheetDetailVo() : ncRateVo;
                rateVo = (baseRateVo.getRateType() == 0) ? ncRateVo : cRateVo;
                // Recalculate charge.
                Double cRate = cRateVo.getValue() == null ? 0.0 : cRateVo.getValue();
                cRate = MathUtils.round3Decimal(cRate);
                Double ncRate = ncRateVo.getValue() == null ? 0.0 : ncRateVo.getValue();
                ncRate = MathUtils.round3Decimal(ncRate);
                // Get franchise cost is origin carrier rate with service markup rate.
                Double franchiseCost = cRate * (1 + serviceMarkupRate / 100);
                franchiseCost = MathUtils.round3Decimal(franchiseCost);
                rateInfoVo.setFranchiseCost(franchiseCost);
                // Get customer cost.
                Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), franchiseCost, ncRate);
                Double charge = resultMap.get("ncRate");
                charge = MathUtils.round3Decimal(charge);
                rateVo.setValue(charge);
                rateInfoVo.setRate(rateVo);
                rateInfoVo.setCustomerCost(charge);
                // Get margin.
                rateInfoVo.setMargin(MathUtils.round3Decimal(rateInfoVo.getCustomerCost() - rateInfoVo.getFranchiseCost()));
                // Get percent.
                Double percent = rateInfoVo.getCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getCustomerCost() - rateInfoVo.getFranchiseCost()) / rateInfoVo.getCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setPercent(percent);
                map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
            }
            // Detect rows and columns used.
            List<RateSheetRowVo> rowVos = cRowVos;
            if (rowVos == null || rowVos.size() == 0) {
                rowVos = ncRowVos;
            }
            List<RateSheetColumnVo> columnVos = cColumnVos;
            if (cColumnVos == null || cColumnVos.size() == 0) {
                columnVos = ncColumnVos;
            }
            // Detect max weight.
            Double maxWeight = 0.0;
            for (RateSheetRowVo rowVo : rowVos) {
                Double weight = rowVo.getRowName();
                if (weight > maxWeight) {
                    maxWeight = weight;
                }
            }
            // Create new rate sheet info model.
            RateSheetInfoModel rateSheetInfo = new RateSheetInfoModel();
            rateSheetInfo.setRows(ModelUtils.createListModelFromVo(rowVos, RateSheetRowModel.class));
            rateSheetInfo.setColumns(ModelUtils.createListModelFromVo(columnVos, RateSheetColumnModel.class));
            rateSheetInfo.setData(map);
            rateSheetInfo.setMaxWeight(String.valueOf(maxWeight));
            return rateSheetInfo;
        }
        return null;
    }
    
    
    protected RateSheetInfoModel loadTntDomRateSheet(Long sheetId, Long ncSheetId, Double serviceMarkupRate, ViewRateSheetRequestVo rateSheetRequestVo) throws Exception {
        // Not load rate sheet if it's id is 0.
        if (sheetId != 0) {
            String key;
            // Load carrier rate sheet rows.
            RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
            List<RateSheetRowVo> cRowVos = rateSheetRowDao.selectBySheetId(sheetId);
            // Remove unnecessary carrier rows.
            List<RateSheetRowVo> showRowVos = new ArrayList<RateSheetRowVo>();
            String rowName;
            for (RateSheetRowVo row : cRowVos) {
                if (row.getIsChar()) {
                    rowName = row.getCharRowName();
                } else {
                    if (row.getRowName() != 0) {
                        rowName = row.getCharRowName();
                    } else {
                        rowName = String.valueOf(row.getRowName());
                    }
                }
                if (rateSheetRequestVo.getColumnName().equalsIgnoreCase(rowName) || "A".equalsIgnoreCase(rowName) || "D".equalsIgnoreCase(rowName)) {
                    showRowVos.add(row);
                }
            }
            cRowVos = showRowVos;
            Map<String, RateSheetRowVo> cRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : cRowVos) {
                key = String.valueOf(rowVo.getRowId());
                cRowMap.put(key, rowVo);
            }
            // Load carrier rate sheet columns.
            RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
            List<RateSheetColumnVo> cColumnVos = rateSheetColumnDao.selectBySheetId(sheetId);
            Map<String, RateSheetColumnVo> cColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : cColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                cColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of carrier rate.
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            List<RateSheetDetailVo> cDetailVos = rateSheetDetailDao.selectBySheetId(sheetId);
            // Load non carrier rate sheet rows.
            List<RateSheetRowVo> ncRowVos = rateSheetRowDao.selectBySheetId(ncSheetId);
            // Remove unnecessary non carrier rows.
            showRowVos = new ArrayList<RateSheetRowVo>();
            for (RateSheetRowVo row : ncRowVos) {
                if (row.getIsChar()) {
                    rowName = row.getCharRowName();
                } else {
                    if (row.getRowName() != 0) {
                        rowName = row.getCharRowName();
                    } else {
                        rowName = String.valueOf(row.getRowName());
                    }
                }
                if (rateSheetRequestVo.getColumnName().equalsIgnoreCase(rowName) || "A".equalsIgnoreCase(rowName) || "D".equalsIgnoreCase(rowName)) {
                    showRowVos.add(row);
                }
            }
            ncRowVos = showRowVos;
            Map<String, RateSheetRowVo> ncRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : ncRowVos) {
                key = String.valueOf(rowVo.getRowId());
                ncRowMap.put(key, rowVo);
            }
            // Load non carrier rate sheet columns.
            List<RateSheetColumnVo> ncColumnVos = rateSheetColumnDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetColumnVo> ncColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : ncColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                ncColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of non carrier.
            List<RateSheetDetailVo> ncDetailVos = rateSheetDetailDao.selectBySheetId(ncSheetId);
            // Create 2 map for carrier rate and none carrier rate.
            Map<String, RateSheetDetailVo> cRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                if (row == null || column == null) {
                    continue;
                }
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                cRateMap.put(key, detailVo);
            }
            Map<String, RateSheetDetailVo> ncRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : ncDetailVos) {
                RateSheetRowVo row = ncRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = ncColumnMap.get(String.valueOf(detailVo.getColumnId()));
                if (row == null || column == null) {
                    continue;
                }
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ncRateMap.put(key, detailVo);
            }
            // Create new rate sheet map to keep rate sheet result.
            Map<String, RateSheetDetailInfoModel> map = new HashMap<String, RateSheetDetailInfoModel>();
            // Recalculate charge of each cell.
            RateSheetDetailVo rateVo = null;
            RateSheetDetailInfoVo rateInfoVo = null;
            List<RateSheetColumnVo> removeColumns = new ArrayList<RateSheetColumnVo>();
            CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                if (row == null) {
                    continue;
                }
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ViewRateSheetBaseRateVo baseRateVo = findBaseRate(rateSheetRequestVo, row.getRowName());
                // Get zone rate.
                Double zoneRate = baseRateVo.getZoneRates() == null ? null : baseRateVo.getZoneRates().get(column.getColumnName());
                // Get base rate.
                Double baseRate = zoneRate != null ? zoneRate : baseRateVo.getRate();
                if (StringUtils.isBlank(column.getColumnName())) {
                    // Get extra info.
                    RateSheetDetailVo extraFilter = new RateSheetDetailVo();
                    extraFilter.setSheetId(sheetId);
                    extraFilter.setColumnId(column.getColumnId());
                    ExtraRateSheetDetailVo extraDetailVo = rateSheetDetailDao.getExtraRateSheetDetail(extraFilter);
                    if (extraDetailVo == null) {
                        // Don't show the invalid column.
                        removeColumns.add(column);
                        continue;
                    }
                    // Update column name.
                    column.setColumnName("ALL TO ALL " + extraDetailVo.getRowName() + " Kg");
                    // Recalculate value of base charge.
                    Double cValue = extraDetailVo.getValue();
                    cValue = MathUtils.round3Decimal(cValue);
                    Double ncValue = 0.0;
                    rateVo = new RateSheetDetailVo();
                    // Create new rate info object.
                    rateInfoVo = new RateSheetDetailInfoVo();
                    rateInfoVo.setRate(rateVo);
                    // Get base franchise cost.
                    Double franchiseCost = cValue * (1 + serviceMarkupRate / 100);
                    franchiseCost = MathUtils.round3Decimal(franchiseCost);
                    rateInfoVo.setBaseFranchiseCost(franchiseCost);
                    // Get base customer cost.
                    Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), franchiseCost, ncValue);
                    Double cBaseCharge = resultMap.get("ncRate");
                    cBaseCharge = MathUtils.round3Decimal(cBaseCharge);
                    rateInfoVo.setBaseCustomerCost(cBaseCharge);
                    rateVo.setBaseCharge(cBaseCharge);
                    // Get base margin.
                    rateInfoVo.setBaseMargin(MathUtils.round3Decimal(rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()));
                    // Get base percent.
                    Double percent = rateInfoVo.getBaseCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()) / rateInfoVo.getBaseCustomerCost();
                    percent = MathUtils.round3Decimal(percent);
                    rateInfoVo.setBasePercent(percent);
                    key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                    map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
                    continue;
                }
                RateSheetDetailVo cRateVo = cRateMap.get(key);
                cRateVo = (cRateVo == null) ? new RateSheetDetailVo() : cRateVo;
                RateSheetDetailVo ncRateVo = ncRateMap.get(key);
                ncRateVo = (ncRateVo == null) ? new RateSheetDetailVo() : ncRateVo;
                rateVo = (baseRateVo.getRateType() == 0) ? ncRateVo : cRateVo;
                // Recalculate value of min charge.
                Double cMinCharge = cRateVo.getMinCharge() == null ? 0.0 : cRateVo.getMinCharge();
                cMinCharge = MathUtils.round3Decimal(cMinCharge);
                Double ncMinCharge = ncRateVo.getMinCharge() == null ? 0.0 : ncRateVo.getMinCharge();
                ncMinCharge = MathUtils.round3Decimal(ncMinCharge);
                // Recalculate value of base charge.
                Double cBaseCharge = cRateVo.getBaseCharge() == null ? 0.0 : cRateVo.getBaseCharge();
                cBaseCharge = MathUtils.round3Decimal(cBaseCharge);
                Double ncBaseCharge = ncRateVo.getBaseCharge() == null ? 0.0 : ncRateVo.getBaseCharge();
                ncBaseCharge = MathUtils.round3Decimal(ncBaseCharge);
                // Recalculate value of per kg.
                Double cPerKg = cRateVo.getPerKg() == null ? 0.0 : cRateVo.getPerKg();
                cPerKg = MathUtils.round3Decimal(cPerKg);
                Double ncPerKg = ncRateVo.getPerKg() == null ? 0.0 : ncRateVo.getPerKg();
                ncPerKg = MathUtils.round3Decimal(ncPerKg);
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                rateInfoVo.setRate(rateVo);
                // Get min franchise cost.
                Double minFranchiseCost = cMinCharge * (1 + serviceMarkupRate / 100);
                minFranchiseCost = MathUtils.round3Decimal(minFranchiseCost);
                rateInfoVo.setMinFranchiseCost(minFranchiseCost);
                // Get min customer cost.
                Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), minFranchiseCost, ncMinCharge);
                Double minCustomerCost = resultMap.get("ncRate");
                minCustomerCost = MathUtils.round3Decimal(minCustomerCost);
                rateInfoVo.setMinCustomerCost(minCustomerCost);
                rateVo.setMinCharge(minCustomerCost);
                // Get min margin.
                rateInfoVo.setMinMargin(MathUtils.round3Decimal(rateInfoVo.getMinCustomerCost() - rateInfoVo.getMinFranchiseCost()));
                // Get min percent.
                Double percent = rateInfoVo.getMinCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getMinCustomerCost() - rateInfoVo.getMinFranchiseCost()) / rateInfoVo.getMinCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setMinPercent(percent);
                // Get base franchise cost.
                Double baseFranchiseCost = cBaseCharge * (1 + serviceMarkupRate / 100);
                baseFranchiseCost = MathUtils.round3Decimal(baseFranchiseCost);
                rateInfoVo.setBaseFranchiseCost(baseFranchiseCost);
                // Get base customer cost.
                resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), baseFranchiseCost, ncBaseCharge);
                Double baseCustomerCost = resultMap.get("ncRate");
                baseCustomerCost = MathUtils.round3Decimal(baseCustomerCost);
                rateInfoVo.setBaseCustomerCost(baseCustomerCost);
                rateVo.setBaseCharge(baseCustomerCost);
                // Get base margin.
                rateInfoVo.setBaseMargin(MathUtils.round3Decimal(rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()));
                // Get base percent.
                percent = rateInfoVo.getBaseCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()) / rateInfoVo.getBaseCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setBasePercent(percent);
                // Get per kg franchise cost.
                Double kgFranchiseCost = cPerKg * (1 + serviceMarkupRate / 100);
                kgFranchiseCost = MathUtils.round3Decimal(kgFranchiseCost);
                rateInfoVo.setKgFranchiseCost(kgFranchiseCost);
                // Get per kg customer cost.
                resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), kgFranchiseCost, ncPerKg);
                Double kgCustomerCost = resultMap.get("ncRate");
                kgCustomerCost = MathUtils.round3Decimal(kgCustomerCost);
                rateInfoVo.setKgCustomerCost(kgCustomerCost);
                rateVo.setPerKg(kgCustomerCost);
                // Get per kg margin.
                rateInfoVo.setKgMargin(MathUtils.round3Decimal(rateInfoVo.getKgCustomerCost() - rateInfoVo.getKgFranchiseCost()));
                // Get per kg percent.
                percent = rateInfoVo.getKgCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getKgCustomerCost() - rateInfoVo.getKgFranchiseCost()) / rateInfoVo.getKgCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setKgPercent(percent);
                // Get column name.
                CarrierZoneVo zoneFilter = new CarrierZoneVo();
                zoneFilter.setCarrier(3L);
                zoneFilter.setZoneCode(column.getColumnName());
                CarrierZoneVo carrierZoneVo = carrierZoneDao.getZoneByCarrierAndCode(zoneFilter);
                if (carrierZoneVo != null) {
                    column.setColumnName(carrierZoneVo.getZoneName() + " - " + column.getColumnName());
                    key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                    map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
                }
            }
            // Detect rows and columns used.
            List<RateSheetRowVo> rowVos = cRowVos;
            if (rowVos == null || rowVos.size() == 0) {
                rowVos = ncRowVos;
            }
            List<RateSheetColumnVo> columnVos = cColumnVos;
            if (cColumnVos == null || cColumnVos.size() == 0) {
                columnVos = ncColumnVos;
            }
            columnVos.removeAll(removeColumns);
            // Create new rate sheet info model.
            RateSheetInfoModel rateSheetInfo = new RateSheetInfoModel();
            rateSheetInfo.setRows(ModelUtils.createListModelFromVo(rowVos, RateSheetRowModel.class));
            rateSheetInfo.setColumns(ModelUtils.createListModelFromVo(columnVos, RateSheetColumnModel.class));
            rateSheetInfo.setData(map);
            return rateSheetInfo;
        }
        return null;
    }

    protected RateSheetInfoModel loadStarTrackRateSheet(Long sheetId, Long ncSheetId, Double serviceMarkupRate, ViewRateSheetRequestVo rateSheetRequestVo) throws Exception {
        // Not load rate sheet if it's id is 0.
        if (sheetId != 0) {
            String key;
            // Load carrier rate sheet rows.
            RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
            List<RateSheetRowVo> cRowVos = rateSheetRowDao.selectBySheetId(sheetId);
            // Remove unnecessary carrier rows.
            List<RateSheetRowVo> showRowVos = new ArrayList<RateSheetRowVo>();
            String rowName;
            for (RateSheetRowVo row : cRowVos) {
                if (row.getIsChar()) {
                    rowName = row.getCharRowName();
                } else {
                    if (row.getRowName() != 0) {
                        rowName = row.getCharRowName();
                    } else {
                        rowName = String.valueOf(row.getRowName());
                    }
                }
                if (rateSheetRequestVo.getColumnName().equalsIgnoreCase(rowName) || "A".equalsIgnoreCase(rowName) || "D".equalsIgnoreCase(rowName)) {
                    showRowVos.add(row);
                }
            }
            cRowVos = showRowVos;
            Map<String, RateSheetRowVo> cRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : cRowVos) {
                key = String.valueOf(rowVo.getRowId());
                cRowMap.put(key, rowVo);
            }
            // Load carrier rate sheet columns.
            RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
            List<RateSheetColumnVo> cColumnVos = rateSheetColumnDao.selectBySheetId(sheetId);
            Map<String, RateSheetColumnVo> cColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : cColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                cColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of carrier rate.
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            List<RateSheetDetailVo> cDetailVos = rateSheetDetailDao.selectBySheetId(sheetId);
            // Load non carrier rate sheet rows.
            List<RateSheetRowVo> ncRowVos = rateSheetRowDao.selectBySheetId(ncSheetId);
            // Remove unnecessary non carrier rows.
            showRowVos = new ArrayList<RateSheetRowVo>();
            for (RateSheetRowVo row : ncRowVos) {
                if (row.getIsChar()) {
                    rowName = row.getCharRowName();
                } else {
                    if (row.getRowName() != 0) {
                        rowName = row.getCharRowName();
                    } else {
                        rowName = String.valueOf(row.getRowName());
                    }
                }
                if (rateSheetRequestVo.getColumnName().equalsIgnoreCase(rowName) || "A".equalsIgnoreCase(rowName) || "D".equalsIgnoreCase(rowName)) {
                    showRowVos.add(row);
                }
            }
            ncRowVos = showRowVos;
            Map<String, RateSheetRowVo> ncRowMap = new HashMap<String, RateSheetRowVo>();
            for (RateSheetRowVo rowVo : ncRowVos) {
                key = String.valueOf(rowVo.getRowId());
                ncRowMap.put(key, rowVo);
            }
            // Load non carrier rate sheet columns.
            List<RateSheetColumnVo> ncColumnVos = rateSheetColumnDao.selectBySheetId(ncSheetId);
            Map<String, RateSheetColumnVo> ncColumnMap = new HashMap<String, RateSheetColumnVo>();
            for (RateSheetColumnVo columnVo : ncColumnVos) {
                key = String.valueOf(columnVo.getColumnId());
                ncColumnMap.put(key, columnVo);
            }
            // Load rate sheet details of non carrier.
            List<RateSheetDetailVo> ncDetailVos = rateSheetDetailDao.selectBySheetId(ncSheetId);
            // Create 2 map for carrier rate and none carrier rate.
            Map<String, RateSheetDetailVo> cRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                if (row == null || column == null) {
                    continue;
                }
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                cRateMap.put(key, detailVo);
            }
            Map<String, RateSheetDetailVo> ncRateMap = new HashMap<String, RateSheetDetailVo>();
            for (RateSheetDetailVo detailVo : ncDetailVos) {
                RateSheetRowVo row = ncRowMap.get(String.valueOf(detailVo.getRowId()));
                RateSheetColumnVo column = ncColumnMap.get(String.valueOf(detailVo.getColumnId()));
                if (row == null || column == null) {
                    continue;
                }
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ncRateMap.put(key, detailVo);
            }
            // Create new rate sheet map to keep rate sheet result.
            Map<String, RateSheetDetailInfoModel> map = new HashMap<String, RateSheetDetailInfoModel>();
            // Recalculate charge of each cell.
            RateSheetDetailVo rateVo = null;
            RateSheetDetailInfoVo rateInfoVo = null;
            List<RateSheetColumnVo> removeColumns = new ArrayList<RateSheetColumnVo>();
            CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
            for (RateSheetDetailVo detailVo : cDetailVos) {
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                RateSheetRowVo row = cRowMap.get(String.valueOf(detailVo.getRowId()));
                if (row == null) {
                    continue;
                }
                RateSheetColumnVo column = cColumnMap.get(String.valueOf(detailVo.getColumnId()));
                key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                ViewRateSheetBaseRateVo baseRateVo = findBaseRate(rateSheetRequestVo, row.getRowName());
                // Get zone rate.
                Double zoneRate = baseRateVo.getZoneRates() == null ? null : baseRateVo.getZoneRates().get(column.getColumnName());
                // Get base rate.
                Double baseRate = zoneRate != null ? zoneRate : baseRateVo.getRate();
                if (StringUtils.isBlank(column.getColumnName())) {
                    // Get extra info.
                    RateSheetDetailVo extraFilter = new RateSheetDetailVo();
                    extraFilter.setSheetId(sheetId);
                    extraFilter.setColumnId(column.getColumnId());
                    ExtraRateSheetDetailVo extraDetailVo = rateSheetDetailDao.getExtraRateSheetDetail(extraFilter);
                    if (extraDetailVo == null) {
                        // Don't show the invalid column.
                        removeColumns.add(column);
                        continue;
                    }
                    // Update column name.
                    column.setColumnName("ALL TO ALL " + extraDetailVo.getRowName() + " Kg");
                    // Recalculate value of base charge.
                    Double cValue = extraDetailVo.getValue();
                    cValue = MathUtils.round3Decimal(cValue);
                    Double ncValue = 0.0;
                    rateVo = new RateSheetDetailVo();
                    // Create new rate info object.
                    rateInfoVo = new RateSheetDetailInfoVo();
                    rateInfoVo.setRate(rateVo);
                    // Get base franchise cost.
                    Double franchiseCost = cValue * (1 + serviceMarkupRate / 100);
                    franchiseCost = MathUtils.round3Decimal(franchiseCost);
                    rateInfoVo.setBaseFranchiseCost(franchiseCost);
                    // Get base customer cost.
                    Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), franchiseCost, ncValue);
                    Double customerCost = resultMap.get("ncRate");
                    customerCost = MathUtils.round3Decimal(customerCost);
                    rateInfoVo.setBaseCustomerCost(customerCost);
                    rateVo.setBaseCharge(customerCost);
                    // Get base margin.
                    rateInfoVo.setBaseMargin(MathUtils.round3Decimal(rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()));
                    // Get base percent.
                    Double percent = rateInfoVo.getBaseCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()) / rateInfoVo.getBaseCustomerCost();
                    percent = MathUtils.round3Decimal(percent);
                    rateInfoVo.setBasePercent(percent);
                    key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                    map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
                    continue;
                }
                RateSheetDetailVo cRateVo = cRateMap.get(key);
                cRateVo = (cRateVo == null) ? new RateSheetDetailVo() : cRateVo;
                RateSheetDetailVo ncRateVo = ncRateMap.get(key);
                ncRateVo = (ncRateVo == null) ? new RateSheetDetailVo() : ncRateVo;
                rateVo = (baseRateVo.getRateType() == 0) ? ncRateVo : cRateVo;
                // Recalculate value of min charge.
                Double cMinCharge = cRateVo.getMinCharge() == null ? 0.0 : cRateVo.getMinCharge();
                cMinCharge = MathUtils.round3Decimal(cMinCharge);
                Double ncMinCharge = ncRateVo.getMinCharge() == null ? 0.0 : ncRateVo.getMinCharge();
                ncMinCharge = MathUtils.round3Decimal(ncMinCharge);
                // Recalculate value of base charge.
                Double cBaseCharge = cRateVo.getBaseCharge() == null ? 0.0 : cRateVo.getBaseCharge();
                cBaseCharge = MathUtils.round3Decimal(cBaseCharge);
                Double ncBaseCharge = ncRateVo.getBaseCharge() == null ? 0.0 : ncRateVo.getBaseCharge();
                ncBaseCharge = MathUtils.round3Decimal(ncBaseCharge);
                // Recalculate value of per kg.
                Double cPerKg = cRateVo.getPerKg() == null ? 0.0 : cRateVo.getPerKg();
                cPerKg = MathUtils.round3Decimal(cPerKg);
                Double ncPerKg = ncRateVo.getPerKg() == null ? 0.0 : ncRateVo.getPerKg();
                ncPerKg = MathUtils.round3Decimal(ncPerKg);
                // Create new rate info object.
                rateInfoVo = new RateSheetDetailInfoVo();
                rateInfoVo.setRate(rateVo);
                // Get min franchise cost.
                Double minFranchiseCost = cMinCharge * (1 + serviceMarkupRate / 100);
                minFranchiseCost = MathUtils.round3Decimal(minFranchiseCost);
                rateInfoVo.setMinFranchiseCost(minFranchiseCost);
                // Get min customer cost.
                Map<String, Double> resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), minFranchiseCost, ncMinCharge);
                Double minCustomerCost = resultMap.get("ncRate");
                minCustomerCost = MathUtils.round3Decimal(minCustomerCost);
                rateInfoVo.setMinCustomerCost(minCustomerCost);
                rateVo.setMinCharge(minCustomerCost);
                // Get min margin.
                rateInfoVo.setMinMargin(MathUtils.round3Decimal(rateInfoVo.getMinCustomerCost() - rateInfoVo.getMinFranchiseCost()));
                // Get min percent.
                Double percent = rateInfoVo.getMinCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getMinCustomerCost() - rateInfoVo.getMinFranchiseCost()) / rateInfoVo.getMinCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setMinPercent(percent);
                // Get base franchise cost.
                Double baseFranchiseCost = cBaseCharge * (1 + serviceMarkupRate / 100);
                baseFranchiseCost = MathUtils.round3Decimal(baseFranchiseCost);
                rateInfoVo.setBaseFranchiseCost(baseFranchiseCost);
                // Get base customer cost.
                resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), baseFranchiseCost, ncBaseCharge);
                Double baseCustomerCost = resultMap.get("ncRate");
                baseFranchiseCost = MathUtils.round3Decimal(baseCustomerCost);
                rateInfoVo.setBaseCustomerCost(baseCustomerCost);
                rateVo.setBaseCharge(baseCustomerCost);
                // Get base margin.
                rateInfoVo.setBaseMargin(MathUtils.round3Decimal(rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()));
                // Get base percent.
                percent = rateInfoVo.getBaseCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getBaseCustomerCost() - rateInfoVo.getBaseFranchiseCost()) / rateInfoVo.getBaseCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setBasePercent(percent);
                // Get per kg franchise cost.
                Double kgFranchiseCost = cPerKg * (1 + serviceMarkupRate / 100);
                kgFranchiseCost = MathUtils.round3Decimal(kgFranchiseCost);
                rateInfoVo.setKgFranchiseCost(kgFranchiseCost);
                // Get per kg customer cost.
                resultMap = ShipmentUtils.calculateBaseCharge(baseRateVo.getRateType(), baseRate, rateSheetRequestVo.getMinimumBaseCharge(), row.getRowName(), kgFranchiseCost, ncPerKg);
                Double kgCustomerCost = resultMap.get("ncRate");
                kgCustomerCost = MathUtils.round3Decimal(kgCustomerCost);
                rateInfoVo.setKgCustomerCost(kgCustomerCost);
                rateVo.setPerKg(kgCustomerCost);
                // Get per kg margin.
                rateInfoVo.setKgMargin(MathUtils.round3Decimal(rateInfoVo.getKgCustomerCost() - rateInfoVo.getKgFranchiseCost()));
                // Get per kg percent.
                percent = rateInfoVo.getKgCustomerCost() == 0 ? 0.0 : 100 * (rateInfoVo.getKgCustomerCost() - rateInfoVo.getKgFranchiseCost()) / rateInfoVo.getKgCustomerCost();
                percent = MathUtils.round3Decimal(percent);
                rateInfoVo.setKgPercent(percent);
                // Get column name.
                CarrierZoneVo zoneFilter = new CarrierZoneVo();
                zoneFilter.setCarrier(72L);
                zoneFilter.setZoneCode(column.getColumnName());
                CarrierZoneVo carrierZoneVo = carrierZoneDao.getZoneByCarrierAndCode(zoneFilter);
                if (carrierZoneVo != null) {
                    column.setColumnName(carrierZoneVo.getZoneName() + " - " + column.getColumnName());
                    key = row.getRowName() + row.getCharRowName() + column.getColumnName();
                    map.put(key, ModelUtils.createModelFromVo(rateInfoVo, RateSheetDetailInfoModel.class));
                }
            }
            // Detect rows and columns used.
            List<RateSheetRowVo> rowVos = cRowVos;
            if (rowVos == null || rowVos.size() == 0) {
                rowVos = ncRowVos;
            }
            List<RateSheetColumnVo> columnVos = cColumnVos;
            if (cColumnVos == null || cColumnVos.size() == 0) {
                columnVos = ncColumnVos;
            }
            columnVos.removeAll(removeColumns);
            // Create new rate sheet info model.
            RateSheetInfoModel rateSheetInfo = new RateSheetInfoModel();
            rateSheetInfo.setRows(ModelUtils.createListModelFromVo(rowVos, RateSheetRowModel.class));
            rateSheetInfo.setColumns(ModelUtils.createListModelFromVo(columnVos, RateSheetColumnModel.class));
            rateSheetInfo.setData(map);
            return rateSheetInfo;
        }
        return null;
    }

    protected ViewRateSheetBaseRateVo findBaseRate(ViewRateSheetRequestVo rateSheetRequestVo, Double weight) {
        if (rateSheetRequestVo.getBaseRates() == null || rateSheetRequestVo.getBaseRates().size() == 0) {
            ViewRateSheetBaseRateVo baseRateVo = new ViewRateSheetBaseRateVo();
            baseRateVo.setRate(0d);
            baseRateVo.setRateType(1);
            baseRateVo.setZoneRates(null);
            baseRateVo.setWeight(0d);
            return baseRateVo;
        }
        Double find = 0.0;
        ViewRateSheetBaseRateVo foundBaseRate = null;
        for (ViewRateSheetBaseRateVo baseRate : rateSheetRequestVo.getBaseRates()) {
            if (baseRate.getWeight() >= find && baseRate.getWeight() <= weight) {
                foundBaseRate = baseRate;
                find = baseRate.getWeight();
            }
        }
        return foundBaseRate;
    }
}
