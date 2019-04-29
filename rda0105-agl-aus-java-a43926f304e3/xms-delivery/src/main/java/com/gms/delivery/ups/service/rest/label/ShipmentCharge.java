package com.gms.delivery.ups.service.rest.label;

public class ShipmentCharge {
	private String Type;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public BillShipper getBillShipper() {
		return BillShipper;
	}
	public void setBillShipper(BillShipper billShipper) {
		BillShipper = billShipper;
	}
	private BillShipper BillShipper;

}
