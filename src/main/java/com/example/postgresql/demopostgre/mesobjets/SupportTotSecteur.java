package com.example.postgresql.demopostgre.mesobjets;

public class SupportTotSecteur {

    String libelle;
    int total;
    int totaluser;

    public SupportTotSecteur(String libelle, int total, int totaluser) {
        this.libelle = libelle;
        this.total = total;
        this.totaluser = totaluser;
    }

    public int getTotaluser() {
        return totaluser;
    }

    public void setTotaluser(int totaluser) {
        this.totaluser = totaluser;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
