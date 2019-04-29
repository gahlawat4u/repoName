package com.gms.xms.workflow.task2.webship.history.toll;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryDetailTollManifestModel;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.webship.TollManifestDao;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.toll.TollManifestVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from PrepareDataViewManifestPriorityTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewManifestPriorityNewStyleTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewManifestPriorityNewStyleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            int ttlPieces = 0;
            Double gttlWeight = 0d;
            Double gttlVol = 0d;
            String manifestIdentifier = "";
            String dispatchDate="";
            String customerCode="";
            String senderInfo="";
            Integer forceVolWeight = ShipmentUtils.getForceVolWeight(52);
            int conNotes = 0;
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            IShipmentService shipmentService = new ShipmentServiceImp();
            PieceDao pieceDao = new PieceDao();
            Long mainShipmentId = request.getShipmentId();

            TollManifestDao tollManifestDao = new TollManifestDao();
            TollManifestVo manifestByMainShipment = tollManifestDao.getManifestByMainShipment(mainShipmentId.toString());
            List<String> shipmentIds = new ArrayList<>();
            String shipmentIdStr = null;
            if (manifestByMainShipment == null) {
                shipmentIds.add(String.valueOf(mainShipmentId));
                manifestIdentifier = AppUtils.leftPad("0", 5, "0".charAt(0));
            } else {
                shipmentIdStr = manifestByMainShipment.getShipmentId();
                shipmentIds = Arrays.asList(shipmentIdStr.split(","));
                manifestIdentifier = manifestByMainShipment.getManifestIdentifier().toPlainString();
                Date createDate = manifestByMainShipment.getCreateDate();
                dispatchDate = DateUtils.convertDateToString(createDate, "dd MMMM yyyy", SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));

            }


            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentInfoManifestVo shipmentMainInfoManifestVo = shipmentService.getShipmentsInfoManifest(mainShipmentId);

            Map<String, Object> data = getShipmentInfo(mainShipmentId);

            HistoryDetailInfoModel historyDetailInfoModel = (HistoryDetailInfoModel) data.get("detailInfoModel");
            customerCode = historyDetailInfoModel.getCustomerCode();

            List<HistoryDetailTollManifestModel> historyDetailShipmentModels = new ArrayList<>();

            CustomerMinimumInfoManifestVo customerMinimumInfoManifestVo = shipmentDao.selectCustomerMinimumInfoByShipmentId(mainShipmentId);

            for (String shipmentId : shipmentIds) {
                Long shipmentIdL = Long.valueOf(shipmentId);
                HistoryDetailTollManifestModel historyDetailShipmentModel = new HistoryDetailTollManifestModel();
                ShipmentInfoManifestVo shipmentInfoManifestVo = shipmentService.getShipmentsInfoManifest(shipmentIdL);
                if(shipmentInfoManifestVo.getStatus()==1)
                {
                    continue;
                }
                AddressDao addressDao = new AddressDao();
                ShipmentAddressVo senderVo = addressDao.getShipmentAddressById(shipmentInfoManifestVo.getSenderAddressId());

                historyDetailInfoModel.setsCompanyName(senderVo.getCompanyName());
                historyDetailInfoModel.setsAddress(senderVo.getAddress());
                historyDetailInfoModel.setsCity(senderVo.getCity());
                historyDetailInfoModel.setsContactName(senderVo.getContactName());
                historyDetailInfoModel.setsPhone(senderVo.getPhone());

                if (customerMinimumInfoManifestVo.getCustomerCode() != null && customerMinimumInfoManifestVo.getPostalCode() != null) {
                    if (!customerMinimumInfoManifestVo.getCustomerCode().equals(shipmentInfoManifestVo.getCustomerCode())
                            && !customerMinimumInfoManifestVo.getPostalCode().equals(shipmentInfoManifestVo.getPostalCode())) {
                        continue;
                    }
                }
                if (!shipmentMainInfoManifestVo.getShipmentTypeId().equals(shipmentInfoManifestVo.getShipmentTypeId())) {
                    continue;
                }
                conNotes += 1;
                ttlPieces += shipmentInfoManifestVo.getNoOfPieces();
                String shipmentDate = DateUtils.convertDateToString(shipmentInfoManifestVo.getShipmentDate(), "dd-MM-yyyy"
                        , SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));
                historyDetailInfoModel.setShipmentDate(shipmentDate);
                Double totalWeight = shipmentDao.getWeightByShipmentId(shipmentIdL);
                gttlWeight += totalWeight;
                ShipmentAddressVo receiverVo = addressDao.getShipmentAddressById(shipmentInfoManifestVo.getReceiverAddressId());
                historyDetailInfoModel.setrCompanyName(receiverVo.getCompanyName());
                historyDetailInfoModel.setrCity(receiverVo.getCity());
                historyDetailInfoModel.setrState(receiverVo.getState());
                historyDetailInfoModel.setrPostalCode(receiverVo.getPostalCode());
                historyDetailInfoModel.setsPhone(senderVo.getPhone());
                List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentIdL);
                Double ttlVol = 0d;
                for (PieceVo pieceVo : pieceVos) {
                    Double l = pieceVo.getDimensionL();
                    Double w = pieceVo.getDimensionW();
                    Double h = pieceVo.getDimensionH();
                    Double vol = l * h * w/forceVolWeight;
                    ttlVol += vol;
                }
                gttlVol += ttlVol;
                String ttlVolS = AppUtils.formatNumber(String.valueOf(ttlVol), 2);
                String courierMessage = shipmentInfoManifestVo.getCourierMessage();
                historyDetailShipmentModel.setIsDg(courierMessage.equals("DG") ? "Y" : "N");
                historyDetailShipmentModel.setConnoteNumber(shipmentInfoManifestVo.getAirbillNumber());
                historyDetailShipmentModel.setReferenceNo(shipmentInfoManifestVo.getReference());
                historyDetailShipmentModel.setsCompanyName(senderVo.getCompanyName());
                historyDetailShipmentModel.setrCity(receiverVo.getCity());
                historyDetailShipmentModel.setrCompanyName(receiverVo.getCompanyName());
                historyDetailShipmentModel.setrPostalCode(receiverVo.getPostalCode());
                historyDetailShipmentModel.setNoOfPieces(shipmentInfoManifestVo.getNoOfPieces());
                historyDetailShipmentModel.setCubicWeight(ttlVolS);
                historyDetailShipmentModel.setDeadWeight(MathUtils.formatWeightStandard(totalWeight));
                senderInfo = senderVo.getCity()+ "," + senderVo.getPostalCode();
                historyDetailShipmentModels.add(historyDetailShipmentModel);
            }

            historyDetailInfoModel.setNoOfPieces(String.valueOf(ttlPieces));
            historyDetailInfoModel.setActualWeight(MathUtils.formatWeightStandard(gttlWeight));
            historyDetailInfoModel.setDimWeight(AppUtils.formatNumber(String.valueOf(gttlVol), 3));

            data.put("shipments", historyDetailShipmentModels);
            data.put("manifestIdentifier", manifestIdentifier);
            data.put("shipCount", conNotes);
            data.put("dispatchDate", dispatchDate);
            data.put("customerCode", customerCode);
            data.put("senderInfo", senderInfo);

            context.put(Attributes.VIEW_AIRBILL_DATA, data);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private Map<String, Object> getShipmentInfo(Long shipmentId) throws Exception {
        HistoryDetailFilter filter = HistoryHelperUtils.prepareHistoryDetailFilter(shipmentId);
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoVo historyDetailInfo = getHistoryDetailInfoVo(filter, detailService, shipmentId);
        CustomerAddressModel customerAddress = getCustomerAddressModel(Long.valueOf(historyDetailInfo.getCustomerCode()));
        DateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();

        String currentTimeOutput = DateUtils.convertDateToString(currentDate, "hh:mm", SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));

        HistoryDetailInfoModel historyDetailInfoModel = ModelUtils.createModelFromVo(historyDetailInfo, HistoryDetailInfoModel.class);

        String isDG = "N";
        if (historyDetailInfoModel.getCourierMessage().equals("DG")) {
            isDG = "Y";
        }

        Map<String, Object> data = new HashMap<>();
        data.put("detailInfoModel", historyDetailInfoModel);
        data.put("isDG", isDG);
        data.put("customerAddress", customerAddress);
        data.put("currentDate", String.valueOf(outFormat.format(currentDate)));
        data.put("currentTime", currentTimeOutput);
        return data;
    }

    private HistoryDetailInfoVo getHistoryDetailInfoVo(HistoryDetailFilter filter, IHistoryDetailService detailService, Long shipmentId) throws Exception {
        HistoryDetailInfoVo historyDetailInfo = detailService.selectHistoryDetailInfo(filter);

        String[] serviceCodeArr = historyDetailInfo.getServiceCode().split(",");
        String serviceCode = serviceCodeArr[0];
        historyDetailInfo.setServiceCode(serviceCode);
        historyDetailInfo.setShipmentId(shipmentId);

        if (historyDetailInfo.getCourierMessage() == null) {
            historyDetailInfo.setCourierMessage("");
        }
        return historyDetailInfo;
    }

    private CustomerAddressModel getCustomerAddressModel(Long customerCode) throws Exception {
        ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
        CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(customerCode);
        return ModelUtils.createModelFromVo(customerAddressVo, CustomerAddressModel.class);
    }

    protected HistoryDetailFilterModel prepareHistoryDetailFilter(String shipmentId) throws Exception {
        HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
        detailFilterModelN.setShipmentId(shipmentId);
        detailFilterModelN.setLbToKg("0.45359237");
        detailFilterModelN.setInToCm("2.54");
        detailFilterModelN.setWeightValue("5000");
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
        detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
        return detailFilterModelN;
    }
}
