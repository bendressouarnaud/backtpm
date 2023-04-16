package com.example.postgresql.demopostgre.beans;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "panneauhisto")
public class Panneauhisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idphis")
    private int idphis;

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

    @Column(name="idpan")
    private int idpan;
    @Column(name="iduser")
    private int iduser;

    public Panneauhisto() {
    }

    public int getIdphis() {
        return idphis;
    }

    public void setIdphis(int idphis) {
        this.idphis = idphis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getHeurecreation() {
        return heurecreation;
    }

    public void setHeurecreation(String heurecreation) {
        this.heurecreation = heurecreation;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getIdpan() {
        return idpan;
    }

    public void setIdpan(int idpan) {
        this.idpan = idpan;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
