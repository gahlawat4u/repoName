package test.com.gms.xms;

import java.io.*;

public class XI_118_InsertForFranchise {

    private static final String OUT_FILE_PATH = "D:\\Projects\\GMS\\bitbucket\\XI\\XI-118\\fill_missing_franchise_base_rate.sql";
    private static final String INSERT_CUS_BASE_RATE = "INSERT INTO `xms_tbl_customer_base_rate` (`customer_code`, `shipment_type_id`, `rate_type`, `weight`, `rate`, `zone_check`, `content`, `bound`, `baserate_description`, `carrierid`) VALUES ('";
    private static final String EXPRESS_DOC = "', '213', '1', '0', '3.5', '0', '0', '0', 'Express - Documents', '54');";
    private static final String EXPRESS_PACKAGE = "', '213', '1', '0', '3.5', '0', '1', '0', 'Express - Package', '54');";
    private static final String EXPRESS_DOC_INBOUND = "', '213', '1', '0', '3.5', '0', '0', '1', 'Express - Documents (Inbound)', '54');";
    private static final String EXPRESS_PACKAGE_INBOUND = "', '213', '1', '0', '3.5', '0', '1', '1', 'Express - Package (Inbound)', '54');";
    private static final String ECONOMY_EXPRESS_PACKAGE = "', '214', '1', '0', '3.5', '0', '1', '0', 'Economy Express - Package', '54');";
    private static final String ECONOMY_EXPRESS_PACKAGE_INBOUND = "', '214', '1', '0', '3.5', '0', '1', '1', 'Economy Express - Package (Inbound)', '54');";
    private static final String[] SUFFIX_TYPE_LIST = new String[]{EXPRESS_DOC, EXPRESS_PACKAGE, EXPRESS_DOC_INBOUND, EXPRESS_PACKAGE_INBOUND, ECONOMY_EXPRESS_PACKAGE, ECONOMY_EXPRESS_PACKAGE_INBOUND};
    private static final String[] CUSTOMER_CODE_LIST = new String[]{"10300001", "10400001", "10500001", "10600001", "10700001", "10800001", "10900001", "11000001", "11100001", "11200001", "11500001", "11600001", "11700001", "11900001", "12000001", "12100001", "12200001", "12300001", "12400001", "12500001", "12600001", "12700001", "12800001", "12900001", "13000001", "13200001", "13300001", "13400001", "13600001", "13700001", "13800001", "13900001"};

    public static void main(String[] args) throws IOException {
        genSQL();
    }

    private static void genSQL() throws IOException {
        Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(OUT_FILE_PATH)), "UTF8"));
        BufferedWriter bw = new BufferedWriter(fw);
        for (String customerCode : CUSTOMER_CODE_LIST) {
            for (String type : SUFFIX_TYPE_LIST) {
                bw.write(INSERT_CUS_BASE_RATE);
                bw.write(customerCode);
                bw.write(type);
                bw.write("\r\n");
            }
        }

        bw.close();
        fw.close();

    }
}
