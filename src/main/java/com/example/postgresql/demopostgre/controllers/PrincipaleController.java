package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.*;
import com.example.postgresql.demopostgre.mesobjets.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

import org.apache.commons.codec.binary.Base64;

@Controller
public class PrincipaleController {

    private String UPLOADED_FOLDER = "D://DossierSpring//Fichiers//";

    // Attributes
    @Autowired
    HopitauxRepository hopitauxRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PanneauRepository panneauRepository;
    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    TypesRepository typesRepository;
    @Autowired
    LocalisationRepository localisationRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    VilleRepository villeRepository;
    @Autowired
    QuartierRepository quartierRepository;
    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    SuperficieRepository superficieRepository;
    @Autowired
    PanneauhistoRepository panneauhistoRepository;
    @Autowired
    RequetepanneauRepository requetepanneauRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    HorsligneRepository horsligneRepository;
    @Autowired
    ParametresRepository parametresRepository;

    @PersistenceUnit
    EntityManagerFactory emf;
    @Value("${adresselocal}")
    private String lienapp;




    // Methodes :
    @GetMapping(value="/getusersfromang", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Utilisateur> getusersfromang(){
        List<Utilisateur> listeUsers = utilisateurRepository.findAll();
        return listeUsers;
    }

    @PostMapping(value="/postusersfromang", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Utilisateur> postusersfromang(){
        List<Utilisateur> listeUsers = utilisateurRepository.findAll();
        return listeUsers;
    }

     @GetMapping(value={"/login", "/login/{id}"})
     public String login(Model model, @PathVariable Optional<Integer> id){
        if(id.isPresent()) model.addAttribute("message", "L'identifiant ou le mot de passe est incorrect");
        else model.addAttribute("message", "");
        //if(logout != null) model.addAttribute("message", "Vous vous etes deconnectes");
        return "login";
     }


    //@GetMapping(value="/acc")
    @GetMapping(value="/acc")
    public String accueil(Model model, Principal principal){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select c.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin >= now()").setMaxResults(20).getResultList();
        model.addAttribute("histoCommande", histoCommande);

        // Nouveaux supports
        String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Object nouveauSupport = emr.createQuery(
                "select count(a.idpan) from panneau a where " +
                        "a.datecreation ='"+dates+"'").getSingleResult();

        // Nombre de commandes :
        /*Object commandeTotal = emr.createQuery(
                "select count(idcom) from commande ").getSingleResult();*/
        //List<Commande> listCom = commandeRepository.findAll();
        // Nombre de panneaux :
        //List<Panneau> listPan = panneauRepository.findAll();
        Object panneauTotal = emr.createQuery(
                "select count(idpan) from panneau ").getSingleResult();
        // Cout total :
        Object coutTotal = emr.createQuery(
                "select sum(cout) from commande ").getSingleResult();
        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotal));
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        // Cout total ODP:
        Object coutTotalODP = emr.createQuery(
                "select sum(coutodp) from commande ").getSingleResult();
        long coutTotodp = (String.valueOf(coutTotalODP).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotalODP));

        // Nombre de clients :
        //List<Client> listCli = clientRepository.findAll();

        //model.addAttribute("nbrecommande", numberFormat.format(commandeTotal));
        model.addAttribute("nbrecommande", numberFormat.format(nouveauSupport));
        model.addAttribute("nbrepanneau", numberFormat.format(panneauTotal));
        /*model.addAttribute("coutTotal", numberFormat.format(coutTot));
        model.addAttribute("coutTotodp", numberFormat.format(coutTotodp));
        model.addAttribute("coutTotaux",
                numberFormat.format(coutTotodp+coutTot));*/
        model.addAttribute("coutTotal", numberFormat.format(1300000000));
        model.addAttribute("coutTotodp", numberFormat.format(200000000));
        model.addAttribute("coutTotaux",
                numberFormat.format(1500000000));
        emr.getTransaction().commit();
        //
        emr.close();


