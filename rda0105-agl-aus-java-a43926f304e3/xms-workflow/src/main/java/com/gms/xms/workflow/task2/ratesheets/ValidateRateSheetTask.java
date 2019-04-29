package com.gms.xms.workflow.task2.ratesheets;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.admin.ratesheets.ImportRateSheetFilter;
import com.gms.xms.persistence.dao.ratesheet.ImportRateSheetDao;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

/**
 * Posted from May 6, 2016 3:38:41 PM
 * <p>
 * Author huynd
 */
public class ValidateRateSheetTask implements Task2 {
    private static final Log log = LogFactory.getLog(ValidateRateSheetTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Check sheet name.
            ImportRateSheetFilter filter = new ImportRateSheetFilter();
            filter.setShipmentTypeId(context.getLong(Attributes.SHIPMENT_TYPE_ID));
            filter.setSheetName(context.getString(Attributes.SHEET_NAME));
            filter.setCarrierCost(context.getInt(Attributes.CARRIER_COST));
            filter.setIsPerWeight(context.getInt(Attributes.PER_WEIGHT));
            ImportRateSheetDao dao = new ImportRateSheetDao();
            Long countRateSheet = dao.countRateSheetNameByFilter(filter);
            if (countRateSheet > 0) {
                throw new Exception("Duplicate Name!");
            }
            // Check duplicate rate sheet.
            ImportRateSheetFilter filterDuplicate = new ImportRateSheetFilter();
            filterDuplicate.setShipmentTypeId(context.getLong(Attributes.SHIPMENT_TYPE_ID));
            filterDuplicate.setSheetName(context.getString(Attributes.SHEET_NAME));
            filterDuplicate.setCarrierCost(context.getInt(Attributes.CARRIER_COST));
            filterDuplicate.setContent(context.getInt(Attributes.CONTENT));
            filterDuplicate.setBound(context.getInt(Attributes.BOUND));
            ImportRateSheetDao daoDuplicate = new ImportRateSheetDao();
            Long countDuplicate = daoDuplicate.countRateSheetDuplicate(filterDuplicate);
            if (countDuplicate > 0) {
                throw new Exception("Import rate sheet already exists.");
            }
            // Check rate sheet file
            String filePath = context.getString(Attributes.RATE_SHEET_FILE);
            File fs = new File(filePath);
            if (filePath == null || !fs.exists()) {
                throw new Exception("Please upload rate sheet data before Saving.");
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
