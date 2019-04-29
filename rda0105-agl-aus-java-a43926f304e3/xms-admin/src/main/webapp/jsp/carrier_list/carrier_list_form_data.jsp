<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="carrier-form">
    <div class="form-group">
        <p class="">
            <xms:localization text="Carrier Name"/>
            :<span class="s30">*</span>
        </p>
        <s:if test="carrier.allowChangeName == 0">
            <s:textfield name="carrier.serviceName" cssClass="form-control" readonly="true"/>
        </s:if>
        <s:else>
            <s:textfield name="carrier.serviceName" cssClass="form-control"/>
            <span class="text-danger"><s:fielderror fieldName="carrier.serviceName"/></span>
        </s:else>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="Carrier Type"/>
            :<span class="s30">*</span>
        </p>
        <label class="radio-inline b14">
            <input class="b15" type="radio" value="0" name="carrier.carrierType"
                   <s:if test="carrier.carrierType == 0 || carrier.carrierType == ''">checked="checked"</s:if>>
            <xms:localization text="International"/>
        </label>
        <label class="radio-inline b14">
            <input class="b15" type="radio" value="1" name="carrier.carrierType"
                   <s:if test="carrier.carrierType == 1">checked="checked"</s:if>>
            <xms:localization text="Domestic"/>
        </label>
        <label class="radio-inline b14">
            <input class="b15" type="radio" value="2" name="carrier.carrierType"
                   <s:if test="carrier.carrierType == 2">checked="checked"</s:if>>
            <xms:localization text="Both"/>
        </label>
        <span class="text-danger"><s:fielderror fieldName="carrier.carrierType"/></span>
    </div>
    <div class="form-group">
        <label class="radio-inline b14">
            <input type="checkbox" name="carrier.inactive" value="0"
                   <s:if test="carrier.inactive == 0">checked="checked"</s:if> />
            <xms:localization text="Active"/>
        </label>
    </div>
    <div class="form-group">
        <label class="radio-inline b14">
            <input type="checkbox" name="carrier.nonCentralized" value="1"
                   <s:if test="carrier.nonCentralized == 1">checked="checked"</s:if> />
            <xms:localization text="Non Centralized"/>
        </label>
    </div>
    <s:hidden name="carrier.serviceId"/>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>
<script type="text/javascript">
    $(document).ready(function () {
        $('input[name="carrier.serviceName"]').keypress(function (e) {
            if (e.which == 13) {
                e.preventDefault();
                $(".ui-dialog-buttonset button").each(function () {
                    if ($(this).text() == "Save") {
                        $(this).trigger("click");
                    }
                });
            }
        });
    });
</script>