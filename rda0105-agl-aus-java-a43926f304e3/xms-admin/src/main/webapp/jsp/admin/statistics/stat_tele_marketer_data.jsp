<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_tele_marketer">
    <s:if test="teleStat1!=null && teleStat2!=null">
		<span> <xms:localization text="No.of prospects entered for today "/> <s:property value="teleStat1[0]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of prospects entered for current week "/> <s:property value="teleStat1[1]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of prospects entered for current month "/> <s:property value="teleStat1[2]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of prospects entered for from beginning "/> <s:property value="teleStat1[3]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of qualified prospects entered for today "/> <s:property
                value="teleStat2[0]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of qualified prospects entered for current week "/> <s:property
                value="teleStat2[1]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of qualified prospects entered for month "/> <s:property
                value="teleStat2[2]"/>
		</span>
        <br/>
		<span> <xms:localization text="No.of qualified prospects entered for from beginning "/> <s:property
                value="teleStat2[3]"/>
		</span>
    </s:if>
    <s:else>
        <xms:localization text="No data to display."/>
    </s:else>
</div>