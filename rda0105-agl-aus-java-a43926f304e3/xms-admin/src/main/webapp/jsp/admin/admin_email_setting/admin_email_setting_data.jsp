<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table mg0">
    <tbody>
    <tr>
        <th class="s42">
            <table class="s36">
                <tbody>
                <tr>
                    <td><xms:localization text="Show"/></td>
                    <td><s:select name="pageSize" list="pageSizes" onchange="onChangePageSize()" id="pageSize"/></td>
                    <td><xms:localization text="Entries"/></td>
                </tr>
                </tbody>
            </table>
        </th>
    </tr>
    </tbody>
</table>
<table class="table table-bordered mg0">
    <thead>
    <tr>
        <th colspan="2"><xms:localization text="Email Address"/></th>
        <th><xms:localization text="E-mail Invoice Confirm"/></th>
        <th><xms:localization text="EDI Import Notify"/></th>
        <th><xms:localization text="Supply Request"/></th>
        <th><xms:localization text="Last Modified"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="emailSettingModels.records.size != 0">
        <s:iterator value="emailSettingModels.records">
            <tr>
                <td width="45"><i id="adjustment-link" class="fa fa-pencil s10 b3 adjustments"
                                  data="<s:property value='id' />" style="font-size: 18px;"
                                  emailData="<s:property value='email' />"
                                  onclick="javascript:editEmail($(this).attr('data'),$(this).attr('emailData'));"></i>
                    <i id="note-link3" data="<s:property value='id' />" class="fa fa-times-circle-o s10 b3"
                       style="font-size: 18px;" data="<s:property value='id' />"
                       dataSetting="<s:property value='idSetting' />"
                       onclick="javascript:deleteEmail($(this).attr('data'), $(this).attr('dataSetting'));"></i></td>
                <td id="edit-email-link"><s:property value="email"/></td>
                <td><s:checkbox name="emailInvoiceConfirm" dataSetting="1" adminEmailId="%{id}"
                                onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                <td><s:checkbox name="ediImportNotify" dataSetting="2" adminEmailId="%{id}"
                                onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                <td><s:checkbox name="supplyRequest" dataSetting="3" adminEmailId="%{id}"
                                onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                <td><s:property value="actionDate"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="6"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:if test="!emailSettingModels.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{emailSettingModels.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="emailSettingModels.pageRange" status="count">
        <s:if test="%{emailSettingModels.pageRange[#count.index] == emailSettingModels.currentPage}">
            <a class="paginate_button current"><s:property value="emailSettingModels.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!emailSettingModels.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{emailSettingModels.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>