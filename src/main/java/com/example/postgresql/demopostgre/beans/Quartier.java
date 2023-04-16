package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "quartier")
public class Quartier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idqua")
    private int idqua;
    @Column(name="libelle")
    private String libelle;
    @Column(name="idvil")
    private int idvill;

    // METHODES :
    public Quartier(){}
    public int getIdqua(){ return idqua; }
    public String getLibelle(){ return libelle; }
    public void setIdqua(int idqua){ this.idqua = idqua; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public int getIdvill(){ return idvill; }
    public void setIdvill(int idvill){ this.idvill = idvill; }

}
