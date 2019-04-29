package com.gms.xms.workflow.render.franchise;

import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.txndb.vo.account.franchises.FranchiseListColumnFlagsVo;

/**
 * Posted from IFranchiseRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IFranchiseRender {
    public void renderFranchiseListXlsFile(FranchiseDetailFilter filter, String outPutFilePath, FranchiseListColumnFlagsVo columnFlags) throws Exception;
}
