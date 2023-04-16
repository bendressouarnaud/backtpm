package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.ReponseUserCheck;
import com.example.postgresql.demopostgre.beans.Utilisateur;
import com.example.postgresql.demopostgre.depots.UtilisateurRepository;
import com.example.postgresql.demopostgre.mesobjets.InfoImage;
import com.example.postgresql.demopostgre.mesobjets.UserPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TempsreelController {

    //    A T T R I B U T E S :
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    // Static
    public static List<UserPosition> listeUserPosition =
            new ArrayList<UserPosition>();

    //    M e t h o d s :
    @GetMapping(value="/tempsreel")
    public String tempsreel(){
        return "suivitempsreel";
    }

    @CrossOrigin("*")
    @GetMapping(value="/getusersrealtime" ,
            produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<UserPosition> getusersrealtime(){

        //
        List<UserPosition> listeUsers = new ArrayList<UserPosition>();
        listeUsers = listeUserPosition;

        // Delete the one equal to 1 :
        /*
        System.out.println("Taille listeUsersCopie : "+listeUsersCopie.size());
        for(int y = 0; y < listeUsers.size(); y++){

            // Display :
            System.out.println("Elt num. : "+y);

            if(listeUsers.get(y).getEtat() == 1){
                // Delete :
                listeUserPosition.remove(y);
                System.out.println("Alerte supprimée");
                System.out.println("Taille listeUsersCopie apres suppression : "+
                        listeUsersCopie.size());
            }
        }
        */

        return listeUsers;
        //return listeUsersCopie;
    }


    // -------------------------------------------------------->
    @GetMapping(value="/gposition/{user}/{latitude}/{longitude}/{etat}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck gposition(@PathVariable String user,
                                      @PathVariable Double latitude,
                                      @PathVariable Double longitude,
                                      @PathVariable int etat){

        ReponseUserCheck ruc = new ReponseUserCheck();
        boolean add = false;

        // check if this data is in the LIST :
        UserPosition up = new UserPosition(user,latitude, longitude, etat);
        for(int i=0; i < listeUserPosition.size(); i++){
            if(up.getNom().equals(listeUserPosition.get(i).getNom())){
                // :
                listeUserPosition.get(i).setLatitude(latitude);
                listeUserPosition.get(i).setLongitude(longitude);
                listeUserPosition.get(i).setEtat(etat);

                //
                add = true;
            }
        }

        //
        if(!add){
            listeUserPosition.add(up);
            // Display :
            //System.out.println("Elt ajouté !");
        }

        ruc.setNom("ok");
        ruc.setUserid(1);

        return ruc;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getagentbyid" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Utilisateur> getagentbyid(@RequestParam("idusr") int idusr){
        List<Utilisateur> retour = new ArrayList<Utilisateur>();
        Utilisateur utilisateur = utilisateurRepository.findByIdusr(idusr);
        retour.add(utilisateur);
        return retour;
    }


    @CrossOrigin("*")
    @GetMapping(value="/cleargpsliste" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<ReponseUserCheck> cleargpsliste(){
        List<ReponseUserCheck> retour = new ArrayList<ReponseUserCheck>();
        listeUserPosition.clear();

        ReponseUserCheck ruc = new ReponseUserCheck();
        ruc.setNom("ok");
        ruc.setUserid(1);
        retour.add(ruc);

        return retour;
    }


}
