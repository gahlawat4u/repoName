<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_move_airbill">
    <div id="md-7" title="Move Airbill">
        <div class="form-group">
            <table class="s36 b24" style="width: auto; margin-bottom: auto">
                <tr>
                    <td><xms:localization text="Airbill"/></td>
                    <td colspan="4"><input class="form-control alloptions " type="text" value="test" disabled=""></td>
                </tr>
                <tr>
                    <td><xms:localization text="Invoice"/></td>
                    <td><label class="radio-inline cl666"> <input id="optionsDocuments" type="radio" checked="checked"
                                                                  value="" name="optionsRadios">
                    </label></td>
                    <td><xms:localization text="Existing:"/><span class="s30">*</span></td>
                    <td colspan="2"><select class="form-control">
                        <option>Show Today Only</option>
                        <option value="0">Thailand</option>
                        <option value="1">Trinidad and Tobago</option>
                        <option value="2">Turkey</option>
                    </select></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label class="radio-inline cl666"> <input id="optionsParcel" type="radio" value=""
                                                                  name="optionsRadios">
                    </label></td>
                    <td><xms:localization text="New"/></td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Select Customer"/>
                        </button>
                    </td>
                    <td><input class="form-control alloptions form_datetime" type="text" placeholder=""></td>
                </tr>
                <tr>
                    <td colspan="5"><xms:localization
                            text="Any customer adjustment credit will also be moved to the target invoice"/></td>
                </tr>
                <tr>
                    <td colspan="3"><xms:localization text="Recalc Customer Cost"/></td>
                    <td><input type="checkbox"></td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
<script type="text/javascript">
</script>