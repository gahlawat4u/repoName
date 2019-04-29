<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered table-hover mg0">
    <thead>
    <tr>
        <th><xms:localization text="WebFreightID"/></th>
        <th><xms:localization text="CustomerID"/></th>
        <th><xms:localization text="Alt User Name"/></th>
        <th><xms:localization text="Password"/></th>
        <th><xms:localization text="Created Date"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="webships!=null && webships.totalRecords>0">
        <s:iterator value="webships.records">
            <tr webship-id="<s:property value="webshipId" />" style="cursor: pointer;">
                <td><s:property value="webshipId"/></td>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="name"/></td>
                <td><s:property value="password"/></td>
                <td><s:property value="createDate"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="5"><xms:localization text="No data available"/>...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records" style="margin-bottom: 15px;">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="webships.startRecord"/> <xms:localization
                    text="to"/> <s:property value="webships.endRecord"/> <xms:localization text="of"/> <s:property
                    value="webships.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!webships.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:getWebships(<s:property value="%{webships.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="webships.pageRange" status="count">
                <s:if test="%{webships.pageRange[#count.index] == webships.currentPage}">
                    <a class="paginate_button current"><s:property value="webships.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:getWebships(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!webships.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:getWebships(<s:property value="%{webships.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#webship-list-result tr[webship-id]").click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            webshipId = $(this).attr("webship-id");
        });
    });


</script>