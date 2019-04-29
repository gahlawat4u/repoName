package com.gms.xms.workflow.task2.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 9, 2016 5:19:53 PM
 * <p>
 * Author huynd
 */
public class PrepareRateSheetColumnTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareRateSheetColumnTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            RateSheetDataModel dataSheet = context.get(Attributes.RATE_SHEET_DATA);
            List<RateSheetRowDataModel> rowData = dataSheet.getRowData();
            List<RateSheetColumnModel> columnDataList = new LinkedList<RateSheetColumnModel>();
            RateSheetColumnModel columnData;
            RateSheetRowDataModel columnTmp = rowData.get(0);
            String columnName;
            Map<String, String> colMap = columnTmp.getRowData();
            for (Map.Entry<String, String> entry : colMap.entrySet()) {
                columnName = entry.toString().split("=")[1];
                columnData = new RateSheetColumnModel();
                columnData.setColumnName(columnName);
                columnDataList.add(columnData);
            }
            context.put(Attributes.RATE_SHEET_COLUMN_DATA, columnDataList);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
