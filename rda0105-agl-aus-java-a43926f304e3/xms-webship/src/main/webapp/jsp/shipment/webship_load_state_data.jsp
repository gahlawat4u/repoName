<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<label class="control-label" for="inputName"> <xms:localization text="State/Province"/></label>
<s:if test="isSender">
    <s:if test="listStates.isEmpty()">
        <s:textfield name="shipmentPage.senderAddress.state" class="form-control" data-placement="top"
                     data-toggle="tooltip"
                     data-original-title='<%=WebUtils.getTooltip(request, "Sate/Province", ENCODE_TYPE.JAVASCRIPT)%>'/>
    </s:if>
    <s:else>
        <s:select name="shipmentPage.senderAddress.state" list="listStates" cssClass="form-control" listKey="stateCode"
                  listValue="stateName"/>
    </s:else>
</s:if>
<s:else>
    <s:if test="listStates.isEmpty()">
        <s:textfield name="shipmentPage.receiverAddress.state" class="form-control" data-placement="top"
                     data-toggle="tooltip"
                     data-original-title='<%=WebUtils.getTooltip(request, "Sate/Province", ENCODE_TYPE.JAVASCRIPT)%>'/>
    </s:if>
    
   <!--  previous code  -->
   <%--  <s:else>
        <s:select name="shipmentPage.receiverAddress.state" list="listStates" cssClass="form-control"
                  listKey="stateName" listValue="stateName"/>
    </s:else> --%>
    
   <!--  code by shahabuddin -->
    <s:else>
        <s:select name="shipmentPage.receiverAddress.state" list="listStates" cssClass="form-control"
                  listKey="stateCode" listValue="stateName"/>
    </s:else>
    
</s:else>