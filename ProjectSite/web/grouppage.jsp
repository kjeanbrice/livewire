<%-- 
    Document   : grouppage
    Created on : Nov 30, 2016, 4:14:54 PM
    Author     : Karl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <title>Group Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                </div>
            </div>

            <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
            <div class="group-box">
                <aside class="sm-side">
                    <div class="user-head">
                        <a class="group-avatar" href="javascript:;">
                            <img  width="64" hieght="60" src="images/temp_logo_w.png">
                        </a>
                        <div class="user-name">
                            <h5><a href="#">Group made by: </a></h5>
                            <span><a href="#">email@server.com</a></span>
                        </div>

                    </div>
                    <div class="options-body">
                        <form action = "#">
                            <button type ="button" id ="btn_delete" data-toggle="modal" data-target="#delete_modal" class="btn btn-deletegroup">
                                Delete Group
                            </button>
                        </form>

                        <div>
                            <br>
                            <form action ="#">
                                <button  class="btn btn-deletegroup" type="button" data-toggle="modal" data-target="#rename_modal" id = "btn_rename">
                                    Rename Group
                                </button>
                            </form>
                            <!-- Add model here --> 

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
                                                      <button class="btn btn-send" type="submit">Submit Request</button>
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
                        <h3>Group Name</h3>
                         <span name = "add_notification" class ="" style="padding-left:2%; ">Notifications go here.</span>
                        <form action="#" class="pull-right position">
                            <div class="input-append">
                                <button class="btn sr-btn" type="button">Add</button>
                                <a href="loginpage.jsp"></a>
                                <input type="text" class="sr-input" placeholder="Add a member">
                                
                            </div>
                            
                            
                            
                        </form>
                    </div>
                    <div class="options-body body-background">
                        <div class="group-option">


                            <div class="btn-group">
                                <a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips">
                                    <i class=" fa fa-refresh"></i>
                                </a>
                            </div>

                        </div>
                        <table class="table table-options table-hover">
                            <tbody>
                            <div class="post-area">

                            </div>

                            </tbody>
                        </table>
                    </div>
                </aside>
            </div>
        </div>
    </div>
</body>
</html>
