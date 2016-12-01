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
        <script src = "scripts/html-helper.js"></script>
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
                            <button type ="button" id ="btn_delete"  class="btn btn-deletegroup">
                                Delete Group
                            </button>
                        </form>

                        <div>
                            <br>
                            <form action ="#">
                                <button  class="btn btn-deletegroup" type="button" id = "btn_rename">
                                    Rename Group
                                </button>
                            </form>
                            <!-- Add model here -->    
                        </div>
                        <ul class="options-nav options-divider">

                        </ul>

                        <div class="options-body text-center">

                        </div>

                </aside>
                <aside class="lg-side">
                    <div class="group-head">
                        <h3>Group Name</h3>
                        <form action="#" class="pull-right position">
                            <div class="input-append">
                                <input type="text" class="sr-input" placeholder="Add a member">
                                <button class="btn sr-btn" type="button">Add</button>
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
