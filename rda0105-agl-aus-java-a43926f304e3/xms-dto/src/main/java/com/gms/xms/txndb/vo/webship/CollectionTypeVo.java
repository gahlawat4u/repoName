package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CollectionTypeVo
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class CollectionTypeVo extends BaseVo {

    private static final long serialVersionUID = -3133441182575325123L;

    private Integer collectionTypeId;

    private String collectionTypeName;

    private Long localizationId;

    @Override
    public String toString() {
        return "CollectionTypeVo [collectionTypeId=" + collectionTypeId + ", collectionTypeName=" + collectionTypeName + ", localizationId=" + localizationId + "]";
    }

    public Integer getCollectionTypeId() {
        return collectionTypeId;
    }

    public void setCollectionTypeId(Integer collectionTypeId) {
        this.collectionTypeId = collectionTypeId;
    }

    public String getCollectionTypeName() {
        return collectionTypeName;
    }

    public void setCollectionTypeName(String collectionTypeName) {
        this.collectionTypeName = collectionTypeName == null ? null : collectionTypeName.trim();
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }
}