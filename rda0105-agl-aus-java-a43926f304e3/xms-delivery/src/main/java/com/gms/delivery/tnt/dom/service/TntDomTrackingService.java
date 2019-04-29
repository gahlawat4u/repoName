package com.gms.delivery.tnt.dom.service;

import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.txndb.vo.TrackingVo;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from TntDomTrackingService
 * <p>
 * Author dattrinh Feb 2, 2016 5:29:30 PM
 */
public class TntDomTrackingService {
    public String execute(String airbillNumber) throws Exception {
        String urlParameters = "User=tntcct&Password=ccttnt&con=" + airbillNumber;
        String urlPath = "https://www.tntexpress.com.au/cct/TrackResultsCon.asp?" + urlParameters;
        URL url = new URL(urlPath);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        // Add request header.
        con.setRequestMethod("GET");
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

    public List<TrackingVo> getTrackingList(String htmlResponse) throws Exception {
        if (htmlResponse == null) {
            return null;
        }
        try {
            List<TrackingVo> trackingVos = new ArrayList<TrackingVo>();
            Document document = Jsoup.parse(htmlResponse);
            Element tableNode = document.getElementsByTag("table").get(0);
            Elements trNodeList = tableNode.getElementsByTag("tr");
            String style;
            String _class;
            String status;
            String date;
            String time;
            String depot;
            for (Element element : trNodeList) {
                style = element.attr("style");
                _class = element.attr("class");
                if ("height:18px; overflow:hidden;".equalsIgnoreCase(style) && StringUtils.isBlank(_class)) {
                    TrackingVo trackingVo = new TrackingVo();
                    status = element.child(0).child(0).text();
                    date = element.child(1).child(0).text();
                    time = element.child(2).child(0).text();
                    depot = element.child(3).child(0).text();
                    trackingVo.setTrackDate(DateUtils.convertStringToDate(date, "dd/MM/yyyy", null));
                    trackingVo.setTrackTime(time);
                    trackingVo.setEventDescription(status);
                    trackingVo.setOriginServiceArea(depot);
                    trackingVos.add(trackingVo);
                }
            }
            return trackingVos;
        } catch (Exception e) {
            throw new Exception("Consignment note number not found.");
        }
    }
}
