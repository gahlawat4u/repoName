package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.txndb.vo.ShipmentVo;

import java.util.List;

/**
 * Posted from SearchHistoryResponse
 * <p>
 * Author TanDT Date Apr 17, 2015
 * <p>
 * Posted from SearchHistoryResponse
 * <p>
 * Author TanDT Date Apr 17, 2015
 */
/**
 * Posted from SearchHistoryResponse
 * <p/>
 * Author TanDT Date Apr 17, 2015
 */

/**
 * Posted from SearchHistoryResponse
 * <p/>
 * Author TanDT Date Apr 17, 2015
 */
public class SearchHistoryResponse extends BaseResponse {

    private List<ShipmentVo> shipmentVos;
    private ShipmentFilter shipmentFilter;

    public List<ShipmentVo> getShipmentVos() {
        return shipmentVos;
    }

    public void setShipmentVos(List<ShipmentVo> shipmentVos) {
        this.shipmentVos = shipmentVos;
    }

    public ShipmentFilter getShipmentFilter() {
        return shipmentFilter;
    }

    public void setShipmentFilter(ShipmentFilter shipmentFilter) {
        this.shipmentFilter = shipmentFilter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentFilter == null) ? 0 : shipmentFilter.hashCode());
        result = prime * result + ((shipmentVos == null) ? 0 : shipmentVos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SearchHistoryResponse other = (SearchHistoryResponse) obj;
        if (shipmentFilter == null) {
            if (other.shipmentFilter != null)
                return false;
        } else if (!shipmentFilter.equals(other.shipmentFilter))
            return false;
        if (shipmentVos == null) {
            if (other.shipmentVos != null)
                return false;
        } else if (!shipmentVos.equals(other.shipmentVos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SearchHistoryResponse [shipmentVos=" + shipmentVos + ", shipmentFilter=" + shipmentFilter + "]";
    }

}
