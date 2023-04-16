package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.HorsligneRepository;
import com.example.postgresql.demopostgre.depots.JournalRepository;
import com.example.postgresql.demopostgre.depots.UtilisateurRepository;
import com.example.postgresql.demopostgre.mesobjets.Beanutilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.persistence.*;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UtilisateurController {

    //  A T T R I B U T E S :
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    HorsligneRepository horsligneRepository;

    @Autowired
    JavaMailSender emailSender;

    @PersistenceUnit
    EntityManagerFactory emf;


    // Liste des utilisateurs
    @GetMapping(value="/gestionuser")
    public String gestionuser(Model model){
        // Get the list of CLIENT :
        //List<Utilisateur> listDonnees = utilisateurRepository.findAll();
        List<String> liste = new ArrayList<String>();
        liste.add("admin");
        liste.add("utilisateur");
        liste.add("superadmin");
        List<Utilisateur> listDonnees = utilisateurRepository.findByRolesIn(liste);
        model.addAttribute("listDonnees", listDonnees);
        return "utilisateur";
    }


    // Liste des utilisateurs
    @GetMapping(value="/horsligne")
    public String horsligne(Model model){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resHorsligne = emr.createQuery(
                "select a.nom,a.identifiant,b.autorisation,b.idhor " +
                        "from utilisateur a inner join horsligne b on " +
                        "a.idusr=b.idusr").getResultList();
        emr.getTransaction().commit();
        emr.close();

        model.addAttribute("resHorsligne", resHorsligne);
        return "utilisateurright";
    }


    // nouvright
    @GetMapping(value="/nouvright")
    public String nouvright(Model model){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resHorsligne = emr.createQuery(
                "select a.nom,a.identifiant,b.autorisation,b.idhor " +
                        "from utilisateur a inner join horsligne b on " +
                        "a.idusr=b.idusr").getResultList();
        emr.getTransaction().commit();
        emr.close();

        // Liste Utilisateur :
        List<Utilisateur> listUtilisateur = utilisateurRepository.findAll();
        model.addAttribute("listUtilisateur", listUtilisateur);
        model.addAttribute("resHorsligne", resHorsligne);
        model.addAttribute("nouvrt", 0);
        return "utilisateurright";
    }




    // modifutilisateurrt
    @GetMapping(value="/modifutilisateurrt/{id}")
    public String modifutilisateurrt(@PathVariable int id,Model model){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resHorsligne = emr.createQuery(
                "select a.nom,a.identifiant,b.autorisation,b.idhor " +
                        "from utilisateur a inner join horsligne b on " +
                        "a.idusr=b.idusr").getResultList();
        emr.getTransaction().commit();
        emr.close();

        // Get The USER :
        Utilisateur utr = utilisateurRepository.findByIdusr(id);

        // Liste Utilisateur :
        model.addAttribute("resHorsligne", resHorsligne);
        model.addAttribute("utilisateur", utr);
        return "utilisateurright";
    }


    // Ajouter nouvel utilisateur
    @PostMapping(value="/creert")
    public String creert(@RequestParam("utilisateur") int utilisateur,
                                   @RequestParam("autorisation") int autorisation,
                                   Principal principal,
                                   Model model) {

        Utilisateur urequest = utilisateurRepository.findByIdusr(utilisateur);

        // Check first :
        Horsligne hExiste = horsligneRepository.findByIdusr(utilisateur);
        if(hExiste==null){
            // Create :
            // Get the list of CLIENT :
            Horsligne hl = new Horsligne();
            hl.setAutorisation(autorisation);
            hl.setIdusr(utilisateur);
            horsligneRepository.save(hl);

            //
            Utilisateur ur =
                    utilisateurRepository.findByIdentifiant(
                            principal.getName().trim());
            saveJournal(ur.getIdusr(), "L'utilisateur a ajouté une nouvelle autorisation" +
                    " pour le compte : "+urequest.getNom());
        }
        else{
            hExiste.setAutorisation(autorisation);
            hExiste.setIdusr(utilisateur);
            horsligneRepository.save(hExiste);

            //
            Utilisateur ur =
                    utilisateurRepository.findByIdentifiant(
                            principal.getName().trim());
            saveJournal(ur.getIdusr(), "L'utilisateur a modifié l'autorisation" +
                    " du compte : "+urequest.getNom());
        }


        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resHorsligne = emr.createQuery(
                "select a.nom,a.identifiant,b.autorisation,b.idhor " +
                        "from utilisateur a inner join horsligne b on " +
                        "a.idusr=b.idusr").getResultList();
        emr.getTransaction().commit();
        emr.close();

        model.addAttribute("resHorsligne", resHorsligne);
        return "utilisateurright";
    }


    // Ajouter nouvel utilisateur
    @PostMapping(value="/modifiert")
    public String modifiert(@RequestParam("utilisateur") int utilisateur,
                         @RequestParam("autorisation") int autorisation,
                         Principal principal,
                         Model model) {

        // Get the list of CLIENT :
        Horsligne hl = horsligneRepository.findByIdusr(utilisateur);
        if(hl!=null) {
            hl.setAutorisation(autorisation);
            hl.setIdusr(utilisateur);
            horsligneRepository.save(hl);
            Utilisateur urequest = utilisateurRepository.findByIdusr(utilisateur);
            //
            Utilisateur ur =
                    utilisateurRepository.findByIdentifiant(
                            principal.getName().trim());
            saveJournal(ur.getIdusr(), "L'utilisateur a modifié l'autorisation" +
                    " du compte : " + urequest.getNom());
        }

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resHorsligne = emr.createQuery(
                "select a.nom,a.identifiant,b.autorisation,b.idhor " +
                        "from utilisateur a inner join horsligne b on " +
                        "a.idusr=b.idusr").getResultList();
        emr.getTransaction().commit();
        emr.close();

        model.addAttribute("resHorsligne", resHorsligne);
        return "utilisateurright";
    }



    // Get user with their password :
    @GetMapping(value="/gestionuserpwd")
    public String gestionuserpwd(Model model){
        // Get the list of CLIENT :
        List<Utilisateur> listDonnees = utilisateurRepository.findAll();
        model.addAttribute("listDonnees", listDonnees);
        return "utilisateurpwd";
    }


    // Nouvel utilisateur
    @GetMapping(value="/nouvutilisateur")
    public String nouvutilisateur(Model model){
        // Get the list of utilisateur :
        List<Utilisateur> listDonnees = utilisateurRepository.findAll();
        model.addAttribute("listDonnees", listDonnees);
        model.addAttribute("nouvutilisateur",1);
        return "utilisateur";
    }

    // Ajouter nouvel utilisateur
    @PostMapping(value="/creerutilisateur")
    public String creerutilisateur(@RequestParam("email") String email,
                                   @RequestParam("nom") String nom,
                                   @RequestParam("role") String role,
                                   @RequestParam("etatcompte") int etatcompte,
                                   @RequestParam("numero") String numero,
                                   Model model){

        // Get the list of CLIENT :
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setActif(etatcompte);
        utilisateur.setEmail(email);
        String[] tampon = email.split("@");
        utilisateur.setIdentifiant(tampon[0].toLowerCase());
        utilisateur.setNumero(numero);

        // Check if the user mail is not in use :
        Utilisateur checkUtilisateur = utilisateurRepository.findByIdentifiant(tampon[0].toLowerCase());
        if ( email.toLowerCase().indexOf("@") == -1 ) {
            model.addAttribute("warninguserid",
                    "L'adresse email a été mal renseigné !!!");
        }
        else if(checkUtilisateur==null){
            // Existe , Interdire la creation :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String heure = new SimpleDateFormat("HH:mm").format(new Date());

            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //String hashedPassword = passwordEncoder.encode(password);
            /*String motPasse = date.replaceAll("-","")+
                heure.replaceAll(":","");*/
            String motPasse = heure.replaceAll(":","");
            utilisateur.setPassword(motPasse);
            utilisateur.setRoles(role);

            // Save :
            utilisateurRepository.save(utilisateur);

            // Envoi du mail :
            mailCreation("Création de compte",
                    tampon[0].toLowerCase(),
                    motPasse,
                    email);

            // Get the list of UTILISATEURS :
            List<Utilisateur> listDonnees = utilisateurRepository.findAll();
            model.addAttribute("listDonnees", listDonnees);
            model.addAttribute("motPasse", motPasse);
        }
        else{
            // Identifier EXISTS :
            model.addAttribute("warninguserid",
                    "L'identifiant existe déjà en base !!!");
        }

        return "utilisateur";
    }


    private void mailCreation(String objet, String identifiant, String motpasse,
                              String adresseMail){
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,
                    "utf-8");
            StringBuilder contenu = new StringBuilder();
            contenu.append("<h2> Informations relatives au compte </h2>");
            contenu.append("<div><p>Identifiant : <span style='font-weight:bold;'>"+identifiant+"</span></p></div>");
            contenu.append("<div><p>Mot de passe : <span style='font-weight:bold;'>"+motpasse+"</span></p></div>");
            //contenu.append("<div><p>Mot de passe : "+motpasse+"</p></div>");
            contenu.append(
                    "<div><p>Lien de l'application : http://www.gestdp.com/gestpann/acc</p></div>");
            //
            helper.setText(String.valueOf(contenu), true);
            helper.setTo(adresseMail);
            helper.setSubject(objet);
            helper.setFrom("bendressouarnaud@gmail.com");
            helper.setCc("ngbandamakonan@gmail.com");
            emailSender.send(mimeMessage);
        }
        catch (Exception exc){
            //
        }
    }

    // Modifier un utilisateur
    @GetMapping(value="/modifutilisateur/{id}")
    public String modifutilisateur(@PathVariable int id,
                                   Model model){
        // Get the utilisateur :
        Utilisateur utilisateur = utilisateurRepository.findByIdusr(id);
        // Get the list of CLIENT :
        List<Utilisateur> listDonnees = utilisateurRepository.findAll();
        model.addAttribute("listDonnees", listDonnees);
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateur";
    }

    // modifier client
    @PostMapping(value="/modifierutilisateur")
    public String modifierutilisateur(
            @RequestParam("id") String id,
            @RequestParam("email") String email,
            @RequestParam("nom") String nom,
            @RequestParam("role") String role,
            @RequestParam("etatcompte") int etatcompte,
            @RequestParam("numero") String numero,
            Model model){

        // Get the list of utilisateur :
        Utilisateur utilisateur = utilisateurRepository.findByIdusr(Integer.parseInt(id));
        utilisateur.setNom(nom);
        utilisateur.setActif(etatcompte);
        utilisateur.setEmail(email);
        String[] tampon = email.split("@");
        utilisateur.setIdentifiant(tampon[0]);
        utilisateur.setRoles(role);
        utilisateur.setNumero(numero);

        // Save :
        utilisateurRepository.save(utilisateur);

        // Get the list of CLIENT :
        List<Utilisateur> listDonnees = utilisateurRepository.findAll();
        model.addAttribute("listDonnees", listDonnees);
        return "utilisateur";
    }

    // check user credentials :
    @GetMapping(value="/checkuser/{userid}/{userpwd}/{profile}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck checkuser(@PathVariable String userid, @PathVariable String userpwd
            , @PathVariable String profile){

        ReponseUserCheck ruc = new ReponseUserCheck();
        Utilisateur user = utilisateurRepository.findByIdentifiantAndPasswordAndRolesAndActif
                (userid,userpwd,profile,1);
        //Utilisateur user = utilisateurRepository.findByIdentifiantAndPassword(userid,userpwd);
        if(user==null){
            ruc.setNom("aucun");
            ruc.setUserid(0);
        }
        else{
            ruc.setNom(user.getNom());
            ruc.setUserid(user.getIdusr());
            saveJournal(user.getIdusr(),"Utilisateur connecté depuis un smartphone");
        }
        return ruc;
    }



    @CrossOrigin("*")
    @PostMapping(value="/authmobileusermac", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Beanutilisateur authmobileusermac(@RequestBody UserLog ug){
        //
        Beanutilisateur br = new Beanutilisateur();
        Utilisateur ur = utilisateurRepository.findByIdentifiantAndPasswordAndRolesAndActif
                (ug.getIdentifiant(),ug.getMotdepasse(),ug.getProfil(),1);

        br.setIdusr(ur==null ? 0 : ur.getIdusr());
        br.setIdentifiant(ur==null ? "" : ur.getIdentifiant());
        br.setMotdepasse(ur==null ? "" : ur.getPassword());
        br.setToken("");
        br.setFcmtoken("");
        if(ur != null)
            saveJournal(ur.getIdusr(),"Utilisateur connecté depuis un smartphone");

        return br;
    }




    // Save in the journal
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

}
