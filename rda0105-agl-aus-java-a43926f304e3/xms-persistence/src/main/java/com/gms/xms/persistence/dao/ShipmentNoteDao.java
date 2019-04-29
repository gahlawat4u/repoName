package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ShipmentNoteFilter;
import com.gms.xms.txndb.vo.ShipmentNoteVo;

import java.util.List;
import java.util.Map;

public class ShipmentNoteDao extends BaseDao<ShipmentNoteVo> {
    public List<ShipmentNoteVo> selectNoteList(ShipmentNoteFilter filter) throws DaoException {
        return selectList(filter, "selectNoteByShipmentId");
    }

    public Integer insertShipmentNote(Map<String, String> context, ShipmentNoteVo shipmentNoteVo) throws DaoException {
        return insert(context, shipmentNoteVo, "ShipmentNode_InsertNote");
    }
}
