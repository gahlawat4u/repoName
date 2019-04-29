<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_state_list_add">
    <s:hidden name="stateModel.countryId" value="%{countryId}"></s:hidden>
    <s:hidden name="stateModel.status" value="0"></s:hidden>
    <s:hidden name="stateModel.id"></s:hidden>
    <div class="form-group">
        <p class=""><xms:localization text="State Name"/> :</p>
        <s:textfield name="stateModel.stateName" class="form-control alloptions"></s:textfield>

    </div>
    <div class="form-group">
        <p class=""><xms:localization text="State Code"/> :</p>
        <s:textfield name="stateModel.stateCode" class="form-control alloptions" maxLength="10"></s:textfield>
    </div>
    <div class="form-group">
        <s:hidden name="isAddState" value="1"></s:hidden>
        <s:hidden name="isEditState" value="1"></s:hidden>
        <p class="">
            <xms:localization text="City Name"/> :<span class="s30">*</span>
        </p>
        <s:textfield name="stateModel.cityName" class="form-control alloptions"></s:textfield>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="City Code"/> :<span class="s30">*</span>
        </p>
        <s:textfield name="stateModel.cityCode" class="form-control alloptions" maxLength="10"></s:textfield>
    </div>
    <div class="form-group">
        <p class=""><xms:localization text="Postcode(from)"/> :</p>
        <s:textfield name="stateModel.fromPostCode" class="form-control alloptions" maxLength="20"></s:textfield>
    </div>
    <div class="form-group">
        <p class=""><xms:localization text="Postcode(To)"/> :</p>
        <s:textfield name="stateModel.toPostCode" class="form-control alloptions" maxLength="20"></s:textfield>
    </div>
    <div class="form-group">
        <p class=""><xms:localization text="DHL Zone Code"/> :</p>
        <s:textfield name="stateModel.dhlZone" class="form-control alloptions"></s:textfield>
    </div>
</s:form>

