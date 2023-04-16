package com.example.postgresql.demopostgre.beans;

public class HistoRapportQuartier {

    // Attributs :
    public String libpan,libtype,emplacement,secteur,dcreate;

    // MEthodes :
    public HistoRapportQuartier(){}

    public String getLibpan(){ return libpan; }
    public String getLibtype(){ return libtype; }
    public String getEmplacement(){ return emplacement; }

    public String getSecteur() {
        return secteur;
    }

    public String getDcreate() {
        return dcreate;
    }
}
