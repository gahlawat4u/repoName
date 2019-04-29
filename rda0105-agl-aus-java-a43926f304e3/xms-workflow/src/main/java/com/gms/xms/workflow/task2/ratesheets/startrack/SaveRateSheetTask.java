package com.gms.xms.workflow.task2.ratesheets.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.persistence.service.importratesheet.IImportRateSheetService;
import com.gms.xms.persistence.service.importratesheet.ImportRateSheetServiceImp;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 23, 2016 3:39:42 PM
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
            List<RateSheetDetailModel> detailDataList = context.get(Attributes.RATE_SHEET_DETAIL_DATA);
            IImportRateSheetService rateSheetService = new ImportRateSheetServiceImp();
            rateSheetService.saveImportRateSheet(addInfo, rateSheet, columnDataList, rowDataList, detailDataList);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
