package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.PieceVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from PieceDaoService
 * <p>
 * Author TanDT Date Mar 28, 2015
 */
public class PieceDao extends BaseDao<PieceVo> {
    public PieceDao() {
        super();
    }

    public PieceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<PieceVo> selectPieceById(Long shipmentId) throws DaoException {
        return selectList(shipmentId, "Piece.getPieceBySmId");
    }

    public List<PieceVo> selectPieceByIdNonGroup(Long shipmentId) throws DaoException {
        return selectList(shipmentId, "Piece.getPieceBySmIdNonGroup");
    }

    public void insert(Map<String, String> context, PieceVo pieceVo) throws DaoException {
        insert(context, pieceVo, "Piece.insert");
    }

    public List<PieceVo> selectByShipmentId(long shipmentId) throws DaoException {
        return selectList(shipmentId, "Piece.selectByShipmentId");
    }

    public List<PieceVo> selectGroupedPiecesByShipmentId(long shipmentId) throws DaoException {
        return selectList(shipmentId, "Piece.selectGroupedPiecesByShipmentId");
    }

    public List<PieceVo> selectPiecesByShipmentId(long shipmentId) throws DaoException {
        return selectList(shipmentId, "Piece.selectPiecesByShipmentId");
    }

    public PieceVo getTotalWeightAndCustomerValue(long shipmentId) throws DaoException {
        return (PieceVo) selectObject(shipmentId, "Piece.getTotalWeightAndCustomerValue");
    }
}
