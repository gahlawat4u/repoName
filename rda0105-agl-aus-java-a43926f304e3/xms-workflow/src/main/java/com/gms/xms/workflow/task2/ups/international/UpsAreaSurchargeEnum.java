package com.gms.xms.workflow.task2.ups.international;

import java.util.Arrays;
import java.util.List;

/**
 * Created by thinhdd
 * Date 5/24/2017.
 */
public enum UpsAreaSurchargeEnum {
    NONE_AREA_CHARGE(Arrays.asList("Extended Area Surcharge**", "No", "Pickup Area Surcharge - Extended**"),""),
    REMOTE_AREA_CHARGE(Arrays.asList("Remote Area Surcharge","Remote Area Surcharge - Extended"),"Remote Area Surcharge"),
    EXTEND_AREA_CHARGE(Arrays.asList("Extended Area Surcharge","Delivery Area Surcharge- Extended","Delivery Area Surcharge"), "Extended Area Surcharge");

    private List<String> names;
    private String description;

    UpsAreaSurchargeEnum(List<String> names, String description) {
        this.names = names;
        this.description = description;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
