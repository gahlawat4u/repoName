package com.gms.xms.weblib.controller.webship.history;

/**
 * Created by thinhdd
 * Date 6/9/2017.
 */
public class ColumnStatus {
    private String name;
    private Boolean show;

    public ColumnStatus(String name, Boolean show) {
        this.name = name;
        this.show = show;
    }

    public ColumnStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
