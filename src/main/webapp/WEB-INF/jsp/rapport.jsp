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
        <title>Accueil | G&eacute;n&eacute;rer rapport</title>
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

        <link href="<c:url value='/assets/plugins/datatables/css/datatables.min.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/datatables/css/datatables.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-2.0.1.css' />" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

        <script>

            var lienapp = "";

            function checkrapport(){

                var retour = true;
                var getChoix = parseInt($('#choixrapport').val());
                if(getChoix == 5){ // Support
                    // Check if the type of support is not empty :
                    var types = parseInt($('#types').val());
                    if(types == 0){
                        // Warning :
                        retour = false;
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Aucun type de support n'a été choisi !!</h3>";
                    }
                    else{
                        retour = true;
                    }
                }

                return retour;
            }

            function changeRapportSupport(){
                var getChoix = parseInt($('#choixrapport').val());
                if(getChoix == 5){
                    getItems(3,1,"choixrapport");
                }
            }

            function changeQuartier(){
                //alert("Test START");
                var codeCommune = $('#commune').val();
                getItems(0,codeCommune,"quartier");
                //$('#quartier').val(codeCommune);
                //$('#quartier').select2("refresh");
            }

            //
            function changeSecteur(){
                //alert("On y va !!!");
                var codeCommune = $('#quartier').val();
                getItems(1,codeCommune,"secteur");
            }

            //
            function changeSupport(){
                var codeSecteur = $('#secteur').val();
                getItems(2,codeSecteur,"types");
            }


            //
            function modifieraction(){
                var getChoix = parseInt($('#choixrapport').val());
                switch(getChoix){
                    case 2:
                        // Check if secteur has values :
                        var getSecteur = parseInt($('#secteur').val());
                        if(getSecteur==0){
                            $('#choixrapport').val(2); // Pour quartier :
                            $('#choixrapport').select2().trigger('change');
                        }
                        break;

                    case 1:
                        // Check if quartier has values :
                        var getQuartier = parseInt($('#quartier').val());
                        if(getQuartier==0){
                            $('#choixrapport').val(3); // Pour Commune :
                            $('#choixrapport').select2().trigger('change');
                        }
                        break;

                    default:
                        // nothing to do :
                        break;
                }
            }


            // To get all supports :
            function getItems(choix, id,objet){

                // Desactiver :
                //document.getElementById("valider").disabled = false;

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
                                if(j==(idLibelle.length-1)){
                                    quartier.append(option).trigger('change');
                                }
                                else quartier.append(option);
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                quartier.append(option).trigger('change');

                                // Change choix select :
                                $('#choixrapport').val(3); // Pour Commune :
                                //$('#choixrapport').select2().trigger('change');
                            }
                            else{
                                //
                                $('#choixrapport').val(2); // Pour quartier :
                                //$('#choixrapport').select2().trigger('change');
                            }
                            //    document.getElementById("valider").disabled = true;
                        }
                        else if(objet=="secteur"){
                            $('#secteur').empty();
                            //$('#secteur').select2('refresh');
                            var secteur = $('#secteur');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                if(j==(idLibelle.length-1)){
                                    secteur.append(option).trigger('change');
                                }
                                else secteur.append(option);
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                secteur.append(option).trigger('change');

                                // Change choix select :
                                var getquartier = parseInt($('#quartier').val());
                                if(getquartier==0){
                                    $('#choixrapport').val(3); // Pour ville :
                                    //$('#choixrapport').select2().trigger('change');
                                }
                                else{
                                    $('#choixrapport').val(2); // Pour quartier :
                                    //$('#choixrapport').select2().trigger('change');
                                }
                            }
                            else{
                                //
                                $('#choixrapport').val(6); // Pour secteur :
                                //$('#choixrapport').select2().trigger('change');
                            }
                            //    document.getElementById("valider").disabled = true;
                        }
                        else if(objet=="types"){
                            //$('#support').empty();
                            $('#types').empty();
                            //$('#types').select2('refresh');
                            var mTypes = $('#types');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                mTypes.append(option).trigger('change');
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                mTypes.append(option).trigger('change');
                            }
                        }
                        else if(objet=="choixrapport"){
                            $('#types').empty();
                            //$('#types').select2('refresh');
                            var mTypes = $('#types');
                            for (var j = 0; j < idLibelle.length; j++) {
                                var option = new Option(idLibelle[j], idTab[j], true, true);
                                mTypes.append(option).trigger('change');
                            }
                            // desactiver le button au cas ou il n'y aurait pas de valeurs :
                            /*if(idLibelle.length==0){
                                // Add a default one :
                                var option = new Option("--", 0, true, true);
                                support.append(option).trigger('change');
                            }*/
                        }

                        // choixrapport
                    }
                );
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
                                <span class="title">Action</span>
                                <span class="arrow "></span><span class="badge badge-accent">HOT</span>
                            </a>
                            <ul class="sub-menu" >
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
                                <h1 class="title">Rapport</h1>
                                <!-- PAGE HEADING TAG - END -->
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->




                    <!-- AJOUT DE NOUVEAUX CHAMPS -->
                    <form id="general_validate" method="POST" action="/gestpann/genrap"
                        onsubmit="return checkrapport();"
                    >

                        <div class="col-xl-12">
                              <section class="box ">
                                  <header class="panel_header">
                                      <h2 class="title float-left">S&eacute;lection du rapport</h2>
                                      <div class="actions panel_actions float-right">
                                          <a class="box_toggle fa fa-chevron-down"></a>
                                          <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                          <a class="box_close fa fa-times"></a>
                                      </div>
                                  </header>
                                  <div class="content-body">

                                      <div class="row">
                                          <div class="col-6">

                                              <div class="form-group">
                                                  <label class="form-label">Rapport</label>
                                                  <div class="controls">
                                                      <select class="select2" name="choixrapport" id="choixrapport"
                                                        onchange="changeRapportSupport();">
                                                         <!--<option value="1">Par Client</option>-->
                                                         <option value="6">Par Secteur</option>
                                                         <option value="2">Par Quartier</option>
                                                         <option value="3">Par Commune</option>
                                                         <!--<option value="4">Support en cours en cours d'utilisation</option>-->
                                                         <option value="5">Par Support</option>
                                                      </select>
                                                  </div>
                                              </div>

                                          </div>


                                          <div class="col-6">

                                              <div class="form-group">
                                                  <label class="form-label">Type de rapport(extension)</label>
                                                  <div class="controls">
                                                      <select class="select2" name="typerapport">
                                                         <option value="1">Fichier PDF</option>
                                                         <option value="2">Fichier EXCEL</option>
                                                      </select>
                                                  </div>
                                              </div>

                                          </div>
                                      </div>

                                  </div>
                              </section>
                        </div>
                        <div class="clearfix"></div>



                        <div class="col-xl-12">
                              <section class="box ">
                                  <header class="panel_header">
                                      <h2 class="title float-left">S&Eacute;lection des filtres</h2>
                                      <div class="actions panel_actions float-right">
                                          <a class="box_toggle fa fa-chevron-down"></a>
                                          <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                          <a class="box_close fa fa-times"></a>
                                      </div>
                                  </header>
                                  <div class="content-body">

                                          <div class="row">
                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Ville</label>
                                                      <div class="controls">
                                                          <select class="select2" name="ville" id="commune"
                                                            onchange="changeQuartier();" >
                                                              <c:forEach items="${listeVille}" var="ville" >
                                                                  <option value="${ ville.getIdvil() }">
                                                                    ${ ville.getLibelle() }
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
                                                          <select class="select2" name="quartier" id="quartier"
                                                             onchange="changeSecteur();">
                                                              <option value="0">--</option>
                                                          </select>
                                                      </div>
                                                  </div>
                                              </div>


                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Secteur</label>
                                                      <div class="controls">
                                                          <select class="select2" name="secteur" id="secteur"
                                                            onchange="changeSupport();">
                                                              <option value="0">--</option>
                                                          </select>
                                                      </div>
                                                  </div>
                                              </div>



                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">Type de support</label>
                                                      <div class="controls">
                                                          <select class="select2" name="types" id="types">
                                                              <option value="0">--</option>
                                                          </select>
                                                      </div>
                                                  </div>
                                              </div>

                                                <!--
                                              <div class="col-6">
                                                  <div class="form-group">
                                                      <label class="form-label">
                                                      Supports regroup&eacute;s par </label>
                                                      <div class="controls">
                                                          <select class="select2" name="regroupement"
                                                           id="regroupement" onchange="modificationsup()">
                                                             <option value="1">Secteur</option>
                                                             <option value="2">Quartier</option>
                                                             <option value="3">Commune</option>
                                                          </select>
                                                      </div>
                                                  </div>
                                              </div>
                                              -->

                                              <div class="col-12">
                                                  <div id="infos">
                                                  </div>
                                              </div>


                                              <div class="col-12">
                                                  <div class="float-right ">
                                                      <button type="submit" class="btn btn-success">G&eacute;n&eacute;rer</button>
                                                      <input type="button" class="btn btn-warning"
                                                        onclick="actualiser()"
                                                      value="Actualiser" />
                                                  </div>
                                              </div>

                                          </div>

                                  </div>
                              </section>
                        </div>
                        <div class="clearfix"></div>
                    </form>

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

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->
        <script src="<c:url value='/assets/plugins/jquery-ui/smoothness/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/datepicker/js/datepicker.js' />" type="text/javascript"></script>
        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>



        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('.select2').select2();

                // Call that :
                //modification();
                changeQuartier();
            });

            function actualiser(){
                //alert("OK");
                changeQuartier();
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

        <!-- Hidden elements -->
        <input type="hidden" id="lienapp" value="${ lienapp }" />
    </body>
</html>



