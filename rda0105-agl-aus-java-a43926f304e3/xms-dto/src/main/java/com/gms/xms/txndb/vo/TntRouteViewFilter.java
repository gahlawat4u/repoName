package com.gms.xms.txndb.vo;


/**
 * @author tkvcl
 */
public class TntRouteViewFilter extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -2687836409158569234L;

    private String destinationSuburb;


    private String postCode;


    private String serviceGroup;


    public String getDestinationSuburb() {
        return destinationSuburb;
    }


    public void setDestinationSuburb(String destinationSuburb) {
        this.destinationSuburb = destinationSuburb;
    }


    public String getPostCode() {
        return postCode;
    }


    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }


    public String getServiceGroup() {
        return serviceGroup;
    }


    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }


    @Override
    public String toString() {
        return "TntRouteViewFillter [destinationSuburb=" + destinationSuburb + ", postCode=" + postCode + ", serviceGroup=" + serviceGroup + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destinationSuburb == null) ? 0 : destinationSuburb.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((serviceGroup == null) ? 0 : serviceGroup.hashCode());
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
        TntRouteViewFilter other = (TntRouteViewFilter) obj;
        if (destinationSuburb == null) {
            if (other.destinationSuburb != null)
                return false;
        } else if (!destinationSuburb.equals(other.destinationSuburb))
            return false;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (serviceGroup == null) {
            if (other.serviceGroup != null)
                return false;
        } else if (!serviceGroup.equals(other.serviceGroup))
            return false;
        return true;
    }


}