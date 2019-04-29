package com.gms.xms.workflow.task2.ratesheets.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.StarTrackConstants;
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
 * Posted from Jun 23, 2016 11:59:44 AM
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
            List<String> checkCol = new LinkedList<String>();
            RateSheetColumnModel columnData;
            RateSheetRowDataModel columnTmp;
            Map<String, String> rowMap;
            String colName = "";
            for (int i = 0; i < rowData.size(); i++) {
                columnTmp = new RateSheetRowDataModel();
                columnTmp = rowData.get(i);
                rowMap = columnTmp.getRowData();
                colName = rowMap.get(StarTrackConstants.DESTINATION_ZONE);
                if (!checkCol.contains(colName)) {
                    columnData = new RateSheetColumnModel();
                    columnData.setColumnName(colName);
                    columnData.setZoneName(rowMap.get(StarTrackConstants.DESTINATION_ZONE_NAME));
                    columnDataList.add(columnData);
                    checkCol.add(colName);
                }
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
