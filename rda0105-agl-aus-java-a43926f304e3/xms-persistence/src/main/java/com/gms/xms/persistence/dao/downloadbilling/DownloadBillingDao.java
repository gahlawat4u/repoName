package com.gms.xms.persistence.dao.downloadbilling;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.downloadbilling.DownloadLogVo;
import com.gms.xms.txndb.vo.admin.downloadbilling.DownloadTestVo;

import java.util.Map;

public class DownloadBillingDao extends BaseDao<Object> {

    public DownloadBillingDao() {
        super();
    }

    public DownloadBillingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public Integer checkDownloadFile(String fileName) throws DaoException {
        return (Integer) selectObject(fileName, "DownloadBilling.checkDownloadFile");
    }

    public Long selectCustomerCodeFromShipment(Long shipmentId) throws DaoException {
        return (Long) selectObject(shipmentId, "DownloadBilling.selectCustomerCodeFromShipment");
    }

    public InvoiceVo selectInvoiceByAirbillNumber(String airbillNumber) throws DaoException {
        return (InvoiceVo) selectObject(airbillNumber, "DownloadBilling.selectInvoiceByAirbillNumber");
    }

    public void saveDownloadTest(Map<String, String> context, DownloadTestVo downloadTestVo) throws DaoException {
        insert(context, downloadTestVo, "DownloadBilling.saveDownloadTest");
    }

    public void saveDownloadLog(Map<String, String> context, DownloadLogVo downloadLogVo) throws DaoException {
        insert(context, downloadLogVo, "DownloadBilling.saveDownloadLog");
    }

    public void deleteShipmentBilling(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "DownloadBilling.deleteShipmentBilling");
    }

    public void deleteShipmentInvoice(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "DownloadBilling.deleteShipmentInvoice");
    }

}
