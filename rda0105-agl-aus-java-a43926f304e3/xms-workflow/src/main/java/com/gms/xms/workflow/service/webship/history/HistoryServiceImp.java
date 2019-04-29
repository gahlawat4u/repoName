package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GlobalFunctions;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentNoteDao;
import com.gms.xms.persistence.dao.webship.history.HistoryDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.HistoryDetailScheduleInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryVo;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;

import java.util.List;
import java.util.Map;

public class HistoryServiceImp implements IHistoryService {
    @Override
    public List<HistoryVo> selectByFilter(HistoryFilter filter) throws Exception {
        String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country").trim();
        filter.setDefaultOriginCountry(Integer.parseInt(defaultOriginCountry));
        HistoryDao dao = new HistoryDao();
        List<HistoryVo> historyVos = dao.selectByFilter(filter);
        PieceDao pieceDao = new PieceDao();
        ShipmentDao shipmentDao = new ShipmentDao();
        for (HistoryVo historyVo : historyVos) {
            Long shipmentId = Long.valueOf(historyVo.getShipmentId());
            // Get shipment info.
            ShipmentVo shipmentVo = shipmentDao.selectById(shipmentId);
            // Get pieces.
            List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentId);
            // Recalculate dim weight and actual weight.
            Integer serviceId = historyVo.getServiceId();

            BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(serviceId, shipmentVo.getShipmentTypeId());
            double weight = grossWeight.getQuoteWeight(pieceVos, shipmentVo.getDimensionUnit(), shipmentVo.getWeightUnit());

            String weightString = String.format("%.2f", weight);
            if(defaultOriginCountry.equals("16"))
            {
                if(serviceId==15 || serviceId==2 || serviceId==3 || serviceId==52 || serviceId==59)
                {
                    weight = Math.ceil(weight);
                }
                weightString = String.format("%.1f", weight);
            }
            historyVo.setWeight(weightString+ " " + shipmentVo.getWeightUnit() + "(s)");
        }
        return historyVos;
    }

    @Override
    public Integer selectCountByFilter(HistoryFilter filter) throws DaoException {
        HistoryDao dao = new HistoryDao();
        return dao.selectCountByFilter(filter);
    }

    @Override
    public List<ShipmentNoteVo> selectNoteList(ShipmentNoteFilter filter) throws DaoException {
        ShipmentNoteDao dao = new ShipmentNoteDao();
        return dao.selectNoteList(filter);
    }

    @Override
    public Integer insertShipmentNote(Map<String, String> context, ShipmentNoteVo shipmentNoteVo) throws DaoException {
        ShipmentNoteDao dao = new ShipmentNoteDao();
        return dao.insertShipmentNote(context, shipmentNoteVo);
    }

    @Override
    public String getAwbBarcode(Long shipmentId) throws DaoException {
        ShipmentDao dao = new ShipmentDao();
        return dao.selectAwbBarcode(shipmentId);
    }

    @Override
    public EmailTemplateVo getEmailTemplateByName(String templateName) throws DaoException {
        EmailTemplateDao dao = new EmailTemplateDao();
        return dao.getEmailTemplateByName(templateName);
    }

    @Override
    public HistoryDetailScheduleInfoVo selectHistoryDetailScheduleCollection(Long shipmentId) throws DaoException {
        HistoryDao historyDao = new HistoryDao();
        return historyDao.selectHistoryDetailScheduleCollection(shipmentId);
    }
}
