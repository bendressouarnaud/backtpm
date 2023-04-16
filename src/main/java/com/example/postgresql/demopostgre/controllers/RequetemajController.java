package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.*;
import com.example.postgresql.demopostgre.mesobjets.CalculTsp;
import com.example.postgresql.demopostgre.mesobjets.PanneauRqt;
import com.example.postgresql.demopostgre.mesobjets.UserProcess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RequetemajController {

    //  A T T R I B U T E S :
    @Autowired
    RequetepanneauRepository requetepanneauRepository;
    @Autowired
    PanneauRepository panneauRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    PanneauhistoRepository panneauhistoRepository;
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    TypesRepository typesRepository;
    @Autowired
    ParametresRepository parametresRepository;


    @Autowired
    public JavaMailSender emailSender;

    @PersistenceUnit
    EntityManagerFactory emf;



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


    // Liste des utilisateurs
    @GetMapping(value="/requetepan")
    public String getrequetepan(Model model){

        // Get the list of CLIENT :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> histoRequetes = emr.createQuery(
                "select a.nom , d.nom , c.libelle, b.dates, b.etat, b.idrqtpn" +
                        " from utilisateur a inner join requetepanneau b on a.idusr=b.idadmin inner join " +
                        " panneau c on b.idpan=c.idpan inner join utilisateur d on b.iduser=d.idusr").getResultList();
        model.addAttribute("histoRequetes", histoRequetes);
        emr.getTransaction().commit();
        emr.close();
        return "requetepanneau";
    }


    // Nouveau client
    @GetMapping(value="/nouvrequetepan")
    public String nouvrequetepan(Model model){
        // Users LIST :
        List<Utilisateur> listUtilisateur = utilisateurRepository.findByRoles("utilisateur");
        // Supports LIST :
        //List<Panneau> listPanneau = panneauRepository.findAll();
        //List<Panneau> listPanneau = panneauRepository.findAll();

        // Get
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> histoRequetes = emr.createQuery(
                "select a.nom as admin, d.nom as utilis, c.libelle, b.dates, b.etat, b.idrqtpn" +
                        " from utilisateur a inner join requetepanneau b on a.idusr=b.idadmin inner join " +
                        " panneau c on b.idpan=c.idpan inner join utilisateur d on b.iduser=d.idusr").getResultList();
        model.addAttribute("histoRequetes", histoRequetes);

        //
        List<Object[]> listePanneau = emr.createQuery(
                "select a.idpan , a.libelle from panneau a ").setMaxResults(100).getResultList();
        model.addAttribute("listePanneau", listePanneau);
        emr.getTransaction().commit();
        emr.close();

        model.addAttribute("listUtilisateur", listUtilisateur);
        //model.addAttribute("listPanneau", listPanneau);
        model.addAttribute("nouvrequetepan",1);
        return "requetepanneau";
    }


    // Ajouter nouvelle REQUETE :
    @PostMapping(value="/creerrequetepan")
    public String creerrequetepan(@RequestParam("panneau") String panneau,
                                  @RequestParam("utilisateur") int utilisateur,
                                  Model model, Principal principal) throws Exception{
        //
        Requetepanneau rqtPan = new Requetepanneau();
        rqtPan.setIdpan(Integer.parseInt(panneau));
        rqtPan.setIduser(utilisateur);
        rqtPan.setIdadmin(2);
        rqtPan.setEtat(0);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        rqtPan.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        // Add :
        requetepanneauRepository.save(rqtPan);

        // track in the Journal :
        Utilisateur user = utilisateurRepository.findByIdentifiant(principal.getName().trim());
        saveJournal(user.getIdusr(),
                "Création d'une requete de mise à jour de support ID : "+panneau);

        // Get the list of CLIENT :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> histoRequetes = emr.createQuery(
                "select a.nom , d.nom , c.libelle, b.dates, b.etat, b.idrqtpn" +
                        " from utilisateur a inner join requetepanneau b on a.idusr=b.idadmin inner join " +
                        " panneau c on b.idpan=c.idpan inner join utilisateur d on b.iduser=d.idusr").getResultList();
        model.addAttribute("histoRequetes", histoRequetes);
        emr.getTransaction().commit();
        emr.close();

        // Send the mail :
        Utilisateur utilis = utilisateurRepository.findByIdusr(utilisateur);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(utilis.getEmail());
        message.setSubject("Bonsoir");
        message.setText("Demande de MAJ du panneau 1");
        emailSender.send(message);

        return "requetepanneau";
    }


    // modifcommande
    @GetMapping(value="/modifrequetepan/{id}")
    public String modifrequetepan(@PathVariable int id,
                                  Model model)  {

        // Get :
        Requetepanneau rqtPan = requetepanneauRepository.findByIdrqtpn(id);
        model.addAttribute("requetepan", rqtPan);

        // Get the list of CLIENT :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> histoRequetes = emr.createQuery(
                "select a.nom , d.nom , c.libelle, b.dates, b.etat, b.idrqtpn" +
                        " from utilisateur a inner join requetepanneau b on a.idusr=b.idadmin inner join " +
                        " panneau c on b.idpan=c.idpan inner join utilisateur d on b.iduser=d.idusr").getResultList();
        model.addAttribute("histoRequetes", histoRequetes);
        emr.getTransaction().commit();
        emr.close();

        // Users LIST :
        List<Utilisateur> listUtilisateur = utilisateurRepository.findByRoles("utilisateur");
        // Supports LIST :
        List<Panneau> listPanneau = panneauRepository.findAll();
        model.addAttribute("listUtilisateur", listUtilisateur);
        model.addAttribute("listPanneau", listPanneau);

        return "requetepanneau";
    }


    // Ajouter nouveau client
    @PostMapping(value="/modifierrequetepan")
    public String modifierrequetepan(@RequestParam int id,
                                     @RequestParam("panneau") String panneau,
                                     @RequestParam("utilisateur") int utilisateur,
                                     Model model, Principal principal) throws Exception{
        //
        Requetepanneau rqtPan = requetepanneauRepository.findByIdrqtpn(id);
        rqtPan.setIdpan(Integer.parseInt(panneau));
        rqtPan.setIduser(utilisateur);
        rqtPan.setIdadmin(2);
        rqtPan.setEtat(1);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        rqtPan.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        // Add :
        requetepanneauRepository.save(rqtPan);

        // track in the Journal :
        Utilisateur user = utilisateurRepository.findByIdentifiant(principal.getName().trim());
        saveJournal(user.getIdusr(),
                "Modification d'une requete de mise à jour de support ID : "+panneau);


        // Get the list of CLIENT :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List<Object[]> histoRequetes = emr.createQuery(
                "select a.nom , d.nom , c.libelle, b.dates, b.etat, b.idrqtpn" +
                        " from utilisateur a inner join requetepanneau b on a.idusr=b.idadmin inner join " +
                        " panneau c on b.idpan=c.idpan inner join utilisateur d on b.iduser=d.idusr").getResultList();
        model.addAttribute("histoRequetes", histoRequetes);
        emr.getTransaction().commit();
        emr.close();
        return "requetepanneau";
    }


    // Get the requests sent to the user that makes the request :
    @GetMapping(value="/getsupportrqt/{iduser}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Panneau> getsupportrqtjson(@PathVariable int iduser){
        List<Requetepanneau> listeDonnees =
                requetepanneauRepository.findAllByIduserAndEtat(iduser, 0);
        // Now that we get the data, get the appropriate PANNEAU :
        List<Panneau> listPanneau = new ArrayList<Panneau>();
        for(Requetepanneau rqtpn : listeDonnees){
            // Get the PANNEAU
            listPanneau.add(panneauRepository.findByIdpan(rqtpn.getIdpan()));
        }
        return listPanneau;
    }

    // Get the requests sent to the user that makes the request :
    @GetMapping(value="/getsupportrqtcp/{iduser}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<PanneauRqt> getsupportrqtcp(@PathVariable int iduser){
        List<Requetepanneau> listeDonnees =
                requetepanneauRepository.findAllByIduserAndEtat(iduser, 0);
        // Now that we get the data, get the appropriate PANNEAU :
        List<PanneauRqt> listPanneauRqt = new ArrayList<PanneauRqt>();
        for(Requetepanneau rqtpn : listeDonnees){
            // Get the PANNEAU
            Panneau pn = panneauRepository.findByIdpan(rqtpn.getIdpan());
            listPanneauRqt.add(new PanneauRqt(pn, rqtpn.getIdrqtpn()));
        }
        return listPanneauRqt;
    }


    @GetMapping(value="/afficherhistopanneau/{id}")
    public String afficherhistopanneau(@PathVariable int id, Model model){
        List<Panneauhisto> listeDonnees = panneauhistoRepository.findAllByIdpan(id);
        model.addAttribute("listeDonnees", listeDonnees);
        return "histopanneau";
    }

    //getexecution
    @GetMapping(value="/getexecution")
    public String getexecution(){
        return "executionprixmanuel";
    }



    /*
    public HttpEntity<byte[]> creercommande(@RequestParam("dateexec") String dateexec){
        try {
            return genereExcel();
        }
        catch (Exception exc){

        }
        return null;
    }
    */
    @PostMapping(value="/soumettreexecution")
    public String creercommande(@RequestParam("dateexec") String dateexec){

        UserProcess up = new UserProcess();
        try {
            //computePrix(ClassFonction.retourDate(dateexec));
            computePrix();
        }
        catch (Exception exc){
            System.out.println("Exception : "+exc.toString());
        }

        return "executionprixmanuel";
    }



    // try to generate EXCEL file :
    public HttpEntity<byte[]> genereExcel() throws Exception{
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");

        // Add DATA :
        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");

        cell = row.createCell(1);
        cell.setCellValue(20);

        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        workbook.write(archivo);

        if(null!=workbook && null!=archivo) {
            workbook.close();
            archivo.close();
        }

        byte[] documentContent = archivo.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=myexcelfile.xlsx");
        headers.setContentLength(documentContent.length);
        return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
    }



    public void computePrix() {
        try {
            // First get SUPPORT types :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //Date dateS = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List <Object[]> resultatPanneau = em.createQuery(
                "select a.idsec,a.taille,a.types,a.superficie,a.idpan " +
                " from panneau a where a.idpan between 4563 and 4691").getResultList();
            /*
            List<Panneau> listeDesPanneaux =
                panneauRepository.findAllByIdpanBetween(1397,3019);
            */

                    //panneauRepository.findAll();//.findAllByDatecreation(date_s);
            //System.out.println("taille listeDesPanneaux  :  "+listeDesPanneaux.size());
            Date tpdatedebut = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-01-01");
            Date tpdatefin = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-12-31");
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String datej = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Browse :
            //Commande cmde;
            System.out.println("Taille = " + resultatPanneau.size());
            int i = 0;
            //for (Panneau pn : listeDesPanneaux) {
            for (Object[] objetTab : resultatPanneau) {
                i++;
                System.out.println("i = " + i + "  /  " + resultatPanneau.size());

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
                                "where idsec=" +
                                ((Integer)objetTab[0]) ).getSingleResult();
                //  pn.getIdsec()

                int populat = (Integer) population;
                emr.getTransaction().commit();
                emr.close();

                //
                // Compute the COST  -- pn.getTaille()
                Taille taille = tailleRepository.findByIdtai((Integer)objetTab[1]);
                CalculTsp cTsp = new CalculTsp(populat, (Integer)objetTab[2]);  // pn.getTypes()
                double cout = cTsp.calculMontantTsp(tpdatedebut, tpdatefin, taille.getValeur());
                cmde.setCout((int) cout);

                // Calculer L'ODP si necessaire :
                emr = emf.createEntityManager();
                emr.getTransaction().begin();
                Object taxeODP = emr.createQuery(
                        "select c.taux " +
                                "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                                "join ville c on b.idvill=c.idvil " +
                                "where idsec=" +((Integer)objetTab[0]) ).getSingleResult();
                // (pn.getIdsec()

                int taux = (Integer) taxeODP;
                emr.getTransaction().commit();
                emr.close();
                //    * * * * * * * * *   Superficie ODP
                taille = tailleRepository.findByIdtai((Integer)objetTab[3]); //pn.getSuperficie()
                double coutODP = cTsp.calculMontantOdp(tpdatedebut, tpdatefin, taille.getValeur(), taux);
                //System.out.println("Cout ODP : "+coutODP);
                cmde.setCoutodp((int) coutODP);

                cmde.setIdpan((Integer)objetTab[4]); //pn.getIdpan()
                // Client :
                cmde.setIdcli(1);

                Parametres prmts = parametresRepository.findByIdpar(1);
                if(prmts.getEnregsupport() == 1){
                    // Save :
                    commandeRepository.save(cmde);
                }
            }
        }
        catch (Exception exc){
            System.out.println("Exception exc :  "+exc.getLocalizedMessage());
        }
    }

    // return "redirect:/panneau";
    @GetMapping(value="/updateodpdata")
    public String updateodpdata(){
        try{
            computePrixActualiser();
        }
        catch (Exception exc){

        }

        return "redirect:/acc";
    }

    public void computePrixActualiser() {
        try {
            // First get SUPPORT types :
            //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //Date dateS = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);

            //System.out.println("Etape 0");

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List <Object[]> resPanneau = em.createQuery(
                "select distinct a.idpan,c.idtyp from commande a inner join panneau b on " +
                "a.idpan=b.idpan inner join types c on b.types=c.idtyp where c.idtyp " +
                "in (17,19) and a.coutodp > 0").getResultList();

            // Browse :
            System.out.println("Nombres : " + resPanneau.size());
            int compteur = 0;
            for (Object[] objetTab : resPanneau) {

                compteur ++;
                System.out.println(compteur + " / "+resPanneau.size());

                //Long val = (Integer)objetTab[0];
                int valeur = (Integer)objetTab[0];
                int valeurIdtyp = (Integer)objetTab[1];
                //System.out.println("Etape 2");
                //int valeur = val.intValue();
                //System.out.println("Etape 3");

                // Get PANNEAU :
                Panneau pn = panneauRepository.findByIdpan(valeur);

                // TRY To GET COMMANDE object :
                Commande cmde = commandeRepository.findByIdpan(valeur);

                //
                if(cmde != null) {
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
                                        "where idsec=" + pn.getIdsec()).getSingleResult();

                        int populat = (Integer) population;
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
                        if(valeurIdtyp == 56){
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
                            //    * * * * * * * * *   Superficie ODP :
                            tle = tailleRepository.findByIdtai(typs.getIdtail());
                            double coutODP = cTsp.calculMontantOdp(tpdatedt, tpdatefn,
                                    tle.getValeur(), taux);
                            cmde.setCoutodp((int) coutODP);
                        }
                        else cmde.setCoutodp(0);

                        //cmde.setCout(Integer.parseInt(cout));
                        // Panneau :
                        //cmde.setIdpan(id);
                        // Client :
                        //cmde.setIdcli(1);

                        Parametres prmts = parametresRepository.findByIdpar(1);
                        if(prmts.getEnregsupport() == 1){
                            // Save :
                            commandeRepository.save(cmde);
                        }

                    } catch (Exception exc) {
                        System.out.println("Exception survenue : " + exc.toString());
                    }
                }

            }


        }
        catch (Exception exc){
            //System.out.println("Exception exc :  "+exc.getLocalizedMessage());
        }
    }

}
