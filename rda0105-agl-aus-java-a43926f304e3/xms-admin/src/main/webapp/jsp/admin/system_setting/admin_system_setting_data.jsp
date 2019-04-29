<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="system-setting-table">
    <thead>
    <tr>
        <th><xms:localization text="Name"/></th>
        <th><xms:localization text="Value"/></th>
        <th><xms:localization text="Edit Admin Level"/></th>
        <th><xms:localization text="Description"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="systemSettingListModel.records.size != 0">
        <s:iterator value="systemSettingListModel.records">
            <tr>

                <td><s:hidden value="%{settingId}" id="setting_id"></s:hidden> <s:property value="settingName"/></td>
                <td><s:property value="settingValue"/></td>
                <td><s:property value="userLevel"/></td>
                <td><s:property value="description"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="4"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="systemSettingListModel.startRecord"/>
                <xms:localization text="to"/> <s:property value="systemSettingListModel.endRecord"/> <xms:localization
                        text="of"/> <s:property value="systemSettingListModel.totalRecords"/> <xms:localization
                        text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:hidden value="%{systemSettingListModel.currentPage}" id="hid_curren_page"></s:hidden>
            <s:if test="!systemSettingListModel.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{systemSettingListModel.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="systemSettingListModel.pageRange" status="count">
                <s:if test="%{systemSettingListModel.pageRange[#count.index] == systemSettingListModel.currentPage}">
                    <a class="paginate_button current"><s:property value="systemSettingListModel.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!systemSettingListModel.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{systemSettingListModel.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var page = 1;
        var pageSize = $("select[name='pageSize'] option:selected").val();
        var fieldList = ["setting_name", "setting_value", "user_level", "description"];
        $("#system-setting-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function viewDetailstate(id) {
        var data = {
            'settingId': id,
            'page': $('#hid_curren_page').val(),
            'pageSize': $("select[name='pageSize'] option:selected").val()
        };
        loadDialog("admin_system_setting_edit.ix?reqType=json", data, "form_edit_system_setting", "Edit", "Cancel", "edit_setting_dialog", "Edit System Setting", "admin-system-setting-list");
    }

    $('table#system-setting-table tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var settingId = $(this).find('#setting_id').val();
        viewDetailstate(settingId);
    });
</script>
