package com.gms.xms.model.webship;

/**
 * Posted from AddressDetailModel
 * <p>
 * Author HungNT Date Jul 24, 2015
 */
public class AddressDetailModel extends AddressModel {
    private static final long serialVersionUID = -1982380878709145763L;

    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
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
        AddressDetailModel other = (AddressDetailModel) obj;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddressDetailModel [countryName=" + countryName + "]";
    }

}
