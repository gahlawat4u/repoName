package com.gms.xms.txndb.vo;


/**
 * @author tkvcl
 */
public class TollIpecTotalRateVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 3069400519903111681L;

    private String customer;

    private String unit;

    private String service;

    private String source;

    private String destination;

    private String type;

    private Integer startRange;

    private Integer range1;

    private Integer range2;

    private Integer range3;

    private Integer range4;

    private Integer range5;

    private Integer range6;

    private Integer range7;

    private Double charge1;

    private Double charge2;

    private Double charge3;

    private Double charge4;

    private Double charge5;

    private Double charge6;

    private Double charge7;

    private Integer cubic;

    private Double minimum;

    private Integer additional;

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

    public Integer getStartRange() {
        return startRange;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    public Integer getRange1() {
        return range1;
    }

    public void setRange1(Integer range1) {
        this.range1 = range1;
    }

    public Integer getRange2() {
        return range2;
    }

    public void setRange2(Integer range2) {
        this.range2 = range2;
    }

    public Integer getRange3() {
        return range3;
    }

    public void setRange3(Integer range3) {
        this.range3 = range3;
    }

    public Integer getRange4() {
        return range4;
    }

    public void setRange4(Integer range4) {
        this.range4 = range4;
    }

    public Integer getRange5() {
        return range5;
    }

    public void setRange5(Integer range5) {
        this.range5 = range5;
    }

    public Integer getRange6() {
        return range6;
    }

    public void setRange6(Integer range6) {
        this.range6 = range6;
    }

    public Integer getRange7() {
        return range7;
    }

    public void setRange7(Integer range7) {
        this.range7 = range7;
    }

    public Double getCharge1() {
        return charge1;
    }

    public void setCharge1(Double charge1) {
        this.charge1 = charge1;
    }

    public Double getCharge2() {
        return charge2;
    }

    public void setCharge2(Double charge2) {
        this.charge2 = charge2;
    }

    public Double getCharge3() {
        return charge3;
    }

    public void setCharge3(Double charge3) {
        this.charge3 = charge3;
    }

    public Double getCharge4() {
        return charge4;
    }

    public void setCharge4(Double charge4) {
        this.charge4 = charge4;
    }

    public Double getCharge5() {
        return charge5;
    }

    public void setCharge5(Double charge5) {
        this.charge5 = charge5;
    }

    public Double getCharge6() {
        return charge6;
    }

    public void setCharge6(Double charge6) {
        this.charge6 = charge6;
    }

    public Double getCharge7() {
        return charge7;
    }

    public void setCharge7(Double charge7) {
        this.charge7 = charge7;
    }

    public Integer getCubic() {
        return cubic;
    }

    public void setCubic(Integer cubic) {
        this.cubic = cubic;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Integer getAdditional() {
        return additional;
    }

    public void setAdditional(Integer additional) {
        this.additional = additional;
    }

    public Double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return "TollIpecTotalRateVo [customer=" + customer + ", unit=" + unit + ", service=" + service + ", source=" + source + ", destination=" + destination + ", type=" + type + ", startRange=" + startRange + ", range1=" + range1 + ", range2=" + range2 + ", range3=" + range3 + ", range4=" + range4 + ", range5=" + range5 + ", range6=" + range6 + ", range7=" + range7 + ", charge1=" + charge1 + ", charge2=" + charge2 + ", charge3=" + charge3 + ", charge4=" + charge4 + ", charge5=" + charge5
                + ", charge6=" + charge6 + ", charge7=" + charge7 + ", cubic=" + cubic + ", minimum=" + minimum + ", additional=" + additional + ", surcharge=" + surcharge + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((additional == null) ? 0 : additional.hashCode());
        result = prime * result + ((charge1 == null) ? 0 : charge1.hashCode());
        result = prime * result + ((charge2 == null) ? 0 : charge2.hashCode());
        result = prime * result + ((charge3 == null) ? 0 : charge3.hashCode());
        result = prime * result + ((charge4 == null) ? 0 : charge4.hashCode());
        result = prime * result + ((charge5 == null) ? 0 : charge5.hashCode());
        result = prime * result + ((charge6 == null) ? 0 : charge6.hashCode());
        result = prime * result + ((charge7 == null) ? 0 : charge7.hashCode());
        result = prime * result + ((cubic == null) ? 0 : cubic.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((minimum == null) ? 0 : minimum.hashCode());
        result = prime * result + ((range1 == null) ? 0 : range1.hashCode());
        result = prime * result + ((range2 == null) ? 0 : range2.hashCode());
        result = prime * result + ((range3 == null) ? 0 : range3.hashCode());
        result = prime * result + ((range4 == null) ? 0 : range4.hashCode());
        result = prime * result + ((range5 == null) ? 0 : range5.hashCode());
        result = prime * result + ((range6 == null) ? 0 : range6.hashCode());
        result = prime * result + ((range7 == null) ? 0 : range7.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((startRange == null) ? 0 : startRange.hashCode());
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
        TollIpecTotalRateVo other = (TollIpecTotalRateVo) obj;
        if (additional == null) {
            if (other.additional != null)
                return false;
        } else if (!additional.equals(other.additional))
            return false;
        if (charge1 == null) {
            if (other.charge1 != null)
                return false;
        } else if (!charge1.equals(other.charge1))
            return false;
        if (charge2 == null) {
            if (other.charge2 != null)
                return false;
        } else if (!charge2.equals(other.charge2))
            return false;
        if (charge3 == null) {
            if (other.charge3 != null)
                return false;
        } else if (!charge3.equals(other.charge3))
            return false;
        if (charge4 == null) {
            if (other.charge4 != null)
                return false;
        } else if (!charge4.equals(other.charge4))
            return false;
        if (charge5 == null) {
            if (other.charge5 != null)
                return false;
        } else if (!charge5.equals(other.charge5))
            return false;
        if (charge6 == null) {
            if (other.charge6 != null)
                return false;
        } else if (!charge6.equals(other.charge6))
            return false;
        if (charge7 == null) {
            if (other.charge7 != null)
                return false;
        } else if (!charge7.equals(other.charge7))
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
        if (minimum == null) {
            if (other.minimum != null)
                return false;
        } else if (!minimum.equals(other.minimum))
            return false;
        if (range1 == null) {
            if (other.range1 != null)
                return false;
        } else if (!range1.equals(other.range1))
            return false;
        if (range2 == null) {
            if (other.range2 != null)
                return false;
        } else if (!range2.equals(other.range2))
            return false;
        if (range3 == null) {
            if (other.range3 != null)
                return false;
        } else if (!range3.equals(other.range3))
            return false;
        if (range4 == null) {
            if (other.range4 != null)
                return false;
        } else if (!range4.equals(other.range4))
            return false;
        if (range5 == null) {
            if (other.range5 != null)
                return false;
        } else if (!range5.equals(other.range5))
            return false;
        if (range6 == null) {
            if (other.range6 != null)
                return false;
        } else if (!range6.equals(other.range6))
            return false;
        if (range7 == null) {
            if (other.range7 != null)
                return false;
        } else if (!range7.equals(other.range7))
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
        if (startRange == null) {
            if (other.startRange != null)
                return false;
        } else if (!startRange.equals(other.startRange))
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