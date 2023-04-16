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

                            <h4 class="modal-title">Illustration support</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
                                            <select style="z-index:200000" id="utilisateur"  name="utilisateur" >
                                                <c:forEach items="${listeUtilisateur}" var="utilisateurs" >
                                                    <option value="${ utilisateurs.getIdusr() }">
                                                        ${ utilisateurs.getNom() }
                                                    </option>
                                                </c:forEach>
                                            </select>
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
        <input type="hidden" id="choix"  value="${ choix }" />
        <input type="hidden" id="valeur"  value="${ valeur }" />
        <input type="hidden" id="typesupport"  value="${ typesupport }" />
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


        <script src="<c:url value='/assets/plugins/select2/select2.min.js' />" type="text/javascript"></script>


        <!--<script src="https://openlayers.org/en/v4.6.5/build/ol.js"
            type="text/javascript"></script>-->
        <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtJHXa9mv08ooUymhgm5Ty3h960s-_gQ0&sensor=false"></script>
        <!--<script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>-->
        <script>
            var map;
            var mapLat = -33.829357;
            var mapLng = 150.961761;
            var mapDefaultZoom = 10;
            var images = "";
            var lienapp = "";

            var tabLongitude = new Array();
            var tabLatitude = new Array();
            var tabLibelle = new Array();
            var tabIdpan = new Array();
            var tabIdpanHold = new Array();
            var i=0;

            var choix = 0;
            var typesupport = 0;
            var valeur = "";

            // Hold PANNEAU ID :
            var getPanneauId = 0;


            $( document ).ready(function() {

                // Initialize :
                //$('.select2').select2();

                choix = parseInt($('#choix').val());
                typesupport = parseInt($('#typesupport').val());
                valeur = $('#valeur').val();
                lienapp = $('#lienapp').val();
                getsupports(choix, valeur, typesupport);
            });


            // To get all supports :
            function getsupports(choix, valeur,typesupport){
                $.get(lienapp + "gestpann/getsupportschoix?choix="+choix+"&id="+valeur+"&typesupport="+typesupport,
                    function (data){
                        $(data).find('item').each(function(){
                        	var latit = $(this).find('latitude').text();
                        	var longi = $(this).find('longitude').text();
                        	var texte = $(this).find('libelle').text();
                        	var libtypes = $(this).find('libtypes').text();
                        	var idpan = $(this).find('idpan').text();

                        	// set :
                        	tabLatitude[i] = parseFloat(latit);
                        	tabLongitude[i] = parseFloat(longi);
                        	tabLibelle[i] = texte;
                        	tabIdpan[i] = libtypes; // parseInt(idpan);
                        	tabIdpanHold[i] = parseInt(idpan);
                        	i = i +1;
                        });

                        //alert("Taille tabLongitude :"+tabLongitude.length);

                        if(tabLongitude.length == 0){
                            alert("Il n'y a pas de données pour ce lieu!!!");
                            return;
                        }

                        //
                        //alert("On commence l'affichage !!!");

                        var map = new google.maps.Map(document.getElementById('map'), {
                          zoom: 10,
                          center: new google.maps.LatLng(tabLatitude[0], tabLongitude[0]),
                          mapTypeId: google.maps.MapTypeId.ROADMAP
                        });

                        var infowindow = new google.maps.InfoWindow();

                        // Display all points :
                        var marker;
                        for (var j = 0; j < tabLongitude.length; j++) {
                            marker = new google.maps.Marker({
                                position: new google.maps.LatLng(tabLatitude[j], tabLongitude[j]),
                                map: map,
                                icon: {
                                  url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
                                }
                            });

                            google.maps.event.addListener(marker, 'click', (function(marker, j) {
                              return function() {
                                infowindow.setContent(tabIdpan[j]+" - "+tabIdpanHold[j]);
                                infowindow.open(map, marker);
                                //
                                //alert("Idpan : " + tabIdpanHold[j] );
                                $('#ultraModal-1').modal('toggle');
                                // Afficher :
                                document.getElementById("contenu-image").innerHTML =
                                    "<p>Veuillez patienter ...</p>";
                                displayImage(tabIdpanHold[j]);
                              }
                            })(marker, j));
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

                //
                getPanneauId = idpan;

                $.get(lienapp + "gestpann/getsupportbyid?idpan="+idpan,
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
                            "<div>Agent : "+users+"</div><br>"+
                            "<div><input type='button' value='Actualiser le support'"+
                            " onclick='miseajour("+idpan+")' class='btn btn-primary' /></div>";
                    }
                );
            }

            function miseajour(valeur) {
                $('#ultraModal-1').modal('hide');

                var tabUsersId = new Array();
                var tabUsersName = new Array();
                var j = 0;

                // Call here to display the users who worked in the area :
                $.get(lienapp + "gestpann/getagentusers?idpan="+
                    valeur,
                    function (data){
                        $(data).find('item').each(function(){
                            var idu = $(this).find('idusr').text();
                            var nom = $(this).find('nom').text();
                            //
                            tabUsersId[j] = parseInt(idu);
                            tabUsersName[j] = nom;
                            j = j+1;
                        });

                        // Now process :
                        var options = [];

                        var tamponCode = "";
                        for (var k = 0; k < tabUsersId.length; k++) {
                          tamponCode = "<option value='"+tabUsersId[k]+"'>"+tabUsersName[k]+"</option>";
                          options.push(tamponCode);
                        }

                        if(options.length > 0){
                        	$("#utilisateur").html(options);
                        }
                    }
                );

                $('#ultraModal-7').modal('toggle');
            }

            function soumettre(){
                // getPanneauId
                var utilisateur = $("#utilisateur").val();
                var retour = "";

                //alert("getPanneauId : "+getPanneauId);
                //return;

                $.get(lienapp + "gestpann/updaterequest?idpan="+
                    getPanneauId+"&userid="+utilisateur,
                    function (data){
                        $(data).find('item').each(function(){
                            retour = $(this).find('emplacement').text();
                        });

                        //
                        $('#ultraModal-7').modal('hide');

                        // Afficher :
                        if(retour === "ok") alert("Demande traitee !");
                        else alert("Impossible de traiter la demande !");
                    }
                );
            }

            function init() {}
        </script>

    </body>
</html>