<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;Home&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Language Value"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Language Value"/></li>
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
                                    <xms:localization text="Language Value"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="form-group">
                                                    <form id="language-value-search-form">
                                                        <table class="s36 b24">
                                                            <tbody>
                                                            <tr>
                                                                <td><xms:localization text="Lang code:"/></td>
                                                                <td><s:i18n_select name="langCode" list="languageList"
                                                                                   cssClass="form-control" headerKey=""
                                                                                   headerValue="All"
                                                                                   i18nitem="no"/></td>
                                                                <td><xms:localization text="Original:"/></td>
                                                                <td><s:textfield name="original"
                                                                                 cssClass="form-control"/></td>
                                                                <td>
                                                                    <button type="button" class="btn s37"
                                                                            onclick="getData(1)">
                                                                        <xms:localization text="Search"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                        <s:hidden id="orderField" name="orderField"/>
                                                        <s:hidden id="orderType" name="orderType"/>
                                                    </form>
                                                </div>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select list="listPageSize"
                                                                                  cssClass="form-control"
                                                                                  onchange="changePageSize($(this).val())"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="language-value-div">
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="language-value-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Language code"/></th>
                                                            <th><xms:localization text="Original"/></th>
                                                            <th><xms:localization text="Destination"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="languageValueList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="languageValueList.records">
                                                                <tr ondblclick="showLanguaValue('<s:property
                                                                        value="id"/>')">
                                                                    <td><s:property value="langCode"/></td>
                                                                    <td><s:property value="original"/></td>
                                                                    <td><s:property value="destination"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $("#language-value-table").tablesorter({
                                                                sortFieldId: "orderField",
                                                                sortTypeId: "orderType",
                                                                fieldList: ["lang_code", "original", "destination"],
                                                                callback: function () {
                                                                    getData(1);
                                                                }
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="languageValueList.hasPrev()">
                                                            <a href="javascript:changePage(<s:property value="%{languageValueList.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
														<span> <s:iterator value="languageValueList.pageRange"
                                                                           status="count">
                                                            <s:if test="%{languageValueList.pageRange[#count.index] == languageValueList.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="languageValueList.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="languageValueList.hasNext()">
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{languageValueList.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%--div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button class="btn s37" onclick="showAddDialog()">
                                                        <xms:localization text="New Language Value" />
                                                    </button >
                                                </div>
                                            </div>
                                        </div--%>
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
<div id="language-value-dialog"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#language-value-search-form").submit(function (e) {
            getData(1);
            return false;
        });
    });
    var page = 1;
    var pageSize = "";
    function changePage(p) {
        page = p;
        getData(page, pageSize);
    }
    function changePageSize(ps) {
        pageSize = ps;
        getData(1, pageSize);
    }
    function getData(p, ps) {
        ps = typeof (ps) != "undefined" ? ps : pageSize;
        var data = $("#language-value-search-form").serialize() + "&page=" + p + "&pageSize=" + ps;
        doPostDataByParameters("language_value_get_data.ix?reqType=json", data, "", "language-value-div", false);
    }
    function showLanguaValue(id) {
        var data = {
            "id": id,
            "page": page,
            "pageSize": pageSize
        };
        loadDialog("language_value_edit.ix?reqType=json", data, "language-value-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "language-value-dialog", "<xms:localization text="Edit language value" />", "language-value-div");
    }
    function selectAccessorial(id) {
        languageValueId = id;
    }
    function showAddDialog() {
        var data = {
            "page": page,
            "pageSize": pageSize
        };
        loadDialog("language_value_add.ix?reqType=json", data, "language-value-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "language-value-dialog", "<xms:localization text="Add new language value" />", "language-value-div");
    }
</script>
<!--END CONTENT-->
