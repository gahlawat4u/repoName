package com.gms.xms.workflow.task2.tnt.connote;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.tnt.TntViewConnoteModel;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from GetTntDomesticBaseChargeTask
 * <p>
 * Author TANDT
 */
public class ProcessRenderViewConnoteTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessRenderViewConnoteTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            TntViewConnoteModel tntViewConnoteModel = context.get(Attributes.TNT_VIEW_CONNOTE_DATA);
            String miniTime = String.valueOf(System.currentTimeMillis());
            String fileName = "tnt_connote_" + tntViewConnoteModel.getShipment().getAirbillNumber() + "_" + miniTime + ".pdf";
            String htmlPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + "tnt_connote_" + tntViewConnoteModel.getShipment().getAirbillNumber() + "_" + miniTime + ".html";
            String pdfPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileName;
            Map<String, Object> data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(addInfo));
            data.put("connoteData", tntViewConnoteModel);
            AppUtils.template2File(htmlPath, false, "templates/pdf/history/tnt_view_connote.ftl", data);
            AppUtils.createPDFFromHTMLWithFont(htmlPath, pdfPath, "arial", true);
            context.put(Attributes.FILE_NAME, fileName);
            context.put(Attributes.FILE_PATH, pdfPath);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }
}
