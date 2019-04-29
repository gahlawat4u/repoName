package com.gms.delivery.toll.service;

import com.gms.xms.model.TrackingModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from TollTrackingService
 * <p>
 * Author dattrinh Feb 19, 2016 2:44:13 PM
 */
public class TollTrackingService {
    public String execute(String airbillNumber) throws Exception {
        String urlPath = "https://online.toll.com.au/v1/trackAndTrace/searchConsignments";
        URL url = new URL(urlPath);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        // Add request header.
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        // Send post request.
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("connoteIds", airbillNumber);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonObject.toString());
        wr.flush();
        wr.close();
        // Get response.
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        // Return result.
        return response.toString();
    }

    public List<TrackingModel> getTrackingInfo(String response) throws Exception {
        List<TrackingModel> trackingModels = new ArrayList<TrackingModel>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        JsonElement errors = jsonObject.get("errors");
        if (errors.getAsJsonArray().size() == 0) {
            JsonElement connote = jsonObject.get("tatConnotes").getAsJsonArray().get(0);
            JsonArray events = connote.getAsJsonObject().get("consignmentEvents").getAsJsonArray();
            String eventDateTime, eventDate, eventType, location;
            TrackingModel trackingModel;
            for (JsonElement event : events) {
                eventDateTime = event.getAsJsonObject().get("eventDateTime").getAsString();
                eventDate = event.getAsJsonObject().get("eventDate").getAsString();
                eventType = event.getAsJsonObject().get("eventType").getAsString();
                location = event.getAsJsonObject().get("location").getAsString();
                trackingModel = new TrackingModel();
                trackingModel.setTrackDate(eventDate);
                trackingModel.setTrackTime(eventDateTime);
                trackingModel.setEventDescription(eventType);
                trackingModel.setOriginServiceArea(location);
                trackingModels.add(trackingModel);
            }
        } else {
            String errorMsg = errors.getAsString();
            throw new Exception(errorMsg);
        }
        return trackingModels;
    }
}
