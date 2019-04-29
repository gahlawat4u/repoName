package com.gms.xms.common.config.dto;

/**
 * Posted from PickupTime
 * <p>
 * Author dattrinh Jan 26, 2016 9:51:32 AM
 */
public class PickupTime {
    private String timeValue;
    private String timeDisplay;

    public PickupTime() {

    }

    public PickupTime(String timeValue, String timeDisplay) {
        this.setTimeValue(timeValue);
        this.setTimeDisplay(timeDisplay);
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }

    public String getTimeDisplay() {
        return timeDisplay;
    }

    public void setTimeDisplay(String timeDisplay) {
        this.timeDisplay = timeDisplay;
    }

    @Override
    public String toString() {
        return "PickupTime [timeValue=" + timeValue + ", timeDisplay=" + timeDisplay + "]";
    }
}
