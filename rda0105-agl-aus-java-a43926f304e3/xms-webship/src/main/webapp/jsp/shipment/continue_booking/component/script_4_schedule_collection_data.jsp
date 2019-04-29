<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script type="text/javascript">
    $(document).ready(function () {
        $('.schedule-time').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
        $(".schedule-time").datetimepicker("update", $("#shipment-date-input").val());
        setdefaulttime();
        onScheduleCollectionChange();
    });

    function onScheduleCollectionChange() {
        var scheduleCollectionType = $("#ws-schedule-collection-select option:selected").val();
        if (scheduleCollectionType == 1) {
            $("#ws-schedule-collection-div").show();
        } else {
            $("#ws-schedule-collection-div").hide();
        }
    }


</script>