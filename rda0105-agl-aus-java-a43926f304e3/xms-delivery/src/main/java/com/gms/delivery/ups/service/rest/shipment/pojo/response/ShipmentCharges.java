
package com.gms.delivery.ups.service.rest.shipment.pojo.response;


public class ShipmentCharges {

	private TransportationCharges TransportationCharges;

	  public TransportationCharges getTransportationCharges() { return this.TransportationCharges; }

	  public void setTransportationCharges(TransportationCharges TransportationCharges) { this.TransportationCharges = TransportationCharges; }

	  private ServiceOptionsCharges ServiceOptionsCharges;

	  public ServiceOptionsCharges getServiceOptionsCharges() { return this.ServiceOptionsCharges; }

	  public void setServiceOptionsCharges(ServiceOptionsCharges ServiceOptionsCharges) { this.ServiceOptionsCharges = ServiceOptionsCharges; }

	  private TotalCharges TotalCharges;

	  public TotalCharges getTotalCharges() { return this.TotalCharges; }

	  public void setTotalCharges(TotalCharges TotalCharges) { this.TotalCharges = TotalCharges; }


}
