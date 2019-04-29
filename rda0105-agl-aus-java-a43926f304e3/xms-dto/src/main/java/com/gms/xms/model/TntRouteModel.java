package com.gms.xms.model;


/**
 * @author tkvcl
 */
public class TntRouteModel extends BaseModel {


    /**
     *
     */
    private static final long serialVersionUID = 1388173088770581556L;

    private String destinationSuburb;

    private String stateCode;

    private String postCode;

    private String originDepot;

    private String gatewayDepot;

    private String forwardingDepot;

    private String routeBin;

    private String serviceGroup;

    public String getDestinationSuburb() {
        return destinationSuburb;
    }

    public void setDestinationSuburb(String destinationSuburb) {
        this.destinationSuburb = destinationSuburb;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOriginDepot() {
        return originDepot;
    }

    public void setOriginDepot(String originDepot) {
        this.originDepot = originDepot;
    }

    public String getGatewayDepot() {
        return gatewayDepot;
    }

    public void setGatewayDepot(String gatewayDepot) {
        this.gatewayDepot = gatewayDepot;
    }

    public String getForwardingDepot() {
        return forwardingDepot;
    }

    public void setForwardingDepot(String forwardingDepot) {
        this.forwardingDepot = forwardingDepot;
    }

    public String getRouteBin() {
        return routeBin;
    }

    public void setRouteBin(String routeBin) {
        this.routeBin = routeBin;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    @Override
    public String toString() {
        return "TntRouteVo [destinationSuburb=" + destinationSuburb + ", stateCode=" + stateCode + ", postCode=" + postCode + ", originDepot=" + originDepot + ", gatewayDepot=" + gatewayDepot + ", forwardingDepot=" + forwardingDepot + ", routeBin=" + routeBin + ", serviceGroup=" + serviceGroup + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destinationSuburb == null) ? 0 : destinationSuburb.hashCode());
        result = prime * result + ((forwardingDepot == null) ? 0 : forwardingDepot.hashCode());
        result = prime * result + ((gatewayDepot == null) ? 0 : gatewayDepot.hashCode());
        result = prime * result + ((originDepot == null) ? 0 : originDepot.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((routeBin == null) ? 0 : routeBin.hashCode());
        result = prime * result + ((serviceGroup == null) ? 0 : serviceGroup.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
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
        TntRouteModel other = (TntRouteModel) obj;
        if (destinationSuburb == null) {
            if (other.destinationSuburb != null)
                return false;
        } else if (!destinationSuburb.equals(other.destinationSuburb))
            return false;
        if (forwardingDepot == null) {
            if (other.forwardingDepot != null)
                return false;
        } else if (!forwardingDepot.equals(other.forwardingDepot))
            return false;
        if (gatewayDepot == null) {
            if (other.gatewayDepot != null)
                return false;
        } else if (!gatewayDepot.equals(other.gatewayDepot))
            return false;
        if (originDepot == null) {
            if (other.originDepot != null)
                return false;
        } else if (!originDepot.equals(other.originDepot))
            return false;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (routeBin == null) {
            if (other.routeBin != null)
                return false;
        } else if (!routeBin.equals(other.routeBin))
            return false;
        if (serviceGroup == null) {
            if (other.serviceGroup != null)
                return false;
        } else if (!serviceGroup.equals(other.serviceGroup))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        return true;
    }


}