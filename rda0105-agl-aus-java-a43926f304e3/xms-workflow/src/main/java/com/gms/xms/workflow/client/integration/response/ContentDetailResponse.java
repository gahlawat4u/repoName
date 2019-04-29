package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ContentDetailVo;

import java.util.List;

/**
 * Posted from ContentDetailResponse
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ContentDetailResponse extends BaseResponse {
    private List<ContentDetailVo> contentDetailVos;

    public List<ContentDetailVo> getContentDetailVos() {
        return contentDetailVos;
    }

    public void setContentDetailVos(List<ContentDetailVo> contentDetailVos) {
        this.contentDetailVos = contentDetailVos;
    }
}
