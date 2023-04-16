package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "superficie")
public class Superficie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsup")
    private int idsup;
    @Column(name="libelle")
    private String libelle;

    public Superficie() {
    }

    public int getIdsup() {
        return idsup;
    }

    public void setIdsup(int idsup) {
        this.idsup = idsup;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
