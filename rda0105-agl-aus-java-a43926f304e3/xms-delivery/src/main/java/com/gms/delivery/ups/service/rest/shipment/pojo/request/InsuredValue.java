package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsuredValue {

	    @SerializedName("CurrencyCode")
	    @Expose
	    private String currencyCode;
	    
	    @SerializedName("MonetaryValue")
	    @Expose
	    private String monetaryValue;
	    
	    @SerializedName("Type")
	    @Expose
	    private Type type;

		public String getCurrencyCode() {
			return currencyCode;
		}

		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}

		public String getMonetaryValue() {
			return monetaryValue;
		}

		public void setMonetaryValue(String monetaryValue) {
			this.monetaryValue = monetaryValue;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}
	    
	    
}
