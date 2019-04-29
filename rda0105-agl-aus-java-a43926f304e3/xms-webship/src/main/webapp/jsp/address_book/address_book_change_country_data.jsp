<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<s:if test="stateList!=null && stateList.size>0">
    <s:select name="addressBookModels[%{index}].state" value="state"
              list="stateList" listKey="stateName" listValue="stateName"
              cssClass="form-control"/>
</s:if>
<s:else>
    <s:textfield name="addressBookModels[%{index}].state" value="%{state}"
                 cssClass="form-control sel-box" data-placement="top"/>
</s:else>