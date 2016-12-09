<%-- 
    Document   : grouppage
    Created on : Nov 30, 2016, 4:14:54 PM
    Author     : Karl
--%>

<%@page import="utility.GenUtils"%>
<%@page import="general.UserData"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <title>Group</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/gen-styles/group_postcomment_style.css">
        <link rel="stylesheet" href="styles/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles/gen-styles/loginformgen.css">
        <link rel="stylesheet" href="styles/gen-styles/grouppagestyle.css">
        <script src = "scripts/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class = "load_area" style="display:none" >
            <div class="container">
                <div class ="row row-no-padding">
                    <div class = " col-md-12 user-login-head">
                        <%

                            if (session.getAttribute("SIGNED_IN_USER") == null) {
                                String errorString = "You must log in first!";
                                request.setAttribute("LOGIN_ERROR", errorString);
                                RequestDispatcher d = this.getServletContext().getRequestDispatcher("/loginpage.jsp");
                                d.forward(request, response);
                            }

                            Integer group_number;
                            if (request.getParameter("groupID") == null || ((String) request.getParameter("groupID")).length() == 0) {
                                group_number = -1;
                            } else {
                                group_number = Integer.parseInt((String) request.getParameter("groupID"));
                            }
                            UserData user = GenUtils.getUserData(request.getSession());

                            String user_name = user.getFirstname().toUpperCase() + " " + user.getLastname().toUpperCase();
                            System.out.println("User: " + user.getFirstname() + "UserID: " + user.getUserid());
                            //RequestDispatcher d = this.getServletContext().getRequestDispatcher("/ProcessPopulateGroup");
                            //d.forward(request, response);

                        %>
                        <img src="images/temp_logo.png" alt ="Logo Here" height="50" width="50">
                        <br>
                        <span id = "group_welcome">
                            HI <%=user_name%>!
                        </span>
                        <br>
                        <span id = "group_type">
                            GROUP TYPE
                        </span>

                        <span id ="group_id" style ="display: none">
                            <%=group_number%>
                        </span>
                        <span id ="guser_id" style ="display: none">
                            <%=user.getUserid()%>
                        </span>
                        <span id ="guser_email" style ="display: none">
                            <%=user.getEmail()%>
                        </span>
                    </div>
                </div>

                <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
                <div class="group-box" >
                    <aside class="sm-side">
                        <div class="user-head">
                            <a class="group-avatar" href="javascript:;">
                                <img class="userimg" src="images/user_icon.gif">
                            </a>
                            <div class="user-name" >
                                <h5><span id="user_name_area" > </span></h5>
                                <span id = "user_email"><a href="#"></a></span>
                            </div>

                        </div>
                        <div class="options-body">
                            <form action ="#">
                                <button class="btn btn-othergroup" type="button" data-toggle="modal" data-target="#creategroup_modal" id = "btn_creategroup">
                                    Create New Group
                                </button>
                            </form> 
                            <form action ="#">
                                <br>
                                <button class="btn btn-othergroup" type="button" data-toggle="modal" data-target="#groupmembers_modal" id = "btn_viewmembers">
                                    View Group Members
                                </button>
                            </form>
                            <form action ="#">
                                <span id="join_button_area">
                                    <br>
                                    <button  class="btn btn-othergroup" type="button" data-toggle="modal" data-target="#joingroup_modal" id = "btn_join_group" >
                                        Join Group
                                    </button>
                                </span>
                            </form>
                            <form action ="#">
                                <span id="unjoin_button_area">
                                    <br>
                                    <button  class="btn btn-othergroup" type="button" data-toggle="modal" data-target="#unjoingroup_modal" id = "btn_unjoin_group" >
                                        Unjoin Group
                                    </button>
                                </span>
                            </form>

                            <form action ="#">
                                <br>
                                <a  class="btn btn-othergroup" access="admin" data-toggle="modal" data-target="#rename_modal" id = "btn_rename">
                                    Rename Group
                                </a>
                            </form>
                            <form action = "#"> 
                                <br>
                                <button type ="button" id ="btn_delete" access="admin" data-toggle="modal" data-target="#delete_modal" class="btn btn-deletegroup">
                                    Delete Group
                                </button>
                            </form>                     
                        </div>




                        <!-- Add model here --> 

                        <div>
                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="rename_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Rename your group</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label">New name</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" placeholder="" id="input_rename" name = "input_rename" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <span access="admin" class = "lbl-error" id="err_rename"></span>
                                                        <br>
                                                        <button class="btn btn-primary" type="button" id="rename_submit">Submit Request</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="groupmembers_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Manage Group Members</h4>
                                        </div>
                                        <div class="modal-body" style="overflow:auto;" >
                                            <div class="form-group">
                                                <div style="max-height:400px;overflow:auto;">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>First Name</th>
                                                                <th>Last Name</th>
                                                                <th>Email</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody class = "groupmembers_area">

                                                        </tbody>
                                                    </table>   
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <input access="admin" type="text" placeholder="Remove user by email address" id="input_remove_member" name = "input_remove" class="form-control">
                                                    <span access="admin" class = "lbl-error" id="err_remove_member"></span>
                                                    <br>
                                                    <button access="admin" class="btn pull-right btn-primary" id="btn_remove_member" type="button">Remove</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="unjoingroup_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Leave Group</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" class="form-horizontal" style= "text-align: center;">
                                                <div class="form-group">
                                                    <label class="col-lg-7 col-lg-offset-2 control-label">Are you sure you want to leave this group?</label>

                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-8">
                                                        <button class="btn btn-primary" type="button" id="btn_unjoingroup">  Yes  </button>
                                                        <button class="btn btn-primary" aria-hidden="true" data-dismiss="modal" type="button">  No  </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="joingroup_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Join Group</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" class="form-horizontal" style= "text-align: center;">
                                                <div class="form-group">
                                                    <label class="col-lg-7 col-lg-offset-2 control-label">Are you sure you want to join this group?</label>

                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-8">
                                                        <button class="btn btn-primary" type="button" id="btn_joingroup">  Yes  </button>
                                                        <button class="btn btn-primary" aria-hidden="true" data-dismiss="modal" type="button">  No  </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="findusers_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Search Results</h4>
                                        </div>
                                        <div class="modal-body" style="overflow:auto;" >
                                            <div class="form-group">
                                                <div style="max-height:400px;overflow:auto;">
                                                    <table class="table table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>First Name</th>
                                                                <th>Last Name</th>
                                                                <th>Email</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody class = "users_area">

                                                        </tbody>
                                                    </table>   
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <input access="admin" type="text" placeholder="Add a user by their email address" id="input_add_member" name = "input_remove" class="form-control">
                                                    <span access="admin" class = "lbl-error" id="err_add_member"></span>
                                                    <br>
                                                    <button access="admin" class="btn pull-right btn-primary" id="btn_add_member" type="button">Add User</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->


                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="edit_comment_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Edit your comment</h4>
                                        </div>
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <textarea name="edit_comment_feed" id="edit_feed" class="form-control edit_text_area"  rows="3" placeholder="New comment"></textarea>        
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <button class="btn btn-primary" id="btn_edit_comment" type="button">Submit Request</button>
                                                    <span  class = "lbl-error" id="err_edit_comment"></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->


                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="edit_comment_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Edit your comment</h4>
                                        </div>
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <textarea name="edit_comment_feed" id="edit_feed" class="form-control edit_text_area"  rows="3" placeholder="New comment"></textarea>        
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <button class="btn btn-primary" id="btn_edit_comment" type="button">Submit Request</button>
                                                    <span  class = "lbl-error" id="err_edit_comment"></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="edit_post_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Edit your post</h4>
                                        </div>
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <textarea name="edit_post_feed" id="edit_post_feed" class="form-control edit_text_area"  rows="3" placeholder="New comment"></textarea>        
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <button class="btn btn-primary" id="btn_edit_post" type="button">Submit Request</button>
                                                    <span  class = "lbl-error" id="err_edit_post"></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->



                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="creategroup_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button" id = "btn_creategroup_close">×</button>
                                            <h4 class="modal-title">Create New Group</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-lg-3 control-label">Group Name:</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" placeholder="Name of group" id="input_group_name" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-3 control-label">Group Type:</label>
                                                    <div class="col-lg-9">
                                                        <input type="text" placeholder="Type of group" id="input_group_type" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <span  class = "col-lg-12 control-label lbl-error" id="err_create_group"></span>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <button class="btn btn-primary pull-right" type="button" id="btn_creategroup">Send Request</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="delete_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Delete your group</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form action = "userpage.jsp" id = "form_delete" role="form" class="form-horizontal" style= "text-align: center;">
                                                <div class="form-group">
                                                    <label class="col-lg-7 col-lg-offset-2 control-label">Are you sure you want to delete this group?</label>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-8">                           
                                                        <button class="btn btn-primary" type="button" id="btn_delete_confirm">  Yes  </button>
                                                        <button class="btn btn-primary" aria-hidden="true" data-dismiss="modal" type="button">  No  </button>                                               
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="creategroup_modal" class="modal fade" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                            <h4 class="modal-title">Edit your comment</h4>
                                        </div>
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <textarea name="edit_comment_feed" id="edit_feed" class="form-control edit_text_area"  rows="3" placeholder="New comment"></textarea>        
                                            </div>
                                            <div class="form-group ">
                                                <div>
                                                    <button class="btn btn-primary" id="btn_edit_comment" type="button">Submit Request</button>
                                                    <span  class = "lbl-error" id="err_edit_comment"></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                        <ul class="options-nav options-divider">

                        </ul>

                        <div class="options-body text-center">

                        </div>

                    </aside>
                    <aside class="lg-side">
                        <div class="group-head">
                            <span id = "group_name" class = "group_name_area"><h3></h3></span>
                            <span name = "add_notification" class ="" style="padding-left:2%; "></span>
                            <div class="pull-right position">
                                <div class="input-append">
                                    <button class="btn sr-btn" id="btn_search" data-toggle="modal" data-target="#findusers_modal" type="button">Search</button>
                                    <input type="text" class="sr-input" placeholder="Search user by email" id="input_search_user">
                                </div>



                            </div>
                        </div>
                        <div class="options-body body-background">
                            <div class="group-option">


                                <div class="btn-group">

                                </div>

                            </div>
                            <div class="table table-options table-hover">
                                <div class="post-area">
                                    <div class="wrapper">
                                        <div class="middle_box">
                                            <div class="feed_form">
                                                <form action="" id="frmpost" method="post">      
                                                    <div class="row_ele">
                                                        <div class="form-group">
                                                            <textarea name="post_feed" id="post_feed" class="form-control" style="resize: none;" rows="3" placeholder="What do you want to talk about?"></textarea>
                                                        </div>
                                                    </div>  
                                                    <div class="row_ele">
                                                        <input class="btn btn-primary" id="btnpost" type="button" name="submit" value="Post"/>
                                                        <span  class = "lbl-error" id="err_post"></span>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="clear"></div>
                                            <div class="feed_div" id="feed_div">
                                                <div id ="post_area_template">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
        </div>

        <script src = "scripts/group_page_transactions.js"></script>
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
    </body>


</html>
