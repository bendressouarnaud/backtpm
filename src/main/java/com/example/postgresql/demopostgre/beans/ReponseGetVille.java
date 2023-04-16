package com.example.postgresql.demopostgre.beans;

public class ReponseGetVille {

    String ville;
    Long nbrequartier,nbresecteur,nbrepan;

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Long getNbrequartier() {
        return nbrequartier;
    }

    public void setNbrequartier(Long nbrequartier) {
        this.nbrequartier = nbrequartier;
    }

    public Long getNbresecteur() {
        return nbresecteur;
    }

    public void setNbresecteur(Long nbresecteur) {
        this.nbresecteur = nbresecteur;
    }

    public Long getNbrepan() {
        return nbrepan;
    }

    public void setNbrepan(Long nbrepan) {
        this.nbrepan = nbrepan;
    }

    public ReponseGetVille() {
    }
}
