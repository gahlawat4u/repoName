package com.gms.xms.workflow.task2.webship.history;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.ComponentDao;
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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewThermalLableTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewThermalLableTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewThermalLableTask.class);

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
            InputStream imgDefault = new ClassPathResource("templates/pdf/history/AWB_VOID.jpg").getInputStream();
            byte[] imgBytesAsBase64Default = Base64.encodeBase64( IOUtils.toByteArray(imgDefault) );
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            String imgVoied = imgAsBase64Default;
            InputStream imgDhl = new ClassPathResource("templates/pdf/history/dhl_label.png").getInputStream();
            byte[] imgBytesAsBase64Dhl = Base64.encodeBase64( IOUtils.toByteArray(imgDhl) );
            String imgDataAsBase64Dhl = new String(imgBytesAsBase64Dhl);
            String imgAsBase64Dhl = "data:image/png;base64," + imgDataAsBase64Dhl;
            String dhlImage = imgAsBase64Dhl;
            String pieceBarcode = "";
            Integer count = 0;
            if (pieces.size() > 0) {
                for (@SuppressWarnings("unused")
                PieceVo piece : pieces) {
                    count += 1;
                    historyDetailInfo.setNoOfPieces(count);
                }
            } else {
                context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                context.put(Attributes.ERROR_MESSAGE, "Airbill ".concat(historyDetailInfo.getTracking()).concat(" does not exist in the system or it lacks information. Please turn back. "));
                return false;
            }
            // get InboundSortCode , outboundSortCode
            String inboundSortCode = "";
            String outboundSortCode = "";
            String[] aryDhlNote = historyDetailInfo.getBillingCode().split("-");
            if (aryDhlNote.length >= 2) {
                inboundSortCode = (StringUtils.isEmpty(aryDhlNote[0])) ? aryDhlNote[0] : "";
                inboundSortCode = (StringUtils.isEmpty(aryDhlNote[1])) ? aryDhlNote[1] : "";
            }
            ComponentDao componentDao = new ComponentDao();
            String dhlAccount = componentDao.getDHLAccount(historyDetailInfo.getCustomerCode());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailPieces", detailPieces);
            data.put("detailInfo", historyDetailInfo);
            data.put("dhlImage", dhlImage);
            data.put("outboundSortCode", outboundSortCode);
            data.put("inboundSortCode", inboundSortCode);
            data.put("dhlAccount", dhlAccount);
            data.put("imgVoied", imgVoied);
            data.put("pieces", pieces);
            data.put("isThermalLable", "0");
            data.put("pieceBarcode", pieceBarcode);
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
