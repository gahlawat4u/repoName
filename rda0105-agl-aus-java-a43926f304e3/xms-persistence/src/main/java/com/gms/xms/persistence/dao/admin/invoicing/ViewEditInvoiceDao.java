package com.gms.xms.persistence.dao.admin.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.ViewEditInvoiceAccessorialVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingFilter;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingVo;

import java.util.List;

/**
 * Posted from InvoiceInfoDao
 * <p>
 * Author TANDT
 */
public class ViewEditInvoiceDao extends BaseDao<Object> {
    public ViewEditInvoiceDao() {
        super();
    }

    public ViewEditInvoiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public InvoiceInfoVo selectInvoiceInfoById(Long invoiceId) throws DaoException {
        return (InvoiceInfoVo) this.select(invoiceId, "ViewEditInvoice.selectInvoiceInfoById");
    }

    public Integer selectInvoiceStatusByAribillNumber(ShipmentVo shipmentVo) throws DaoException {
        return (Integer) selectObject(shipmentVo, "ViewEditInvoice.selectInvoiceStatusFromAirbillNumber");
    }

    public List<AirbillInfoVo> selectAirbillList(Long invoiceId) throws DaoException {
        return this.selectList(invoiceId, "ViewEditInvoice.selectAirbillList");
    }

    public List<AccessorialInfoVo> selectAccessorialByShipmentId(ShipmentVo shipmentVo) throws DaoException {
        return this.selectList(shipmentVo, "AccessorialInfo.selectAccessorialByShipmentId");
    }

    public List<AccessorialInfoVo> selectAccessorialByShipmentIdAndAirbillNumber(ShipmentVo shipmentVo) throws DaoException {
        return this.selectList(shipmentVo, "AccessorialInfo.selectAccessorialByShipmentIdAndAirbillNumber");
    }

    public List<ViewEditInvoiceAccessorialVo> selectAccessorialsByShipmentId(Integer serviceId) throws DaoException {
        return this.selectList(serviceId, "AccessorialInfo.selectAccessorialsByShipmentId");
    }

    public AirbillInfoEditVo selectAirbillInfoEditByShipment(ShipmentVo shipmentVo) throws DaoException {
        return (AirbillInfoEditVo) this.select(shipmentVo, "AccessorialInfo.selectAirbillInfoEditByShipmentId");
    }

    public List<InvoiceExistingVo> selectExistingInvoice(InvoiceExistingFilter filter) throws DaoException {
        return this.selectList(filter, "ViewEditInvoice.selectExistingInvoice");
    }

    public List<InvoiceCustomerVo> selectCustomerCodeForAddingInvoice(InvoiceExistingFilter filter) throws DaoException {
        return this.selectList(filter, "ViewEditInvoice.selectCustomerCodeForAddingInvoice");
    }
}
