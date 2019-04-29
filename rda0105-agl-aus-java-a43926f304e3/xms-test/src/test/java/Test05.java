import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.persistence.dao.AirbillPausingDeductDao;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.AirbillPausingDeductVo;
import com.gms.xms.txndb.vo.InvoiceVo;

import java.util.Calendar;
import java.util.Date;

public class Test05 {
    public static void main(String[] args) throws DaoException {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        AirbillPausingDeductDao pausingDeductDao = new AirbillPausingDeductDao();
        AirbillAdjustmentVo orgAdjustmentVo = adjustmentDao.selectById(16603L);
        AirbillPausingDeductVo airbillPausingDeductVo = pausingDeductDao.selectByAirbillNumber(orgAdjustmentVo.getAirbillNumber());
        Date startPausingDate = orgAdjustmentVo.getStartPausingDate();
        InvoiceDao invoiceDao = new InvoiceDao();
        InvoiceVo invoiceVo = invoiceDao.getInvoiceByAirbill(orgAdjustmentVo);
        Calendar cal = Calendar.getInstance();
        cal.setTime(invoiceVo.getInvoiceDate());
        cal.add(Calendar.DAY_OF_YEAR, airbillPausingDeductVo.getPausingDay().intValue() + 60);
        System.out.println("Invoice Date: " + DateUtils.convertDateToString(invoiceVo.getInvoiceDate(), "yyyy-MM-dd", null));
        System.out.println("Start Pausing Date: " + DateUtils.convertDateToString(startPausingDate, "yyyy-MM-dd", null));
        System.out.println("Deduct date: " + DateUtils.convertDateToString(cal.getTime(), "yyyy-MM-dd", null));
        System.out.println("Period: " + String.valueOf(airbillPausingDeductVo.getPausingDay().intValue() + 60));
        if (startPausingDate.after(cal.getTime())) {
            System.out.println(startPausingDate + " is after " + cal.getTime());
        } else {
            System.out.println(startPausingDate + " is before " + cal.getTime());
        }
    }
}
