package com.gms.delivery.ups.service.rest.shipment.cancel;

public class Response {

	 private String condition_msg;

	    private String err_header_msg;

	    public String getCondition_msg ()
	    {
	        return condition_msg;
	    }

	    public void setCondition_msg (String condition_msg)
	    {
	        this.condition_msg = condition_msg;
	    }

	    public String getErr_header_msg ()
	    {
	        return err_header_msg;
	    }

	    public void setErr_header_msg (String err_header_msg)
	    {
	        this.err_header_msg = err_header_msg;
	    }

	    @Override
	    public String toString()
	    {
	        return "Response [condition_msg = "+condition_msg+", err_header_msg = "+err_header_msg+"]";
	    }
}
