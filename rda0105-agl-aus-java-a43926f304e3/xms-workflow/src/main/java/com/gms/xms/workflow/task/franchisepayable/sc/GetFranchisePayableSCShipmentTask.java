package com.gms.xms.workflow.task.franchisepayable.sc;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.franchisepayable.sc.FranchisePayableSCDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCShipmentTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCShipmentVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.NullFranchisePayableSCShipmentTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.NullFranchisePayableSCShipmentVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetFranchisePayableSCShipmentTask
 * <p>
 * Author DatTV Oct 29, 2015
 */
public class GetFranchisePayableSCShipmentTask implements Task {
    private static final Log log = LogFactory.getLog(GetFranchisePayableSCShipmentTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchisePayableSCDaoService fpbSCDao = new FranchisePayableSCDaoService();
        // Get the filter from the context.
        FranchisePayableFilter filter = GsonUtils.fromGson(context.get(Attributes.FRANCHISE_PAYABLE_FILTER), FranchisePayableFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for Franchise Payable: Shipment Detail
            List<FranchisePayableSCShipmentVo> result = fpbSCDao.getShipmentDetails(filter);

            // Puts result into the context
            context.put(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_RESULT, GsonUtils.toGson(result));

            // Do DAO service to get Shipment Details records count.
            Long recordCount = fpbSCDao.getShipmentDetailsRecordCount(filter);

            // Puts it into the context.
            context.put(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_RECORD_COUNT_RESULT, String.valueOf(recordCount));

            // Check if it's the final page then we must get summary record.
            Integer startRecord = filter.getStartRecord();
            Integer recordSize = filter.getRecordSize();
            if ((startRecord != null && recordSize != null && (recordCount - startRecord) <= recordSize) || (startRecord == null || recordSize == null)) {
                // Get summary record of taxable shipments.
                FranchisePayableSCShipmentVo taxableTotal = fpbSCDao.getTaxableShipmentDetailsTotal(filter);
                if (taxableTotal == null) {
                    taxableTotal = new NullFranchisePayableSCShipmentVo();
                }

                // Get summary record of non-taxable shipments.
                FranchisePayableSCShipmentVo nonTaxableTotal = fpbSCDao.getNonTaxableShipmentDetailsTotal(filter);
                if (nonTaxableTotal == null) {
                    nonTaxableTotal = new NullFranchisePayableSCShipmentVo();
                }

                // Get summary record.
                FranchisePayableSCShipmentTotalVo totalResult = fpbSCDao.getShipmentDetailsTotal(filter);
                if (totalResult == null) {
                    totalResult = new NullFranchisePayableSCShipmentTotalVo();
                }

                // Puts it into the context.
                context.put(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_TAXABLE_TOTAL_RESULT, GsonUtils.toGson(taxableTotal));
                context.put(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_NON_TAXABLE_TOTAL_RESULT, GsonUtils.toGson(nonTaxableTotal));
                context.put(Attributes.FRANCHISE_PAYABLE_SHIPMENT_DETAILS_TOTAL_RESULT, GsonUtils.toGson(totalResult));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }

}
