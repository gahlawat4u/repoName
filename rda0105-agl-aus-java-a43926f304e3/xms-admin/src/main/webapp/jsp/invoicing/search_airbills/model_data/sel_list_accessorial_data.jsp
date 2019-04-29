<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:select list="listAccessorial" name="searchAirbillFilter.accessorialName" listKey="description"
          listValue="description" cssClass="form-control" headerKey="" headerValue="All"/>