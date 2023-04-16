package com.example.postgresql.demopostgre.beans;

public class ClassFonction {


    public ClassFonction(){}

    public static String retourDate(String dates){
        //      05/14/2019     ---     2019-05-14
        String retour = "";
        String[] tampon = dates.split("/");  // MM-DD-YYYY

        retour = tampon[2]+"-"+tampon[0]+"-"+tampon[1];
        return retour;
    }


}
