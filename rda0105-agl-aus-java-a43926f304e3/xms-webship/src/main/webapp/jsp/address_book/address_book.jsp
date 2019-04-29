<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="tab-general">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-md-12">
                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="portlet box">
                        <div class="portlet-header">
                            <div class="caption">
                                <xms:localization text="Address Book"/>
                            </div>
                            <div class="tools">
                                <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="panel-body pan">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="col-lg-6">
                                                <div >
                                                    <ul class="pagination mtn mbn">
                                                        <li><span class="lsc"><xms:localization text="Show"/></span>
                                                        </li>
                                                        <li><span class="lsc t1"> <s:select list="{5,10,20,50,100}"
                                                                                            value="addressBookList.pageSize"
                                                                                            class="form-control"
                                                                                            id="selPageSize"
                                                                                            onchange="changePageSize()"></s:select>
														</span></li>
                                                        <li><span class="lsc"><xms:localization text="entries"/></span>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-lg-12" style="padding-bottom: 10px;">
                                                <div class="form-group mgb">
                                                    <s:form id="search-form" >
                                                        <div class="form-group">
                                                            <table class="s36" >
                                                                <tr>
                                                                    <td><label> <xms:localization text="Search"/> :
                                                                    </label></td>
                                                                    <td ><input  name="searchFilter.contactName" class="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="contactName" />
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.companyName" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="companyName"/>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.address1" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="address1"/>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.address2" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="address2"/>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.city" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"   placeholder="city"/>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.state" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="state"/>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.postalCode" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="postalCode"/>
                                                                    </td>
                                                                    <td ><s:select id="sel-country-list" name="searchFilter.country"
                                                                                                        list="countryList"
                                                                                                        listKey="countryId"
                                                                                                        listValue="countryName"
                                                                                                        cssClass="form-control sel-box"
                                                                                                        onchange="getData($('#selPageSize').val())"></s:select>
                                                                    </td>
                                                                    <td ><s:textfield name="searchFilter.phone" cssClass="form-control" onblur="getData($('#selPageSize').val())"
                                                                                     onkeypress="return event.keyCode !=13;"  placeholder="phone"/>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </s:form>
                                                </div>
                                            </div>
                                            <div id="div-address-book-table" class="col-xs-12">
                                                <table class="table table-hover table-bordered mg0"
                                                       id="address-book-table">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Contact"/></th>
                                                        <th><xms:localization text="Company"/></th>
                                                        <th><xms:localization text="Address"/>#</th>
                                                        <th><xms:localization text="Address"/> 2</th>
                                                        <th><xms:localization text="City"/></th>
                                                        <th><xms:localization text="State/Province"/></th>
                                                        <th><xms:localization text="Postal Code"/></th>
                                                        <th><xms:localization text="Country"/></th>
                                                        <th><xms:localization text="Phone"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>

                                                    <s:if test="addressBookList.records.isEmpty()">
                                                        <td colspan="8"><xms:localization
                                                                text="No data available..."/></td>
                                                    </s:if>
                                                    <s:else>
                                                        <s:iterator value="addressBookList.records">
                                                            <tr data-address-id="<s:property value="addressId"/>">
                                                                <td><s:property value="contactName"/></td>
                                                                <td><s:property value="companyName"/></td>
                                                                <td><s:property value="address1"/></td>
                                                                <td><s:property value="address2"/></td>
                                                                <td><s:property value="city"/></td>
                                                                <td><s:property value="state"/></td>
                                                                <td><s:property value="postalCode"/></td>
                                                                <td><s:property value="countryName"/></td>
                                                                <td><s:property value="phone"/></td>
                                                            </tr>
                                                        </s:iterator>
                                                    </s:else>
                                                    </tbody>
                                                </table>
                                                <s:hidden value="%{addressBookList.currentPage}"
                                                          id="currPage"></s:hidden>
                                                <input type="hidden" id="selectedId" value=""/>
                                                <script type="text/javascript">
                                                    $(document).ready(function () {
                                                        $('#selectedId').val("");
                                                        $('table#address-book-table tbody tr').click(function () {
                                                            $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                            selectedAddressId = $(this).attr("data-address-id");
                                                            $('#selectedId').val(selectedAddressId);
                                                            $('#btnEdit').attr('disabled', false);
                                                            $('#btnRemove').attr('disabled', false);
                                                        });
                                                    });
                                                </script>
                                                <div class="text-right">
                                                    <ul class="pagination mts mbs" data-hover="">
                                                        <s:if test="!addressBookList.hasPrev()">
                                                            <li class="disabled"><a><xms:localization
                                                                    text="Previous"/></a></li>
                                                        </s:if>
                                                        <s:else>
                                                            <li>
                                                                <a href="javascript:changePage(<s:property value="%{addressBookList.currentPage - 1}"/>)"><xms:localization
                                                                        text="Previous"/></a></li>
                                                        </s:else>
                                                        <s:iterator value="addressBookList.pageRange" status="count">
                                                            <s:if test="%{addressBookList.pageRange[#count.index] == addressBookList.currentPage}">
                                                                <li class="active"><a><s:property
                                                                        value="addressBookList.currentPage"/></a></li>
                                                            </s:if>
                                                            <s:else>
                                                                <li>
                                                                    <a href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </li>
                                                            </s:else>
                                                        </s:iterator>
                                                        <s:if test="!addressBookList.hasNext()">
                                                            <li class="disabled"><a href="#"><xms:localization
                                                                    text="Next"/></a></li>
                                                        </s:if>
                                                        <s:else>
                                                            <li>
                                                                <a href="javascript:changePage(<s:property value="%{addressBookList.currentPage + 1}"/>)"><xms:localization
                                                                        text="Next"/></a></li>
                                                        </s:else>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class=" text-right pal pdt10">
                                    <div class="row">
                                        <div class="col-lg-10 text-left">
                                            <s:a action="address_book_add" class="btn s33 s44">
                                                <xms:localization text="Add"/>
                                            </s:a>
                                            <button class="btn s33 s44" disabled="disabled" id="btnEdit"
                                                    onclick="doEdit()">
                                                <xms:localization text="Edit"/>
                                            </button>
                                            <button class="btn s33 s44" disabled="disabled" id="btnRemove"
                                                    onclick="doRemove()">
                                                <xms:localization text="Remove"/>
                                            </button>
                                            <s:a class="btn s33 s44" action="address_book_import">
                                                <xms:localization text="Import"/>
                                            </s:a>
                                            <button class="btn s33 s44" type="button"
                                                    onclick="getData($('#selPageSize').val(),'1')">
                                                <xms:localization text="Refresh"/>
                                            </button>
                                            <s:a action="address_book_export" class="btn s33 s44">
                                                <xms:localization text="Export"/>
                                            </s:a>
                                        </div>
                                        <div class="col-lg-2">
                                            <button class="btn s33 s44" type="button" onclick="onShipTo()">
                                                <xms:localization text="Ship To"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="delete-dialog"></div>
