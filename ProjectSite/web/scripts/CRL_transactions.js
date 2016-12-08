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


$("#btn_getemailList").click(function() {getEmailList();});
$("#btn_refresh").click(function() {getMessages();});
$("#create_ad").click(function() {$("#ad_creation").fadeIn("slow");});
$("#list_ads").click(function() {getAds();});
$("#btn_create_ad").click(function() {
    createAdvertisement(
            1, "date",$("#type").val(),$("#company").val(),$("#item").val(),$("#content").val(),$("#price").val(),$("#num_available").val());
});

$("#btn_getsuggestions").click(function() {getCustomerSuggestions($(" #suggest_space #user_email").val())});;

$("#item_suggestions").click(function() {$("#suggest_space").fadeIn("slow");});
$("#bestsellers").click(function() {$("#best_seller_space").fadeIn("slow"); getBestSellers();});


$("#btn_getgroups").click(function() {getCustomerGroups($(" #user_group_space #user_email").val())});;

$("#user_groups").click(function() {$("#user_group_space").fadeIn("slow");});
$("#user_history").click(function() {$("#user_history_space").fadeIn("slow");});
$("#btn_gethistory").click(function() {getCustomerHistory($("#user_history_space #user_email").val())});
$("#create_transaction").click(function() {$("#create_transaction_space").fadeIn("slow");});




$("#btn_make_transaction").click(function(){createTransaction($("#ad_id").val(), $("#seller_id").val(),$("#consumer_id").val(),$("#create_transaction_space #number_of_units").val(),
$("#create_transaction_space #account_number").val());
});

function getEmailList() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CUSTOMER_LIST";

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
          
              var emails = $.parseJSON( e.responseText);
              var listString = ""
            for (email in emails.emails) {
                listString += emails.emails[email];
                listString += ','
            }
            alert("COPY AND PASTE THE FOLLOWING IN THE TO SECTION OF YOUR EMAIL:" + listString);

        }

    })
    }
    
    
    


    
    
    
      function generateTransaction(ad_id, seller_id,consumer_id,date,units,account_number) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CREATE_ADVERTISEMENT?" + 
          "ad_id=" +  ad_id +
          "&seller_id=" + seller_id +
          "&consumer_id=" + consumer_id +
  "&date=" + date +
   "&item=" + item+
   "&units=" + units;
   "&account_number=" +account_number;

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



    


});

      
    function getAds() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GET_ADS";

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
        },
        error: function (e) {
            $("#list_ads_space").empty()
            var ads = $.parseJSON( e.responseText);
            $("#list_ads_space").append( "<div id=\"ads\"><\/div>" );
            for(m in ads.ads) {
              $("#ads").append("<div id=\""+ads.ads[m].advertisement_id + "\"></div>")
              $("#"+ads.ads[m].advertisement_id).append("<h3>" + ads.ads[m].item_name +"<\/h3>")
              $("#"+ads.ads[m].advertisement_id).append("<h4>" + ads.ads[m].company +"<\/h4>")
              $("#"+ads.ads[m].advertisement_id).append("<p>" + ads.ads[m].content +"<\/p>")
              $("#"+ads.ads[m].advertisement_id).append("<a onclick=\"deleteAd("+ ads.ads[m].advertisement_id + ")\" href=\"javascript:void(0);\">Delete<\/a>")
              $("#"+ads.ads[m].advertisement_id).append("<hr>");
//            }


        }
        $("#list_ads_space").fadeIn("slow");
        $("#ads").fadeIn("slow");
    }});
}

function deleteAd(id) {
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=DELETE_ADVERTISEMENT&ad_id=" +id

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'json',
        success: function (send_sucess) {
            getAds();
        },
        error: function () {
            getAds();
        }

    });
}

    function createAdvertisement(employee_id, date,type,company,item,content,price,available) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CREATE_ADVERTISEMENT" + 
          "&employee_id=" +  employee_id +
          "&date_of_ad=" + date +
          "&type=" + type +
  "&company=" + company +
   "&item=" + item+
   "&content=" + content +
   "&unit_price=" +price +
           "&num_available=" + available;

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
    
        function createTransaction(ad_id, seller_id,consumer_id,number_of_units,account_number) {
        var senderId = parseInt($("#guser_id").text());
        var receiver = $("#receiver").val();
        var subject = $("#subject").val();
        var message = $("#message").val()
        var $url = "/ProjectSite/ProcessCRLTransaction?transaction=GENERATE_TRANSACTION" + 
          "&ad_id=" +  ad_id +
          "&seller_id=" + seller_id +
          "&consumer_id=" + consumer_id +
  "&number_of_units=" + number_of_units +
   "&account_number=" + account_number;

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
    
        function getCustomerGroups(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=CUSTOMER_GROUPS"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#user_group_space").empty()
            var groups = $.parseJSON( e.responseText);
            console.log(groups);
            for(m in groups.groups) {
              $("#user_group_space").append("<h5>" + groups.groups[m] +"<\/h5>")

        }
        $("#user_group_space").fadeIn("slow");

        }

    });
    }
    
    function getCustomerSuggestions(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=ITEM_SUGGESTION"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
            console.log(e);
            var suggestions = $.parseJSON( e.responseText);
             $("#suggest_space").empty()
            var suggestions = $.parseJSON( e.responseText);
            for(m in suggestions.suggestions) {
              $("#suggest_space").append("<h5>" + suggestions.suggestions[m] +"<\/h5>")

        }
        $("#suggest_space").fadeIn("slow");

        }

    });
    }
    
        
    function getCustomerHistory(email) {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=ITEM_SUGGESTION"
    +"&email=" + email;

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#user_history_space").empty()
            var suggestions = $.parseJSON( e.responseText);
            console.log(suggestions)
            for(m in suggestions.suggestions) {
              $("#user_history_space").append("<h5>" + suggestions.suggestions[m] +"<\/h5>")

        }
        $("#user_history_space").fadeIn("slow");

        }

    });
    }
    
    
        function getBestSellers() {
  var senderId = parseInt($("#guser_id").text());
    var $url = "/ProjectSite/ProcessCRLTransaction?transaction=BEST_SELLER";

    $.ajax({
        method: 'get',
        url: $url,
        dataType: 'application/json',
        success: function (response) {
         
        },
        error: function (e) {
             $("#best_seller_space").empty()
            var bestSellers = $.parseJSON( e.responseText);
            console.log(suggestions)
            for(m in bestSellers.groups) {
              $("#best_seller_space").append("<h5>" + bestSellers.groups[m] +"<\/h5>")

        }
        $("#best_seller_space").fadeIn("slow");

        }

    });
    }