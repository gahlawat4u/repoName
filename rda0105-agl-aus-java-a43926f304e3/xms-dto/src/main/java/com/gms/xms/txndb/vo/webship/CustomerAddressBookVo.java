package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CustomerAddressBookVo
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class CustomerAddressBookVo extends BaseVo {
    private static final long serialVersionUID = -2892719977341092413L;

    private Long addressId;

    private Long customerCode;

    private Long webshipId;

    private String contactName;

    private String companyName;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String postalCode;

    private Long country;

    private String phone;

    private String email;

    private String department;

    private String fax;

    private Integer defaultServiceType;

    private Integer defaultPackageType;

    private Integer defaultBillingType;

    private String accountNumber;

    private Integer isResidential;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getDefaultServiceType() {
        return defaultServiceType;
    }

    public void setDefaultServiceType(Integer defaultServiceType) {
        this.defaultServiceType = defaultServiceType;
    }

    public Integer getDefaultPackageType() {
        return defaultPackageType;
    }

    public void setDefaultPackageType(Integer defaultPackageType) {
        this.defaultPackageType = defaultPackageType;
    }

    public Integer getDefaultBillingType() {
        return defaultBillingType;
    }

    public void setDefaultBillingType(Integer defaultBillingType) {
        this.defaultBillingType = defaultBillingType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getIsResidential() {
        return isResidential;
    }

    public void setIsResidential(Integer isResidential) {
        this.isResidential = isResidential;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
        result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((defaultBillingType == null) ? 0 : defaultBillingType.hashCode());
        result = prime * result + ((defaultPackageType == null) ? 0 : defaultPackageType.hashCode());
        result = prime * result + ((defaultServiceType == null) ? 0 : defaultServiceType.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((isResidential == null) ? 0 : isResidential.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((webshipId == null) ? 0 : webshipId.hashCode());
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
        CustomerAddressBookVo other = (CustomerAddressBookVo) obj;
        if (accountNumber == null) {
            if (other.accountNumber != null)
                return false;
        } else if (!accountNumber.equals(other.accountNumber))
            return false;
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
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (defaultBillingType == null) {
            if (other.defaultBillingType != null)
                return false;
        } else if (!defaultBillingType.equals(other.defaultBillingType))
            return false;
        if (defaultPackageType == null) {
            if (other.defaultPackageType != null)
                return false;
        } else if (!defaultPackageType.equals(other.defaultPackageType))
            return false;
        if (defaultServiceType == null) {
            if (other.defaultServiceType != null)
                return false;
        } else if (!defaultServiceType.equals(other.defaultServiceType))
            return false;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
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
        if (isResidential == null) {
            if (other.isResidential != null)
                return false;
        } else if (!isResidential.equals(other.isResidential))
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
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (webshipId == null) {
            if (other.webshipId != null)
                return false;
        } else if (!webshipId.equals(other.webshipId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAddressBookVo [addressId=" + addressId + ", customerCode=" + customerCode + ", webshipId=" + webshipId + ", contactName=" + contactName + ", companyName=" + companyName + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", country=" + country + ", phone=" + phone + ", email=" + email + ", department=" + department + ", fax=" + fax + ", defaultServiceType=" + defaultServiceType
                + ", defaultPackageType=" + defaultPackageType + ", defaultBillingType=" + defaultBillingType + ", accountNumber=" + accountNumber + ", isResidential=" + isResidential + "]";
    }
}