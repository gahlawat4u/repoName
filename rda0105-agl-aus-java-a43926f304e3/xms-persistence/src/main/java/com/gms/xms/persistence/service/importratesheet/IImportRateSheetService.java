package com.gms.xms.persistence.service.importratesheet;

import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.RateSheetRowModel;

import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 23, 2016 4:29:50 PM
 * <p>
 * Author huynd
 */
public interface IImportRateSheetService {

    public void saveImportRateSheet(Map<String, String> context, RateSheetModel rateSheet, List<RateSheetColumnModel> columnDataList, List<RateSheetRowModel> rowDataList, List<RateSheetDetailModel> detailDataList) throws Exception;

}
