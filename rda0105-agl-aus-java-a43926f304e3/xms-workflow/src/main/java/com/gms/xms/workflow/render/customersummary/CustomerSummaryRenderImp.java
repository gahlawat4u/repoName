package com.gms.xms.workflow.render.customersummary;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.txndb.vo.reports.customer.status.CustomerStatusColumn;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerSummaryRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerSummaryRenderImp extends BaseRender implements ICustomerSummaryRender {
    public CustomerSummaryRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderXLS(List<CustomerStatusColumn> columns, List<Map<String, Object>> summary, Map<String, Object> summaryTotal, String outPutFilePath) {
        List<CustomerStatusColumn> totalColumns = new LinkedList<>();
        for (int i = 3; i < columns.size(); i++) {
            CustomerStatusColumn column = columns.get(i);
            totalColumns.add(column);
        }
        String totalCustomer = String.valueOf(summary.size());
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.putVar("columns", columns);
        data.putVar("totalColumns", totalColumns);
        data.putVar("summaries", summary);
        data.putVar("summaryTotal", summaryTotal);
        data.putVar("totalCustomer", totalCustomer);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/customer_summary_report/customer_summary_report.xls", data);
    }

    @Override
    public void renderHTML(List<CustomerStatusColumn> columns, List<Map<String, String>> summary, Map<String, String> summaryTotal, String outPutFilePath) {
        String tempName = "customer_summary_report.ftl";
        List<CustomerStatusColumn> totalColumns = new LinkedList<>();
        for (int i = 3; i < columns.size(); i++) {
            CustomerStatusColumn column = columns.get(i);
            totalColumns.add(column);
        }
        String totalCustomer = String.valueOf(summary.size());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("columns", columns);
        data.put("totalColumns", totalColumns);
        data.put("summaries", summary);
        data.put("summaryTotal", summaryTotal);
        data.put("totalCustomer", totalCustomer);
        AppUtils.template2File(outPutFilePath, false, "templates/html/customer_summary_report/" + tempName, data);
    }
}
