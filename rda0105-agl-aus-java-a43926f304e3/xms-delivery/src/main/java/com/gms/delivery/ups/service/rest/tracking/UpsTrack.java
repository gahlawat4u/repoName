package com.gms.delivery.ups.service.rest.tracking;

public class UpsTrack {

	private TrackResponse TrackResponse;

    public TrackResponse getTrackResponse ()
    {
        return TrackResponse;
    }

    public void setTrackResponse (TrackResponse TrackResponse)
    {
        this.TrackResponse = TrackResponse;
    }

    @Override
    public String toString()
    {
        return "UpsTrack [TrackResponse = "+TrackResponse+"]";
    }

	
	
}
