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
        <li class="hidden"><a href="#">Admin Franchise Setting</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">Admin Franchise Setting</li>
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
                                <div class="caption">Admin Franchise Setting</div>
                                <!--<div class="col-lg-8 flr">
                        <div class="form-group flr mgb">
                          <table class="s36">
                            <tbody>
                              <tr>
                                <td><select class="form-control">
                                    <option>Option</option>
                                    <option value="0">123</option>
                                    <option value="1">5344</option>
                                    <option value="2">2443</option>
                                  </select></td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>-->
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive" style="height: 650px !important;">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b class="s30">Warning :</b> <br/> - changing any of the below
                                                    values can adversely affect the system! <br/> - Double-click the
                                                    entry to modify its value.
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
                                                                    <td><s:select name="pageSize" list="pageSizes"
                                                                                  onchange="onChangePageSize()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <table class="table table-bordered mg0">
                                                    <thead>
                                                    <tr>

                                                        <th>Name</th>
                                                        <th>Value</th>
                                                        <th>Edit Admin Level</th>
                                                        <th>Description</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:if test="franchiseSettingListExtModels.records.size != 0">
                                                        <s:iterator value="franchiseSettingListExtModels.records">
                                                            <tr>
                                                                <td><s:property value="name"/></td>
                                                                <td><s:property value="value"/></td>
                                                                <td><s:property value="adminLevel"/></td>
                                                                <td><s:property value="description"/></td>
                                                            </tr>
                                                        </s:iterator>
                                                    </s:if>
                                                    <s:else>
                                                        <tr>
                                                            <td colspan="6"><xms:localization text="No record to view"/>
                                                                ...
                                                            </td>
                                                        </tr>
                                                    </s:else>


                                                    </tbody>
                                                </table>

                                                <div class="dataTables_paginate">
                                                    <s:if test="!franchiseSettingListExtModels.hasPrev()">
                                                        <a class="paginate_button previous disabled"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a href="javascript:changePage(<s:property value="%{franchiseSettingListExtModels.currentPage - 1}"/>)"
                                                           class="paginate_button previous"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:else>
													<span> <s:iterator value="franchiseSettingListExtModels.pageRange"
                                                                       status="count">
                                                        <s:if test="%{franchiseSettingListExtModels.pageRange[#count.index] == franchiseSettingListExtModels.currentPage}">
                                                            <a class="paginate_button current"><s:property
                                                                    value="franchiseSettingListExtModels.currentPage"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button"
                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                        </s:else>
                                                    </s:iterator>
													</span>
                                                    <s:if test="!franchiseSettingListExtModels.hasNext()">
                                                        <a class="paginate_button next" href="#"><xms:localization
                                                                text="Next"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a class="paginate_button next"
                                                           href="javascript:changePage(<s:property value="%{franchiseSettingListExtModels.currentPage+1}"/>)"><xms:localization
                                                                text="Next"/></a>
                                                    </s:else>
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
    $(document).ready(function () {
        var page = 1;
        var pageSize = $("select[name='pageSize'] option:selected").val();
        //doSearch(pageSize, page);
    });
    function doSearch(pageSize, page) {
        var data = "page=" + page;
        data = data + "&pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("franchise_setting_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#franchise-setting-list").html(res.content);
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
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, page);
    }

    function onChangePageSize() {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
    }


</script>