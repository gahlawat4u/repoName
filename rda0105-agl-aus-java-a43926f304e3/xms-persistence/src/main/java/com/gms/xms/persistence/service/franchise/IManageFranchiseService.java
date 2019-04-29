package com.gms.xms.persistence.service.franchise;

import com.gms.xms.txndb.vo.account.franchises.AddFranchiseVo;
import com.gms.xms.txndb.vo.account.franchises.SaveManageFranchiseVo;

import java.util.Map;

/**
 * Posted from Apr 13, 2016 2:57:22 PM
 * <p>
 * Author huynd
 */
public interface IManageFranchiseService {
    public void updateFranchise(Map<String, String> context, SaveManageFranchiseVo franchise) throws Exception;

    public void addFranchise(Map<String, String> context, AddFranchiseVo franchise) throws Exception;
}
