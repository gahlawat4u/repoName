package com.gms.xms.weblib.controller.webship.login;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.login.WebshipDetailModel;
import com.gms.xms.persistence.service.webship.login.ILoginService;
import com.gms.xms.persistence.service.webship.login.LoginServiceImp;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.List;

/**
 * Posted from QuickSearchController
 * <p>
 * Author DatTV Date Jul 9, 2015 5:06:59 PM
 */
public class QuickSearchController extends JsonBaseController {

    private static final long serialVersionUID = -8159936978283532808L;

    private String name;
    private List<WebshipDetailModel> userList;

    private String orderField;
    private String orderType;

    public String search() {
        ILoginService loginService = new LoginServiceImp();

        WebshipDetailFilter filter = this.buildFilter();
        if (filter == null) {
            setErrorMessage("You must login to get this function.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return "success";
        }

        try {
            List<WebshipDetailVo> webshipDetailVos = loginService.selectChildrenByFilter(filter);
            List<WebshipDetailModel> webshipDetailModels = ModelUtils.createListModelFromVo(webshipDetailVos, WebshipDetailModel.class);
            this.setUserList(webshipDetailModels);
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }

        return "success";
    }

    private WebshipDetailFilter buildFilter() {
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null)
            return null;
        WebshipDetailFilter filter = new WebshipDetailFilter();
        Long searchCustomerCode = webshipLoginVo.getParentCustomerCode() == null ? webshipLoginVo.getCustomerCode() : webshipLoginVo.getParentCustomerCode();
        filter.setCustomerCode(String.valueOf(searchCustomerCode));
        filter.setName(name);
        filter.setOrderField(orderField);
        if(orderType != null && !orderType.isEmpty())
            filter.setOrderType(Integer.parseInt(orderType));

        return filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebshipDetailModel> getUserList() {
        return userList;
    }

    public void setUserList(List<WebshipDetailModel> userList) {
        this.userList = userList;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}