package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class ShippingSHRoot {
	
	  private Response Response;

	    private Status Status;
	    
	    private ShipmentResults ShipmentResults;

		  public ShipmentResults getShipmentResults() { return this.ShipmentResults; }

		  public void setShipmentResults(ShipmentResults ShipmentResults) { this.ShipmentResults = ShipmentResults; }

	    public void setResponse(Response Response){
	        this.Response = Response;
	    }
	    public Response getResponse(){
	        return this.Response;
	    }
	    public void setStatus(Status Status){
	        this.Status = Status;
	    }
	    public Status getStatus(){
	        return this.Status;
	    }

	

}
