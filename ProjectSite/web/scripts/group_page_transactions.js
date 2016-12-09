$(document).ready(function () {

    //Template for the post/comment section on the group page section using Mustache.js. MODIFY AT YOUR OWN RISK.
    var template = "" +
            "{{#Posts}}<div class='feed_box' id='{{postID}}'>" +
            "<div class='feed_left'>" +
            "<p><img class='userimg' src='images/user_icon.gif'></p>" +
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
            "Edit&nbsp;|&nbsp;</a>" +
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
            "Edit&nbsp;|&nbsp;</a>" +
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

    var group_members_template = "" +
            "{{#GroupMembers}}" +
            "<tr>" +
            "<td>{{firstName}}</td>" +
            "<td>{{lastName}}</td>" +
            "<td>{{userEmail}}</td>" +
            "</tr>{{/GroupMembers}}";

    /*Group page load functions defined here*/
    //Populate the group page with data from the database
    
    
    var group_id = $('#group_id').text().trim();
    if(group_id === "-1"){
        $('#join_button_area').hide();
        $('#unjoin_button_area').hide();
        $('[access="admin"]').hide();
        $('.load_area').show();
        $('#creategroup_modal').modal({ backdrop: 'static', keyboard: true });
        $('#creategroup_modal').modal('show');   
        $('#btn_creategroup_close').hide();
    }
    else{
        load_grouppage();
    }


    /*Disable form submission on this page. All transactions are handled via ajax calls.*/
    //$('form').on('submit', function (e) {
       // e.preventDefault();
    //});


    /*Transaction functions are defined here*/
    

    //Transaction for a user to join a group
    $('#btn_creategroup').on('click', function () {
        var group_name = $('#input_group_name').val().trim();
        var group_type = $('#input_group_type').val().trim();
        var user_id = $('#guser_id').text().trim();
        var err_label = $('#err_create_group');
        err_label.hide();
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=CREATE_GROUP&userID=" + user_id + "&groupName=" + group_name 
                +"&groupType=" + group_type;
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (create_status) {
                console.log("CREATE GROUP Success: " + create_status);
                //$('#rename_modal').modal('hide');
                var number_test = (/^\d+$/.test(create_status.trim()));
                if ((/^\d+$/.test(create_status.trim()))) {
                    console.log("Create Group - Is a number: " + create_status);
                    
                    $('#group_id').text(create_status.trim());
                    $('#creategroup_modal').modal('hide');
                    $('#btn_creategroup_close').show();
                    load_grouppage();
                    
                    
                } else {
                    err_label.hide();
                    err_label.text("One or more fields are empty. Both fields are required to create a group");
                    err_label.fadeIn(1000);
                    console.log("CREATE GROUP:ERROR");
                }

            },
            error: function () {
                console.log("CREATE GROUP Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });
    
    
    $('#btn_delete_confirm').on('click', function (e){
        var group_id = $('#group_id').text().trim();
        var user_id = $('#guser_id').text().trim();
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_GROUP&groupID=" + group_id + "&userID=" + user_id;
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (remove_status) {
                console.log("Delete Success: " + remove_status);          
                if (remove_status.trim() === "DELETE_GROUP:SUCCESS") {
                    console.log("DELETION SUCCESSFUL");
                    $('#delete_modal').modal('hide');
                    $('#form_delete').submit();
                } else {
                    console.log("DELETE_GROUP:An Error occured");
                }
            },
            error: function () {
                console.log("Add Member Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });
    
    $('#btn_joingroup').on('click', function () {
        var email_address = $('#guser_email').text().trim();
        var group_id = $('#group_id').text().trim();
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_MEMBER&groupID=" + group_id + "&userID=ADMIN_JOIN" 
                +"&emailAddress=" + email_address;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (remove_status) {
                console.log("Join Success: " + remove_status);
                //$('#rename_modal').modal('hide');
                if (remove_status.trim() === "ADD_MEMBER:SUCCESS") {
                    console.log("User succesfully added to this group.");
                    refresh_grouppage();
                    $('#joingroup_modal').modal('hide');
                } else {
                    console.log("This user is already in the group or the email address you entered is invalid.");

                }

            },
            error: function () {
                console.log("Add Member Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });
    
     $('#btn_unjoingroup').on('click', function () {
        var email_address = $('#guser_email').text().trim();
        var group_id = $('#group_id').text().trim();
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=REMOVE_MEMBER&groupID=" + group_id + "&userID=ADMIN_JOIN" 
                +"&emailAddress=" + email_address;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (remove_status) {
                console.log("Unjoin Success: " + remove_status);
                if (remove_status.trim() === "REMOVE_MEMBER:SUCCESS") {
                    console.log("User succesfully removed to this group.");
                    refresh_grouppage();
                    $('#unjoingroup_modal').modal('hide');
                } else {
                    console.log("This user is not in this group. You should have never reached here.");

                }

            },
            error: function () {
                console.log("REMOVE_MEMBER Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });





    //Transaction to make a post in the current group.
    $('#btnpost').on('click', function () {
        var post_content = $('#post_feed').val().trim();
        var err_label = $('#err_post');
        var post_div = '#post_area_template';
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
                    refresh_grouppage(post_div);
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
        var control = '#post_area_template';

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
                    refresh_grouppage(control);
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
        var post_div = '#post_area_template';
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_POST&postID=" + post_id + "&userID=" + user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Post:Delete-Success");
                console.log("Post:Delete-Response: " + comment_status);
                refresh_grouppage(post_div);
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
        var control = '#post_area_template';
        var url = "/ProjectSite/ProcessGroupTransaction?transaction=DELETE_COMMENT&commentID=" + comment_id + "&userID=" + user_id;

        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (comment_status) {
                console.log("Comment:Delete-Success");
                console.log("Comment:Delete-Response: " + comment_status);
                refresh_grouppage(control);
            },
            error: function () {
                console.log("Comment:Delete: Aw, It didn't connect to the servlet :(");
            }

        });
    });



    //Transaction to edit a comment on the group page.
    $('body').on('click', 'a.link_btn.edit_comment_btn', function () {
        $('#edit_comment_modal').modal('show');
        $('#edit_comment_modal').attr("comment_userid", $(this).attr("comment_userid"));
        $('#edit_comment_modal').attr("commentid", $(this).attr("commentid"));
    });


    //Ajax query to edit a comment on the group page
    $('#btn_edit_comment').on('click', function () {
        var edit_content = $('#edit_feed').val().trim();
        var comment_userid = $('#edit_comment_modal').attr('comment_userid').trim();
        var comment_id = $('#edit_comment_modal').attr('commentid').trim();
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


    //Transaction to edit a post on the group page.
    $('body').on('click', 'a.link_btn.edit_post_btn', function () {
        $('#edit_post_modal').modal('show');
        $('#edit_post_modal').attr("post_userid", $(this).attr("post_userid"));
        $('#edit_post_modal').attr("postid", $(this).attr("postid"));
    });


    //Ajax query to edit a post on the group page
    $('#btn_edit_post').on('click', function () {
        var edit_content = $('#edit_post_feed').val().trim();
        var post_userid = $('#edit_post_modal').attr('post_userid').trim();
        var post_id = $('#edit_post_modal').attr('postid').trim();
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


    $('#rename_submit').on('click', function () {
        var new_group_name = $('#input_rename').val().trim();
        var err_label = $('#err_rename');
        var group_name_div = '.group_name_area';


        err_label.hide();
        if (new_group_name.length === 0) {
            err_label.text("The new name of your group cannot be empty.");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=RENAME&groupID=" + group_id + "&userID=" + user_id
                    + "&newGroupName=" + new_group_name;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (post_status) {
                    console.log("Post-Success");
                    console.log("Post-Response: " + post_status);
                    $('#rename_modal').modal('hide');
                    refresh_grouppage(group_name_div);

                },
                error: function () {
                    console.log("Post-Error: Aw, It didn't connect to the servlet :(");
                }

            });

        }
    });

    //Loads the group members for the specified group
    $('#btn_viewmembers').on('click', function () {
        load_groupmembers();
    });

    $('#btn_remove_member').on('click', function () {
        var email_address = $('#input_remove_member').val().trim();
        var err_label = $('#err_remove_member');

        err_label.hide();
        if (email_address.length === 0) {
            err_label.text("The email address of the person you want to remove from this group cannot be empty.");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=REMOVE_MEMBER&groupID=" + group_id + "&userID=" + user_id
                    + "&emailAddress=" + email_address;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (remove_status) {
                    console.log("Remove member Sucess: " + remove_status);
                    //$('#rename_modal').modal('hide');
                    if (remove_status.trim() === "REMOVE_MEMBER:SUCCESS") {
                        load_groupmembers();
                    } else {
                        err_label.text("The email address of the person you want to remove from this group is invalid.");
                        err_label.fadeIn(600);
                    }

                },
                error: function () {
                    console.log("Remove Member Failure: Aw, It didn't connect to the servlet :(");
                }

            });

        }
    });



    $('#btn_search').on('click', function () {
        search_for_users();
    });

    $('#btn_add_member').on('click', function () {
        var email_address = $('#input_add_member').val().trim();
        var err_label = $('#err_add_member');

        err_label.hide();
        if (email_address.length === 0) {
            err_label.text("The email address of the person you want to add to this group cannot be empty.");
            err_label.fadeIn(600);
        } else {
            err_label.hide();
            var user_id = $('#guser_id').text().trim();
            var group_id = $('#group_id').text().trim();
            var url = "/ProjectSite/ProcessGroupTransaction?transaction=ADD_MEMBER&groupID=" + group_id + "&userID=" + user_id
                    + "&emailAddress=" + email_address;

            $.ajax({
                method: 'get',
                url: url,
                dataType: 'text',
                success: function (remove_status) {
                    console.log("Add member Sucess: " + remove_status);
                    //$('#rename_modal').modal('hide');
                    if (remove_status.trim() === "ADD_MEMBER:SUCCESS") {
                        search_for_users();
                        err_label.text("User succesfully added to this group.");
                        err_label.fadeIn(600);
                    } else {
                        err_label.text("This user is already in the group or the email address you entered is invalid.");
                        err_label.fadeIn(600);
                    }

                },
                error: function () {
                    console.log("Add Member Failure: Aw, It didn't connect to the servlet :(");
                }

            });

        }
    });

    function search_for_users() {
        var group_id = $('#group_id').text().trim();
        var users_area = $('.users_area');
        var email = $('#input_search_user').val().trim();
        var err_label = $('#err_add_member');
        err_label.hide();


        var $url = "/ProjectSite/ProcessGroupTransaction?transaction=SEARCH_MEMBER&groupID=" + group_id + "&emailAddress=" + email;
        $.ajax({
            method: 'post',
            url: $url,
            dataType: 'json',
            success: function (users_list) {
                console.log("Search Users:Success");
                var JSON_user_list = users_list;

                var user_name_output = "First member " + JSON_user_list.GroupMembers.firstName + " " + JSON_user_list.GroupMembers.lastName;
                console.log("User name: " + user_name_output);

                //var test1 = JSON_post_info.ownerFirstName;
                var users_data = Mustache.render(group_members_template, JSON_user_list);
                users_area.hide();
                users_area.html(users_data);
                users_area.fadeIn(2000);
                console.log(users_data);
            },
            error: function () {
                users_area.hide();
                users_area.html("");
                users_area.fadeIn(2000);
                //alert("Loading users failed");
            }

        });
    }


    function load_groupmembers() {
        var group_id = $('#group_id').text().trim();
        var groupmembers_area = $('.groupmembers_area');
        var $url = "/ProjectSite/ProcessGroupTransaction?transaction=GET_MEMBERS&groupID=" + group_id;

        $.ajax({
            method: 'post',
            url: $url,
            dataType: 'json',
            success: function (group_members) {
                console.log("Get Group Members:Success");
                var JSON_group_members = group_members;

                var user_name_output = "First member " + JSON_group_members.GroupMembers.firstName + " " + JSON_group_members.GroupMembers.lastName;
                console.log("User name: " + user_name_output);

                //var test1 = JSON_post_info.ownerFirstName;
                var member_data = Mustache.render(group_members_template, JSON_group_members);
                groupmembers_area.hide();
                groupmembers_area.html(member_data);
                groupmembers_area.fadeIn(2000);
                console.log(member_data);

            },
            error: function () {
                groupmembers_area.hide();
                groupmembers_area.html("");
                groupmembers_area.fadeIn(2000);
                //alert("Loading group members failed");
            }

        });
    }



    function checkMemberStatus() {
        var group_id = $('#group_id').text().trim();
        var guser_id = $('#guser_id').text().trim();

        var temp = $('a.link_btn.edit_post_btn[post_userid=' + guser_id + "]");
        var $url = "/ProjectSite/ProcessGroupTransaction?transaction=CHECK_JOINSTATUS&groupID=" + group_id + "&userID=" + guser_id;

        $.ajax({
            method: 'post',
            url: $url,
            dataType: 'text',
            success: function (message) {
                if (message.trim() === "CHECK_JOINSTATUS:OWNER") {
                    $('#unjoin_button_area').hide();
                    $('#join_button_area').hide();
                    $('[access="admin"]').show()
                } else if (message.trim() === "CHECK_JOINSTATUS:JOINED" || message.trim() === "CHECK_JOINSTATUS:UNJOINED") {
                    $('#join_button_area').hide();
                    $('#unjoin_button_area').hide();

                    $('[access="admin"]').hide();

                    $('a.link_btn.edit_comment_btn').hide();
                    $('a.link_btn.delete_comment_btn').hide();

                    $('a.link_btn.edit_comment_btn[comment_userid=' + guser_id + ']').show();
                    $('a.link_btn.delete_comment_btn[comment_userid=' + guser_id + ']').show();

                    $('a.link_btn.delete_post_btn').hide();
                    $('a.link_btn.edit_post_btn').hide();

                    $('a.link_btn.delete_post_btn[post_userid=' + guser_id + "]").show();
                    $('a.link_btn.edit_post_btn[post_userid=' + guser_id + "]").show();

                    if (message.trim() === "CHECK_JOINSTATUS:JOINED") {
                        $('#unjoin_button_area').show();
                    } else {
                        $('#join_button_area').show();
                    }
                }

            },
            error: function () {
                alert("Loading group members failed");
            }

        });
    }

    function load_grouppage() {
        var user_name = $('#user_name_area');
        var email_area = $('#user_email a');
        var group_name = $('#group_name h3');
        var group_type_area = $('#group_type');


        var group_id = $('#group_id').text().trim();
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

                var group_type = JSON_group_info.groupType;
                if (group_type.trim().length === 0) {
                    group_type_area.text("THE DIRTY BITS");
                } else {
                    group_type_area.text(group_type.toUpperCase());
                }

                //var test1 = JSON_post_info.ownerFirstName;
                var post_data = Mustache.render(template, JSON_group_info);
                $('.load_area').hide();
                post_comment_area.html(post_data);
                checkMemberStatus();
                $('.load_area').fadeIn(2000);

                //console.log(post_data);

            },
            error: function () {
                alert("Loading the group page failed");
            }

        });
    }



    function refresh_grouppage(control) {
        var user_name = $('#user_name_area');
        var email_area = $('#user_email a');
        var group_name = $('#group_name h3');


        var group_id = $('#group_id').text().trim();
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

                post_comment_area.css('display', 'none');
                post_comment_area.html(post_data);
                checkMemberStatus();
                post_comment_area.fadeIn(1500);




                //console.log(post_data);

            },
            error: function () {
                alert("Refresh group page failed.");
            }

        });
    }
});


