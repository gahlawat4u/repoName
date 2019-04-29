package com.gms.xms.weblib.controller.admin.payment;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.BankModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.BankDao;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.InvoicePaymentDao;
import com.gms.xms.persistence.dao.InvoicePaymentDetailDao;
import com.gms.xms.persistence.service.payment.IPaymentService;
import com.gms.xms.persistence.service.payment.PaymentServiceImp;
import com.gms.xms.txndb.model.admin.payment.AddPaymentModel;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.payment.AddPaymentVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from Apr 22, 2016 3:59:51 PM
 * <p>
 * Author dattrinh
 */
public class AddPaymentController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String invoicePaymentId;
    private List<BankModel> bankList;
    private InvoicePaymentVo invoicePayment;
    private AddPaymentModel payment;

    public String show() {
        try {
            validInvoicePayment();
            prepareBankList();
            preparePayment();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Check null.
            if (this.getPayment() == null) {
                throw new CustomException("No payment for adding.");
            }
            // Check valid.
            if (!isValidPayment(this.getPayment())) {
                setErrorCode(ErrorCode.FIELD_ERROR);
                prepareBankList();
                return "input";
            } else {
                AddPaymentVo paymentVo = ModelUtils.createVoFromModel(this.getPayment(), AddPaymentVo.class);
                checkAndAddPayment(paymentVo);
                addActionMessage(this.getLocalizationText("Add payment successfull."));
                return "success";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "success";
        }
    }

    protected void validInvoicePayment() throws Exception {
        if (StringUtils.isBlank(this.getInvoicePaymentId())) {
            throw new CustomException("Please input invoice payment id.");
        }
        long invoicePaymentId;
        try {
            invoicePaymentId = Long.valueOf(this.getInvoicePaymentId());
        } catch (Exception e) {
            throw new CustomException("Invalid invoice payment id.");
        }
        InvoicePaymentVo invoicePaymentVo = getInvoicePaymentById(invoicePaymentId);
        if (invoicePaymentVo == null) {
            throw new CustomException("This invoice payment doesn't exists.");
        }
        if (invoicePaymentVo.getCanReverse() == 0) {
            throw new CustomException("This invoice payment cannot reverse.");
        }
        this.setInvoicePayment(invoicePaymentVo);
    }

    protected void preparePayment() throws Exception {
        // Get invoice info from the invoice payment.
        InvoiceDao invoiceDao = new InvoiceDao();
        InvoiceVo invoiceVo = invoiceDao.selectById(this.getInvoicePayment().getInvoiceId());
        // Prepare add payment model.
        AddPaymentVo paymentVo = new AddPaymentVo();
        paymentVo.setCustomerCode(invoiceVo.getCustomerCode());
        paymentVo.setAmount(this.getInvoicePayment().getAmount().negate());
        paymentVo.setCheque("NSF of cheque");
        paymentVo.setBankId(0L);
        paymentVo.setPaymentDate(new Date());
        paymentVo.setPaymentType(0);
        paymentVo.setInvoiceCode(invoiceVo.getInvoiceCode());
        paymentVo.setNote("");
        paymentVo.setInvoicePaymentId(this.getInvoicePayment().getInvoicePaymentId());

        // Convert 2 model.
        AddPaymentModel paymentModel = ModelUtils.createModelFromVo(paymentVo, AddPaymentModel.class);
        this.setPayment(paymentModel);
    }

    protected boolean isValidPayment(AddPaymentModel payment) {
        if (StringUtils.isBlank(payment.getInvoicePaymentId())) {
            addFieldError("payment.invoicePaymentId", "No invoice payment id");
        }
        if (StringUtils.isBlank(payment.getPaymentDate())) {
            addFieldError("payment.paymentDate", "Payment date cannot be empty");
        } else {
            Date paymentDate = null;
            try {
                paymentDate = DateUtils.convertStringToDate(this.getPayment().getPaymentDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            } catch (Exception e) {
            }
            if (paymentDate == null) {
                addFieldError("payment.paymentDate", "Invalid payment date");
            }
        }
        if (StringUtils.isBlank(this.getPayment().getCheque())) {
            addFieldError("payment.cheque", "Cheque cannot be empty");
        }
        if (StringUtils.isBlank(this.getPayment().getAmount())) {
            addFieldError("payment.amount", "Amount cannot be empty");
        }
        return !hasFieldErrors();
    }

    protected void checkAndAddPayment(AddPaymentVo paymentVo) throws Exception {
        InvoicePaymentDetailDao invoicePaymentDetailDao = new InvoicePaymentDetailDao();
        InvoicePaymentDao invoicePaymentDao = new InvoicePaymentDao();
        InvoiceDao invoiceDao = new InvoiceDao();
        Long invoicePaymentId = paymentVo.getInvoicePaymentId();
        // Get invoice payment info.
        InvoicePaymentVo invoicePaymentVo = invoicePaymentDao.getInvoicePaymentById(invoicePaymentId);
        // Check amount of payment and invoice payment.
        BigDecimal paymentAmount = paymentVo.getAmount().abs();
        if (paymentAmount.compareTo(invoicePaymentVo.getAmount()) != 0) {
            throw new CustomException("The amount of this invoice payment have changed or the posted amount is invalid, please check.");
        }
        // Get invoice info from the invoice payment.
        InvoiceVo invoiceVo = invoiceDao.selectById(invoicePaymentVo.getInvoiceId());
        // Create new customer payment.
        CustomerPaymentVo customerPaymentVo = new CustomerPaymentVo();
        customerPaymentVo.setCustomerCode(invoiceVo.getCustomerCode());
        customerPaymentVo.setAmount(paymentVo.getAmount());
        customerPaymentVo.setCheque("NSF of cheque");
        customerPaymentVo.setBankId(paymentVo.getBankId());
        customerPaymentVo.setPaymentDate(paymentVo.getPaymentDate());
        customerPaymentVo.setPaymentType(0);
        // Create new invoice payment.
        InvoicePaymentVo newInvoicePaymentVo = new InvoicePaymentVo();
        newInvoicePaymentVo.setInvoiceId(invoiceVo.getInvoiceId());
        newInvoicePaymentVo.setAmount(paymentVo.getAmount());
        newInvoicePaymentVo.setLateFee(BigDecimal.ZERO);
        newInvoicePaymentVo.setReverseFlag((byte) 0);
        newInvoicePaymentVo.setRevInvoicePaymentId(invoicePaymentId);
        newInvoicePaymentVo.setApplyDate(paymentVo.getPaymentDate());
        // Create invoice payment detail from detail of old invoice payment.
        List<InvoicePaymentDetailVo> invoicePaymentDetailVos = invoicePaymentDetailDao.selectByInvoicePayemntId(invoicePaymentId);
        if (invoicePaymentDetailVos == null || invoicePaymentDetailVos.size() == 0) {
            throw new CustomException("This invoice payment hasn't been got invoice payment details. Please reconcile it first.");
        }
        newInvoicePaymentVo.setInvoicePaymentDetails(new ArrayList<InvoicePaymentDetailVo>());
        for (InvoicePaymentDetailVo invoicePaymentDetailVo : invoicePaymentDetailVos) {
            // Clean old invoice payment id.
            invoicePaymentDetailVo.setInvoicePaymentId(null);
            // Reverse amount.
            invoicePaymentDetailVo.setAmount(invoicePaymentDetailVo.getAmount().negate());
            newInvoicePaymentVo.getInvoicePaymentDetails().add(invoicePaymentDetailVo);
        }
        customerPaymentVo.setInvoicePayments(new ArrayList<InvoicePaymentVo>());
        customerPaymentVo.getInvoicePayments().add(newInvoicePaymentVo);
        // Create new note.
        NoteVo noteVo = new NoteVo();
        Long userId = Long.valueOf((String) this.getSession("SESS_XMS_ADMIN_ID"));
        noteVo.setUserId(userId);
        noteVo.setAccountNo(invoiceVo.getCustomerCode());
        String code = String.valueOf(invoiceVo.getCustomerCode());
        Byte noteType = 0;
        if (code.length() >= 5 && code.endsWith("00001")) {
            noteType = 2;
        } else {
            noteType = 1;
        }
        noteVo.setNoteType(noteType); // Note Type: 0 - contact, 1 - customer, 2
        // - franchise
        noteVo.setNote(paymentVo.getNote() == null ? "" : paymentVo.getNote());
        noteVo.setModifyDate(new Date());
        noteVo.setCheck(false);
        noteVo.setCurrent((byte) 0);
        noteVo.setInvoiceCode(invoiceVo.getInvoiceCode());
        // Save to database.
        IPaymentService paymentService = new PaymentServiceImp();
        paymentService.savePayment(this.getAddInfoMap(), customerPaymentVo, noteVo);
    }

    protected InvoicePaymentVo getInvoicePaymentById(long invoicePaymentId) throws DaoException {
        InvoicePaymentDao dao = new InvoicePaymentDao();
        InvoicePaymentVo invoicePaymentVo = dao.getInvoicePaymentById(invoicePaymentId);
        return invoicePaymentVo;
    }

    protected void prepareBankList() throws Exception {
        BankDao bankDao = new BankDao();
        List<BankVo> bankVos = bankDao.getAll();
        List<BankModel> bankModels = ModelUtils.createListModelFromVo(bankVos, BankModel.class);
        this.setBankList(bankModels);
    }

    public List<BankModel> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankModel> bankList) {
        this.bankList = bankList;
    }

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    protected InvoicePaymentVo getInvoicePayment() {
        return invoicePayment;
    }

    protected void setInvoicePayment(InvoicePaymentVo invoicePayment) {
        this.invoicePayment = invoicePayment;
    }

    public AddPaymentModel getPayment() {
        return payment;
    }

    public void setPayment(AddPaymentModel payment) {
        this.payment = payment;
    }
}
