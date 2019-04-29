package com.gms.xms.txndb.vo;

public class ContentDetailVo extends BaseVo {
    private static final long serialVersionUID = -8160478819572024906L;

    private Long contentDetailId;

    private String description;

    private Integer localizationId;

    public Long getContentDetailId() {
        return contentDetailId;
    }

    public void setContentDetailId(Long contentDetailId) {
        this.contentDetailId = contentDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Integer localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "ContentDetailVo [contentDetailId=" + contentDetailId + ", description=" + description + ", localizationId=" + localizationId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contentDetailId == null) ? 0 : contentDetailId.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
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
        ContentDetailVo other = (ContentDetailVo) obj;
        if (contentDetailId == null) {
            if (other.contentDetailId != null)
                return false;
        } else if (!contentDetailId.equals(other.contentDetailId))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        return true;
    }
}