package com.gms.xms.persistence.service.payment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CustomerPaymentVo;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.NoteVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IPaymentService
 * <p>
 * Author DatTV Sep 26, 2015
 */
public interface IPaymentService {
    public List<InvoicePaymentVo> selectByInvoiceCode(String invoiceCode) throws DaoException;

    public void savePayment(Map<String, String> context, CustomerPaymentVo paymentVo, NoteVo noteVo) throws Exception;
}
