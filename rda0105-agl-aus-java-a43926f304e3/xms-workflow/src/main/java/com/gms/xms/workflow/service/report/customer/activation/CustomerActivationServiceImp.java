package com.gms.xms.workflow.service.report.customer.activation;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerActivationServiceImp.java
 * <p>
 * Author dattrinh 11:58:23 AM
 */
public class CustomerActivationServiceImp extends BaseService implements ICustomerActivationService {

    public CustomerActivationServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<CustomerActivationVo> getActivationReport(CustomerActivationFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_ACTIVATION_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerActivationReport");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<CustomerActivationVo> activations = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ACTIVATION_REPORT_RESULT), new TypeToken<List<CustomerActivationVo>>() {
            }.getType());
            return activations;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }

    @Override
    public long getActivationCount(CustomerActivationFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_ACTIVATION_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerActivationCount");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            Long recordCount = Long.valueOf(context.get(Attributes.CUSTOMER_ACTIVATION_RECORD_COUNT_RESULT));
            return recordCount;
        } else {
            throw new Exception(context.get(Attributes.ERROR_MESSAGE));
        }
    }
}
