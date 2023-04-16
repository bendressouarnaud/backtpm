package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity
public class Hopitaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idhop")
    private int idhop;

    @Column(name="libelle")
    private String libelle;

    // Attribute :
    public Hopitaux(){}
    public int getIdhop(){ return idhop; }
    public String getLibelle(){ return libelle; }
    public void setIdhop(int idhop){ this.idhop = idhop; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
}
