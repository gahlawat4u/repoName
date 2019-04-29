import com.gms.delivery.tnt.dom.service.TntDomTrackingService;

public class TntDomTrackingTest {
    public static void main(String[] args) throws Exception {
        TntDomTrackingService service = new TntDomTrackingService();
        String htmlResponse = service.execute("980126165924X"); // 980126165924X
        service.getTrackingList(htmlResponse);
    }
}
