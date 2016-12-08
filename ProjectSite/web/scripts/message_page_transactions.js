$(document).ready(function () {




$("#btn_go_home").click(function() {
     window.history.back();
});


getMessages();
$("#btnsend").click(function() {sendMessage();});
$("#btn_refresh").click(function() {getMessages();});
function getMessages() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessMessageGet?userId=" + senderId;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
        },
        error: function (e) {
            $("#messages").empty()
            var messages = $.parseJSON( e.responseText);
            $(".wrapper").append( "<div id=\"messages\"><\/div>" );
            for(m in messages.messages) {
              $("#messages").append("<div id=\""+messages.messages[m].message_id + "\"></div>")
              $("#"+messages.messages[m].message_id).append("<h3>" + messages.messages[m].message_sender_name +"<\/h3>")
              $("#"+messages.messages[m].message_id).append("<h4>" + messages.messages[m].message_subject +"<\/h4>")
              $("#"+messages.messages[m].message_id).append("<p>" + messages.messages[m].message_content +"<\/p>")
              $("#"+messages.messages[m].message_id).append("<a onclick=\"deleteMessage("+ messages.messages[m].message_id+ ")\" href=\"javascript:void(0);\">Delete<\/a>")
              $("#"+messages.messages[m].message_id).append("<hr>");
            }


        }

    });
}

    function sendMessage() {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessMessageSend?sender=" + senderId +
          "&receiver=" +  receiver +
          "&subject=" + subject +
          "&message=" + message;

        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (send_sucess) {
              $("#btn_refresh").click();
            },
            error: function () {
              $("#btn_refresh").click();
            }

        });
    }



});

function deleteMessage(id) {
    var $url = "/ProjectSite/ProcessMessageDelete?message_id=" + id

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'json',
        success: function (send_sucess) {
          $("#btn_refresh").click();
        },
        error: function () {
          $("#btn_refresh").click();
        }

    });
}
