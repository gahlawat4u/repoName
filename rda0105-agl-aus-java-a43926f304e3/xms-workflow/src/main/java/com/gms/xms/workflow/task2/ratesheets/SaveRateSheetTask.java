package com.gms.xms.workflow.task2.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ratesheet.ImportRateSheetDao;
import com.gms.xms.txndb.vo.RateSheetColumnVo;
import com.gms.xms.txndb.vo.RateSheetDetailVo;
import com.gms.xms.txndb.vo.RateSheetRowVo;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 9, 2016 5:19:27 PM
 * <p>
 * Author huynd
 */
public class SaveRateSheetTask implements Task2 {
    private static final Log log = LogFactory.getLog(SaveRateSheetTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            RateSheetModel rateSheet = context.get(Attributes.RATE_SHEET_MODEL);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            List<RateSheetColumnModel> columnDataList = context.get(Attributes.RATE_SHEET_COLUMN_DATA);
            List<RateSheetRowModel> rowDataList = context.get(Attributes.RATE_SHEET_ROW_DATA);
            Integer cells = columnDataList.size() * rowDataList.size();
            rateSheet.setCells(String.valueOf(cells));
            RateSheetVo rateSheetVo = ModelUtils.createVoFromModel(rateSheet, RateSheetVo.class);
            ImportRateSheetDao rateSheetDao = new ImportRateSheetDao();
            // Save rate sheet.
            rateSheetDao.insertRateSheet(addInfo, rateSheetVo);
            Long sheetId = rateSheetVo.getSheetId();
            // System.out.println("Sheet id = " + rateSheetVo.getSheetId());
            // Save rate sheet colum.
            List<RateSheetColumnVo> columnDataListVo = ModelUtils.createListVoFromModel(columnDataList, RateSheetColumnVo.class);
            List<String> columnIdList = new LinkedList<String>();
            for (RateSheetColumnVo column : columnDataListVo) {
                RateSheetColumnVo columnVo = new RateSheetColumnVo();
                columnVo.setSheetId(sheetId);
                columnVo.setColumnName(column.getColumnName());
                rateSheetDao.insertDataCols(addInfo, columnVo);
                columnIdList.add(columnVo.getColumnId().toString());
            }
            // Save rate sheet row.
            List<RateSheetRowVo> rowDataListVo = ModelUtils.createListVoFromModel(rowDataList, RateSheetRowVo.class);
            List<String> rowIdList = new LinkedList<String>();
            for (RateSheetRowVo row : rowDataListVo) {
                RateSheetRowVo rowVo = new RateSheetRowVo();
                rowVo.setSheetId(sheetId);
                if (row.getRowName() != null) {
                    rowVo.setRowName(row.getRowName());
                } else {
                    rowVo.setCharRowName(row.getCharRowName());
                }
                rateSheetDao.insertDataRows(addInfo, rowVo);
                rowIdList.add(rowVo.getRowId().toString());
            }
            // Save rate sheet detail.
            List<RateSheetDetailModel> detailDataList = context.get(Attributes.RATE_SHEET_DETAIL_DATA);
            List<RateSheetDetailVo> detailDataListVo = ModelUtils.createListVoFromModel(detailDataList, RateSheetDetailVo.class);
            int count = 0;
            for (int i = 0; i < rowIdList.size(); i++) {
              
            	for (int j = 0; j < columnIdList.size(); j++) {
                  
            			System.out.println("Row : "+i+" Column No- "+j);
            	
                	
                	RateSheetDetailVo detailVo = new RateSheetDetailVo();
                    detailVo.setSheetId(sheetId);
                    detailVo.setRowId(Long.valueOf(rowIdList.get(i)));
                    detailVo.setColumnId(Long.valueOf(columnIdList.get(j)));
                    detailVo.setValue(detailDataListVo.get(count).getValue());
                    rateSheetDao.insertDataDetails(addInfo, detailVo);
                    count++;
                }
            	System.out.println("---------Row- "+i+" Completed --------------");
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
