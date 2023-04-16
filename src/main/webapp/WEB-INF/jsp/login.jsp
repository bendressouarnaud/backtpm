<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html class=" ">
    <head>

        <!--
        <meta name="_csrf" content="${_csrf.token}" />
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        -->
        <!--
         * @Package: Complete Admin - Responsive Theme
         * @Subpackage: Bootstrap
         * @Version: BS4-1.0
         * This file is part of Complete Admin Theme.
        -->
        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8" />
        <title>GestPAN | Connexion</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <link rel="shortcut icon" href="<c:url value='/assets/images/favicon.png' />" type="image/x-icon" />    <!-- Favicon -->
        <link rel="apple-touch-icon-precomposed" href="<c:url value='/assets/images/apple-touch-icon-57-precomposed.png' />">	<!-- For iPhone -->
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value='/assets/images/apple-touch-icon-114-precomposed.png' />">    <!-- For iPhone 4 Retina display -->
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value='/assets/images/apple-touch-icon-72-precomposed.png' />">    <!-- For iPad -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='/assets/images/apple-touch-icon-144-precomposed.png' />">    <!-- For iPad Retina display -->




        <!-- CORE CSS FRAMEWORK - START -->
        <link href="<c:url value='/assets/plugins/pace/pace-theme-flash.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css"/>
        <!-- <link href="../assets/plugins/bootstrap/css/bootstrap-theme.min.css' />" rel="stylesheet" type="text/css"/> -->
        <link href="<c:url value='/assets/fonts/font-awesome/css/font-awesome.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/animate.min.css" rel="stylesheet' />" type="text/css"/>
        <link href="<c:url value='/assets/plugins/perfect-scrollbar/perfect-scrollbar.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS FRAMEWORK - END -->

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - START -->


        <link href="<c:url value='/assets/plugins/icheck/skins/all.css' />" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY   class=" login_page" -->
    <body style="background-image: url(<c:url value='/assets/images/mtnpageaccueil1.jpg'/>); background-repeat: no-repeat; background-attachment: fixed; background-size: cover;">

    <div class="container-fluid">
        <div class="login-wrapper row">
            <div id="login" class="login loginpage offset-xl-4 offset-lg-3 offset-md-3 offset-0 col-12 col-md-6 col-xl-4">
                <!--<h1><a href="#" title="Login Page" tabindex="-1">Complete Admin</a></h1>-->
                <div style="text-align:center">
                    <img src="<c:url value='/assets/images/logoMTN.jpeg' />" alt="user-image" >
                    <!--<img src="<c:url value='/assets/images/logo_nbci.png' />" alt="user-image" >-->
                </div>

                <form name="loginform" id="loginform" action="${contextPath}/login" method="post">
                    <p>
                        <label for="user_login">Identifiant : <br />
                            <input type="text" name="username" id="user_login" class="input" placeholder="identifiant" size="20" /></label>
                    </p>
                    <p>
                        <label for="user_pass">Mot de passe :<br />
                            <input type="password" name="password" id="user_pass" class="input" placeholder="Mot de passe" size="20" /></label>
                    </p>

                    <!--
                    <p class="forgetmenot">
                        <label class="icheck-label form-label" for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="forever" class="icheck-minimal-aero" checked> Remember me</label>
                    </p>
                    -->
                    <p class="forgetmenot">
                        ${message}
                    </p>

                    <!--
                    <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />
                    -->

                    <p class="submit">
                        <input type="submit" name="wp-submit" id="wp-submit"
                            class="btn btn-accent btn-block" value="Se connecter" />
                    </p>
                </form>

                <!--
                <p id="nav">
                    <a class="float-left" href="#" title="Password Lost and Found">Forgot password?</a>
                    <a class="float-right" href="ui-register.html" title="Sign Up">Sign Up</a>
                </p>
                -->


            </div>
        </div>
    </div>




    <!-- MAIN CONTENT AREA ENDS -->
    <!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->


    <!-- CORE JS FRAMEWORK - START -->
    <script src="<c:url value='/assets/js/jquery-3.2.1.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/js/popper.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/js/jquery.easing.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/plugins/bootstrap/js/bootstrap.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/plugins/pace/pace.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/plugins/perfect-scrollbar/perfect-scrollbar.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/assets/plugins/viewport/viewportchecker.js' />" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="<c:url value='/assets/js/jquery-1.11.2.min.js' />"><\/script>');</script>
    <!-- CORE JS FRAMEWORK - END -->


    <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - START -->

    <script src="<c:url value='/assets/plugins/icheck/icheck.min.js' />" type="text/javascript"></script>
    <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


    <!-- CORE TEMPLATE JS - START -->
    <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
    <!-- END CORE TEMPLATE JS - END -->


    <!-- General section box modal start -->
    <div class="modal" id="section-settings" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog animated bounceInDown">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Section Settings</h4>
                </div>
                <div class="modal-body">

                    Body goes here...

                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                    <button class="btn btn-success" type="button">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal end -->
    </body>
</html>



