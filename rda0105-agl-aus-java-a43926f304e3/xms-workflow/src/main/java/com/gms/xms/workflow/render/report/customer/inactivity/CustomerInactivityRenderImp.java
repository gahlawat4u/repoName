package com.gms.xms.workflow.render.report.customer.inactivity;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.model.reports.customer.inactivity.CustomerInactivityModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.customer.inactivity.CustomerInactivityServiceImp;
import com.gms.xms.persistence.service.report.customer.inactivity.ICustomerInactivityService;
import com.gms.xms.txndb.vo.reports.customer.inactivity.CustomerInactivityVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerInactivityRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerInactivityRenderImp extends BaseRender implements ICustomerInactivityRender {
    private ICustomerInactivityService inactivityService = new CustomerInactivityServiceImp();

    public CustomerInactivityRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderCustomerInactivityHtmlFile(CustomerInactivityFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerInactivityVo> customerInactivityVos = inactivityService.getInactivityReport(filter);
        List<CustomerInactivityModel> customerInactivityModels = ModelUtils.createListModelFromVo(customerInactivityVos, CustomerInactivityModel.class);
        Long totalCount = inactivityService.getInactivityCount(filter);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("customerList", customerInactivityModels);
        data.put("totalCustomer", totalCount);
        AppUtils.template2File(outputFilePath, false, "templates/html/report/customer/customer_inactivity_report/customer_inactivity_report.ftl", data);
    }

    @Override
    public void renderCustomerInactivityXlsFile(CustomerInactivityFilter filter, String outPutFilePath) throws Exception {
        List<CustomerInactivityVo> customerInactivityVos = inactivityService.getInactivityReport(filter);
        List<CustomerInactivityModel> customerInactivityModels = ModelUtils.createListModelFromVo(customerInactivityVos, CustomerInactivityModel.class);
        Long totalCount = inactivityService.getInactivityCount(filter);

        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("customerList", customerInactivityModels);
        data.putVar("totalCustomer", totalCount);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/report/customer/customer_inactivity/customer_inactivity.xls", data);
    }
}
