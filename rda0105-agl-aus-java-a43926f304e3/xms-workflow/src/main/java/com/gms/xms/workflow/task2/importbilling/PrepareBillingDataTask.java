package com.gms.xms.workflow.task2.importbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.BillingDataModel;
import com.gms.xms.model.admin.imports.BillingRowDataModel;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 30, 2016 11:49:18 AM
 * <p>
 * Author huynd
 */
public class PrepareBillingDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareBillingDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            BillingDataModel dataSheet = context.get(Attributes.BILLING_DATA_FILE);
            Integer serviceId = context.getInt(Attributes.SERVICE_ID);
            ImportBillingFieldsModel fields = context.get(Attributes.FIELDS);
            Map<String, String> fieldName = fields.getFieldName();
            String key, value;
            BillingRowDataModel billingRowData;
            BillingDataModel billingData = new BillingDataModel();
            List<BillingRowDataModel> listRowData = new LinkedList<BillingRowDataModel>();
            for (BillingRowDataModel data : dataSheet.getRowData()) {
                Boolean emptyRow = true;
                Map<String, String> rowData = data.getCellData();
                billingRowData = new BillingRowDataModel();
                billingRowData.setCellData(new HashMap<String, String>());
                Integer count = 1;
                for (int i = 0; i < fieldName.size(); i++) {
                    for (int j = 0; j < rowData.size(); j++) {
                        if (i == j) {
                            if (fieldName.get(String.valueOf(i)).contains("access")) {
                                key = String.valueOf(count).trim();
                                count++;
                            } else {
                                key = fieldName.get(String.valueOf(i));
                            }
                            if (key.equalsIgnoreCase("0") || StringUtils.isBlank(key)) {
                                continue;
                            }
                            value = (rowData.get(String.valueOf(j)) == null) ? "" : rowData.get(String.valueOf(j)).trim();
                            if (!StringUtils.isBlank(value)) {
                                emptyRow = false;
                            }
                            billingRowData.getCellData().put(key, value);
                        }
                    }
                }
                if (emptyRow && serviceId == 52) {
                    break;
                }
                if (!emptyRow) {
                    listRowData.add(billingRowData);
                }
            }
            if (serviceId == 1 || serviceId == 15 || serviceId == 52) {
                listRowData.add(new BillingRowDataModel());
            }
            billingData.setRowData(listRowData);
            context.put(Attributes.BILLING_DATA, billingData);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
