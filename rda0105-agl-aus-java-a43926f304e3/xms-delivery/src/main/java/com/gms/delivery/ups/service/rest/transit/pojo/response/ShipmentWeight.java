package com.gms.delivery.ups.service.rest.transit.pojo.response;

public class ShipmentWeight {
	private UnitOfMeasurement UnitOfMeasurement;
	private String Weight;

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return UnitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		UnitOfMeasurement = unitOfMeasurement;
	}
	

}
