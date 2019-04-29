/*
package test.com.gms.xms.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class RenderTest {
    public static void main(String[] args) throws Exception {

	String templatePath = "D:/template.xls";
	String outPutFilePath = "D:/result.xls";
	List<TestModel> summaries = new LinkedList<TestModel>();
	int carrierCost = 1;
	int carrierCostBaseCharge = 1;
	int customerCostBaseCharge = 1;
	int marginOnBaseCharge = 1;

	int totCarrierCost = 0;
	int totCarrierCostBaseCharge = 0;
	int totCustomerCostBaseCharge = 0;
	int totMarginOnBaseCharge = 0;

	int userLevel = 3;
	for (int i = 1; i < 5; i++) {
	    TestModel summary = new TestModel();
	    summary.setCustomerNumber(String.valueOf(i));
	    summary.setCustomerName("Customer #" + String.valueOf(i));
	    summary.setSalesRep("Sale Reps #" + String.valueOf(i));
	    carrierCost++;
	    carrierCostBaseCharge++;
	    customerCostBaseCharge++;
	    marginOnBaseCharge++;
	    summary.setCarrierCost(String.valueOf(carrierCost));
	    summary.setCarrierCostBaseCharge(String.valueOf(carrierCostBaseCharge));
	    summary.setCustomerCostBaseCharge(String.valueOf(customerCostBaseCharge));
	    summary.setMarginOnBaseCharge(String.valueOf(marginOnBaseCharge));

	    totCarrierCost += carrierCost;
	    totCarrierCostBaseCharge += carrierCostBaseCharge;
	    totCustomerCostBaseCharge += customerCostBaseCharge;
	    totMarginOnBaseCharge += marginOnBaseCharge;
	    summaries.add(summary);
	}
	TestModel total = new TestModel();
	total.setCarrierCost(String.valueOf(totCarrierCost));
	total.setCarrierCostBaseCharge(String.valueOf(totCarrierCostBaseCharge));
	total.setCustomerCostBaseCharge(String.valueOf(totCustomerCostBaseCharge));
	total.setMarginOnBaseCharge(String.valueOf(totMarginOnBaseCharge));

	Context data = new Context();
	data.putVar("userLevel", userLevel);
	data.putVar("summaryList", summaries);
	data.putVar("totalSummary", total);
	data.putVar("totalCustomer", "5");
	data.putVar("currencySymbol", "$");
	try {
	    InputStream is = new FileInputStream(new File(templatePath));
	    OutputStream os = new FileOutputStream(new File(outPutFilePath));
	    JxlsHelper.getInstance().processTemplate(is, os, data);
	} catch (Exception e) {
	    throw e;
	}

    }
}
*/