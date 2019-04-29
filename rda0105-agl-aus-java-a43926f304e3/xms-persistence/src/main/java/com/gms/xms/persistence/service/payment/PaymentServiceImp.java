package com.gms.xms.persistence.service.payment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.CustomerPaymentDao;
import com.gms.xms.persistence.dao.InvoicePaymentDao;
import com.gms.xms.persistence.dao.InvoicePaymentDetailDao;
import com.gms.xms.persistence.dao.NoteDao;
import com.gms.xms.txndb.vo.CustomerPaymentVo;
import com.gms.xms.txndb.vo.InvoicePaymentDetailVo;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.NoteVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from PaymentServiceImp
 * <p>
 * Author DatTV Sep 26, 2015
 */
public class PaymentServiceImp implements IPaymentService {

    private static final Log log = LogFactory.getLog(PaymentServiceImp.class);

    @Override
    public List<InvoicePaymentVo> selectByInvoiceCode(String invoiceCode) throws DaoException {
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao();
        return invoicePaymentDao.selectByInvoiceCode(invoiceCode);
    }

    @Override
    public void savePayment(Map<String, String> context, CustomerPaymentVo paymentVo, NoteVo noteVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        CustomerPaymentDao customerPaymentDao = new CustomerPaymentDao(sessionClient);
        InvoicePaymentDetailDao invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
        NoteDao noteDao = new NoteDao(sessionClient);
        sessionClient.startTransaction();
        log.info("Add payment: start transaction...");
        try {
            // Insert customer payment.
            customerPaymentDao.insert(context, paymentVo);
            log.info("Insert customer payment: " + paymentVo);
            // Insert invoice payment.
            if (paymentVo.getInvoicePayments() != null && paymentVo.getInvoicePayments().size() > 0) {
                for (InvoicePaymentVo invoicePayment : paymentVo.getInvoicePayments()) {
                    invoicePayment.setCusPaymentId(paymentVo.getCusPaymentId());
                    invoicePaymentDao.insert(context, invoicePayment);
                    log.info("Insert invoice payment: " + invoicePayment);
                    // Insert invoice payment detail.
                    if (invoicePayment.getInvoicePaymentDetails() != null && invoicePayment.getInvoicePaymentDetails().size() > 0) {
                        for (InvoicePaymentDetailVo invoicePaymentDetail : invoicePayment.getInvoicePaymentDetails()) {
                            invoicePaymentDetail.setInvoicePaymentId(invoicePayment.getInvoicePaymentId());
                            invoicePaymentDetailDao.insert(context, invoicePaymentDetail);
                            log.info("Insert invoice payment detail: " + invoicePaymentDetail);
                        }
                    }
                    // Update reserve flag of old invoice payment.
                    InvoicePaymentVo oldInvoicePayment = invoicePaymentDao.getInvoicePaymentById(invoicePayment.getRevInvoicePaymentId());
                    oldInvoicePayment.setReverseFlag((byte) 1);
                    invoicePaymentDao.update(context, oldInvoicePayment);
                    log.info("Update invoice payment: " + oldInvoicePayment);
                }
            }
            // Insert note.
            noteVo.setPaymentId(paymentVo.getCusPaymentId());
            noteDao.insert(context, noteVo);
            // Commit transaction.
            sessionClient.endTransaction();
            log.info("Add payment: commit transaction...");
        } catch (Exception e) {
            // Roll back transaction.
            sessionClient.rollback();
            log.info("Add payment: roll back transaction...");
            throw e;
        }
    }
}