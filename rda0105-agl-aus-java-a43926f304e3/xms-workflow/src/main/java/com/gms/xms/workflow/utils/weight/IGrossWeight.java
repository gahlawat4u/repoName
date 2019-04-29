package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 28, 2016 4:09:19 PM
 * <p>
 * Author dattrinh
 */
public interface IGrossWeight {
    public double getGrossWeight(double l, double w, double h, String dimUnit) throws Exception;

    public Integer getForceVolWeight() throws Exception;
}
