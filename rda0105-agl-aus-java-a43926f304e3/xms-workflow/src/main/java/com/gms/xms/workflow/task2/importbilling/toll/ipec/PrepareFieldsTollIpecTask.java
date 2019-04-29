package com.gms.xms.workflow.task2.importbilling.toll.ipec;

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
 * Posted from Jun 2, 2016 8:36:07 AM
 * <p>
 * Author huynd
 */
public class PrepareFieldsTollIpecTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsTollIpecTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "0");
            fieldName.put("1", "0");
            fieldName.put("2", "ship_date");
            fieldName.put("3", "airbill_number");
            fieldName.put("4", "shipper_reference");
            fieldName.put("5", "service_code");
            fieldName.put("6", "sender_company");
            fieldName.put("7", "from_suburb");
            fieldName.put("8", "from_pc");
            fieldName.put("9", "from_zone");
            fieldName.put("10", "receiver_company");
            fieldName.put("11", "to_suburb");
            fieldName.put("12", "to_pc");
            fieldName.put("13", "to_zone");
            fieldName.put("14", "pieces");
            fieldName.put("15", "0");
            fieldName.put("16", "0");
            fieldName.put("17", "weight");
            fieldName.put("18", "fuel_surcharge");
            fieldName.put("19", "base_charge");
            fieldName.put("20", "carrier_cost");
            fieldName.put("21", "tax_amount");
            fieldName.put("22", "0");
            fieldName.put("23", "0");
            fieldName.put("24", "0");
            fieldName.put("25", "0");
            fieldName.put("26", "0");
            fieldName.put("27", "description");
            fieldName.put("28", "extra_charge1");
            fieldName.put("29", "extra_amt1");
            fieldName.put("30", "extra_charge2");
            fieldName.put("31", "extra_amt2");
            fieldName.put("32", "extra_charge3");
            fieldName.put("33", "extra_amt3");
            fieldName.put("34", "extra_charge4");
            fieldName.put("35", "extra_amt4");
            fieldName.put("36", "extra_charge5");
            fieldName.put("37", "extra_amt5");

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
