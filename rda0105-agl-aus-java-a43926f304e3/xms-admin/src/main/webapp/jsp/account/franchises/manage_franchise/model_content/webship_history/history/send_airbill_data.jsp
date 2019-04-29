<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!-- Modal -->
<s:form id="form-send-airbill">
    <div class="form-group">
        <label class="control-label" for="inputName">
            <xms:localization text="Email Address"/>
            <span class="s30"> *</span>
        </label>
        <s:hidden name="shipmentId" value="%{shipmentId}"></s:hidden>
        <s:hidden name="sendAirlbillHistoryFillterModel.templateEmail" value="Send Airbill Label"></s:hidden>
        <s:textfield name="sendAirlbillHistoryFillterModel.emails" id="list_email" placeholder="" class="form-control"
                     data-placement="top"/>
        <div class="remember_me" id="error_send_airbill">
        </div>
    </div>

</s:form>
<!-- End Modal -->