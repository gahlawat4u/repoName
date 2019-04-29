package com.gms.xms.model;

import org.codehaus.jackson.annotate.JsonIgnore;

public class CustomerAddressModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 6512325071068291617L;

    private String customerCode;

    private String userType;

    private String customerName;

    private String contactName;

    private String contactTitle;

    private String address1;

    private String address2;

    private String city;

    private String stateCode;

    private String country;

    private String postalCode;

    private String phone;

    private String fax;

    private String email;

    private String mobile;

    private String alternatePhone;

    private String owner;

    private String ownerPhone;

    private String ownerEmail;

    private String apContact;

    private String apPhone;

    private String apEmail;

    private String otherContact;

    private String otherPhone;

    private String otherEmail;

    private String other2Contact;

    private String other2Phone;

    private String other2Email;

    private CountryModel countryD;
    @JsonIgnore
    private String countryName;

    public CountryModel getCountryD() {
        return countryD;
    }

    public void setCountryD(CountryModel countryD) {
        this.countryD = countryD;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
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

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getApContact() {
        return apContact;
    }

    public void setApContact(String apContact) {
        this.apContact = apContact;
    }

    public String getApPhone() {
        return apPhone;
    }

    public void setApPhone(String apPhone) {
        this.apPhone = apPhone;
    }

    public String getApEmail() {
        return apEmail;
    }

    public void setApEmail(String apEmail) {
        this.apEmail = apEmail;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getOtherEmail() {
        return otherEmail;
    }

    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    public String getOther2Contact() {
        return other2Contact;
    }

    public void setOther2Contact(String other2Contact) {
        this.other2Contact = other2Contact;
    }

    public String getOther2Phone() {
        return other2Phone;
    }

    public void setOther2Phone(String other2Phone) {
        this.other2Phone = other2Phone;
    }

    public String getOther2Email() {
        return other2Email;
    }

    public void setOther2Email(String other2Email) {
        this.other2Email = other2Email;
    }

    @JsonIgnore
    public String getDisplayName() {
        return customerCode + " - " + customerName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "CustomerAddressModel [customerCode=" + customerCode + ", userType=" + userType + ", customerName=" + customerName + ", contactName=" + contactName + ", contactTitle=" + contactTitle + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", stateCode=" + stateCode + ", country=" + country + ", postalCode=" + postalCode + ", phone=" + phone + ", fax=" + fax + ", email=" + email + ", mobile=" + mobile + ", alternatePhone=" + alternatePhone + ", owner=" + owner
                + ", ownerPhone=" + ownerPhone + ", ownerEmail=" + ownerEmail + ", apContact=" + apContact + ", apPhone=" + apPhone + ", apEmail=" + apEmail + ", otherContact=" + otherContact + ", otherPhone=" + otherPhone + ", otherEmail=" + otherEmail + ", other2Contact=" + other2Contact + ", other2Phone=" + other2Phone + ", other2Email=" + other2Email + ", countryD=" + countryD + ", countryName=" + countryName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
        result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
        result = prime * result + ((alternatePhone == null) ? 0 : alternatePhone.hashCode());
        result = prime * result + ((apContact == null) ? 0 : apContact.hashCode());
        result = prime * result + ((apEmail == null) ? 0 : apEmail.hashCode());
        result = prime * result + ((apPhone == null) ? 0 : apPhone.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((contactTitle == null) ? 0 : contactTitle.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((countryD == null) ? 0 : countryD.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((other2Contact == null) ? 0 : other2Contact.hashCode());
        result = prime * result + ((other2Email == null) ? 0 : other2Email.hashCode());
        result = prime * result + ((other2Phone == null) ? 0 : other2Phone.hashCode());
        result = prime * result + ((otherContact == null) ? 0 : otherContact.hashCode());
        result = prime * result + ((otherEmail == null) ? 0 : otherEmail.hashCode());
        result = prime * result + ((otherPhone == null) ? 0 : otherPhone.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((ownerEmail == null) ? 0 : ownerEmail.hashCode());
        result = prime * result + ((ownerPhone == null) ? 0 : ownerPhone.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
        CustomerAddressModel other = (CustomerAddressModel) obj;
        if (address1 == null) {
            if (other.address1 != null)
                return false;
        } else if (!address1.equals(other.address1))
            return false;
        if (address2 == null) {
            if (other.address2 != null)
                return false;
        } else if (!address2.equals(other.address2))
            return false;
        if (alternatePhone == null) {
            if (other.alternatePhone != null)
                return false;
        } else if (!alternatePhone.equals(other.alternatePhone))
            return false;
        if (apContact == null) {
            if (other.apContact != null)
                return false;
        } else if (!apContact.equals(other.apContact))
            return false;
        if (apEmail == null) {
            if (other.apEmail != null)
                return false;
        } else if (!apEmail.equals(other.apEmail))
            return false;
        if (apPhone == null) {
            if (other.apPhone != null)
                return false;
        } else if (!apPhone.equals(other.apPhone))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (contactTitle == null) {
            if (other.contactTitle != null)
                return false;
        } else if (!contactTitle.equals(other.contactTitle))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (countryD == null) {
            if (other.countryD != null)
                return false;
        } else if (!countryD.equals(other.countryD))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fax == null) {
            if (other.fax != null)
                return false;
        } else if (!fax.equals(other.fax))
            return false;
        if (mobile == null) {
            if (other.mobile != null)
                return false;
        } else if (!mobile.equals(other.mobile))
            return false;
        if (other2Contact == null) {
            if (other.other2Contact != null)
                return false;
        } else if (!other2Contact.equals(other.other2Contact))
            return false;
        if (other2Email == null) {
            if (other.other2Email != null)
                return false;
        } else if (!other2Email.equals(other.other2Email))
            return false;
        if (other2Phone == null) {
            if (other.other2Phone != null)
                return false;
        } else if (!other2Phone.equals(other.other2Phone))
            return false;
        if (otherContact == null) {
            if (other.otherContact != null)
                return false;
        } else if (!otherContact.equals(other.otherContact))
            return false;
        if (otherEmail == null) {
            if (other.otherEmail != null)
                return false;
        } else if (!otherEmail.equals(other.otherEmail))
            return false;
        if (otherPhone == null) {
            if (other.otherPhone != null)
                return false;
        } else if (!otherPhone.equals(other.otherPhone))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (ownerEmail == null) {
            if (other.ownerEmail != null)
                return false;
        } else if (!ownerEmail.equals(other.ownerEmail))
            return false;
        if (ownerPhone == null) {
            if (other.ownerPhone != null)
                return false;
        } else if (!ownerPhone.equals(other.ownerPhone))
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
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        if (userType == null) {
            if (other.userType != null)
                return false;
        } else if (!userType.equals(other.userType))
            return false;
        return true;
    }
}
