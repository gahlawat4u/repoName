<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_country_list_edit">
    <div class="form-group <s:if test="fieldErrors.containsKey('countryModel.countryCode')">has-error</s:if>">
        <label class="control-label"> <xms:localization text="Country Code:"/> <span class="s30">*</span>
        </label>
        <s:textfield name="countryModel.countryCode" class="form-control alloptions" placeholder="" maxlength="25"/>
        <span class="text-danger"><s:property value="fieldErrors.get('countryModel.countryCode').get(0)"/></span>
    </div>
    <s:hidden name="countryModel.countryId"/>
    <s:hidden name="countryId"/>
    <s:hidden name="isEditCountry" value="1"/>
    <div class="form-group <s:if test="fieldErrors.containsKey('countryModel.countryName')">has-error</s:if>">
        <label class="control-label"> <xms:localization text="Country Name:"/><span class="s30">*</span>
        </label>
        <s:textfield name="countryModel.countryName" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
        <span class="text-danger"><s:property value="fieldErrors.get('countryModel.countryName').get(0)"/></span>
    </div>
    <div class="form-group <s:if test="fieldErrors.containsKey('countryModel.displayName')">has-error</s:if>">
        <label class="control-label"> <xms:localization text="Display Name:"/> <span class="s30">*</span>
        </label>
        <s:textfield name="countryModel.displayName" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
        <span class="text-danger"><s:property value="fieldErrors.get('countryModel.displayName').get(0)"/></span>
    </div>
    <div class="form-group">
        <label class="control-label"> <xms:localization text="Other Name:"/>
        </label>
        <s:textfield name="countryModel.otherName" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <div class="form-group">
        <label class="control-label"> <xms:localization text="GST Percent:"/>
        </label>
        <s:textfield name="countryModel.gstPercent" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <div class="form-group">
        <label class="control-label"> <xms:localization text="Region:"/>
        </label>
        <s:hidden name="countryModel.dhlCountry.dhlCountryId"></s:hidden>
        <s:textfield name="countryModel.dhlCountry.dhlRegion" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <div class="form-group">
        <label class="control-label"> <xms:localization text="Timezone:"/>
        </label>
        <s:select list="listTimeZone" listKey="key" listValue="value" id="sel_time_zone" cssClass="form-control s52"
                  name="countryModel.dhlCountry.dhlTimeZone" value="countryModel.dhlCountry.dhlTimeZone"></s:select>
    </div>
    <s:hidden name="orderField"/>
    <s:hidden name="orderType"/>
</s:form>

