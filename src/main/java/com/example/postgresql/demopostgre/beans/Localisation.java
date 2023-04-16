package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idloc")
    private int idloc;

    @Column(name="libelle")
    private String libelle;

    // Attribute :
    public Localisation(){}
    public int getIdloc(){ return idloc; }
    public String getLibelle(){ return libelle; }
    public void setIdloc(int idloc){ this.idloc = idloc; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
}
