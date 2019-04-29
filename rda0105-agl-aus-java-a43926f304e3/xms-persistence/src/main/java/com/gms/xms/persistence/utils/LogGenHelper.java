package com.gms.xms.persistence.utils;

import com.gms.xms.persistence.config.MyBatisSettings;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LogGenHelper {
    private Map<String, Object> tableMap = new HashMap<String, Object>();

    public LogGenHelper() {
        init();
    }

    private void init() {
        tableMap.clear();
        tableMap.put("xms_tbl_shipment_billing", new ShipmentBillingVo());
    }

    public void genXML() throws Exception {

        Connection conn = MyBatisSettings.getSqlSessionFactory().openSession().getConnection();
        // BEGIN
        String content = "";

        for (String tableName : tableMap.keySet()) {
            String actionFields = "";
            ResultSet rs = conn.prepareStatement("SHOW COLUMNS FROM " + tableName).executeQuery();
            Map<String, String> fieldMap = new HashMap<String, String>();
            String fn = "";
            while (rs.next()) {
                fn = rs.getString(1);
                fieldMap.put(fn.toLowerCase().replaceAll("_", ""), fn);
            }
            rs.close();

            Map<String, String> propertyMap = new HashMap<String, String>();
            for (String key : (Set<String>) BeanUtils.describe(tableMap.get(tableName)).keySet()) {
                propertyMap.put(key.toLowerCase(), key);
            }

            for (String fieldName : fieldMap.keySet()) {
                String propertyName = propertyMap.get(fieldName);
                if (propertyName != null) {
                    actionFields = actionFields + fieldMap.get(fieldName) + ">#(" + propertyName + ")#>#{" + propertyName + "}#@,@\n";
                }
            }
            if (actionFields.length() > 4)
                actionFields = actionFields.substring(0, actionFields.length() - 4);
            content += "<event id=\"ORIGINAL\"" + "  action-table=\"" + tableName + "\">" + "\n";
            content += "<action-fields>" + "\n";
            content += actionFields + "\n";
            content += "</action-fields>" + "\n";
            content += "<action-filter></action-filter>" + "\n";
            content += "</event>" + "\n";
        }
        System.out.println(content);
        // END
    }

}
