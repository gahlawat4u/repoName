<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="remember_me">
    <ul style="margin-left: 5px; margin-top: 10px;list-style: none;">
        <li style="color: #F00;"><s:fielderror/></li>
        <li style="color: #F00;"><s:actionerror/></li>
    </ul>
</div>
<table class="table" style="font-size: 11px;">
    <tr>
        <td class="td1">ID :</td>
        <td class="td2" colspan="2"><s:textfield name="menuModel.menuId" class="form-control alloptions"
                                                 readonly="true"></s:textfield></td>
    </tr>
    <tr>
        <s:hidden name="isEditMenu" value="1"></s:hidden>
        <td class="td1"><xms:localization text="Label"/> :</td>
        <td class="td2" colspan="2"><s:textfield name="menuModel.menuName"
                                                 class="form-control alloptions"></s:textfield></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Hidden"/>? :</td>
        <td class="td2" colspan="2"><s:checkbox name="menuModel.hidden"></s:checkbox></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Admin Level"/> :</td>
        <td class="td2" colspan="2"><s:textfield name="menuModel.userLevelId"
                                                 class="form-control alloptions"></s:textfield></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Other Admin Levels(comma-separated)"/>? :</td>
        <td class="td2" colspan="2"><s:textfield name="menuModel.otherLevel"
                                                 class="form-control alloptions"></s:textfield></td>
    </tr>
</table>
