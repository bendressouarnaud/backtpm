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
        <title>Gestion | COMMUNE</title>
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
                                <img alt="" src="<c:url value='/data/profile/user_images.png'/>" class="img-fluid rounded-circle">
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
                                    <a class="" href="/gestpann/nouvelville">Nouvelle Commune</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/searchcommune">Recherche</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/ville">Actualiser</a>
                                </li>
                                <li>
                                    <a class="" href="/gestpann/acc">Retour accueil</a>
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
                                <h1 class="title">Gestion COMMUNE</h1>
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


                    <!-- We add these fields to make FILTERS -->
                    <c:if test="${!empty recherche}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Filtres</h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                    </div>
                                </header>
                                  <div class="content-body">
                                      <form id="general_validate" method="POST" action="/gestpann/choixcommunefiltre">
                                          <div class="row">
                                              <div class="col-6">

                                                  <div class="form-group">
                                                      <label class="form-label">Commune</label>
                                                      <div class="controls">
                                                          <select id="commune" class="select2" name="commune" onchange="changeQuartier();">
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
                                                          <select id="quartier" class="select2" name="quartier" onchange="changeSecteur();">
                                                              <option value="0">--</option>
                                                          </select>
                                                     </div>
                                                 </div>

                                              </div>

                                              <div class="col-6">

                                                 <div class="form-group">
                                                     <label class="form-label">Secteur</label>
                                                     <div class="controls">
                                                          <select class="select2" name="secteur" id="secteur">
                                                              <option value="0">--</option>
                                                          </select>
                                                     </div>
                                                 </div>

                                              </div>


                                              <div class="col-6">
                                                 <div class="form-group">
                                                     <label class="form-label">Choix du rapport</label>
                                                     <div class="controls">
                                                          <select class="select2" name="choix"
                                                            id="choix" onchange="modifieraction()">
                                                             <option value="0">Commune</option>
                                                             <option value="1">Quartier</option>
                                                             <option value="2">Secteur</option>
                                                          </select>
                                                     </div>
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
                    </c:if>

                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->


                    <!-- AJOUT DE NOUVEAUX CHAMPS -->
                    <c:if test="${!empty nouvelville || !empty ville}">
                        <div class="col-xl-12">
                              <section class="box ">
                                  <header class="panel_header">
                                      <h2 class="title float-left">AJOUT NOUVELLE COMMUNE</h2>
                                      <div class="actions panel_actions float-right">
                                          <a class="box_toggle fa fa-chevron-down"></a>
                                          <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                          <a class="box_close fa fa-times"></a>
                                      </div>
                                  </header>
                                  <div class="content-body">
                                      <form id="general_validate" method="POST"
                                        action="${(!empty ville) ? '/gestpann/modifierville' : '/gestpann/creerville' }"
                                        onsubmit="return activateFieldId();">
                                          <div class="row">

                                              <div class="col-6">

                                                  <div class="form-group">
                                                      <label class="form-label">Id</label>
                                                          <div class="controls">
                                                          <input disabled type="text" class="form-control" name="id"
                                                            value="${(!empty ville) ? ville.getIdvil() : '' }" id="id" >
                                                      </div>
                                                  </div>

                                                  <div class="form-group">
                                                      <label class="form-label">Libell&eacute;</label>
                                                      <div class="controls">
                                                          <input type="text" class="form-control" name="libelle"
                                                           value="${(!empty ville) ? ville.getLibelle() : '' }"
                                                           >
                                                      </div>
                                                  </div>
                                              </div>



                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Population</label>
                                                      <div class="controls">
                                                          <input type="text" class="form-control" name="population"
                                                           value="${(!empty ville) ? ville.getPopulation() : '' }"
                                                           >
                                                      </div>
                                                  </div>
                                              </div>


                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Taux (montant)</label>
                                                      <div class="controls">
                                                          <input type="text" class="form-control" name="taux"
                                                           value="${(!empty ville) ? ville.getTaux() : '' }"
                                                           >
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





                    <c:if test="${!empty listVille}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Liste des Communes</h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                        <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                        <a class="box_close fa fa-times"></a>
                                    </div>
                                </header>
                                <div class="content-body">    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-12 padding-0">

                                            <table id="example-1" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Population</th>
                                                        <th>Taux</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Population</th>
                                                        <th>Taux</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${listVille}" var="ville" >
                                                        <tr>
                                                            <td>${ ville.getLibelle() }</td>
                                                            <td>${ ville.getPopulation() }</td>
                                                            <td>${ ville.getTaux() }</td>
                                                            <td><a class="box_toggle fa fa-wrench"
                                                                href="/gestpann/modifville/${ ville.getIdvil() }"></a></td>
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


                    <!-- Afficher les INFOS de la COMMUNE -->
                    <c:if test="${!empty choixville}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Information relative &agrave; la commune</h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                        <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                        <a class="box_close fa fa-times"></a>
                                    </div>
                                </header>
                                <div class="content-body">    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-12 padding-0">

                                            <table id="example-1" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Population</th>
                                                        <th>Taux</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Population</th>
                                                        <th>Taux</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${listVille}" var="ville" >
                                                        <tr>
                                                            <td>${ ville.getLibelle() }</td>
                                                            <td>${ ville.getPopulation() }</td>
                                                            <td>${ ville.getTaux() }</td>
                                                            <td><a class="box_toggle fa fa-wrench"
                                                                href="/gestpann/modifville/${ ville.getIdvil() }"></a></td>
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



                    <!-- Afficher les INFOS du QUARTIER -->
                    <c:if test="${!empty showville}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">
                                        <span style="color:red;">Quartier(s)</span> li&eacute;s &agrave; la commune
                                            <span style="color:red;">${showville.getLibelle()}</span></h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                        <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                        <a class="box_close fa fa-times"></a>
                                    </div>
                                </header>
                                <div class="content-body">    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-12 padding-0">

                                            <table id="example-1" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Libell&eacute;</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${listQuartier}" var="quartier" >
                                                        <tr>
                                                            <td>${ quartier.getLibelle() }</td>
                                                            <td><a class="box_toggle fa fa-wrench"
                                                                href="/gestpann/modifquartier/${ quartier.getIdqua() }"></a></td>
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



                    <!-- Afficher les INFOS du QUARTIER -->
                    <c:if test="${!empty villesecteur}">
                        <div class="col-xl-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">
                                        <span style="color:red;">Secteur(s)</span> li&eacute;s &agrave; la commune
                                            <span style="color:red;">${villesecteur.getLibelle()}</span></h2>
                                    <div class="actions panel_actions float-right">
                                        <a class="box_toggle fa fa-chevron-down"></a>
                                        <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                        <a class="box_close fa fa-times"></a>
                                    </div>
                                </header>
                                <div class="content-body">    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-12 padding-0">

                                            <table id="example-1" class="table table-striped dt-responsive display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Quartier</th>
                                                        <th>Secteur</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Quartier</th>
                                                        <th>Secteur</th>
                                                        <th>Modifier</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${listesecteurquartier}" var="resultat" >
                                                        <tr>
                                                            <td>${ resultat[0] }</td>
                                                            <td>${ resultat[1] }</td>
                                                            <td><a class="box_toggle fa fa-wrench"
                                                                href="/gestpann/modifsecteur/${ resultat[2] }"></a></td>
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

            var lienapp = "";

            $(document).ready(function(){
                $('.select2').select2();
                $('#example-1').DataTable();

                // Call to initialise :
                changeQuartier();
            });




            function changeQuartier(){
                //alert("Test START");
                var codeCommune = $('#commune').val();
                getItems(0,codeCommune,"quartier");
            }

            //
            function changeSecteur(){
                var codeCommune = $('#quartier').val();
                getItems(1,codeCommune,"secteur");
            }


            //
            function modifieraction(){
                var getChoix = parseInt($('#choix').val());
                switch(getChoix){
                    case 2:
                        // Check if secteur has values :
                        var getSecteur = parseInt($('#secteur').val());
                        if(getSecteur==0){
                            $('#choix').val(1); // Pour quartier :
                            $('#choix').select2().trigger('change');
                        }
                        break;

                    case 1:
                        // Check if quartier has values :
                        var getQuartier = parseInt($('#quartier').val());
                        if(getQuartier==0){
                            $('#choix').val(0); // Pour quartier :
                            $('#choix').select2().trigger('change');
                        }
                        break;

                    default:
                        // nothing to do :
                        break;
                }
            }


            // To get all supports :
            function getItems(choix, id,objet){

                var idTab = new Array();
                var idLibelle = new Array();
                var i=0;
                lienapp = $('#lienapp').val();

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

                                // Change choix select :
                                $('#choix').val(0); // Pour ville :
                                $('#choix').select2().trigger('change');
                            }
                            else{
                                //
                                $('#choix').val(1); // Pour quartier :
                                $('#choix').select2().trigger('change');
                            }
                            //    document.getElementById("valider").disabled = true;
                        }
                        else{
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

                                // Change choix select :
                                var getquartier = parseInt($('#quartier').val());
                                if(getquartier==0){
                                    $('#choix').val(0); // Pour ville :
                                    $('#choix').select2().trigger('change');
                                }
                                else{
                                    $('#choix').val(1); // Pour quartier :
                                    $('#choix').select2().trigger('change');
                                }
                            }
                            else{
                                //
                                $('#choix').val(2); // Pour quartier :
                                $('#choix').select2().trigger('change');
                            }
                            //    document.getElementById("valider").disabled = true;
                        }

                        //mapLat = parseFloat(tabLatitude[0]);
                        //mapLng = parseFloat(tabLongitude[0]);
                        //initialize_map();
                        //// Display all points :
                        //for (var j = 0; j < tabLongitude.length; j++) {
                        //    add_map_point(tabLatitude[j], tabLongitude[j]);
                        //}
                    }
                );
            }


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

        <!-- Hidden elements -->
        <input type="hidden" id="lienapp" value="${ lienapp }" />

    </body>
</html>



