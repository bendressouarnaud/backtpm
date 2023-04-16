package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.*;
import com.example.postgresql.demopostgre.mesobjets.InfoImage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.security.Principal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WebServicesUtilisateur {

    // Attributes :
    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    QuartierRepository quartierRepository;
    @Autowired
    VilleRepository villeRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RequetepanneauRepository requetepanneauRepository;

    @Autowired
    JavaMailSender emailSender;


    @PersistenceUnit
    EntityManagerFactory emf;


    // check user credentials :
    @GetMapping(value="/sendsecteur/{secteur}/{idquartier}/{iduser}/{quartiersecteur}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseUserCheck getsecteurfromuser(@PathVariable String secteur,
        @PathVariable int idquartier,
        @PathVariable int iduser,
        @PathVariable int quartiersecteur
        ){

        //
        ReponseUserCheck ruc = new ReponseUserCheck();

        if(quartiersecteur==0) {           //  Secteur :
            /* First check if the name of the sector does not exist already for
                the specified QUARTIER
                */
            Secteur searchSecteur = secteurRepository.
                findByLibelleAndIdquar(secteur.trim().toLowerCase(),
                    idquartier);
            if(searchSecteur!=null){
                // Le secteur existe deja :
                Quartier qtr = quartierRepository.findByIdqua(idquartier);
                saveJournal(iduser,
                        "Utilisateur a tenté d'ajouter le secteur "+
                                secteur+" au quartier "+qtr.getLibelle()+
                                " depuis le smartphone !");
                ruc.setNom("nok");
                ruc.setUserid(1);
            }
            else {
                Secteur sctr = new Secteur();
                sctr.setIdquar(idquartier);
                sctr.setLibelle(secteur);
                // Save :
                secteurRepository.save(sctr);

                // Now save in JOURNAL :
                Quartier qtr = quartierRepository.findByIdqua(idquartier);
                saveJournal(iduser,
                    "Utilisateur a ajouté le secteur " +
                    secteur+" au quartier "+qtr.getLibelle()+
                            " depuis le smartphone !");

                ruc.setNom("ok");
                ruc.setUserid(1);
            }
        }
        else if(quartiersecteur==1) {           //  Quartier :

            // Check first if the QUARTIER does not already for the COMMUNE specified :
            Quartier searchQuartier = quartierRepository.
                    findByLibelleAndIdvill(secteur.trim().toLowerCase(),
                            idquartier);
            if(searchQuartier!=null){
                // Le secteur existe deja :
                Ville vle = villeRepository.findByIdvil(idquartier);
                saveJournal(iduser,
                        "Utilisateur a tenté d'ajouter le quartier "+
                                secteur+" à la commune "+vle.getLibelle()+
                                " depuis le smartphone !");
                ruc.setNom("nok");
                ruc.setUserid(1);
            }
            else {
                Quartier qtr = new Quartier();
                qtr.setIdvill(idquartier);
                qtr.setLibelle(secteur);
                // Save :
                quartierRepository.save(qtr);

                // Now save in JOURNAL :
                saveJournal(iduser,
                        "Utilisateur ayant créé un quartier depuis le smartphone !");

                ruc.setNom("ok");
                ruc.setUserid(1);
            }
        }

        //
        return ruc;
    }




    // check user credentials :
    @GetMapping(value="/getvilledata/{idville}",
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ReponseGetVille getvilledata(@PathVariable int idville
    ){

        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> donneesQuartier = new ArrayList<Object[]>();

        //
        donneesQuartier = emr.createQuery(
            "select d.libelle,count(c.idqua) as nbrequartier " +
            "from quartier c inner join ville d on c.idvill=d.idvil " +
            "where d.idvil = "+idville +
            " group by d.libelle").getResultList();

        //
        List<ReponseGetVille> listeObjet = new ArrayList<ReponseGetVille>();
        ReponseGetVille rgv = new ReponseGetVille();

        // Browse  :
        for(Object[] ob : donneesQuartier){
            // Create a new Object :
            rgv = new ReponseGetVille();
            rgv.setVille((String)ob[0]);
            rgv.setNbrequartier((Long)ob[1]);
            //rgv.setNbresecteur((Long)ob[2]);
            //rgv.setNbrepan((Long)ob[3]);
        }

        List <Object[]> donneesSecteur = new ArrayList<Object[]>();
        //
        donneesSecteur = emr.createQuery(
            "select d.libelle,count(b.idsec) as nbresecteur from secteur b inner join " +
            "quartier c on b.idquar=c.idqua inner join ville d on c.idvill=d.idvil " +
            "where d.idvil = "+idville +
            " group by d.libelle").getResultList();
        // Browse  :
        for(Object[] ob : donneesSecteur){
            // Create a new Object :
            //rgv = new ReponseGetVille();
            //rgv.setVille((String)ob[0]);
            //rgv.setNbrequartier((Long)ob[1]);
            rgv.setNbresecteur((Long)ob[1]);
            //rgv.setNbrepan((Long)ob[3]);
        }

        //
        List <Object[]> donneesPanneau = new ArrayList<Object[]>();
        //
        donneesPanneau = emr.createQuery(
                "select d.libelle,count(a.idpan) from " +
                        "panneau a inner join secteur b on a.idsec=b.idsec inner join " +
                        "quartier c on b.idquar=c.idqua inner join ville d on c.idvill=d.idvil " +
                        "where d.idvil = "+idville +
                        " group by d.libelle").getResultList();
        // Browse  :
        for(Object[] ob : donneesPanneau){
            rgv.setNbrepan((Long)ob[1]);
        }


        //
        emr.getTransaction().commit();
        emr.close();

        //
        return rgv;
    }





    // serve WebSelect :
    @GetMapping(value="/webselect")
    public String servewebselect(){
        //
        return "webselect";
    }


    // execute WebSelect :
    @PostMapping (value="/sendwebselect")
    public String sendwebselect(@RequestParam("requete") String requete,
        Model model){

        String URL = "jdbc:mysql://35.192.148.69:3306/municipalite?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USERNAME = "arnaud";
        String PASSWORD = "arnaud";

        try {
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    requete);

            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();

            String[] tabColumn = new String[columnCount];
            //ArrayList<String> columns = new ArrayList<>();
            int j = 0;
            int save_i = 0;
            //if(columnCount>1) {
            for (int i = 1; i <= columnCount; i++) {
                tabColumn[i-1] = metadata.getColumnName(i);
            }
            //save_i++;
            //if (columnCount > 0) tabColumn[j] = metadata.getColumnName(save_i);
            //}
            //else tabColumn[0] = metadata.getColumnName(columnCount);

            model.addAttribute("colonnes",tabColumn);
            model.addAttribute("taille",columnCount);

            //System.out.println("Nombre de colonnes --- : "+columnCount);

            //model.addAttribute("donnees",columns);
            connection.close();


            //   Run the query :
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> resultatRequete =
                    emr.createQuery(requete).getResultList();
            model.addAttribute("resultatRequete",resultatRequete);
            // Close :
            emr.getTransaction().commit();
            emr.close();
            model.addAttribute("ok","ok");
            //System.out.println("Nombre de donnees --- : "+resultatRequete.size());
        }
        catch (Exception exc){
            System.out.println("Erreur requete --- : "+exc.toString());
        }
        finally {
            model.addAttribute("requete",requete);
        }

        //
        return "webselect";
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


    // @ResponseBody
    @GetMapping(value="/getpanneausecteurinfo/{id}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<RetourSupportTexte> getpanneausecteurinfo(@PathVariable int id){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> resPanneau = emr.createQuery(
            "select a.libelle,a.longitude, a.latitude,a.emplacement " +
            " from panneau a where a.idsec="+id).getResultList();
        emr.getTransaction().commit();

        List<RetourSupportTexte> dataRetourSupportTexte = new ArrayList<RetourSupportTexte>();
        RetourSupportTexte retourSupportTexte;

        // Parcourir :
        for(Object[] obj : resPanneau){
            retourSupportTexte = new RetourSupportTexte();
            retourSupportTexte.setLibelle(String.valueOf(obj[0]));
            retourSupportTexte.setLongitude(Double.valueOf(String.valueOf(obj[1])));
            retourSupportTexte.setLatitude(Double.valueOf(String.valueOf(obj[2])));
            retourSupportTexte.setEmplacement(String.valueOf(obj[3]));

            //
            dataRetourSupportTexte.add(retourSupportTexte);
        }

        //
        emr.close();
        return dataRetourSupportTexte;
    }



    @CrossOrigin("*")
    @GetMapping(value="/updaterequest" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<RetourSupportTexte> updaterequest(@RequestParam("idpan") int idpan,
                                         @RequestParam("userid") int userid,
                                                  Principal principal){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listeResultats = new ArrayList<Object[]>();
        RetourSupportTexte rs;
        List<RetourSupportTexte> listeRetour = new ArrayList<RetourSupportTexte>();

        //
        listeResultats = emr.createQuery(
                "select a.emplacement, a.dateheure,  b.libelle as secteurs," +
                        "c.libelle as quartiers, d.libelle as villes, " +
                        "e.nom as users, a.datecreation, a.image from panneau a inner join " +
                        "secteur b on a.idsec = b.idsec inner join " +
                        "quartier c on b.idquar=c.idqua inner join " +
                        "ville d on c.idvill=d.idvil inner join " +
                        "utilisateur e on a.idusr=e.idusr " +
                        "where a.idpan= "+idpan).getResultList();
        emr.getTransaction().commit();

        //
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try{

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");

            StringBuilder contenu = new StringBuilder();
            Utilisateur utilis = utilisateurRepository.findByIdusr(userid);
            contenu.append("<h2>Bonjour "+utilis.getNom()+"</h2>");
            contenu.append("<div><p>Il vous ait demand&eacute; de proc&eacute;der &agrave; la " +
                    "mise &agrave; d'un support. Les coordonn&eacute;s de ce dernier " +
                    "sont donn&eacute;s ci-dessous.</p></div>");
            contenu.append("<p>Secteur : "+ String.valueOf(listeResultats.get(0)[2])+"</p>");
            contenu.append("<p>Quartier : "+ String.valueOf(listeResultats.get(0)[3])+"</p>");
            contenu.append("<p>Commune : "+ String.valueOf(listeResultats.get(0)[4])+"</p>");
            contenu.append("<p>Emplacement : "+ String.valueOf(listeResultats.get(0)[0])+"</p>");
            contenu.append("<p>&nbsp;</p>");
            contenu.append("<p>Illustration : pi&egrave;ce jointe </p>");

            String images = String.valueOf(listeResultats.get(0)[7]);
            //byte[] byteArray = Base64.decodeBase64(images);
            helper.addAttachment("Illustration.jpeg",
                    new ByteArrayResource(images.getBytes()));

            contenu.append("<img alt='Support' height='175' width='200' " +
                    "src='data:image/png;base64,"+images+"' />");

            //
            helper.setText(String.valueOf(contenu), true);
            helper.setTo(utilis.getEmail());
            helper.setSubject("Mise à jour d'un support publicitaire");
            helper.setFrom("bendressouarnaud@gmail.com");
            emailSender.send(mimeMessage);

            //
            rs = new RetourSupportTexte();
            rs.setEmplacement("ok");
            rs.setLibelle("ok");
            rs.setLatitude(1.0);
            rs.setLongitude(1.0);
            listeRetour.add(rs);


            //
            Requetepanneau rqtPan = new Requetepanneau();
            rqtPan.setIdpan(idpan);
            rqtPan.setIduser(userid);
            rqtPan.setIdadmin(2);
            rqtPan.setEtat(0);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            rqtPan.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            // Add :
            requetepanneauRepository.save(rqtPan);

            // track in the Journal :
            //Utilisateur user = utilisateurRepository.findByIdentifiant(principal.getName().trim());
            saveJournal(userid,
                    "Création d'une requete de mise à jour de support ID : "+idpan);
        }
        catch (Exception exc){
            rs = new RetourSupportTexte();
            rs.setEmplacement("pok");
            rs.setLibelle("pok");
            rs.setLatitude(1.0);
            rs.setLongitude(1.0);
            listeRetour.add(rs);

            saveJournal(userid,
                    "Erreur survenue pendant la création d'une mise à jour " +
                            "de requete support : "+exc.toString());
        }

        emr.close();

        //
        return listeRetour;
    }




}
