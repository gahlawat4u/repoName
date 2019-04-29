package com.gms.xms.persistence.service.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipCustomerDetailDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailChargeVo;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from WebshipCustomerDetailServiceImp
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailServiceImp implements IWebshipCustomerDetailService {
    private WebshipCustomerDetailDao dao = new WebshipCustomerDetailDao();

    @Override
    public List<WebshipCustomerDetailVo> getWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException {
        // Get list of shipments.
        List<WebshipCustomerDetailVo> detailVos = dao.getWebshipCustomerDetailReport(filter);
        if (detailVos == null || detailVos.size() == 0) {
            return detailVos;
        }
        Double custTotal = null;
        Double franTotal = null;
        Double marginTotal = null;
        // Calculate total customer cost, franchise cost and margin for each
        // shipment
        // and get more extra information.
        for (WebshipCustomerDetailVo detailVo : detailVos) {
            custTotal = 0.0;
            franTotal = 0.0;
            marginTotal = 0.0;
            if (detailVo.getCharges() != null && detailVo.getCharges().size() != 0) {
                for (WebshipCustomerDetailChargeVo charge : detailVo.getCharges()) {
                    custTotal += charge.getCustomerCost() != null ? charge.getCustomerCost() : 0.0;
                    franTotal += charge.getFranchiseCost() != null ? charge.getFranchiseCost() : 0.0;
                    marginTotal += charge.getMargin() != null ? charge.getMargin() : 0.0;
                }
            }
            detailVo.setTotalCustomerCost(custTotal);
            detailVo.setTotalFranchiseCost(franTotal);
            detailVo.setTotalMargin(marginTotal);
            // Set Duties/Taxes Fee.
            detailVo.setDutiesTaxesFee(StringUtils.isBlank(detailVo.getTermOfTrade()) ? "" : detailVo.getTermOfTrade());
            // Set Insurance.
            detailVo.setInsurance(detailVo.getWithInsurance() > 0 ? true : false);
            // Set Dangerous Goods.
            detailVo.setDangerousGoods("dg".equalsIgnoreCase(detailVo.getCourierMessage()) ? true : false);
        }
        return detailVos;
    }

    @Override
    public long countWebshipCustomerDetailReport(WebshipCustomerDetailFilter filter) throws DaoException {
        return dao.countWebshipCustomerDetailReport(filter);
    }
}
