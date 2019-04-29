package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.BankVo;

import java.util.List;

/**
 * Posted from BankResponse
 * <p>
 * Author DatTV Date Apr 9, 2015 1:57:18 PM
 */
public class BankResponse extends BaseResponse {
    private List<BankVo> bankList;

    public List<BankVo> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankVo> bankList) {
        this.bankList = bankList;
    }
}
