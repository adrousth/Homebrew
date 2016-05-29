$(document).ready(function(){

    $("input[type=number]").bind('keyup input', function(){
        var value = 0;

        $("input.qty").each(function() {
            var i = parseFloat($(this).val());

            if (!isNaN(i))
            {
                value += i;
            }
        });
        if (value > 10) {
            $("#totalQty").addClass("danger");
        }
        $("#totalQty").val(value);

    });
});
