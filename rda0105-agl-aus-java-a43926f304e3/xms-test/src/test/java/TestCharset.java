import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gms.xms.model.admin.ratesheets.RateSheetDataModel;
import com.gms.xms.model.admin.ratesheets.RateSheetRowDataModel;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class TestCharset {
    public static void main(String[] args) throws IOException{
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.setProperty("file.encoding", "Latin-1");
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());
        
        
        
        
        String excelFilePath = "/home/rakesh/Desktop/Toll_ipec_rate.xls";
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFilePath));
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
                        
                        
                        System.out.print(",");
                        
                        columnCount++;
                    }
                    
                }
                System.out.println(); 
        }
        }
    }
    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
}

