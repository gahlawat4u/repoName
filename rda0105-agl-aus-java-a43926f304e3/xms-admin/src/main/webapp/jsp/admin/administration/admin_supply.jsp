<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Supplies List"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Supplies List"/></li>
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
                                    <xms:localization text="Edit Supplies"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <s:if test="!hasActionErrors()">
                                        <div class="form-body pal">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <div class="form-group mgb" id="list-supply-data">
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
                                                                                    <td><s:property
                                                                                            value="supplyName"/></td>
                                                                                    <td>
                                                                                        <button class="btn s33"
                                                                                                type="button"
                                                                                                onclick="showSupplyDetail(
                                                                                                    <s:property
                                                                                                            value="supplyId"/>)">
                                                                                            <xms:localization
                                                                                                    text="Edit"/>
                                                                                        </button>
                                                                                        <button class="btn s33"
                                                                                                type="button"
                                                                                                onclick="showSupplyLocalize(
                                                                                                    <s:property
                                                                                                            value="supplyId"/>)">
                                                                                            <xms:localization
                                                                                                    text="Localize Supply"/>
                                                                                        </button>
                                                                                        <button class="btn s33"
                                                                                                type="button"
                                                                                                onclick="showSupplyDelete(
                                                                                                    <s:property
                                                                                                            value="supplyId"/>)">
                                                                                            <xms:localization
                                                                                                    text="Remove"/>
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
                                                    <button class="btn s33" type="button" onclick="addNewSupply()">
                                                        <xms:localization text="Add Supply"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </s:if>
                                    <s:else>
                                        <s:actionerror/>
                                    </s:else>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="supply-detail-dialog" title='<xms:localization text="Supply Detail" />'></div>
    <div id="supply-delete-dialog" title='<xms:localization text="Delete Supply" />'></div>
    <div id="supply-localization-dialog" title='<xms:localization text="Localization Supply" />'></div>
    <div id="supply-add-dialog" title='<xms:localization text="Add Supply" />'></div>
</div>
<!--END CONTENT-->
<script type="text/javascript">
    function showSupplyDetail(id) {
        var data = {
            'id': id
        };
        loadDialog("admin_supply_detail.ix?reqType=json", data,
                "admin_supply_edit", "Edit", "Cancel",
                "supply-detail-dialog", "Edit Supply", "list-supply-data");
    }

    function showSupplyLocalize(id) {
        var data = {
            'id': id
        };
        loadDialog("admin_supply_localization.ix?reqType=json", data,
                "admin_supply_localization", "Update", "Cancel",
                "supply-localization-dialog", "Localization Supply", "list-supply-data");
    }

    function showSupplyDelete(id) {
        var data = {
            'id': id
        };
        loadDialog("admin_supply_delete.ix?reqType=json", data,
                "admin_supply_delete", "Delete", "Cancel",
                "supply-delete-dialog", "Delete Supply", "list-supply-data");
    }

    function addNewSupply() {
        loadDialog("admin_supply_add.ix?reqType=json", "",
                "admin_supply_add", "Add", "Cancel",
                "supply-add-dialog", "Add Supply", "list-supply-data");
    }


</script>