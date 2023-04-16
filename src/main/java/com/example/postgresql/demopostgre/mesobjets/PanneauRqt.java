package com.example.postgresql.demopostgre.mesobjets;

import com.example.postgresql.demopostgre.beans.Panneau;

public class PanneauRqt {

    Panneau pn;
    int idrqtpn;

    public PanneauRqt(Panneau pn, int idrqtpn) {
        this.pn = pn;
        this.idrqtpn = idrqtpn;
    }

    public int getIdrqtpn() {
        return idrqtpn;
    }

    public void setIdrqtpn(int idrqtpn) {
        this.idrqtpn = idrqtpn;
    }

    public Panneau getPn() {
        return pn;
    }

    public void setPn(Panneau pn) {
        this.pn = pn;
    }
}
