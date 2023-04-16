package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "requetepanneau")
public class Requetepanneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrqtpn")
    private int idrqtpn;

    @Column(name="idadmin")
    private int idadmin;

    @Column(name="iduser")
    private int iduser;

    @Column(name="idpan")
    private int idpan;

    @Column(name="dates")
    private Date dates;

    @Column(name="etat")
    private int etat;

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Requetepanneau() {
    }

    public int getIdrqtpn() {
        return idrqtpn;
    }

    public void setIdrqtpn(int idrqtpn) {
        this.idrqtpn = idrqtpn;
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdpan() {
        return idpan;
    }

    public void setIdpan(int idpan) {
        this.idpan = idpan;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
