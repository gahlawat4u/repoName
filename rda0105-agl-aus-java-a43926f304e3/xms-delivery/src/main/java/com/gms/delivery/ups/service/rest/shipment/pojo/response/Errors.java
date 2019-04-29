package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class Errors
{
    private ErrorDetail errordetail;


	public ErrorDetail getErrordetail() {
		return errordetail;
	}

	public void setErrordetail(ErrorDetail errordetail) {
		this.errordetail = errordetail;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [ErrorDetail = "+errordetail+"]";
    }
}
