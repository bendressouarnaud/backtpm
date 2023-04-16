package com.example.postgresql.demopostgre.mesobjets;

public class UserPosition {

    String nom;
    Double latitude, longitude;
    int etat; // 0 : Normal, 1 : Alerte

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public UserPosition() {
    }

    public UserPosition(String nom, Double latitude, Double longitude, int etat) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.etat = etat;
    }
}
