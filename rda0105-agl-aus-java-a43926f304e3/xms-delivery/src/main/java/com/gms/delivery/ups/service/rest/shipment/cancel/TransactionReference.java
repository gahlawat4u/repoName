package com.gms.delivery.ups.service.rest.shipment.cancel;

public class TransactionReference {

	private String CustomerContext;
	private String XpciVersion;
	public String getCustomerContext() {
		return CustomerContext;
	}
	public void setCustomerContext(String customerContext) {
		CustomerContext = customerContext;
	}
	public String getXpciVersion() {
		return XpciVersion;
	}
	public void setXpciVersion(String xpciVersion) {
		XpciVersion = xpciVersion;
	}
	
}
