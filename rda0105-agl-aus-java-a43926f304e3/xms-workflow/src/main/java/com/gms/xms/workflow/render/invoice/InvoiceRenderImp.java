package com.gms.xms.workflow.render.invoice;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.invoices.AirbillDetailModel;
import com.gms.xms.model.webship.invoices.GSTSummaryModel;
import com.gms.xms.model.webship.invoices.TaxInvoiceModel;
import com.gms.xms.persistence.service.customerbillingaddress.CustomerBillingAddressServiceImp;
import com.gms.xms.persistence.service.customerbillingaddress.ICustomerBillingAddressService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.webship.invoices.AirbillChargeVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillDetailVo;
import com.gms.xms.txndb.vo.webship.invoices.GSTSummaryVo;
import com.gms.xms.txndb.vo.webship.invoices.TaxInvoiceVo;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.util.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from InvoiceRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class InvoiceRenderImp extends BaseRender implements IInvoiceRender {
    public InvoiceRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    private IInvoiceService invoiceService = new InvoiceServiceImp();

    @Override
    public void renderWebshipInvoiceHtmlReport(InvoiceFilter filter, String outputFilePath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/webship/invoices/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;

        // Get models
        TaxInvoiceVo taxInvoiceVo = invoiceService.getTaxInvoiceByCode(filter.getInvoiceCode());
        Double ifNotPaidByDue = 0.00;
        try {
            ifNotPaidByDue = taxInvoiceVo.getTotalAmount() + taxInvoiceVo.getInvoiceLateFee();
        } catch (Exception e) {
        }
        taxInvoiceVo.setIfNotPaidByDue(ifNotPaidByDue);
        TaxInvoiceModel taxInvoiceModel = ModelUtils.createModelFromVo(taxInvoiceVo, TaxInvoiceModel.class);

        GSTSummaryVo gstSummaryVo = invoiceService.getGSTSummaryByInvoiceId(taxInvoiceVo.getInvoiceId());
        Double totalGstAmount = 0.00;
        try {
            totalGstAmount = gstSummaryVo.getGstCustomerCost() + gstSummaryVo.getCustomerTaxAmount();
        } catch (Exception e) {
        }
        gstSummaryVo.setTotalGstAmount(totalGstAmount);
        GSTSummaryModel gstSummaryModel = ModelUtils.createModelFromVo(gstSummaryVo, GSTSummaryModel.class);

        List<AirbillDetailVo> airbillDetailVos = invoiceService.getAirbillListByInvoiceCode(filter.getInvoiceCode());
        String calServiceFlag = SystemSettingCache.getInstance().getValueByKey("Calculate Service Tax");
        String taxName = SystemSettingCache.getInstance().getValueByKey("Tax Name");
        taxName = taxName != null ? taxName : "GST";
        Double total = 0.00;
        for (AirbillDetailVo airbillDetailVo : airbillDetailVos) {
            List<AirbillChargeVo> airbillChargeVos = airbillDetailVo.getCharges();
            Double atotalGst = 0.00;
            Double atotal = 0.00;
            Double atotalWithTax = 0.00;
            for (AirbillChargeVo airbillChargeVo : airbillChargeVos) {
                atotalGst += airbillChargeVo.getCustomerTaxAmount();
                atotal += airbillChargeVo.getCustomerCost();
                atotalWithTax += airbillChargeVo.getCustomerCost() + airbillChargeVo.getCustomerTaxAmount();
            }
            if (calServiceFlag.equals("0") && atotalGst != 0) {
                AirbillChargeVo gst = new AirbillChargeVo();
                gst.setDescription(taxName);
                gst.setCustomerCost(atotalGst);
                airbillChargeVos.add(gst);
                airbillDetailVo.setTotal(atotalWithTax);
            } else {
                airbillDetailVo.setTotal(atotal);
            }
            total += atotal;
        }

        List<AirbillDetailModel> airbillDetailModels = ModelUtils.createListModelFromVo(airbillDetailVos, AirbillDetailModel.class);
        ICustomerBillingAddressService addressService = new CustomerBillingAddressServiceImp();
        String customerCode = String.valueOf(filter.getCustomerCode());
        String franchiseCode = customerCode.substring(0, 3) + "00001";
        CustomerBillingAddressVo franchiseBillingAddressVo = addressService.getCustomerBillingAddressByCustomerCode(Long.parseLong(franchiseCode));
        String franchisePhone = franchiseBillingAddressVo.getBillingPhone();

        String systemAddress = "";
        systemAddress = SystemSettingCache.getInstance().getValueByKey("System Address for Tax Invoice");
        if (systemAddress != null) {
            systemAddress = systemAddress.replace("[PHONE_NUMBER]", franchisePhone);
            systemAddress = systemAddress.toUpperCase();
        }

        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");

        String mailPaymentTo = "";
        mailPaymentTo = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
        mailPaymentTo = mailPaymentTo.toUpperCase();

        CustomerBillingAddressVo customerBillingAddressVo = addressService.getCustomerBillingAddressByCustomerCode(filter.getCustomerCode());
        CustomerBillingAddressModel customerBillingAddressModel = ModelUtils.createModelFromVo(customerBillingAddressVo, CustomerBillingAddressModel.class);

        String invoiceSign = SystemSettingCache.getInstance().getValueByKey("Invoice Signature");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("logo", logo);
        data.put("systemAddress", systemAddress);
        data.put("siteAddress", siteAddress);
        data.put("billTo", customerBillingAddressModel);
        data.put("mailPaymentTo", mailPaymentTo);
        data.put("taxInvoice", taxInvoiceModel);
        data.put("gstSummary", gstSummaryModel);
        data.put("airbillList", airbillDetailModels);
        data.put("invoiceSign", invoiceSign);
        data.put("total", total);
        data.put("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        AppUtils.template2File(outputFilePath, false, "templates/pdf/webship/invoices/ws_invoices.ftl", data);
    }
}
