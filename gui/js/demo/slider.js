$(document).ready(function() {
    $("#slider").slider({
        animate: true,
        value:22,
        min: 15.00,
        max: 29.00,
        step: 0.1,
        slide: function(event, ui) {
            update(ui.value); //changed
        }
    });

    //Added, set initial value.
    $("#amount-label").text(22);
    
    update();
});

//changed. now with parameter
function update(val) {
  //changed. Now, directly take value from ui.value. if not set (initial, will use current value.)
  var $amount = val;

   $( "#amount-label" ).text($amount);
}