<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!DOCTYPE html>
<html class=" ">
    <head>
        <!--
            Ajouté le 22/09/2019
        -->

        <!--   SECURITY TAG
        <meta http-equiv="Content-Security-Policy"
            content="default-src 'self' https://cdnjs.cloudflare.com/ajax/
            https://cdn.datatables.net/1.10.19/
            https://code.jquery.com/ https://cdn.datatables.net/1.10.19/
            https://fonts.googleapis.com/ http://34.68.214.253/gestpann/
            https://fonts.gstatic.com/ 'unsafe-inline' 'unsafe-eval';
            img-src 'self' data: blob: "/>
            -->

        <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
        <meta charset="utf-8" />
        <title>GestPAN - Tableau de bord</title>
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
        <link href="<c:url value='/assets/plugins/jvectormap/jquery-jvectormap-2.0.1.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/icheck/skins/minimal/white.css' />" rel="stylesheet" type="text/css" media="screen"/>

        <!-- HEADER SCRIPTS INCLUDED ON THIS PAGE - END -->


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>
        <!-- CORE CSS TEMPLATE - END -->

        <!-- CHART js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>

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
                <div>
                    <div class='float-right'
                        style='background-repeat: no-repeat; height: 60px;'>

                        <img src="<c:url value='/assets/images/logoMTN.jpeg' />"
                        alt="user-image" >
                    </div>

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
                                <h1 class="title">Tableau de BORD</h1>
                                <!-- PAGE HEADING TAG - END -->
                            </div>

                            <div class="float-right d-none">
                                <ol class="breadcrumb">
                                    <li>
                                        <a href="index.html"><i class="fa fa-home"></i>Home</a>
                                    </li>
                                    <li>
                                        <a href="charts-echart-line.html">Echarts</a>
                                    </li>
                                    <li class="active">
                                        <strong>Bar & Stacked Charts</strong>
                                    </li>
                                </ol>
                            </div>

                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- MAIN CONTENT AREA STARTS -->








                    <!--  -->
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="mt-0 header-title">Les COMMUNES contenant le plus de supports</h4>
                                    <!--<div class="ct-chart ct-golden-section">-->
                                    <div class="card-body">
                                        <canvas id="commune_support" height="100" class="mypiechart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="row">

                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="mt-0 header-title">les TYPES de supports les plus repr&eacute;sent&eacute;s au niveau national</h4>
                                    <!--<div class="ct-chart ct-golden-section">-->
                                    <div class="card-body">
                                        <canvas id="total_support" height="100" class="mypiechart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="row">

                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="mt-0 header-title">Representation des 5 plus nombreux supports dans les 5 principales villes</h4>
                                    <!--<div class="ct-chart ct-golden-section">-->
                                    <div class="card-body">
                                        <canvas id="canvascinq" height="100" class="mypiechart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>



                    <!-- MAIN CONTENT AREA ENDS -->
                </section>
            </section>
            <!-- END CONTENT -->




            </div>
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

        <script src="<c:url value='/assets/plugins/echarts/echarts-all.js' />" type="text/javascript"></script>
        <!--<script src="<c:url value='/assets/js/chart-echarts.js' />" type="text/javascript"></script>-->
        <!-- OTHER SCRIPTS INCLUDED ON THIS PAGE - END -->



        <!-- CORE TEMPLATE JS - START -->
        <script src="<c:url value='/assets/js/scripts.js' />" type="text/javascript"></script>
        <script type="text/javascript">

            var colorIdsBankSet = [];
            var lienapp = "";

            $(document).ready(function(){

                // Pour les COMMUNES contenant le plus de supports
                var communesTAb = new Array();
                var communesTAbNombre = new Array();
                //
                var typesTAb = new Array();
                var typesTAbNombre = new Array();
                lienapp = $('#lienapp').val();
                //

                //
                window.chartColors = {
                    red: 'rgb(255, 99, 132)',
                    orange: 'rgb(255, 159, 64)',
                    yellow: 'rgb(255, 205, 86)',
                    green: 'rgb(255, 255, 0)',
                    greendark: 'rgb(0, 145, 90)',
                    blue: 'rgb(0, 124, 177)',
                    bluelight: 'rgb(100, 204, 249)',
                    purple: 'rgb(153, 102, 255)',
                    grey: 'rgb(133, 133, 133)',
                    brown: 'rgb(155, 93, 73)'
                };


                $.get(lienapp + "gestpann/getcommuneplusupport",
					function(data) {

					    var i=0;
						$(data).find('item').each(function(){
                            //
                            var libelle = $(this).find('libelle').text();
                   		    var total = parseInt($(this).find('total').text());

                   		    //
                   		    communesTAb[i] = libelle;
                   		    communesTAbNombre[i] = total;
                   		    i = i + 1;
						});

                        //
                        if(communesTAb.length > 0) {
                            var couleur = Chart.helpers.color;
                            var barChartData = {
                                labels: communesTAb,
                                datasets: [{
                                    label: 'Supports',
                                    backgroundColor: couleur(window.chartColors.green).alpha(0.5).rgbString(),
                                    borderColor: window.chartColors.green,
                                    borderWidth: 1,
                                    data: communesTAbNombre
                                }]
                            };

                            //
                            var ctx = document.getElementById('commune_support').getContext('2d');
                            new Chart(ctx, {
                                type: 'bar',
                                data: barChartData,
                                options: {
                                    responsive: true,
                                    scales: {
                                        yAxes: [{
                                            display: true,
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                    },
                                    legend: {
                                        position: 'top'
                                    },
                                    title: {
                                        display: false,
                                        text: 'Bar'
                                    },
                                    plugins: {
                                        datalabels: {
                                            display: false
                                        }
                                    }
                                }
                            });
                        }

					}
                );






                //  TYPES REPRESENTES
                $.get(lienapp + "gestpann/gettypeplusupport",
					function(data) {

					    var j=0;
						$(data).find('item').each(function(){
                            var libelle = $(this).find('libelle').text();
                   		    var total = parseInt($(this).find('total').text());

                   		    //alert("libellé : "+libelle);

                   		    //
                   		    typesTAb[j] = libelle;
                   		    typesTAbNombre[j] = total;
                   		    j = j + 1;
						});


                        //
                        if(typesTAb.length > 0) {
                            var couleur = Chart.helpers.color;
                            var barChartData = {
                                labels: typesTAb,
                                datasets: [{
                                    label: 'Quantite',
                                    backgroundColor: couleur(window.chartColors.blue).alpha(0.7).rgbString(),
                                    borderColor: window.chartColors.blue,
                                    borderWidth: 1,
                                    data: typesTAbNombre
                                }]
                            };

                            //
                            var ctx = document.getElementById('total_support').getContext('2d');
                            new Chart(ctx, {
                                type: 'bar',
                                data: barChartData,
                                options: {
                                    responsive: true,
                                    scales: {
                                        yAxes: [{
                                            display: true,
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                    },
                                    legend: {
                                        position: 'top'
                                    },
                                    title: {
                                        display: false,
                                        text: 'Bar'
                                    },
                                    plugins: {
                                        datalabels: {
                                            display: false
                                        }
                                    }
                                }
                            });
                        }
					}
                );


                //  TYPES REPRESENTES
                $.get(lienapp + "gestpann/getbiggestcitiesforsup",
                    function(data) {

                        var gg=0;
                        var tabDonnee = [];
                        var tabDonneecp = [];
                        var tabIdvil = [];
                        var tabIdsupport = [];
                        var tabLibsupport = [];

                        $(data).find('item').each(function(){

                            var tampon = {};
                            tampon.ville = $(this).find('ville').text();
                            tampon.typesupport = $(this).find('typesupport').text();
                            tampon.idsupport = parseInt($(this).find('idsupport').text());
                            tampon.quantite = parseInt($(this).find('quantite').text());
                            tampon.idvil = parseInt($(this).find('idvil').text());
                            /*if(tabIdvil.indexOf(tampon.idvil == -1)){
                                // No, so add id :
                                tabIdvil.push(tampon.idvil);

                                var ensemble = {}; //parseInt($(this).find('ideta').text());
                                ensemble.id = tampon.idvil;
                                ensemble.couleur = gg+1;
                                //
                                colorIdsBankSet[gg] = ensemble;
                                gg++;
                            }
                            */


                            var mExisteVil = false;
                            for(var z=0; z < tabIdvil.length ; z++) {
                                if (tabIdvil[z] == tampon.idvil){
                                    mExisteVil = true;
                                    break;
                                }
                            }
                            if(!mExisteVil) {
                                // No, so add id :
                                tabIdvil.push(tampon.idvil);
                                var ensemble = {}; //parseInt($(this).find('ideta').text());
                                ensemble.id = tampon.idvil;
                                ensemble.couleur = gg+1;
                                //
                                colorIdsBankSet[gg] = ensemble;
                                gg++;
                            }



                            var mExiste = false;
                            for(var k=0; k < tabIdsupport.length ; k++) {
                                if (tabIdsupport[k] == tampon.idsupport){
                                    mExiste = true;
                                    break;
                                }
                            }
                            if(!mExiste) {
                                // No, so add id :
                                tabIdsupport.push(tampon.idsupport);
                                tabLibsupport.push(tampon.typesupport);
                            }

                            /*var existe = false;
                            if(tabIdsupport.indexOf(parseInt($(this).find('idsupport').text()) == -1)){
                                // No, so add id :
                                tabIdsupport.push(tampon.idsupport);
                                tabLibsupport.push(tampon.typesupport);
                            }
                            */
                            // Push :
                            tabDonnee.push(tampon);
                        });

                        //console.log("Taille ID support : "+tabIdsupport.length);

                        // Fill the ARRAY with '0' :
                        var existe = false;
                        var setVille = "";
                        var setSupport = "";
                        for(var k=0; k < tabIdsupport.length ; k++){
                            for(var j=0; j < tabIdvil.length ; j++){
                                existe = false;
                                setVille = "";
                                setSupport = "";
                                for(var l=0; l < tabDonnee.length ; l++){
                                    if(tabDonnee[l].idvil == tabIdvil[j]){
                                        // Set it :
                                        setVille = tabDonnee[l].ville;
                                        setSupport = tabDonnee[l].typesupport;
                                        if(tabIdsupport[k] == tabDonnee[l].idsupport){
                                            existe = true;
                                            break;
                                        }
                                    }
                                }
                                // False ? Yes :
                                if(!existe){
                                    // Set it :
                                    var tampon = {};
                                    tampon.ville = setVille;
                                    tampon.typesupport = setSupport;
                                    tampon.idsupport = tabIdsupport[k];
                                    tampon.quantite = 0;
                                    tampon.idvil = tabIdvil[j];
                                    tabDonneecp.push(tampon);
                                }
                            }
                        }

                        // Refresh :
                        for(var l=0; l < tabDonneecp.length ; l++){
                            tabDonnee.push(tabDonneecp[l]);
                        }


                        //
                        if(tabDonnee.length > 0) {
                            // Organize
                            var tabDataset = [];
                            for(var k=0; k < tabIdvil.length ; k++){
                                var tampons = {};
                                tampons.tab = [];
                                tampons.ville = "";
                                tampons.idvil = 0;

                                for(var j=0; j < tabIdsupport.length ; j++){
                                    for(var l=0; l < tabDonnee.length ; l++){
                                        if((tabIdvil[k]==tabDonnee[l].idvil) && (tabIdsupport[j]==tabDonnee[l].idsupport)){

                                            // Set Data :
                                            tampons.ville = tabDonnee[l].ville;
                                            tampons.tab.push(tabDonnee[l].quantite);
                                            tampons.idvil = tabDonnee[l].idvil;
                                            break;
                                        }
                                    }
                                }

                                // Keep DATA :
                                tabDataset.push(tampons);
                            }


                            // Now, create a table to hold data :
                            var tableHold = [];
                            var color = Chart.helpers.color;

                            //
                            for(var j=0; j < tabDataset.length ; j++){
                                tableHold.push(
                                    {
                                        label : tabDataset[j].ville,
                                        backgroundColor: color(traiterColorBank(tabDataset[j].idvil)).alpha(0.6).rgbString(),
                                        borderColor: traiterColorBank(tabDataset[j].idvil),
                                        data: tabDataset[j].tab
                                    });
                            }

                            //
                            var barChartData = {
                                labels: tabLibsupport,
                                datasets: tableHold
                            };

                            // SETUP :
                            var ctx = document.getElementById('canvascinq').getContext('2d');
                            window.myBar = new Chart(ctx, {
                                type: 'bar',
                                data: barChartData,
                                options: {
                                    responsive: true,
                                    scales: {
                                        yAxes: [{
                                            display: true,
                                            scaleLabel: {
                                                display: true,
                                                labelString: 'Nombre total de supports'
                                            },
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                    },
                                    legend: {
                                        position: 'top',
                                    },
                                    title: {
                                        display: true,
                                        text: 'Repartition des supports (2019)'
                                    }
                                }
                            });

                        }
                    }
                );

            });


            function traiterColorBank(idvil){
                for(var z=0; z < colorIdsBankSet.length ; z++){
                    if(colorIdsBankSet[z].id==idvil){

                        //console.log("Ideta : "+ideta);

                        if(colorIdsBankSet[z].couleur==1) return window.chartColors.blue;
                        else if(colorIdsBankSet[z].couleur==2) return window.chartColors.brown;
                        else if(colorIdsBankSet[z].couleur==3) return window.chartColors.grey;
                        else if(colorIdsBankSet[z].couleur==4) return window.chartColors.green;
                        else if(colorIdsBankSet[z].couleur==5) return window.chartColors.orange;
                        else if(colorIdsBankSet[z].couleur==6) return window.chartColors.bluelight;
                        else if(colorIdsBankSet[z].couleur==7) return window.chartColors.greendark;
                        else if(colorIdsBankSet[z].couleur==8) return window.chartColors.red;
                        else if(colorIdsBankSet[z].couleur==9) return window.chartColors.purple;
                        else return window.chartColors.blue;
                    }
                }
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



