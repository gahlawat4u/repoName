package com.gms.delivery.ups.service.rest.tracking;

public class Shipment {

	private String ShipmentIdentificationNumber;

    private Service Service;

    private ReferenceNumber[] ReferenceNumber;

    private Shipper Shipper;

    private Package Package;

    private DeliveryDateUnavailable DeliveryDateUnavailable;

    private ShipmentWeight ShipmentWeight;

    private String PickupDate;

    private ShipTo ShipTo;

    public String getShipmentIdentificationNumber ()
    {
        return ShipmentIdentificationNumber;
    }

    public void setShipmentIdentificationNumber (String ShipmentIdentificationNumber)
    {
        this.ShipmentIdentificationNumber = ShipmentIdentificationNumber;
    }

    public Service getService ()
    {
        return Service;
    }

    public void setService (Service Service)
    {
        this.Service = Service;
    }

    public ReferenceNumber[] getReferenceNumber ()
    {
        return ReferenceNumber;
    }

    public void setReferenceNumber (ReferenceNumber[] ReferenceNumber)
    {
        this.ReferenceNumber = ReferenceNumber;
    }

    public Shipper getShipper ()
    {
        return Shipper;
    }

    public void setShipper (Shipper Shipper)
    {
        this.Shipper = Shipper;
    }

    public Package getPackage ()
    {
        return Package;
    }

    public void setPackage (Package Package)
    {
        this.Package = Package;
    }

    public DeliveryDateUnavailable getDeliveryDateUnavailable ()
    {
        return DeliveryDateUnavailable;
    }

    public void setDeliveryDateUnavailable (DeliveryDateUnavailable DeliveryDateUnavailable)
    {
        this.DeliveryDateUnavailable = DeliveryDateUnavailable;
    }

    public ShipmentWeight getShipmentWeight ()
    {
        return ShipmentWeight;
    }

    public void setShipmentWeight (ShipmentWeight ShipmentWeight)
    {
        this.ShipmentWeight = ShipmentWeight;
    }

    public String getPickupDate ()
    {
        return PickupDate;
    }

    public void setPickupDate (String PickupDate)
    {
        this.PickupDate = PickupDate;
    }

    public ShipTo getShipTo ()
    {
        return ShipTo;
    }

    public void setShipTo (ShipTo ShipTo)
    {
        this.ShipTo = ShipTo;
    }

    @Override
    public String toString()
    {
        return "Shipment [ShipmentIdentificationNumber = "+ShipmentIdentificationNumber+", Service = "+Service+", ReferenceNumber = "+ReferenceNumber+", Shipper = "+Shipper+", Package = "+Package+", DeliveryDateUnavailable = "+DeliveryDateUnavailable+", ShipmentWeight = "+ShipmentWeight+", PickupDate = "+PickupDate+", ShipTo = "+ShipTo+"]";
    }
}
