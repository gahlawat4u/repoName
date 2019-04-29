package com.gms.xms.workflow.task2.importbilling.othercarrier;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from Jul 23, 2016 9:22:50 AM
 * <p>
 * Author huynd
 */
public class ReadImportBillingFileOtherCarrierTask implements Task2 {
    private static final Log log = LogFactory.getLog(ReadImportBillingFileOtherCarrierTask.class);

    @SuppressWarnings("resource")
    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String filePath = context.getString(Attributes.BILLING_FILE);
            Boolean hasHeader = (context.getBoolean(Attributes.HAS_HEADER) == null) ? false : context.getBoolean(Attributes.HAS_HEADER);
            Integer start = (hasHeader) ? 1 : 0;
            Integer sheetAt = 0;
            String ext = context.getString(Attributes.EXTENSION);
            filePath = filePath.replace("\\", "/").trim();
            String key, value;
            BillingRowDataModel data;
            BillingDataModel dataSheet = new BillingDataModel();
            List<BillingRowDataModel> rowData = new ArrayList<BillingRowDataModel>();
            ImportBillingFieldsModel fields = context.get(Attributes.FIELDS);
            Integer countFields = fields.getFieldName().size();

            if ("xls".equalsIgnoreCase(ext)) {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(sheetAt);
                HSSFRow row;
                HSSFCell cell;

                int rows = sheet.getPhysicalNumberOfRows();

                for (int j = start; j < rows; j++) {
                    row = sheet.getRow(j);
                    data = new BillingRowDataModel();
                    data.setCellData(new HashMap<String, String>());
                    if (row != null) {
                        for (int i = 0; i < countFields; i++) {
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
                    String[] rowArray = line.split(",");

                    data = new BillingRowDataModel();
                    data.setCellData(new HashMap<String, String>());
                    if (rowArray != null) {
                        for (int i = 0; i < countFields; i++) {
                            if (i >= rowArray.length) {
                                cell = "";
                            } else {
                                cell = rowArray[i];
                            }
                            key = String.valueOf(i);
                            if (cell != null) {
                                value = (cell.contains("$")) ? cell.replace("$", "") : cell;
                                // value = value.replace("-", "");
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
