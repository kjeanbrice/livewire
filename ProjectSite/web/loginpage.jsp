<%-- 
    Document   : loginpage
    Created on : Nov 27, 2016, 5:42:15 PM
    Author     : Karl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>The Dirty Bits Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/css/bootstrap.min.css">
        <link rel="stylesheet" href="styles/gen-styles/loginformstyle.css">
        <link rel="stylesheet" href="styles/gen-styles/loginformgen.css">
        <script src = "scripts/html-helper.js"></script>
    </head>
    <body class = "body-background">
        <div class = "container-fluid user-login-background ">

            <div class ="row row-no-padding">
                <div class = " col-md-12 user-login-head">  
                    <img src="images/temp_logo.png" alt ="Logo Here" height="50" width="50">
                    <br>
                    <span id = "team_name">
                        THE DIRTY BITS
                    </span>
                </div>
            </div>
            <div class ="row panel-padding">
                <div class="col-md-6 col-md-offset-3">
                    <div class="loginarea centerlogin">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <a href="#" class="active" id="login-form-link">Sign In</a>
                                    </div>
                                    <div class="col-xs-6">
                                        <a href="#" id="register-form-link">Register</a>
                                    </div>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-8 col-lg-offset-2">
                                        <form id="login-form" action="ProcessLogin" method="post" role="form" style="display: block;">
                                            <div class="form-group">
                                                <input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="${requestScope.invaliduser.email}">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                            </div>
                                            <div class="form-group text-center">
                                                <span id ="err-login" class = "lbl-error">
                                                    <%
                                                        String loginError = (String) request.getAttribute("LOGIN_ERROR");
                                                        if (loginError != null) {
                                                            out.println(loginError);
                                                        }
                                                    %>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="text-center">
                                                            <br>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <form id="register-form" action=" " method="post" role="form" style="display: none;">
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
                                                <input type="text" name="state" id="state" tabindex="1" class="form-control" placeholder="State" value="">
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src = "scripts/jquery-3.1.1.min.js"></script>
        <script src = "scripts/login_form_transition_effect.js"></script> 
    </body>
</html>


