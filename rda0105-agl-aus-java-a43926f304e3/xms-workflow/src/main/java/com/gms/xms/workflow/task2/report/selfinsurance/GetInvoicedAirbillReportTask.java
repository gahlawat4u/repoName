package com.gms.xms.workflow.task2.report.selfinsurance;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;
import com.gms.xms.txndb.model.reports.selfinsurance.InvoicedAirbillModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.InvoicedAirbillVo;
import com.gms.xms.workflow.core2.Task2;

import java.util.List;

/**
 * Posted from May 23, 2016 2:32:17 PM
 * <p>
 * Author dattrinh
 */
public class GetInvoicedAirbillReportTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get filter.
            InvoicedAirbillFilter filter = context.get(Attributes.INVOICED_AIRBILL_REPORT_FILTER);
            // Get record count.
            Long recordCount = context.getLong(Attributes.INVOICED_AIRBILL_REPORT_RECORD_COUNT);
            // Get nlinks count.
            Integer nlinks = context.getInt(Attributes.NLINKS);
            boolean isGetFullRequest = filter.getPage() == null || filter.getPageSize() == null;
            SelfInsuranceDao dao = new SelfInsuranceDao();
            List<InvoicedAirbillVo> invoicedAirbillVos;
            List<InvoicedAirbillModel> invoicedAirbillModels;
            if (!isGetFullRequest) {
                // Build paging object.
                Paging<InvoicedAirbillModel> paging = new Paging<InvoicedAirbillModel>(filter.getPage(), nlinks, filter.getPageSize(), recordCount);
                filter.setPage(paging.getCurrentPage());
                invoicedAirbillVos = dao.getInvoicedAirbillReport(filter);
                invoicedAirbillModels = ModelUtils.createListModelFromVo(invoicedAirbillVos, InvoicedAirbillModel.class);
                paging.setRecords(invoicedAirbillModels);
                // Set invoiced airbills report.
                context.put(Attributes.INVOICED_AIRBILL_REPORT, paging);
            } else {
                // Get full invoiced airbills report.
                invoicedAirbillVos = dao.getInvoicedAirbillReport(filter);
                invoicedAirbillModels = ModelUtils.createListModelFromVo(invoicedAirbillVos, InvoicedAirbillModel.class);
                context.put(Attributes.INVOICED_AIRBILL_REPORT_FULL, invoicedAirbillModels);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
