package com.gms.xms.workflow.task2.report.selfinsurance;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;
import com.gms.xms.txndb.model.reports.selfinsurance.SummaryInvoicedAirbillModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.SummaryInvoicedAirbillVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 23, 2016 2:32:17 PM
 * <p>
 * Author dattrinh
 */
public class GetSummaryInvoicedAirbillReportTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get filter.
            InvoicedAirbillFilter filter = context.get(Attributes.INVOICED_AIRBILL_REPORT_FILTER);
            SelfInsuranceDao dao = new SelfInsuranceDao();
            SummaryInvoicedAirbillVo summaryInvoicedAirbillVo = dao.getSummaryInvoicedAirbillReport(filter);
            SummaryInvoicedAirbillModel summaryInvoicedAirbillModel = ModelUtils.createModelFromVo(summaryInvoicedAirbillVo, SummaryInvoicedAirbillModel.class);
            context.put(Attributes.SUMMARY_INVOICED_AIRBILL_REPORT, summaryInvoicedAirbillModel);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
