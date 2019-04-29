package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CountryModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from AddressModel
 * <p>
 * Author TanDT Date Mar 27, 2015
 */
public class AddressModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -3038970797894767590L;

    private String addressId;

    private String companyName;

    private String phone;

    private String contactName;

    private String email;

    private String country;

    private String address;

    private String address2;

    private String city;

    private String postalCode;

    private String state;

    private String locationDesc;

    private CountryModel countryDetail;

    @JsonIgnore
    private String residential;

    @JsonIgnore
    private String saveToAddressBook;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public CountryModel getCountryDetail() {
        return countryDetail;
    }

    public void setCountryDetail(CountryModel countryDetail) {
        this.countryDetail = countryDetail;
    }

    public String getResidential() {
        return residential;
    }

    public void setResidential(String residential) {
        this.residential = residential;
    }

    public String getSaveToAddressBook() {
        return saveToAddressBook;
    }

    public void setSaveToAddressBook(String saveToAddressBook) {
        this.saveToAddressBook = saveToAddressBook;
    }

    @Override
    public String toString() {
        return "AddressModel [addressId=" + addressId + ", companyName=" + companyName + ", phone=" + phone + ", contactName=" + contactName + ", email=" + email + ", country=" + country + ", address=" + address + ", address2=" + address2 + ", city=" + city + ", postalCode=" + postalCode + ", state=" + state + ", locationDesc=" + locationDesc + ", countryDetail=" + countryDetail + ", residential=" + residential + ", saveToAddressBook=" + saveToAddressBook + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((countryDetail == null) ? 0 : countryDetail.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((locationDesc == null) ? 0 : locationDesc.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((residential == null) ? 0 : residential.hashCode());
        result = prime * result + ((saveToAddressBook == null) ? 0 : saveToAddressBook.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        AddressModel other = (AddressModel) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (address2 == null) {
            if (other.address2 != null)
                return false;
        } else if (!address2.equals(other.address2))
            return false;
        if (addressId == null) {
            if (other.addressId != null)
                return false;
        } else if (!addressId.equals(other.addressId))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (countryDetail == null) {
            if (other.countryDetail != null)
                return false;
        } else if (!countryDetail.equals(other.countryDetail))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (locationDesc == null) {
            if (other.locationDesc != null)
                return false;
        } else if (!locationDesc.equals(other.locationDesc))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (residential == null) {
            if (other.residential != null)
                return false;
        } else if (!residential.equals(other.residential))
            return false;
        if (saveToAddressBook == null) {
            if (other.saveToAddressBook != null)
                return false;
        } else if (!saveToAddressBook.equals(other.saveToAddressBook))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }
}
