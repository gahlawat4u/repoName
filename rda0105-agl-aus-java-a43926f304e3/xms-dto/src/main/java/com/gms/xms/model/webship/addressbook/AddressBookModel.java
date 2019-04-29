package com.gms.xms.model.webship.addressbook;

import com.gms.xms.model.StateModel;
import com.gms.xms.model.webship.CustomerAddressBookModel;

import java.util.List;

/**
 * Posted from AddressBookModel
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class AddressBookModel extends CustomerAddressBookModel {
    private static final long serialVersionUID = -7664089405550138431L;

    private String countryName;
    private String countryId;
    private List<StateModel> stateList;

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

    public List<StateModel> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateModel> stateList) {
        this.stateList = stateList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((stateList == null) ? 0 : stateList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressBookModel other = (AddressBookModel) obj;
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
        if (stateList == null) {
            if (other.stateList != null)
                return false;
        } else if (!stateList.equals(other.stateList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddressBookModel [countryName=" + countryName + ", countryId=" + countryId + ", stateList=" + stateList + "]";
    }

}
