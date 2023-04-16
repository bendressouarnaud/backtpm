package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.Quartier;
import com.example.postgresql.demopostgre.beans.Secteur;
import com.example.postgresql.demopostgre.beans.Ville;
import com.example.postgresql.demopostgre.depots.QuartierRepository;
import com.example.postgresql.demopostgre.depots.SecteurRepository;
import com.example.postgresql.demopostgre.depots.VilleRepository;
import com.example.postgresql.demopostgre.mesobjets.GetIpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Controller
public class VilleController {

    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    QuartierRepository quartierRepository;
    @Autowired
    VilleRepository villeRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    @Value("${adresselocal}")
    private String lienapp;


    //   ##########################    Taille :
    // client
    @GetMapping(value="/ville")
    public String accueilville(Model model){
        // Get the list of CLIENT :
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }

    // Nouveau client
    @GetMapping(value="/nouvelville")
    public String nouvelville(Model model){
        // Get the list of CLIENT :
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        model.addAttribute("nouvelville",1);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }


    // Recherche
    @GetMapping(value="/searchcommune")
    public String searchcommune(Model model){
        // Get the list of CLIENT :
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listCommune",listCommune);
        model.addAttribute("recherche",0);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }

    // choixcommunefiltre
    @PostMapping(value="/choixcommunefiltre")
    public String choixcommunefiltre(@RequestParam("commune") int commune,
                              @RequestParam("quartier") int quartier,
                              @RequestParam("secteur") int secteur,
                              @RequestParam("choix") int choix,
                              Model model){

        // Declare :
        //List<Ville> listCommune;
        //Ville ville;

        // Process :
        switch (choix){
            case 0:
                // Commune :
                Ville ville = villeRepository.findByIdvil(commune);
                model.addAttribute("mville", ville);
                model.addAttribute("choixville", choix);
                List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
                model.addAttribute("listCommune",listCommune);
                model.addAttribute("recherche",0);
                break;

            case 1:
                // Quartier,
                List<Quartier> listQuartier =
                        quartierRepository.findAllByIdvillOrderByLibelleAsc(commune);
                Ville ville1 = villeRepository.findByIdvil(commune);
                model.addAttribute("showville", ville1);
                model.addAttribute("listQuartier", listQuartier);
                List<Ville> listCommune1 = villeRepository.findAllByOrderByLibelleAsc();
                model.addAttribute("listCommune",listCommune1);
                model.addAttribute("recherche",0);
                break;

            case 2:
                // Secteur
                EntityManager emr = emf.createEntityManager();
                emr.getTransaction().begin();
                List<Object[]> resultatRequete =
                        emr.createQuery("select " +
                                "b.libelle,c.libelle,c.idsec " +
                                "from ville a inner join quartier b on a.idvil=b.idvill inner " +
                                "join secteur c on b.idqua=c.idquar " +
                                "where a.idvil="+commune).getResultList();
                model.addAttribute("listesecteurquartier",resultatRequete);
                // Close :
                emr.close();

                //
                Ville ville2 = villeRepository.findByIdvil(commune);
                model.addAttribute("villesecteur", ville2);
                List<Ville> listCommune2 = villeRepository.findAllByOrderByLibelleAsc();
                model.addAttribute("listCommune",listCommune2);
                model.addAttribute("recherche",0);
                break;
        }

        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }


    // Ajouter nouvelle VILLE
    @PostMapping(value="/creerville")
    public String creertaille(@RequestParam("libelle") String libelle,
                              @RequestParam("population") String population,
                              @RequestParam("taux") int taux,
                              Model model){
        // Get the list of CLIENT :
        Ville objet = new Ville();
        objet.setLibelle(libelle);
        objet.setPopulation(Integer.parseInt(population));
        objet.setTaux(taux);
        // Save :
        villeRepository.save(objet);

        // Get the list of CLIENT :
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }

    // Modifier une VILLE
    @GetMapping(value="/modifville/{id}")
    public String modiftaille(@PathVariable int id,
                              Model model){
        // Get the CLIENT :
        Ville objet = villeRepository.findByIdvil(id);
        // Get the list of CLIENT :
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        model.addAttribute("ville", objet);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }


    // modifier client
    @PostMapping(value="/modifierville")
    public String modifiertaille(
            @RequestParam("id") String id,
            @RequestParam("libelle") String libelle,
            @RequestParam("population") String population,
            @RequestParam("taux") int taux,
            Model model){

        // Get the list of CLIENT :
        Ville objet = villeRepository.findByIdvil(Integer.parseInt(id));
        objet.setLibelle(libelle);
        objet.setPopulation(Integer.parseInt(population));
        objet.setTaux(taux);
        // Save :
        villeRepository.save(objet);

        // Get the list of CLIENT :
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "commune";
    }




}
