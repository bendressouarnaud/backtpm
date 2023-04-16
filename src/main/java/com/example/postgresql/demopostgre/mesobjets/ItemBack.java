package com.example.postgresql.demopostgre.mesobjets;

public class ItemBack {

    public int id;
    public String libelle;

    public ItemBack() {
    }

    public ItemBack(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
