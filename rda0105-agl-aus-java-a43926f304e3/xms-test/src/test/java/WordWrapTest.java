import com.gms.xms.common.utils.AppUtils;

import java.text.DecimalFormat;

/**
 * Posted from Sep 24, 2016 10:38:31 PM
 * <p>
 * Author dattrinh
 */
public class WordWrapTest {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("##0.00");
        String text = "0.896";
        Double number = Double.valueOf(text);
        System.out.println(df.format(number));
        String ss = AppUtils.wordWrap("An example of a long word is: Supercalifragulistic", 15, "\n", true);
        System.out.println(ss);
    }
}
