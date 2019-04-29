
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shipment {

    @SerializedName("Description")
    @Expose
    private String description;
    
    @SerializedName("Shipper")
    @Expose
    private Shipper shipper;
    
    @SerializedName("ShipTo")
    @Expose
    private ShipTo shipTo;
    
    @SerializedName("ShipFrom")
    @Expose
    private ShipFrom shipFrom;
    
    @SerializedName("PaymentInformation")
    @Expose
    private PaymentInformation paymentInformation;
    
    @SerializedName("Service")
    @Expose
    private Service service;
    
    @SerializedName("ReturnService")
    @Expose
    private ReturnService returnService;
    
   @SerializedName("Package")
    @Expose
    private List<Package> _package;
   
   @SerializedName("Package")
   @Expose
   private Package packageWithoutList;
  
    
    @SerializedName("ShipmentServiceOptions")
    @Expose
    private ShipmentServiceOptions shipmentServiceOptions;

    @SerializedName("PackageServiceOptions")
    @Expose
    private PackageServiceOptions packageServiceOptions;

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public ShipTo getShipTo() {
        return shipTo;
    }

    public void setShipTo(ShipTo shipTo) {
        this.shipTo = shipTo;
    }

    public ShipFrom getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(ShipFrom shipFrom) {
        this.shipFrom = shipFrom;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    /*public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }*/

    
    
	public ShipmentServiceOptions getShipmentServiceOptions() {
		return shipmentServiceOptions;
	}

	

	
	
	/*public Package get_package() {
		return _package;
	}

	public void set_package(Package _package) {
		this._package = _package;
	}*/

	public List<Package> get_package() {
		return _package;
	}

	public void set_package(List<Package> _package) {
		this._package = _package;
	}

	public void setShipmentServiceOptions(ShipmentServiceOptions shipmentServiceOptions) {
		this.shipmentServiceOptions = shipmentServiceOptions;
	}

	public PackageServiceOptions getPackageServiceOptions() {
		return packageServiceOptions;
	}

	public void setPackageServiceOptions(PackageServiceOptions packageServiceOptions) {
		this.packageServiceOptions = packageServiceOptions;
	}

	public Package getPackageWithoutList() {
		return packageWithoutList;
	}

	public void setPackageWithoutList(Package packageWithoutList) {
		this.packageWithoutList = packageWithoutList;
	}

	public ReturnService getReturnService() {
		return returnService;
	}

	public void setReturnService(ReturnService returnService) {
		this.returnService = returnService;
	}
	

}
