<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:select name="franchises.franchiseCode" list="franchises"
          class="form-control" listKey="code" headerKey="-1"
          headerValue="Select a Franchise" listValue="name"
          onchange="changeFranchise($(this).val())" id="sel_franchise"
          value="%{franchiseCode}"></s:select>
<s:hidden name="franchiseCode"></s:hidden>