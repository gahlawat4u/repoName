package com.gms.xms.workflow.task2.webship.history.tnt;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.model.webship.history.HistoryDetailShipmentModel;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.tntconnote.ITntConnoteService;
import com.gms.xms.persistence.service.tntconnote.TntConnoteServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.tnt.TntDomConnoteShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.HistoryHelperUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from PrepareDataViewManifestDomTask
 * <p>
 * Author TANDT
 */
public class PrepareDataViewManifestNewStyleDomTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataViewManifestNewStyleDomTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            int ttlPieces = 0;
            Double gttlWeight = 0d;
            Double gttlVol = 0d;
            HistoryViewFileRequest request = context.get(Attributes.HISTORY_VIEW_AIRBILL_REQUEST);
            IShipmentService shipmentService = new ShipmentServiceImp();
            PieceDao pieceDao = new PieceDao();

            Long mainShipmentId = request.getShipmentId();

            ITntConnoteService tntConnoteService = new TntConnoteServiceImp();
            List<TntDomConnoteShipmentInfoVo> tntDomConnoteShipmentInfoVos = tntConnoteService.selectTntConnoteSameInfoDay(mainShipmentId);
            Long manifestNo;
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentInfoManifestVo shipmentMainInfoManifestVo = shipmentService.getShipmentsInfoManifest(mainShipmentId);


            Map<String, Object> data = new HashMap<>();
            data = getShipmentInfo(mainShipmentId);

            HistoryDetailInfoModel historyDetailInfoModel = (HistoryDetailInfoModel) data.get("detailInfoModel");
            List<HistoryDetailShipmentModel> historyDetailShipmentModels = new ArrayList<>();
            List<Long> shipmentIds = new ArrayList<>();

            for (TntDomConnoteShipmentInfoVo tntDomConnoteShipmentInfoVo : tntDomConnoteShipmentInfoVos) {
                manifestNo = tntDomConnoteShipmentInfoVo.getWmanifestId();
                data.put("currentDate", tntDomConnoteShipmentInfoVo.getManifestDate());
                data.put("currentTime", tntDomConnoteShipmentInfoVo.getManifestTime());
                shipmentIds.add(tntDomConnoteShipmentInfoVo.getShipmentId());
            }
            CustomerMinimumInfoManifestVo customerMinimumInfoManifestVo = shipmentDao.selectCustomerMinimumInfoByShipmentId(mainShipmentId);


            if (shipmentIds.size() == 0) {
                shipmentIds.add(mainShipmentId);
            }
            for (Long shipmentId : shipmentIds) {
                HistoryDetailShipmentModel historyDetailShipmentModel = new HistoryDetailShipmentModel();
                ShipmentInfoManifestVo shipmentInfoManifestVo = shipmentService.getShipmentsInfoManifest(shipmentId);
                AddressDao addressDao = new AddressDao();
                ShipmentAddressVo senderVo = addressDao.getShipmentAddressById(shipmentInfoManifestVo.getSenderAddressId());

                historyDetailInfoModel.setsCompanyName(StringEscapeUtils.escapeXml(senderVo.getCompanyName()));
                historyDetailInfoModel.setsAddress(StringEscapeUtils.escapeXml(senderVo.getAddress()));
                historyDetailInfoModel.setsCity(senderVo.getCity());
                historyDetailInfoModel.setsContactName(StringEscapeUtils.escapeXml(senderVo.getContactName()));
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

                ttlPieces += shipmentInfoManifestVo.getNoOfPieces();
                String shipmentDate = DateUtils.convertDateToString(shipmentInfoManifestVo.getShipmentDate(), "dd-MM-yyyy"
                        , SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));
                historyDetailInfoModel.setShipmentDate(shipmentDate);
                Double totalWeight = shipmentDao.getWeightByShipmentId(shipmentId);
                if (totalWeight == null) {
                    totalWeight = 0d;
                }
                gttlWeight += totalWeight;
                ShipmentAddressVo receiverVo = addressDao.getShipmentAddressById(shipmentInfoManifestVo.getReceiverAddressId());
                historyDetailInfoModel.setrCompanyName(StringEscapeUtils.escapeXml(receiverVo.getCompanyName()));
                historyDetailInfoModel.setrCity(receiverVo.getCity());
                historyDetailInfoModel.setrContactName(StringEscapeUtils.escapeXml(receiverVo.getContactName()));
                historyDetailInfoModel.setrState(receiverVo.getState());
                historyDetailInfoModel.setrPostalCode(receiverVo.getPostalCode());
                historyDetailInfoModel.setsPhone(senderVo.getPhone());
                List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentId);
                Double ttlVol = 0d;
                for (PieceVo pieceVo : pieceVos) {
                    Double l = pieceVo.getDimensionL();
                    Double w = pieceVo.getDimensionW();
                    Double h = pieceVo.getDimensionH();
                    Double vol = 0.000001 * l * h * w;
                    ttlVol += vol;
                }
                gttlVol += ttlVol;
                String ttlVolS = AppUtils.formatNumber(String.valueOf(ttlVol), 3);
                String courierMessage = shipmentInfoManifestVo.getCourierMessage();
                historyDetailShipmentModel.setIsDg(courierMessage.equals("DG") ? "Y" : "N");
                historyDetailShipmentModel.setConsignmentNo(shipmentInfoManifestVo.getAirbillNumber());
                historyDetailShipmentModel.setSenderRef(StringEscapeUtils.escapeXml(shipmentInfoManifestVo.getReference()));
                historyDetailShipmentModel.setReceiverName(StringEscapeUtils.escapeXml(receiverVo.getCompanyName()));
                String destination = String.format("%s %s %s",
                        receiverVo.getCity(), receiverVo.getState(), receiverVo.getPostalCode());
                historyDetailShipmentModel.setDestination(destination);
                historyDetailShipmentModel.setItems(shipmentInfoManifestVo.getNoOfPieces());
                historyDetailShipmentModel.setWeight(MathUtils.formatWeightStandard(totalWeight));
                historyDetailShipmentModel.setCubic(ttlVolS);
                historyDetailShipmentModels.add(historyDetailShipmentModel);
            }
            historyDetailInfoModel.setNoOfPieces(String.valueOf(ttlPieces));
            historyDetailInfoModel.setActualWeight(MathUtils.formatWeightStandard(gttlWeight));
            historyDetailInfoModel.setDimWeight(AppUtils.formatNumber(String.valueOf(gttlVol), 3));
            historyDetailInfoModel.setContentDescription(StringEscapeUtils.escapeXml(historyDetailInfoModel.getContentDescription()));
            historyDetailInfoModel.setReferenceNo(StringEscapeUtils.escapeXml(historyDetailInfoModel.getReferenceNo()));

            data.put("listPieceGrouped", historyDetailShipmentModels);

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
}