<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_change_sender_address">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <s:if test="type==1">
                    <tr>
                        <td><xms:localization
                                text="Are you sure you want to change sender address to customer physical address"/>?
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr>
                        <td><xms:localization
                                text="Are you sure you want to change Sender Address to Sender Address from Web Freight"/>?
                        </td>
                    </tr>
                </s:else>
            </table>
        </form>
    </div>
</div>