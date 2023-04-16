package com.example.postgresql.demopostgre.mesobjets;

public class GetSecteur {

    int idsec,idqua;
    String libelle;

    public GetSecteur() {
    }

    public GetSecteur(int idsec, int idqua, String libelle) {
        this.idsec = idsec;
        this.idqua = idqua;
        this.libelle = libelle;
    }

    public int getIdsec() {
        return idsec;
    }

    public void setIdsec(int idsec) {
        this.idsec = idsec;
    }

    public int getIdqua() {
        return idqua;
    }

    public void setIdqua(int idqua) {
        this.idqua = idqua;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
