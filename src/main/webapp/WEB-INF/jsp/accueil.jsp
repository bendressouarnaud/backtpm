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
        <title>Accueil | GestPAN</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />


        <!--   SECURITY TAG
        <meta http-equiv="Content-Security-Policy"
            content="default-src 'self' https://cdnjs.cloudflare.com/ajax/ https://cdn.datatables.net/1.10.19/ https://code.jquery.com/ https://cdn.datatables.net/1.10.19/ https://fonts.googleapis.com/ http://34.68.214.253/gestpann/  https://fonts.gstatic.com/ 'unsafe-inline' 'unsafe-eval'; img-src 'self' data: blob: "/>
        -->


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
        <!--<link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
         CORE CSS TEMPLATE - END -->

        <!-- PERSONAL SCRIPT -->
        <script>



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
                <div>
                    <div class='float-right'
                        style='background-repeat: no-repeat; height: 60px;'>

                        <!--<img src="<c:url value='/assets/images/logo_nbci.png' />" alt="user-image" >-->
                        <img src="<c:url value='/assets/images/logoMTN.jpeg' />" alt="user-image" >
                    </div>

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

                        <li class='menusection'>Menu principal</li>
                        <li class="open">
                            <a href="/gestpann/acc">
                                <i class="fa fa-dashboard"></i>
                                <span class="title">Accueil</span>
                            </a>
                        </li>
                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">Gestion</span>
                                <span class="arrow "></span>
                                <!--<span class="badge badge-accent">HOT</span>-->
                            </a>
                            <ul class="sub-menu" >
                                <li>
                                    <a class="" href="/gestpann/deconnex">D&eacute;connexion</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/dashboard">Tableau de bord</a>
                                </li>


                                <li>
                                    <a class="" href="/gestpann/getrapportfinal">
                                    Rapport final
                                    </a>
                                </li>

                                <!--
                                <li>
                                    <a class="" href="/gestpann/updateodpdata">
                                    Update ODP Panneau ...
                                    </a>
                                </li>
                                -->

                                <c:if test="${ role == 'superuser'}">
                                    <li>
                                        <a class="" href="/gestpann/gestionuserpwd">Mots de passe USERS</a>
                                    </li>

                                    <li>
                                        <a class="" href="/gestpann/parametres">Param&egrave;tres</a>
                                    </li>

                                    <li>
                                        <a class="" href="/gestpann/webselect"  >WebSelect</a>
                                    </li>
                                    <!--
                                    <li>
                                        <a class="" href="/gestpann/getexecution"  >Ex&eacute;cution manuel</a>
                                    </li>-->

                                </c:if>

                                <c:if test="${ role == 'superuser'}">
                                    <li>
                                        <a class="" href="/gestpann/client"  >Client</a>
                                    </li>
                                </c:if>
                                <li>
                                    <a class="" href="/gestpann/panneau"  >Support</a>
                                </li>
                                <c:if test="${ role=='admin' || role=='superuser'}">
                                    <li>
                                        <a class="" href="/gestpann/taille">Taille</a>
                                    </li>
                                    <li>
                                        <a class="" href="/gestpann/ville">Commune</a>
                                    </li>
                                    <li>
                                        <a class="" href="/gestpann/quartier">Quartier</a>
                                    </li>
                                    <li>
                                        <a class="" href="/gestpann/secteur">Secteur</a>
                                    </li>

                                    <li>
                                        <a class="" href="/gestpann/types">Type</a>
                                    </li>

                                    <li>
                                         <a class="" href="/gestpann/horsligne">Autorisation Hors ligne</a>
                                    </li>

                                </c:if>

                                <c:if test="${ role == 'superuser'}">
                                    <li>
                                        <a class="" href="/gestpann/gestionuser">Utilisateur</a>
                                    </li>
                                </c:if>

                                <li>
                                     <a class="" href="/gestpann/choixafficherpanneaux">
                                        Afficher les supports
                                     </a>
                                </li>

                                <!-- Test du WEBSOCKET
                                <li>
                                     <a class="" href="/gestpann/testwebsoc">
                                        Test web socket
                                     </a>
                                </li>-->

                                <li>
                                     <a class="" target="_blank" href="/gestpann/tempsreel">
                                        Test temps reel
                                     </a>
                                </li>



                                <c:if test="${ role  == 'admin'  || role=='superuser'}">
                                    <li>
                                         <a class="" href="/gestpann/journal">
                                            Journal des actions
                                         </a>
                                    </li>
                                </c:if>
                            </ul>
                        </li>
                        <li class="">
                            <a href="javascript:;">
                                <i class="fa fa-columns"></i>
                                <span class="title">Nouvelles entr&eacute;es</span>
                                <span class="arrow "></span>
                            </a>
                            <ul class="sub-menu" >
                                <li>
                                    <a class="" href="/gestpann/nouvcom" >Nouvelle publication</a>
                                </li>

                                <li>
                                    <a class="" href="/gestpann/histopubli" >Historique publication</a>
                                </li>

                                <li>
                                    <!-- RequetemajController -->
                                    <a class="" href="/gestpann/requetepan" >Requ&ecirc;te MAJ support</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/rapports" >Rapports</a>
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
                                <h1 class="title">Accueil</h1>
                                <!-- PAGE HEADING TAG - END -->
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->




                    <!-- AJOUT DE NOUVEAUX CHAMPS -->
                    <c:if test="${!empty nouvcommande || !empty commande}">
                        <div class="col-xl-12">
                              <section class="box ">
                                  <header class="panel_header">
                                      <h2 class="title float-left">AJOUT D'UNE NOUVELLE PUBLICATION</h2>
                                      <div class="actions panel_actions float-right">
                                          <a class="box_toggle fa fa-chevron-down"></a>
                                      </div>
                                  </header>
                                  <div class="content-body">
                                      <form id="general_validate" method="POST"
                                        action="${(!empty commande) ? '/gestpann/modifiercommande' : '/gestpann/creercommande' }"
                                        onsubmit="return activateFieldId();">
                                          <div class="row">



                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Id</label>
                                                          <div class="controls">
                                                          <input disabled type="text" class="form-control" name="id"
                                                            value="${(!empty commande) ? commande.getIdcom() : '' }" id="id" >
                                                      </div>
                                                  </div>

                                                  <div class="form-group">
                                                      <label class="form-label">P&eacute;riode fin</label>
                                                      <div class="controls">
                                                            <input id="ddfin" type="text" class="form-control datepicker" name="fin"
                                                                value="${(!empty commande) ? datefin : '' }"
                                                            >
                                                      </div>
                                                  </div>
                                              </div>

                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">P&eacute;riode d&eacute;but</label>
                                                      <div class="controls">
                                                          <input id="ddbut" type="text" class="form-control datepicker" name="debut"
                                                           value="${(!empty commande) ? datedebut : '' }"
                                                           >
                                                      </div>
                                                  </div>


                                                  <div class="form-group">
                                                      <label class="form-label">Produit &agrave; publier</label>
                                                      <div class="controls">
                                                          <input type="text" class="form-control" name="produit"
                                                              value="${(!empty commande) ? commande.getPdtpublier() : '' }"
                                                          >
                                                      </div>
                                                  </div>
                                              </div>

                                              <c:if test="${!empty nouvcommande}">
                                                  <div class="col-6">
                                                    <div class="form-group">
                                                          <label class="form-label">Commune</label>
                                                          <div class="controls">
                                                              <select id="commune" class="select2" id="s2example-2"
                                                                onchange="changeQuartier();">
                                                                  <c:forEach items="${listCommune}" var="commune" >
                                                                      <option value="${ commune.getIdvil() }">
                                                                        ${ commune.getLibelle() }
                                                                      </option>
                                                                  </c:forEach>
                                                              </select>
                                                          </div>
                                                      </div>
                                                  </div>


                                                  <div class="col-6">
                                                    <div class="form-group">
                                                          <label class="form-label">Quartier</label>
                                                          <div class="controls">
                                                              <select id="quartier" class="select2" id="s2example-2" onchange="changeSecteur();">
                                                                <option value="0">--</option>
                                                              </select>
                                                          </div>
                                                      </div>
                                                  </div>


                                                  <div class="col-6">
                                                    <div class="form-group">
                                                          <label class="form-label">Secteur</label>
                                                          <div class="controls">
                                                              <select id="secteur" class="select2" id="s2example-2" onchange="changeSupport();">
                                                                <option value="0">--</option>
                                                              </select>
                                                          </div>
                                                      </div>
                                                  </div>
                                                </c:if>

                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Support</label>
                                                      <div class="controls">
                                                          <select class="select2" id="support" name="panneau">
                                                              <option value="${ (!empty commande) ? commande.getIdpan() : 0 }">
                                                                ${ (!empty commande) ? mpanneau.getLibelle() : '--' }
                                                              </option>
                                                          </select>
                                                      </div>
                                                  </div>
                                              </div>


                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Client</label>
                                                      <div class="controls">
                                                            <select class="select2" id="s2example-1" name="client">
                                                                <c:forEach items="${listClient}" var="client" >
                                                                    <option value="${ client.getIdcli() }"
                                                                    ${ (!empty commande) ? (commande.getIdcli()==client.getIdcli()) ? "selected" : "" : ""}>
                                                                        ${ client.getLibelle() }
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                      </div>
                                                  </div>

                                              </div>

                                              <div class="col-12">
                                                  <div id="infos">
                                                  </div>
                                              </div>

                                              <div class="col-12">
                                                  <div class="float-right ">
                                                      <button type="submit" class="btn btn-success">Enregistrer</button>
                                                      <button type="reset" class="btn">Effacer</button>
                                                  </div>
                                              </div>

                                          </div>
                                      </form>
                                  </div>
                              </section>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>




                    <div class="clearfix"></div>
                    <div class="col-xl-12">
                        <section class="box nobox marginBottom0">
                            <div class="content-body">
                                <div class="row">

                                    <div class="col-xl-3 col-md-6 col-12">
                                        <div class="r4_counter db_box">
                                            <i class='float-left fa fa-shopping-cart icon-md icon-rounded icon-accent'></i>
                                            <div class="stats">
                                                <!--<h4><strong>${ nbrepanneau }</strong></h4>-->
                                                <h4><strong>125 105</strong></h4>
                                                <span>Nombre total Supports</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xl-3 col-md-6 col-12">
                                        <div class="r4_counter db_box">
                                            <i class='float-left fa fa-money icon-md icon-rounded icon-purple'></i>
                                            <div class="stats">
                                                <!--<h4><strong>${ coutTotal }</strong></h4>-->
                                                <h4><strong>945 819 902</strong></h4>
                                                <span>Taxes sur publicit&eacute;</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xl-3 col-md-6 col-12">
                                        <div class="r4_counter db_box">
                                            <!--<i class='float-left fa fa-users icon-md icon-rounded icon-warning'></i>-->
                                            <i class='float-left fa fa-money icon-md icon-rounded icon-warning'></i>
                                            <div class="stats">
                                                <!--<h4><strong>${ coutTotodp }</strong></h4>-->
                                                <h4><strong>554 180 098</strong></h4>
                                                <span>Taxes ODP</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xl-3 col-md-6 col-12">
                                        <div class="r4_counter db_box">
                                            <i class='float-left fa fa-money icon-md icon-rounded icon-purple'></i>
                                            <div class="stats">
                                                <!--<h4><strong>${ coutTotaux }</strong></h4>-->
                                                <h4><strong>1 500 000 000</strong></h4>
                                                <span>Taxes totales</span>
                                            </div>
                                        </div>
                                    </div>

                                </div> <!-- End .row -->
                            </div>
                        </section>
                    </div>


                    <div class="clearfix"></div>
                    <div class="row margin-0">
                        <div class="col-12"><!--  col-lg-6 -->
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">5 plus nombreux Supports  sur les 3 derniers jours</h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                    </div>
                                </header>
                                <div class="content-body">        <div class="row">
                                        <div class="col-12">
                                            <div class="chart-container">
                                                <div class="" style="height:200px" id="platform_type_dates"></div>
                                            </div>
                                        </div>
                                    </div> <!-- End .row -->
                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="clearfix"></div>



                    <div class="col-xl-12">
                        <section class="box ">
                            <header class="panel_header">
                                <h2 class="title float-left">R&eacute;capitulatif des commandes</h2>
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
                                                    <th>Libell&eacute; support</th>
                                                    <th>Produit</th>
                                                    <th>Co&ucirc;t</th>
                                                    <th>Date d&eacute;but</th>
                                                    <th>Date fin</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Libell&eacute; support</th>
                                                    <th>Produit</th>
                                                    <th>Co&ucirc;t</th>
                                                    <th>Date d&eacute;but</th>
                                                    <th>Date fin</th>
                                                    <th>Action</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach items="${histoCommande}" var="commandes" >
                                                	<tr>
                                                		<td>${ commandes[0] }</td>
                                                		<td>${ commandes[1] }</td>
                                                		<td>${ commandes[2] }</td>
                                                		<td>${ fn:replace(commandes[3], '00:00:00.0', '') }</td>
                                                		<td>${ fn:replace(commandes[4], '00:00:00.0', '') }</td>
                                                		<td><a class="box_toggle fa fa-wrench"
                                                		    href="/gestpann/modifcommande/${ commandes[5] }"></a></td>
                                                	</tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>

                                <input type="hidden" id="orientation"
                                    value="${ (!empty nouvcommande) ? 1 : 0} " />

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

        <!-- Hidden elements -->
        <input type="hidden" id="lienapp" value="${ lienapp }" />


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




        <script src="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-2.0.1.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/js/dashboard.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/echarts/echarts-custom-for-dashboard.js' />" type="text/javascript"></script>


        <script src="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/datepicker/js/datepicker.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>

        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <script type="text/javascript">

            var lienapp = "";

            $(document).ready(function(){

                lienapp = $('#lienapp').val();

                $('#example').DataTable();
                $('.select2').select2();
                $('.datepicker').datepicker();

                //orientation
                var orientation = parseInt($('#orientation').val());
                //alert("orientation : "+orientation);
                if(parseInt(orientation)==1){
                    changeQuartier();
                }

                //  Pour L'ANIMATION :
				var mTabTotalSupport1 = new Array();
				var mTabTotalSupport2 = new Array();
				var mTabTotalSupport3 = new Array();

				var mTabTotalDate1 = new Array();
				var mTabTotalDate2 = new Array();
				var mTabTotalDate3 = new Array();

				var tabTroisAnnees = new Array()


                // Add this :
                var mTabCompaniesLibelle = new Array();
                var mTabCompaniesCA = new Array();
                var mTabCompaniesYear = new Array();

				//
				var myVar = setInterval(
					function(){
						if(mTabTotalSupport1.length > 0 &&
						    mTabTotalSupport2.length > 0 &&
						    mTabTotalSupport3.length > 0){
							afficher();
						}
					},
				300);


                // Recuperer les 3 derniers dates
                // lienapp+"gestpann/getuserjs/0"
				$.get(lienapp + "gestpann/getuserjs/0",
					function(data) {

					    var cpt_i = 0;

						$(data).find('item').each(function(){
						    //alert($(this).text());
						    tabTroisAnnees[cpt_i] = $(this).text();
						    cpt_i = cpt_i +1;
						});

						// Call the function to briong data back for each YEAR :
						for (var j = 0; j < tabTroisAnnees.length; j++) {
							//alert("dates  :"+ tabTroisAnnees[j]);
							getDataBack(j, tabTroisAnnees[j]);
						}

						// Update, just display the DAY and not the full date :
						//alert("tabTroisAnnees[0] : "+tabTroisAnnees[0]);
						//alert("tabTroisAnnees[1] : "+tabTroisAnnees[1]);
						//alert("tabTroisAnnees[2] : "+tabTroisAnnees[2]);
						var tp1 = tabTroisAnnees[0].split('-');
						tabTroisAnnees[0] = tp1[1]+""+tp1[2];
						var tp2 = tabTroisAnnees[1].split('-');
						tabTroisAnnees[1] = tp2[1]+""+tp2[2];
						var tp3 = tabTroisAnnees[2].split('-');
						tabTroisAnnees[2] = tp3[1]+""+tp3[2];

					}
                );



				function getDataBack(index, dates){
					var i=0;
                    $.get(lienapp + "gestpann/getuserjsdata/"+dates,
						function(data) {
							$(data).find('item').each(function(){

                                //alert("index i : "+index);
                                var libelle = $(this).find('libelle').text();
                   				var total = parseInt($(this).find('total').text());

								// set :
								switch(index) {
									case 0:
										// Date la plus recente :
										mTabTotalSupport1[i] = libelle;
										mTabTotalDate1[i] = total;
										break;
									case 1:
										// Année au milieu :
										mTabTotalSupport2[i] = libelle;
										mTabTotalDate2[i] = total;
										break;
									case 2:
										// Année au milieu :
										mTabTotalSupport3[i] = libelle;
										mTabTotalDate3[i] = total;
										break;
								}
								i = i +1;
							});
						}
                    );
				}


				function afficher(){

					// Stop
					clearInterval(myVar);
					//alert("affichage ");

					var myChart = echarts.init(document.getElementById('platform_type_dates'));
					var idx = 1;
					var option_dt = {

						timeline : {
							show: true,
							data : tabTroisAnnees,
							label : {
								formatter : function(s) {
									return s.slice(0, 5);
								}
							},
							x:10,
							y:null,
							x2:10,
							y2:0,
							width:400,
							height:50,
							backgroundColor:"rgba(0,0,0,0)",
							borderColor:"#eaeaea",
							borderWidth:0,
							padding:5,
							controlPosition:"left",
							autoPlay:true,
							loop:true,
							playInterval:5000,
							lineStyle:{
								width:1,
								color:"#bdbdbd",
								type:""
							},

						},

						options : [
							{
								color: ['#3F51B5','#303F9F','#1A237E','#9FA8DA','#7986CB','#C5CAE9'],
								title : {
									text: '',
									subtext: ''
								},
								tooltip : {
									trigger: 'item',
									formatter: "{a} <br/>{b} : {c} ({d}%)"
								},
								legend: {
									show: false,
									x: 'left',
									orient:'vertical',
									padding: 0,
									data:mTabTotalSupport1
									//data:['Mangoe','Windows','Linux','Android','Others']
								},
								toolbox: {
									show : true,
									color : ['#bdbdbd','#bdbdbd','#bdbdbd','#bdbdbd'],
									feature : {
										mark : {show: false},
										dataView : {show: false, readOnly: true},
										magicType : {
											show: true,
												itemSize:12,
												itemGap: 12,
											type: ['pie', 'funnel'],
											option: {
												funnel: {
													x: '10%',
													width: '80%',
													funnelAlign: 'center',
													max: 50
												},
												pie: {
													roseType : 'none',
												}
											}
										},
										restore : {show: false},
										saveAsImage : {show: true}
									}
								},


												series : [
													{
														name:tabTroisAnnees[0],
														type:'pie',
														radius : [15, '70%'],
														roseType : 'radius',
														center: ['50%', '45%'],
														width: '50%',       // for funnel
														itemStyle : {
															normal : { label : { show : true }, labelLine : { show : true } },
															emphasis : { label : { show : false }, labelLine : {show : false} }
														},
														data:[{value: mTabTotalDate1[0],  name:mTabTotalSupport1[0]}, {value: mTabTotalDate1[1],  name:mTabTotalSupport1[1]}, {value: mTabTotalDate1[2],  name:mTabTotalSupport1[2]}, {value: mTabTotalDate1[3],  name:mTabTotalSupport1[3]}, {value: mTabTotalDate1[4],  name:mTabTotalSupport1[4]}]
													}
												]
										},
									{
										series : [
											{
												name:tabTroisAnnees[1],
												type:'pie',
												data:[{value: mTabTotalDate2[0],  name:mTabTotalSupport2[0]}, {value: mTabTotalDate2[1],  name:mTabTotalSupport2[1]}, {value: mTabTotalDate2[2],  name:mTabTotalSupport2[2]}, {value: mTabTotalDate2[3],  name:mTabTotalSupport2[3]}, {value: mTabTotalDate2[4],  name:mTabTotalSupport2[4]}]
											}
										]
									},
									{
										series : [
											{
												name:tabTroisAnnees[2],
												type:'pie',
												data:[{value: mTabTotalDate3[0],  name:mTabTotalSupport3[0]}, {value: mTabTotalDate3[1],  name:mTabTotalSupport3[1]}, {value: mTabTotalDate3[2],  name:mTabTotalSupport3[2]}, {value: mTabTotalDate3[3],  name:mTabTotalSupport3[3]}, {value: mTabTotalDate3[4],  name:mTabTotalSupport3[4]}]
											}
										]
									},

						] // end options object
					};

					myChart.setOption(option_dt);
				}
				/***    F I N   *****/

            });

            function changeQuartier(){
                //alert("Test START");
                var codeCommune = $('#commune').val();
                getItems(0,codeCommune,"quartier");
            }

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
                    // ddfin
                    var dateFin = $('#ddfin').val();
                    //alert("dateFin : "+dateFin);
                    if(!checkDate(dateFin)){
                        return false;
                    }
                }
                else return false;

                var orientation = parseInt($('#orientation').val());
                if(parseInt(orientation)==1){
                    // Il doit avoir des données dans la liste deroulante SUPPORT :
                    var support = parseInt($('#support').val());
                    if(parseInt(support)==0){
                        // Alerter infos
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Veuillez s&eacute;lectionner un support !!!</h3>";
                        return false;
                    }
                    else{
                        document.getElementById("id").disabled = false;
                        return true;
                    }
                }
                else{
                    document.getElementById("id").disabled = false;
                    return true;
                }
            }





            //
            function changeSecteur(){
                var codeCommune = $('#quartier').val();
                getItems(1,codeCommune,"secteur");
            }


            //
            function changeSupport(){
                var codeSecteur = $('#secteur').val();
                getItems(2,codeSecteur,"support");
            }


            // To get all supports :
            function getItems(choix, id,objet){

                // Desactiver :
                //document.getElementById("valider").disabled = false;

                var idTab = new Array();
                var idLibelle = new Array();
                var i=0;

                //alert("Test");
                /*
                $.ajax({
                    url: 'http://34.68.214.253/gestpann/getitemback/'+choix+'/'+id,
                    method: 'GET',
                    type: 'xml',
                    crossDomain: true,
                    success: function (response) {
                        alert(response);
                    },
                    error: function (error) {
                        alert(error);
                    }
                    });
                });
                */

                $.get(lienapp + "gestpann/getitemback/"+choix+"/"+id,
                    function (data){
                        $(data).find('item').each(function(){
                        	var id = $(this).find('id').text();
                        	var libelle = $(this).find('libelle').text();

                        	// set :
                        	idTab[i] = id;
                        	idLibelle[i] = libelle;
                        	i = i +1;

                        	//alert("Libelle : "+libelle);
                        });

                        // Clear items :
                        if(objet=="quartier") {
                            //$('#quartier').html('').select2({data: [{id: '', text: ''}]});
                            $('#quartier').empty();
                            var quartier = $('#quartier');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                quartier.append(option).trigger('change');
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                quartier.append(option).trigger('change');
                            }
                        }
                        else if(objet=="secteur"){
                            $('#secteur').empty();
                            var secteur = $('#secteur');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                secteur.append(option).trigger('change');
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                secteur.append(option).trigger('change');
                            }
                        }
                        else if(objet=="support"){
                            $('#support').empty();
                            var support = $('#support');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                support.append(option).trigger('change');
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                support.append(option).trigger('change');
                            }
                        }

                    }
                );
            }
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



