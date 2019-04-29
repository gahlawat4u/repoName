<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN SIDEBAR MENU-->
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><a href="#"><xms:localization text="Credit Notes"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
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
                                <div class="caption" id="frozen-status" style="display: none;">
                                    <xms:localization text="Credit Notes"/>
                                    <span class="b2"><xms:localization text="This Credit is Frozen"/></span>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Filter By:"/></td>
                                                <td><select class="form-control" id="search-filter"
                                                            onchange="doPostDataByParameters('credit_notes_change_search_filter.ix?reqType=json', {'creditNotesPageModel.filterBy': $(this).val()}, '', 'search-filter-input')">
                                                    <option value="date"><xms:localization text="Date"/></option>
                                                    <option value="franchise"><xms:localization
                                                            text="Franchise #"/></option>
                                                    <option value="customer"><xms:localization
                                                            text="Customer #"/></option>
                                                </select></td>
                                                <td id="search-filter-input"><s:select cssClass="form-control"
                                                                                       list="listCreditNoteDate"
                                                                                       id="sel-create-date"></s:select></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="searchCreditNote()">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                </td>
                                                <td><xms:localization text="Credit Notes:"/></td>
                                                <td id="td_credit_notes"><select class="form-control">
                                                    <option value=""><xms:localization
                                                            text="Select a Credit Note"/></option>
                                                </select></td>
                                                <s:hidden id="credit-note-status"/>
                                                <s:hidden id="selected-credit-note"/>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;" id="buildContentPageCreditNote">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><img src="images/LOGOiN.png" width="140"/></td>
                                                        <td class="td2"><s:property escape="false"
                                                                                    value="systemAdminInfo.systemAddress"/>
                                                            <br/> <s:property value="systemAdminInfo.siteAddress"/></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-4">
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><xms:localization text="CREDIT TO:"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-4">
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><xms:localization text="MAIL PAYMENT TO:"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table table-striped table-bordered mg0"
                                                       style="font-size: 11px;">
                                                    <tr>
                                                        <th><xms:localization text="Credit Number"/></th>
                                                        <th><xms:localization text="Credit Date"/></th>
                                                        <th><xms:localization text="Customer #"/></th>
                                                        <th><xms:localization text="Credits"/></th>
                                                        <th><xms:localization text="Total Credited"/></th>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                                <br/>
                                            </div>
                                            <div class="col-lg-5">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-bordered mg0" style="font-size: 11px;">
                                                    <tr class="b1">
                                                        <th><xms:localization text="GST Summary"/></th>
                                                        <th><xms:localization text="GST Percent"/></th>
                                                        <th><xms:localization text="Credit Amount"/></th>
                                                        <th><xms:localization text="GST Amount"/></th>
                                                        <th><xms:localization text="Total Amount"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th class="b1"><xms:localization text="GST Shipments"/></th>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="b1"><xms:localization text="Non-GST Shipments"/></th>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-7">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-bordered table-hover mg0"
                                                       style="font-size: 11px;">
                                                    <tr>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="Airbill #"/></th>
                                                        <th><xms:localization text="Invoice Date"/></th>
                                                        <th><xms:localization text="Reason for Credit"/></th>
                                                        <th><xms:localization text="Amount"/></th>
                                                        <th><xms:localization text="GST Amount"/></th>
                                                        <th><xms:localization text="Total Credit"/></th>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="7"><b><xms:localization text="GRAND TOTAL:"/></b>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <div class="dataTables_paginate">
                                                    <a class="paginate_button previous"><xms:localization
                                                            text="Previous"/></a><span><a
                                                        class="paginate_button current">1</a></span><a
                                                        class="paginate_button next"><xms:localization text="Next"/></a>
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
    </div>
</div>
<!--END CONTENT-->
<script type="text/javascript">
    function searchCreditNote() {
        var filterBy = $("#search-filter").val();
        switch (filterBy) {
            case "date":
                var data = {
                    'creditNotesPageModel.dateSelect': $('#sel-create-date').val()
                };
                break;
            case "franchise":
                var data = {
                    'creditNotesPageModel.franchiseCode': $('#sel-franchise-code').val()
                };
                break;
            case "customer":
                var data = {
                    'creditNotesPageModel.customerCode': $('#search-customer-code').val()
                };
                break;
        }
        doPostDataByParameters("list_credit_note_by_date.ix?reqType=json", data, "", "td_credit_notes", true, true);
    }

    function showInforCreditCode(sel) {
        loadingDialog.dialog("open");
        var creditCode = sel.val();
        if (creditCode == '') {
            loadingDialog.dialog("close");
            return false;
        }
        if (creditCode == "Print all") {
            var creditNoteCodes = $("#credit-note-codes").val();
            $.post("credit_notes_printable_preview_all.ix", {
                "creditNotesPageModel.creditNoteCodes": creditNoteCodes
            }, function (res) {
                loadingDialog.dialog("close");
                var win = window.open("", "win2", "status=no");
                win.document.write(res);
            });
        } else {
            $("#selected-credit-note").val(creditCode);
            doPostDataByParameters("content_credit_note.ix?reqType=json", {
                'creditNotesPageModel.creditNoteCode': creditCode
            }, "", "buildContentPageCreditNote");
        }
    }

    function changeSearchFilter(filter) {
        var data = {
            "creditNotesPageModel.filterBy": filter
        };
    }


</script>