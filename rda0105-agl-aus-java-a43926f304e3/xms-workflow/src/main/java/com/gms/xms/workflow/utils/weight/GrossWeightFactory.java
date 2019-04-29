package com.gms.xms.workflow.utils.weight;

/**
 * Posted from Jul 29, 2016 9:30:41 AM
 * <p>
 * Author dattrinh
 */
public class GrossWeightFactory {
    public static BaseGrossWeight getGrossWeight(Integer serviceId, Integer shipmentTypeId) {
        switch (serviceId) {
            case 1:
                return new DhlGrossWeight();
            case 2:
                return new AaeGrossWeight();
            case 3:
                return new TntDomGrossWeight();
            case 15:
                return new DhlDomGrossWeight();
            case 40:
                return new FedExGrossWeight();
            case 50:
                return new UkMailGrossWeight();
            case 51:
                return new UpsGrossWeight();
            case 52:
                return new TollGrossWeight();
            case 54:
                return new TntIntlGrossWeight();
            case 59:
                return new TollIpecGrossWeight();
            case 400:
                return new UpsGrossWeight();
            case 72:
                StartrackGrossWeight startrackGrossWeight = new StartrackGrossWeight();
                startrackGrossWeight.setShipmentTypeId(shipmentTypeId);
                return startrackGrossWeight;
            default:
                return new DefaultGrossWeight();
        }
    }
}
