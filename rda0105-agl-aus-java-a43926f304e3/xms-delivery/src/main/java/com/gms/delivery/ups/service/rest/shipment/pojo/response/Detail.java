package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class Detail
{
    private Errors errors;

   
    public Errors getErrors() {
		return errors;
	}


	public void setErrors(Errors errors) {
		this.errors = errors;
	}


	@Override
    public String toString()
    {
        return "ClassPojo [Errors = "+errors+"]";
    }
}
			
	