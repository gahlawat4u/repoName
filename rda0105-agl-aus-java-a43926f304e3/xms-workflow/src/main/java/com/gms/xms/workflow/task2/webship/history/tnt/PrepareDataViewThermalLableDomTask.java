package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.TntRouteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.history.*;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.TntRouteViewFilter;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewThermalLableTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewThermalLableDomTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewThermalLableDomTask.class);

    @Override
    public boolean execute(ContextBase2 context) {
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

            File imgDefault = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/AWB_VOID.jpg");
            byte[] imgBytesDefault = AppUtils.readContentIntoByteArray(imgDefault);
            byte[] imgBytesAsBase64Default = Base64.encodeBase64(imgBytesDefault);
            String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
            String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
            pieceBarcode = imgAsBase64Default;

            File imgTntDefault = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/tnt.png");
            byte[] imgBytesTntDefault = AppUtils.readContentIntoByteArray(imgTntDefault);
            byte[] imgBytesAsBase64TntDefault = Base64.encodeBase64(imgBytesTntDefault);
            String imgDataAsBase64TntDefault = new String(imgBytesAsBase64TntDefault);
            String imgAsBase64TntDefault = "data:image/png;base64," + imgDataAsBase64TntDefault;
            tntBarcode = imgAsBase64TntDefault;
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            List<PieceVo> pieceVos = new ArrayList<PieceVo>();
            pieceVos = detailService.selectPieceByIdNonGroup(shipmentId);
            Integer countPiece = 0;
            if (pieceVos != null) {
                pieceModels = ModelUtils.createListModelFromVo(pieceVos, PieceModel.class);
                countPiece = pieceModels.size();
            }
            if (countPiece == 0) {
                count = 1;
                PieceModel piece = new PieceModel();
                piece.setCustomValue("");
                piece.setDeadWeight("0");
                piece.setDimension("");
                piece.setQuantity("0");
                piece.setWeight("0");
                HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                prepareDataTntDom(count, historyViewAirbillModel, piece, detailInfoModel, getServerPathBacode, shipmentId);
                detailInfoModel.setNoOfPieces(count.toString());
                listPieceViewAirbill.add(historyViewAirbillModel);
            } else {
                for (PieceModel piece : pieceModels) {
                    count += 1;
                    HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                    prepareDataTntDom(count, historyViewAirbillModel, piece, detailInfoModel, getServerPathBacode, shipmentId);
                    detailInfoModel.setNoOfPieces(count.toString());
                    listPieceViewAirbill.add(historyViewAirbillModel);
                }
            }

            Map<String, Object> data = new HashMap<String, Object>();
            detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));

            List<HistoryDetailPieceModel> detailPieceModels = ModelUtils.createListModelFromVo(historyDetailService.selectPieceInfo(filter, false), HistoryDetailPieceModel.class);
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

    protected void prepareDataTntDom(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece, HistoryDetailInfoModel detailInfoModel, String getServerPathBacode, Long shipmentId) throws Exception {
        String reasonForExport = "";
        if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
            reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
        }
        String destinationPortDescription = "NULL";
        TntRouteViewFilter tntRouteFilter = new TntRouteViewFilter();
        tntRouteFilter.setDestinationSuburb(detailInfoModel.getrCity());
        tntRouteFilter.setPostCode(detailInfoModel.getrPostalCode());
        tntRouteFilter.setServiceGroup(detailInfoModel.getServiceGroup());
        IHistoryDetailService serviceHistory = new HistoryDetailServiceImp();
        List<TntRouteModel> tntRouteModels = new ArrayList<TntRouteModel>();
        tntRouteModels = ModelUtils.createListModelFromVo(serviceHistory.selectByFilterView(tntRouteFilter), TntRouteModel.class);
        String sortBin = "0000";
        String exPort = "";
        String defaultRout = "";
        if (StringUtils.isEmpty(detailInfoModel.getServiceGroup())) {
            detailInfoModel.setServiceGroup("EXP");
        }
        if (StringUtils.isEmpty(detailInfoModel.getOriginDepot().trim())) {
            exPort = "Ex ".concat(detailInfoModel.getOriginDepot());
        }
        for (TntRouteModel tntRouteModel : tntRouteModels) {
            if ("".equals(defaultRout) || tntRouteModel.getOriginDepot().equals(detailInfoModel.getOriginDepot())) {
                sortBin = tntRouteModel.getRouteBin();

                if (tntRouteModel.getGatewayDepot().equals(tntRouteModel.getForwardingDepot())) {
                    destinationPortDescription = tntRouteModel.getGatewayDepot();
                } else {
                    destinationPortDescription = "via ".concat(tntRouteModel.getGatewayDepot()).concat(" to ").concat(tntRouteModel.getForwardingDepot());
                }

                if (tntRouteModel.getOriginDepot().equals(detailInfoModel.getOriginDepot())) {
                    break;
                }
            }
            defaultRout = "1";
        }

        String strDanger = "Does Not Contain Dangerous Goods";
        if (detailInfoModel.getCourierMessage() != null) {
            if (detailInfoModel.getCourierMessage().trim().equals("DG")) {
                String dgary[] = detailInfoModel.getDhlRoutingCode().split("##@##");
                String UNno = "";
                if (dgary[0] != null) {
                    UNno = dgary[0];
                }
                String dGPkGroup = "";
                if (dgary[1] != null) {
                    dGPkGroup = dgary[1];
                }
                strDanger = "Contain Dangerous Goods, UN Number(".concat(UNno).concat("), Packaging Group(").concat(dGPkGroup).concat("), ").concat(detailInfoModel.getContentDescription());
            }
        } else {
            detailInfoModel.setCourierMessage("");
        }

        historyViewAirbillModel.setItemCode(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString()));
        historyViewAirbillModel.setPieceBarcode("6104".concat(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString())).concat("0").concat(detailInfoModel.getrPostalCode()).concat("0"));
        AppUtils.createBarCode("6104".concat(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString())).concat("0").concat(detailInfoModel.getrPostalCode()).concat("0"), "png", getServerPathBacode + "/TNT_DOM_" + shipmentId + count + ".png", 300, 90);
        historyViewAirbillModel.setPicesCount(count.toString());

        File img = new File(getServerPathBacode + "/TNT_DOM_" + String.valueOf(shipmentId) + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(AppConstants.class.getClassLoader().getResource("templates/pdf/history").getPath() + "/AWB_VOID.jpg");
        }
        byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        historyViewAirbillModel.setImageBarcode(imgAsBase64);
        if (piece.getDeadWeight() != null) {
            historyViewAirbillModel.setWeight(piece.getDeadWeight());
        }
        historyViewAirbillModel.setDestinationPortDescription(destinationPortDescription);
        historyViewAirbillModel.setSortBin(sortBin);
        historyViewAirbillModel.setExPort(exPort);
        historyViewAirbillModel.setStrDanger(strDanger);
        historyViewAirbillModel.setReasonForExport(reasonForExport);

    }

}
