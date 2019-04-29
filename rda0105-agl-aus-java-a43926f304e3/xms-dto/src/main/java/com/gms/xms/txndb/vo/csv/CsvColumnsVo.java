package com.gms.xms.txndb.vo.csv;

import com.gms.xms.txndb.vo.BaseVo;

public class CsvColumnsVo extends BaseVo {
    private static final long serialVersionUID = 665727581424567942L;
    private String columnKey;
    private String columnName;

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
