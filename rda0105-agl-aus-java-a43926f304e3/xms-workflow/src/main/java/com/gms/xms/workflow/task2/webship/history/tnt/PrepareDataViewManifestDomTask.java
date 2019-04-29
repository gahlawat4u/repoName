package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryDetailPieceModel;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailPieceVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from PrepareDataViewManifestDomTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewManifestDomTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewManifestDomTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            Long shipmentId = request.getShipmentId();

            HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(shipmentId);
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoVo historyDetailInfo = detailService.selectHistoryDetailInfo(filter);
            String[] serviceCodeArr = historyDetailInfo.getServiceCode().split(",");
            String serviceCode = serviceCodeArr[0];
            historyDetailInfo.setServiceCode(serviceCode);
            historyDetailInfo.setShipmentId(request.getShipmentId());
            List<PieceVo> pieces = detailService.selectPieceByIdNonGroup(shipmentId);
            List<HistoryDetailPieceVo> detailPieces = detailService.selectPieceInfo(filter, true);

            if (historyDetailInfo.getCourierMessage() == null) {
                historyDetailInfo.setCourierMessage("");
            }

            historyDetailInfo.setNoOfPieces(pieces.size());

            if (pieces.size() <= 0) {
                historyDetailInfo.setNoOfPieces(0);
                if (historyDetailInfo.getDimWeight() == null) {
                    historyDetailInfo.setDimWeight("0");
                }
            }
            DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();

            String currentTimeOutput = DateUtils.convertDateToString(currentDate, "hh:mm", SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));

            HistoryDetailInfoModel historyDetailInfoModel = ModelUtils.createModelFromVo(historyDetailInfo, HistoryDetailInfoModel.class);
            if (historyDetailInfoModel.getContents().equals("1")) {
                historyDetailInfoModel.setContents("N");
            } else {
                historyDetailInfoModel.setContents("D");
            }
            ICustomerService customerService = new CustomerServiceImp();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerService.selectByCode(historyDetailInfo.getCustomerCode());
            CustomerModel customerModel = new CustomerModel();
            if (customerVo != null) {
                customerModel = ModelUtils.createModelFromVo(customerVo, CustomerModel.class);
            }

            ManageFranchiseFilter manageFranchiseFilter = new ManageFranchiseFilter();
            manageFranchiseFilter.setFranchiseCode(Long.parseLong(historyDetailInfo.getCustomerCode()));

            IFranchiseService franchiseService = new FranchiseServiceImp();

            FranchiseVo franchiseVo = franchiseService.selectFranchiseByFilter(manageFranchiseFilter);
            FranchiseModel franchiseModel = new FranchiseModel();
            if (franchiseVo != null) {
                franchiseModel = ModelUtils.createModelFromVo(franchiseVo, FranchiseModel.class);
            }

            String isDG = "N";
            if (historyDetailInfoModel.getCourierMessage().equals("DG")) {
                isDG = "Y";
            }
            ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
            CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(Long.parseLong(historyDetailInfo.getCustomerCode()));
            CustomerAddressModel customerAddress = ModelUtils.createModelFromVo(customerAddressVo, CustomerAddressModel.class);

            List<HistoryDetailPieceModel> listPieceGrouped = ModelUtils.createListModelFromVo(detailService.selectPieceInfo(filter, true), HistoryDetailPieceModel.class);

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("detailPieces", detailPieces);
            data.put("detailInfoModel", historyDetailInfoModel);
            data.put("pieces", pieces);
            data.put("customerModel", customerModel);
            data.put("isThermalLable", "0");
            data.put("isDG", isDG);
            data.put("customerAddress", customerAddress);
            data.put("listPieceGrouped", listPieceGrouped);
            data.put("franchiseModel", franchiseModel);
            data.put("currentDate", String.valueOf(outFormat.format(currentDate)));
            data.put("currentTime", currentTimeOutput);
            context.put(Attributes.VIEW_AIRBILL_DATA, data);

        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
