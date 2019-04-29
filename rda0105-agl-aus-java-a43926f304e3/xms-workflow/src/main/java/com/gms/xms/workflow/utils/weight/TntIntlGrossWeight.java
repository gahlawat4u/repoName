package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 28, 2016 4:20:04 PM
 * <p>
 * Author dattrinh
 */
public class TntIntlGrossWeight extends BaseGrossWeight {

    @Override
    public Integer getForceVolWeight() throws Exception {
        Integer defaultOrginCountry = Integer.valueOf(this.getSystemSettingDao().getSystemSettingByName("Default Origin Country").getSettingValue().trim());
        Integer forceVolWeight = null;
        if (defaultOrginCountry == 218) { // United Kingdom
            forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (TNT)").getSettingValue().trim()); // 5000;
        } else {
            forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (TNT Int)").getSettingValue().trim()); // 6000;
        }
        return forceVolWeight == null ? 5000 : forceVolWeight;
    }
}
