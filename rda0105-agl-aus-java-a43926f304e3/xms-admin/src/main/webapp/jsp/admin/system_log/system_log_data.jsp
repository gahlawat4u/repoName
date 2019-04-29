<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div style="overflow: auto">
    <table class="table table-bordered table-hover mg0 " id="datatable1">
        <thead>
        <tr>
            <th><xms:localization text="Date"/></th>
            <th><xms:localization text="Franchise"/></th>
            <th><xms:localization text="User"/></th>
            <th><xms:localization text="Action"/></th>
            <th><xms:localization text="Table"/></th>
            <th><xms:localization text="Filter"/></th>
            <th><xms:localization text="Changes Mode"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="eventLogModels.records.size != 0">
            <s:iterator value="eventLogModels.records" status="row">
                <tr>
                    <td><s:property value="actionDate"/></td>
                    <td><s:property value="userCode"/></td>
                    <td><s:property value="userName"/></td>
                    <td><s:property value="actionType"/></td>
                    <td><s:property value="actionTable"/></td>
                    <td><s:property value="filter" escape="false"/></td>
                    <td><s:property value="changesMode" escape="false"/></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr>
                <td colspan="8"><xms:localization text="No data available..."/></td>
            </tr>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="eventLogModels.startRecord"/> <xms:localization
                    text="to"/> <s:property value="eventLogModels.endRecord"/> <xms:localization text="of"/> <s:property
                    value="eventLogModels.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!eventLogModels.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{eventLogModels.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="eventLogModels.pageRange" status="count">
                <s:if test="%{eventLogModels.pageRange[#count.index] == eventLogModels.currentPage}">
                    <a class="paginate_button current"><s:property value="eventLogModels.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!eventLogModels.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{eventLogModels.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    <s:if test="eventLogModels==null || eventLogModels.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>
</script>