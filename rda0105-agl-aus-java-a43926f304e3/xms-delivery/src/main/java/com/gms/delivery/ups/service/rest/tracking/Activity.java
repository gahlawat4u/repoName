package com.gms.delivery.ups.service.rest.tracking;

public class Activity {

	private Status Status;

    private String Time;

    private String Date;

    private ActivityLocation ActivityLocation;

    public Status getStatus ()
    {
        return Status;
    }

    public void setStatus (Status Status)
    {
        this.Status = Status;
    }

    public String getTime ()
    {
        return Time;
    }

    public void setTime (String Time)
    {
        this.Time = Time;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public ActivityLocation getActivityLocation ()
    {
        return ActivityLocation;
    }

    public void setActivityLocation (ActivityLocation ActivityLocation)
    {
        this.ActivityLocation = ActivityLocation;
    }

    @Override
    public String toString()
    {
        return "Activity [Status = "+Status+", Time = "+Time+", Date = "+Date+", ActivityLocation = "+ActivityLocation+"]";
    }
}
