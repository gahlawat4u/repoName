<%@ taglib prefix="xms" uri="/xms-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!-- AddNote -->
<div id="addnote" title="Add Note">
    <s:form id="form-add-note">
        <div class="form-group">
            <label class="control-label" for="inputName">
                <xms:localization text="Note"/>
                <span class="s30"> *</span>
            </label>
            <s:hidden value='%{shipmentNoteFilterModel.shipmentId}' name="shipmentNoteModel.shipmentId"></s:hidden>
            <textarea class="form-control alloptions" name="shipmentNoteModel.note" rows="10" data-placement="top" placeholder=""></textarea>
        </div>
        <s:actionerror/>
        <s:actionmessage/>
        <s:fielderror/>
    </s:form>
</div>
<!-- End AddNote -->