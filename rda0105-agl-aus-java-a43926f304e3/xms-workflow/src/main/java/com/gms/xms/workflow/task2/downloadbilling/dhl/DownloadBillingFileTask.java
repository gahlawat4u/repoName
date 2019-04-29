package com.gms.xms.workflow.task2.downloadbilling.dhl;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.downloadbilling.DownloadedFileModel;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jun 2, 2016 2:21:54 PM
 * <p>
 * Author huynd
 */
public class DownloadBillingFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(DownloadBillingFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            ServiceVo service = context.get(Attributes.SERVICE_VO);
            String serviceName = service.getServiceName();
            context.put(Attributes.SERVICE_NAME, serviceName);
            String server = service.getFtpServer();
            String user = service.getFtpUserName();
            String pass = service.getFtpUserPassword();
            String folder = service.getServerFolder();
            String fileNamePrefix = service.getFileNamePrefix();
            String fileNameCountryCode = service.getFileCountryCode();
            int port = 21;
            String filePath = context.get(Attributes.DOWNLOAD_FILE_PATH);
            filePath = filePath.replace("\\", "/").trim();
            if (filePath == null || StringUtils.isBlank(filePath)) {
                throw new Exception("Download file path is not existed!");
            }

            DownloadedFileModel files = new DownloadedFileModel();
            Map<String, Long> downloadedFile = new HashMap<String, Long>();
            // Login to ftp server
            ftpClient.connect(server, port);
            // login to server
            if (!ftpClient.login(user, pass)) {
                ftpClient.logout();
                throw new Exception("Failed to login!");
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // change current directory
            ftpClient.changeWorkingDirectory(folder);
            // get list of filenames
            FTPFile[] ftpFiles = ftpClient.listFiles();
            if (ftpFiles != null && ftpFiles.length > 0) {
                for (FTPFile file : ftpFiles) {
                    if (!file.isFile()) {
                        continue;
                    }
                    if (file.getName().contains(fileNamePrefix + fileNameCountryCode)) {
                        File downloadFile = new File(filePath + file.getName());
                        // get output stream
                        OutputStream output = new BufferedOutputStream(new FileOutputStream(downloadFile));
                        // get the file from the remote system
                        ftpClient.retrieveFile(file.getName(), output);
                        downloadedFile.put(file.getName(), file.getSize());
                        files.setFiles(downloadedFile);
                        // close output stream
                        output.close();
                    }
                }
            }
            context.put(Attributes.DOWNLOAD_FILE_LIST, files);
            context.put(Attributes.FILE_NAME_PREFIX, fileNamePrefix);
            context.put(Attributes.FILE_NAME_COUNTRY_CODE, fileNameCountryCode);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

}
