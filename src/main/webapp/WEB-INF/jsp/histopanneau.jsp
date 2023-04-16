<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html class=" ">
    <head>
        <!--
         * @Package: Complete Admin - Responsive Theme
         * @Subpackage: Bootstrap
         * @Version: BS4-1.0
         * This file is part of Complete Admin Theme.
        -->
        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8" />
        <title>Gestion | SUPPORTS</title>
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
        <!-- <link href="<c:url value='/assets/plugins/bootstrap/css/bootstrap-theme.min.css' />" rel="stylesheet" type="text/css"/> -->
        <link href="<c:url value='/assets/fonts/font-awesome/css/font-awesome.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/animate.min.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/plugins/perfect-scrollbar/perfect-scrollbar.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS FRAMEWORK - END -->

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - START -->


        <link href="<c:url value='/assets/plugins/datatables/css/datatables.min.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/select2/select2.css' />" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->


        <!-- PERSONAL SCRIPT -->
        <script>
            function activateFieldId(){
                document.getElementById("id").disabled = false;
                return true;
            }
        </script>
    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body class=" ">

		<!-- START TOPBAR -->
        <div class='page-topbar '>
            <div class='logo-area'>

            </div>
            <div class='quick-area'>
                <div class='float-left'>
                    <ul class="info-menu left-links list-inline list-unstyled">
                        <li class="sidebar-toggle-wrap list-inline-item">
                            <a href="#" data-toggle="sidebar" class="sidebar_toggle">
                                <i class="fa fa-bars"></i>
                            </a>
                        </li>

                    </ul>
                </div>

            </div>

        </div>
        <!-- END TOPBAR -->

        <!-- START CONTAINER -->
        <div class="page-container row-fluid container-fluid">

            <!-- SIDEBAR - START -->
            <div class="page-sidebar fixedscroll">

                <!-- MAIN MENU - START -->
                <div class="page-sidebar-wrapper" id="main-menu-wrapper">

                    <!-- USER INFO - START -->
                    <div class="profile-info row">

                        <div class="profile-image col-4">
                            <a href="ui-profile.html">
                                <img alt="" src="<c:url value='/data/profile/user_images.png' />" class="img-fluid rounded-circle">
                            </a>
                        </div>

                        <div class="profile-details col-8">

                            <h3>
                                <a href="#">Avatar</a>

                                <!-- Available statuses: online, idle, busy, away and offline -->
                                <span class="profile-status online"></span>
                            </h3>

                            <!--<p class="profile-title">Comptable</p>-->

                        </div>

                    </div>
                    <!-- USER INFO - END -->



                    <ul class='wraplist'>

                        <li class='menusection'>Menu Principal</li>
                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">Actions</span>
                            </a>
                            <ul class="sub-menu" >
                                <li>
                                    <a class="" href="/gestpann/afficherhistopanneau"  >Actualiser</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/acc"  >Retour accueil</a>
                                </li>
                            </ul>
                        </li>

                    </ul>


                </div>
                <!-- MAIN MENU - END -->



            </div>
            <!--  SIDEBAR - END -->

            <!-- START CONTENT -->
            <section id="main-content" class=" ">
                <section class="wrapper main-wrapper row" style=''>

                    <div class='col-12'>
                        <div class="page-title">

                            <div class="float-left">
                                <h1 class="title">Historique du support</h1>
                            </div>

                            <div class="float-right d-none">
                                <ol class="breadcrumb">
                                    <li>
                                        <a href="index.html"><i class="fa fa-home"></i>Home</a>
                                    </li>
                                    <li>
                                        <a href="tables-basic.html">Tables</a>
                                    </li>
                                    <li class="active">
                                        <strong>Data Tables</strong>
                                    </li>
                                </ol>
                            </div>

                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->





                    <div class="col-xl-12">
                        <section class="box nobox ">
                            <div class="content-body">
                                <div class="row">

                                    <div class="clearfix"></div><br>
                                    <div class="col-lg-12 search_data">
                                        <ul class="nav nav-tabs float-left  vertical col-lg-2 col-xl-2 col-md-3 col-3 left-aligned" id="myTab5" role="tablist">
                                            <li class="nav-item">
                                                <a href="#web-1" data-toggle="tab" role="tab" class="nav-link active" >
                                                    <i class="fa fa-home"></i> Photos
                                                </a>
                                            </li>
                                            <!--
                                            <li class="nav-item">
                                                <a href="#images-1" data-toggle="tab" role="tab" class="nav-link">
                                                    <i class="fa fa-image"></i> Images
                                                </a>
                                            </li>
                                            -->
                                        </ul>

                                        <div class="tab-content float-left  vertical col-xl-10 col-lg-10 col-md-3 col-3 left-aligned" id="myTabContent5">
                                            <div class="tab-pane fade show active" id="web-1">

                                                <c:forEach items="${listeDonnees}" var="donnees" >

                                                    <div class="search_result">
                                                        <div class="float-left col-md-2 col-3">
                                                            <img class="img-fluid" src="data:image/jpeg;base64,${ donnees.getImage() }">
                                                        </div>
                                                        <div class="float-left col-md-10 col-9">
                                                            <h4>Date de cr&eacute;ation : &nbsp;<a href="#">${ fn:replace(donnees.getDatecreation(), '00:00:00.0', '')  }</a></h4>
                                                            <p>Heure de cr&eacute;ation : &nbsp;${ donnees.getHeurecreation() }</p>
                                                        </div>
                                                    </div>
                                                </c:forEach>

                                            </div>
                                            <div class="tab-pane fade" id="images-1">

                                                <p>That said, in some situations it may be desirable to turn this functionality off. Therefore, we also provide the ability to disable the data attribute API by unbinding all events on the document namespaced with data-api. </p>
                                                <p>You can use all Bootstrap plugins purely through the markup API without writing a single line of JavaScript. This is Bootstrap's first-class API and should be your first consideration when using in a plugin.</p>
                                                <p>That said, in some situations it may be desirable to turn this functionality off. Therefore, we also provide the ability to. </p>

                                            </div>
                                            <div class="tab-pane fade" id="contacts-1">

                                                <p>Don't use data attributes from multiple plugins on the same element. For example, a button cannot both have a tooltip and toggle a modal. To accomplish this, use a wrapping element.</p>
                                                <p>You can use all Bootstrap plugins purely through the markup API without writing a single line of JavaScript. This is Bootstrap's first-class API and should be your first consideration when using in a plugin.</p>
                                                <p>That said, in some situations it may be desirable to turn this functionality off. Therefore, we also provide the ability to. </p>

                                            </div>

                                            <div class="tab-pane fade" id="projects-1">

                                                <p>We also believe you should be able to use all Bootstrap plugins purely through the JavaScript API. All public APIs are single, chainable methods, and return the collection acted upon.</p>
                                                <p>Don't use data attributes from multiple plugins on the same element. For example, a button cannot both have a tooltip and toggle a modal. To accomplish this, use a wrapping element.</p>
                                                <p>You can use all Bootstrap plugins purely through the markup API without writing a single line of JavaScript. This is Bootstrap's first-class API and should be your first consideration when using in a plugin.</p>
                                                <p>That said, in some situations it may be desirable to turn this functionality off. Therefore, we also provide the ability to. </p>


                                            </div>

                                            <div class="tab-pane fade" id="map-1">
                                                <p>Location and Maps Search Results</p>
                                            </div>
                                            <div class="tab-pane fade" id="videos-1">
                                                <p>Videos Search Results</p>
                                            </div>
                                            <div class="tab-pane fade" id="messages-1">
                                                <p>Messages Search Results</p>
                                            </div>
                                            <div class="tab-pane fade" id="profile-1">
                                                <p>Profile Search Results</p>
                                            </div>


                                        </div>

                                        <!-- </div>  -->

                                    </div>

                                </div>
                            </div>























                    <!-- MAIN CONTENT AREA ENDS -->
                </section>
            </section>
            <!-- END CONTENT -->




            <div class="chatapi-windows ">




            </div>    </div>
        <!-- END CONTAINER -->
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

        <script src="<c:url value='/assets/plugins/datatables/js/dataTables.min.js' />" type="text/javascript"></script>
		<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js">
        </script>
        <script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js">
        </script>

        <script src="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>

        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('.select2').select2();
            });
        </script>
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



