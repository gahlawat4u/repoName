package com.gms.xms.workflow.render.report.customer.activation;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.model.reports.customer.activation.CustomerActivationModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import com.gms.xms.workflow.service.report.customer.activation.CustomerActivationServiceImp;
import com.gms.xms.workflow.service.report.customer.activation.ICustomerActivationService;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerActivationRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class CustomerActivationRenderImp extends BaseRender implements ICustomerActivationRender {
    private ICustomerActivationService activationService = new CustomerActivationServiceImp(this.getAddInfo());

    public CustomerActivationRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderCustomerActivationHtmlFile(CustomerActivationFilter filter, String outputFilePath, String realPath) throws Exception {
        List<CustomerActivationVo> customerActivationVos = activationService.getActivationReport(filter);
        List<CustomerActivationModel> customerActivationModels = ModelUtils.createListModelFromVo(customerActivationVos, CustomerActivationModel.class);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("customerList", customerActivationModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey("CurrencySymbol"));
        AppUtils.template2File(outputFilePath, false, "templates/html/customer_activation/customer_activation.ftl", data);
    }

    @Override
    public void renderCustomerActivationXlsFile(CustomerActivationFilter filter, String outPutFilePath) throws Exception {
        List<CustomerActivationVo> customerActivationVos = activationService.getActivationReport(filter);
        List<CustomerActivationModel> customerActivationModels = ModelUtils.createListModelFromVo(customerActivationVos, CustomerActivationModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey("CurrencySymbol")));
        data.putVar("customerList", customerActivationModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/customer_activation/customer_activation.xls", data);
    }
}
