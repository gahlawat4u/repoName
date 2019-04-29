package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.InvoicePaymentDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from InvoicePaymentDetailDao
 * <p>
 * Author DatTV Date Apr 17, 2015 9:23:16 PM
 */
public class InvoicePaymentDetailDao extends BaseDao<InvoicePaymentDetailVo> {

    public InvoicePaymentDetailDao() {
        super();
    }

    public InvoicePaymentDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Inserts new invoice payment detail
     *
     * @param invoicePaymentDetail
     * @return
     * @throws DaoException
     */
    public int insert(Map<String, String> context, InvoicePaymentDetailVo invoicePaymentDetail) throws DaoException {
        return insert(context, invoicePaymentDetail, "InvoicePaymentDetail.insert");
    }

    public int delete(Map<String, String> context, InvoicePaymentDetailVo detailVo) throws DaoException {
        return delete(context, detailVo, "InvoicePaymentDetail.deleteByVo");
    }

    public List<InvoicePaymentDetailVo> selectByInvoicePayemntId(long invoicePaymentId) throws DaoException {
        return selectList(invoicePaymentId, "InvoicePaymentDetail.selectByInvoicePaymentId");
    }
}