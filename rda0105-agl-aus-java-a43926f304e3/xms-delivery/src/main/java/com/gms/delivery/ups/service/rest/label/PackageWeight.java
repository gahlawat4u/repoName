package com.gms.delivery.ups.service.rest.label;

public class PackageWeight {
private UnitOfMeasurement UnitOfMeasurement;
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
private String Weight ;

}
