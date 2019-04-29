package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.admin.SearchAirbillShipmentTypeVo;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillFilter;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillVo;
import com.gms.xms.txndb.vo.invoicing.searchairbill.TotalSearchAirbillVo;

import java.util.List;

public class SearchAirbillDao extends BaseDao<Object> {
    public List<SearchAirbillShipmentTypeVo> getShipmentTypeListByServiceId(Integer serviceId) throws DaoException {
        return selectList(serviceId, "SearchAirbill.getShipmentTypeListByServiceId");
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceVo> selectInvoicesDateByFranchiseCodeList(List<String> listFranchiseCode) throws DaoException {
        return (List<InvoiceVo>) (Object) selectObjectList(listFranchiseCode, "SearchAirbill.selectInvoicesDateByFranchiseCodeList");
    }

    public List<SearchAirbillVo> selectAirbillList(SearchAirbillFilter filter) throws DaoException {
        return selectList(filter, "SearchAirbill.selectAirbillList");
    }

    public TotalSearchAirbillVo selectCountAirbillList(SearchAirbillFilter filter) throws DaoException {
        return (TotalSearchAirbillVo) select(filter, "SearchAirbill.selectCountAirbillList");
    }
}
