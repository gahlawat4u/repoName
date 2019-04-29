package com.gms.xms.persistence.dao.admin.edigenerate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.edigenerate.TntShipmentDetailForEtVo;
import com.gms.xms.dto.edigenerate.TntShipmentForEtVo;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.ShipmentVo;

import java.util.List;

/**
 * Posted from Sep 22, 2016 11:54:53 AM
 * <p>
 * Author dattrinh
 */
public class EdiGenerateDao extends BaseDao<Object> {
    public List<TntShipmentForEtVo> getTntShipmentForEt() throws DaoException {
        return selectList(null, "EdiGenerate.getTntShipmentForEt");
    }

    public List<TntShipmentDetailForEtVo> getTntShipmentDetailForEt(TntShipmentForEtVo filter) throws DaoException {
        return selectList(filter, "EdiGenerate.getTntShipmentDetailForEt");
    }

    public ShipmentVo getShipmentInfoByShipmentIdForEtFile(Long shipmentId) throws DaoException {
        return (ShipmentVo) select(shipmentId, "EdiGenerate.getShipmentInfoByShipmentIdForEtFile");
    }
}
