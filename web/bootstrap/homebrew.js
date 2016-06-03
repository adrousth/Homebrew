$(document).ready(function(){
    getTotalQty();
    $("input[type=number]").bind('keyup input', function(){
        getTotalQty();
    });
});

function getTotalQty() {
    var value = 0;
    var total = $("#totalQty");
    $("input.qty").each(function() {
        var i = parseFloat($(this).val());

        if (!isNaN(i))
        {
            value += i;
        }
    });
    if (value > 10) {
        if (!$('.popover').hasClass('in')) {
            total.addClass("alert-danger");
            total.popover({content: "Total can not be greater than 10oz", trigger: "manual", placement: "bottom"});
            total.popover("show");
        }


    } else {

        if ($('.popover').hasClass('in')) {
            total.removeClass("alert-danger");
            $('[data-toggle="popover"]').popover("hide");
        }
    }
    total.text(value);
}
