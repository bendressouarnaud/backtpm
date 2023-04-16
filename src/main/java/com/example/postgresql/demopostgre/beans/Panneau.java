package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "panneau")
public class Panneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpan")
    private int idpan;

    @Column(name="libelle")
    private String libelle;
    @Column(name="taille")
    private int taille;
    @Column(name="types")
    private int types;
    @Column(name="emplacement")
    private String emplacement;

    @Column(name="idsec")
    private int idsec;

    @Column(name="superficie")
    private int superficie;
    @Column(name="image")
    private String image;
    @Column(name="datecreation")
    private Date datecreation;
    @Column(name="heurecreation")
    private String heurecreation;
    @Column(name="longitude")
    private Double longitude;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="dateheure")
    private String dateheure;
    @Column(name="idusr")
    private int idusr;

    // METHODS :
    public Panneau(){}
    public int getIdpan(){ return idpan; }
    public String getLibelle(){ return libelle; }
    public int getTaille(){ return taille; }
    public int getTypes(){ return types; }
    public int getIdsec(){ return idsec; }
    public String getEmplacement(){ return emplacement; }
    public int getSuperficie(){ return superficie; }
    public String getImage(){ return image; }
    public Date getDatecreation(){ return datecreation; }
    public String getHeurecreation(){ return heurecreation; }
    public String getDateheure(){ return dateheure; }
    public Double getLongitude(){ return longitude; }
    public Double getLatitude(){ return latitude; }

    public void setIdpan(int idpan){ this.idpan = idpan; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public void setTaille(int taille){ this.taille = taille; }
    public void setTypes(int types){ this.types = types; }
    public void setIdsec(int idsec){ this.idsec = idsec; }
    public void setEmplacement(String emplacement){ this.emplacement = emplacement; }

    public void setSuperficie(int superficie){ this.superficie = superficie; }
    public void setImage(String image){ this.image = image; }
    public void setHeurecreation(String heurecreation){ this.heurecreation = heurecreation; }
    public void setDateheure(String dateheure){ this.dateheure = dateheure; }
    public void setDatecreation(Date datecreation){ this.datecreation = datecreation; }
    public void setLongitude(Double longitude){ this.longitude = longitude; }
    public void setLatitude(Double latitude){ this.latitude = latitude; }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }
}
