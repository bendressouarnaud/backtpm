package com.example.postgresql.demopostgre.mesobjets;

public class GetTypesTotal {

    String libelle;
    long total;

    public GetTypesTotal(String libelle, long total) {
        this.libelle = libelle;
        this.total = total;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
