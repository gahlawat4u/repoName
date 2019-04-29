package com.gms.delivery.ups.service.rest.tracking;

public class Response {

	private String ResponseStatusCode;

    private String ResponseStatusDescription;

    private TransactionReference TransactionReference;

    public String getResponseStatusCode ()
    {
        return ResponseStatusCode;
    }

    public void setResponseStatusCode (String ResponseStatusCode)
    {
        this.ResponseStatusCode = ResponseStatusCode;
    }

    public String getResponseStatusDescription ()
    {
        return ResponseStatusDescription;
    }

    public void setResponseStatusDescription (String ResponseStatusDescription)
    {
        this.ResponseStatusDescription = ResponseStatusDescription;
    }

    public TransactionReference getTransactionReference ()
    {
        return TransactionReference;
    }

    public void setTransactionReference (TransactionReference TransactionReference)
    {
        this.TransactionReference = TransactionReference;
    }

    @Override
    public String toString()
    {
        return "Response [ResponseStatusCode = "+ResponseStatusCode+", ResponseStatusDescription = "+ResponseStatusDescription+", TransactionReference = "+TransactionReference+"]";
    }
}
