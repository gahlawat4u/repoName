<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!-- Modal -->
<s:if test="hasActionErrors() || hasFieldErrors()">
    <div class="alert alert-danger" role="alert">
        <s:fielderror/>
        <s:actionerror/>
    </div>
</s:if>
<s:elseif test="hasActionMessages()">
    <div class="alert alert-success" role="alert">
        <s:actionmessage/>
    </div>
</s:elseif>
<form id="dimension-form">
    <div class="row">
        <div class="col-lg-6">
            <s:hidden name="dimension.id"/>
            <s:hidden name="dimension.webshipId"/>
            <div class="form-group">
                <label class="control-label" for="inputName"> <xms:localization text="Name"/><span class="s30"> *</span></label>
                <s:textfield name="dimension.name" cssClass="form-control alloptions"/>
            </div>
            <div class="form-group">
                <label class="control-label" for="inputName"> L<span class="s30"> *</span></label>
                <s:textfield name="dimension.l" cssClass="form-control alloptions"
                             onkeypress="return formartNumber(event,this,false);"/>
            </div>
            <div class="form-group">
                <label class="control-label" for="inputName"> H<span class="s30"> *</span></label>
                <s:textfield name="dimension.h" cssClass="form-control alloptions"
                             onkeypress="return formartNumber(event,this,false);"/>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="control-label" for="inputName"> W<span class="s30"> *</span></label>
                <s:textfield name="dimension.w" cssClass="form-control alloptions"
                             onkeypress="return formartNumber(event,this,false);"/>
            </div>
            <div class="form-group">
                <label class="control-label" for="inputName"><xms:localization text="Comment"/></label>
                <s:textarea name="dimension.comment" cssClass="form-control alloptions"/>
            </div>
        </div>
    </div>
</form>
<!-- End Modal -->