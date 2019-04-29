package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from FranchisePayableMSDaoService
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSDaoService {
    private FranchisePayableMSOverviewDao payableMSOverviewDao;
    private FranchisePayableMSMarginDao payableMSMarginDao;
    private FranchisePayableMS61DaysDao payableMS61DaysDao;
    private FranchisePayableMSNonCentralDao payableMSNonCentralDao;
    private FranchisePayableMSDeductDao payableMSDeductDao;

    public FranchisePayableMSDaoService() {
        payableMSOverviewDao = new FranchisePayableMSOverviewDao();
        payableMSMarginDao = new FranchisePayableMSMarginDao();
        payableMS61DaysDao = new FranchisePayableMS61DaysDao();
        payableMSNonCentralDao = new FranchisePayableMSNonCentralDao();
        payableMSDeductDao = new FranchisePayableMSDeductDao();
    }

    /**
     * Return overview value object
     *
     * @param filter - filter
     * @return - overview object
     * @throws DaoException
     */
    public FranchisePayableMSOverviewVo getOverview(FranchisePayableFilter filter) throws DaoException {
        // Determine non centralized tab enable or disable
        boolean enableNonCentralized = true;
        try {
            enableNonCentralized = Boolean.parseBoolean(AppConstants.APP_SETTINGS.getEnableNonCentralizedTab());
        } catch (Exception e) {
            enableNonCentralized = false;
        }

        // Get default tax percent
        double taxPercent = 0.1d;
        try {
            SystemSettingDao systemSettingDao = new SystemSettingDao();
            String defaultTaxPercent = systemSettingDao.getSystemSettingByName("Default Franchise Payable Tax Percent").getSettingValue();
            taxPercent = Double.valueOf(defaultTaxPercent) / 100;
        } catch (Exception e) {
        }

        // Calculate overview data
        try {
            long setups = payableMSOverviewDao.getSetups(filter);
            long activations = payableMSOverviewDao.getActivations(filter);
            long printedInvoices = payableMSOverviewDao.getPrintedInvoices(filter);
            long emailInvoices = payableMSOverviewDao.getEmailInvoices(filter);
            // BigDecimal _61DaysPayments =
            // payableMSOverviewDao.get61DaysPayments(filter);

            // Get payment margin details total record
            FranchisePayableMSMarginVo msMarginTotalVo = payableMSMarginDao.getPaymentMarginDetailsTotal(filter);

            // Get 61+ days payment credit details total record
            FranchisePayableMS61DaysVo ms61DaysTotalVo = payableMS61DaysDao.get61DaysPaymentCreditDetailsTotal(filter);

            // Get non centralized details total record
            FranchisePayableMSNonCentralVo msNonCentralTotalVo = payableMSNonCentralDao.getNonCentralizedDetailsTotal(filter);

            // Get carrier cost deduction total record
            FranchisePayableMSDeductVo msDeductTotalVo = payableMSDeductDao.getCarrierCostDeductionTotal(filter);

            FranchisePayableMSOverviewVo msOverviewVo = new FranchisePayableMSOverviewVo();
            msOverviewVo.setSetups(setups);
            msOverviewVo.setActivations(activations);
            msOverviewVo.setPrintedInvoices(printedInvoices);
            msOverviewVo.setEmailInvoices(emailInvoices);
            // msOverviewVo.setDay61Payment((_61DaysPayments != null) ?
            // _61DaysPayments : BigDecimal.ZERO);

            // Set other information for overview
            msOverviewVo.setMarginShare(msMarginTotalVo != null ? msMarginTotalVo.getProfitShareExcGst() : BigDecimal.ZERO);
            msOverviewVo.setDay61MarginShare(ms61DaysTotalVo != null ? ms61DaysTotalVo.getProfitShareExcGst() : BigDecimal.ZERO);
            if (enableNonCentralized) {
                msOverviewVo.setNonCentralizedMarginShare((msNonCentralTotalVo != null) ? msNonCentralTotalVo.getProfitShareExcGst() : BigDecimal.ZERO);
            } else {
                msOverviewVo.setNonCentralizedMarginShare(BigDecimal.ZERO);
            }
            msOverviewVo.setLateFee(BigDecimal.ZERO);

            BigDecimal grossPayables = BigDecimal.ZERO;
            grossPayables = grossPayables.add(msOverviewVo.getMarginShare());
            grossPayables = grossPayables.add(msOverviewVo.getDay61MarginShare());
            grossPayables = grossPayables.add(msOverviewVo.getNonCentralizedMarginShare());
            grossPayables = grossPayables.add(msOverviewVo.getLateFee());
            msOverviewVo.setGrossPayables(grossPayables);

            // Remove this column
            // So set it to ZERO
            msOverviewVo.setCarrierCredits(BigDecimal.ZERO);

            // Calculate Repaid Carrier Deductions (Exclude GST)
            BigDecimal repaidCarrierDeductionsIncGst = ms61DaysTotalVo != null ? ms61DaysTotalVo.getRepaidCarrierDeductions() : BigDecimal.ZERO;
            BigDecimal repaidCarrierDeductionsExcGst = repaidCarrierDeductionsIncGst.divide(BigDecimal.valueOf(1 + taxPercent), BigDecimal.ROUND_HALF_UP);

            msOverviewVo.setRepaidCarrierDeductions(repaidCarrierDeductionsExcGst);

            // Non centralized carrier cost
            BigDecimal nonCentralCarrierCostExcGst = msNonCentralTotalVo != null ? msNonCentralTotalVo.getFranchiseCostExcGst() : BigDecimal.ZERO;
            BigDecimal nonCentralCarrierCostGst = msNonCentralTotalVo != null ? msNonCentralTotalVo.getFranchiseCostGst() : BigDecimal.ZERO;
            if (enableNonCentralized) {
                msOverviewVo.setNonCentralCarrierCostExcGst(nonCentralCarrierCostExcGst);
                msOverviewVo.setNonCentralCarrierCostGst(nonCentralCarrierCostGst);
            } else {
                msOverviewVo.setNonCentralCarrierCostExcGst(BigDecimal.ZERO);
                msOverviewVo.setNonCentralCarrierCostGst(BigDecimal.ZERO);
            }

            // Calculate Carrier Cost Deduction from Franchise Charge (Exclude
            // GST) (of Carrier Cost Deduction Tab)
            BigDecimal franchiseChargeIncGst = msDeductTotalVo != null ? msDeductTotalVo.getFranchiseCharge() : BigDecimal.ZERO;
            BigDecimal franchiseChargeExcGst = franchiseChargeIncGst.divide(BigDecimal.valueOf(1 + taxPercent), BigDecimal.ROUND_HALF_UP);
            msOverviewVo.setCarrierCostDeduction(franchiseChargeExcGst);
            msOverviewVo.setTechFees(BigDecimal.ZERO);
            msOverviewVo.setMarketingFees(BigDecimal.ZERO);

            BigDecimal netPayablesExcGst = BigDecimal.ZERO;
            netPayablesExcGst = netPayablesExcGst.add(msOverviewVo.getGrossPayables());
            netPayablesExcGst = netPayablesExcGst.add(msOverviewVo.getRepaidCarrierDeductions());
            netPayablesExcGst = netPayablesExcGst.subtract(msOverviewVo.getCarrierCostDeduction());
            netPayablesExcGst = netPayablesExcGst.subtract(msOverviewVo.getTechFees());
            netPayablesExcGst = netPayablesExcGst.subtract(msOverviewVo.getMarketingFees());
            msOverviewVo.setNetPayablesExcGst(netPayablesExcGst);
            BigDecimal tax = BigDecimal.valueOf(taxPercent);
            BigDecimal netPayablesGst = netPayablesExcGst.multiply(tax);

            //total payables
            BigDecimal totalPayables = netPayablesExcGst.add(netPayablesGst);
            totalPayables = totalPayables.add(nonCentralCarrierCostExcGst);
            totalPayables = totalPayables.add(nonCentralCarrierCostGst);

            netPayablesGst = netPayablesGst.setScale(2, BigDecimal.ROUND_HALF_UP);
            totalPayables = totalPayables.setScale(2, BigDecimal.ROUND_HALF_UP);
            msOverviewVo.setNetPayablesGst(netPayablesGst);
            msOverviewVo.setTotalPayables(totalPayables);

            return msOverviewVo;
        } catch (DaoException e) {
            throw e;
        }
    }

    public FranchisePayableInvoiceExportVo getInvoiceExportData(FranchisePayableFilter filter) throws DaoException {
        // Determine non centralized tab enable or disable
        boolean enableNonCentralized = true;
        try {
            enableNonCentralized = Boolean.parseBoolean(AppConstants.APP_SETTINGS.getEnableNonCentralizedTab());
        } catch (Exception e) {
            enableNonCentralized = false;
        }

        // Get default tax percent
        double taxPercent = 0.1d;
        try {
            SystemSettingDao systemSettingDao = new SystemSettingDao();
            String defaultTaxPercent = systemSettingDao.getSystemSettingByName("Default Franchise Payable Tax Percent").getSettingValue();
            taxPercent = Double.valueOf(defaultTaxPercent) / 100;
        } catch (Exception e) {
        }
        // Get payment margin details total record
        FranchisePayableMSMarginVo msMarginTotalVo = payableMSMarginDao.getPaymentMarginDetailsTotal(filter);

        // Get 61+ days payment credit details total record
        FranchisePayableMS61DaysVo ms61DaysTotalVo = payableMS61DaysDao.get61DaysPaymentCreditDetailsTotal(filter);

        // Get non centralized details total record
        FranchisePayableMSNonCentralVo msNonCentralTotalVo = payableMSNonCentralDao.getNonCentralizedDetailsTotal(filter);

        // Get carrier cost deduction total record
        FranchisePayableMSDeductVo msDeductTotalVo = payableMSDeductDao.getCarrierCostDeductionTotal(filter);

        FranchisePayableInvoiceExportVo invoiceVo = new FranchisePayableInvoiceExportVo();

        // Get margin share
        BigDecimal marginShareExcGst = msMarginTotalVo != null ? msMarginTotalVo.getProfitShareExcGst() : BigDecimal.ZERO;
        BigDecimal marginShareGst = marginShareExcGst.multiply(BigDecimal.valueOf(taxPercent)).setScale(2, BigDecimal.ROUND_HALF_UP);
        invoiceVo.setMarginShareExcGst(marginShareExcGst);
        invoiceVo.setMarginShareGst(marginShareGst);

        // Get day 61 payment
        BigDecimal day61MarginShareExcGst = ms61DaysTotalVo != null ? ms61DaysTotalVo.getProfitShareExcGst() : BigDecimal.ZERO;
        BigDecimal day61MarginShareGst = day61MarginShareExcGst.multiply(BigDecimal.valueOf(taxPercent)).setScale(2, BigDecimal.ROUND_HALF_UP);
        invoiceVo.setDay61MarginShareExcGst(day61MarginShareExcGst);
        invoiceVo.setDay61MarginShareGst(day61MarginShareGst);

        // Get non centralized carrier cost
        BigDecimal nonCentralizedMarginShareExcGst = BigDecimal.ZERO;
        if (enableNonCentralized) {
            nonCentralizedMarginShareExcGst = msNonCentralTotalVo != null ? msNonCentralTotalVo.getProfitShareExcGst() : BigDecimal.ZERO;
        } else {
            nonCentralizedMarginShareExcGst = BigDecimal.ZERO;
        }
        BigDecimal nonCentralizedMarginShareGst = nonCentralizedMarginShareExcGst.multiply(BigDecimal.valueOf(taxPercent)).setScale(2, BigDecimal.ROUND_HALF_UP);
        invoiceVo.setNonCentralizedMarginShareExcGst(nonCentralizedMarginShareExcGst);
        invoiceVo.setNonCentralizedMarginShareGst(nonCentralizedMarginShareGst);

        // Get late fee - no formula so set to 0
        invoiceVo.setLateFeeExcGst(BigDecimal.ZERO);
        invoiceVo.setLateFeeGst(BigDecimal.ZERO);

        // Get gross payables
        BigDecimal grossPayablesExcGst = BigDecimal.ZERO;
        grossPayablesExcGst = grossPayablesExcGst.add(invoiceVo.getMarginShareExcGst());
        grossPayablesExcGst = grossPayablesExcGst.add(invoiceVo.getDay61MarginShareExcGst());
        grossPayablesExcGst = grossPayablesExcGst.add(invoiceVo.getNonCentralizedMarginShareExcGst());
        grossPayablesExcGst = grossPayablesExcGst.add(invoiceVo.getLateFeeExcGst());
        invoiceVo.setGrossPayablesExcGst(grossPayablesExcGst);

        BigDecimal grossPayablesGst = BigDecimal.ZERO;
        grossPayablesGst = grossPayablesGst.add(invoiceVo.getMarginShareGst());
        grossPayablesGst = grossPayablesGst.add(invoiceVo.getDay61MarginShareGst());
        grossPayablesGst = grossPayablesGst.add(invoiceVo.getNonCentralizedMarginShareGst());
        grossPayablesGst = grossPayablesGst.add(invoiceVo.getLateFeeGst());
        invoiceVo.setGrossPayablesGst(grossPayablesGst);

        // Get carrier credit - no formula so set to 0
        invoiceVo.setCarrierCreditExcGst(BigDecimal.ZERO);
        invoiceVo.setCarrierCreditGst(BigDecimal.ZERO);

        // Get repaid carrier deductions
        BigDecimal repaidCarrierDeductions = ms61DaysTotalVo != null ? ms61DaysTotalVo.getRepaidCarrierDeductions() : BigDecimal.ZERO;

        // toantq removed BigDecimal repaidCarrierDeductionsExcGst =
        // repaidCarrierDeductions.divide(taxRate, 2, RoundingMode.HALF_UP);
        BigDecimal repaidCarrierDeductionsExcGst = repaidCarrierDeductions.divide(BigDecimal.valueOf(1 + taxPercent), BigDecimal.ROUND_HALF_UP);
        BigDecimal repaidCarrierDeductionsGst = repaidCarrierDeductions.subtract(repaidCarrierDeductionsExcGst);
        repaidCarrierDeductions = repaidCarrierDeductionsExcGst.add(repaidCarrierDeductionsGst);

        invoiceVo.setRepaidCarrierDeductionsExcGst(repaidCarrierDeductionsExcGst);
        invoiceVo.setRepaidCarrierDeductionsGst(repaidCarrierDeductionsGst);
        invoiceVo.setRepaidCarrierDeductions(repaidCarrierDeductions);

        // Get Non Central Carrier Cost
        BigDecimal nonCentralCarrierCostExcGst = BigDecimal.ZERO;
        BigDecimal nonCentralCarrierCostGst = BigDecimal.ZERO;
        BigDecimal nonCentralCarrierCost = BigDecimal.ZERO;
        if (enableNonCentralized) {
            nonCentralCarrierCostExcGst = msNonCentralTotalVo != null ? msNonCentralTotalVo.getFranchiseCostExcGst() : BigDecimal.ZERO;
            nonCentralCarrierCostGst = msNonCentralTotalVo != null ? msNonCentralTotalVo.getFranchiseCostGst() : BigDecimal.ZERO;
        }
        nonCentralCarrierCost = nonCentralCarrierCost.add(nonCentralCarrierCostExcGst);
        nonCentralCarrierCost = nonCentralCarrierCost.add(nonCentralCarrierCostGst);
        invoiceVo.setNonCentralCarrierCostExcGst(nonCentralCarrierCostExcGst);
        invoiceVo.setNonCentralCarrierCostGst(nonCentralCarrierCostGst);
        invoiceVo.setNonCentralCarrierCost(nonCentralCarrierCost);

        // Get total other payables
        BigDecimal totalOtherPayablesExcGst = BigDecimal.ZERO;
        totalOtherPayablesExcGst = totalOtherPayablesExcGst.add(invoiceVo.getCarrierCreditExcGst());
        totalOtherPayablesExcGst = totalOtherPayablesExcGst.add(invoiceVo.getRepaidCarrierDeductionsExcGst());
        invoiceVo.setTotalOtherPayablesExcGst(totalOtherPayablesExcGst);

        BigDecimal totalOtherPayablesGst = BigDecimal.ZERO;
        totalOtherPayablesGst = totalOtherPayablesGst.add(invoiceVo.getCarrierCreditGst());
        totalOtherPayablesGst = totalOtherPayablesGst.add(invoiceVo.getRepaidCarrierDeductionsGst());
        invoiceVo.setTotalOtherPayablesGst(totalOtherPayablesGst);

        // Get Carrier Cost Deduction
        BigDecimal franchiseCharge = msDeductTotalVo != null ? msDeductTotalVo.getFranchiseCharge() : BigDecimal.ZERO;
        // toantq remove BigDecimal carrierCostDeductionExcGst =
        // franchiseCharge.divide(taxRate, 2, RoundingMode.HALF_UP);
        BigDecimal carrierCostDeductionExcGst = franchiseCharge.divide(BigDecimal.valueOf(1 + taxPercent), BigDecimal.ROUND_HALF_UP);
        BigDecimal carrierCostDeductionGst = franchiseCharge.subtract(carrierCostDeductionExcGst);
        BigDecimal carrierCostDeduction = franchiseCharge;

        invoiceVo.setCarrierCostDeductionExcGst(carrierCostDeductionExcGst);
        invoiceVo.setCarrierCostDeductionGst(carrierCostDeductionGst);
        invoiceVo.setCarrierCostDeduction(carrierCostDeduction);
        // Get teach fees
        invoiceVo.setTechFeesExcGst(BigDecimal.ZERO);
        invoiceVo.setTechFeesGst(BigDecimal.ZERO);

        // Get marketing fees
        invoiceVo.setMarketingFeesExcGst(BigDecimal.ZERO);
        invoiceVo.setMarketingFeesGst(BigDecimal.ZERO);

        // Get total costs
        BigDecimal totalCostsExcGst = BigDecimal.ZERO;
        totalCostsExcGst = totalCostsExcGst.add(invoiceVo.getCarrierCostDeductionExcGst());
        totalCostsExcGst = totalCostsExcGst.add(invoiceVo.getTechFeesExcGst());
        totalCostsExcGst = totalCostsExcGst.add(invoiceVo.getMarketingFeesExcGst());
        invoiceVo.setTotalCostsExcGst(totalCostsExcGst);

        BigDecimal totalCostsGst = BigDecimal.ZERO;
        totalCostsGst = totalCostsGst.add(invoiceVo.getCarrierCostDeductionGst());
        totalCostsGst = totalCostsGst.add(invoiceVo.getTechFeesGst());
        totalCostsGst = totalCostsGst.add(invoiceVo.getMarketingFeesGst());
        invoiceVo.setTotalCostsGst(totalCostsGst);

        // Get net payables
        BigDecimal netPayablesExcGst = BigDecimal.ZERO;
        netPayablesExcGst = netPayablesExcGst.add(invoiceVo.getGrossPayablesExcGst());
        netPayablesExcGst = netPayablesExcGst.add(invoiceVo.getTotalOtherPayablesExcGst());
        netPayablesExcGst = netPayablesExcGst.subtract(invoiceVo.getTotalCostsExcGst());
        invoiceVo.setNetPayablesExcGst(netPayablesExcGst);

        // Get gst payables
        BigDecimal netPayablesGst = BigDecimal.ZERO;
        netPayablesGst = netPayablesExcGst.multiply(BigDecimal.valueOf(taxPercent)).setScale(2, BigDecimal.ROUND_HALF_UP);
        invoiceVo.setNetPayablesGst(netPayablesGst);

        // Get total net payable
        BigDecimal totalNetPayable = BigDecimal.ZERO;
        totalNetPayable = totalNetPayable.add(invoiceVo.getNetPayablesExcGst());
//        totalNetPayable = totalNetPayable.add(invoiceVo.getNetPayablesGst());
//        totalNetPayable = totalNetPayable.add(invoiceVo.getNonCentralCarrierCostExcGst());
        invoiceVo.setTotalNetPayable(totalNetPayable);

        //Get total payable
        BigDecimal totalPayableGst = BigDecimal.ZERO;
        totalPayableGst = totalPayableGst.add(invoiceVo.getNetPayablesGst());
        totalPayableGst = totalPayableGst.add(invoiceVo.getNonCentralCarrierCostGst());
        invoiceVo.setTotalPayableGst(totalPayableGst);

        BigDecimal totalPayableExcGst = BigDecimal.ZERO;
        totalPayableExcGst = totalPayableExcGst.add(invoiceVo.getTotalNetPayable());
        totalPayableExcGst = totalPayableExcGst.add(invoiceVo.getNonCentralCarrierCostExcGst());
        invoiceVo.setTotalPayableExcGst(totalPayableExcGst);

        BigDecimal totalPayable = BigDecimal.ZERO;
        totalPayable = totalPayable.add(totalPayableExcGst);
        totalPayable = totalPayable.add(totalPayableGst);
        invoiceVo.setTotalPayable(totalPayable);

        return invoiceVo;
    }

    // Get payment margin details.
    public List<FranchisePayableMSMarginVo> getPaymentMarginDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSMarginDao payableMSMarginDao = new FranchisePayableMSMarginDao();
        return payableMSMarginDao.getPaymentMarginDetails(filter);
    }

    public FranchisePayableMSMarginVo getPaymentMarginDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSMarginDao payableMSMarginDao = new FranchisePayableMSMarginDao();
        return payableMSMarginDao.getPaymentMarginDetailsTotal(filter);
    }

    public Integer getPaymentMarginDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSMarginDao payableMSMarginDao = new FranchisePayableMSMarginDao();
        return payableMSMarginDao.getPaymentMarginDetailsRecordCount(filter);
    }

    // Get carrier cost deduction.
    public List<FranchisePayableMSDeductVo> getCarrierCostDeduction(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSDeductDao payableMSDeductDao = new FranchisePayableMSDeductDao();
        return payableMSDeductDao.getCarrierCostDeduction(filter);
    }

    public FranchisePayableMSDeductVo getCarrierCostDeductionTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSDeductDao payableMSDeductDao = new FranchisePayableMSDeductDao();
        return payableMSDeductDao.getCarrierCostDeductionTotal(filter);
    }

    public Integer getCarrierCostDeductionRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSDeductDao payableMSDeductDao = new FranchisePayableMSDeductDao();
        return payableMSDeductDao.getCarrierCostDeductionRecordCount(filter);
    }

    // Get 61+ days payment credit details.
    public List<FranchisePayableMS61DaysVo> get61DaysPaymentCreditDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMS61DaysDao payableMS61DaysDao = new FranchisePayableMS61DaysDao();
        return payableMS61DaysDao.get61DaysPaymentCreditDetails(filter);
    }

    public FranchisePayableMS61DaysVo get61DaysPaymentCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMS61DaysDao payableMS61DaysDao = new FranchisePayableMS61DaysDao();
        return payableMS61DaysDao.get61DaysPaymentCreditDetailsTotal(filter);
    }

    public Integer get61DaysPaymentCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMS61DaysDao payableMS61DaysDao = new FranchisePayableMS61DaysDao();
        return payableMS61DaysDao.get61DaysPaymentCreditDetailsRecordCount(filter);
    }

    // Get non centralized details.
    public List<FranchisePayableMSNonCentralVo> getNonCentralizedDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSNonCentralDao payableMSNonCentralDao = new FranchisePayableMSNonCentralDao();
        return payableMSNonCentralDao.getNonCentralizedDetails(filter);
    }

    public FranchisePayableMSNonCentralVo getNonCentralizedDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSNonCentralDao payableMSNonCentralDao = new FranchisePayableMSNonCentralDao();
        return payableMSNonCentralDao.getNonCentralizedDetailsTotal(filter);
    }

    public Integer getNonCentralizedDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSNonCentralDao payableMSNonCentralDao = new FranchisePayableMSNonCentralDao();
        return payableMSNonCentralDao.getNonCentralizedDetailsRecordCount(filter);
    }

    // Get carrier credit details.
    public List<FranchisePayableMSCreditVo> getCarrierCreditDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSCreditDao payableMSCreditDao = new FranchisePayableMSCreditDao();
        return payableMSCreditDao.getCarrierCreditDetails(filter);
    }

    public FranchisePayableMSCreditVo getCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSCreditDao payableMSCreditDao = new FranchisePayableMSCreditDao();
        return payableMSCreditDao.getCarrierCreditDetailsTotal(filter);
    }

    public Integer getCarrierCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSCreditDao payableMSCreditDao = new FranchisePayableMSCreditDao();
        return payableMSCreditDao.getCarrierCreditDetailsRecordCount(filter);
    }

    // Get over payment.
    public List<FranchisePayableMSOverpaymentVo> getOverpayment(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSOverpaymentDao payableMSOverpaymentDao = new FranchisePayableMSOverpaymentDao();
        return payableMSOverpaymentDao.getOverpayment(filter);
    }

    public FranchisePayableMSOverpaymentVo getOverpaymentTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSOverpaymentDao payableMSOverpaymentDao = new FranchisePayableMSOverpaymentDao();
        return payableMSOverpaymentDao.getOverpaymentTotal(filter);
    }

    public Integer getOverpaymentRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableMSOverpaymentDao payableMSOverpaymentDao = new FranchisePayableMSOverpaymentDao();
        return payableMSOverpaymentDao.getOverpaymentRecordCount(filter);
    }
}