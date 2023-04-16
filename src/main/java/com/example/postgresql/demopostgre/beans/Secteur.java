package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsec")
    private int idsec;
    @Column(name="libelle")
    private String libelle;
    @Column(name="idqua")
    private int idquar;

    // METHODES :
    public Secteur(){}
    public int getIdsec(){ return idsec; }
    public String getLibelle(){ return libelle; }
    public void setIdsec(int idsec){ this.idsec = idsec; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public int getIdquar(){ return idquar; }
    public void setIdquar(int idquar){ this.idquar = idquar; }

}
