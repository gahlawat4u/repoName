package com.gms.xms.workflow.client;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.admin.InvoiceSettingFilter;
import com.gms.xms.model.CreditNoteModel;
import com.gms.xms.model.invoicing.CreditNoteInfoModel;
import com.gms.xms.model.invoicing.CreditNoteShowListModel;
import com.gms.xms.model.invoicing.ManageCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.persistence.dao.admin.InvoiceSettingDao;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.request.SendCreditNotesRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.gms.xms.workflow.client.integration.response.SendCreditNotesResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from SendCreditNotesClient
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class SendCreditNotesClient extends WorkflowBaseClient {

    public SendCreditNotesClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public SendCreditNotesResponse sendCreditNotes(SendCreditNotesRequest sendCreditNotesRequest) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        List<CreditNoteVo> creditNoteVos = sendCreditNotesRequest.getCreditNoteVos();
        context.put(Attributes.STR_ADD_INFO, GsonUtils.toGson(sendCreditNotesRequest.getAddInfo()));
        context.put(Attributes.CREDIT_NOTE_LIST, GsonUtils.toGson(creditNoteVos));
        context.put(Attributes.TMP_PATH, sendCreditNotesRequest.getTmpPath());
        context.put(Attributes.WFP_NAME, "Wfl-SendCreditNotes");
        context = WorkFlowManager.getInstance().process(context);

        SendCreditNotesResponse sendCreditNotesResponse = new SendCreditNotesResponse();
        sendCreditNotesResponse.setErrorCode(context.getErrorCode());
        sendCreditNotesResponse.setErrorMessage(context.getErrorMessage());
        return sendCreditNotesResponse;
    }

    public CreditNoteResponse getDateList(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        CreditNoteDao creditNoteDao = new CreditNoteDao();

        List<String> dateList = new LinkedList<String>();
        List<CreditNoteVo> creditNoteVos = creditNoteDao.selectCreateDateListForSendCreditNotes(creditNoteFilter);
        List<CreditNoteModel> creditNoteModels = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
        for (CreditNoteModel creditNoteModel : creditNoteModels) {
            dateList.add(creditNoteModel.getCreateDate());
        }
        creditNoteResponse.setDateList(dateList);
        return creditNoteResponse;
    }

    public void renderCreditNotesHtmlFile(ManageCreditNoteModel dataModel, String outPutFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/invoicing/printing_tools/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

        CreditNoteInfoModel creditNoteInfoModel = dataModel.getCreditNoteInfo();
        Integer credits = Integer.parseInt(creditNoteInfoModel.getCredits());
        List<CreditNoteShowListModel> creditNoteShowListModelsCheck = dataModel.getListCreditNoteAdj();
        List<CreditNoteShowListModel> creditNoteShowListModels = new LinkedList<>();
        for (int i = 0; i < creditNoteShowListModelsCheck.size(); i++) {
            CreditNoteShowListModel creditNoteShowListModel = creditNoteShowListModelsCheck.get(i);
            Float amount = Float.parseFloat(creditNoteShowListModel.getShipmentAmount());
            if (amount != 0) {
                creditNoteShowListModels.add(creditNoteShowListModel);
            }
        }
        dataModel.setListCreditNoteAdj(creditNoteShowListModels);
        credits = creditNoteShowListModels.size();

        String invSignature = "";
        // Get invoice signature if country is France
        String defaultCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
        if (StringUtils.isNotBlank(defaultCountry) && defaultCountry.equalsIgnoreCase("70")) {
            InvoiceSettingDao invoiceSettingDao = new InvoiceSettingDao();
            String franchiseCodeStr = String.valueOf(dataModel.getCreditNoteInfo().getCustomerCode()).substring(0, 3) + "00001";
            Long franchiseCode = Long.parseLong(franchiseCodeStr);
            InvoiceSettingFilter filter = new InvoiceSettingFilter();
            filter.setFranchiseCode(franchiseCode);
            filter.setInvSettingName("Invoice Signature");
            invSignature = invoiceSettingDao.getInvoiceSetting(filter);
        }

        creditNoteInfoModel.setCredits(String.valueOf(credits));
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("logo", imgAsBase64);
        data.put("creditInfo", creditNoteInfoModel);
        data.put("listAdj", dataModel.getListCreditNoteAdj());
        data.put("system", dataModel.getInfoSystemAdmin());
        data.put("gstSummary", dataModel.getGstSummary());
        data.put("invSignature", invSignature);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outPutFilePath, false, "templates/pdf/invoicing/printing_tools/send_credit_notes.ftl", data);
    }
}
