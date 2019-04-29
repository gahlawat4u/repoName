package com.gms.xms.workflow.task2.ratesheets.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.StarTrackConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 9, 2016 5:19:43 PM
 * <p>
 * Author huynd
 */
public class PrepareRateSheetDetailTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareRateSheetDetailTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            RateSheetDataModel dataSheet = context.get(Attributes.RATE_SHEET_DATA);
            List<RateSheetRowDataModel> rowData = dataSheet.getRowData();
            List<RateSheetDetailModel> detailDataList = new LinkedList<RateSheetDetailModel>();
            RateSheetDetailModel detailData;
            RateSheetRowDataModel rowDataTmp;
            Map<String, String> detailMap;
            for (int i = 0; i < rowData.size(); i++) {
                rowDataTmp = new RateSheetRowDataModel();
                rowDataTmp = rowData.get(i);
                detailMap = rowDataTmp.getRowData();
                String minCharge = "", baseCharge = "", perKg = "";
                minCharge = detailMap.get(StarTrackConstants.MIN_CHARGE);
                baseCharge = detailMap.get(StarTrackConstants.BASIC_CHARGE);
                perKg = detailMap.get(StarTrackConstants.KG_RATE);
                detailData = new RateSheetDetailModel();
                detailData.setValue("0");
                detailData.setMinCharge(minCharge);
                detailData.setBaseCharge(baseCharge);
                detailData.setPerKg(perKg);
                detailData.setColumnName(detailMap.get(StarTrackConstants.DESTINATION_ZONE));
                detailData.setCharRowName(detailMap.get(StarTrackConstants.ORIGIN_ZONE));
                detailDataList.add(detailData);
            }
            context.put(Attributes.RATE_SHEET_DETAIL_DATA, detailDataList);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
