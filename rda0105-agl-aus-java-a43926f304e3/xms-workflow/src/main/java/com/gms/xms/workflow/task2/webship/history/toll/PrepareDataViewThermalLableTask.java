package com.gms.xms.workflow.task2.webship.history.toll;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.history.*;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
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
            HistoryDetailInfoModel detailInfoModel = new HistoryDetailInfoModel();
            IHistoryDetailService historyDetailService = new HistoryDetailServiceImp();
            HistoryDetailFilter filter = new HistoryDetailFilter();
            filter = ModelUtils.createVoFromModel(preapareDetailFilterModel(shipmentId), HistoryDetailFilter.class);
            detailInfoModel = ModelUtils.createModelFromVo(historyDetailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
            detailInfoModel.setBaseCharge(detailInfoModel.getBaseCharge());

            String getServerPathBacode = request.getServerPathBarcode();
            String pieceBarcode = "";
            String tntBarcode = "";
            Integer count = 0;
            List<HistoryViewAirbillModel> listPieceViewAirbill = new ArrayList<HistoryViewAirbillModel>();
            List<PieceModel> pieceModels = new ArrayList<PieceModel>();
            pieceModels = ModelUtils.createListModelFromVo(historyDetailService.selectPieceByIdNonGroup(shipmentId), PieceModel.class);
            List<HistoryDetailAccessorialModel> detailAccessorialModels = new ArrayList<HistoryDetailAccessorialModel>();

            InputStream imgDefault = new ClassPathResource("templates/pdf/history/AWB_VOID.jpg").getInputStream();
            byte[] imgBytesAsBase64Default = Base64.encodeBase64( IOUtils.toByteArray(imgDefault) );
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            pieceBarcode = imgAsBase64Default;

            InputStream imgTntDefault = new ClassPathResource("templates/pdf/history/tnt.png").getInputStream();
            byte[] imgBytesAsBase64TntDefault = Base64.encodeBase64( IOUtils.toByteArray(imgTntDefault) );
            String imgDataAsBase64TntDefault = new String(imgBytesAsBase64TntDefault);
            String imgAsBase64TntDefault = "data:image/png;base64," + imgDataAsBase64TntDefault;
            tntBarcode = imgAsBase64TntDefault;
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            pieceModels = ModelUtils.createListModelFromVo(detailService.selectPieceByIdNonGroup(shipmentId), PieceModel.class);
            if (pieceModels.size() > 0) {
                for (PieceModel piece : pieceModels) {
                    count += 1;
                    HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

                    String reasonForExport = "";
                    if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
                        reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
                    }
                    if (detailInfoModel.getShipmentTypeId().equals("215")) {
                        detailInfoModel.setServiceCode("3");
                    }
                    if (detailInfoModel.getShipmentTypeId().equals("216")) {
                        detailInfoModel.setServiceCode("4");
                    }
                    if (detailInfoModel.getShipmentTypeId().equals("217")) {
                        detailInfoModel.setServiceCode("8");
                    }
                    String productCode = "";
                    if (detailInfoModel.getServiceGroup() != null) {
                        productCode = detailInfoModel.getServiceGroup().replaceAll("0", "");
                    }
                    pieceBarcode = "6".concat(detailInfoModel.getServiceCode()).concat(productCode).concat(detailInfoModel.getrPostalCode().concat(detailInfoModel.getTracking())).concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
                    historyViewAirbillModel.setPieceBarcode(pieceBarcode);
                    AppUtils.createBarCode(pieceBarcode, "png", getServerPathBacode + "/TOLL_IPEC_" + shipmentId + count + ".png", 300, 90);

                    historyViewAirbillModel.setItemCode(detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
                    File img = new File(getServerPathBacode + "/TOLL_IPEC_" + shipmentId + count + ".png");
                    if (detailInfoModel.getStatus().equals("1")) {
                        img = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/AWB_VOID.jpg");
                    }

                    historyViewAirbillModel.setPicesCount(count.toString());
                    byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
                    byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
                    String imgDataAsBase64 = new String(imgBytesAsBase64);
                    String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

                    historyViewAirbillModel.setImageBarcode(imgAsBase64);
                    historyViewAirbillModel.setWeight(piece.getDeadWeight());
                    historyViewAirbillModel.setDimession(piece.getDimension());
                    historyViewAirbillModel.setReasonForExport(reasonForExport);

                    detailInfoModel.setNoOfPieces(count.toString());
                    listPieceViewAirbill.add(historyViewAirbillModel);

                }
            } else {
                count = 1;
                HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

                String reasonForExport = "";
                if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
                    reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
                }
                if (detailInfoModel.getShipmentTypeId().equals("215")) {
                    detailInfoModel.setServiceCode("3");
                }
                if (detailInfoModel.getShipmentTypeId().equals("216")) {
                    detailInfoModel.setServiceCode("4");
                }
                if (detailInfoModel.getShipmentTypeId().equals("217")) {
                    detailInfoModel.setServiceCode("8");
                }
                String productCode = "";
                if (detailInfoModel.getServiceGroup() != null) {
                    productCode = detailInfoModel.getServiceGroup().replaceAll("0", "");
                }
                pieceBarcode = "6".concat(detailInfoModel.getServiceCode()).concat(productCode).concat(detailInfoModel.getrPostalCode().concat(detailInfoModel.getTracking())).concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
                historyViewAirbillModel.setPieceBarcode(pieceBarcode);
                AppUtils.createBarCode(pieceBarcode, "png", getServerPathBacode + "/TOLL_IPEC_" + shipmentId + count + ".png", 300, 90);

                historyViewAirbillModel.setItemCode(detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
                File img = new File(getServerPathBacode + "/TOLL_IPEC_" + shipmentId + count + ".png");
                if (detailInfoModel.getStatus().equals("1")) {
                    img = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/AWB_VOID.jpg");
                }

                historyViewAirbillModel.setPicesCount(count.toString());
                byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
                byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

                historyViewAirbillModel.setImageBarcode(imgAsBase64);
                historyViewAirbillModel.setWeight("0");
                historyViewAirbillModel.setDimession("0");
                historyViewAirbillModel.setReasonForExport(reasonForExport);

                detailInfoModel.setNoOfPieces(count.toString());
                listPieceViewAirbill.add(historyViewAirbillModel);
            }

            Map<String, Object> data = new HashMap<String, Object>();
            detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));

            List<HistoryDetailPieceModel> detailPieceModels = ModelUtils.createListModelFromVo(historyDetailService.selectPieceInfo(filter, true), HistoryDetailPieceModel.class);
            detailAccessorialModels = ModelUtils.createListModelFromVo(historyDetailService.selectHistoryDetailAccessorial(filter), HistoryDetailAccessorialModel.class);

            data.put("detailPieceModels", detailPieceModels);
            data.put("detailInfoModel", detailInfoModel);
            data.put("detailAccessorialModels", detailAccessorialModels);
            data.put("isThermalLable", "0");
            data.put("listPieceViewAirbill", listPieceViewAirbill);
            data.put("pieceBarcode", pieceBarcode);
            data.put("tntBarcode", tntBarcode);
            context.put(Attributes.VIEW_AIRBILL_DATA, data);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    protected HistoryDetailFilterModel preapareDetailFilterModel(Long shipmentId) throws Exception {
        HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
        detailFilterModelN.setShipmentId(String.valueOf(shipmentId));
        detailFilterModelN.setLbToKg("0.45359237");
        detailFilterModelN.setInToCm("2.54");
        detailFilterModelN.setWeightValue("5000");
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
        detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
        return detailFilterModelN;
    }
}
