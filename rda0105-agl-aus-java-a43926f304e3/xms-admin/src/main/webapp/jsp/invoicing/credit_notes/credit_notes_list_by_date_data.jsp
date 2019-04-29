<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:i18n_select cssClass="form-control" list="listCreditCode" id="listCreditCode" name="creditNoteModels.creditCode"
               onchange="showInforCreditCode($(this));" headerKey="" headerValue="Select a Credit Note"/>
<s:hidden id="credit-note-codes" name="creditNotesPageModel.creditNoteCodes"/>