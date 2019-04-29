package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;

import java.util.Map;

/**
 * Posted from WebshipQuoteLogDetailDao
 * <p>
 * Author TanDT Date May 12, 2015
 */
public class WebshipQuoteLogDetailDao extends BaseDao<WebshipQuoteLogDetailVo> {
    public WebshipQuoteLogDetailDao() {
        super();
    }

    public WebshipQuoteLogDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * @return
     * @throws DaoException
     */

    public Integer update(Map<String, String> context, WebshipQuoteLogDetailVo webshipQuoteLogDetailVo) throws DaoException {
        return update(context, webshipQuoteLogDetailVo, "WebshipQuoteLogDetail.update");
    }

    public Integer insert(Map<String, String> context, WebshipQuoteLogDetailVo webshipQuoteLogDetailVo) throws DaoException {
        return insert(context, webshipQuoteLogDetailVo, "WebshipQuoteLogDetail.insert");
    }
}
