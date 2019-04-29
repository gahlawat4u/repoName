<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<div class="portlet-header">
    <div class="col-lg-8 flr">
        <div class="form-group flr mgb">
            <select id="catType" name="catType" class="form-control" onchange="changePage(1)">
                <option value="0"><xms:localization text="All Categories"/></option>
                <option value="1"><xms:localization text="Payments"/></option>
            </select>
        </div>
    </div>
</div>
<div class="portlet-body" style="padding: 0px;">
    <div class="tab-content responsive">
        <div class="tab-pane fade in active">
            <div class="row">
                <div class="col-lg-12">
                    <form id="notes_and_follow_up_search_form">
                        <s:hidden name="invoiceCode"/>
                        <s:hidden name="page"/>
                        <s:hidden name="orderField"/>
                        <s:hidden name="orderType"/>
                        <table class="table mg0">
                            <tr>
                                <th class="s42"><xms:localization text="Show"/> <s:select id="pageSize" name="pageSize"
                                                                                          list="pageSizes"
                                                                                          onchange="doSearch()"
                                                                                          cssStyle="height:22px; padding-top:1px;"/>
                                    <xms:localization text="entries"/></th>
                            </tr>
                        </table>
                    </form>
                    <div id="note_and_follow_up_result">
                        <table class="table table-bordered mg0 table-hover" id="note_and_follow_up_table">
                            <thead>
                            <tr>
                                <th><xms:localization text="Follow Up?"/></th>
                                <th><xms:localization text="Follow Up Date"/></th>
                                <th><xms:localization text="Modify Date"/></th>
                                <th><xms:localization text="Category"/></th>
                                <th><xms:localization text="Invoice No"/></th>
                                <th><xms:localization text="Note"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:if test="notes==null || notes.totalRecords==0">
                                <tr>
                                    <td colspan="6"><xms:localization text="No data available..."/></td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="notes.records">
                                    <tr>
                                        <td><s:checkbox name="check"/></td>
                                        <td><s:property value="followUpDate"/></td>
                                        <td><s:property value="modifyDate"/></td>
                                        <td><s:property value="category"/></td>
                                        <td><s:property value="invoiceCode"/></td>
                                        <td><s:property value="note"/></td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                        <div class="dataTables_paginate">
                            <s:if test="notes!=nul">
                                <s:if test="notes.hasPrev()">
                                    <a href="javascript:changePage(<s:property value="%{notes.currentPage - 1}"/>)"
                                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                                </s:if>
								<span> <s:iterator value="notes.pageRange" status="count">
                                    <s:if test="%{notes.pageRange[#count.index] == notes.currentPage}">
                                        <a class="paginate_button current"><s:property value="notes.currentPage"/></a>
                                    </s:if>
                                    <s:else>
                                        <a class="paginate_button"
                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                    </s:else>
                                </s:iterator>
								</span>
                                <s:if test="notes.hasNext()">
                                    <a class="paginate_button next"
                                       href="javascript:changePage(<s:property value="%{notes.currentPage+1}"/>)"><xms:localization
                                            text="Next"/></a>
                                </s:if>
                            </s:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var fieldList = ["`check`", "follow_up_date", "modify_date", "category", "invoicecode", "note"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#note_and_follow_up_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function doSearch() {
        var data = $("#notes_and_follow_up_search_form").serialize();
        data += "&catType=" + $("#catType option:selected").val();
        loadingDialog.dialog("open");
        $.post("note_and_follow_up_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#note_and_follow_up_result").html(res.content);
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
        doSearch();
    }


</script>