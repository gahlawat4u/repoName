package com.gms.xms.workflow.task2.ratesheets.startrack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from Jun 23, 2016 11:11:37 AM
 * <p>
 * Author huynd
 */
public class ReadRateSheetDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(ReadRateSheetDataTask.class);

    @SuppressWarnings("resource")
    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String filePath = context.getString(Attributes.RATE_SHEET_FILE);
            filePath = filePath.replace("\\", "/").trim();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            String cell = "";
            Integer count = 0, start = 1;
            String key, value;
            RateSheetRowDataModel data;
            RateSheetDataModel dataSheet = new RateSheetDataModel();
            List<RateSheetRowDataModel> rowData = new LinkedList<RateSheetRowDataModel>();
            while ((line = br.readLine()) != null) {
                if (count < start) {
                    count++;
                    continue;
                }
                // use comma as separator
                String[] rowArray = line.split(",");

                data = new RateSheetRowDataModel();
                data.setRowData(new HashMap<String, String>());
                if (rowArray != null) {
                    for (int i = 0; i < rowArray.length; i++) {
                        cell = rowArray[i];
                        key = String.valueOf(i);
                        if (cell != null) {
                            value = cell;
                            data.getRowData().put(key, value);
                        }
                    }
                    rowData.add(data);
                }
                count++;
            }
            dataSheet.setRowData(rowData);
            context.put(Attributes.RATE_SHEET_DATA, dataSheet);
            // System.out.println(dataSheet);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
