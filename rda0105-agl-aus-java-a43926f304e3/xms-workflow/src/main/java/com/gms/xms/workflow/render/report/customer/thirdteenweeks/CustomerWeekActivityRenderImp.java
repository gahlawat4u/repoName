package com.gms.xms.workflow.render.report.customer.thirdteenweeks;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.model.reports.customer.thirdteenweeks.CustomerWeekActivityModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.customer.thirdteenweeks.CustomerWeekActivityServiceImp;
import com.gms.xms.persistence.service.report.customer.thirdteenweeks.ICustomerWeekActivityService;
import com.gms.xms.txndb.vo.reports.customer.thirdteenweeks.CustomerWeekActivityVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerWeekActivityRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerWeekActivityRenderImp extends BaseRender implements ICustomerWeekActivityRender {
    private ICustomerWeekActivityService service = new CustomerWeekActivityServiceImp();

    public CustomerWeekActivityRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderCustomerWeekActivityHtmlFile(CustomerWeekActivityFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerWeekActivityVo> customerWeekActivityVos = service.getWeekActivityReport(filter);
        List<CustomerWeekActivityModel> customerWeekActivityModels = ModelUtils.createListModelFromVo(customerWeekActivityVos, CustomerWeekActivityModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("customerList", customerWeekActivityModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/customer/customer_13week_activity/customer_13week_activity.ftl", data);
    }

    @Override
    public void renderCustomerWeekActivityXlsFile(CustomerWeekActivityFilter filter, String outPutFilePath) throws Exception {
        List<CustomerWeekActivityVo> customerWeekActivityVos = service.getWeekActivityReport(filter);
        List<CustomerWeekActivityModel> customerWeekActivityModels = ModelUtils.createListModelFromVo(customerWeekActivityVos, CustomerWeekActivityModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        data.putVar("customerList", customerWeekActivityModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/customer/customer_13week_activity/customer_13week_activity.xls", data);
    }
}
