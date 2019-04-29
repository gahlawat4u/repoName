package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionReference {
	
	@SerializedName("CustomerContext")
    @Expose
private String CustomerContext;

public String getCustomerContext() {
	return CustomerContext;
}

public void setCustomerContext(String customerContext) {
	CustomerContext = customerContext;
}

}
