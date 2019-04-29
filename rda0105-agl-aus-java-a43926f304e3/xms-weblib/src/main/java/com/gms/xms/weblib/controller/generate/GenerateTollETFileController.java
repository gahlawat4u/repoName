package com.gms.xms.weblib.controller.generate;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Posted from Sep 15, 2016 4:11:27 PM
 * <p>
 * Author huynd
 */
public class GenerateTollETFileController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String beginGenerate;
    private String endGenerate;
    private String fileName;

    public String generateFile() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.setBeginGenerate(dateFormat.format(new Date()));
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            String filePath = request.getSession().getServletContext().getRealPath("") + "/edi_file/toll_manifest_file/";
            context.put(GenerateETFileConstants.FILE_PATH, filePath);
            context.put(Attributes.WFP_NAME, "Wfl-GenerateTollETFile");
            context = WorkFlowManager2.getInstance().process(context);
            String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
            this.setFileName(fileName);
            this.setEndGenerate(dateFormat.format(new Date()));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getBeginGenerate() {
        return beginGenerate;
    }

    public void setBeginGenerate(String beginGenerate) {
        this.beginGenerate = beginGenerate;
    }

    public String getEndGenerate() {
        return endGenerate;
    }

    public void setEndGenerate(String endGenerate) {
        this.endGenerate = endGenerate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
