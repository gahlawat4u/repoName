package com.gms.xms.workflow.task2.importbilling.tnt.intl;

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
 * Posted from May 9, 2016 5:19:53 PM
 * <p>
 * Author huynd
 */
public class PrepareFieldsTntIntlTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsTntIntlTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "airbill_number");
            fieldName.put("1", "ship_date");
            fieldName.put("2", "shipper_name");
            fieldName.put("3", "sender_city");
            fieldName.put("4", "sender_postcode");
            fieldName.put("5", "receiver_name");
            fieldName.put("6", "receiver_city");
            fieldName.put("7", "receiver_postcode");
            fieldName.put("8", "shipper_reference");
            fieldName.put("9", "service_area_code_origin");
            fieldName.put("10", "service_area_code_destination");
            fieldName.put("11", "pieces");
            fieldName.put("12", "weight");
            fieldName.put("13", "carrier_cost");
            fieldName.put("14", "fuel_surcharge");
            fieldName.put("15", "fuel_surcharge_cost");
            fieldName.put("16", "access_1_description");
            fieldName.put("17", "access_1_cost");
            fieldName.put("18", "access_2_description");
            fieldName.put("19", "access_2_cost");
            fieldName.put("20", "access_3_description");
            fieldName.put("21", "access_3_cost");
            fieldName.put("22", "access_4_description");
            fieldName.put("23", "access_4_cost");
            fieldName.put("24", "access_5_description");
            fieldName.put("25", "access_5_cost");
            fieldName.put("26", "access_6_description");
            fieldName.put("27", "access_6_cost");
            fieldName.put("28", "access_7_description");
            fieldName.put("29", "access_7_cost");
            fieldName.put("30", "access_8_description");
            fieldName.put("31", "access_8_cost");
            fieldName.put("32", "access_9_description");
            fieldName.put("33", "access_9_cost");
            fieldName.put("34", "access_10_description");
            fieldName.put("35", "access_10_cost");
            fieldName.put("36", "access_11_description");
            fieldName.put("37", "access_11_cost");
            fieldName.put("38", "sender_countrycode");
            fieldName.put("39", "receiver_countrycode");
            fieldName.put("40", "description");

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
