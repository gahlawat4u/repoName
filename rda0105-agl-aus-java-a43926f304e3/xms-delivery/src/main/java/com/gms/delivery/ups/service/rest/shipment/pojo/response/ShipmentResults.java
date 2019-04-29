
package com.gms.delivery.ups.service.rest.shipment.pojo.response;


public class ShipmentResults {

	 private ShipmentCharges ShipmentCharges;

	  public ShipmentCharges getShipmentCharges() { return this.ShipmentCharges; }

	  public void setShipmentCharges(ShipmentCharges ShipmentCharges) { this.ShipmentCharges = ShipmentCharges; }

	  private BillingWeight BillingWeight;

	  public BillingWeight getBillingWeight() { return this.BillingWeight; }

	  public void setBillingWeight(BillingWeight BillingWeight) { this.BillingWeight = BillingWeight; }

	  private String ShipmentIdentificationNumber;

	  public String getShipmentIdentificationNumber() { return this.ShipmentIdentificationNumber; }

	  public void setShipmentIdentificationNumber(String ShipmentIdentificationNumber) { this.ShipmentIdentificationNumber = ShipmentIdentificationNumber; }

	  private PackageResults[] PackageResults;

	  public PackageResults[] getPackageResults ()
	    {
	        return PackageResults;
	    }

	    public void setPackageResults (PackageResults[] PackageResults)
	    {
	        this.PackageResults = PackageResults;
	    }
	    
	    
}
