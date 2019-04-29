package com.gms.xms.model.admin.quicksearch;

public class SearchTypeModel {
    private String id;
    private String typeName;

    public SearchTypeModel(String id, String typeName) {
        super();
        this.id = id;
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SearchTypeModel [id=" + id + ", typeName=" + typeName + "]";
    }
}
