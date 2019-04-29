package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
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
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewManifestTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewManifestTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewManifestTask.class);

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
            List<HistoryDetailPieceVo> detailPieces = detailService.selectPieceInfo(filter, true);

            InputStream imgDefault = new ClassPathResource("templates/pdf/history/AWB_VOID.jpg").getInputStream();
            byte[] imgBytesAsBase64Default = Base64.encodeBase64( IOUtils.toByteArray(imgDefault) );
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            String imgVoied = imgAsBase64Default;

            AppUtils.createBarCode(historyDetailInfo.getAirbillNumber(), "png", request.getServerPathBarcode() + "/TNT_" + historyDetailInfo.getAirbillNumber() + ".png", 500, 500);
            File pieceBarcodeImg = new File(request.getServerPathBarcode() + "/TNT_" + historyDetailInfo.getAirbillNumber() + ".png");
            byte[] imgBytesDefaultPieceBarcode = AppUtils.readContentIntoByteArray(pieceBarcodeImg);
            byte[] imgBytesAsBase64DefaultPieceBarcode = Base64.encodeBase64(imgBytesDefaultPieceBarcode);
            String imgDataAsBase64DefaultPieceBarcode = new String(imgBytesAsBase64DefaultPieceBarcode);
            String imgAsBase64DefaultPieceBarcode = "data:image/png;base64," + imgDataAsBase64DefaultPieceBarcode;
            String pieceBarcodeStr = imgAsBase64DefaultPieceBarcode;

            InputStream imgTnt = new ClassPathResource("templates/pdf/history/tnt.png").getInputStream();
            byte[] imgBytesAsBase64Tnt = Base64.encodeBase64( IOUtils.toByteArray(imgTnt) );
            String imgDataAsBase64Tnt = new String(imgBytesAsBase64Tnt);
            String imgAsBase64Tnt = "data:image/png;base64," + imgDataAsBase64Tnt;
            String tntImage = imgAsBase64Tnt;

            if (historyDetailInfo.getCourierMessage() == null) {
                historyDetailInfo.setCourierMessage("");
            }

            historyDetailInfo.setNoOfPieces(pieces.size());
            if (pieces.size() == 0) {
                historyDetailInfo.setTotalWeight("0");
            }
            DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();

            DateFormat outFormatTime = new SimpleDateFormat("HH:mm:ss");
            Date currentTime = new Date();

            HistoryDetailInfoModel historyDetailInfoModel = ModelUtils.createModelFromVo(historyDetailInfo, HistoryDetailInfoModel.class);
            if (historyDetailInfoModel.getContents().equals("1")) {
                historyDetailInfoModel.setContents("N");
            } else {
                historyDetailInfoModel.setContents("D");
            }
            if (historyDetailInfoModel.getInternalServiceCode() == null) {
                historyDetailInfoModel.setInternalServiceCode("");
            }
            historyDetailInfoModel.setrCompanyName(StringEscapeUtils.escapeXml(historyDetailInfoModel.getrCompanyName()));
            historyDetailInfoModel.setsCompanyName(StringEscapeUtils.escapeXml(historyDetailInfoModel.getsCompanyName()));
            historyDetailInfoModel.setrContactName(StringEscapeUtils.escapeXml(historyDetailInfoModel.getrContactName()));
            historyDetailInfoModel.setsContactName(StringEscapeUtils.escapeXml(historyDetailInfoModel.getsContactName()));
            historyDetailInfoModel.setContentDescription(StringEscapeUtils.escapeXml(historyDetailInfoModel.getContentDescription()));
            historyDetailInfoModel.setReferenceNo(StringEscapeUtils.escapeXml(historyDetailInfoModel.getReferenceNo()));
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailPieces", detailPieces);
            data.put("detailInfoModel", historyDetailInfoModel);
            data.put("tntImage", tntImage);
            data.put("imgVoied", imgVoied);
            data.put("pieces", pieces);
            data.put("isThermalLable", "0");
            data.put("pieceBarcodeStr", pieceBarcodeStr);
            data.put("currentDate", String.valueOf(outFormat.format(currentDate)));
            data.put("currentTime", String.valueOf(outFormatTime.format(currentTime)));
            data.put("currencyCode", SystemSettingCache.getInstance().getValueByKey("CurrencyCode"));
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
