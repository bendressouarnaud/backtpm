package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "parametres")
public class Parametres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpar")
    private int idpar;

    @Column(name="enregsupport")
    private int enregsupport;

    public Parametres() {
    }

    public int getIdpar() {
        return idpar;
    }

    public void setIdpar(int idpar) {
        this.idpar = idpar;
    }

    public int getEnregsupport() {
        return enregsupport;
    }

    public void setEnregsupport(int enregsupport) {
        this.enregsupport = enregsupport;
    }
}
