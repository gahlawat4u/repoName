package com.gms.xms.model.massedit;

import com.gms.xms.model.AccessorialModel;

/**
 * Posted from Jul 18, 2016 3:37:49 PM
 * <p>
 * Author huynd
 */
public class MassAccessorialModel extends AccessorialModel {

    private static final long serialVersionUID = 1L;

    private String defaultCharge;

    private String id;

    private String display;

    public String getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(String defaultCharge) {
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
        return "MassAccessorialModel [defaultCharge=" + defaultCharge + ", id=" + id + ", display=" + display + "]";
    }

}
