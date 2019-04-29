package com.gms.xms.model.importfield;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.StateModel;

import java.util.List;

/**
 * Posted from CustomerAddressBookImportMapStateCountryModel
 * <p>
 * Author HungNT Date May 13, 2015
 */
public class CustomerAddressBookImportMapStateCountryModel extends BaseModel {
    private static final long serialVersionUID = -8522724786411648705L;

    private String countryName;
    private String countryId;
    private String state;
    private List<StateModel> stateList;
    private String isSelectState;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsSelectState() {
        return isSelectState;
    }

    public void setIsSelectState(String isSelectState) {
        this.isSelectState = isSelectState;
    }

    public List<StateModel> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateModel> stateList) {
        this.stateList = stateList;
    }

    @Override
    public String toString() {
        return "CustomerAddressBookImportMapStateCountryModel [countryName=" + countryName + ", countryId=" + countryId + ", state=" + state + ", stateList=" + stateList + ", isSelectState=" + isSelectState + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((isSelectState == null) ? 0 : isSelectState.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((stateList == null) ? 0 : stateList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerAddressBookImportMapStateCountryModel other = (CustomerAddressBookImportMapStateCountryModel) obj;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (isSelectState == null) {
            if (other.isSelectState != null)
                return false;
        } else if (!isSelectState.equals(other.isSelectState))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (stateList == null) {
            if (other.stateList != null)
                return false;
        } else if (!stateList.equals(other.stateList))
            return false;
        return true;
    }
}
