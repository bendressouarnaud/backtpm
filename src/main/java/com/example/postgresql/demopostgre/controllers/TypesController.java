package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.Taille;
import com.example.postgresql.demopostgre.beans.Types;
import com.example.postgresql.demopostgre.depots.TailleRepository;
import com.example.postgresql.demopostgre.depots.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TypesController {

    @Autowired
    TypesRepository typesRepository;
    @Autowired
    TailleRepository tailleRepository;
    @PersistenceUnit
    EntityManagerFactory emf;


    //   ##########################    Taille :
    // client
    @GetMapping(value="/types")
    public String accueiltypes(Model model){
        // Get the list of TYPES :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listTypes = emr.createQuery(
                "select a.libelle,a.idtyp,b.libelle from types a left join taille " +
                        "b on a.idtail=b.idtai ").getResultList();
        model.addAttribute("listTypes", listTypes);
        emr.getTransaction().commit();
        //
        emr.close();

        return "types";
    }

    // Nouveau client
    @GetMapping(value="/nouveltypes")
    public String nouveltaille(Model model){

        // Get the list of TYPES :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listTypes = emr.createQuery(
                "select a.libelle,a.idtyp,b.libelle from types a left join taille " +
                        "b on a.idtail=b.idtai ").getResultList();
        model.addAttribute("listTypes", listTypes);
        emr.getTransaction().commit();
        //
        emr.close();

        model.addAttribute("nouveltypes",1);
        // Get taille :
        List<Taille> listeTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listeTaille);
        //System.out.println("Taille de la liste : "+listeTaille.size());
        return "types";
    }



    // Ajouter nouveau client
    @PostMapping(value="/creertypes")
    public String creertaille(@RequestParam("libelle") String libelle,
                              @RequestParam("taille") int taille,
                              Model model){
        // Get the list of CLIENT :
        Types objet = new Types();
        objet.setLibelle(libelle);
        objet.setIdtail(taille);
        // Save :
        typesRepository.save(objet);

        // Get the list of TYPES :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listTypes = emr.createQuery(
                "select a.libelle,a.idtyp,b.libelle from types a left join taille " +
                        "b on a.idtail=b.idtai ").getResultList();
        model.addAttribute("listTypes", listTypes);
        emr.getTransaction().commit();
        //
        emr.close();
        return "types";
    }



    // Modifier un client
    @GetMapping(value="/modiftypes/{id}")
    public String modiftaille(@PathVariable int id,
                              Model model){
        // Get the CLIENT :
        Types objet = typesRepository.findByIdtyp(id);

        // Get the list of TYPES :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listTypes = emr.createQuery(
                "select a.libelle,a.idtyp,b.libelle from types a left join taille " +
                        "b on a.idtail=b.idtai ").getResultList();
        model.addAttribute("listTypes", listTypes);
        emr.getTransaction().commit();
        //
        emr.close();

        model.addAttribute("types", objet);

        // Get taille :
        List<Taille> listeTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listeTaille);

        return "types";
    }


    // modifier client
    @PostMapping(value="/modifiertypes")
    public String modifiertaille(
            @RequestParam("id") String id,
            @RequestParam("libelle") String libelle,
            @RequestParam("taille") int taille,
            Model model){

        // Get the list of CLIENT :
        Types objet = typesRepository.findByIdtyp(Integer.parseInt(id));
        objet.setLibelle(libelle);
        objet.setIdtail(taille);
        // Save :
        typesRepository.save(objet);

        // Get the list of TYPES :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listTypes = emr.createQuery(
                "select a.libelle,a.idtyp,b.libelle from types a left join taille " +
                        "b on a.idtail=b.idtai ").getResultList();
        model.addAttribute("listTypes", listTypes);
        emr.getTransaction().commit();
        //
        emr.close();

        return "types";
    }

}
