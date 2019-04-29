package test.com.gms.xms.common;

import com.gms.xms.common.utils.DateUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {
    public static void main(String[] args) {
        Date date = new Date();
        XMLGregorianCalendar calendar = DateUtils.convertDate2XMLGregorianCalendar(date);
        System.out.println(calendar);
        System.out.println(DateUtils.convertXMLGregorianCalendar2Date(calendar));

        try {
            System.out.println(DateUtils.createPickupTimeList(6, 24));
            System.out.println(DateUtils.createPickupTimeList(14, 24));
            String time = "17:30:00";
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date timeDate = sdf.parse(time);
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            System.out.println(sdf2.format(timeDate));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
