package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternationalForms {

	
	
	    @SerializedName("TermsOfShipment")
	    @Expose
	    private String termsOfShipment;
	    
	    @SerializedName("FormType")
	    @Expose
	    private String formType ;

	    
	    
		public String getFormType() {
			return formType;
		}

		public void setFormType(String formType) {
			this.formType = formType;
		}

		public String getTermsOfShipment() {
			return termsOfShipment;
		}

		public void setTermsOfShipment(String termsOfShipment) {
			this.termsOfShipment = termsOfShipment;
		}
	    
	    
}
