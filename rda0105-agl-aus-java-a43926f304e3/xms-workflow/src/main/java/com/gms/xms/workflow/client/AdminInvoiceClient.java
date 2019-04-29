package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from AdminInvoiceClient
 * <p>
 * Author TanDT Date May 21, 2015
 */
public class AdminInvoiceClient extends WorkflowBaseClient {

    public AdminInvoiceClient(Map<String, String> context) {
        super(context);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public CreditNoteResponse managerCreditNotes(CreditNoteRequest request) throws Exception {
        CreditNoteResponse response = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        // prepare Para for task
        context.put(Attributes.ADMIN_USER_INFO_LOGIN, GsonUtils.toGson(request.getUserVo()));
        context.put(Attributes.CREDIT_NOTES_REQUEST, GsonUtils.toGson(request));
        context.put(Attributes.CREDIT_NOTES_RESPONSE, GsonUtils.toGson(response));
        context.put(Attributes.WFP_NAME, "Wfl-InvoicingManageCreditNotes");
        context = WorkFlowManager.getInstance().process(context);
        response = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTES_RESPONSE), CreditNoteResponse.class);
        List<CustomerVo> listCustomerVos = GsonUtils.fromGson(context.get(Attributes.LIST_CUSTOMER_CODE_BY_LOGIN), new TypeToken<List<CustomerVo>>() {
        }.getType());

        List<CreditNoteVo> listCreditNoteVos = GsonUtils.fromGson(context.get(Attributes.LIST_CREDIT_NOTE_BY_CUSTOMER_CODE), new TypeToken<List<CreditNoteVo>>() {
        }.getType());

        response.setListCustomerVos(listCustomerVos);
        response.setCreditNoteVos(listCreditNoteVos);
        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        return response;
    }
}
