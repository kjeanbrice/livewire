$(document).ready(function () {

    //Template for the post/comment section on the group page section using Mustache.js. MODIFY AT YOUR OWN RISK.
    var template = "" +
            "{{#Posts}}<div class='feed_box' id='{{postID}}'>" +
            "<div class='feed_left'>" +
            "<p><img class='userimg' src='images/temp_logo.png'></p>" +
            "<p>{{ownerFirstName}}&nbsp;{{ownerLastName}}</p>" +
            "</div>" +
            "<div class='feed_right'>" +
            "<span class ='post_refresh'><p>{{postContent}}</p></span>" +
            "<p class='likebox'>" +
            "Total Like : {{pLikes}} &nbsp;|&nbsp;" +
            "<a class='link_btn dislike_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#!'>" +
            "Dislike</a>&nbsp;|&nbsp;" +
            "<a class='link_btn like_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#!'>" +
            "Like</a>&nbsp;|&nbsp;" +
            "<a class='link_btn edit_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#!'>" +
            "Edit</a>&nbsp;|&nbsp;" +
            "<a class='link_btn delete_post_btn' post_userid = '{{pUserID}}' postid='{{postID}}' href='#!'>" +
            "Delete</a>" +
            "</p>" +
            "<div class='clear'></div>" +
            "<div class='comment_div'>" +
            "{{#Comments}}" +
            "<div class='clear'></div>" +
            "<div class='comment_ele'>" +
            "<p><a class='link_btn' href='#'>{{cFirstName}}&nbsp;{{cLastName}}</a></p>" +
            "<span class= 'comment_refresh'><p>{{commentContent}}</p></span>" +
            "<p class='likebox'>" +
            "Total Like : {{cLikes}} &nbsp;|&nbsp;" +
            "<a class='link_btn dislike_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#!'>" +
            "Dislike</a>&nbsp;|&nbsp;" +
            "<a class='link_btn like_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#!'>" +
            "Like</a>&nbsp;|&nbsp;" +
            "<a class='link_btn edit_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#!'>" +
            "Edit</a>&nbsp;|&nbsp;" +
            "<a class='link_btn delete_comment_btn' comment_userid = '{{cUserID}}' commentid='{{commentID}}' href='#!'>" +
            "Delete</a>" +
            "</p>" +
            "</div>{{/Comments}}" +
            "</div>" +
            "<div class='clear'></div>" +
            "<p>" +
            "<form id='commentform_{{postID}}' class = 'comment_submit' action='javascript:void(0);' method='get'>" +
            "<input type='hidden' name='action' value='comment'/>" +
            "<input type='hidden' name='post_id' value='{{postID}}'/>" +
            "<input class='input comment_input form-control' style = 'width:75%' type='text' name='comment' id='comment_{{postID}}' placeholder='Write your comment here'/>" +
            "<input class='btn btn-primary' postid='{{postID}}' type='submit' name='sendbtn' value='>'/>" +
            "<br><br><span class='lbl-error' name = 'err_comment'></span>" +
            "</form></p></div>" +
            "<div class='clear'></div>" +
            "</div>{{/Posts}}";


    //Load the group page for the requested group id.
    load_grouppage();

    //Transaction to make a post in the current group.
    $('#btnpost').on('click', function () {
        var post_content = $('#post_feed').val().trim();
        var err_label = $('#err_post');
        err_label.hide();
        if (post_content.length === 0) {
            err_label.text("You must add text to your post!");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_POST&groupID=" + group_id + "&userID=" + user_id + "&content=" + post_content;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (post_status) {
                    console.log("Post-Success");
                    console.log("Post-Response: " + post_status);
                    refresh_grouppage();
                },
                error: function () {
                    console.log("Post-Error: Aw, It didn't connect to the servlet :(");
                }

            });

        }

    });

    //Transaction to make a comment on a specific post. 
    $('body').on('submit', 'form.comment_submit', function (e) {
        e.preventDefault();
        var comment_form = $(this);
        var post_id = (comment_form.find('input[name=post_id]')).val();
        var comment_content = (comment_form.find('input[name=comment]')).val().trim();
        var err_label = comment_form.find('span[name=err_comment]');
        var user_id = $('#guser_id').text().trim();

        err_label.text("");
        if (comment_content.length === 0) {
            err_label.text("You must add text to your comment!");
            err_label.hide();
            err_label.fadeIn(600);
        } else {
            err_label.text("");
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_COMMENT&postID=" + post_id + "&userID=" + user_id + "&content=" + comment_content;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (post_status) {
                    console.log("Post-Success");
                    console.log("Post-Response: " + post_status);
                    refresh_grouppage();
                },
                error: function () {
                    console.log("Post-Error: Aw, It didn't connect to the servlet :(");
                }

            });
        }


    });

    //Transaction to like a post on the group page.
    $('body').on('click', 'a.link_btn.like_post_btn', function () {

        var user_id = $('#guser_id').text().trim();
        var post_user_id = $(this).attr("post_userid");
        var post_id = $(this).attr("postid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=LIKE_POST&postID=" + post_id + "&userID=" + user_id + "&postUserID=" + post_user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (post_status) {
                console.log("Post:Like-Success");
                console.log("Post:Like-Response: " + post_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Post:Like-Error: Aw, It didn't connect to the servlet :(");
            }

        });



    });


    //Transaction to dislike a post on the group page.
    $('body').on('click', 'a.link_btn.dislike_post_btn', function () {
        var user_id = $('#guser_id').text().trim();
        var post_user_id = $(this).attr("post_userid");
        var post_id = $(this).attr("postid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DISLIKE_POST&postID=" + post_id + "&userID=" + user_id + "&postUserID=" + post_user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (post_status) {
                console.log("Post:Dislike-Success");
                console.log("Post:Dislike-Response: " + post_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Post:Dislike-Error: Aw, It didn't connect to the servlet :(");
            }

        });
    });


    //Transaction to like a comment on the group page.
    $('body').on('click', 'a.link_btn.like_comment_btn', function () {
        
        var user_id = $('#guser_id').text().trim();
        var comment_user_id = $(this).attr("comment_userid");
        var comment_id = $(this).attr("commentid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=LIKE_COMMENT&commentID=" + comment_id + "&userID=" + user_id + "&commentUserID=" + comment_user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Comment:Like-Success");
                console.log("Comment:Like-Response: " + comment_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Comment:Like: Aw, It didn't connect to the servlet :(");
            }

        });
    });


    //Transaction to dislike a comment on the group page.
    $('body').on('click', 'a.link_btn.dislike_comment_btn', function () {

        var user_id = $('#guser_id').text().trim();
        var comment_user_id = $(this).attr("comment_userid");
        var comment_id = $(this).attr("commentid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DISLIKE_COMMENT&commentID=" + comment_id + "&userID=" + user_id + "&commentUserID=" + comment_user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Comment:Dislike-Success");
                console.log("Comment:Dislike-Response: " + comment_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Comment:Dislike: Aw, It didn't connect to the servlet :(");
            }

        });
    });


    
    //Transaction to delete a post on the group page.
    $('body').on('click', 'a.link_btn.delete_post_btn', function () {

        var user_id = $(this).attr("post_userid");
        var post_id = $(this).attr("postid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_POST&postID=" + post_id + "&userID=" + user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Post:Delete-Success");
                console.log("Post:Delete-Response: " + comment_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Post:Delete: Aw, It didn't connect to the servlet :(");
            }

        });
    });
    
    
    
    //Transaction to delete a comment on the group page.
    $('body').on('click', 'a.link_btn.delete_comment_btn', function () {

        var user_id = $(this).attr("comment_userid");
        var comment_id = $(this).attr("commentid");
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_COMMENT&commentID=" + comment_id + "&userID=" + user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Comment:Delete-Success");
                console.log("Comment:Delete-Response: " + comment_status);
                refresh_grouppage();
            },
            error: function () {
                console.log("Comment:Delete: Aw, It didn't connect to the servlet :(");
            }

        });
    });
    
    

     //Transaction to edit a comment on the group page.
    $('body').on('click', 'a.link_btn.edit_comment_btn', function () {
        $('#edit_comment_modal').modal('show');
        $('#edit_comment_modal').attr("comment_userid",$(this).attr("comment_userid"));
        $('#edit_comment_modal').attr("commentid",$(this).attr("commentid"));   
    });
    
    
     
     $('#btn_edit_comment').on('click', function () {
        var edit_content = $('#edit_feed').val().trim();
        var comment_userid = $('#edit_comment_modal').attr('comment_userid').trim();
        var comment_id =  $('#edit_comment_modal').attr('commentid').trim();
        var err_label = $('#err_edit_comment');
        var control = '.comment_refresh';
        
        err_label.hide();
        if (edit_content.length === 0) {
            err_label.text("Your new comment cannot be empty.");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=EDIT_COMMENT&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                    + "&commentOwner=" + comment_userid + "&commentID=" + comment_id;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (post_status) {
                    console.log("Post-Success");
                    console.log("Post-Response: " + post_status);
                    $('#edit_comment_modal').modal('hide');
                    refresh_grouppage(control);
                },
                error: function () {
                    console.log("Post-Error: Aw, It didn't connect to the servlet :(");
                }

            });

        }
    });
    
    
     //Transaction to edit a comment on the group page.
    $('body').on('click', 'a.link_btn.edit_post_btn', function () {
        $('#edit_post_modal').modal('show');
        $('#edit_post_modal').attr("post_userid",$(this).attr("post_userid"));
        $('#edit_post_modal').attr("postid",$(this).attr("postid"));   
    });
    
    
     
     $('#btn_edit_post').on('click', function () {
        var edit_content = $('#edit_post_feed').val().trim();
        var post_userid = $('#edit_post_modal').attr('post_userid').trim();
        var post_id =  $('#edit_post_modal').attr('postid').trim();
        var err_label = $('#err_edit_post');
        var post_div = '.post_refresh';
        
        
        err_label.hide();
        if (edit_content.length === 0) {
            err_label.text("Your new post cannot be empty.");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=EDIT_POST&groupID=" + group_id + "&userID=" + user_id + "&content=" + edit_content 
                    + "&postOwner=" + post_userid + "&postID=" + post_id;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (post_status) {
                    console.log("Post-Success");
                    console.log("Post-Response: " + post_status);
                    $('#edit_post_modal').modal('hide');
                    refresh_grouppage(post_div);
                   
                },
                error: function () {
                    console.log("Post-Error: Aw, It didn't connect to the servlet :(");
                }

            });

        }
    });
    



