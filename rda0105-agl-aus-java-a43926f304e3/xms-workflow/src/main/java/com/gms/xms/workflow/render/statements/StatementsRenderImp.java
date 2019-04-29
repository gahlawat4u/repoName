package com.gms.xms.workflow.render.statements;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.StatementInvoiceFilter;
import com.gms.xms.model.admin.invoicing.manageinvoice.statement.StatementInvoiceModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.dao.invoicing.StatementDao;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementInvoiceVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.customeraging.CustomerAgingServiceImp;
import com.gms.xms.workflow.service.customeraging.ICustomerAgingService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementsRenderImp extends BaseRender implements IStatementsRender {

    public StatementsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String realPath, String outputFilePath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/html/statements/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;

        ICustomerAgingService agingService = new CustomerAgingServiceImp(this.getAddInfo());
        CustomerAgingVo agingVo = agingService.selectByCustomerCode(customerCode);
        CustomerAgingModel agingModel = ModelUtils.createModelFromVo(agingVo, CustomerAgingModel.class);

        String companyAddress = SystemSettingCache.getInstance().getValueByKey("System Address for Tax Invoice");
        companyAddress = StringUtils.replace(companyAddress, "\n", "<br/>");
        // Get customer billing address.
        String franchiseCode = customerCode.substring(0, 3);
        franchiseCode += "00001";
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        CustomerBillingAddressVo customerBillingAddressVo = billingAddressDao.getByCustomerCode(franchiseCode);
        // Replace [PHONE_NUMBER] by phone number of the franchise billing
        // address.
        companyAddress = StringUtils.replace(companyAddress, "[PHONE_NUMBER]", customerBillingAddressVo.getBillingPhone());

        String mailPaymentTo = "";
        mailPaymentTo = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
        mailPaymentTo = StringUtils.replace(mailPaymentTo, "\n", "<br/>");

        StatementDao dao = new StatementDao();
        StatementCustomerBillingAddressVo billingAddressVo = dao.getBillingAddressByCustCode(customerCode);

        StatementDao statementDao = new StatementDao();
        List<StatementInvoiceVo> statementInvoiceVos = statementDao.getInvoiceByCustCode(filter);
        List<StatementInvoiceModel> statementInvoiceModels = ModelUtils.createListModelFromVo(statementInvoiceVos, StatementInvoiceModel.class);
        StatementInvoiceVo summaryVo = statementDao.sumInvoiceByCustCode(filter);
        StatementInvoiceModel summaryModel = ModelUtils.createModelFromVo(summaryVo, StatementInvoiceModel.class);
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        Map<String, Object> data = new HashMap<>();
        data.put("realPath", realPath);
        data.put("logo", logo);
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("customerCode", customerCode);
        data.put("aging", agingModel);
        data.put("currentDate", currentDateStr);
        data.put("companyAddress", companyAddress);
        data.put("billingAddress", billingAddressVo);
        data.put("mailPaymentTo", mailPaymentTo);
        data.put("invoices", statementInvoiceModels);
        data.put("invoiceTotal", summaryModel);
        data.put("siteAddress", siteAddress);
        AppUtils.template2File(outputFilePath, false, "templates/html/statements/statements.ftl", data);
    }

    @Override
    public void generateHtmlForPdfFile(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String outputFilePath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/invoicing/statements/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;

        ICustomerAgingService agingService = new CustomerAgingServiceImp(this.getAddInfo());
        CustomerAgingVo agingVo = agingService.selectByCustomerCode(customerCode);
        CustomerAgingModel agingModel = ModelUtils.createModelFromVo(agingVo, CustomerAgingModel.class);

        String companyAddress = SystemSettingCache.getInstance().getValueByKey("System Address for Tax Invoice");
        companyAddress = StringUtils.replace(companyAddress, "\n", "<br/>");
        // Get customer billing address.
        String franchiseCode = customerCode.substring(0, 3);
        franchiseCode += "00001";
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        CustomerBillingAddressVo customerBillingAddressVo = billingAddressDao.getByCustomerCode(franchiseCode);
        // Replace [PHONE_NUMBER] by phone number of the franchise billing
        // address.
        companyAddress = StringUtils.replace(companyAddress, "[PHONE_NUMBER]", customerBillingAddressVo.getBillingPhone());

        String mailPaymentTo = "";
        mailPaymentTo = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
        mailPaymentTo = StringUtils.replace(mailPaymentTo, "\n", "<br/>");

        StatementDao dao = new StatementDao();
        StatementCustomerBillingAddressVo billingAddressVo = dao.getBillingAddressByCustCode(customerCode);

        StatementDao statementDao = new StatementDao();
        List<StatementInvoiceVo> statementInvoiceVos = statementDao.getInvoiceByCustCode(filter);
        List<StatementInvoiceModel> statementInvoiceModels = ModelUtils.createListModelFromVo(statementInvoiceVos, StatementInvoiceModel.class);
        StatementInvoiceVo summaryVo = statementDao.sumInvoiceByCustCode(filter);
        StatementInvoiceModel summaryModel = ModelUtils.createModelFromVo(summaryVo, StatementInvoiceModel.class);
        String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
        Map<String, Object> data = new HashMap<>();
        data.put("logo", logo);
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("customerCode", customerCode);
        data.put("aging", agingModel);
        data.put("currentDate", currentDateStr);
        data.put("companyAddress", companyAddress);
        data.put("billingAddress", billingAddressVo);
        data.put("mailPaymentTo", mailPaymentTo);
        data.put("invoices", statementInvoiceModels);
        data.put("invoiceTotal", summaryModel);
        data.put("siteAddress", siteAddress);
        AppUtils.template2File(outputFilePath, false, "templates/pdf/invoicing/statements/statements.ftl", data);
    }
}
