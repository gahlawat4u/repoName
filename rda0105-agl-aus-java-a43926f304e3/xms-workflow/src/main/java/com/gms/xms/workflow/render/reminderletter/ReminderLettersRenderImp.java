package com.gms.xms.workflow.render.reminderletter;

import com.gms.xms.cache.SystemSettingCache;
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
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReminderLettersRenderImp extends BaseRender implements IReminderLettersRender {

    public ReminderLettersRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlPrintInvoice(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String realPath, String printLetter, String outputFilePath) throws Exception {
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
        data.put("printLetter", printLetter);
        AppUtils.template2File(outputFilePath, false, "templates/html/print_letters/print_letters.ftl", data);
    }
}
