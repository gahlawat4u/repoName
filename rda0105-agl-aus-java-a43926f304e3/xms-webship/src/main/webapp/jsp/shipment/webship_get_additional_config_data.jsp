<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:iterator value="listAdditionalConfig" status="addConCount">
    <div class="form-group">
        <s:if test="addConType=='checkbox'">
            <label class="fw0">
                <input data-group="<s:property value="addConCode"/>" tabindex="5"
                       id="service_cons_<s:property value="#addConCount.index" />"
                       type="<s:property value="addConType" />"
                       name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].value" value="1"
                       onclick="showServiceAddConDetails(<s:property value="#addConCount.index"/>)"/> &nbsp;
                <s:property value="addConName"/>
            </label>
        </s:if>
        <s:else>
            <label class="control-label">
                <s:property value="addConName"/>
            </label>
            <input tabindex="5" id="<s:property value="#addConCount.index" />" type="<s:property value="addConType" />"
                   name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].value" value="1"/>
        </s:else>
        <input type="hidden" name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].addConName"
               value="<s:property value="addConName"/>"/>
        <input type="hidden" name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].addConCode"
               value="<s:property value="addConCode"/>"/>
        <s:if test="!listProperties.isEmpty()">
            <div class="row" id="service_cons_<s:property value="#addConCount.index" />_add" style="display: none;">
                <s:iterator value="listProperties" status="propCount" var="prop">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <s:iterator value="listAccessorial">
                                <%-- <s:if test="code == addConCode">
                                    test
                                </s:if> --%>
                            </s:iterator>
                            <input type="hidden" value="<s:property value="addConDetailName" />"
                                   name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].listProperties[<s:property value="#propCount.index"/>].addConDetailName"/>
                            <input type="hidden" value="<s:property value="addConDetailCode" />"
                                   name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].listProperties[<s:property value="#propCount.index"/>].addConDetailCode"/>
                            <s:if test="addConDetailType == 'list'">
                                <label class="control-label">
                                    <s:property value="addConDetailName"/>
                                </label>
                                <select class="form-control"
                                        name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].listProperties[<s:property value="#propCount.index"/>].value">
                                    <s:iterator value="%{getList()}">
                                        <option value="<s:property />"><s:property/></option>
                                    </s:iterator>
                                </select>
                            </s:if>
                            <s:elseif test="addConDetailType == 'checkbox'">
                                <label class="fw0">
                                    <input type="checkbox" tabindex="5"
                                           name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].listProperties[<s:property value="#propCount.index"/>].value"/>
                                    &nbsp;
                                    <s:property value="addConDetailName"/>
                                </label>
                            </s:elseif>
                            <s:else>
                                <label class="control-label">
                                    <s:property value="addConDetailName"/>
                                </label>
                                <input type="<s:property value="addConDetailType"/>" class="form-control"
                                       name="shipmentPage.addCons[<s:property value="#addConCount.index"/>].listProperties[<s:property value="#propCount.index"/>].value">
                            </s:else>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </s:if>
    </div>
    <s:set var="totalConCount"><s:property value="%{#addConCount.count}"/></s:set>
</s:iterator>
<s:if test="listAdditionalConfig.size==0">
    <s:set var="totalConCount">0</s:set>
</s:if>
<div class="form-group" id="divAglWarranty">
    <label class="fw0">
        <input data-group="aglWarranty" tabindex="5" id="service_cons_<s:property value="#totalConCount" />"
               type="checkbox" name="shipmentPage.addCons[<s:property value="#totalConCount"/>].value" value="1"
               onclick="showServiceAddConDetails(<s:property value="#totalConCount"/>)" disabled="disabled"/> &nbsp;
        <xms:localization text="AGL Warranty"/>
    </label>
    <input type="hidden" name="shipmentPage.addCons[<s:property value="#totalConCount"/>].value"/>
    <input type="hidden" name="shipmentPage.addCons[<s:property value="#totalConCount"/>].addConName"
           value="agl Warranty"/>
    <input type="hidden" name="shipmentPage.addCons[<s:property value="#totalConCount"/>].addConCode"
           value="aglWarranty"/>
</div>
<script type="text/javascript">
    var isAglWarranty = <s:property value="isAglWarranty"/>;
    $(document).ready(function () {
        if (isAglWarranty) {
            $("#divAglWarranty").show();
            $("#divAglWarranty").find("input[type='checkbox']").attr("checked", true);
            $("#divAglWarranty").find("input[name='shipmentPage.addCons[<s:property value="#totalConCount"/>].value']").val(1);
        } else {
            $("#divAglWarranty").hide();
            $("#divAglWarranty").find("input[type='checkbox']").attr("checked", false);
            $("#divAglWarranty").find("input[name='shipmentPage.addCons[<s:property value="#totalConCount"/>].value']").val(0);
        }
    });
    function showServiceAddConDetails(id) {
    	
    	
    	var isChecked = $("#service_cons_" + id).is(':checked');
        if (isChecked) {
        var extraTask =	$("input[name='shipmentPage.addCons["+id+"].addConName']").val();
       // alert(""+extraTask);
        	if( extraTask == 'Dangerous Goods'){
        		var conformation = confirm(" Please call AGL customer service on 08 94793399 to check if this DG can be sent using this service. ");
        		if(conformation == true){
           		 $("#service_cons_" + id + "_add").show();
           	}else{
           		$("#service_cons_" + id + "").attr('checked', false);
        		$("#service_cons_" + id + "_add").hide();
           	}
        	}
        	else if(extraTask == 'Time Critical Service Request'){
        		var conformation = confirm(" Should your consignment be time critical please call our AGL customer service team on 08 94793399 so that we can assist you with this. ");
        		if(conformation == true){
           		 $("#service_cons_" + id + "_add").show();
           	}else{
           		$("#service_cons_" + id + "").attr('checked', false);
        		$("#service_cons_" + id + "_add").hide();
           	}
        	}else
        	{
        		$("#service_cons_" + id + "").attr('checked', true);
        		$("#service_cons_" + id + "_add").show();
        	}
        } else {
            $("#service_cons_" + id + "_add").hide();
        }
    }
</script>