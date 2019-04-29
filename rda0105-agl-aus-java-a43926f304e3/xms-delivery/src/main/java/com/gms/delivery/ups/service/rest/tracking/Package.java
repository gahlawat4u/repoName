package com.gms.delivery.ups.service.rest.tracking;

public class Package {

	private Activity[] Activity;

    private ReferenceNumber ReferenceNumber;

    private String TrackingNumber;

    private PackageWeight PackageWeight;

    public Activity[] getActivity ()
    {
        return Activity;
    }

    public void setActivity (Activity[] Activity)
    {
        this.Activity = Activity;
    }

    public ReferenceNumber getReferenceNumber ()
    {
        return ReferenceNumber;
    }

    public void setReferenceNumber (ReferenceNumber ReferenceNumber)
    {
        this.ReferenceNumber = ReferenceNumber;
    }

    public String getTrackingNumber ()
    {
        return TrackingNumber;
    }

    public void setTrackingNumber (String TrackingNumber)
    {
        this.TrackingNumber = TrackingNumber;
    }

    public PackageWeight getPackageWeight ()
    {
        return PackageWeight;
    }

    public void setPackageWeight (PackageWeight PackageWeight)
    {
        this.PackageWeight = PackageWeight;
    }

    @Override
    public String toString()
    {
        return "Package [Activity = "+Activity+", ReferenceNumber = "+ReferenceNumber+", TrackingNumber = "+TrackingNumber+", PackageWeight = "+PackageWeight+"]";
    }
}
