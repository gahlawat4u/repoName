package com.gms.xms.persistence.dao.customeraging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinhdd
 * Date 5/12/2017.
 */
public enum RangeAging{
    RAGE_0(" range_0 >0 ",0,0),
    RAGE_1_15(" range_1_15 > 0",1,15),
    RAGE_16_30(" range_16_30 > 0",16,30),
    RAGE_31_45(" range_31_45 >0 ",31,45),
    RAGE_46_60(" range_46_60 >0 ",46,60),
    RAGE_61_90(" range_61_90 >0 ",61,90),
    RAGE_91_120(" range_91_120 >0 ",91,120);

    private String name;
    private int bot;
    private int top;

    RangeAging(String name, int bot, int top) {
        this.name = name;
        this.bot = bot;
        this.top = top;
    }

    public int getBot() {
        return bot;
    }

    public void setBot(int bot) {
        this.bot = bot;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<RangeAging> getAll(){
        List<RangeAging> rangeAgings = new ArrayList<>();
        rangeAgings.add(RAGE_0);
        rangeAgings.add(RAGE_1_15);
        rangeAgings.add(RAGE_16_30);
        rangeAgings.add(RAGE_31_45);
        rangeAgings.add(RAGE_46_60);
        rangeAgings.add(RAGE_61_90);
        rangeAgings.add(RAGE_91_120);
        return rangeAgings;
    }
}
