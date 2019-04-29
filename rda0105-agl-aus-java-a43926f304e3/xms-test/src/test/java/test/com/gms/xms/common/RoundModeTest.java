package test.com.gms.xms.common;

import com.gms.xms.common.utils.MathUtils;

public class RoundModeTest {
    public static void main(String[] args) throws Exception {
        Double n = 2.31235D;
        int intVal = n.intValue();
        Double frac = n - intVal;
        Double result;
        System.out.println("Int: " + intVal);
        System.out.println("Frac: " + frac);
        if (frac == 0) {
            result = n;
        } else if (frac < 0.5) {
            result = intVal + 0.5D;
        } else {
            result = intVal + 1.0D;
        }
        System.out.println("Result: " + result);
        System.out.println("Shipment weight round no ceil: " + MathUtils.shipmentWeightRound(n, false));
        System.out.println("Shipment weight round with ceil: " + MathUtils.shipmentWeightRound(n, true));
    }
}
