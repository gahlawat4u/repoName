package com.gms.delivery.ups.service.rest.shipment.pojo.response;


public class PackageResults {

	private String TrackingNumber;

	  public String getTrackingNumber() { return this.TrackingNumber; }

	  public void setTrackingNumber(String TrackingNumber) { this.TrackingNumber = TrackingNumber; }

	  private ServiceOptionsCharges ServiceOptionsCharges;

	  public ServiceOptionsCharges getServiceOptionsCharges() { return this.ServiceOptionsCharges; }

	  public void setServiceOptionsCharges(ServiceOptionsCharges ServiceOptionsCharges) { this.ServiceOptionsCharges = ServiceOptionsCharges; }

	  private ShippingLabel ShippingLabel;

	  public ShippingLabel getShippingLabel() { return this.ShippingLabel; }

	  public void setShippingLabel(ShippingLabel ShippingLabel) { this.ShippingLabel = ShippingLabel; }

}
