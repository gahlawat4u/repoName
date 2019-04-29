package com.gms.xms.persistence.service.packagetype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.txndb.vo.PackageFilter;
import com.gms.xms.txndb.vo.webship.PackageVo;

import java.util.List;

/**
 * Posted from PackageServiceImp
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public class PackageServiceImp implements IPackageService {
    PackageDao dao = new PackageDao();

    @Override
    public List<PackageVo> getPackageList() throws Exception {
        List<PackageVo> packageVos = dao.getPackageList();
        return packageVos;
    }

    @Override
    public List<PackageVo> getPackageListByShipmentTypeId(Integer shipmentTypeId) throws Exception {
        List<PackageVo> packageVos = dao.getPackageListByShipmentTypeId(shipmentTypeId);
        return packageVos;
    }

    @Override
    public PackageVo getPackageByShipmentTypeIdAndPackageId(PackageFilter packageFilter) throws Exception {
        PackageVo packageVo = dao.selectPackageByShipmentTypeIdAndPackageId(packageFilter);
        return packageVo;
    }

    @Override
    public PackageVo getPackagebyId(Integer packageId) throws DaoException {
        return dao.getPackageById(packageId);
    }
}
