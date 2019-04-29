package com.gms.xms.model.webship.supplies;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.SupplyModel;

import java.util.List;

/**
 * Posted from SupplyServiceModel
 * <p>
 * Author DatTV Date Jul 16, 2015 10:27:49 AM
 */
public class SupplyServiceModel extends BaseModel {

    private static final long serialVersionUID = -1372957879929008251L;

    private ServiceModel service;
    private List<SupplyModel> supplies;

    @Override
    public String toString() {
        return "SupplyServiceModel [service=" + service + ", supplies=" + supplies + "]";
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public List<SupplyModel> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<SupplyModel> supplies) {
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
        SupplyServiceModel other = (SupplyServiceModel) obj;
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
