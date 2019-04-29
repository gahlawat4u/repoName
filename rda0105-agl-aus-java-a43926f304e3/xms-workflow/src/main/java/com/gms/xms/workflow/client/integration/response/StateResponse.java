package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.StateVo;

import java.util.List;

/**
 * Posted from StateResponse
 * <p>
 * Author HungNT Date May 13, 2015
 */
public class StateResponse extends BaseResponse {
    private List<StateVo> stateVos;
    private Integer recordCount;

    public List<StateVo> getStateVos() {
        return stateVos;
    }

    public void setStateVos(List<StateVo> stateVos) {
        this.stateVos = stateVos;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }
}
