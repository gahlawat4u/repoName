package test.com.gms.xms.service;

import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.junit.Test;

import java.util.List;

/**
 * Posted from ServiceTest
 * <p>
 * Author DatTV Oct 15, 2015
 */
public class ServiceTest {
    @Test
    public void getActiveServicesWithBaseRatesTest() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.getActiveServicesWithBaseRates("10000003");
        for (ServiceVo serviceVo : serviceVos) {
            System.out.println("=============== SERVICE ==============");
            System.out.println(serviceVo.getServiceName());
            System.out.println("======================================");
            List<ShipmentTypeVo> shipmentTypes = serviceVo.getShipmentTypes();
            for (ShipmentTypeVo shipmentTypeVo : shipmentTypes) {
                List<PackageTypeVo> packageTypeVos = shipmentTypeVo.getPackageTypes();
                for (PackageTypeVo packageTypeVo : packageTypeVos) {
                    boolean showPackage = false;
                    switch (packageTypeVo.getPackageType()) {
                        case 0: // Non Type
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierDocumentRate());
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierDocumentRate());
                                    }
                                }
                            }
                            break;
                        case 1: // Document outbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierDocumentRate());
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierDocumentRate());
                                    }
                                }
                            }
                            break;
                        case 2: // Document inbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierDocumentInboundRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierDocumentInboundRate());
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierDocumentInboundRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierDocumentInboundRate());
                                    }
                                }
                            }
                            break;
                        case 3: // Package outbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierPackageRate() != 0 || shipmentTypeVo.getCarrierPackagePerWeightRate() != 0) {
                                        showPackage = true;
                                        if (shipmentTypeVo.getCarrierPackageRate() != 0) {
                                            packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPackageRate());
                                        } else if (shipmentTypeVo.getCarrierPackagePerWeightRate() != 0) {
                                            packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPackagePerWeightRate());
                                        }
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierPackageRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierPackageRate());
                                    }
                                }
                            }
                            break;
                        case 4: // Package inbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierPackageInboundRate() != 0 || shipmentTypeVo.getCarrierPackageInboundPerWeightRate() != 0) {
                                        showPackage = true;
                                        if (shipmentTypeVo.getCarrierPackageInboundRate() != 0) {
                                            packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPackageInboundRate());
                                        } else if (shipmentTypeVo.getCarrierPackageInboundPerWeightRate() != 0) {
                                            packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPackageInboundPerWeightRate());
                                        }
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierPackageInboundRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierPackageInboundRate());
                                    }
                                }
                            }
                            break;
                        case 5: // Pak outbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierPakRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPakRate());
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierPakRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierPakRate());
                                    }
                                }
                            }
                            break;
                        case 6: // Pak inbound
                            if (!shipmentTypeVo.getAllowCarrier() && !shipmentTypeVo.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentTypeVo.getAllowCarrier()) {
                                    if (shipmentTypeVo.getCarrierPakInboundRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setCarrierSheetId(shipmentTypeVo.getCarrierPakInboundRate());
                                    }
                                }
                                if (shipmentTypeVo.getAllowNonCarrier()) {
                                    if (shipmentTypeVo.getNonCarrierPakInboundRate() != 0) {
                                        showPackage = true;
                                        packageTypeVo.setNonCarrierSheetId(shipmentTypeVo.getNonCarrierPakInboundRate());
                                    }
                                }
                            }
                            break;
                    }
                    if (!showPackage) {
                        continue;
                    } else {
                        IRateSheetService rateSheetService = new RateSheetServiceImp();
                        // Get carrier zone list
                        if (packageTypeVo.getCarrierSheetId() != null) {
                            packageTypeVo.setCarrierZone(rateSheetService.getColumnsBySheetId(packageTypeVo.getCarrierSheetId()));
                        }
                        // Get non carrier zone list
                        if (packageTypeVo.getNonCarrierSheetId() != null) {
                            packageTypeVo.setNonCarrierZone(rateSheetService.getColumnsBySheetId(packageTypeVo.getNonCarrierSheetId()));
                        }
                    }
                    System.out.println("\t" + shipmentTypeVo.getShipmentTypeId() + " - " + shipmentTypeVo.getShipmentTypeName() + " - " + packageTypeVo.getPackageTypeName());
                    List<CustomerBaseRateVo> baseRateVos = packageTypeVo.getBaseRates();
                    if (baseRateVos != null && baseRateVos.size() > 0) {
                        for (CustomerBaseRateVo customerBaseRateVo : baseRateVos) {
                            System.out.println("\t\t" + customerBaseRateVo.getWeight() + "\t" + customerBaseRateVo.getRateType() + "\t" + customerBaseRateVo.getRate());
                            List<CustomerBaseRateDetailVo> baseRateDetailVos = customerBaseRateVo.getCustomerBaseRateDetails();
                            System.out.print("\t\t\t");
                            if (baseRateDetailVos != null) {
                                for (CustomerBaseRateDetailVo customerBaseRateDetailVo : baseRateDetailVos) {
                                    System.out.print(" (" + customerBaseRateDetailVo.getZone() + "," + customerBaseRateDetailVo.getRate() + ")");
                                }
                            } else {
                                List<RateSheetColumnVo> zones = null;
                                if (packageTypeVo.getCarrierZone() != null && packageTypeVo.getCarrierZone().size() == 0) {
                                    zones = packageTypeVo.getCarrierZone();
                                }
                                if (packageTypeVo.getNonCarrierZone() != null && packageTypeVo.getNonCarrierZone().size() == 0) {
                                    zones = packageTypeVo.getNonCarrierZone();
                                }
                                if (zones != null) {
                                    for (RateSheetColumnVo rateSheetColumnVo : zones) {
                                        System.out.print(" (" + rateSheetColumnVo.getColumnName() + ",0)");
                                    }
                                }
                            }
                            System.out.println();
                        }
                    } else {
                        System.out.println("\t\t0\t0\t0.00");
                        System.out.print("\t\t\t");
                        List<RateSheetColumnVo> zones = null;
                        if (packageTypeVo.getCarrierZone() != null && packageTypeVo.getCarrierZone().size() == 0) {
                            zones = packageTypeVo.getCarrierZone();
                        }
                        if (packageTypeVo.getNonCarrierZone() != null && packageTypeVo.getNonCarrierZone().size() == 0) {
                            zones = packageTypeVo.getNonCarrierZone();
                        }
                        if (zones != null) {
                            for (RateSheetColumnVo rateSheetColumnVo : zones) {
                                System.out.print(" (" + rateSheetColumnVo.getColumnName() + ",0)");
                            }
                        }
                    }
                }
            }
        }
    }
}