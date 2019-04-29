import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class TimeZoneTest {
    public static void main(String[] args) {
        System.out.println(AppConstants.ADJUSTMENT_STATUS_MAP);
        Date now = Calendar.getInstance().getTime();
        String auDate = DateUtils.convertDateToString(now, "dd/MM/yyy hh:mm:ss", "Australia/Brisbane");
        String localDate = DateUtils.convertDateToString(now, "dd/MM/yyy hh:mm:ss", null);
        System.out.println("Local: " + localDate);
        System.out.println("Au: " + auDate);
    }
}
