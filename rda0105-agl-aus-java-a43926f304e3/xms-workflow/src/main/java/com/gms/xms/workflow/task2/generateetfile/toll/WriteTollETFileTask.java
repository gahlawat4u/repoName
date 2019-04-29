package com.gms.xms.workflow.task2.generateetfile.toll;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.generateetfile.GenerateTollETDataVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Posted from Sep 23, 2016 2:26:53 PM
 * <p>
 * Author huynd
 */
public class WriteTollETFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(WriteTollETFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Create ET file
            String filePath = context.getString(GenerateETFileConstants.FILE_PATH);
            filePath = filePath.replace("\\", "/").trim();
            String fileName = context.getString(GenerateETFileConstants.FILE_NAME);
            DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(filePath + fileName));
            // Write ET file
            String aRow;
            Map<String, String> aMap;
            List<GenerateTollETDataVo> tollETData = context.get(GenerateETFileConstants.GENERATE_TOLL_ET_DATA);
            for (GenerateTollETDataVo tollETDataRow : tollETData) {
                aMap = tollETDataRow.getaRow();
                aRow = "";
                aRow += aMap.get("airbill_number");
                aRow += aMap.get("line_item_no");
                aRow += aMap.get("connote_entered_code");
                aRow += aMap.get("activity_code");
                aRow += aMap.get("segment_code");
                aRow += aMap.get("service_code");
                aRow += aMap.get("system_identifer");
                aRow += aMap.get("despatch_date");
                aRow += aMap.get("special_handling");
                aRow += aMap.get("status_flag");
                aRow += aMap.get("receiver_company_name");
                aRow += aMap.get("receiver_address1");
                aRow += aMap.get("receiver_address2");
                aRow += aMap.get("receiver_city_name");
                aRow += aMap.get("receiver_postal_code");
                aRow += aMap.get("delv_early_date");
                aRow += aMap.get("delv_late_date");
                aRow += aMap.get("delv_early_time");
                aRow += aMap.get("delv_late_time");
                aRow += aMap.get("delv_special_instruction");
                aRow += aMap.get("delv_special_code");
                aRow += aMap.get("receiver_contact_name");
                aRow += aMap.get("receiver_phone");
                aRow += aMap.get("receiver_fax");
                aRow += aMap.get("sender_company_name");
                aRow += aMap.get("sender_city_name");
                aRow += aMap.get("sender_postal_code");
                aRow += aMap.get("sender_contact_name");
                aRow += aMap.get("sender_phone");
                aRow += aMap.get("sender_fax");
                aRow += aMap.get("connote_reference");
                aRow += aMap.get("receiver_key");
                aRow += aMap.get("pallet_chep");
                aRow += aMap.get("pallet_loscam");
                aRow += aMap.get("pallet_other");
                aRow += aMap.get("insur_class");
                aRow += aMap.get("insur_cover");
                aRow += aMap.get("charge_code");
                aRow += aMap.get("apply_basic_charge");
                aRow += aMap.get("charge_account");
                aRow += aMap.get("charge_basic");
                aRow += aMap.get("charge_freight");
                aRow += aMap.get("charge_extra");
                aRow += aMap.get("charge_other");
                aRow += aMap.get("charge_tax");
                aRow += aMap.get("charge_surcharge");
                aRow += aMap.get("charge_total");
                aRow += aMap.get("charge_insur_per_con");
                aRow += aMap.get("charge_insur_additional");
                aRow += aMap.get("item_identifier");
                aRow += aMap.get("line_unit_commod_items_count");
                aRow += aMap.get("line_unit_commod_items_code");
                aRow += aMap.get("number_of_items");
                aRow += aMap.get("line_weight");
                aRow += aMap.get("line_commod");
                aRow += aMap.get("package_type");
                aRow += aMap.get("desc_of_goods");
                aRow += aMap.get("cubic_length");
                aRow += aMap.get("cubic_width");
                aRow += aMap.get("cubic_height");
                aRow += aMap.get("cubic_qty");
                aRow += aMap.get("cubic_volume");
                aRow += aMap.get("sender_reference");
                aRow += aMap.get("rating_code");
                aRow += aMap.get("consolidation_flag");
                aRow += aMap.get("security_flag");
                aRow += aMap.get("remote_printing_flag");
                aRow += aMap.get("handpriced_insur");
                aRow += aMap.get("direct_delivery");
                aRow += aMap.get("returns_collections");
                aRow += aMap.get("service_description");
                aRow += aMap.get("extra_chrg_codes");
                aRow += aMap.get("extended_description");
                aRow += aMap.get("sender_address1");
                aRow += aMap.get("sender_address2");
                aRow += aMap.get("connote_narration");
                aRow += aMap.get("sender_location");
                aRow += aMap.get("sender_location_ext");
                aRow += aMap.get("receiver_location");
                aRow += aMap.get("receiver_location_ext");
                aRow += aMap.get("danger_class");
                aRow += aMap.get("dg_pack_group");
                aRow += aMap.get("dg_un_code");
                aRow += aMap.get("item_number");
                aRow += aMap.get("extended_connote_reference");
                aRow += aMap.get("sender_country_name");
                aRow += aMap.get("receiver_country_name");
                aRow += aMap.get("declared_value");
                aRow += aMap.get("declared_currency_code");
                aRow += aMap.get("other_refs");
                aRow += aMap.get("spare");
                aRow += aMap.get("hud_batch_number");
                aRow += aMap.get("sender_id");
                aRow += aMap.get("sender_state");
                aRow += aMap.get("receiver_state");
                aRow += aMap.get("manifest_number");
                aRow += aMap.get("record_id");
                aRow += aMap.get("system_reserved");
                aRow += aMap.get("checksum");
                // aRow += aMap.get("terminator");
                aRow += "\r\n";
                dataOut.write(aRow.getBytes("UTF-8"));
            }
            dataOut.write("%%EOF\r\n".getBytes("UTF-8"));
            dataOut.close();
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
