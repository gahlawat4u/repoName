package com.gms.xms.workflow.task2.webship.cancelschedule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thinhdd
 * Date 4/22/2017.
 */
public enum ReasonVoidEnum {
    PACKAGE_NOT_READY("PACKAGE NOT READY","001"),
    RATES_TOO_HIGH("RATES TOO HIGH","002"),
    TRANSIT_TIME_TOO_SLOW("TRANSIT TIME TOO SLOW","003"),
    TAKE_TO_SERVICE_CENTER_OR_DROP_BOX("TAKE TO SERVICE CENTER OR DROP BOX","004"),
    COMMITMENT_TIME_NOT_MET("COMMITMENT TIME NOT MET","005"),
    REASON_NOT_GIVEN("REASON NOT GIVEN","006"),
    OTHER("OTHER","007"),
    PICKUP_MODIFIED("PICKUP MODIFIED","008");

    private String name;
    private String key;

    private static final Map<String, ReasonVoidEnum> lookup = new HashMap<String, ReasonVoidEnum>();

    static {
        for (ReasonVoidEnum reason : ReasonVoidEnum.values()) {
            lookup.put(reason.getKey(), reason);
        }
    }

    public static ReasonVoidEnum get(String key) {
        return lookup.get(key);
    }

    ReasonVoidEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
