package com.gms.xms.persistence.service.piece;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.txndb.vo.PieceVo;

import java.util.List;

public class PieceServiceImp implements IPieceService {
    private PieceDao dao = new PieceDao();

    @Override
    public List<PieceVo> getPiecesByShipmentId(Long shipmentId) throws DaoException {
        return dao.selectPieceById(shipmentId);
    }
}
