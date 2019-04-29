import com.gms.delivery.toll.service.TollTrackingService;
import com.gms.xms.model.TrackingModel;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        TollTrackingService trackingService = new TollTrackingService();
        String response = null;
        try {
            response = trackingService.execute("7365160011407");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            List<TrackingModel> trackingModels = trackingService.getTrackingInfo(response);
            System.out.println(trackingModels);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
