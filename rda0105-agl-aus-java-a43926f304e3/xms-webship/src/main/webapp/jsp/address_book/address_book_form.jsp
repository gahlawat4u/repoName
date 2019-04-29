<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="tab-general">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-md-12">
                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
            </div>
        </div>
        <div class="col-lg-12">
            <s:form method="post">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Address Fields"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Contact"/> <span class="s30">*</span>
                                                    </label>
                                                    <s:textfield name="addressBookModel.contactName"
                                                                 cssClass="form-control" cssErrorClass="dangers"/>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.contactName"/> </span>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Company"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.companyName"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="addressBookModel.address1"
                                                                 cssClass="form-control" cssErrorClass="dangers"/>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.address1"/> </span>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address 2"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.address2"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="City"/> <span class="s30">*</span>
                                                    </label>
                                                    <s:textfield name="addressBookModel.city" cssClass="form-control"
                                                                 cssErrorClass="dangers"
                                                                 onkeyup="searchCity($(this).val())"/>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.city"/> </span>
                                                </div>
                                                <div id="city-search"></div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="State/Province"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.state" cssClass="form-control"
                                                                 cssErrorClass="dangers"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Postal Code"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.postalCode"
                                                                 cssClass="form-control" cssErrorClass="dangers"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Country"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:select name="addressBookModel.country" list="countryList"  id="addressCountryId"
                                                              class="form-control" listKey="countryId"
                                                              listValue="countryName"
                                                              cssErrorClass="dangers"></s:select>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.country"/> </span>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Phone"/> <span class="s30">*</span>
                                                    </label>
                                                    <s:textfield name="addressBookModel.phone" cssClass="form-control"
                                                                 cssErrorClass="dangers"/>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.phone"/> </span>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Email"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.email" cssClass="form-control"
                                                                 cssErrorClass="dangers"/>
                                                    <span class="s30"><s:fielderror
                                                            fieldName="addressBookModel.email"/> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="chat-form s11"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Additional Detail"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Department"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.department"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Fax"/>
                                                    </label>
                                                    <s:textfield name="addressBookModel.fax" cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Default Service Type"/>
                                                    </label>
                                                    <s:i18n_select name="addressBookModel.defaultServiceType"
                                                                   list="shipmentTypeList" listKey="shipmentTypeId"
                                                                   listValue="shipmentTypeName" cssClass="form-control"
                                                                   headerKey="0" headerValue="No default"
                                                                   i18nitem="no"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Default Package Type"/></label>
                                                    <s:i18n_select name="addressBookModel.defaultPackageType"
                                                                   list="packageList" listKey="packageId"
                                                                   listValue="packageName" cssClass="form-control"
                                                                   headerKey="0" headerValue="No default"
                                                                   i18nitem="no"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Default Billing Type"/></label>
                                                    <s:i18n_select name="addressBookModel.defaultBillingType"
                                                                   list="billingTypeList" listKey="billingId"
                                                                   listValue="billingName" cssClass="form-control"
                                                                   headerKey="0" headerValue="No default"
                                                                   i18nitem="no"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"><xms:localization
                                                            text="Account No"/></label>
                                                    <s:textfield name="addressBookModel.accountNumber"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="fw0"> <input name="addressBookModel.isResidential"
                                                                               type="checkbox" value="1"
                                                                               <s:if test="addressBookModel.isResidential.equalsIgnoreCase('1')">checked="checked"</s:if> />
                                                        &nbsp; <xms:localization text="Residential Address"/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <s:hidden name="addressBookModel.addressId" value="%{addressId}"/>
                                    <div class=" text-right pal pdt10">
                                        <button class="btn s33 " type="submit">
                                            <xms:localization text="Save"/>
                                        </button>
                                        <s:a action="address_book" class="btn s33 ">
                                            <xms:localization text="Cancel"/>
                                        </s:a>
                                    </div>
                                </div>
                                <div class="chat-form s11"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('body').click(function () {
        $('.sss1').hide();
    });
    function searchCity(cityName) {
        var searchData = {
            cityName: cityName,
            countryId: $("#addressCountryId").val(),
        };
        var resultDiv = "#city-search";
        $.post("address_book_search_city.ix?reqType=json", searchData, function (res) {
            if (res.errorCode == "SUCCESS") {
                $(resultDiv).html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
    function onCityClick(obj) {
        var cityName = $(obj).find("div[data-cityName]").html();
        cityName = cityName.trim();
        var postalCode = $(obj).find("div[data-postalCode]").html();
        postalCode = postalCode.trim();
        var stateCode = $(obj).find("div[data-stateCode]").html();
        stateCode = stateCode.trim();
        console.log(cityName + " - " + postalCode + " - " + stateCode);
        $("input[name='addressBookModel.city']").val(cityName);
        $("input[name='addressBookModel.postalCode']").val(postalCode);
        $("input[name='addressBookModel.state']").val(stateCode);
    }


</script>