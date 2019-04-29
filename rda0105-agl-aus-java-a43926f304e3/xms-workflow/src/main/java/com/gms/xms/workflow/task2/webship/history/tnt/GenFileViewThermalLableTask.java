package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.PdfScaleUtils;
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
 * Posted from GenFileViewThermalLableTask
 * <p>
 * Author TANDT
 */
public class GenFileViewThermalLableTask implements Task2 {
    private static final Log log = LogFactory.getLog(GenFileViewThermalLableTask.class);

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
            String pdfFilePath = request.getTmpFileOutputServer() + fileViewName + shipmentId + miniTime + ".pdf";
            AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
            String thermalFileName = "tnt_thermal_label_" + shipmentId + miniTime + ".pdf";
            String thermalFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalFileName;
            new PdfScaleUtils().manipulatePdf(pdfFilePath, thermalFilePath, 0.3f);

            HistoryViewFileResultVo historyViewAirbillResultVo = new HistoryViewFileResultVo();

            historyViewAirbillResultVo.setFileName(thermalFileName);
            historyViewAirbillResultVo.setStream(new FileInputStream(new File(thermalFilePath)));
            historyViewAirbillResultVo.setPathFileView("tmp/" + thermalFilePath);

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
