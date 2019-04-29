package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.filter.invoicing.MoveUnfrozenInvoicesFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.invoice.MoveUnfrozenInvoicesDao;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.txndb.parameter.InvoiceIdParam;
import com.gms.xms.txndb.vo.InvoiceVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from MoveUnfrozenInvoicesServiceImp
 * <p>
 * Author dattrinh Mar 10, 2016 5:13:41 PM
 */
public class MoveUnfrozenInvoicesServiceImp implements IMoveUnfrozenInvoicesService {
    private static final Log log = LogFactory.getLog(MoveUnfrozenInvoicesServiceImp.class);

    private MoveUnfrozenInvoicesDao dao = new MoveUnfrozenInvoicesDao();

    @Override
    public List<Date> getUnfrozenInvoiceDates(String franchiseList) throws DaoException {
        return dao.getUnfrozenInvoiceDates(franchiseList);
    }

    @Override
    public long moveInvoices(Map<String, String> context, MoveUnfrozenInvoicesFilter filter) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        InvoiceDao invoiceDao = new InvoiceDao(sessionClient);
        MoveUnfrozenInvoicesDao moveInvoiceDao = new MoveUnfrozenInvoicesDao(sessionClient);
        long count = 0;
        try {
            // Create new transaction.
            sessionClient.startTransaction();
            // Get list of unfrozen invoices by from date to move.
            List<InvoiceVo> invoiceVos = dao.getMoveInvoices(filter);
            if (invoiceVos != null && invoiceVos.size() > 0) {
                String newInvoiceCode;
                InvoiceVo newInvoice;
                for (InvoiceVo invoiceVo : invoiceVos) {
                    // Create new Invoice Code.
                    newInvoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(invoiceVo.getCustomerCode()), filter.getToDate());
                    // Check if the new invoice code exists or not?
                    newInvoice = invoiceDao.getByCode(newInvoiceCode);
                    if (newInvoice != null) {
                        // If new invoice is frozen then don't move.
                        if (newInvoice.getStatus() != 0) {
                            continue;
                        }
                        // Update shipment invoice.
                        InvoiceIdParam invoiceIdParam = new InvoiceIdParam();
                        invoiceIdParam.setNewInvoiceId(newInvoice.getInvoiceId());
                        invoiceIdParam.setOldInvoiceId(invoiceVo.getInvoiceId());
                        moveInvoiceDao.updateInvoiceId4ShipmentInvoice(context, invoiceIdParam);
                        log.info("Update Shipment Invoice: change invoice id from " + invoiceIdParam.getOldInvoiceId() + " to " + invoiceIdParam.getNewInvoiceId());
                        // Delete invoice with id.
                        invoiceDao.deleteById(context, invoiceIdParam.getOldInvoiceId());
                        log.info("Delete Invoice with invoiceid = " + invoiceIdParam.getOldInvoiceId());
                        // Update customer activation date.
                        IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                        manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoiceVo.getCustomerCode()), sessionClient);
                        manageCustomerService.updateCustomerActivationDate(context, String.valueOf(newInvoice.getCustomerCode()), sessionClient);
                    } else {
                        // New invoice code not existed.
                        // Update invoice code and invoice date.
                        newInvoice = new InvoiceVo();
                        newInvoice.setInvoiceId(invoiceVo.getInvoiceId());
                        newInvoice.setInvoiceCode(newInvoiceCode);
                        newInvoice.setInvoiceDate(filter.getToDate());
                        invoiceDao.update(context, newInvoice);
                        // Update customer activation date.
                        IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                        manageCustomerService.updateCustomerActivationDate(context, String.valueOf(invoiceVo.getCustomerCode()), sessionClient);
                        log.info("Update Invoice with invoiceid = " + newInvoice.getInvoiceId());
                    }
                    count++;
                }
            }
            sessionClient.endTransaction();
            return count;
        } catch (DaoException e) {
            sessionClient.rollback();
            throw e;
        }
    }
}
