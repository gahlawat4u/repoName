package com.gms.xms.workflow.task2.startrack.viewmanifest;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hungnt
 */
public class RenderStarTrackViewManifestTask implements Task2 {
    private static final Log log = LogFactory.getLog(RenderStarTrackViewManifestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            String maniFestPdfStr = shipmentVo.getManifestPdf();
            String miniTime = String.valueOf(System.currentTimeMillis());
            String fileName = "startrack_manifest_" + shipmentVo.getAirbillNumber() + "_" + miniTime + ".pdf";
            String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileName;
            AppUtils.createPDFFromBarCode(pdfFilePath, maniFestPdfStr);
            context.put(Attributes.FILE_NAME, fileName);
            context.put(Attributes.FILE_PATH, pdfFilePath);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
