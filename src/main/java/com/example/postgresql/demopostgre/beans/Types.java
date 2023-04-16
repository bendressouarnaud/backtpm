package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "types")
public class Types {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idtyp")
    private int idtyp;

    @Column(name="libelle")
    private String libelle;

    @Column(name="idtail")
    private int idtail;

    // Attribute :
    public Types(){}
    public int getIdtyp(){ return idtyp; }
    public String getLibelle(){ return libelle; }
    public void setIdtyp(int idtyp){ this.idtyp = idtyp; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public int getIdtail() { return idtail; }
    public void setIdtail(int idtail) { this.idtail = idtail; }
}
