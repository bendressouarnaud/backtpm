package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "taille")
public class Taille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idtai")
    private int idtai;

    @Column(name="libelle")
    private String libelle;
    @Column(name="valeur")
    private double valeur;

    // Attribute :
    public Taille(){}
    public int getIdtai(){ return idtai; }
    public String getLibelle(){ return libelle; }
    public void setIdtai(int idtai){ this.idtai = idtai; }
    public void setLibelle(String libelle){ this.libelle = libelle; }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
}



