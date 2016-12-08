$(document).ready(function () {

    //Template for the post/comment section on the group page section using Mustache.js. MODIFY AT YOUR OWN RISK.
    // var template = "" +
    //         "{{#Posts}}<div class='feed_box' id='{{postID}}'>" +
    //         "<div class='feed_left'>" +
    //         "<p><img class='userimg' src='images/temp_logo.png'></p>" +
    //         "<p>{{ownerFirstName}}&nbsp;{{ownerLastName}}</p>" +
    //         "</div>" +
    //         "<div class='feed_right'>" +
    //         "<p>{{postContent}}</p>" +
    //         "<p class='likebox'>" +
    //         "Total Like : {{pLikes}} &nbsp;|&nbsp;" +
    //         "<a class='link_btn dislike_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#'>" +
    //         "Dislike</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn like_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#'>" +
    //         "Like</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn edit_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#'>" +
    //         "Edit</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn delete_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#'>" +
    //         "Delete</a>" +
    //         "</p>" +
    //         "<div class='clear'></div>" +
    //         "<div class='comment_div'>" +
    //         "{{#Comments}}" +
    //         "<div class='clear'></div>" +
    //         "<div class='comment_ele'>" +
    //         "<p><a class='link_btn' href='#'>{{cFirstName}}&nbsp;{{cLastName}}</a></p>" +
    //         "<p>{{commentContent}}</p>" +
    //         "<p class='likebox'>" +
    //         "Total Like : {{cLikes}} &nbsp;|&nbsp;" +
    //         "<a class='link_btn dislike_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#'>" +
    //         "Dislike</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn like_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#'>" +
    //         "Like</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn edit_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#'>" +
    //         "Edit</a>&nbsp;|&nbsp;" +
    //         "<a class='link_btn delete_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#'>" +
    //         "Delete</a>" +
    //         "</p>" +
    //         "</div>{{/Comments}}" +
    //         "</div>" +
    //         "<div class='clear'></div>" +
    //         "<p>" +
    //         "<form id='commentform_{{postID}}' class = 'comment_submit' action='javascript:void(0);' method='get'>" +
    //         "<input type='hidden' name='action' value='comment'/>" +
    //         "<input type='hidden' name='post_id' value='{{postID}}'/>" +
    //         "<input class='input comment_input form-control' style = 'width:75%' type='text' name='comment' id='comment_{{postID}}' placeholder='Write your comment here'/>" +
    //         "<input class='btn btn-primary' postid='{{postID}}' type='submit' name='sendbtn' value='>'/>" +
    //         "<br><br><span class='lbl-error' name = 'err_comment'></span>" +
    //         "</form></p></div>" +
    //         "<div class='clear'></div>" +
    //         "</div>{{/Posts}}";
    //
    //
    // //Load the group page for the requested group id.
    // refresh_grouppage();
    //
    // //Transaction to make a post in the current group.
    // $('#btnpost').on('click', function () {
    //     var post_content = $('#post_feed').val().trim();
    //     var err_label = $('#err_post');
    //     err_label.hide();
    //     if (post_content.length === 0) {
    //         err_label.text("You must add text to your post!");
    //         err_label.fadeIn(600);
    //     } else {
    //         err_label.hide();
    //         var user_id = $('#guser_id').text().trim();
    //         var group_id = $('#group_id').text().trim();
    //         var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_POST&groupID=" + group_id + "&userID=" + user_id + "&content=" + post_content;
    //
    //         $.ajax({
    //             method: 'get',
    //             url: url,
    //             dataType: 'text',
    //             success: function (post_status) {
    //                 console.log("Post-Success");
    //                 console.log("Post-Response: " + post_status);
    //                 refresh_grouppage();
    //             },
    //             error: function () {
    //                 console.log("Post-Error: Aw, It didn't connect to the servlet :(");
    //             }
    //
    //         });
    //
    //     }
    //
    // });
    //
    // //Transaction to make a comment on a specific post.
    // $('body').on('submit', 'form.comment_submit', function (e) {
    //     e.preventDefault();
    //     var comment_form = $(this);
    //     var post_id = (comment_form.find('input[name=post_id]')).val();
    //     var comment_content = (comment_form.find('input[name=comment]')).val().trim();
    //     var err_label = comment_form.find('span[name=err_comment]');
    //     var user_id = $('#guser_id').text().trim();
    //
    //     err_label.text("");
    //     if (comment_content.length === 0) {
    //         err_label.text("You must add text to your comment!");
    //         err_label.hide();
    //         err_label.fadeIn(600);
    //     } else {
    //         err_label.text("");
    //         var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_COMMENT&postID=" + post_id + "&userID=" + user_id + "&content=" + comment_content;
    //
    //         $.ajax({
    //             method: 'get',
    //             url: url,
    //             dataType: 'text',
    //             success: function (post_status) {
    //                 console.log("Post-Success");
    //                 console.log("Post-Response: " + post_status);
    //                 refresh_grouppage();
    //             },
    //             error: function () {
    //                 console.log("Post-Error: Aw, It didn't connect to the servlet :(");
    //             }
    //
    //         });
    //     }
    //
    //
    // });
    //
    // //Transaction to like a post on the group page.
    // $('body').on('click', 'a.link_btn.like_post_btn', function () {
    //
    //     var user_id = $(this).attr("post_userid");
    //     var post_id = $(this).attr("postid");
    //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=LIKE_POST&postID=" + post_id + "&userID=" + user_id;
    //
    //     $.ajax({
    //         method: 'get',
    //         url: url,
    //         dataType: 'text',
    //         success: function (post_status) {
    //             console.log("Post:Like-Success");
    //             console.log("Post:Like-Response: " + post_status);
    //             refresh_grouppage();
    //         },
    //         error: function () {
    //             console.log("Post:Like-Error: Aw, It didn't connect to the servlet :(");
    //         }
    //
    //     });
    //
    //
    //
    // });
    //
    //
    // //Transaction to dislike a post on the group page.
    // $('body').on('click', 'a.link_btn.dislike_post_btn', function () {
    //
    //     var user_id = $(this).attr("post_userid");
    //     var post_id = $(this).attr("postid");
    //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=DISLIKE_POST&postID=" + post_id + "&userID=" + user_id;
    //
    //     $.ajax({
    //         method: 'get',
    //         url: url,
    //         dataType: 'text',
    //         success: function (post_status) {
    //             console.log("Post:Dislike-Success");
    //             console.log("Post:Dislike-Response: " + post_status);
    //             refresh_grouppage();
    //         },
    //         error: function () {
    //             console.log("Post:Dislike-Error: Aw, It didn't connect to the servlet :(");
    //         }
    //
    //     });
    // });
    //
    //
    // //Transaction to like a comment on the group page.
    // $('body').on('click', 'a.link_btn.like_comment_btn', function () {
    //
    //     var user_id = $(this).attr("comment_userid");
    //     var comment_id = $(this).attr("commentid");
    //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=LIKE_COMMENT&commentID=" + comment_id + "&userID=" + user_id;
    //
    //     $.ajax({
    //         method: 'get',
    //         url: url,
    //         dataType: 'text',
    //         success: function (comment_status) {
    //             console.log("Comment:Like-Success");
    //             console.log("Comment:Like-Response: " + comment_status);
    //             refresh_grouppage();
    //         },
    //         error: function () {
    //             console.log("Comment:Like: Aw, It didn't connect to the servlet :(");
    //         }
    //
    //     });
    // });
    //
    //
    // //Transaction to dislike a comment on the group page.
    // $('body').on('click', 'a.link_btn.dislike_comment_btn', function () {
    //
    //     var user_id = $(this).attr("comment_userid");
    //     var comment_id = $(this).attr("commentid");
    //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=DISLIKE_COMMENT&commentID=" + comment_id + "&userID=" + user_id;
    //
    //     $.ajax({
    //         method: 'get',
    //         url: url,
    //         dataType: 'text',
    //         success: function (comment_status) {
    //             console.log("Comment:Dislike-Success");
    //             console.log("Comment:Dislike-Response: " + comment_status);
    //             refresh_grouppage();
    //         },
    //         error: function () {
    //             console.log("Comment:Dislike: Aw, It didn't connect to the servlet :(");
    //         }
    //
    //     });
    // });
    //
    //
    //
    // //Transaction to delete a post on the group page.
    // $('body').on('click', 'a.link_btn.delete_post_btn', function () {
    //
    //     var user_id = $(this).attr("post_userid");
    //     var post_id = $(this).attr("postid");
    //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_POST&postID=" + post_id + "&userID=" + user_id;
    //
    //     $.ajax({
    //         method: 'get',
    //         url: url,
    //         dataType: 'text',
    //         success: function (comment_status) {
    //             console.log("Post:Delete-Success");
    //             console.log("Post:Delete-Response: " + comment_status);
    //             refresh_grouppage();
    //         },
    //         error: function () {
    //             console.log("Post:Delete: Aw, It didn't connect to the servlet :(");
    //         }
    //
    //     });
    // });
    //
    //
    //
    // //Transaction to delete a comment on the group page.
    // // $('body').on('click', 'a.link_btn.delete_comment_btn', function () {
    // //
    // //     var user_id = $(this).attr("comment_userid");
    // //     var comment_id = $(this).attr("commentid");
    // //     var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_COMMENT&commentID=" + comment_id + "&userID=" + user_id;
    // //
    // //     $.ajax({
    // //         method: 'get',
    // //         url: url,
    // //         dataType: 'text',
    // //         success: function (comment_status) {
    // //             console.log("Comment:Delete-Success");
    // //             console.log("Comment:Delete-Response: " + comment_status);
    // //             refresh_grouppage();
    // //         },
    // //         error: function () {
    // //             console.log("Comment:Delete: Aw, It didn't connect to the servlet :(");
    // //         }
    // //
    // //     });
    // // });
    // //
    // //
    //
    //
    //
    //
    //





//getMessages();
//$("#btnsend").click(function() {sendMessage();});
//$("#btn_refresh").click(function() {getMessages();});
//function getMessages() {
//  var senderId = parseInt($("#guser_id").text());
//    var $url = "/ProjectSite/ProcessMessageGet?userId=" + senderId;
//
//    $.ajax({
//        method: 'get',
//        url: $url,
//        dataType: 'application/json',
//        success: function (response) {
//        },
//        error: function (e) {
//            $("#messages").empty()
//            var messages = $.parseJSON( e.responseText);
//            $(".wrapper").append( "<div id=\"messages\"><\/div>" );
//            for(m in messages.messages) {
//              $("#messages").append("<div id=\""+messages.messages[m].message_id + "\"></div>")
//              $("#"+messages.messages[m].message_id).append("<h3>" + messages.messages[m].message_sender_name +"<\/h3>")
//              $("#"+messages.messages[m].message_id).append("<h4>" + messages.messages[m].message_subject +"<\/h4>")
//              $("#"+messages.messages[m].message_id).append("<p>" + messages.messages[m].message_content +"<\/p>")
//              $("#"+messages.messages[m].message_id).append("<a onclick=\"deleteMessage("+ messages.messages[m].message_id+ ")\" href=\"javascript:void(0);\">Delete<\/a>")
//              $("#"+messages.messages[m].message_id).append("<hr>");
//            }
//
//
//        }
//
//    });
//}

$("#btn_messages").click(function() {var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "messagepage.jsp"
        window.location = link;});


$("#btn_CRL_Panel").click(function() {var link = "http://" + window.location.hostname + ":" + window.location.port + "/ProjectSite/" + "CRLpage.jsp"
        window.location = link;});

//
//    function sendMessage() {
//        var senderId = parseInt($("#guser_id").text());
//        var receiver = $("#receiver").val();
//        var subject = $("#subject").val();
//        var message = $("#message").val()
//        var $url = "/ProjectSite/ProcessMessageSend?sender=" + senderId +
//          "&receiver=" +  receiver +
//          "&subject=" + subject +
//          "&message=" + message;
//
//        $.ajax({
//            method: 'get',
//            url: $url,
//            dataType: 'json',
//            success: function (send_sucess) {
//              $("#btn_refresh").click();
//            },
//            error: function () {
//              $("#btn_refresh").click();
//            }
//
//        });
//    }
//
//
//
//});

//function deleteMessage(id) {
//    var $url = "/ProjectSite/ProcessMessageDelete?message_id=" + id
//
//    $.ajax({
//        method: 'get',
//        url: $url,
//        dataType: 'json',
//        success: function (send_sucess) {
//          $("#btn_refresh").click();
//        },
//        error: function () {
//          $("#btn_refresh").click();
//        }
//
//    });
//}
});