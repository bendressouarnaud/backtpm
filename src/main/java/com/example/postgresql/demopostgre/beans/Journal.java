package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idjou")
    private int idjou;

    @Column(name="idusr")
    private int idusr;
    @Column(name="dates")
    private Date dates;
    @Column(name="heure")
    private String heure;
    @Column(name="action")
    private String action;

    public Journal() {
    }

    public int getIdjou() {
        return idjou;
    }

    public void setIdjou(int idjou) {
        this.idjou = idjou;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
