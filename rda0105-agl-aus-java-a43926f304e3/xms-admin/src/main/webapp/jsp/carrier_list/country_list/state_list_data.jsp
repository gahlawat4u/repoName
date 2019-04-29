<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 datatable1" id="datatable1">
    <thead>
    <tr>
        <th><xms:localization text="ID"/></th>
        <th><xms:localization text="State Name"/></th>
        <th><xms:localization text="State Code"/></th>
        <th><xms:localization text="City Name"/></th>
        <th><xms:localization text="City Code"/></th>
        <th><xms:localization text="Postal Code (From)"/></th>
        <th><xms:localization text="Postal Code (To)"/></th>
        <th><xms:localization text="DHL Zone Code"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="adm-state-list-filter">
        <td><s:textfield name="id" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="stateName" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="stateCode" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="cityName" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="cityCode" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="postCodeFrom" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="postCodeTo" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
        <td><s:textfield name="dhlZoneCode" cssClass="form-control" onkeyup="onKeyUpStateSearch()"/></td>
    </tr>
    <s:if test="stateLists.records.size != 0">
        <s:iterator value="stateLists.records">
            <tr id="edit-state-link">
                <td><s:hidden value="%{id}" id="state_id"></s:hidden> <s:property value="id"/></td>
                <td><s:property value="stateName"/></td>
                <td><s:property value="stateCode"/></td>
                <td><s:property value="cityName"/></td>
                <td><s:property value="cityCode"/></td>
                <td><s:property value="fromPostCode"/></td>
                <td><s:property value="toPostCode"/></td>
                <td><s:property value="dhlZone"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="8"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>

    </tbody>
</table>
<s:hidden value="%{stateLists.currentPage}" id="hid_curren_page"></s:hidden>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="stateLists.startRecord"/> <xms:localization
                    text="to"/> <s:property value="stateLists.endRecord"/> <xms:localization text="of"/> <s:property
                    value="stateLists.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!stateLists.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{stateLists.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="stateLists.pageRange" status="count">
                <s:if test="%{stateLists.pageRange[#count.index] == stateLists.currentPage}">
                    <a class="paginate_button current"><s:property value="stateLists.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!stateLists.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{stateLists.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var page = 1;
        var pageSize = $("select[name='pageSize'] option:selected").val();
        var fieldList = ["id", "state_name", "state_code", "city_name", "city_code", "from_postcode", "to_postcode", "dhl_zone"];
        $("#datatable1").tablesorter({
            sortFieldId: "state_list_order_field",
            sortTypeId: "state_list_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function viewDetailstate(id) {
        var data = {
            'stateId': id
        };
        loadDialog("state_list_edit.ix?reqType=json", data, "form_state_list_edit", "Edit", "Cancel", "state-edit-dialog", "Edit state", "state_list_data");
    }

    $('table#datatable1 tbody tr').click(function () {
        $("#btn_del").prop("disabled", false);
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var stateId = $(this).find('#state_id').val();
        $("#hid_state_id").val(stateId);
    });

    $('table#datatable1 tbody tr').dblclick(function () {
        var stateId = $(this).find('#state_id').val();
        viewDetailState(stateId);
    });
</script>
