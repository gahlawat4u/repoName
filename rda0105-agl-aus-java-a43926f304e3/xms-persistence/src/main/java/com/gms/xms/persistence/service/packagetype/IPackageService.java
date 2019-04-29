package com.gms.xms.persistence.service.packagetype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PackageFilter;
import com.gms.xms.txndb.vo.webship.PackageVo;

import java.util.List;

/**
 * Posted from IPackageService
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public interface IPackageService {
    public List<PackageVo> getPackageList() throws Exception;

    public List<PackageVo> getPackageListByShipmentTypeId(Integer shipmentTypeId) throws Exception;

    public PackageVo getPackageByShipmentTypeIdAndPackageId(PackageFilter packageFilter) throws Exception;

    public PackageVo getPackagebyId(Integer packageId) throws DaoException;
}
