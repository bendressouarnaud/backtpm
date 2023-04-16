package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "ville")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idvil")
    private int idvil;
    @Column(name="libelle")
    private String libelle;
    @Column(name="population")
    private int population;
    @Column(name="taux")
    private int taux;

    // METHODES :
    public Ville(){}
    public int getIdvil(){ return idvil; }
    public String getLibelle(){ return libelle; }
    public void setIdvil(int idvil){ this.idvil = idvil; }
    public void setLibelle(String libelle){ this.libelle = libelle; }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }
}
