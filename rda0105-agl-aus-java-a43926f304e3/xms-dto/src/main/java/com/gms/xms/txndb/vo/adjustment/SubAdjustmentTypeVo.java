package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SubAdjustmentTypeVo
 * <p>
 * Author DatTV Date May 18, 2015 2:31:31 PM
 */
public class SubAdjustmentTypeVo extends BaseVo {

    private static final long serialVersionUID = 5734077964208852158L;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "AdjustmentTypeVo [typeName=" + typeName + "]";
    }
}
