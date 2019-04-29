package com.gms.delivery.ups.service.rest.tracking;

public class TrackResponse {

	private Response Response;

    private Shipment Shipment;

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    public Shipment getShipment ()
    {
        return Shipment;
    }

    public void setShipment (Shipment Shipment)
    {
        this.Shipment = Shipment;
    }

    @Override
    public String toString()
    {
        return "TrackResponse [Response = "+Response+", Shipment = "+Shipment+"]";
    }
}
