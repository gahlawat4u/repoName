package com.gms.delivery.ups.service.rest.tracking;

public class UpsTrackingResponse {

	
	 private String DeliveryDate;

	    private String StatusType;

	    private String DeliveryTime;

	    private String ResponseStatusDescription;

	    private String SignedForByName;

	    private String code_status;

	    private Loop_data[] loop_data;

	    public String getDeliveryDate ()
	    {
	        return DeliveryDate;
	    }

	    public void setDeliveryDate (String DeliveryDate)
	    {
	        this.DeliveryDate = DeliveryDate;
	    }

	    public String getStatusType ()
	    {
	        return StatusType;
	    }

	    public void setStatusType (String StatusType)
	    {
	        this.StatusType = StatusType;
	    }

	    public String getDeliveryTime ()
	    {
	        return DeliveryTime;
	    }

	    public void setDeliveryTime (String DeliveryTime)
	    {
	        this.DeliveryTime = DeliveryTime;
	    }

	    public String getResponseStatusDescription ()
	    {
	        return ResponseStatusDescription;
	    }

	    public void setResponseStatusDescription (String ResponseStatusDescription)
	    {
	        this.ResponseStatusDescription = ResponseStatusDescription;
	    }

	    public String getSignedForByName ()
	    {
	        return SignedForByName;
	    }

	    public void setSignedForByName (String SignedForByName)
	    {
	        this.SignedForByName = SignedForByName;
	    }

	    public String getCode_status ()
	    {
	        return code_status;
	    }

	    public void setCode_status (String code_status)
	    {
	        this.code_status = code_status;
	    }

	    public Loop_data[] getLoop_data ()
	    {
	        return loop_data;
	    }

	    public void setLoop_data (Loop_data[] loop_data)
	    {
	        this.loop_data = loop_data;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [DeliveryDate = "+DeliveryDate+", StatusType = "+StatusType+", DeliveryTime = "+DeliveryTime+", ResponseStatusDescription = "+ResponseStatusDescription+", SignedForByName = "+SignedForByName+", code_status = "+code_status+", loop_data = "+loop_data+"]";
	    }
}
