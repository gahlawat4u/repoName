package com.gms.xms.persistence.service.admin.searchairbill;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.SearchAirbillShipmentTypeVo;

import java.util.List;

public class SearchAirbillServiceImp implements ISearchAirbillService {
    SearchAirbillDao dao = new SearchAirbillDao();

    @Override
    public List<SearchAirbillShipmentTypeVo> getListShipmentTypeByServiceId(Integer serviceId) throws DaoException {
        return dao.getShipmentTypeListByServiceId(serviceId);
    }

    @Override
    public List<InvoiceVo> getInvoiceDatesByFranchiseCodeList(List<String> listFranchiseCode) throws DaoException {
        return dao.selectInvoicesDateByFranchiseCodeList(listFranchiseCode);
    }

    @Override
    public List<ShipmentBillingVo> selectImportDateForSearchAirbill() throws DaoException {
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
        return shipmentBillingDao.selectImportDateForSearchAirbill();
    }
}
