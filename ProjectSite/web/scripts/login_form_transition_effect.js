$(document).ready(function (){

    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

$("#register-submit").click(function() {
     register() ;
});
});



       function register() {
        var email = $("#remail").val();
        var address = $("#address").val();
        var password = $("#rpassword").val();
        var first_name = $("#first_name").val()
        var last_name = $("#last_name").val()
        var $url = "/ProjectSite/ProcessRegistration?email=" + email + 
          "&address=" +  address +
          "&password=" + password +
          "&first_name=" + first_name +
  "&last_name=" + last_name;
        console.log($url);
        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
            },
            error: function () {
            }

        });
    }





