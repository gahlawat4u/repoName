package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.TntRouteViewFilter;
import com.gms.xms.txndb.vo.TntRouteVo;
import com.gms.xms.txndb.vo.webship.history.*;

import java.util.List;

/**
 * Posted from IHistoryDetailService
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public interface IHistoryDetailService {
    public HistoryDetailInfoVo selectHistoryDetailInfo(HistoryDetailFilter filter) throws Exception;

    public List<HistoryDetailPieceVo> selectPieceInfo(HistoryDetailFilter filter, boolean isGroup) throws Exception;

    public List<HistoryDetailAccessorialVo> selectHistoryDetailAccessorial(HistoryDetailFilter filter) throws DaoException;

    List<PieceVo> selectPieceByIdGroup(Long shipmentId) throws DaoException;

    public List<HistoryDetailAccessorialVo> selectHistoryDetailAccessorial(HistoryDetailFilter filter, HistoryDetailInfoVo historyDetailInfoVo) throws DaoException;

    public List<PieceVo> selectPieceByIdNonGroup(Long shipmentId) throws DaoException;

    public List<TntRouteVo> selectByFilterView(TntRouteViewFilter filter) throws DaoException;

    public List<HistoryProductAirbillVo> selectHistoryProductAirbill(Long shipmentId) throws DaoException;

}
