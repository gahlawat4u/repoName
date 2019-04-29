/*
 * The table sorter plug-in using to add sorting function to a table
 * Author: dattrinh
 * Date: 16-11-2015 
 */
(function ($) {
    $.fn.tablesorter = function (options) {
        // This is default settings and extra options
        var defaults = {
            fieldList: [],
            sortFieldId: "orderField",
            sortTypeId: "orderType"
        };
        var settings = $.extend(defaults, options);
        // Add sort-field attribute to header column
        this.find("thead>tr>th").each(function (i) {
            if (settings.fieldList[i] != "") {
                // Add sort-field attribute
                $(this).attr("sort-field", settings.fieldList[i]);
                // Add hand pointer
                $(this).css("cursor", "pointer");
                // Add sorting icon
                $(this).append(" <i class='fa fa-sort'></i>");
            }
        });
        // Add hand pointer and click to sort event to sorting table column
        this.find("thead>tr>th[sort-field]").each(function (i) {
            var curField = $("#" + settings.sortFieldId).val();
            var curType = $("#" + settings.sortTypeId).val();
            if (curField == $(this).attr("sort-field")) {
                // Change sorting icon of current sorting column
                if (curType == 0) {
                    $(this).find("i").removeClass().addClass("fa fa-sort-up");
                } else {
                    $(this).find("i").removeClass().addClass("fa fa-sort-down");
                }
            }
            // Add click event handler
            $(this).click(function () {
                // Get current sorting
                var curField = $("#" + settings.sortFieldId).val();
                var curType = $("#" + settings.sortTypeId).val();
                var field = $(this).attr("sort-field");
                var type = 0;
                if (curField == field) {
                    type = (curType == 0) ? 1 : 0;
                }
                // Change current sorting
                $("#" + settings.sortFieldId).val(field);
                $("#" + settings.sortTypeId).val(type);
                // Call search function
                settings.callback();
            });
        });
        return this;
    };
})(jQuery);