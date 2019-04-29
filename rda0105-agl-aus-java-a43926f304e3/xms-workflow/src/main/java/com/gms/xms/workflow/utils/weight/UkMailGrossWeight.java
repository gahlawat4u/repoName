package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 28, 2016 4:20:04 PM
 * <p>
 * Author dattrinh
 */
public class UkMailGrossWeight extends BaseGrossWeight {

    @Override
    public Integer getForceVolWeight() throws Exception {
        Integer forceVolWeight = null;
        forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (UKMail)").getSettingValue().trim()); // 4000;
        return forceVolWeight == null ? 5000 : forceVolWeight;
    }
}
