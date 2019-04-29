package com.gms.xms.persistence.service.admin.searchairbill;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.SearchAirbillShipmentTypeVo;

import java.util.List;

public interface ISearchAirbillService {
    public List<SearchAirbillShipmentTypeVo> getListShipmentTypeByServiceId(Integer serviceId) throws DaoException;

    public List<InvoiceVo> getInvoiceDatesByFranchiseCodeList(List<String> listFranchiseCode) throws DaoException;

    public List<ShipmentBillingVo> selectImportDateForSearchAirbill() throws DaoException;

}
