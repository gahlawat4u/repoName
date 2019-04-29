package com.gms.xms.filter.note;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from NoteFilter
 * <p>
 * Author DatTV Oct 1, 2015
 */
public class NoteFilter extends BaseFilter {
    private String customerCode;

    @Override
    public String toString() {
        return "NoteFilter [customerCode=" + customerCode + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
