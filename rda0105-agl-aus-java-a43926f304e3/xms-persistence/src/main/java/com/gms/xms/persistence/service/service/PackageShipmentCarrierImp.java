package com.gms.xms.persistence.service.service;

import java.util.List;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.PackageShipmentCarrierDao;
import com.gms.xms.txndb.vo.PackageShipmentCarrierVO;

public class PackageShipmentCarrierImp implements PackageShipmentCarrier {

	PackageShipmentCarrierDao  packageShipmentCarrierDao = new PackageShipmentCarrierDao();
	@Override
	public List<PackageShipmentCarrierVO> selectByShipmentTypeId(PackageShipmentCarrierVO packageShipmentCarrierVO) throws DaoException {
		
		return packageShipmentCarrierDao.selectByShipmentTypeId(packageShipmentCarrierVO);
	}

}
