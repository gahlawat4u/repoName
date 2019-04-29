package com.gms.xms.weblib.controller.generate;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.service.edigenerate.EdiGenerateService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Posted from Sep 22, 2016 10:43:08 AM
 * <p>
 * Author dattrinh
 */
public class GenerateTntETFileController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private String action;
    private String files;

    public String generate() {
        try {
            String dir = this.getServerPath(AppConstants.APP_SETTINGS.getEdiPath());
            String message = "";
            // Do TNT.
            if ("tnt".equalsIgnoreCase(this.getAction())) {
                message = doTnt(dir);
            }
            // Do Dispose.
            if ("dispose".equalsIgnoreCase(this.getAction())) {
                message = doDispose(dir);
            }
            // Do delete.
            if ("delete".equalsIgnoreCase(this.getAction())) {
                message = doDelete(dir);
            }
            this.addActionMessage(message);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected String doDelete(String dir) throws Exception {
        EdiGenerateService ediGenerateService = new EdiGenerateService(this.getAddInfoMap());
        int ftpReturn = ediGenerateService.tntEtFileDelete(this.getFiles(), dir);
        String etMsg = "";
        if (ftpReturn == -1) {
            etMsg = this.getFiles() + " ET file fail to delete at FTP server. Can not login to FTP server.";
        } else if (ftpReturn == -2) {
            etMsg = this.getFiles() + " ET file fail to delete at FTP server. File can not file";
        } else if (ftpReturn == 0) {
            etMsg = this.getFiles() + " ET file fail to delete at FTP server.";
        } else {
            etMsg = this.getFiles() + " ET file successfully deleted at FTP server.";
        }
        return etMsg;
    }

    protected String doDispose(String dir) throws Exception {
        EdiGenerateService ediGenerateService = new EdiGenerateService(this.getAddInfoMap());
        int ftpReturn = ediGenerateService.tntEtFileDispose(this.getFiles(), dir);
        String etMsg = "";
        if (ftpReturn == -1) {
            etMsg = this.getFiles() + " ET file fail to dispose at FTP server. Cannot login to FTP server.";
        } else if (ftpReturn == 0) {
            etMsg = this.getFiles() + " ET file fail to dispose at FTP server.";
        } else {
            etMsg = this.getFiles() + " ET file successfully disposed at FTP server.";
            ediGenerateService.updateTntTransmissionUpload(this.getFiles());
        }
        return etMsg;
    }

    protected String doTnt(String dir) throws Exception {
        EdiGenerateService ediGenerateService = new EdiGenerateService(this.getAddInfoMap());
        String etMsg = "<br/>Fail to generate ET file.";
        String retMessage = etMsg;
        String fileName = ediGenerateService.generateETFileCombine(dir).trim();
        File file = new File(dir + "/" + fileName);
        if (!StringUtils.isBlank(fileName) && file.exists()) {
            retMessage += "<br/>Successful generate ET file " + dir + "/" + fileName;
            int ftpReturn = ediGenerateService.tntEtFileDispose(fileName, dir);
            if (ftpReturn == -1) {
                etMsg = fileName + " ET file fail to dispose at FTP server. Cannot login to FTP server.";
            } else if (ftpReturn == 0) {
                etMsg = fileName + " ET file fail to dispose at FTP server.";
            } else {
                etMsg = fileName + " ET file successfully disposed at FTP server.";
                ediGenerateService.updateTntTransmissionUpload(fileName);
            }
            retMessage += etMsg;
        }
        String email = "syaung09@gmail.com";
        ediGenerateService.sendEmailTntEtFile("", "TNT Domestic ET file dispose", etMsg, email, true);
        return retMessage;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
