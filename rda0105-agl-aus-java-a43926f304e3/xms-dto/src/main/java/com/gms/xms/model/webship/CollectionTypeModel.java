package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CollectionTypeModel
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class CollectionTypeModel extends BaseModel {

    private static final long serialVersionUID = -1325708947681893411L;

    private String collectionTypeId;

    private String collectionTypeName;

    private String localizationId;

    @Override
    public String toString() {
        return "CollectionTypeModel [collectionTypeId=" + collectionTypeId + ", collectionTypeName=" + collectionTypeName + ", localizationId=" + localizationId + "]";
    }

    public String getCollectionTypeId() {
        return collectionTypeId;
    }

    public void setCollectionTypeId(String collectionTypeId) {
        this.collectionTypeId = collectionTypeId;
    }

    public String getCollectionTypeName() {
        return collectionTypeName;
    }

    public void setCollectionTypeName(String collectionTypeName) {
        this.collectionTypeName = collectionTypeName;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}