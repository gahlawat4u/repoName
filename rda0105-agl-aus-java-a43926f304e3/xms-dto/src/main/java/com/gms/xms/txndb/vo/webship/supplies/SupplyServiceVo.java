package com.gms.xms.txndb.vo.webship.supplies;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.SupplyVo;

import java.util.List;

/**
 * Posted from SupplyServiceVo
 * <p>
 * Author DatTV Date Jul 16, 2015 10:27:37 AM
 */
public class SupplyServiceVo extends BaseVo {

    private static final long serialVersionUID = -6974451370797080371L;

    private ServiceVo service;
    private List<SupplyVo> supplies;

    @Override
    public String toString() {
        return "SupplyServiceVo [service=" + service + ", supplies=" + supplies + "]";
    }

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }

    public List<SupplyVo> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<SupplyVo> supplies) {
        this.supplies = supplies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        result = prime * result + ((supplies == null) ? 0 : supplies.hashCode());
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
        SupplyServiceVo other = (SupplyServiceVo) obj;
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        if (supplies == null) {
            if (other.supplies != null)
                return false;
        } else if (!supplies.equals(other.supplies))
            return false;
        return true;
    }

}
