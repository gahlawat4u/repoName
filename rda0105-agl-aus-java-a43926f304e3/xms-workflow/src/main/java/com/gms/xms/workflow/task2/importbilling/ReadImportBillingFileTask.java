package com.gms.xms.workflow.task2.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from May 24, 2016 3:37:20 PM
 * <p>
 * Author huynd
 */
public class ReadImportBillingFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(ReadImportBillingFileTask.class);

    @SuppressWarnings("resource")
    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String filePath = context.getString(Attributes.BILLING_FILE);
            Integer serviceId = context.getInt(Attributes.SERVICE_ID);
            Boolean hasHeader = (context.getBoolean(Attributes.HAS_HEADER) == null) ? false : context.getBoolean(Attributes.HAS_HEADER);
            Integer start = 0;
            Integer sheetAt = 0;
            if (hasHeader) {
                if (serviceId == 52) {
                    start = 3;
                } else {
                    start = 1;
                }
            }
            if (serviceId == 52) {
                sheetAt = 1;
            }
            String ext = context.getString(Attributes.EXTENSION);
            filePath = filePath.replace("\\", "/").trim();
            String key, value;
            BillingRowDataModel data;
            BillingDataModel dataSheet = new BillingDataModel();
            List<BillingRowDataModel> rowData = new LinkedList<BillingRowDataModel>();

            if ("xls".equalsIgnoreCase(ext)) {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(sheetAt);
                HSSFRow row;
                HSSFCell cell;

                int rows = sheet.getPhysicalNumberOfRows();
                int columns;

                for (int j = start; j < rows; j++) {
                    row = sheet.getRow(j);
                    columns = sheet.getRow(j).getPhysicalNumberOfCells();
                    data = new BillingRowDataModel();
                    data.setCellData(new LinkedHashMap<String, String>());
                    if (row != null) {
                        for (int i = 0; i < columns; i++) {
                            cell = row.getCell(i);
                            key = String.valueOf(i);
                            if (cell != null) {
                                value = cell.toString();
                                data.getCellData().put(key, value);
                            }
                        }
                        rowData.add(data);
                    }
                }
            } else if ("csv".equalsIgnoreCase(ext)) {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = "";
                String cell = "";
                Integer count = 0;
                while ((line = br.readLine()) != null) {
                    if (count < start) {
                        count++;
                        continue;
                    }
                    // use comma as separator
                    String[] rowArray = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                    data = new BillingRowDataModel();
                    data.setCellData(new LinkedHashMap<String, String>());
                    if (rowArray != null) {
                        for (int i = 0; i < rowArray.length; i++) {
                            cell = rowArray[i].replace("\"", "");
                            key = String.valueOf(i);
                            if (cell != null) {
                                value = cell;
                                data.getCellData().put(key, value);
                            }
                        }
                        rowData.add(data);
                    }
                    count++;
                }
            } else {
                throw new Exception("Invalid import file.");
            }
            dataSheet.setRowData(rowData);
            context.put(Attributes.BILLING_DATA_FILE, dataSheet);
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
