<%@ taglib prefix="xms" uri="/xms-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!-- AddNote -->
<div class="row" style="width: 400px;">
    <div class="col-xs-12">
        <s:form id="form-add-note">
            <div class="form-group">
                <label class="control-label" for="inputName">
                    <xms:localization text="Note"/>
                    <span class="s30"> *</span>
                </label>
                <s:hidden value='%{shipmentId}' name="noteModel.shipmentId"></s:hidden>
                <textarea class="form-control alloptions" name="noteModel.note" rows="10" maxlength="25"
                          data-placement="top" placeholder=""></textarea>
            </div>
        </s:form>
    </div>
</div>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>
<!-- End AddNote -->