    <%-- 
    Document   : grouppage
    Created on : Nov 30, 2016, 4:14:54 PM
    Author     : Karl
--%>

<%@page import="general.UserData"%>
<%@page import="utility.DatabaseUtils"%>
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
                    <span id ="group_id" style ="display: none">
                        <%--<%=group_number%>--%>
                    </span>
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
                            <button  class="btn btn-othergroup" type="button" data-toggle="modal" data-target="#groupmembers_modal" id="btn_getemailList">
                                Get Email List
                            </button>
                        </form>

<!--                        <form action ="#">
                            <br>
                            <a  class="btn btn-othergroup"  data-toggle="modal" data-target="#rename_modal" id = "btn_rename">
                                Rename Group
                            </a>
                        </form>-->
                         <form action ="#">
                            <br>
                            <a  class="btn btn-othergroup"  data-toggle="modal" data-target="#leave_modal" id = "btn_leave">
                                Log Out
                            </a>
                        </form>

  <form action ="#">
                            <br>
                            <a  class="btn btn-othergroup"  data-toggle="modal" data-target="#leave_modal" id = "btn_refresh">
                                Refresh
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
                                                                                   
                                            <h3 id="list_ads" style="cursor:pointer">List Current Advertisements</h3>
                                        <div class="feed_form" id="list_ads_space" style="display:none">
                              
                                        </div>
                                            
                                            
                                            <h3 id="create_transaction" style="cursor:pointer">Create a Transaction</h3>
                                        <div class="feed_form" id="create_transaction_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="ad_id" tabindex="1" class="form-control" placeholder="AD ID" >
                                            </div>
                                            <div class="form-group">
                                                <input type="text" name="company" id="seller_id" tabindex="2" class="form-control" placeholder="Seller Email">
                                            </div>
                                            <div class="form-group">
                                                <input type="text" name="item" id="consumer_id" tabindex="2" class="form-control" placeholder="Consumer Email">
                                            </div>
                                              <div class="form-group">
                                                <input type="text" name="price" id="number_of_units" tabindex="2" class="form-control" placeholder="Number">
                                            </div>
                                            
                                            
                                                    </div>  
                                       
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_make_transaction" type="button" name="create" value="Make Transaction"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                            </form>
                                        </div>
                                            
                                        <h3 id="company_list" style="cursor:pointer">List of Items Sold By Company</h3>
                                        <div class="feed_form" id="company_list_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="item_company_name" tabindex="1" class="form-control" placeholder="Company Name" >
                                                      </div>
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_get_items" type="button" name="get_items" value="Get Items"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                                        <div id="suggestions"></div>
                                            </form>
                                        </div
                                    </div>                                    </div>
                                            
                                            
                                     <h3 id="people_item_list" style="cursor:pointer">List of People Who Bought Item</h3>
                                        <div class="feed_form" id="people_item_list_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="item_name" tabindex="1" class="form-control" placeholder="Item Name" >
                                                      </div>
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_get_names" type="button" name="get_names" value="Get Names"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                                        <div id="suggestions"></div>
                                            </form>
                                        </div
                                    </div>                                    </div>
                                
                                
                                
                                <h3 id="transaction_user_list" style="cursor:pointer">List Transactions By User</h3>
                                        <div class="feed_form" id="transaction_user_list_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="user_name_for_transaction" tabindex="1" class="form-control" placeholder="User Email" >
                                                      </div>
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_get_transactions_by_user" type="button" name="get_transactions_by_user" value="Get Transactions"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                                        <div id="suggestions"></div>
                                            </form>
                                        </div
                                    </div>                                    </div>
                            
                            
                            
                                   <h3 id="transaction_item_list" style="cursor:pointer">List Transactions By Item Name</h3>
                                        <div class="feed_form" id="transaction_item_list_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="item_name_for_transaction" tabindex="1" class="form-control" placeholder="User Email" >
                                                      </div>
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_get_transactions_by_item" type="button" name="get_transactions_by_user" value="Get Transactions"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                                        <div id="suggestions"></div>
                                            </form>
                                        </div
                                    </div>                                    </div>
                                            
                                         <h3 id="account_alter" style="cursor:pointer">Add/Edit/Delete Account</h3>
                                         
                                         <form id="alter-form" action=" " method="post" role="form" style="display: none;">
                                             <h4>Click on a name to view and edit info about the account</h4>
                                            <div class="form-group">
                                                <input type="text" name="remail" id="remail" tabindex="1" class="form-control" placeholder="Email Address" value="">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="rpassword" id="rpassword" tabindex="2" class="form-control" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <input type="text" name="address" id="address" tabindex="1" class="form-control" placeholder="Address" value="">
                                            </div>
                                            <div class="form-group">
                                                <input type="text" name="first_name" id="first_name" tabindex="1" class="form-control" placeholder="First name" value="">
                                            </div>
                                            <div class="form-group">
                                                <input type="text" name="last_name" id="last_name" tabindex="1" class="form-control" placeholder="Last name" value="">
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="button" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Add/Update">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                         
                                         
                                        <div class="feed_form" id="account_alter_space" style="display:none">
                                            <form action="" id="frmpost" method="post">   
                                                    <div class="row_ele">
                                                      <div class="form-group">
                                                <input type="text" name="type" id="user_email" tabindex="1" class="form-control" placeholder="User Email" >
                                                      </div>
                                                <div class="row_ele">
                                                    <input class="btn btn-primary" id="btn_edit" type="button" name="get_suggestions" value="Get Suggestions"/>
                                                    <span  class = "lbl-error" id="err_post"></span>
                                                </div>
                                                        <div id="suggestions"></div>
                                            </form>
                                        </div
                                    </div>                                    </div>
                                            
                                            
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
<script src = "scripts/manager_transaction.js"></script>
<script src = "https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>
</body>


</html>

