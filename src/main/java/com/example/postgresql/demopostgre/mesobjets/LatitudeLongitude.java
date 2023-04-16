package com.example.postgresql.demopostgre.mesobjets;

public class LatitudeLongitude {

    // Attributes
    private Double latitude,longitude;
    private String libelle;
    private String libtypes;
    private int idpan;


    // Methods :
    public LatitudeLongitude(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatitudeLongitude(Double latitude, Double longitude, String libelle) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.libelle = libelle;
    }

    public LatitudeLongitude(Double latitude, Double longitude,
                             String libelle, String libtypes) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.libelle = libelle;
        this.libtypes = libtypes;
    }

    public LatitudeLongitude(Double latitude, Double longitude,
                             String libelle, String libtypes, int idpan) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.libelle = libelle;
        this.libtypes = libtypes;
        this.idpan = idpan;
    }

    public int getIdpan() {
        return idpan;
    }

    public void setIdpan(int idpan) {
        this.idpan = idpan;
    }

    public String getLibtypes() {
        return libtypes;
    }

    public void setLibtypes(String libtypes) {
        this.libtypes = libtypes;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
