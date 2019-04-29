package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GlobalFunctions;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TntRouteDao;
import com.gms.xms.persistence.dao.webship.history.HistoryDetailDao;
import com.gms.xms.persistence.dao.webship.history.HistoryProductAirbillDao;
import com.gms.xms.persistence.daoservice.webship.history.HistoryDetailAccessorialDaoService;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.TntRouteViewFilter;
import com.gms.xms.txndb.vo.TntRouteVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.*;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from HistoryDetailServiceImp
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailServiceImp implements IHistoryDetailService {

    @Override
    public HistoryDetailInfoVo selectHistoryDetailInfo(HistoryDetailFilter filter) throws Exception {
        String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country").trim();
        filter.setDefaultOriginCountry(Integer.parseInt(defaultOriginCountry));
        HistoryDetailDao dao = new HistoryDetailDao();
        HistoryDetailInfoVo detailInfoVo = dao.selectHistoryDetailInfo(filter);
        // Get pieces.
        PieceDao pieceDao = new PieceDao();
        List<PieceVo> pieceVos = pieceDao.selectByShipmentId(detailInfoVo.getShipmentId());
        // Recalculate dim weight and actual weight.
        Integer serviceId = detailInfoVo.getServiceId();
        BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(serviceId, detailInfoVo.getShipmentTypeId());
        double dimWeight = grossWeight.getTotalGrossWeight(pieceVos, detailInfoVo.getDimensionUnit());
        double actualWeight = grossWeight.getTotalActualWeight(pieceVos, detailInfoVo.getWeightUnit());
        if(dimWeight > actualWeight)
            detailInfoVo.setWeightType("Dimensional");
        else
            detailInfoVo.setWeightType("Actual");
        if(detailInfoVo.getWeightUnit().equals("lb"))
        {
            dimWeight = GlobalFunctions.weightUnitConvertKgToPound(detailInfoVo.getWeightUnit(), dimWeight);
        }
        if (serviceId == 1) { // DHL Intl
            if (dimWeight > actualWeight) {
                detailInfoVo.setTotalWeight(String.valueOf(MathUtils.shipmentWeightRound(dimWeight, false)));
            } else {
                detailInfoVo.setTotalWeight(String.valueOf(MathUtils.shipmentWeightRound(actualWeight, false)));
            }
        }
        NumberFormat formatter = new DecimalFormat("###,##0.00");
        if (defaultOriginCountry.equals("16")) {
            formatter = new DecimalFormat("###,##0.0");
        }

        String result = formatter.format(dimWeight);
        if (!StringUtils.isBlank(detailInfoVo.getDimensionUnit())) {
            result += " " + detailInfoVo.getWeightUnit() + "(s)";
        }
        detailInfoVo.setDimWeight(result);
        if (defaultOriginCountry.equals("16") && (serviceId == 15 || serviceId == 2 || serviceId == 3 || serviceId == 52)) {
            actualWeight = MathUtils.shipmentWeightRound(actualWeight, true);
        }
        result = formatter.format(actualWeight);

        if (!StringUtils.isBlank(detailInfoVo.getDimensionUnit())) {
            result += " " + detailInfoVo.getWeightUnit() + "(s)";
        }

        detailInfoVo.setActualWeight(result);

        return detailInfoVo;
    }

    @Override
    public List<HistoryDetailPieceVo> selectPieceInfo(HistoryDetailFilter filter, boolean isGroup) throws Exception {
        String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country").trim();

        ShipmentDao shipmentDao = new ShipmentDao();
        // Get shipment info.
        ShipmentVo shipmentVo = shipmentDao.selectById(filter.getShipmentId());
        // Get carrier id.
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentVo.getShipmentTypeId());
        // Recalculate cubic weight for each piece.
        BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(shipmentTypeVo.getServiceId(), shipmentVo.getShipmentTypeId());
        HistoryDetailPieceVo detailPieceVo;
        String dimension, cubicWeight;
        double weight;
        NumberFormat formatter = new DecimalFormat("###,##0.00");
        if("16".equals(defaultOriginCountry))
        {
            formatter = new DecimalFormat("###,##0.0");
        }
        List<HistoryDetailPieceVo> detailPieceVos = new ArrayList<HistoryDetailPieceVo>();
        // Get pieces.
        PieceDao pieceDao = new PieceDao();
        List<PieceVo> pieceVos;
        if(isGroup)
            pieceVos = pieceDao.selectGroupedPiecesByShipmentId(filter.getShipmentId());
        else
            pieceVos = pieceDao.selectPiecesByShipmentId(filter.getShipmentId());
        for (PieceVo pieceVo : pieceVos) {
            dimension = String.valueOf(pieceVo.getDimensionL().intValue());
            dimension += " x " + String.valueOf(pieceVo.getDimensionW().intValue());
            dimension += " x " + String.valueOf(pieceVo.getDimensionH().intValue());
            dimension += " " + shipmentVo.getDimensionUnit() + "(s)";
            detailPieceVo = new HistoryDetailPieceVo();
            detailPieceVo.setPieces(pieceVo.getQuantity());
            detailPieceVo.setDimension(dimension);
            detailPieceVo.setDeadWeight(formatter.format(pieceVo.getQuantity() * pieceVo.getDeadWeight()) + " " + shipmentVo.getWeightUnit() + "(s)");
            weight = pieceVo.getQuantity() * grossWeight.getGrossWeight(pieceVo.getDimensionL(), pieceVo.getDimensionW(), pieceVo.getDimensionH(), shipmentVo.getDimensionUnit());
            if(shipmentVo.getWeightUnit().equals("lb"))
                weight = GlobalFunctions.weightUnitConvertKgToPound(shipmentVo.getWeightUnit(), weight);
            cubicWeight = formatter.format(weight) + " " + shipmentVo.getWeightUnit() + "(s)";
            detailPieceVo.setCubicWeight(cubicWeight);
            detailPieceVos.add(detailPieceVo);
        }
        return detailPieceVos;
    }

    @Override
    public List<PieceVo> selectPieceByIdNonGroup(Long shipmentId) throws DaoException {
        PieceDao dao = new PieceDao();
        return dao.selectPieceByIdNonGroup(shipmentId);
    }

    @Override
    public List<PieceVo> selectPieceByIdGroup(Long shipmentId) throws DaoException {
        PieceDao dao = new PieceDao();
        return dao.selectGroupedPiecesByShipmentId(shipmentId);
    }

    @Override
    public List<HistoryDetailAccessorialVo> selectHistoryDetailAccessorial(HistoryDetailFilter filter, HistoryDetailInfoVo historyDetailInfoVo) throws DaoException {
        HistoryDetailAccessorialDaoService dao = new HistoryDetailAccessorialDaoService();
        return dao.historyDetailAccessorial(filter, historyDetailInfoVo);
    }

    @Override
    public List<HistoryDetailAccessorialVo> selectHistoryDetailAccessorial(HistoryDetailFilter filter) throws DaoException {
        return this.selectHistoryDetailAccessorial(filter, null);
    }

    @Override
    public List<TntRouteVo> selectByFilterView(TntRouteViewFilter filter) throws DaoException {
        TntRouteDao dao = new TntRouteDao();
        return dao.selectByFilterView(filter);
    }

    @Override
    public List<HistoryProductAirbillVo> selectHistoryProductAirbill(Long shipmentId) throws DaoException {
        HistoryProductAirbillDao dao = new HistoryProductAirbillDao();
        return dao.selectHistoryProductAirbill(shipmentId);
    }

}
