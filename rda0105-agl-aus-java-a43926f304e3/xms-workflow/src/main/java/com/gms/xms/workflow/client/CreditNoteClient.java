package com.gms.xms.workflow.client;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.admin.InvoiceSettingFilter;
import com.gms.xms.model.CreditNoteModel;
import com.gms.xms.model.CreditNotesGSTSummaryModel;
import com.gms.xms.model.invoicing.ManageCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.persistence.dao.admin.InvoiceSettingDao;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.util.*;

/**
 * Posted from CreditNoteClient
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class CreditNoteClient extends WorkflowBaseClient {
    private static final Log log = LogFactory.getLog(CreditNoteClient.class);

    public CreditNoteClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    public CreditNoteResponse getFreezeDateList(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-GetFreezeCreditNoteDateList");
        context = WorkFlowManager.getInstance().process(context);

        if (context.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            List<String> dateList = new LinkedList<String>();
            List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_RESULT), new TypeToken<List<CreditNoteVo>>() {
            }.getType());
            List<CreditNoteModel> creditNoteModels = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
            for (CreditNoteModel creditNoteModel : creditNoteModels) {
                dateList.add(creditNoteModel.getCreateDate());
            }
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setDateList(dateList);
        } else {
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setErrorMessage(context.getErrorMessage());
        }
        return creditNoteResponse;
    }

    public CreditNoteResponse getDateList(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCreditNoteCreateDateList");
        context = WorkFlowManager.getInstance().process(context);

        if (context.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            List<String> dateList = new LinkedList<String>();
            List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_RESULT), new TypeToken<List<CreditNoteVo>>() {
            }.getType());
            List<CreditNoteModel> creditNoteModels = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
            for (CreditNoteModel creditNoteModel : creditNoteModels) {
                dateList.add(creditNoteModel.getCreateDate());
            }
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setDateList(dateList);
        } else {
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setErrorMessage(context.getErrorMessage());
        }
        return creditNoteResponse;
    }

    public CreditNoteResponse getCreditNotesList(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCreditNotesList");
        context = WorkFlowManager.getInstance().process(context);

        if (context.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            List<CreditNoteVo> creditNoteVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_LIST_RESULT), new TypeToken<List<CreditNoteVo>>() {
            }.getType());

            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setCreditNoteVos(creditNoteVos);
        } else {
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setErrorMessage(context.getErrorMessage());
        }
        return creditNoteResponse;
    }

    public CreditNoteResponse freezeCreditNotes(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-FreezeCreditNotes");
        context = WorkFlowManager.getInstance().process(context);

        creditNoteResponse.setErrorCode(context.getErrorCode());
        creditNoteResponse.setErrorMessage(context.getErrorMessage());
        return creditNoteResponse;
    }

    public CreditNoteResponse unfreezeCreditNotes(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-UnfreezeCreditNotes");
        context = WorkFlowManager.getInstance().process(context);

        creditNoteResponse.setErrorCode(context.getErrorCode());
        creditNoteResponse.setErrorMessage(context.getErrorMessage());
        return creditNoteResponse;
    }

    public CreditNoteResponse freezeCreditNotesByCode(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-FreezeCreditNotesByCode");
        context = WorkFlowManager.getInstance().process(context);

        creditNoteResponse.setErrorCode(context.getErrorCode());
        creditNoteResponse.setErrorMessage(context.getErrorMessage());
        return creditNoteResponse;
    }

    public CreditNoteResponse unfreezeCreditNotesByCode(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteFilter creditNoteFilter = creditNoteRequest.getCreditNoteFilter();
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTE_FILTER, GsonUtils.toGson(creditNoteFilter));
        context.put(Attributes.WFP_NAME, "Wfl-UnfreezeCreditNotesByCode");
        context = WorkFlowManager.getInstance().process(context);

        creditNoteResponse.setErrorCode(context.getErrorCode());
        creditNoteResponse.setErrorMessage(context.getErrorMessage());
        return creditNoteResponse;
    }

    /**
     * getCreditNotesDetail
     *
     * @param creditNoteRequest
     * @return
     * @throws Exception
     */
    public CreditNoteResponse getCreditNotesDetail(CreditNoteRequest creditNoteRequest) throws Exception {
        CreditNoteResponse creditNoteResponse = new CreditNoteResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CREDIT_NOTES_CODE, String.valueOf(creditNoteRequest.getCreditNoteCode()));
        context.put(Attributes.WFP_NAME, "Wfl-CreditNoteViewDetail");
        context = WorkFlowManager.getInstance().process(context);

        if (context.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {

            CreditNoteInfoVo creditNoteInfo = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTES_INFO), CreditNoteInfoVo.class);
            List<CreditNoteShowListVo> creditNoteShowList = GsonUtils.fromGson(context.get(Attributes.LIST_ADJ_CREDIT_NOTE), new TypeToken<List<CreditNoteShowListVo>>() {
            }.getType());
            CreditNoteInfoSystemAdminVo infoSystemAdminVo = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTE_SYSTEM_ADMIN), CreditNoteInfoSystemAdminVo.class);

            // Calculate total GST amount and total non GST amount
            Double totalGstAmount = 0D;
            Double totalNonGstAmount = 0D;
            Double taxPercent = 0D;
            try {
                totalGstAmount = creditNoteInfo.getShipmentAmount() + creditNoteInfo.getGstAmount();
            } catch (Exception e) {
                log.error(e);
            }
            try {
                totalNonGstAmount = creditNoteInfo.getNonGstAmount() + creditNoteInfo.getNonShipmentAmount();
            } catch (Exception e) {
                log.error(e);
            }
            try {
                if (creditNoteInfo.getGstAmount() != 0) {
                    taxPercent = (creditNoteInfo.getGstAmount() / creditNoteInfo.getShipmentAmount()) * 100;
                }
            } catch (Exception e) {
                log.error(e);
            }
            creditNoteInfo.setTaxPercent(taxPercent);
            creditNoteInfo.setTotalAmount(totalGstAmount);
            creditNoteInfo.setNonTotalAmount(totalNonGstAmount);

            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setCreditNoteInfo(creditNoteInfo);
            creditNoteResponse.setListAdjustment(creditNoteShowList);
            creditNoteResponse.setInfoSystemAdminVo(infoSystemAdminVo);
        } else {
            creditNoteResponse.setErrorCode(context.getErrorCode());
            creditNoteResponse.setErrorMessage(context.getErrorMessage());
        }
        // Get GST Summary vo
        if (StringUtils.isNotBlank(context.get(Attributes.CREDIT_NOTES_GST_SUMMARY))) {
            List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTES_GST_SUMMARY), new TypeToken<List<CreditNotesGSTSummaryVo>>() {
            }.getType());
            creditNoteResponse.setCreditNotesGSTSummaryVos(creditNotesGSTSummaryVos);
        }
        return creditNoteResponse;
    }

    public CreditNoteVo getCreditNoteByCreditCode(String creditCode) throws DaoException {
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        CreditNoteVo creditNoteVo = creditNoteDao.selectCreditNoteByCreditCode(creditCode);
        return creditNoteVo;
    }

    public void renderHtmlFile(String creditNoteCode, String outputFilePath, String realPath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/invoicing/credit_notes/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteCode(creditNoteCode);
        CreditNoteResponse creditNoteResponse = getCreditNotesDetail(creditNoteRequest);
        ManageCreditNoteVo manageCreditNoteVo = new ManageCreditNoteVo();
        CreditNoteInfoSystemAdminVo creditNoteInfoSystemAdminVo = creditNoteResponse.getInfoSystemAdminVo();
        String mailPayment = creditNoteInfoSystemAdminVo.getMailPaymentToAddress();
        String systemAddress = creditNoteInfoSystemAdminVo.getSystemAddress();
        mailPayment = mailPayment.toUpperCase();
        mailPayment = mailPayment.replaceAll("(\r\n|\n|<BR/>)", "<br />");
        systemAddress = systemAddress.toUpperCase();
        systemAddress = systemAddress.replaceAll("(\r\n|\n|<BR/>)", "<br />");
        creditNoteInfoSystemAdminVo.setMailPaymentToAddress(mailPayment);
        creditNoteInfoSystemAdminVo.setSystemAddress(systemAddress);
        manageCreditNoteVo.setInfoSystemAdmin(creditNoteInfoSystemAdminVo);

        CreditNoteInfoVo creditNoteInfoVo = creditNoteResponse.getCreditNoteInfo();
        String defaultCountryId = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
        if (defaultCountryId.equalsIgnoreCase("70")) { // FR invoice numbering
            InvoiceNumberingService invoiceNumberingService = new InvoiceNumberingService();
            if (creditNoteInfoVo.getStatus() > 0) {
                String newCreditNoteCode = invoiceNumberingService.getCodeWithCounter(creditNoteInfoVo.getCreateDate(), creditNoteInfoVo.getCreditCode(), creditNoteInfoVo.getCustomerCode());
                creditNoteInfoVo.setCreditCode(newCreditNoteCode);
            }
        }
        Integer credits = creditNoteInfoVo.getCredits();
        // remove adjustment which have amount = 0
        List<CreditNoteShowListVo> creditNoteShowListVosCheck = creditNoteResponse.getListAdjustment();
        List<CreditNoteShowListVo> creditNoteShowListVos = new LinkedList<>();
        for (int i = 0; i < creditNoteShowListVosCheck.size(); i++) {
            CreditNoteShowListVo creditNoteShowListVo = creditNoteShowListVosCheck.get(i);
            Double shipmentAmount = creditNoteShowListVo.getShipmentAmount();
            if (shipmentAmount != 0) {
                creditNoteShowListVos.add(creditNoteShowListVo);
            }
        }
        credits = creditNoteShowListVos.size();

        String invSignature = "";
        // Get invoice signature if country is France
        String defaultCountry = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
        if (StringUtils.isNotBlank(defaultCountry) && defaultCountry.equalsIgnoreCase("70")) {
            InvoiceSettingDao invoiceSettingDao = new InvoiceSettingDao();
            String franchiseCodeStr = String.valueOf(creditNoteInfoVo.getCustomerCode()).substring(0, 3) + "00001";
            Long franchiseCode = Long.parseLong(franchiseCodeStr);
            InvoiceSettingFilter filter = new InvoiceSettingFilter();
            filter.setFranchiseCode(franchiseCode);
            filter.setInvSettingName("Invoice Signature");
            invSignature = invoiceSettingDao.getInvoiceSetting(filter);
        }

        creditNoteInfoVo.setCredits(credits);
        manageCreditNoteVo.setListCreditNoteAdj(creditNoteShowListVos);
        manageCreditNoteVo.setCreditNoteInfo(creditNoteInfoVo);
        ManageCreditNoteModel manageCreditNoteModel = ModelUtils.createModelFromVo(manageCreditNoteVo, ManageCreditNoteModel.class);

        // Get GST Summary vo
        List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos = creditNoteResponse.getCreditNotesGSTSummaryVos();
        List<CreditNotesGSTSummaryModel> creditNotesGSTSummaryModels = ModelUtils.createListModelFromVo(creditNotesGSTSummaryVos, CreditNotesGSTSummaryModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("logo", imgAsBase64);
        data.put("creditNote", manageCreditNoteModel);
        data.put("invSignature", invSignature);
        data.put("gstSummary", creditNotesGSTSummaryModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        String templateName = "credit_notes.ftl";
        if (defaultCountry.equalsIgnoreCase("70")) {
            templateName = "credit_notes_fr.ftl";
        }
        AppUtils.template2File(outputFilePath, false, "templates/pdf/invoicing/credit_notes/" + templateName, data);
    }

    public void renderHtmlPrintPreviewFile(String creditNoteCode, String outputFilePath, String realPath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/html/credit_notes/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteCode(creditNoteCode);
        CreditNoteResponse creditNoteResponse = getCreditNotesDetail(creditNoteRequest);
        ManageCreditNoteVo manageCreditNoteVo = new ManageCreditNoteVo();
        CreditNoteInfoVo creditNoteInfoVo = creditNoteResponse.getCreditNoteInfo();
        String defaultCountryId = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
        if (defaultCountryId.equalsIgnoreCase("70")) { // FR invoice numbering
            InvoiceNumberingService invoiceNumberingService = new InvoiceNumberingService();
            if (creditNoteInfoVo.getStatus() > 0) {
                String newCreditNoteCode = invoiceNumberingService.getCodeWithCounter(creditNoteInfoVo.getCreateDate(), creditNoteInfoVo.getCreditCode(), creditNoteInfoVo.getCustomerCode());
                creditNoteInfoVo.setCreditCode(newCreditNoteCode);
            }
        }
        manageCreditNoteVo.setCreditNoteInfo(creditNoteInfoVo);
        manageCreditNoteVo.setInfoSystemAdmin(creditNoteResponse.getInfoSystemAdminVo());
        manageCreditNoteVo.setListCreditNoteAdj(creditNoteResponse.getListAdjustment());
        ManageCreditNoteModel manageCreditNoteModel = ModelUtils.createModelFromVo(manageCreditNoteVo, ManageCreditNoteModel.class);

        String invSignature = "";
        // Get invoice signature if country is France
        String defaultCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
        if (StringUtils.isNotBlank(defaultCountry) && defaultCountry.equalsIgnoreCase("70")) {
            InvoiceSettingDao invoiceSettingDao = new InvoiceSettingDao();
            String franchiseCodeStr = String.valueOf(creditNoteResponse.getCreditNoteInfo().getCustomerCode()).substring(0, 3) + "00001";
            Long franchiseCode = Long.parseLong(franchiseCodeStr);
            InvoiceSettingFilter filter = new InvoiceSettingFilter();
            filter.setFranchiseCode(franchiseCode);
            filter.setInvSettingName("Invoice Signature");
            invSignature = invoiceSettingDao.getInvoiceSetting(filter);
        }
        // Get GST Summary vo
        List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos = creditNoteResponse.getCreditNotesGSTSummaryVos();
        List<CreditNotesGSTSummaryModel> creditNotesGSTSummaryModels = ModelUtils.createListModelFromVo(creditNotesGSTSummaryVos, CreditNotesGSTSummaryModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("logo", imgAsBase64);
        data.put("creditNote", manageCreditNoteModel);
        data.put("invSignature", invSignature);
        data.put("gstSummary", creditNotesGSTSummaryModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/credit_notes/credit_notes_print_preview.ftl", data);
    }

    public void renderHtmlPrintPreviewFileAll(List<String> creditNoteCodes, String outputFilePath, String realPath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/html/credit_notes/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        List<ManageCreditNoteModel> creditNoteModels = new ArrayList<>();
        for (String creditNoteCode : creditNoteCodes) {
            creditNoteRequest.setCreditNoteCode(creditNoteCode);
            CreditNoteResponse creditNoteResponse = getCreditNotesDetail(creditNoteRequest);
            ManageCreditNoteVo manageCreditNoteVo = new ManageCreditNoteVo();
            CreditNoteInfoVo creditNoteInfoVo = creditNoteResponse.getCreditNoteInfo();
            String defaultCountryId = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
            if (defaultCountryId.equalsIgnoreCase("70")) { // FR invoice
                // numbering
                InvoiceNumberingService invoiceNumberingService = new InvoiceNumberingService();
                if (creditNoteInfoVo.getStatus() > 0) {
                    String newCreditNoteCode = invoiceNumberingService.getCodeWithCounter(creditNoteInfoVo.getCreateDate(), creditNoteInfoVo.getCreditCode(), creditNoteInfoVo.getCustomerCode());
                    creditNoteInfoVo.setCreditCode(newCreditNoteCode);
                }
            }
            manageCreditNoteVo.setCreditNoteInfo(creditNoteInfoVo);
            manageCreditNoteVo.setInfoSystemAdmin(creditNoteResponse.getInfoSystemAdminVo());
            manageCreditNoteVo.setListCreditNoteAdj(creditNoteResponse.getListAdjustment());
            manageCreditNoteVo.setGstSummary(creditNoteResponse.getCreditNotesGSTSummaryVos());
            ManageCreditNoteModel manageCreditNoteModel = ModelUtils.createModelFromVo(manageCreditNoteVo, ManageCreditNoteModel.class);
            String invSignature = "";
            // Get invoice signature if country is France
            String defaultCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            if (StringUtils.isNotBlank(defaultCountry) && defaultCountry.equalsIgnoreCase("70")) {
                InvoiceSettingDao invoiceSettingDao = new InvoiceSettingDao();
                String franchiseCodeStr = String.valueOf(creditNoteResponse.getCreditNoteInfo().getCustomerCode()).substring(0, 3) + "00001";
                Long franchiseCode = Long.parseLong(franchiseCodeStr);
                InvoiceSettingFilter filter = new InvoiceSettingFilter();
                filter.setFranchiseCode(franchiseCode);
                filter.setInvSettingName("Invoice Signature");
                invSignature = invoiceSettingDao.getInvoiceSetting(filter);
            }
            manageCreditNoteModel.setInvSignature(invSignature);
            creditNoteModels.add(manageCreditNoteModel);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("logo", logo);
        data.put("listCreditNotes", creditNoteModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/credit_notes/credit_notes_print_preview_all.ftl", data);
    }
}
