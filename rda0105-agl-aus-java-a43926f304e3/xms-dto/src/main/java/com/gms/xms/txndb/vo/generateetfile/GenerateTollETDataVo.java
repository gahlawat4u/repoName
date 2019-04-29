package com.gms.xms.txndb.vo.generateetfile;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Map;

/**
 * Posted from Sep 23, 2016 11:07:59 AM
 * <p>
 * Author huynd
 */
public class GenerateTollETDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Map<String, String> aRow;

    public Map<String, String> getaRow() {
        return aRow;
    }

    public void setaRow(Map<String, String> aRow) {
        this.aRow = aRow;
    }

    @Override
    public String toString() {
        return "GenerateTollETDataVo [aRow=" + aRow + "]";
    }
}
