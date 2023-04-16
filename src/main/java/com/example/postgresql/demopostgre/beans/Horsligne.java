package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "horsligne")
public class Horsligne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idhor")
    private int idhor;

    @Column(name="idusr")
    private int idusr;

    @Column(name="autorisation")
    private int autorisation;

    public Horsligne() {
    }

    public int getIdhor() {
        return idhor;
    }

    public void setIdhor(int idhor) {
        this.idhor = idhor;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public int getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(int autorisation) {
        this.autorisation = autorisation;
    }
}
