package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeFilter;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeVo;

import java.util.List;

/**
 * Posted from SearchPostalCodeDao
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeDao extends BaseDao<SearchPostalCodeVo> {

    public List<SearchPostalCodeVo> searchCityByNameOrPostalCode(SearchPostalCodeFilter filter) throws DaoException {
        return selectList(filter, "PostalCode.searchCityByNameOrPostalCode");
    }
}