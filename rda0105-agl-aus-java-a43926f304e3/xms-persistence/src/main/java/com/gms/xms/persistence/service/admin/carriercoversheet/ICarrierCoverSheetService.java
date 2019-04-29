/**
 *
 */
package com.gms.xms.persistence.service.admin.carriercoversheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.admin.ratesheets.CarrierCoverSheetVo;

import java.util.Map;

/**
 * Posted from ICarrierCoverSheetService
 *
 * @author HungNT - @since Oct 9, 2015
 */
public interface ICarrierCoverSheetService {
    public void insertCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException;

    public void updateCarrierCoverSheet(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws DaoException;

    public CarrierCoverSheetVo getCarrierCoverSheetByCarrierId(Integer carrierId) throws DaoException;

    public void doAttach(Map<String, String> context, CarrierCoverSheetVo carrierCoverSheetVo) throws Exception;

    public void removeAttach(Map<String, String> context, Integer carrierId) throws DaoException;

    public Integer getCountByCoverSheetId(Long coverSheetId) throws DaoException;
}
