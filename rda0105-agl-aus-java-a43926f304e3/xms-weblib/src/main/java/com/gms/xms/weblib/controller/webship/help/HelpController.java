package com.gms.xms.weblib.controller.webship.help;

import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.ActionBaseController;

/**
 * Posted from HelpController
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public class HelpController extends ActionBaseController {
    private static final long serialVersionUID = -2280433407672596511L;

    private CustomerAddressModel customerAddress;

    public String show() {
        this.setPageTitle("Help");
        this.setBreadCrumb("Help");
        try {
            WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
            String customerCode = webshipLoginVo.getParentCustomerCode() == null ? webshipLoginVo.getCustomerCode().toString() : webshipLoginVo.getParentCustomerCode().toString();
            customerCode = customerCode.substring(0, 3).concat("00001");
            CustomerAddressDao dao = new CustomerAddressDao();
            CustomerAddressVo customerAddressVo = new CustomerAddressVo();
            customerAddressVo.setCustomerCode(Long.valueOf(customerCode));
            customerAddressVo.setUserType(2);
            customerAddressVo = dao.selectCustomerAddressByCode(customerAddressVo);
            CustomerAddressModel customerAddress = ModelUtils.createModelFromVo(customerAddressVo, CustomerAddressModel.class);
            this.setCustomerAddress(customerAddress);
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public CustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }
}
