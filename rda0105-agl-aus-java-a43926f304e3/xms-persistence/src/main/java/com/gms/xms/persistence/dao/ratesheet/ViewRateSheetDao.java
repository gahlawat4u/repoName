package com.gms.xms.persistence.dao.ratesheet;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.ratesheet.ViewRateSheetShipmentVo;

/**
 * Posted from Jul 23, 2016 9:29:31 AM
 * <p>
 * Author dattrinh
 */
public class ViewRateSheetDao extends BaseDao<Object> {
    public ViewRateSheetDao() {
        super();
    }

    public ViewRateSheetDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public ViewRateSheetShipmentVo getShipmentInfo(ViewRateSheetShipmentVo filter) throws DaoException {
        return (ViewRateSheetShipmentVo) select(filter, "ViewRateSheet.getShipmentInfo");
    }
}
