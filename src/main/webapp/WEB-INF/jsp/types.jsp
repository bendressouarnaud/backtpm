<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        <title>Gestion | TYPES SUPPORT</title>
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
                            <a href="/gestpann/acc">
                                <img alt="" src="<c:url value='/data/profile/user_images.png' />" class="img-fluid rounded-circle">
                            </a>
                        </div>

                        <div class="profile-details col-8">

                            <h3>
                                <a href="/gestpann/acc">Avatar</a>

                                <!-- Available statuses: online, idle, busy, away and offline -->
                                <span class="profile-status online"></span>
                            </h3>

                            <!--<p class="profile-title">Web Developer</p>-->

                        </div>

                    </div>
                    <!-- USER INFO - END -->



                    <ul class='wraplist'>

                        <li class='menusection'>Main</li>
                        <li class="">
                            <a href="#">
                                <i class="fa fa-dashboard"></i>
                                <span class="title">Principal</span>
                            </a>
                        </li>
                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">Actions</span>
                            </a>
                            <ul class="sub-menu" >
                                <li>
                                    <a class="" href="/gestpann/nouveltypes"  >Nouveau type</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/types"  >Actualiser</a>
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
                                <!-- PAGE HEADING TAG - START -->
                                <h1 class="title">Gestion TYPES</h1>
                                <!-- PAGE HEADING TAG - END -->
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



                    <!-- AJOUT DE NOUVEAUX CHAMPS -->
                    <c:if test="${!empty nouveltypes || !empty types}">
                        <div class="col-xl-12">
                              <section class="box ">
                                  <header class="panel_header">
                                      <h2 class="title float-left">AJOUT NOUVEAU TYPE</h2>
                                      <div class="actions panel_actions float-right">
                                          <a class="box_toggle fa fa-chevron-down"></a>
                                          <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                          <a class="box_close fa fa-times"></a>
                                      </div>
                                  </header>
                                  <div class="content-body">
                                      <form id="general_validate" method="POST"
                                        action="${(!empty types) ? '/gestpann/modifiertypes' : '/gestpann/creertypes' }"
                                        onsubmit="return activateFieldId();">
                                          <div class="row">

                                              <div class="col-6">

                                                  <div class="form-group">
                                                      <label class="form-label">Id</label>
                                                          <div class="controls">
                                                          <input disabled type="text" class="form-control" name="id"
                                                            value="${(!empty types) ? types.getIdtyp() : '' }" id="id" >
                                                      </div>
                                                  </div>

                                                  <div class="form-group">
                                                      <label class="form-label">Libell&eacute;</label>
                                                      <div class="controls">
                                                          <input type="text" class="form-control" name="libelle"
                                                           value="${(!empty types) ? types.getLibelle() : '' }"
                                                           >
                                                      </div>
                                                  </div>
                                              </div>

                                              <div class="col-6">
                                                <div class="form-group">
                                                    <label class="form-label">Dimension</label>
                                                    <div class="controls">
                                                        <select class="select2"  name="taille">
                                                            <c:forEach items="${listTaille}" var="taille" >
                                                                <option value="${ taille.getIdtai() }"
                                                                  ${ (!empty types) ? (types.getIdtail()==taille.getIdtai()) ? "selected" : "" : ""}>
                                                                  ${ taille.getLibelle() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                              </div>


                                              <div class="col-12">
                                                  <div class="float-right ">
                                                      <button type="submit" class="btn btn-success">Enregistrer</button>
                                                      <button type="reset" class="btn">Annuler</button>
                                                  </div>
                                              </div>

                                          </div>
                                      </form>
                                  </div>
                              </section>
                        </div>
                    </c:if>




                    <div class="col-xl-12">
                        <section class="box ">
                            <header class="panel_header">
                                <h2 class="title float-left">Liste des Types</h2>
                                <div class="actions panel_actions float-right">
                                    <a class="box_toggle fa fa-chevron-down"></a>
                                    <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                    <a class="box_close fa fa-times"></a>
                                </div>
                            </header>
                            <div class="content-body">    <div class="row">
                                    <div class="col-lg-12 col-md-12 col-12 padding-0">

                                        <table id="example" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Libell&eacute;</th>
                                                    <th>Taille</th>
                                                    <th>Modifier</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Libell&eacute;</th>
                                                    <th>Taille</th>
                                                    <th>Modifier</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>

												<c:forEach items="${listTypes}" var="types" >
													<tr>
														<td>${ types[0] }</td>
														<td>${ types[2] }</td>
														<td><a class="box_toggle fa fa-wrench"
														    href="/gestpann/modiftypes/${ types[1] }"></a></td>
													</tr>
												</c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </section>
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
        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <!-- END CORE TEMPLATE JS - END -->

        <!-- FOR DATABLE -->
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('.select2').select2();
                $('#example').DataTable();
            });
        </script>


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


