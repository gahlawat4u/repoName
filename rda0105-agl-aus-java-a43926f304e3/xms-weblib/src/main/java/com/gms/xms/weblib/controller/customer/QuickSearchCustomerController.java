package com.gms.xms.weblib.controller.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.filter.customer.SearchCustomerFilter;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.customers.SearchCustomerDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Jul 5, 2016 4:00:14 PM
 * <p>
 * Author dattrinh
 */
public class QuickSearchCustomerController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private String customerCode;
    private List<CustomerAddressModel> customerList;

    public String show() {
        try {
            // Set default search props.
            this.setCustomerCode("");
            loadCustomerList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadCustomerList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void loadCustomerList() throws Exception {
        SearchCustomerFilter filter = this.buildFilter();
        SearchCustomerDao dao = new SearchCustomerDao();
        List<CustomerAddressVo> customerAddressVos = dao.selectCustomerByCode(filter);
        List<CustomerAddressModel> customerAddressModels = ModelUtils.createListModelFromVo(customerAddressVos, CustomerAddressModel.class);
        if (customerAddressModels == null) {
            customerAddressModels = new ArrayList<CustomerAddressModel>();
        }
        this.setCustomerList(customerAddressModels);
        if (customerAddressModels.size() > 0) {
            this.setCustomerCode(customerAddressModels.get(0).getCustomerCode());
        }
    }

    protected SearchCustomerFilter buildFilter() throws Exception {
        SearchCustomerFilter filter = new SearchCustomerFilter();
        filter.setCustomerCode(this.getCustomerCode());
        filter.setFranchiseList(buildFranchiseCodeList("All"));
        return filter;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public List<CustomerAddressModel> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerAddressModel> customerList) {
        this.customerList = customerList;
    }
}
