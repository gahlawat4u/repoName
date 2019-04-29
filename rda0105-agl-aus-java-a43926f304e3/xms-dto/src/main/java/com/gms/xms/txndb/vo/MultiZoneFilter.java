package com.gms.xms.txndb.vo;

/**
 * Posted from MultiZoneFilter
 * <p>
 * Author HungNT Date Apr 20, 2015
 */
public class MultiZoneFilter extends MultiZoneVo {
    private static final long serialVersionUID = -2258844176988076800L;

    private String senderZoneCode;
    private String receiverZoneCode;

    public String getSenderZoneCode() {
        return senderZoneCode;
    }

    public void setSenderZoneCode(String senderZoneCode) {
        this.senderZoneCode = senderZoneCode;
    }

    public String getReceiverZoneCode() {
        return receiverZoneCode;
    }

    public void setReceiverZoneCode(String receiverZoneCode) {
        this.receiverZoneCode = receiverZoneCode;
    }

    @Override
    public String toString() {
        return "MultiZoneFilter [senderZoneCode=" + senderZoneCode + ", receiverZoneCode=" + receiverZoneCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((receiverZoneCode == null) ? 0 : receiverZoneCode.hashCode());
        result = prime * result + ((senderZoneCode == null) ? 0 : senderZoneCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MultiZoneFilter other = (MultiZoneFilter) obj;
        if (receiverZoneCode == null) {
            if (other.receiverZoneCode != null)
                return false;
        } else if (!receiverZoneCode.equals(other.receiverZoneCode))
            return false;
        if (senderZoneCode == null) {
            if (other.senderZoneCode != null)
                return false;
        } else if (!senderZoneCode.equals(other.senderZoneCode))
            return false;
        return true;
    }
}
