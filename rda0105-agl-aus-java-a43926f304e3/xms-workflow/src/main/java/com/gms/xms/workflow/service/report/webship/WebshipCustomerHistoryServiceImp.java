package com.gms.xms.workflow.service.report.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipCustomerHistoryServiceImp.java
 * <p>
 * Author dattrinh 2:31:11 PM
 */
public class WebshipCustomerHistoryServiceImp extends BaseService implements IWebshipCustomerHistoryService {
    public WebshipCustomerHistoryServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<Map<String, String>> getWebshipCustomerHistoryReport(WebshipCustomerHistoryFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetWebshipCustomerHistoryReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<Map<String, String>> histories = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_REPORT_RESULT), new TypeToken<List<Map<String, String>>>() {
            }.getType());
            return histories;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getWebshipCustomerHistoryCount(WebshipCustomerHistoryFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetWebshipCustomerHistoryCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