function load_grouppage() {
        var user_name = $('#user_name_area');
        var email_area = $('#user_email a');
        var group_name = $('#group_name h3');


        var group_id = $('#group_id').text();
        var post_comment_area = $('#post_area_template');
        var $url = "/ProjectSite/ProcessPopulateGroup?groupId=" + group_id;

        $.ajax({
            method: 'post',
            url: $url,
            dataType: 'json',
            success: function (group_info) {
                console.log("Refresh Group page:Success");
                var JSON_group_info = group_info;

                var user_name_output = "Created by " + JSON_group_info.firstName + " " + JSON_group_info.lastName;
                console.log("User name: " + user_name_output);
                user_name.text(user_name_output);

                var group_output = JSON_group_info.groupName;
                group_name.text(group_output);

                var email_output = JSON_group_info.email;
                email_area.text(email_output);

                //var test1 = JSON_post_info.ownerFirstName;
                var post_data = Mustache.render(template, JSON_group_info);
                post_comment_area.hide();
                post_comment_area.html(post_data);
                post_comment_area.fadeIn(2000);
                //console.log(post_data);

            },
            error: function () {
                alert("Aw, It didn't work");
            }

        });
    }






    function refresh_grouppage(control) {
        var user_name = $('#user_name_area');
        var email_area = $('#user_email a');
        var group_name = $('#group_name h3');


        var group_id = $('#group_id').text();
        var post_comment_area = $('#post_area_template');
        var $url = "/ProjectSite/ProcessPopulateGroup?groupId=" + group_id;

        $.ajax({
            method: 'post',
            url: $url,
            dataType: 'json',
            success: function (group_info) {
                console.log("Refresh Group page:Success");
                var JSON_group_info = group_info;

                var user_name_output = "Created by " + JSON_group_info.firstName + " " + JSON_group_info.lastName;
                console.log("User name: " + user_name_output);
                user_name.text(user_name_output);

                var group_output = JSON_group_info.groupName;
                group_name.text(group_output);

                var email_output = JSON_group_info.email;
                email_area.text(email_output);

                //var test1 = JSON_post_info.ownerFirstName;
                var post_data = Mustache.render(template, JSON_group_info);
                post_comment_area.html(post_data);  
                if(control !== undefined){
                    $(''+control).hide();
                    $(''+control).fadeIn(2000);
                }
                //console.log(post_data);

            },
            error: function () {
                alert("Aw, It didn't work");
            }

        });
    }
});


