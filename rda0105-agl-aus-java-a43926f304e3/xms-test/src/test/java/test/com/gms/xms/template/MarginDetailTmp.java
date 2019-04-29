/*
package test.com.gms.xms.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.franchisepayable.FranchisePayableMSCreditModel;

public class MarginDetailTmp extends MainRender {

	@Override
	void render() {
		this.setOutputFilePath("output/test.html");
		this.setTemplateDir(AppConstants.class.getClassLoader()
				.getResource("templates/pdf/franchise_payable_reports_ms").getPath());
		this.setTemplateFilePath("fpb_credit.ftl");
		
		Map<String, Object> data = new HashMap<String, Object>();
		List<FranchisePayableMSCreditModel> carrierCreditDetails = new ArrayList<FranchisePayableMSCreditModel>();
		
		FranchisePayableMSCreditModel creditModel = new FranchisePayableMSCreditModel();
		creditModel.setCustomerName("test airbill number1");
	    creditModel.setInvoiceNumber("test airbill number1");
	    creditModel.setAirbillNumber("test airbill number1");
	    creditModel.setCarrierAmount("test airbill number1");
	    creditModel.setCarrierAmountGst("test airbill number1");
	    creditModel.setCarrierCredit("test airbill number1");
	    creditModel.setCarrierCreditGst("test airbill number1");
	    creditModel.setCustomerAmount("test airbill number1");
	    creditModel.setCustomerAmountGst("test airbill number1");
		carrierCreditDetails.add(creditModel);
		
		creditModel = new FranchisePayableMSCreditModel();
		creditModel.setCustomerName("test airbill number2");
	    creditModel.setInvoiceNumber("test airbill number2");
	    creditModel.setAirbillNumber("test airbill number2");
	    creditModel.setCarrierAmount("test airbill number2");
	    creditModel.setCarrierAmountGst("test airbill number2");
	    creditModel.setCarrierCredit("test airbill number2");
	    creditModel.setCarrierCreditGst("test airbill number2");
	    creditModel.setCustomerAmount("test airbill number2");
	    creditModel.setCustomerAmountGst("test airbill number2");
	    carrierCreditDetails.add(creditModel);
		data.put("carrierCreditDetails", carrierCreditDetails);
		
		AppUtils.template2File(this.getOutputFilePath(), false, this.getTemplateDir(), this.getTemplateFilePath(), data);
	}
	public static void main(String[] args) {
		MarginDetailTmp detailTmp = new MarginDetailTmp();
		detailTmp.render();
		System.out.println("Done");
	}

}
*/
