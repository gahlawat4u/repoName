package com.gms.xms.persistence.dao.customeraging;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableTaskDaoService;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAgingTaskDaoService
 * <p>
 * Author DatTV Date Aug 13, 2015 11:11:11 AM
 */
public class CustomerAgingTaskDaoService {

    private static final Log log = LogFactory.getLog(FranchisePayableTaskDaoService.class);

    private CustomerAgingTaskDao customerAgingTaskDao;

    // Prepare data for customer aging report.
    public void prepareDataForCustomerAgingReport(Map<String, String> context, CustomerAgingFilter filter) throws DaoException {
        // SqlSessionClient sessionClient = new SqlSessionClient();
        // sessionClient.startTransaction();
        // customerAgingTaskDao = new CustomerAgingTaskDao(sessionClient);
        customerAgingTaskDao = new CustomerAgingTaskDao();
        try {
            customerAgingTaskDao.prepareDataForCustomerAgingInvoice(context, filter);
            makeConditionCheckRange(filter);
            customerAgingTaskDao.prepareDataForCustomerAging(context, filter);
            // sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            // sessionClient.rollback();
            throw ex;
        }
    }

    private void makeConditionCheckRange(CustomerAgingFilter filter)
    {
        Integer bot=filter.getMinInvoiceAge();
        Integer top=filter.getMaxInvoiceAge();
        Integer botIndex=0;
        Integer topIndex=0;
        if((bot==null && top==null) || (bot!=null && bot==0 && top!=null && top>121))
        {
            filter.setFilterDateInvoiceAgeRange("1=1");
            return;
        }
        bot= bot==null?0:bot;
        top= top==null?121:top;

        List<RangeAging> rangeAgings = RangeAging.getAll();
        for(int i=0; i<rangeAgings.size(); i++){
            RangeAging rangeAging = rangeAgings.get(i);
            if(bot >= rangeAging.getBot() && bot <= rangeAging.getTop()){
                botIndex = i;
            }
            if(top >= rangeAging.getBot() && top <= rangeAging.getTop()){
                topIndex = i;
            }
        }

        String rangeCondition = "";
        topIndex = top>120?rangeAgings.size()-1:topIndex;
        for(int j = botIndex; j<=topIndex; j++)
        {
            rangeCondition += rangeAgings.get(j).getName() + " or ";
        }
        if(top>120)
        {
            rangeCondition += " range_120 > 0 ";
        }
        int length = rangeCondition.length();
        if(rangeCondition.substring(length -3, length-1).equals("or"))
            rangeCondition = rangeCondition.substring(0, length-3);
        filter.setFilterDateInvoiceAgeRange(rangeCondition);
    }

}
