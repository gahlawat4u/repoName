package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

public class WebshipSettingModel extends BaseModel {
    private static final long serialVersionUID = -8600416385400026168L;

    private String dimensionId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String serviceId;
    private String companyName;
    private String contactName;
    private String addressSearchType;
    private String addressId;
    private String fromAddressId;
    private String toAddressId;

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getFromAddressId() {
        return fromAddressId;
    }

    public void setFromAddressId(String fromAddressId) {
        this.fromAddressId = fromAddressId;
    }

    public String getToAddressId() {
        return toAddressId;
    }

    public void setToAddressId(String toAddressId) {
        this.toAddressId = toAddressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressSearchType() {
        return addressSearchType;
    }

    public void setAddressSearchType(String addressSearchType) {
        this.addressSearchType = addressSearchType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((dimensionId == null) ? 0 : dimensionId.hashCode());
        result = prime * result + ((fromAddressId == null) ? 0 : fromAddressId.hashCode());
        result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
        result = prime * result + ((oldPassword == null) ? 0 : oldPassword.hashCode());
        result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
        result = prime * result + ((toAddressId == null) ? 0 : toAddressId.hashCode());
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
        WebshipSettingModel other = (WebshipSettingModel) obj;
        if (addressId == null) {
            if (other.addressId != null)
                return false;
        } else if (!addressId.equals(other.addressId))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (confirmPassword == null) {
            if (other.confirmPassword != null)
                return false;
        } else if (!confirmPassword.equals(other.confirmPassword))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (dimensionId == null) {
            if (other.dimensionId != null)
                return false;
        } else if (!dimensionId.equals(other.dimensionId))
            return false;
        if (fromAddressId == null) {
            if (other.fromAddressId != null)
                return false;
        } else if (!fromAddressId.equals(other.fromAddressId))
            return false;
        if (newPassword == null) {
            if (other.newPassword != null)
                return false;
        } else if (!newPassword.equals(other.newPassword))
            return false;
        if (oldPassword == null) {
            if (other.oldPassword != null)
                return false;
        } else if (!oldPassword.equals(other.oldPassword))
            return false;
        if (serviceId == null) {
            if (other.serviceId != null)
                return false;
        } else if (!serviceId.equals(other.serviceId))
            return false;
        if (toAddressId == null) {
            if (other.toAddressId != null)
                return false;
        } else if (!toAddressId.equals(other.toAddressId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WebshipSettingModel [dimensionId=" + dimensionId + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + ", serviceId=" + serviceId + ", companyName=" + companyName + ", contactName=" + contactName + ", addressId=" + addressId + ", fromAddressId=" + fromAddressId + ", toAddressId=" + toAddressId + "]";
    }
}
