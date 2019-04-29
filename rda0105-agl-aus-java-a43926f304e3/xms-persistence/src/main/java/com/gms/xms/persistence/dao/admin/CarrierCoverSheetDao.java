/**
 *
 */
package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.ratesheets.CarrierCoverSheetVo;

import java.util.Map;

/**
 * Posted from CarrierCoverSheetDao
 *
 * @author HungNT - @since Oct 9, 2015
 */
public class CarrierCoverSheetDao extends BaseDao<CarrierCoverSheetVo> {

    public CarrierCoverSheetVo selectByCarrierId(Integer carrierId) throws DaoException {
        return select(carrierId, "CarrierCoverSheet.selectByCarrierId");
    }

    public Integer selectCountByCoverSheetId(Long coverSheetId) throws DaoException {
        return (Integer) selectObject(coverSheetId, "CarrierCoverSheet.selectCountByCoverSheetId");
    }

    public void insertCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException {
        insert(context, carrierCoverSheetVo, "CarrierCoverSheet.insertCarrierCoverSheet");
    }

    public void updateCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException {
        update(context, carrierCoverSheetVo, "CarrierCoverSheet.updateCarrierCoverSheet");
    }

    public void deleteCarrierCoverSheet(Map<String, String> context, Integer carrierId) throws DaoException {
        delete(context, carrierId, "CarrierCoverSheet.deleteCarrierCoverSheet");
    }
}