        // Get the user roles :
        Utilisateur utilisateur =
            utilisateurRepository.findByIdentifiant(
                principal.getName().trim());
        // Set the role
        model.addAttribute("role",utilisateur.getRoles());
        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "accueil";
    }

    // Reach DASHBOARD :
    @GetMapping(value="/dashboard")
    public String dashboard(Model model){
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "dashboard";
    }


    // hopital
    @GetMapping(value="/hopital")
    public String hopital(Model model){

        // Get the list of HOPITAUX :
        List<Hopitaux> listtHop = hopitauxRepository.findAll();
        model.addAttribute("taillehop", listtHop.size());

        return "hopital";
    }

    // client
    @GetMapping(value="/client")
    public String client(Model model){

        // Get the list of CLIENT :
        List<Client> listClient = clientRepository.findAll();
        model.addAttribute("listClient", listClient);

        return "client";
    }

    // Nouveau client
    @GetMapping(value="/nouvclient")
    public String nouvclient(Model model){

        // Get the list of CLIENT :
        List<Client> listClient = clientRepository.findAll();
        model.addAttribute("listClient", listClient);
        model.addAttribute("nouvclient",1);

        return "client";
    }

    // Ajouter nouveau client
    @PostMapping(value="/creerclient")
    public String creerclient(@RequestParam("email") String email,
                              @RequestParam("nom") String nom,
                              @RequestParam("contact") String contact,
                              Model model){

        // Get the list of CLIENT :
        Client cl = new Client();
        cl.setEmail(email);
        cl.setLibelle(nom);
        cl.setContact(contact);
        // Save :
        clientRepository.save(cl);

        // Get the list of CLIENT :
        List<Client> listClient = clientRepository.findAll();
        model.addAttribute("listClient", listClient);
        return "client";
    }

    // Modifier un client
    @GetMapping(value="/modifclient/{id}")
    public String modifclient(@PathVariable int id,
                              Model model){

        // Get the CLIENT :
        Client client = clientRepository.findByIdcli(id);

        // Get the list of CLIENT :
        List<Client> listClient = clientRepository.findAll();
        model.addAttribute("listClient", listClient);
        model.addAttribute("client", client);
        return "client";
    }


    // modifier client
    @PostMapping(value="/modifierclient")
    public String modifierclient(
            @RequestParam("id") String id,
            @RequestParam("email") String email,
            @RequestParam("nom") String nom,
            @RequestParam("contact") String contact,
            Model model, Principal principal){

        // Get the list of CLIENT :
        Client cl = clientRepository.findByIdcli(Integer.parseInt(id));
        cl.setEmail(email);
        cl.setLibelle(nom);
        cl.setContact(contact);
        // Save :
        clientRepository.save(cl);

        // track in the Journal :
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());
        saveJournal(utilisateur.getIdusr(), "Modification d'un client ID : "+id);

        // Get the list of CLIENT :
        List<Client> listClient = clientRepository.findAll();
        model.addAttribute("listClient", listClient);
        return "client";
    }


    public void saveJournal(int user,String action){
        Journal jrl = new Journal();
        jrl.setIdusr(user);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        try {
            jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            jrl.setHeure(heure);
        }
        catch (Exception exc){
        }
        jrl.setAction(action);
        journalRepository.save(jrl);
    }


    // POST :
    @PostMapping(value="/soumettrechoixpanneau")
    public String soumettrechoixpanneau(@RequestParam("commune") int commune,
        @RequestParam("quartier") int quartier,
        @RequestParam("secteur") int secteur,
        @RequestParam("choix") int choix,
        @RequestParam("lestypes") int lestypes,
        Model model){

        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        String filtre = (lestypes > 0) ? (" and a.types = "+lestypes) : "";
        //System.out.println(filtre);

        // traiter :
        switch (choix){
            case 0:
                // Afficher les points liés à une commune :
                List <Object[]> resPanneau = emr.createQuery(
                        "select a.libelle,e.libelle,c.libelle,a.emplacement,a.idpan,d.libelle," +
                                "a.image" +
                                " from panneau a inner join secteur b on a.idsec=b.idsec " +
                                " inner join quartier d on b.idquar=d.idqua inner join ville e " +
                                " on e.idvil=d.idvill " +
                                "inner join types c on a.types=c.idtyp"+
                        " where e.idvil = "+commune+filtre).getResultList();
                model.addAttribute("resPanneau",resPanneau);
                break;

            case 1:
                // Afficher les points liés à une quartier :
                List <Object[]> resPanneau1 = emr.createQuery(
                    "select a.libelle,e.libelle,c.libelle,a.emplacement,a.idpan,d.libelle," +
                    "a.image" +
                    " from panneau a inner join secteur b on a.idsec=b.idsec " +
                    " inner join quartier d on b.idquar=d.idqua inner join ville e " +
                    " on e.idvil=d.idvill " +
                    "inner join types c on a.types=c.idtyp"+
                    " where d.idqua = "+quartier+filtre).getResultList();
                model.addAttribute("resPanneau",resPanneau1);
                break;

            case 2:
                // Afficher les points liés à un secteur :
                List <Object[]> resPanneau2 = emr.createQuery(
                    "select a.libelle,e.libelle,c.libelle,a.emplacement,a.idpan,d.libelle," +
                    "a.image,b.libelle " +
                    " from panneau a inner join secteur b on a.idsec=b.idsec " +
                    " inner join quartier d on b.idquar=d.idqua inner join ville e " +
                    " on e.idvil=d.idvill " +
                    "inner join types c on a.types=c.idtyp"+
                    " where b.idsec = "+secteur+filtre).getResultList();
                model.addAttribute("resPanneauSecteur",resPanneau2);
                break;
        }

        emr.getTransaction().commit();
        //
        emr.close();

        // Get COMMUNES :
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listCommune",listCommune);

        // Get all support TYPES :
        List<Types> listeTypes = typesRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeTypes",listeTypes);
        // Set app link :
        model.addAttribute("lienapp", lienapp);

        //
        return "panneau";
    }


    // client
    @GetMapping(value="/panneau")
    public String panneau(Model model){

        // Get the TOWN where the DECLARATION has bee done :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> dixdernierssupp = emr.createQuery(
            "select a.libelle,e.libelle,c.libelle," +
                    "a.emplacement,a.idpan,d.libelle," +
                "a.image,b.libelle,a.heurecreation" +
                " from panneau a inner join secteur b on a.idsec=b.idsec " +
                " inner join quartier d on b.idquar=d.idqua inner join ville e " +
                " on e.idvil=d.idvill " +
                "inner join types c on a.types=c.idtyp order by a.idpan desc ").setMaxResults(10).getResultList();
        model.addAttribute("dixdernierssupp", dixdernierssupp);
        emr.getTransaction().commit();
        //
        emr.close();

        // Get all support TYPES :
        List<Types> listeTypes = typesRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeTypes",listeTypes);

        // Get COMMUNES :
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listCommune",listCommune);
        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "panneau";
    }

    // nouvpanneau
    @GetMapping(value="/nouvpanneau")
    public String nouvpanneau(Model model){

        // Get the TOWN where the DECLARATION has bee done :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resPanneau = emr.createQuery(
                "select a.libelle,b.libelle,c.libelle,a.emplacement,a.idpan" +
                        " from panneau a inner join taille b on a.idtai=b.idtai " +
                        "inner join types c on a.idtyp=c.idtyp").getResultList();
        model.addAttribute("resPanneau", resPanneau);

        // Get all TYPES
        List<Types> listTypes = typesRepository.findAll();
        // Get all TAILLE
        List<Taille> listTaille = tailleRepository.findAll();
        // Get all VILLE
        List<Ville> listVille = villeRepository.findAll();
        // Get all QUARTIER
        List<Quartier> listQuartier = quartierRepository.findAll();


        model.addAttribute("listTypes", listTypes);
        model.addAttribute("listTaille", listTaille);

        model.addAttribute("listVille", listVille);
        model.addAttribute("listQuartier", listQuartier);

        model.addAttribute("nouvpanneau", 1);

        emr.getTransaction().commit();
        //
        emr.close();

        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "panneau";
    }


    // Ajouter nouveau panneau
    @PostMapping(value="/creerpanneau")
    public String creerpanneau(@RequestParam("libelle") String libelle,
                               @RequestParam("taille") String taille,
                               @RequestParam("types") String types,
                               @RequestParam("emplacement") String emplacement,
                               @RequestParam("ville") String ville,
                               @RequestParam("quartier") String quartier,
                               Model model){

        // Get the list of CLIENT :
        Panneau pn = new Panneau();
        pn.setLibelle(libelle);
        //pn.setIdtai(Integer.parseInt(taille));
        //pn.setIdtyp(Integer.parseInt(types));
        pn.setEmplacement(emplacement);
        //pn.setIdvil(Integer.parseInt(ville));
        //pn.setIdqua(Integer.parseInt(quartier));

        // Save :
        panneauRepository.save(pn);

        // Get the list of PANNEAU :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resPanneau = emr.createQuery(
                "select a.libelle,b.libelle,c.libelle,a.emplacement,a.idpan" +
                        " from panneau a inner join taille b on a.idtai=b.idtai " +
                        "inner join types c on a.idtyp=c.idtyp").getResultList();
        model.addAttribute("resPanneau", resPanneau);
        emr.getTransaction().commit();
        //
        emr.close();

        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "panneau";
    }


    // Supprimer PANNEAUX :  :  supprimerpanneau
    @GetMapping(value="/supprimerpanneau/{id}")
    public String supprimerpanneau(@PathVariable int id,
                               Principal principal){
        //
        try {
            Panneau pn = panneauRepository.findByIdpan(id);
            if (pn != null) {
                panneauRepository.deleteByIdpan(id);

                // Save :
                Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());
                saveJournal(utilisateur.getIdusr(),
                        "Suppression du support ayant pour ID : "+
                        id);

                // Find the COMMANDE
                Commande cmde = commandeRepository.findByIdpan(pn.getIdpan());
                //
                if (cmde != null) {
                    commandeRepository.deleteByIdcom(cmde.getIdcom());
                }

                // Now delete the HISTORY :
                /*
                List<Panneauhisto> listPho =
                        panneauhistoRepository.findAllByIdpan(pn.getIdpan());
                if (listPho != null) {
                    for (Panneauhisto po : listPho) {
                        panneauhistoRepository.deleteByIdphis(po.getIdphis());
                    }
                }
                */
            }
        }
        catch (Exception exc){
            // Nothing :

        }

        return "redirect:/panneau";
    }


    // Modifier panneau
    @GetMapping(value="/modifpanneau/{id}")
    public String modifpanneau(@PathVariable int id,
                               Model model){

        // Get the PANNEAU :
        Panneau panneau = panneauRepository.findByIdpan(id);
        if(panneau == null)
            return "redirect:/panneau";

        // Get the user :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        Object resultat = emr.createQuery(
            "select a.nom from utilisateur a inner join panneau b on " +
            "a.idusr=b.idusr where idpan = "+id).getSingleResult();

        String utilisateur = (String)resultat;
        emr.getTransaction().commit();
        emr.close();

        //
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("panneau", panneau);

        // Get all TYPES
        List<Types> listTypes = typesRepository.findAll();
        // Get all TAILLE
        //List<Taille> listTaille = tailleRepository.findAll();
        // Get the SECTORS related to the quartier
        Secteur sctr = secteurRepository.findByIdsec(panneau.getIdsec());
        List<Secteur> listSecteur = secteurRepository.findAllByIdquar(sctr.getIdquar());
        //List<Secteur> listSecteur = secteurRepository.findAll();


        model.addAttribute("listTypes", listTypes);
        //model.addAttribute("listTaille", listTaille);
        //model.addAttribute("listVille", listVille);
        //model.addAttribute("listQuartier", listQuartier);
        model.addAttribute("listSecteur", listSecteur);

        // Get COMMUNES :
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listCommune",listCommune);

        //emr.getTransaction().commit();
        //emr.close();

        // Get all support TYPES :
        List<Types> listeTypes = typesRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listeTypes",listeTypes);

        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "panneau";
    }


    //
    @PostMapping(value="/modifierpanneau")
    public String modifierpanneau(@RequestParam("id") int id,
                                  @RequestParam("libelle") String libelle,
                                  @RequestParam("types") int types,
                                  @RequestParam("emplacement") String emplacement,
                                  @RequestParam("secteur") int secteur,
                                  Model model, Principal principal){
        //
        //@RequestParam("taille") int taille,

        // Get the list of CLIENT :
        Panneau pn = panneauRepository.findByIdpan(id);

        // Get the previous TYPE
        Types tps = typesRepository.findByIdtyp(pn.getTypes());
        // Get the new one :
        // Get the previous TYPE
        Types tpsNew = typesRepository.findByIdtyp(types);

        pn.setLibelle(libelle);
        //pn.setTaille(taille);
        pn.setTypes(types);
        pn.setIdsec(secteur);
        pn.setEmplacement(emplacement);

        // Save :
        panneauRepository.save(pn);
        //System.out.println("Modification effectuée");


        // track in the Journal :
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());
        saveJournal(utilisateur.getIdusr(), "Modification du support ayant pour ID : "+
                id+". Ancien type : "+tps.getLibelle()+
                "   --   Nouveau type : "+tpsNew.getLibelle());


        // --------------------------
        // TRY To GET COMMANDE object :
        Commande cde = commandeRepository.findByIdpan(id);
        Commande cmde;
        if(cde == null) cmde = new Commande();
        else cmde = cde;
        //long donneeSupprime = commandeRepository.deleteByIdcom(cde.getIdcom());
        //System.out.println("Commande supprimée");

        try {
            Date tpdatedt = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-01-01");
            //cmde = new Commande();
            cmde.setDatedebut(tpdatedt);
            // Date Fin :
            Date tpdatefn = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-12-31");
            cmde.setDatefin(tpdatefn); // tpdatedt tpdatefn
            // Date actuelle :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            cmde.setDates(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            // Heure actuelle :
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            cmde.setHeure(heure);
            // Produit :
            cmde.setPdtpublier("--");

            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            Object population = emr.createQuery(
                    "select c.population " +
                            "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                            "join ville c on b.idvill=c.idvil " +
                            "where idsec="+pn.getIdsec()).getSingleResult();

            int populat = (Integer)population;
            emr.getTransaction().commit();
            emr.close();

            // Compute the COST
            Types typs = typesRepository.findByIdtyp(pn.getTypes());
            Taille tle = tailleRepository.findByIdtai(typs.getIdtail());
            //Taille tle = tailleRepository.findByIdtai(pn.getTaille());
            CalculTsp cTsp = new CalculTsp(populat, pn.getTypes());
            double cout = cTsp.calculMontantTsp(tpdatedt, tpdatefn, tle.getValeur());
            cmde.setCout((int) cout);

            // Calculer L'ODP si necessaire :
            emr = emf.createEntityManager();
            emr.getTransaction().begin();
            Object taxeODP = emr.createQuery(
                    "select c.taux " +
                            "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                            "join ville c on b.idvill=c.idvil " +
                            "where idsec=" + pn.getIdsec()).getSingleResult();

            int taux = (Integer) taxeODP;
            emr.getTransaction().commit();
            emr.close();
            //    * * * * * * * * *   Superficie ODP
            int[] supportIdOdp = {1,2,7,8,9,10,11,12,13,14,15};
            //System.out.println("Idtyp : "+pn.getTypes());
            //if(pn.getSuperficie() > 0) {
            //if(IntStream.of(supportIdOdp).anyMatch(x -> x == pn.getTypes())) {
                //taille = tailleRepository.findByIdtai(pn.getSuperficie());
            tle = tailleRepository.findByIdtai(typs.getIdtail());
            double coutODP = cTsp.calculMontantOdp(tpdatedt, tpdatefn,
                    tle.getValeur(), taux);
            //System.out.println("Cout ODP : "+coutODP);
            cmde.setCoutodp((int) coutODP);
            //} else cmde.setCoutodp(0);

            //cmde.setCout(Integer.parseInt(cout));
            // Panneau :
            cmde.setIdpan(id);
            // Client :
            cmde.setIdcli(1);

            Parametres prmts = parametresRepository.findByIdpar(1);
            if(prmts.getEnregsupport() == 1){
                // Save :
                commandeRepository.save(cmde);
            }
        }
        catch (Exception exc){
            saveJournal(utilisateur.getIdusr(),
                    "Exception durant modification panneau - userID : "+
                            id+"   --  Exception : "+exc.getMessage());
        }

        //
        try{
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List <Object[]> dixdernierssupp = emr.createQuery(
                    "select a.libelle,e.libelle,c.libelle," +
                            "a.emplacement,a.idpan,d.libelle," +
                            "a.image,b.libelle,a.heurecreation" +
                            " from panneau a inner join secteur b on a.idsec=b.idsec " +
                            " inner join quartier d on b.idquar=d.idqua inner join ville e " +
                            " on e.idvil=d.idvill " +
                            "inner join types c on a.types=c.idtyp order by a.idpan desc ").setMaxResults(10).getResultList();
            model.addAttribute("dixdernierssupp", dixdernierssupp);
            emr.getTransaction().commit();
            //
            emr.close();

            // Get COMMUNES :
            List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
            model.addAttribute("listCommune",listCommune);

            // Get all support TYPES :
            List<Types> listeTypes = typesRepository.findAllByOrderByLibelleAsc();
            model.addAttribute("listeTypes",listeTypes);
        }
        catch (Exception exc){
            saveJournal(utilisateur.getIdusr(),
                    "Exception durant recueil des données après " +
                            "modification du panneau - userID : "+
                            id+"   --  Exception : "+exc.getMessage());
        }

        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "panneau";
    }


    // nouvcom
    @GetMapping(value="/nouvcom")
    public String nouvcom(Model model){

        // Get the TOWN where the DECLARATION has bee done :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select a.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin >= now()").getResultList();
        model.addAttribute("histoCommande", histoCommande);

        // Get all CLIENT
        List<Client> listClient = clientRepository.findAll();
        // Get all TAILLE
        //List<Panneau> listPanneau = panneauRepository.findAll();

        model.addAttribute("listClient", listClient);
        //model.addAttribute("listPanneau", listPanneau);
        model.addAttribute("nouvcommande", 1);

        // Nombre de commandes :
        List<Commande> listCom = commandeRepository.findAll();
        // Nombre de panneaux :
        List<Panneau> listPan = panneauRepository.findAll();
        // Cout total :
        Object coutTotal = emr.createQuery(
                "select sum(cout) from commande ").getSingleResult();
        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotal));
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        // Cout total ODP:
        Object coutTotalODP = emr.createQuery(
                "select sum(coutodp) from commande ").getSingleResult();
        long coutTotodp = (String.valueOf(coutTotalODP).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotalODP));

        // Nombre de clients :
        //List<Client> listCli = clientRepository.findAll();
        // Get the communes :
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();

        model.addAttribute("nbrecommande", listCom.size());
        model.addAttribute("nbrepanneau", listPan.size());
        /*model.addAttribute("coutTotal", numberFormat.format(coutTot));
        model.addAttribute("coutTotodp", numberFormat.format(coutTotodp));
        model.addAttribute("coutTotaux",
                numberFormat.format(coutTotodp+coutTot));*/
        model.addAttribute("coutTotal", numberFormat.format(1300000000));
        model.addAttribute("coutTotodp", numberFormat.format(200000000));
        model.addAttribute("coutTotaux",
                numberFormat.format(1500000000));
        model.addAttribute("listCommune",listCommune);

        emr.getTransaction().commit();
        //
        emr.close();

        model.addAttribute("lienapp", lienapp);

        return "accueil";
    }


    // creercommande
    @PostMapping(value="/creercommande")
    public String creercommande(@RequestParam("debut") String debut,
                                @RequestParam("fin") String fin,
                                @RequestParam("produit") String produit,
                                @RequestParam("panneau") String panneau,
                                @RequestParam("client") String client,
                                Model model, Principal principal) throws Exception {

        //
        //System.out.println(debut);
        //System.out.println(fin);

        Date tpdatedt = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(debut));
        Commande cmde = new Commande();
        cmde.setDatedebut(tpdatedt);
        // Date Fin :
        Date tpdatefn  = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(fin));
        cmde.setDatefin(tpdatefn); // tpdatedt tpdatefn
        // Date actuelle :
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        cmde.setDates(new SimpleDateFormat("yyyy-MM-dd").
                parse(date));
        // Heure actuelle :
        String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        cmde.setHeure(heure);
        // Produit :
        cmde.setPdtpublier(produit);
        // Obtenir la population de la ville :
        Panneau pn = panneauRepository.findByIdpan(Integer.parseInt(panneau));

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        Object population = emr.createQuery(
                "select c.population " +
                        "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                        "join ville c on b.idvill=c.idvil " +
                        "where idsec="+pn.getIdsec()).getSingleResult();

        int populat = (Integer)population;
        emr.getTransaction().commit();
        emr.close();

        // Get TYPE
        Types typs = typesRepository.findByIdtyp(pn.getTypes());
        Taille taille = tailleRepository.findByIdtai(typs.getIdtail());
        //Taille taille = tailleRepository.findByIdtai(typs.getIdtail() pn.getTaille());

        //
        CalculTsp cTsp = new CalculTsp(populat, pn.getTypes());
        double cout = cTsp.calculMontantTsp(tpdatedt, tpdatefn,taille.getValeur());
        cmde.setCout((int)cout);

        // Calculer L'ODP si necessaire :
        emr = emf.createEntityManager();
        emr.getTransaction().begin();
        Object taxeODP = emr.createQuery(
                "select c.taux " +
                        "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                        "join ville c on b.idvill=c.idvil " +
                        "where idsec="+pn.getIdsec()).getSingleResult();

        int taux = (Integer)taxeODP;
        emr.getTransaction().commit();
        emr.close();
        //    * * * * * * * * *   Superficie ODP
        /*
        *     Verifier si le type du support correspond à ceux pour lesquels
        *     l'on doit calculer l'ODP
         */
        int[] supportIdOdp = {1,2,7,8,9,10,11,12,13,14,15};
        //if(pn.getSuperficie() > 0) {
        //if(IntStream.of(supportIdOdp).anyMatch(x -> x == pn.getTypes())) {
        //taille = tailleRepository.findByIdtai(pn.getSuperficie());
        taille = tailleRepository.findByIdtai(typs.getIdtail());
        double coutODP = cTsp.calculMontantOdp(tpdatedt, tpdatefn, taille.getValeur(), taux);
        //System.out.println("Cout ODP : "+coutODP);
        cmde.setCoutodp((int) coutODP);
        //}
        //else cmde.setCoutodp(0);

        //cmde.setCout(Integer.parseInt(cout));
        // Panneau :
        cmde.setIdpan(Integer.parseInt(panneau));
        // Client :
        cmde.setIdcli(Integer.parseInt(client));

        // track in the Journal :
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());

        // Save :
        Parametres prmts = parametresRepository.findByIdpar(1);
        if(prmts.getEnregsupport() == 1){
            commandeRepository.save(cmde);
            saveJournal(utilisateur.getIdusr(), "Ajout d'une commande !");
        }


        // Get the list of PANNEAU :
        emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select a.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin >= now()").getResultList();
        model.addAttribute("histoCommande", histoCommande);


        // Nombre de commandes :
        List<Commande> listCom = commandeRepository.findAll();
        // Nombre de panneaux :
        List<Panneau> listPan = panneauRepository.findAll();
        // Cout total :
        Object coutTotal = emr.createQuery(
                "select sum(cout) from commande ").getSingleResult();
        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotal));
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        // Cout total ODP:
        Object coutTotalODP = emr.createQuery(
                "select sum(coutodp) from commande ").getSingleResult();
        long coutTotodp = (String.valueOf(coutTotalODP).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotalODP));

        // Nombre de clients :
        //List<Client> listCli = clientRepository.findAll();

        model.addAttribute("nbrecommande", listCom.size());
        model.addAttribute("nbrepanneau", listPan.size());
        /*model.addAttribute("coutTotal", numberFormat.format(coutTot));
        model.addAttribute("coutTotodp", numberFormat.format(coutTotodp));
        model.addAttribute("coutTotaux",
                numberFormat.format(coutTotodp+coutTot));*/
        model.addAttribute("coutTotal", numberFormat.format(1300000000));
        model.addAttribute("coutTotodp", numberFormat.format(200000000));
        model.addAttribute("coutTotaux",
                numberFormat.format(1500000000));

        emr.getTransaction().commit();
        //
        emr.close();
        model.addAttribute("lienapp", lienapp);
        return "accueil";
    }


    // modifcommande
    @GetMapping(value="/modifcommande/{id}")
    public String modifcommande(@PathVariable int id,
                                Model model)  {

        //
        Commande cmde = commandeRepository.findByIdcom(id);
        // get the date in string
        String datedebut = new SimpleDateFormat("MM/dd/yyyy").format(cmde.getDatedebut());
        String datefin = new SimpleDateFormat("MM/dd/yyyy").format(cmde.getDatefin());

        // Get the list of PANNEAU :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select a.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin >= now()").getResultList();
        model.addAttribute("histoCommande", histoCommande);
        model.addAttribute("commande", cmde);

        // Find the support linked to the request :
        Panneau pnu = panneauRepository.findByIdpan(cmde.getIdpan());
        model.addAttribute("mpanneau", pnu);

        // Get all CLIENT
        List<Client> listClient = clientRepository.findAll();
        // Get all TAILLE
        //List<Panneau> listPanneau = panneauRepository.findAll();

        model.addAttribute("listClient", listClient);
        //model.addAttribute("listPanneau", listPanneau);

        model.addAttribute("datedebut", datedebut);
        model.addAttribute("datefin", datefin);

        // Nombre de commandes :
        List<Commande> listCom = commandeRepository.findAll();
        // Nombre de panneaux :
        List<Panneau> listPan = panneauRepository.findAll();
        // Cout total :
        Object coutTotal = emr.createQuery(
                "select sum(cout) from commande ").getSingleResult();
        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotal));
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        // Cout total ODP:
        Object coutTotalODP = emr.createQuery(
                "select sum(coutodp) from commande ").getSingleResult();
        long coutTotodp = (String.valueOf(coutTotalODP).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotalODP));
        // Nombre de clients :
        //List<Client> listCli = clientRepository.findAll();

        model.addAttribute("nbrecommande", listCom.size());
        model.addAttribute("nbrepanneau", listPan.size());
        /*model.addAttribute("coutTotal", numberFormat.format(coutTot));
        model.addAttribute("coutTotodp", numberFormat.format(coutTotodp));
        model.addAttribute("coutTotaux",
                numberFormat.format(coutTotodp+coutTot));*/

        model.addAttribute("coutTotal", numberFormat.format(1300000000));
        model.addAttribute("coutTotodp", numberFormat.format(200000000));
        model.addAttribute("coutTotaux",
                numberFormat.format(1500000000));

        emr.getTransaction().commit();
        emr.close();
        model.addAttribute("lienapp", lienapp);
        return "accueil";
    }


    // modifiercommande
    @PostMapping(value="/modifiercommande")
    public String modifiercommande(@RequestParam("id") String id,
                                   @RequestParam("debut") String debut,
                                   @RequestParam("fin") String fin,
                                   @RequestParam("produit") String produit,
                                   @RequestParam("panneau") String panneau,
                                   @RequestParam("client") String client,
                                   Model model, Principal principal) throws Exception {

        //
        //System.out.println(debut);
        //System.out.println(fin);

        Date tpdatedt = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(debut));
        Commande cmde = commandeRepository.findByIdcom(Integer.parseInt(id));
        cmde.setDatedebut(tpdatedt);
        // Date Fin :
        Date tpdatefn = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(fin));
        cmde.setDatefin(tpdatefn);
        // Date actuelle :
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        cmde.setDates(new SimpleDateFormat("yyyy-MM-dd").
                parse(date));
        // Heure actuelle :
        String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        cmde.setHeure(heure);
        // Produit :
        cmde.setPdtpublier(produit);

        // Obtenir la population de la ville :
        Panneau pn = panneauRepository.findByIdpan(Integer.parseInt(panneau));
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        Object population = emr.createQuery(
                "select c.population " +
                        "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                        "join ville c on b.idvill=c.idvil " +
                        "where idsec="+pn.getIdsec()).getSingleResult();

        int populat = (Integer)population;
        emr.getTransaction().commit();
        emr.close();

        // Compute the COST
        //Taille taille = tailleRepository.findByIdtai(pn.getTaille());
        Types typs = typesRepository.findByIdtyp(pn.getTypes());
        Taille taille = tailleRepository.findByIdtai(typs.getIdtail());
        CalculTsp cTsp = new CalculTsp(populat, pn.getTypes());
        double cout = cTsp.calculMontantTsp(tpdatedt, tpdatefn,taille.getValeur());
        cmde.setCout((int)cout);

        // Calculer L'ODP si necessaire :
        emr = emf.createEntityManager();
        emr.getTransaction().begin();
        Object taxeODP = emr.createQuery(
                "select c.taux " +
                        "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                        "join ville c on b.idvill=c.idvil " +
                        "where idsec="+pn.getIdsec()).getSingleResult();

        int taux = (Integer)taxeODP;
        emr.getTransaction().commit();
        emr.close();
        //    * * * * * * * * *   Superficie ODP
        int[] supportIdOdp = {1,2,7,8,9,10,11,12,13,14,15};
        //if(pn.getSuperficie() > 0) {
        //if(IntStream.of(supportIdOdp).anyMatch(x -> x == pn.getTypes())) {
        //taille = tailleRepository.findByIdtai(pn.getSuperficie());
        taille = tailleRepository.findByIdtai(typs.getIdtail());
        double coutODP = cTsp.calculMontantOdp(tpdatedt, tpdatefn, taille.getValeur(), taux);
        //System.out.println("Cout ODP : "+coutODP);
        cmde.setCoutodp((int) coutODP);
        //}
        //else cmde.setCoutodp(0);


        // cout
        //cmde.setCout(1000);
        //cmde.setCout(Integer.parseInt(cout));
        // Panneau :
        cmde.setIdpan(Integer.parseInt(panneau));
        // Client :
        cmde.setIdcli(Integer.parseInt(client));

        // track in the Journal :
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());

        Parametres prmts = parametresRepository.findByIdpar(1);
        if(prmts.getEnregsupport() == 1){
            // Save :
            commandeRepository.save(cmde);
            saveJournal(utilisateur.getIdusr(), "Modification d'une commande ID : "+id);
        }

        // Get the list of PANNEAU :
        emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select a.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin >= now()").getResultList();
        model.addAttribute("histoCommande", histoCommande);

        // Nombre de commandes :
        List<Commande> listCom = commandeRepository.findAll();
        // Nombre de panneaux :
        List<Panneau> listPan = panneauRepository.findAll();
        // Cout total :
        Object coutTotal = emr.createQuery(
                "select sum(cout) from commande ").getSingleResult();
        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotal));
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        // Cout total ODP:
        Object coutTotalODP = emr.createQuery(
                "select sum(coutodp) from commande ").getSingleResult();
        long coutTotodp = (String.valueOf(coutTotalODP).equals("null")) ?
                0 : Long.parseLong(String.valueOf(coutTotalODP));
        // Nombre de clients :
        //List<Client> listCli = clientRepository.findAll();

        model.addAttribute("nbrecommande", listCom.size());
        model.addAttribute("nbrepanneau", listPan.size());
        /*model.addAttribute("coutTotal", numberFormat.format(coutTot));
        model.addAttribute("coutTotodp", numberFormat.format(coutTotodp));
        model.addAttribute("coutTotaux",
                numberFormat.format(coutTotodp+coutTot));*/
        model.addAttribute("coutTotal", numberFormat.format(1300000000));
        model.addAttribute("coutTotodp", numberFormat.format(200000000));
        model.addAttribute("coutTotaux",
                numberFormat.format(1500000000));

        emr.getTransaction().commit();
        emr.close();

        model.addAttribute("lienapp", lienapp);
        return "accueil";
    }


    //rapports
    @GetMapping(value="/rapports")
    public String modifcommande(Model model)  {

        List<Ville> listeVille = villeRepository.findAll();
        model.addAttribute("listeVille", listeVille);
        // Set app link :
        model.addAttribute("lienapp", lienapp);

        return "rapport";
    }

    // genrap
    @PostMapping(value="/genrap")
    public HttpEntity<byte[]> genrap(@RequestParam("choixrapport") String choixrapport,
                                     @RequestParam("ville") String ville,
                                     @RequestParam("quartier") String quartier,
                                     @RequestParam("secteur") String secteur,
                                     @RequestParam("typerapport") String typerapport,
                                     @RequestParam("types") int types,
                                     HttpServletResponse response)
            throws Exception
    {

        // Get the list of PANNEAU :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        List <Object[]> histoCommande = null;
        switch (Integer.parseInt(choixrapport)){
            case 1:
                // Par client :
                /*histoCommande = emr.createQuery(
                        "select c.libelle,d.libelle,e.libelle,f.libelle,TO_CHAR(b.datedebut, 'yyyy-mm-dd') " +
                                "as dbut, TO_CHAR(b.datefin, 'yyyy-mm-dd') as dfin,b.cout from commande " +
                                "b inner join panneau c on b.idpan=c.idpan inner join secteur d on c.idsec=" +
                                "d.idsec inner join quartier e on e.idqua=d.idquar inner join ville f on " +
                                "f.idvil=e.idvill where b.idcli="+client).getResultList();
                */
                break;

            case 2:
                // par quartier
                histoCommande = emr.createQuery(
                        "select d.libelle as secteur, a.libelle as libpan,c.libelle as libtyp, a.emplacement , " +
                                "a.datecreation as dcreate " +
                                "from panneau a inner join " +
                                "secteur d on a.idsec=d.idsec inner join " +
                                "quartier b on d.idquar=b.idqua inner join types c on a.types=c.idtyp where b.idqua=" +
                                quartier).getResultList();
                break;

            case 3:
                // par commune :
                histoCommande = emr.createQuery(
                        "select  a.libelle as libpan,c.libelle as libqua,b.libelle as sect," +
                                " e.libelle as libtyp, a.emplacement " +
                                "from panneau a inner join " +
                                "secteur b on a.idsec=b.idsec inner join quartier c on b.idquar=c.idqua " +
                                "inner join " +
                                " types e on a.types=e.idtyp where c.idvill=" + ville).getResultList();
                break;

            case 5:
                // Par support :
                /*
                histoCommande = emr.createQuery(
                    "select d.libelle,count(a.idpan) as nbre from panneau a inner join " +
                    "secteur b on a.idsec=b.idsec inner join quartier c on b.idquar=c.idqua " +
                    "inner join ville d on c.idvill=d.idvil where a.types = "+ types+
                    " group by d.libelle order by d.libelle asc" ).getResultList();
                */

                histoCommande = emr.createQuery(
                    "select a.libelle,b.libelle,c.libelle,count(d.idpan) from " +
                    "ville a inner join quartier b on a.idvil=b.idvill inner join secteur c on " +
                    "b.idqua=c.idquar inner join panneau d on c.idsec=d.idsec where d.types = "+ types+
                    " group by a.libelle,b.libelle,c.libelle order by a.libelle asc" ).getResultList();

                break;

            case 6:
                // Par secteur :
                histoCommande = emr.createQuery(
                        "select  a.libelle as libpan," +
                                " e.libelle as libtyp, a.emplacement " +
                                "from panneau a inner join " +
                                " types e on a.types=e.idtyp where a.idsec=" + secteur).getResultList();
                break;

            default:
                histoCommande = null;
                break;
        }


        /*
        List <Object[]> histoQuartier = emr.createQuery(
                "select a.libelle as libpan,c.libelle as libtyp, a.emplacement " +
                        "from panneau a inner join quartier b on a.idqua=b.idqua inner join " +
                        "types c on a.idtyp=c.idtyp where b.idqua=" + quartier).getResultList();
        */
        emr.getTransaction().commit();
        // Close :
        emr.close();

        List<HistoCommandePanneau> donneeRapport = new ArrayList<HistoCommandePanneau>();
        HistoCommandePanneau hcp = new HistoCommandePanneau();
        //
        List<HistoRapportQuartier> histoRQ = new ArrayList<HistoRapportQuartier>();
        HistoRapportQuartier hrq = new HistoRapportQuartier();
        //
        List<HistoRapportVille> histoVI = new ArrayList<HistoRapportVille>();
        HistoRapportVille hrv = new HistoRapportVille();
        //
        List<HistoRapportSecteur> histoSR = new ArrayList<HistoRapportSecteur>();
        HistoRapportSecteur hrs = new HistoRapportSecteur();
        // Support :
        List<HistoRapportSupport> histoSP = new ArrayList<HistoRapportSupport>();
        HistoRapportSupport hrt = new HistoRapportSupport();
        //
        if(Integer.parseInt(choixrapport)==1) {
            hcp.support = "";
            hcp.dtedebut = "";
            hcp.dtefin = "";
            hcp.prix = "";
            hcp.ville = "";
            hcp.quartier = "";
            hcp.secteur = "";
            donneeRapport.add(hcp);

            NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);

            for(Object[] obj : histoCommande){
                hcp = new HistoCommandePanneau();

                hcp.support = String.valueOf(obj[0]);
                hcp.secteur = String.valueOf(obj[1]);
                hcp.quartier = String.valueOf(obj[2]);
                hcp.ville = String.valueOf(obj[3]);
                hcp.dtedebut = String.valueOf(obj[4]);
                hcp.dtefin = String.valueOf(obj[5]);
                long couts = Long.parseLong(String.valueOf(obj[6]));
                hcp.prix = numberFormat.format(couts);
                donneeRapport.add(hcp);
            }
        }
        else if(Integer.parseInt(choixrapport)==2) {
            hrq.libpan = "";
            hrq.libtype = "";
            hrq.emplacement = "";
            hrq.secteur = "";
            hrq.dcreate = "";
            //
            histoRQ.add(hrq);

            for (Object[] obj : histoCommande) {
                hrq = new HistoRapportQuartier();

                hrq.secteur = String.valueOf(obj[0]);
                hrq.libpan = String.valueOf(obj[1]);
                hrq.libtype = String.valueOf(obj[2]);
                hrq.emplacement = String.valueOf(obj[3]);
                hrq.dcreate = String.valueOf(obj[4]).replaceAll(" 00:00:00.0",
                        "");
                histoRQ.add(hrq);
            }
        }
        else if(Integer.parseInt(choixrapport)==3) {
            hrv.libpan = "";
            hrv.libtyp = "";
            hrv.libqua = "";
            hrv.emplacement = "";
            hrv.sect="";
            //
            histoVI.add(hrv);

            for (Object[] obj : histoCommande) {
                hrv = new HistoRapportVille();

                hrv.libqua = String.valueOf(obj[1]);
                hrv.libpan = String.valueOf(obj[0]);
                hrv.libtyp = String.valueOf(obj[3]);
                hrv.emplacement = String.valueOf(obj[4]);
                hrv.sect=String.valueOf(obj[2]);

                histoVI.add(hrv);
            }
        }
        else if(Integer.parseInt(choixrapport)==5) {
            // Par support :
            //List<HistoRapportSupport> histoSP = new ArrayList<HistoRapportSupport>();
            //HistoRapportSupport hrt = new HistoRapportSupport();

            hrt.libelle = "-";
            hrt.quartier = "-";
            hrt.secteur = "-";
            hrt.nbre = 0;
            histoSP.add(hrt);

            for (Object[] obj : histoCommande) {
                hrt = new HistoRapportSupport();
                hrt.libelle = String.valueOf(obj[0]); // Commune
                hrt.quartier = String.valueOf(obj[1]); // Commune
                hrt.secteur = String.valueOf(obj[2]); // Commune
                hrt.nbre = Integer.parseInt(String.valueOf(obj[3]));
                histoSP.add(hrt);
            }
        }
        else if(Integer.parseInt(choixrapport)==6) {
            // Par secteur :
            hrs.libpan = "";
            hrs.libtyp = "";
            hrs.emplacement = "";
            histoSR.add(hrs);

            for (Object[] obj : histoCommande) {
                hrs = new HistoRapportSecteur();
                hrs.libpan = String.valueOf(obj[0]);
                hrs.libtyp = String.valueOf(obj[1]);
                hrs.emplacement = String.valueOf(obj[2]);
                histoSR.add(hrs);
            }
        }

        //

        if(Integer.parseInt(choixrapport)==1) {
            if(Integer.parseInt(typerapport)==1) { // PDF

                // Get the CLIENT :
                Client mClient = clientRepository.findByIdcli(Integer.parseInt("0"));
                //Client mClient = clientRepository.findByIdcli(Integer.parseInt(client));

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(donneeRapport);
                InputStream inputStream =
                        this.getClass().getResourceAsStream("/reports/rapport.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

                // set the parameters :
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("itemdatasource", dataSource);
                parameters.put("client", mClient.getLibelle());

                /*response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", String.format(
                        "attachment; filename=\"rapport.pdf\""));*/
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

                /*
                OutputStream out = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                */
                HttpHeaders header = new HttpHeaders();
                final byte[] data;
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                header.setContentType(MediaType.APPLICATION_PDF);
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapport.pdf");
                header.setContentLength(data.length);

                return new HttpEntity<byte[]>(data, header);
            }
            else{
                // Fichier EXCEL :
                JRXlsxExporter exporter = new JRXlsxExporter();

                Client mClient = clientRepository.findByIdcli(Integer.parseInt("0"));

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(donneeRapport);
                InputStream inputStream =
                        this.getClass().getResourceAsStream("/reports/rapport.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

                // set the parameters :
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("itemdatasource", dataSource);
                parameters.put("client", mClient.getLibelle());

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


                //sending excel file to browser
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                final byte[] rawBytes;

                try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                    // Set CONFIGURATION :
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(false);
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    xlsxExporter.setConfiguration(configuration);

                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                    xlsxExporter.exportReport();

                    rawBytes = xlsReport.toByteArray();
                }
                HttpHeaders header = new HttpHeaders();
                final byte[] data;

                data= rawBytes;
                header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapport.xlsx");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
        }
        else if(Integer.parseInt(choixrapport)==2) {

            Quartier mQuartier = quartierRepository.findByIdqua(Integer.parseInt(quartier));
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(histoRQ);
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/reports/rapportquartier.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // set the parameters :
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("itemdatasource", dataSource);
            parameters.put("quartier", mQuartier.getLibelle());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, dataSource);

            if(Integer.parseInt(typerapport)==1) { // PDF
                HttpHeaders header = new HttpHeaders();
                final byte[] data;
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                header.setContentType(MediaType.APPLICATION_PDF);
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportquartier.pdf");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
            else{
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                final byte[] rawBytes;

                try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                    // Set CONFIGURATION :
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(false);
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    xlsxExporter.setConfiguration(configuration);

                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                    xlsxExporter.exportReport();

                    rawBytes = xlsReport.toByteArray();
                }
                HttpHeaders header = new HttpHeaders();
                final byte[] data;

                data= rawBytes;
                header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportquartier.xlsx");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }

        }
        else if(Integer.parseInt(choixrapport)==3) {
            Ville mVille = villeRepository.findByIdvil(Integer.parseInt(ville));

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(histoVI);
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/reports/rapportville.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // set the parameters :
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("itemdatasource", dataSource);
            parameters.put("ville", mVille.getLibelle());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, dataSource);

            if(Integer.parseInt(typerapport)==1) { // PDF
                HttpHeaders header = new HttpHeaders();
                final byte[] data;
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                header.setContentType(MediaType.APPLICATION_PDF);
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportcommune.pdf");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
            else{
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                final byte[] rawBytes;

                try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                    // Set CONFIGURATION :
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(false);
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    xlsxExporter.setConfiguration(configuration);

                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                    xlsxExporter.exportReport();

                    rawBytes = xlsReport.toByteArray();
                }
                HttpHeaders header = new HttpHeaders();
                final byte[] data;

                data= rawBytes;
                header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportcommune.xlsx");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
        }
        else if(Integer.parseInt(choixrapport)==5) {
            // Support :
            Types typs = typesRepository.findByIdtyp(types);
            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(histoSP);
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/reports/rapportsupport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // set the parameters :
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("itemdatasource", dataSource);
            parameters.put("support", typs.getLibelle());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, dataSource);

            if(Integer.parseInt(typerapport)==1) { // PDF
                HttpHeaders header = new HttpHeaders();
                final byte[] data;
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                header.setContentType(MediaType.APPLICATION_PDF);
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportsupport.pdf");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
            else{
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                final byte[] rawBytes;

                try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                    // Set CONFIGURATION :
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(false);
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    xlsxExporter.setConfiguration(configuration);

                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                    xlsxExporter.exportReport();
                    rawBytes = xlsReport.toByteArray();
                }
                HttpHeaders header = new HttpHeaders();
                final byte[] data;

                data= rawBytes;
                header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportsupport.xlsx");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }

        }
        else if(Integer.parseInt(choixrapport)==6) {
            Secteur str = secteurRepository.findByIdsec(Integer.parseInt(ville));

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(histoSR);
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/reports/rapportsecteur.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // set the parameters :
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("itemdatasource", dataSource);
            parameters.put("secteur", str.getLibelle());
            parameters.put("totalpanneaux", String.valueOf(histoCommande.size()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, dataSource);

            if(Integer.parseInt(typerapport)==1) { // PDF
                HttpHeaders header = new HttpHeaders();
                final byte[] data;
                data = JasperExportManager.exportReportToPdf(jasperPrint);
                header.setContentType(MediaType.APPLICATION_PDF);
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportsecteur.pdf");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
            else{
                JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                final byte[] rawBytes;

                try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                    // Set CONFIGURATION :
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(false);
                    configuration.setRemoveEmptySpaceBetweenRows(true);
                    xlsxExporter.setConfiguration(configuration);

                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                    xlsxExporter.exportReport();

                    rawBytes = xlsReport.toByteArray();
                }
                HttpHeaders header = new HttpHeaders();
                final byte[] data;

                data= rawBytes;
                header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportsecteur.xlsx");
                header.setContentLength(data.length);
                return new HttpEntity<byte[]>(data, header);
            }
        }

        return null;
    }


    private byte[] convertToImg(String base64) throws IOException
    {
        return Base64.decodeBase64(base64);
    }

    public void writeByteToImageFile(byte[] imgBytes, String imgFileName) throws IOException
    {
        File imgFile = new File(imgFileName);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));
        ImageIO.write(img, "png", imgFile);
    }


    @GetMapping(value="/fichier")
    public String prendfichier(Model model){
        Ville vile = villeRepository.findByIdvil(11);
        model.addAttribute("images", vile.getLibelle());

        return "testimage";
    }



    public void computePrix(int idpan,int idsec,int idtai,int idtyp,
                            int superficie) {
        try {
            // First get SUPPORT types :
            String date =
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            Date tpdatedebut = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2023-01-01");
            Date tpdatefin = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2023-12-31");
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String datej = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Browse :
            int i = 0;
                i++;

                Commande cmde = new Commande();
                cmde.setDatedebut(tpdatedebut);
                cmde.setDatefin(tpdatefin);
                cmde.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(datej));
                cmde.setHeure(heure);
                cmde.setPdtpublier("--");

                EntityManager emr = emf.createEntityManager();
                emr.getTransaction().begin();
                Object population = emr.createQuery(
                        "select c.population " +
                                "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                                "join ville c on b.idvill=c.idvil " +
                                "where idsec=" + idsec ).getSingleResult();
                //  pn.getIdsec()

                int populat = (Integer) population;
                emr.getTransaction().commit();
                emr.close();

                //
                // Compute the COST  -- pn.getTaille()
                Types typs = typesRepository.findByIdtyp(idtyp);
                //Taille taille = tailleRepository.findByIdtai(typs.getIdtail());
                Taille taille = tailleRepository.findByIdtai(typs.getIdtail());
                CalculTsp cTsp = new CalculTsp(populat, idtyp);  // pn.getTypes()
                double cout = cTsp.calculMontantTsp(tpdatedebut, tpdatefin, taille.getValeur());
                cmde.setCout((int) cout);

                // Calculer L'ODP si necessaire :
                emr = emf.createEntityManager();
                emr.getTransaction().begin();
                Object taxeODP = emr.createQuery(
                        "select c.taux " +
                                "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                                "join ville c on b.idvill=c.idvil " +
                                "where idsec=" +idsec ).getSingleResult();
                // (pn.getIdsec()

                int taux = (Integer) taxeODP;
                emr.getTransaction().commit();
                emr.close();
                //    * * * * * * * * *   Superficie ODP :
                int[] supportIdOdp = {1,2,7,8,9,10,11,12,13,14,15};
                //if(pn.getSuperficie() > 0) {
                if(IntStream.of(supportIdOdp).anyMatch(x -> x == idtyp)) {
                    //taille = tailleRepository.findByIdtai(pn.getSuperficie());
                    taille = tailleRepository.findByIdtai(typs.getIdtail());
                    double coutODP = cTsp.calculMontantOdp(tpdatedebut, tpdatefin, taille.getValeur(), taux);
                    //System.out.println("Cout ODP : "+coutODP);
                    cmde.setCoutodp((int) coutODP);
                }
                else cmde.setCoutodp(0);

                cmde.setIdpan(idpan); //pn.getIdpan()
                // Client :
                cmde.setIdcli(1);

                Parametres prmts = parametresRepository.findByIdpar(1);
                if(prmts.getEnregsupport() == 1){
                    // Save :
                    commandeRepository.save(cmde);
                }
        }
        catch (Exception exc){
            System.out.println("Exception exc :  "+exc.getLocalizedMessage());
        }
    }



    @PostMapping(value="/sendnewsupport", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Quetecreation sendnewsupport(@RequestBody Beannewpanneau bu,
                              HttpServletRequest request){
        //
        Quetecreation ret = new Quetecreation();
        int idPanBack = 0;

        // Check if the USER's account is ACTIVE :
        Utilisateur usr = utilisateurRepository.findByIdusr(bu.getIdusr());
        try {
            Panneau panneau = new Panneau();
            panneau.setLibelle(bu.getLibelle());
            panneau.setSuperficie(bu.getSuperficie());
            panneau.setTypes(bu.getTypes());
            panneau.setTaille(bu.getTaille());
            panneau.setLatitude(bu.getLatitude());
            panneau.setLongitude(bu.getLongitude());
            panneau.setEmplacement(bu.getEmplacement());
            panneau.setIdsec(bu.getSecteur());
            panneau.setImage(bu.getImage());
            panneau.setDateheure(bu.getDateheure());
            // Date actuelle :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            panneau.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            // Heure actuelle :
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            ret.setLibelle(heure);
            panneau.setHeurecreation(heure);
            // Ajout de l'ID User :
            panneau.setIdusr(bu.getIdusr());
            // Save :
            try {
                idPanBack = panneauRepository.save(panneau).getIdpan();
            }
            catch (Exception exc){
            }


            // Save user action :
            // Save in the journal
            Journal jrl = new Journal();
            jrl.setIdusr(bu.getIdusr());
            try {
                jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                jrl.setHeure(heure);

                jrl.setAction("Ajout du support "+bu.getLibelle());
                journalRepository.save(jrl);
            }
            catch (Exception exc){
            }



            // Get the ID of the one just inserted :
            Panneau pn = panneauRepository.
                    findByDatecreationAndHeurecreationAndDateheureAndIdusr(
                            new SimpleDateFormat("yyyy-MM-dd").
                                    parse(date), heure, bu.getDateheure(), bu.getIdusr());
                    /*.findByDatecreationAndHeurecreationAndDateheure(
                            new SimpleDateFormat("yyyy-MM-dd").
                    parse(date), heure, dateheure);*/
            if(pn != null){
                Panneauhisto panneauHisto = new Panneauhisto();
                panneauHisto.setLatitude(bu.getLatitude());
                panneauHisto.setLongitude(bu.getLongitude());
                panneauHisto.setImage(bu.getImage());
                // Date actuelle :
                panneauHisto.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                // Heure actuelle :
                panneauHisto.setHeurecreation(heure);
                panneauHisto.setIdpan(pn.getIdpan());
                panneauHisto.setIduser(bu.getIdusr());
                // Save :
                try {
                    panneauhistoRepository.save(panneauHisto);
                }
                catch (Exception exc){
                }

                // Now, compute :
                computePrix(pn.getIdpan(),bu.getSecteur(),
                        bu.getTaille(),
                        bu.getTypes(), bu.getSuperficie());
            }
        }
        catch (Exception exc){
            saveJournal(bu.getIdusr(), "exception sendnewfile : "+  exc.toString());
            ret.setId(0);
            return ret;
        }

        ret.setId(idPanBack);
        return ret;
    }



    // genrap
    @PostMapping(value="/sendfile")
    @ResponseBody
    public String savefichier(@RequestParam("image") String image,
                              @RequestParam("latitude") String latitude,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("libelle") String libelle,
                              @RequestParam("taille") String taille,
                              @RequestParam("type") String type,
                              @RequestParam("emplacement") String emplacement,
                              @RequestParam("superficie") String superficie,
                              @RequestParam("secteur") String secteur,
                              @RequestParam("dateheure") String dateheure,
                              @RequestParam("idusr") int idusr,
                              HttpServletRequest request){

        // Check if the USER's account is ACTIVE :
        Utilisateur usr = utilisateurRepository.findByIdusr(idusr);
        if(usr.getActif()==0) return "nok";

        try {

            Panneau panneau = new Panneau();
            panneau.setLibelle(libelle);
            panneau.setSuperficie(Integer.parseInt(superficie));
            panneau.setTypes(Integer.parseInt(type));
            panneau.setTaille(Integer.parseInt(taille));
            panneau.setLatitude(Double.parseDouble(latitude));
            panneau.setLongitude(Double.parseDouble(longitude));
            panneau.setEmplacement(emplacement);
            panneau.setIdsec(Integer.parseInt(secteur));
            panneau.setImage(image);
            panneau.setDateheure(dateheure);
            // Date actuelle :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            panneau.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            // Heure actuelle :
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            panneau.setHeurecreation(heure);
            // Ajout de l'ID User :
            panneau.setIdusr(idusr);
            // Save :
            try {
                panneauRepository.save(panneau);
            }
            catch (Exception exc){
                saveJournal(idusr, "exception support ajout : "+exc.toString());
                return "nok";
            }


            // Save user action :
            // Save in the journal
            Journal jrl = new Journal();
            jrl.setIdusr(idusr);
            try {
                jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                jrl.setHeure(heure);

                jrl.setAction("Ajout du support "+libelle);
                journalRepository.save(jrl);
            }
            catch (Exception exc){
                saveJournal(idusr, "exception support creation : "+exc.toString());
            }



            // Get the ID of the one just inserted :
            Panneau pn = panneauRepository.
                findByDatecreationAndHeurecreationAndDateheureAndIdusr(
                    new SimpleDateFormat("yyyy-MM-dd").
                        parse(date), heure, dateheure,idusr);
                    /*.findByDatecreationAndHeurecreationAndDateheure(
                            new SimpleDateFormat("yyyy-MM-dd").
                    parse(date), heure, dateheure);*/
            if(pn != null){
                Panneauhisto panneauHisto = new Panneauhisto();
                panneauHisto.setLatitude(Double.parseDouble(latitude));
                panneauHisto.setLongitude(Double.parseDouble(longitude));
                panneauHisto.setImage(image);
                // Date actuelle :
                panneauHisto.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                // Heure actuelle :
                panneauHisto.setHeurecreation(heure);
                panneauHisto.setIdpan(pn.getIdpan());
                panneauHisto.setIduser(idusr);
                // Save :
                try {
                    panneauhistoRepository.save(panneauHisto);
                }
                catch (Exception exc){
                    saveJournal(idusr,
                            "exception historique creation : "+exc.toString());
                }

                // Now, compute :
                computePrix(pn.getIdpan(),Integer.parseInt(secteur),
                        Integer.parseInt(taille),
                        Integer.parseInt(type), Integer.parseInt(superficie));
            }

            //String base64 = image;
            //byte[] base64Val = convertToImg(base64);
            //writeByteToImageFile(base64Val, path+"\\image.png");
            //writeByteToImageFile(base64Val, UPLOADED_FOLDER+"image.png");
            //System.out.println("Saved");
        }
        catch (Exception exc){
            saveJournal(idusr, "exception sendfile : "+  exc.toString());
            //System.out.println("Erreur : "+exc.toString());
            return "nok";
        }

        return "ok";
    }


    // Upload from mobile
    @PostMapping(value="/sendfilemob")
    @ResponseBody
    public String sendfilemob(@RequestParam("image") String image,
                              @RequestParam("latitude") String latitude,
                              @RequestParam("longitude") String longitude,
                              @RequestParam("libelle") String libelle,
                              @RequestParam("taille") String taille,
                              @RequestParam("type") String type,
                              @RequestParam("emplacement") String emplacement,
                              @RequestParam("superficie") String superficie,
                              @RequestParam("secteur") String secteur,
                              @RequestParam("dateheure") String dateheure,
                              @RequestParam("idusr") int idusr,
                              HttpServletRequest request){
        String retour ="ok";

        try {

            // check if USER has the right to UPLOAD :
            Horsligne lgne = horsligneRepository.findByIdusr(idusr);

            // Check if the USER's account is ACTIVE :
            Utilisateur usr = utilisateurRepository.findByIdusr(idusr);
            if(usr.getActif()==0) return "nok";

            //System.out.println("idusr : "+idusr);
            if(lgne!=null) {
                if(lgne.getAutorisation()==0){
                    Panneau panneau = new Panneau();
                    panneau.setLibelle(libelle);
                    panneau.setSuperficie(Integer.parseInt(superficie));
                    panneau.setTypes(Integer.parseInt(type));
                    panneau.setTaille(Integer.parseInt(taille));
                    panneau.setLatitude(Double.parseDouble(latitude));
                    panneau.setLongitude(Double.parseDouble(longitude));
                    panneau.setEmplacement(emplacement);
                    panneau.setIdsec(Integer.parseInt(secteur));
                    panneau.setImage(image);
                    panneau.setDateheure(dateheure);
                    // Date actuelle :
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    panneau.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                            parse(date));
                    // Heure actuelle :
                    String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    panneau.setHeurecreation(heure);
                    // Ajout de l'ID User :
                    panneau.setIdusr(idusr);

                    // Save :
                    try {
                        panneauRepository.save(panneau);
                    }
                    catch (Exception exc){
                        saveJournal(idusr, "exception support upload : "+exc.toString());
                        return "ko";
                    }


                    // Save user action :
                    // Save in the journal
                    Journal jrl = new Journal();
                    jrl.setIdusr(idusr);
                    try {
                        jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                                parse(date));
                        jrl.setHeure(heure);
                    } catch (Exception exc) {
                    }
                    jrl.setAction("Ajout du support (synchronisation) depuis le MOBILE " + libelle);
                    journalRepository.save(jrl);


                    // Get the ID of the one just inserted :
                    Panneau pn = panneauRepository.
                            findByDatecreationAndHeurecreationAndDateheureAndIdusr(
                                    new SimpleDateFormat("yyyy-MM-dd").
                                            parse(date), heure, dateheure, idusr);
                    /*.findByDatecreationAndHeurecreationAndDateheure(
                            new SimpleDateFormat("yyyy-MM-dd").
                    parse(date), heure, dateheure);*/
                    if (pn != null) {
                        Panneauhisto panneauHisto = new Panneauhisto();
                        panneauHisto.setLatitude(Double.parseDouble(latitude));
                        panneauHisto.setLongitude(Double.parseDouble(longitude));
                        panneauHisto.setImage(image);
                        // Date actuelle :
                        panneauHisto.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                                parse(date));
                        // Heure actuelle :
                        panneauHisto.setHeurecreation(heure);
                        panneauHisto.setIdpan(pn.getIdpan());
                        panneauHisto.setIduser(idusr);

                        try {
                            // Save :
                            panneauhistoRepository.save(panneauHisto);
                        }
                        catch (Exception exc){
                            saveJournal(idusr, "exception histo upload : "+exc.toString());
                            return "ko";
                        }

                        // Now, compute :
                        computePrix(pn.getIdpan(), Integer.parseInt(secteur),
                                Integer.parseInt(taille),
                                Integer.parseInt(type), Integer.parseInt(superficie));
                    }
                }
                else retour ="nok";
            }
            else retour ="nok";
        }
        catch (Exception exc){
            System.out.println("Erreur : "+exc.toString());
        }

        return retour;
    }



    // genrap
    @PostMapping(value="/sendmodiffile")
    @ResponseBody
    public String modifySupportPicture(@RequestParam("image") String image,
                                       @RequestParam("latitude") String latitude,
                                       @RequestParam("longitude") String longitude,
                                       @RequestParam("supportid") String supportid,
                                       @RequestParam("rqtpanneau") int rqtpanneau,
                                       @RequestParam("userid") int userid,
                                       HttpServletRequest request){
        try {
            Panneauhisto panneauHisto = new Panneauhisto();
            panneauHisto.setLatitude(Double.parseDouble(latitude));
            panneauHisto.setLongitude(Double.parseDouble(longitude));
            panneauHisto.setImage(image);
            // Date actuelle :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            panneauHisto.setDatecreation(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            // Heure actuelle :
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            panneauHisto.setHeurecreation(heure);
            panneauHisto.setIdpan(Integer.parseInt(supportid));
            panneauHisto.setIduser(userid);

            // Save :
            panneauhistoRepository.save(panneauHisto);

            // Track :
            // Save in the journal
            Journal jrl = new Journal();
            jrl.setIdusr(userid);
            try {
                jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                jrl.setHeure(heure);
            }
            catch (Exception exc){

            }
            jrl.setAction("Mise à jour du support Id: "+supportid);
            journalRepository.save(jrl);


            //
            if(rqtpanneau==1){
                // Means that the user updated the support's picture :
                //   because no other user will receive an update foir this PANEL :
                Panneau pn = panneauRepository.findByIdpan(Integer.parseInt(supportid));
                Requetepanneau rqtPn = requetepanneauRepository.findByIdpanAndEtat(pn.getIdpan(),0);
                // Update the request :
                rqtPn.setEtat(1);
                requetepanneauRepository.save(rqtPn);

                // Now send a mail :
                Utilisateur utilis = utilisateurRepository.findByIdusr(rqtPn.getIdadmin());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(utilis.getEmail());
                message.setSubject("Bonsoir");
                message.setText("La demande a ete traitee");
                emailSender.send(message);
            }
        }
        catch (Exception exc){
            System.out.println("Erreur : "+exc.toString());
        }

        return "ok";
    }





    // @ResponseBody
    @GetMapping(value="/getville", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Ville> savefichierget(){

        //List<Ville> lstVille = villeRepository.findAll();
        List<Ville> lstVille = villeRepository.findAllByOrderByLibelleAsc();
        return lstVille;

    }


    // @ResponseBody
    @PostMapping(value="/getville", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Ville> savefichierpost(){

        //List<Ville> lstVille = villeRepository.findAll();
        List<Ville> lstVille = villeRepository.findAllByOrderByLibelleAsc();
        return lstVille;

    }


    // @ResponseBody
    @PostMapping(value="/getquartier/{id}", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quartier> postquartier(@PathVariable int id){

        //List<Quartier> lstQuartier = quartierRepository.findAllByIdvill(id);
        List<Quartier> lstQuartier = quartierRepository.findAllByIdvillOrderByLibelleAsc(id);
        return lstQuartier;

    }


    // @ResponseBody
    @GetMapping(value="/getquartier/{id}", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quartier> getquartier(@PathVariable int id){
        //List<Quartier> lstQuartier = quartierRepository.findAllByIdvill(id);
        List<Quartier> lstQuartier = quartierRepository.findAllByIdvillOrderByLibelleAsc(id);
        return lstQuartier;
    }


    @PostMapping(value="/getlinkedquartier", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quartier> getlinkedquartier(@RequestBody Quete qe){
        //
        return quartierRepository.findAllByIdvillOrderByLibelleAsc(Integer.valueOf(qe.getCode()));
    }

    @PostMapping(value="/getlinkedsecteur", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Secteur> getlinkedsecteur(@RequestBody Quete qe){
        //
        return secteurRepository.findAllByIdquarOrderByLibelleAsc(Integer.valueOf(qe.getCode()));
    }


    // @ResponseBody
    @PostMapping(value="/getsecteur/{id}", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Secteur> postsecteur(@PathVariable int id){
        //List<Secteur> lstSecteur = secteurRepository.findAllByIdquar(id);
        List<Secteur> lstSecteur = secteurRepository.findAllByIdquarOrderByLibelleAsc(id);
        return lstSecteur;
    }

    // @ResponseBody
    @PostMapping(value="/getnewsecteur", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Secteur> getnewsecteur(){
        return secteurRepository.findAll();
    }

    @PostMapping(value="/getnewville", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Ville> getnewville(){
        return villeRepository.findAll();
    }

    @PostMapping(value="/getnewquartier", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quartier> getnewquartier(){
        return quartierRepository.findAll();
    }


    @PostMapping(value="/savenewquartier", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Quete savenewquartier(@RequestBody Quetecreation qn){
        //
        Quartier qr = new Quartier();
        qr.setIdvill(qn.getId());
        qr.setLibelle(qn.getLibelle());
        quartierRepository.save(qr);
        //
        Quete qe = new Quete();
        qe.setCode("ok");
        return qe;
    }


    @PostMapping(value="/savenewsecteur", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Quete savenewsecteur(@RequestBody Quetecreation qn){
        //
        Secteur sr = new Secteur();
        sr.setIdquar(qn.getId());
        sr.setLibelle(qn.getLibelle());
        secteurRepository.save(sr);
        //
        Quete qe = new Quete();
        qe.setCode("ok");
        return qe;
    }


    // @ResponseBody
    @GetMapping(value="/getsecteur/{id}", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Secteur> getsecteur(@PathVariable int id){
        //List<Secteur> lstSecteur = secteurRepository.findAllByIdquar(id);
        List<Secteur> lstSecteur = secteurRepository.findAllByIdquarOrderByLibelleAsc(id);
        return lstSecteur;
    }



    // @ResponseBody
    @PostMapping(value="/getpanneausecteur/{id}", produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<RecapitulatifPanneau> getpanneausecteur(@PathVariable int id){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resPanneau = emr.createQuery(
                "select a.idpan,a.libelle,a.taille,a.emplacement,a.idsec,a.superficie," +
                        "a.datecreation, a.heurecreation, a.longitude, a.latitude " +
                        ", a.dateheure from panneau a where a.idsec="+id).getResultList();
        emr.getTransaction().commit();

        //List<HistoCommandePanneau> donneeRapport = new ArrayList<HistoCommandePanneau>();
        List<RecapitulatifPanneau> donneeRecapitulatifPanneau = new ArrayList<RecapitulatifPanneau>();
        RecapitulatifPanneau recapitulatifPanneau = new RecapitulatifPanneau();



        for(Object[] obj : resPanneau){
            recapitulatifPanneau = new RecapitulatifPanneau();
            recapitulatifPanneau.identifiant = String.valueOf(obj[0]);
            recapitulatifPanneau.libelle = String.valueOf(obj[1]);
            recapitulatifPanneau.dates = String.valueOf(obj[6]).replaceAll(" 00:00:00.0","");
            recapitulatifPanneau.heure = String.valueOf(obj[7]);
            recapitulatifPanneau.emplacement = String.valueOf(obj[3]);
            recapitulatifPanneau.dateheure = String.valueOf(obj[10]);

            donneeRecapitulatifPanneau.add(recapitulatifPanneau);
        }

        //List<Panneau> listePanneau = panneauRepository.findAllByIdsec(id);
        // Close :
        emr.close();
        return donneeRecapitulatifPanneau;
    }






    // @ResponseBody
    /*@PostMapping(value="/getpanneausecteur/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Panneau> postpanneausecteurjson(@PathVariable int id){
        List<Panneau> listePanneau = panneauRepository.findAllByIdsec(id);
        return listePanneau;
    }
    */



    // // Cout total :
    //        Object coutTotal = emr.createQuery(
    //                "select sum(cout) from commande ").getSingleResult();
    //        long coutTot = (String.valueOf(coutTotal).equals("null")) ?
    //                0 : Long.parseLong(String.valueOf(coutTotal));


    // @ResponseBody
    @GetMapping(value="/getpanneausecteurtotal/{id}/{userid}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<SupportTotSecteur> getpanneausecteurtotal(@PathVariable int id,
        @PathVariable int userid){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        // Get Supports number per SECTOR :
        Object supportTotal = emr.createQuery(
            "select count(a.idpan) from panneau a where a.idsec = "+
                id).getSingleResult();
        int supportTot = (String.valueOf(supportTotal).equals("null")) ?
            0 : Integer.parseInt(String.valueOf(supportTotal));

        // Get the supports the USER has created :
        String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Object supportUserTotal = emr.createQuery(
                "select count(a.idpan) from panneau a where a.datecreation = '"+
                        dates+"' and a.idusr="+userid+" and a.idsec="+
                        id).getSingleResult();
        int supportUserTot = (String.valueOf(supportUserTotal).equals("null")) ?
                0 : Integer.parseInt(String.valueOf(supportUserTotal));

        //SupportTotSecteur sts = new SupportTotSecteur("secteur",supportTot);
        List<SupportTotSecteur> listeRetour = new ArrayList<SupportTotSecteur>();
        listeRetour.add(new SupportTotSecteur(dates,supportTot,
                supportUserTot));

        emr.getTransaction().commit();
        emr.close();

        return listeRetour;
    }


    // @ResponseBody
    @PostMapping(value="/getnewpanneausecteurtotal",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<SupportTotSecteur> getnewpanneausecteurtotal(@RequestBody Queterequest qt){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();

        // Get Supports number per SECTOR :
        Object supportTotal = emr.createQuery(
                "select count(a.idpan) from panneau a where a.idsec = "+
                        qt.getId()).getSingleResult();
        int supportTot = (String.valueOf(supportTotal).equals("null")) ?
                0 : Integer.parseInt(String.valueOf(supportTotal));

        // Get the supports the USER has created :
        String dates = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Object supportUserTotal = emr.createQuery(
                "select count(a.idpan) from panneau a where a.datecreation = '"+
                        dates+"' and a.idusr="+qt.getUserid()+" and a.idsec="+
                        qt.getId()).getSingleResult();
        int supportUserTot = (String.valueOf(supportUserTotal).equals("null")) ?
                0 : Integer.parseInt(String.valueOf(supportUserTotal));

        //SupportTotSecteur sts = new SupportTotSecteur("secteur",supportTot);
        List<SupportTotSecteur> listeRetour = new ArrayList<SupportTotSecteur>();
        listeRetour.add(new SupportTotSecteur(dates,supportTot,
                supportUserTot));

        emr.getTransaction().commit();
        emr.close();

        return listeRetour;
    }



    // @ResponseBody
    @GetMapping(value="/getpanneausecteur/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Panneau> getpanneausecteurjson(@PathVariable int id){
        //List<Panneau> listePanneau = panneauRepository.findAllByIdsec(id);

        // Afficher les dix derniers PANNEAUX du SECTEUR :
        List<Panneau> listePanneau =
            panneauRepository.findTop5ByIdsecOrderByDatecreationDesc(id);
        return listePanneau;
    }


    @PostMapping(value="/getnewpanneausecteur", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Panneau> getnewpanneausecteur(@RequestBody Quete qe){
        List<Panneau> listePanneau =
                panneauRepository.findTop5ByIdsecOrderByDatecreationDesc(
                        Integer.parseInt(qe.getCode()));
        return listePanneau;
    }


    // get All Communes :
    @GetMapping(value="/getallcommunes/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Ville> getallcommunesjson(@PathVariable int id){
        List<Ville> listeVille = villeRepository.findAllByIdvil(id);
        //List<Ville> listeVille = villeRepository.findAll();
        return listeVille;
    }

    // get All Quartiers :
    @GetMapping(value="/getallquartiers/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Quartier> getallquartiersjson(@PathVariable int id){
        List<Quartier> listeQuartier =
                quartierRepository.findAllByIdvillOrderByLibelleAsc(id);
        //List<Quartier> listeQuartier = quartierRepository.findAll();
        return listeQuartier;
    }


    // getallsecteurs
    @GetMapping(value="/getallsecteurs/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<GetSecteur> getallsecteursjson(@PathVariable int id){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resSecteur = emr.createQuery(
                "select c.idsec,c.libelle,c.idquar from ville a inner join quartier b on " +
                        "a.idvil=b.idvill inner join secteur c on b.idqua=c.idquar " +
                        "where a.idvil ="+id).getResultList();
        emr.getTransaction().commit();
        emr.close();

        //fg
        List<GetSecteur> donneeSecteur = new ArrayList<GetSecteur>();
        GetSecteur gSecteur = new GetSecteur();

        for(Object[] obj : resSecteur){

            gSecteur = new GetSecteur();
            gSecteur.setIdsec((Integer)obj[0]);
            gSecteur.setLibelle(String.valueOf(obj[1]));
            gSecteur.setIdqua((Integer)obj[2]);
            donneeSecteur.add(gSecteur);
        }

        //List<Secteur> listeSecteur = secteurRepository.findAll();
        return donneeSecteur;
    }


    // getalltailles
    @GetMapping(value="/getalltailles", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Taille> getalltaillesjson(){
        List<Taille> listeTaille = tailleRepository.findAll();
        return listeTaille;
    }

    // getalltailles
    @GetMapping(value="/getalltypes", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Types> getalltypesjson(){
        List<Types> listeTypes = typesRepository.findAll();
        return listeTypes;
    }





    // Get TAILLE :
    @RequestMapping(path={"/gettaille"},
        method={ RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<Taille> gettaille(){
        List<Taille> liste = tailleRepository.findAll();
        return liste;
    }

    // Get TYPES :
    @RequestMapping(path={"/gettypes"},
        method={ RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<Types> gettypes(){
        List<Types> liste = typesRepository.findAllByOrderByLibelleAsc();
        //List<Types> liste = typesRepository.findAll();
        return liste;
    }


    // Get SUPERFICIE :
    @GetMapping(value="/getsuperficie", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Superficie> getsuperficie(){
        List<Superficie> liste = superficieRepository.findAll();
        return liste;
    }





    //   ##########################    Taille :
    // client
    @GetMapping(value="/taille")
    public String accueiltaille(Model model){

        // Get the list of CLIENT :
        List<Taille> listTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listTaille);
        return "taille";
    }

    // Nouveau client
    @GetMapping(value="/nouveltaille")
    public String nouveltaille(Model model){

        // Get the list of CLIENT :
        List<Taille> listTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listTaille);
        model.addAttribute("nouveltaille",1);
        return "taille";
    }



    // Ajouter nouveau client
    @PostMapping(value="/creertaille")
    public String creertaille(@RequestParam("libelle") String libelle,
                              @RequestParam("valeur") double valeur,
                              Model model){

        // Get the list of CLIENT :
        Taille tl = new Taille();
        tl.setLibelle(libelle);
        tl.setValeur(valeur);
        // Save :
        tailleRepository.save(tl);

        // Get the list of CLIENT :
        List<Taille> listTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listTaille);
        return "taille";
    }



    // Modifier un client
    @GetMapping(value="/modiftaille/{id}")
    public String modiftaille(@PathVariable int id,
                              Model model){
        // Get the CLIENT :
        Taille taille = tailleRepository.findByIdtai(id);
        // Get the list of CLIENT :
        List<Taille> listTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listTaille);
        model.addAttribute("taille", taille);
        return "taille";
    }


    // modifier client
    @PostMapping(value="/modifiertaille")
    public String modifiertaille(
            @RequestParam("id") String id,
            @RequestParam("libelle") String libelle,
            @RequestParam("valeur") double valeur,
            Model model){

        // Get the list of CLIENT :
        Taille taille = tailleRepository.findByIdtai(Integer.parseInt(id));
        taille.setLibelle(libelle);
        taille.setValeur(valeur);
        // Save :
        tailleRepository.save(taille);

        // Get the list of CLIENT :
        List<Taille> listTaille = tailleRepository.findAll();
        model.addAttribute("listTaille", listTaille);
        return "taille";
    }


    // Afficher un panneau
    @GetMapping(value="/affichpanneau/{id}")
    public String affichpanneau(@PathVariable int id,
                                Model model) {
        // Get the PANNEAU :
        Panneau panneau = panneauRepository.findByIdpan(id);
        model.addAttribute("latitude", panneau.getLatitude());
        model.addAttribute("longitude", panneau.getLongitude());
        model.addAttribute("image", panneau.getImage());

        return "afficherpanneau";
    }



    @GetMapping(value="/affichpanneauimage/{id}")
    public String affichpanneauimage(@PathVariable int id, Model model){
        Panneau panneau = panneauRepository.findByIdpan(id);
        model.addAttribute("images", panneau.getImage());
        return "testimage";
    }


}
