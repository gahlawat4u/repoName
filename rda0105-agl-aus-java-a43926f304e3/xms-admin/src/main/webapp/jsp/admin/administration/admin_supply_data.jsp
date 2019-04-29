<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="s36">
    <s:if test="serviceList==null || serviceList.size()==0">
        <xms:localization text="No data available..."/>
    </s:if>
    <s:else>
        <s:iterator value="serviceList">
            <tr>
                <th colspan="3"><s:property value="service.serviceName"/></th>
            </tr>
            <tr>
                <td height="5"></td>
            </tr>
            <s:iterator value="supplies">
                <tr>
                    <td><s:property value="supplyName"/></td>
                    <td>
                        <button class="btn s33" type="button"
                                onclick="showSupplyDetail(<s:property value="supplyId"/>)">
                            <xms:localization text="Edit"/>
                        </button>
                        <button class="btn s33" type="button"
                                onclick="showSupplyLocalize(<s:property value="supplyId"/>)">
                            <xms:localization text="Localize Supply"/>
                        </button>
                        <button class="btn s33" type="button"
                                onclick="showSupplyDelete(<s:property value="supplyId"/>)">
                            <xms:localization text="Remove"/>
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