<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html class=" ">
    <head>
        <!--
        <meta name="_csrf" content="${_csrf.token}"/>
         default header name is X-CSRF-TOKEN
        <meta name="_csrf_header" content="${_csrf.headerName}"/>-->

        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8" />
        <title>Gestion | choix</title>
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

        <script>

            <!-- Variable -->
            var lienapp = "";

            function checkplusieurscommunes(){

                var tamponTexte = $("#communemul option:selected").text();
                var retour = false;

                if(tamponTexte.length==0){
                    var info = document.getElementById("infos");
                    info.innerHTML = "<h3 style='color:red;'>Veuillez sélectionner par défaut au moins une ville pour le choix multiple !!!</h3>";
                    retour = false;
                }
                else{
                    retour = true;
                }

                return retour;
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
                                $('#choix').val(0); // Pour ville :
                                //$('#choix').select2().trigger('change');
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
                                    $('#choix').val(0); // Pour ville :
                                    //$('#choix').select2().trigger('change');
                                }
                                else{
                                    $('#choix').val(1); // Pour quartier :
                                    //$('#choix').select2().trigger('change');
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
                                    <a class="" href="/gestpann/requetepan"  >Actualiser</a>
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
                                <h1 class="title">Choix des filtres</h1>
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
                    <div class="col-xl-12">
                        <section class="box ">
                              <header class="panel_header">
                                  <h2 class="title float-left">Filtres</h2>
                                  <div class="actions panel_actions float-right">
                                      <a class="box_toggle fa fa-chevron-down"></a>
                                      <a class="box_setting fa fa-cog" data-toggle="modal" href="#section-settings"></a>
                                      <a class="box_close fa fa-times"></a>
                                  </div>
                              </header>
                              <div class="content-body">
                                  <form id="general_validate" method="POST" action="/gestpann/soumettrechoix"
                                    onsubmit="return checkplusieurscommunes();">
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
                                                  <label class="form-label">Commune - choix multiple pour la carte -</label>
                                                  <div class="controls">
                                                      <select id="communemul" class="select2" name="communemul" multiple>
                                                        <c:set var ="cptr" value = "${0}"/>
                                                          <c:forEach items="${listCommune}" var="commune" >
                                                              <option value="${ commune.getIdvil() }" ${ cptr==0 ? "selected" : "" } >
                                                                ${ commune.getLibelle() }
                                                              </option>
                                                              <c:set var="cptr" value="${cptr + 1}" />
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

                                          <div class="col-6">
                                             <div class="form-group">
                                                 <label class="form-label">Type de support</label>
                                                 <div class="controls">
                                                      <select class="select2" name="typesupport"
                                                        id="typesupport" >
                                                          <option value="0">Tous les types</option>
                                                          <c:forEach items="${listTypes}" var="typess" >
                                                              <option value="${ typess.getIdtyp() }">
                                                                ${ typess.getLibelle() }
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
                                                  <button type="submit" value="2" name="affichage" id="affichage" class="btn btn-primary">Affichage multiple</button>
                                                  <button type="submit" value="1" name="affichage" id="valider" class="btn btn-warning">Afficher pleine carte</button>
                                                  <button type="submit" value="0" name="affichage" id="validerinfo" class="btn btn-success">Afficher carte avec Info</button>
                                              </div>
                                          </div>
                                      </div>
                                  </form>
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

        <script src="<c:url value='/assets/plugins/datatables/js/dataTables.min.js' />" type="text/javascript"></script> <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js' />">
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

                //
                changeQuartier();
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

        <!-- Hidden elements -->
        <input type="hidden" id="lienapp" value="${ lienapp }" />
    </body>
</html>



