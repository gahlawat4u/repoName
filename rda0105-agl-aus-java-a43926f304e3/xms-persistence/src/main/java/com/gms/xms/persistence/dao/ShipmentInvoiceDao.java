package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentInvoiceDao
 * <p>
 * Author DatTV Date Apr 9, 2015 9:55:29 AM
 */
public class ShipmentInvoiceDao extends BaseDao<ShipmentInvoiceVo> {
    /**
     * Gets list of shipment invoices (airbills) that have got outstanding of the invoice that specified by invoice code
     *
     * @param invoiceCode
     * @return List<{@link ShipmentInvoiceVo}>
     * @throws DaoException
     */

    public ShipmentInvoiceDao() {
        super();
    }

    public ShipmentInvoiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<ShipmentInvoiceVo> selectByInvoiceCode(String invoiceCode) throws DaoException {
        return selectList(invoiceCode, "ShipmentInvoice.selectByInvoiceCode");
    }

    public List<ShipmentInvoiceVo> selectByShipmentInvoiceByCode(String invoiceCode) throws DaoException {
        return selectList(invoiceCode, "ShipmentInvoice.selectByShipmentInvoiceByCode");
    }

    public Integer selectVoidStatusInvoiceByShipment(Long shipmentId) throws DaoException {
        return (Integer) selectObject(shipmentId, "ShipmentInvoi_checkStatusVoidByShipmentId");
    }

    public Long selectEmptyInvoiceId(ShipmentInvoiceVo shipmentInvoice) throws DaoException {
        return (Long) selectObject(shipmentInvoice, "ShipmentInvoice.selectEmptyInvoiceId");
    }

    public Integer checkEmptyInvoice(ShipmentInvoiceVo shipmentInvoice) throws DaoException {
        return (Integer) selectObject(shipmentInvoice, "ShipmentInvoice.checkEmptyInvoice");
    }

    public void updateInvoiceIdFromShipment(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice) throws DaoException {
        update(context, shipmentInvoice, "ShipmentInvoice.updateInvoiceIdFromShipment");
    }

    public void deleteShipment(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice) throws DaoException {
        delete(context, shipmentInvoice, "ShipmentInvoice.deleteShipment");
    }

    public void insert(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice) throws DaoException {
        insert(context, shipmentInvoice, "ShipmentInvoice.insert");
    }

    public List<ShipmentInvoiceVo> getShipmentInvoiceByInvoiceCode(String invoiceCode) throws DaoException {
        return selectList(invoiceCode, "ShipmentInvoice.getShipmentInvoiceByInvoiceCode");
    }
}
