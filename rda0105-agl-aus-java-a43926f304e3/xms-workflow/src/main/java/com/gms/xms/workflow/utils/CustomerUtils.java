package com.gms.xms.workflow.utils;

/**
 * Created by thinhdd on 2/14/2017.
 */
public class CustomerUtils {
    public static String getIsFranchiseFromCusCode(Long cuscode) {
        String codeFranchise = String.valueOf(cuscode).substring(3);
        if (codeFranchise.equals("0001")) {
            return "franchise";
        } else {
            return "customer";
        }
    }
}
