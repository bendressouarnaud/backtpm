package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.mesobjets.GetTypesTotal;
import com.example.postgresql.demopostgre.mesobjets.Villesupports;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.StoredProcedureQuery;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserJsRequestController {


    @PersistenceUnit
    EntityManagerFactory emf;



    // Use getsupports
    @CrossOrigin("*")
    @GetMapping(value="/getuserjs/{choix}" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<String> getuserjs(@PathVariable int choix){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Timestamp> resultat = new ArrayList<Timestamp>();

        switch (choix) {
            case 0:
                // Les 3 dernieres dates
                resultat = emr.createQuery(
                    "select distinct a.datecreation from panneau a " +
                    "order by a.datecreation desc" ).setMaxResults(3).
                        getResultList();
                break;
        }

        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<String> listeObjet = new ArrayList<String>();
        // Browse listePanneau :
        for(Timestamp ts : resultat){
            // Create a new Object :
            listeObjet.add(new SimpleDateFormat("yyyy-MM-dd").
                    format(new Date(ts.getTime())));
        }
        //
        emr.close();
        return listeObjet;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getuserjsdata/{dates}" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<GetTypesTotal> getuserjs(@PathVariable String dates){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
            "select b.libelle,count(a.idpan) from panneau a inner join " +
            "types b on a.types=b.idtyp where a.datecreation = '"+dates+"' " +
            "group by b.libelle order by count(a.idpan) desc" )
            .setMaxResults(5).
            getResultList();

        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<GetTypesTotal> listeObjet = new ArrayList<GetTypesTotal>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new GetTypesTotal((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }


    // Les COMMUNE contenant le plus de supports
    @CrossOrigin("*")
    @GetMapping(value="/getcommuneplusupport" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<GetTypesTotal> getcommuneplusupport(){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
            "select d.libelle,count(a.idpan) from panneau a inner join secteur b on " +
            "a.idsec=b.idsec inner join quartier c on b.idquar=c.idqua inner join ville d on " +
            "c.idvill=d.idvil group by d.libelle order by count(a.idpan) desc" )
            .setMaxResults(5).getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<GetTypesTotal> listeObjet = new ArrayList<GetTypesTotal>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new GetTypesTotal((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }



    // les TYPES de supports les plus représentés
    @CrossOrigin("*")
    @GetMapping(value="/gettypeplusupport" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<GetTypesTotal> gettypeplusupport(){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resultat = new ArrayList<Object[]>();

        resultat = emr.createQuery(
            "select b.libelle,count(a.idpan) from panneau a inner join types b on " +
            "a.types=b.idtyp group by b.libelle order by count(a.idpan) desc" )
                .setMaxResults(5).getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<GetTypesTotal> listeObjet = new ArrayList<GetTypesTotal>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            listeObjet.add(new GetTypesTotal((String)ob[0],
                    (Long)ob[1]));
        }

        emr.close();
        return listeObjet;
    }







    // les TYPES de supports les plus représentés
    @CrossOrigin("*")
    @GetMapping(value="/getbiggestcitiesforsup" ,
            produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Villesupports> getbiggestcitiesforsup(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Call the stored Procedure :
        StoredProcedureQuery procedureQuery =
            em.createStoredProcedureQuery("findBigCitiesPerSupport");
        List<Object[]> resultat = procedureQuery.getResultList();

        em.getTransaction().commit();
        em.close();

        // The list of OBJECTS :
        List<Villesupports> liste = new ArrayList<>();
        // Browse listePanneau :
        for(Object[] ob : resultat){
            // Create a new Object :
            Villesupports vs = new Villesupports();
            vs.setVille(String.valueOf(ob[0]));
            vs.setIdvil(Integer.parseInt(String.valueOf(ob[1])));
            vs.setTypesupport(String.valueOf(ob[2]));
            vs.setIdsupport(Integer.parseInt(String.valueOf(ob[3])));
            vs.setQuantite(Integer.parseInt(String.valueOf(ob[4])));
            liste.add(vs);
        }

        // Flush :
        return liste;
    }

}
