package com.gms.delivery.ups.service.rest.tracking;

public class Shipper {

	 private Address Address;

	    private String ShipperNumber;

	    public Address getAddress ()
	    {
	        return Address;
	    }

	    public void setAddress (Address Address)
	    {
	        this.Address = Address;
	    }

	    public String getShipperNumber ()
	    {
	        return ShipperNumber;
	    }

	    public void setShipperNumber (String ShipperNumber)
	    {
	        this.ShipperNumber = ShipperNumber;
	    }

	    @Override
	    public String toString()
	    {
	        return "Shipper [Address = "+Address+", ShipperNumber = "+ShipperNumber+"]";
	    }
}
