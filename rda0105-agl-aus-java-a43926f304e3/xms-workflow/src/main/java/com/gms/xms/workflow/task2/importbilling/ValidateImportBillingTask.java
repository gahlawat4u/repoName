package com.gms.xms.workflow.task2.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 25, 2016 8:36:46 AM
 * <p>
 * Author huynd
 */
public class ValidateImportBillingTask implements Task2 {
    private static final Log log = LogFactory.getLog(ValidateImportBillingTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Check invoice date
            String invoiceDate = context.getString(Attributes.INVOICE_DATE);
            if (StringUtils.isBlank(invoiceDate)) {
                throw new Exception("Choose invoice date.");
            }
            InvoiceDao invoiceDao = new InvoiceDao();
            Long count = invoiceDao.checkFreezedInvoiceByDate(DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
            if (count > 0) {
                throw new Exception("Invoice date is already freezed.");
            }
            // Check import file
            String filePath = context.getString(Attributes.BILLING_FILE);
            if (filePath == null || StringUtils.isBlank(filePath)) {
                throw new Exception("Imported file contains blank value.");
            }
            BillingDataModel dataSheet = context.get(Attributes.BILLING_DATA_FILE);
            Integer countColumns = dataSheet.getRowData().get(0).getCellData().size();
            ImportBillingFieldsModel fields = context.get(Attributes.FIELDS);
            Integer countFields = fields.getFieldName().size();
            if (countColumns != countFields) {
                throw new Exception("Imported file get wrong format.");
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
