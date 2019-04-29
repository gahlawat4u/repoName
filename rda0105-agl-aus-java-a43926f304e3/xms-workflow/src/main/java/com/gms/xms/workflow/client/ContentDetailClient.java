package com.gms.xms.workflow.client;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ContentDetailDao;
import com.gms.xms.txndb.vo.ContentDetailVo;
import com.gms.xms.workflow.client.integration.response.ContentDetailResponse;

import java.util.List;

/**
 * Posted from ContentDetailClient
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class ContentDetailClient {
    public ContentDetailResponse getAllContentDetail() throws DaoException {
        ContentDetailDao contentDetailDao = new ContentDetailDao();
        List<ContentDetailVo> contentDetailVos = contentDetailDao.selectAllContentDetail();
        ContentDetailResponse contentDetailResponse = new ContentDetailResponse();
        contentDetailResponse.setContentDetailVos(contentDetailVos);
        return contentDetailResponse;
    }
}
