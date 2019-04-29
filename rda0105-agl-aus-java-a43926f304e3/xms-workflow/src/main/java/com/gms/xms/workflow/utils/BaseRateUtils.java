package com.gms.xms.workflow.utils;

import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.CustomerBaseRateModel;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.admin.administration.RateSheetColModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.RateSheetColumnVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateExtVo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from BaseRateUtils
 * <p>
 * Author TANDT 28-10-2015
 */
public class BaseRateUtils {

    /**
     * Function Get a HashMap<ShipmentTypeModel, RateSheetColModel>
     *
     * @param List <ShipmentTypeModel>
     * @return HashMap<ShipmentTypeModel, RateSheetColModel>
     * @throws Exception
     */
    public static HashMap<String, RateSheetColModel> getBaseRateByShipmentTypes(List<ShipmentTypeModel> shipmentTypes, String profileId, String customerCode) throws Exception {
        HashMap<String, RateSheetColModel> rateSheetsN = new HashMap<String, RateSheetColModel>();
        if (shipmentTypes != null) {
            for (ShipmentTypeModel model : shipmentTypes) {
                String smTypeName = "";
                smTypeName = model.getShipmentTypeName();
                // check is document out = 1
                if (model.getDocument().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierDocumentRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierDocumentRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierDocumentRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierDocumentRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    CustomerProfileFilter filter = new CustomerProfileFilter();

                    if (StringUtils.isNotEmpty(profileId)) {
                        filter.setProfileId(Long.parseLong(profileId));
                    }
                    if (StringUtils.isNotEmpty(customerCode)) {
                        filter.setCustomerCode(Long.parseLong(customerCode));
                    }
                    filter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    filter.setContent(0);
                    filter.setBound(0);
                    List<CustomerBaseRateVo> baseRateDetailVos = new ArrayList<CustomerBaseRateVo>();
                    CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                    CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                    customerBaseRateFilter.setContent(0);
                    customerBaseRateFilter.setBound(0);
                    customerBaseRateFilter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    customerBaseRateFilter.setCustomerCode(Long.parseLong(customerCode));
                    baseRateDetailVos = customerBaseRateDao.getBaseRateDetailByFilter(customerBaseRateFilter);
                    if (baseRateDetailVos != null) {
                        rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVos, CustomerBaseRateModel.class));
                    }
                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Documents"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Documents"), rateSheetModel);
                        }
                    }

                }
                // check package out
                if (model.getPackage().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierPackageRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPackageRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }

                        }
                        if (!model.getCarrierPackagePerWeightRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPackagePerWeightRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierPackageRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierPackageRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                        if (!model.getNonCarrierPackagePerWeightRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierPackagePerWeightRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    CustomerProfileFilter filter = new CustomerProfileFilter();
                    if (StringUtils.isNotEmpty(profileId)) {
                        filter.setProfileId(Long.parseLong(profileId));
                    }
                    if (StringUtils.isNotEmpty(customerCode)) {
                        filter.setCustomerCode(Long.parseLong(customerCode));
                    }
                    filter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    filter.setContent(1);
                    filter.setBound(0);
                    List<CustomerBaseRateVo> baseRateDetailVos = new ArrayList<CustomerBaseRateVo>();
                    CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                    CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                    customerBaseRateFilter.setContent(1);
                    customerBaseRateFilter.setBound(0);
                    customerBaseRateFilter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    customerBaseRateFilter.setCustomerCode(Long.parseLong(customerCode));
                    baseRateDetailVos = customerBaseRateDao.getBaseRateDetailByFilter(customerBaseRateFilter);
                    if (baseRateDetailVos != null) {
                        rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVos, CustomerBaseRateModel.class));
                    }
                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Package"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Package"), rateSheetModel);
                        }
                    }
                }

                // check doc in
                if (model.getDocumentInbound().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierDocumentInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierDocumentInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierDocumentInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierDocumentInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    CustomerProfileFilter filter = new CustomerProfileFilter();
                    if (StringUtils.isNotEmpty(profileId)) {
                        filter.setProfileId(Long.parseLong(profileId));
                    }
                    if (StringUtils.isNotEmpty(customerCode)) {
                        filter.setCustomerCode(Long.parseLong(customerCode));
                    }
                    filter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    filter.setContent(0);
                    filter.setBound(1);
                    List<CustomerBaseRateVo> baseRateDetailVos = new ArrayList<CustomerBaseRateVo>();
                    CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                    CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                    customerBaseRateFilter.setContent(0);
                    customerBaseRateFilter.setBound(1);
                    customerBaseRateFilter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    customerBaseRateFilter.setCustomerCode(Long.parseLong(customerCode));
                    baseRateDetailVos = customerBaseRateDao.getBaseRateDetailByFilter(customerBaseRateFilter);
                    if (baseRateDetailVos != null) {
                        rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVos, CustomerBaseRateModel.class));
                    }
                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Documents (Inbound)"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Documents (Inbound)"), rateSheetModel);
                        }
                    }
                }

                // check Package in
                if (model.getPackageInbound().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierPackageInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPackageInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                        if (!model.getCarrierPackageInboundPerWeightRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPackageInboundPerWeightRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierPackageInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierPackageInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                        if (!model.getNonCarrierPackageInboundPerWeightRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierPackageInboundPerWeightRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    CustomerProfileFilter filter = new CustomerProfileFilter();
                    if (StringUtils.isNotEmpty(profileId)) {
                        filter.setProfileId(Long.parseLong(profileId));
                    }
                    if (StringUtils.isNotEmpty(customerCode)) {
                        filter.setCustomerCode(Long.parseLong(customerCode));
                    }
                    filter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    filter.setContent(1);
                    filter.setBound(1);
                    List<CustomerBaseRateVo> baseRateDetailVos = new ArrayList<CustomerBaseRateVo>();
                    CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                    CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                    customerBaseRateFilter.setContent(1);
                    customerBaseRateFilter.setBound(1);
                    customerBaseRateFilter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    customerBaseRateFilter.setCustomerCode(Long.parseLong(customerCode));
                    baseRateDetailVos = customerBaseRateDao.getBaseRateDetailByFilter(customerBaseRateFilter);
                    if (baseRateDetailVos != null) {
                        rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVos, CustomerBaseRateModel.class));
                    }
                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Package (Inbound)"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Package (Inbound)"), rateSheetModel);
                        }
                    }
                }

                // check pak
                if (model.getPak().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierPakRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPakRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierPakRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPakRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }

                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Pak"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Pak"), rateSheetModel);
                        }
                    }
                }

                // check pak In
                if (model.getPakInbound().equals("true")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierPakInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierPakInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierPakInboundRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierPakInboundRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }

                    rateSheetModel.setShipmentType(model);
                    if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Pak (Inbound)"), rateSheetModel);
                    }
                }

                // check non-type
                if (model.getPak().equals("false") && model.getPakInbound().equals("false") && model.getPackageInbound().equals("false") && model.getPackage().equals("false") && model.getDocumentInbound().equals("false") && model.getDocument().equals("false")) {
                    RateSheetColModel rateSheetModel = new RateSheetColModel();
                    if (model.getAllowCarrier().equals("true")) {
                        if (!model.getCarrierDocumentRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getCarrierDocumentRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }
                    if (model.getAllowNonCarrier().equals("true")) {
                        if (!model.getNonCarrierDocumentRate().equals("0")) {
                            IRateSheetService service = new RateSheetServiceImp();
                            List<RateSheetColumnVo> rateSheetColumnVos = new ArrayList<RateSheetColumnVo>();
                            rateSheetColumnVos = service.getColumnsBySheetId(Long.parseLong(model.getNonCarrierDocumentRate()));
                            if (rateSheetColumnVos.size() > 0) {
                                rateSheetModel.setNonCarrierRateSheetCols(ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class));
                            }
                        }
                    }

                    CustomerProfileFilter filter = new CustomerProfileFilter();
                    if (StringUtils.isNotEmpty(profileId)) {
                        filter.setProfileId(Long.parseLong(profileId));
                    }
                    if (StringUtils.isNotEmpty(customerCode)) {
                        filter.setCustomerCode(Long.parseLong(customerCode));
                    }
                    filter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    filter.setContent(0);
                    filter.setBound(0);
                    List<CustomerBaseRateVo> baseRateDetailVos = new ArrayList<CustomerBaseRateVo>();
                    CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
                    CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
                    customerBaseRateFilter.setContent(0);
                    customerBaseRateFilter.setBound(0);
                    customerBaseRateFilter.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                    customerBaseRateFilter.setCustomerCode(Long.parseLong(customerCode));
                    baseRateDetailVos = customerBaseRateDao.getBaseRateDetailByFilter(customerBaseRateFilter);
                    if (baseRateDetailVos != null) {
                        rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVos, CustomerBaseRateModel.class));
                    }
                    rateSheetModel.setShipmentType(model);
                    if (model.getServiceId().equals("59")) {
                        rateSheetsN.put(smTypeName.concat(" - ").concat("Documents"), rateSheetModel);
                    } else {
                        if (rateSheetModel.getCarrierRateSheetCols() != null || rateSheetModel.getNonCarrierRateSheetCols() != null) {
                            rateSheetsN.put(smTypeName.concat(" - ").concat("Documents"), rateSheetModel);
                        }
                    }
                    // For TOLL Priority
                    if (model.getServiceGroup() != null) {
                        if (model.getServiceGroup().contains("#")) {
                            ICustomerProfileService serviceN = new CustomerProfileServiceImp();
                            CustomerProfileFilter filterN = new CustomerProfileFilter();

                            if (StringUtils.isNotEmpty(profileId)) {
                                filterN.setProfileId(Long.parseLong(profileId));
                            }
                            if (StringUtils.isNotEmpty(customerCode)) {
                                filterN.setCustomerCode(Long.parseLong(customerCode));
                            }
                            filterN.setShipmentTypeId(Integer.parseInt(model.getShipmentTypeId()));
                            filterN.setContent(0);
                            filterN.setBound(0);
                            List<CustomerProfileBaseRateExtVo> baseRateDetailVosN = new ArrayList<CustomerProfileBaseRateExtVo>();
                            baseRateDetailVosN = serviceN.selectBaseRateByShipment(filterN);
                            if (baseRateDetailVosN != null) {
                                rateSheetModel.setProfileBaseRateDetail(ModelUtils.createListModelFromVo(baseRateDetailVosN, CustomerBaseRateModel.class));
                            }
                            rateSheetModel.setShipmentType(model);
                            rateSheetsN.put(smTypeName, rateSheetModel);
                        }
                    }

                }
            }
        }
        return rateSheetsN;
    }

    public static String recalRateSheet(String value, String rateType, String valuePer) throws Exception {
        Integer rateTypeN = Integer.parseInt(rateType);
        Float valueN = Float.parseFloat(value);
        Float valuePerN = Float.parseFloat(valuePer);
        switch (rateTypeN) {
            case 0: // DHL
                valueN = valueN - (valueN * (valuePerN / 100));
                break;
            case 1: // Margin
                valueN = valueN / (1 - (valuePerN / 100));
                break;
            case 2: // Markup
                valueN = valueN + (valueN * (valuePerN / 100));
                break;
        }

        return ShipmentUtils.roundFloatBy2(valueN).toString();
    }
}