package com.gms.xms.workflow.service.report.customer.status;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerStatusServiceImp
 * <p>
 * Author DatTV Nov 6, 2015
 */
public class CustomerStatusServiceImp extends BaseService implements ICustomerStatusService {

    public CustomerStatusServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<Map<String, String>> getWeeklyReport(CustomerStatusFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_STATUS_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetWeeklyCustomerStatusReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<Map<String, String>> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_WEEKLY_REPORT_RESULT), new TypeToken<List<Map<String, String>>>() {
            }.getType());
            return result;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public List<Map<String, String>> getMonthlyReport(CustomerStatusFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_STATUS_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetMonthlyCustomerStatusReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<Map<String, String>> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_MONTHLY_REPORT_RESULT), new TypeToken<List<Map<String, String>>>() {
            }.getType());
            return result;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public Map<String, String> sumWeeklyReport(CustomerStatusFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_STATUS_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-SumWeeklyCustomerStatusReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Map<String, String> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_WEEKLY_TOTAL_RESULT), new TypeToken<Map<String, String>>() {
            }.getType());
            return result;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public Map<String, String> sumMonthlyReport(CustomerStatusFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_STATUS_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-SumMonthlyCustomerStatusReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Map<String, String> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_MONTHLY_TOTAL_RESULT), new TypeToken<Map<String, String>>() {
            }.getType());
            return result;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
