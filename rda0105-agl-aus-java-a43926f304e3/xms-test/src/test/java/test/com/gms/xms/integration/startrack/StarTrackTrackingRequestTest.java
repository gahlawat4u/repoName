package test.com.gms.xms.integration.startrack;

import com.gms.delivery.startrack.xmlpi.tracking.request.StartrackTrackingRequest;

import java.util.Date;

public class StarTrackTrackingRequestTest {
    public static void main(String[] args) throws Exception {
        StartrackTrackingRequest request = new StartrackTrackingRequest();
        request.setSource("TEAM");
        request.setAccountNo("10143119");
        request.setUserAccessKey("9283571805854");
        request.setConsignmentId("2YAZ50000018");
        request.setUserName("Aglaustralia60");
        request.setPassword("Startrack21");
        request.setNonce("FREYAlA=");
        request.setCreateDate(new Date());
        System.out.println(request.getXml());
    }
}
