package com.gms.xms.persistence.service.piece;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PieceVo;

import java.util.List;

public interface IPieceService {
    public List<PieceVo> getPiecesByShipmentId(Long shipmentId) throws DaoException;
}
