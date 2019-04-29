package com.gms.xms.persistence.service.service;

import java.util.List;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PackageShipmentCarrierVO;

public interface PackageShipmentCarrier {

	public List<PackageShipmentCarrierVO> selectByShipmentTypeId(PackageShipmentCarrierVO packageShipmentCarrierVO) throws DaoException ;
}
