package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileResultVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.Map;

public class WebshipHistory2Client extends WorkflowBaseClient {

    public WebshipHistory2Client(Map<String, String> addInfo) {
        super(addInfo);
    }

    public HistoryViewFileResultVo viewThermalLable(HistoryViewFileRequest historyViewAirbillRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, historyViewAirbillRequest);
        context.put(Attributes.WFP_NAME, "Wfl-DHLViewThermalLable");
        HistoryViewFileResultVo historyViewAirbillResultVo = new HistoryViewFileResultVo();
        context = WorkFlowManager2.getInstance().process(context);
        if (!ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
            historyViewAirbillResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
        }

        return historyViewAirbillResultVo;
    }

    public HistoryViewFileResultVo viewPackingList(HistoryViewFileRequest historyViewFileRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, historyViewFileRequest);
        context.put(Attributes.WFP_NAME, "Wfl-DHLViewPakingList");
        HistoryViewFileResultVo historyViewPackingListResultVo = new HistoryViewFileResultVo();
        context = WorkFlowManager2.getInstance().process(context);
        if (!ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
            historyViewPackingListResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
        }

        return historyViewPackingListResultVo;
    }

    public HistoryViewFileResultVo viewCommercialInvoice(HistoryViewFileRequest historyViewFileRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, historyViewFileRequest);
        context.put(Attributes.WFP_NAME, "Wfl-DHLViewCommercialInvoice");
        HistoryViewFileResultVo historyViewCommercialResultVo = new HistoryViewFileResultVo();
        context = WorkFlowManager2.getInstance().process(context);
        if (!ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
            historyViewCommercialResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
        }

        return historyViewCommercialResultVo;
    }

    public HistoryViewFileResultVo viewTntIntAirbill(HistoryViewFileRequest historyViewFileRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, historyViewFileRequest);
        context.put(Attributes.WFP_NAME, "Wfl-TntViewAirbill");
        HistoryViewFileResultVo historyViewAirbillResultVo = new HistoryViewFileResultVo();
        context = WorkFlowManager2.getInstance().process(context);
        if (!ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
            historyViewAirbillResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
        }

        return historyViewAirbillResultVo;
    }

    public HistoryViewFileResultVo viewTntIntThermalLable(HistoryViewFileRequest historyViewAirbillRequest) throws Exception {
        ContextBase2 context = new ContextBase2(this.getAddInfo());
        context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, historyViewAirbillRequest);
        context.put(Attributes.WFP_NAME, "Wfl-TntIntViewThermalLable");
        HistoryViewFileResultVo historyViewAirbillResultVo = new HistoryViewFileResultVo();
        context = WorkFlowManager2.getInstance().process(context);
        if (!ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
            historyViewAirbillResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
        }

        return historyViewAirbillResultVo;
    }

}
