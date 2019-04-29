package com.gms.xms.workflow.service.customersummary;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerSummaryServiceImp
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class CustomerSummaryServiceImp extends BaseService implements ICustomerSummaryService {

    public CustomerSummaryServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<Map<String, String>> selectByFilter(CustomerSummaryFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_SUMMARY_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerSummaryReport");
        context = WorkFlowManager.getInstance().process(context);
        List<Map<String, String>> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_SUMMARY_LIST_RESULT), new TypeToken<List<Map<String, String>>>() {
        }.getType());
        return result;
    }

    @Override
    public long countByFilter(CustomerSummaryFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_SUMMARY_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-CountCustomerSummaryReport");
        context = WorkFlowManager.getInstance().process(context);
        long count = Long.valueOf(context.get(Attributes.CUSTOMER_SUMMARY_RECORD_COUNT_RESULT));
        return count;
    }

    @Override
    public Map<String, String> sumByFilter(CustomerSummaryFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_SUMMARY_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-SumCustomerSummaryReport");
        context = WorkFlowManager.getInstance().process(context);
        Map<String, String> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_SUMMARY_TOTAL_RESULT), new TypeToken<Map<String, String>>() {
        }.getType());
        return result;
    }
}
