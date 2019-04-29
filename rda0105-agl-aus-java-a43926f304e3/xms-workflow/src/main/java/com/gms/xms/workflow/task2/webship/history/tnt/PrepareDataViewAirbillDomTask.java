package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailPieceVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewAirbillTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewAirbillDomTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewAirbillDomTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            Long shipmentId = request.getShipmentId();

            HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(shipmentId);
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoVo historyDetailInfo = detailService.selectHistoryDetailInfo(filter);
            historyDetailInfo.setShipmentId(request.getShipmentId());
            List<PieceVo> pieces = detailService.selectPieceByIdNonGroup(shipmentId);
            List<HistoryDetailPieceVo> detailPieces = detailService.selectPieceInfo(filter, false);

            File imgDefault = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/AWB_VOID.jpg");
            byte[] imgBytesDefault = AppUtils.readContentIntoByteArray(imgDefault);
            byte[] imgBytesAsBase64Default = Base64.encodeBase64(imgBytesDefault);
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            String imgVoied = imgAsBase64Default;

            File imgTnt = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/tnt.png");
            byte[] imgBytesTnt = AppUtils.readContentIntoByteArray(imgTnt);
            byte[] imgBytesAsBase64Tnt = Base64.encodeBase64(imgBytesTnt);
            String imgDataAsBase64Tnt = new String(imgBytesAsBase64Tnt);
            String imgAsBase64Tnt = "data:image/png;base64," + imgDataAsBase64Tnt;
            String tntImage = imgAsBase64Tnt;

            if (historyDetailInfo.getCourierMessage() == null) {
                historyDetailInfo.setCourierMessage("");
            }

            historyDetailInfo.setNoOfPieces(pieces.size());

            if (pieces.size() <= 0) {
                historyDetailInfo.setNoOfPieces(0);
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailPieces", detailPieces);
            data.put("detailInfoModel", historyDetailInfo);
            data.put("tntImage", tntImage);
            data.put("imgVoied", imgVoied);
            data.put("pieces", pieces);
            data.put("isThermalLable", "0");

            context.put(Attributes.VIEW_AIRBILL_DATA, data);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
