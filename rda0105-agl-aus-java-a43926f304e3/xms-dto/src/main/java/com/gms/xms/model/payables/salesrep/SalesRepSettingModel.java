package com.gms.xms.model.payables.salesrep;

import com.gms.xms.model.SalesRepModel;
import com.gms.xms.model.UserModel;

import java.util.List;

/**
 * Posted from SalesRepSettingModel
 * <p>
 * Author dattrinh Mar 21, 2016 1:58:46 PM
 */
public class SalesRepSettingModel extends SalesRepModel {

    private static final long serialVersionUID = 1L;

    private UserModel user;
    private List<SalesRepPayoutGoalModel> salesRepServices;
    private String active;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<SalesRepPayoutGoalModel> getSalesRepServices() {
        return salesRepServices;
    }

    public void setSalesRepServices(List<SalesRepPayoutGoalModel> salesRepServices) {
        this.salesRepServices = salesRepServices;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
