package com.gms.xms.persistence.service.tntconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TntConnoteFilter;
import com.gms.xms.txndb.vo.tnt.TntConnoteShipmentInfoVo;
import com.gms.xms.txndb.vo.tnt.TntDomConnoteShipmentInfoVo;

import java.util.List;
import java.util.Map;

public interface ITntConnoteService {
    public String getConnNumber(TntConnoteFilter tntConnoteFilter) throws DaoException;

    public void insertTntConnote(Map<String, String> context, TntConnoteFilter tntConnoteFilter) throws DaoException;

    List<TntConnoteShipmentInfoVo> selectTntDomConnoteInfo(Long customerCode) throws DaoException;

    List<TntDomConnoteShipmentInfoVo> selectTntConnoteSameInfoDay(Long shipmentId) throws DaoException;

    Long countTntDomConnoteInfo(Long customerCode) throws DaoException;

    void generateTntManifestList(Map<String, String> context, Map<Long, Long> shipmentTypeConnoteIdMap, List<String> shipmentIds) throws DaoException;
}
