<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:fielderror/>
<s:actionerror/>
<s:actionmessage/>
<s:select name="franchiseCode"
          list="franchises"
          class="form-control"
          listKey="code" headerKey="-1"
          headerValue="Select a Franchise"
          listValue="name"
          onchange="changeFranchise($(this).val())"
          id="sel_franchise"
          value="franchiseCode"></s:select>