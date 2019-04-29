package com.gms.xms.workflow.task2.importbilling.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.ImportBillingFieldsModel;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 30, 2016 11:48:41 AM
 * <p>
 * Author huynd
 */
public class PrepareFieldsTntDomesticTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareFieldsTntDomesticTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ImportBillingFieldsModel fields = new ImportBillingFieldsModel();
            Map<String, String> fieldName = new HashMap<String, String>();
            fieldName.put("0", "0");
            fieldName.put("1", "billing_account");
            fieldName.put("2", "shipper_detail");
            fieldName.put("3", "airbill_number");
            fieldName.put("4", "ship_date");
            fieldName.put("5", "service_code");
            fieldName.put("6", "sender_city");
            fieldName.put("7", "sender_postcode");
            fieldName.put("8", "receiver_detail");
            fieldName.put("9", "receiver_city");
            fieldName.put("10", "receiver_postcode");
            fieldName.put("11", "shipper_reference");
            fieldName.put("12", "S_R_T_payer");
            fieldName.put("13", "service_area_code_origin");
            fieldName.put("14", "service_area_code_destination");
            fieldName.put("15", "pal");
            fieldName.put("16", "weight");
            fieldName.put("17", "0");
            fieldName.put("18", "NetBasic");
            fieldName.put("19", "NetFreight");
            fieldName.put("20", "NetOther");
            fieldName.put("21", "NetMinAddon");
            fieldName.put("22", "access_RMP");
            fieldName.put("23", "access_RMD");
            fieldName.put("24", "access_OS0");
            fieldName.put("25", "access_OS1");
            fieldName.put("26", "access_OS2");
            fieldName.put("27", "access_OS3");
            fieldName.put("28", "access_RED");
            fieldName.put("29", "access_STR");
            fieldName.put("30", "access_RET");
            fieldName.put("31", "access_FUT");
            fieldName.put("32", "access_MC");
            fieldName.put("33", "access_LR");
            fieldName.put("34", "access_RSP");
            fieldName.put("35", "access_RSD");
            fieldName.put("36", "access_OS4");
            fieldName.put("37", "access_OS5");
            fieldName.put("38", "access_Misc");
            fieldName.put("39", "access_Dangerous Goods");
            fieldName.put("40", "NetATC");//remove 'access_' because this surcharge not exist in DB AU
            fieldName.put("41", "NetRTI");//remove 'access_'  because this surcharge not exist in DB AU
            fieldName.put("42", "CTR");//remove 'access_'  because this surcharge not exist in DB AU
            fieldName.put("43", "access_MHP");
            fieldName.put("44", "access_DSS");
            fieldName.put("45", "surcharge");
            fieldName.put("46", "total");

            fields.setFieldName(fieldName);

            // Prepare accessorial list
            AccessorialDao accessorialDao = new AccessorialDao();
            AccessorialVo accessorialVo = new AccessorialVo();
            accessorialVo.setCarrier(3L);
            List<SurchargeListVo> accessorials = accessorialDao.selectAccessorialListByCarrier(accessorialVo);
            String value = "", code;
            Integer count = 1;
            Map<String, String> accessorialData = new HashMap<>();
            for (int i = 0; i < fieldName.size(); i++) {
                if (fieldName.get(String.valueOf(i)).contains("access")) {
                    code = fieldName.get(String.valueOf(i)).replace("access_", "").replace("_", " ");
                    for (SurchargeListVo accessorial : accessorials) {
                        if (code.equalsIgnoreCase(accessorial.getCode()) || code.equalsIgnoreCase(accessorial.getDescription())) {
                            value = accessorial.getDescription();
                            accessorialData.put(String.valueOf(count), value);
                            count++;
                        }
                    }
                }
            }
            fields.setAccessorials(accessorialData);
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
