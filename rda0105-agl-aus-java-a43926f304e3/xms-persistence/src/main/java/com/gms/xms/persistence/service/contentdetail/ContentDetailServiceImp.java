package com.gms.xms.persistence.service.contentdetail;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ContentDetailDao;
import com.gms.xms.txndb.vo.ContentDetailVo;

import java.util.List;

/**
 * Posted from ContentDetailServiceImp
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public class ContentDetailServiceImp implements IContentDetailService {
    private ContentDetailDao dao = new ContentDetailDao();

    @Override
    public List<ContentDetailVo> getContentDetailList() throws DaoException {
        return dao.selectAllContentDetail();
    }
}
