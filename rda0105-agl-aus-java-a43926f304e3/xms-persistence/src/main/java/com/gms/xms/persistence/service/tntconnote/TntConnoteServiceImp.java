package com.gms.xms.persistence.service.tntconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TntConnoteFilter;
import com.gms.xms.filter.webship.TntConnoteShipmentInfoFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.webship.TntConnoteDao;
import com.gms.xms.txndb.vo.tnt.TntConnoteShipmentInfoVo;
import com.gms.xms.txndb.vo.tnt.TntDomConnoteShipmentInfoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class TntConnoteServiceImp implements ITntConnoteService {
    private static final Log logger = LogFactory.getLog(TntConnoteServiceImp.class);

    private TntConnoteDao dao = new TntConnoteDao();

    @Override
    public String getConnNumber(TntConnoteFilter tntConnoteFilter) throws DaoException {
        return dao.selectConnumberByShipmentId(tntConnoteFilter);
    }

    @Override
    public void insertTntConnote(Map<String, String> context, TntConnoteFilter tntConnoteFilter) throws DaoException {
        dao.insertTntConnote(context, tntConnoteFilter);
    }

    @Override
    public List<TntConnoteShipmentInfoVo> selectTntDomConnoteInfo(Long customerCode) throws DaoException {
        return dao.selectTntDomConnoteInfo(customerCode);
    }

    @Override
    public List<TntDomConnoteShipmentInfoVo> selectTntConnoteSameInfoDay(Long shipmentId) throws DaoException {
        return dao.selectTntConnoteSameInfoDay(shipmentId);
    }

    @Override
    public Long countTntDomConnoteInfo(Long customerCode) throws DaoException {
        return dao.countTntDomConnoteInfo(customerCode);
    }

    @Override
    public void generateTntManifestList(Map<String, String> context, Map<Long, Long> shipmentTypeConnoteIdMap, List<String> shipmentIds) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        dao = new TntConnoteDao(sessionClient);
        sessionClient.startTransaction();
        String shipmentList="";
        for(String shipmentId : shipmentIds)
        {
            shipmentList += shipmentId + ",";
        }
        shipmentList = shipmentList.substring(0, shipmentList.length() - 1);
        try {
            for (Map.Entry<Long, Long> entry : shipmentTypeConnoteIdMap.entrySet()) {
                TntConnoteShipmentInfoFilter tntConnoteShipmentInfoFilter = new TntConnoteShipmentInfoFilter(
                        entry.getKey(), entry.getValue(), shipmentList);
                dao.updateTntWManifestInfoConnote(context, tntConnoteShipmentInfoFilter);
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }

    }
}