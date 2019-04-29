package test.com.gms.xms.json;

import com.gms.xms.model.account.customers.manage.CustomerAccountSetupModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.CustomerAccountSetupVo;

public class TestJson {
    public static void main(String[] args) throws Exception {
        CustomerAccountSetupModel accountSetupModel = new CustomerAccountSetupModel();
        accountSetupModel.setActivateDate("28-02-2012");
        CustomerAccountSetupVo accountSetupVo = ModelUtils.createVoFromModel(accountSetupModel, CustomerAccountSetupVo.class);
        CustomerVo cus = accountSetupVo;
        System.out.println(cus);
    }
}