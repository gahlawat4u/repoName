package com.gms.delivery.ups.service.rest.tracking;

public class PackageWeight {

	private UnitOfMeasurement UnitOfMeasurement;

    private String Weight;

    public UnitOfMeasurement getUnitOfMeasurement ()
    {
        return UnitOfMeasurement;
    }

    public void setUnitOfMeasurement (UnitOfMeasurement UnitOfMeasurement)
    {
        this.UnitOfMeasurement = UnitOfMeasurement;
    }

    public String getWeight ()
    {
        return Weight;
    }

    public void setWeight (String Weight)
    {
        this.Weight = Weight;
    }

    @Override
    public String toString()
    {
        return "PackageWeight [UnitOfMeasurement = "+UnitOfMeasurement+", Weight = "+Weight+"]";
    }
}
