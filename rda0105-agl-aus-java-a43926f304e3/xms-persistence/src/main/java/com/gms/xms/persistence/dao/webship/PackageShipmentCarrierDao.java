package com.gms.xms.persistence.dao.webship;

import java.util.List;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.PackageShipmentCarrierVO;

public class PackageShipmentCarrierDao extends BaseDao<PackageShipmentCarrierVO> {
	
	public PackageShipmentCarrierDao() {
		super();
	}

	 public PackageShipmentCarrierDao(SqlSessionClient sqlSessionClient) {
			super(sqlSessionClient);
		    }
	 
	 public List<PackageShipmentCarrierVO> selectByShipmentTypeId(PackageShipmentCarrierVO packageShipmentCarrierVO) throws DaoException {
			return selectList(packageShipmentCarrierVO, "PackageShipmentCarrier.selectByShipmentTypeId");
		    }

	
}
