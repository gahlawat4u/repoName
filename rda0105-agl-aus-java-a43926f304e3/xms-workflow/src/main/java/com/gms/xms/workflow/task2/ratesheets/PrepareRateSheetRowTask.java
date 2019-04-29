package com.gms.xms.workflow.task2.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 9, 2016 5:19:47 PM
 * <p>
 * Author huynd
 */
public class PrepareRateSheetRowTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareRateSheetRowTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            RateSheetDataModel dataSheet = context.get(Attributes.RATE_SHEET_DATA);
            List<RateSheetRowDataModel> rowData = dataSheet.getRowData();
            List<RateSheetRowModel> rowDataList = new LinkedList<RateSheetRowModel>();
            RateSheetRowModel rowDataModel;
            RateSheetRowDataModel rowDataTmp;
            Map<String, String> rowMap;
            String rowName;
            for (int i = 1; i < rowData.size(); i++) {
                rowDataTmp = new RateSheetRowDataModel();
                rowDataTmp = rowData.get(i);
                rowMap = rowDataTmp.getRowData();
                rowName = rowMap.entrySet().iterator().next().toString().split("=")[1];
                rowDataModel = new RateSheetRowModel();
                if (!isNumeric(rowName)) {
                    rowDataModel.setRowName("");
                    rowDataModel.setCharRowName(rowName);
                } else {
                    rowDataModel.setRowName(rowName);
                    rowDataModel.setCharRowName("");
                }
                rowDataList.add(rowDataModel);
            }
            context.put(Attributes.RATE_SHEET_ROW_DATA, rowDataList);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

}
