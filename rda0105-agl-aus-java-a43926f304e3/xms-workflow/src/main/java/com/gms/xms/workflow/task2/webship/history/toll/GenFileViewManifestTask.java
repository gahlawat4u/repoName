package com.gms.xms.workflow.task2.webship.history.toll;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileResultVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Posted from GenFileViewManifestTask
 * <p>
 * Author TANDT
 */
public class GenFileViewManifestTask implements Task2 {
    private static final Log log = LogFactory.getLog(GenFileViewManifestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            String miniTime = "";
            miniTime = String.valueOf(System.currentTimeMillis());
            String fileViewName = request.getFileName();
            String shipmentId = String.valueOf(request.getShipmentId());
            String outputFilePath = request.getTmpFileOutputServer() + fileViewName + shipmentId + miniTime + ".html";
            Map<String, Object> data = context.get(Attributes.VIEW_AIRBILL_DATA);
            AppUtils.template2File(outputFilePath, false, "templates/pdf/history/" + fileViewName + ".ftl", data);
            String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileViewName + shipmentId + miniTime + ".pdf";

            AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
            HistoryViewFileResultVo historyViewAirbillResultVo = new HistoryViewFileResultVo();

            historyViewAirbillResultVo.setFileName(fileViewName + shipmentId + miniTime + ".pdf");
            historyViewAirbillResultVo.setStream(new FileInputStream(new File(pdfFilePath)));
            historyViewAirbillResultVo.setPathFileView("tmp/" + fileViewName + shipmentId + miniTime + ".pdf");

            context.put(Attributes.VIEW_AIRBILL_RESULT_DATA, historyViewAirbillResultVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;

    }

}
