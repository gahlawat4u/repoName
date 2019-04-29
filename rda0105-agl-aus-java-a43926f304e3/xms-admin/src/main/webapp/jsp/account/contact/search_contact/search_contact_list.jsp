<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Search Contacts"/> </a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Search Contacts"/></li>
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
                                <div class="caption">
                                    <xms:localization text="Search Contacts"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <div class="form-group  ">
                                                    <p>
                                                        <b><xms:localization text="Note"/>: </b> <br/> -
                                                        <xms:localization
                                                                text="Double-click the entry to get its Sales Stage"/>
                                                        .
                                                    </p>

                                                    <form id="manage_contact_form">
                                                        <table class="s36 b24">
                                                            <tbody>
                                                            <tr>
                                                                <td><xms:localization text="Company Name"/>:</td>
                                                                <td><input class="form-control alloptions"
                                                                           name="companyName" value="${companyName}"
                                                                           type="text" placeholder="" maxlength="25">
                                                                </td>
                                                                <td><xms:localization text="Contact Name"/>:</td>
                                                                <td><input class="form-control alloptions"
                                                                           name="contactName" value="${contactName}"
                                                                           type="text" placeholder="" maxlength="25">
                                                                </td>
                                                                <td><xms:localization text="Address 1"/>:</td>
                                                                <td><input class="form-control alloptions"
                                                                           name="address1" value="${address1}"
                                                                           type="text" placeholder="" maxlength="25">
                                                                </td>
                                                                <td><xms:localization text="Address 2"/>:</td>
                                                                <td><input class="form-control alloptions"
                                                                           name="address2" value="${address2}"
                                                                           type="text" placeholder="" maxlength="25">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td><xms:localization text="Phone"/>:</td>
                                                                <td><input class="form-control alloptions" name="phone"
                                                                           value="${phone}" type="text" placeholder=""
                                                                           maxlength="25"></td>
                                                                <td><xms:localization text="Email"/>:</td>
                                                                <td><input class="form-control alloptions" name="email"
                                                                           value="${email}" type="text" placeholder=""
                                                                           maxlength="25"></td>
                                                                <td><xms:localization text="Postal Code"/>:</td>
                                                                <td><input class="form-control alloptions"
                                                                           name="postalCode" value="${postalCode}"
                                                                           type="text" placeholder="" maxlength="25">
                                                                </td>
                                                                <td><xms:localization text="Status"/>:</td>
                                                                <td><s:select list="statusList" listValue="name"
                                                                              name="saleStage" listKey="id"
                                                                              cssClass="form-control"/></td>
                                                                <td>
                                                                    <button class="btn s37" type="submit"
                                                                            onclick="onSearchClick()">
                                                                        <xms:localization text="Search"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                        <input type="hidden" id="orderField" name="orderField"
                                                               value="prospectid"/> <input type="hidden" id="orderType"
                                                                                           name="orderType" value="0"/>
                                                        <input type="hidden" id="page" name="page" value="1"/>
                                                    </form>
                                                    <s:hidden id="export-data"/>
                                                </div>
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="pageSize" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                                  list="pageSizes"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="manage_contact_result">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0"
                                                               id="manage_contact_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Company Name"/></th>
                                                                <th><xms:localization text="Contact Name"/></th>
                                                                <th><xms:localization text="Address 1"/></th>
                                                                <th><xms:localization text="Address 2"/></th>
                                                                <th><xms:localization text="Phone"/></th>
                                                                <th><xms:localization text="Email"/></th>
                                                                <th><xms:localization text="Postal Code"/></th>
                                                                <th><xms:localization text="Sales Stage"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="contacts!=null && contacts.totalRecords>0">
                                                                <s:iterator value="contacts.records">
                                                                    <tr>
                                                                        <td><s:property value="companyName"/></td>
                                                                        <td><s:property value="contactName"/></td>
                                                                        <td><s:property value="address1"/></td>
                                                                        <td><s:property value="address2"/></td>
                                                                        <td><s:property value="phone"/></td>
                                                                        <td><s:property value="email"/></td>
                                                                        <td><s:property value="postalCode"/></td>
                                                                        <td><s:property value="saleStage"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                            </s:if>
                                                            <s:else>
                                                                <tr>
                                                                    <td colspan="8"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="contacts.startRecord"/> <xms:localization
                                                                        text="to"/> <s:property
                                                                        value="contacts.endRecord"/> <xms:localization
                                                                        text="of"/> <s:property
                                                                        value="contacts.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="contacts!=null">
                                                                    <s:if test="!contacts.hasPrev()">
                                                                        <a class="paginate_button previous disabled"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a href="javascript:changePage(<s:property value="%{contacts.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:else>
																	<span> <s:iterator value="contacts.pageRange"
                                                                                       status="count">
                                                                        <s:if test="%{contacts.pageRange[#count.index] == contacts.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="contacts.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="!contacts.hasNext()">
                                                                        <a class="paginate_button next disabled"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button next"
                                                                           href="javascript:changePage(<s:property value="%{contacts.currentPage+1}"/>)"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:else>
                                                                </s:if>
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
        </div>
    </div>
</div>
<!--END CONTENT-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        <s:if test="contacts==null || contacts.totalRecords==0">
        hasRecords = false;
        </s:if>
        <s:else>
        hasRecords = true;
        </s:else>
    });
    function doSearch() {
        var data = $("#manage_contact_form").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("manage_contact_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#manage_contact_result").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onSearchClick() {
        $("#page").val(1);
        $("#orderField").val("prospectid");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("manage_contact_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                });
                break;
        }
    }


</script>