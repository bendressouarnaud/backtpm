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
        <title>GEstSUPP : Carte</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />



        <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon" />    <!-- Favicon -->
        <link rel="apple-touch-icon-precomposed" href="assets/images/apple-touch-icon-57-precomposed.png">	<!-- For iPhone -->
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/images/apple-touch-icon-114-precomposed.png">    <!-- For iPhone 4 Retina display -->
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/images/apple-touch-icon-72-precomposed.png">    <!-- For iPad -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/images/apple-touch-icon-144-precomposed.png">    <!-- For iPad Retina display -->




        <!-- CORE CSS FRAMEWORK - START -->
        <link href="assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- <link href="assets/plugins/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/> -->
        <link href="assets/fonts/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/plugins/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS FRAMEWORK - END -->

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - START -->


        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE CSS TEMPLATE - START -->
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/responsive.css" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

        <!--
		<link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">
        <script src="https://openlayers.org/en/v4.6.5/build/ol.js" type="text/javascript"></script>
            -->



    </head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
    <body class=" "><!-- START TOPBAR -->
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

            <div class="page-sidebar pagescroll">

                <!-- MAIN MENU - START -->
                <div class="page-sidebar-wrapper" id="main-menu-wrapper">

                    <!-- USER INFO - START -->
                    <div class="profile-info row">

                        <div class="profile-image col-4">
                            <a href="/gestpann/acc">
                                <img alt="" src="data/profile/user_images.png" class="img-fluid rounded-circle">
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
                            <a href="/gestpann/acc">
                                <i class="fa fa-dashboard"></i>
                                <span class="title">Principal</span>
                            </a>
                        </li>
                        <li class="">
                            <a href="/gestpann/choixafficherpanneaux">
                                <i class="fa fa-calendar"></i>
                                <span class="title">Retour</span>
                            </a>
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
                                <!-- PAGE HEADING TAG - START --><h1 class="title">Donn&eacute;es sur carte</h1><!-- PAGE HEADING TAG - END -->                            </div>


                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->



                    <div class="row margin-0">

                        <input type="hidden" id="choix"  value="${ choix }" />
                        <input type="hidden" id="valeur"  value="${ valeur }" />
                        <input type="hidden" id="typesupport"  value="${ typesupport }" />
                        <input type="hidden" id="lienapp" value="${ lienapp }" />

                        <div class="col-12 col-lg-6">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Informations suppl&eacute;mentaires</h2>
                                </header>
                                <div class="content-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <div >
                                                <p>${ information }</p>
                                                <p>Total supports : </p>
                                                <div id="infos"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>


                        <!--<div class="col-12 col-lg-6">-->
                        <div class="col-12">
                            <section class="box ">
                                <header class="panel_header">
                                    <h2 class="title float-left">Carte</h2>
                                </header>
                                <div class="content-body">        <div class="row">
                                        <div class="col-12">
                                            <div id="map" class="gmap"></div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>






                    </div>
                    <!--
                        You need to include this script on any page that has a Google Map.
                        When using Google Maps on your own site you MUST signup for your own API key at:
                            https://developers.google.com/maps/documentation/javascript/tutorial#api_key
                        After your sign up replace the key in the URL below or paste in the new script tag that Google provides.
                    -->

                    <!-- MAIN CONTENT AREA ENDS -->
                </section>
            </section>
            <!-- END CONTENT -->




        </div>
        <!-- END CONTAINER -->
        <!-- LOAD FILES AT PAGE END FOR FASTER LOADING -->


        <!-- CORE JS FRAMEWORK - START -->
        <script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="assets/js/popper.min.js" type="text/javascript"></script>
        <script src="assets/js/jquery.easing.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/plugins/pace/pace.min.js" type="text/javascript"></script>
        <script src="assets/plugins/perfect-scrollbar/perfect-scrollbar.min.js" type="text/javascript"></script>
        <script src="assets/plugins/viewport/viewportchecker.js" type="text/javascript"></script>
        <script>window.jQuery || document.write('<script src="assets/js/jquery-1.11.2.min.js"><\/script>');</script>
        <!-- CORE JS FRAMEWORK - END -->




        <!-- CORE TEMPLATE JS - START -->
        <script src="assets/js/scripts.js" type="text/javascript"></script>
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

<script src="http://www.openlayers.org/api/OpenLayers.js"></script>
<script type="text/javascript">

   var map;
   var mapLat = -33.829357;
   var mapLng = 150.961761;
   var mapDefaultZoom = 10;
   var images = "";

   var tabLongitude = new Array();
   var tabLatitude = new Array();
   var i=0;

    $(document).ready(function() {
        var choix = parseInt($('#choix').val());
        var typesupport = parseInt($('#typesupport').val());
        var valeur = $('#valeur').val();

        // Afficher :
        getsupports(choix, valeur, typesupport);
    });


    // To get all supports :
    function getsupports(choix, valeur, typesupport){

        var lienapp = $('#lienapp').val();
        $.get(lienapp + "gestpann/getsupportschoix/"+choix+"/"+valeur,
            function (data){
                $(data).find('item').each(function(){
                	var latit = $(this).find('latitude').text();
                	var longi = $(this).find('longitude').text();

                	// set :
                	tabLatitude[i] = parseFloat(latit);
                	tabLongitude[i] = parseFloat(longi);
                	i = i +1;
                });

                mapLat = parseFloat(tabLatitude[0]);
                mapLng = parseFloat(tabLongitude[0]);

                // Update the number of SUPPORTS :
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>"+
                    tabLongitude.length+"</h3>";

                // Display all points :
                var tmp1;
                map = new OpenLayers.Map("map");
                map.addLayer(new OpenLayers.Layer.OSM());
                for (var j = 0; j < tabLongitude.length; j++) {

                    var lonLat = new OpenLayers.LonLat( parseFloat(tabLongitude[j]) ,
                        parseFloat(tabLatitude[j]) )
                          .transform(
                            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
                            map.getProjectionObject() // to Spherical Mercator Projection
                          );

                    //var zoom=16;
                    if(j==0) tmp1 = lonLat;

                    var markers = new OpenLayers.Layer.Markers( "Markers" );
                    map.addLayer(markers);
                    markers.addMarker(new OpenLayers.Marker(lonLat));
                }

                if(tabLongitude.length > 0){
                    // Zoom in
                    var zoom=16;
                    map.setCenter (tmp1, zoom);
                }
                else alert("Il n'y a pas de supports !!!");
            }
        );
    }



</script>







<style type="text/css">
    /* Set a size for our map container, the Google Map will take up 100% of this container */
    .gmap {
        width: 100%;
        height: 800px;
    }
</style>
