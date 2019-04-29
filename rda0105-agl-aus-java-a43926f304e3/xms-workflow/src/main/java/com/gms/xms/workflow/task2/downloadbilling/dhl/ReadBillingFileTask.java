package com.gms.xms.workflow.task2.downloadbilling.dhl;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.admin.downloadbilling.DownloadedFileModel;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.persistence.dao.downloadbilling.DownloadBillingDao;
import com.gms.xms.txndb.vo.admin.downloadbilling.DownloadLogVo;
import com.gms.xms.txndb.vo.admin.downloadbilling.DownloadTestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Posted from Jun 3, 2016 8:38:41 AM
 * <p>
 * Author huynd
 */
public class ReadBillingFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(ReadBillingFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            String key, value;
            BillingRowDataModel data;
            List<BillingDataModel> listBilling = new LinkedList<BillingDataModel>();
            BillingDataModel dataSheet;
            List<BillingRowDataModel> rowData;
            BufferedReader br;
            DownloadedFileModel files = context.get(Attributes.DOWNLOAD_FILE_LIST);
            String fileNamePrefix = context.getString(Attributes.FILE_NAME_PREFIX);
            String fileNameCountryCode = context.getString(Attributes.FILE_NAME_COUNTRY_CODE);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            Map<String, Long> downloadedFile = files.getFiles();
            String fileName = "";
            Long fileSize = 0L;
            DownloadBillingDao downloadBilling = new DownloadBillingDao();
            DownloadTestVo downloadTest;
            DownloadLogVo downloadLog;
            String[] fileDate;
            String importDate;
            for (Map.Entry<String, Long> entry : downloadedFile.entrySet()) {
                String filePath = context.get(Attributes.DOWNLOAD_FILE_PATH);
                filePath = filePath.replace("\\", "/").trim();
                fileName = entry.getKey();
                fileSize = entry.getValue();
                // Save download test
                downloadTest = new DownloadTestVo();
                downloadTest.setFileName(fileName);
                downloadTest.setFileSize(String.valueOf(fileSize));
                downloadTest.setDownloadTime(new Date());
                downloadBilling.saveDownloadTest(addInfo, downloadTest);
                // Check file downloaded already
                if (isDownloadedFile(fileName)) {
                    continue;
                }
                // Save download log
                downloadLog = new DownloadLogVo();
                downloadLog.setFileName(fileName);
                downloadLog.setDownloadDate(new Date());
                downloadBilling.saveDownloadLog(addInfo, downloadLog);

                filePath += fileName;
                br = new BufferedReader(new FileReader(filePath));
                String line = "", cell = "";
                rowData = new ArrayList<BillingRowDataModel>();
                while ((line = br.readLine()) != null) {
                    // use vertical bar as separator
                    String[] rowArray = line.split("\\|");

                    data = new BillingRowDataModel();
                    data.setCellData(new HashMap<String, String>());
                    if (rowArray != null) {
                        for (int i = 0; i < rowArray.length; i++) {
                            cell = rowArray[i];
                            key = String.valueOf(i);
                            if (cell != null) {
                                value = (StringUtils.isBlank(cell)) ? "" : cell;
                                data.getCellData().put(key, value);
                            }
                        }
                        rowData.add(data);
                    }
                }
                dataSheet = new BillingDataModel();
                dataSheet.setRowData(rowData);
                dataSheet.setFileName(fileName);
                dataSheet.setDownloadFileId(String.valueOf(downloadLog.getId()));
                // Keep import date to be same as FTP date
                fileName = fileName.replace(fileNamePrefix + fileNameCountryCode + "_", "");
                fileDate = fileName.split("_");
                importDate = fileDate[0].substring(0, 4) + "-" + fileDate[0].substring(4, 6) + "-" + fileDate[0].substring(6, 8);
                dataSheet.setImportDate(importDate);
                listBilling.add(dataSheet);
            }
            context.put(Attributes.BILLING_DATA_FILE, listBilling);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private boolean isDownloadedFile(String fileName) throws DaoException {
        DownloadBillingDao billingDao = new DownloadBillingDao();
        Integer count = billingDao.checkDownloadFile(fileName);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
