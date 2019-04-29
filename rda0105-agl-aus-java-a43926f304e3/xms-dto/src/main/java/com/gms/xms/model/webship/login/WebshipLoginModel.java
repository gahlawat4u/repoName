package com.gms.xms.model.webship.login;

import com.gms.xms.model.webship.WebshipModel;

/**
 * Posted from WebshipLoginModel
 * <p>
 * Author DatTV Date Jul 7, 2015 4:09:45 PM
 */
public class WebshipLoginModel extends WebshipModel {
    private static final long serialVersionUID = 2655196006705767674L;

    private String inactive;
    private String webshipAdminId;
    private String adminFunction;

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getWebshipAdminId() {
        return webshipAdminId;
    }

    public void setWebshipAdminId(String webshipAdminId) {
        this.webshipAdminId = webshipAdminId;
    }

    public String getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(String adminFunction) {
        this.adminFunction = adminFunction;
    }

    public String toString() {
        return "WebshipLoginModel [inactive=" + inactive + ", webshipAdminId=" + webshipAdminId + ", adminFunction=" + adminFunction + ", toString()=" + super.toString() + "]";
    }
}