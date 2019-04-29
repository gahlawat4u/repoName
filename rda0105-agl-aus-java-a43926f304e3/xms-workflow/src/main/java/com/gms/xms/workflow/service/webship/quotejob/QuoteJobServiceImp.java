package com.gms.xms.workflow.service.webship.quotejob;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GlobalFunctions;
import com.gms.xms.persistence.dao.WebshipQuoteLogDao;
import com.gms.xms.persistence.daoservice.webship.quotejob.QuoteLogDaoService;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.QuoteJobFilter;
import com.gms.xms.txndb.vo.QuotePieceVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;
import com.gms.xms.workflow.utils.ShipmentUtils;

import java.util.List;
import java.util.Map;

/**
 * Posted from QuoteJobServiceImp
 * <p>
 * Author HungNT Date Jul 9, 2015
 */
public class QuoteJobServiceImp implements IQuoteJobService {
    private WebshipQuoteLogDao dao = new WebshipQuoteLogDao();

    @Override
    public List<QuoteJobVo> getQuoteJobList(QuoteJobFilter quoteJobFilter) throws Exception {
        List<QuoteJobVo> quoteJobVos = dao.selectQuoteJobList(quoteJobFilter);
        return quoteJobVos;
    }

    @Override
    public Long getQuoteJobListCount(QuoteJobFilter quoteJobFilter) throws Exception {
        Long quoteJobTotalRecord = dao.selectCountQuoteJobList(quoteJobFilter);
        return quoteJobTotalRecord;
    }

    @Override
    public QuoteJobVo getQuoteJobDetail(QuoteJobFilter quoteJobFilter) throws Exception {
        QuoteJobVo quoteJobVo = dao.selectQuoteJobDetailById(quoteJobFilter.getQuoteId());
        IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(quoteJobVo.getShipmentTypeId());
        Integer serviceId = 0;
        if (shipmentTypeVo != null) {
            serviceId = shipmentTypeVo.getServiceId();
        }
        Double actualWeight = 0D;
        Double dimWeight = 0D;

        for (QuotePieceVo piece : quoteJobVo.getQuotePieces()) {
            Double dimW = piece.getDimensionW();
            Double dimH = piece.getDimensionH();
            Double dimL = piece.getDimensionL();
            Double cubicWeight = 0D;
            if (serviceId == 72) { // Star track
                cubicWeight = ShipmentUtils.getGrossWeight(dimW, dimH, dimL, quoteJobVo.getDimensionUnit(), ShipmentUtils.getForceVolWeightStartrack(shipmentTypeVo.getShipmentTypeName()));
            }
            else {
                cubicWeight = ShipmentUtils.getGrossWeight(dimW, dimH, dimL, quoteJobVo.getDimensionUnit(), ShipmentUtils.getForceVolWeight(serviceId));
            }
            if(quoteJobVo.getWeightUnit().equals("LB") || quoteJobVo.getWeightUnit().equals("lb"))
                cubicWeight = GlobalFunctions.weightUnitConvertKgToPound(quoteJobVo.getWeightUnit(), cubicWeight);
            piece.setCubicWeight(cubicWeight * piece.getQuantity());
            piece.setWeight(piece.getWeight());

            actualWeight += piece.getWeight() * piece.getQuantity();
            dimWeight += piece.getCubicWeight();
        }
        quoteJobVo.setActualWeight(actualWeight);
        quoteJobVo.setDimWeight(dimWeight);
        return quoteJobVo;
    }

    @Override
    public void saveQuoteLog(Map<String, String> context, QuoteJobVo quoteJobVo) throws DaoException {
        QuoteLogDaoService quoteLogDaoService = new QuoteLogDaoService();
        quoteLogDaoService.saveQuoteLog(context, quoteJobVo);
    }

    @Override
    public String getPreviousQuoteNumber(Long customerCode) throws DaoException {
        return dao.selectPreviousQuoteNumber(customerCode);
    }
}
