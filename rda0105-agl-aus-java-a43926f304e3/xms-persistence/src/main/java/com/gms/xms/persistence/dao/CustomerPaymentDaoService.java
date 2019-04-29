package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerPaymentDaoService
 * <p>
 * Author DatTV Date Apr 17, 2015 8:59:23 PM
 */
public class CustomerPaymentDaoService {

    private static final Log logger = LogFactory.getLog(CustomerPaymentDaoService.class);

    private CustomerPaymentDao customerPaymentDao;
    private InvoicePaymentDao invoicePaymentDao;
    private InvoicePaymentDetailDao invoicePaymentDetailDao;
    private OverpaymentDao overpaymentDao;
    private NoteDao noteDao;
    private InvoiceDao invoiceDao;

    /**
     * Save the customer payment, over payment and note if it has
     *
     * @param customerPayment
     * @param overpayment
     * @param note
     * @throws DaoException
     */
    public void saveCustomerPayment(Map<String, String> context, CustomerPaymentVo customerPayment, OverpaymentVo overpayment, NoteVo note) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        customerPaymentDao = new CustomerPaymentDao(sessionClient);
        invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
        overpaymentDao = new OverpaymentDao(sessionClient);
        noteDao = new NoteDao(sessionClient);
        invoiceDao = new InvoiceDao(sessionClient);

        try {
            // Insert customer payment
            customerPaymentDao.insert(context, customerPayment);

            Long cusPaymentId = customerPayment.getCusPaymentId();

            // Insert invoice payment
            for (InvoicePaymentVo invoicePayment : customerPayment.getInvoicePayments()) {
                invoicePayment.setCusPaymentId(cusPaymentId);
                invoicePaymentDao.insert(context, invoicePayment);
                Long invoicePaymentId = invoicePayment.getInvoicePaymentId();
                if (invoicePayment.getAmount().compareTo(invoicePayment.getRemainningBalance()) >= 0) {
                    InvoiceVo invoiceVo = invoiceDao.getByCode(invoicePayment.getInvoiceCode());
                    invoiceVo.setPaid(1);
                    invoiceDao.update(context, invoiceVo);
                }

                // Insert invoice payment detail
                for (InvoicePaymentDetailVo invoicePaymentDetail : invoicePayment.getInvoicePaymentDetails()) {
                    invoicePaymentDetail.setInvoicePaymentId(invoicePaymentId);
                    invoicePaymentDetailDao.insert(context, invoicePaymentDetail);
                }
            }

            // Insert over payment if it's not null
            if (overpayment != null) {
                overpayment.setCusPaymentId(cusPaymentId);
                overpaymentDao.insert(context, overpayment);
            }

            // Insert note if it's not null
            if (note != null) {
                note.setPaymentId(cusPaymentId);
                noteDao.insert(context, note);
            }

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            logger.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }

    /**
     * Save all invoice payments includes invoice payment details
     *
     * @param invoicePayments
     * @throws DaoException
     */
    public void saveInvoicePayment(Map<String, String> context, List<InvoicePaymentVo> invoicePayments, OverpaymentVo overpayment) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        invoicePaymentDao = new InvoicePaymentDao(sessionClient);
        invoicePaymentDetailDao = new InvoicePaymentDetailDao(sessionClient);
        overpaymentDao = new OverpaymentDao();

        try {
            // Insert invoice payment
            for (InvoicePaymentVo invoicePayment : invoicePayments) {
                invoicePaymentDao.insert(context, invoicePayment);
                Long invoicePaymentId = invoicePayment.getInvoicePaymentId();

                // Insert invoice payment detail
                for (InvoicePaymentDetailVo invoicePaymentDetail : invoicePayment.getInvoicePaymentDetails()) {
                    invoicePaymentDetail.setInvoicePaymentId(invoicePaymentId);
                    invoicePaymentDetailDao.insert(context, invoicePaymentDetail);
                }
            }

            // Delete over payment if it's over amount is 0 else update it
            if (overpayment.getOverAmount().compareTo(BigDecimal.ZERO) == 0) {
                overpaymentDao.delete(context, overpayment.getCusPaymentId());
            } else {
                overpaymentDao.update(context, overpayment);
            }

            sessionClient.endTransaction();
        } catch (DaoException ex) {
            logger.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }
}
