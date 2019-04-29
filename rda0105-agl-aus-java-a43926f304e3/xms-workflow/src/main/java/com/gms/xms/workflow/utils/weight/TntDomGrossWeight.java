package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 28, 2016 4:20:04 PM
 * <p>
 * Author dattrinh
 */
public class TntDomGrossWeight extends BaseGrossWeight {

    @Override
    public Integer getForceVolWeight() throws Exception {
        Integer forceVolWeight = null;
        forceVolWeight = Integer.parseInt(this.getSystemSettingDao().getSystemSettingByName("Volume Weight Divided By (TNT)").getSettingValue().trim()); // 5000;
        return forceVolWeight == null ? 5000 : forceVolWeight;
    }
}
