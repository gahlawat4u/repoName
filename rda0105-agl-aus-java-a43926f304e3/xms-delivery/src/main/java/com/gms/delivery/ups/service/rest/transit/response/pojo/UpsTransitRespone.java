package com.gms.delivery.ups.service.rest.transit.response.pojo;

import java.util.Arrays;

import com.gms.delivery.ups.service.rest.tracking.response.pojo.ErrorResponse;

public class UpsTransitRespone {

	private Services[] SERVICES;

	
	public Services[] getSERVICES ()
    {
        return SERVICES;
    }

    public void setSERVICES (Services[] SERVICES)
    {
        this.SERVICES = SERVICES;
    }

    @Override
	public String toString() {
		return "UpsTransitRespone [SERVICES=" + Arrays.toString(SERVICES)  + "]";
	}
}
