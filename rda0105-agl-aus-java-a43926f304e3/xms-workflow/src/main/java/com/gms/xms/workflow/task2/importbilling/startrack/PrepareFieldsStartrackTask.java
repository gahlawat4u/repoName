package com.gms.xms.workflow.task2.importbilling.startrack;

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
 * Posted from Jun 22, 2016 11:52:36 AM
 * <p>
 * Author huynd
 */
public class PrepareFieldsStartrackTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsStartrackTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "account_number");
            fieldName.put("1", "customer_code");
            fieldName.put("2", "service_area_code_origin");
            fieldName.put("3", "service_area_code_destination");
            fieldName.put("4", "service_code");
            fieldName.put("5", "ship_date");
            fieldName.put("6", "airbill_number");
            fieldName.put("7", "shipper_ref");
            fieldName.put("8", "pieces");
            fieldName.put("9", "cubic_weight");
            fieldName.put("10", "0");
            fieldName.put("11", "shipper_name");
            fieldName.put("12", "0");
            fieldName.put("13", "sender_address1");
            fieldName.put("14", "sender_address2");
            fieldName.put("15", "sender_city");
            fieldName.put("16", "sender_postcode");
            fieldName.put("17", "sender_state");
            fieldName.put("18", "receiver_name");
            fieldName.put("19", "0");
            fieldName.put("20", "receiver_address1");
            fieldName.put("21", "receiver_address2");
            fieldName.put("22", "receiver_city");
            fieldName.put("23", "receiver_postcode");
            fieldName.put("24", "receiver_state");
            fieldName.put("25", "receiver_phone");
            fieldName.put("26", "shipper_ref2");
            fieldName.put("27", "0");
            fieldName.put("28", "shipper_ref3");
            fieldName.put("29", "0");
            fieldName.put("30", "weight");
            fieldName.put("31", "base_charge");
            fieldName.put("32", "fuel_surcharge");
            fieldName.put("33", "security_surcharge");
            fieldName.put("34", "dg_surcharge");
            fieldName.put("35", "manual_surcharge");
            fieldName.put("36", "futile_pickup_surcharge");
            fieldName.put("37", "po_box_delivery_surcharge");
            fieldName.put("38", "carded_freight_surcharge");
            fieldName.put("39", "second_delivery_surcharge");

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
