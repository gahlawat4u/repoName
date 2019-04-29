<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_countr_list_edit">
    <div class="form-group">
        <p class="">
            Import Type :<span class="s30">*</span>
        </p>
        <select class="form-control s52">
            <option>DHL Express Worldwide</option>
            <option value="0">Thailand</option>
            <option value="1">Trinidad and Tobago</option>
            <option value="2">Turkey</option>
        </select>
    </div>
    <div class="form-group">
        <input id="" class="w10" type="file" placeholder="Inlcude some file">
    </div>
</s:form>