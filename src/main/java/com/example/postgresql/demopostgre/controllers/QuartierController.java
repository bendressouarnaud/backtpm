package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.Quartier;
import com.example.postgresql.demopostgre.beans.Ville;
import com.example.postgresql.demopostgre.depots.QuartierRepository;
import com.example.postgresql.demopostgre.depots.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Controller
public class QuartierController {

    @Autowired
    QuartierRepository quartierRepository;
    @Autowired
    VilleRepository villeRepository;


    @PersistenceUnit
    EntityManagerFactory emf;


    //   ##########################    Taille :
    // client
    @GetMapping(value="/quartier")
    public String accueilville(Model model){
        // Get the list of CLIENT :
        //List<Quartier> listQuartier = quartierRepository.findAll();
        //model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("recherche", "0");
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        return "quartier";
    }

    // Nouveau client
    @GetMapping(value="/nouvelquartier")
    public String nouvelquartier(Model model){
        // Get the list of CLIENT :
        //List<Quartier> listQuartier = quartierRepository.findAll();
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        //model.addAttribute("listQuartier", listQuartier);
        //model.addAttribute("recherche", "0");

        model.addAttribute("nouvelquartier",1);
        return "quartier";
    }

    // Ajouter nouvelle VILLE
    @PostMapping(value="/creerquartier")
    public String creerquartier(@RequestParam("libelle") String libelle,
                                @RequestParam("ville") String ville,
                                Model model){
        // Get the list of CLIENT :
        Quartier objet = new Quartier();
        objet.setLibelle(libelle);
        objet.setIdvill(Integer.parseInt(ville));
        // Save :
        quartierRepository.save(objet);
        // Get the list of CLIENT :
        //List<Quartier> listQuartier = quartierRepository.findAll();
        //model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("recherche", "0");
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        return "quartier";
    }

    // Modifier une VILLE
    @GetMapping(value="/modifquartier/{id}")
    public String modiftaille(@PathVariable int id,
                              Model model){
        // Get the CLIENT :
        Quartier objet = quartierRepository.findByIdqua(id);
        // Get the list of CLIENT :
        //List<Quartier> listQuartier = quartierRepository.findAll();
        List<Ville> listVille = villeRepository.findAll();
        //model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("listVille", listVille);
        model.addAttribute("quartier", objet);
        return "quartier";
    }


    // modifier client
    @PostMapping(value="/modifierquartier")
    public String modifiertaille(
            @RequestParam("id") String id,
            @RequestParam("libelle") String libelle,
            @RequestParam("ville") String ville,
            Model model){

        // Get the list of CLIENT :
        Quartier objet = quartierRepository.findByIdqua(Integer.parseInt(id));
        objet.setLibelle(libelle);
        objet.setIdvill(Integer.parseInt(ville));
        // Save :
        quartierRepository.save(objet);

        // Get the list of CLIENT :
        //List<Quartier> listQuartier = quartierRepository.findAll();
        //model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("recherche", "0");
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        return "quartier";
    }


    // Recherche Quartier
    @PostMapping(value="/recherchequartier")
    public String recherchequartier(@RequestParam("ville") int ville,
                                Model model){
        // Get the list of QUARTIER related to VILLE :
        List<Quartier> listQuartier = quartierRepository.findAllByIdvill(ville);
        model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("recherche", "0");
        List<Ville> listVille = villeRepository.findAll();
        model.addAttribute("listVille", listVille);
        return "quartier";
    }

}
