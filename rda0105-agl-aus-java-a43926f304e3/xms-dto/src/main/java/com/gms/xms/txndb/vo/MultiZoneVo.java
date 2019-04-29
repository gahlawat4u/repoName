package com.gms.xms.txndb.vo;

public class MultiZoneVo extends BaseVo {
    private static final long serialVersionUID = 8634793722017224932L;

    private Long zoneId;

    private String originName;

    private String originCode;

    private String destinationName;

    private String destinationCode;

    private String zone;

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName == null ? null : originName.trim();
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode == null ? null : originCode.trim();
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName == null ? null : destinationName.trim();
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode == null ? null : destinationCode.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    @Override
    public String toString() {
        return "MultiZoneVo [zoneId=" + zoneId + ", originName=" + originName + ", originCode=" + originCode + ", destinationName=" + destinationName + ", destinationCode=" + destinationCode + ", zone=" + zone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destinationCode == null) ? 0 : destinationCode.hashCode());
        result = prime * result + ((destinationName == null) ? 0 : destinationName.hashCode());
        result = prime * result + ((originCode == null) ? 0 : originCode.hashCode());
        result = prime * result + ((originName == null) ? 0 : originName.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        result = prime * result + ((zoneId == null) ? 0 : zoneId.hashCode());
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
        MultiZoneVo other = (MultiZoneVo) obj;
        if (destinationCode == null) {
            if (other.destinationCode != null)
                return false;
        } else if (!destinationCode.equals(other.destinationCode))
            return false;
        if (destinationName == null) {
            if (other.destinationName != null)
                return false;
        } else if (!destinationName.equals(other.destinationName))
            return false;
        if (originCode == null) {
            if (other.originCode != null)
                return false;
        } else if (!originCode.equals(other.originCode))
            return false;
        if (originName == null) {
            if (other.originName != null)
                return false;
        } else if (!originName.equals(other.originName))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        if (zoneId == null) {
            if (other.zoneId != null)
                return false;
        } else if (!zoneId.equals(other.zoneId))
            return false;
        return true;
    }
}