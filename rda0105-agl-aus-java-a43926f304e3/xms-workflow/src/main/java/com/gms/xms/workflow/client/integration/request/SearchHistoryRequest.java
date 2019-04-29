package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.ShipmentFilter;

/**
 * Posted from SearchHistoryRequest
 * <p>
 * Author TanDT Date Apr 17, 2015
 */
public class SearchHistoryRequest extends BaseRequest {
    private ShipmentFilter shipmentFilter;

    public ShipmentFilter getShipmentFilter() {
        return shipmentFilter;
    }

    public void setShipmentFilter(ShipmentFilter shipmentFilter) {
        this.shipmentFilter = shipmentFilter;
    }

    @Override
    public String toString() {
        return "SearchHistoryRequest [shipmentFilter=" + shipmentFilter + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentFilter == null) ? 0 : shipmentFilter.hashCode());
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
        SearchHistoryRequest other = (SearchHistoryRequest) obj;
        if (shipmentFilter == null) {
            if (other.shipmentFilter != null)
                return false;
        } else if (!shipmentFilter.equals(other.shipmentFilter))
            return false;
        return true;
    }


}
