package test.com.gms.xms;

import java.io.*;

public class XI_118 {

    private static final String OLD_MARKUP_FOLDER = "D:\\Projects\\GMS\\bitbucket\\XI\\XI-118\\oldmarkup\\";
    private static final String HEADER_BASE_RATE = "update xms_tbl_customer_base_rate set rate = CASE \r";
    private static final String HEADER_BASE_RATE_DETAIL = "UPDATE xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br ON brd.customer_base_rate_id = br.customer_base_rate_id SET brd.rate = CASE \r";
    private static final String DHL_DESCRIPTION = "('DHL Worldwide Express - Package','DHL Worldwide Express - Documents','DHL Worldwide Express - Documents (Inbound)','DHL Worldwide Express - Package (Inbound)');";
    private static final String EXPRESS_DESCRIPTION = "('Express - Documents','Express - Package','Express - Documents (Inbound)','Express - Package (Inbound)');";
    private static final String ECONOMY_DESCRIPTION = "('Economy Express - Package','Economy Express - Package (Inbound)','Economy - Package','Economy - Package (Inbound)');";
    private static final double RATE_INCREMENT = 1.035;

    public static void main(String[] args) throws UnsupportedEncodingException {
//        Carrier DHL_MARKUP = new Carrier(100, 103.3, 111.9, 116.2125, CARRIER_TYPE.DHL, RATE_TYPE.MARKUP);
//        Carrier DHL_MARGIN = new Carrier(100, 103.3, 111.9, 116.2125, CARRIER_TYPE.DHL, RATE_TYPE.MARGIN);
        Carrier EXPRESS_MARKUP = new Carrier(100, 100, 112.5, 112.5, CARRIER_TYPE.EXPRESS, RATE_TYPE.MARKUP);
        Carrier EXPRESS_MARGIN = new Carrier(100, 100, 112.5, 112.5, CARRIER_TYPE.EXPRESS, RATE_TYPE.MARGIN);
        Carrier ECO_MARKUP = new Carrier(100, 100, 112.5, 112.5, CARRIER_TYPE.ECONOMY, RATE_TYPE.MARKUP);
        Carrier ECO_MARGIN = new Carrier(100, 100, 112.5, 112.5, CARRIER_TYPE.ECONOMY, RATE_TYPE.MARGIN);

//        genSQL("dhl_markup", DHL_MARKUP, false);
//        genSQL("dhl_margin", DHL_MARGIN, false);
        genSQL("tntexpress_markup", EXPRESS_MARKUP, false);
        genSQL("tntexpress_margin", EXPRESS_MARGIN, false);
        genSQL("tnteco_markup", ECO_MARKUP, false);
        genSQL("tnteco_margin", ECO_MARGIN, false);

        //base rate detail (by zone
//        genSQL("detail_dhl_markup", DHL_MARKUP, true);
//        genSQL("detail_dhl_margin", DHL_MARGIN, true);
        genSQL("detail_tntexpress_markup", EXPRESS_MARKUP, true);
        genSQL("detail_tntexpress_margin", EXPRESS_MARGIN, true);
        genSQL("detail_tnteco_markup", ECO_MARKUP, true);
        genSQL("detail_tnteco_margin", ECO_MARGIN, true);
    }

