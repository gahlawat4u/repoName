<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="portlet-body b12 b11">
        <div class="form-group">
            <table class="s36">
                <tr>
                    <td><xms:localization text="Print Rate Sheets"/></td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Check All"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Check None"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Print Checked Rate Sheets"/>
                        </button>
                    </td>
                    <td><input type="checkbox" value=""></td>
                    <td>Pdf</td>
                    <td><input type="checkbox" value=""></td>
                    <td>Excel</td>
                </tr>
            </table>
        </div>
        <s:iterator value="rateSheets">
            <div class="form-group b23">
                <table class="s36">
                    <tr>
                        <td width="290"><s:property value="key"/></td>
                        <td>
                            <input type="checkbox" disabled="disabled"/>
                        </td>
                        <td><s:select list="listRateType" listKey="key" listValue="value"
                                      value='value.profileBaseRateDetail[0].rateType'
                                      class="form-control"></s:select></td>
                        <td width="50">
                            <s:if test="%{value.profileBaseRateDetail[0].rateP != null }">
                                <input type="text" class="form-control alloptions"
                                       value='<s:property value="value.profileBaseRateDetail[0].rateP" />'/>
                            </s:if>
                            <s:else>
                                <input type="text" class="form-control alloptions" value='0.0'/>
                            </s:else>
                        </td>
                        <td>%</td>

                    </tr>
                </table>
            </div>
        </s:iterator>
    </div>
</div>