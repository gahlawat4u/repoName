package com.gms.xms.workflow.task2.ratesheets;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 6, 2016 4:35:42 PM
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
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows = sheet.getPhysicalNumberOfRows();
            int columns;
            String key, value;
            RateSheetRowDataModel data;
            RateSheetDataModel dataSheet = new RateSheetDataModel();
            List<RateSheetRowDataModel> rowData = new LinkedList<RateSheetRowDataModel>();
            Boolean isEmptyFirstCell = true;
            if (sheet != null && sheet.getRow(0) != null && sheet.getRow(0).getCell(0) != null) {
                isEmptyFirstCell = false;
            }
            for (int j = 0; j < rows; j++) {
                row = sheet.getRow(j);
                columns = sheet.getRow(j).getPhysicalNumberOfCells();
                if (isEmptyFirstCell && j == 0) {
                    columns++;
                }
                data = new RateSheetRowDataModel();
                data.setRowData(new LinkedHashMap<String, String>());
                if (row != null) {
                    int columnCount = 0;
                    for (int i = 0; i < columns; i++) {
                        cell = row.getCell(i);
                        key = String.valueOf(i);
                        if (cell != null && !StringUtils.isBlank(cell.toString())) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            value = cell.toString();
                            data.getRowData().put(key, value);
                            columnCount++;
                        }
                    }
                    if (columnCount > 0) {
                        rowData.add(data);
                    }
                }
            }
            // System.out.println(GsonUtils.toGson(rowData, new TypeToken<List<RateSheetRowDataModel>>() {
            // }.getType()));
            dataSheet.setRowData(rowData);
            context.put(Attributes.RATE_SHEET_DATA, dataSheet);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
