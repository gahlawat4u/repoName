package com.gms.xms.workflow.render.managecontact;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.account.contact.ManageContactFilter;
import com.gms.xms.model.account.contact.ManageContactModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.contact.ManageContactDao;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageContactRenderImp extends BaseRender implements IManageContactRender {

    public ManageContactRenderImp(Map<String, String> addInfo) {
        super(addInfo);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void generateXlsFile(ManageContactFilter filter, String outputFilePath) throws Exception {
        // TODO Auto-generated method stub
        ManageContactDao dao = new ManageContactDao();
        List<ManageContactVo> ManageContactVos = dao.getManageContactByFilter(filter);
        List<ManageContactModel> ManageContactModels = ModelUtils.createListModelFromVo(ManageContactVos, ManageContactModel.class);
        Map<String, Object> data = new HashMap<>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("listData", ManageContactModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/account/search_contact/contact_list.xls", data);
    }

}
