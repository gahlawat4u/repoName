package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.admin.ratesheets.RateSheetInfoModel;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.ServiceTypeVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.render.ratesheets.IRateSheetsRender;
import com.gms.xms.workflow.render.ratesheets.RateSheetsRenderImp;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from Apr 6, 2016 4:48:20 PM
 * <p>
 * Author dattrinh
 */
public class ViewRateSheetController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String viewRequest;
    private List<String> listViewRequest;
    private RateSheetInfoModel rateSheet;
    private RateSheetInfoModel perWeightRateSheet;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            // Valid view rate sheet request.
            ViewRateSheetRequestVo rateSheetRequestVo = GsonUtils.fromGson(this.getViewRequest(), ViewRateSheetRequestVo.class);
            if (rateSheetRequestVo == null) {
                throw new CustomException("No view rate sheet request.");
            }
            // Execute work flow to get rate sheet data.
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.VIEW_RATE_SHEET_REQUEST_VO, rateSheetRequestVo);
            context.put(Attributes.WFP_NAME, "Wfl-ViewCustomerRateSheet");
            // Check error and get data.
            WorkFlowManager2.getInstance().process(context);
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            } else {
                RateSheetInfoModel rateSheet = context.get(Attributes.RATE_SHEET_RESULT);
                RateSheetInfoModel perWeightRateSheet = context.get(Attributes.PER_WEIGHT_RATE_SHEET_RESULT);
                this.setRateSheet(rateSheet);
                this.setPerWeightRateSheet(perWeightRateSheet);
                // Get service type info.
                ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
                switch (serviceTypeVo.getServiceId()) {
                    case 3: // TNT Dom Service
                        return "tnt_dom";
                    case 72: // Star Track service.
                        // If the shipment type is Road Express or Premium Air
                        // Freight then show rate sheet look like TNT Dom.
                        if (serviceTypeVo.getShipmentTypeId() == 228 || serviceTypeVo.getShipmentTypeId() == 229) {
                            return "tnt_dom";
                        } else {
                            return "success";
                        }
                    default:
                        return "success";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showByAirbill() {
        try {
            // Valid airbill info.
            if (StringUtils.isBlank(this.getShipmentId()) || StringUtils.isBlank(this.getAirbillNumber())) {
                throw new CustomException("Invalid shipment id or airbill number.");
            } else {
                try {
                    Long.valueOf(this.getShipmentId());
                } catch (Exception e) {
                    throw new CustomException("Invalid shipment id.");
                }
            }
            // Execute work flow to get rate sheet data.
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
            context.put(Attributes.AIRBILL_NUMBER, this.getAirbillNumber());
            context.put(Attributes.WFP_NAME, "Wfl-ViewAirbillRateSheet");
            // Check error and get data.
            WorkFlowManager2.getInstance().process(context);
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            } else {
                RateSheetInfoModel rateSheet = context.get(Attributes.RATE_SHEET_RESULT);
                RateSheetInfoModel perWeightRateSheet = context.get(Attributes.PER_WEIGHT_RATE_SHEET_RESULT);
                this.setRateSheet(rateSheet);
                this.setPerWeightRateSheet(perWeightRateSheet);
                // Get service type info.
                ServiceTypeVo serviceTypeVo = context.get(Attributes.SERVICE_TYPE_VO);
                switch (serviceTypeVo.getServiceId()) {
                    case 3: // TNT Dom Service
                        return "tnt_dom";
                    case 72: // Star Track service.
                        // If the shipment type is Road Express or Premium Air
                        // Freight then show rate sheet look like TNT Dom.
                        if (serviceTypeVo.getShipmentTypeId() == 228 || serviceTypeVo.getShipmentTypeId() == 229) {
                            return "tnt_dom";
                        } else {
                            return "success";
                        }
                    default:
                        return "success";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportPdf() {
        try {
            List<Map<String, RateSheetInfoModel>> outboundRateSheets = new LinkedList<>();
            List<Map<String, RateSheetInfoModel>> inboundboundRateSheets = new LinkedList<>();
            String customerCode = "";
            Integer shipmentTypeId = 0;
            for (String request : this.getListViewRequest()) {
                // Valid view rate sheet request.
                ViewRateSheetRequestVo rateSheetRequestVo = GsonUtils.fromGson(request, ViewRateSheetRequestVo.class);
                if (rateSheetRequestVo == null) {
                    throw new CustomException("No view rate sheet request.");
                }
                customerCode = rateSheetRequestVo.getCustomerCode();
                shipmentTypeId = rateSheetRequestVo.getShipmentTypeId();
                // Execute work flow to get rate sheet data.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.VIEW_RATE_SHEET_REQUEST_VO, rateSheetRequestVo);
                context.put(Attributes.WFP_NAME, "Wfl-ViewCustomerRateSheet");
                // Check error and get data.
                WorkFlowManager2.getInstance().process(context);
                if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                } else {
                    RateSheetInfoModel rateSheet = context.get(Attributes.RATE_SHEET_RESULT);
                    RateSheetInfoModel perWeightRateSheet = context.get(Attributes.PER_WEIGHT_RATE_SHEET_RESULT);
                    if (rateSheet != null) {
                        rateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    if (perWeightRateSheet != null) {
                        perWeightRateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    Map<String, RateSheetInfoModel> rateSheetData = new HashMap<>();
                    rateSheetData.put("rateSheet", rateSheet);
                    rateSheetData.put("perWeightRateSheet", perWeightRateSheet);
                    if (rateSheetRequestVo.getBound() == 0) {
                        outboundRateSheets.add(rateSheetData);
                    } else {
                        inboundboundRateSheets.add(rateSheetData);
                    }
                }
            }
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            Integer carrierId = shipmentTypeVo.getServiceId();
            IRateSheetsRender render = new RateSheetsRenderImp(this.getAddInfoMap());
            String realPath = request.getSession().getServletContext().getRealPath("cover_images") + "/";
            String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/pdf_rate_sheets_" + AppUtils.createMessageReference() + ".pdf";
            render.generatePdfFileRateSheets(outboundRateSheets, inboundboundRateSheets, customerCode, carrierId, realPath, outPutFilePath);
            this.setFileName("pdf_rate_sheets.pdf");
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportExcel() {
        try {
            List<Map<String, RateSheetInfoModel>> outboundRateSheets = new LinkedList<>();
            List<Map<String, RateSheetInfoModel>> inboundboundRateSheets = new LinkedList<>();
            String customerCode = "";
            Integer shipmentTypeId = 0;
            for (String request : this.getListViewRequest()) {
                // Valid view rate sheet request.
                ViewRateSheetRequestVo rateSheetRequestVo = GsonUtils.fromGson(request, ViewRateSheetRequestVo.class);
                if (rateSheetRequestVo == null) {
                    throw new CustomException("No view rate sheet request.");
                }
                customerCode = rateSheetRequestVo.getCustomerCode();
                shipmentTypeId = rateSheetRequestVo.getShipmentTypeId();
                // Execute work flow to get rate sheet data.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.VIEW_RATE_SHEET_REQUEST_VO, rateSheetRequestVo);
                context.put(Attributes.WFP_NAME, "Wfl-ViewCustomerRateSheet");
                // Check error and get data.
                WorkFlowManager2.getInstance().process(context);
                if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                } else {
                    RateSheetInfoModel rateSheet = context.get(Attributes.RATE_SHEET_RESULT);
                    RateSheetInfoModel perWeightRateSheet = context.get(Attributes.PER_WEIGHT_RATE_SHEET_RESULT);
                    if (rateSheet != null) {
                        rateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    if (perWeightRateSheet != null) {
                        perWeightRateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    Map<String, RateSheetInfoModel> rateSheetData = new HashMap<>();
                    rateSheetData.put("rateSheet", rateSheet);
                    rateSheetData.put("perWeightRateSheet", perWeightRateSheet);
                    if (rateSheetRequestVo.getBound() == 0) {
                        outboundRateSheets.add(rateSheetData);
                    } else {
                        inboundboundRateSheets.add(rateSheetData);
                    }
                }
            }
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            Integer carrierId = shipmentTypeVo.getServiceId();
            IRateSheetsRender render = new RateSheetsRenderImp(this.getAddInfoMap());
            String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/xls_rate_sheets_" + AppUtils.createMessageReference() + ".xls";
            render.generateHtmlXlsFileRateSheets(outboundRateSheets, inboundboundRateSheets, customerCode, carrierId, outPutFilePath);
            this.setFileName("rate_sheets.xls");
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportHtml() {
        try {
            List<Map<String, RateSheetInfoModel>> outboundRateSheets = new LinkedList<>();
            List<Map<String, RateSheetInfoModel>> inboundboundRateSheets = new LinkedList<>();
            String customerCode = "";
            Integer shipmentTypeId = 0;
            for (String request : this.getListViewRequest()) {
                // Valid view rate sheet request.
                ViewRateSheetRequestVo rateSheetRequestVo = GsonUtils.fromGson(request, ViewRateSheetRequestVo.class);
                if (rateSheetRequestVo == null) {
                    throw new CustomException("No view rate sheet request.");
                }
                customerCode = rateSheetRequestVo.getCustomerCode();
                shipmentTypeId = rateSheetRequestVo.getShipmentTypeId();
                // Execute work flow to get rate sheet data.
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.VIEW_RATE_SHEET_REQUEST_VO, rateSheetRequestVo);
                context.put(Attributes.WFP_NAME, "Wfl-ViewCustomerRateSheet");
                // Check error and get data.
                WorkFlowManager2.getInstance().process(context);
                if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                } else {
                    RateSheetInfoModel rateSheet = context.get(Attributes.RATE_SHEET_RESULT);
                    RateSheetInfoModel perWeightRateSheet = context.get(Attributes.PER_WEIGHT_RATE_SHEET_RESULT);
                    if (rateSheet != null) {
                        rateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    if (perWeightRateSheet != null) {
                        perWeightRateSheet.setShipmentTypeId(String.valueOf(shipmentTypeId));
                    }
                    Map<String, RateSheetInfoModel> rateSheetData = new HashMap<>();
                    rateSheetData.put("rateSheet", rateSheet);
                    rateSheetData.put("perWeightRateSheet", perWeightRateSheet);
                    if (rateSheetRequestVo.getBound() == 0) {
                        outboundRateSheets.add(rateSheetData);
                    } else {
                        inboundboundRateSheets.add(rateSheetData);
                    }
                }
            }
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
            Integer carrierId = shipmentTypeVo.getServiceId();
            IRateSheetsRender render = new RateSheetsRenderImp(this.getAddInfoMap());
            String realPath = WebUtils.getContextPathURL(this.request);
            String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/print_rate_sheets_" + AppUtils.createMessageReference() + ".html";
            render.generateHTMLPrintRateSheets(outboundRateSheets, inboundboundRateSheets, customerCode, carrierId, realPath, outPutFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outPutFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(String viewRequest) {
        this.viewRequest = viewRequest;
    }

    public RateSheetInfoModel getRateSheet() {
        return rateSheet;
    }

    public void setRateSheet(RateSheetInfoModel rateSheet) {
        this.rateSheet = rateSheet;
    }

    public RateSheetInfoModel getPerWeightRateSheet() {
        return perWeightRateSheet;
    }

    public void setPerWeightRateSheet(RateSheetInfoModel perWeightRateSheet) {
        this.perWeightRateSheet = perWeightRateSheet;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public List<String> getListViewRequest() {
        return listViewRequest;
    }

    public void setListViewRequest(List<String> listViewRequest) {
        this.listViewRequest = listViewRequest;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
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
}
