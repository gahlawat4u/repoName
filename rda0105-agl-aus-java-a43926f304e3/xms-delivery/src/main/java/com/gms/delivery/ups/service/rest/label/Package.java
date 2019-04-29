package com.gms.delivery.ups.service.rest.label;

public class Package {
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Packaging getPackaging() {
		return Packaging;
	}
	public void setPackaging(Packaging packaging) {
		Packaging = packaging;
	}
	public Dimensions getDimensions() {
		return Dimensions;
	}
	public void setDimensions(Dimensions dimensions) {
		Dimensions = dimensions;
	}
	public PackageWeight getPackageWeight() {
		return PackageWeight;
	}
	public void setPackageWeight(PackageWeight packageWeight) {
		PackageWeight = packageWeight;
	}
	private String Description;
	private Packaging Packaging;
	private Dimensions Dimensions;
	private PackageWeight PackageWeight;
}
