package com.gms.xms.workflow.render.searchpayment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.admin.receivables.payments.PaymentFilter;
import com.gms.xms.model.admin.receivables.payments.PaymentDetailModel;
import com.gms.xms.model.admin.receivables.payments.PaymentTotalModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.receivables.payments.PaymentDao;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentDetailVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.PaymentTotalVo;
import com.gms.xms.txndb.vo.admin.receivables.payments.SearchPaymentColumnFlagsVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPaymentRenderImp extends BaseRender implements ISearchPaymentRender {

    public SearchPaymentRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(PaymentFilter filter, SearchPaymentColumnFlagsVo columnFlags, String outputFilePath, String realPath) throws Exception {
        PaymentDao dao = new PaymentDao();
        List<PaymentDetailVo> paymentDetailVos = dao.getPaymentByFilter(filter);
        List<PaymentDetailModel> paymentDetailModels = ModelUtils.createListModelFromVo(paymentDetailVos, PaymentDetailModel.class);
        PaymentTotalVo totalVo = dao.sumPaymentByFilter(filter);
        PaymentTotalModel totalModel = ModelUtils.createModelFromVo(totalVo, PaymentTotalModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("flags", columnFlags);
        data.put("realPath", realPath);
        data.put("listData", paymentDetailModels);
        data.put("total", totalModel);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/search_payment/search_payment.ftl", data);
    }

    @Override
    public void generateXlsFile(PaymentFilter filter, SearchPaymentColumnFlagsVo columnFlags, String outputFilePath) throws Exception {
        PaymentDao dao = new PaymentDao();
        List<PaymentDetailVo> paymentDetailVos = dao.getPaymentByFilter(filter);
        List<PaymentDetailModel> paymentDetailModels = ModelUtils.createListModelFromVo(paymentDetailVos, PaymentDetailModel.class);
        PaymentTotalVo totalVo = dao.sumPaymentByFilter(filter);
        PaymentTotalModel totalModel = ModelUtils.createModelFromVo(totalVo, PaymentTotalModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("columnFlags", columnFlags);
        data.put("listData", paymentDetailModels);
        data.put("total", totalModel);
        data.put("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/search_payment/search_payment.xls", data);
    }
}
