<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="!listCreditNoteDate.isEmpty()">
    <s:select list="listCreditNoteDate" id="sel-create-date" cssClass="form-control"></s:select>
</s:if>
<s:elseif test="!franchiseCodeList.isEmpty()">
    <s:select list="franchiseCodeList" id="sel-franchise-code" cssClass="form-control"></s:select>
</s:elseif>
<s:else>
    <input type="text" class="form-control" id="search-customer-code"/>
</s:else>