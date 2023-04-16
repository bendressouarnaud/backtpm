package com.example.postgresql.demopostgre.beans;

public class RetourSupportTexte {

    private String libelle,emplacement;
    Double latitude, longitude;

    public RetourSupportTexte() {
    }

    public RetourSupportTexte(String libelle, Double latitude, Double longitude) {
        this.libelle = libelle;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
}
