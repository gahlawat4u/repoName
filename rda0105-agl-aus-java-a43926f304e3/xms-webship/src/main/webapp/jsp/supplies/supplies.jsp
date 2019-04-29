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
                                <xms:localization text="Order Supplies"/>
                            </div>
                            <div class="tools">
                                <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="panel-body pan">
                                <form id="ws-supply-order-supplies-form">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b><xms:localization text="Note"/>: </b></br> -
                                                    <xms:localization
                                                            text="Order the number of items you want and click 'Order Supplies' to send your request to your account representative"/>
                                                </p>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group mgb">
                                                            <table class="s36">
                                                                <s:if test="serviceList==null || serviceList.size()==0">
                                                                    <xms:localization text="No data available..."/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:iterator value="serviceList">
                                                                        <tr>
                                                                            <th colspan="3"><s:property
                                                                                    value="service.serviceName"/></th>
                                                                        </tr>
                                                                        <tr>
                                                                            <td height="5"></td>
                                                                        </tr>
                                                                        <s:iterator value="supplies">
                                                                            <tr>
                                                                                <td width="60"><input type="text"
                                                                                                      class="form-control alloptions"
                                                                                                      data-placement="top"
                                                                                                      maxlength="25"
                                                                                                      data-supply-id="<s:property value="supplyId" />"/>
                                                                                </td>
                                                                                <td><s:property
                                                                                        value="supplyName"/></td>
                                                                                <td>
                                                                                    <button class="btn s33"
                                                                                            type="button"
                                                                                            onclick="showSupplyDetail(
                                                                                                <s:property
                                                                                                        value="supplyId"/>)">
                                                                                        <xms:localization
                                                                                                text="Detail"/>
                                                                                    </button>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="5"></td>
                                                                            </tr>
                                                                        </s:iterator>
                                                                    </s:iterator>
                                                                </s:else>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class=" text-right pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <button class="btn s33" type="reset">
                                                    <xms:localization text="Clear"/>
                                                </button>
                                                <button class="btn s33" type="button" onclick="orderSupplies()">
                                                    <xms:localization text="Order Supplies"/>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="supply-detail-dialog" title='<xms:localization text="Supply Detail" />'></div>
<script type="text/javascript">
    var supplyDetailDialog = $("#supply-detail-dialog").dialog({
        modal: true,
        buttons: {
            "<xms:localization text="OK" />": function () {
                $(this).dialog("close");
            }
        },
        width: "auto",
        show: {
            effect: "fade",
            duration: 300
        },
        autoOpen: false
    });

    function showSupplyDetail(id) {
        var data = {
            "id": id
        };
        $.post("supplies_detail.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                supplyDetailDialog.html(res.content);
                supplyDetailDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function wsGetSupplyList() {
        var result = "{";
        $("#ws-supply-order-supplies-form input[data-supply-id]").each(function () {
            result += "\"" + $(this).attr("data-supply-id") + "\":\"" + $(this).val() + "\",";
        });
        return result.substring(0, result.length - 1) + "}";
        ;
    }

    function orderSupplies() {
        var data = "supplyList=" + wsGetSupplyList();
        loadingDialog.dialog("open");
        $.post("supplies_order.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            console.log(res.errorCode);
            if (res.errorCode == "SUCCESS") {
                alertDialog.html('<xms:localization text="Order supplies successfull." />');
                alertDialog.dialog("open");
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
</script>