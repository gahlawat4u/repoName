package com.gms.delivery.ups.service.rest.tracking;

public class Status {

	private StatusCode StatusCode;

    private StatusType StatusType;

    public StatusCode getStatusCode ()
    {
        return StatusCode;
    }

    public void setStatusCode (StatusCode StatusCode)
    {
        this.StatusCode = StatusCode;
    }

    public StatusType getStatusType ()
    {
        return StatusType;
    }

    public void setStatusType (StatusType StatusType)
    {
        this.StatusType = StatusType;
    }

    @Override
    public String toString()
    {
        return "Status [StatusCode = "+StatusCode+", StatusType = "+StatusType+"]";
    }
}
