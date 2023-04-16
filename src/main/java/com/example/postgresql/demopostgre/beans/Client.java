package com.example.postgresql.demopostgre.beans;

import javax.persistence.*;

@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcli")
    private int idcli;

    @Column(name="libelle")
    private String libelle;
    @Column(name="contact")
    private String contact;
    @Column(name="email")
    private String email;

    // Attribute :
    public Client(){}
    public int getIdcli(){ return idcli; }
    public String getLibelle(){ return libelle; }
    public String getContact(){ return contact; }
    public String getEmail(){ return email; }

    public void setIdcli(int idcli){ this.idcli = idcli; }
    public void setLibelle(String libelle){ this.libelle = libelle; }
    public void setContact(String contact){ this.contact = contact; }
    public void setEmail(String email){ this.email = email; }

}
