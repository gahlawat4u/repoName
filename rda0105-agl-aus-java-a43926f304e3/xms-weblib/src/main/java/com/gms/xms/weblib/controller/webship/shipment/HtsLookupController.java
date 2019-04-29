package com.gms.xms.weblib.controller.webship.shipment;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.HtsGoodModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.htsgood.HtsGoodServiceImp;
import com.gms.xms.persistence.service.htsgood.IHtsGoodService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.HtsGoodVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.List;

/**
 * Posted from HtsLookupController
 * <p>
 * Author @author HungNT Jan 29, 2016
 */
public class HtsLookupController extends JsonBaseController {
    private static final long serialVersionUID = 7718712816935150753L;
    private List<HtsGoodModel> listHtsGood;
    private HtsGoodModel htsGoodModel;
    private String description;
    private List<CountryModel> countryList;
    private Integer index;

    public String search() {
        try {
            IHtsGoodService service = new HtsGoodServiceImp();
            List<HtsGoodVo> htsGoodVos = service.getHtsGoodByDescription(this.getDescription());
            List<HtsGoodModel> htsGoodModels = ModelUtils.createListModelFromVo(htsGoodVos, HtsGoodModel.class);
            this.setListHtsGood(htsGoodModels);
            this.setSession("HTS Search Description", this.getDescription());
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String lookup() {
        try {
            String desc = (String) this.getSession("HTS Search Description");
            IHtsGoodService service = new HtsGoodServiceImp();
            List<HtsGoodVo> htsGoodVos = service.getHtsGoodByDescription(desc);
            List<HtsGoodModel> htsGoodModels = ModelUtils.createListModelFromVo(htsGoodVos, HtsGoodModel.class);
            this.setListHtsGood(htsGoodModels);
            this.setDescription(desc);
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        CountryModel headerValue = new CountryModel();
        headerValue.setCountryId("0");
        headerValue.setCountryName("Please select a country.");
        countryModels.remove(0);
        countryModels.add(0, headerValue);
        this.setCountryList(countryModels);
    }

    public List<HtsGoodModel> getListHtsGood() {
        return listHtsGood;
    }

    public void setListHtsGood(List<HtsGoodModel> listHtsGood) {
        this.listHtsGood = listHtsGood;
    }

    public HtsGoodModel getHtsGoodModel() {
        return htsGoodModel;
    }

    public void setHtsGoodModel(HtsGoodModel htsGoodModel) {
        this.htsGoodModel = htsGoodModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
