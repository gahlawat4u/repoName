package com.gms.xms.model.tracking.startrack;

/**
 * Posted from Jul 19, 2016 11:50:56 AM
 * <p>
 * Author dattrinh
 */
public class StarTrackTrackingEventModel {
    private String dateTime;
    private String location;
    private String description;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StarTrackTrackingEventModel [dateTime=" + dateTime + ", location=" + location + ", description=" + description + "]";
    }
}
