package com.gms.xms.txndb.vo.webship.addressbook;

import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;

import java.util.List;

/**
 * Posted from AddressBookVo
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class AddressBookVo extends CustomerAddressBookVo {

    private static final long serialVersionUID = -5730697682697397773L;
    private String countryName;
    private Long countryId;
    private List<StateVo> stateList;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<StateVo> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateVo> stateList) {
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
        AddressBookVo other = (AddressBookVo) obj;
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
        return "AddressBookVo [countryName=" + countryName + ", countryId=" + countryId + ", stateList=" + stateList + "]";
    }

}
