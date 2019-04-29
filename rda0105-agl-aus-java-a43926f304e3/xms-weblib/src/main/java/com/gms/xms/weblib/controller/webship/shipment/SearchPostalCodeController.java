package com.gms.xms.weblib.controller.webship.shipment;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ship.SearchPostalCodeModel;
import com.gms.xms.persistence.service.postalcode.IPostalCodeService;
import com.gms.xms.persistence.service.postalcode.PostalCodeServiceImp;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeFilter;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from SearchPostalCodeController
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeController extends JsonBaseController {

    private static final long serialVersionUID = 5182500102453508173L;
    private String cityName;
    private String countryId;
    private String postalCode;
    private String isSender;
    private List<SearchPostalCodeModel> cities;

    public String search() {
        IPostalCodeService postalCodeService = new PostalCodeServiceImp();
        try {
            List<SearchPostalCodeVo> postalCodeVos = postalCodeService.searchPostalCode(this.buildFilter());
            List<SearchPostalCodeModel> postalCodeModels = new ArrayList<SearchPostalCodeModel>();
            postalCodeModels = ModelUtils.createListModelFromVo(postalCodeVos, SearchPostalCodeModel.class);
            this.setCities(postalCodeModels);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    private SearchPostalCodeFilter buildFilter() {
        SearchPostalCodeFilter filter = new SearchPostalCodeFilter();
        filter.setCityName(cityName);
        filter.setPostalCode(postalCode);
        filter.setCountryId(Long.valueOf(countryId));
        return filter;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<SearchPostalCodeModel> getCities() {
        return cities;
    }

    public void setCities(List<SearchPostalCodeModel> cities) {
        this.cities = cities;
    }

    public String getIsSender() {
        return isSender;
    }

    public void setIsSender(String isSender) {
        this.isSender = isSender;
    }
}