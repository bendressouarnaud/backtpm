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
        <title>Accueil | Journal</title>
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
        <link href="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/datepicker/css/datepicker.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/select2/select2.css' />" rel="stylesheet" type="text/css" media="screen"/>


        <link href="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-2.0.1.css' />" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END
        <link href="<c:url value='/assets/plugins/datatables/css/datatables.min.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/datatables/css/datatables.css' />" rel="stylesheet" type="text/css" media="screen"/>
        -->

        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" rel="stylesheet"  />
        <link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet"  />


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

        <!-- PERSONAL SCRIPT -->
        <script>


            //   --  DATE
            function checkDate(valeur){
            	var retour = true;

            	if (!/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/.test(valeur)){
            	    // Alerter infos
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>L'une des dates renseign&eacute;e est incorrecte !!!</h3>";
            		retour = false;
            	}
            	else{
                    var dates = valeur.split('/');
                    //alert("dates[0] : "+dates[0]);
                    if(parseInt(dates[0]) > 12){
                        // Alerter infos
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>L'une des dates renseign&eacute;e est incorrecte !!!</h3>";
                        retour = false;
                    }
            	}

            	return retour;
            }


            function activateFieldId(){

                var dateDebut = $('#ddbut').val();
                //alert("dateDebut : "+dateDebut);
                if(checkDate(dateDebut)){
                    return true;
                }
                else return false;
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

                            <!--<p class="profile-title">Comptable</p>-->

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
                                    <a class="" href="/gestpann/journal"  >Actualiser</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/acc"  >Retour accueil</a>
                                </li>
                            </ul>
                        </li>

                    </ul>

                    <!--
                    <div class="menustats">
                        <h5>Project Progress</h5>
                        <div class="progress">
                            <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                            </div>
                        </div>
                        <h5>Target Achieved</h5>
                        <div class="progress">
                            <div class="progress-bar progress-bar-accent" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
                            </div>
                        </div>
                    </div>
                    -->
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
                                <h1 class="title">Choix des filtres</h1>
                                <!-- PAGE HEADING TAG - END -->
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->




                    <!-- AJOUT DE NOUVEAUX CHAMPS -->
                    <div class="col-xl-12">
                        <section class="box ">
                              <header class="panel_header">
                                  <h2 class="title float-left">Filtres</h2>
                                  <div class="actions panel_actions float-right">
                                      <a class="box_toggle fa fa-chevron-down"></a>
                                  </div>
                              </header>
                              <div class="content-body">
                                  <form id="general_validate" method="POST" action="/gestpann/soumettreexecution"
                                    onsubmit="return activateFieldId();"
                                  >
                                      <div class="row">

                                          <div class="col-6">
                                              <div class="form-group">
                                                    <label class="form-label">Date ex&eacute;cution</label>
                                                    <div class="controls">
                                                        <input id="ddbut" type="text" class="form-control datepicker" name="dateexec"
                                                            value=""
                                                        />
                                                    </div>
                                              </div>
                                          </div>


                                          <div class="col-12">
                                              <div id="infos">
                                              </div>
                                          </div>

                                          <div class="col-12">
                                              <div class="float-right ">
                                                  <button type="submit" id="valider" class="btn btn-success">Soumettre</button>
                                              </div>
                                          </div>
                                      </div>
                                  </form>
                              </div>
                          </section>
                    </div>
                    <div class="clearfix"></div>




                    <div class="clearfix"></div>


                    <c:if test="${!empty donnees}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Historique des actions
                                    <span style="color:red;">-- ${user}</span></h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                    </div>
                                </header>
                                <div class="content-body">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-12 padding-0">
                                            <table id="example" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Dates</th>
                                                        <th>Heure</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Dates</th>
                                                        <th>Heure</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${listeJournal}" var="journal" >
                                                        <tr>
                                                            <td>${ fn:replace(journal.getDates(), '00:00:00.0', '') }</td>
                                                            <!--<td>${ journal.getDates() }</td>-->
                                                            <td>${ journal.getHeure() }</td>
                                                            <td>${ journal.getAction() }</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </c:if>


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


        <!--
        <script src="<c:url value='/assets/plugins/datatables/js/dataTables.min.js' />" type="text/javascript"></script>
         OTHER SCRIPTS INCLUDED ON THIS PAGE - START
        -->


        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>



        <!--
        <script src="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-2.0.1.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/js/dashboard.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/echarts/echarts-custom-for-dashboard.js' />" type="text/javascript"></script>
        -->

        <script src="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/datepicker/js/datepicker.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>

        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                //$('.select2').select2();
                //$('#example').DataTable();
                $('.datepicker').datepicker();

                //orientation
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



