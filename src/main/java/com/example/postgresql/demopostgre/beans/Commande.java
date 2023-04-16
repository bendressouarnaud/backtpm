package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcom")
    private int idcom;
    @Column(name="dates")
    private Date dates;
    @Column(name="heure")
    private String heure;
    @Column(name="datedebut")
    private Date datedebut;
    @Column(name="datefin")
    private Date datefin;
    @Column(name="pdtpublier")
    private String pdtpublier;
    @Column(name="cout")
    private int cout;
    @Column(name="idcli")
    private int idcli;
    @Column(name="idpan")
    private int idpan;
    @Column(name="coutodp")
    private int coutodp;


    // METHODS : :
    public Commande(){}
    public int getIdcom(){ return idcom; }
    public void setIdcom(int idcom){ this.idcom = idcom; }
    public void setDates(Date dates){ this.dates = dates; }
    public Date getDates(){ return dates; }
    public void setHeure(String heure){ this.heure = heure; }
    public String getHeure(){ return heure; }
    public void setDatedebut(Date datedebut){ this.datedebut = datedebut; }
    public Date getDatedebut(){ return datedebut; }
    public void setDatefin(Date datefin){ this.datefin = datefin; }
    public Date getDatefin(){ return datefin; }
    public void setPdtpublier(String pdtpublier){ this.pdtpublier = pdtpublier; }
    public String getPdtpublier(){ return pdtpublier; }
    public int getCout(){ return cout; }
    public void setCout(int cout){ this.cout = cout; }
    public int getIdcli(){ return idcli; }
    public void setIdcli(int idcli){ this.idcli = idcli; }
    public int getIdpan(){ return idpan; }
    public void setIdpan(int idpan){ this.idpan = idpan; }
    public int getCoutodp() {
        return coutodp;
    }

    public void setCoutodp(int coutodp) {
        this.coutodp = coutodp;
    }
}
