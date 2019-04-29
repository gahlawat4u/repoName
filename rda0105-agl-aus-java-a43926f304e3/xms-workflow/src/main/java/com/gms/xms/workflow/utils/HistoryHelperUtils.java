/**
 *
 */
package com.gms.xms.workflow.utils;

import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;

/**
 * Posted from HistoryHelperUtils
 * <p>
 * Author TANDT
 */
public class HistoryHelperUtils {

    /***
     * Prepare History Detail Filter for get History Detail
     *
     * @param shipmentId
     * @throws Exception
     */
    public static HistoryDetailFilter prepareHistoryDetailFilter(Long shipmentId) throws Exception {
        HistoryDetailFilter detailFilterN = new HistoryDetailFilter();
        detailFilterN.setShipmentId(shipmentId);
        detailFilterN.setLbToKg(0.45359237D);
        detailFilterN.setInToCm(2.54D);
        detailFilterN.setWeightValue(5000D);
        return detailFilterN;
    }
}
