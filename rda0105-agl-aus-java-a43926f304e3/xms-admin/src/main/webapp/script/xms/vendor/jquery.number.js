(function ($) {

    "use strict";

    $.fn.number = function (number, decimals, dec_point, thousands_sep) {

        if (number === true) {
            if (this.is('input:text')) {
                var bvalue = $(this).val();
                $(this).val(toDecimal(bvalue));
                return this.on({
                    'keypress.format': function (e) {
                        var $this = $(this);
                        return Numericvaluewithoutminus(e, $this.val(), true);
                    },
                    'blur.format': function (e) {
                        var $this = $(this);
                        $this.val(toDecimal($this.val()));
                    },
                    'paste.format': function (e) {
                        var $this = $(this),
                            original = e.originalEvent,
                            val = null;

                        // Get the text content stream.
                        if (window.clipboardData && window.clipboardData.getData) { // IE
                            val = window.clipboardData.getData('Text');
                        } else if (original.clipboardData && original.clipboardData.getData) {
                            val = original.clipboardData.getData('text/plain');
                        }
                        $this.val(toDecimal(val));
                        e.preventDefault();
                        return false;
                    }

                })
            }
            ;
        }
    }
})(jQuery);
