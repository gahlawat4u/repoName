<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:form id="webship-group-form">
    <div id="add-carriers" title="New Web Freight Group">
        <div class="form-group">
            <p class="">
                <xms:localization text="Web Freight Group Name:"/>
                <span class="s30">*</span>
            </p>
            <s:textfield name="webshipGroup.webshipGroupName" class="form-control alloptions"/>
            <span class="text-danger"><s:fielderror fieldName="webshipGroup.webshipGroupName"/></span>
        </div>
        <div class="form-group">
            <p class="">
                <xms:localization text="Owner Customer:"/>
            </p>
            <s:select name="webshipGroup.ownerCustomerId" list="customerOwerList" value="webshipGroup.ownerCustomerId"
                      listKey="customerCode" listValue="owner"/>
            <span class="text-danger"><s:fielderror fieldName="webshipGroup.ownerCustomerId"/></span>
        </div>
    </div>
    <s:hidden name="webshipGroup.webshipGroupId"/>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>
