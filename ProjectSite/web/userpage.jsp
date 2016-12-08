    <%-- 
    Document   : grouppage
    Created on : Nov 30, 2016, 4:14:54 PM
    Author     : Karl
--%>

<%@page import="general.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
    <head>
        <title>Group Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/gen-styles/group_postcomment_style.css">
        <link rel="stylesheet" href="styles/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles/gen-styles/loginformgen.css">
        <link rel="stylesheet" href="styles/gen-styles/grouppagestyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
</head>
<body>
    <div class = "" >
        <div class="container">
            <div class ="row row-no-padding">
                <div class = " col-md-12 user-login-head">  
                    <img src="images/temp_logo.png" alt ="Logo Here" height="50" width="50">
                    <br>
                    <span id = "team_name">
                        THE DIRTY BITS
                    </span>
                     <%

                            if (session.getAttribute("SIGNED_IN_USER") == null) {
                                String errorString = "You must log in first!";
                                request.setAttribute("LOGIN_ERROR", errorString);
                                RequestDispatcher d = this.getServletContext().getRequestDispatcher("/loginpage.jsp");
                                d.forward(request, response);
                            }
                            
//                            if(request.getAttribute("GROUP_ID") == null){
//                                String errorString = "Invalid group number!";
//                                request.setAttribute("LOGIN_ERROR", errorString);
//                                RequestDispatcher d = this.getServletContext().getRequestDispatcher("/loginpage.jsp");
//                                d.forward(request, response);
//                            }
//                            
//                            Integer group_number = Integer.parseInt((String)request.getAttribute("GROUP_ID"));
                            UserData user = (UserData)(session.getAttribute("SIGNED_IN_USER"));
                            //RequestDispatcher d = this.getServletContext().getRequestDispatcher("/ProcessPopulateGroup");
                            //d.forward(request, response);

                        %>
             
                    <span id ="guser_id" style ="display: none">
                        <%=user.getUserid()%>
                    </span>
                </div>
            </div>

            <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
            <div class="group-box">
                <aside class="sm-side">
                    <div class="user-head">
                        <a class="group-avatar" href="javascript:;">
                            <img  width="64" hieght="60" src="images/temp_logo_w.png">
                        </a>
                        <div class="user-name" >
                            <h5><span id="user_name_area" > </span></h5>
                            <span id = "user_email"><a href="#"></a></span>
                        </div>

                    </div>
                    <div class="options-body">
                        <br>
                        <center> <img src="images/profile_default.jpg" alt ="Logo Here" height="200" width="200">
                            <br><br><h3> <%=user.getFirstname()%> <%=user.getLastname()%></h3></center>
                        <br>
<!--                        <form action = "#"> 
                            <button type ="button" id ="btn_delete" data-toggle="modal" data-target="#delete_modal" class="btn btn-deletegroup">
                                Delete Group
                            </button>
                        </form>-->
                        
                         <form action ="#">
                             <br>
                            <button  class="btn btn-othergroup" type="button"  id = "btn_messages">
                                Send/Read Messages
                            </button>
                        </form>

                        <form action ="#">
                            <br>
                            <a  class="btn btn-othergroup"   id="btn_CRL_Panel">
                                Customer-Representative Panel
                            </a>
                        </form>
                         <form action ="#">
                            <br>
                            <a  class="btn btn-othergroup"  data-toggle="modal" data-target="#leave_modal" id = "btn_leave">
                                Log Out
                            </a>
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
                                                    <button class="btn btn-send" id="rename_submit" type="submit">Submit Request</button>
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
                                        <form role="form" class="form-horizontal" style= "text-align: center;">
                                            <div class="form-group">
                                                <label class="col-lg-7 col-lg-offset-2 control-label">Are you sure you want to delete this group?</label>

                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-offset-2 col-lg-8">
                                                    <button class="btn btn-send" type="submit">  Yes  </button>
                                                    <button class="btn btn-send" type="button">  No  </button>
                                                </div>
                                            </div>
                                        </form>
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
                        <span id = "group_name"><h3></h3></span>
                        <span name = "add_notification" class ="" style="padding-left:2%; "></span>
<!--                        <form action="#" class="pull-right position">
                            <div class="input-append">
                                <button class="btn sr-btn" type="button">Add</button>
                                <a href="loginpage.jsp"></a>
                                <input type="text" class="sr-input" placeholder="Add a member">

                            </div>



                        </form>-->
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
                                        <h3> Posts</h3>
                                        <div class="feed_form">
                                            <form action="" id="frmpost" method="post">      
                                                <div class="row_ele">
                                                    <div class="form-group">
                                                    <textarea name="post_feed" id="post_feed" class="form-control" style="resize: none;" rows="3" placeholder="What's on your mind?"></textarea>
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

<script src = "scripts/jquery-3.1.1.min.js"></script>
<script src = "scripts/user_page_transactions.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
</body>


</html>
