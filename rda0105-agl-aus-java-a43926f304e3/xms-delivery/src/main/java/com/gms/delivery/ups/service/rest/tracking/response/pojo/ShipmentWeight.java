package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class ShipmentWeight {
	
	private UnitOfMeasurement UnitOfMeasurement;
	private String Weight;
	
	public UnitOfMeasurement getUnitOfMeasurement() {
		return UnitOfMeasurement;
	}
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		UnitOfMeasurement = unitOfMeasurement;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}

}
