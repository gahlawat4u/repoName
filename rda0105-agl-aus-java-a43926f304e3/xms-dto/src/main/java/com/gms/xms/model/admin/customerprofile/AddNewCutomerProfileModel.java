package com.gms.xms.model.admin.customerprofile;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.SaveCustomerProfileBaseRateModel;

public class AddNewCutomerProfileModel extends BaseModel {
    private static final long serialVersionUID = 85788423537529239L;

    private CustomerProfileModel customerProfile;
    private SaveCustomerProfileBaseRateModel saveCustProfileBaseRate;
    private CustomerCollectionModel collection;

    public CustomerProfileModel getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(CustomerProfileModel customerProfile) {
        this.customerProfile = customerProfile;
    }

    public SaveCustomerProfileBaseRateModel getSaveCustProfileBaseRate() {
        return saveCustProfileBaseRate;
    }

    public void setSaveCustProfileBaseRate(SaveCustomerProfileBaseRateModel saveCustProfileBaseRate) {
        this.saveCustProfileBaseRate = saveCustProfileBaseRate;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "AddNewCutomerProfileModel [customerProfile=" + customerProfile + ", saveCustProfileBaseRate=" + saveCustProfileBaseRate + ", collection=" + collection + "]";
    }
}
