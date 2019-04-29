<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="country-list-table">
    <thead>
    <tr>
        <th><xms:localization text="Country Code"/></th>
        <th><xms:localization text="Country Name"/></th>
        <th><xms:localization text="Display Name"/></th>
        <th><xms:localization text="Other Name"/></th>
        <th class="col_hidden th_gst"><xms:localization text="GST"/></th>
        <th><xms:localization text="Last Modified"/></th>
        <th><xms:localization text="Show in Country List"/></th>
        <th class="col_hidden th_ap_code"><xms:localization text="DHL-AP Code"/></th>
        <th class="col_hidden th_ap_code"><xms:localization text="DHL-AP Zone"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="adm-country-list-filter">
        <td><s:textfield cssClass="form-control" name="countryCode" onkeyup="onSearchCountryKeyUp()"/></td>
        <td><s:textfield cssClass="form-control" name="countryName" onkeyup="onSearchCountryKeyUp()"/></td>
        <td><s:textfield cssClass="form-control" name="displayName" onkeyup="onSearchCountryKeyUp()"/></td>
        <td><s:textfield cssClass="form-control" name="otherName" onkeyup="onSearchCountryKeyUp()"/></td>
        <td class="col_hidden th_gst"><s:textfield cssClass="form-control" name="gst"
                                                   onkeyup="onSearchCountryKeyUp()"/></td>
        <td><s:textfield cssClass="form-control form_datetime" data-date-format="dd MM yyyy" name="modifiedDate"
                         onchange="doSearch()"/></td>
        <td>
            <!--<s:checkbox name="isShow" onchange="doSearch()" />-->&nbsp;
        </td>
        <td class="col_hidden th_ap_code"><s:textfield cssClass="form-control" name="apCode"
                                                       onkeyup="onSearchCountryKeyUp()"/></td>
        <td class="col_hidden th_ap_code"><s:textfield cssClass="form-control" name="apZone"
                                                       onkeyup="onSearchCountryKeyUp()"/></td>
    </tr>
    <s:if test="countryLists.records.size != 0">
        <s:iterator value="countryLists.records">
            <tr id="edit-country-link">
                <td><s:hidden value="%{countryId}" id="country_id"></s:hidden> <s:property value="countryCode"/></td>
                <td><s:property value="countryName"/></td>
                <td><s:property value="displayName"/></td>
                <td><s:property value="otherName"/></td>
                <td class="col_hidden th_gst"><s:property value="gstPercent"/></td>
                <td><s:property value="modifiedDate"/></td>
                <td><s:checkbox name="isShow" countryId='%{countryId}'
                                onclick="isShow($(this).is(':checked'), $(this).attr('countryId'));"></s:checkbox></td>
                <td class="col_hidden th_ap_code"><s:property value="dhlApCode"/></td>
                <td class="col_hidden th_ap_code"><s:property value="dhlApZone"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="9"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="countryLists.startRecord"/> <xms:localization
                    text="to"/> <s:property value="countryLists.endRecord"/> <xms:localization text="of"/> <s:property
                    value="countryLists.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!countryLists.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{countryLists.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="countryLists.pageRange" status="count">
                <s:if test="%{countryLists.pageRange[#count.index] == countryLists.currentPage}">
                    <a class="paginate_button current"><s:property value="countryLists.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!countryLists.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{countryLists.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
        $(".col_hidden").hide();
        optionShowCol();
        var fieldList = ["countryCode", "countryName", "displayName", "otherName", "gstPercent", "modifiedDate", "isShow", "dhlApCode", "dhlApZone"];
        $("#country-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function optionShowCol() {
        $("#div_list_chk_option").find("input").each(function () {
            var index = $("#div_list_chk_option").find("input").index(this) - 1;
            var colToHide = $("#country-list-table").find("." + $(this).attr("name"));
            if ($(this).is(":checked")) {
                $(this).attr('checked', true);
                colToHide.show();
            } else {
                $(this).attr('checked', false);
                colToHide.hide();
            }
        });
    }
    function viewDetailCountry(id) {
        var data = {
            'countryId': id,
            'orderField': $("#order_field").val(),
            'orderType': $("#order_type").val()
        };
        loadDialog("country_list_edit.ix?reqType=json", data, "form_country_list_edit", "Edit", "Cancel", "country-edit-dialog", "Edit Country", "country_list_data");
    }

    $('table#country-list-table tbody tr').click(function () {
        $("#btn_view_state").attr("disabled", false);
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var countryId = $(this).find('#country_id').val();
        idCountry = countryId;
    });

    $('table#country-list-table tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var countryId = $(this).find('#country_id').val();
        viewDetailCountry(countryId);
    });


</script>