    /**
     *
     * @param inputFileName
     * @param carrier
     * @param isDetail if generate for by zone check
     */
    private static void genSQL(String inputFileName, Carrier carrier, boolean isDetail) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(OLD_MARKUP_FOLDER + inputFileName + ".txt")))) {
            Writer fw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(OLD_MARKUP_FOLDER + "new\\" + inputFileName + ".sql")), "UTF8"));
            BufferedWriter bw = new BufferedWriter(fw);
            if (isDetail) {
                bw.write(HEADER_BASE_RATE_DETAIL);
            } else {
                bw.write(HEADER_BASE_RATE);
            }
            String line;
            int rateTypeInt = 1;
            while ((line = br.readLine()) != null) {
                try {
                    double oldMarkup = Double.parseDouble(line);
                    if (oldMarkup >= 100) {
                        continue;
                    }
                    //calculate new markup rate
                    double customerCost2016;
                    double finalCustomerCost2017;
                    double newMarkup = 0;
                    switch (carrier.rateType) {
                        case MARKUP:
                            rateTypeInt = 1;
                            customerCost2016 = carrier.getFranchiseCostBefore() * (1 + oldMarkup / 100); //=C3+E3*C3
                            finalCustomerCost2017 = customerCost2016 * RATE_INCREMENT; //4.9%
                            newMarkup = (finalCustomerCost2017 - carrier.getFranchiseCostAfter()) / carrier.getFranchiseCostAfter() * 100; //=((I3-D3)/D3)
                            break;
                        case MARGIN:
                            rateTypeInt = 2;
                            customerCost2016 = carrier.getFranchiseCostBefore() / (1 - oldMarkup / 100); //=C3/(1-E3)
                            finalCustomerCost2017 = customerCost2016 * RATE_INCREMENT; //4.9%
                            newMarkup = (finalCustomerCost2017 - carrier.getFranchiseCostAfter()) / finalCustomerCost2017 * 100; //=((I3-D3)/I3)
                            break;
                    }
                    newMarkup = Math.round(newMarkup * 100.0) / 100.0;
                    if (oldMarkup % 1 == 0) {
                        if (isDetail) {
                            bw.write(" WHEN CAST(brd.rate as CHAR(8)) = '" + (int) oldMarkup + "' THEN " + newMarkup + "\r");
                        } else {
                            bw.write(" WHEN CAST(rate as CHAR(8)) = '" + (int) oldMarkup + "' THEN " + newMarkup + "\r");
                        }
                    } else {
                        if (isDetail) {
                            bw.write(" WHEN CAST(brd.rate as CHAR(8)) = '" + oldMarkup + "' THEN " + newMarkup + "\r");
                        } else {
                            bw.write(" WHEN CAST(rate as CHAR(8)) = '" + oldMarkup + "' THEN " + newMarkup + "\r");
                        }
                    }
                } catch (Exception ignored) {

                }
            }
            if (isDetail) {
                bw.write(" ELSE brd.rate END WHERE rate_type = " + rateTypeInt + " AND baserate_description IN ");
            } else {
                bw.write(" ELSE rate END WHERE rate_type = " + rateTypeInt + " AND baserate_description IN ");
            }
            switch (carrier.carrierType) {
                case DHL:
                    bw.write(DHL_DESCRIPTION);
                    break;
                case EXPRESS:
                    bw.write(EXPRESS_DESCRIPTION);
                    break;
                case ECONOMY:
                    bw.write(ECONOMY_DESCRIPTION);
                    break;
            }
            br.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum RATE_TYPE {
        MARKUP,
        MARGIN
    }

    private enum CARRIER_TYPE {
        DHL,
        EXPRESS,
        ECONOMY
    }

    static class Carrier {
        double columnA;
        double columnB;
        double franchiseCostBefore;
        double franchiseCostAfter;
        CARRIER_TYPE carrierType;
        RATE_TYPE rateType;

        Carrier(double columnA, double columnB, double franchiseCostBefore, double franchiseCostAfter, CARRIER_TYPE carrierType, RATE_TYPE rateType) {
            this.columnA = columnA;
            this.columnB = columnB;
            this.franchiseCostBefore = franchiseCostBefore;
            this.franchiseCostAfter = franchiseCostAfter;
            this.carrierType = carrierType;
            this.rateType = rateType;
        }

        public double getColumnA() {
            return columnA;
        }

        public void setColumnA(double columnA) {
            this.columnA = columnA;
        }

        public double getColumnB() {
            return columnB;
        }

        public void setColumnB(double columnB) {
            this.columnB = columnB;
        }

        public double getFranchiseCostBefore() {
            return franchiseCostBefore;
        }

        public void setFranchiseCostBefore(double franchiseCostBefore) {
            this.franchiseCostBefore = franchiseCostBefore;
        }

        public double getFranchiseCostAfter() {
            return franchiseCostAfter;
        }

        public void setFranchiseCostAfter(double franchiseCostAfter) {
            this.franchiseCostAfter = franchiseCostAfter;
        }

        public CARRIER_TYPE getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(CARRIER_TYPE carrierType) {
            this.carrierType = carrierType;
        }

        public RATE_TYPE getRateType() {
            return rateType;
        }

        public void setRateType(RATE_TYPE rateType) {
            this.rateType = rateType;
        }
    }

}