<script type="text/javascript">
    var selectedAddressId = "";
    var pageSize = $('#selPageSize').val();
    var searchFilter = $("#search-filter-list").val();
    function doEdit() {
        selectedAddressId = $('#selectedId').val();
        var currPage = $('#currPage').val();
        if (selectedAddressId == "") {
            alert("Please select an address!");
        }
        var url = "address_book_edit.ix?addressId=" + selectedAddressId + "&page=" + currPage + "&pageSize=" + pageSize;
        window.open(url, '_self');
    }
    function doRemove() {
        selectedAddressId = $('#selectedId').val();
        if (selectedAddressId == "") {
            alert("Please select an address!");
        }
        var data = {
            'addressId': selectedAddressId,
            'page': currPage,
            'pageSize': pageSize
        }
        var currPage = $('#currPage').val();
        loadDeleteDialog("address_book_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete addressId" />" + selectedAddressId, "delete-dialog", "div-address-book-table", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />", "<xms:localization text="Delete" />");
    }
    function changePage(page) {
        getData(pageSize, page);
    }
    function changePageSize() {
        pageSize = $('#selPageSize').val();
        getData(pageSize, '1');
    }
    function getData(pageSize, page) {
        var p = typeof page !== "undefined" ? page : 1;
        var ps = typeof pageSize !== "undefined" ? pageSize : 5;
        var data = $("#search-form").serialize();
        data += "&page=" + p + "&pageSize=" + ps;
        doPostDataByParameters("address_book_get_data.ix?reqType=json", data, "", "div-address-book-table");
    }
    function changeSearchFilter(filter) {
        searchFilter = filter;
        if (searchFilter == "searchFilter.country") {
            $("#search-terms-div").hide();
            $("#sel-country-div").show();
            $("#sel-country-list").attr("name", filter);
            $("#search-terms-input").attr("name", "");
        } else {
            $("#search-terms-div").show();
            $("#sel-country-div").hide();
            $("#search-terms-input").attr("name", filter);
            $("#sel-country-list").attr("name", "");
        }
    }

    function onShipTo() {
        var addressId = $("#selectedId").val();
        if (addressId == "") {
            alert("Please select an address!");
        } else {
            window.location = "webship.ix?addressId=" + addressId;
        }
    }
</script>
<!--END CONTENT-->