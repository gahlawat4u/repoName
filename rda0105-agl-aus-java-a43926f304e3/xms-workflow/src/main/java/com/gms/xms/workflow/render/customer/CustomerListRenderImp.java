package com.gms.xms.workflow.render.customer;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.CustomerDetailModel;
import com.gms.xms.model.account.customers.CustomerListEntryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.txndb.vo.CustomerDetailVo;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.account.customers.CustomerListEntryVo;
import com.gms.xms.txndb.vo.account.customers.SearchCustomerColumnFlagsVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerListRenderImp
 * <p>
 * Author DatTV Dec 24, 2015
 */
public class CustomerListRenderImp extends BaseRender implements ICustomerListRender {
    public CustomerListRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    private ICustomerService customerService = new CustomerServiceImp();

    @Override
    public void renderSearchCustomerXLSFile(CustomerFilter filter, String outPutFilePath, SearchCustomerColumnFlagsVo columnFlags) throws Exception {
        ExportLocalizationHelper lang = new ExportLocalizationHelper(this.getAddInfo());
        List<CustomerDetailVo> customerDetailVos = customerService.searchCustomers(filter);
        List<CustomerDetailModel> customerDetailModels = ModelUtils.createListModelFromVo(customerDetailVos, CustomerDetailModel.class);
        for (CustomerDetailModel customerDetailModel : customerDetailModels) {
            if (customerDetailModel.getEmailInvoice().equals("true")) {
                customerDetailModel.setEmailInvoice(lang.translate("Yes"));
            } else {
                customerDetailModel.setEmailInvoice(lang.translate("No"));
            }
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("flags", columnFlags);
        data.put("customerList", customerDetailModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/account/search_customer/customer_list.xls", data);
    }

    @Override
    public void renderCustomerListHtmlFile(CustomerFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerListEntryVo> customerListEntryVos = customerService.selectCustomerList(filter);
        List<CustomerListEntryModel> customerListEntryModels = ModelUtils.createListModelFromVo(customerListEntryVos, CustomerListEntryModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("realPath", realPath);
        data.put("customerList", customerListEntryModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey("CurrencySymbol"));
        AppUtils.template2File(outputFilePath, false, "templates/html/customer_list/customer_list.ftl", data);
    }

    @Override
    public void renderCustomerListXlsFile(CustomerFilter filter, String outPutFilePath) throws Exception {
        List<CustomerListEntryVo> customerListEntryVos = customerService.selectCustomerList(filter);
        List<CustomerListEntryModel> customerListEntryModels = ModelUtils.createListModelFromVo(customerListEntryVos, CustomerListEntryModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey("CurrencySymbol")));
        data.put("customerList", customerListEntryModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/account/customer_list/customer_list.xls", data);
    }
}
