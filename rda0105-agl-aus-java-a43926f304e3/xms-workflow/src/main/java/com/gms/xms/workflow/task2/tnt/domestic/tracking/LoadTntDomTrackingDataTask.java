package com.gms.xms.workflow.task2.tnt.domestic.tracking;

import com.gms.delivery.tnt.dom.service.TntDomTrackingService;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Posted from LoadTntDomTrackingDataTask
 * <p>
 * Author TANDT
 */
public class LoadTntDomTrackingDataTask implements Task2 {

    private static final Log log = LogFactory.getLog(LoadTntDomTrackingDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get tracking response.
            String trackResponse = context.get(Attributes.TRACKING_RESPONSE);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Create HistoryDetailInfo.
            HistoryDetailInfoVo detailInfoVo = new HistoryDetailInfoVo();
            detailInfoVo = prepareHistoryDetail(String.valueOf(shipmentId));
            // Create TrackingVo.
            TntDomTrackingService service = new TntDomTrackingService();
            List<TrackingVo> trackingVos = new ArrayList<TrackingVo>();
            trackingVos = service.getTrackingList(trackResponse);

            //XTD-68: get Signed For By from tntexpress site request
            String urlParameters = "User=tntcct&Password=ccttnt&con=" + detailInfoVo.getAirbillNumber();
            String urlPath = "https://www.tntexpress.com.au/cct/TrackResultsCon.asp?" + urlParameters;
            String response = getResultFromUrl(urlPath, true, "");

            String htmlRegex = ".*?location='(.*?)'.*";
            Pattern pattern = Pattern.compile(htmlRegex);
            Matcher matcher = pattern.matcher(response);
            if (matcher.matches()) {
                String TNT_POD_url = matcher.group(1);
                //hard coded cookies from PHP code
                if (TNT_POD_url.contains("SigImg.asp")) {
                    String cookies = "CCT=strUserName=TNTCCT&PWD=ccttnt&strGroup=; __utma=203472725.465232918.1317965966.1317965966.1318317311.2; __utmz=203472725.1317965966.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASPSESSIONIDAQQBQCCR=NICMDJIDBDOCDMEBFANFGAGD";
                    String signImgResponse = getResultFromUrl(TNT_POD_url, TNT_POD_url.contains("https"), cookies);
                    String signByRegex = ".*Signed By: </b>(\\S+)</font>.*";
                    Pattern signByPattern = Pattern.compile(signByRegex);
                    Matcher signByMatcher = signByPattern.matcher(signImgResponse);
                    if (signByMatcher.matches()) {
                        detailInfoVo.setrContactName(signByMatcher.group(1));
                    }
                }
            }

            if (trackingVos != null) {
                context.put(Attributes.TRACKING_LIST_RESULT, trackingVos);
                context.put(Attributes.HISTORY_DETAIL_INFO_VO, detailInfoVo);
            } else {
                String errorMsg = "TNT Tracking Connection Error";
                errorMsg += "\n" + "There was an error while trying your shipment.";
                throw new Exception(errorMsg);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private String getResultFromUrl(String urlPath, boolean isHttps, String cookie) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection con;
        if (isHttps) {
            con = (HttpsURLConnection) url.openConnection();
        } else {
            con = (HttpURLConnection) url.openConnection();
        }
        con.setRequestProperty("Cookie", cookie);
        // Add request header.
        con.setRequestMethod("GET");
        // Get response.
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        return response.toString();
    }

    protected HistoryDetailInfoVo prepareHistoryDetail(String shipmentId) throws Exception {
        HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
        detailFilterModelN.setShipmentId(shipmentId);
        detailFilterModelN.setLbToKg("0.45359237");
        detailFilterModelN.setInToCm("2.54");
        detailFilterModelN.setWeightValue("5000");
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
        detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());

        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        HistoryDetailInfoVo detailInfoVo = detailService.selectHistoryDetailInfo(filter);
        return detailInfoVo;
    }

}
