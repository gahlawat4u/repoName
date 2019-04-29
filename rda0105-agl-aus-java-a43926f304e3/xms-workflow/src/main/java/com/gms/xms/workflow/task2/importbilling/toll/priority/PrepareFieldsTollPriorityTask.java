package com.gms.xms.workflow.task2.importbilling.toll.priority;

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
 * Posted from May 31, 2016 2:17:35 PM
 * <p>
 * Author huynd
 */
public class PrepareFieldsTollPriorityTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsTollPriorityTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "0");
            fieldName.put("1", "service_code");
            fieldName.put("2", "0");
            fieldName.put("3", "product_code");
            fieldName.put("4", "description");
            fieldName.put("5", "ship_date");
            fieldName.put("6", "airbill_number");
            fieldName.put("7", "shipper_reference");
            fieldName.put("8", "shipper_reference2");
            fieldName.put("9", "shipper_detail");
            fieldName.put("10", "receiver_detail");
            fieldName.put("11", "service_area_code_origin");
            fieldName.put("12", "sender_postcode");
            fieldName.put("13", "sender_city");
            fieldName.put("14", "service_area_code_destination");
            fieldName.put("15", "receiver_postcode");
            fieldName.put("16", "receiver_city");
            fieldName.put("17", "pieces");
            fieldName.put("18", "weight");
            fieldName.put("19", "carrier_cost");
            fieldName.put("20", "0");
            fieldName.put("21", "0");
            fieldName.put("22", "fuel_surcharge");
            fieldName.put("23", "security");
            fieldName.put("24", "carbon");
            fieldName.put("25", "area");
            fieldName.put("26", "item_amount");
            fieldName.put("27", "gst_code");
            fieldName.put("28", "gst_amount");
            fieldName.put("29", "total_amount");

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
