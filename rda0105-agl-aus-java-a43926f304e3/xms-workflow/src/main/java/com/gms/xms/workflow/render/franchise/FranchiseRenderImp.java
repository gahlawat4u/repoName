package com.gms.xms.workflow.render.franchise;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.model.account.franchises.FranchiseDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.account.franchises.FranchiseDetailVo;
import com.gms.xms.txndb.vo.account.franchises.FranchiseListColumnFlagsVo;
import com.gms.xms.workflow.render.BaseRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchiseRenderImp
 * <p>
 * Author DatTV Dec 25, 2015
 */
public class FranchiseRenderImp extends BaseRender implements IFranchiseRender {
    private IFranchiseService franchiseService = new FranchiseServiceImp();

    public FranchiseRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void renderFranchiseListXlsFile(FranchiseDetailFilter filter, String outPutFilePath, FranchiseListColumnFlagsVo columnFlags) throws Exception {
        List<FranchiseDetailVo> franchiseDetailVos = franchiseService.getFranchises(filter);
        List<FranchiseDetailModel> franchiseDetailModels = ModelUtils.createListModelFromVo(franchiseDetailVos, FranchiseDetailModel.class);
        for (FranchiseDetailModel franchiseDetailModel : franchiseDetailModels) {
            if (franchiseDetailModel.getEmailInvoice().equals("true")) {
                franchiseDetailModel.setEmailInvoice("Yes");
            } else {
                franchiseDetailModel.setEmailInvoice("No");
            }
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("flags", columnFlags);
        data.put("franchiseList", franchiseDetailModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/account/franchise_list/franchise_list.xls", data);
    }
}
