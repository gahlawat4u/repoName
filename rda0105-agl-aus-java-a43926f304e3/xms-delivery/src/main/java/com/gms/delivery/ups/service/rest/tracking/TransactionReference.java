package com.gms.delivery.ups.service.rest.tracking;

public class TransactionReference {

	private String CustomerContext;

    public String getCustomerContext ()
    {
        return CustomerContext;
    }

    public void setCustomerContext (String CustomerContext)
    {
        this.CustomerContext = CustomerContext;
    }

    @Override
    public String toString()
    {
        return "TransactionReference [CustomerContext = "+CustomerContext+"]";
    }
}
