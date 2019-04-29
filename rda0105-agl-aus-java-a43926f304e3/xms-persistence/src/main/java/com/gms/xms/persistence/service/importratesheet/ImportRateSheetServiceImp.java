package com.gms.xms.persistence.service.importratesheet;

import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.persistence.dao.ratesheet.ImportRateSheetDao;
import com.gms.xms.txndb.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 23, 2016 4:30:37 PM
 * <p>
 * Author huynd
 */
public class ImportRateSheetServiceImp implements IImportRateSheetService {
    private static final Log logger = LogFactory.getLog(ImportRateSheetServiceImp.class);

    @Override
    public void saveImportRateSheet(Map<String, String> context, RateSheetModel rateSheet, List<RateSheetColumnModel> columnDataList, List<RateSheetRowModel> rowDataList, List<RateSheetDetailModel> detailDataList) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            ImportRateSheetDao rateSheetDao = new ImportRateSheetDao(sessionClient);
            CarrierZoneDao carrierZoneDao = new CarrierZoneDao(sessionClient);
            Long carrier = 72L;

            Integer cells = columnDataList.size() * rowDataList.size();
            rateSheet.setCells(String.valueOf(cells));
            RateSheetVo rateSheetVo = ModelUtils.createVoFromModel(rateSheet, RateSheetVo.class);

            // Save rate sheet.
            rateSheetDao.insertRateSheet(context, rateSheetVo);
            Long sheetId = rateSheetVo.getSheetId();

            // Save rate sheet column.
            List<RateSheetColumnVo> columnDataListVo = ModelUtils.createListVoFromModel(columnDataList, RateSheetColumnVo.class);
            RateSheetColumnVo columnVo;
            Long columnId;
            Map<String, Long> columnIdMap = new LinkedHashMap<String, Long>();
            for (RateSheetColumnVo column : columnDataListVo) {
                columnVo = new RateSheetColumnVo();
                columnVo.setSheetId(sheetId);
                columnVo.setColumnName(column.getColumnName());
                columnId = rateSheetDao.selectRateSheetColumnId(columnVo);
                if (columnId == null) {
                    rateSheetDao.insertDataCols(context, columnVo);
                    columnId = columnVo.getColumnId();
                    columnIdMap.put(column.getColumnName(), columnId);
                }
                // Check and save carrier zone
                checkAndSaveCarrierZone(context, column.getColumnName(), column.getZoneName(), carrier, carrierZoneDao, false);
            }
            // Save rate sheet row.
            List<RateSheetRowVo> rowDataListVo = ModelUtils.createListVoFromModel(rowDataList, RateSheetRowVo.class);
            RateSheetRowVo rowVo;
            Long rowId;
            Map<String, Long> rowIdMap = new LinkedHashMap<String, Long>();
            for (RateSheetRowVo row : rowDataListVo) {
                rowVo = new RateSheetRowVo();
                rowVo.setSheetId(sheetId);
                rowVo.setCharRowName(row.getCharRowName());
                rowId = rateSheetDao.selectRateSheetRowId(rowVo);
                if (rowId == null) {
                    rateSheetDao.insertDataRows(context, rowVo);
                    rowId = rowVo.getRowId();
                    rowIdMap.put(row.getCharRowName(), rowId);
                }
                // Check and save carrier zone
                checkAndSaveCarrierZone(context, row.getCharRowName(), row.getZoneName(), carrier, carrierZoneDao, true);
            }
            // Save rate sheet detail.
            List<RateSheetDetailVo> detailDataListVo = ModelUtils.createListVoFromModel(detailDataList, RateSheetDetailVo.class);
            for (RateSheetDetailVo rateSheetDetail : detailDataListVo) {
                rateSheetDetail.setSheetId(sheetId);
                rateSheetDetail.setRowId(rowIdMap.get(rateSheetDetail.getCharRowName()));

                if(rateSheetDetail.getColumnName() != null){
                   // System.out.println(rateSheetDetail.getColumnName()+"::::");
                	rateSheetDetail.setColumnId(columnIdMap.get(rateSheetDetail.getColumnName()));
                    rateSheetDao.insertDataDetails(context, rateSheetDetail);
                	
                }
            }

            // Update number of cells's rate sheet
            cells = rowIdMap.size() * columnIdMap.size();
            rateSheetVo.setCells(cells);
            rateSheetDao.updateCells(context, rateSheetVo);

            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            logger.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    protected void checkAndSaveCarrierZone(Map<String, String> context, String zoneCode, String zoneName, Long carrier, CarrierZoneDao carrierZoneDao, Boolean isDisplay) throws Exception {
        CarrierZoneVo carrierZoneVo = new CarrierZoneVo();
        carrierZoneVo.setZoneCode(zoneCode);
        carrierZoneVo.setZoneName(zoneName);
        carrierZoneVo.setIsDisplay(isDisplay);
        carrierZoneVo.setCarrier(carrier);
        carrierZoneVo.setStateCode(0);
        carrierZoneVo.setZoneNo("");
        if (carrierZoneDao.checkCarrierZoneStartrack(carrierZoneVo) == 0) {
            carrierZoneDao.saveCarrierZone(context, carrierZoneVo);
        }
    }
}
