package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

/**
 * File Name: CreditNoteShowListFilter.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.filter.invoicing <br/>
 * Class: CreditNoteShowListFilter
 */
public class CreditNoteShowListFilter extends BaseFilter {
    private String creditCode;

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

}
