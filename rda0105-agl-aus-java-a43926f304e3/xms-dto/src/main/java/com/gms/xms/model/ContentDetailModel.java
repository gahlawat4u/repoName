package com.gms.xms.model;

/**
 * Posted from ContentDetailModel
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ContentDetailModel extends BaseModel {
    private static final long serialVersionUID = -2612947952983428635L;

    private String contentDetailId;
    private String description;
    private String localizationId;

    public String getContentDetailId() {
        return contentDetailId;
    }

    public void setContentDetailId(String contentDetailId) {
        this.contentDetailId = contentDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "ContentDetailModel [contentDetailId=" + contentDetailId + ", description=" + description + ", localizationId=" + localizationId + "]";
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
        ContentDetailModel other = (ContentDetailModel) obj;
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
