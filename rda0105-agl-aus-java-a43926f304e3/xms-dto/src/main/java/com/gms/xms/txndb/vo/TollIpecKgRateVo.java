package com.gms.xms.txndb.vo;

/**
 * @author tkvcl
 */
public class TollIpecKgRateVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -1103189528256415299L;

    private String customer;

    private String unit;

    private String service;

    private String source;

    private String destination;

    private String type;

    private Double minimum;

    private Double basic;

    private Double freight;

    private Integer cubic;

    private Double surcharge;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Integer getCubic() {
        return cubic;
    }

    public void setCubic(Integer cubic) {
        this.cubic = cubic;
    }

    public Double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return "TollIpecKgRateVo [customer=" + customer + ", unit=" + unit + ", service=" + service + ", source=" + source + ", destination=" + destination + ", type=" + type + ", minimum=" + minimum + ", basic=" + basic + ", freight=" + freight + ", cubic=" + cubic + ", surcharge=" + surcharge + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((basic == null) ? 0 : basic.hashCode());
        result = prime * result + ((cubic == null) ? 0 : cubic.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((freight == null) ? 0 : freight.hashCode());
        result = prime * result + ((minimum == null) ? 0 : minimum.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((surcharge == null) ? 0 : surcharge.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        TollIpecKgRateVo other = (TollIpecKgRateVo) obj;
        if (basic == null) {
            if (other.basic != null)
                return false;
        } else if (!basic.equals(other.basic))
            return false;
        if (cubic == null) {
            if (other.cubic != null)
                return false;
        } else if (!cubic.equals(other.cubic))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (freight == null) {
            if (other.freight != null)
                return false;
        } else if (!freight.equals(other.freight))
            return false;
        if (minimum == null) {
            if (other.minimum != null)
                return false;
        } else if (!minimum.equals(other.minimum))
            return false;
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (surcharge == null) {
            if (other.surcharge != null)
                return false;
        } else if (!surcharge.equals(other.surcharge))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }


}