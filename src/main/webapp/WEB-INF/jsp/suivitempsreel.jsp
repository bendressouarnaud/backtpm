<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <title>Positionnement | Tous les supports</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!--<link rel="stylesheet"
            href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">-->

        <!-- CORE CSS FRAMEWORK - START -->
        <link href="<c:url value='/assets/plugins/pace/pace-theme-flash.css' />" rel="stylesheet" type="text/css" media="screen"/>
        <link href="<c:url value='/assets/plugins/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/fonts/font-awesome/css/font-awesome.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/animate.min.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/plugins/perfect-scrollbar/perfect-scrollbar.css' />" rel="stylesheet" type="text/css"/>


        <!-- CORE CSS TEMPLATE - START -->
        <link href="<c:url value='/assets/css/style.css' />" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/assets/css/responsive.css' />" rel="stylesheet" type="text/css"/>

        <link href="<c:url value='/assets/plugins/select2/select2.css' />" rel="stylesheet" type="text/css" media="screen"/>

    </head>

    <body >
        <div>

            <!-- modal start -->
            <div class="modal fade" id="ultraModal-1" tabindex="-1" role="dialog" aria-labelledby="ultraModal-Label" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">

                            <h4 class="modal-title">Information</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body" id="contenu-image">
                            Patientez svp ...
                        </div>
                        <!--
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
                            <button class="btn btn-success" type="button">Save changes</button>
                        </div>
                        -->
                    </div>
                </div>
            </div>


            <!-- modal start -->
            <div class="modal fade" id="ultraModal-7"  tabindex="-1" role="dialog" aria-labelledby="ultraModal-Label" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Choisir un utilisateur</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <!--<h4 class="modal-title">Mise &agrave; jour </h4>-->
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-6">

                                    <div class="form-group">
                                        <label class="form-label">Utilisateur</label>
                                        <div class="controls"> <!-- class="select2" -->

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <input type="button" class="btn btn-primary"
                                            value="Soumettre" onclick="soumettre()">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div id="map" style="width: 100vw; height: 100vh;">
            </div>

        </div>
        <!--
        <input type="hidden" id="choix"  value="${ choix }" />
        <input type="hidden" id="valeur"  value="${ valeur }" />
        <input type="hidden" id="typesupport"  value="${ typesupport }" />
        -->


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


        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>


        <!--<script src="https://openlayers.org/en/v4.6.5/build/ol.js"
            type="text/javascript"></script>-->
        <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtJHXa9mv08ooUymhgm5Ty3h960s-_gQ0&sensor=false"></script>
        <!--<script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>-->
        <script>
            var map;
            var mapLat = 5.38011114;
            var mapLng = -3.99335701;
            var mapDefaultZoom = 10;
            var images = "";

            var tabLongitude = new Array();
            var tabLatitude = new Array();
            var tabLibelle = new Array();
            var tabIdpan = new Array();
            var tabIdpanHold = new Array();
            var tabEtat = new Array();

            // Marker Array :
            var tabMarkers = new Array();
            var tabMarkersAlerte = new Array();

            var i=0;

            var choix = 0;
            var typesupport = 0;
            var valeur = "";

            // Hold PANNEAU ID :
            var getPanneauId = 0;
            var tickVariable = 0;
            var intervalVar;
            var intervalVarBlink;
            var compteur = 0;

            var switchLight = 0;

            // carte :
            var map;
            var infowindow = new google.maps.InfoWindow();


            $( document ).ready(function() {

                // Initialize :
                document.addEventListener('keydown', logKey);
                //$('.select2').select2();

                // get the first users :
                getUsers();
            });


            function getUsers(){

                // Clean the MAP :
                compteur = compteur + 1;
                // alert("compteur : "+compteur);
                if(tickVariable == 1) removeMarkers();

                //
                //$.get("http://localhost:8080/gestpann/getusersrealtime",
                $.get("http://www.gestdp.com/gestpann/getusersrealtime",
                    function (data){
                        $(data).find('item').each(function(){
                            var latit = $(this).find('latitude').text();
                            var longi = $(this).find('longitude').text();
                            var nom = $(this).find('nom').text();
                            var etat = parseInt($(this).find('etat').text());

                            // set :
                            tabLatitude[i] = parseFloat(latit);
                            tabLongitude[i] = parseFloat(longi);
                            tabLibelle[i] = nom;
                            tabEtat[i] = etat;
                            i++;
                        });

                        if(tabLongitude.length == 0){
                            // Initialiser :
                            tabLatitude[0] = mapLat;
                            tabLongitude[0] = mapLng;
                            tabEtat[0] = 0;
                            tabLibelle[0] = "1";
                        }

                        //
                        if(tickVariable == 0) {
                            map = new google.maps.Map(document.getElementById('map'), {
                              zoom: 10,
                              center: new google.maps.LatLng(tabLatitude[0], tabLongitude[0]),
                              mapTypeId: google.maps.MapTypeId.ROADMAP
                            });
                        }

                        //var infowindow = new google.maps.InfoWindow();

                        // Display all points :
                        var marker;
                        for (var j = 0; j < tabLongitude.length; j++) {

                            var couleurMarker = "";
                            if(tabEtat[j] == 0) couleurMarker = "http://maps.google.com/mapfiles/ms/icons/blue-dot.png";
                            else couleurMarker = "http://maps.google.com/mapfiles/ms/icons/orange.png";

                            marker = new google.maps.Marker({
                                position: new google.maps.LatLng(tabLatitude[j], tabLongitude[j]),
                                map: map,
                                icon: {
                                  url: couleurMarker
                                }
                            });

                            // Add the marker by checking its ETAT :
                            var tampon = {};
                            tampon.latitude = tabLatitude[j];
                            tampon.longitude = tabLongitude[j];
                            tampon.nom = tabLibelle[j];
                            tampon.marker = marker;
                            tampon.blink = 0;
                            if(tabEtat[j] == 1){

                                // Delete if the ELEMENT is already THERE :
                                var existe_pas = 0;
                                for(var b=0; b<tabMarkersAlerte.length; b++){
                                    if(tabMarkersAlerte[b].nom == tampon.nom){
                                        // existe :
                                        existe_pas = 1;
                                    }
                                }

                                //
                                if(existe_pas == 0){
                                    tabMarkersAlerte.push(tampon);
                                    //console.log("Une alerte ajouté ");
                                }

                            }
                            else tabMarkers.push(marker);

                            if(tabEtat[j] == 0){
                                // Position des AGENTS :
                                google.maps.event.addListener(marker, 'click', (function(marker, j) {
                                  return function() {
                                    infowindow.setContent("Identifiant : "+tabLibelle[j]);
                                    infowindow.open(map, marker);
                                    //
                                    $('#ultraModal-1').modal('toggle');
                                    // Afficher :
                                    document.getElementById("contenu-image").innerHTML =
                                        "<p>Veuillez patienter ...</p>";
                                    displayAgentInfo(tabLibelle[j]);
                                  }
                                })(marker, j));
                            }
                            else{
                                // Position des ALARMES :
                                google.maps.event.addListener(marker, 'click', (function(marker, j) {
                                  return function() {
                                    infowindow.setContent("Identifiant : "+tabLibelle[j]);
                                    infowindow.open(map, marker);
                                    //
                                    $('#ultraModal-1').modal('toggle');
                                    // Afficher :
                                    document.getElementById("contenu-image").innerHTML =
                                        "<p>Veuillez patienter ...</p>";
                                    displayImage(tabLibelle[j]);
                                  }
                                })(marker, j));
                            }
                        }

                        // Activate the LOOP :
                        if(tickVariable == 0) {
                            tickVariable = 1;
                            intervalVar = setInterval(getUsers, 10000);

                            //
                            intervalVarBlink = setInterval(blinkMarkers, 1000);
                        }

                    }
                );
            }


            function displayImage(idpan){

                var imagetexte = "";
                var emplacement = "";
                var dateheure = "";
                var secteurs = "";
                var quartiers = "";
                var villes = "";
                var users = "";

                //getPanneauId = idpan;

                //$.get("http://localhost:8080/gestpann/getsupportbyid?idpan="+idpan,
                $.get("http://www.gestdp.com/gestpann/getsupportbyid?idpan="+idpan,
                    function (data){
                        $(data).find('item').each(function(){
                            imagetexte = $(this).find('imagetexte').text();
                            dateheure = $(this).find('dateheure').text();
                            secteurs = $(this).find('secteurs').text();
                            quartiers = $(this).find('quartiers').text();
                            villes = $(this).find('villes').text();
                            users = $(this).find('users').text();
                        });

                        // Afficher :
                        document.getElementById("contenu-image").innerHTML =
                            "<img class='img-fluid' src='data:image/jpeg;base64,"+imagetexte+"'><br>"+
                            "<div>Date : "+dateheure+"</div>"+
                            "<div>Secteur : "+secteurs+"</div>"+
                            "<div>Quartier : "+quartiers+"</div>"+
                            "<div>Ville : "+villes+"</div>"+
                            "<div>Agent : "+users+"</div><br>";
                    }
                );
            }



            function displayAgentInfo(idusr){

                var nom = "";
                var email = "";
                var numero = "";

                //
                getPanneauId = idusr;

                //$.get("http://localhost:8080/gestpann/getagentbyid?idusr="+idusr,
                $.get("http://www.gestdp.com/gestpann/getagentbyid?idusr="+idusr,
                    function (data){
                        $(data).find('item').each(function(){
                            nom = $(this).find('nom').text();
                            email = $(this).find('email').text();
                            numero = $(this).find('numero').text();
                        });

                        // Afficher :
                        document.getElementById("contenu-image").innerHTML =
                            "<div>Nom : "+nom+"</div>"+
                            "<div>Email : "+email+"</div>"+
                            "<div>Num&eacute;ro : "+numero+"</div>";
                    }
                );
            }



            // Delete all markers :
            function removeMarkers(){
                for(var b=0; b<tabMarkers.length; b++){
                    tabMarkers[b].setMap(null);
                }
            }


            // Make the marker blink :
            function blinkMarkers(){

                //alert("switchLight : "+switchLight);
                //console.log("On rentre dans  blinkMarkers ");
                //console.log("Taille tabMarkersAlerte : "+tabMarkersAlerte.length);

                for(var k=0; k < tabMarkersAlerte.length; k++){

                    //console.log("K : "+k);
                    if(tabMarkersAlerte[k].blink == 0){

                        // Now change it :
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng(
                            tabMarkersAlerte[k].latitude,
                            tabMarkersAlerte[k].longitude),
                            map: map,
                            icon: {
                              url: "http://maps.google.com/mapfiles/ms/icons/orange-dot.png"
                            }
                        });

                        // Try to set the DATA :
                        google.maps.event.addListener(marker, 'click', (function(marker, k) {
                          return function() {
                            infowindow.setContent("Identifiant : "+tabMarkersAlerte[k].nom);
                            infowindow.open(map, marker);
                            //
                            $('#ultraModal-1').modal('toggle');
                            // Afficher :
                            document.getElementById("contenu-image").innerHTML =
                                "<p>Veuillez patienter ...</p>";
                            displayImage(tabMarkersAlerte[k].nom);
                          }
                        })(marker, k));

                        tabMarkersAlerte[k].blink = 1;
                    }
                    else{
                        tabMarkersAlerte[k].blink = 0;

                        // Now change it :
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng(
                            tabMarkersAlerte[k].latitude,
                            tabMarkersAlerte[k].longitude),
                            map: map,
                            icon: {
                              url: "http://maps.google.com/mapfiles/ms/icons/orange.png"
                            }
                        });

                        // Try to set the DATA :
                        google.maps.event.addListener(marker, 'click', (function(marker, k) {
                          return function() {
                            infowindow.setContent("Identifiant : "+tabMarkersAlerte[k].nom);
                            infowindow.open(map, marker);
                            //
                            $('#ultraModal-1').modal('toggle');
                            // Afficher :
                            document.getElementById("contenu-image").innerHTML =
                                "<p>Veuillez patienter ...</p>";
                            displayImage(tabMarkersAlerte[k].nom);
                          }
                        })(marker, k));
                    }

                    // Clear :
                    //tabMarkersAlerte[k].marker.setMap(null);
                }
            }

			function logKey(e) {

				var key = e.which || e.keyCode;
				if (e.ctrlKey && key == 75){
				    // Ctrl + K
					clearInterval(intervalVar);
					clearInterval(intervalVarBlink);
					alert("Rafraichissement stoppé !");

					// Clear the LIST of position :
					effacerListe();
				}

			  //log.textContent += ` ${e.code}`;
			  //log.textContent += ` ${e.keyCode}`;
			}


            function effacerListe(){

                var nom = "";

                //$.get("http://localhost:8080/gestpann/cleargpsliste",
                $.get("http://www.gestdp.com/gestpann/cleargpsliste",
                    function (data){
                        $(data).find('item').each(function(){
                            nom = $(this).find('nom').text();
                        });
                    }
                );
            }


        </script>

    </body>
</html>