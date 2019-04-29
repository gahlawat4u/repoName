package com.gms.delivery.ups.service.rest.shipment.cancel;

public class UpsCancelTrackResponse {

	private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "UpsCancelTrackResponse [response = "+response+"]";
    }
}
