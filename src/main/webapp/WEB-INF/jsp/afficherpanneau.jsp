<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <title>Positionnement | Support</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="https://openlayers.org/en/v4.6.5/css/ol.css" type="text/css">
        <script src="https://openlayers.org/en/v4.6.5/build/ol.js" type="text/javascript"></script>
        <script src="<c:url value='/assets/js/jquery-3.2.1.min.js' />" type="text/javascript"></script>

        <!--   SECURITY TAG
        <meta http-equiv="Content-Security-Policy"
            content="default-src 'self' https://openlayers.org/en/ 'unsafe-inline' 'unsafe-eval'; img-src 'self' data: blob: "/>
        -->

        <script>
        /* OSM & OL example code provided by https://mediarealm.com.au/ */
            var map;
            var mapLat = -33.829357;
            var mapLng = 150.961761;
            var mapDefaultZoom = 10;
            var images = "";

            $( document ).ready(function() {

                var latitude = $('#latitude').val();
                var longitude = $('#longitude').val();
                images = $('#images').val();

                mapLat = parseFloat(latitude);
                mapLng = parseFloat(longitude);
                //alert("latitude : "+latitude);

                initialize_map();
                add_map_point(mapLat, mapLng);
            });

            // url: "https://a.tile.openstreetmap.org/{z}/{x}/{y}.png"

            function initialize_map() {
                map = new ol.Map({
                target: "map",
                layers: [
                new ol.layer.Tile({
                source: new ol.source.OSM({
                url: "https://a.tile.openstreetmap.org/{z}/{x}/{y}.png"
                })
                })
                ],
                view: new ol.View({
                center: ol.proj.fromLonLat([mapLng, mapLat]),
                zoom: mapDefaultZoom
                })
                });
            }

            // "https://upload.wikimedia.org/wikipedia/commons/e/ec/RedDot.svg"
            // images

            function add_map_point(lat, lng) {
                var vectorLayer = new ol.layer.Vector({
                    source:new ol.source.Vector({
                        features: [new ol.Feature({
                            geometry: new ol.geom.Point(ol.proj.transform([parseFloat(lng),
                            parseFloat(lat)], 'EPSG:4326', 'EPSG:3857')),
                        })]
                    }),
                    style: new ol.style.Style({
                        image: new ol.style.Icon({
                        anchor: [0.5, 0.5],
                        anchorXUnits: "fraction",
                        anchorYUnits: "fraction",
                        src: "https://upload.wikimedia.org/wikipedia/commons/e/ec/RedDot.svg"
                        })
                    })
                });
                map.addLayer(vectorLayer);
            }
        </script>
    </head>

    <body >
        <div id="map" style="width: 100vw; height: 100vh;"></div>

        <input type="hidden" id="latitude"  value="${ latitude }" />
        <input type="hidden" id="longitude"  value="${ longitude }" />
        <input type="hidden" id="images"  value="data:image/jpeg;base64,${image}" />

    </body>
</html>