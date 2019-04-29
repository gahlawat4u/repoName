<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="portlet-body b12 b11">
        <s:iterator value="baseRateModels">
            <div class="form-group b23">
                <table class="s36">
                    <tr>
                        <td width="290"><s:property value="service.serviceName"/></td>
                        <td><s:select list="listRateType" listKey="key" listValue="value" value="1"
                                      class="form-control"></s:select></td>
                        <td width="50"><s:textfield class="form-control alloptions" name="rate" maxlength="25"
                                                    placeholder=""></s:textfield></td>
                        <td>%</td>
                    </tr>
                </table>
            </div>
        </s:iterator>
    </div>
</div>