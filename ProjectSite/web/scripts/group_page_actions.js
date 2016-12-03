$(document).ready(function () {
    var template = "" +
            "{{#Posts}}<div class='feed_box' id='{{postID}}'>" +
            "<div class='feed_left'>" +
            "<p><img class='userimg' src='images/temp_logo.png'></p>"+
            "<p>{{ownerFirstName}}&nbsp;{{ownerLastName}}</p>" +
            "</div>" +
            "<div class='feed_right'>" +
            "<p>{{postContent}}</p>" + 
            "<p class='likebox'>" +
            "Total Like : -1 &nbsp;|&nbsp;" +
            "<a class='link_btn dis_like_btn' postid='{{postID}}' href='#'>" +
            "Dislike</a>&nbsp;|&nbsp;" +
            "<a class='link_btn like_btn' postid='{{postID}}' href='#'>" +
            "Like</a>&nbsp;|&nbsp;" + 
            "<a class='link_btn' href='#'>"+
            "Comment</a>" + 
            "</p>" +
            "<div class='clear'></div>"+
            "<div class='comment_div'>" +
            "{{#Comments}}" + 
            "<div class='clear'></div>"+
            "<div class='comment_ele'>"+
            "<p><a class='link_btn' href='#'>{{firstName}}&nbsp;{{lastName}}</a></p>"+
            "<p>{{commentContent}}</p>"+
            "</div>{{/Comments}}"+
            "</div>" +
            "<div class='clear'></div>"+
            "<p>"+
            "<form id='commentform_{{postID}}' method='post'>"+
            "<input type='hidden' name='action' value='comment'/>"+
            "<input type='hidden' name='post_id' value='{{postID}}'/>" +
            "<input class='input comment_input form-control' style = 'width:75%' type='text' name='comment' id='comment_{{postID}}' placeholder='Write your comment here'/>"+
            "<input class='btn btn-primary' postid='{{postID}}' type='button' name='sendbtn' value='>'/>"+
            "</form></p></div>"+
            "<div class='clear'></div>"+
            "</div>{{/Posts}}";
            
            
    var template2 = "" +
            "{{#Posts}}<div class='feed_box' id='{{postID}}'>" +
            "<div class='feed_left'>" +
            "<p><img class='userimg' src='/usericon.gif'></p>"+
            "<p>{{ownerFirstName}}{{ownerLastName}}</p>" +
            "</div>{{/Posts}}"
    
    var template3 = "<body>{{ownerfirstName}}{{ownerlastName}}</body>";
    var test_obj = {ownerFirstName:"HI",ownerLastName:"There",guess:"bleh.."};

    refresh_grouppage();

















    function refresh_grouppage() {
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
                alert("Success");
                var JSON_group_info = group_info;
             
                var user_name_output = user_name.text() + JSON_group_info.firstName + " " + JSON_group_info.lastName;
                console.log("User name: " + user_name_output);
                user_name.text(user_name_output);

                var group_output = JSON_group_info.groupName;
                group_name.text(group_output);
                
                var email_output = JSON_group_info.email;
                email_area.text(email_output);
                
                //var test1 = JSON_post_info.ownerFirstName;
                var post_data = Mustache.render(template,JSON_group_info);
                post_comment_area.html(post_data);
                console.log(post_data);

            },
            error: function () {
                alert("Aw, It didn't work");
            }

        });
    }
});


