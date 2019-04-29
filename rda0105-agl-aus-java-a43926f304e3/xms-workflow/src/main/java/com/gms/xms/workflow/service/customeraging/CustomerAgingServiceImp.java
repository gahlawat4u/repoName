package com.gms.xms.workflow.service.customeraging;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.gms.xms.workflow.service.BaseService;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAgingServiceImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerAgingServiceImp extends BaseService implements ICustomerAgingService {

    public CustomerAgingServiceImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public List<CustomerAgingVo> selectByFilter(CustomerAgingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_AGING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerAgingReport");
        context = WorkFlowManager.getInstance().process(context);
        List<CustomerAgingVo> result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_LIST_RESULT), new TypeToken<List<CustomerAgingVo>>() {
        }.getType());
        return result;
    }

    @Override
    public long countByFilter(CustomerAgingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_AGING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetCountCustomerAgingReport");
        context = WorkFlowManager.getInstance().process(context);
        long count = Long.valueOf(context.get(Attributes.CUSTOMER_AGING_RECORD_COUNT_RESULT));
        return count;
    }

    @Override
    public CustomerAgingVo sumByFilter(CustomerAgingFilter filter) throws Exception {
        ContextBase context = new ContextBase(this.getContext());
        context.put(Attributes.CUSTOMER_AGING_FILTER, GsonUtils.toGson(filter));
        context.put(Attributes.WFP_NAME, "Wfl-GetTotalCustomerAgingReport");
        context = WorkFlowManager.getInstance().process(context);
        CustomerAgingVo result = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_TOTAL_RESULT), CustomerAgingVo.class);
        return result;
    }

    @Override
    public CustomerAgingVo selectByCustomerCode(String customerCode) throws DaoException {
        CustomerAgingDao agingDao = new CustomerAgingDao();
        return agingDao.selectByCustomerCode(customerCode);
    }
}