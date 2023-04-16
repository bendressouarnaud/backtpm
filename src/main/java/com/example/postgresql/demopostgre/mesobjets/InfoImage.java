package com.example.postgresql.demopostgre.mesobjets;

public class InfoImage {

    // Attributes
    private String imagetexte;
    private String emplacement,dateheure,secteurs,quartiers,villes,users;

    public InfoImage(String imagetexte, String emplacement) {
        this.imagetexte = imagetexte;
        this.emplacement = emplacement;
    }

    public InfoImage(String imagetexte, String emplacement, String dateheure,
                     String secteurs, String quartiers, String villes, String users) {
        this.imagetexte = imagetexte;
        this.emplacement = emplacement;
        this.dateheure = dateheure;
        this.secteurs = secteurs;
        this.quartiers = quartiers;
        this.villes = villes;
        this.users = users;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getImagetexte() {
        return imagetexte;
    }

    public void setImagetexte(String imagetexte) {
        this.imagetexte = imagetexte;
    }

    public String getDateheure() {
        return dateheure;
    }

    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }

    public String getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(String secteurs) {
        this.secteurs = secteurs;
    }

    public String getQuartiers() {
        return quartiers;
    }

    public void setQuartiers(String quartiers) {
        this.quartiers = quartiers;
    }

    public String getVilles() {
        return villes;
    }

    public void setVilles(String villes) {
        this.villes = villes;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
