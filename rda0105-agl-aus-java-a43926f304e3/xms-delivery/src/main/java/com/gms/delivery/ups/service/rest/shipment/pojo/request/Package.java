
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Packaging")
    @Expose
    private Packaging packaging;
    @SerializedName("Dimensions")
    @Expose
    private Dimensions dimensions;
    @SerializedName("PackageWeight")
    @Expose
    private PackageWeight packageWeight;

    
    //previous code
    @SerializedName("PackageServiceOptions")
    @Expose
    private PackageServiceOptions packageServiceOptions;


    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public PackageWeight getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(PackageWeight packageWeight) {
        this.packageWeight = packageWeight;
    }

	

	public PackageServiceOptions getPackageServiceOptions() {
		return packageServiceOptions;
	}

	public void setPackageServiceOptions(PackageServiceOptions packageServiceOptions) {
		this.packageServiceOptions = packageServiceOptions;
	}

    
    
    
}
