package com.gms.xms.model.adjustment;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SubAdjustmentTypeModel
 * <p>
 * Author DatTV Date May 18, 2015 2:32:57 PM
 */
public class SubAdjustmentTypeModel extends BaseModel {

    private static final long serialVersionUID = -3066375211335012895L;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SubAdjustmentTypeModel [typeName=" + typeName + "]";
    }
}
