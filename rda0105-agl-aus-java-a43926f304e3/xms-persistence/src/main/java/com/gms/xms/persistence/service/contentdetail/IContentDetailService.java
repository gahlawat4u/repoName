package com.gms.xms.persistence.service.contentdetail;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ContentDetailVo;

import java.util.List;

/**
 * Posted from IContentDetailService
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public interface IContentDetailService {
    public List<ContentDetailVo> getContentDetailList() throws DaoException;
}
