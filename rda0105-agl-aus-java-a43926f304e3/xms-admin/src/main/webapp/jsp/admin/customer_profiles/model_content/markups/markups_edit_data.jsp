<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_markup_edit">
    <s:hidden name="filterMarkup.page"></s:hidden>
    <s:hidden name="filterMarkup.pageSize"></s:hidden>
    <s:hidden name="filterMarkup.profileId"></s:hidden>
    <s:hidden name="isEditMarkup" value="1"></s:hidden>
    <s:hidden name="markupModel.accessorialId"></s:hidden>
    <s:hidden name="markupModel.profileId"></s:hidden>
    <div class="form-group">
        <label><xms:localization text="ID"/> :</label> <s:textfield name="markupModel.code"
                                                                    class="form-control alloptions"
                                                                    readonly="true"></s:textfield>
    </div>
    <div class="form-group">
        <label><xms:localization text="Description"/> :</label> <s:textfield name="markupModel.description"
                                                                             class="form-control alloptions"
                                                                             readonly="true"></s:textfield>
    </div>
    <div class="form-group">
        <label><xms:localization text="Markup Type"/> :</label> <s:textfield name="markupModel.typeName"
                                                                             class="form-control alloptions"
                                                                             readonly="true"></s:textfield>
    </div>
    <div class="form-group">
        <label><xms:localization text="Amount"/> :</label> <s:textfield name="markupModel.amount"
                                                                        onkeypress="return formartNumber(event,this,true);"
                                                                        class="form-control alloptions"></s:textfield>
    </div>
</s:form>
