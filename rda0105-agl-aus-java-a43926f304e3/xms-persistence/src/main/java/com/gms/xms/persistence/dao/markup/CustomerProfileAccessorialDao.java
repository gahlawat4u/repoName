package com.gms.xms.persistence.dao.markup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.CustomerProfileAccessorialVo;

import java.util.List;

/**
 * Posted from CustomerProfileAccessorialDao
 * <p>
 * Author DangDh Oct 26, 2016
 */
public class CustomerProfileAccessorialDao extends BaseDao<CustomerProfileAccessorialVo> {
    public CustomerProfileAccessorialDao() {
        super();
    }

    public CustomerProfileAccessorialDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public List<CustomerProfileAccessorialVo> selectByProfileId(Long profileId) throws DaoException {
        return selectList(profileId, "CustomerProfileAccessorial.selectByProfileId");
    }
}
