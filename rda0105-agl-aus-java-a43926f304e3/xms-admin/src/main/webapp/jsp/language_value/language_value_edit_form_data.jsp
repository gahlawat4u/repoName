<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="language-value-form">
    <span class="text-danger"><s:fielderror fieldName="languageValue"/></span>

    <div class="form-group">
        <table class="table" style="font-size: 11px;">
            <tbody>
            <tr>
                <td class="td1"><s:hidden name="languageValue.id"/><xms:localization text="Lang code"/>:<span
                        class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textfield name="languageValue.langCode" cssClass="form-control"
                                                             readonly="true"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Original"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textarea cols="50" rows="5" name="languageValue.original"
                                                            class="form-control" readonly="true"/> <span
                        class="text-danger"><s:fielderror fieldName="languageValue.original"/></span></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Destination"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textarea cols="50" rows="5" name="languageValue.destination"
                                                            class="form-control"/> <span
                        class="text-danger"><s:fielderror fieldName="languageValue.destination"/></span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>