/**
 *
 */
package com.gms.xms.persistence.service.admin.carriercoversheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.admin.CarrierCoverSheetDao;
import com.gms.xms.txndb.vo.admin.ratesheets.CarrierCoverSheetVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from CarrierCoverSheetServiceImp
 *
 * @author HungNT - @since Oct 9, 2015
 */
public class CarrierCoverSheetServiceImp implements ICarrierCoverSheetService {
    private static final Log log = LogFactory.getLog(CarrierCoverSheetServiceImp.class);
    private CarrierCoverSheetDao dao = new CarrierCoverSheetDao();

    @Override
    public CarrierCoverSheetVo getCarrierCoverSheetByCarrierId(Integer carrierId) throws DaoException {
        return dao.selectByCarrierId(carrierId);
    }

    @Override
    public Integer getCountByCoverSheetId(Long coverSheetId) throws DaoException {
        return dao.selectCountByCoverSheetId(coverSheetId);
    }

    @Override
    public void insertCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException {
        dao.insertCarrierCoverSheet(context, carrierCoverSheetVo);
    }

    @Override
    public void updateCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException {
        dao.updateCarrierCoverSheet(context, carrierCoverSheetVo);
    }

    @Override
    public void doAttach(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws Exception {
        // Check if the carrier cover sheet is existed
        CarrierCoverSheetVo carrierCoverSheetVoCheck = dao.selectByCarrierId(carrierCoverSheetVo.getCarrierId());
        if (carrierCoverSheetVoCheck != null && carrierCoverSheetVoCheck.getCarrierId() != 0) {
            dao.updateCarrierCoverSheet(context, carrierCoverSheetVo);
            log.info("Updated: " + GsonUtils.toGson(carrierCoverSheetVo));
        } else {
            if (carrierCoverSheetVo.getCoverSheetId() == 0) {
                throw new Exception("Please set cover sheet first.");
            }
            dao.insertCarrierCoverSheet(context, carrierCoverSheetVo);
            log.info("Inserted: " + GsonUtils.toGson(carrierCoverSheetVo));
        }
    }

    @Override
    public void removeAttach(Map<String, String> context, Integer carrierId) throws DaoException {
        dao.deleteCarrierCoverSheet(context, carrierId);
        log.info("Deleted: carrierId = " + String.valueOf(carrierId));
    }
}
