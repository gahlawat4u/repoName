package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.TntRouteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.history.*;
import com.gms.xms.txndb.vo.TntRouteViewFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryProductAirbillVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ManageCustomerWebshipHistoryDetailController
 * <p>
 * Author TANDT 06-11-2015
 */
public class ManageCustomerWebshipHistoryDetailController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -113977720546062776L;
    private List<HistoryDetailPieceModel> detailPieceModels;
    private List<PieceModel> pieceModels;
    private List<HistoryViewAirbillModel> listPieceViewAirbill;
    private List<HistoryDetailAccessorialModel> detailAccessorialModels;
    private HistoryDetailInfoModel detailInfoModel;
    private HistoryDetailFilterModel detailFilterModel;
    private List<HistoryProductAirbillModel> productAirbillModels;
    private String totalAmount;
    private InputStream stream;
    private String shipmentId;
    private String awbBarcode;
    private String pieceBarcode;
    private String tntBarcode;
    private String fileName;
    private String small;
    private String pathFileView;

    public String show() {
        try {
            prepareHistoryDetailFilter();
            prepareHistoryDetail();
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String viewAirbill() {
        this.setPageTitle("View Airbill");
        try {
            String fileFtlViewAirbill = "";
            if (validViewAirbill()) {
                prepareHistoryDetailFilter();
                prepareHistoryDetail();
                putDataToViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
                fileFtlViewAirbill = ShipmentUtils.checkViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
                if (!hasActionErrors() && !hasFieldErrors()) {
                    if ("".equals(fileFtlViewAirbill)) {
                        validAirbillBarcode();
                        return "export";
                    } else {

                        genFileViewAirbill(fileFtlViewAirbill);
                        if (small != null) {
                            if (small.equals("1")) {
                                return "export";
                            }
                        }
                        return "export";
                    }
                } else {
                    return "error";
                }
            } else {
                return "error";
            }
        } catch (Exception e) {
            addActionError("This shipment is error !");
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String viewCommercialInvoice() {
        this.setPageTitle("View Commercial Invoice");
        try {
            if (validViewAirbill()) {
                prepareHistoryDetailFilter();
                prepareHistoryDetail();
                prepareViewcommercial();
                if (!hasActionErrors() && !hasFieldErrors()) {
                    genFileViewAirbill("viewCommercialInvoice");
                    return "export";
                } else {
                    return "error";
                }
            } else {
                return "error";
            }
        } catch (Exception e) {
            addActionError("This shipment is error !");
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String exportDetail() {
        try {
            this.setPageTitle("History Export Shipment Detail");
            prepareHistoryDetailFilter();
            prepareHistoryDetail();
            Map<String, Object> data = new HashMap<String, Object>();
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/export_history_detail.html";
            data.put("detailPieceModels", detailPieceModels);
            data.put("detailInfoModel", detailInfoModel);
            data.put("detailAccessorialModels", detailAccessorialModels);
            AppUtils.template2File(outputFilePath, false, "templates/pdf/history/history_detail.ftl", data);

            this.setFileName("export_history_detail.pdf");
            FileOutputStream fos = new FileOutputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/export_history_detail.pdf"));
            ITextRenderer render = new ITextRenderer();
            render.setDocument(new File(outputFilePath));
            render.layout();
            render.createPDF(fos);
            fos.close();
            this.setStream(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/export_history_detail.pdf")));
            return "export";

        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void genFileViewAirbill(String fileViewName) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        String miniTime = "";
        miniTime = String.valueOf(System.currentTimeMillis());
        String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName + shipmentId + miniTime + ".html";
        detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));
        data.put("detailPieceModels", detailPieceModels);
        data.put("detailInfoModel", detailInfoModel);
        data.put("detailAccessorialModels", detailAccessorialModels);
        data.put("isThermalLable", "0");
        data.put("listPieceViewAirbill", listPieceViewAirbill);
        data.put("pieceBarcode", pieceBarcode);
        data.put("tntBarcode", tntBarcode);
        data.put("productAirbillModels", productAirbillModels);
        data.put("ttAmount", totalAmount);
        if (small != null) {
            if (small.equals("1")) {
                data.put("isThermalLable", "1");
            }
        }
        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/" + fileViewName + ".ftl", data);

        this.setFileName(fileViewName + shipmentId + miniTime + ".pdf");
        FileOutputStream fos = new FileOutputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName + shipmentId + miniTime + ".pdf"));
        ITextRenderer render = new ITextRenderer();
        render.setDocument(new File(outputFilePath));
        render.layout();
        render.createPDF(fos);
        fos.close();
        this.setStream(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName + shipmentId + miniTime + ".pdf")));
        this.setPathFileView("tmp/" + fileViewName + shipmentId + miniTime + ".pdf");
    }

    protected void prepareViewcommercial() throws Exception {
        IHistoryDetailService service = new HistoryDetailServiceImp();
        List<HistoryProductAirbillModel> productAirbillModels = new ArrayList<HistoryProductAirbillModel>();
        List<HistoryProductAirbillVo> productAirbillVos = new ArrayList<HistoryProductAirbillVo>();
        productAirbillVos = service.selectHistoryProductAirbill(Long.parseLong(shipmentId));
        Double totalAmount = 0D;
        if (productAirbillVos.size() > 0) {
            for (HistoryProductAirbillVo productAirbillVo : productAirbillVos) {
                if (productAirbillVo.getQty() > 0) {
                    productAirbillVo.setAmount(MathUtils.round(productAirbillVo.getAmount(), 2));
                    productAirbillVo.setPrice(productAirbillVo.getAmount() / productAirbillVo.getQty());
                    productAirbillVo.setPrice(MathUtils.round(productAirbillVo.getPrice(), 2));
                    totalAmount += productAirbillVo.getAmount();
                }
            }
        }
        this.setTotalAmount(totalAmount.toString());
        productAirbillModels = ModelUtils.createListModelFromVo(productAirbillVos, HistoryProductAirbillModel.class);
        this.setProductAirbillModels(productAirbillModels);
    }

    protected void putDataToViewAirbill(Integer serviceId) throws Exception {
        Integer count = 0;
        if (listPieceViewAirbill == null) {
            listPieceViewAirbill = new ArrayList<HistoryViewAirbillModel>();
        }

        File imgDefault = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        byte[] imgBytesDefault = AppUtils.readContentIntoByteArray(imgDefault);
        byte[] imgBytesAsBase64Default = Base64.encodeBase64(imgBytesDefault);
        String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
        String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
        pieceBarcode = imgAsBase64Default;

        File imgTntDefault = new File(this.getServerPath("/images") + "/tnt.png");
        byte[] imgBytesTntDefault = AppUtils.readContentIntoByteArray(imgTntDefault);
        byte[] imgBytesAsBase64TntDefault = Base64.encodeBase64(imgBytesTntDefault);
        String imgDataAsBase64TntDefault = new String(imgBytesAsBase64TntDefault);
        String imgAsBase64TntDefault = "data:image/png;base64," + imgDataAsBase64TntDefault;
        tntBarcode = imgAsBase64TntDefault;

        String articlePrefix = SystemSettingCache.getInstance().getValueByKey("AAE Article Number Prefix").trim();
        if (pieceModels.size() > 0) {
            for (PieceModel piece : pieceModels) {
                count += 1;
                HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                if (serviceId == 3) {
                    prepareDataTntDom(count, historyViewAirbillModel, piece);
                }
                if (serviceId == 54) {
                    prepareDataTntInt(count, historyViewAirbillModel, piece);
                }
                if (serviceId == 59) {
                    prepareDataTollIpec(count, historyViewAirbillModel, piece);
                }

                if (serviceId == 52) {
                    prepareDataTollPriority(count, historyViewAirbillModel, piece);
                }

                if (serviceId == 2) {
                    prepareDataAaeViewAirbill(count, historyViewAirbillModel, piece, articlePrefix);
                }

                detailInfoModel.setNoOfPieces(count.toString());
                listPieceViewAirbill.add(historyViewAirbillModel);
            }
        } else {
            addActionError("Airbill ".concat(detailInfoModel.getTracking()).concat(" does not exist in the system or it lacks information. Please turn back. "));
        }
    }

    protected boolean validAirbillBarcode() throws Exception {
        IHistoryService iHistoryService = new HistoryServiceImp();
        if (StringUtils.isEmpty(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)))) {
            addFieldError("historyViewAirbillModel.barcode", "Can not Find Barcode!");
        } else {
            this.setAwbBarcode(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)));
            this.setShipmentId(shipmentId);
            String filePath = this.getServerPath("/barcode") + "/" + shipmentId + ".pdf";
            AppUtils.createPDFFromBarCode(filePath, awbBarcode);
            this.setFileName(shipmentId + ".pdf");
            this.setStream(new FileInputStream(new File(filePath)));

        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareDataAaeViewAirbill(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece, String articlePrefix) throws Exception {
        String articleId = "";
        if (piece.getLicensePlate().length() > 5) {
            articlePrefix = "";
        }
        if (piece.getLicensePlate().equals("")) {
            articleId = AppUtils.leftPad(count.toString(), 5, "0".charAt(0));
        } else {
            articleId = AppUtils.leftPad(piece.getLicensePlate(), 5, "0".charAt(0));
        }
        articleId = articlePrefix.concat(articleId);
        String atlCode = "";
        atlCode = detailInfoModel.getDhlRoutingDataId();
        String atlPrefix = SystemSettingCache.getInstance().getValueByKey("AAE ATL Number Prefix");
        String atlEnd = SystemSettingCache.getInstance().getValueByKey("AAE ATL Number End");
        String atlNumber = AppUtils.leftPad(atlCode, atlEnd.length(), "0".charAt(0));
        if (StringUtils.isEmpty(atlCode)) {
            atlCode = "1";
        }
        atlCode = atlPrefix.concat(atlNumber);

        AppUtils.createBarCode(atlCode, "png", this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png", 130, 15);
        File imgAtlCode = new File(this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            imgAtlCode = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }

        byte[] imgBytesAtlCode = AppUtils.readContentIntoByteArray(imgAtlCode);
        byte[] imgBytesAsBase64AtlCode = Base64.encodeBase64(imgBytesAtlCode);
        String imgDataAsBase64AtlCode = new String(imgBytesAsBase64AtlCode);
        String imgAsBase64AtlCode = "data:image/png;base64," + imgDataAsBase64AtlCode;
        historyViewAirbillModel.setImageBarcode3(imgAsBase64AtlCode);
        historyViewAirbillModel.setPieceBarcode3(atlCode);

        if (detailInfoModel.getReferenceNo().equals("")) {
            detailInfoModel.setReferenceNo(detailInfoModel.getCustomerCode());
        }

        String routingCode = detailInfoModel.getProductContentCode().concat(detailInfoModel.getrPostalCode()).concat(detailInfoModel.getPrimaryPort());
        AppUtils.createBarCode(routingCode, "png", this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png", 200, 60);
        File imgRoutingCode = new File(this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            imgRoutingCode = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }

        byte[] imgBytesRoutingCode = AppUtils.readContentIntoByteArray(imgRoutingCode);
        byte[] imgBytesAsBase64RoutingCode = Base64.encodeBase64(imgBytesRoutingCode);
        String imgDataAsBase64RoutingCode = new String(imgBytesAsBase64RoutingCode);
        String imgAsBase64RoutingCode = "data:image/png;base64," + imgDataAsBase64RoutingCode;
        historyViewAirbillModel.setImageBarcode(imgAsBase64RoutingCode);
        historyViewAirbillModel.setPieceBarcode(routingCode);

        if (StringUtils.isEmpty(detailInfoModel.getPackageType())) {
            detailInfoModel.setPackageType("CARTON");
        }

        String freightId = "A".concat(detailInfoModel.getTracking()).concat(articleId);
        AppUtils.createBarCode(freightId, "png", this.getServerPath("/barcode") + "/AEE_" + shipmentId + count + ".png", 250, 75);
        File img = new File(this.getServerPath("/barcode") + "/AEE_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }

        historyViewAirbillModel.setArticleId(articleId);
        historyViewAirbillModel.setPicesCount(count.toString());
        byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        historyViewAirbillModel.setImageBarcode2(imgAsBase64);
        historyViewAirbillModel.setPieceBarcode2(freightId);
        historyViewAirbillModel.setWeight(piece.getDeadWeight());
    }

    protected void prepareDataTollIpec(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece) throws Exception {
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
        String pieceBarcode = "6".concat(detailInfoModel.getServiceCode()).concat(productCode).concat(detailInfoModel.getrPostalCode().concat(detailInfoModel.getTracking())).concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
        historyViewAirbillModel.setPieceBarcode(pieceBarcode);
        AppUtils.createBarCode(pieceBarcode, "png", this.getServerPath("/barcode") + "/TOLL_IPEC_" + shipmentId + count + ".png", 300, 90);

        historyViewAirbillModel.setItemCode(detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
        File img = new File(this.getServerPath("/barcode") + "/TOLL_IPEC_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
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
    }

    protected void prepareDataTollPriority(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece) throws Exception {
        String airbillNo = detailInfoModel.getrPostalCode().concat(detailInfoModel.getServiceCode()).concat(detailInfoModel.getConnNumber()).concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
        String reasonForExport = "";
        if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
            reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
        }
        String pieceBarcode = ShipmentUtils.getTollCheckDigit("T".concat(airbillNo));
        String pieceBarcode2 = ShipmentUtils.getTollCheckDigit(detailInfoModel.getConnNumber().concat(airbillNo));
        historyViewAirbillModel.setPieceBarcode(pieceBarcode);
        AppUtils.createBarCode(pieceBarcode, "png", this.getServerPath("/barcode") + "/TOLL_PRIORITY_" + shipmentId + count + ".png", 388, 42);
        AppUtils.createBarCode(pieceBarcode2, "png", this.getServerPath("/barcode") + "/TOLL_PRIORITY_1_" + shipmentId + count + ".png", 388, 42);

        historyViewAirbillModel.setItemCode(detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
        File img = new File(this.getServerPath("/barcode") + "/TOLL_PRIORITY_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }

        historyViewAirbillModel.setPicesCount(count.toString());
        byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

        File img2 = new File(this.getServerPath("/barcode") + "/TOLL_PRIORITY_1_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img2 = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }
        byte[] imgBytes2 = AppUtils.readContentIntoByteArray(img2);
        byte[] imgBytesAsBase642 = Base64.encodeBase64(imgBytes2);
        String imgDataAsBase642 = new String(imgBytesAsBase642);
        String imgAsBase642 = "data:image/png;base64," + imgDataAsBase642;
        historyViewAirbillModel.setDimWeight(piece.getWeight());
        historyViewAirbillModel.setImageBarcode(imgAsBase64);
        historyViewAirbillModel.setImageBarcode2(imgAsBase642);
        historyViewAirbillModel.setWeight(piece.getDeadWeight());
        historyViewAirbillModel.setDimession(piece.getDimension());
        historyViewAirbillModel.setReasonForExport(reasonForExport);
    }

    protected void prepareDataTntInt(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece) throws Exception {
        historyViewAirbillModel.setPieceBarcode(detailInfoModel.getTracking());
        String reasonForExport = "";
        if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
            reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
        }
        AppUtils.createBarCode(detailInfoModel.getTracking(), "png", this.getServerPath("/barcode") + "/TNT_INT_" + shipmentId + count + ".png", 300, 90);

        File img = new File(this.getServerPath("/barcode") + "/TNT_INT_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }
        historyViewAirbillModel.setPicesCount(count.toString());
        byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

        File imgTnt = new File(this.getServerPath("/images") + "/tnt.png");
        byte[] imgBytesTnt = AppUtils.readContentIntoByteArray(imgTnt);
        byte[] imgBytesAsBase64Tnt = Base64.encodeBase64(imgBytesTnt);
        String imgDataAsBase64Tnt = new String(imgBytesAsBase64Tnt);
        String imgAsBase64Tnt = "data:image/png;base64," + imgDataAsBase64Tnt;

        historyViewAirbillModel.setTntLogo(imgAsBase64Tnt);
        historyViewAirbillModel.setImageBarcode(imgAsBase64);
        historyViewAirbillModel.setWeight(piece.getDeadWeight());
        historyViewAirbillModel.setDimession(piece.getDimension());
        historyViewAirbillModel.setReasonForExport(reasonForExport);
    }

    protected void prepareDataTntDom(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece) throws Exception {
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
        AppUtils.createBarCode("6104".concat(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString())).concat("0").concat(detailInfoModel.getrPostalCode()).concat("0"), "png", this.getServerPath("/barcode") + "/TNT_DOM_" + shipmentId + count + ".png", 300, 90);
        historyViewAirbillModel.setPicesCount(count.toString());

        File img = new File(this.getServerPath("/barcode") + "/TNT_DOM_" + shipmentId + count + ".png");
        if (detailInfoModel.getStatus().equals("1")) {
            img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
        }
        byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        historyViewAirbillModel.setImageBarcode(imgAsBase64);
        historyViewAirbillModel.setWeight(piece.getDeadWeight());
        historyViewAirbillModel.setDestinationPortDescription(destinationPortDescription);
        historyViewAirbillModel.setSortBin(sortBin);
        historyViewAirbillModel.setExPort(exPort);
        historyViewAirbillModel.setStrDanger(strDanger);
        historyViewAirbillModel.setReasonForExport(reasonForExport);
    }

    protected boolean validViewAirbill() {
        if (StringUtils.isEmpty(this.getShipmentId())) {
            addFieldError("shipmentId", "Please choice a shipment!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareHistoryDetail() throws Exception {
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(this.getDetailFilterModel(), HistoryDetailFilter.class);
        this.setDetailInfoModel(ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class));
        detailInfoModel.setBaseCharge(detailInfoModel.getBaseCharge());
        this.setPieceModels(ModelUtils.createListModelFromVo(detailService.selectPieceByIdNonGroup(Long.parseLong(shipmentId)), PieceModel.class));
        this.setDetailPieceModels(ModelUtils.createListModelFromVo(detailService.selectPieceInfo(filter, true), HistoryDetailPieceModel.class));
        this.setDetailAccessorialModels(ModelUtils.createListModelFromVo(detailService.selectHistoryDetailAccessorial(filter), HistoryDetailAccessorialModel.class));
    }

    protected void prepareHistoryDetailFilter() throws Exception {
        if (validHistoryDetail()) {
            HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
            detailFilterModelN.setShipmentId(shipmentId);
            detailFilterModelN.setLbToKg("0.45359237");
            detailFilterModelN.setInToCm("2.54");
            detailFilterModelN.setWeightValue("5000");
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
            HistoryDetailFilter filter = new HistoryDetailFilter();
            filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
            historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
            detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
            this.setDetailFilterModel(detailFilterModelN);
        }
    }

    protected boolean validHistoryDetail() {
        if (StringUtils.isEmpty(shipmentId)) {
            addFieldError("detailFilterModel.shipmentId", "Please select a shipment!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public HistoryDetailFilterModel getDetailFilterModel() {
        return detailFilterModel;
    }

    public void setDetailFilterModel(HistoryDetailFilterModel detailFilterModel) {
        this.detailFilterModel = detailFilterModel;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<HistoryDetailAccessorialModel> getDetailAccessorialModels() {
        return detailAccessorialModels;
    }

    public void setDetailAccessorialModels(List<HistoryDetailAccessorialModel> detailAccessorialModels) {
        this.detailAccessorialModels = detailAccessorialModels;
    }

    public List<HistoryDetailPieceModel> getDetailPieceModels() {
        return detailPieceModels;
    }

    public void setDetailPieceModels(List<HistoryDetailPieceModel> detailPieceModels) {
        this.detailPieceModels = detailPieceModels;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

    public String getAwbBarcode() {
        return awbBarcode;
    }

    public void setAwbBarcode(String awbBarcode) {
        this.awbBarcode = awbBarcode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public List<HistoryViewAirbillModel> getListPieceViewAirbill() {
        return listPieceViewAirbill;
    }

    public void setListPieceViewAirbill(List<HistoryViewAirbillModel> listPieceViewAirbill) {
        this.listPieceViewAirbill = listPieceViewAirbill;
    }

    public List<PieceModel> getPieceModels() {
        return pieceModels;
    }

    public void setPieceModels(List<PieceModel> pieceModels) {
        this.pieceModels = pieceModels;
    }

    public String getPieceBarcode() {
        return pieceBarcode;
    }

    public void setPieceBarcode(String pieceBarcode) {
        this.pieceBarcode = pieceBarcode;
    }

    public String getTntBarcode() {
        return tntBarcode;
    }

    public void setTntBarcode(String tntBarcode) {
        this.tntBarcode = tntBarcode;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getPathFileView() {
        return pathFileView;
    }

    public void setPathFileView(String pathFileView) {
        this.pathFileView = pathFileView;
    }

    public List<HistoryProductAirbillModel> getProductAirbillModels() {
        return productAirbillModels;
    }

    public void setProductAirbillModels(List<HistoryProductAirbillModel> productAirbillModels) {
        this.productAirbillModels = productAirbillModels;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

}