
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Packaging {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("PackageServiceOptions")
    @Expose
    private PackageServiceOptions packageServiceOptions;

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public PackageServiceOptions getPackageServiceOptions() {
		return packageServiceOptions;
	}

	public void setPackageServiceOptions(PackageServiceOptions packageServiceOptions) {
		this.packageServiceOptions = packageServiceOptions;
	}

    
}
