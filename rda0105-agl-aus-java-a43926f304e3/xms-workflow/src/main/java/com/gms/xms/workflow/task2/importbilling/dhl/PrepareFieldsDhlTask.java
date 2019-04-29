package com.gms.xms.workflow.task2.importbilling.dhl;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Aug 17, 2016 10:23:09 AM
 * <p>
 * Author huynd
 */
public class PrepareFieldsDhlTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsDhlTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "account");
            fieldName.put("1", "invoice");
            fieldName.put("2", "ship_date");
            fieldName.put("3", "origin_code");
            fieldName.put("4", "origin_text");
            fieldName.put("5", "airway_bill");
            fieldName.put("6", "shipper_reference");
            fieldName.put("7", "charge_description");
            fieldName.put("8", "destination_code");
            fieldName.put("9", "destination_text");
            fieldName.put("10", "product");
            fieldName.put("11", "weight");
            fieldName.put("12", "weight_type");
            fieldName.put("13", "pieces");
            fieldName.put("14", "charge");
            fieldName.put("15", "discount");
            fieldName.put("16", "tax");
            fieldName.put("17", "net_charge");
            fieldName.put("18", "sender_name");
            fieldName.put("19", "receiver_name");

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
