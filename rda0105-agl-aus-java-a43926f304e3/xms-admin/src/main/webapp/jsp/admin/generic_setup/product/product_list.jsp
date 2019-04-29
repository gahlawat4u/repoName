<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Products of "/> <s:property
                value="productCarrier.productCarrierName"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Products of "/> <s:property
                value="productCarrier.productCarrierName"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->

<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:else>
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
                                        <xms:localization text="Products of "/>
                                        <s:property value="productCarrier.productCarrierName"/>
                                    </div>
                                </div>
                                <div class="portlet-body" style="padding: 0px;">
                                    <div class="tab-content responsive">
                                        <div id="Overview-tab" class="tab-pane fade in active">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <p>
                                                        <xms:localization
                                                                text="<b>Note :</b> <br /> - This is the Product List that are available in the qualification data.<br /> - Double-click the entry to modify its value."/>
                                                    </p>
                                                </div>
                                                <div class="col-lg-12">
                                                    <table class="table mg0">
                                                        <tbody>
                                                        <tr>
                                                            <th class="s42">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select id="pageSize" list="PageSizes"
                                                                                      cssClass="form-control"
                                                                                      onchange="search()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <form id="product_search_form">
                                                        <s:hidden id="id" name="id"/>
                                                        <s:hidden id="page" name="page"/>
                                                        <s:hidden id="orderField" name="orderField"/>
                                                        <s:hidden id="orderType" name="orderType"/>
                                                    </form>
                                                    <div id="product_list_result">
                                                        <table class="table table-bordered mg0 table-hover table-pointer"
                                                               id="product_list_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="No"/></th>
                                                                <th><xms:localization text="Product Name"/></th>
                                                                <th><xms:localization text="Description"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="productList==null || productList.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="3"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="productList.records">
                                                                    <tr data-product-id="<s:property value="productId" />"
                                                                        ondblclick='load(<s:property
                                                                                value="productId"/>)'>
                                                                        <td><s:property value="productId"/></td>
                                                                        <td><s:property value="productName"/></td>
                                                                        <td><s:property value="description"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                        <div class="dataTables_paginate">
                                                            <s:if test="!productList.hasPrev()">
                                                                <a class="paginate_button previous disabled"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a href="javascript:changePage(<s:property value="%{productList.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:else>
															<span> <s:iterator value="productList.pageRange"
                                                                               status="count">
                                                                <s:if test="%{productList.pageRange[#count.index] == productList.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="productList.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="!productList.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="#"><xms:localization text="Next"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{productList.currentPage+1}"/>)"><xms:localization
                                                                        text="Next"/></a>
                                                            </s:else>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button class="btn s37" onclick="backToProductCarrier()">
                                                        <xms:localization text="Back to Product"/>
                                                    </button>
                                                    <button class="btn s37" onclick="load('')">
                                                        <xms:localization text="New Product"/>
                                                    </button>
                                                    <button class="btn s37" id="btnRemove" disabled="disabled"
                                                            onclick="showDeleteDialog()">
                                                        <xms:localization text="Delete Product"/>
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
    </div>
    <!--END CONTENT-->
    <div id="product_dialog"></div>
    <div id="delete_product_confirm_dialog"></div>

    <script type="text/javascript">
        var productId = 0;

        $(document).ready(function () {
            $('table#product_list_table tbody tr').click(function () {
                productId = $(this).attr('data-product-id');
                if (typeof (productId) != "undefined" && productId != "") {
                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                    $('#btnRemove').attr('disabled', false);
                }
            });
            $("#product_list_table").tablesorter({
                sortFieldId: "orderField",
                sortTypeId: "orderType",
                fieldList: ["product_id", "product_name", "description"],
                callback: search
            });
        });

        function search() {
            var data = $("#product_search_form").serialize();
            data += "&pageSize=" + $("#pageSize option:selected").val();
            loadingDialog.dialog("open");
            $.post("product_search.ix?reqType=json", data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    $("#product_list_result").html(res.content);
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

        function changePage(page) {
            $("#page").val(page);
            search();
        }

        function backToProductCarrier() {
            var url = "product_carrier_list.ix";
            window.location.href = url;
        }

        function load(productId) {
            var data = {
                "id": $("#id").val(),
                "productId": productId
            };
            loadingDialog.dialog("open");
            $.post("product_load.ix?reqType=json", data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    // Define save product dialog.
                    var buttons = {};
                    buttons["<xms:localization text="Save" />"] = function () {
                        save();
                    }
                    buttons["<xms:localization text="Cancel" />"] = function () {
                        $(this).dialog("close");
                    }
                    var title = "";
                    if (productId == "") {
                        title = '<xms:localization text="New Product"/>';
                    } else {
                        title = '<xms:localization text="Edit Product"/>';
                    }
                    var dialog = $("#product_dialog").dialog({
                        modal: true,
                        autoOpen: false,
                        width: 'auto',
                        height: 'auto',
                        minWidth: '300',
                        maxHeight: '800',
                        buttons: buttons,
                        title: title,
                        show: {
                            effect: "fade",
                            duration: 300
                        }
                    });
                    dialog.html(res.content);
                    // Show save product carrier dialog.
                    dialog.dialog("open");
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

        function save() {
            var data = $("#product_form").serialize();
            $.post("product_save.ix?reqType=json", data, function (res) {
                if (res.errorCode == "FIELD_ERROR") {
                    $("#product_form").replaceWith(res.content);
                } else if (res.errorCode == "SUCCESS") {
                    $("#product_dialog").dialog("close");
                    search();
                    messageDialog.html('<xms:localization text="Saved successfully." />');
                    messageDialog.dialog("open");
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });
        }

        function showDeleteDialog() {
            if (productId == "" || productId == 0) {
                alertDialog.html('<xms:localization text="Please select Product to delete." />');
                alertDialog.dialog("open");
                return;
            }
            var buttons = {};
            buttons["<xms:localization text="Yes" />"] = function () {
                $(this).dialog("close");
                var data = {
                    "productId": productId
                };
                $.post("product_delete.ix?reqType=json", data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        search();
                        messageDialog.html('<xms:localization text="Deleted successfully." />');
                        messageDialog.dialog("open");
                    } else {
                        alertDialog.html(res.errorMsg);
                        alertDialog.dialog("open");
                    }
                }).fail(function () {
                    alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                    alertDialog.dialog("open");
                });
            }
            buttons["<xms:localization text="No" />"] = function () {
                $(this).dialog("close");
            }
            var dialog = $("#delete_product_confirm_dialog").dialog({
                modal: true,
                autoOpen: false,
                width: 'auto',
                height: 'auto',
                maxHeight: '800',
                buttons: buttons,
                title: '<xms:localization text="Delete product" />',
                show: {
                    effect: "fade",
                    duration: 300
                }
            });
            dialog.html('<xms:localization text="Are you sure you want to delete this product?" />');
            dialog.dialog("open");
        }
    </script>
</s:else>