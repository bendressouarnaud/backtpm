package com.example.postgresql.demopostgre.mesobjets;

import com.example.postgresql.demopostgre.beans.Utilisateur;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalculTsp {

    int population = 0;
    int typeID = 0;

    public CalculTsp(int population, int typeID) {
        this.population = population;
        this.typeID = typeID;
    }

    public double differencedates(Date debut, Date fin){
        double retour = 0;

        long diffInMillies = Math.abs(fin.getTime() - debut.getTime());
        retour = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return (retour/30);
    }

    public double calculMontantTsp(Date debut, Date fin, double superficie){
        double retour = 0;

        if (population < 50000){
            switch (typeID){
                case 1:
                case 2:
                case 3:
                    // Affiches Lumineuses
                    retour = 1000*superficie*differencedates(debut,fin);
                    break;

                default:
                    // Affiches peintes
                    retour = 250*superficie*differencedates(debut,fin);
                    break;
            }
        }
        else if(population <= 200000){
            switch (typeID){
                case 1:
                case 2:
                case 3:
                    // Affiches Lumineuses
                    retour = 2000*superficie*differencedates(debut,fin);
                    break;

                case 6:
                    // Banderoles :
                    retour = 2500*superficie*differencedates(debut,fin);
                    break;

                default:
                    // Affiches peintes
                    retour = 500*superficie*differencedates(debut,fin);
                    break;
            }
        }
        else if(population > 200000){
            switch (typeID){
                case 1:
                case 2:
                case 3:
                    // Affiches Lumineuses :
                    retour = 3000*superficie*differencedates(debut,fin);
                    break;

                case 6:
                    // Banderoles :
                    retour = 5000*superficie*differencedates(debut,fin);
                    break;

                default:
                    // Affiches peintes :
                    retour = 1000*superficie*differencedates(debut,fin);
                    break;
            }
        }
        return retour;
    }



    public double calculMontantOdp(Date debut, Date fin, double superficie,
                                   int taxe){
        double retour = 0;

        switch (typeID){
            case 1:
            case 2:
                // Affiches Lumineuses
                retour = taxe*superficie*differencedates(debut,fin);
                break;

            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 56:
                // Affiches Peintes    25-33
                //System.out.println("Diffence date : "+differencedates(debut,fin));
                //System.out.println("Taxe : "+taxe);
                //System.out.println("superficie : "+superficie);
                retour = taxe*superficie*differencedates(debut,fin);
                break;

            default:
                // Affiches peintes
                //System.out.println("Diffence date : "+differencedates(debut,fin));
                retour = 0 * superficie*differencedates(debut,fin);
                break;
        }

        return retour;
    }



}
