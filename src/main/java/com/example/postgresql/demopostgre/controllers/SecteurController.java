package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.Quartier;
import com.example.postgresql.demopostgre.beans.Secteur;
import com.example.postgresql.demopostgre.depots.QuartierRepository;
import com.example.postgresql.demopostgre.depots.SecteurRepository;
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
public class SecteurController {

    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    QuartierRepository quartierRepository;


    @PersistenceUnit
    EntityManagerFactory emf;


    //   ##########################    Taille :
    // client
    @GetMapping(value="/secteur")
    public String accueilville(Model model){
        // Get the list of CLIENT :
        List<Secteur> listSecteur = secteurRepository.findAll();
        model.addAttribute("listSecteur", listSecteur);
        return "secteur";
    }

    // Nouveau client
    @GetMapping(value="/nouvelsecteur")
    public String nouvelquartier(Model model){
        // Get the list of CLIENT :
        List<Secteur> listSecteur = secteurRepository.findAll();
        List<Quartier> listQuartier = quartierRepository.findAll();
        model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("listSecteur", listSecteur);
        model.addAttribute("nouvelsecteur",1);
        return "secteur";
    }

    // Ajouter nouvelle VILLE
    @PostMapping(value="/creersecteur")
    public String creerquartier(@RequestParam("libelle") String libelle,
                                @RequestParam("quartier") String quartier,
                                Model model){
        // Get the list of CLIENT :
        Secteur objet = new Secteur();
        objet.setLibelle(libelle);
        objet.setIdquar(Integer.parseInt(quartier));
        // Save :
        secteurRepository.save(objet);
        // Get the list of CLIENT :
        List<Secteur> listSecteur = secteurRepository.findAll();
        model.addAttribute("listSecteur", listSecteur);
        return "secteur";
    }

    // Modifier une VILLE
    @GetMapping(value="/modifsecteur/{id}")
    public String modiftaille(@PathVariable int id,
                              Model model){
        // Get the CLIENT :
        Secteur objet = secteurRepository.findByIdsec(id);
        // Get the list of CLIENT :
        List<Secteur> listSecteur = secteurRepository.findAll();
        List<Quartier> listQuartier = quartierRepository.findAll();
        model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("listSecteur", listSecteur);
        model.addAttribute("secteur", objet);
        return "secteur";
    }


    // modifier client
    @PostMapping(value="/modifiersecteur")
    public String modifiertaille(
            @RequestParam("id") String id,
            @RequestParam("libelle") String libelle,
            @RequestParam("quartier") String quartier,
            Model model){

        // Get the list of CLIENT :
        Secteur objet = secteurRepository.findByIdsec(Integer.parseInt(id));
        objet.setLibelle(libelle);
        objet.setIdquar(Integer.parseInt(quartier));
        // Save :
        secteurRepository.save(objet);
        // Get the list of CLIENT :
        List<Secteur> listSecteur = secteurRepository.findAll();
        model.addAttribute("listSecteur", listSecteur);
        return "secteur";
    }

}
