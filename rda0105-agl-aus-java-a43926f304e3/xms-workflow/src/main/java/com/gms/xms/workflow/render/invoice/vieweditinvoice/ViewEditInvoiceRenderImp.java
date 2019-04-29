package com.gms.xms.workflow.render.invoice.vieweditinvoice;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.invoicing.manageinvoice.AirbillInfoModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.InvoiceInfoModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewEditInvoiceRenderImp extends BaseRender implements IViewEditInvoiceRender {
    private static final Log log = LogFactory.getLog(ViewEditInvoiceRenderImp.class);

    public ViewEditInvoiceRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlViewPdfFile(ContextBase2 context, Boolean showPayments) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/invoicing/view_edit_invoices/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;
        context = WorkFlowManager2.getInstance().process(context);
        // Write log if there is error message.
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            log.error(context.getString(Attributes.ERROR_MESSAGE));
            throw new CustomException(context.getString(Attributes.ERROR_MESSAGE));
        }
        InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
        String mailPaymentToResult = context.getString(Attributes.MAIL_PAYMENT_TO);
        mailPaymentToResult = AppUtils.reconcileUTF8String(mailPaymentToResult);
        String companyAddressResult = context.getString(Attributes.COMPANY_ADDRESS);
        List<AirbillInfoVo> airbillInfoVos = context.get(Attributes.AIRBILL_INFO_VO);

        InvoiceInfoModel invoiceInfoModel = ModelUtils.createModelFromVo(invoiceInfoVo, InvoiceInfoModel.class);
        List<AirbillInfoModel> airbillInfoModels = ModelUtils.createListModelFromVoEscapeHtmlAndIgnoreNonPrintableChars(airbillInfoVos, AirbillInfoModel.class);
        String invSignature = SystemSettingCache.getInstance().getValueByKey("Invoice Signature");
        invSignature.replaceAll("(\\r|\\n|\\r\\n)+", "<br/>");
        invSignature = AppUtils.reconcileUTF8String(invSignature);
        String mtime = AppUtils.createMessageReference();
        String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoices_" + invoiceInfoModel.getInvoiceCode() + mtime + ".html";
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        Map<String, Object> data = new HashMap<>();
        data.put("logo", logo);
        data.put("showPayments", showPayments);
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("invInfo", invoiceInfoModel);
        data.put("mailToAddress", mailPaymentToResult);
        data.put("companyAddress", companyAddressResult);
        data.put("listAirbills", airbillInfoModels);
        data.put("invSignature", invSignature);
        data.put("siteAddress", siteAddress);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/invoicing/view_edit_invoices/view_edit_invoices.ftl", data);
        context.put(Attributes.OUT_PUT_FILE_PATH, outputFilePath);
    }

    @Override
    public void generateHtmlViewPrintableFile(ContextBase2 context, Boolean showPayments) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/html/view_edit_invoices/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;
        context = WorkFlowManager2.getInstance().process(context);
        // Write log if there is error message.
        if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
            log.error(context.getString(Attributes.ERROR_MESSAGE));
            throw new CustomException(context.getString(Attributes.ERROR_MESSAGE));
        }
        InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
        String mailPaymentToResult = context.getString(Attributes.MAIL_PAYMENT_TO);
        mailPaymentToResult = AppUtils.reconcileUTF8String(mailPaymentToResult);
        String companyAddressResult = context.getString(Attributes.COMPANY_ADDRESS);
        companyAddressResult = AppUtils.reconcileUTF8String(companyAddressResult);
        List<AirbillInfoVo> airbillInfoVos = context.get(Attributes.AIRBILL_INFO_VO);

        InvoiceInfoModel invoiceInfoModel = ModelUtils.createModelFromVo(invoiceInfoVo, InvoiceInfoModel.class);
        List<AirbillInfoModel> airbillInfoModels = ModelUtils.createListModelFromVoEscapeHtmlAndIgnoreNonPrintableChars(airbillInfoVos, AirbillInfoModel.class);
        String invSignature = SystemSettingCache.getInstance().getValueByKey("Invoice Signature");
        invSignature.replaceAll("(\\r|\\n|\\r\\n)+", "<br/>");
        invSignature = AppUtils.reconcileUTF8String(invSignature);
        String mtime = AppUtils.createMessageReference();
        String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoices_" + invoiceInfoModel.getInvoiceCode() + mtime + ".html";
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        Map<String, Object> data = new HashMap<>();
        data.put("siteAddress", siteAddress);
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("showPayments", showPayments);
        data.put("logo", logo);
        data.put("invInfo", invoiceInfoModel);
        data.put("mailToAddress", mailPaymentToResult);
        data.put("companyAddress", companyAddressResult);
        data.put("listAirbills", airbillInfoModels);
        data.put("invSignature", invSignature);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/invoicing/view_edit_invoices/view_edit_invoices.ftl", data);
        context.put(Attributes.OUT_PUT_FILE_PATH, outputFilePath);
    }
}
