<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Product Carriers"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Product Carriers"/></li>
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
                                    <xms:localization text="Product Carriers"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note :</b> <br /> - This is the Product Carrier List that are available for product category.<br /> - Double-click the entry to modify its value."/>
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
                                                                    <td><s:select id="pageSize" name="pageSize"
                                                                                  list="pageSizes"
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
                                                <s:hidden id="page" name="page"/>
                                                <s:hidden id="orderField" name="orderField"/>
                                                <s:hidden id="orderType" name="orderType"/>
                                                <div id="product_carrier_result">
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="product_carrier_table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="No"/></th>
                                                            <th><xms:localization text="Product Carrier Name"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="productCarrierList==null || productCarrierList.totalRecords==0">
                                                            <tr>
                                                                <td colspan="2"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="productCarrierList.records">
                                                                <tr data-product-carrier-id='<s:property value="productCarrierId" />'
                                                                    ondblclick='load("<s:property
                                                                            value="productCarrierId"/>")'>
                                                                    <td><s:property value="productCarrierId"/></td>
                                                                    <td><s:property value="productCarrierName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="!productCarrierList.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{productCarrierList.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="productCarrierList.pageRange"
                                                                           status="count">
                                                            <s:if test="%{productCarrierList.pageRange[#count.index] == productCarrierList.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="productCarrierList.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!productCarrierList.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{productCarrierList.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:else>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button class="btn s37" id="btnView" onclick="viewProduct()"
                                                            disabled="disabled">
                                                        <xms:localization text="View Product"/>
                                                    </button>
                                                    <button id="add-carriers-link" class="btn s37" onclick="load('')">
                                                        <xms:localization text="Add Product Carrier"/>
                                                    </button>
                                                    <button class="btn s37" type="button" id="btnRemove"
                                                            disabled="disabled" onclick="showDeleteDialog()">
                                                        <xms:localization text="Delete Product Carrier"/>
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
</div>
<!--END CONTENT-->
<div id="product_carrier_dialog"></div>
<div id="product_carrier_confirm_dialog"></div>

<script type="text/javascript">
    var productCarrierId = 0;

    $(document).ready(function () {
        $('table#product_carrier_table tbody tr').click(function () {
            productCarrierId = $(this).attr('data-product-carrier-id');
            if (typeof (productCarrierId) != "undefined" && productCarrierId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                $('#btnView').attr('disabled', false);
                $('#btnRemove').attr('disabled', false);
            }
        });
        $("#product_carrier_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: ["product_carrierid", "product_carriername"],
            callback: search
        });
    });

    function search() {
        var data = {
            "page": $("#page").val(),
            "pageSize": $("#pageSize option:selected").val(),
            "orderField": $("#orderField").val(),
            "orderType": $("#orderType").val()
        };
        loadingDialog.dialog("open");
        $.post("product_carrier_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#product_carrier_result").html(res.content);
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

    function load(productCarrierId) {
        var data = {
            "productCarrierId": productCarrierId
        };
        loadingDialog.dialog("open");
        $.post("product_carrier_load.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define save product carrier dialog.
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    save();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var title = "";
                if (productCarrierId == "") {
                    title = '<xms:localization text="New Product Carrier"/>';
                } else {
                    title = '<xms:localization text="Edit Product Carrier"/>';
                }
                var dialog = $("#product_carrier_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
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
        var data = $("#product_carrier_form").serialize();
        $.post("product_carrier_save.ix?reqType=json", data, function (res) {
            if (res.errorCode == "FIELD_ERROR") {
                $("#product_carrier_form").replaceWith(res.content);
            } else if (res.errorCode == "SUCCESS") {
                $("#product_carrier_dialog").dialog("close");
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
        if (productCarrierId == "" || productCarrierId == 0) {
            alertDialog.html('<xms:localization text="Please select Product Carrier to delete." />');
            alertDialog.dialog("open");
            return;
        }
        var buttons = {};
        buttons["<xms:localization text="Yes" />"] = function () {
            $(this).dialog("close");
            var data = {
                "productCarrierId": productCarrierId
            };
            $.post("product_carrier_delete.ix?reqType=json", data, function (res) {
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
        var dialog = $("#product_carrier_confirm_dialog").dialog({
            modal: true,
            autoOpen: false,
            width: 'auto',
            height: 'auto',
            maxHeight: '800',
            buttons: buttons,
            title: '<xms:localization text="Delete product carrier" />',
            show: {
                effect: "fade",
                duration: 300
            }
        });
        dialog.html('<xms:localization text="Are you sure you want to delete this product carrier?" />');
        dialog.dialog("open");
    }

    function viewProduct() {
        if (productCarrierId == "" || productCarrierId == 0) {
            alertDialog.html('<xms:localization text="Please select Product to delete." />');
            alertDialog.dialog("open");
            return;
        }
        var url = "product_list.ix?id=" + productCarrierId;
        window.location.href = url;
    }


</script>