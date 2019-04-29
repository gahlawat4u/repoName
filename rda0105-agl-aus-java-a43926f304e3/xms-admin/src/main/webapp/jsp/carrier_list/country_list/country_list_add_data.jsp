<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_countr_list_add">
    <div class="form-group">
        <s:hidden name="isAddCountry" value="1"></s:hidden>
        <p class="">
            <xms:localization text="Country Code:"/><span class="s30">*</span>
        </p>
        <s:textfield name="countryModel.countryCode" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="Country Name:"/><span class="s30">*</span>
        </p>
        <s:textfield name="countryModel.countryName" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <div class="form-group">
        <p class=""><xms:localization text="GST Percent:"/></p>
        <s:textfield name="countryModel.gstPercent" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
</s:form>

