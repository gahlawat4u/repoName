package com.gms.xms.txndb.vo.massedit;

import com.gms.xms.txndb.vo.AccessorialVo;

/**
 * Posted from Jul 18, 2016 3:37:45 PM
 * <p>
 * Author huynd
 */
public class MassAccessorialVo extends AccessorialVo {

    private static final long serialVersionUID = 1L;

    private Double defaultCharge;

    private String id;

    private String display;

    public Double getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "MassAccessorialVo [defaultCharge=" + defaultCharge + ", id=" + id + ", display=" + display + "]";
    }

}
