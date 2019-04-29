package com.gms.xms.persistence.daoservice.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.webship.history.HistoryDetailAccessorialDao;
import com.gms.xms.persistence.dao.webship.history.HistoryDetailDao;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailAccessorialVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from HistoryDetailPieceDao
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailAccessorialDaoService {
    /**
     * @return
     * @throws DaoException
     */

    public List<HistoryDetailAccessorialVo> historyDetailAccessorial(HistoryDetailFilter filter) throws DaoException{
        return historyDetailAccessorial(filter, null);
    }

    public List<HistoryDetailAccessorialVo> historyDetailAccessorial(HistoryDetailFilter filter, HistoryDetailInfoVo detailInfoVo) throws DaoException {
        HistoryDetailAccessorialDao historyDetailAccessorialDao = new HistoryDetailAccessorialDao();
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        HistoryDetailDao historyDetailDao = new HistoryDetailDao();
        List<HistoryDetailAccessorialVo> accessorialVos = historyDetailAccessorialDao.selectHistoryDetailAccessorial(filter);
        if(detailInfoVo==null)
        {
            detailInfoVo = historyDetailDao.selectHistoryDetailInfo(filter);
        }
        String descript = "";
        Double assCharge = 0D;
        Double totalCharge = 0D;
        Double vat = 0D;
        String vatRate = "";
        String taxName = "";
        Double insuarance = 0D;
        Double nonCharge = 0D;
        Double manualHandlingSurchargeValue = 0D;

        for (HistoryDetailAccessorialVo historyDetailAccessorialVo : accessorialVos) {
            descript = historyDetailAccessorialVo.getDescription();
            assCharge = historyDetailAccessorialVo.getAmount();
            if (historyDetailAccessorialVo.getCarrierId() == 3 && (descript.equals("Carbon Price Recovery") || descript.equals("Dangerous Goods") || descript.equals("Remote Area Pickup") || descript.equals("Remote Area Delivery"))) {
                if (descript.equals("Dangerous Goods")) {
                    if (!detailInfoVo.getServiceType().equals("Road Express")) {
                        assCharge = Double.parseDouble(systemSettingDao.getSystemSettingByName("Dangerous Goods Cost").getSettingValue());
                    }
                    historyDetailAccessorialVo.setAmount(MathUtils.round(assCharge, 2));
                    historyDetailAccessorialVo.setDescription(descript);
                } else {
                    historyDetailAccessorialVo.setDescription(descript);
                    historyDetailAccessorialVo.setAmount(MathUtils.round(assCharge, 2));
                }
            } else if(historyDetailAccessorialVo.getCarrierId() == 400){
                if(historyDetailAccessorialVo.getCode().equals("RAS") || historyDetailAccessorialVo.getCode().equals("EAS"))
                {
                    if(historyDetailAccessorialVo.getType() != null)
                        descript += historyDetailAccessorialVo.getType() == 0 ?" - origin" : " - destination";
                    historyDetailAccessorialVo.setDescription(descript);
                    historyDetailAccessorialVo.setAmount(MathUtils.round(assCharge, 2));
                }
            }
            else{
                historyDetailAccessorialVo.setDescription(descript);
                historyDetailAccessorialVo.setAmount(MathUtils.round(assCharge, 2));
            }
            totalCharge += assCharge;
        }
        if (detailInfoVo.getsCountryName().equals(detailInfoVo.getrCountryName())) {
            vatRate = systemSettingDao.getSystemSettingByName("Domestic Tax Percentage").getSettingValue();
        } else {
            vatRate = systemSettingDao.getSystemSettingByName("VAT percent based on Base Charge").getSettingValue();
        }

        if (detailInfoVo.getWithInsurance() != null && detailInfoVo.getWithInsurance() > 0) {
            HistoryDetailAccessorialVo withInsuranceN = new HistoryDetailAccessorialVo();
            withInsuranceN.setAmount(MathUtils.round((double) detailInfoVo.getWithInsurance(), 2));
            withInsuranceN.setDescription("Additional Protection");
            accessorialVos.add(withInsuranceN);
            insuarance = (double) detailInfoVo.getWithInsurance();
        }

        if (detailInfoVo.getNonStandardCharge() != null && detailInfoVo.getNonStandardCharge() > 0) {
            HistoryDetailAccessorialVo nonStandardChargeN = new HistoryDetailAccessorialVo();
            nonStandardChargeN.setAmount(MathUtils.round((double) detailInfoVo.getNonStandardCharge(), 2));
            nonStandardChargeN.setDescription("Non-standard Shipping Charge");
            accessorialVos.add(nonStandardChargeN);
            nonCharge = (double) detailInfoVo.getNonStandardCharge();
        }

        if (detailInfoVo.getManualHandlingSurcharge() != null && detailInfoVo.getManualHandlingSurcharge() > 0) {
            HistoryDetailAccessorialVo manualHandlingSurcharge = new HistoryDetailAccessorialVo();
            manualHandlingSurcharge.setAmount(MathUtils.round((double) detailInfoVo.getManualHandlingSurcharge(), 2));
            manualHandlingSurcharge.setDescription("Manual handling surcharge");
            accessorialVos.add(manualHandlingSurcharge);
            manualHandlingSurchargeValue = (double) detailInfoVo.getManualHandlingSurcharge();
        }


        if (!StringUtils.isEmpty(vatRate)) {
            taxName = systemSettingDao.getSystemSettingByName("Tax Name").getSettingValue();
            vat = (MathUtils.round((double) detailInfoVo.getBaseCharge(), 2) + MathUtils.round(totalCharge, 2)
                    + MathUtils.round(insuarance, 2) + MathUtils.round(nonCharge, 2)
                    + MathUtils.round(manualHandlingSurchargeValue, 2))
                    * (Double.parseDouble(vatRate) / 100);
            totalCharge += vat;
            HistoryDetailAccessorialVo vatAcc = new HistoryDetailAccessorialVo();
            vatAcc.setAmount(MathUtils.round((vat), 2));
            vatAcc.setDescription(taxName);
            accessorialVos.add(vatAcc);
        } else {

        }
        HistoryDetailAccessorialVo totalQuote = new HistoryDetailAccessorialVo();
        totalCharge = MathUtils.round((double) detailInfoVo.getBaseCharge(), 2) + MathUtils.round(totalCharge, 2)
                + MathUtils.round(insuarance, 2) + MathUtils.round(nonCharge, 2) + MathUtils.round(manualHandlingSurchargeValue, 2);
        totalQuote.setAmount(MathUtils.round((double) totalCharge, 2));
        totalQuote.setDescription("Total Charges");
        accessorialVos.add(totalQuote);

        return accessorialVos;
    }
}
