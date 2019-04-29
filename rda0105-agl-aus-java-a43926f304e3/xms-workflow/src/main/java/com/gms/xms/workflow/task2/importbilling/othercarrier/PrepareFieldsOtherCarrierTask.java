package com.gms.xms.workflow.task2.importbilling.othercarrier;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jul 23, 2016 9:18:14 AM
 * <p>
 * Author huynd
 */
public class PrepareFieldsOtherCarrierTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsOtherCarrierTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            List<String> selectedFields = context.get(Attributes.IMPORT_FIELDS);
            Map<String, String> fieldName = new HashMap<String, String>();
            Integer index = 0;
            for (String field : selectedFields) {
                fieldName.put(String.valueOf(index), field);
                index++;
            }
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            fields.setFieldName(fieldName);
            context.put(Attributes.FIELDS, fields);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
