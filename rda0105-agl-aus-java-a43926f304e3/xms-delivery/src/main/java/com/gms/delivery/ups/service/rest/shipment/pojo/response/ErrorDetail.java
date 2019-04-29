package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class ErrorDetail
{
    private String severity;

    private PrimaryErrorCode primaryerrorcode;

	public String getSeverity() {
		return severity;
	}



	public void setSeverity(String severity) {
		this.severity = severity;
	}



	public PrimaryErrorCode getPrimaryerrorcode() {
		return primaryerrorcode;
	}



	public void setPrimaryerrorcode(PrimaryErrorCode primaryerrorcode) {
		this.primaryerrorcode = primaryerrorcode;
	}



	@Override
    public String toString()
    {
        return "ClassPojo [Severity = "+severity+", PrimaryErrorCode = "+primaryerrorcode+"]";
    }
}
	
