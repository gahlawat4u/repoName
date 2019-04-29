package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PackageFilter;
import com.gms.xms.txndb.vo.webship.PackageVo;

import java.util.List;

/**
 * Posted from PackageDaoService
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class PackageDao extends BaseDao<PackageVo> {
    /**
     * Gets a package by id
     *
     * @param packageId
     * @return {@link PackageVo}
     * @throws DaoException
     */
    public PackageVo getPackageById(Integer packageId) throws DaoException {
        return select(packageId, "Package.getPackageById");
    }

    /**
     * Gets list of package types
     *
     * @return List <{@link PackageVo}>
     * @throws DaoException
     */
    public List<PackageVo> getPackageList() throws DaoException {
        return selectList(new PackageVo(), "Package.getPackageList");
    }

    public List<PackageVo> getPackageListByCarrier(Integer serviceId) throws DaoException {
        return selectList(serviceId, "Package.getPackageListByCarrier");
    }

    /**
     * Gets list of package types by shipment type id
     *
     * @param shipmentTypeId
     * @return List<{@link PackageVo>}
     * @throws DaoException
     */
    public List<PackageVo> getPackageListByShipmentTypeId(Integer shipmentTypeId) throws DaoException {
        return selectList(shipmentTypeId, "Package.getPackageListByShipmentTypeId");
    }

    /**
     * Gets list of package types by service id
     *
     * @param serviceId
     * @return List<{@link PackageVo>}
     * @throws DaoException
     */
    public List<PackageVo> getPackageListByServiceId(Integer serviceId) throws DaoException {
        return selectList(serviceId, "Package.getPackageListByServiceId");
    }

    /**
     * Get package by shipment type id and package id
     *
     * @param {@link PackageFilter} packageFilter
     * @return {@link PackageVo} package
     * @throws DaoException
     */
    public PackageVo selectPackageByShipmentTypeIdAndPackageId(PackageFilter packageFilter) throws DaoException {
        return select(packageFilter, "Package.selectPackageByShipmentTypeIdAndPackageId");
    }

    public Integer getPackageIdByContent(PackageVo packageVo) throws DaoException {
        return (Integer) selectObject(packageVo, "Package.getPackageIdByContent");
    }
}