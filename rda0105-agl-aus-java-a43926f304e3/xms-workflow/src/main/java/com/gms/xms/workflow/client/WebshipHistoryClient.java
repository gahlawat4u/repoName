package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentModel;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.client.integration.request.HistoryRequest;
import com.gms.xms.workflow.client.integration.request.ReshipRequest;
import com.gms.xms.workflow.client.integration.request.SearchHistoryRequest;
import com.gms.xms.workflow.client.integration.response.HistoryResponse;
import com.gms.xms.workflow.client.integration.response.ReshipResponse;
import com.gms.xms.workflow.client.integration.response.SearchHistoryResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebshipHistoryClient extends WorkflowBaseClient {

    public WebshipHistoryClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public ContextBase getWebshipHistory(ShipmentFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.SHIPMENT_FILLTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-WebshipHistory");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getWebshipHistoryCount(ShipmentFilter fillter) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.SHIPMENT_FILLTER, GsonUtils.toGson(fillter));
        context.put(Attributes.WFP_NAME, "Wfl-WebshipHistoryCount");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    public ContextBase getWebshipHistoryDetail(long shipmentId) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WEBSHIP_HISTORY_SHIPMENTID, String.valueOf(shipmentId));
        context.put(Attributes.WFP_NAME, "Wfl-WebshipHistoryDetail");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    /**
     * @param reshipRequest
     * @return ReshipResponse
     * @throws Exception
     */
    public ReshipResponse reshipHistory(ReshipRequest reshipRequest) throws Exception {
        ReshipResponse response = new ReshipResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.RESHIP_HISTORY_RESQUEST, String.valueOf(reshipRequest.getShipmentId()));
        context.put(Attributes.WFP_NAME, "Wfl-ReshipHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.RESHIP_HISTORY_RESPONSE), ReshipResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public SearchHistoryResponse searchHistoryResponse(SearchHistoryRequest searchHistoryRequest) throws Exception {
        SearchHistoryResponse response = new SearchHistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.RESHIP_HISTORY_RESQUEST, GsonUtils.toGson(searchHistoryRequest));
        context.put(Attributes.WFP_NAME, "Wfl-SearchHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_HISTORY_LIST_RESULT), SearchHistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse voidShipmentHistory(HistoryRequest historyRequest) throws Exception {
        HistoryResponse response = new HistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(historyRequest));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-VoidShipmentHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse voidShipmentHistoryAc(HistoryRequest historyRequest) throws Exception {
        HistoryResponse response = new HistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(historyRequest));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-VoidShipmentHistoryAc");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse webshipHistory(HistoryRequest historyRequest) throws Exception {
        HistoryResponse response = new HistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(historyRequest));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-WebshipHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public void renderThermaLabel(long shipmentId, String outputFilePath) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WEBSHIP_HISTORY_SHIPMENTID, String.valueOf(shipmentId));
        context.put(Attributes.WFP_NAME, "Wfl-ThermaLabel");
        context = WorkFlowManager.getInstance().process(context);
        ShipmentVo shipment = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_HISTORY_DETAIL_RESULT), ShipmentVo.class);
        ShipmentModel shipmentDetail = ModelUtils.createModelFromVo(shipment, ShipmentModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("shipmentDetail", shipmentDetail);
        data.put("bortop", "style=\"border-top-style:0;\"");
        data.put("OutboundSortCode", "OutboundSortCodetest");
        data.put("InboundSortCode", "InboundSortCodetest");
        data.put("frtAccount", "");
        data.put("totalweight", "");
        data.put("securityInspection", "");
        data.put("dhllogo", "");
        data.put("barCode", "<br/> <b>AWB Barcode Not Available </b>");

        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/thermal_label.ftl", data);
    }

    public HistoryResponse showHistoryDetail(HistoryRequest resquest) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(resquest));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-DetailHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse getScheduleCollectionWebship(HistoryRequest resquest) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(resquest));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-ScheduleCollectionWebship");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse scheduleCollectionWebship(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-ScheduleCollectionWebshipUpdateInsert");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse showNoteDetail(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-GetNoteByShipmentId");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        return response;
    }

    public HistoryResponse insertShipmentNote(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        // Filter
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-InsertShipmentNote");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public void renderHistoryHtmlReport(ShipmentFilter fillter, String outputFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ContextBase context = getWebshipHistory(fillter);
        List<ShipmentModel> listShipment = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_HISTORY_LIST_RESULT), new TypeToken<List<ShipmentVo>>() {
        }.getType());
        data.put("shipments", listShipment);
        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/history.ftl", data);

    }

    public void renderHistoryHtmlReport(HistoryRequest historyRequest, String outputFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();

        historyRequest.getShipmentFilter().setPage(1);
        historyRequest.getShipmentFilter().setRecordSize(10001);

        HistoryResponse response = webshipHistory(historyRequest);
        List<ShipmentModel> listShipment = ModelUtils.createListModelFromVo(response.getShipmentVos(), ShipmentModel.class);

        while (response.getCountShipment().intValue() >= historyRequest.getShipmentFilter().getRecordSize().intValue()) {
            historyRequest.getShipmentFilter().setPage(historyRequest.getShipmentFilter().getPage() + 1);
            response = webshipHistory(historyRequest);
            if (!response.getShipmentVos().isEmpty()) {
                listShipment.addAll(ModelUtils.createListModelFromVo(response.getShipmentVos(), ShipmentModel.class));
            } else {
                break;
            }
        }

        data.put("shipments", listShipment);
        AppUtils.template2File(outputFilePath, false, "templates/pdf/history/history.ftl", data);

    }

    public void renderHistoryExcelReport(HistoryRequest historyRequest, String outputFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();

        historyRequest.getShipmentFilter().setPage(1);
        historyRequest.getShipmentFilter().setRecordSize(10001);

        HistoryResponse response = webshipHistory(historyRequest);
        List<ShipmentModel> listShipment = ModelUtils.createListModelFromVo(response.getShipmentVos(), ShipmentModel.class);

        while (response.getCountShipment().intValue() >= historyRequest.getShipmentFilter().getRecordSize().intValue()) {
            historyRequest.getShipmentFilter().setPage(historyRequest.getShipmentFilter().getPage() + 1);
            response = webshipHistory(historyRequest);
            if (!response.getShipmentVos().isEmpty()) {
                listShipment.addAll(ModelUtils.createListModelFromVo(response.getShipmentVos(), ShipmentModel.class));
            } else {
                break;
            }
        }

        data.put("listShipment", listShipment);
        InputStream is = new ClassPathResource("templates/pdf/history/history.xlsx").getInputStream();
        XLSTransformer transformer = new XLSTransformer();

        Workbook resultWorkbook = transformer.transformXLS(is, data);
        saveWorkbook(resultWorkbook, outputFilePath);
    }

    public HistoryResponse sendAirbillHistory(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-SendAirbillHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse getAwbBarcodeHistory(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-WebshipHistoryViewAwbHistory");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
        resultWorkbook.write(os);
        os.flush();
        os.close();
    }

    public HistoryResponse multiVoidProceedToVoid(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-MultiVoidProceedToVoid");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }

    public HistoryResponse multiVoidProceedToUpdateCollection(HistoryRequest request) throws Exception {
        HistoryResponse response = new HistoryResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.HISTORY_RESQUEST, GsonUtils.toGson(request));
        context.put(Attributes.HISTORY_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-MultiVoidProceedToUpdateCollection");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.HISTORY_RESPONSE), HistoryResponse.class);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }
}
