package com.gms.xms.persistence.service.postalcode;

import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeFilter;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeVo;

import java.util.List;

/**
 * Posted from IPostalCodeService
 * <p>
 * Author DatTV Aug 25, 2015
 */
public interface IPostalCodeService {
    public List<SearchPostalCodeVo> searchPostalCode(SearchPostalCodeFilter filter) throws Exception;
}
