<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form-add-weight-break">
    <div class="col-xs-12">
        <s:set var="paramMap"
               value="#{'{currentWeight}':'weightBreakModel.currentWeight', '{nextWeight}':'weightBreakModel.nextWeight'}"></s:set>
        <p>
            <s:if test="currentWeight != '' && nextWeight == ''">
                <xms:localization text="Please select a weight break greater than {currentWeight}."
                                  paramMap="paramMap"/>
            </s:if>
            <s:if test="currentWeight != '' && nextWeight != ''">
                <xms:localization
                        text="Please select a weight break greater than {currentWeight} and less than {nextWeight}."
                        paramMap="paramMap"/>
            </s:if>
        </p>

        <div class="form-group">
            <s:textfield name="weightBreakModel.requestWeight" cssClass="form-control" id="request_weight"/>
        </div>
    </div>
    <s:hidden name="weightBreakModel.currentWeight"/>
    <s:hidden name="weightBreakModel.nextWeight"/>
    <s:hidden name="weightBreakModel.htmlString"/>
    <s:hidden name="weightBreakModel.globalIndex"/>
    <s:hidden name="weightBreakModel.currentIndex"/>
    <s:hidden name="weightBreakModel.displayName"/>
    <s:hidden name="weightBreakModel.baseRateData"/>
</s:form>
<script type="text/javascript">
    $('#request_weight').number(true, 2, '.', '');
</script>