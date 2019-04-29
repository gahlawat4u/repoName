package com.gms.xms.workflow.render.customeraging;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingColumnFlagsVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.customeraging.CustomerAgingServiceImp;
import com.gms.xms.workflow.service.customeraging.ICustomerAgingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAgingRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerAgingRenderImp extends BaseRender implements ICustomerAgingRender {
    private ICustomerAgingService agingService = new CustomerAgingServiceImp(this.getAddInfo());

    public CustomerAgingRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderHTMLFile(CustomerAgingFilter filter, CustomerAgingColumnFlagsVo columnFlags, String htmlFilePath) throws Exception {
        CustomerAgingVo agingTotalVo = agingService.sumByFilter(filter);
        CustomerAgingModel agingTotal = ModelUtils.createModelFromVo(agingTotalVo, CustomerAgingModel.class);

        List<CustomerAgingVo> agingVos = agingService.selectByFilter(filter);
        List<CustomerAgingModel> agingList = ModelUtils.createListModelFromVo(agingVos, CustomerAgingModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(AppConstants.CURRENCY_SYMBOL));
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("agingTotal", agingTotal);
        data.put("agingList", agingList);
        data.put("columnFlags", columnFlags);

        AppUtils.template2File(htmlFilePath, false, "templates/html/customer_aging/customer_aging.ftl", data);
    }

    @Override
    public void renderXLSFile(CustomerAgingFilter filter, CustomerAgingColumnFlagsVo columnFlags, String xlsFilePath) throws Exception {
        CustomerAgingVo agingTotalVo = agingService.sumByFilter(filter);
        CustomerAgingModel agingTotal = ModelUtils.createModelFromVo(agingTotalVo, CustomerAgingModel.class);

        List<CustomerAgingVo> agingVos = agingService.selectByFilter(filter);
        List<CustomerAgingModel> agingList = ModelUtils.createListModelFromVo(agingVos, CustomerAgingModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(AppConstants.CURRENCY_SYMBOL));
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("agingTotal", agingTotal);
        data.put("agingList", agingList);
        data.put("columnFlags", columnFlags);
        AppUtils.generateXLSFile(xlsFilePath, "templates/excel/customer_aging/customer_aging.xls", data);
    }
}
