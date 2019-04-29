package com.gms.xms.persistence.service.postalcode;

import com.gms.xms.persistence.dao.webship.SearchPostalCodeDao;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeFilter;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeVo;

import java.util.List;

public class PostalCodeServiceImp implements IPostalCodeService {

    @Override
    public List<SearchPostalCodeVo> searchPostalCode(SearchPostalCodeFilter filter) throws Exception {
        SearchPostalCodeDao postalCodeDao = new SearchPostalCodeDao();
        return postalCodeDao.searchCityByNameOrPostalCode(filter);
    }
}