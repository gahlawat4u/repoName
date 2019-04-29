package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetListCountryDestinationTask
 * <p>
 * Author TANDT
 */
public class GetListCountryDestinationTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListCountryDestinationTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ICountryService service = new CountryServiceImp();
            List<CountryVo> countryVos = service.getCountryList();
            List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
            for (CountryModel country : countryModels) {
                country.setCountryName(country.getCountryCode().concat(" - ").concat(country.getCountryName()));
            }
            CountryModel headerValue = new CountryModel();
            headerValue.setCountryId("0");
            headerValue.setCountryName("Please select a country.");
            countryModels.remove(0);
            countryModels.add(0, headerValue);
            context.put(Attributes.COUNTRY_DESTINATION_LIST_RESULT, countryModels);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
